package rj.adminuser.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import rj.adminuser.entity.AdminUser;
import rj.adminuser.service.AdminUserService;

/**
 * Created by 隽 on 2016/11/26.
 */
public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

    private AdminUserService adminUserService;

    public void setAdminUserService(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    private AdminUser adminUser=new AdminUser();
    @Override
    public AdminUser getModel() {
        return adminUser;
    }


    //管理员的登陆方法
    public String login(){
        AdminUser existAdminUser=adminUserService.login(adminUser);
        if (existAdminUser==null){
            addActionError("用户名或者密码错误~~~");
            return "loginFail";
        }
        else {
            //保存信息到action
            ServletActionContext.getRequest().getSession().setAttribute("existAdminUser",existAdminUser);
            return "loginSuccess";
        }
    }


}
