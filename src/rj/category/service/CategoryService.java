package rj.category.service;

import org.springframework.transaction.annotation.Transactional;
import rj.category.dao.CategoryDao;
import rj.category.entity.Category;

import java.util.List;

/**
 * Created by 隽 on 2016/11/20.
 */
//session管理
    @Transactional
public class CategoryService {
    private CategoryDao categoryDao;

    public void setCategoryDao(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }
    //业务中查询所有分类的方法
    public List<Category> findAll() {
       return categoryDao.findAll();
    }
}
