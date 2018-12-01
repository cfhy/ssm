package com.yyb.service;

import com.yyb.base.BaseService;
import com.yyb.mapper.CountryMapper;
import com.yyb.model.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class CountryService extends BaseService<Country> {
    @Autowired
    CountryMapper countryMapper;

    public Country queryOne(int id) {
        Country country = countryMapper.queryOne(id);
        return country;
    }

    @Transactional(readOnly = false, rollbackFor = {RuntimeException.class, Exception.class})
    public Integer deleteById(int id) {
        return countryMapper.deleteById(id);
    }
}
