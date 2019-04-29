package com.gms.xms.persistence.service.admin.payables.salesrep;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepReportFilter;
import com.gms.xms.filter.admin.payables.salesrep.SalesRepSettingFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.admin.payables.salesrep.SalesRepDao;
import com.gms.xms.persistence.dao.admin.payables.salesrep.SalesRepReportDao;
import com.gms.xms.persistence.dao.admin.payables.salesrep.SalesRepServiceDao;
import com.gms.xms.persistence.dao.admin.payables.salesrep.SalesRepSettingDao;
import com.gms.xms.txndb.vo.SalesRepServiceVo;
import com.gms.xms.txndb.vo.SalesRepVo;
import com.gms.xms.txndb.vo.payables.salesrep.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from SalesRepServiceImp
 * <p>
 * Author dattrinh Mar 22, 2016 4:00:00 PM
 */
public class SalesRepServiceImp implements ISalesRepService {
    private static final Log log = LogFactory.getLog(SalesRepServiceImp.class);
    private SalesRepSettingDao salesRepSettingDao = new SalesRepSettingDao();

    @Override
    public List<SalesRepSettingVo> getSalesRepSettingsByFilter(SalesRepSettingFilter filter) throws DaoException {
        return salesRepSettingDao.getSalesRepSettingsByFilter(filter);
    }

    @Override
    public long countSalesRepSettingsByFilter(SalesRepSettingFilter filter) throws DaoException {
        return salesRepSettingDao.countSalesRepSettingsByFilter(filter);
    }

    @Override
    public SalesRepSettingVo getSalesRepSettingsBySaleRepId(Long saleRepId) throws DaoException {
        return salesRepSettingDao.getSalesRepSettingsBySaleRepId(saleRepId);
    }

    @Override
    public void addSalesRepSetting(Map<String, String> context, SalesRepSettingVo salesRepSettingVo) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        try {
            SalesRepDao salesRepDao = new SalesRepDao(sessionClient);
            SalesRepServiceDao salesRepServiceDao = new SalesRepServiceDao(sessionClient);
            SalesRepVo salesRepVo = salesRepSettingVo;
            // Insert new sales rep.
            salesRepDao.insertSalesRep(context, salesRepVo);
            log.info("Insert sales rep: " + salesRepVo);
            // Insert sales rep services.
            for (SalesRepPayoutGoalVo service : salesRepSettingVo.getSalesRepServices()) {
                SalesRepServiceVo salesRepServiceVo = service;
                salesRepServiceVo.setSalesRepId(salesRepVo.getSalesRepId());
                // Insert new service if it's goal or payout is not null.
                if (salesRepServiceVo.getGoal() != null || salesRepServiceVo.getPayout() != null) {
                    if (salesRepServiceVo.getGoal() == null) {
                        salesRepServiceVo.setGoal(0.0);
                    }
                    if (salesRepServiceVo.getPayout() == null) {
                        salesRepServiceVo.setPayout(0.0);
                    }
                    salesRepServiceDao.insertSalesRepService(context, salesRepServiceVo);
                    log.info("Insert sales rep service: " + salesRepServiceVo);
                }
            }
            sessionClient.endTransaction();
        } catch (Exception e) {
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void editSalesRepSetting(Map<String, String> context, SalesRepSettingVo salesRepSettingVo) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        try {
            SalesRepDao salesRepDao = new SalesRepDao(sessionClient);
            SalesRepServiceDao salesRepServiceDao = new SalesRepServiceDao(sessionClient);
            SalesRepVo salesRepVo = salesRepSettingVo;
            // Update sales rep.
            salesRepDao.updateSalesRep(context, salesRepVo);
            log.info("Update sales rep: " + salesRepVo);
            // Update sales rep services.
            for (SalesRepPayoutGoalVo service : salesRepSettingVo.getSalesRepServices()) {
                SalesRepServiceVo salesRepServiceVo = service;
                // Check if this service setup or not?
                SalesRepServiceVo existService = salesRepServiceDao.selectByFilter(salesRepServiceVo);
                // If service exists.
                if (existService != null) {
                    if (salesRepServiceVo.getGoal() == null && salesRepServiceVo.getPayout() == null) {
                        // Delete service.
                        salesRepServiceDao.deleteSalesRepService(context, salesRepServiceVo);
                        log.info("Delete sales rep service: " + salesRepServiceVo);
                    } else {
                        // Update service.
                        salesRepServiceDao.updateSalesRepService(context, salesRepServiceVo);
                        log.info("Update sales rep service: " + salesRepServiceVo);
                    }
                } else {
                    if (salesRepServiceVo.getGoal() != null || salesRepServiceVo.getPayout() != null) {
                        // Insert new service.
                        if (salesRepServiceVo.getGoal() == null) {
                            salesRepServiceVo.setGoal(0.0);
                        }
                        if (salesRepServiceVo.getPayout() == null) {
                            salesRepServiceVo.setPayout(0.0);
                        }
                        salesRepServiceDao.insertSalesRepService(context, salesRepServiceVo);
                        log.info("Insert sales rep service: " + salesRepServiceVo);
                    }
                }
            }
            sessionClient.endTransaction();
        } catch (Exception e) {
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public SalesRepSettingVo getNewSalesRepSettingByUserId(Long userId) throws DaoException {
        return salesRepSettingDao.getNewSalesRepSettingByUserId(userId);
    }

    @Override
    public SalesRepOverviewVo getSalesRepOverview(SalesRepReportFilter filter) throws DaoException {
        SalesRepReportDao dao = new SalesRepReportDao();
        return dao.getSalesRepOverview(filter);
    }

    @Override
    public List<SalesRepServiceStatVo> getSalesRepServiceStats(SalesRepReportFilter filter) throws DaoException {
        SalesRepReportDao dao = new SalesRepReportDao();
        return dao.getSalesRepServiceStats(filter);
    }

    @Override
    public List<SalesRepAirbillStatVo> getSalesRepAirbillStats(SalesRepReportFilter filter) throws DaoException {
        SalesRepReportDao dao = new SalesRepReportDao();
        return dao.getSalesRepAirbillStats(filter);
    }

    @Override
    public long countSalesRepAirbillStats(SalesRepReportFilter filter) throws DaoException {
        SalesRepReportDao dao = new SalesRepReportDao();
        return dao.countSalesRepAirbillStats(filter);
    }
}
