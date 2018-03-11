package com.pri.crm.dao.impl;

import com.pri.crm.dao.BaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 3:27 PM 27/02/2018
 * @Modified By:
 */
public class BaseDaoImpl<T> extends MyHibernateDaoSupport implements BaseDao<T> {

    private Class clazz;

    public BaseDaoImpl() {
        //通过反射获取类上的注解
        ParameterizedType  pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        clazz = (Class) pt.getActualTypeArguments()[0];
    }

    @Override
    public void save(T t) {
        getHibernateTemplate().save(t);
    }

    @Override
    public void delete(T t) {
        getHibernateTemplate().delete(t);
    }

    @Override
    public void update(T t) {
        getHibernateTemplate().update(t);
    }

    @Override
    public int findCount(DetachedCriteria criteria) {

        criteria.setProjection(Projections.rowCount());
        List<Long> list = (List<Long>) getHibernateTemplate().findByCriteria(criteria);
        return list.get(0).intValue();

    }

    @Override
    public List<T> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
        criteria.setProjection(null);
        return  (List<T>)getHibernateTemplate().findByCriteria(criteria,(currentPage-1)*pageSize,pageSize);
    }

    @Override
    public T findById(Serializable id) {
        return getHibernateTemplate().get((Class<T>) clazz,id);
    }

    @Override
    public List<T> findAll() {
        return (List<T>) getHibernateTemplate().findByCriteria(DetachedCriteria.forClass(clazz));
    }
}
