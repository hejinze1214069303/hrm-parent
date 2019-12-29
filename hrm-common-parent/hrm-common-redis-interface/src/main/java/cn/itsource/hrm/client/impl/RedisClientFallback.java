package cn.itsource.hrm.client.impl;

import cn.itsource.hrm.client.RedisClient;
import org.springframework.stereotype.Component;

/**
 * @Description TODo
 * @Author hjz
 * @Date 2019/12/300:21
 * @Version v1.0
 */
@Component
public class RedisClientFallback implements RedisClient {

    @Override
    public void set(String key, String value) {

    }

    @Override
    public String get(String key) {
        return "获取失败!";
    }
}
