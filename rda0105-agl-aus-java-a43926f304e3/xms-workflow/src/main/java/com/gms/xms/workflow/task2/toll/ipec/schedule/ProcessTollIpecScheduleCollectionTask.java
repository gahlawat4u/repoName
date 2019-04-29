package com.gms.xms.workflow.task2.toll.ipec.schedule;

import com.gms.delivery.toll.service.TollIpecPickupService;
import com.gms.delivery.toll.xmlpi.pickup.response.TollPickupResult;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.toll.TollIpecPickupRequestVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from ProcessTntDomScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 3, 2016
 */
public class ProcessTollIpecScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessTollIpecScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TollIpecPickupRequestVo tollIpecPickupRequestVo = context.get(Attributes.TOLL_IPEC_PICKUP_REQUEST_VO);
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            TollIpecPickupService tollIpecPickupService = new TollIpecPickupService();

            String xmlRequest = tollIpecPickupService.prepareXmlRequest(tollIpecPickupRequestVo);
            String xmlResponse = tollIpecPickupService.execute(xmlRequest, null);
            TollPickupResult tollPickupResult = tollIpecPickupService.parseResponse(xmlResponse);
            String errorMessage = tollIpecPickupService.getErrorMessage(tollPickupResult);
            String confirmationNumber = "";
            if (!StringUtils.isBlank(errorMessage)) {
                throw new Exception(errorMessage);
            } else {
                confirmationNumber = tollPickupResult.getPickupResponse().getPickupConfirmation().getConfirmationNumber();
            }
            scheduleCollectionVo.setConfirmationNo(confirmationNumber);
            context.put(Attributes.SCHEDULECOLLECTION_VO, scheduleCollectionVo);
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e.getMessage(), e);
            return false;
        }
        return true;
    }

}
