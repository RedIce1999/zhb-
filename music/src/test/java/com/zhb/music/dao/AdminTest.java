package com.zhb.music.dao;

import com.zhb.music.domain.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminTest {
    @Autowired
    private AdminMapper adminMapper;
    //测试管理员登录
    @Test
    public void testAdmin(){
        Admin admin=new Admin();
        admin.setName("admin");
        admin.setPassword("123");
        int count = adminMapper.verifyPassword(admin);
        //List<Admin> all = adminDao.all();
        //System.out.println(all);
        //System.out.println(adminDao);
    }
}
