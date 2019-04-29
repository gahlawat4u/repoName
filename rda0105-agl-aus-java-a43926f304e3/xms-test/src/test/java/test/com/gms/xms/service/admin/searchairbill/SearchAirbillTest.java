package test.com.gms.xms.service.admin.searchairbill;

import com.gms.xms.persistence.dao.admin.SearchAirbillDao;
import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillFilter;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * File Name: SearchAirbillTest.java <br/>
 * Author: TANDT <br/>
 * Create Date: 21-03-2016 <br/>
 * Project Name: xms-test <br/>
 * package Name: test.com.gms.xms.service.admin.searchairbill <br/>
 */
public class SearchAirbillTest {
    @Test
    public void searchAirbillTest() throws Exception {
        SearchAirbillFilter filter = new SearchAirbillFilter();
        filter.setServiceId(1);
        filter.setServiceLevel("128,1,0,1");
        String serviceLevels[] = StringUtils.split(filter.getServiceLevel(), ",");
        String billingShipmentTypeId = serviceLevels[0];
        String billingContents = serviceLevels[1];
        String billingBound = serviceLevels[2];
        String carrier = serviceLevels[3];
        filter.setBillingBound(Integer.parseInt(billingBound));
        filter.setBillingContents(Integer.parseInt(billingContents));
        filter.setCarrierId(Integer.parseInt(carrier));
        filter.setShipmentTypeId(Integer.parseInt(billingShipmentTypeId));
        filter.setPage(1);
        filter.setFranchiseList("100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122,123,124,125,126,127,128,129,130,131,132,133,134,135,998,999");
        filter.setPageSize(100);
        filter.setIsCheckServiceLevel(false);
        filter.setIsCheckServiceId(false);
        SearchAirbillDao dao = new SearchAirbillDao();
        System.out.println(dao.selectAirbillList(filter).size());
    }
}