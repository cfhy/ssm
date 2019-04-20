package com.yyb.service;


import com.yyb.dao.MemberDao;
import com.yyb.dao.ZhangWuDao;
import com.yyb.model.Member;
import com.yyb.model.ZhangWu;

import java.util.List;

/**
 *  业务层类
 *  接收上一层,视图层view的数据
 *  经过计算,传递给dao层,操作数据库
 *  调用dao层中的类,类成员位置,创建Dao类的对象
 */
public class MemberService {
    private MemberDao dao = new MemberDao();

    /*
	 *  定义方法,实现查询所有的账务数据
	 *  此方法,由控制层调用, 去调用dao层的方法
	 *  返回存储ZhangWu对象的List集合
	 */
    public List<Member> selectAll(){
        return dao.selectAll();
    }

    /*
	 * 定义方法，实现添加账务
	 * 是由控制层调用，传递ZhangWu对象
	 */
    public void addMember(Member member) {
        dao.addMember(member);
    }

    /*
	 * 定义方法，实现编辑会员
	 * 由控制层调用，传递Member对象
	 * 调用dao层的方法，传递Member对象
	 */
    public void editMember(Member member) {
        dao.editMember(member);
    }

    /*
	 * 定义方法，实现删除会员功能
	 * 由控制层调用，传递主键id
	 * 调用dao层方法，传递主键id
	 */
    public void deleteMember(int memberId) {
        dao.deleteMember(memberId);
    }

    /**
     * 根据用户名和密码查询会员
     * @param userName
     * @param password
     * @return
     */
    public Member findMember(String userName, String password){
      return dao.findMember(userName,password);
    }
}
