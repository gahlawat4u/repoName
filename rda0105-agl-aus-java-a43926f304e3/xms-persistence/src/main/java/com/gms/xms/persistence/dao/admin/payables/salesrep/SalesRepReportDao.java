package com.gms.xms.persistence.dao.admin.payables.salesrep;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepReportFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepAirbillStatVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepOverviewVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepServiceStatVo;

import java.util.List;

/**
 * Posted from SalesRepReportDao
 * <p>
 * Author dattrinh Mar 23, 2016 4:39:20 PM
 */
public class SalesRepReportDao extends BaseDao<Object> {
    public SalesRepOverviewVo getSalesRepOverview(SalesRepReportFilter filter) throws DaoException {
        return (SalesRepOverviewVo) this.select(filter, "SalesRepReport.getSalesRepOverview");
    }

    public List<SalesRepServiceStatVo> getSalesRepServiceStats(SalesRepReportFilter filter) throws DaoException {
        return this.selectList(filter, "SalesRepReport.getSalesRepServiceStats");
    }

    public List<SalesRepAirbillStatVo> getSalesRepAirbillStats(SalesRepReportFilter filter) throws DaoException {
        return this.selectList(filter, "SalesRepReport.getSalesRepAirbillStats");
    }

    public long countSalesRepAirbillStats(SalesRepReportFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "SalesRepReport.countSalesRepAirbillStats");
    }
}