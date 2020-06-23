package org.ccccye.weather.controller;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ccccye.weather.common.ServerResponse;
import org.ccccye.weather.common.utils.SimpleHttpTestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@RunWith(SpringRunner.class)
@SpringBootTest // 指定启动类
public class WeatherControllerTest {
    private final static Log logger = LogFactory.getLog(WeatherControllerTest.class);
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        //MockMvcBuilders.webAppContextSetup(WebApplicationContext context)：指定WebApplicationContext，将会从该上下文获取相应的控制器并得到相应的MockMvc；
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//建议使用这种
    }

    @Test
    public void getWeatherInfo() {
        String responseString = null; //将相应的数据转换为字符
        try {
            responseString = mockMvc.perform(MockMvcRequestBuilders.get("/weather/get.do?adcode=440300") //请求的url,请求的方法是get
                    .contentType(MediaType.APPLICATION_JSON_UTF8) //数据的格式
                    .accept(MediaType.APPLICATION_JSON_UTF8)
            ).andExpect(MockMvcResultMatchers.status().isOk())  //返回的状态是200
                    .andDo(MockMvcResultHandlers.print()) //打印出请求和相应的内容
                    .andReturn().getResponse().getContentAsString();
        } catch (Exception e) {
            logger.error(e);
//            e.printStackTrace();
        }
//        System.out.println(responseString);

    }
}