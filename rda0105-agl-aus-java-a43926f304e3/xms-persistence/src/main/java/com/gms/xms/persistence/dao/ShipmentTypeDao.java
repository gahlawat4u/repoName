package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from ShipmentTypeDaoService
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class ShipmentTypeDao extends BaseDao<ShipmentTypeVo> {
    public ShipmentTypeDao() {
        super();
    }

    public ShipmentTypeDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * Gets list of all shipment types
     *
     * @return List<{@link ShipmentTypeVo}>
     * @throws DaoException
     */
    public List<ShipmentTypeVo> selectAll() throws DaoException {
        return selectList(new ShipmentTypeVo(), "ShipmentType.selectAll");
    }

    /**
     * Gets list of shipment type by service id
     *
     * @param serviceId
     * @return List<{@link ShipmentTypeVo}>
     * @throws DaoException
     */
    public List<ShipmentTypeVo> selectShowServiceByServiceId(Integer serviceId) throws DaoException {
        return selectList(serviceId, "ShipmentType.selectShowServiceByServiceId");
    }

    /**
     * Get shipment type by shipment type id
     *
     * @param {@link Integer} shipmentTypeId
     * @return {@link ShipmentTypeVo} shipment type
     * @throws DaoException
     */
    public ShipmentTypeVo selectById(Integer shipmentTypeId) throws DaoException {
        return select(shipmentTypeId, "ShipmentType.selectById");
    }

    /**
     * Get shipment type list by customer code and webship id
     *
     * @param {@link ShipmentTypeFilter} shipmentTypeFilter
     * @return list of {@link ShipmentTypeVo}
     * @throws DaoException
     */
    public List<ShipmentTypeVo> selectByCustomerCode(String customerCode) throws DaoException {
        return selectList(customerCode, "ShipmentType.selectByCustomerCode");
    }

    public ShipmentTypeVo selectByServiceType(Integer shipmentTypeId) throws DaoException {
        return select(shipmentTypeId, "ShipmentType.selectByServiceType");
    }

    public List<ShipmentTypeVo> selectByServiceId(ShipmentTypeFilter filter) throws DaoException {
        return selectList(filter, "ShipmentType.selectByServiceId");
    }

    public Integer selectCountByServiceId(Integer serviceId) throws DaoException {
        return (Integer) selectObject(serviceId, "ShipmentType.selectCountByServiceId");
    }

    public List<ShipmentTypeVo> checkDuplicateShipmentType(ShipmentTypeFilter filter) throws DaoException {
        return selectList(filter, "ShipmentType.checkDuplicateShipmentType");
    }

    public Integer selectTotalUsedRecord(Integer shipmentTypeId) throws DaoException {
        return (Integer) selectObject(shipmentTypeId, "ShipmentType.selectTotalUsedRecord");
    }

    public void insertShipmentType(Map<String, String> context, ShipmentTypeVo shipmentTypeVo) throws DaoException {
        insert(context, shipmentTypeVo, "ShipmentType.insertShipmentType");
    }

    public void updateShipmentType(Map<String, String> context, ShipmentTypeVo shipmentTypeVo) throws DaoException {
        update(context, shipmentTypeVo, "ShipmentType.updateShipmentType");
    }

    public void deleteShipmentType(Map<String, String> context, Integer shipmentTypeId) throws DaoException {
        delete(context, shipmentTypeId, "ShipmentType.deleteShipmentType");
    }

    public ShipmentTypeVo selectByServiceTypeExt(Integer shipmentTypeId) throws DaoException {
        return select(shipmentTypeId, "ShipmentType.selectByServiceTypeExt");
    }

    public List<ShipmentTypeVo> getServicesByCarrier(Integer carrierId) throws DaoException {
        return this.selectList(carrierId, "ShipmentType.getServicesByCarrier");
    }
   

    public List<ShipmentTypeVo> selectByServiceIdRename(Integer serviceId) throws DaoException {
        return this.selectList(serviceId, "ShipmentType.selectByServiceIdRename");
    }

    public ShipmentTypeVo selectByServiceTypeUsingServiceGroup(Integer serviceId) throws DaoException {
        return this.select(serviceId, "ShipmentType.selectByServiceTypeUsingServiceGroup");
    }

    public ShipmentTypeVo selectByServiceGroupAndServiceId(ShipmentTypeFilter filter) throws DaoException {
        return this.select(filter, "ShipmentType.selectByServiceGroupAndServiceId");
    }

    public ShipmentTypeVo selectByShipmentTypeName(String shipmentTypeName) throws DaoException {
        return select(shipmentTypeName, "ShipmentType.selectByShipmentTypeName");
    }

    public ShipmentTypeVo selectByShipmentTypeNameAndCarrier(ShipmentTypeFilter shipmentTypeFilter) throws DaoException {
        return select(shipmentTypeFilter, "ShipmentType.selectByShipmentTypeNameAndCarrier");
    }

    public ShipmentTypeVo selectByServiceCodeAndCarrier(ShipmentTypeFilter shipmentTypeFilter) throws DaoException {
        return select(shipmentTypeFilter, "ShipmentType.selectByServiceCodeAndCarrier");
    }

    public ShipmentTypeVo selectByServiceCodeAndCarrierTollIpec(ShipmentTypeFilter shipmentTypeFilter) throws DaoException {
        return select(shipmentTypeFilter, "ShipmentType.selectByServiceCodeAndCarrierTollIpec");
    }

    public List<ShipmentTypeVo> selectByServiceCodeAndCarrierStartrack(ShipmentTypeFilter shipmentTypeFilter) throws DaoException {
        return this.selectList(shipmentTypeFilter, "ShipmentType.selectByServiceCodeAndCarrierStartrack");
    }

    public ShipmentTypeVo selectByEdiDescription(ShipmentTypeFilter shipmentTypeFilter) throws DaoException {
        return select(shipmentTypeFilter, "ShipmentType.selectByEdiDescription");
    }

    public List<ShipmentTypeVo> selectShowServiceWebshipByFilter(ShipmentTypeFilter shipmentTypeFilter) throws DaoException {
        return selectList(shipmentTypeFilter, "ShipmentType.selectShowServiceWebshipByFilter");
    }

    public ShipmentTypeVo getShipmentTypeByCode(ShipmentTypeVo filter) throws DaoException {
        return select(filter, "ShipmentType.getShipmentTypeByCode");
    }
}