package com.pri.crm.dao.impl;

import com.pri.crm.dao.CustomerDao;
import com.pri.crm.domain.Customer;
import org.springframework.stereotype.Repository;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 8:47 PM 08/02/2018
 * @Modified By:
 */
@Repository("customerDao")
public class CustomerDaoImpl  extends BaseDaoImpl<Customer> implements CustomerDao {

}
