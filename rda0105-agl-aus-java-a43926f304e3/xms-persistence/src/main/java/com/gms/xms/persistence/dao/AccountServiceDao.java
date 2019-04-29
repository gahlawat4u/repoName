package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.txndb.vo.AccountServiceVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from AccountServiceDao
 * <p>
 * Author DatTV Sep 30, 2015
 */
public class AccountServiceDao extends BaseDao<AccountServiceVo> {
    public AccountServiceDao() {
        super();
    }

    public AccountServiceDao(SqlSessionClient sqlSessionClient) {
        super(sqlSessionClient);
    }

    public List<AccountServiceVo> selectActiveServiceByFranchiseCode(String franchiseCode) throws DaoException {
        return selectList(franchiseCode, "AccountService.selectActiveServiceByFranchiseCode");
    }

    public List<AccountServiceVo> selectActiveServiceByCustomerCode(String franchiseCode) throws DaoException {
        return selectList(franchiseCode, "AccountService.selectActiveServiceByCustomerCode");
    }

    public void insert(Map<String, String> context, AccountServiceVo accountServiceVo) throws DaoException {
        this.insert(context, accountServiceVo, "AccountService.insert");
    }

    public void deleteByCustCode(Map<String, String> context, Long customerCode) throws DaoException {
        this.delete(context, customerCode, "AccountService.deleteByCustCode");
    }
}