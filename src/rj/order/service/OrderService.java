package rj.order.service;

import org.springframework.transaction.annotation.Transactional;
import rj.order.dao.OrderDao;
import rj.order.entity.Order;
import rj.utils.PageBean;

import java.util.List;

/**
 * Created by 隽 on 2016/11/25.
 */
@Transactional
public class OrderService {
    private OrderDao orderDao;

    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    public void save(Order order) {
        orderDao.save(order);
    }

    /**
     * 我的订单的业务代码
     * @param uid
     * @param page
     * @return
     */
    public PageBean<Order> findByPageUid(Integer uid, Integer page) {
        //创建pagebean对象
        PageBean<Order> pageBean=new PageBean<>();

        //设置当前页
        pageBean.setPage(page);

        //设置总数据数
        Integer total;
        //调用dao的查询总数据数的方法
        total=orderDao.findCountUid(uid);
        pageBean.setTotal(total);

        //设置一页显示多少
        int limit=5;
        pageBean.setLimit(limit);

        //设置总页数
        int totalPage=0;
        if(total % limit == 0){
            totalPage = total / limit;
        }else{
            totalPage = total / limit + 1;
        }
        pageBean.setTotalPage(totalPage);

        // 每页显示的数据集合:
        // 从哪开始:
        int begin=(page-1)*limit;
        //根据一级分类查询到商品，并封装数据到pagebean的list中
        List<Order> list = orderDao.findByPageUid(uid,begin,limit);
        pageBean.setList(list);
        return pageBean;
    }

    /**
     * 根据订单id查询订单
     * @param oid
     * @return
     */
    public Order findByOid(Integer oid){
       return orderDao.findByOid(oid);
    }

}
