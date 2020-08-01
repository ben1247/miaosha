package com.miaoshaproject.service;

/**
* 〈一句话功能简述〉<br>
* 封装本地缓存操作类
*
* @author zhangyue
* @see [相关类/方法]（可选）
* @since [产品/模块版本] （可选）
* @date 2020/8/1 9:56 下午
*/
public interface CacheService {
    // 存方法
    void setCommonCache(String key , Object value);

    // 取方法
    Object getFromCommonCache(String key);
}
