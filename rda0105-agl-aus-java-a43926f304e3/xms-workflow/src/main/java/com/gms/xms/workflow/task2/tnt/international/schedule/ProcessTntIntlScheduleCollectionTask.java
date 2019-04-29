package com.gms.xms.workflow.task2.tnt.international.schedule;

import com.gms.delivery.tnt.service.TntPickupService;
import com.gms.delivery.tnt.xmlpi.shipping.response.Document;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.shipment.TntShipmentRequestVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from ProcessTntIntlScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 2, 2016
 */
public class ProcessTntIntlScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessTntIntlScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TntShipmentRequestVo tntShipmentRequestVo = context.get(Attributes.TNT_SHIPMENT_REQUEST_VO);
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            TntPickupService tntPickupService = new TntPickupService();
            String xmlRequest = tntPickupService.prepareXmlRequest(tntShipmentRequestVo);
            String xmlResponse = tntPickupService.execute(xmlRequest, null);
            Document document = tntPickupService.parseResponse(xmlResponse);
            String confirmationNumber = "";
            if (document.getERROR() != null && document.getERROR().size() > 0) {
                String errMsg = "";
                for (com.gms.delivery.tnt.xmlpi.shipping.response.Error error : document.getERROR()) {
                    errMsg += "Error code: " + error.getCODE() + "<br/>";
                    errMsg += "Error message:" + error.getDESCRIPTION() + "<br/>";
                }
                throw new Exception(errMsg);
            } else {
                if (StringUtils.isNotEmpty(document.getBOOK().getCONSIGNMENT().get(0).getSUCCESS()) && document.getBOOK().getCONSIGNMENT().get(0).getSUCCESS().equalsIgnoreCase("Y")) {
                    confirmationNumber = document.getBOOK().getCONSIGNMENT().get(0).getBOOKINGREF();
                }
            }
            scheduleCollectionVo.setConfirmationNo(confirmationNumber);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

}
