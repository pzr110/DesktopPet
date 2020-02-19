package com.pzr.desktoppet.utils.easyhttp.token;

import android.text.TextUtils;


import com.pzr.desktoppet.utils.easyhttp.interceptor.BaseExpiredInterceptor;

import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.Response;

/**
 * <p>描述： </p>
 *
 * @author by houJia<br>
 * 2018-9-5 <br>
 */
public class TokenInterceptor extends BaseExpiredInterceptor {

    public static final String TOKEN = "core/connect/token";
    public static final String WEB_API_TOKEN_URL_DEBUG = "";
    public static final String WEB_API_TOKEN_URL = "";
    public static final String WEB_API_TOKEN_URL_PRO = "";

//    private ApiResult apiResult;
    private AccessTokenBean tokenBean;

    @Override
    public boolean isResponseExpired(Response response, String bodyString) {
//        try {
//            apiResult = GsonUtils.fromJson(bodyString, ApiResult.class);
//            if (apiResult != null) {
//                int code = apiResult.getFailure();
//                if (code == ComParamContact.Code.TOKEN_EXPIRED) {
//                    return true;
//                }
//            }
//            return false;
//        } catch (JsonSyntaxException e) {
//            //注意 !!! 此处只json解析异常 其他错误交给上级
//            e.printStackTrace();
//            return false;
//        }
        return false;
    }

    @Override
    public Response responseExpired(Chain chain, String bodyString) {

//        try {
//            if (apiResult.getFailure() == ComParamContact.Code.TOKEN_EXPIRED) {
//                refreshToken();
//                if (tokenBean != null) {
//                    return processAccessTokenError(chain, chain.request());
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
        return null;
    }

//    private Response processAccessTokenError(Interceptor.Chain chain, Request request) throws IOException {
//        Request newRequest = request.newBuilder().header(ComParamContact.Common.AUTH, TokenManager.getInstance().getToken()).build();
//        EasyHttp.getInstance().getCommonHeaders().remove(ComParamContact.Common.AUTH);
//        EasyHttp.getInstance().getCommonHeaders().put(ComParamContact.Common.AUTH, TokenManager.getInstance().getToken());
//        return chain.proceed(newRequest);


        // 以下
//        LogUtils.e("oldRequest-->" + request);
//        LogUtils.e("(TokenInterceptor.java:72)processAccessTokenError-->新token" + TokenManager.getInstance().getToken());
//        LogUtils.e("(TokenInterceptor.java:73)requestUrl-->" + request.url());
//        String method = request.method();
//        LogUtils.e("requestMethod-->" + method);
//        LogUtils.e("requestHeader-->" + request.header(ComParamContact.Common.AUTH));
//        Request.Builder newBuilder = request.newBuilder();
//        newBuilder.removeHeader(ComParamContact.Common.AUTH);
//        Request newRequest;
//        if (method.equalsIgnoreCase("GET")) {
//            FormBody oldBody = getRequestParams(request.url().query());
//            if(oldBody == null) {
//                newRequest = newBuilder.addHeader(ComParamContact.Common.AUTH, TokenManager.getInstance().getToken()).build();
//            } else {
//                FormBody.Builder newBody = new FormBody.Builder();
//                for (int i = 0; i < oldBody.size(); i++) {
//                    String name = oldBody.encodedName(i);
//                    String value = oldBody.encodedValue(i);
//                    newBody.add(name, value);
//                }
//                String newUrl = packageParams(newBody.build());
//                LogUtils.e("newUrl-->" + newUrl);
//                HttpUrl newHttpUrl = request.url().newBuilder().query(newUrl).build();
//                newRequest = newBuilder.url(newHttpUrl).addHeader(ComParamContact.Common.AUTH, TokenManager.getInstance().getToken()).get().build();
//            }
//        } else {
//            newRequest = newBuilder.addHeader(ComParamContact.Common.AUTH, TokenManager.getInstance().getToken()).build();
//        }
//        LogUtils.e("newRequest-->" + newRequest);
//        LogUtils.e("newRequestMethod-->" + newRequest.method());
//        LogUtils.e("requestHeaderNew-->" + newRequest.header(ComParamContact.Common.AUTH));
//        return chain.proceed(newRequest);
//    }
    /**
     * 将GET请求的参数封装成FormBody
     */
//    private FormBody getRequestParams(String params) {
//        if (params == null)
//            return null;
//        String[] strArr = params.split("&");
//        if (CommonUtils.isEmptyArray(strArr)) {
//            return null;
//        }
//
//        TreeMap<String, String> treeMap = new TreeMap<>();
//        FormBody.Builder fBuilder = new FormBody.Builder();
//        for (String s : strArr) {
//            String[] sArr = s.split("=");
//            if (sArr.length < 2)
//                continue;
//            treeMap.put(sArr[0], sArr[1]);
//            fBuilder.add(sArr[0], sArr[1]);
//        }
//        return fBuilder.build();
//    }

    /**
     * 封装参数
     */
    private String packageParams(FormBody oldBody) {
        List<String> namesAndValues = new ArrayList<>();
        for (int i = 0; i < oldBody.size(); i++) {
            String name = oldBody.encodedName(i);
            String value = oldBody.encodedValue(i);
            if (!TextUtils.isEmpty(name)) {
                namesAndValues.add(name);
                namesAndValues.add(value);
            }
        }
        StringBuilder sb = new StringBuilder();
        namesAndValuesToQueryString(sb, namesAndValues);
        return sb.toString();
    }

    /**
     * 合并GET参数
     */
    private void namesAndValuesToQueryString(StringBuilder out, List<String> namesAndValues) {
        for (int i = 0, size = namesAndValues.size(); i < size; i += 2) {
            String name = namesAndValues.get(i);
            String value = namesAndValues.get(i + 1);
            if (i > 0)
                out.append('&');
            out.append(name);
            if (value != null) {
                out.append('=');
                out.append(value);
            }
        }
    }

    private void refreshToken() {
//        EasyHttp.post(TOKEN)
//                .baseUrl(BuildConfig.APP_BUILD_TYPE == 1 ? WEB_API_TOKEN_URL_DEBUG : BuildConfig.APP_BUILD_TYPE == 2 ? WEB_API_TOKEN_URL_PRO
//                        : WEB_API_TOKEN_URL)
//                .params("grant_type", "client_credentials")
//                .params("scope", "write")
//                .params("client_id", "za.client")
//                .params("client_secret", "ZAHKS61B754C541BBCFC6A45A9E9EC5E47D8702B78C29")
//                .accessToken(false)
//                .syncRequest(true)
//                .execute(new SimpleCallBack<String>() {
//                    @Override
//                    public void onError(ApiException e) {
//                        //notifyLoginExit(e.message!!)
//                    }
//
//                    @Override
//                    public void onSuccess(String response) {
//                        tokenBean = GsonUtils.fromJson(response, AccessTokenBean.class);
//                        TokenManager.getInstance().setToken(String.format("%s %s", tokenBean.getTokenType(), tokenBean.getAccessToken()));
//
//                        EasyHttp.getInstance().getCommonHeaders().remove(ComParamContact.Common.AUTH);
//                        EasyHttp.getInstance().getCommonHeaders().put(ComParamContact.Common.AUTH, TokenManager.getInstance().getToken());
//                    }
//                });
    }
}
