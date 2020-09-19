package com.zhb.music.dao;

import com.zhb.music.domain.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentTest {
    @Autowired
    private CommentMapper commentMapper;

    //插入一条数据
    @Test
    public void insert(){
        Comment comment=new Comment();
        comment.setUserId(1);
        comment.setSongId(1);
        comment.setSongListId(80);
        comment.setContent("好");
        comment.setType((byte)1);
        comment.setUp(1);
        commentMapper.insertSelective(comment);
    }

    //根据id删除
    @Test
    public void deleteById(){
        commentMapper.deleteComment(59);
    }

    @Test
    public void allComment(){
        List<Comment> comments = commentMapper.allComment();
        System.out.println(comments);
    }
}
