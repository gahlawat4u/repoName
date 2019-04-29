package test.com.gms.xms.persistence.invoicing;

import com.gms.xms.model.admin.administration.RateSheetColModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.persistence.dao.ShipmentTypeDao;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.workflow.utils.BaseRateUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * File Name: BaseRateDaoTest.java <br/>
 * Author: TANDT <br/>
 * Create Date: 16-03-2016 <br/>
 * Project Name: xms-test <br/>
 * package Name: test.com.gms.xms.persistence.invoicing <br/>
 */
public class BaseRateDaoTest {
    public static void main(String[] args) throws Exception {
        ShipmentTypeDao dao = new ShipmentTypeDao();
        ShipmentTypeVo shipmentType = dao.selectById(1);
        ShipmentTypeModel shipmentTypeModel = ModelUtils.createModelFromVo(shipmentType, ShipmentTypeModel.class);
        List<ShipmentTypeModel> listShipmentType = new ArrayList<ShipmentTypeModel>();
        listShipmentType.add(shipmentTypeModel);
        HashMap<String, RateSheetColModel> baseRateResult = BaseRateUtils.getBaseRateByShipmentTypes(listShipmentType, "", "10000001");
        System.out.println("Result list:\n" + baseRateResult);
    }
}
