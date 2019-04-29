package test.com.gms.xms.persistence.report.selfinsurance;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.reports.selfinsurance.InvoicedAirbillFilter;
import com.gms.xms.filter.reports.selfinsurance.WebshipLabelFilter;
import com.gms.xms.persistence.dao.report.selfinsurance.SelfInsuranceDao;

import java.util.Calendar;

/**
 * Posted from SelfInsuranceDaoTest
 * <p>
 * Author dattrinh Mar 18, 2016 3:47:14 PM
 */
public class SelfInsuranceDaoTest {
    public static void main(String[] args) throws DaoException {
        SelfInsuranceDao dao = new SelfInsuranceDao();
        WebshipLabelFilter filter = new WebshipLabelFilter();
        filter.setFranchiseList("101,102");
        filter.setSearchType(2);
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 0, 1);
        filter.setStartDate(cal.getTime());
        cal.set(2015, 1, 1);
        filter.setEndDate(cal.getTime());
        filter.setPage(1);
        filter.setPageSize(10);
        System.out.println("Webship Labels Report");
        System.out.println("Count: " + dao.countWebshipLabelReport(filter));
        System.out.println("Record: " + dao.getWebshipLabelReport(filter));
        InvoicedAirbillFilter iFilter = new InvoicedAirbillFilter();
        iFilter.setFranchiseList("101,102");
        iFilter.setCurrencySymbol("$");
        iFilter.setSearchType(1);
        cal.set(2015, 0, 1);
        iFilter.setStartDate(cal.getTime());
        cal.set(2015, 1, 1);
        iFilter.setEndDate(cal.getTime());
        iFilter.setPage(1);
        iFilter.setPageSize(10);
        System.out.println("\n\nInvoiced Airbills Report");
        System.out.println("Count: " + dao.countInvoicedAirbillReport(iFilter));
        System.out.println("Record: " + dao.getInvoicedAirbillReport(iFilter));
    }
}
