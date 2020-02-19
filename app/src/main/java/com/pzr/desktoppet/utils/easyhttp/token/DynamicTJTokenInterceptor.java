package com.pzr.desktoppet.utils.easyhttp.token;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * <p>描述： </p>
 *
 * @author by houJia<br>
 * 2019年5月23日 15:30:53 <br>
 */
public class DynamicTJTokenInterceptor implements Interceptor {

    public static final String APP_KEY = "SaLSasaas2934PQsmFKw1cbPZnqrjag3Y5MU1T";
    public static final String TOKEN = "auth/getToken?appcode=SAAS2";
    public static final String TJ_API_BASE_URL_DEBUG = "http://139.129.205.11:92/";
    public static final String TJ_API_BASE_URL = "http://114.215.27.165:82/";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        HttpUrl httpUrl = HttpUrl.parse(parseUrl(request.url().url().toString()));

        httpUrl = addCommonParam(httpUrl);

        request = request.newBuilder().url(httpUrl).build();

        return chain.proceed(request);
    }


    //解析前：https://xxx.xxx.xxx/app/skinTestResult?appId=10101
    //解析后：https://xxx.xxx.xxx/app/skinTestResult
    private String parseUrl(String url) {
        if (!"".equals(url) && url.contains("?")) {// 如果URL不是空字符串
            url = url.substring(0, url.indexOf('?'));
        }
        return url;
    }

    private HttpUrl addCommonParam(HttpUrl httpUrl) {
        StringBuilder url = new StringBuilder();
//        if (TextUtils.isEmpty(TJTokenManager.getInstance().getToken())) {
//            refreshToken();
//        }
//        url.append("")
//                .append(String.valueOf(System.currentTimeMillis() / 1000))
//                .append("&token=").append(TJTokenManager.getInstance().getToken());

        return httpUrl.newBuilder(url.toString()).build();
    }

    private void refreshToken() {
        Map<String, String> param = new HashMap<>();
        param.put("appkey", APP_KEY);
        param.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
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
//                        TJAccessTokenBean tokenBean = GsonUtils.fromJson(response, TJAccessTokenBean.class);
//                        TJTokenManager.getInstance().setToken(tokenBean.getToken());
//                    }
//                });
    }

}