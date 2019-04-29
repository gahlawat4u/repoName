package com.gms.xms.persistence.dao.report.selfinsurance;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.selfinsurance.InvoicedAirbillFilter;
import com.gms.xms.filter.reports.selfinsurance.WebshipLabelFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.reports.selfinsurance.InvoicedAirbillVo;
import com.gms.xms.txndb.vo.reports.selfinsurance.SummaryInvoicedAirbillVo;
import com.gms.xms.txndb.vo.reports.selfinsurance.WebshipLabelVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from SelfInsuranceDao
 * <p>
 * Author dattrinh Mar 18, 2016 3:44:39 PM
 */
public class SelfInsuranceDao extends BaseDao<Object> {
    public List<WebshipLabelVo> getWebshipLabelReport(WebshipLabelFilter filter) throws DaoException {
        return this.selectList(filter, "SelfInsurance.getWebshipLabelReport");
    }

    public long countWebshipLabelReport(WebshipLabelFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "SelfInsurance.countWebshipLabelReport");
    }

    public List<InvoicedAirbillVo> getInvoicedAirbillReport(InvoicedAirbillFilter filter) throws DaoException {
        return this.selectList(filter, "SelfInsurance.getInvoicedAirbillReport");
    }

    public long countInvoicedAirbillReport(InvoicedAirbillFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "SelfInsurance.countInvoicedAirbillReport");
    }

    public SummaryInvoicedAirbillVo getSummaryInvoicedAirbillReport(InvoicedAirbillFilter filter) throws DaoException {
        return (SummaryInvoicedAirbillVo) this.selectObject(filter, "SelfInsurance.getSummaryInvoicedAirbillReport");
    }

    public void prepareInvoicedAirbillReport(Map<String, String> context, InvoicedAirbillFilter filter) throws DaoException {
        this.insert(context, filter, "SelfInsurance.prepareInvoicedAirbillReport");
    }
}
