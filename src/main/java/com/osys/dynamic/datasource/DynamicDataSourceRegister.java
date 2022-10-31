package com.osys.dynamic.datasource;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p><b>{@link DynamicDataSourceRegister} Description</b>: 将主数据源、动态数据源注册为 Bean
 * </p>
 * @author Created by osys on 2022/08/30 17:19.
 */
@Configuration(value = "dynamicDataSourceRegister")
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private DataSource defaultDataSource;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 所有数据源
        Map<String, Object> targetDataSources = new HashMap<>();
        // 将主数据源添加到 targetDataSources 中
        targetDataSources.put("dataSource", defaultDataSource);

        // 创建 DynamicDataSource 路由类，并注册到容器里做数据源
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSourceResolver.class);
        beanDefinition.setSynthetic(true);

        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        // 命名要求如此：
        // 默认(主)数据源 -> defaultTargetDataSource
        // 目标(其它)数据源 -> targetDataSources
        // 详情请看：AbstractRoutingDataSource.determineTargetDataSource() 方法
        propertyValues.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        propertyValues.addPropertyValue("targetDataSources", targetDataSources);

        registry.registerBeanDefinition("dataSources", beanDefinition);
    }

    @Override
    public void setEnvironment(Environment environment) {
        String mainUrl = environment.getProperty("spring.datasource.url");
        String mainUser = environment.getProperty("spring.datasource.username");
        String mainPassword = environment.getProperty("spring.datasource.password");
        String mainDriverClassName = environment.getProperty("spring.datasource.driver-class-name");

        // 主数据源
        String uri = mainUrl.split("//")[1];
        String hostPort = uri.split("/")[0];
        String[] dsNameParma = uri.split("/")[1].split("\\?");
        String[] parma = dsNameParma[1].split("&");
        DataSourceProperty defaultDataSourceProperty =
                new DataSourceProperty(
                        mainDriverClassName,
                        mainUser,
                        mainPassword,
                        hostPort.split(":")[0],
                        hostPort.split(":")[1],
                        dsNameParma[0],
                        "defaultDataSource");
        this.defaultDataSource = DynamicDataSourceBuilder.create()
                .setPropertyValues(defaultDataSourceProperty).build();
    }
}
