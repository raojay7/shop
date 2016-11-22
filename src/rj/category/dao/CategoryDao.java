package rj.category.dao;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import rj.category.entity.Category;

import java.util.List;

/**
 * Created by 隽 on 2016/11/20.
 */
public class CategoryDao extends HibernateDaoSupport{
    private SessionFactory sessionFactory;

    /**
     * dao的查询所有分类方法
     * @return
     */
    public List<Category> findAll() {
        String hql="from Category";
        List<Category> list=this.getHibernateTemplate().find(hql);
        return  list;
    }
}
