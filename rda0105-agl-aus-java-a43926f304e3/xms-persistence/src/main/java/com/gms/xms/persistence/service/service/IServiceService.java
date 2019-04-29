package com.gms.xms.persistence.service.service;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.webship.ServiceFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from IServiceService
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public interface IServiceService {
    public ServiceVo selectById(Integer serviceId) throws Exception;

    public List<ServiceVo> selectBySupply() throws DaoException;

    public List<ServiceVo> getServiceListByFilter(ServiceFilter filter) throws DaoException;

    public List<ServiceVo> selectAll() throws DaoException;

    public List<ServiceVo> getActiveServices() throws DaoException;

    public List<ServiceVo> getActiveServicesRe(CustomerProfileFilter filter) throws DaoException;

    public List<ServiceVo> getAllService(ServiceFilter filter) throws DaoException;

    public Integer getAllServiceCount() throws DaoException;

    public void insertService(Map<String, String> context, ServiceVo serviceVo) throws DaoException;

    public void updateService(Map<String, String> context, ServiceVo serviceVo) throws DaoException;

    public void deleteService(Map<String, String> context, Integer serviceId) throws DaoException;

    public ServiceVo getServiceByName(String serviceName) throws DaoException;

    public List<ServiceVo> getNonCentralizedServices() throws DaoException;

    public List<ServiceVo> getWebshipActiveServicesByCustCode(String customerCode) throws DaoException;

    public List<ServiceVo> getActiveServicesWithBaseRates(String customerCode) throws DaoException;

    public List<ServiceVo> getNonActiveServicesWithBaseRates(String customerCode) throws DaoException;

    public List<ServiceVo> selectAllServiceOther(ServiceFilter filter) throws DaoException;

    public List<ServiceVo> getIntegratedServices() throws DaoException;

    public List<ServiceVo> getIntegratedServiceWithShipmentTypes() throws DaoException;

    public ServiceVo selectAllByShipmentId(Long shipmentId) throws DaoException;
}
