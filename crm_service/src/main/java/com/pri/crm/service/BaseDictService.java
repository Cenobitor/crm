package com.pri.crm.service;

import com.pri.crm.domain.BaseDict;

import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 9:40 PM 08/02/2018
 * @Modified By:
 */
public interface BaseDictService {
    List<BaseDict> findByType(String dict_type_code);
}
