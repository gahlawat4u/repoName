package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.SupplyVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SupplyDao
 * <p>
 * Author DatTV Date Jul 15, 2015 5:12:04 PM
 */
public class SupplyDao extends BaseDao<SupplyVo> {

    public List<SupplyVo> selectAll() throws DaoException {
        return selectList(null, "Supply.selectAll");
    }

    public List<SupplyVo> selectByServiceId(Integer serviceId) throws DaoException {
        return selectList(serviceId, "Supply.selectByServiceId");
    }

    public SupplyVo selectById(Integer supplyId) throws DaoException {
        return select(supplyId, "Supply.selectById");
    }

    public List<SupplyVo> selectWithLocalization() throws DaoException {
        return selectList(null, "Supply.selectWithLocalization");
    }

    public void insert(Map<String, String> context, SupplyVo supplyVo) throws DaoException {
        this.insert(context, supplyVo, "Supply.insert");
    }

    public void update(Map<String, String> context, SupplyVo supplyVo) throws DaoException {
        this.update(context, supplyVo, "Supply.update");
    }

    public void delete(Map<String, String> context, SupplyVo supplyVo) throws DaoException {
        this.delete(context, supplyVo, "Supply.delete");
    }
}