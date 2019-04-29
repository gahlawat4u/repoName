package com.gms.xms.persistence.dao.invoice;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.ImportTotalReportFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.invoicing.ImportTotalReportVo;

import java.util.Date;
import java.util.List;

/**
 * Posted from ImportTotalReportDao
 * <p>
 * Author dattrinh Mar 9, 2016 4:59:45 PM
 */
public class ImportTotalReportDao extends BaseDao<ImportTotalReportVo> {
    public List<ImportTotalReportVo> getImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException {
        return this.selectList(filter, "ImportTotalReport.getImportTotalReportByFilter");
    }

    public long countImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException {
        return (long) this.selectObject(filter, "ImportTotalReport.countImportTotalReportByFilter");
    }

    public ImportTotalReportVo sumImportTotalReportByFilter(ImportTotalReportFilter filter) throws DaoException {
        return this.select(filter, "ImportTotalReport.sumImportTotalReportByFilter");
    }

    @SuppressWarnings("unchecked")
    public List<Date> getImportDateList() throws DaoException {
        return (List<Date>) (Object) this.selectList(null, "ImportTotalReport.getImportDateList");
    }
}