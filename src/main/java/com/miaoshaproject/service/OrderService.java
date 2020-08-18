package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.OrderModel;

public interface OrderService {

    // 方案一(推荐)：通过前端url上传过来秒杀活动id。然后下单接口内校验对应id是否属于对应商品且活动已开始
    // 方案二：直接在下单接口内判断对应的商品是否存在秒杀活动，若存在进行中的则以秒杀价格下单
    OrderModel createOrder(Long userId,Long itemId,Long promoId, Integer amount,String stockLogId) throws BusinessException;

}
