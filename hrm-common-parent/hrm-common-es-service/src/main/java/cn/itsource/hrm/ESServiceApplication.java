package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description TODo
 * @Author hjz
 * @Date 2020/1/123:36
 * @Version v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class ESServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ESServiceApplication.class, args);
    }

}
