package com.pzr.desktoppet.utils.easyhttp.token;

import com.google.gson.annotations.SerializedName;

/**
 * Created by xiaote on 2018/3/24.
 * Token类
 */

public class AccessTokenBean {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("custom_field")
    private String customField;
    @SerializedName("custom_complex_field")
    private CustomComplexField customComplexField;

    public String getAccessToken() {
        return accessToken;
    }

    public String getRealAccessToken() {
        return String.format("%s %s", tokenType, accessToken);
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getCustomField() {
        return customField;
    }

    public void setCustomField(String customField) {
        this.customField = customField;
    }

    public CustomComplexField getCustomComplexField() {
        return customComplexField;
    }

    public void setCustomComplexField(CustomComplexField customComplexField) {
        this.customComplexField = customComplexField;
    }

    public static class CustomComplexField {
        /**
         * SomeString : foo
         * SomeInt : 42
         */

        @SerializedName("SomeString")
        private String someString;
        @SerializedName("SomeInt")
        private int someInt;

        public String getSomeString() {
            return someString;
        }

        public void setSomeString(String someString) {
            this.someString = someString;
        }

        public int getSomeInt() {
            return someInt;
        }

        public void setSomeInt(int someInt) {
            this.someInt = someInt;
        }

        @Override
        public String toString() {
            return "CustomComplexField{" +
                    "someString='" + someString + '\'' +
                    ", someInt=" + someInt +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "accessToken='" + accessToken + '\'' +
                ", expiresIn=" + expiresIn +
                ", tokenType='" + tokenType + '\'' +
                ", customField='" + customField + '\'' +
                ", customComplexField=" + customComplexField +
                '}';
    }
}
