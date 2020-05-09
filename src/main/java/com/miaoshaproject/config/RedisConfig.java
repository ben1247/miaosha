package com.miaoshaproject.config;

import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.stereotype.Component;

/**
* 〈一句话功能简述〉<br>
* Redis 相关配置
*
* @author zhangyue
* @see [相关类/方法]（可选）
* @since [产品/模块版本] （可选）
* @date 2020/5/7 10:18 下午
*/
@Component
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 3600)
public class RedisConfig {

}
