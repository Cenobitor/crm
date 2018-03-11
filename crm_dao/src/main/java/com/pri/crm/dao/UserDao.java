package com.pri.crm.dao;

import com.pri.crm.domain.User;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 5:41 PM 08/02/2018
 * @Modified By:
 */
public interface UserDao extends BaseDao<User>{

    User findUser(User user);
}
