package com.osys.dynamic;

import com.osys.dynamic.datasource.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

/**
 * <p><b>{@link DataSourcePointApplication} Description</b>: app
 * </p>
 * @author Created by osys on 2022/08/28 10:51.
 */
@Import(DynamicDataSourceRegister.class)
@SpringBootApplication
@EnableAspectJAutoProxy
public class DataSourcePointApplication {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(DataSourcePointApplication.class, args);
    }

}
