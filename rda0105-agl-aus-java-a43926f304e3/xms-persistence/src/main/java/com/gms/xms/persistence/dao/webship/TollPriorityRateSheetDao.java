package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.toll.TollPriorityRateSheetFilter;
import com.gms.xms.txndb.vo.webship.toll.TollPriorityRateSheetVo;

import java.util.List;

/**
 * Posted from TollPriorityRateSheetDao
 * <p>
 * Author HungNT Date Aug 26, 2015
 */
public class TollPriorityRateSheetDao extends BaseDao<TollPriorityRateSheetVo> {
    public TollPriorityRateSheetVo selectTollPriorityRateSheet(TollPriorityRateSheetFilter filter) throws DaoException {
        return select(filter, "TollPriorityRateSheet.selectTollPriorityRateSheet");
    }

    public List<TollPriorityRateSheetVo> getTollPriorityRateSheets(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "TollPriorityRateSheet.getTollPriorityRateSheets");
    }

    public long countTollPriorityRateSheets(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(null, "TollPriorityRateSheet.countTollPriorityRateSheets");
    }
}
