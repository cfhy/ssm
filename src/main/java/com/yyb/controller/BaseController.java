package com.yyb.controller;

import com.yyb.model.Member;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    protected Member member = null;

    @ModelAttribute
    public Member getMemberInfo(HttpServletRequest request) {
        Object m = request.getSession().getAttribute("member");
        if (m != null) {
            member = (Member) m;
        }
        return member;
    }
}
