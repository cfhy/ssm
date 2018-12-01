package com.yyb.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * fastjson工具类
 */
public class FastJsonUtils {
 
    private static final SerializeConfig config;
 
    static {
        config = new SerializeConfig();
        // 使用和json-lib兼容的日期输出格式
        config.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        // 使用和json-lib兼容的日期输出格式
        config.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }
 
    private static final SerializerFeature[] features = {
            // 输出空置字段
            SerializerFeature.WriteMapNullValue,
            // list字段如果为null，输出为[]，而不是null
            SerializerFeature.WriteNullListAsEmpty,
            // 数值字段如果为null，输出为0，而不是null
            SerializerFeature.WriteNullNumberAsZero,
            // Boolean字段如果为null，输出为false，而不是null
            SerializerFeature.WriteNullBooleanAsFalse,
            // 字符类型字段如果为null，输出为""，而不是null
            SerializerFeature.WriteNullStringAsEmpty
    };

    /**
     * 对象转json字符串
     * @param object
     * @return
     */
    public static String objectToJson(Object object) {
        return JSON.toJSONString(object, config, features);
    }

    /**
     * 对象转json字符串,不对null进行处理
     * @param object
     * @return
     */
    public static String  objectToJsonNoFeature(Object object) {
        return JSON.toJSONString(object, config);
    }

    /**
     * json字段串转Object对象
     * @param text
     * @return
     */
    public static Object jsonToBean(String text) {
        return JSON.parse(text);
    }

    /**
     * json字段串转为指定类型对象
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToBean(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    /**
     * json字段串转换为Object数组
     * @param text
     * @return
     */
    public static Object[] jsonToArray(String text) {
        return jsonToArray(text, null);
    }

    /**
     * json字段串转换为指定类型的数组
     * @param text
     * @return
     */
    public static <T> Object[] jsonToArray(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz).toArray();
    }
 
    // 转换为List
    public static <T> List<T> jsonToList(String text, Class<T> clazz) {
        return JSON.parseArray(text, clazz);
    }
    
    /**
     * 将string转化为序列化的json字符串
     * @param text
     * @return
     */
    public static Object textToJson(String text) {
        Object objectJson  = JSON.parse(text);
        return objectJson;
    }
    
    /**
     * json字符串转化为map
     * @param s
     * @return
     */
    public static <K, V> Map<K, V>  jsonToMap(String s) {
    	Map<K, V> m = (Map<K, V>) JSONObject.parseObject(s);
        return m;
    }

}
 
