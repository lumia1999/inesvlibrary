package com.inesv.library.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chan on 2016/11/23.
 * <p>
 * json解析生成工具类
 */

public class JsonUtil {
    private final static String TAG = "JsonUtil";

    /**
     * 解析json
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T json2Bean(String jsonString, Class<T> clazz) {
        if (StringUtil.isEmpty(jsonString)) {
            return null;
        }
        Gson gson = new Gson();
        T t = null;
        try {
            LogUtil.e(TAG, "jsonParseToBean:" + jsonString);
            t = gson.fromJson(jsonString, clazz);
            return t;
        } catch (Exception e) {
            LogUtil.e(TAG, "json解析异常:" + jsonString);
            e.printStackTrace();
            return t;
        }
    }

    /**
     * 解析纯数组json
     *
     * @param jsonString
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> ArrayList<T> array2Bean(String jsonString, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {
        }.getType();
        ArrayList<JsonObject> jsonObjects = new Gson().fromJson(jsonString, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(new Gson().fromJson(jsonObject, clazz));
        }
        return arrayList;
    }

    /**
     * 将map转换成json
     *
     * @param map
     * @return
     */
    public static String map2Json(Map<String, String> map) {
        if (map == null) {
            LogUtil.d(TAG, "bean2son:map不能为空!");
            return "";
        }
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .disableHtmlEscaping()
                .create();
        return gson.toJson(map);
    }
}
