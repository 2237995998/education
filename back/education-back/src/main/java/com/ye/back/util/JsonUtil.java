package com.ye.back.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.ye.back.response.Result;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * @PackageName:com.ye.background.util
 * @ClassName:JsonUtil
 * @Description:
 * @author:何进业
 * @date:2021/5/24 16:36
 */
public class JsonUtil {

    public static <E, T> T getJavaBeanInMapData(String json, E key, Class<T> tClass){
        Map<E, T> data = getData(json, Map.class);
        return JSON.parseObject(JSON.toJSONString(data.get(key)), tClass);

    }

    public static <E, T> List<T> getJavaBeanInMapListData(String json, E key, Class<T> tClass){
        String jsonString = JSON.toJSONString(getData(json, Map.class).get(key));
        return JSONObject.parseObject(jsonString, buildType(List.class, tClass));
    }



    public static int getCode(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        return (int) jsonObject.get("code");
    }

    public static String getMessage(String json) {
        JSONObject jsonObject = JSON.parseObject(json);
        return (String) jsonObject.get("message");
    }

    public static <T> T getData(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, new TypeReference<Result<T>>(clazz) {}).getData();
    }

    public static <T> List<T> getListData(String json, Class<T> clazz) {
        return parseListResult(json, clazz).getData();
    }

    public static <T> Result<T> parseResult(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, new TypeReference<Result<T>>(clazz) {
        });
    }

    public static <T> Result<List<T>> parseListResult(String json, Class<T> clazz) {
        return JSONObject.parseObject(json, buildType(Result.class, List.class, clazz));
    }

    public static Type buildType(Type... types) {
        ParameterizedTypeImpl beforeType = null;
        if (types != null && types.length > 0) {
            for (int i = types.length - 1; i > 0; i--) {
                beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types[i] : beforeType}, null, types[i - 1]);
            }
        }
        return beforeType;
    }
}
