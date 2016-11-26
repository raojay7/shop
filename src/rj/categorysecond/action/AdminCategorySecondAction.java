package rj.categorysecond.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import rj.category.entity.Category;
import rj.category.service.CategoryService;
import rj.categorysecond.entity.CategorySecond;
import rj.categorysecond.service.CategorySecondService;
import rj.utils.PageBean;

import java.util.List;

/**
 * Created by 隽 on 2016/11/26.
 */
public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

    private CategorySecond categorySecond=new CategorySecond();
    @Override
    public CategorySecond getModel() {
        return categorySecond;
    }
    private CategorySecondService categorySecondService;

    public void setCategorySecondService(CategorySecondService categorySecondService) {
        this.categorySecondService = categorySecondService;
    }
    // 注入一级分类的Service
    private CategoryService categoryService;

    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //分页的相关准备
    private Integer page;

    public void setPage(Integer page) {
        this.page = page;
    }
    //查询二级分类的方法
    public String findAll(){
        PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
        ActionContext.getContext().getValueStack().set("pageBean", pageBean);
        return "findAll";
    }
    // 跳转到添加页面的方法:
    public String addPage() {
        // 查询所有一级分类.
        List<Category> cList = categoryService.findAll();
        // 将集合存入到值栈中.
        ActionContext.getContext().getValueStack().set("cList", cList);
        // 页面跳转:
        return "addPage";
    }

    // 添加二级分类的方法:
    public String save() {
        categorySecondService.save(categorySecond);
        return "saveSuccess";
    }

    // 删除二级分类的方法:
    public String delete() {
        categorySecondService.delete(categorySecond);
        return "deleteSuccess";
    }

    // 编辑二级分类的方法:
    public String edit() {
        // 根据id查询二级分类:
        categorySecond = categorySecondService.findByCsid(categorySecond
                .getCsid());
        // 查询所有一级分类:
        List<Category> cList = categoryService.findAll();
        // 将集合存入到值栈中.
        ActionContext.getContext().getValueStack().set("cList", cList);
        // 页面跳转:
        return "editSuccess";
    }

    // 修改二级分类的方法:
    public String update(){
        categorySecondService.update(categorySecond);
        return "updateSuccess";
    }


}
