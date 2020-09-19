package com.zhb.music.dao;


import com.zhb.music.domain.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface AdminMapper {

    @Select("select count(*) from admin where name=#{name} and password=#{password}")
    int verifyPassword(Admin admin);

    @Select("select * from admin")
    List<Admin> all();
}