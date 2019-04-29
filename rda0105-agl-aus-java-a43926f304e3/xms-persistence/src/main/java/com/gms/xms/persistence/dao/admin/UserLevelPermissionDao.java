package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.administration.UserLevelPermissionVo;

import java.util.Map;

/**
 * Posted from PermissionDao
 * <p>
 * Author TANDT
 */
public class UserLevelPermissionDao extends BaseDao<UserLevelPermissionVo> {

    public void deleteUserLevelPermission(Map<String, String> context, UserLevelPermissionVo vo) throws DaoException {
        this.delete(context, vo, "UserLevelPermission.delete");
    }

    public void insertUserLevelPermission(Map<String, String> context, UserLevelPermissionVo vo) throws DaoException {
        this.insert(context, vo, "UserLevelPermission.insert");
    }
}
