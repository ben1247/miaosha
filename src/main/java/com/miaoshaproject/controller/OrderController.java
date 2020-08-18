package com.miaoshaproject.controller;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.mq.MqProducer;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller("order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials="true",allowedHeaders = "*")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MqProducer mqProducer;

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/create",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(
                                        @RequestParam(name = "itemId") Long itemId,
                                        @RequestParam(name = "amount") Integer amount,
                                        @RequestParam(name = "promoId",required = false) Long promoId) throws BusinessException {

        // 获取用户的登录信息
//        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        String token = httpServletRequest.getParameterMap().get("token")[0];
        if (StringUtils.isEmpty(token)){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"用户未登录，不能下单");
        }
        // 获取登录用户信息
//        UserModel userModel = (UserModel)httpServletRequest.getSession().getAttribute("LOGIN_USER");
        UserModel userModel = (UserModel)redisTemplate.opsForValue().get(token);
        if (userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"用户未登录，不能下单");
        }

        // 判断库存是否已售罄，若对应的售罄key存在，则直接返回下单失败
        Boolean stockInvalid = redisTemplate.hasKey("promo_item_stock_invalid_"+itemId);
        if (stockInvalid != null && stockInvalid){
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }

        //加入库存流水init状态
        String stockLogId = itemService.initStockLog(itemId,amount);

        //再去完成对应的下单事务型消息机制
//        orderService.createOrder(userModel.getId(),itemId,promoId,amount);
        if (!mqProducer.transactionAsyncReduceStock(userModel.getId(),itemId,promoId,amount,stockLogId)){
            throw new BusinessException(EmBusinessError.UNKNOWN_ERROR,"下单失败");
        }

        return CommonReturnType.create(null);
    }


}
