package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.SessionInfoVo;

import java.util.Map;

public class SessionInfoDao extends BaseDao<SessionInfoVo> {

    public SessionInfoDao() {
        super();
    }

    public SessionInfoDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public SessionInfoVo getBySessionId(String code) throws DaoException {
        return select(code, "SessionInfo.getBySessionId");
    }

    public Integer insert(Map<String, String> context) throws DaoException {
        return insert(context, null, "SessionInfo.insert");
    }
}
