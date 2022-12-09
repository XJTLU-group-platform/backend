package com.xyh.can301.service.Impl;


import com.xyh.can301.entity.Group;
import com.xyh.can301.mapper.GroupMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GroupServiceImpl {

    @Autowired
    private GroupMapper groupMapper;

    public int saveGroup(Group g, String cv) {
        return groupMapper.saveGroup(g) + groupMapper.saveCv(g.getGleaderid(), g.getGid(), cv);
    }

    public int delGroup(String id) {
        return groupMapper.delGroup(id) + groupMapper.delGroup2(id);
    }

    public List<Group> searchGroup(String tag){
        return groupMapper.selectGroupByTag(tag);
    }

    public List<Group> getAllGroup(){return groupMapper.getAllGroup();}

    public List<Group> searchGroupByUid(String id) {
        return groupMapper.selectGroupByUid(id);
    }

    public Group searchGroupById(String id) {
        return groupMapper.selectGroupById(id);
    }

    public int joinGroup(String gid, String uid, String cv) {
        return groupMapper.joinGroup(gid) + groupMapper.joinGroup2(gid, uid, cv);
    }

    public int quitGroup(String gid, String uid) {
        return groupMapper.quitGroup(gid) + groupMapper.quitGroup2(gid, uid);
    }

    public String geOwnerCv(String uid, String gid) { return groupMapper.getOwnerCv(uid, gid);
    }
}
