package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.ProductFilter;
import com.gms.xms.txndb.vo.admin.ProductVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IProductService
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public interface IProductService {
    public List<ProductVo> selectByFilter(ProductFilter filter) throws DaoException;

    public long countByFilter(ProductFilter filter) throws DaoException;

    public ProductVo selectById(Long productId) throws DaoException;

    public void delete(Map<String, String> context, Long ProductId) throws DaoException;

    public void insert(Map<String, String> context, ProductVo product) throws DaoException;

    public void update(Map<String, String> context, ProductVo product) throws DaoException;

    public long countByName(ProductFilter searchFilter) throws DaoException;
}
