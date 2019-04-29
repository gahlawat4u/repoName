package com.gms.xms.persistence.service.webship.shipment;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentInfoManifestVo;
import com.gms.xms.txndb.vo.ShipmentVo;

import java.util.List;
import java.util.Map;

public interface IShipmentService {
    public ShipmentVo selectById(Long shipmentId) throws DaoException;

    public List<PieceVo> selectPieceById(Long shipmentId) throws DaoException;

    public void update(Map<String, String> context, ShipmentVo shipmentVo) throws DaoException;

    public void deleteShipments(Map<String, String> context, List<String> shipmentIds) throws DaoException;

    public void deleteShipmentById(Map<String, String> context, Long shipmentId) throws DaoException;

    public void voidShipment(Map<String, String> context, ScheduleCollectionVo schedule, ShipmentVo shipment) throws DaoException;

    ShipmentInfoManifestVo getShipmentsInfoManifest(Long shipmentId) throws DaoException;

}
