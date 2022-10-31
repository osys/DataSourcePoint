package com.osys.dynamic.datasource;

import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p><b>{@link DynamicDataSourceConfig} Description</b>: 多源数据库配置信息保存类。推荐在项目启动时，将所有数据源连接配置信息保存在该类中。
 * </p>
 * @author Created by osys on 2022/08/30 10:41.
 */
@Configuration(value = "dynamicDataSourceConfig")
public class DynamicDataSourceConfig {

    /** 数据源 key 和配置属性实体类的键值对 */
    private final ConcurrentMap<String, DataSourceProperty> dataSourceProps = new ConcurrentHashMap<>();

    /** 保存一个数据源连接信息 */
    public void registerDataSource(DataSourceProperty dataSourceProperty) {
        dataSourceProps.put(dataSourceProperty.getKey(), dataSourceProperty);
    }

    /** 移除一个数据源连接信息 */
    public void removeDataSource(String dataSourceKey) {
        dataSourceProps.remove(dataSourceKey);
    }

    /** 获取一个数据源连接信息 */
    public DataSourceProperty getDataSourceProperty(String dataSourceKey) {
        return dataSourceProps.get(dataSourceKey);
    }
}
