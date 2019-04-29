package com.gms.xms.persistence.service.webship.settings;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.webship.WebshipDao;
import com.gms.xms.txndb.vo.webship.WebshipVo;

import java.util.Map;

/**
 * Posted from WebhsipPasswordServiceImp
 * <p>
 * Author DatTV Date Jul 14, 2015 10:47:29 AM
 */
public class WebshipPasswordServiceImp implements IWebshipPasswordService {
    private WebshipDao webshipDao = new WebshipDao();

    @Override
    public void changePassword(Map<String, String> context, WebshipVo webshipVo) throws DaoException {
        webshipDao.changePassword(context, webshipVo);
    }
}