package com.pri.crm.service;


import com.pri.crm.domain.User;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 5:37 PM 08/02/2018
 * @Modified By:
 */
public interface UserService {
    void register(User user);

    User login(User user);
}
