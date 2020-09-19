package com.zhb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhb.music.domain.Consumer;
import com.zhb.music.service.impl.ConsumerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Controller
public class ConsumerController {

    @Autowired
    private ConsumerServiceImpl consumerService;

//    添加用户
    @ResponseBody
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public Object addUser(Consumer consumer){

        JSONObject jsonObject = new JSONObject();
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());
        boolean res = consumerService.addUser(consumer);
        if (res) {
            jsonObject.put("code", 1);
            jsonObject.put("msg", "注册成功");
            return jsonObject;
        } else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "注册失败");
            return jsonObject;
        }
    }

//    判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/user/login/status", method = RequestMethod.POST)
    public Object loginStatus(Consumer consumer, HttpSession session){

        JSONObject jsonObject = new JSONObject();
        String username=consumer.getUsername();
        String password=consumer.getPassword();
        boolean res = consumerService.veritypasswd(username, password);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "登录成功");
            jsonObject.put("userMsg", consumerService.loginStatus(username));
            session.setAttribute("username", username);
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "用户名或密码错误");
            return jsonObject;
        }


    }

//    返回所有用户
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object allUser(){
        return consumerService.allUser();
    }

//    返回指定ID的用户
    @RequestMapping(value = "/user/detail", method = RequestMethod.GET)
    public Object userOfId(String id){

        return consumerService.userOfId(Integer.parseInt(id));
    }

//    删除用户
    @RequestMapping(value = "/user/delete", method = RequestMethod.GET)
    public Object deleteUser(String id){

        return consumerService.deleteUser(Integer.parseInt(id));
    }

//    更新用户信息
    @ResponseBody
    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public Object updateUserMsg(Consumer consumer){
        JSONObject jsonObject = new JSONObject();
        consumer.setUpdateTime(new Date());
        boolean res = consumerService.updateUserMsg(consumer);
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

//    更新用户头像
    @ResponseBody
    @RequestMapping(value = "/user/avatar/update", method = RequestMethod.POST)
    public Object updateUserPic(@RequestParam("file") MultipartFile avatorFile, @RequestParam("id")int id){
        JSONObject jsonObject = new JSONObject();

        if (avatorFile.isEmpty()) {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "文件上传失败！");
            return jsonObject;
        }
        String fileName = System.currentTimeMillis()+avatorFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "avatorImages" ;
        File file1 = new File(filePath);
        if (!file1.exists()){
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeAvatorPath = "/avatorImages/"+fileName;
        try {
            avatorFile.transferTo(dest);
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatorPath);
            boolean res = consumerService.updateUserAvator(consumer);
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
            jsonObject.put("msg", "上传失败"+e.getMessage());
            return jsonObject;
        }finally {
            return jsonObject;
        }
    }
}
