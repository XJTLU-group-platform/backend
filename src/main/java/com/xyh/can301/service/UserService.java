package com.xyh.can301.service;

import com.xyh.can301.entity.User;

public interface UserService {

    long getUid(String uname);

    int saveUser(User u);

    User getUser(int id);

    String getUserPassword(int id);

}
