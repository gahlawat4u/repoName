package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ProductCarrierFilter;
import com.gms.xms.persistence.dao.admin.ProductCarrierDao;
import com.gms.xms.txndb.vo.admin.ProductCarrierVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ProductCarrierServiceImp
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductCarrierServiceImp implements IProductCarrierService {
    @Override
    public List<ProductCarrierVo> selectByFilter(ProductCarrierFilter filter) throws DaoException {
        ProductCarrierDao productCarrierDao = new ProductCarrierDao();
        return productCarrierDao.selectByFilter(filter);
    }

    @Override
    public long countByFilter(ProductCarrierFilter filter) throws DaoException {
        ProductCarrierDao productCarrierDao = new ProductCarrierDao();
        return productCarrierDao.countByFilter(filter);
    }

    @Override
    public ProductCarrierVo selectById(Long productCarrierId) throws DaoException {
        ProductCarrierDao productCarrierDao = new ProductCarrierDao();
        return productCarrierDao.selectById(productCarrierId);
    }

    @Override
    public void delete(Map<String, String> context, Long productCarrierId) throws DaoException {
        ProductCarrierDao productCarrierDao = new ProductCarrierDao();
        productCarrierDao.delete(context, productCarrierId);
    }

    @Override
    public void insert(Map<String, String> context, ProductCarrierVo productCarrier) throws DaoException {
        ProductCarrierDao productCarrierDao = new ProductCarrierDao();
        productCarrierDao.insert(context, productCarrier);
    }

    @Override
    public void update(Map<String, String> context, ProductCarrierVo productCarrier) throws DaoException {
        ProductCarrierDao productCarrierDao = new ProductCarrierDao();
        productCarrierDao.update(context, productCarrier);
    }

    @Override
    public long countByName(ProductCarrierVo productCarrier) throws DaoException {
        ProductCarrierDao productCarrierDao = new ProductCarrierDao();
        return productCarrierDao.countByName(productCarrier);
    }
}
