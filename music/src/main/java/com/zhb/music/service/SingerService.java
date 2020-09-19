package com.zhb.music.service;



import com.zhb.music.domain.Singer;
import java.util.List;

public interface SingerService {

    boolean addSinger(Singer singer);

    boolean updateSingerMsg(Singer singer);

    boolean updateSingerPic(Singer singer);

    boolean deleteSinger(Integer id);

    List<Singer> allSinger();

    List<Singer> singerOfName(String name);

    List<Singer> singerOfSex(Integer sex);
}
