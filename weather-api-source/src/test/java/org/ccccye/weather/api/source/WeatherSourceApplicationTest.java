package org.ccccye.weather.api.source;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherSourceApplicationTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisTest(){
        redisTemplate.opsForValue().set("age", "18");
        System.out.println(redisTemplate.opsForValue().get("age"));
    }
}