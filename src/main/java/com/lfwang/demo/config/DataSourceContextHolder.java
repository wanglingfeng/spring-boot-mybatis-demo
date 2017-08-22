package com.lfwang.demo.config;

/**
 * Created by lfwang on 2017/8/21.
 */
public class DataSourceContextHolder {
    
    public enum Type {
        read, write
    }
    
    private static final ThreadLocal<Type> local = new ThreadLocal<>();

    /**
     * 设置为读库
     */
    public static void read() {
        local.set(Type.read);
    }

    /**
     * 设置为写库
     */
    public static void write() {
        local.set(Type.write);
    }

    public static Type getType() {
        return local.get();
    }
}
