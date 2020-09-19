package com.zhb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhb.music.domain.Singer;
import com.zhb.music.domain.Song;
import com.zhb.music.service.impl.SingerServiceImpl;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Controller
public class SingerController {

    @Autowired
    private SingerServiceImpl singerService;


//    添加歌手
    @ResponseBody
    @RequestMapping(value = "/singer/add", method = RequestMethod.POST)
    public Object addSinger(Singer singer){
        JSONObject jsonObject = new JSONObject();

        boolean res = singerService.addSinger(singer);
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

//    返回所有歌手
    @RequestMapping(value = "/singer", method = RequestMethod.GET)
    public Object allSinger(){
        return singerService.allSinger();
    }

//    根据歌手名查找歌手
    @RequestMapping(value = "/singer/name/detail", method = RequestMethod.GET)
    public Object singerOfName(String name){
        return singerService.singerOfName(name);
    }

//    根据歌手性别查找歌手
    @RequestMapping(value = "/singer/sex/detail", method = RequestMethod.GET)
    public Object singerOfSex(String sex){

        return singerService.singerOfSex(Integer.parseInt(sex));
    }

//    删除歌手
    @RequestMapping(value = "/singer/delete", method = RequestMethod.GET)
    public Object deleteSinger(String id){

        return singerService.deleteSinger(Integer.parseInt(id));
    }

//    更新歌手信息
    @ResponseBody
    @RequestMapping(value = "/singer/update", method = RequestMethod.POST)
    public Object updateSingerMsg(Singer singer){
        JSONObject jsonObject = new JSONObject();

        boolean res = singerService.updateSingerMsg(singer);
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

//    更新歌手头像
    @ResponseBody
    @RequestMapping(value = "/singer/avatar/update", method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "singerPic" ;
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/img/singerPic/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatorPath);
            boolean res = singerService.updateSingerPic(singer);
            if (res){
                jsonObject.put("code", 1);
                jsonObject.put("pic", storeAvatorPath);
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
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}

