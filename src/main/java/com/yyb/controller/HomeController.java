package com.yyb.controller;

import com.yyb.model.Member;
import com.yyb.service.MemberService;
import com.yyb.utils.ResponseData;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


@Controller
public class HomeController extends BaseController{
    private MemberService memberService = new MemberService();

    @RequestMapping(value = "/")
    public String index() {
        if (member == null) {
            return "redirect:login";
        }
        return "index";
    }

    @RequestMapping(value = "/index")
    public String home() {
        if (member == null) {
            return "redirect:login";
        }
        return "index";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/bill")
    public String bill() {
        return "bill";
    }

    @RequestMapping(value = "/addBill")
    public String addBill() {
        return "addBill";
    }

    @ResponseBody
    @RequestMapping(value = "commitLogin")
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
     * 添加会员的方法,接收用户的输入的信息，封装成Member对象
     **/
    @RequestMapping(value = "/add")
    public ResponseData addMember(Member member) {
        memberService.addMember(member);
        return ResponseData.success("添加会员成功");
    }

    /**
     * 实现对会员的编辑功能,接收用户的输入的信息，封装成Member对象
     **/
    @RequestMapping(value = "/edit")
    public ResponseData editZhangWu(Member member) {
        memberService.editMember(member);
        return ResponseData.success("修改会员成功");
    }

    /**
     * 实现会员删除,接收用户的输入，输入一个主键数据
     **/
    @RequestMapping(value = "/delete")
    public ResponseData deleteMember(int memberId) {
        memberService.deleteMember(memberId);
        return ResponseData.success("删除会员成功");
    }

    /**
     * 查询所有
     **/
    @RequestMapping(value = "/list")
    public ResponseData findAll() {
        List<Member> zhangWuList = memberService.selectAll();
        return ResponseData.success(zhangWuList);
    }

}
