package com.gms.xms.persistence.service.supply;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.SupplyVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ISupplyService
 * <p>
 * Author DatTV Date Jul 15, 2015 5:17:11 PM
 */
public interface ISupplyService {
    public List<SupplyVo> selectAll() throws DaoException;

    public List<SupplyVo> selectByServiceId(Integer serviceId) throws DaoException;

    public SupplyVo selectById(Integer supplyId) throws DaoException;

    public List<SupplyVo> selectWithLocalization() throws DaoException;

    public void insert(Map<String, String> context, SupplyVo supplyVo) throws DaoException;

    public void update(Map<String, String> context, SupplyVo supplyVo) throws DaoException;

    public void delete(Map<String, String> context, SupplyVo supplyVo) throws DaoException;
}
