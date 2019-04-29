package com.gms.xms.persistence.dao.admin.edigenerate;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.dto.edigenerate.TntTransmissionParamVo;
import com.gms.xms.dto.edigenerate.TntTransmissionVo;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;

import java.util.Map;

/**
 * Posted from Sep 22, 2016 11:54:53 AM
 * <p>
 * Author dattrinh
 */
public class TntTransmissionDao extends BaseDao<Object> {
    public TntTransmissionDao() {
        super();
    }

    public TntTransmissionDao(SqlSessionClient sessionClient) {
        super(sessionClient);
    }

    public void insert(Map<String, String> context, TntTransmissionVo tntTransmissionVo) throws DaoException {
        insert(context, tntTransmissionVo, "TntTransmission.insert");
    }

    public TntTransmissionVo getTntTransmissionByParams(TntTransmissionParamVo paramVo) throws DaoException {
        return (TntTransmissionVo) select(paramVo, "TntTransmission.getTntTransmissionByParams");
    }

    public void updateTntTransmissionUpload(Map<String, String> context, TntTransmissionVo tntTransmissionVo) throws DaoException {
        update(context, tntTransmissionVo, "TntTransmission.updateTntTransmissionUpload");
    }
}
