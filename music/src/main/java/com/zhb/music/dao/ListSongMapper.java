package com.zhb.music.dao;


import com.zhb.music.domain.ListSong;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ListSongMapper {

    @Insert("insert into list_song (id,song_id,song_list_id) values(#{id},#{songId},#{songListId})")
    int insertSelective(ListSong record);

    @Update("<script> update list_song " +
            "<set>"+
                "<if test='songId!=null'> song_id=#{songId},</if> " +
                "<if test='songListId!=null'> song_list_id=#{songListId},</if>" +
            "</set> " +
            "where id=#{id} </script>")
    int updateListSongMsg(ListSong record);

    @Delete("delete from list_song where song_id=#{songId}")
    int deleteListSong(Integer songId);

    @Select("select * from list_song")
    List<ListSong> allListSong();

    @Select("select * from list_song where song_list_id=#{songListId}")
    List<ListSong> listSongOfSongId(Integer songListId);
}
