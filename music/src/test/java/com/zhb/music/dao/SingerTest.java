package com.zhb.music.dao;

import com.zhb.music.domain.Singer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SingerTest {
    @Autowired
    private SingerMapper singerMapper;

    @Test
    public void insertSelective() throws ParseException {
        Singer singer=new Singer();
        singer.setId(44);
        singer.setName("草东");
        singer.setSex((byte)1);
        singer.setPic("/img/singerPic/xusong.jpg");
        SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=dateTimeFormat.parse("1999-03-23");
        singer.setBirth(date);
        singer.setLocation("福建");
        singer.setIntroduction("摇滚");
        singerMapper.insertSelective(singer);
    }

    @Test
    public void deleteByPrimaryKey(){
        singerMapper.deleteSinger(44);
    }

    @Test
    public void selectByPrimaryKey(){
        Singer singer = singerMapper.selectByPrimaryKey(42);
        System.out.println(singer);
    }

    @Test
    public void allSinger(){
        List<Singer> singers = singerMapper.allSinger();
        System.out.println(singers);
    }

}
