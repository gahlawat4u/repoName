package com.gms.xms.persistence.service.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.MoveUnfrozenInvoicesFilter;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from IMoveUnfrozenInvoicesService
 * <p>
 * Author dattrinh Mar 10, 2016 5:13:06 PM
 */
public interface IMoveUnfrozenInvoicesService {
    public List<Date> getUnfrozenInvoiceDates(String franchiseList) throws DaoException;

    public long moveInvoices(Map<String, String> context, MoveUnfrozenInvoicesFilter filter) throws Exception;
}
