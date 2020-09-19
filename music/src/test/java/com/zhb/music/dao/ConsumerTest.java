package com.zhb.music.dao;

import com.zhb.music.domain.Consumer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConsumerTest {
    @Autowired
    private ConsumerMapper consumerMapper;

    @Test
    public void insert() throws ParseException {
        SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=dateTimeFormat.parse("1999-03-23");

        Consumer consumer=new Consumer();
        consumer.setId(29);
        consumer.setUsername("藤原拓海");
        consumer.setPassword("123");
        consumer.setSex((byte)0);
        consumer.setPhoneNum("1231");
        consumer.setEmail("zhb@qq.com");

        consumer.setIntroduction("车手");
        consumer.setLocation("福建");
        consumer.setAvator("/img/user.jpg");
        consumer.setCreateTime(date);
        consumer.setUpdateTime(date);
        consumerMapper.insertSelective(consumer);
    }

    @Test
    public void updateUserMsg() throws ParseException {
        SimpleDateFormat dateTimeFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date=dateTimeFormat.parse("1999-03-23");
        Consumer consumer=new Consumer();
        consumer.setId(29);
        consumer.setUsername("藤原拓海");
        consumer.setPassword("123");
        consumer.setSex((byte)0);
        consumer.setPhoneNum("1231");
        consumer.setEmail("zhb@qq.com");
        consumer.setBirth(date);
        consumer.setIntroduction("车手");
        consumer.setLocation("福建");
        consumer.setAvator("/img/user.jpg");
        consumer.setCreateTime(date);
        consumer.setUpdateTime(date);
        consumerMapper.updateUserMsg(consumer);
    }
    //根据账号密码查询
    @Test
    public void unameAndPsw(){
        int count = consumerMapper.verifyPassword("Yin", "123");
        System.out.println(count);
    }
    //根据用户名查询记录条数
    @Test
    public void selectByUsername(){
        consumerMapper.existUsername("Yin");
    }

    //根据id删除
    @Test
    public void deleteById(){
        consumerMapper.deleteUser(29);
    }

    //查询所有
    @Test
    public void all(){
        List<Consumer> consumers = consumerMapper.allUser();
        System.out.println(consumers);
    }

    //根据id查询
    @Test
    public void selectById(){
        List<Consumer> consumers = consumerMapper.userOfId(28);
        System.out.println(consumers);
    }

    //根据用户名查询用户数据
    @Test
    public void selectDataByUsername(){
        consumerMapper.loginStatus("Yin");
    }
}
