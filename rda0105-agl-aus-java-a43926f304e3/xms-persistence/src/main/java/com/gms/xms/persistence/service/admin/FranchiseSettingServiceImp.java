package com.gms.xms.persistence.service.admin;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.FranchiseSettingListExtFilter;
import com.gms.xms.persistence.dao.admin.FranchiseSettingListExtDao;
import com.gms.xms.txndb.vo.admin.FranchiseSettingListExtVo;

import java.util.List;

/**
 * Posted from AdminEmailImp
 * <p>
 * Author TANDT
 */
public class FranchiseSettingServiceImp implements IFranchiseSettingService {

    @Override
    public List<FranchiseSettingListExtVo> selectListFranchiseByCode(FranchiseSettingListExtFilter filter) throws DaoException {
        FranchiseSettingListExtDao dao = new FranchiseSettingListExtDao();
        return dao.selectListFranchiseByCode(filter);
    }

    @Override
    public Integer selectListFranchiseByCodeCount(FranchiseSettingListExtFilter filter) throws DaoException {
        FranchiseSettingListExtDao dao = new FranchiseSettingListExtDao();
        return dao.selectListFranchiseByCodeCount(filter);
    }


}
