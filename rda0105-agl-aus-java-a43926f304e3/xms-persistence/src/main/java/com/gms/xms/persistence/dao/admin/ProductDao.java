package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ProductFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.ProductVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ProductDao
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductDao extends BaseDao<ProductVo> {
    public ProductDao() {
        super();
    }

    public ProductDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void insert(Map<String, String> context, ProductVo product) throws DaoException {
        insert(context, product, "Product.insert");
    }

    public void update(Map<String, String> context, ProductVo product) throws DaoException {
        update(context, product, "Product.update");
    }

    public void delete(Map<String, String> context, Long productId) throws DaoException {
        delete(context, productId, "Product.delete");
    }

    public List<ProductVo> selectByFilter(ProductFilter filter) throws DaoException {
        return this.selectList(filter, "Product.selectByFilter");
    }

    public long countByFilter(ProductFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Product.countByFilter");
    }

    public ProductVo selectById(Long productId) throws DaoException {
        return this.select(productId, "Product.selectById");
    }

    public long countByName(ProductFilter searchFilter) throws DaoException {
        return (long) this.selectObject(searchFilter, "Product.countProductByName");
    }
}