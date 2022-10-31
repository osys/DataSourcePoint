可以拉取使用，在Spring配置文件中，配置好对应的MySQL连接信息。如：

```yaml
# 这里是 yml 配置，可能你的项目是 properties 配置，思路也是一样的
spring:
  datasource:
    name: 'dataSourceUrl'
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: 'jdbc:mysql://127.0.0.1:3306/point_connect_demo?useUnicode=true&characterEncoding=utf-8&serverTimezone=Asia/Shanghai'
    username: 'root'
    password: '123456'
```

实质，后边会在注册主数据源的时候读区该信息(DynamicDataSourceRegister.java)：

```java
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
```

如有问题，修改 `DynamicDataSourceRegister#setEnvironment(Environment)` 方法，使其能创建主数据源即可。



其次需要在 SpringApplication 中引入 `DynamicDataSourceRegister.class`，以及增加 `@EnableAspectJAutoProxy`：

```java
package com.osys.dynamic;

import com.osys.dynamic.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

@Import(DynamicDataSourceRegister.class)
@SpringBootApplication
@EnableAspectJAutoProxy
public class DataSourcePointApplication {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(DataSourcePointApplication.class, args);
    }

}
```



在数据源连接信息这块，也饿时需要修改的，你的项目99.999%不会和这里一样，只需要将自己的项目多个数据库连接信息保存在 `DynamicDataSourceConfig` 中即可。

```java
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
 * 将所有的数据源连接信息，从数据库中读取出来，并保存在 {@link DynamicDataSourceConfig} 中mingt
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
```



使用定义的 `TargetDataSource` 和 `DynamicDataSource` 注解选择不同的数据源：可以在 Service 层，也可以在 Repository 层。注解作用在方法上

```java
@DynamicDataSource
public Object method(String dbKey) {
    // ...
}

@TargetDataSource(name = "dbKey")
public Object method() {
    // ...
}
```

