package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.TntRemoteAreaVo;

import java.util.List;

/**
 * Posted from TntRemoteAreaDao
 * <p>
 * Author HungNT Date Aug 12, 2015
 */
public class TntRemoteAreaDao extends BaseDao<TntRemoteAreaVo> {
    public Integer selectCountRemoteAreaService(TntRemoteAreaVo tntRemoteAreaVo) throws DaoException {
        return (Integer) selectObject(tntRemoteAreaVo, "TntRemoteArea.selectCountRemoteAreaService");
    }

    public List<TntRemoteAreaVo> getTntRemoteAreas(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "TntRemoteArea.getTntRemoteAreas");
    }

    public long countTntRemoteAreas(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(null, "TntRemoteArea.countTntRemoteAreas");
    }
}