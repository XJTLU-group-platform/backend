package com.xyh.can301.service.Impl;

import com.xyh.can301.entity.User;
import com.xyh.can301.mapper.UserMapper;
import com.xyh.can301.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    public long getUid(String uname) {
        Object o = userMapper.getUserId(uname);
        if(o == null){
            return -1;
        }else {
            return (long) o;
        }
    }


    public int saveUser(User u) {
        return userMapper.addUser(u);
    }

    public String getUserPassword(int id) {
        if(getUser(id) == null){
            return "null";
        }
        return getUser(id).getUpassword();
    }

    @Override
    public User getUser(int id) {
        List<Integer> idList = userMapper.getUserIdList();
        if (idList.contains(id)){
            return userMapper.findUserById(id);
        } else {
            return null;
        }
    }
}
