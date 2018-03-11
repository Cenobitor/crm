package com.pri.crm.service;

import com.pri.crm.domain.PageBean;
import com.pri.crm.domain.SaleVisit;
import org.hibernate.criterion.DetachedCriteria;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 9:55 PM 25/02/2018
 * @Modified By:
 */
public interface SaleVisitService {
    void save(SaleVisit saleVisit);

    PageBean<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

}
