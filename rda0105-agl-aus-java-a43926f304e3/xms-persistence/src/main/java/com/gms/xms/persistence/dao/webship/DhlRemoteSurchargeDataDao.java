package com.gms.xms.persistence.dao.webship;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.DhlRemoteSurchargeDataFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.webship.DhlRemoteSurchargeDataVo;

import java.util.List;

public class DhlRemoteSurchargeDataDao extends BaseDao<DhlRemoteSurchargeDataVo> {
    public List<DhlRemoteSurchargeDataVo> getDhlRemoteSurchargeData(DhlRemoteSurchargeDataFilter filter) throws DaoException {
        return selectList(filter, "DhlRemoteSurchargeData.selectDhlRemoteSurchargeData");
    }
}
