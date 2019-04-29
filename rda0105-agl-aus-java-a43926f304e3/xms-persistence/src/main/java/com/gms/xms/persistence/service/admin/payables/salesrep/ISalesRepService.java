package com.gms.xms.persistence.service.admin.payables.salesrep;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepReportFilter;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepSettingFilter;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepAirbillStatVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepOverviewVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepServiceStatVo;
import com.gms.xms.txndb.vo.payables.salesrep.SalesRepSettingVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ISalesRepService
 * <p>
 * Author dattrinh Mar 22, 2016 3:59:16 PM
 */
public interface ISalesRepService {
    public List<SalesRepSettingVo> getSalesRepSettingsByFilter(SalesRepSettingFilter filter) throws DaoException;

    public long countSalesRepSettingsByFilter(SalesRepSettingFilter filter) throws DaoException;

    public SalesRepSettingVo getSalesRepSettingsBySaleRepId(Long saleRepId) throws DaoException;

    public void addSalesRepSetting(Map<String, String> context, SalesRepSettingVo salesRepSettingVo) throws DaoException;

    public void editSalesRepSetting(Map<String, String> context, SalesRepSettingVo salesRepSettingVo) throws DaoException;

    public SalesRepSettingVo getNewSalesRepSettingByUserId(Long userId) throws DaoException;

    public SalesRepOverviewVo getSalesRepOverview(SalesRepReportFilter filter) throws DaoException;

    public List<SalesRepServiceStatVo> getSalesRepServiceStats(SalesRepReportFilter filter) throws DaoException;

    public List<SalesRepAirbillStatVo> getSalesRepAirbillStats(SalesRepReportFilter filter) throws DaoException;

    public long countSalesRepAirbillStats(SalesRepReportFilter filter) throws DaoException;
}
