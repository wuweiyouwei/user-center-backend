package com.wv.userhome.exception;

import com.wv.userhome.common.ErrorCode;

/**
 * 自定义异常类
 * @author Mv
 * @date 2022/11/19 14:49
 */
public class BusinessException extends RuntimeException {

    private final int code;

    private final String decription;

    public BusinessException(String message, int code, String decription) {
        super(message);
        this.code = code;
        this.decription = decription;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.decription = errorCode.getDescription();
    }

    public BusinessException(ErrorCode errorCode, String decription) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
        this.decription = decription;
    }

    public int getCode() {
        return code;
    }

    public String getDecription() {
        return decription;
    }
}
