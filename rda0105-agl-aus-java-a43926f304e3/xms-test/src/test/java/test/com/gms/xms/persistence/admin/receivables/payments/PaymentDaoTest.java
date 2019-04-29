package test.com.gms.xms.persistence.admin.receivables.payments;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.receivables.payments.PaymentFilter;
import com.gms.xms.persistence.dao.admin.receivables.payments.PaymentDao;

import java.util.Calendar;

/**
 * Posted from PaymentDaoTest
 * <p>
 * Author dattrinh Mar 17, 2016 3:13:46 PM
 */
public class PaymentDaoTest {
    public static void main(String[] args) throws DaoException {
        PaymentDao dao = new PaymentDao();
        PaymentFilter filter = new PaymentFilter();
        filter.setFranchiseList("101,102,103");
        Calendar cal = Calendar.getInstance();
        cal.set(2015, 5, 1);
        filter.setStartDate(cal.getTime());
        cal.set(2015, 5, 30);
        filter.setEndDate(cal.getTime());
        filter.setCustomerCode("101");
        filter.setNote("short paid");
        filter.setMin(40.0);
        filter.setMax(500.0);
        System.out.println("Count: " + dao.countPaymentByFilter(filter));
        System.out.println("Records: " + dao.getPaymentByFilter(filter));
    }
}
