package com.gms.xms.persistence.dao.franchisepayable.sc;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.*;

import java.util.List;

/**
 * Posted from FranchisePayableSCDaoService
 * <p>
 * Author DatTV Date Apr 4, 2015
 */
public class FranchisePayableSCDaoService {

    // Get carrier credit details.
    public List<FranchisePayableSCCreditVo> getCarrierCreditDetails(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCCreditDao payableSCCreditDao = new FranchisePayableSCCreditDao();
        return payableSCCreditDao.getCarrierCreditDetails(filter);
    }

    public FranchisePayableSCCreditTotalVo getCarrierCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCCreditDao payableSCCreditDao = new FranchisePayableSCCreditDao();
        return payableSCCreditDao.getCarrierCreditDetailsTotal(filter);
    }

    public FranchisePayableSCCreditVo getTaxableCarrierCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCCreditDao payableSCCreditDao = new FranchisePayableSCCreditDao();
        return payableSCCreditDao.getTaxableCarrierCreditDetailsTotal(filter);
    }

    public FranchisePayableSCCreditVo getNonTaxableCarrierCreditDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCCreditDao payableSCCreditDao = new FranchisePayableSCCreditDao();
        return payableSCCreditDao.getNonTaxableCarrierCreditDetailsTotal(filter);
    }

    public Integer getCarrierCreditDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCCreditDao payableSCCreditDao = new FranchisePayableSCCreditDao();
        return payableSCCreditDao.getCarrierCreditDetailsRecordCount(filter);
    }

    // Get over payment.
    public List<FranchisePayableSCOverpaymentVo> getOverpayment(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCOverpaymentDao payableSCOverpaymentDao = new FranchisePayableSCOverpaymentDao();
        return payableSCOverpaymentDao.getOverpayment(filter);
    }

    public FranchisePayableSCOverpaymentVo getOverpaymentTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCOverpaymentDao payableSCOverpaymentDao = new FranchisePayableSCOverpaymentDao();
        return payableSCOverpaymentDao.getOverpaymentTotal(filter);
    }

    public Integer getOverpaymentRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCOverpaymentDao payableSCOverpaymentDao = new FranchisePayableSCOverpaymentDao();
        return payableSCOverpaymentDao.getOverpaymentRecordCount(filter);
    }

    // Get shipment details
    public List<FranchisePayableSCShipmentVo> getShipmentDetails(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCShipmentDao payableSCShipmentDao = new FranchisePayableSCShipmentDao();
        return payableSCShipmentDao.getShipmentDetails(filter);
    }

    public FranchisePayableSCShipmentTotalVo getShipmentDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCShipmentDao payableSCShipmentDao = new FranchisePayableSCShipmentDao();
        return payableSCShipmentDao.getShipmentDetailsTotal(filter);
    }

    public FranchisePayableSCShipmentVo getTaxableShipmentDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCShipmentDao payableSCShipmentDao = new FranchisePayableSCShipmentDao();
        return payableSCShipmentDao.getTaxableShipmentDetailsTotal(filter);
    }

    public FranchisePayableSCShipmentVo getNonTaxableShipmentDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCShipmentDao payableSCShipmentDao = new FranchisePayableSCShipmentDao();
        return payableSCShipmentDao.getNonTaxableShipmentDetailsTotal(filter);
    }

    public Long getShipmentDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCShipmentDao payableSCShipmentDao = new FranchisePayableSCShipmentDao();
        return payableSCShipmentDao.getShipmentDetailsRecordCount(filter);
    }

    // Get tech fee details
    public List<FranchisePayableSCTechFeeVo> getTechFeeDetails(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCTechFeeDao payableSCTechFeeDao = new FranchisePayableSCTechFeeDao();
        return payableSCTechFeeDao.getTechFeeDetails(filter);
    }

    public FranchisePayableSCTechFeeTotalVo getTechFeeDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCTechFeeDao payableSCTechFeeDao = new FranchisePayableSCTechFeeDao();
        return payableSCTechFeeDao.getTechFeeDetailsTotal(filter);
    }

    public Long getTechFeeDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        FranchisePayableSCTechFeeDao payableSCTechFeeDao = new FranchisePayableSCTechFeeDao();
        return payableSCTechFeeDao.getTechFeeDetailsRecordCount(filter);
    }
}