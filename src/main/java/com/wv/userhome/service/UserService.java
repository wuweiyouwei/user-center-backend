package com.wv.userhome.service;

import com.wv.userhome.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

/**
* @author 21192
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2022-11-14 20:39:59
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录状态键
     */
    String USER_LOGIN_STATE = "userLoginState";

    /**
     *  用户注册功能
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @param planetCode 编号
     * @return 新用户id
     */
    long userRegister(String userAccount, String userPassword, String checkPassword,String planetCode);

    /**
     * 用户登录功能
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param request 请求
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     * @param originUser
     * @return
     */
    User getSafetyUser(User originUser);


    /**
     * 用户注销
     * @param request
     */
    int userLogout(HttpServletRequest request);
}

