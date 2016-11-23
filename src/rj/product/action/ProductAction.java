package rj.product.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import rj.category.service.CategoryService;
import rj.product.entity.Product;
import rj.product.service.ProductService;
import rj.utils.PageBean;

/**
 * Created by 隽 on 2016/11/22.
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }


    //由于一级分类的查询已经做过，所以直接调用一级分类的方法就是，但是需要注入
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    private Product product=new Product();
    @Override
    public Product getModel() {
        return product;
    }
    /**
     * 根据pid查询的方法
     */
    public String findByPid(){
        product=productService.findByPid(product.getPid());
        return "findByPid";
    }

    //接收cid
    private Integer cid;

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    //需要得到当前的页数传给pagebean
    private int page;

    public void setPage(int page) {
        this.page = page;
    }



    /**
     * 根据cid查询的方法
     * 由于商品本身有的id只是二级id，所以要属性驱动得到一级分类的id
     * @return
     */
    public String findByCid(){

        //先查询所有的一级分类，在查询所有的二级分类
        //List<Category> cList = categoryService.findAll();
        //由于先前在indexaction中已经查询的所有的一级分类并放在了session中，直接可以获取session中的数据
        PageBean<Product> pageBean=productService.findByCid(cid,page);
        //往值栈里面放入数据
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        setCsid(null);
        return "findByCid";
    }

    //！！！！！！！！！！！！！！！！！！！！！！！
    //需要得到二级分类的id,自己属性驱动封装,并且必须为对象，不然后面不能作为判断null的依据
    //导致无法分别显示分页
    private Integer csid;

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public Integer getCsid() {
        return csid;
    }

    /**
     * 通过二级分类查询所有的商品
     * 带有分页的功能
     * 根据csid调用product的service方法直接得到pagebean（已经有了数据的）
     * @return
     */
    public String findByCsid(){
        PageBean<Product> pageBean=productService.findByCsid(csid,page);
        //往值栈里面放入数据
       //名字相同都为pagebean，目的：盖掉cid
        ActionContext.getContext().getValueStack().set("pageBean",pageBean);
        //清除cid,防止分页时候出现问题
        setCid(null);
        return "findByCsid";
    }

}
