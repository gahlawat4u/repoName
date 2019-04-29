package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.TerritoryFilter;
import com.gms.xms.persistence.dao.admin.TerritoryDao;
import com.gms.xms.txndb.vo.admin.TerritoryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from TerritoryServiceImp
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class TerritoryServiceImp implements ITerritoryService {
    public List<TerritoryVo> selectByFilter(TerritoryFilter filter) throws DaoException {
        TerritoryDao territorDao = new TerritoryDao();
        // List<TerritoryVo> territoryVos = territorDao.selectByFilter(filter);
        return territorDao.selectByFilter(filter);
    }

    public long countByFilter(TerritoryFilter filter) throws DaoException {
        TerritoryDao territorDao = new TerritoryDao();
        return territorDao.countByFilter(filter);
    }

    public TerritoryVo selectById(Long territoryId) throws DaoException {
        TerritoryDao territorDao = new TerritoryDao();
        return territorDao.selectById(territoryId);
    }

    public void delete(Map<String, String> context, Long territoryId) throws DaoException {
        TerritoryDao territorDao = new TerritoryDao();
        territorDao.delete(context, territoryId);
    }

    public void insert(Map<String, String> context, TerritoryVo territory) throws DaoException {
        TerritoryDao territorDao = new TerritoryDao();
        territorDao.insert(context, territory);
    }

    public void update(Map<String, String> context, TerritoryVo territory) throws DaoException {
        TerritoryDao territorDao = new TerritoryDao();
        territorDao.update(context, territory);
    }

    public long countByName(TerritoryVo territory) throws DaoException {
        TerritoryDao territorDao = new TerritoryDao();
        return territorDao.countByName(territory);
    }
}
