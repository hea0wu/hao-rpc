package com.wuhao.haorpc.server;

import io.vertx.core.Vertx;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 基于 Vert.x 实现的 web 服务器 VertxHttpServer，能够监听指定端口并处理请求
 */
public class VertxHttpServer implements HttpServer {

    /**
     * 启动服务器
     *
     * @param port 服务器监听的端口
     */
    @Override
    public void doStart(int port) {
        // 静态工厂方法，创建一个单例的 Vert.x 实例
        Vertx vertx = Vertx.vertx();

        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        // 监听端口并处理请求
        // 通过 server.requestHandler 绑定我实现的请求处理器
        server.requestHandler(new HttpServerHandler());

        // server.listen() 启动服务器并监听指定端口
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("Server is now listening on port " + port);
            } else {
                System.err.println("Failed to start server: " + result.cause());
            }
        });
    }
}
