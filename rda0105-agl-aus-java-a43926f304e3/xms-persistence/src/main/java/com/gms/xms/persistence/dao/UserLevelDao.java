package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.UserLevelVo;

import java.util.List;

/**
 * Posted from UserLevelDao
 * <p>
 * Author DatTV Date May 22, 2015 1:49:24 PM
 */
public class UserLevelDao extends BaseDao<UserLevelVo> {
    public UserLevelVo selectById(Integer userLevelId) throws DaoException {
        return this.select(userLevelId, "UserLevel.selectById");
    }

    public List<UserLevelVo> selectForPermission() throws DaoException {
        return this.selectList(null, "UserLevel.selectForPermission");
    }

    public List<UserLevelVo> getUserLevelsByUserId(Long userId) throws DaoException {
        return this.selectList(userId, "UserLevel.getUserLevelsByUserId");
    }

    public List<UserLevelVo> selectForMenuEditor() throws DaoException {
        return this.selectList(null, "UserLevel.selectForMenuEditor");
    }
}
