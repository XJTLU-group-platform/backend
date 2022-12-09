package com.xyh.can301.service;

import com.xyh.can301.entity.User;

import java.util.UUID;

public interface UserService {

    String getUid(String uname);

    int saveUser(User u);

    User getUser(String id);

    String getUserPassword(String id);

}
