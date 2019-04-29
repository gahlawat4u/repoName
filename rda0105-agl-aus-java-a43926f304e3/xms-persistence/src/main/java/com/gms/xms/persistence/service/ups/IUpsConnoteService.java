package com.gms.xms.persistence.service.ups;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.UpsConnoteFilter;

import java.util.Map;

/**
 * Created by thinhdd
 * Date 4/7/2017.
 */
public interface IUpsConnoteService {
    String getConnNumber(UpsConnoteFilter connoteFilter) throws DaoException;

    void insertUpsConnote(Map<String, String> context, UpsConnoteFilter connoteFilter) throws DaoException;
}
