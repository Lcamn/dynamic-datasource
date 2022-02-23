package com.l.dynamic.datasource;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.oas.annotations.EnableOpenApi;

@Slf4j
@EnableOpenApi
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.l.dynamic.datasource.mapper")
public class DynamicDatasourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDatasourceApplication.class, args);
        log.info("启动成功。。");
    }

}
