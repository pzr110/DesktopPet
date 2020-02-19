package com.pzr.desktoppet;

import android.app.Application;

import com.pzr.desktoppet.utils.easyhttp.EasyHttp;
import com.pzr.desktoppet.utils.easyhttp.cache.converter.SerializableDiskConverter;
import com.pzr.desktoppet.utils.easyhttp.model.HttpHeaders;

public class PetDelegate {

    private Application mApplication;
    public PetDelegate(PetApplication petApplication) {
        this.mApplication = petApplication;
    }
    public void onCreate() {
        initEasyHttp();
    }

    /**
     * 初始化网络
     */
    private void initEasyHttp() {
        EasyHttp.init(mApplication);
//全局设置请求头
        HttpHeaders headers = new HttpHeaders();
//        headers.put("Content-Type", "text/html; charset=utf-8");
////headers.put("Accept-Encoding", "gzip, deflate") //如果加这一句 log会乱码
//        headers.put("Connection", "keep-alive");
//        headers.put("Accept", "*/*");

        EasyHttp.getInstance()
                .debug("PET", BuildConfig.DEBUG)
                .setBaseUrl("https://petdemoserver.now.sh/")
//                .setBaseUrl("http://192.168.1.182:8080/")
//                .setBaseUrl("http://192.168.1.116:8080/")
                //如果使用默认的60秒,以下三行也不需要设置
                .setReadTimeOut(60 * 1000)
                .setWriteTimeOut(60 * 1000)
                .setConnectTimeout(60 * 1000)
                .addCommonHeaders(headers)//设置全局公共头
                .setCacheDiskConverter(new SerializableDiskConverter())//默认缓存使用序列化转化
//                .addInterceptor(new DynamicTokenInterceptor()) //添加参数签名拦截器
//                .addInterceptor(new TokenInterceptor())//Token异常拦截器
                .setCacheMaxSize(50 * 1024 * 1024)//设置缓存大小为50M
                .setCacheVersion(1)//缓存版本为1
                .setCertificates();//信任所有证书
//.addConverterFactory(GsonConverterFactory.create(gson))//本框架没有采用Retrofit的Gson转化，所以不用配置
    }

    public void onTerminate() {
        mApplication = null;
    }
}
