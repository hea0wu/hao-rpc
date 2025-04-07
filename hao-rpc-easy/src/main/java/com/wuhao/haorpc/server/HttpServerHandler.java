package com.wuhao.haorpc.server;

import com.wuhao.haorpc.model.RpcRequest;
import com.wuhao.haorpc.model.RpcResponse;
import com.wuhao.haorpc.registry.LocalRegistry;
import com.wuhao.haorpc.serializer.JdkSerializer;
import com.wuhao.haorpc.serializer.Serializer;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author:WuHao
 * @version:1.0
 *
 * HTTP 请求处理
 */
public class HttpServerHandler implements Handler<HttpServerRequest> {


    /**
     *  Vert.x 中是通过实现 Handler<HttpServerRequest> 接口来自定义请求处理器的。
     *  并且可以通过 request.bodyHandler 异步处理请求
     *
     * 业务流程如下：
     * 反序列化请求为对象，并从请求对象中获取参数。
     * 根据服务名称从本地注册器中获取到对应的服务实现类。
     * 通过反射机制调用方法，得到返回结果。
     * 对返回结果进行封装和序列化，并写入到响应中。
     */

    @Override
    public void handle(HttpServerRequest request) {
        // 指定序列化器
        // 使用 final 关键字的主要目的是 确保 serializer 变量的引用不可变
        final Serializer serializer = new JdkSerializer();

        // 记录日志
        System.out.println("Received request: " + request.method() + " " + request.uri());

        // 异步处理 HTTP 请求
        request.bodyHandler(body -> {
            byte[] bytes = body.getBytes();
            RpcRequest rpcRequest = null;
            try {
                rpcRequest = serializer.deserialize(bytes, RpcRequest.class);
            } catch (Exception e) {
                e.printStackTrace();
            }

            // 构造响应结果对象
            RpcResponse rpcResponse = new RpcResponse();
            // 如果请求为 null，直接返回
            if (rpcRequest == null) {
                rpcResponse.setMessage("rpcRequest is null");
                doResponse(request, rpcResponse, serializer);
                return;
            }

            try {
                // 获取要调用的服务实现类，通过反射调用
                Class<?> implClass = LocalRegistry.get(rpcRequest.getServiceName());
                Method method = implClass.getMethod(rpcRequest.getMethodName(), rpcRequest.getParameterTypes());
                Object result = method.invoke(implClass.newInstance(), rpcRequest.getArgs());
                // 封装返回结果
                rpcResponse.setData(result);
                rpcResponse.setDataType(method.getReturnType());
                rpcResponse.setMessage("ok");
            } catch (Exception e) {
                e.printStackTrace();
                rpcResponse.setMessage(e.getMessage());
                rpcResponse.setException(e);
            }
            // 响应
            doResponse(request, rpcResponse, serializer);
        });
    }

    /**
     * 响应
     *
     * @param request
     * @param rpcResponse
     * @param serializer
     */
    void doResponse(HttpServerRequest request, RpcResponse rpcResponse, Serializer serializer) {
        HttpServerResponse httpServerResponse = request.response()
                .putHeader("content-type", "application/json");
        try {
            // 序列化
            byte[] serialized = serializer.serialize(rpcResponse);
            httpServerResponse.end(Buffer.buffer(serialized));
        } catch (IOException e) {
            e.printStackTrace();
            // 将序列化后的数据写入 HTTP 响应体，并结束响应。
            // Buffer.buffer() 是 Vert.x 提供的二进制数据容器
            httpServerResponse.end(Buffer.buffer());
        }
    }

}
