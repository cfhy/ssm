package com.yyb.service;

import com.yyb.base.BaseService;
import com.yyb.common.utils.RedisUtils;
import com.yyb.mapper.CountryMapper;
import com.yyb.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true,rollbackFor = {RuntimeException.class, Exception.class} )
public class CountryService extends BaseService<Country> {
    @Autowired
    CountryMapper countryMapper;
    @Autowired
    RedisUtils redisUtils;

    public Country queryOne(int id) {
        Country country = countryMapper.queryOne(id);
        String key="SSM:COUNTRY:ID:"+id;
        if(!redisUtils.hasKey(key)){
            redisUtils.set(key,country);
        }
        Country obj = (Country)redisUtils.get(key);
        return obj;
    }

    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, Exception.class})
    public Integer deleteById(int id) {
        return countryMapper.deleteById(id);
    }

    /**
     * 演示事务
     * 由于service类上加了@Transactional(readOnly = true)注解，所以除了查询之外，必须添加@Transactional(readOnly = false)
     *
     * @param id
     * @return
     */
    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, Exception.class})
    public void transactional(int id) {
        Country country = new Country();
        country.setId(id);
        country.setCountrycode("china");
        country.setCountryname("中华人民共和国");
        countryMapper.insert(country);

        Country country1 = countryMapper.queryOne(id);
        if (country1 != null) {
            //修改只需要设置ID和要修改的值即可，不需要修改的设置为null,如下：
            Country country2 = new Country();
            country2.setId(id);
            country2.setCountryname("中国");

            //也可以下查询再赋值，如下：
//            country1.setCountryname("中国");
//            countryMapper.update(country1);
        }

        countryMapper.deleteById(id);
    }
}
