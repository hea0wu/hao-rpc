package com.wuhao.example.provider;

import com.wuhao.example.common.sercive.UserService;
import com.wuhao.haorpc.registry.LocalRegistry;
import com.wuhao.haorpc.server.HttpServer;
import com.wuhao.haorpc.server.VertxHttpServer;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 简易服务提供者示例
 */
public class EasyProviderExample {
    public static void main(String[] args){
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        HttpServer httpServer = new VertxHttpServer();
        httpServer.doStart(8080);
    }
}
