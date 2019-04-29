package com.gms.xms.persistence.service.accessorial;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.surchargelist.SurchargeListVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AccessorialServiceImp
 * <p>
 * Author HungNT Date Jul 27, 2015
 */
public class AccessorialServiceImp implements IAccessorialSerivce {
    private AccessorialDao dao = new AccessorialDao();

    @Override
    public List<AccessorialVo> getAccessorialListByFilter(AccessorialFilter accessorialFilter) throws DaoException {
        return dao.selectSurChargeList(accessorialFilter);
    }

    @Override
    public AccessorialVo getAccessorialByFilter(AccessorialFilter accessorialFilter) throws DaoException {
        return dao.getAccessorialValueByFilter(accessorialFilter);
    }

    @Override
    public AccessorialVo selectById(Long accessorialId) throws DaoException {
        return dao.selectById(accessorialId);
    }

    @Override
    public List<SurchargeListVo> getSurchargeList(AccessorialFilter filter) throws DaoException {
        return dao.selectAccessorialList(filter);
    }

    @Override
    public Integer getSurchargeListRecordCount(AccessorialFilter filter) throws DaoException {
        return dao.selectAccessorialListCount(filter);
    }

    @Override
    public void addAccessorial(Map<String, String> context, AccessorialVo accessorialVo) throws DaoException {
        dao.insertAccessorial(context, accessorialVo);
    }

    @Override
    public void updateAccessorial(Map<String, String> context, AccessorialVo accessorialVo) throws DaoException {
        dao.updateAccessorial(context, accessorialVo);
    }

    @Override
    public List<SurchargeListVo> selectAccessorialListByCarrier(AccessorialVo accessorialVo) throws DaoException {
        return dao.selectAccessorialListByCarrier(accessorialVo);
    }
}
