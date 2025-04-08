package com.wuhao.haorpc.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;

/**
 * @author:WuHao
 * @version:1.0
 *
 * 配置工具类
 * 读取配置文件并返回配置对象，可以简化调用. 支持外层传入要读取的配置内容前缀、支持传入环境等
 */

public class ConfigUtils {

    /**
     * 加载配置对象
     *
     * @param tClass 要转换成的目标类的Class对象
     * @param prefix 配置文件中的前缀，用于过滤相关配置项
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix) {
        return loadConfig(tClass, prefix, "");
    }

    /**
     * 加载配置对象，支持区分环境
     *
     * @param tClass 要转换成的目标类的Class对象
     * @param prefix 配置文件中的前缀，用于过滤相关配置项
     * @param environment 环境变量，用于确定加载哪个配置文件
     * @param <T>
     * @return
     */
    public static <T> T loadConfig(Class<T> tClass, String prefix, String environment) {
        // 初始化一个StringBuilder对象，默认配置文件名为application
        StringBuilder configFileBuilder = new StringBuilder("application");
        if (StrUtil.isNotBlank(environment)) {
            // 如果环境变量不为空，则在文件名中添加环境变量，例如application-dev
            configFileBuilder.append("-").append(environment);
        }
        // 在文件名后添加.properties后缀
        configFileBuilder.append(".properties");
        // 使用构建的文件名创建一个Props对象，Props类用于读取.properties文件
        Props props = new Props(configFileBuilder.toString());
        // 将Props对象中的配置项转换为指定类型的Java对象，并返回。toBean方法会根据prefix过滤配置项，并将这些配置项映射到T类型的对象中
        return props.toBean(tClass, prefix);
    }

}