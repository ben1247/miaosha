package com.miaoshaproject.mongo;

import com.miaoshaproject.constant.Constant;
import com.miaoshaproject.dataobject.ItemDO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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

    public List<ItemDO> selectItem(Map<String,Object> condition){
        Query query = new Query();

        // 条件
        Criteria criteria = new Criteria();
        if (condition.get("title") != null){
            String title = (String) condition.get("title");
            // 模糊查询以 【^】开始 以【$】结束 【.*】相当于Mysql中的%
//            Pattern pattern = Pattern.compile("^.*"+title+".*$",Pattern.CASE_INSENSITIVE);
            criteria.and("title").regex("^.*"+title+".*$");
        }
        query.addCriteria(criteria);

        // 分页
        Integer pageNo = (Integer) condition.get("pageNo");
        Integer pageSize = (Integer) condition.get("pageSize");
        query.skip((pageNo - 1) * pageSize);
        query.limit(pageSize);

        // 排序
        query.with(Sort.by(Sort.Order.desc("sales")));

        List<ItemDO> itemList = mongoTemplate.find(query,ItemDO.class, Constant.MongoCollections.MONGO_COLLECTION_ITEM);
        return itemList;
    }

//    public List<ItemDO> selectItem(Map<String,Object> condition){
//        String title = (String) condition.get("title");
//        Query query = new Query(Criteria.where("title").is(title));
//        return mongoTemplate.find(query,ItemDO.class);
//    }
}
