package com.zhb.music.dao;

import com.zhb.music.domain.Rank;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RankTest {
    @Autowired
    private RankMapper rankMapper;

    @Test
    public void insertSelective(){
        Rank rank=new Rank();
        rank.setId((long) 34);
        rank.setConsumerId((long) 13);
        rank.setSongListId((long) 34);
        rank.setScore(10);
        rankMapper.insertSelective(rank);
    }

    @Test
    public void selectScoreSum(){
        int sum = rankMapper.selectScoreSum((long) 2);
        System.out.println(sum);

    }

    @Test
    public void selectRankNum(){
        int num = rankMapper.selectRankNum((long) 2);
        System.out.println(num);

    }
}
