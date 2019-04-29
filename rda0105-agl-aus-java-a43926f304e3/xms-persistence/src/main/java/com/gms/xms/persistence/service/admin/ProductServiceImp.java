package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ProductFilter;
import com.gms.xms.persistence.dao.admin.ProductDao;
import com.gms.xms.txndb.vo.admin.ProductVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ProductServiceImp
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductServiceImp implements IProductService {
    @Override
    public List<ProductVo> selectByFilter(ProductFilter filter) throws DaoException {
        ProductDao productDao = new ProductDao();
        // List<ProductVo> productVos = productDao.selectByFilter(filter);
        return productDao.selectByFilter(filter);
    }

    @Override
    public long countByFilter(ProductFilter filter) throws DaoException {
        ProductDao productDao = new ProductDao();
        return productDao.countByFilter(filter);
    }

    @Override
    public ProductVo selectById(Long productId) throws DaoException {
        ProductDao productDao = new ProductDao();
        return productDao.selectById(productId);
    }

    @Override
    public void delete(Map<String, String> context, Long productId) throws DaoException {
        ProductDao productDao = new ProductDao();
        productDao.delete(context, productId);
    }

    @Override
    public void insert(Map<String, String> context, ProductVo product) throws DaoException {
        ProductDao productDao = new ProductDao();
        productDao.insert(context, product);
    }

    @Override
    public void update(Map<String, String> context, ProductVo product) throws DaoException {
        ProductDao productDao = new ProductDao();
        productDao.update(context, product);
    }

    @Override
    public long countByName(ProductFilter searchFilter) throws DaoException {
        ProductDao productDao = new ProductDao();
        return productDao.countByName(searchFilter);
    }
}
