package test.com.gms.xms.service;

import com.gms.xms.filter.account.franchises.FranchiseDetailFilter;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import org.junit.Test;

/**
 * Posted from FranchiseServiceTest
 * <p>
 * Author DatTV Oct 22, 2015
 */
public class FranchiseServiceTest {
    @Test
    public void getActiveServicesWithBaseRatesTest() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        FranchiseDetailFilter filter = new FranchiseDetailFilter();
        filter.setFranchiseList("100,101,102,103");
        filter.setPage(1);
        filter.setPageSize(10);
        filter.setOrderField("customer_name");
        filter.setOrderType(1);
        System.out.println(franchiseService.getFranchises(filter));
    }
}