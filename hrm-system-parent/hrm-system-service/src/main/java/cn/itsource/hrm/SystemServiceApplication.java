package cn.itsource.hrm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description TODo
 * @Author hjz
 * @Date 2019/12/2422:49
 * @Version v1.0
 */
@SpringBootApplication
@MapperScan("cn.itsource.hrm.mapper")
@EnableTransactionManagement
@EnableEurekaClient
@EnableSwagger2
public class SystemServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemServiceApplication.class, args);
    }
}