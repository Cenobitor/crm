package com.pri.crm.web.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.pri.crm.domain.User;
import org.apache.struts2.ServletActionContext;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 4:37 PM 27/02/2018
 * @Modified By:
 */

public class LoginInterceptor extends MethodFilterInterceptor{

    @Override
    protected String doIntercept(ActionInvocation actionInvocation) throws Exception {
        User user = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
        if (user == null){
            System.out.println("未登录");
            return "login";
        }
        return actionInvocation.invoke();
    }
}