package com.wuhao.haorpc.config;

import lombok.Data;
/**
 * @author:WuHao
 * @version:1.0
 *
 * RPC 框架配置类
 * 用于保存配置信息,属性有默认值
 */
@Data
public class RpcConfig {
    /**
     * 名称
     */
    private String name = "hao-rpc";

    /**
     * 版本号
     */
    private String version = "1.0";

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;
}
