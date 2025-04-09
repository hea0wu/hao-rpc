package com.wuhao.haorpc.serializer;

import com.wuhao.haorpc.spi.SpiLoader;

import java.util.HashMap;
import java.util.Map;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 序列化器工厂（用于获取序列化器对象）
 */
public class SerializerFactory {

    // 使用静态代码块，在工厂首次加载时，就会调用 SpiLoader 的 load 方法加载序列化器接口的所有实现类，
    // 之后就可以通过调用 getInstance 方法获取指定的实现类对象了
    static {
        SpiLoader.load(Serializer.class);
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
