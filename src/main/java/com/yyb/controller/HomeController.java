package com.yyb.controller;

import com.yyb.utils.ResponseData;
import com.yyb.model.ZhangWu;
import com.yyb.service.ZhangWuService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/zhangwu")
public class ZhangWuController {
    private ZhangWuService zhangWuService = new ZhangWuService();

    /**
     * 添加账务的方法,接收用户的输入的信息，封装成ZhangWu对象
     **/
    @RequestMapping(value = "/add")
    public ResponseData addZhangWu(ZhangWu zw) {
        zhangWuService.addZhangWu(zw);
        return ResponseData.success("添加成功");
    }

    /**
     * 实现对账务的编辑功能,接收用户的输入的信息，封装成ZhangWu对象
     **/
    @RequestMapping(value = "/edit")
    public ResponseData editZhangWu(ZhangWu zw) {
        zhangWuService.editZhangWu(zw);
        return ResponseData.success("修改成功");
    }

    /**
     * 实现账务删除,接收用户的输入，输入一个主键数据
     **/
    @RequestMapping(value = "/delete")
    public ResponseData deleteZhangWu(int zwid) {
        zhangWuService.deleteZhangWu(zwid);
        return ResponseData.success("删除成功");
    }

    /**
     * 查询所有
     **/
    @RequestMapping(value = "/list")
    public ResponseData findAll() {
        List<ZhangWu> zhangWuList = zhangWuService.selectAll();
        return ResponseData.success(zhangWuList);
    }

    /**
     * 带条件查询
     **/
    @RequestMapping(value = "/findByWhere")
    public ResponseData findByWhere(String startDate, String endDate) {
        List<ZhangWu> zhangWuList = zhangWuService.select(startDate, endDate);
        return ResponseData.success(zhangWuList);
    }

}
