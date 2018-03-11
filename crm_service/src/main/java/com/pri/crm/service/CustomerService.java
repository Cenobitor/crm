package com.pri.crm.service;

import com.pri.crm.domain.Customer;
import com.pri.crm.domain.PageBean;
import org.hibernate.criterion.DetachedCriteria;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 8:43 PM 08/02/2018
 * @Modified By:
 */
public interface CustomerService {
    void save(Customer customer);

    PageBean<Customer> findByPage(DetachedCriteria criteria, int currentPage, int pageSize);

    void delete(Customer customer);

    Customer findById(Long cust_id);

    void updateCustomer(Customer customer);

    List<Customer> findAll();
}
