package cn.itsource.hrm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description TODo
 * @Author hjz
 * @Date 2019/12/2923:57
 * @Version v1.0
 */
@SpringBootApplication
@EnableEurekaClient
public class RedisServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisServiceApplication.class, args);
    }

}