package com.gms.xms.persistence.dao.franchisepayable;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.FranchiseServiceMarkupFilter;
import com.gms.xms.filter.account.franchises.ManageFranchiseFilter;
import com.gms.xms.filter.account.franchises.MarkupRateFilter;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.*;

import java.util.List;
import java.util.Map;

public class FranchiseDao extends BaseDao<Object> {
    public FranchiseDao() {
        super();
    }

    public FranchiseDao(SqlSessionClient sessionClient) {
        super(sessionClient);
    }

    public FranchiseInfoVo getFranchiseInfoByCode(String code) throws DaoException {
        return (FranchiseInfoVo) select(code, "Franchise.selectByCode");
    }

    public FranchiseVo selectByFranchiseCode(String franchiseCode) throws DaoException {
        return (FranchiseVo) select(franchiseCode, "Franchise.selectByFranchiseCode");
    }

    public List<FranchiseInfoVo> getFranchiseListManagedByUser(UserVo user) throws DaoException {
        return selectList(user, "Franchise.selectByUser");
    }

    public FranchiseInfoVo getParentFranchiseByCode(String code) throws DaoException {
        return (FranchiseInfoVo) select(code, "Franchise.selectParentByCode");
    }

    public FranchiseInfoVo getFranchiseByUserId(String userId) throws DaoException {
        return (FranchiseInfoVo) select(userId, "Franchise.selectByUserId");
    }

    public long getCount() throws DaoException {
        return (long) selectObject(null, "Franchise.getCount");
    }

    public Float selectMinimumBaseChargeByFranchiseCode(Long franchiseCode) throws DaoException {
        return (Float) selectObject(franchiseCode, "Franchise.selectMinimumBaseChargeByFranchiseCode");
    }

    public FranchiseVo selectFranchiseByFranchiseCode(String franchiseCode) throws DaoException {
        return (FranchiseVo) selectObject(franchiseCode, "Franchise.selectFranchiseByFranchiseCode");
    }

    public FranchiseVo selectFranchiseByFranchiseCodeExt(String franchiseCode) throws DaoException {
        return (FranchiseVo) selectObject(franchiseCode, "Franchise.selectFranchiseByFranchiseCodeExt");
    }

    public FranchiseVo selectFranchiseByFilter(ManageFranchiseFilter filter) throws DaoException {
        return (FranchiseVo) selectObject(filter, "Franchise.selectFranchiseByFilter");
    }

    public Long selectSaleRepByCustomerCode(String customerCode) throws DaoException {
        return (Long) selectObject(customerCode, "Franchise.selectSaleRepByCustomerCode");
    }

    public Integer checkAglWarranty(Long franchiseCode) throws DaoException {
        return (Integer) selectObject(franchiseCode, "Franchise.checkAglWarranty");
    }

    public MarkupRateVo selectMarkupRateByCustomerCode(MarkupRateFilter filter) throws DaoException {
        return (MarkupRateVo) selectObject(filter, "Franchise.selectMarkupRateByCustomerCode");
    }

    public FranchiseServiceMarkupVo getFranchiseServiceMarkup(FranchiseServiceMarkupFilter filter) throws DaoException {
        return (FranchiseServiceMarkupVo) selectObject(filter, "Franchise.getFranchiseServiceMarkup");
    }

    public void update(Map<String, String> context, FranchiseVo franchise) throws DaoException {
        this.update(context, franchise, "Franchise.update");
    }

    public void updateActiveDateByFranchiseCode(Map<String, String> context, FranchiseVo franchise) throws DaoException {
        this.update(context, franchise, "Franchise.updateActiveDateByFranchiseCode");
    }

    public void insert(Map<String, String> context, FranchiseVo franchise) throws DaoException {
        this.insert(context, franchise, "Franchise.insert");
    }

    public Long getUserIdByFranchiseCode(String franchiseCode) throws DaoException {
        return (Long) selectObject(franchiseCode, "Franchise.getUserIdByFranchiseCode");
    }
    
    public void updateFranchiseProfileImage(Map<String, String> context,FranchiseVo franchise) throws DaoException{
		this.update(context, franchise, "Franchise.updateFranchiseProfileImage");
	}
}