package test.com.gms.xms.persistence.edigenerate;

import com.gms.xms.workflow.service.edigenerate.EdiGenerateService;

import java.util.HashMap;

/**
 * Posted from Sep 25, 2016 3:44:12 PM
 * <p>
 * Author dattrinh
 */
public class EdiGenerateTest {
    public static void main(String[] args) {
        // EdiGenerateDao dao = new EdiGenerateDao();
        try {
            // List<TntShipmentForEtVo> tntShipmentForEtVos =
            // dao.getTntShipmentForEt();
            // System.out.println(tntShipmentForEtVos);
            // List<TntShipmentDetailForEtVo> tntShipmentDetailForEtVos =
            // dao.getTntShipmentDetailForEt(tntShipmentForEtVos.get(0));
            // System.out.println(tntShipmentDetailForEtVos);
            // ShipmentVo shipmentVo =
            // dao.getShipmentInfoByShipmentIdForEtFile(tntShipmentDetailForEtVos.get(0).getShipmentId());
            // System.out.println(shipmentVo);
            EdiGenerateService service = new EdiGenerateService(new HashMap<String, String>());
            service.generateETFileCombine("E:\\edi_file");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
