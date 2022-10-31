package com.osys.dynamic.example.dto;

import java.util.StringJoiner;

/**
 * <p><b>{@link UserQq} Description</b>:
 * </p>
 * @author Created by osys on 2022/09/02 11:32.
 */
public class UserQq {
    private int userId;

    private String account;

    private String password;

    private String createTime;

    private String updateTime;

    public UserQq() {
    }

    public UserQq(int userId, String account, String password, String createTime, String updateTime) {
        this.userId = userId;
        this.account = account;
        this.password = password;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserQq.class.getSimpleName() + "[", "]")
                .add("userId=" + userId)
                .add("account='" + account + "'")
                .add("password='" + password + "'")
                .add("createTime='" + createTime + "'")
                .add("updateTime='" + updateTime + "'")
                .toString();
    }
}
