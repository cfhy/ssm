package com.yyb.controller;

import com.yyb.model.Member;
import com.yyb.model.ZhangWu;
import com.yyb.service.MemberService;
import com.yyb.service.ZhangWuService;
import com.yyb.utils.ResponseData;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/member")
public class MemberController extends BaseController {
    private MemberService memberService = new MemberService();


    @ResponseBody
    @RequestMapping(value = "login")
    public ResponseData commitLogin(String username, String password, HttpServletRequest request) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return ResponseData.fail("用户名密码不能为空");
        }
        //根据用户名和密码判断是、系统是否存在
        Member member = memberService.findMember(username, password);
        if (member == null) {
            return ResponseData.fail("用户名密码错误");
        }
        if (request.getSession().getAttribute("member") == null) {
            request.getSession().setAttribute("member", member);
        }
        return ResponseData.success();
    }

    /**
     * 添加|修改会员的方法,接收用户的输入的信息，封装成Member对象
     **/
    @RequestMapping(value = "/save")
    public ResponseData addMember(Member member) {
        if(member.getId()!=null && member.getId()>0){
            memberService.editMember(member);
            return ResponseData.success("","修改会员成功");
        }else {
            memberService.addMember(member);
            return ResponseData.success("","添加会员成功");
        }
    }

    /**
     * 实现会员删除,接收用户的输入，输入一个主键数据
     **/
    @RequestMapping(value = "/delete")
    public ResponseData deleteMember(int memberId) {
        memberService.deleteMember(memberId);
        return ResponseData.success("","删除会员成功");
    }

    /**
     * 查询所有
     **/
    @RequestMapping(value = "/list")
    public List<Member> findAll(String userName) {
        List<Member> zhangWuList = memberService.selectAll(userName);
        return zhangWuList;
    }

    /**
     * 详情
     **/
    @RequestMapping(value = "/detail")
    public Member memberDetail(Integer memberId) {
        return memberService.detail(memberId);
    }

}
