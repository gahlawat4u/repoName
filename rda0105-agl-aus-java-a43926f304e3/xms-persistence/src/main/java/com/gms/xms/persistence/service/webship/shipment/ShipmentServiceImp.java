package com.gms.xms.persistence.service.webship.shipment;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ScheduleCollectionDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.TrackingDao;
import com.gms.xms.persistence.dao.webship.OtherConnoteDao;
import com.gms.xms.persistence.dao.webship.TntConnoteDao;
import com.gms.xms.persistence.dao.webship.TollConnoteDao;
import com.gms.xms.persistence.dao.webship.TollIpecConnoteDao;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentInfoManifestVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ShipmentServiceImp implements IShipmentService {
    private static final Log logger = LogFactory.getLog(ShipmentServiceImp.class);

    @Override
    public ShipmentVo selectById(Long shipmentId) throws DaoException {
        ShipmentDao dao = new ShipmentDao();
        return dao.selectById(shipmentId);
    }

    @Override
    public List<PieceVo> selectPieceById(Long shipmentId) throws DaoException {
        PieceDao dao = new PieceDao();
        return dao.selectPieceById(shipmentId);
    }

    @Override
    public void update(Map<String, String> context, ShipmentVo shipmentVo) throws DaoException {
        ShipmentDao dao = new ShipmentDao();
        dao.update(context, shipmentVo);
    }

    @Override
    public void deleteShipments(Map<String, String> context, List<String> shipmentIds) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        ShipmentDao dao = new ShipmentDao(sessionClient);
        TntConnoteDao tntConnoteDao = new TntConnoteDao(sessionClient);
        OtherConnoteDao otherConnoteDao = new OtherConnoteDao(sessionClient);
        TollConnoteDao tollConnoteDao = new TollConnoteDao(sessionClient);
        TollIpecConnoteDao tollIpecConnoteDao = new TollIpecConnoteDao(sessionClient);
        TrackingDao trackingDao = new TrackingDao(sessionClient);
        long smId;
        try {
            // Start transaction
            sessionClient.startTransaction();
            for (String shipmentId : shipmentIds) {
                smId = Long.valueOf(shipmentId);
                // Delete TNT connote for the shipment.
                tntConnoteDao.deleteByShipmentId(context, smId);
                logger.info("Delete TNT Connote by shipment id = " + smId);
                // Delete Other connote for the shipment.
                otherConnoteDao.deleteByShipmentId(context, smId);
                logger.info("Delete Other Connote by shipment id = " + smId);
                // Delete Toll connote for the shipment.
                tollConnoteDao.deleteByShipmentId(context, smId);
                logger.info("Delete Toll Connote by shipment id = " + smId);
                // Delete Toll Ipec connote for the shipment.
                tollIpecConnoteDao.deleteByShipmentId(context, smId);
                logger.info("Delete Toll Ipec Connote by shipment id = " + smId);
                // Delete tracking info.
                trackingDao.deleteByShipmentId(context, smId);
                logger.info("Delete tracking info by shipment id = " + smId);
                // Delete shipment.
                dao.delete(context, smId);
                logger.info("Delete shipment by id = " + smId);
            }
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            logger.error(e);
            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public void deleteShipmentById(Map<String, String> context, Long shipmentId) throws DaoException {
        ShipmentDao dao = new ShipmentDao();
        dao.delete(context, shipmentId);
    }

    @Override
    public void voidShipment(Map<String, String> context, ScheduleCollectionVo schedule, ShipmentVo shipment) throws DaoException {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        ShipmentDao dao = new ShipmentDao(sessionClient);
        ScheduleCollectionDao scheduleDao = new ScheduleCollectionDao(sessionClient);
        try {
            if (schedule != null) {
                Date currentDate = new Date();
                schedule.setCreateDate(currentDate);
                schedule.setStatus((byte) 0);
                scheduleDao.cancelScheduleCollectionById(context, schedule);
            }
            Date currentDate = new Date();
            shipment.setCreateDate(currentDate);
            shipment.setStatus(1);
            // Put signal to the context to log update shipment as void.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Void");
            dao.update(context, shipment);
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Log database action error
            logger.error(e);

            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }

    }

    @Override
    public ShipmentInfoManifestVo getShipmentsInfoManifest(Long shipmentId) throws DaoException {
        ShipmentDao shipmentDao = new ShipmentDao();
        Long customerCode = shipmentDao.selectCustomerCodeByShipmentId(shipmentId);
        if (getIsFranchiseFromCusCode(customerCode)) {
            return shipmentDao.getShipmentInfoFranchiseCodeByShipmentId(shipmentId);
        }else
        {
            return shipmentDao.getShipmentInfoCustomerCodeByShipmentId(shipmentId);
        }
    }

    private Boolean getIsFranchiseFromCusCode(Long cuscode) {
        String code = String.valueOf(cuscode);
        return code.length() > 3 && code.substring(3, code.length()).equalsIgnoreCase("00001");
    }
}
