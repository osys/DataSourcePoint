package com.osys.dynamic.example.controller;

import com.osys.dynamic.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p><b>{@link UserController} Description</b>: 测试查询
 *
 * <ul>接口与数据源：
 *     <li>/user/select    ----> 主数据源</li>
 *     <li>/user/shopping  ----> db_one 数据源</li>
 *     <li>/user/qq        ----> db_two 数据源</li>
 *     <li>/user/game      ----> db_three 数据源</li>
 *     <li>/user/school      ----> db_four 数据源</li>
 * </ul>
 * </p>
 * @author Created by osys on 2022/08/29 16:59.
 */
@RestController
@RequestMapping(path = "/user")
public class UserController {

    private UserService userService;

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.POST, path = "/select")
    @ResponseBody
    public String selectUser(HttpServletRequest request, HttpServletResponse response) {
        // 默认数据库
        return userService.selectUsers().toString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/shopping")
    @ResponseBody
    public String selectAllUserShopping(HttpServletRequest request, HttpServletResponse response) {
        // 数据库 key：db_one
        return userService.selectAllUserShopping("db_one").toString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/qq")
    @ResponseBody
    public String selectAllUserQq(HttpServletRequest request, HttpServletResponse response) {
        // 数据库 key：db_two
        return userService.selectAllUserQq("db_two").toString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/game")
    @ResponseBody
    public String selectAllUserLoveGame(HttpServletRequest request, HttpServletResponse response) {
        // 数据库 key：db_three
        return userService.selectAllUserLoveGame("db_three").toString();
    }

    @RequestMapping(method = RequestMethod.POST, path = "/school")
    @ResponseBody
    public String selectAllUserSchool(HttpServletRequest request, HttpServletResponse response) {
        // 数据库 key：db_four
        return userService.selectAllUserSchool().toString();
    }
}
