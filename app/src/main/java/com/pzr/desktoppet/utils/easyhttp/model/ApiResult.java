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

package com.pzr.desktoppet.utils.easyhttp.model;

import java.io.Serializable;

/**
 * <p>描述：提供的默认的标注返回api</p>
 * 作者： zhouyou<br>
 * 日期： 2017/5/15 16:58 <br>
 * 版本： v1.0<br>
 */
public class ApiResult<T> implements Serializable {

    private static final long serialVersionUID = -8839500421439614071L;
    private int errcode;
    private int total;
    private int failure;
    private T result;
    private Object obj;
    private String failureMsg;
    private boolean success;
    private T data;
    private String errmsg;
    private String page;

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFailure() {
        return failure;
    }

    public void setFailure(int failure) {
        this.failure = failure;
    }

    public T getRows() {
        return result;
    }

    public void setRows(T rows) {
        this.result = rows;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getFailureMsg() {
        return failureMsg;
    }

    public void setFailureMsg(String failureMsg) {
        this.failureMsg = failureMsg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * 接口请求成功默认返回-1 但是对于没有failure字段的接口默认为0
     *
     * @return
     */
    public boolean isOk() {
        return failure == 0 || failure == -1;
    }


    @Override
    public String toString() {
        return "ApiResult{" +
                ", page=" + page +
                ", total=" + total +
                ", failure=" + failure +
                ", rows=" + result +
                ", obj=" + obj +
                ", failureMsg='" + failureMsg + '\'' +
                ", success=" + success +
                ", data=" + data +
                '}';
    }
}
