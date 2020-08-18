package com.miaoshaproject.mq;

import com.alibaba.fastjson.JSON;
import com.miaoshaproject.dao.StockLogDOMapper;
import com.miaoshaproject.dataobject.StockLogDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.OrderService;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Component
public class MqProducer {

    private DefaultMQProducer producer;

    private TransactionMQProducer transactionProducer;

    @Value("${mq.nameserver.addr}")
    private String nameAddr;
    @Value("${mq.topicname}")
    private String topicName;

    @Autowired
    private OrderService orderService;

    @Autowired
    private StockLogDOMapper stockLogDOMapper;

    @PostConstruct
    public void init() throws MQClientException {
        // 做mq producer的初始化
        producer = new DefaultMQProducer("producer_group");
        producer.setNamesrvAddr(nameAddr);
        producer.start();

        transactionProducer = new TransactionMQProducer("transaction_producer_group");
        transactionProducer.setNamesrvAddr(nameAddr);
        transactionProducer.start();
        transactionProducer.setTransactionListener(new TransactionListener() {
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object arg) {
                // 真正要做的事，创建订单
                Long userId = (Long)((Map)arg).get("userId");
                Long itemId = (Long)((Map)arg).get("itemId");
                Long promoId = (Long)((Map)arg).get("promoId");
                Integer amount = (Integer)((Map)arg).get("amount");
                String stockLogId = (String)((Map)arg).get("stockLogId");
                try {
                    orderService.createOrder(userId,itemId,promoId,amount,stockLogId);
                } catch (BusinessException e) {
                    e.printStackTrace();
                    // 设置对应的stockLog为回滚状态
                    StockLogDO stockLogDO = stockLogDOMapper.selectByPrimaryKey(stockLogId);
                    stockLogDO.setStatus(3);
                    stockLogDOMapper.updateByPrimaryKeySelective(stockLogDO);
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }
                return LocalTransactionState.COMMIT_MESSAGE;
            }

            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                // 根据是否扣减库存成功，来判断要返回COMMIT、ROLLBACK还是继续UNKNOWN
                String jsonString = new String(msg.getBody());
                Map<String,Object> map = JSON.parseObject(jsonString, Map.class);
                Long itemId = Long.parseLong((String) map.get("itemId"));
                Integer amount = Integer.parseInt((String) map.get("amount"));
                String stockLogId= (String)map.get("stockLogId");
                StockLogDO stockLogDO = stockLogDOMapper.selectByPrimaryKey(stockLogId);
                if (stockLogDO == null){
                    return LocalTransactionState.UNKNOW;
                }

                if (stockLogDO.getStatus() == 2){
                    return LocalTransactionState.COMMIT_MESSAGE;
                }else if (stockLogDO.getStatus() == 1){
                    return LocalTransactionState.UNKNOW;
                }
                return LocalTransactionState.ROLLBACK_MESSAGE;
            }
        });
    }

    /**
    * 〈一句话功能简述〉
    * 事务型同步库存扣减消息
    *
    * @author zhangyue
    * @see [相关类/方法]（可选）
    * @since [产品/模块版本] （可选）
     * @param itemId
     * @param amount
    * @Return
    * @date 2020/8/16 4:54 下午
    */
    public boolean transactionAsyncReduceStock(Long userId,Long itemId,Long promoId,Integer amount,String stockLogId){

        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("itemId",String.valueOf(itemId));
        bodyMap.put("amount",String.valueOf(amount));
        bodyMap.put("stockLogId",stockLogId);
        Message message = new Message(topicName,"increase",
                JSON.toJSON(bodyMap).toString().getBytes(StandardCharsets.UTF_8));

        Map<String,Object> argsMap = new HashMap<>();
        argsMap.put("userId",userId);
        argsMap.put("itemId",itemId);
        argsMap.put("promoId",promoId);
        argsMap.put("amount",amount);
        argsMap.put("stockLogId",stockLogId);

        TransactionSendResult sendResult = null;
        try {
            sendResult = transactionProducer.sendMessageInTransaction(message,argsMap); // 往消息队列投递一条prepare消息
        } catch (MQClientException e) {
            e.printStackTrace();
            return false;
        }

        if (sendResult.getLocalTransactionState() == LocalTransactionState.ROLLBACK_MESSAGE){
            return false;
        }else if (sendResult.getLocalTransactionState() == LocalTransactionState.COMMIT_MESSAGE){
            return true;
        }else {
            return false;
        }
    }

    /**
    * 〈一句话功能简述〉
    * 同步库存扣减消息
    *
    * @author zhangyue
    * @see [相关类/方法]（可选）
    * @since [产品/模块版本] （可选）
    * @param itemId
    * @param amount
    * @Return
    * @date 2020/8/15 4:42 下午
    */
    public boolean asyncReduceStock(Long itemId,Integer amount){
        Map<String,Object> bodyMap = new HashMap<>();
        bodyMap.put("itemId",String.valueOf(itemId));
        bodyMap.put("amount",String.valueOf(amount));
        Message message = new Message(topicName,"increase",
                JSON.toJSON(bodyMap).toString().getBytes(StandardCharsets.UTF_8));
        try {
            producer.send(message);
        } catch (MQClientException e) {
            e.printStackTrace();
            return false;
        } catch (RemotingException e) {
            e.printStackTrace();
            return false;
        } catch (MQBrokerException e) {
            e.printStackTrace();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
