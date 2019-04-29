package test.com.gms.xms.persistence.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.invoicing.MoveUnfrozenInvoicesFilter;
import com.gms.xms.persistence.dao.invoice.MoveUnfrozenInvoicesDao;

import java.util.Calendar;

/**
 * Posted from MoveUnfrozenInvoicesDaoTest
 * <p>
 * Author dattrinh Mar 10, 2016 5:00:09 PM
 */
public class MoveUnfrozenInvoicesDaoTest {
    public static void main(String[] args) {
        MoveUnfrozenInvoicesDao dao = new MoveUnfrozenInvoicesDao();
        MoveUnfrozenInvoicesFilter filter = new MoveUnfrozenInvoicesFilter();
        filter.setFranchiseList("101,103");
        Calendar cal = Calendar.getInstance();
        cal.set(2016, 01, 04);
        filter.setFromDate(cal.getTime());
        filter.setExclude(true);
        filter.setCustomerList("10100209,10000001,10101200");
        try {
            System.out.println(dao.getUnfrozenInvoiceDates("101,102"));
            System.out.println("Unfrozen invoices: ");
            System.out.println(dao.getMoveInvoices(filter));
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
