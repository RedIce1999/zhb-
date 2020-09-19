package com.zhb.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/avatorImages/**").addResourceLocations("file:D:\\download\\music-website-master\\music-server\\data\\avatorImages\\");
        registry.addResourceHandler("/img/singerPic/**").addResourceLocations("file:D:\\download\\music-website-master\\music-server\\data\\img\\singerPic\\");
        registry.addResourceHandler("/img/songPic/**").addResourceLocations("file:D:\\download\\music-website-master\\music-server\\data\\img\\songPic\\");
        registry.addResourceHandler("/song/**").addResourceLocations("file:D:\\download\\music-website-master\\music-server\\data\\song\\");
        registry.addResourceHandler("/img/songListPic/**").addResourceLocations("file:D:\\download\\music-website-master\\music-server\\data\\img\\songListPic\\");

    }
}
