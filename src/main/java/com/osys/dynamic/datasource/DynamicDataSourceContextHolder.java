package com.osys.dynamic.datasource;

/**
 * <p><b>{@link DynamicDataSourceContextHolder} Description</b>: 使用 ThreadLocal 来指定 DataSource 的 key
 * </p>
 * @author Created by osys on 2022/08/30 11:12.
 */
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDataSourceKey(String dataSourceKey) {
        contextHolder.set(dataSourceKey);
    }

    public static String getDataSourceKey() {
        return contextHolder.get();
    }

    public static void clearDataSourceKey() {
        contextHolder.remove();
    }
}
