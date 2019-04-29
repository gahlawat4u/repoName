package com.gms.xms.persistence.service.billingtype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.BillingTypeDao;
import com.gms.xms.txndb.vo.webship.BillingTypeVo;

import java.util.List;

/**
 * Posted from BillingTypeServiceImp
 * <p>
 * Author DatTV Date Jul 9, 2015 4:46:09 PM
 */
public class BillingTypeServiceImp implements IBillingTypeService {
    private BillingTypeDao billingTypeDao = new BillingTypeDao();

    @Override
    public List<BillingTypeVo> getBillingTypeList() throws DaoException {
        return billingTypeDao.getBillingTypeList();
    }

    @Override
    public BillingTypeVo getBillingTypeById(Integer billingId) throws DaoException {
        return billingTypeDao.selectBillingTypeById(billingId);
    }
}
