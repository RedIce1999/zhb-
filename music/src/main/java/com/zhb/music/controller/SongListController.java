package com.zhb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhb.music.domain.SongList;
import com.zhb.music.service.impl.SongListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@RestController
@Controller
public class SongListController {

    @Autowired
    private SongListServiceImpl songListService;


//    添加歌单
    @ResponseBody
    @RequestMapping(value = "/songList/add", method = RequestMethod.POST)
    public Object addSongList(SongList songList){
        JSONObject jsonObject = new JSONObject();

        boolean res = songListService.addSongList(songList);
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

//    返回所有歌单
    @RequestMapping(value = "/songList", method = RequestMethod.GET)
    public Object allSongList(){
        return songListService.allSongList();
    }

//    返回指定标题对应的歌单
    @RequestMapping(value = "/songList/title/detail", method = RequestMethod.GET)
    public Object songListOfTitle(String title){

        return songListService.songListOfTitle(title);
    }

//    返回标题包含文字的歌单
    @RequestMapping(value = "/songList/likeTitle/detail", method = RequestMethod.GET)
    public Object songListOfLikeTitle(String title){
        return songListService.likeTitle('%'+ title + '%');
    }

//    返回指定类型的歌单
    @RequestMapping(value = "/songList/style/detail", method = RequestMethod.GET)
    public Object songListOfStyle(String style){
        return songListService.likeStyle('%'+ style + '%');
    }

//    删除歌单
    @RequestMapping(value = "/songList/delete", method = RequestMethod.GET)
    public Object deleteSongList(String id){
        return songListService.deleteSongList(Integer.parseInt(id));
    }

//    更新歌单信息
    @ResponseBody
    @RequestMapping(value = "/songList/update", method = RequestMethod.POST)
    public Object updateSongListMsg(SongList songList){
        JSONObject jsonObject = new JSONObject();

        boolean res = songListService.updateSongListMsg(songList);
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

//    更新歌单图片
    @ResponseBody
    @RequestMapping(value = "/songList/img/update", method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songListPic" ;
        System.out.println(System.getProperty("user.dir"));
        System.out.println(fileName);
        System.out.println(filePath);
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
            System.out.println("创建了文件夹");
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/songListPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatorPath);
            boolean res = songListService.updateSongListImg(songList);
            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("avator", storeAvatorPath);
                jsonObject.put("msg", "上传成功");
                return jsonObject;
            }else {
                jsonObject.put("code", 0);
                jsonObject.put("msg", "上传失败");
                return jsonObject;
            }
        }catch (IOException e){
            jsonObject.put("code", 0);
            jsonObject.put("msg", "上传失败" + e.getMessage());
            System.out.println("文件传输失败");
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}















