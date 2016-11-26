package rj.order.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import rj.order.entity.Order;
import rj.order.entity.OrderItem;
import rj.order.service.OrderService;
import rj.utils.PageBean;

import java.util.List;

/**
 * Created by 隽 on 2016/11/26.
 */
public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{
    // 模型驱动使用的类
    private Order order = new Order();

    public Order getModel() {
        return order;
    }
    // 接收page参数
    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }

    // 注入OrderService
    private OrderService orderService;

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public String findAll(){
        //通过service方法得到pagebean
        PageBean<Order> pageBean=orderService.findByPage(page);
        //设置
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        return "findAll";
    }

    // 根据订单的id查询订单项:
    public String findOrderItem(){
        // 根据订单id查询订单项:
        List<OrderItem> list = orderService.findOrderItem(order.getOid());
        // 显示到页面:
        ActionContext.getContext().getValueStack().set("list", list);
        // 页面跳转
        return "findOrderItem";
    }
    public String updateState(){

        order=orderService.findByOid(order.getOid());
        //修改订单状态
        order.setState(3);
        orderService.update(order);
        return "updateStateSuccess";
    }
}
