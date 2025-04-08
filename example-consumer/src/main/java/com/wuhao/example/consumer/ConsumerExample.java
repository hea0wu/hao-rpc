package com.wuhao.example.consumer;

import com.wuhao.haorpc.config.RpcConfig;
import com.wuhao.haorpc.utils.ConfigUtils;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 服务消费者示例
 */
public class ConsumerExample {
    public static void main(String[] args) {
        RpcConfig rpc = ConfigUtils.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpc);
    }
}
