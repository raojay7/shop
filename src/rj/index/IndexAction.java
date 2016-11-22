package rj.index;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import rj.category.entity.Category;
import rj.category.service.CategoryService;
import rj.product.entity.Product;
import rj.product.service.ProductService;

import java.util.List;

/**
 * Created by 隽 on 2016/11/20.
 */
public class IndexAction extends ActionSupport {

    //得到一级分类的数据
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    //得到商品数据
    private ProductService productService;

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    //跳转到首页


    /**
     * 需要在一级分类中注入一级分类的service
     * 由于还需要显示商品，所以要注入商品的数据
     * @return  String
     * @throws  Exception
     */
    @Override
    public String execute() throws Exception {
        //调用service的查询所有方法
        List<Category> clist=categoryService.findAll();
        //由于一级分类要一直有，所有把他注入到session中
        ActionContext.getContext().getSession().put("clist",clist);
        //查询热门商品0为不热门
        List<Product> hlist=productService.findHot();
        //保存在值栈中为了能够显示在首页
        ActionContext.getContext().getValueStack().set("hlist",hlist);
        //调用查询最新商品方法,拿到集合,并把结果放在值栈中
        List<Product> nlist=productService.findNew();
        ActionContext.getContext().getValueStack().set("nlist",nlist);

        return "index";
    }

}