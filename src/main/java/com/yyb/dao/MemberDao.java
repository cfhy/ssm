package com.yyb.dao;

import com.yyb.model.Member;
import com.yyb.model.ZhangWu;
import com.yyb.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;

/**
 * 实现对数据表 gjp_zhangwu 数据增删改查操作
 * dbuils工具类完成,类成员创建QueryRunner对象,指定数据源
 **/
public class MemberDao {
    private QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

    /*
     * 定义方法,查询数据库,获取所有用户
     * 方法,由业务层调用
     * 结果集,将所有的会员数据,存储到Bean对象中,存储到集合中
     */
    public List<Member> selectAll(String userName) {
        List<Member> list = null;
        try {
            //查询会员数据的SQL语句
            String sql = "SELECT * FROM member";
            if (!StringUtils.isEmpty(userName)) {
                sql += " WHERE userName=?";
                Object[] params = {userName};
                list = qr.query(sql, new BeanListHandler<>(Member.class), params);
            } else {
                //调用qr对象的方法,query方法,结果集BeanListHandler
                list = qr.query(sql, new BeanListHandler<>(Member.class));
            }

            return list;
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException("查询所有账务失败");
        }
    }

    /*
     * 定义方法，实现添加会员功能
     * 由业务层调用，传递Member对象
     * 将Member对象中的数据，添加到数据库
     */
    public void addMember(Member member) {
        try {
            //拼接添加数据的sql
            String sql = "INSERT INTO member (userName,password,isAdmin) VALUES(?,?,?)";
            //创建对象数组
            //实际参数来源是传递过来的对象member
            Object[] params = {member.getUserName(), member.getPassword(), member.getIsAdmin()};
            //调用qr对象中的方法update执行添加
            qr.update(sql, params);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException("会员添加失败");
        }
    }

    /*
     * 定义方法，实现编辑功能
     * 由业务层调用，传递Member对象
     * 将对象中的数据，更新到数据表
     */
    public void editMember(Member member) {
        try {
            //更新数据的SQL
            String sql = "UPDATE member SET userName=?,password=?,isAdmin=? WHERE id=?";
            //定义对象数组，封装所有数据
            Object[] params = {member.getUserName(), member.getPassword(), member.getIsAdmin(),member.getId()};
            //调用qr对象方法update执行更新
            qr.update(sql, params);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException("编辑会员失败");
        }

    }

    /*
     * 定义方法，实现删除业务
     * 业务层调用，传递主键id
     */
    public void deleteMember(int memberId) {
        try {
            //拼写删除数据SQL
            String sql = "DELETE FROM member WHERE id=?";
            qr.update(sql, memberId);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException("删除会员失败");
        }
    }

    /**
     * 根据用户名和密码查询会员
     *
     * @param userName
     * @param password
     * @return
     */
    public Member findMember(String userName, String password) {
        try {
            //拼写条件查询的SQL语句
            String sql = "SELECT * FROM member WHERE userName=? AND password=?";
            //定义对象数组,存储?占位符
            Object[] params = {userName, password};
            //调用qr对象的方法query查询数据表,获取结果集
            return qr.query(sql, new BeanHandler<>(Member.class), params);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException("用户查询失败");
        }
    }

    /**
     * 查询会员详情
     *
     * @param memberId
     * @return
     */
    public Member detail(Integer memberId) {
        try {
            //拼写条件查询的SQL语句
            String sql = "SELECT * FROM member WHERE id=?";
            //定义对象数组,存储?占位符
            Object[] params = {memberId};
            //调用qr对象的方法query查询数据表,获取结果集
            return qr.query(sql, new BeanHandler<>(Member.class), params);
        } catch (SQLException ex) {
            System.out.println(ex);
            throw new RuntimeException("用户查询失败");
        }
    }
}
