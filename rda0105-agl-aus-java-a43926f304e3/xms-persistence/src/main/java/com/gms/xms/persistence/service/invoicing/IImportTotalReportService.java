package com.gms.xms.persistence.service.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.ImportTotalReportFilter;
import com.gms.xms.txndb.vo.invoicing.ImportTotalReportVo;

import java.util.Date;
import java.util.List;

/**
 * Posted from IImportTotalReportService
 * <p>
 * Author dattrinh Mar 10, 2016 10:29:14 AM
 */
public interface IImportTotalReportService {
    public List<ImportTotalReportVo> getImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException;

    public long countImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException;

    public ImportTotalReportVo sumImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException;

    public List<Date> getImportDateList() throws DaoException;
}
