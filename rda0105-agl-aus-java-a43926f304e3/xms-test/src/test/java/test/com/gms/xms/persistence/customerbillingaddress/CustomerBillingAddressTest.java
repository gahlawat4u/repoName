package test.com.gms.xms.persistence.customerbillingaddress;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;

public class CustomerBillingAddressTest {
    public static void main(String[] args) throws DaoException {
        CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
        System.out.println(billingAddressDao.getByCustomerCode("10300001"));
    }
}
