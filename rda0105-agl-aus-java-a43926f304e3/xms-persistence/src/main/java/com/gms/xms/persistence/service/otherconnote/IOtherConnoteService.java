package com.gms.xms.persistence.service.otherconnote;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.OtherConnoteFilter;

import java.util.Map;

/**
 * Posted from IOtherConnoteService
 * <p>
 * Author @author HungNT Feb 19, 2016
 */
public interface IOtherConnoteService {
    public String getConnNumber(OtherConnoteFilter otherConnoteFilter) throws DaoException;

    public void insertConnote(Map<String, String> context, OtherConnoteFilter otherConnoteFilter) throws DaoException;
}
