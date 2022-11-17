package com.xyh.can301.mapper;

import com.xyh.can301.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface UserMapper {


    Object getUserId(String username);

    int addUser(User u);

    List<Integer> getUserIdList();

    User findUserById(int id);
}
