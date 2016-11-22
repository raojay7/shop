package rj.product.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import rj.product.entity.Product;

import java.util.List;

/**
 * Created by 隽 on 2016/11/22.
 */
public class ProductDao extends HibernateDaoSupport{
    private SessionFactory sessionFactory;

    public List<Product> findHot() {
        //使用离线查询，把具体的业务逻辑放在service层
        DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);

        //查询热门的商品，条件就是is_hot=1
        criteria.add(Restrictions.eq("is_hot",1));
        //倒序的排序输出
        criteria.addOrder(Order.desc("pdate"));
        //执行查询,采用分页
        List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
        return list;
    }

    public List<Product> findNew() {
        DetachedCriteria criteria=DetachedCriteria.forClass(Product.class);
        criteria.addOrder(Order.desc("pdate"));
        List<Product> list = this.getHibernateTemplate().findByCriteria(criteria,0,10);
        return list;
    }
}
