package test.com.gms.xms.workflow;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.txndb.vo.FranchisePayableFilter;
import com.gms.xms.workflow.client.FranchisePayableSCClient;
import org.junit.Test;

import java.util.*;

/**
 * Posted from SCShipmentReportTest
 * <p>
 * Author DatTV Oct 30, 2015
 */
public class SCShipmentReportTest {
    @Test
    public void getShipmentDetailsTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        FranchisePayableSCClient client = new FranchisePayableSCClient(context);
        Calendar calendar = new GregorianCalendar(2014, 10, 1);
        FranchisePayableFilter filter = new FranchisePayableFilter();
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 10, 11);
        filter.setEndDate(calendar.getTime());
        filter.setFranchiseCodeList(Arrays.asList(new String[]{"100", "101"}));
        filter.setRecordSize(5);
        filter.setStartRecord(0);
        Map<String, Object> result = client.getShipmentDetails(filter);
        System.out.println(result.get(Attributes.RESULT));
    }

    @Test
    public void getTechFeeDetailsTest() throws Exception {
        Map<String, String> context = new HashMap<String, String>();
        FranchisePayableSCClient client = new FranchisePayableSCClient(context);
        Calendar calendar = new GregorianCalendar(2014, 10, 1);
        FranchisePayableFilter filter = new FranchisePayableFilter();
        filter.setStartDate(calendar.getTime());
        calendar = new GregorianCalendar(2014, 10, 11);
        filter.setEndDate(calendar.getTime());
        filter.setFranchiseCodeList(Arrays.asList(new String[]{"100", "101"}));
        filter.setRecordSize(5);
        filter.setStartRecord(0);
        Map<String, Object> result = client.getTechFeeDetails(filter);
        System.out.println(result.get(Attributes.RESULT));
    }
}
