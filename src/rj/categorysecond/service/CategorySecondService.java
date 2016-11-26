package rj.categorysecond.service;

import org.springframework.transaction.annotation.Transactional;
import rj.categorysecond.dao.CategorySecondDao;
import rj.categorysecond.entity.CategorySecond;
import rj.utils.PageBean;

import java.util.List;

/**
 * Created by 隽 on 2016/11/26.
 */
@Transactional
public class CategorySecondService {
    private CategorySecondDao categorySecondDao;

    public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
        this.categorySecondDao = categorySecondDao;
    }

    /**
     * 分页查询二级分类的方法
     * @param page
     * @return
     */
    public PageBean<CategorySecond> findByPage(Integer page) {
        PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();

        // 设置参数:
        pageBean.setPage(page);
        // 设置每页显示记录数:
        int limit = 10;
        pageBean.setLimit(limit);
        // 设置总记录数:
        int totalCount = categorySecondDao.findCount();
        pageBean.setTotal(totalCount);
        // 设置总页数:
        int totalPage = 0;
        if (totalCount % limit == 0) {
            totalPage = totalCount / limit;
        } else {
            totalPage = totalCount / limit + 1;
        }
        pageBean.setTotalPage(totalPage);
        // 设置页面显示数据的集合:
        int begin = (page - 1) * limit;
        List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
        pageBean.setList(list);
        return pageBean;
    }


    public void save(CategorySecond categorySecond) {
        categorySecondDao.save(categorySecond);
    }

    public void delete(CategorySecond categorySecond) {
        categorySecondDao.delete(categorySecond);
    }

    public CategorySecond findByCsid(Integer csid) {
        return categorySecondDao.findByCsid(csid);
    }

    public void update(CategorySecond categorySecond) {
        categorySecondDao.update(categorySecond);
    }

    public List<CategorySecond> findAll() {
        return categorySecondDao.findAll();
    }
}
