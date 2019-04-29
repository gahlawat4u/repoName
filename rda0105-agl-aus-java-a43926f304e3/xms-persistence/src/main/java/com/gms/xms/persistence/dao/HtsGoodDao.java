package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.HtsGoodVo;

import java.util.List;

/**
 * Posted from HtsGoodDao
 * <p>
 * Author @author HungNT Jan 29, 2016
 */
public class HtsGoodDao extends BaseDao<HtsGoodVo> {
    public List<HtsGoodVo> getHtsGoodListByDescription(String description) throws DaoException {
        return selectList(description, "HtsGood.selectHtsGoodByDescription");
    }

    public HtsGoodVo getHtsGoodById(Integer htsGoodId) throws DaoException {
        return select(htsGoodId, "HtsGood.selectHtsGoodById");
    }

    public List<HtsGoodVo> selectHtsGoodByIdOrCode(String codeBinded) throws DaoException {
        return selectList(codeBinded, "HtsGood.selectHtsGoodByIdOrCode");
    }
}
