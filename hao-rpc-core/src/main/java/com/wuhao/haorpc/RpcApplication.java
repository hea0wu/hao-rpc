package com.wuhao.haorpc;

import com.wuhao.haorpc.config.RpcConfig;
import com.wuhao.haorpc.constant.RpcConstant;
import com.wuhao.haorpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author:WuHao
 * @version:1.0
 *
 * RPC 框架应用
 * RPC 项目的启动入口、并且维护项目全局用到的变量
 * 双检锁单例模式，支持在获取配置时才调用 init 方法实现懒加载。
 * 为了便于扩展，还支持自己传入配置对象；如果不传入，则默认调用前面写好的 ConfigUtils 来加载配置
 */
@Slf4j
public class RpcApplication {

    // 静态变量
    private static volatile RpcConfig rpcConfig;

    /**
     * 框架初始化，支持传入自定义配置
     * @param newRpcConfig 自定义配置
     */
    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
    }

    /**
     * 初始化
     */
    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            // 配置加载失败，使用RpcConfig类的默认值
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    /**
     * 获取配置
     * @return rpcConfig
     */
    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }
}
