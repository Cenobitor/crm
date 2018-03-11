package com.pri.crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pri.crm.domain.LinkMan;
import com.pri.crm.domain.PageBean;
import com.pri.crm.service.LinkManService;
import com.pri.crm.utils.Constant;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 10:47 PM 24/02/2018
 * @Modified By:
 */
@Controller("linkManAction")
@Scope("prototype")
@ParentPackage("base")
@Namespace("/")
public class LinkManAction extends ActionSupport implements ModelDriven<LinkMan>{

    private LinkMan linkMan;
    private List<LinkMan> list;
    private Long cid;
    private int currentPage = 1;
    private int pageSize = 5;
    private LinkMan editLinkMan;

    @Resource(name = "linkManService")
    private LinkManService linkManService;

    public LinkMan getEditLinkMan() {
        return editLinkMan;
    }

    @Override
    public LinkMan getModel() {
        if (linkMan == null ){
            linkMan = new LinkMan();
        }
        return linkMan;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public List<LinkMan> getList() {
        return list;
    }

    @Action(value = "linkMan_findByCid",
            results = {
                    @Result(name = Constant.JSON_SUCCESS,type = "json",params = {"root","list"})
            })
    public String findByCid(){

        DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
        criteria.add(Restrictions.eq("customer.cust_id",cid));
        list = linkManService.findByCid(criteria);
        return Constant.JSON_SUCCESS;
    }

    @Action(value = "linkMan_update",
            results = {
                    @Result(name = Constant.UPDATE_SUCCESS,type = "redirectAction",location = "linkMan_findByPage.action")
            })
    public String update(){
        linkManService.updateLinkMan(linkMan);
        return Constant.UPDATE_SUCCESS;
    }

    @Action(value = "linkMan_edit",
            results = {
                    @Result(name = Constant.EDIT_SUCCESS,location = "/jsp/linkman/edit.jsp")
            })
    public String edit(){
        editLinkMan = linkManService.findById(linkMan.getLkm_id());
        return Constant.EDIT_SUCCESS;
    }


    @Action(value = "linkMan_del",
            results = {
                    @Result(name = Constant.DELETE_SUCCESS,type="redirectAction",location = "linkMan_findByPage.action")
            })
    public String del(){
        linkManService.delete(linkMan);
        return Constant.DELETE_SUCCESS;
    }

    @Action(value = "linkMan_save",
            results = {
                    @Result(name = Constant.SAVE_SUCCESS,type="redirectAction",location = "linkMan_findByPage")
            })
    public String save(){
        linkManService.save(linkMan);
        return Constant.SAVE_SUCCESS;
    }


    @Action(value = "linkMan_findByPage",
            results = {
                    @Result(name = Constant.PAGE_SUCCESS,location = "/jsp/linkman/list.jsp")
            })
    public String findByPage(){

        DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);

        if (!StringUtils.isEmpty(linkMan.getLkm_name())){
            criteria.add(Restrictions.like("lkm_name","%"+linkMan.getLkm_name()+"%"));
        }
        if (!StringUtils.isEmpty(linkMan.getLkm_gender())){
            System.out.println(linkMan.getLkm_gender());
            criteria.add(Restrictions.like("lkm_gender",linkMan.getLkm_gender()));
        }
        if (!StringUtils.isEmpty(linkMan.getLkm_mobile())){
            criteria.add(Restrictions.like("lkm_mobile","%"+linkMan.getLkm_mobile()+"%"));
        }
        if (!StringUtils.isEmpty(linkMan.getLkm_email())){
            criteria.add(Restrictions.like("lkm_email","%"+linkMan.getLkm_email()+"%"));
        }
        if (!StringUtils.isEmpty(linkMan.getLkm_qq())){
            criteria.add(Restrictions.like("lkm_qq","%"+linkMan.getLkm_qq()+"%"));
        }

        if (linkMan.getCustomer() != null && !StringUtils.isEmpty(linkMan.getCustomer().getCust_id())){
            criteria.add(Restrictions.like("customer.cust_id",linkMan.getCustomer().getCust_id()));
        }


        PageBean<LinkMan> pageBean = linkManService.findByPage(criteria,currentPage,pageSize);

        ActionContext.getContext().getValueStack().push(pageBean);

        return Constant.PAGE_SUCCESS;
    }

}
