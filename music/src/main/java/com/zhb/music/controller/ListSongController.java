package com.zhb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhb.music.domain.ListSong;
import com.zhb.music.service.impl.ListSongServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class ListSongController {

    @Autowired
    private ListSongServiceImpl listSongService;

//    给歌单添加歌曲
    @ResponseBody
    @RequestMapping(value = "/ListSong/add", method = RequestMethod.POST)
    public Object addListSong(ListSong listSong){
    JSONObject jsonObject = new JSONObject();
    boolean res = listSongService.addListSong(listSong);
    if (res){
        jsonObject.put("code", 1);
        jsonObject.put("msg", "添加成功");
        return jsonObject;
    }else {
        jsonObject.put("code", 0);
        jsonObject.put("msg", "添加失败");
        return jsonObject;
    }
}

//    返回歌单里包含的所有歌曲
    @RequestMapping(value = "/listSong", method = RequestMethod.GET)
    public Object allListSong(){
        return listSongService.allListSong();
    }

//    返回歌单里指定歌单ID的歌曲
    @RequestMapping(value = "/listSong/detail", method = RequestMethod.GET)
    public Object listSongOfSongId(String songListId){
        return listSongService.listSongOfSongId(Integer.parseInt(songListId));
    }

//    删除歌单里的歌曲
    @RequestMapping(value = "/ListSong/delete", method = RequestMethod.GET)
    public Object deleteListSong(String songId){
        return listSongService.deleteListSong(Integer.parseInt(songId));
    }

//    更新歌单里面的歌曲信息
    @ResponseBody
    @RequestMapping(value = "/listSong/update", method = RequestMethod.POST)
    public Object updateListSongMsg(ListSong listSong){
        JSONObject jsonObject = new JSONObject();
        boolean res = listSongService.updateListSongMsg(listSong);
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
