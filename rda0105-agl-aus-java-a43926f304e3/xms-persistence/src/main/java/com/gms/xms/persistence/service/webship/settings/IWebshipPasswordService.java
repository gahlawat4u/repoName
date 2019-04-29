package com.gms.xms.persistence.service.webship.settings;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.webship.WebshipVo;

import java.util.Map;

/**
 * Posted from IWebhsipPasswordService
 * <p>
 * Author DatTV Date Jul 14, 2015 10:44:21 AM
 */
public interface IWebshipPasswordService {
    public void changePassword(Map<String, String> context, WebshipVo passwordVo) throws DaoException;
}