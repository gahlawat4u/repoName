package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.TerritoryFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.TerritoryVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from TerritoryDao
 * <p>
 * Author HoangPH Oct 17, 2015
 */
public class TerritoryDao extends BaseDao<TerritoryVo> {
    public TerritoryDao() {
        super();
    }

    public TerritoryDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void insert(Map<String, String> context, TerritoryVo territory) throws DaoException {
        insert(context, territory, "Territory.insert");
    }

    public void update(Map<String, String> context, TerritoryVo territory) throws DaoException {
        update(context, territory, "Territory.update");
    }

    public void delete(Map<String, String> context, Long territoryId) throws DaoException {
        delete(context, territoryId, "Territory.delete");
    }

    public List<TerritoryVo> selectByFilter(TerritoryFilter filter) throws DaoException {
        return this.selectList(filter, "Territory.selectByFilter");
    }

    public long countByFilter(TerritoryFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Territory.countByFilter");
    }

    public TerritoryVo selectById(Long territoryId) throws DaoException {
        return this.select(territoryId, "Territory.selectById");
    }

    public long countByName(TerritoryVo territory) throws DaoException {
        return (long) this.selectObject(territory, "Territory.countTerritoryByName");
    }
}