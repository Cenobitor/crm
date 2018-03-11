package com.pri.crm.dao.impl;

import com.pri.crm.dao.BaseDictDao;
import com.pri.crm.domain.BaseDict;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 9:44 PM 08/02/2018
 * @Modified By:
 */
@Repository("baseDictDao")
public class BaseDictDaoImpl extends BaseDaoImpl<BaseDict> implements BaseDictDao {

    @Override
    public List<BaseDict> findByType(String dict_type_code) {
        String hql = "from BaseDict where dict_type_code = ?";
        return (List<BaseDict>) getHibernateTemplate().find(hql, dict_type_code);

    }
}
