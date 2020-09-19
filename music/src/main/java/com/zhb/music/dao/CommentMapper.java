package com.zhb.music.dao;


import com.zhb.music.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommentMapper {

    @Insert("insert into comment (id,user_id,song_id,song_list_id,content,create_time,type,up)" +
            " values(#{id},#{userId},#{songId},#{songListId},#{content},#{createTime},#{type},#{up})")
    int insertSelective(Comment record);

    @Update("<script> update collect " +
            "<set>"+
                "<if test='userId!=null'> user_id=#{userId},</if> " +
                "<if test='songId!=null'> song_id=#{songId},</if> " +
                "<if test='songListId!=null'> song_list_id=#{songListId},</if> " +
                "<if test='createTime!=null'> create_time=#{createTime},</if> " +
                "<if test='type!=null'> type=#{type},</if> " +
                "<if test='up!=null'> up=#{up},</if>" +
            "</set>" +
            "where id=#{id} </script>")
    int updateCommentMsg(Comment record);

    @Delete("delete from comment where id=#{id}")
    int deleteComment(Integer id);

    @Select("select * from comment ")
    List<Comment> allComment();

    @Select("select * from comment where song_id=#{song_id}")
    List<Comment> commentOfSongId(Integer songId);

    @Select("select * from comment where song_id=#{song_list_id}")
    List<Comment> commentOfSongListId(Integer songListId);
}
