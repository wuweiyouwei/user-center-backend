package com.wv.userhome.service.impl;

import java.util.Date;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wv.userhome.common.ErrorCode;
import com.wv.userhome.domain.User;
import com.wv.userhome.exception.BusinessException;
import com.wv.userhome.service.UserService;
import com.wv.userhome.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 21192
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2022-11-14 20:39:59
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 盐值
     */
    private final String SALT = "yupi";


//    /**
//     * 用户登录态键
//     */
//    private static final String USER_LOGIN_STATE = "userLoginState";

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword, String planetCode) {


        // 1.非空
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword, planetCode)) {
//            return -1;
            throw new BusinessException(ErrorCode.PARAM_ERROR, "参数为空");
        }
        // 2.账户长度不小于4位
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "账户长度过段");
        }
        // 3.密码就不小于8位
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "密码长度过段");
        }
        // 编号
        if (planetCode.length() > 5) {
            throw new BusinessException(ErrorCode.PARAM_ERROR, "编号过长 ");
        }
        // 4.账户不包含特殊字符

        String validPattern = "[`~!@#$%^&*()+=|{}:;\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return -1;
        }
        // 5.账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        // 5.1编号不能重复
        queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("planetCode", planetCode);
        count = userMapper.selectCount(queryWrapper);
        if (count > 0) {
            return -1;
        }

        // 6.密码和校验密码相同
        if (!userPassword.equals(checkPassword)) {
            return -1;
        }
        // 7.密码加密

        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 8.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        user.setPlanetCode(planetCode);

        boolean saveResult = this.save(user);
        if (!saveResult) {
            return -1;
        }

        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {

        // 1.1校验非空
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            return null;
        }

        // 1.2检验账户密码长度
        if (userAccount.length() < 4 || userPassword.length() < 8) {
            return null;
        }

        // 1.3不包含特殊字符
        String validPattern = "[`~!@#$%^&*()+=|{}:;\\\\\\\\[\\\\\\\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？']";
        Matcher matcher = Pattern.compile(validPattern).matcher(userAccount);
        if (matcher.find()) {
            return null;
        }

        // 2.加密
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        // 3.查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        // 3.1用户不存在
        if (user == null) {
            log.info("user login failed, userAccount cannot match the userPassword");
            return null;
        }

        // 3.2 用户存在 进行脱敏
        User safetyUser = getSafetyUser(user);


        // 4.记录用户登录态状态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    @Override
    public User getSafetyUser(User originUser) {

        if (originUser == null) {
            return null;
        }

        // 3.2 用户存在 进行脱敏
        User safetyUser = new User();
        safetyUser.setId(originUser.getId());
        safetyUser.setUsername(originUser.getUsername());
        safetyUser.setUserAccount(originUser.getUserAccount());
        safetyUser.setAvatarUrl(originUser.getAvatarUrl());
        safetyUser.setGender(originUser.getGender());
        safetyUser.setPhone(originUser.getPhone());
        safetyUser.setEmail(originUser.getEmail());
        safetyUser.setPlanetCode(originUser.getPlanetCode());
        safetyUser.setUserRole(originUser.getUserRole());
        safetyUser.setCreateTime(originUser.getCreateTime());
        safetyUser.setUserStatus(originUser.getUserStatus());

        return safetyUser;
    }

    @Override
    public int userLogout(HttpServletRequest request) {

        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);

        return 1;

    }
}




