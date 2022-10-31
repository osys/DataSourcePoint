package com.osys.dynamic.datasource.mapper;

import com.osys.dynamic.datasource.DataSourceProperty;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p><b>{@link RegisterConnectMapper} Description</b>: 多数据源连接信息 mapper
 * </p>
 * @author Created by osys on 2022/08/30 10:28.
 */
@Repository(value = "registerConnectMapper")
@Mapper
public interface RegisterConnectMapper {

    @Select("SELECT * FROM connect_property")
    @Results(value = {
            @Result(column = "key", property = "key", javaType = java.lang.String.class),
            @Result(column = "data_source_name", property = "dataSourceName", javaType = java.lang.String.class),
            @Result(column = "port", property = "port", javaType = java.lang.String.class),
            @Result(column = "host", property = "host", javaType = java.lang.String.class),
            @Result(column = "password", property = "password", javaType = java.lang.String.class),
            @Result(column = "user_name", property = "userName", javaType = java.lang.String.class),
            @Result(column = "driver_class_name", property = "driverClassName", javaType = java.lang.String.class)
    })
    List<DataSourceProperty> selectProperty();
}
