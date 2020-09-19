package com.zhb.music.dao;


import com.zhb.music.domain.Collect;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CollectMapper {

    @Insert("insert into collect (id, user_id, type," +
            " song_id, song_list_id, create_time) " +
            " values(#{id},#{userId},#{type},#{songId},#{songListId},#{createTime})")
    int insertSelective(Collect record);

    @Select("select * from collect where id=#{id}")
    Collect selectByPrimaryKey(Integer id);

    @Select("select count(*) from collect where user_id=#{userId} and song_id=#{songId}")
    int existSongId(@Param("userId") Integer userId, @Param("songId") Integer songId);

    @Update("<script> update collect " +
            "<set>"+
                "<if test='userId!=null'>user_id=#{userId},</if> " +
                "<if test='type!=null'> type=#{type},</if> " +
                "<if test='songId!=null'>song_id=#{songId},</if> " +
                "<if test='songListId!=null'> song_list_id=#{songListId},</if> " +
                "<if test='createTime!=null'> create_time=#{createTime},</if> " +
            "</set>"+
            "where id=#{id} </script>")
    int updateCollectMsg(Collect collect);

    @Delete("delete from collect where user_id=#{userId} and song_id=#{songId}")
    int deleteCollect(@Param("userId") Integer userId, @Param("songId") Integer songId);

    @Select("select * from collect")
    List<Collect> allCollect();

    @Select("select * from collect where user_id=#{userId}")
    List<Collect> collectionOfUser(Integer userId);
}
