package com.miaoshaproject.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @ClassName MongoConfig
 * @Description MongoDB Config
 * @Author ZhangYue
 * @Date 2021/3/15 16:17
 **/
@Configuration
public class MongoConfig {

    @Value("${mongo.uri}")
    private String uri;
    @Value("${mongo.maxConnectionsPerHost}")
    private Integer maxConnectionsPerHost;

    private final static String DATABASE_NAME = "miaosha";

    @Bean
    public MongoTemplate mongoTemplate(){
        MongoClientURI mongoClientURI = new MongoClientURI(uri, MongoClientOptions.builder().connectionsPerHost(maxConnectionsPerHost));
        MongoClient client = new MongoClient(mongoClientURI);
        return new MongoTemplate(client,DATABASE_NAME);
    }

}
