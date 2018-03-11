package com.pri.crm.web.action;

import com.opensymphony.xwork2.ActionSupport;
import com.pri.crm.domain.BaseDict;
import com.pri.crm.service.BaseDictService;
import com.pri.crm.utils.Constant;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 8:38 PM 08/02/2018
 * @Modified By:
 */
@Controller("baseDictAction")
@Scope("prototype")
@ParentPackage("base")
@Namespace("/")
public class BaseDictAction extends ActionSupport{

    private String dict_type_code;
    @Resource(name = "baseDictService")
    private BaseDictService baseDictService;

    public void setDict_type_code(String dict_type_code) {
        this.dict_type_code = dict_type_code;
    }

    private List<BaseDict> list;

    public List<BaseDict> getList() {
        return list;
    }


    @Action(value = "baseDict_findByType",
            results = {
                    @Result(name = Constant.JSON_SUCCESS,type = "json",params={"root","list"})
            })
    public String findByType(){

        list = baseDictService.findByType(dict_type_code);

        return Constant.JSON_SUCCESS;

    }
}
