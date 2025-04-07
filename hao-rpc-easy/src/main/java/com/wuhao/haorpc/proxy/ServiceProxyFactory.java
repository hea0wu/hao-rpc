package com.wuhao.haorpc.proxy;

import java.lang.reflect.Proxy;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 服务代理工厂（用于创建代理对象）
 */
public class ServiceProxyFactory {
    /**
     * 根据服务类获取代理对象
     *
     * @param serviceClass
     * @param <T>
     * @return
     */
    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                                            new Class[]{serviceClass},
                                            new ServiceProxy());
    }
}
