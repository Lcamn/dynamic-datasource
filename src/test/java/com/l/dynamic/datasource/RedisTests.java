package com.l.dynamic.datasource;

import com.l.dynamic.datasource.config.RedisKeyConsts;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class RedisTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void testRedis() {
        for (int i = 0; i < 10; i++) {
            stringRedisTemplate.opsForValue().set("alipay:data:operate:1170083783739147511+" + i, i + "", RedisKeyConsts.DEFAULT_LOCK_PERIOD_SECOND, TimeUnit.DAYS);
        }
    }

    @Test
    void C() {
        LocalDate start = LocalDate.now();
        LocalDate end = LocalDate.now();
        long days = start.until(end, ChronoUnit.DAYS);
        System.out.println(days);
        for (int i = 0; i < days; i++) {
            System.out.println("day+" + i);
        }
    }
}
