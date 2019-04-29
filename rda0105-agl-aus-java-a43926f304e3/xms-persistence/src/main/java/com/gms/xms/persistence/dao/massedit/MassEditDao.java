package com.gms.xms.persistence.dao.massedit;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.massedit.MassAccessorialVo;
import com.gms.xms.txndb.vo.massedit.ServiceLevelVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from Jul 15, 2016 4:05:27 PM
 * <p>
 * Author huynd
 */
public class MassEditDao extends BaseDao<Object> {
    public MassEditDao() {
        super();
    }

    public MassEditDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public void massEditChangeWeight(Map<String, String> context, ShipmentBillingVo shipmentBillingVo) throws DaoException {
        update(context, shipmentBillingVo, "MassEdit.massEditChangeWeight");
    }

    public CustomerAddressForMassEditVo selectCustomerAddress(ShipmentBillingFilter filter) throws DaoException {
        return (CustomerAddressForMassEditVo) selectObject(filter, "MassEdit.selectCustomerAddress");
    }

    public WebshipAddressForMassEditVo selectWebshipAddress(ShipmentBillingFilter filter) throws DaoException {
        return (WebshipAddressForMassEditVo) selectObject(filter, "MassEdit.selectWebshipAddress");
    }

    public void massEditChangeSenderAddress(Map<String, String> context, AddressVo addressVo) throws DaoException {
        update(context, addressVo, "MassEdit.massEditChangeSenderAddress");
    }

    public List<ServiceLevelVo> getServiceLevelByCarrier(Long carrierId) throws DaoException {
        return selectList(carrierId, "MassEdit.getServiceLevelByCarrier");
    }

    public List<MassAccessorialVo> selectAllAccessorials() throws DaoException {
        return selectList(null, "MassEdit.selectAllAccessorials");
    }

    public List<MassAccessorialVo> selectAccessorialsByCarrier(Long carrierId) throws DaoException {
        return selectList(carrierId, "MassEdit.selectAccessorialsByCarrier");
    }
}
