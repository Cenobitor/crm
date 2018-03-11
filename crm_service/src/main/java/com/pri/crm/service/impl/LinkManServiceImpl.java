package com.pri.crm.service.impl;

import com.pri.crm.dao.LinkManDao;
import com.pri.crm.domain.LinkMan;
import com.pri.crm.domain.PageBean;
import com.pri.crm.service.LinkManService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 10:48 PM 24/02/2018
 * @Modified By:
 */
@Service("linkManService")
@Transactional
public class LinkManServiceImpl implements LinkManService {

    @Resource(name = "linkManDao")
    private LinkManDao linkManDao;

    @Override
    public void save(LinkMan linkMan) {
        linkManDao.save(linkMan);
    }

    @Override
    public PageBean<LinkMan> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {

        PageBean<LinkMan> pageBean = new PageBean<>();
        pageBean.setPageSize(pageSize);
        pageBean.setCurrentPage(currentPage);

        int count = linkManDao.findCount(criteria);
        pageBean.setTotalSize(count);

        pageBean.setTotalPage((int) Math.ceil(count*1.0/pageSize));

        List<LinkMan> linkMans = linkManDao.findByPage(criteria,currentPage,pageSize);
        pageBean.setList(linkMans);

        return pageBean;
    }

    @Override
    public void delete(LinkMan linkMan) {
        linkManDao.delete(linkMan);
    }

    @Override
    public LinkMan findById(Long lkm_id) {
        return linkManDao.findById(lkm_id);
    }

    @Override
    public void updateLinkMan(LinkMan linkMan) {
        linkManDao.update(linkMan);
    }

    @Override
    public List<LinkMan> findByCid(DetachedCriteria criteria) {
        return linkManDao.findByCid(criteria);
    }
}
