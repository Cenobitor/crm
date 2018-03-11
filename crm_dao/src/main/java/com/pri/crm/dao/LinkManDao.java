package com.pri.crm.dao;

import com.pri.crm.domain.LinkMan;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 10:51 PM 24/02/2018
 * @Modified By:
 */
public interface LinkManDao extends BaseDao<LinkMan> {
    List<LinkMan> findByCid(DetachedCriteria criteria);
}
