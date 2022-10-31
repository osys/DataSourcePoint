package com.osys.dynamic.datasource.aspect;

import com.osys.dynamic.datasource.DynamicDataSourceContextHolder;
import com.osys.dynamic.datasource.annotation.DynamicDataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * <p><b>{@link DynamicDataSourceAspect} Description</b>:
 * 在查询前，根据注解 DynamicDataSource 的 name 设置为 DataSource 的 key，
 * 查询时获取到对应的 DataSource
 * </p>
 * @author Created by osys on 2022/09/01 16:00.
 */
@Aspect
@Component(value = "dynamicDataSourceAspect")
public class DynamicDataSourceAspect {

    @Pointcut("@annotation(dataSource) && args(dataSourceKey,..)")
    public void datasource(DynamicDataSource dataSource, String dataSourceKey) { }

    @Around("datasource(dataSource, dataSourceKey)")
    public Object selectDataSource(ProceedingJoinPoint pjp, DynamicDataSource dataSource, String dataSourceKey) {
        try {
            DynamicDataSourceContextHolder.setDataSourceKey(dataSourceKey);
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            DynamicDataSourceContextHolder.clearDataSourceKey();
        }
    }
}
