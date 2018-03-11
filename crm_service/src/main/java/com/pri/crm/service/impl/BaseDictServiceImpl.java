package com.pri.crm.service.impl;

import com.pri.crm.dao.BaseDictDao;
import com.pri.crm.domain.BaseDict;
import com.pri.crm.service.BaseDictService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 9:40 PM 08/02/2018
 * @Modified By:
 */
@Service("baseDictService")
public class BaseDictServiceImpl implements BaseDictService {
    @Resource(name = "baseDictDao")
    private BaseDictDao baseDictDao;

    @Override
    public List<BaseDict> findByType(String dict_type_code) {
        return baseDictDao.findByType(dict_type_code);
    }
}
