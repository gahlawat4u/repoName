package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.AdministrationFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.administration.PermissionVo;

import java.util.List;

/**
 * Posted from PermissionDao
 * <p>
 * Author TANDT
 */
public class PermissionDao extends BaseDao<PermissionVo> {

    public List<PermissionVo> selectPermissionAll(AdministrationFilter filter) throws DaoException {
        return this.selectList(filter, "Permission.selectPermissionAll");
    }

    public Long selectPermissionAllCount(AdministrationFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Permission.selectPermissionAllCount");
    }
}
