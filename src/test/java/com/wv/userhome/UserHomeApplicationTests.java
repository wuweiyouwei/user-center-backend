package com.wv.userhome;

import com.wv.userhome.domain.User;
import com.wv.userhome.mapper.UserMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserHomeApplicationTests {


    @Autowired
    UserMapper userMapper;
    @Test
    void contextLoads() {
        System.out.println("开始测试");
        List<User> users = userMapper.selectList(null);
        Assertions.assertEquals(4,users.size());
        users.forEach(System.out::println);
    }

}
