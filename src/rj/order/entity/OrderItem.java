package rj.order.entity;

import rj.product.entity.Product;

/**
 * 订单项实体
 * 可以参与业务逻辑
 * Created by 隽 on 2016/11/25.
 */
public class OrderItem {
    private Integer itemid;
    private Integer count;//总计几个相同的商品
    private Double subtotal;//一个订单中的总费用
    // 商品外键:对象
    private Product product;
    // 订单外键:对象
    private Order order;
    public Integer getItemid() {
        return itemid;
    }
    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }
    public Integer getCount() {
        return count;
    }
    public void setCount(Integer count) {
        this.count = count;
    }
    public Double getSubtotal() {
        return subtotal;
    }
    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product product) {
        this.product = product;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
}
