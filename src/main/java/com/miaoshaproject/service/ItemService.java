package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;
import java.util.Map;

public interface ItemService {

    // 创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    // 商品列表浏览
    List<ItemModel> listItem(Map<String,Object> condition);

    // 商品详情浏览
    ItemModel getItemById(Long id);

    // item及promo model缓存模型
    ItemModel getItemByIdInCache(Long id);

    // 库存扣减（redis）
    boolean decreaseStock(Long itemId,Integer amount) throws BusinessException;

    // 库存回补
    boolean increaseStock(Long itemId,Integer amount);

    // 异步扣减（mq+db）
    boolean asyncDecreaseStock(Long itemId,Integer amount);

    // 商品销量增加
    void increaseSales(Long itemId,Integer amount);

    // 初始化库存流水
    String initStockLog(Long itemId,Integer amount);

    // 同步商品信息到mongo
    void syncItemToMongo();
}
