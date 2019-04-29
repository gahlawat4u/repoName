package test.com.gms.xms.workflow.startrack;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.ShipmentInfoVo;
import com.gms.xms.txndb.vo.webship.ServiceAddConVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.workflow.core2.WorkFlowManager2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 13, 2016 4:42:48 PM
 * <p>
 * Author huynd
 */
public class GetStartrackChargeTest {
    public static void main(String[] args) throws Exception {
        Map<String, String> addInfoMap = new HashMap<String, String>();
        ContextBase2 context = new ContextBase2(addInfoMap);
        AddressVo senderAddress = new AddressVo();
        senderAddress.setPostalCode("4509");
        senderAddress.setCity("NORTH LAKES");
        senderAddress.setState("QLD");
        AddressVo receiverAddress = new AddressVo();
        receiverAddress.setPostalCode("2018");
        receiverAddress.setCity("ROSEBERY");
        receiverAddress.setState("NSW");
        receiverAddress.setAddress("Unit 415, 30-40 Harcourt Pde");
        receiverAddress.setAddress2("po box");
        ShipmentInfoVo shipmentInfoVo = new ShipmentInfoVo();
        shipmentInfoVo.setShipmentTypeId(229);
        shipmentInfoVo.setSenderAddress(senderAddress);
        shipmentInfoVo.setReceiverAddress(receiverAddress);
        List<ServiceAddConVo> addConVo = new ArrayList<ServiceAddConVo>();
        ServiceAddConVo serviceAddConVo = new ServiceAddConVo();
        serviceAddConVo.setValue("1");
        serviceAddConVo.setAddConCode("dangerousgoods");
        addConVo.add(serviceAddConVo);
        serviceAddConVo = new ServiceAddConVo();
        serviceAddConVo.setValue("1");
        serviceAddConVo.setAddConCode("aglWarranty");
        addConVo.add(serviceAddConVo);
        shipmentInfoVo.setAddCons(addConVo);
        shipmentInfoVo.setServiceId(72);
        context.put(Attributes.SHIPMENT_INFO_VO, shipmentInfoVo);
        context.put(Attributes.SHIPMENT_TOTAL_WEIGHT, 1D);
        WebshipLoginVo webshipLoginInfo = new WebshipLoginVo();
        webshipLoginInfo.setCustomerCode(10000001L);
        context.put(Attributes.USER_LOGGIN_INFO, webshipLoginInfo);
        context.put(Attributes.WFP_NAME, "Wfl-GetStartrackCharge");
        ///////////////////////////////////////
        context = WorkFlowManager2.getInstance().process(context);
        ShipmentInfoVo shipmentInfoVoR = context.get(Attributes.SHIPMENT_INFO_VO);
        System.out.println("Zone: " + shipmentInfoVoR.getZone());
        System.out.println("Service area code: \n" + context.get(Attributes.SERVICE_AREA_CODE));
        System.out.println("Carrier cost: " + context.get(Attributes.CARRIER_COST));
        System.out.println("Customer cost: " + context.get(Attributes.CUSTOMER_COST));
        List<AccessorialVo> accessorialVos = context.get(Attributes.SURCHARGE_LIST);
        System.out.println("Surcharge list: \n" + accessorialVos);
        System.out.println("Total charge: " + context.get(Attributes.TOTAL_CHARGE));
    }
}
