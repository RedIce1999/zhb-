package com.zhb.music.dao;

import com.zhb.music.domain.Song;
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
public class SongTest {
    @Autowired
    private SongMapper songMapper;

    @Test
    public void insertSelective() throws ParseException {
        Song song=new Song();
        song.setId(114);
        song.setSingerId(1);
        song.setName("张杰");
        song.setIntroduction("很好听");
        SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=dateTimeFormat.parse("1999-03-23");
        song.setCreateTime(date);
        song.setUpdateTime(date);
        song.setPic("zaizheli");
        song.setLyric("llalala");
        song.setUrl("zaizheli");
        songMapper.insertSelective(song);
    }

    @Test
    public void updateSongMsg() throws ParseException {
        Song song=new Song();
        song.setId(114);
        song.setIntroduction("真难听");
        song.setName("薛之谦");
        SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=dateTimeFormat.parse("1999-03-23");
        song.setCreateTime(date);
        song.setUpdateTime(date);
        song.setPic("zaizheli");
        song.setLyric("llalala");
        song.setUrl("zaizheli");
        songMapper.updateSongMsg(song);
    }
    @Test
    public void selectByPrimaryKey(){
        Song song = songMapper.selectByPrimaryKey(85);
        System.out.println(song);
    }

    @Test
    public void songOfSingerName(){
        List<Song> songs = songMapper.songOfSingerName("张杰%");
        System.out.println(songs);
    }

}
