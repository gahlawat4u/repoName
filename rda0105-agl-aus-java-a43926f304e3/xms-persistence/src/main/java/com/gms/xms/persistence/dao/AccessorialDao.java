package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.surchargelist.SurchargeListVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AccessorialDao
 * <p>
 * Author HungNT Date Apr 24, 2015
 */
public class AccessorialDao extends BaseDao<AccessorialVo> {
    public AccessorialDao() {
        super();
    }

    public AccessorialDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<AccessorialVo> selectSurChargeList(AccessorialFilter accessorialFilter) throws DaoException {
        return selectList(accessorialFilter, "Accessorial.selectSurChargeList");
    }

    public AccessorialVo getAccessorialValueByFilter(AccessorialFilter accessorialFilter) throws DaoException {
        return select(accessorialFilter, "Accessorial.getAccessorialValueByFilter");
    }

    public AccessorialVo getAccessorialValueByFilterForRecalculate(AccessorialFilter accessorialFilter) throws DaoException {
        return select(accessorialFilter, "Accessorial.getAccessorialValueByFilterForRecalculate");
    }


    public AccessorialVo selectById(Long accessorialId) throws DaoException {
        return select(accessorialId, "Accessorial.selectById");
    }

    public List<SurchargeListVo> selectAccessorialList(AccessorialFilter filter) throws DaoException {
        return selectList(filter, "Accessorial.selectAccessorialList");
    }

    public List<SurchargeListVo> selectAccessorialListByCarrier(AccessorialVo accessorialVo) throws DaoException {
        return selectList(accessorialVo, "Accessorial.selectAccessorialListByCarrier");
    }

    public Integer selectAccessorialListCount(AccessorialFilter filter) throws DaoException {
        return (Integer) selectObject(filter, "Accessorial.selectAccessorialListCount");
    }

    public AccessorialVo checkAccessorialByCode(AccessorialVo accessorialVo) throws DaoException {
        return (AccessorialVo) selectObject(accessorialVo, "Accessorial.checkAccessorialByCode");
    }

    public AccessorialVo checkAccessorialByDescription(AccessorialVo accessorialVo) throws DaoException {
        return (AccessorialVo) selectObject(accessorialVo, "Accessorial.checkAccessorialByDescription");
    }

    public Integer insertAccessorial(Map<String, String> context, AccessorialVo accessorialVo) throws DaoException {
        return insert(context, accessorialVo, "Accessorial.insertAccessorial");
    }

    public void updateAccessorial(Map<String, String> context, AccessorialVo accessorialVo) throws DaoException {
        update(context, accessorialVo, "Accessorial.updateAccessorial");
    }
}
