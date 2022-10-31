package com.osys.dynamic.example.mapper;

import com.osys.dynamic.datasource.annotation.DynamicDataSource;
import com.osys.dynamic.datasource.annotation.TargetDataSource;
import com.osys.dynamic.example.dto.User;
import com.osys.dynamic.example.dto.UserLoveGame;
import com.osys.dynamic.example.dto.UserQq;
import com.osys.dynamic.example.dto.UserSchool;
import com.osys.dynamic.example.dto.UserShopping;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p><b>{@link UserMapper} Description</b>: 使用不同数据源查询的 mapper
 * </p>
 * @author Created by osys on 2022/08/29 16:57.
 */
@Repository(value = "userMapper")
@Mapper
public interface UserMapper {

    @Select("SELECT * FROM user")
    List<User> selectUsers();

    @Select("SELECT user_id, platform, shop_name, product_name, price FROM user_shopping")
    @Results(value = {
            @Result(column = "user_id", property = "userId", javaType = java.lang.Integer.class),
            @Result(column = "platform", property = "platform", javaType = java.lang.String.class),
            @Result(column = "shop_name", property = "shopName", javaType = java.lang.String.class),
            @Result(column = "product_name", property = "productName", javaType = java.lang.String.class),
            @Result(column = "price", property = "price", javaType = java.lang.Float.class)
    })
    @DynamicDataSource
    List<UserShopping> selectAllUserShopping(String dbKey);

    @Select("SELECT user_id, account, password, create_time, update_time FROM user_qq")
    @Results(value = {
            @Result(column = "user_id", property = "userId", javaType = java.lang.Integer.class),
            @Result(column = "account", property = "account", javaType = java.lang.String.class),
            @Result(column = "password", property = "password", javaType = java.lang.String.class),
            @Result(column = "create_time", property = "createTime", javaType = java.lang.String.class),
            @Result(column = "update_time", property = "updateTime", javaType = java.lang.String.class)
    })
    @DynamicDataSource
    List<UserQq> selectAllUserQq(String dbKey);

    @Select("SELECT user_id, game_name FROM user_love_game")
    @Results(value = {
            @Result(column = "user_id", property = "userId", javaType = java.lang.Integer.class),
            @Result(column = "game_name", property = "gameName", javaType = java.lang.String.class)
    })
    @DynamicDataSource
    List<UserLoveGame> selectAllUserLoveGame(String dbKey);

    @Select("SELECT name, school_id, user_id, class_name, course FROM school")
    @Results(value = {
            @Result(column = "user_id", property = "userId", javaType = java.lang.Integer.class),
            @Result(column = "name", property = "name", javaType = java.lang.String.class),
            @Result(column = "school_id", property = "schoolId", javaType = java.lang.Integer.class),
            @Result(column = "class_name", property = "className", javaType = java.lang.String.class),
            @Result(column = "course", property = "course", javaType = java.lang.String.class)
    })
    @TargetDataSource(name = "db_four")
    List<UserSchool> selectAllUserSchool();
}
