package com.gms.xms.persistence.dao.admin.customer.baserate;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.customer.baserate.*;

import java.util.List;

/**
 * Posted from Apr 5, 2016 10:08:18 AM
 * <p>
 * Author dattrinh
 */
public class ManageCustBaseRateDao extends BaseDao<Object> {
    public List<ServiceTypeVo> getServiceTypeByCarrier(Long serviceId) throws DaoException {
        return this.selectList(serviceId, "ManageCustBaseRate.getServiceTypeByCarrier");
    }

    public ServiceTypeVo getServiceTypeByShipmentTypeId(ServiceTypeVo serviceTypeVo) throws DaoException {
        return (ServiceTypeVo) this.select(serviceTypeVo, "ManageCustBaseRate.getServiceTypeByShipmentTypeId");
    }

    public List<OtherCustBaseRateVo> getOtherBaseRateByCustomerCode(String customerCode) throws Exception {
        return this.selectList(customerCode, "ManageCustBaseRate.getOtherBaseRateByCustomerCode");
    }

    public List<CustBaseRateVo> getCustBaseRateByFilter(CustBaseRateVo baseRateVo) throws DaoException {
        return this.selectList(baseRateVo, "ManageCustBaseRate.getCustBaseRateByFilter");
    }

    public List<CustBaseRateDetailVo> getCustBaseRateDetailByFilter(CustBaseRateDetailByFilter filter) throws DaoException {
        return this.selectList(filter, "ManageCustBaseRate.getCustBaseRateDetailByFilter");
    }

    public List<String> getZonesBySheetId(Long sheetId) throws DaoException {
        return this.selectList(sheetId, "ManageCustBaseRate.getZonesBySheetId");
    }
}
