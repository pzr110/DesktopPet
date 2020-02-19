package com.pzr.desktoppet.utils.easyhttp.token;

/**
 * Created by xiaote on 2018/3/24.
 * Token类
 */

public class TJAccessTokenBean {

    /**
     * errcode : 0
     * expire : 7200
     * errmsg : 获取票据成功！
     * token : U0FBUzIsU2FMU2FzYWFzMjkzNFBRc21GS3cxY2JQWm5xcmphZzNZNU1VMVQsMTU1ODU5NTk5MywxNTU4NTk2MDA5MTk4
     */

    private int errcode;
    private int expire;
    private String errmsg;
    private String token;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TJAccessTokenBean{" +
                "errcode=" + errcode +
                ", expire=" + expire +
                ", errmsg='" + errmsg + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
