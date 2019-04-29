package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.FranchisePayableMSOverviewVo;

import java.math.BigDecimal;

/**
 * Posted from FranchisePayableMSOverviewDao
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableMSOverviewDao extends BaseDao<FranchisePayableMSOverviewVo> {

    public long getSetups(FranchisePayableFilter filter) throws DaoException {
        return (long) selectObject(filter, "FranchisePayableMSOverview.getSetups");
    }

    public long getActivations(FranchisePayableFilter filter) throws DaoException {
        return (long) selectObject(filter, "FranchisePayableMSOverview.getActivations");
    }

    public long getPrintedInvoices(FranchisePayableFilter filter) throws DaoException {
        return (long) selectObject(filter, "FranchisePayableMSOverview.getPrintedInvoices");
    }

    public long getEmailInvoices(FranchisePayableFilter filter) throws DaoException {
        return (long) selectObject(filter, "FranchisePayableMSOverview.getEmailInvoices");
    }

    public BigDecimal get61DaysPayments(FranchisePayableFilter filter) throws DaoException {
        return (BigDecimal) selectObject(filter, "FranchisePayableMSOverview.get61DaysPayments");
    }
}
