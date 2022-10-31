package com.osys.dynamic;

import com.osys.dynamic.datasource.DataSourceProperty;
import com.osys.dynamic.datasource.service.RegisterConnectService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DataSourcePointApplicationTests {

    @Autowired
    @Qualifier(value = "registerConnectService")
    private RegisterConnectService registerConnectService;

    @Test
    void contextLoads() {
    }

    @Test
    void selectPropertyTest() {
        List<DataSourceProperty> dataSourcePropertyList = registerConnectService.selectProperty();
        for (DataSourceProperty dataSourceProperty : dataSourcePropertyList) {
            System.out.println(dataSourceProperty);
        }
    }
}
