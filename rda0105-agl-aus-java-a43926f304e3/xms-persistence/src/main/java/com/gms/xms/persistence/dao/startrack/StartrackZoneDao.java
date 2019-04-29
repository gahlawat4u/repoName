package com.gms.xms.persistence.dao.startrack;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.startrack.StartrackZoneVo;

public class StartrackZoneDao extends BaseDao<Object> {
    public StartrackZoneDao() {
        super();
    }

    public StartrackZoneDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public StartrackZoneVo selectStartrackZone(StartrackZoneVo startrackZoneVo) throws DaoException {
        return (StartrackZoneVo) selectObject(startrackZoneVo, "Startrack.selectStartrackZone");
    }

}
