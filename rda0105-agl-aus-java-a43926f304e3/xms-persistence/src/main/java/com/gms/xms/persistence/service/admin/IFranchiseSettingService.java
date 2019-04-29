package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.FranchiseSettingListExtFilter;
import com.gms.xms.txndb.vo.admin.FranchiseSettingListExtVo;

import java.util.List;

/**
 * Posted from IAdminEmailService
 * <p>
 * Author TANDT
 */
public interface IFranchiseSettingService {
    public List<FranchiseSettingListExtVo> selectListFranchiseByCode(FranchiseSettingListExtFilter filter) throws DaoException;

    public Integer selectListFranchiseByCodeCount(FranchiseSettingListExtFilter filter) throws DaoException;
}
