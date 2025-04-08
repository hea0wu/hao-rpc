package com.wuhao.example.consumer;

import com.wuhao.example.common.model.User;
import com.wuhao.example.common.sercive.UserService;
import com.wuhao.haorpc.config.RpcConfig;
import com.wuhao.haorpc.proxy.ServiceProxyFactory;
import com.wuhao.haorpc.utils.ConfigUtils;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 服务消费者示例
 */
public class ConsumerExample {
    public static void main(String[] args) {
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("wuhao");
        // 调用
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
        long number = userService.getNumber();
        System.out.println(number);
    }
}
