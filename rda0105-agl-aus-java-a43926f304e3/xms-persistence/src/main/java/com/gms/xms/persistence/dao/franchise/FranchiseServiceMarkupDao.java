package com.gms.xms.persistence.dao.franchise;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.FranchiseServiceMarkupVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from FranchiseServiceMarkupDao
 * <p>
 * Author @author HungNT May 20, 2016
 */
public class FranchiseServiceMarkupDao extends BaseDao<FranchiseServiceMarkupVo> {
    public FranchiseServiceMarkupDao() {
        super();
    }

    public FranchiseServiceMarkupDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<FranchiseServiceMarkupVo> selectFranchiseServiceMarkupByFranchiseCode(Long franchiseCode) throws DaoException {
        return selectList(franchiseCode, "FranchiseServiceMarkup.selectServiceMarkupByFranchiseCode");
    }

    public FranchiseServiceMarkupVo selectFranchiseServiceMarkup(FranchiseServiceMarkupVo franchiseServiceMarkupVo) throws DaoException {
        return select(franchiseServiceMarkupVo, "FranchiseServiceMarkup.selectServiceMarkupByVo");
    }

    public void inserFranchiseServiceMarkup(Map<String, String> context, FranchiseServiceMarkupVo franchiseServiceMarkupVo) throws DaoException {
        insert(context, franchiseServiceMarkupVo, "FranchiseServiceMarkup.insertServiceMarkup");
    }

    public void updateFranchiseServiceMarkup(Map<String, String> context, FranchiseServiceMarkupVo franchiseServiceMarkupVo) throws DaoException {
        update(context, franchiseServiceMarkupVo, "FranchiseServiceMarkup.updateServiceMarkup");
    }
}
