package com.gms.xms.workflow.task2.toll.priority.schedule;

import com.gms.delivery.toll.service.TollPriorityPickupService;
import com.gms.delivery.toll.xmlpi.pickup.response.TollPickupResult;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.dto.delivery.toll.TollPriorityPickupRequestVo;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from ProcessTollPriorityScheduleCollectionTask
 * <p>
 * Author @author HungNT Feb 17, 2016
 */
public class ProcessTollPriorityScheduleCollectionTask implements Task2 {
    private static final Log log = LogFactory.getLog(ProcessTollPriorityScheduleCollectionTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            TollPriorityPickupRequestVo tollPriorityPickupRequestVo = context.get(Attributes.TOLL_PRIORITY_PICKUP_REQUEST_VO);
            ScheduleCollectionVo scheduleCollectionVo = context.get(Attributes.SCHEDULECOLLECTION_VO);
            TollPriorityPickupService tollPriorityPickupService = new TollPriorityPickupService();

            String xmlRequest = tollPriorityPickupService.prepareXmlRequest(tollPriorityPickupRequestVo);
            String xmlResponse = tollPriorityPickupService.execute(xmlRequest, null);
            TollPickupResult tollPickupResult = tollPriorityPickupService.parseResponse(xmlResponse);
            String errorMessage = tollPriorityPickupService.getErrorMessage(tollPickupResult);
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
