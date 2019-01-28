package com.yyb.tasks;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yyb.common.utils.JacksonUtils;
import com.yyb.model.Country;
import com.yyb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

/**
 * @author yyb
 * @date 2018/11/22 13:45
 * @desc
 */
@Component
@EnableScheduling
@Transactional(readOnly = true,rollbackFor = {RuntimeException.class,Exception.class})
public class ScheduledTasks {
    @Autowired
    private CountryService countryService;

    /**
     * 每30秒执行一次
     */
    @Scheduled(cron = "0/30 * * * * ? ")
    @Transactional(readOnly = false,rollbackFor = {RuntimeException.class,Exception.class})
    public void auctionTask() throws JsonProcessingException {
        Integer id=(int) (Math.random() * 100);
        Country country = new Country();
        country.setId(id);
        country.setCountrycode("china"+id);
        country.setCountryname("中华人民共和国"+id);
        countryService.insert(country);

        Country country1 = countryService.queryOne(id);
        String json = JacksonUtils.obj2json(country1);
        System.out.println(json);
    }
}