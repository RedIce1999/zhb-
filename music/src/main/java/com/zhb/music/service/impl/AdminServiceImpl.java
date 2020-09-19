package com.zhb.music.service.impl;

import com.zhb.music.dao.AdminMapper;
import com.zhb.music.domain.Admin;
import com.zhb.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean veritypasswd(Admin admin) {

        return adminMapper.verifyPassword(admin)>0?true:false;
    }
}
