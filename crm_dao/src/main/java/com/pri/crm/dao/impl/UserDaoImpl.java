package com.pri.crm.dao.impl;

import com.pri.crm.dao.UserDao;
import com.pri.crm.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 5:42 PM 08/02/2018
 * @Modified By:
 */
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

    @Override
    public User findUser(User user) {
        String hql = "from User where user_code = ? and user_password = ? and user_state = 1";
        List<User> list =(List<User>)getHibernateTemplate().find(hql, user.getUser_code(), user.getUser_password());
        if (list.size() != 0){
            return list.get(0);
        }
        return null;
    }
}
