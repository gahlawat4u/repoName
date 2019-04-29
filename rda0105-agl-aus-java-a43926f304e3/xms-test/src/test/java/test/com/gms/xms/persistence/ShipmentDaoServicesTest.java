package test.com.gms.xms.persistence;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.txndb.vo.ShipmentFilter;
import com.gms.xms.workflow.client.WebshipHistoryClient;
import com.gms.xms.workflow.client.integration.request.SearchHistoryRequest;
import com.gms.xms.workflow.client.integration.response.SearchHistoryResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ShipmentDaoServicesTest {

    @Test
    public void test() throws DaoException {
        try {
            SearchHistoryRequest searchHistoryRequest = new SearchHistoryRequest();
            SearchHistoryResponse searchHistoryResponse = new SearchHistoryResponse();
            ShipmentFilter shipmentFilter = new ShipmentFilter();
            shipmentFilter.setCustomerCode(10000001L);
            shipmentFilter.setTotalRecord(60);
            shipmentFilter.setRecordSize(10);
            shipmentFilter.setStartRecord(0);
            searchHistoryRequest.setShipmentFilter(shipmentFilter);
            Map<String, String> context = new HashMap<String, String>();
            WebshipHistoryClient client = new WebshipHistoryClient(context);
            searchHistoryResponse = client.searchHistoryResponse(searchHistoryRequest);
            System.out.println(searchHistoryResponse);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}