package com.xyh.can301.mapper;

import com.xyh.can301.entity.Group;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.UUID;

@Mapper
public interface GroupMapper {


    List<Group> selectGroupByTag(String tag);

    List<Group> selectGroupByUid(String id);

    Group selectGroupById(String id);

    int joinGroup(String gid);

    int joinGroup2(String gid, String uid, String cv);

    int quitGroup(String gid);

    int quitGroup2(String gid, String uid);

    int saveGroup(Group g);

    int saveCv(String uid, String gid, String cv);

    int delGroup(String gid);

    int delGroup2(String gid);

    List<Group> getAllGroup();

    String getOwnerCv(String uid, String gid);
}
