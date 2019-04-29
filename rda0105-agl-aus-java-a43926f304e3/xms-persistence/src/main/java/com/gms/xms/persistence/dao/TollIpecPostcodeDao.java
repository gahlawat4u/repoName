package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.TollIpecPostcodeVo;
import com.gms.xms.txndb.vo.webship.TollIpecZoneFilter;

import java.util.List;

/**
 * @author tkvcl
 */
public class TollIpecPostcodeDao extends BaseDao<TollIpecPostcodeVo> {

    public List<TollIpecPostcodeVo> selectForRemoteArea(TollIpecZoneFilter filter) throws DaoException {
        return selectList(filter, "TollIpecPostcode.selectForRemoteArea");
    }

}
