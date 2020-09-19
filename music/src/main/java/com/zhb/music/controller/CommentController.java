package com.zhb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhb.music.domain.Comment;
import com.zhb.music.service.impl.CommentServiceImpl;
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
public class CommentController {
    @Autowired
    private CommentServiceImpl commentService;

//  提交评论
    @ResponseBody
    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    public Object addComment(Comment comment){

        JSONObject jsonObject = new JSONObject();
        if (comment.getType() == 0) {
            comment.setSongListId(null);
        } else if (comment.getType() == 1) {
            comment.setSongId(null);
        }
        comment.setCreateTime(new Date());
        boolean res = commentService.addComment(comment);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评论成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评论失败");
            return jsonObject;
        }
    }

//    获取所有评论列表
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public Object allComment(){
        return commentService.allComment();
    }

//    获得指定歌曲ID的评论列表
    @RequestMapping(value = "/comment/song/detail", method = RequestMethod.GET)
    public Object commentOfSongId(String songId){
        return commentService.commentOfSongId(Integer.parseInt(songId));
    }

//    获得指定歌单ID的评论列表
    @RequestMapping(value = "/comment/songList/detail", method = RequestMethod.GET)
    public Object commentOfSongListId(String songListId){
        return commentService.commentOfSongListId(Integer.parseInt(songListId));
    }

//    点赞
    @ResponseBody
    @RequestMapping(value = "/comment/like", method = RequestMethod.POST)
    public Object commentOfLike(Comment comment){

    JSONObject jsonObject = new JSONObject();
    boolean res = commentService.updateCommentMsg(comment);
    if (res){
        jsonObject.put("code", 1);
        jsonObject.put("msg", "点赞成功");
        return jsonObject;
    }else {
        jsonObject.put("code", 0);
        jsonObject.put("msg", "点赞失败");
        return jsonObject;
    }
}

//    删除评论
    @RequestMapping(value = "/comment/delete", method = RequestMethod.GET)
    public Object deleteComment(String id){
        return commentService.deleteComment(Integer.parseInt(id));
    }

//    更新评论
    @ResponseBody
    @RequestMapping(value = "/comment/update", method = RequestMethod.POST)
    public Object updateCommentMsg(Comment comment){
        JSONObject jsonObject = new JSONObject();
        boolean res = commentService.updateCommentMsg(comment);
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
