package com.gms.xms.persistence.dao;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.receivables.BankFilter;
import com.gms.xms.txndb.vo.BankVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from BankDao
 * <p>
 * Author DatTV Date Apr 7, 2015 3:48:53 PM
 */
public class BankDao extends BaseDao<BankVo> {

    /**
     * Gets list of banks
     *
     * @return List<{@link BankVo}>
     * @throws DaoException
     */
    public List<BankVo> getAll() throws DaoException {
        return selectList(null, "Bank.selectAll");
    }

    public List<BankVo> getBankList(BankFilter filter) throws DaoException {
        return this.selectList(filter, "Bank.selectBankList");
    }

    public long countByFilter(BankFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "Bank.countBankListByFilter");
    }

    public void insert(Map<String, String> context, BankVo bank) throws DaoException {
        insert(context, bank, "Bank.insert");
    }

    public void update(Map<String, String> context, BankVo bank) throws DaoException {
        update(context, bank, "Bank.update");
    }

    public void delete(Map<String, String> context, Long bankId) throws DaoException {
        delete(context, bankId, "Bank.delete");
    }

    public BankVo selectById(Long bankId) throws DaoException {
        return this.select(bankId, "Bank.selectById");
    }

    public long countByName(BankVo bank) throws DaoException {
        return (long) this.selectObject(bank, "Bank.countBankByName");
    }
}