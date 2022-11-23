package com.wv.userhome.domain.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 * @author Mv
 * @date 2022/11/16 16:29
 */
@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -657859590450199891L;

    private String userAccount;
    private String userPassword;
    private String checkPassword;
    private String planetCode;


}
