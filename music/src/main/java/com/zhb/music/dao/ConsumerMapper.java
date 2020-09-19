package com.zhb.music.dao;


import com.zhb.music.domain.Consumer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ConsumerMapper {


    @Insert("insert into consumer (id,username,password,sex,phone_num,email,birth,introduction,location,avator,create_time,update_time) " +
            "values(#{id},#{username},#{password},#{sex},#{phoneNum},#{email},#{birth},#{introduction},#{location},#{avator},#{createTime},#{updateTime})")
    int insertSelective(Consumer record);

    @Select("select count(*) from consumer where username=#{username} and password=#{password}")
    int verifyPassword(@Param("username") String username, @Param("password") String password);

    @Select("select count(*) from consumer where username=#{username}")
    int existUsername(String username);

    @Update("<script> update consumer " +
            "<set>"+
                "<if test='username!=null'> username=#{username},</if> " +
                "<if test='password!=null'> password=#{password},</if> " +
                "<if test='sex!=null'> sex=#{sex},</if> " +
                "<if test='phoneNum!=null'> phone_num=#{phoneNum},</if> " +
                "<if test='email!=null'> email=#{email},</if> " +
                "<if test='birth!=null'> birth=#{birth},</if> " +
                "<if test='introduction!=null'> introduction=#{introduction},</if>" +
                "<if test='location!=null'> location=#{location},</if> " +
                "<if test='avator!=null'> avator=#{avator},</if>" +
                "<if test='createTime!=null'> create_time=#{createTime},</if> " +
                "<if test='updateTime!=null'> update_time=#{updateTime},</if>" +
            "</set>" +
            "where id=#{id} </script>")
    int updateUserMsg(Consumer record);

    @Update("<script> update consumer set " +
            "<if test='avator!=null'> avator=#{avator}</if>" +
            "where id=#{id} </script>")
    int updateUserAvator(Consumer record);

    @Delete("delete from consumer where id=#{id}")
    int deleteUser(Integer id);

    @Select("select * from consumer")
    List<Consumer> allUser();

    @Select("select * from consumer where id=#{id}")
    List<Consumer> userOfId(Integer id);

    @Select("select * from consumer where username=#{username}")
    List<Consumer> loginStatus(String username);

}
