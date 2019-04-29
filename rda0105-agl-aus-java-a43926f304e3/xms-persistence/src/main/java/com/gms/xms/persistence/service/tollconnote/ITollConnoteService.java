package com.gms.xms.persistence.service.tollconnote;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TollConnoteFilter;

import java.util.Map;

public interface ITollConnoteService {
    public String getConnNumber(TollConnoteFilter tollConnoteFilter) throws DaoException;

    public void insertConnote(Map<String, String> context, TollConnoteFilter tollConnoteFilter) throws DaoException;

    public Long countTollPriorityConnoteInfo(String customerCode) throws DaoException;

}
