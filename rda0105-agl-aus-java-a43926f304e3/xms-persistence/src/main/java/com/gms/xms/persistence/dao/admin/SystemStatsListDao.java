package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.SystemStatsListFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.SystemStatsListVo;

import java.util.List;

/**
 * Posted from AdminEmailDao
 * <p>
 * Author TANDT
 */
public class SystemStatsListDao extends BaseDao<SystemStatsListVo> {
    public List<SystemStatsListVo> selectSystemStatsList(SystemStatsListFilter filter) throws DaoException {
        return this.selectList(filter, "SystemStatsList.selectSystemStatsList");
    }

    public Long selectSystemStatsListCount(SystemStatsListFilter filter) throws DaoException {
        return (long) selectObject(filter, "SystemStatsList.selectSystemStatsListCount");
    }
}
