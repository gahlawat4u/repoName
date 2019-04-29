package test.com.gms.xms.service;

import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import org.junit.Test;

/**
 * Posted from ServiceTest
 * <p>
 * Author DatTV Oct 15, 2015
 */
public class ShipmentTypeServiceTest {
    @Test
    public void getServicesByCarrierTest() throws Exception {
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        System.out.println(service.getServicesByCarrier(53));
    }
}