package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.UserHomeVo;

import java.util.Map;

public class UserHomeDao extends BaseDao<UserHomeVo> {
    public UserHomeVo selectByUserId(Long userId) throws DaoException {
        return select(userId, "UserHome.selectUserHomeByUserId");
    }

    public void insert(Map<String, String> context, UserHomeVo userHomeVo) throws DaoException {
        insert(context, userHomeVo, "UserHome.insert");
    }

    public void update(Map<String, String> context, UserHomeVo userHomeVo) throws DaoException {
        update(context, userHomeVo, "UserHome.update");
    }
}
