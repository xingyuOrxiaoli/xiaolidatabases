package com.xiaoli.utils;


import com.xiaoli.constants.HttpStatusEnum;
import com.xiaoli.entity.Result;

/**
 * 响应结果生成工具
 */
public class ResultGenerator {

    private ResultGenerator() {
    }

    public static <T> Result<T> getResultByHttp(HttpStatusEnum constants, String msg, T data) {
        Result<T> result = new Result<>();
        result.setResultCode(constants.getStatus());
        result.setResultMsg(msg);
        result.setData(data);
        return result;
    }

    public static <T> Result<T> getResultByHttp(HttpStatusEnum constants, T data) {
        Result<T> result = new Result<>();
        result.setResultCode(constants.getStatus());
        result.setResultMsg(constants.getContent());
        result.setData(data);
        return result;
    }

    /**
     * 自定义提示消息
     * @param constants Http枚举
     * @param msg 提示消息
     */
    public static Result<String> getResultByMsg(HttpStatusEnum constants, String msg) {
        Result<String> result = new Result<>();
        result.setResultCode(constants.getStatus());
        result.setResultMsg(msg);
        return result;
    }

    /**
     * @Description: 根据传入的常量返回对应result
     * @Param: [constants] http状态
     */
    public static Result<String> getResultByHttp(HttpStatusEnum constants) {
        Result<String> result = new Result<>();
        result.setResultCode(constants.getStatus());
        result.setResultMsg(constants.getContent());
        return result;
    }
}
