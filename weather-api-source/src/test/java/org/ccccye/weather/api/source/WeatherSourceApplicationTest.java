package org.ccccye.weather.api.source;


import org.ccccye.weather.api.source.service.WeatherService;
import org.ccccye.weather.common.vo.WeatherVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherSourceApplication.class)
public class WeatherSourceApplicationTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private WeatherService weatherService;

    @Test
    public void redisTest(){
        redisTemplate.opsForValue().set("age", "18");
        System.out.println(redisTemplate.opsForValue().get("age"));
    }

    @Test
    public void getWeatherTest(){

        WeatherVo v1 = weatherService.getWeatherInfo("123");
        System.out.println(v1);
        WeatherVo v2 = weatherService.getWeatherInfo("456");
        System.out.println(v2);
    }

    @Test
    public void redisListTest(){
        long ret = redisTemplate.opsForList().rightPush("book", "Study1");
        System.out.println("s1:ret:"+ret);
        ret = redisTemplate.opsForList().rightPush("book", "Study2");
        System.out.println("s2:ret:"+ret);
        ret = redisTemplate.opsForList().rightPush("book", "Study3");
        System.out.println("s3:ret:"+ret);
    }

    @Test
    public void redisListLPOPTest(){
         String book = (String) redisTemplate.opsForList().leftPop("book");
        System.out.println(book);
        book = (String) redisTemplate.opsForList().leftPop("book");
        System.out.println(book);
        book = (String) redisTemplate.opsForList().leftPop("book");
        System.out.println(book);
        book = (String) redisTemplate.opsForList().leftPop("book");
        System.out.println(book);
    }
}