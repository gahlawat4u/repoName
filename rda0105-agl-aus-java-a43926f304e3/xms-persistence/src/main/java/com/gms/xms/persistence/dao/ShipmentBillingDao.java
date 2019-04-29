package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.CheckDuplicateAirbillFilter;
import com.gms.xms.txndb.vo.CheckShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentBillingFilter;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.airbill.ShipmentBillingInfoVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ShipmentBillingDao
 * <p>
 * Author TanDT Date May 28, 2015
 */
public class ShipmentBillingDao extends BaseDao<Object> {
    public ShipmentBillingDao() {
        super();
    }

    public ShipmentBillingDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<ShipmentBillingVo> selectSurchargesByFilter(ShipmentBillingFilter filter) throws DaoException {
        return selectList(filter, "ShipmentBilling.selectSurchargesByFilter");
    }

    public void updateForAcceptSubmit(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        update(context, shipmentBillingVo, "ShipmentBilling.updateForAcceptSubmit");
    }

    public void updateShipmentBillingForViewEdit(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        update(context, shipmentBillingVo, "ShipmentBilling.updateShipmentBillingForViewEdit");
    }

    public void updateShipmentBilling(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        update(context, shipmentBillingVo, "ShipmentBilling.updateShipmentBilling");
    }

    public void insertShipmentBillingSurcharge(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        insert(context, shipmentBillingVo, "ShipmentBilling.insertShipmentBillingSurcharge");
    }

    public void deleteShipmentBillingSurcharge(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        delete(context, shipmentBillingVo, "ShipmentBilling.deleteShipmentBillingSurcharge");
    }

    public ShipmentBillingVo selectIsBaseChargeByFilter(ShipmentBillingFilter shipmentBillingFilter) throws DaoException {
        return (ShipmentBillingVo) select(shipmentBillingFilter, "ShipmentBilling.selectIsBaseChargeByFilter");
    }

    public List<ShipmentBillingVo> selectByFilter(ShipmentBillingFilter shipmentBillingFilter) throws DaoException {
        return selectList(shipmentBillingFilter, "ShipmentBilling.selectByFilter");
    }

    public void deleteShipment(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        delete(context, shipmentBillingVo, "ShipmentBilling.deleteShipment");
    }

    public List<ShipmentBillingVo> selectImportDateForSearchAirbill() throws DaoException {
        return selectList(null, "ShipmentBilling.selectImportDateForSearchAirbill");
    }

    public List<ShipmentBillingVo> selectByAirbillNumber(ShipmentBillingFilter filter) throws DaoException {
        return selectList(filter, "ShipmentBilling.selectByAirbillNumber");
    }

    public int isAglWarrantyAirbill(ShipmentBillingVo shipmentBillingVo) throws DaoException {
        return (int) selectObject(shipmentBillingVo, "ShipmentBilling.isAglWarrantyAirbill");
    }

    public Double getAglWarrantyAmount(ShipmentBillingVo shipmentBillingVo) throws DaoException {
        return (Double) selectObject(shipmentBillingVo, "ShipmentBilling.getAglWarrantyAmount");
    }

    public ShipmentBillingInfoVo getShipmentBillingInfoByCode(ShipmentBillingVo shipmentBillingVo) throws DaoException {
        return (ShipmentBillingInfoVo) this.select(shipmentBillingVo, "ShipmentBilling.getShipmentBillingInfoByCode");
    }

    public ShipmentBillingVo getErrorShipmentBillingByAWB(String airbillNumber) throws DaoException {
        return (ShipmentBillingVo) this.select(airbillNumber, "ShipmentBilling.getErrorShipmentBillingByAWB");
    }

    public List<ShipmentBillingVo> getErrorShipmentBillingByAWBList(String airbillList) throws DaoException {
        return this.selectList(airbillList, "ShipmentBilling.getErrorShipmentBillingByAWBList");
    }

    public List<ShipmentBillingVo> getErrorShipmentBillingChargesByAWB(String airbillNumber) throws DaoException {
        return this.selectList(airbillNumber, "ShipmentBilling.getErrorShipmentBillingChargesByAWB");
    }

    public String checkShipmentBilling(CheckShipmentBillingVo shipmentBillingVo) throws DaoException {
        return (String) selectObject(shipmentBillingVo, "ShipmentBilling.checkShipmentBilling");
    }

    public String checkShipmentBillingDHL(CheckShipmentBillingVo shipmentBillingVo) throws DaoException {
        return (String) selectObject(shipmentBillingVo, "ShipmentBilling.checkShipmentBillingDHL");
    }

    public ShipmentBillingVo checkDuplicateShipmentBilling(ShipmentBillingVo shipmentBillingVo) throws DaoException {
        return (ShipmentBillingVo) select(shipmentBillingVo, "ShipmentBilling.checkDuplicateShipmentBilling");
    }

    public void updateIdAndDescriptionForErrorAirbill(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        update(context, shipmentBillingVo, "ShipmentBilling.updateIdAndDescriptionForErrorAirbill");
    }

    public void updateCharges(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        update(context, shipmentBillingVo, "ShipmentBilling.updateCharges");
    }

    public List<ShipmentBillingVo> getShipmentBillingSurcharges(ShipmentBillingVo shipmentBillingVo) throws DaoException {
        return selectList(shipmentBillingVo, "ShipmentBilling.getShipmentBillingSurcharges");
    }

    public List<ShipmentBillingVo> checkForDeleteBilling(CheckDuplicateAirbillFilter checkDuplicateAirbillFilter) throws DaoException {
        return selectList(checkDuplicateAirbillFilter, "ShipmentBilling.checkForDeleteBilling");
    }

    public Integer selectMaxAccessorialCount(ShipmentBillingFilter shipmentBillingFilter) throws DaoException {
        return (Integer) selectObject(shipmentBillingFilter, "ShipmentBilling.selectMaxAccessorialCount");
    }

    public void deleteWarrantyService(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        delete(context, shipmentBillingVo, "ShipmentBilling.deleteWarrantyService");
    }

    public void deleteAirbillError(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        delete(context, shipmentBillingVo, "ShipmentBilling.deleteAirbillError");
    }
}
