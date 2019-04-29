package com.gms.xms.persistence.service.bank;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.receivables.BankFilter;
import com.gms.xms.persistence.dao.BankDao;
import com.gms.xms.txndb.vo.BankVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from BankServiceImp
 * <p>
 * Author HoangPH Nov 4, 2015
 */
public class BankServiceImp implements IBankService {
    public List<BankVo> selectByFilter(BankFilter filter) throws DaoException {
        BankDao bankDao = new BankDao();
        return bankDao.getBankList(filter);
    }

    public long countByFilter(BankFilter filter) throws DaoException {
        BankDao bankDao = new BankDao();
        return bankDao.countByFilter(filter);
    }

    public BankVo selectById(Long bankId) throws DaoException {
        BankDao bankDao = new BankDao();
        return bankDao.selectById(bankId);
    }

    public void delete(Map<String, String> context, Long bankId) throws DaoException {
        BankDao bankDao = new BankDao();
        bankDao.delete(context, bankId);
    }

    public void insert(Map<String, String> context, BankVo bank) throws DaoException {
        BankDao bankDao = new BankDao();
        bankDao.insert(context, bank);
    }

    public void update(Map<String, String> context, BankVo bank) throws DaoException {
        BankDao bankDao = new BankDao();
        bankDao.update(context, bank);
    }

    public long countByName(BankVo bank) throws DaoException {
        BankDao bankDao = new BankDao();
        return bankDao.countByName(bank);
    }
}
