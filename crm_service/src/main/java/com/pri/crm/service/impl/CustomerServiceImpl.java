package com.pri.crm.service.impl;

import com.pri.crm.dao.CustomerDao;
import com.pri.crm.domain.Customer;
import com.pri.crm.domain.PageBean;
import com.pri.crm.service.CustomerService;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 8:43 PM 08/02/2018
 * @Modified By:
 */
@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Resource(name = "customerDao")
    private CustomerDao customerDao;

    @Override
    public void save(Customer customer) {
        customerDao.save(customer);
    }

    @Override
    public PageBean<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize) {
        PageBean<Customer> pageBean = new PageBean<Customer>();
        int count = customerDao.findCount(criteria);
        pageBean.setTotalSize(count);

        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setTotalPage((int) Math.ceil(count*1.0/pageSize));

        List<Customer> list = customerDao.findByPage(criteria,currentPage,pageSize);
        pageBean.setList(list);

        return pageBean;
    }

    @Override
    public void delete(Customer customer) {
        customerDao.delete(customer);
    }

    @Override
    public Customer findById(Long cust_id) {

        return customerDao.findById(cust_id);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDao.update(customer);
    }

    @Override
    public List<Customer> findAll() {

        return customerDao.findAll();
    }

}
