package com.github.xuanyu66.tidbcloudtoolkit.backend.util;

import com.github.xuanyu66.tidbcloudtoolkit.backend.exception.GsonException;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.*;

public class GsonUtil {
    private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.disableHtmlEscaping();//禁止将部分特殊字符转义为unicode编码
        gson = gsonBuilder.create();
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(String json, Class<V> type) {
        return gson.fromJson(json, type);
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * JSON反序列化
     */
    public static <V> V from(String json, TypeToken<V> typeToken) {
        return gson.fromJson(json, typeToken.getType());
    }


    /**
     * JSON反序列化（List）
     */
    public static <V> List<V> fromList(String json, Class<V> type) {
        TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
        return gson.fromJson(json, typeToken.getType());
    }

    /**
     * JSON反序列化（Map）
     */
    public static Map<String, Object> fromMap(String json) {
        return gson.fromJson(json, new TypeToken<HashMap<String, Object>>() {
        }.getType());
    }

    /**
     * 序列化为JSON
     */
    public static <V> String to(List<V> list) {
        return gson.toJson(list);
    }

    /**
     * 序列化为JSON
     */
    public static <V> String to(V v) {
        return gson.toJson(v);
    }

    /**
     * 从json串中获取某个字段
     *
     * @return String，默认为 null
     */
    public static String getAsString(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        String propertyValue;
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            propertyValue = jsonByKey.getAsString();
        } catch (Exception e) {
            propertyValue = jsonByKey.toString();
        }
        return propertyValue;
    }

    /**
     * 从json串中获取某个字段
     *
     * @return int，默认为 0
     */
    public static int getAsInt(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0;
        }
        try {
            return jsonByKey.getAsInt();
        } catch (Exception e) {
            throw new GsonException("gson get int error", e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return long，默认为 0
     */
    public static long getAsLong(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0L;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0L;
        }
        try {
            return jsonByKey.getAsLong();
        } catch (Exception e) {
            throw new GsonException("gson get long error", e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return double，默认为 0.0
     */
    public static double getAsDouble(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return 0.0;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return 0.0;
        }
        try {
            return jsonByKey.getAsDouble();
        } catch (Exception e) {
            throw new GsonException("gson get double error", e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return boolean, 默认为 false
     */
    public static boolean getAsBoolean(String json, String key) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        JsonPrimitive jsonByKey = (JsonPrimitive) getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return false;
        }
        try {
            if (jsonByKey.isBoolean()) {
                return jsonByKey.getAsBoolean();
            } else {
                if (jsonByKey.isString()) {
                    String string = jsonByKey.getAsString();
                    if ("1".equals(string)) {
                        return true;
                    } else {
                        return BooleanUtils.toBoolean(string);
                    }
                } else {//number
                    return BooleanUtils.toBoolean(jsonByKey.getAsInt());
                }
            }
        } catch (Exception e) {
            throw new GsonException("gson get boolean error", e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return object, 默认为 null
     */
    public static <V> V getAsObject(String json, String key, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            return from(jsonByKey.getAsString(), type);
        } catch (Exception e) {
            throw new GsonException("gson get list error", e);
        }
    }

    /**
     * 从json串中获取某个字段
     *
     * @return list, 默认为 null
     */
    public static <V> List<V> getAsList(String json, String key, Class<V> type) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        JsonElement jsonByKey = getAsJsonObject(json, key);
        if (null == jsonByKey) {
            return null;
        }
        try {
            JsonArray jsonArray = jsonByKey.getAsJsonArray();
            TypeToken<List<V>> typeToken = (TypeToken<List<V>>) TypeToken.getParameterized(ArrayList.class, type);
            return from(jsonArray.toString(), typeToken);
        } catch (Exception e) {
            throw new GsonException("gson get list error", e);
        }
    }

    /**
     * 从json串中获取某个字段
     */
    public static JsonElement getAsJsonObject(String json, String key) {
        try {
            JsonElement element = JsonParser.parseString(json);
            JsonObject jsonObj = element.getAsJsonObject();
            return jsonObj.get(key);
        } catch (JsonSyntaxException e) {
            throw new GsonException("gson get object from json error", e);
        }
    }
}