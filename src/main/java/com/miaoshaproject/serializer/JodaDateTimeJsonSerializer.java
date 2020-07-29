package com.miaoshaproject.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.joda.time.DateTime;

import java.io.IOException;

/**
* 〈一句话功能简述〉<br>
* redis 支持 DateTime 的序列化类
*
* @author zhangyue
* @see [相关类/方法]（可选）
* @since [产品/模块版本] （可选）
* @date 2020/7/29 10:39 下午
*/
public class JodaDateTimeJsonSerializer extends JsonSerializer<DateTime> {


    @Override
    public void serialize(DateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(dateTime.toString("yyyy-MM-dd HH:ss:mm"));
    }
}
