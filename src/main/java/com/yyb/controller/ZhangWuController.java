package com.yyb.controller;

import com.yyb.utils.ResponseData;
import com.yyb.model.ZhangWu;
import com.yyb.service.ZhangWuService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/zhangwu")
public class ZhangWuController extends BaseController {
    private ZhangWuService zhangWuService = new ZhangWuService();

    /**
     * 保存账务的方法,接收用户的输入的信息，封装成ZhangWu对象
     **/
    @RequestMapping(value = "/save")
    public ResponseData addZhangWu(ZhangWu zw) {
        if (member == null) {
            return ResponseData.success("登录已过期");
        }
        zw.setMemberId(member.getId());
        if(zw.getZwid()!=null && zw.getZwid()>0){
            zhangWuService.editZhangWu(zw);
            return ResponseData.success("","修改成功");
        }else {
            Date d = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            zw.setCreatetime(sdf.format(d));
            zhangWuService.addZhangWu(zw);
            return ResponseData.success("","添加成功");
        }
    }

    /**
     * 实现账务删除,接收用户的输入，输入一个主键数据
     **/
    @RequestMapping(value = "/delete")
    public ResponseData deleteZhangWu(int zwid) {
        if (member == null) {
            return ResponseData.success("","登录已过期");
        }
        zhangWuService.deleteZhangWu(zwid);
        return ResponseData.success("","删除成功");
    }

    /**
     * 带条件查询
     **/
    @RequestMapping(value = "/list")
    public List<ZhangWu> findByWhere(String startDate, String endDate) {
        List<ZhangWu> zhangWuList =null;
        if (member!= null) {
            if(!StringUtils.isEmpty(startDate) && !StringUtils.isEmpty(endDate)){
                zhangWuList =   zhangWuService.select(startDate, endDate,member.getId());
            }else{
                zhangWuList = zhangWuService.selectAll(member.getId());
            }
        }
        return zhangWuList;
    }

    /**
     * 获取详情
     **/
    @RequestMapping(value = "/detail")
    public ZhangWu detail(int zwid) {
        return zhangWuService.detail(zwid);
    }

}
