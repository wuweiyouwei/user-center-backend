package com.wv.userhome.common;

/**
 * 返回工具类
 * @author Mv
 * @date 2022/11/19 13:43
 */
public class ResultUtils {

    /**
     *  成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);

    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String message, String decription) {
        return new BaseResponse<>(errorCode.getCode(), null,message, decription);

    }

    /**
     * 失败
     * @param code
     * @return
     */
    public static BaseResponse error(int code, String message, String decription) {
        return new BaseResponse<>(code, null, message, decription);

    }

    /**
     * 失败
     * @param errorCode
     * @return
     */
    public static BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(), description);

    }
}
