package rj.categorysecond.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import rj.categorysecond.entity.CategorySecond;
import rj.utils.PageHibernateCallback;

import java.util.List;

/**
 * Created by 隽 on 2016/11/26.
 */
public class CategorySecondDao extends HibernateDaoSupport{

    public int findCount() {
        String hql = "select count(*) from CategorySecond";
        List<Long> list = this.getHibernateTemplate().find(hql);
        if (list != null && list.size() > 0) {
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<CategorySecond> findByPage(int begin, int limit) {
        String hql = "from CategorySecond order by csid desc";
        List<CategorySecond> list = this.getHibernateTemplate().execute(
                new PageHibernateCallback<CategorySecond>(hql, null, begin,
                        limit));
        return list;
    }

    public void save(CategorySecond categorySecond) {
        getHibernateTemplate().save(categorySecond);
    }

    public void delete(CategorySecond categorySecond) {
        getHibernateTemplate().delete(categorySecond);
    }

    public CategorySecond findByCsid(Integer csid) {
        return getHibernateTemplate().get(CategorySecond.class,csid);
    }

    public void update(CategorySecond categorySecond) {
        getHibernateTemplate().update(categorySecond);
    }

    public List<CategorySecond> findAll() {
        String hql="from CategorySecond";
        return getHibernateTemplate().find(hql);
    }
}
