package com.pri.crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pri.crm.domain.PageBean;
import com.pri.crm.domain.SaleVisit;
import com.pri.crm.domain.User;
import com.pri.crm.service.SaleVisitService;
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
import java.util.Date;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 10:47 PM 24/02/2018
 * @Modified By:
 */
@Controller("saleVisitAction")
@Scope("prototype")
@ParentPackage("base")
@Namespace("/")
public class SaleVisitAction extends ActionSupport implements ModelDriven<SaleVisit> {

    @Resource(name = "saleVisitService")
    private SaleVisitService saleVisitService;

    private SaleVisit saleVisit;
    private int currentPage = 1;
    private int pageSize = 5;
    private Date start_visit_time;
    private Date end_visit_time;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setStart_visit_time(Date start_visit_time) {
        this.start_visit_time = start_visit_time;
    }

    public void setEnd_visit_time(Date end_visit_time) {
        this.end_visit_time = end_visit_time;
    }

    @Override
    public SaleVisit getModel() {
        if (saleVisit == null){
            saleVisit = new SaleVisit();
        }
        return saleVisit;
    }


    @Action(value = "saleVisit_save",
            results = {
            @Result(name = "Constant.SAVE_SUCCESS",type="redirectAction",location = "saleVisit_findByPage")
            } )
    public String save(){

        User user = (User) ActionContext.getContext().getSession().get("user");
        saleVisit.setVisit_user(user);
        saleVisitService.save(saleVisit);
        return Constant.SAVE_SUCCESS;
    }

    @Action(value = "saleVisit_findByPage",results = {
            @Result(name = Constant.INPUT_ERROR,location = "/jsp/visit/list.jsp"),
            @Result(name = Constant.PAGE_SUCCESS,location = "/jsp/visit/list.jsp")
    })
    public String findByPage(){

        DetachedCriteria criteria = DetachedCriteria.forClass(SaleVisit.class);

        if (start_visit_time != null && end_visit_time != null && start_visit_time.after(end_visit_time)){
            addActionError("开始时间不能晚于结束时间");
            return Constant.INPUT_ERROR;
        }
        if (start_visit_time != null && end_visit_time != null){
            criteria.add(Restrictions.between("visit_time",start_visit_time,end_visit_time));
        }else {
            if (start_visit_time != null ){
                criteria.add(Restrictions.ge("visit_time",start_visit_time));
            }
            if (end_visit_time != null){
                criteria.add(Restrictions.le("visit_time",end_visit_time));
            }
        }
        if (saleVisit.getVisit_cust() != null && !StringUtils.isEmpty(saleVisit.getVisit_cust().getCust_id())){
            System.out.println(saleVisit.getVisit_cust().getCust_id());
            criteria.add(Restrictions.eq("visit_cust.cust_id",saleVisit.getVisit_cust().getCust_id()));
        }

        PageBean<SaleVisit> pageBean = saleVisitService.findByPage(criteria,currentPage,pageSize);


        ActionContext.getContext().getValueStack().push(pageBean);

        return Constant.PAGE_SUCCESS;
    }
}
