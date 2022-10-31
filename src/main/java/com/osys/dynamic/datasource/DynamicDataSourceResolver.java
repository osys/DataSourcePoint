package com.osys.dynamic.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p><b>{@link DynamicDataSourceResolver} Description</b>: 在进行查询时，选择需要的 DataSource。
 * 默认为主数据源。key = null 时，为主数据源。
 * key != null 时，如果 dynamicDataSources 中存在对应的数据源，直接取出使用。
 * 如果 dynamicDataSources 中不存在 key 对应的数据源，那么从 dynamicDataSourceConfig 中获取 key 对应的数据源配置(如果存在)，
 * 创建新的数据源，保存在 dynamicDataSources 中，并在查询中使用，否则使用默认数据源(主数据源)。
 * </p>
 * @author Created by osys on 2022/08/30 11:09.
 */
public class DynamicDataSourceResolver extends AbstractRoutingDataSource {

    /** 保存动态数据库连接的 Map， dataSourceKey -> DataSource */
    private static final ConcurrentMap<String, DataSource> dynamicDataSources = new ConcurrentHashMap<>();

    private DynamicDataSourceConfig dynamicDataSourceConfig;

    @Autowired
    @Qualifier(value = "dynamicDataSourceConfig")
    public void setDynamicDataSourceConfig(DynamicDataSourceConfig dynamicDataSourceConfig) {
        this.dynamicDataSourceConfig = dynamicDataSourceConfig;
    }

    /**
     * 确定当前查找 key。这通常会被实现来检查线程绑定的事务上下文。
     * 允许任意键。
     * @return target dataSource key
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }

    /**
     * 检索当前目标数据源。
     * 确定 current lookup key，在 targetDataSources 映射中执行查找，必要时回退到指定的 defaultTargetDataSource。
     */
    @Override
    protected DataSource determineTargetDataSource() {
        Object lookupKey = determineCurrentLookupKey();
        if (lookupKey == null) {
            super.logger.info("使用默认数据源");
            return super.determineTargetDataSource();
        }

        DataSource dataSource = dynamicDataSources.get(String.valueOf(lookupKey));
        if (dataSource != null) {
            super.logger.info("使用数据源:" + lookupKey);
            return dataSource;
        }

        DataSourceProperty dataSourceProperty = dynamicDataSourceConfig.getDataSourceProperty(String.valueOf(lookupKey));
        if (dataSourceProperty == null) {
            super.logger.info("使用默认数据源");
            return super.determineTargetDataSource();
        }
        DataSource createDataSource = DynamicDataSourceBuilder
                .create()
                .setPropertyValues(dataSourceProperty)
                .build();
        dynamicDataSources.put(String.valueOf(lookupKey), createDataSource);
        super.logger.info("使用数据源:" + lookupKey);
        return createDataSource;
    }

    public static void removeDataSource(String key) {
        dynamicDataSources.remove(key);
    }

    public static void addDataSource(String key, DataSource dataSource) {
        dynamicDataSources.put(key, dataSource);
    }
}
