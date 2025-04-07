package com.wuhao.example.consumer;

import com.wuhao.example.common.model.User;
import com.wuhao.example.common.sercive.UserService;
import com.wuhao.haorpc.proxy.ServiceProxyFactory;

/**
 * @author:WuHao
 * @version:1.0
 * 简易服务消费者示例
 */
public class EasyConsumerExample {
    public static void main(String[] args) {
        // 动态代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);

        User user = new User();
        user.setName("wuhao");

        User newUser = userService.getUser(user);
        if(newUser != null){
            System.out.println(newUser.getName());
        }else {
            System.out.println("user is null");
        }
    }
}
