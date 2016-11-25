package rj.order.entity;

import rj.user.entity.User;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 订单实体
 * Created by 隽 on 2016/11/25.
 */
public class Order {
    private Integer oid;
    private Double total;//总共费用
    private Date ordertime;//订单时间
    private Integer state;// 1:未付款   2:订单已经付款   3:已经发货   4:订单结束
    private String name;//真实姓名
    private String phone;//电话
    private String addr;//送货地址
    // 用户的外键:对象
    private User user;//指向用户，多对一
    // 配置订单项的集合
    private Set<OrderItem> orderItems = new HashSet<OrderItem>();

    public Integer getOid() {
        return oid;
    }
    public void setOid(Integer oid) {
        this.oid = oid;
    }
    public Double getTotal() {
        return total;
    }
    public void setTotal(Double total) {
        this.total = total;
    }
    public Date getOrdertime() {
        return ordertime;
    }
    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddr() {
        return addr;
    }
    public void setAddr(String addr) {
        this.addr = addr;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Set<OrderItem> getOrderItems() {
        return orderItems;
    }
    public void setOrderItems(Set<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
