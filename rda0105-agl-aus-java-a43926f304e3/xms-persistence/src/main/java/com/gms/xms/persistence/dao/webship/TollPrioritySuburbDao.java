package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.systemstats.SysStatsFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.toll.TollPrioritySuburbFilter;
import com.gms.xms.txndb.vo.webship.toll.TollPrioritySuburbVo;

import java.util.List;

/**
 * Posted from TollPrioritySuburbDao
 * <p>
 * Author HungNT Date Aug 25, 2015
 */
public class TollPrioritySuburbDao extends BaseDao<TollPrioritySuburbVo> {
    public String selectTollPriorityZone(TollPrioritySuburbFilter filter) throws DaoException {
        return (String) selectObject(filter, "TollPrioritySuburb.selectTollPriorityZone");
    }

    public List<TollPrioritySuburbVo> getTollPrioritySuburbs(SysStatsFilter filter) throws DaoException {
        return selectList(filter, "TollPrioritySuburb.getTollPrioritySuburbs");
    }

    public long countTollPrioritySuburbs(SysStatsFilter filter) throws DaoException {
        return (long) selectObject(filter, "TollPrioritySuburb.countTollPrioritySuburbs");
    }
}
