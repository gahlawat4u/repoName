package com.gms.xms.workflow.task2.tnt.international.booking;

import com.gms.delivery.tnt.service.TntShipmentService;
import com.gms.delivery.tnt.xmlpi.shipping.response.Document;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.model.webship.ship.BookingDataVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.shipment.TntShipmentRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from ProcessTntBookingApiTask
 * <p>
 * Author TANDT
 */
public class ProcessTntIntlShipmentRequestTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessTntIntlShipmentRequestTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {

        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TntShipmentRequestVo tntShipmentRequestVo = context.get(Attributes.TNT_SHIPMENT_REQUEST_VO);
            BookingDataVo bookingDataVo = context.get(Attributes.BOOKING_DATA_VO);
            ShipmentVo shipmentVo = bookingDataVo.getShipmentVo();
            TntShipmentService tntShipmentService = new TntShipmentService();
            String xmlRequest = tntShipmentService.prepareXmlRequest(tntShipmentRequestVo);
            String xmlResponse = tntShipmentService.execute(xmlRequest, null);
            // String xmlResponse = "<?xml version=\"1.0\"
            // standalone=\"yes\"?><document><CREATE><CONREF>tesst</CONREF><CONNUMBER>GE320220397AU</CONNUMBER><SUCCESS>Y</SUCCESS></CREATE><RATE></RATE><SHIP><CONSIGNMENT><CONREF>tesst</CONREF><CONNUMBER>GE320220397AU</CONNUMBER><SUCCESS>Y</SUCCESS></CONSIGNMENT></SHIP></document>";
            Document document = tntShipmentService.parseResponse(xmlResponse);
            String awbNumber = "";
            if (document.getERROR() != null && document.getERROR().size() > 0) {
                String errMsg = "";
                for (com.gms.delivery.tnt.xmlpi.shipping.response.Error error : document.getERROR()) {
                    errMsg += "Error code: " + error.getCODE() + "<br/>";
                    errMsg += "Error message:" + error.getDESCRIPTION() + "<br/>";
                }
                throw new Exception(errMsg);
            } else {
                if (StringUtils.isNotEmpty(document.getCREATE().get(0).getSUCCESS()) && document.getCREATE().get(0).getSUCCESS().equalsIgnoreCase("Y")) {
                    awbNumber = document.getCREATE().get(0).getCONNUMBER();
                    shipmentVo.setAirbillNumber(awbNumber);
                }
            }
            context.put(Attributes.BOOKING_DATA_VO, bookingDataVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }
}
