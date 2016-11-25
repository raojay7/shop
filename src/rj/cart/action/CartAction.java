package rj.cart.action;

import org.apache.struts2.ServletActionContext;
import rj.cart.entity.Cart;
import rj.cart.entity.CartItem;
import rj.product.entity.Product;
import rj.product.service.ProductService;

/**
 * Created by 隽 on 2016/11/25.
 */
public class CartAction {
    //接收pid
    private Integer pid;

    public void setPid(Integer pid) {
        this.pid = pid;
    }
    //接收数量
   private Integer count;

    public void setCount(Integer count) {
        this.count = count;
    }

    //拿到service对象
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    //将购物项添加到购物车的执行方法
    public String addCart(){
        //自己封装cartItem对象
        CartItem cartItem=new CartItem();
        //设置商品数量
        cartItem.setCount(count);
        //设置商品
        //先用service查询到在设置
        Product product = productService.findByPid(pid);
        cartItem.setProduct(product);
        //将购物项添加到购物车，但是购物车要是同一个，所以在session中获取
        Cart cart =getCart();
        cart.addCart(cartItem);
        return "addCart";
    }

    private Cart getCart() {
        //从session中获得购物车
        Cart cart= (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
        if (cart==null){
            cart=new Cart();
            ServletActionContext.getRequest().getSession().setAttribute("cart",cart);
        }
        return cart;
    }
    //清空购物车执行方法
    public String  clearCart(){
        //得到购物车
        Cart cart = getCart();
        //调用map的清空方法
        cart.clearCart();
        return "clearCart";
    }
    //移除商品购物的方法
    public String removeCart(){
        //得到购物车
        Cart cart = getCart();
        cart.removeCart(pid);
        return "removeCart";
    }
    public String myCart(){
        return "myCart";
    }
}
