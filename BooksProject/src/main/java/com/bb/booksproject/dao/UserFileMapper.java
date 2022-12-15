package com.bb.booksproject.dao;

import com.bb.booksproject.pojo.UserFile;


import java.util.List;

public interface UserFileMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserFile record);

    int insertSelective(UserFile record);

    UserFile selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserFile record);

    int updateByPrimaryKey(UserFile record);

    List<UserFile> queryAll();

}