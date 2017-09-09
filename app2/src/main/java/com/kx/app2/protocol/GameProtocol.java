package com.kx.app2.protocol;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kx.app2.bean.ApkItem;
import com.kx.app2.utils.Constans;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by KX on 2017/9/8.
 */

public class GameProtocol {

    public List<ApkItem> loadData(String index) throws Exception {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(Constans.HOST + "game?index=" + index).build();
        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {
            ResponseBody body = response.body();
            String jsonStr = body.string();
            Gson gson = new Gson();
            return gson.fromJson(jsonStr, new TypeToken<List<ApkItem>>() {
            }.getType());
        }
        return null;
    }
}
