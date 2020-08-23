package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.PromoDOMapper;
import com.miaoshaproject.dataobject.PromoDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.PromoService;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.PromoModel;
import com.miaoshaproject.service.model.UserModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Override
    public PromoModel getPromoByItemId(Long itemId) {

        // 获取对应商品的秒杀活动信息
        PromoDO promoDO = promoDOMapper.selectByItemId(itemId);

        // dataobject -> model
        PromoModel promoModel = convertFromDataObject(promoDO);
        if (promoModel == null){
            return null;
        }

        // 判断秒杀活动即将进行的或正在进行
        if (promoModel.getStartDate().isAfterNow()){
            // 还未开始
            promoModel.setStatus(1);
        }else if (promoModel.getEndDate().isBeforeNow()){
            // 已结束
            promoModel.setStatus(3);
        }else {
            // 活动进行中
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    @Override
    public void publishPromo(Long promoId) {
        PromoDO promoDO = promoDOMapper.selectByPrimaryKey(promoId);
        if (promoDO == null || promoDO.getItemId() == null || promoDO.getItemId()==0){
            return;
        }
        ItemModel itemModel = itemService.getItemById(promoDO.getItemId());

        // 将库存同步到redis内
        redisTemplate.opsForValue().set("promo_item_stock_"+itemModel.getId(),itemModel.getStock());
        if (itemModel.getStock() >0){
            Boolean stockInvalid = redisTemplate.hasKey("promo_item_stock_invalid_"+promoDO.getItemId());
            if (stockInvalid != null && stockInvalid){
                // 删除库存售罄不足的
                redisTemplate.delete("promo_item_stock_invalid_"+promoDO.getItemId());
            }
        }
    }

    @Override
    public String generatePromoToken(Long promoId,Long itemId,Long userId) {
        PromoDO promoDO = promoDOMapper.selectByPrimaryKey(promoId);

        PromoModel promoModel = convertFromDataObject(promoDO);
        if (promoModel == null){
            return null;
        }

        // 判断秒杀活动即将进行的或正在进行
        if (promoModel.getStartDate().isAfterNow()){
            // 还未开始
            promoModel.setStatus(1);
        }else if (promoModel.getEndDate().isBeforeNow()){
            // 已结束
            promoModel.setStatus(3);
        }else {
            // 活动进行中
            promoModel.setStatus(2);
        }
        // 判断活动是否正在进行
        if (promoModel.getStatus() != 2){
            return null;
        }

        // 判断item信息是否存在
        ItemModel itemModel = itemService.getItemByIdInCache(itemId);
        if (itemModel == null){
            return null;
        }

        // 判断用户信息是否存在
        UserModel userModel = userService.getUserByIdInCache(userId);
        if (userModel == null){
            return null;
        }

        // 生成token并且存入redis内并给一个5分钟的有效期
        String token = UUID.randomUUID().toString().replace("-","");
        redisTemplate.opsForValue().set("promo_token_"+promoId+"_userid_"+userId+"_itemid_"+itemId,token);
        redisTemplate.expire("promo_token_"+promoId+"_userid_"+userId+"_itemid_"+itemId,5, TimeUnit.MINUTES);
        return token;
    }

    private PromoModel convertFromDataObject(PromoDO promoDO){
        if (promoDO == null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO,promoModel);
        promoModel.setPromoItemPrice(BigDecimal.valueOf(promoDO.getPromoItemPrice()));
        promoModel.setStartDate(new DateTime(promoDO.getStartDate()));
        promoModel.setEndDate(new DateTime(promoDO.getEndDate()));
        return promoModel;
    }
}
