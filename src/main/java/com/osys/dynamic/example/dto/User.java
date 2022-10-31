package com.osys.dynamic.example.dto;

import java.util.StringJoiner;

/**
 * <p><b>{@link User} Description</b>:
 * </p>
 *
 * @author Created by osys on 2022/08/29 16:56.
 */
public class User {
    private Integer id;
    private String username;
    private String sex;
    private Long birthday;
    private String address;

    public User() {
    }

    public User(Integer id, String username, String sex, Long birthday, String address) {
        this.id = id;
        this.username = username;
        this.sex = sex;
        this.birthday = birthday;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("username='" + username + "'")
                .add("sex='" + sex + "'")
                .add("birthday=" + birthday)
                .add("address='" + address + "'")
                .toString();
    }
}
