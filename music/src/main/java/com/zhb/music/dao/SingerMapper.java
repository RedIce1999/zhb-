package com.zhb.music.dao;


import com.zhb.music.domain.Singer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface SingerMapper {


    @Insert("insert into singer (id,name,sex,pic,birth,location,introduction) " +
            "values(#{id},#{name},#{sex},#{pic},#{birth},#{location},#{introduction})")
    int insertSelective(Singer record);

    @Select("select * from singer where id=#{id}")
    Singer selectByPrimaryKey(Integer id);

    @Update("<script> update singer " +
            "<set>"+
                "<if test='name!=null'> name=#{name},</if> " +
                "<if test='sex!=null'> sex=#{sex},</if> " +
                "<if test='pic!=null'> pic=#{pic},</if> " +
                "<if test='birth!=null'> birth=#{birth},</if> " +
                "<if test='location!=null'> location=#{location},</if>" +
                "<if test='introduction!=null'> introduction=#{introduction},</if>" +
            "</set> " +
            "where id=#{id} </script>")
    int updateByPrimaryKey(Singer record);

    @Update("<script> update singer " +
            "<set>"+
                "<if test='name!=null'> name=#{name},</if> " +
                "<if test='sex!=null'> sex=#{sex},</if> " +
                "<if test='pic!=null'> pic=#{pic},</if> " +
                "<if test='birth!=null'> birth=#{birth},</if> " +
                "<if test='location!=null'> location=#{location},</if>" +
                "<if test='introduction!=null'> introduction=#{introduction},</if> " +
            "</set>" +
            "where id=#{id} </script>")
    int updateSingerMsg(Singer record);

    @Update("<script> update singer set " +
            "<if test='pic!=null'> pic=#{pic}</if> " +
            "where id=#{id} </script>")
    int updateSingerPic(Singer record);

    @Delete("delete from singer where id=#{id}")
    int deleteSinger(Integer id);

    @Select("select * from singer")
    List<Singer> allSinger();

    @Select("select * from where name like #{name}")
    List<Singer> singerOfName(String name);

    @Select("select * from singer where sex=#{sex}")
    List<Singer> singerOfSex(Integer sex);
}
