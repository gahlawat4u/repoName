package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.ServiceBaseRateFilter;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.webship.ServiceFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from ServiceDaoService
 * <p>
 * Author DatTV Date Mar 25, 2015
 */
public class ServiceDao extends BaseDao<ServiceVo> {
    public ServiceDao() {
        super();
    }

    public ServiceDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    /**
     * Gets list of services
     *
     * @return The list of services
     * @throws DaoException
     */
    public List<ServiceVo> selectAll() throws DaoException {
        return selectList(null, "Service.selectAll");
    }

    public List<ServiceVo> selectBySupply() throws DaoException {
        return selectList(null, "Service.selectBySupply");
    }

    /**
     * Gets list of services by webship id
     *
     * @param map
     * @return List<{@link ServiceVo}>
     * @throws DaoException
     */
    public List<ServiceVo> getServiceListByWebshipId(Map<String, Object> map) throws DaoException {
        return selectList(map, "Service.getServiceListByWebshipId");
    }

    /**
     * Gets list of services by customer code and carrier type
     *
     * @param map
     * @return List<{@link ServiceVo}>
     * @throws DaoException
     */
    public List<ServiceVo> getServiceListByFilter(ServiceFilter filter) throws DaoException {
        return selectList(filter, "Service.getServiceListByFilter");
    }

    /**
     * Get service by service id
     *
     * @param {@link Integer} serviceId
     * @return {@link ServiceVo} service
     * @throws DaoException
     */
    public ServiceVo selectById(Integer serviceId) throws DaoException {
        return select(serviceId, "Service.selectById");
    }

    public List<ServiceVo> getActiveServices(String activeCarrierList) throws DaoException {
        return selectList(activeCarrierList, "Service.getActiveServices");
    }

    public List<ServiceVo> getActiveServicesRe(CustomerProfileFilter filter) throws DaoException {
        return selectList(filter, "Service.getActiveServicesRe");
    }

    public List<ServiceVo> getNonCentralizedServices(String activeCarrierList) throws DaoException {
        return selectList(activeCarrierList, "Service.getNonCentralizedServices");
    }

    public List<ServiceVo> selectAllService(ServiceFilter filter) throws DaoException {
        return selectList(filter, "Service.selectAllService");
    }

    public Integer selectAllServiceCount() throws DaoException {
        return (Integer) selectObject(new ServiceVo(), "Service.selectAllServiceCount");
    }

    public ServiceVo selectByName(String serviceName) throws DaoException {
        return select(serviceName, "Service.selectByName");
    }

    public void insertService(Map<String, String> context, ServiceVo serviceVo) throws DaoException {
        insert(context, serviceVo, "Service.insertService");
    }

    public void updateService(Map<String, String> context, ServiceVo serviceVo) throws DaoException {
        update(context, serviceVo, "Service.updateService");
    }

    public void deleteService(Map<String, String> context, Integer serviceId) throws DaoException {
        delete(context, serviceId, "Service.deleteService");
    }

    public List<ServiceVo> getWebshipActiveServicesByCustCode(String customerCode) throws DaoException {
        return this.selectList(customerCode, "Service.getWebshipActiveServicesByCustCode");
    }

    public List<ServiceVo> getActiveServicesWithBaseRates(ServiceBaseRateFilter filter) throws DaoException {
        return this.selectList(filter, "Service.getActiveServicesWithBaseRates");
    }

    public List<ServiceVo> getNonActiveServicesWithBaseRates(ServiceBaseRateFilter filter) throws DaoException {
        return this.selectList(filter, "Service.getNonActiveServicesWithBaseRates");
    }

    public List<ServiceVo> selectAllServiceOther(ServiceFilter filter) throws DaoException {
        return this.selectList(filter, "Service.selectAllServiceOther");
    }

    public List<ServiceVo> getIntegratedServices() throws DaoException {
        return this.selectList(null, "Service.getIntegratedServices");
    }

    public ServiceVo selectAllByShipmentId(Long shipmentId) throws DaoException {
        return this.select(shipmentId, "Service.selectAllByShipmentId");
    }

    public List<ServiceVo> getIntegratedServiceWithShipmentTypes() throws DaoException {
        return this.selectList(null, "Service.getIntegratedServiceWithShipmentTypes");
    }
}
