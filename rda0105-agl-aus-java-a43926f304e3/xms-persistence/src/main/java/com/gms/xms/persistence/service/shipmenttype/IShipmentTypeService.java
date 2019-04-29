package com.gms.xms.persistence.service.shipmenttype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IShipmentTypeService
 * <p>
 * Author HungNT Date Jul 10, 2015
 */
public interface IShipmentTypeService {
    public List<ShipmentTypeVo> getShipmentTypeList() throws Exception;

    public ShipmentTypeVo getShipmentTypeByShipmentTypeId(Integer shipmentTypeId) throws Exception;

    public List<ShipmentTypeVo> getShipmentTypeByCustomerCode(String customerCode) throws Exception;

    public List<ShipmentTypeVo> getShowShipmentTypeListByServiceId(Integer serviceId) throws DaoException;

    public List<ShipmentTypeVo> getShowServiceWebshipByFilter(ShipmentTypeFilter shipmentTypeFilter) throws DaoException;

    public List<ShipmentTypeVo> getShipmentTypeListByServiceId(ShipmentTypeFilter filter) throws DaoException;

    public Integer getCountShipmentTypeByServiceId(Integer serviceId) throws DaoException;

    public Integer getTotalUsedRecords(Integer shipmentTypeId) throws DaoException;

    public void addShipmentType(Map<String, String> context, ShipmentTypeVo shipmentTypeVo) throws DaoException;

    public void updateShipmentType(Map<String, String> context, ShipmentTypeVo shipmentTypeVo) throws DaoException;

    public void deleteShipmentType(Map<String, String> context, Integer shipmentTypeId) throws DaoException;

    public List<ShipmentTypeVo> checkDuplicateShipmentType(ShipmentTypeFilter filter) throws DaoException;

    public ShipmentTypeVo selectByServiceTypeExt(Integer shipmentTypeId) throws DaoException;

    public List<ShipmentTypeVo> getServicesByCarrier(Integer carrierId) throws DaoException;
    
  
    
}
