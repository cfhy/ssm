package com.yyb.service;

import com.yyb.Application;
import com.yyb.common.utils.FastJsonUtils;
import com.yyb.model.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class CountryServiceTest {
    @Autowired
    CountryService countryService;

    @Test
    public  void  insert() {
        Country country=new Country();
        country.setId(10);
        country.setCountrycode("china");
        country.setCountryname("中国");

        Integer result = countryService.insert(country);

        assertTrue(result>0);
    }

    @Test
    public  void  update() {
        Country country=new Country();
        country.setId(10);
        country.setCountryname("中华人民共和国");

        Integer result = countryService.update(country);

        assertTrue(result>0);
    }

    @Test
    public  void  delete() {
        Integer result = countryService.deleteById(10);
        assertTrue(result>0);
    }

    @Test
    public  void  findOne() {
        Country one = countryService.queryOne(10);
        System.out.println("==============================");
        System.out.println(FastJsonUtils.objectToJson(one));
        System.out.println("==============================");
        assertNotEquals(null,one);
    }
}