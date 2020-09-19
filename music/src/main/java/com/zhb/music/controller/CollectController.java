package com.zhb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhb.music.domain.Collect;
import com.zhb.music.service.impl.CollectServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@Controller
public class CollectController {

    @Autowired
    private CollectServiceImpl collectService;

//    添加收藏的歌曲
    @ResponseBody
    @RequestMapping(value = "/collection/add", method = RequestMethod.POST)
    public Object addCollection(Collect collect){

        JSONObject jsonObject = new JSONObject();
        if (collectService.existSongId(collect.getUserId(), collect.getSongId()) ){
            jsonObject.put("code", 2);
            jsonObject.put("msg", "已收藏");
            return jsonObject;
        }
        //type:0代表歌曲，1代表歌单
        if (collect.getType() == 0) {
            collect.setSongListId(null);
        } else if (collect.getType() == 1) {
            collect.setSongId(null);
        }
        collect.setCreateTime(new Date());
        boolean res = collectService.addCollection(collect);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "收藏成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "收藏失败");
            return jsonObject;
        }
    }

//    返回所有用户收藏列表
    @RequestMapping(value = "/collection", method = RequestMethod.GET)
    public Object allCollection(){
        return collectService.allCollect();
    }

//    返回的指定用户ID收藏列表
    @RequestMapping(value = "/collection/detail", method = RequestMethod.GET)
    public Object collectionOfUser(String userId){
        return collectService.collectionOfUser(Integer.parseInt(userId));
    }

//    删除收藏的歌曲
    @RequestMapping(value = "/collection/delete", method = RequestMethod.GET)
    public Object deleteCollection(@Param("userId") String userId, @Param("songId") String songId){
        return collectService.deleteCollect(Integer.parseInt(userId), Integer.parseInt(songId));
    }

//    更新收藏
    @ResponseBody
    @RequestMapping(value = "/collection/update", method = RequestMethod.POST)
    public Object updateCollectMsg(Collect collect){
        JSONObject jsonObject = new JSONObject();
        boolean res = collectService.updateCollectMsg(collect);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "修改成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "修改失败");
            return jsonObject;
        }
    }
}

