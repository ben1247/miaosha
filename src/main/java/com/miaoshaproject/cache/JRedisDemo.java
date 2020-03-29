package com.miaoshaproject.cache;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

public class JRedisDemo {


    public static void pipeline1(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        long st = System.currentTimeMillis();

        Pipeline pipeline = jedis.pipelined();
        for(int i = 0; i < 100000; i++){
            pipeline.set("key" + i , "val" + i);
            pipeline.del("key" + i);
        }
        pipeline.sync();

        long et = System.currentTimeMillis();
        System.out.println("总共话费： " + (et - st) + " ms");
    }

    public static void pipeline2(){
        Jedis jedis = new Jedis("127.0.0.1",6379);
        long st = System.currentTimeMillis();

        for (int i = 0; i < 100000; i++){
            jedis.set("key" + i , "val" + i);
            jedis.del("key" + i);
        }

        long et = System.currentTimeMillis();
        System.out.println("总共话费： " + (et - st) + " ms");
    }


    public static void main(String [] args){
//        pipeline1();

//        pipeline2();
    }

}
