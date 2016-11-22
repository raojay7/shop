package rj.user.action;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import rj.user.entity.User;
import rj.user.service.IUserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户模块的action
 * 1.0转到注册页面
 * 2.0注册
 * 3.0用户激活
 * 4.0登陆
 * 5.0退出
 * @author 隽
 *
 */
public class UserAction extends ActionSupport implements ModelDriven<User>{
    /**
     * 1使用模型驱动
     */
    private User user=new User();
    public User getModel(){
        return user;
    }

    /**
     * 2注入数据，service
     */
    private IUserService userService;
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    public IUserService getUserService() {
        return userService;
    }
    // 接收验证码:
    private String checkcode;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    /**
     * 1.0转到注册页面
     * @return
     */
    public String registPage(){
        return "registPage";
    }
    /**
     * ajax进行异步校验用户名的执行方法
     * @return
     * @throws IOException
     */
    public String findByName() throws IOException{
        //调用service进行查询是否存在user
        User existUser = userService.findByUsername(user.getName());
        //获得response对象
        HttpServletResponse response = ServletActionContext.getResponse();
        //设置解码
        response.setContentType("text/html;charset=utf-8");
        //判断:如果存在返回注册失败，不存在注册成功
        if(existUser!=null){
            //查询到了
            response.getWriter().write("<font color='red'>用户名已经存在</font>");
        }
        else {
            response.getWriter().write("<font color='green'>用户名可以使用</font>");
        }
        return NONE;

    }

    //2.0用户注册方法
    public String regist(){
        // 判断验证码程序:
        // 从session中获得验证码的随机值:
        String checkcode1 = (String) ServletActionContext.getRequest()
                .getSession().getAttribute("checkcode");
        if(!checkcode.equalsIgnoreCase(checkcode1)){
            this.addActionError("验证码输入错误!");
            return "checkcodeFail";
        }
        userService.save(user);
        this.addActionMessage("注册成功!请去邮箱激活!");
        return "msg";
    }
    //3.0用户激活操作
    public String active(){
        //根据激活码查找用户，找到了修改状态、清空code并给出提示信息；没有直接提示：激活失败
        User existu=userService.findByCode(user.getCode());
        if(existu!=null){
            //找到了
            existu.setCode(null);
            existu.setState(1);
            //执行更新操作
            userService.update(existu);
            //显示成功
            addActionMessage("激活成功！");
        }else {
            //显示失败
            addActionMessage("激活失败！");
        }
        return "msg";
    }
    /**
     * 跳转到登录页面
     */
    public String loginPage() {
        return "loginPage";
    }
    /**
     * 4.0登陆的方法
     */
    public String login(){
        //检查账号密码是否匹配且存在
        User existUser=userService.login(user);
        if(existUser==null){
            //登陆失败，提示信息
            addActionError("登陆失败：请重试账号密码或者激活邮箱");
            //跳转到登陆页面
            return "login";
        }
        else {
            //登陆成功,将用户的信息保存在session中
            ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
            //页面跳转
            return "loginSuccess";

        }
    }



    /**
     * 5.0用户退出的方法
     */
    public String quit(){
        // 销毁session
        ServletActionContext.getRequest().getSession().invalidate();
        return "quit";
    }

}