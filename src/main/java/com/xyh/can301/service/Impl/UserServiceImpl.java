package com.xyh.can301.service.Impl;

import com.xyh.can301.entity.User;
import com.xyh.can301.mapper.UserMapper;
import com.xyh.can301.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public String getUid(String uname) {
        Object o = userMapper.getUserId(uname);
        if(o == null){
            return "null";
        }else {
            return (String)o;
        }
    }


    public int saveUser(User u) {
        return userMapper.addUser(u);
    }

    public String getUserPassword(String id) {
        if(getUser(id) == null){
            return "null";
        }
        return getUser(id).getUpassword();
    }

    @Override
    public User getUser(String id) {
        List<String> idList = userMapper.getUserIdList();
        if (idList.contains(id)){
            return userMapper.findUserById(id);
        } else {
            return null;
        }
    }
}
