package rj.order.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import rj.order.entity.Order;
import rj.order.entity.OrderItem;
import rj.utils.PageHibernateCallback;

import java.util.List;

/**
 * Created by 隽 on 2016/11/25.
 */
public class OrderDao extends HibernateDaoSupport{

    /**
     * 为了保存订单的同时保存订单项，在订单项的映射文件中cscade加save-update
     * @param order
     */
    public void save(Order order) {
        getHibernateTemplate().save(order);
    }



    /**
     * dao层我的订单的总共个数
     * @param uid
     * @return
     */
    public Integer findCountUid(Integer uid) {
        String hql="select count(*) from Order o where o.user.uid=?";
        List<Long> list = getHibernateTemplate().find(hql, uid);
        if (list!=null&&list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    /**
     * dao层我的订单实体
     * @param uid
     * @param begin
     * @param limit
     * @return
     */
    public List<Order> findByPageUid(Integer uid, int begin, int limit) {
        String hql="from Order o where o.user.uid=? order by ordertime desc";

        List<Order> list=getHibernateTemplate().execute(new PageHibernateCallback<Order>
                (hql,new Object[]{uid},begin,limit));

        if (list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public Order findByOid(Integer oid) {
       return getHibernateTemplate().get(Order.class,oid);
    }

    /**
     * 查询总数
     * @return
     */
    public Integer findCount() {
        String hql="select count(*) from Order";
        List<Long> list = getHibernateTemplate().find(hql);
        if (list!=null&&list.size()>0){
            return list.get(0).intValue();
        }
        return null;
    }

    /**
     * 查询所有订单方法
     * @param begin
     * @param limit
     * @return
     */
    public List<Order> findByPage(int begin, int limit) {
        String hql="from Order";
        List<Order> list=getHibernateTemplate().execute(new PageHibernateCallback<Order>
                (hql,null,begin,limit));

        if (list!=null&&list.size()>0){
            return list;
        }
        return null;
    }

    public List<OrderItem> findOrderItem(Integer oid) {
        String hql="from OrderItem oi where oi.order.oid=?";
        List<OrderItem> list = getHibernateTemplate().find(hql,oid);
        return list;
    }

    public void update(Order order) {
        getHibernateTemplate().update(order);
    }
}
