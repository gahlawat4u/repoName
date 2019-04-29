package com.gms.xms.persistence.dao.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.FranchiseSettingListExtFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.admin.FranchiseSettingListExtVo;

import java.util.List;

/**
 * Posted from AdminEmailDao
 * <p>
 * Author TANDT
 */
public class FranchiseSettingListExtDao extends BaseDao<FranchiseSettingListExtVo> {
    public List<FranchiseSettingListExtVo> selectListFranchiseByCode(FranchiseSettingListExtFilter filter) throws DaoException {
        return this.selectList(filter, "FranchiseSettingListExt.selectListFranchiseByCode");
    }

    public Integer selectListFranchiseByCodeCount(FranchiseSettingListExtFilter filter) throws DaoException {
        return (int) this.selectObject(filter, "FranchiseSettingListExt.selectListFranchiseByCodeCount");
    }
}
