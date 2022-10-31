package com.osys.dynamic.datasource.aspect;

import com.osys.dynamic.datasource.DynamicDataSourceContextHolder;
import com.osys.dynamic.datasource.annotation.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * <p><b>{@link TargetDataSourceAspect} Description</b>:
 * 在查询前，根据注解 TargetDataSource 的 name 设置为 DataSource 的 key，查询时获取到对应的 DataSource
 * </p>
 *
 * @author Created by osys on 2022/08/30 11:09.
 */
@Aspect
@Component
public class TargetDataSourceAspect {
    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, TargetDataSource ds) throws Throwable {
        DynamicDataSourceContextHolder.setDataSourceKey(ds.name());
    }

    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
        DynamicDataSourceContextHolder.clearDataSourceKey();
    }
}
