package com.yyb.model;

import java.util.Date;

public class ZhangWu {
    //id
    private Integer  zwid;
    //会员ID
    private Integer memberId;
    //分类名称
    private String flname;
    //记账金额
    private double  money;
    //账户
    private String zhanghu;
    //账务类型
    private String type;
    //创建时间
    private String createtime;
    //账务描述
    private String description;


    public ZhangWu(int zwid, String flname, double money, String zhanghu, String createtime,String type, String description) {
        this.zwid = zwid;
        this.flname = flname;
        this.money = money;
        this.zhanghu = zhanghu;
        this.type = type;
        this.createtime = createtime;
        this.description = description;
    }
    public ZhangWu() {

    }

    public Integer getZwid() {
        return zwid;
    }

    public void setZwid(Integer zwid) {
        this.zwid = zwid;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getFlname() {
        return flname;
    }

    public void setFlname(String flname) {
        this.flname = flname;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getZhanghu() {
        return zhanghu;
    }

    public void setZhanghu(String zhanghu) {
        this.zhanghu = zhanghu;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
