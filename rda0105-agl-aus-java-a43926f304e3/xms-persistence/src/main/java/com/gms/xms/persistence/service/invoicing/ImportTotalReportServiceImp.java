package com.gms.xms.persistence.service.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.ImportTotalReportFilter;
import com.gms.xms.persistence.dao.invoice.ImportTotalReportDao;
import com.gms.xms.txndb.vo.invoicing.ImportTotalReportVo;

import java.util.Date;
import java.util.List;

/**
 * Posted from ImportTotalReportServiceImp
 * <p>
 * Author dattrinh Mar 10, 2016 10:35:44 AM
 */
public class ImportTotalReportServiceImp implements IImportTotalReportService {
    private ImportTotalReportDao dao = new ImportTotalReportDao();

    @Override
    public List<ImportTotalReportVo> getImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException {
        return dao.getImportTotalReportByFilter(filter);
    }

    @Override
    public long countImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException {
        return dao.countImportTotalReportByFilter(filter);
    }

    @Override
    public List<Date> getImportDateList() throws DaoException {
        return dao.getImportDateList();
    }

    @Override
    public ImportTotalReportVo sumImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException {
        return dao.sumImportTotalReportByFilter(filter);
    }
}
