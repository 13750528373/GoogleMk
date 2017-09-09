package com.kx.app2.base;

import com.google.gson.Gson;
import com.kx.app2.utils.Constans;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by KX on 2017/9/8.
 */

public abstract class BasicProtocol<T> {

    private Map<String, String> mParams;


    public void setParams(Map<String, String> params) {
        mParams = params;
    }

    public T loadData() throws Exception {

        OkHttpClient okHttpClient = new OkHttpClient();
        String url = getUrl();
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {

            ResponseBody body = response.body(); //取出返回的结果
            String jsonStr = body.string();

            ParameterizedType parameterizedtype = (ParameterizedType) getClass().getGenericSuperclass();
            Type[] actualTypeArguments = parameterizedtype.getActualTypeArguments();


            Gson gson = new Gson();
            return gson.fromJson(jsonStr, actualTypeArguments[0]);
        }
        return null;
    }

    public String getUrl() {
        String url = Constans.HOST;
        url += getInterfaceName();

        if (mParams != null && mParams.size() > 0) {
            url += "?";
            for (Map.Entry<String, String> entry : mParams.entrySet()) {
                url += entry.getKey();
                url += "=";
                url += entry.getValue();
                url += "&";
            }
            url = url.substring(0, url.length() - 1);
        }


        return url;
    }


    /**
     * url中接口名字，app home  game hot
     **/
    public abstract String getInterfaceName();
}
