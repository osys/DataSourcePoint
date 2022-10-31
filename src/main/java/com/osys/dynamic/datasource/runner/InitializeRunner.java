package com.osys.dynamic.datasource.runner;

import com.osys.dynamic.datasource.DataSourceProperty;
import com.osys.dynamic.datasource.DynamicDataSourceConfig;
import com.osys.dynamic.datasource.service.RegisterConnectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p><b>{@link InitializeRunner} Description</b>: 将所有的数据源连接信息，从数据库中读取出来，并保存在 {@link DynamicDataSourceConfig} 中
 * </p>
 * @author Created by osys on 2022/08/30 10:14.
 */
@Component(value = "initializeRunner")
public class InitializeRunner implements CommandLineRunner {

    /** 假设数据库连接信息保存在项目主数据库的表中 */
    private RegisterConnectService registerConnectService;

    private DynamicDataSourceConfig dynamicDataSourceConfig;

    @Autowired
    @Qualifier(value = "registerConnectService")
    public void setRegisterConnectService(RegisterConnectService registerConnectService) {
        this.registerConnectService = registerConnectService;
    }

    @Autowired
    @Qualifier(value = "dynamicDataSourceConfig")
    public void setDynamicDataSourceConfig(DynamicDataSourceConfig dynamicDataSourceConfig) {
        this.dynamicDataSourceConfig = dynamicDataSourceConfig;
    }

    @Override
    public void run(String... args) {
        registerDataSource();
    }

    private void registerDataSource() {
        List<DataSourceProperty> dataSourcePropertyList = registerConnectService.selectProperty();
        for (DataSourceProperty dataSourceProperty : dataSourcePropertyList) {
            // 默认使用 com.zaxxer.hikari.HikariDataSource
            dataSourceProperty.setClazzType(com.zaxxer.hikari.HikariDataSource.class);
            dynamicDataSourceConfig.registerDataSource(dataSourceProperty);
        }
    }
}
