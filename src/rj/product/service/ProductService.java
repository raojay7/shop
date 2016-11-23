package rj.product.service;

import org.springframework.transaction.annotation.Transactional;
import rj.product.dao.ProductDao;
import rj.product.entity.Product;
import rj.utils.PageBean;

import java.util.List;

/**
 * Created by 隽 on 2016/11/22.
 */
//session管理
    @Transactional
public class ProductService {
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

    public List<Product> findHot() {
        return productDao.findHot();
    }

    public List<Product> findNew() {
        return  productDao.findNew();
    }

    public Product findByPid(int pid) {
        return productDao.findByPid(pid);
    }

    /**
     * 通过一级分类分页的查询商品
     * 调用dao的方法的同时加上业务逻辑
     * @param cid
     * @param page
     * @return
     */
    public PageBean<Product> findByCid(Integer cid, int page) {
        //创建pagebean对象
        PageBean<Product> pageBean=new PageBean<>();

        //设置当前页
        pageBean.setPage(page);

        //设置总数据数
        int total=0;
        //调用dao的查询总数据数的方法
        total=productDao.findCountCid(cid);
        pageBean.setTotal(total);

        //设置一页显示多少
        int limit=8;
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
        List<Product> list = productDao.findByPageCid(cid,begin,limit);
        pageBean.setList(list);
        return pageBean;
    }

    public PageBean<Product> findByCsid(int csid, int page) {
        //创建pagebean对象
        PageBean<Product> pageBean=new PageBean<>();

        //设置当前页
        pageBean.setPage(page);

        //设置总数据数
        int total=0;
        //调用dao的查询总数据数的方法
        total=productDao.findCountCsid(csid);
        pageBean.setTotal(total);

        //设置一页显示多少
        int limit=8;
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
        //根据二级分类直接查询到商品，并封装数据到pagebean的list中
        List<Product> list = productDao.findByCsid(csid,begin,limit);
        pageBean.setList(list);
        return pageBean;
    }
}
