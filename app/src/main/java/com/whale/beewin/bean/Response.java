package com.whale.beewin.bean;


import com.whale.beewin.utils.Constant;

/**
 * 请求数据统一处理，接收泛型
 * Created by Sunflower on 2016/1/11.
 */
public class Response<T> {

    public String status;
    public String message;
    public T result;

    public boolean isSuccess() {
        return status.equals(Constant.OK);
    }
}
