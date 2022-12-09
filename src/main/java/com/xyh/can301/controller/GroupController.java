package com.xyh.can301.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xyh.can301.entity.Group;
import com.xyh.can301.entity.Result;
import com.xyh.can301.service.Impl.GroupServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/group")
@Slf4j
public class GroupController {

    @Autowired
    private GroupServiceImpl groupService;

    @PostMapping(value = "/add")
    public String add(@RequestBody String groupInfo){
        JSONObject jo = JSON.parseObject(groupInfo);
        String cv = jo.getString("cv");
        Group g = new Group(UUID.randomUUID().toString(), jo.getString("gtag"), jo.getString("gtitle"), jo.getString("gdescription"), jo.getIntValue("gnumber"), 1, jo.getObject("gleaderid", UUID.class).toString());
        log.info("group to be added: " + g.getGtitle());
        int success = groupService.saveGroup(g, cv);
        if (success == 2) {
            log.info("add group successfully: " +  g);
            return JSON.toJSONString(Result.create(200, "add group successfully", g));
        } else {
            return JSON.toJSONString(Result.create(400, "failed to add group "));
        }
    }

    @PostMapping(value = "/del")
    public String del(@RequestBody String gid){
        JSONObject jo = JSON.parseObject(gid);
        String id = jo.getObject("gid", UUID.class).toString();
        int exist = groupService.searchGroupById(id) == null ? 0 : 1;
        if(exist == 0){ //如果这个id的group不存在
            return JSON.toJSONString(Result.fail("the group does not exist"));
        }else {
            int success = groupService.delGroup(id);
            if (success == 2) {
                return JSON.toJSONString(Result.create(200, "del group successfully"));
            } else {
                return JSON.toJSONString(Result.create(400, "failed to del group "));
            }
        }

    }

    @PostMapping(value = "/search")
    public String search(@RequestBody String gtag){
        JSONObject jo = JSON.parseObject(gtag);
        String tag = jo.getString("gtag");
        if (gtag.equals("") && tag == null) {
            List<Group> group_list = groupService.getAllGroup();
            return JSON.toJSONString(Result.create(200,"Get group_list information success", group_list));
        }
        List<Group> group_list = groupService.searchGroup(tag);
        if (group_list ==null){
            return JSON.toJSONString(Result.fail("Get group list failed"));
        }
        List<Group> res = new ArrayList<>();
        for (int i = group_list.size() - 1; i >= 0; i--) {
            res.add(group_list.get(i));
        }
        return JSON.toJSONString(Result.create(200,"Get group_list information success", group_list));

    }

    @PostMapping(value = "/joined")
    public String joined(@RequestBody String uid){
        JSONObject jo = JSON.parseObject(uid);
        String id = jo.getObject("uid", UUID.class).toString();
        List<Group> group_list = groupService.searchGroupByUid(id);
        if (group_list ==null){
            return JSON.toJSONString(Result.fail("Get group list failed"));
        }
        return JSON.toJSONString(Result.create(200,"Get joined group success", group_list));

    }

    @PostMapping(value = "/detail")
    public String detail(@RequestBody String req){
        JSONObject jo = JSON.parseObject(req);
        String gid = jo.getObject("gid", UUID.class).toString();
        String uid = jo.getObject("uid", UUID.class).toString();
        Group g = groupService.searchGroupById(gid);
        String role;
        if(g.getGleaderid() != null && g.getGleaderid().equals(uid)){
            role = "owner";
        }else {
            if(groupService.searchGroupByUid(uid).contains(g)){
                role = "member";
            }else {
                role = "visitor";
            }
        }
        String cv = groupService.geOwnerCv(uid, gid);
        return JSON.toJSONString(Result.create(200, role + ";" + cv, g));
    }

    @PostMapping(value = "/join")
    public String join(@RequestBody String req){
        JSONObject jo = JSON.parseObject(req);
        String gid = jo.getObject("gid", UUID.class).toString();
        String uid = jo.getObject("uid", UUID.class).toString();
        String cv = jo.getString("cv");
        int success = groupService.joinGroup(gid, uid, cv);
        if (success == 2) {
            return JSON.toJSONString(Result.create(200, "join group successfully"));
        } else {
            return JSON.toJSONString(Result.create(400, "failed to join group"));
        }
    }

    @PostMapping(value = "/quit")
    public String quit(@RequestBody String req){
        JSONObject jo = JSON.parseObject(req);
        String gid = jo.getObject("gid", UUID.class).toString();
        String uid = jo.getObject("uid", UUID.class).toString();
        int success = groupService.quitGroup(gid, uid);
        if (success == 2) {
            return JSON.toJSONString(Result.create(200, "quit group successfully"));
        } else {
            return JSON.toJSONString(Result.create(400, "failed to quit group"));
        }
    }



}
