package com.wv.userhome.common;

/**
 * 错误码
 * @author Mv
 * @date 2022/11/19 14:05
 */

public enum ErrorCode {


    /**
     * 状态码 -- 成功
     */
    SUCCESS(0,"ok", ""),
    /**
     * 状态码 -- 参数错误
     */
    PARAM_ERROR(40000, "请求参数错误", ""),
    /**
     * 状态码 -- 请求数据为空
     */
    NULL_ERROR(40001, "请求数据为空", ""),
    /**
     * 状态码 -- 未登录
     */
    NOT_LOGIN(40100, "未登录", ""),
    /**
     * 状态码 -- 无权限
     */
    NO_AUTH(40101, "无权限", ""),

    /**
     * 状态码 -- 系统内部异常
     */
    SYSTEM_ERROR(50000, "系统内部异常", "");



    private final int code;

    /**
     *  状态码信息
     */
    private final String message;

    /**
     * 状态码描述
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
