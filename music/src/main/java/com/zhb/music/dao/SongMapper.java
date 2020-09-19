package com.zhb.music.dao;


import com.zhb.music.domain.Song;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface SongMapper {

    @Insert("insert into song (id,singer_id,name,introduction,create_time,update_time,pic,lyric,url) " +
            "values(#{id},#{singerId},#{name},#{introduction},#{createTime},#{updateTime},#{pic},#{lyric},#{url})")
    int insertSelective(Song record);

    @Select("select * from song where id=#{id}")
    Song selectByPrimaryKey(Integer id);

    @Update("<script> update song " +
            "<set>"+
                "<if test='singerId!=null'>singer_id=#{singerId},</if> " +
                "<if test='name!=null'> name=#{name},</if> " +
                "<if test='introduction!=null'> introduction=#{introduction},</if> " +
                "<if test='createTime!=null'> create_time=#{createTime},</if> " +
                "<if test='updateTime!=null'> update_time=#{updateTime},</if> " +
                "<if test='pic!=null'> pic=#{pic},</if> " +
                "<if test='lyric!=null'> lyric=#{lyric},</if>" +
            "</set> " +
            "where id=#{id} </script>")
    int updateSongMsg(Song record);

    @Update("<script> update song set " +
            "<if test='url!=null'> url=#{url}</if> " +
            "where id=#{id} </script>")
    int updateSongUrl(Song record);

    @Update("<script> update song set " +
            "<if test='pic!=null'> pic=#{pic}</if> " +
            "where id=#{id} </script>")
    int updateSongPic(Song record);

    @Delete("delete from song where id=#{id}")
    int deleteSong(Integer id);

    @Select("select * from song")
    List<Song> allSong();

    @Select("select * from song where singer_id=#{singerId}")
    List<Song> songOfSingerId(Integer singerId);

    @Select("select * from song where id=#{id}")
    List<Song> songOfId(Integer id);

    @Select("select * from song where name like#{name}")
    List<Song> songOfSingerName(String name);

    @Select("select * from name=#{name}")
    List<Song> songOfName(String name);

}
