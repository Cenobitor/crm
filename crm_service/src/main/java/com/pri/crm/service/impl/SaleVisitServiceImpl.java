package com.pri.crm.service.impl;

import com.pri.crm.dao.SaleVisitDao;
import com.pri.crm.domain.PageBean;
import com.pri.crm.domain.SaleVisit;
import com.pri.crm.service.SaleVisitService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 9:56 PM 25/02/2018
 * @Modified By:
 */
@Service("saleVisitService")
@Transactional
public class SaleVisitServiceImpl implements SaleVisitService {

    @Resource(name = "saleVisitDao")
    private SaleVisitDao saleVisitDao;

    @Override
    public void save(SaleVisit saleVisit) {
        saleVisitDao.save(saleVisit);
    }

    @Override
    public PageBean<SaleVisit> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
        PageBean<SaleVisit> pageBean = new PageBean<>();

        pageBean.setPageSize(pageSize);
        int count = saleVisitDao.findCount(criteria);
        pageBean.setTotalSize(count);

        pageBean.setTotalPage((int) Math.ceil(count*1.0/pageSize));
        pageBean.setCurrentPage(currentPage);

        List<SaleVisit> list = saleVisitDao.findByPage(criteria,currentPage,pageSize);
        pageBean.setList(list);




        return pageBean;
    }
}
