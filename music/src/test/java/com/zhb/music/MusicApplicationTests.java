package com.zhb.music;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.transform.Source;
import java.io.File;

@SpringBootTest
@RunWith(SpringRunner.class)
class MusicApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("这里修改了");
    }
    @Test
    public void test(){
        File file=new File("zhb.txt");
        System.out.println(file);
    }



}
