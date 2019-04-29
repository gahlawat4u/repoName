package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.QuotePieceVo;

import java.util.Map;

/**
 * Posted from QuotePieceDao
 * <p>
 * Author TanDT Date May 14, 2015
 */
public class RaleSapeListUserDao extends BaseDao<QuotePieceVo> {

    /**
     * @return
     * @throws DaoException
     */

    public Integer insert(Map<String, String> context, QuotePieceVo quotePieceVo) throws DaoException {
        return insert(context, quotePieceVo, "QuotePiece.insert");
    }
}
