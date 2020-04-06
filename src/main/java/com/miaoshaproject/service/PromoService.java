package com.miaoshaproject.service;

import com.miaoshaproject.service.model.PromoModel;

/**
* 〈一句话功能简述〉<br>
* 秒杀服务
*
* @author zhangyue
* @see [相关类/方法]（可选）
* @since [产品/模块版本] （可选）
* @date 2020/4/6 4:18 下午
*/
public interface PromoService {

    // 根据itemId获取即将进行的或正在进行的秒杀活动
    PromoModel getPromoByItemId(Long itemId);

}
