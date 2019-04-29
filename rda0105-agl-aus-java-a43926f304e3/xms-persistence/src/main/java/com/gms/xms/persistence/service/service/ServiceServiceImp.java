package com.gms.xms.persistence.service.service;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.customers.manage.ServiceBaseRateFilter;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.webship.ServiceFilter;

import java.util.List;
import java.util.Map;

/**
 * Posted from ServiceServiceImp
 * <p>
 * Author HungNT Date Jul 11, 2015
 */
public class ServiceServiceImp implements IServiceService {
    private ServiceDao dao = new ServiceDao();

    @Override
    public ServiceVo selectById(Integer serviceId) throws Exception {
        ServiceVo serviceVo = dao.selectById(serviceId);
        return serviceVo;
    }

    @Override
    public List<ServiceVo> selectBySupply() throws DaoException {
        return dao.selectBySupply();
    }

    @Override
    public List<ServiceVo> getServiceListByFilter(ServiceFilter filter) throws DaoException {
        List<ServiceVo> serviceVos = dao.getServiceListByFilter(filter);
        return serviceVos;
    }

    @Override
    public List<ServiceVo> selectAll() throws DaoException {
        return dao.selectAll();
    }

    @Override
    public List<ServiceVo> getActiveServices() throws DaoException {
        String activeCarrierList = AppConstants.APP_SETTINGS.getActiveCarriers();
        return dao.getActiveServices(activeCarrierList);
    }

    @Override
    public List<ServiceVo> getAllService(ServiceFilter filter) throws DaoException {
        return dao.selectAllService(filter);
    }

    @Override
    public Integer getAllServiceCount() throws DaoException {
        return dao.selectAllServiceCount();
    }

    @Override
    public ServiceVo getServiceByName(String serviceName) throws DaoException {
        return dao.selectByName(serviceName);
    }

    @Override
    public void insertService(Map<String, String> context, ServiceVo serviceVo) throws DaoException {
        dao.insertService(context, serviceVo);
    }

    @Override
    public void updateService(Map<String, String> context, ServiceVo serviceVo) throws DaoException {
        dao.updateService(context, serviceVo);
    }

    @Override
    public void deleteService(Map<String, String> context, Integer serviceId) throws DaoException {
        dao.deleteService(context, serviceId);
    }

    @Override
    public List<ServiceVo> getNonCentralizedServices() throws DaoException {
        String activeCarrierList = AppConstants.APP_SETTINGS.getActiveCarriers();
        return dao.getNonCentralizedServices(activeCarrierList);
    }

    @Override
    public List<ServiceVo> getWebshipActiveServicesByCustCode(String customerCode) throws DaoException {
        return dao.getWebshipActiveServicesByCustCode(customerCode);
    }

    @Override
    public List<ServiceVo> getActiveServicesWithBaseRates(String customerCode) throws DaoException {
        String activeCarrierList = AppConstants.APP_SETTINGS.getActiveCarriers();
        ServiceBaseRateFilter filter = new ServiceBaseRateFilter();
        filter.setActiveCarrierList(activeCarrierList);
        filter.setCustomerCode(customerCode);
        return dao.getActiveServicesWithBaseRates(filter);
    }

    @Override
    public List<ServiceVo> getNonActiveServicesWithBaseRates(String customerCode) throws DaoException {
        String activeCarrierList = AppConstants.APP_SETTINGS.getActiveCarriers();
        ServiceBaseRateFilter filter = new ServiceBaseRateFilter();
        filter.setActiveCarrierList(activeCarrierList);
        filter.setCustomerCode(customerCode);
        return dao.getNonActiveServicesWithBaseRates(filter);
    }

    @Override
    public List<ServiceVo> selectAllServiceOther(ServiceFilter filter) throws DaoException {
        ServiceDao dao = new ServiceDao();
        return dao.selectAllServiceOther(filter);
    }

    @Override
    public List<ServiceVo> getActiveServicesRe(CustomerProfileFilter filter) throws DaoException {
        ServiceDao dao = new ServiceDao();
        return dao.getActiveServicesRe(filter);
    }

    @Override
    public List<ServiceVo> getIntegratedServices() throws DaoException {
        ServiceDao dao = new ServiceDao();
        return dao.getIntegratedServices();
    }

    @Override
    public List<ServiceVo> getIntegratedServiceWithShipmentTypes() throws DaoException {
        ServiceDao dao = new ServiceDao();
        return dao.getIntegratedServiceWithShipmentTypes();
    }

    @Override
    public ServiceVo selectAllByShipmentId(Long shipmentId) throws DaoException {
        ServiceDao dao = new ServiceDao();
        return dao.selectAllByShipmentId(shipmentId);
    }
}
