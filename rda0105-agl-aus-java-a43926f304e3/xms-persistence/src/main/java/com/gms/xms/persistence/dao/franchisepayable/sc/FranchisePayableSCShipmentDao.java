package com.gms.xms.persistence.dao.franchisepayable.sc;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCShipmentTotalVo;
import com.gms.xms.txndb.vo.franchisepayable.sc.FranchisePayableSCShipmentVo;

import java.util.List;

/**
 * Posted from FranchisePayableSCShipmentDao
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class FranchisePayableSCShipmentDao extends BaseDao<FranchisePayableSCShipmentVo> {
    // Franchise Payable - Shipment details (Service Charge)

    public List<FranchisePayableSCShipmentVo> getShipmentDetails(FranchisePayableFilter filter) throws DaoException {
        return selectList(filter, "FranchisePayableSCShipment.getShipmentDetails");
    }

    public FranchisePayableSCShipmentTotalVo getShipmentDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return (FranchisePayableSCShipmentTotalVo) selectObject(filter, "FranchisePayableSCShipment.getShipmentDetailsTotal");
    }

    public FranchisePayableSCShipmentVo getTaxableShipmentDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableSCShipment.getTaxableShipmentDetailsTotal");
    }

    public FranchisePayableSCShipmentVo getNonTaxableShipmentDetailsTotal(FranchisePayableFilter filter) throws DaoException {
        return select(filter, "FranchisePayableSCShipment.getNonTaxableShipmentDetailsTotal");
    }

    public Long getShipmentDetailsRecordCount(FranchisePayableFilter filter) throws DaoException {
        return (Long) selectObject(filter, "FranchisePayableSCShipment.getShipmentDetailsRecordCount");
    }
}
