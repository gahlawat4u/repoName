package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.UserVo;

import java.util.List;

/**
 * Posted from Aug 22, 2016 5:03:44 PM
 * <p>
 * Author dattrinh
 */
public class ProspectDao extends BaseDao<Object> {
    public List<String> getProspectByUser(UserVo userVo) throws DaoException {
        Integer level = null;
        if (userVo != null && userVo.getUserLevelId() != null) {
            level = userVo.getUserLevelId();
        }
        if (level == null || (level > 8 && level != 9 && level != 11)) {
            return null;
        }
        return selectList(userVo, "Prospect.getProspectByUser");
    }
}