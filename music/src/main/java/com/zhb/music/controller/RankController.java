package com.zhb.music.controller;

import com.alibaba.fastjson.JSONObject;
import com.zhb.music.domain.Rank;
import com.zhb.music.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Controller
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

//    提交评分
    @ResponseBody
    @RequestMapping(value = "/rank/add", method = RequestMethod.POST)
    public Object addRank(Rank rank){
        JSONObject jsonObject = new JSONObject();

        boolean res = rankService.addRank(rank);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

//    获取指定歌单的评分
    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public Object rankOfSongListId(String songListId){
        return rankService.rankOfSongListId(Long.parseLong(songListId));
    }
}
