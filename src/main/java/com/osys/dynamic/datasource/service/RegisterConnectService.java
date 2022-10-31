package com.osys.dynamic.datasource.service;

import com.osys.dynamic.datasource.DataSourceProperty;
import com.osys.dynamic.datasource.mapper.RegisterConnectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p><b>{@link RegisterConnectService} Description</b>: 多数据源连接信息 service
 * </p>
 * @author Created by osys on 2022/08/30 10:26.
 */
@Service(value = "registerConnectService")
public class RegisterConnectService {

    private RegisterConnectMapper registerConnectMapper;

    @Autowired
    @Qualifier(value = "registerConnectMapper")
    public void setRegisterConnectMapper(RegisterConnectMapper registerConnectMapper) {
        this.registerConnectMapper = registerConnectMapper;
    }

    public List<DataSourceProperty> selectProperty() {
        return registerConnectMapper.selectProperty();
    }
}
