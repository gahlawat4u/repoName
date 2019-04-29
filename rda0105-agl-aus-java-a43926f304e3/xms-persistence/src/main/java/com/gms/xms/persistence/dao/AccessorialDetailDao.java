package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AccessorialDetailFilter;
import com.gms.xms.txndb.vo.AccessorialDetailUpdateVo;
import com.gms.xms.txndb.vo.AccessorialDetailVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AccessorialDetailDao
 * <p>
 * Author HungNT Date Sep 16, 2015
 */
public class AccessorialDetailDao extends BaseDao<AccessorialDetailVo> {
    public AccessorialDetailDao() {
        super();
    }

    public AccessorialDetailDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<AccessorialDetailVo> selectAllbyAccessorialId(AccessorialDetailFilter filter) throws DaoException {
        return selectList(filter, "AccessorialDetail.selectAllByAccessorialId");
    }

    public Integer selectCountByAccessorialId(Long accessorialId) throws DaoException {
        return (Integer) selectObject(accessorialId, "AccessorialDetail.selectCountByAccessorialId");
    }

    public AccessorialDetailVo selectByFilter(AccessorialDetailFilter filter) throws DaoException {
        return select(filter, "AccessorialDetail.selectByFilter");
    }

    public void updateAccessorialDetail(Map<String, String> context, AccessorialDetailUpdateVo accessorialDetailUpdateVo) throws DaoException {
        update(context, accessorialDetailUpdateVo, "AccessorialDetail.updateAccessorialDetail");
    }

    public void insertAccessorialDetail(Map<String, String> context, AccessorialDetailVo accessorialDetailVo) throws DaoException {
        insert(context, accessorialDetailVo, "AccessorialDetail.insertAccessorialDetail");
    }

    public void deleteAccessorialDetail(Map<String, String> context, AccessorialDetailFilter filter) throws DaoException {
        delete(context, filter, "AccessorialDetail.deleteAccessorialDetail");
    }
}
