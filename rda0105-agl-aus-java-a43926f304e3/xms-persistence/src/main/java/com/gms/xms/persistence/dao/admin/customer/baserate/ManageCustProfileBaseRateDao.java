package com.gms.xms.persistence.dao.admin.customer.baserate;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.customerprofile.baserate.*;

import java.util.List;

/**
 * Posted from ManageCustProfileBaseRateDao
 * <p>
 * Author @author HungNT Apr 8, 2016
 */
public class ManageCustProfileBaseRateDao extends BaseDao<Object> {
    public List<CustProfileServiceTypeVo> getServiceTypeByCarrier(Long serviceId) throws DaoException {
        return this.selectList(serviceId, "ManageCustProfileBaseRate.getServiceTypeByCarrier");
    }

    public List<OtherCustProfileBaseRateVo> getOtherBaseRateByProfileId(Long profileId) throws Exception {
        return this.selectList(profileId, "ManageCustProfileBaseRate.getOtherBaseRateByProfileId");
    }

    public List<CustProfileBaseRateVo> getCustProfileBaseRateByFilter(CustProfileBaseRateVo baseRateVo) throws DaoException {
        return this.selectList(baseRateVo, "ManageCustProfileBaseRate.getCustProfileBaseRateByFilter");
    }

    public List<CustProfileBaseRateDetailVo> getCustProfileBaseRateDetailByFilter(CustProfileBaseRateDetailByFilter filter) throws DaoException {
        return this.selectList(filter, "ManageCustProfileBaseRate.getCustProfileBaseRateDetailByFilter");
    }

    public List<String> getZonesBySheetId(Long sheetId) throws DaoException {
        return this.selectList(sheetId, "ManageCustProfileBaseRate.getZonesBySheetId");
    }
}
