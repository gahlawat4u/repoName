package test.com.gms.xms.persistence.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.ImportTotalReportFilter;
import com.gms.xms.persistence.dao.invoice.ImportTotalReportDao;
import com.gms.xms.txndb.vo.invoicing.ImportTotalReportVo;

import java.util.Calendar;
import java.util.List;

/**
 * Posted from ImportTotalReportDaoTest
 * <p>
 * Author dattrinh Mar 9, 2016 5:07:30 PM
 */
public class ImportTotalReportDaoTest {
    public static void main(String[] args) {
        ImportTotalReportDao dao = new ImportTotalReportDao();
        ImportTotalReportFilter filter = new ImportTotalReportFilter();
        filter.setFranchiseList("101,102");
        filter.setCarrier(1L);
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 6, 1);
        filter.setStartDate(cal.getTime());
        filter.setEndDate(cal.getTime());
        try {
            List<ImportTotalReportVo> importTotalReportVos = dao.getImportTotalReportByFilter(filter);
            System.out.println(importTotalReportVos);
            System.out.println(dao.getImportDateList());
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
