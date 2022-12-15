package com.bb.booksproject.service;

import com.bb.booksproject.dao.UserFileMapper;
import com.bb.booksproject.pojo.UserFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserFileService {
    @Autowired
    private UserFileMapper userFileMapper;

    public void update(UserFile userFile) {
        this.userFileMapper.updateByPrimaryKeySelective(userFile);
    }

    public void del(String id) {
        this.userFileMapper.deleteByPrimaryKey(Integer.parseInt(id));
    }

    public UserFile selectById(String id) {
        return this.userFileMapper.selectByPrimaryKey(Integer.parseInt(id))
                ;
    }

    public int add(UserFile userFile) {

        List<UserFile> collect2 = queryAll().stream().filter(x -> x.getId().equals(userFile.getId())).collect(Collectors.toList());

        if (!collect2.isEmpty()) {
            return 1;
        }


        this.userFileMapper.insertSelective(userFile)
        ;
        return 0;
    }

    public List<UserFile> queryAll() {
        return userFileMapper.queryAll();
    }
}
