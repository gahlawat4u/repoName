package com.gms.xms.persistence.service.tollipecconnote;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.webship.TollIpecConnoteFilter;

import java.util.Map;

public interface ITollIpecConnoteService {
    public String getConnNumber(TollIpecConnoteFilter tollIpecConnoteFilter) throws DaoException;

    public void insertConnote(Map<String, String> context, TollIpecConnoteFilter tollIpecConnoteFilter) throws DaoException;
}
