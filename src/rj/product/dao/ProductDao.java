package rj.product.dao;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import rj.product.entity.Product;
import rj.utils.PageHibernateCallback;

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

    public Product findByPid(int pid) {
        return this.getHibernateTemplate().get(Product.class,pid);
    }

    /**
     * 根据分类id查询商品个数
     * @param cid
     * @return
     */
    public int findCountCid(int cid) {
        //由于不能直接从商品中根据一级分类直接查，所以要间接查询
        String hql="select count(*) from Product p where p.categorySecond.category.cid=?";
        List<Long> list= getHibernateTemplate().find(hql,cid);
        if(list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * 根据分类的id查询集合
     * @param cid
     * @param begin
     * @param limit
     * @return
     */
    public List<Product> findByPageCid(Integer cid, int begin, int limit) {
        // select p.* from category c,categorysecond cs,product p where c.cid = cs.cid and cs.csid = p.csid and c.cid = 2
        // select p from Category c,CategorySecond cs,Product p where c.cid = cs.category.cid and cs.csid = p.categorySecond.csid and c.cid = ?
        //另外的方法
        String hql="select p from Product p join p.categorySecond cs join cs.category c where c.cid=?";
        //分页的另外一种方法
        List<Product> list=getHibernateTemplate()
                .execute(new PageHibernateCallback<Product>//
                        (hql,new Object[]{cid},begin,limit));//
        if(list != null && list.size() > 0){
            return list;
        }
        return null;

    }

    /**
     * 通过二级分类查总数据数
     * @param csid
     * @return
     */
    public int findCountCsid(int csid) {
        String hql="select count(*) from Product p where p.categorySecond.csid=?";
        List<Long> list = getHibernateTemplate().find(hql, csid);
        if(list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    /**
     * 通过二级分类的id查list
     * @param csid
     * @param begin
     * @param limit
     * @return
     */
    public List<Product> findByCsid(int csid, int begin, int limit) {
        //多复习关联查询和hql的优化
        String hql="select p from Product p join p.categorySecond cs where cs.csid=?";
        List<Product> list = getHibernateTemplate()
                .execute(new PageHibernateCallback<Product>//
                        (hql,new Object[]{csid},begin,limit));
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    /**
     * 查询总记录数
     * @return
     */
    public int findCount() {
        String hql="select count(*) from Product";
        List<Long> list = getHibernateTemplate().find(hql);
        if(list != null && list.size() > 0){
            return list.get(0).intValue();
        }
        return 0;
    }

    public List<Product> findByPage(int begin, int limit) {
        String hql="from Product order by pdate desc";
        List<Product> list = getHibernateTemplate()
                .execute(new PageHibernateCallback<Product>//
                        (hql,null,begin,limit));
        if(list != null && list.size() > 0){
            return list;
        }
        return null;
    }

    public void save(Product product) {
        getHibernateTemplate().save(product);
    }

    public void delete(Product product) {
        getHibernateTemplate().delete(product);
    }

    public void update(Product product) {
        getHibernateTemplate().update(product);
    }
}
