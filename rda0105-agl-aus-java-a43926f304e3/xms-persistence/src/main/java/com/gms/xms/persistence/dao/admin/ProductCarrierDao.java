package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ProductCarrierFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.ProductCarrierVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ProductCarrierDao
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class ProductCarrierDao extends BaseDao<ProductCarrierVo> {
    public ProductCarrierDao() {
        super();
    }

    public ProductCarrierDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void insert(Map<String, String> context, ProductCarrierVo productCarrier) throws DaoException {
        insert(context, productCarrier, "ProductCarrier.insert");
    }

    public void update(Map<String, String> context, ProductCarrierVo productCarrier) throws DaoException {
        update(context, productCarrier, "ProductCarrier.update");
    }

    public void delete(Map<String, String> context, Long productCarrierId) throws DaoException {
        delete(context, productCarrierId, "ProductCarrier.delete");
    }

    public List<ProductCarrierVo> selectByFilter(ProductCarrierFilter filter) throws DaoException {
        return this.selectList(filter, "ProductCarrier.selectByFilter");
    }

    public long countByFilter(ProductCarrierFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "ProductCarrier.countByFilter");
    }

    public ProductCarrierVo selectById(Long productCarrierId) throws DaoException {
        return this.select(productCarrierId, "ProductCarrier.selectById");
    }

    public long countByName(ProductCarrierVo productCarrier) throws DaoException {
        return (long) this.selectObject(productCarrier, "ProductCarrier.countProductCarrierByName");
    }
}