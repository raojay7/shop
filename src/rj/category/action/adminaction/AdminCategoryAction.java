package rj.category.action.adminaction;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import rj.category.entity.Category;
import rj.category.service.CategoryService;

import java.util.List;

/**
 * Created by 隽 on 2016/11/26.
 */
public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
    private Category category=new Category();
    @Override
    public Category getModel() {
        return category;
    }

    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * 查询所有一级分类方法
     */
    public String findAll(){
        List<Category> clist=categoryService.findAll();
        //将集合数据显示到页面
        ActionContext.getContext().getValueStack().set("clist",clist);
        return "findAll";
    }

    /**
     * 保存一级分类方法
     */
    public String save(){
        //调用service保存
        categoryService.save(category);
        return "saveSuccess";
    }

    public String delete(){
        categoryService.delete(category.getCid());
        return "deleteSuccess";
    }
    public String edit(){
        //查询到数据显示到修改页面
        category=categoryService.findByCid(category.getCid());
        return "editSuccess";
    }

    public String update(){
        //完成修改
        categoryService.update(category);
        return "updateSuccess";
    }
}
