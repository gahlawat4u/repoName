package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.toll.TollRemoteAreaFilter;
import com.gms.xms.txndb.vo.webship.toll.TollRemoteAreaVo;

import java.util.List;

/**
 * Posted from TollRemoteAreaDao
 * <p>
 * Author HungNT Date Aug 26, 2015
 */
public class TollRemoteAreaDao extends BaseDao<TollRemoteAreaVo> {
    public TollRemoteAreaVo selectRemoteArea(TollRemoteAreaFilter filter) throws DaoException {
        return select(filter, "TollRemoteArea.selectRemoteArea");
    }

    public List<TollRemoteAreaVo> getTollRemoteAreas(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "TollRemoteArea.getTollRemoteAreas");
    }

    public long countTollRemoteAreas(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "TollRemoteArea.countTollRemoteAreas");
    }
}
