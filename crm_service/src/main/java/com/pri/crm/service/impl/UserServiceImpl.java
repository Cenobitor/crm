package com.pri.crm.service.impl;

import com.pri.crm.dao.UserDao;
import com.pri.crm.domain.User;
import com.pri.crm.service.UserService;
import com.pri.crm.utils.Md5Util;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 5:38 PM 08/02/2018
 * @Modified By:
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource(name = "userDao")
    private UserDao userDao;

    @Override
    public void register(User user) {
        String pwd = Md5Util.encodePwd(user.getUser_password());
        user.setUser_password(pwd);
        userDao.save(user);
    }

    @Override
    public User login(User user) {

        String pwd = Md5Util.encodePwd(user.getUser_password());
        user.setUser_password(pwd);

        User loginUser = userDao.findUser(user);

        return loginUser;
    }
}
