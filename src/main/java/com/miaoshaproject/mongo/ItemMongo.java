package com.miaoshaproject.mongo;

import com.miaoshaproject.dataobject.ItemDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ItemMongo
 * @Description 商品的mongodb存储类
 * @Author ZhangYue
 * @Date 2021/3/15 17:21
 **/
@Component
public class ItemMongo {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * @MethodName: saveItem
     * @Description 保存单个商品
     * @Author ZhangYue
     * @Date 2021/3/15 18:25
     * @Param [item]
     * @Return void
     **/
    public void saveItem(ItemDO item){
        mongoTemplate.save(item);
    }

    /**
     * @ClassName ItemMongo
     * @Description 批量保存商品
     * @Author ZhangYue
     * @Date 2021/3/15 18:25
    **/
    public void batchSaveItem(List<ItemDO> itemList){
        mongoTemplate.insertAll(itemList);
    }
}
