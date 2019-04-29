package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.TerritoryFilter;
import com.gms.xms.txndb.vo.admin.TerritoryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ITerritoryService
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public interface ITerritoryService {
    public List<TerritoryVo> selectByFilter(TerritoryFilter filter) throws DaoException;

    public long countByFilter(TerritoryFilter filter) throws DaoException;

    public TerritoryVo selectById(Long territoryId) throws DaoException;

    public void delete(Map<String, String> context, Long TerritoryId) throws DaoException;

    public void insert(Map<String, String> context, TerritoryVo territory) throws DaoException;

    public void update(Map<String, String> context, TerritoryVo territory) throws DaoException;

    public long countByName(TerritoryVo territory) throws DaoException;
}
