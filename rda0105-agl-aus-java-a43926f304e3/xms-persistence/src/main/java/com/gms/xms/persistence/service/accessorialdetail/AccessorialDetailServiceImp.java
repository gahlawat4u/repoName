package com.gms.xms.persistence.service.accessorialdetail;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.AccessorialDetailDao;
import com.gms.xms.txndb.vo.AccessorialDetailFilter;
import com.gms.xms.txndb.vo.AccessorialDetailUpdateVo;
import com.gms.xms.txndb.vo.AccessorialDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AccessorialDetailServiceImp
 * <p>
 * Author HungNT Date Sep 16, 2015
 */
public class AccessorialDetailServiceImp implements IAccessorialDetailService {
    private AccessorialDetailDao dao = new AccessorialDetailDao();

    @Override
    public List<AccessorialDetailVo> getListbyAccessorialId(AccessorialDetailFilter filter) throws DaoException {
        return dao.selectAllbyAccessorialId(filter);
    }

    @Override
    public Integer getRecordCountByAccessorialId(Long accessorialId) throws DaoException {
        return dao.selectCountByAccessorialId(accessorialId);
    }

    @Override
    public AccessorialDetailVo getAccessorialDetail(AccessorialDetailFilter filter) throws DaoException {
        return dao.selectByFilter(filter);
    }

    @Override
    public void updateAccessorialDetail(Map<String, String> context, AccessorialDetailUpdateVo accessorialDetailUpdateVo) throws DaoException {
        dao.updateAccessorialDetail(context, accessorialDetailUpdateVo);
    }

    @Override
    public void addAccessorialDetail(Map<String, String> context, AccessorialDetailVo accessorialDetailVo) throws DaoException {
        dao.insertAccessorialDetail(context, accessorialDetailVo);
    }

    @Override
    public void deleteAccessorialDetail(Map<String, String> context, AccessorialDetailFilter filter) throws DaoException {
        dao.deleteAccessorialDetail(context, filter);
    }
}
