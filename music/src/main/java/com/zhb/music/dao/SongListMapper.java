package com.zhb.music.dao;

import com.zhb.music.domain.SongList;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SongListMapper {

    @Insert("insert into song_list (id,title,pic,introduction) values(#{id},#{title},#{pic},#{introduction},#{style})")
    int insertSelective(SongList record);

    @Select("select * from song_list where id=#{id}")
    SongList selectByPrimaryKey(Integer id);

    @Update("<script> update song_list " +
            "<set>"+
                "<if test='title!=null'> title=#{title},</if> " +
                "<if test='pic!=null'> pic=#{pic},</if> " +
                "<if test='style!=null'> style=#{style},</if> " +
                "<if test='introduction!=null'> introduction=#{introduction},</if> " +
            "</set>" +
            "where id=#{id} </script>")
    int updateSongListMsg(SongList record);

    @Update("<script> update song_list set " +
            "<if test='pic!=null'> pic=#{pic}</if> " +
            "where id=#{id} </script>")
    int updateSongListImg(SongList record);

    @Delete("delete from song_list where id=#{id}")
    int deleteSongList(Integer id);

    @Select("select * from song_list")
    List<SongList> allSongList();

    @Select("select * from song_list where title like #{title}")
    List<SongList> likeTitle(String title);

    @Select("select * from song_list where style like #{style}")
    List<SongList> likeStyle(String style);

    @Select("select * from song_list where title=#{title}")
    List<SongList> songListOfTitle(String title);
}
