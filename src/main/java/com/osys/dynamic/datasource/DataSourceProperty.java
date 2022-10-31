package com.osys.dynamic.datasource;

import javax.sql.DataSource;
import java.util.StringJoiner;

/**
 * <p><b>{@link DataSourceProperty} Description</b>: 数据库连接配置，数据库连接信息保存在该实体类
 * </p>
 * @author Created by osys on 2022/08/30 10:18.
 */
public class DataSourceProperty {

    private Class<? extends DataSource> clazzType;

    private String driverClassName;

    private String userName;

    private String password;

    private String host;

    private String port;

    private String dataSourceName;

    /** 后边获取该连接信息对应的 key */
    private String key;

    public DataSourceProperty() {
    }

    public DataSourceProperty(String driverClassName, String userName, String password, String host,
                              String port, String dataSourceName, String key) {
        this.driverClassName = driverClassName;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
        this.dataSourceName = dataSourceName;
        this.key = key;
    }

    public DataSourceProperty(Class<? extends DataSource> clazzType, String driverClassName, String userName,
                              String password, String host, String port, String dataSourceName, String key) {
        this.clazzType = clazzType;
        this.driverClassName = driverClassName;
        this.userName = userName;
        this.password = password;
        this.host = host;
        this.port = port;
        this.dataSourceName = dataSourceName;
        this.key = key;
    }

    public Class<? extends DataSource> getClazzType() {
        return clazzType;
    }

    public void setClazzType(Class<? extends DataSource> clazzType) {
        this.clazzType = clazzType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDataSourceName() {
        return dataSourceName;
    }

    public void setDataSourceName(String dataSourceName) {
        this.dataSourceName = dataSourceName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DataSourceProperty.class.getSimpleName() + "[", "]")
                .add("clazzType=" + clazzType)
                .add("driverClassName='" + driverClassName + "'")
                .add("userName='" + userName + "'")
                .add("password='" + password + "'")
                .add("host='" + host + "'")
                .add("port='" + port + "'")
                .add("dataSourceName='" + dataSourceName + "'")
                .add("key='" + key + "'")
                .toString();
    }
}
