package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ProductCarrierFilter;
import com.gms.xms.txndb.vo.admin.ProductCarrierVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IProductCarrierService
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public interface IProductCarrierService {
    public List<ProductCarrierVo> selectByFilter(ProductCarrierFilter filter) throws DaoException;

    public long countByFilter(ProductCarrierFilter filter) throws DaoException;

    public ProductCarrierVo selectById(Long productCarrierId) throws DaoException;

    public void delete(Map<String, String> context, Long ProductCarrierId) throws DaoException;

    public void insert(Map<String, String> context, ProductCarrierVo productCarrier) throws DaoException;

    public void update(Map<String, String> context, ProductCarrierVo productCarrier) throws DaoException;

    public long countByName(ProductCarrierVo productCarrier) throws DaoException;

}
