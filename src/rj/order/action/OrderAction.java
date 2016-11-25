package rj.order.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import rj.cart.entity.Cart;
import rj.cart.entity.CartItem;
import rj.order.entity.Order;
import rj.order.entity.OrderItem;
import rj.order.service.OrderService;
import rj.user.entity.User;
import rj.utils.PageBean;

import java.util.Date;

/**
 * Created by 隽 on 2016/11/25.
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
    //注入service
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    private Order order=new Order();
    @Override
    public Order getModel() {
        return order;
    }

    /**
     * 生成订单的方法
     */
    public String save(){
        //1保存订单数据到数据库
        //订单数据的补全
        order.setOrdertime(new Date());
        order.setState(1);
        //添加购物车内的信息，先从session中获取
        Cart cart= (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
        //判空
        if(cart==null){
            addActionError("还没购物哦亲~~");
            return "msg";
        }
        order.setTotal(cart.getTotal());
        //设置订单中的订单项
        for(CartItem ci:cart.getCartItems()){
            OrderItem oi=new OrderItem();
            oi.setCount(ci.getCount());
            oi.setSubtotal(ci.getSubtotal());
            oi.setProduct(ci.getProduct());
            order.getOrderItems().add(oi);
        }
        //设置订单所属用户，从session中取
        User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        //判断
        if (user==null){
            addActionError("亲，你还没登陆哦~~");
            //返回登陆页面
            return "login";
        }
        order.setUser(user);
        orderService.save(order);
        //2将订单项显示到页面
        //order是实现了模型驱动的，所以直接在值栈中
        //返回订单页面
        return "saveSuccess";
    }

    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * 我的订单的查询
     */
    public String findByUid(){
        //根据session获取用户
        User user= (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
        //调用orderservice方法查询
        PageBean<Order> pageBean=orderService.findByPageUid(user.getUid(),page);

        //将分页数据显示到值栈上
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findByUidSuccess";
    }

    /**
     * 根据订单的id查询订单的方法
     */
    public String findByOid() {
        //由于oid是order实体里的属性，会自动被模型驱动封装，所以直接调用service方法即可
        order=orderService.findByOid(order.getOid());
        return "findByOidSuccess";
    }
}
