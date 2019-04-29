package test.com.gms.xms.persistence.customers;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.admin.customerprofile.CustomerProfileFilter;
import org.junit.Test;

/**
 * Posted from CustomerProfileTest
 * <p>
 * Author TANDT 04-11-2015
 */
public class CustomerProfileTest {
    @Test
    public void selectByBasicCustomerFilterTest() throws DaoException {
        CustomerProfileFilter filter = new CustomerProfileFilter();
        filter.setProfileId(1L);
        filter.setListServices("1");
        System.out.println();
    }
}