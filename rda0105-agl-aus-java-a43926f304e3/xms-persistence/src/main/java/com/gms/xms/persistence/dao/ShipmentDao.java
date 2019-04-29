package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TollManifestFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.*;

import java.util.List;
import java.util.Map;

/**
 * Posted from ShipmentDaoService
 * <p>
 * Author TanDT Date Mar 26, 2015
 */
public class ShipmentDao extends BaseDao<ShipmentVo> {
    public ShipmentDao() {
        super();
    }

    public ShipmentDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * @return
     * @throws DaoException
     */
    public List<ShipmentVo> getWebshipHistory(ShipmentFilter filter) throws DaoException {
        return selectList(filter, "Shipment_SelectWebshipHistory");
    }

    public void insertShipment(Map<String, String> context, ShipmentVo shipmentVo) throws DaoException {
        insert(context, shipmentVo, "ShipmentMapper.insertShipment");
    }

    public Integer getWebshipHistoryCount(ShipmentFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "Shipment_SelectWebshipHistoryCount");
    }

    public ShipmentVo selectById(Long shipmentId) throws DaoException {
        return select(shipmentId, "ShipmentMapper.selectById");
    }

    public String selectAwbBarcode(Long shipmentId) throws DaoException {
        return (String) selectObject(shipmentId, "ShipmentMapper.selectAwbBarcode");
    }

    public List<ShipmentVo> getShipmentForTracking(ShipmentForTrackingFilter trackingForTrackingFilter) throws DaoException {
        return selectList(trackingForTrackingFilter, "Shipment_SelectShipmentForTracking");
    }

    public ShipmentVo getShipmentInfoForTracking(Long shipmentId) throws DaoException {
        return select(shipmentId, "Shipment_SelectDetailWebshipHistory");
    }

    public ShipmentVo getShipmentScheduleCollection(Long shipmentId) throws DaoException {
        return select(shipmentId, "Shipment_SelectScheduleCollection");
    }

    public Long getCusCodeByShipmentId(Long shipmentId) throws DaoException {
        return (Long) selectObject(shipmentId, "Shipment_SelectCusCodeByShipment");
    }

    public ShipmentVo getShipmentInfoFranchiseByShipID(Long shipmentId) throws DaoException {
        return select(shipmentId, "Shipment_SelectShipmentInfoFranchiseByShipID");
    }

    public ShipmentVo getShipmentInfoCustomerByShipID(Long shipmentId) throws DaoException {
        return select(shipmentId, "Shipment_SelectShipmentInfoCustomerByShipID");
    }

    public ShipmentVo getShipmentForHistoryDetail(Long shipmentId) throws DaoException {
        return select(shipmentId, "Shipment_SelectShipmentForHistoryDetail");
    }

    public ShipmentVo getShipment_SelectDetailWebshipHistory(Long shipmentId) throws DaoException {
        return select(shipmentId, "Shipment_SelectDetailWebshipHistory");
    }

    public Integer checkEU() throws DaoException {
        return (int) selectObject(null, "ShipmentMapper.checkEU");
    }

    public void update(Map<String, String> context, ShipmentVo shipmentVo) throws DaoException {
        update(context, shipmentVo, "ShipmentMapper.update");
    }

    public void delete(Map<String, String> context, Long shipmentId) throws DaoException {
        this.delete(context, shipmentId, "ShipmentMapper.delete");
    }

    public String selectShipmentIdByAirbillNumber(String airbillNumber) throws DaoException {
        return (String) this.selectObject(airbillNumber, "ShipmentMapper.selectShipmentIdByAirbillNumber");
    }

    public CheckShipmentVo checkShipment(ShipmentBillingVo shipmentBillingVo) throws DaoException {
        return (CheckShipmentVo) selectObject(shipmentBillingVo, "ShipmentMapper.checkShipment");
    }

    public ShipmentVo selectByAirbillNumber(String airbillNumber) throws DaoException {
        return select(airbillNumber, "ShipmentMapper.selectByAirbillNumber");
    }

    public Double getWeightByShipmentId(Long shipmentId) throws DaoException {
        return (Double) selectObject(shipmentId, "ShipmentMapper.getWeightByShipmentId");
    }

    public ShipmentVo getShipmentInfoForETFile(Long shipmentId) throws DaoException {
        return (ShipmentVo) selectObject(shipmentId, "ShipmentMapper.getShipmentInfoForETFile");
    }

    public void updateTollManifestIdentifier(Map<String, String> context, TollManifestFilter tollManifestFilter) throws DaoException {
        update(context, tollManifestFilter, "ShipmentMapper.updateTollManifestIdentifier");
    }

    public Long selectCustomerCodeByShipmentId(Long shipmentId) throws DaoException {
        return (Long) this.selectObject(shipmentId, "ShipmentMapper.selectCustomerCodeByShipmentId");
    }

    public CustomerMinimumInfoManifestVo selectCustomerMinimumInfoByShipmentId(Long shipmentId) throws DaoException {
        return (CustomerMinimumInfoManifestVo) this.selectObject(shipmentId, "ShipmentMapper.selectCustomerMinimumInfoByShipmentId");
    }

    public ShipmentInfoManifestVo getShipmentInfoCustomerCodeByShipmentId(Long shipmentId) throws DaoException {
        return (ShipmentInfoManifestVo) this.selectObject(shipmentId, "ShipmentMapper.getShipmentsInfoCustomerCodeByShipmentId");
    }

    public ShipmentInfoManifestVo getShipmentInfoFranchiseCodeByShipmentId(Long shipmentId) throws DaoException {
        return (ShipmentInfoManifestVo) this.selectObject(shipmentId, "ShipmentMapper.getShipmentsInfoFranchiseCodeByShipmentId");
    }

}
