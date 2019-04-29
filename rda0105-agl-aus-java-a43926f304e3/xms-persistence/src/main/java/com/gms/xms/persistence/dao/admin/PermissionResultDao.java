package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdministrationFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.administration.PermissionResultVo;

import java.util.List;

/**
 * Posted from PermissionDao
 * <p>
 * Author TANDT
 */
public class PermissionResultDao extends BaseDao<PermissionResultVo> {

    public List<PermissionResultVo> selectPermissionAll(AdministrationFilter filter) throws DaoException {
        return this.selectList(filter, "Permission.selectPermissionAll");
    }

    public Long selectPermissionAllCount(AdministrationFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Permission.selectPermissionAllCount");
    }
}
