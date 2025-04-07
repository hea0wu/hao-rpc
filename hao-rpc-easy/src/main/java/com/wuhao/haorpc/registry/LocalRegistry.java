package com.wuhao.haorpc.registry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 本地注册中心
 */
public class LocalRegistry {
    /**
     * 注册信息存储
     * 使用线程安全的 ConcurrentHashMap 存储服务注册信息，
     * key 为服务名称、value 为服务的实现类
     * 之后就可以根据要调用的服务名称获取到对应的实现类，然后通过反射进行方法调用
     */
    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * 注册服务
     *
     * @param serviceName 服务名称
     * @param implClass 服务的实现类
     */
    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    /**
     * 获取服务
     *
     * @param serviceName 服务名称
     * @return 服务的实现类
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * 删除服务
     *
     * @param serviceName 服务名称
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
