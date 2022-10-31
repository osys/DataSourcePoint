package com.osys.dynamic.example.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.osys.dynamic.example.dto.User;
import com.osys.dynamic.example.dto.UserLoveGame;
import com.osys.dynamic.example.dto.UserQq;
import com.osys.dynamic.example.dto.UserSchool;
import com.osys.dynamic.example.dto.UserShopping;
import com.osys.dynamic.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p><b>{@link UserService} Description</b>: 使用不同数据源查询的 service
 * </p>
 *
 * @author Created by osys on 2022/08/29 16:58.
 */
@Service(value = "userService")
public class UserService {

    private UserMapper userMapper;

    @Autowired
    @Qualifier(value = "userMapper")
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public JSONArray selectUsers() {
        List<User> users = userMapper.selectUsers();
        JSONArray jsonArray = new JSONArray();
        for (User user : users) {
            JSONObject jsonObj = JSONObject.parseObject(JSONObject.toJSONString(user));
            long birthday = Long.parseLong(String.valueOf(jsonObj.get("birthday"))) * 1000;
            jsonObj.put("birthday", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(birthday)));
            jsonArray.add(jsonObj);
        }
        return jsonArray;
    }

    public JSON selectAllUserShopping(String dbKey) {
        List<UserShopping> userShoppingList = userMapper.selectAllUserShopping(dbKey);
        String toJSONString = JSONObject.toJSONString(userShoppingList);
        return (JSON) JSON.parse(toJSONString);
    }

    public JSON selectAllUserQq(String dbKey) {
        List<UserQq> userQqs = userMapper.selectAllUserQq(dbKey);
        String toJSONString = JSONObject.toJSONString(userQqs);
        return (JSON) JSON.parse(toJSONString);
    }

    public JSONArray selectAllUserLoveGame(String dbKey) {
        List<UserLoveGame> userLoveGameList = userMapper.selectAllUserLoveGame(dbKey);
        JSONArray jsonArr = new JSONArray();
        for (UserLoveGame userLoveGame : userLoveGameList) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("userId", userLoveGame.getUserId());
            jsonObj.put("loveGame", JSONObject.parseObject(userLoveGame.getGameName()));
            jsonArr.add(jsonObj);
        }
        return jsonArr;
    }

    public JSONArray selectAllUserSchool() {
        List<UserSchool> userSchoolList = userMapper.selectAllUserSchool();
        JSONArray jsonArr = new JSONArray();
        for (UserSchool userSchool : userSchoolList) {
            JSONObject jsonObj = new JSONObject();
            jsonObj.put("userId", userSchool.getUserId());
            jsonObj.put("schoolId", userSchool.getSchoolId());
            jsonObj.put("name", userSchool.getName());
            jsonObj.put("className", userSchool.getClassName());
            List<String> courseArr = JSON.parseArray(userSchool.getCourse(), String.class);
            jsonObj.put("course", JSONArray.toJSON(courseArr));
            jsonArr.add(jsonObj);
        }
        return jsonArr;
    }
}
