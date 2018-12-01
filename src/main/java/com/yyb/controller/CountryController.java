package com.yyb.controller;

import com.github.pagehelper.PageInfo;
import com.yyb.base.QueryInfo;
import com.yyb.common.response.ResponseData;
import com.yyb.model.Country;
import com.yyb.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/country")
public class CountryController{

    @Autowired
    private CountryService countryService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResponseData findCountryList(QueryInfo queryInfo){
        PageInfo<Country> pageList = countryService.findPageList(queryInfo);
        return ResponseData.success(pageList);
    }
}
