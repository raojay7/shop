package rj.product.service;

import org.springframework.transaction.annotation.Transactional;
import rj.product.dao.ProductDao;
import rj.product.entity.Product;

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
}
