package com.pzr.desktoppet.utils.easyhttp.token;

import android.text.TextUtils;


import com.pzr.desktoppet.utils.easyhttp.interceptor.BaseDynamicInterceptor;

import java.util.TreeMap;

import okhttp3.Headers;

/**
 * <p>描述： </p>
 *
 * @author by houJia<br>
 * 2018-9-5 <br>
 */
public class DynamicTokenInterceptor extends BaseDynamicInterceptor<DynamicTokenInterceptor> {
    @Override
    public TreeMap<String, String> dynamicBody(TreeMap<String, String> dynamicMap) {
        return dynamicMap;
    }

    @Override
    public Headers dynamicHeader(Headers headers) {
        //Log.e("DynamicTokenInterceptor","dynamicHeader(DynamicTokenInterceptor.java:29)--> "+isAccessToken());
        //Log.e("DynamicTokenInterceptor","dynamicHeader(DynamicTokenInterceptor.java:33)--> "+TokenManager.getInstance().getToken());
        //默认添加Token
        if (!isAccessToken()) {
            return headers;
        }

        if (TextUtils.isEmpty(TokenManager.getInstance().getToken())) {
            TokenManager.getInstance().setToken("");
        }
        return headers.newBuilder().add("Authorization", TokenManager.getInstance().getToken()).build();
    }
}