package com.osys.dynamic.datasource.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p><b>{@link DynamicDataSource} Description</b>: 动态使用数据源注解。
 * 作用在方法上，以方法的首个参数为 DataSource 的key，来进行选择 DataSource，如果首个参数不符合，那么将选择项目默认的(主)数据库
 * </p>
 * @author Created by osys on 2022/09/01 16:05.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DynamicDataSource {

    String name() default MASTER;

    public static final String MASTER = "master";

}
