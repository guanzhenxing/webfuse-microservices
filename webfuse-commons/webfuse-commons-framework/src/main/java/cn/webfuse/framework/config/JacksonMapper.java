package cn.webfuse.framework.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;

public class JacksonMapper {

    private JacksonMapper() {
    }

    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        //小写下划线输出
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
        mapper.setDefaultPropertyInclusion(JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.ALWAYS));

        mapper.configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);

        // 序列化BigDecimal时之间输出原始数字还是科学计数，默认false，即是否以toPlainString()科学计数方式来输出
        mapper.configure(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN, true);

        //设定是否使用Enum的toString函数来读取Enum, 为False时使用Enum的name()函数来读取Enum,
        mapper.configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true);

        // 如果输入不存在的字段时不会报错
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // 使用默认的Jsckson注解
        mapper.setAnnotationIntrospector(new JacksonAnnotationIntrospector());

        // 忽略无法转换的对象
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static void setMapper(ObjectMapper mapper) {
        JacksonMapper.mapper = mapper;
    }

    public static <T> T parse(String json, Class<T> objectType) throws IOException {
        if (json == null) {
            return null;
        }
        Assert.notNull(objectType, "objectType cannot be null.");
        return mapper.readValue(json, objectType);
    }

    public static <T> T parse(InputStream stream, Class<T> objectType) throws IOException {
        Assert.notNull(stream, "stream cannot be null.");
        Assert.notNull(objectType, "objectType cannot be null.");
        return mapper.readValue(stream, objectType);
    }

    public static String toJson(Object obj) throws IOException {
        return mapper.writeValueAsString(obj);
    }

}

