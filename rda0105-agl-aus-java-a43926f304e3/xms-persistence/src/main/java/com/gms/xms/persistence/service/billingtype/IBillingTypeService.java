package com.gms.xms.persistence.service.billingtype;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.BillingTypeVo;

import java.util.List;

/**
 * Posted from IBillingTypeService
 * <p>
 * Author DatTV Date Jul 9, 2015 4:46:06 PM
 */
public interface IBillingTypeService {
    public List<BillingTypeVo> getBillingTypeList() throws DaoException;

    public BillingTypeVo getBillingTypeById(Integer billingId) throws DaoException;
}
