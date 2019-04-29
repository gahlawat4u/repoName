package com.gms.xms.persistence.service.serviceadditionaloptions;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.ServiceAddConFilter;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;

import java.util.List;

/**
 * Posted from IServiceAdditionalConfigService
 * <p>
 * Author HungNT Date Aug 27, 2015
 */
public interface IServiceAdditionalOptionsService {

    public List<ServiceAddConVo> getOptionsByServiceIdAndShipmentTypeId(ServiceAddConFilter filter) throws DaoException;

}
