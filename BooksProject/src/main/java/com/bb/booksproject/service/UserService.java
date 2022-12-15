package com.bb.booksproject.service;

import com.bb.booksproject.pojo.User;

import java.util.List;

/**
 * 用户的service接口
 */
public interface UserService {
    public List<User> getUser(User user);

    public  Integer addUser(User user);

    public Integer deleteById(Integer id);

    public User queryById(Integer id);

    public Integer updateUser(User user);

    public String checkUserName(String username);

    public User checkUserNameAndPassword(String username,String password);
}
