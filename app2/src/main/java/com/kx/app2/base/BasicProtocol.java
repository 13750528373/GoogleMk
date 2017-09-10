package com.kx.app2.base;

import android.os.Environment;

import com.google.gson.Gson;
import com.kx.app2.utils.Constans;
import com.kx.app2.utils.UiUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
        T t = getFromLocal();//拿缓存
        //缓存
        if(t != null){
            return  t;
        }

        return getServerData();
    }

    //读取缓存
    public T getFromLocal() {
        File file = getCacheFile();//拿缓存
        BufferedReader bufferedReader = null;

        try {
            bufferedReader =  new BufferedReader(new FileReader(file));
            String line = bufferedReader.readLine();

            return parseJson(line);
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public File getCacheFile() {
        File dir = null;
        if(Environment.getExternalStorageDirectory().equals(Environment.MEDIA_MOUNTED)){
            dir = new File(Environment.getExternalStorageDirectory(),"/Android/data/GoogleMk/gson");
        }else{
            dir = new File(UiUtils.getContext().getCacheDir(),"gson");
        }
        if(!dir.exists()){
            dir.mkdirs();
        }
        String fileName = MD5Utils.encode(getUrl());
        File file = new File(dir, fileName);


        return file;
    }

    //刷新数据
    private T getServerData() throws IOException {
        OkHttpClient okHttpClient = new OkHttpClient();
        String url = getUrl();
        Request request = new Request.Builder().url(url).build();
        Response response = okHttpClient.newCall(request).execute();

        if (response.isSuccessful()) {

            ResponseBody body = response.body(); //取出返回的结果
            String jsonStr = body.string();
            T t = parseJson(jsonStr);
            saveJson(jsonStr);


            return t;
        }
        return null;
    }

    private void saveJson(String jsonStr) {
        File file = getCacheFile();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));
            bufferedWriter.write(jsonStr);
            bufferedWriter.flush();//fulsh 直接写到目的地，而write写入底层的一个数组中
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private T parseJson(String jsonStr) {
        Gson gson = new Gson();
        ParameterizedType parameterizedtype = (ParameterizedType) getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedtype.getActualTypeArguments();
        return gson.fromJson(jsonStr, actualTypeArguments[0]);
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
