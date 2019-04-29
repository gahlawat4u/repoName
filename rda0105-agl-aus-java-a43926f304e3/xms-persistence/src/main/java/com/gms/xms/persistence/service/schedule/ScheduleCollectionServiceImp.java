package com.gms.xms.persistence.service.schedule;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.AddressDao;
import com.gms.xms.persistence.dao.ScheduleCollectionDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from ScheduleCollectionServiceImp
 * <p>
 * Author dattrinh Jan 18, 2016 6:26:54 PM
 */
public class ScheduleCollectionServiceImp implements IScheduleCollectionService {
    private static final Log log = LogFactory.getLog(ScheduleCollectionServiceImp.class);
    private ScheduleCollectionDao scheduleCollectionDao = new ScheduleCollectionDao();

    @Override
    public ScheduleCollectionVo selectById(String scheduleCollectionId) throws DaoException {
        return scheduleCollectionDao.selectById(scheduleCollectionId);
    }

    @Override
    public ScheduleCollectionVo doSchedule(Map<String, String> context, AddressVo address, ScheduleCollectionVo scheduleCollection) throws DaoException {
        // Create local parameters.
        AddressVo insertAddress = address;
        ScheduleCollectionVo insertScheduleCollection = scheduleCollection;
        SqlSessionClient sessionClient = new SqlSessionClient();
        // Start a transaction.
        sessionClient.startTransaction();
        AddressDao addressDao = new AddressDao(sessionClient);
        ScheduleCollectionDao scheduleCollectionDao = new ScheduleCollectionDao(sessionClient);
        try {
            // Insert new address.
            // Put signal to the context to log insert address as pickup address.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Pickup Address");
            addressDao.insert(context, insertAddress);
            // Update address Id for the schedule collection.
            insertScheduleCollection.setAddressId(insertAddress.getAddressId());
            // Insert the schedule collection.
            // Put signal to the context to log insert schedule collection as Pickup Collection.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Pickup Collection");
            scheduleCollectionDao.insertScheduleCollection(context, insertScheduleCollection);
            // Commit transaction.
            sessionClient.endTransaction();
            return insertScheduleCollection;
        } catch (Exception e) {
            // Roll-back transaction.
            log.error(e);
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public ScheduleCollectionVo modifySchedule(Map<String, String> context, AddressVo address, ScheduleCollectionVo scheduleCollection) throws DaoException {
        // Create local parameters.
        AddressVo updateAddress = address;
        ScheduleCollectionVo updateScheduleCollection = scheduleCollection;
        SqlSessionClient sessionClient = new SqlSessionClient();
        // Start a transaction.
        sessionClient.startTransaction();
        AddressDao addressDao = new AddressDao(sessionClient);
        ScheduleCollectionDao scheduleCollectionDao = new ScheduleCollectionDao(sessionClient);
        try {
            // Insert new address.
            // Put signal to the context to log insert address as Modify Pickup Address.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Modify Pickup Address");
            addressDao.insert(context, updateAddress);
            // Update address Id for the schedule collection.
            updateScheduleCollection.setAddressId(updateAddress.getAddressId());
            // Update the schedule collection.
            // Put signal to the context to log update schedule collection as Modify Pickup Collection.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Modify Pickup Collection");
            scheduleCollectionDao.updateScheduleCollectionById(context, updateScheduleCollection);
            // Commit transaction.
            sessionClient.endTransaction();
            return updateScheduleCollection;
        } catch (Exception e) {
            // Roll-back transaction.
            log.error(e);
            sessionClient.rollback();
            throw e;
        }
    }

    @Override
    public ScheduleCollectionVo getScheduleCollectionForModify(Long shipmentId) throws DaoException {
        return scheduleCollectionDao.getScheduleCollectionForModify(shipmentId);
    }

    @Override
    public List<ScheduleCollectionVo> selectListScheduleCollectionBySmId(Long shipmentId) throws DaoException {
        return scheduleCollectionDao.selectListScheduleCollectionBySmId(shipmentId);
    }

    @Override
    public void cancelScheduleCollection(Map<String, String> context, ScheduleCollectionVo scheduleCollectionVo) throws DaoException {
        scheduleCollectionDao.cancelScheduleCollectionById(context, scheduleCollectionVo);
    }

    @Override
    public void cancelScheduleByShipmentId(Map<String, String> context, Long shipmentId) throws DaoException {
        scheduleCollectionDao.cancelScheduleByShipmentId(context, shipmentId);
    }
}