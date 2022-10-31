package com.osys.dynamic.datasource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.validation.DataBinder;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p><b>{@link DynamicDataSourceBuilder} Description</b>: 方便构建 DataSource 的类
 * </p>
 * @author Created by osys on 2022/08/30 11:22.
 */
public class DynamicDataSourceBuilder {

    private DataSourceProperty property;

    private final ConversionService conversionService = new DefaultConversionService();

    /** 默认 DataSource 类型 */
    private static final Class<? extends DataSource> DEFAULT_DATASOURCE_TYPE = com.zaxxer.hikari.HikariDataSource.class;

    public static DynamicDataSourceBuilder create() {
        return new DynamicDataSourceBuilder();
    }

    /** 添加数据库连接信息 */
    public DynamicDataSourceBuilder setPropertyValues(DataSourceProperty dataSourceProperty) {
        this.property = dataSourceProperty;
        return this;
    }

    /** 构建数据库连接 */
    public DataSource build() {
        DataSource dataSource = buildDataSource(this.property);
        return propertySet(dataSource, this.property);
    }

    /** 数据库连接 */
    public DataSource buildDataSource(DataSourceProperty dataSourceProperty) {
        String driverClassName = dataSourceProperty.getDriverClassName();
        Class<? extends DataSource> dataSourceType = dataSourceProperty.getClazzType();
        if (dataSourceType == null) {
            dataSourceType = DEFAULT_DATASOURCE_TYPE;
        }
        String dataSourceName = dataSourceProperty.getDataSourceName();
        String host = dataSourceProperty.getHost();
        String port = dataSourceProperty.getPort();
        String userName = dataSourceProperty.getUserName();
        String password = dataSourceProperty.getPassword();
        String url = "jdbc:mysql://" +
                host + ":" + port +
                "/" + dataSourceName +
                "?" + "useUnicode=true" +
                "&characterEncoding=utf-8" +
                "&serverTimezone=Asia/Shanghai";
        DataSourceBuilder<? extends DataSource> dataSourceBuilder = DataSourceBuilder.create()
                .driverClassName(driverClassName)
                .url(url)
                .username(userName)
                .password(password)
                .type(dataSourceType);
        return dataSourceBuilder.build();
    }

    /** 数据库连接池的一些配置，也可以不用，正常没什么影响。连接池都有默认配置的。 */
    public DataSource propertySet(DataSource dataSource, DataSourceProperty dataSourceProperty) {
        DataBinder dataBinder = new DataBinder(dataSource);
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreInvalidFields(true);
        dataBinder.setIgnoreUnknownFields(true);
        // 新加连接池配置
        Map<String, Object> values = new HashMap<>();
        values.put("max-pool-size", 10);
        values.put("connection-timeout", 50000);
        values.put("min-idle", 5);
        values.put("idle-timeout", 500000);
        values.put("max-lifetime", 540000);
        dataBinder.bind(new MutablePropertyValues(values));
        return dataSource;
    }
}
