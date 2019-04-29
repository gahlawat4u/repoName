package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.ServiceAddConFilter;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;

import java.util.List;

/**
 * Posted from ServiceAdditionalConfigDao
 * <p>
 * Author HungNT Date Aug 27, 2015
 */
public class ServiceAddConDao extends BaseDao<ServiceAddConVo> {
    public List<ServiceAddConVo> selectByServiceIdAndShipmentTypeId(ServiceAddConFilter filter) throws DaoException {
        return selectList(filter, "ServiceAddCon.selectByServiceIdAndShipmentTypeId");
    }
}
