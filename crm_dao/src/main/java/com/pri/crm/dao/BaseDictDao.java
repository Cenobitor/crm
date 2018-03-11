package com.pri.crm.dao;

import com.pri.crm.domain.BaseDict;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 9:43 PM 08/02/2018
 * @Modified By:
 */
public interface BaseDictDao extends BaseDao<BaseDict>{
    List<BaseDict> findByType(String dict_type_code);
}
