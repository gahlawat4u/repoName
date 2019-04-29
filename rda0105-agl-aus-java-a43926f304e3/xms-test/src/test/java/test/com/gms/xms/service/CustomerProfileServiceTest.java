package test.com.gms.xms.service;

import com.gms.xms.persistence.service.customerprofile.CustomerProfileServiceImp;
import com.gms.xms.persistence.service.customerprofile.ICustomerProfileService;
import org.junit.Test;

/**
 * Posted from CustomerProfileServiceTest
 * <p>
 * Author DatTV Oct 12, 2015
 */
public class CustomerProfileServiceTest {
    @Test
    public void selectByFranchiseCodeTest() throws Exception {
        ICustomerProfileService profileService = new CustomerProfileServiceImp();
        System.out.println(profileService.selectByFranchiseCode("100"));
    }
}