package com.inesv.library.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by chan on 2016/11/23.
 * <p>
 * 网络请求工具类
 */

public class HttpUtil {
    private final static String TAG = "HttpUtil";
    private static OkHttpClient client;

    static {
        client = new OkHttpClient();
    }

    /**
     * 以body提交参数的get请求
     *
     * @param map
     * @param url
     * @param callback
     */
    public static void getDataFromServer(Map<String, String> map, String url, Callback callback) {
        if (!StringUtil.isEmpty(url)) {
            String requestUrl = "";
            if (map != null) {
                List<Map.Entry<String, String>> list = new ArrayList<>();
                Set<Map.Entry<String, String>> entrys = map.entrySet();
                for (Map.Entry<String, String> entry : entrys) {
                    list.add(entry);
                }
                int entrySize = list.size();
                if (entrySize == 0) {
                    requestUrl = url;
                } else if (entrySize > 0) {
                    for (int i = 0; i < entrySize; i++) {
                        if (i == 0) {
                            requestUrl = url + "?" + list.get(i).getKey() + "=" + list.get(i).getValue();
                        } else {
                            requestUrl = requestUrl + "&" + list.get(i).getKey() + "=" + list.get(i).getValue();
                        }
                    }
                }
            } else {
                requestUrl = url;
            }
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .get()
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
            LogUtil.e(TAG, "requestUrl:" + requestUrl);
        } else {
            LogUtil.e(TAG, "请求地址不能为空!");
        }
    }

    /**
     * 以json提交参数的get请求
     *
     * @param map
     * @param url
     * @param callback
     */
    public static void getJsonFromServer(Map<String, String> map, String url, Callback callback) {
        if (!StringUtil.isEmpty(url)) {
            String requestUrl = "";
            if (map != null && map.size() > 0) {
                requestUrl = url + "?data=" + JsonUtil.map2Json(map);
            } else {
                requestUrl = url;
            }
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .get()
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
            LogUtil.e(TAG, "requestUrl:" + requestUrl);
        } else {
            LogUtil.e(TAG, "请求地址不能为空!");
        }
    }

    /**
     * 以body提交参数的post请求
     *
     * @param map
     * @param url
     * @param callback
     */
    public static void postDataFromServer(Map<String, String> map, String url, Callback callback) {
        if (!StringUtil.isEmpty(url)) {
            FormBody.Builder builder = new FormBody.Builder();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    builder.add(entry.getKey(), entry.getValue());
                }
            }
            FormBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
            LogUtil.e(TAG, "map:" + map);
        } else {
            LogUtil.e(TAG, "请求地址不能为空!");
        }
    }

    /**
     * 以json提交参数的post请求
     *
     * @param map
     * @param url
     * @param callback
     */
    public static void postJsonFromServer(Map<String, String> map, String url, Callback callback) {
        if (!StringUtil.isEmpty(url)) {
            FormBody.Builder builder = new FormBody.Builder();
            if (map != null) {
                builder.add("data", JsonUtil.map2Json(map));
            }
            FormBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
            LogUtil.e(TAG, "map:" + map);
        } else {
            LogUtil.e(TAG, "请求地址不能为空!");
        }
    }

    /**
     * 以body方式提交参数并上传文件的请求
     *
     * @param map
     * @param url
     * @param callback
     */
    public static void postDataAndFile(Map<String, String> map, String url, String filePath, Callback callback) {
        File file = null;
        if (!StringUtil.isEmpty(filePath)) {
            file = new File(filePath);
        }
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
        if (!StringUtil.isEmpty(url)) {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            if (map != null) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    String value = entry.getValue();
                    if (value.contains(".jpg") || value.contains(".JPG") || value.contains(".png") || value.contains(".PNG") || value.contains(".f") || value.contains(".png")) {
                        builder.addFormDataPart(entry.getKey(), entry.getValue(), body);
                    } else {
                        builder.addFormDataPart(entry.getKey(), entry.getValue());
                    }
                }
            }
            MultipartBody multipartBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(multipartBody)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(callback);
            LogUtil.e(TAG, "map:" + map);
        } else {
            LogUtil.e(TAG, "请求地址不能为空!");
        }
    }
}
