package com.pri.crm.service;

import com.pri.crm.domain.LinkMan;
import com.pri.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 10:51 PM 24/02/2018
 * @Modified By:
 */
public interface LinkManService {
    void save(LinkMan linkMan);

    PageBean<LinkMan> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

    void delete(LinkMan linkMan);

    LinkMan findById(Long lkm_id);

    void updateLinkMan(LinkMan linkMan);

    List<LinkMan> findByCid(DetachedCriteria criteria);
}
