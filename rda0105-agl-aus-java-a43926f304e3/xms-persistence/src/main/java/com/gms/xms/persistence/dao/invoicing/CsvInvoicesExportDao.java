package com.gms.xms.persistence.dao.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.CsvInvoicesExportFilter;
import com.gms.xms.persistence.dao.BaseDao;
import com.gms.xms.txndb.vo.invoicing.csvinvoices.CsvInvoicesExportVo;

import java.util.List;

public class CsvInvoicesExportDao extends BaseDao<CsvInvoicesExportVo> {
    public List<CsvInvoicesExportVo> selectExportList(CsvInvoicesExportFilter filter) throws DaoException {
        return selectList(filter, "CsvInvoices.selectCsvInvoiceInfo");
    }
}
