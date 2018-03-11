package com.pri.crm.dao;

import org.hibernate.criterion.DetachedCriteria;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 3:22 PM 27/02/2018
 * @Modified By:
 */
public interface BaseDao<T> {

    void save(T t);

    void delete(T t);

    void update(T t);

    int findCount(DetachedCriteria criteria);

    List<T> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

    T findById(Serializable id);

    List<T> findAll();
}
