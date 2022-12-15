package com.bb.booksproject.dao;

import com.bb.booksproject.pojo.User;

import java.util.List;

/**
 * 用户层接口
 */
public interface UserDao {
    /**
     * 根据用户信息查询用户
     * @param user
     * @return
     */
    public List<User> list(User user);

    /**
     * 添加用户信息
     * @param user
     * @return
     */
    public Integer save(User user);

    /**
     * 根据id删除用户信息
     * @param id
     * @return
     */
    public Integer deleteById(Integer id);

    /**
     * 单条查询，根据用户id查询用户信息
     * @param id
     * @return
     */
    public User queryById(Integer id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public Integer updateUser(User user);

    /**
     * 检查账号是否存在
     * @param username
     * @return
     */
    public String checkUserName(String username);

    /**
     * 认证检查--登录功能
     * @param username
     * @param password
     * @return
     * 返回User如果为null，表示登录失败，反之则表示登录成功
     */
    public User checkUserNameAndPassword(String username,String password);

}
