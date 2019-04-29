package test.com.gms.xms.service;

import com.gms.xms.model.admin.administration.RateSheetColModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.txndb.vo.ShipmentTypeFilter;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.utils.BaseRateUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Posted from BaseRateTest
 * <p>
 * Author TANDT 29-10-2015
 */
public class BaseRateTest {
    @Test
    public void getActiveServicesWithBaseRatesTest() throws Exception {
        List<ShipmentTypeModel> shipmentTypes = new ArrayList<ShipmentTypeModel>();
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        ShipmentTypeFilter filter = new ShipmentTypeFilter();
        filter.setServiceId(59);
        List<ShipmentTypeVo> shipmentTypeVos = service.getShipmentTypeListByServiceId(filter);
        shipmentTypes = ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class);
        HashMap<String, RateSheetColModel> hashMap = new HashMap<String, RateSheetColModel>();
        hashMap = BaseRateUtils.getBaseRateByShipmentTypes(shipmentTypes, "1", "");
        System.out.println(hashMap);
    }

}