/*
 * Copyright (C) 2017 zhouyou(478319399@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pzr.desktoppet.utils.easyhttp.func;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.pzr.desktoppet.utils.easyhttp.model.ApiResult;
import com.pzr.desktoppet.utils.easyhttp.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;


/**
 * <p>描述：定义了ApiResult结果转换Func</p>
 * 作者： zhouyou<br>
 * 日期： 2017/3/15 16:52 <br>
 * 版本： v1.0<br>
 */
@SuppressWarnings("unchecked")
public class ApiResultFunc<T> implements Function<ResponseBody, ApiResult<T>> {
    protected Type type;
    protected Gson gson;

    public ApiResultFunc(Type type) {
        gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .serializeNulls()
                .create();
        this.type = type;
    }

    @Override
    public ApiResult<T> apply(@NonNull ResponseBody responseBody) throws Exception {
        ApiResult<T> apiResult = new ApiResult<>();
//        apiResult.setCode(-1);
        if (type instanceof ParameterizedType) {
            //自定义ApiResult
            final Class<T> cls = (Class) ((ParameterizedType) type).getRawType();
            if (ApiResult.class.isAssignableFrom(cls)) {
                final Type[] params = ((ParameterizedType) type).getActualTypeArguments();
                final Class clazz = Utils.getClass(params[0], 0);
                final Class rawType = Utils.getClass(type, 0);
                try {
                    String json = responseBody.string();
                    //增加是List<String>判断错误的问题
                    if (!List.class.isAssignableFrom(rawType) && clazz.equals(String.class)) {
                        apiResult.setData((T) json);
//                        apiResult.setCode(0);
                       /* final Type type = Utils.getType(cls, 0);
                        ApiResult result = gson.fromJson(json, type);
                        if (result != null) {
                            apiResult = result;
                            apiResult.setData((T) json);
                        } else {
                            apiResult.setMsg("json is null");
                        }*/
                    } else {
                        ApiResult result = gson.fromJson(json, type);
                        if (result != null) {
                            apiResult = result;
                            Log.e("ApiResultFunc","apply(ApiResultFunc.java:89)--> "+result.getRows());
                        } else {
                            apiResult.setFailureMsg("json is null");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    apiResult.setFailureMsg(e.getMessage());
                } finally {
                    responseBody.close();
                }
            } else {
                apiResult.setFailureMsg("ApiResult.class.isAssignableFrom(cls) err!!");
            }
        } else {//默认Apiresult
            try {
                final String json = responseBody.string();
                final Class<T> clazz = Utils.getClass(type, 0);
                if (clazz.equals(String.class)) {
                    //apiResult.setData((T) json);
                    //apiResult.setCode(0);
                    final ApiResult result = parseApiResult(json, apiResult);
                    if (result != null) {
                        apiResult = result;
                        apiResult.setData((T) json);
                    } else {
                        apiResult.setFailureMsg("json is null");
                    }
                } else {
                    final ApiResult result = parseApiResult(json, apiResult);
                    if (result != null) {
                        apiResult = result;
                        if (apiResult.getData() != null) {
                            T data = gson.fromJson(apiResult.getData().toString(), clazz);
                            apiResult.setData(data);
                        } else {
                            apiResult.setFailureMsg("ApiResult's data is null");
                        }
                    } else {
                        apiResult.setFailureMsg("json is null");
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                apiResult.setFailureMsg(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
                apiResult.setFailureMsg(e.getMessage());
            } finally {
                responseBody.close();
            }
        }
        return apiResult;
    }

    private ApiResult parseApiResult(String json, ApiResult apiResult) throws JSONException {
        if (TextUtils.isEmpty(json))
            return null;
        JSONObject jsonObject = new JSONObject(json);
        if (jsonObject.has("failure")) {
//            apiResult.setCode(jsonObject.getInt("failure"));
        }
        if (jsonObject.has("result")) {
            String rows = jsonObject.getString("result");
            apiResult.setRows(jsonObject.getString("result"));
            apiResult.setData(jsonObject.getString("result"));
        }
        if (jsonObject.has("msg")) {
//            apiResult.set(jsonObject.getString("msg"));
        }
        return apiResult;
    }
}
