package com.pri.crm.web.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pri.crm.domain.Customer;
import com.pri.crm.domain.PageBean;
import com.pri.crm.domain.User;
import com.pri.crm.service.CustomerService;
import com.pri.crm.utils.Constant;
import com.pri.crm.utils.MyFileUtil;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
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
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 8:40 PM 08/02/2018
 * @Modified By:
 */
@Controller("customerAction")
@Scope("prototype")
@ParentPackage(value = "base")
@Namespace("/")
public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

    private Customer customer;

    @Resource(name = "customerService")
    private CustomerService customerService;

    @Override
    public Customer getModel() {
        if (customer == null) {
            customer = new Customer();
        }
        return customer;
    }

    //获取上传文件
    private File upload;
    private String uploadContentType;
    private String uploadFileName;

    //分页
    private int currentPage = 1;
    private int pageSize = 5;

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }

    private Customer editCustomer;

    public Customer getEditCustomer() {
        return editCustomer;
    }

    private List<Customer> list;

    public List<Customer> getList() {
        return list;
    }

    @Action(value = "customer_findAll",
            results = {
            @Result(name = Constant.JSON_SUCCESS,type = "json",params={"root","list"})
            })
    public String findAll() {
        list = customerService.findAll();
        return Constant.JSON_SUCCESS;
    }

    @Action(value = "customer_edit",
            results = {
            @Result(name = Constant.EDIT_SUCCESS,location = "/jsp/customer/edit.jsp")
            })
    public String edit() {
        editCustomer = customerService.findById(customer.getCust_id());
        return Constant.EDIT_SUCCESS;
    }

    @Action(value = "customer_updateCustomer",
            results = {
                    @Result(name = Constant.UPDATE_SUCCESS,type="redirectAction",location = "customer_findByPage.action")
            })
    public String updateCustomer(){

        customerService.updateCustomer(customer);
        return Constant.UPDATE_SUCCESS;
    }

    @Action(value = "customer_delete",
            results = {
                    @Result(name = Constant.DELETE_SUCCESS,type="redirectAction",location = "customer_findByPage.action")
            })
    public String delete(){
        customerService.delete(customer);
        return Constant.DELETE_SUCCESS;
    }

    @Action(value = "customer_findByPage",
            results = {
                    @Result(name = Constant.PAGE_SUCCESS,location = "/jsp/customer/list.jsp")
            })
    public String findByPage(){

        DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);

        if(customer.getCust_name() != null && !StringUtils.isEmpty(customer.getCust_name().trim())){
            criteria.add(Restrictions.like("cust_name","%"+customer.getCust_name()+"%"));
        }

        if(customer.getCust_phone() != null && !StringUtils.isEmpty(customer.getCust_phone().trim())){
            criteria.add(Restrictions.like("cust_phone","%"+customer.getCust_phone()+"%"));
        }

        if(customer.getCust_source() != null && !StringUtils.isEmpty(customer.getCust_source().getDict_id())){
            criteria.add(Restrictions.eq("cust_source.dict_id",customer.getCust_source().getDict_id()));
        }

        if(customer.getCust_level() != null && !StringUtils.isEmpty(customer.getCust_level().getDict_id())){
            criteria.add(Restrictions.eq("cust_level.dict_id",customer.getCust_level().getDict_id()));
        }
        if(customer.getCust_industry() != null && !StringUtils.isEmpty(customer.getCust_industry().getDict_id())){
            criteria.add(Restrictions.eq("cust_industry.dict_id",customer.getCust_industry().getDict_id()));
        }


        PageBean<Customer> pageBean = customerService.findByPage(criteria,currentPage,pageSize);
        ActionContext.getContext().getValueStack().push(pageBean);
        return Constant.PAGE_SUCCESS;
    }

    @Action(value = "customer_save",
            results = {
                    @Result(name = Constant.SAVE_SUCCESS,type="redirectAction",location = "customer_findByPage"),
                    @Result(name = Constant.INPUT_ERROR,location = "/jsp/customer/add.jsp")
            })
    public String save() throws IOException {

        if(upload != null){
            //存储文件 tmp---jpg | png
            String fileName  = MyFileUtil.getFileName(uploadFileName);
            File file  = new File("/Users/Shared/jpg" , fileName);
            FileUtils.copyFile(upload, file);
            customer.setCust_image("jpg/"+fileName); //jpg/3bbbfca5c08d401f8cb982ad80527e97.jpg
        }

        if (verification()) {return Constant.INPUT_ERROR;}
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");

        customer.setCust_user_id(user);
        customer.setCust_create_id(user);

        customerService.save(customer);
        return Constant.SAVE_SUCCESS;
    }

    private boolean verification() {
        //校验客户名字
        if(StringUtils.isEmpty(customer.getCust_name())){
            addActionError("客户名不能为空");
            return true;
        }
        //校验客户电话
        if(StringUtils.isEmpty(customer.getCust_phone())){
            addActionError("客户电话不能为空");
            return true;
        }
        //校验客户地址
        if(StringUtils.isEmpty(customer.getCust_address())){
            addActionError("客户地址不能为空");
            return true;
        }

        //校验客户行业
        if(StringUtils.isEmpty(customer.getCust_industry().getDict_id())){
            addActionError("客户行业不能为空");
            return true;
        }
        //校验客户级别
        if(StringUtils.isEmpty(customer.getCust_level().getDict_id())){
            addActionError("客户级别不能为空");
            return true;
        }

        //校验客户来源
        if(StringUtils.isEmpty(customer.getCust_level().getDict_id())){
            addActionError("客户来源不能为空");
            return true;
        }
        return false;
    }
}
