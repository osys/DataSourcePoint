package com.osys.dynamic.example.dto;

/**
 * <p><b>{@link UserSchool} Description</b>: UserSchool
 * </p>
 *
 * @author Created by osys on 2022/10/31 11:41.
 */
public class UserSchool {
    private Integer userId;

    private Integer schoolId;

    private String name;

    private String className;

    private String course;

    public UserSchool() {
    }

    public UserSchool(Integer userId, Integer schoolId, String name, String className, String course) {
        this.userId = userId;
        this.schoolId = schoolId;
        this.name = name;
        this.className = className;
        this.course = course;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Integer schoolId) {
        this.schoolId = schoolId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
