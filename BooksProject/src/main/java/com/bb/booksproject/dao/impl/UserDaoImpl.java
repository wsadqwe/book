package com.bb.booksproject.dao.impl;

import com.bb.booksproject.dao.UserDao;
import com.bb.booksproject.pojo.User;
import com.bb.booksproject.util.DelFlagE;
import com.bb.booksproject.util.MyDBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * UserDaO接口实现类
 */
public class UserDaoImpl implements UserDao {
    @Override
    public List<User> list(User user) {
        //通过DBUtils来实现数据库的操作
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        //构建sql语句
        String sql = "select * from user where isDeleted = ?";

        List<User> list = new ArrayList<>();

        try {
            list = queryRunner.query(sql,new ResultSetHandler<List<User>>() {
                @Override
                public List<User> handle(ResultSet rs) throws SQLException {
                    List<User> list = new ArrayList<>();
                    while (rs.next()){
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setEmail(rs.getString("email"));
                        user.setPhonenum(rs.getString("phonenum"));
                        user.setIsDeleted(rs.getInt("isDeleted"));
                        user.setSalt(rs.getString("salt"));
                        list.add(user);
                    }
                    return list;
                }
            },DelFlagE.NO.code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }

    /**
     * 添加用户的方法
     * @param user
     * @return
     */
    @Override
    public Integer save(User user) {
        //获取QueryRunner的对象
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "insert into user(username,password,phonenum,email)values(?,?,?,?)";
        try {
            return queryRunner.update(sql,user.getUsername()
                    ,user.getPassword()
                    ,user.getPhonenum()
                    ,user.getEmail());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;//返回0，说明插入失败
    }

    /**
     * 通过id删除用户信息
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(Integer id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        //物理删除
        //String sql = "delete from user where id = ?";
        //逻辑删除
        String sql = "update user set isDeleted = ? where id = ?";

        try {
            //把isDeleted字段更新为0，表示这条记录被删除了（逻辑删除）
            return queryRunner.update(sql, DelFlagE.YES.code,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    /**
     * 通过id进行单挑查询用户信息
     * @param id
     * @return
     */
    @Override
    public User queryById(Integer id) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from user where id = ?  and isDeleted = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        User user = new User();
                        user.setId(id);
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setPhonenum(rs.getString("phonenum"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                    return null;
                }
            },id,DelFlagE.NO.code);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public Integer updateUser(User user) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "update user set username = ?, password = ?, phonenum = ?, email = ? where id = ?";
        try {
            return queryRunner.update(sql,user.getUsername(),
                    user.getPassword(),
                    user.getPhonenum(),
                    user.getEmail(),
                    user.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    /**
     * 验证账号是否存在
     * @param username
     * @return
     */
    @Override
    public String checkUserName(String username) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select count(1) from user where isDeleted = ? and username = ?";
        try {
            int count = queryRunner.query(sql, new ResultSetHandler<Integer>() {
                @Override
                public Integer handle(ResultSet rs) throws SQLException {
                    rs.next();
                    int count = rs.getInt(1);
                    return count;
                }
            },DelFlagE.NO.code,username);
            return count == 0 ? "success" : "error";
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "error";
    }

    /**
     *登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUserNameAndPassword(String username, String password) {
        QueryRunner queryRunner = MyDBUtils.getQueryRunner();
        String sql = "select * from user where isDeleted = ? and username = ? and password = ?";
        try {
            return queryRunner.query(sql, new ResultSetHandler<User>() {
                @Override
                public User handle(ResultSet rs) throws SQLException {
                    if(rs.next()){
                        User user = new User();
                        user.setId(rs.getInt("id"));
                        user.setUsername(rs.getString("username"));
                        user.setPassword(rs.getString("password"));
                        user.setPhonenum(rs.getString("phonenum"));
                        user.setEmail(rs.getString("email"));
                        return user;
                    }
                    return null;
                }
            },DelFlagE.NO.code,username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        UserDaoImpl dao = new UserDaoImpl();
        List<User> list = dao.list(null);
        for(User user:list){
            System.out.println(user);
        }

    }
}
