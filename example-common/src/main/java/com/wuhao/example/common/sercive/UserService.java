package com.wuhao.example.common.sercive;

import com.wuhao.example.common.model.User;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 用户服务
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    User getUser(User user);
}
