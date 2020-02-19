package com.pzr.desktoppet.utils.easyhttp.token;

import android.text.TextUtils;

import com.pzr.desktoppet.utils.easyhttp.contact.ComParamContact;
import com.pzr.desktoppet.utils.easyhttp.utils.ACache;


/**
 * token管理
 *
 * @author houJia
 * 2018年9月5日 15:40:41
 */
public class TokenManager {
    private final static String key = "auth_model_new";
    private static TokenManager instance = null;
    private String token;
    private ACache aCache;
    private Long timestamp = System.currentTimeMillis();

    public TokenManager() {
//        if (BaseApplication.getContext() == null) {
//            Log.e("TokenManager","TokenManager(TokenManager.java:24)--> 空");
//        }
//        aCache = ACache.get(BaseApplication.getContext(), key);
        this.token = "";
    }

    public static TokenManager getInstance() {
        if (instance == null) {
            synchronized (TokenManager.class) {
                if (instance == null) {
                    instance = new TokenManager();
                }
            }
        }
        return instance;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getToken() {
        if (this.token == null || null == this.token || this.token.equals("")) {
            String object = aCache.getAsString(ComParamContact.Token.AUTH_MODEL);
            if (object != null) {
                this.token = object;
            }
        }
        return this.token;
    }

    public void setToken(String model) {
        if (model != null) {
            this.token = model;
            aCache.put(ComParamContact.Token.AUTH_MODEL, this.token);
        }
    }

    public void clearAuth() {
        this.token = "";
        aCache.put(ComParamContact.Token.AUTH_MODEL, this.token);
        aCache.clear();
    }

    public boolean isLogin() {
        if (getToken() != null && !TextUtils.isEmpty(getToken())) {
            return true;
        }
        return false;
    }
}
