package org.ccccye.citycode.controller;

import org.ccccye.citycode.dto.Citycode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class CitycodeController{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取全部的城市编码
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public List<Citycode> getList(){
//        String sql = "select * from CityCode limit 0,10";
        String sql = "select * from CityCode ";
        List<Citycode> citys = jdbcTemplate.query(sql, new RowMapper<Citycode>() {
            @Override
            public Citycode mapRow(ResultSet resultSet, int i) throws SQLException {
                Citycode student = new Citycode();
                student.setCity_ID(resultSet.getString("City_ID"));
                student.setCity_EN(resultSet.getString("City_EN"));
                student.setCity_CN(resultSet.getString("City_CN"));
                student.setAD_code(resultSet.getString("AD_code"));
                return student;
            }
        });

        return citys;
    }

    @RequestMapping(value = "queryByName/{name}", method = RequestMethod.GET)
    public Citycode queryByName(@PathVariable("name") String cityName){
        if (cityName.endsWith("市")){
            cityName = cityName.substring(0, cityName.length()-1);
        }

        String sql = String.format("select * from CityCode where City_CN = '%s'", cityName);

        Citycode city = jdbcTemplate.query(sql, new RowMapper<Citycode>() {
            @Override
            public Citycode mapRow(ResultSet resultSet, int i) throws SQLException {
                Citycode student = new Citycode();
                student.setCity_ID(resultSet.getString("City_ID"));
                student.setCity_EN(resultSet.getString("City_EN"));
                student.setCity_CN(resultSet.getString("City_CN"));
                student.setAD_code(resultSet.getString("AD_code"));
                return student;
            }
        }).get(0);

        return city;
    }

    @RequestMapping(value = "queryByAdcode/{code}", method = RequestMethod.GET)
    public Citycode queryBuAdcode(@PathVariable("code") String adcode){
        String sql = String.format("select * from CityCode where AD_code = '%s'", adcode);

        Citycode city = jdbcTemplate.query(sql, new RowMapper<Citycode>() {
            @Override
            public Citycode mapRow(ResultSet resultSet, int i) throws SQLException {
                Citycode student = new Citycode();
                student.setCity_ID(resultSet.getString("City_ID"));
                student.setCity_EN(resultSet.getString("City_EN"));
                student.setCity_CN(resultSet.getString("City_CN"));
                student.setAD_code(resultSet.getString("AD_code"));
                return student;
            }
        }).get(0);

        return city;
    }
}