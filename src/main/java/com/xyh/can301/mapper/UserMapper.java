package com.xyh.can301.mapper;

import com.xyh.can301.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;


@Mapper
public interface UserMapper {


    Object getUserId(String username);

    int addUser(User u);

    List<String> getUserIdList();

    User findUserById(String id);
}
