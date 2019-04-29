package com.gms.xms.persistence.service.serviceadditionaloptions;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.webship.ServiceAddConDao;
import com.gms.xms.txndb.vo.webship.ServiceAddConFilter;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;

import java.util.List;

/**
 * Posted from ServiceAdditionalConfigServiceImp
 * <p>
 * Author HungNT Date Aug 27, 2015
 */
public class ServiceAdditionalOptionsServiceImp implements IServiceAdditionalOptionsService {
    private ServiceAddConDao dao = new ServiceAddConDao();

    @Override
    public List<ServiceAddConVo> getOptionsByServiceIdAndShipmentTypeId(ServiceAddConFilter filter) throws DaoException {
        return dao.selectByServiceIdAndShipmentTypeId(filter);
    }
}
