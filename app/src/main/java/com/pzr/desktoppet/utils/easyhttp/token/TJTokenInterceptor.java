package com.pzr.desktoppet.utils.easyhttp.token;

import com.google.gson.JsonSyntaxException;
import com.pzr.desktoppet.utils.easyhttp.interceptor.BaseExpiredInterceptor;
import com.pzr.desktoppet.utils.easyhttp.utils.GsonUtils;


import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>描述：天俊Token接口认证 </p>
 *
 * @author by houJia<br>
 * 2019-5-23 <br>
 */
public class TJTokenInterceptor extends BaseExpiredInterceptor {

    public static final String APP_KEY = "SaLSasaas2934PQsmFKw1cbPZnqrjag3Y5MU1T";
    public static final String TOKEN = "auth/getToken?appcode=SAAS2";
    public static final String TJ_API_BASE_URL_DEBUG = "http://139.129.205.11:92/";
    public static final String TJ_API_BASE_URL = "http://114.215.27.165:82/";
    private TJAccessTokenBean apiResult;
    private TJAccessTokenBean tokenBean;

    @Override
    public boolean isResponseExpired(Response response, String bodyString) {
        try {
            //{"errcode":1004,"errmsg":"apptoken认证失效/已过期。 "}
            //{"errcode":1001,"errmsg":"appcode不能为空【即appid/,渠道编号】。"}
            apiResult = GsonUtils.fromJson(bodyString, TJAccessTokenBean.class);
            if (apiResult != null) {
                return apiResult.getErrcode() == 1004 || apiResult.getErrcode() == 1001;
            }
            return false;
        } catch (JsonSyntaxException e) {
            //注意 !!! 此处只json解析异常 其他错误交给上级
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Response responseExpired(Chain chain, String bodyString) {

        try {
            if (apiResult.getErrcode() == 1004 || apiResult.getErrcode() == 1001) {
                refreshToken();
                if (tokenBean != null) {
                    return processAccessTokenError(chain, chain.request());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Response processAccessTokenError(Chain chain, Request request) throws IOException {
        HttpUrl url = request.url();

//        String commonParams = String.format("?appcode=SAAS2&channelCode=SAAS2&timestamp=%s&token=%s",
//                String.valueOf(System.currentTimeMillis() / 1000), TJTokenManager.getInstance().getToken());
//        Request newRequest = request.newBuilder().url(parseUrl(url.toString()) + commonParams).build();

        return chain.proceed(null);
    }

    private String parseUrl(String url) {
        if (!"".equals(url) && url.contains("?")) {// 如果URL不是空字符串
            url = url.substring(0, url.indexOf('?'));
        }
        return url;
    }

    private void refreshToken() {
//        Map<String, String> param = new HashMap<>();
//        param.put("appkey", APP_KEY);
//        param.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
//        EasyHttp.post(TOKEN)
//                .baseUrl(BuildConfig.APP_BUILD_TYPE == 1 ? TJ_API_BASE_URL_DEBUG : TJ_API_BASE_URL)
//                .requestBody(RequestBodyUtils.getJSONRequestBody(param))
//                .accessToken(false)
//                .syncRequest(true)
//                .execute(new SimpleCallBack<String>() {
//
//                    @Override
//                    public void onError(ApiException e) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(String response) {
//                        tokenBean = GsonUtils.fromJson(response, TJAccessTokenBean.class);
//                        TJTokenManager.getInstance().setToken(tokenBean.getToken());
//                    }
//                });
    }

}
