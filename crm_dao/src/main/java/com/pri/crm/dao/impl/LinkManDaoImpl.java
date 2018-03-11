package com.pri.crm.dao.impl;

import com.pri.crm.dao.LinkManDao;
import com.pri.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 10:48 PM 24/02/2018
 * @Modified By:
 */
@Repository("linkManDao")
public class LinkManDaoImpl extends BaseDaoImpl<LinkMan> implements LinkManDao {


    @Override
    public List<LinkMan> findByCid(DetachedCriteria criteria) {
        return (List<LinkMan>) getHibernateTemplate().findByCriteria(criteria);
    }

}
