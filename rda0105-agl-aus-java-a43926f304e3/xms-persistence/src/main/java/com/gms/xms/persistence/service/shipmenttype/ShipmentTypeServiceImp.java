package com.gms.xms.persistence.service.shipmenttype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from ShipmentTypeServiceImp
 * <p>
 * Author HungNT Date Jul 10, 2015
 */
public class ShipmentTypeServiceImp implements IShipmentTypeService {
    private ShipmentTypeDao dao = new ShipmentTypeDao();
    private static final Log log = LogFactory.getLog(ShipmentTypeServiceImp.class);

    @Override
    public List<ShipmentTypeVo> getShipmentTypeList() throws Exception {
        List<ShipmentTypeVo> shipmentTypeVos = dao.selectAll();
        return shipmentTypeVos;
    }

    @Override
    public ShipmentTypeVo getShipmentTypeByShipmentTypeId(Integer shipmentTypeId) throws Exception {
        ShipmentTypeVo shipmentTypeVo = dao.selectById(shipmentTypeId);
        return shipmentTypeVo;
    }

    @Override
    public List<ShipmentTypeVo> getShipmentTypeByCustomerCode(String customerCode) throws Exception {
        List<ShipmentTypeVo> shipmentTypeVos = dao.selectByCustomerCode(customerCode);
        return shipmentTypeVos;
    }

    @Override
    public List<ShipmentTypeVo> getShowShipmentTypeListByServiceId(Integer serviceId) throws DaoException {
        List<ShipmentTypeVo> shipmentTypeVos = dao.selectShowServiceByServiceId(serviceId);
        return shipmentTypeVos;
    }

    @Override
    public List<ShipmentTypeVo> getShowServiceWebshipByFilter(ShipmentTypeFilter shipmentTypeFilter) throws DaoException {
        List<ShipmentTypeVo> shipmentTypeVos = dao.selectShowServiceWebshipByFilter(shipmentTypeFilter);
        return shipmentTypeVos;
    }

    @Override
    public List<ShipmentTypeVo> getShipmentTypeListByServiceId(ShipmentTypeFilter filter) throws DaoException {
        List<ShipmentTypeVo> shipmentTypeVos = dao.selectByServiceId(filter);
        return shipmentTypeVos;
    }

    @Override
    public Integer getCountShipmentTypeByServiceId(Integer serviceId) throws DaoException {
        return dao.selectCountByServiceId(serviceId);
    }

    @Override
    public List<ShipmentTypeVo> checkDuplicateShipmentType(ShipmentTypeFilter filter) throws DaoException {
        return dao.checkDuplicateShipmentType(filter);
    }

    @Override
    public Integer getTotalUsedRecords(Integer shipmentTypeId) throws DaoException {
        return dao.selectTotalUsedRecord(shipmentTypeId);
    }

    @Override
    public void addShipmentType(Map<String, String> context, ShipmentTypeVo shipmentTypeVo) throws DaoException {
        dao.insertShipmentType(context, shipmentTypeVo);
        log.info("ADD: " + GsonUtils.toGson(shipmentTypeVo));
    }

    @Override
    public void updateShipmentType(Map<String, String> context, ShipmentTypeVo shipmentTypeVo) throws DaoException {
        dao.updateShipmentType(context, shipmentTypeVo);
        log.info("UPDATE: " + GsonUtils.toGson(shipmentTypeVo));
    }

    @Override
    public void deleteShipmentType(Map<String, String> context, Integer shipmentTypeId) throws DaoException {
        dao.deleteShipmentType(context, shipmentTypeId);
        log.info("DELETE: shipmentTypeId=" + shipmentTypeId);
    }

    @Override
    public ShipmentTypeVo selectByServiceTypeExt(Integer shipmentTypeId) throws DaoException {
        ShipmentTypeVo shipmentTypeVo = dao.selectByServiceTypeExt(shipmentTypeId);
        return shipmentTypeVo;
    }

    @Override
    public List<ShipmentTypeVo> getServicesByCarrier(Integer carrierId) throws DaoException {
        return dao.getServicesByCarrier(carrierId);
    }
    
      
}