package com.wuhao.example.provider;

import com.wuhao.example.common.model.User;
import com.wuhao.example.common.sercive.UserService;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 用户服务实现类
 */
public class UserServiceImpl implements UserService {

    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
