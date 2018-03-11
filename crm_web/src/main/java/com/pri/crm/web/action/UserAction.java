package com.pri.crm.web.action;

import cn.dsna.util.images.ValidateCode;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.pri.crm.domain.User;
import com.pri.crm.service.UserService;
import com.pri.crm.utils.Constant;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author: KONG
 * @Description:
 * @Date: Created in 5:14 PM 08/02/2018
 * @Modified By:
 */
@Controller("userAction")
@Scope("prototype")
@ParentPackage(value = "base")
@Namespace(value = "/")
public class UserAction extends ActionSupport implements ModelDriven<User>{

    private User user;

    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Resource(name = "validateCode")
    private ValidateCode validateCode;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    public User getModel() {
        if (user == null){
            user = new User();
        }
        return user;
    }

    @Action(value = "user_register",
            results = {@Result(name = Constant.REGISTER_SUCCESS ,location = "/login.jsp")})
    public String register(){

        user.setUser_state('1');
        userService.register(user);
        return Constant.REGISTER_SUCCESS;
    }
    @Action(value = "user_login",
            results = {
            @Result(name = Constant.LOGIN_SUCCESS,type="redirect",location = "/index.jsp"),
            @Result(name = Constant.LOGIN_ERROR,location = "/login.jsp")
            })
    public String login(){
        User loginUser = userService.login(user);
        if(loginUser != null) {
            ServletActionContext.getRequest().getSession().setAttribute("user",loginUser);
            return Constant.LOGIN_SUCCESS;
        }
        addFieldError("msg","用户名或密码错误!");
        return Constant.LOGIN_ERROR;
    }


    @Action(value = "user_createVerificationCode")
    public String createVerificationCode(){

        String vcode = validateCode.getCode ();

        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("vcode",vcode);

        try {
            validateCode.write (ServletActionContext.getResponse().getOutputStream ());//将服务器生成的验证码，以流的形式写给客户端（变成图片）
        } catch (IOException e) {
            e.printStackTrace();
        }
        return NONE;
    }



    @Action(value = "user_checkVerificationCode")
    public String checkVerificationCode() throws IOException {

        HttpSession session = ServletActionContext.getRequest().getSession();
        String vcode = (String) session.getAttribute("vcode");

        System.out.println("vcode="+vcode);
        System.out.println("code="+code);

        if (!code.equalsIgnoreCase(vcode)){
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("验证码输入有误!");
        }else{
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().print("");
        }
        return NONE;
    }
}
