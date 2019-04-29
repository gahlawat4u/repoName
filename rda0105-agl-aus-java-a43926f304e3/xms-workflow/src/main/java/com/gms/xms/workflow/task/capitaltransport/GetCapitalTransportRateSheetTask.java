package com.gms.xms.workflow.task.capitaltransport;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author tkvcl
 */
public class GetCapitalTransportRateSheetTask implements Task {
    private static final Log log = LogFactory.getLog(GetCapitalTransportRateSheetTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {

        try {


        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            context.put(Attributes.ERROR_MESSAGE, "Not Found rate sheet width from zone ".concat(context.get(Attributes.SENDER_ZONE_CODE)).concat(" to zone ").concat(context.get(Attributes.RECEIVER_ZONE_CODE)));
            log.error(e);
            return false;
        }
        return true;
    }

}
