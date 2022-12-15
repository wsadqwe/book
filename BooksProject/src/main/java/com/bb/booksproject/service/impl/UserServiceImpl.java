package com.bb.booksproject.service.impl;

import com.bb.booksproject.dao.UserDao;
import com.bb.booksproject.dao.impl.UserDaoImpl;
import com.bb.booksproject.pojo.User;
import com.bb.booksproject.service.UserService;

import java.util.List;

/**
 * 用户service接口的实现
 * 通过和Dao的调用来完成复杂的业务逻辑处理
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 获取用户表单
     * @param user
     * @return
     */
    @Override
    public List<User> getUser(User user) {
       //处理对应的业务逻辑
        return userDao.list(user);
    }

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Override
    public Integer addUser(User user) {
        return userDao.save(user);
    }

    /**
     * 通过id删除用户
     * @param id
     * @return
     */
    @Override
    public Integer deleteById(Integer id) {
         return userDao.deleteById(id);
    }

    /**
     * 通过id进行单挑用户信息擦查询
     * @param id
     * @return
     */
    @Override
    public User queryById(Integer id) {
        return userDao.queryById(id);
    }

    /**
     *更新用户信息
     * @param user
     * @return
     */
    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    /**
     * 验证用户名是否存在
     * @param username
     * @return
     */
    @Override
    public String checkUserName(String username) {
        return userDao.checkUserName(username);
    }

    /**
     * 验证用户名和密码
     * @param username
     * @param password
     * @return
     */
    @Override
    public User checkUserNameAndPassword(String username, String password) {
        return userDao.checkUserNameAndPassword(username,password);
    }
}
