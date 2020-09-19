package com.zhb.music.dao;

import com.zhb.music.domain.Collect;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CollectTest {
    @Autowired
    private CollectMapper collectMapper;

    //插入一条收藏
    @Test
    public void insert() throws ParseException {
        Collect collect=new Collect();
        collect.setId(53);
        collect.setUserId(53);
        byte bytes=0;
        collect.setType(bytes);
        collect.setSongId(53);
        SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=dateTimeFormat.parse("1999-03-23");
        collect.setCreateTime(date);
        collectMapper.insertSelective(collect);

    }

    //根据id查询一条
    @Test
    public void selectById() throws ParseException {
        Collect collect = collectMapper.selectByPrimaryKey(2);
        System.out.println(collect);

    }

    @Test
    public void selectByIdAndSongId() throws ParseException {
        int count = collectMapper.existSongId(1,21);
        System.out.println("记录数为"+count);

    }

    //根据userId和songId删除一条记录
    @Test
    public void deleteByIdAndSongId() throws ParseException {
        collectMapper.deleteCollect(53,53);
    }

    @Test
    public void update(){
        Collect collect=new Collect();
        collect.setUserId(100);
        collect.setId(53);
        collect.setType((byte)0);
        collectMapper.updateCollectMsg(collect);
    }
    @Test
    public void selectAll() throws ParseException {
        List<Collect> collects = collectMapper.allCollect();
        System.out.println(collects);
    }

    @Test
    public void selectByUserId() throws ParseException {
        List<Collect> collects = collectMapper.collectionOfUser(94);
        System.out.println(collects);
    }
}
