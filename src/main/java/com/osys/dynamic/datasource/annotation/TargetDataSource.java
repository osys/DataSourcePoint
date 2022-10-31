package com.osys.dynamic.datasource.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p><b>{@link TargetDataSource} Description</b>: 动态使用数据源注解。
 * 作用在方法上，以注解传入的参数 name 为 key，来进行选择 DataSource，如果参数 name 不符合，那么将选择项目默认的(主)数据库
 * </p>
 *
 * @author Created by osys on 2022/08/30 11:09.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    String name();
}
