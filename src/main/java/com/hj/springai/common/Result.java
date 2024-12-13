package com.hj.springai.common;

import lombok.Data;

/**
 * @FileName Result
 * @Description
 * @Author fazhu
 * @date 2024-12-10
 **/
@Data
public class Result<T> {
    //状态码
    private static final int SUCCESS = 200;//成功
    private static final int ERROR = 500;//服务器异常

    //统一返回值
    private int code = 200;
    private String msg = "操作成功";
    private T data = null;

    /**
     * 构造方法
     * @param code 状态码
     * @param msg 消息
     */
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }


    /**
     * 构造方法
     * @param code 状态码
     * @param msg 消息
     * @param data 数据
     */
    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    //成功
    /**
     * 无参成功
     * @return Result
     */
    public static <T> Result success() {
        Result result = new Result(SUCCESS, "操作成功");
        return result;
    }


    /**
     * 含数据的成功
     * @param data 数据
     * @return Result
     * @param <T> 可返回任意类型
     */
    public static <T> Result success(T data) {
        Result result = new Result(SUCCESS, "操作成功", data);
        return result;
    }
    /**
     * 含数据和提示词的成功
     * @param data 数据
     * @return Result
     * @param <T> 可返回任意类型
     */
    public static <T> Result success(String msg,T data) {
        Result result = new Result(SUCCESS, msg, data);
        return result;
    }

    //异常

    /**
     * 异常
     * @return
     * @param <T>
     */
    public static <T> Result error() {
        Result result = new Result(ERROR, "服务器异常", "服务器出现问题了");
        return result;
    }

    /**
     * @param msg
     * @return
     * @param <T>
     */
    public static <T> Result error(String msg) {
        Result result = new Result(ERROR, msg);
        return result;
    }
    public static <T> Result error(int code, String msg) {
        Result result = new Result(code, msg);
        return result;
    }

}