package com.wuhao.haorpc.server;

/**
 * @author:WuHao
 * @version:1.0
 *
 * HTTP 服务器接口
 */
public interface HttpServer {

    /**
     * 启动服务器
     * 定义统一的启动服务器方法，便于后续的扩展，比如实现多种不同的 web 服务器
     * @param port
     */
    void doStart(int port);
}
