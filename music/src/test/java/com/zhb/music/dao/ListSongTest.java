package com.zhb.music.dao;

import com.zhb.music.domain.ListSong;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListSongTest {
    @Autowired
    private ListSongMapper listSongMapper;

    //插入一条记录
    @Test
    public void insertSelective(){
        ListSong listSong=new ListSong();
        listSong.setId(210);
        listSong.setSongId(2000);
        listSong.setSongListId(20);
        listSongMapper.insertSelective(listSong);
    }
    //根据id删除
    @Test
    public void deleteByPrimaryKey(){
        listSongMapper.deleteListSong(210);
    }

    @Test
    public void selectByPrimaryKey(){
//        ListSong listSong = listSongMapper.selectByPrimaryKey(209);
//        System.out.println(listSong);
    }

    @Test
    public void deleteListSong(){
        listSongMapper.deleteListSong(2000);
    }

    @Test
    public void allListSong(){
        List<ListSong> listSongs = listSongMapper.allListSong();
        System.out.println(listSongs);
    }

    @Test
    public void listSongOfSongId(){
        List<ListSong> listSongs = listSongMapper.listSongOfSongId(80);
        System.out.println(listSongs);
    }
}
