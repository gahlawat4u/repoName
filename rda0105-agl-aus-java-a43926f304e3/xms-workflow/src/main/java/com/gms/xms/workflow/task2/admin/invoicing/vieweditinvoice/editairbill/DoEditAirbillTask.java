package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice.editairbill;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.service.admin.invoicing.IViewEditInvoiceService;
import com.gms.xms.persistence.service.admin.invoicing.ViewEditInvoiceServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.utils.RecalculateChargeImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from GetListAccessorialTask
 * <p>
 * Author TANDT
 */
public class DoEditAirbillTask implements Task2 {
    private static final Log log = LogFactory.getLog(DoEditAirbillTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            EditAirbillResultVo editAirbillResult = context.get(Attributes.EDIT_AIRBILL_RESULT);
            Long customerCode = context.getLong(Attributes.CUSTOMER_CODE);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            IViewEditInvoiceService service = new ViewEditInvoiceServiceImp();
            IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(addInfo);
            service.editAirbillDo(addInfo, editAirbillResult, customerCode, iRecalculateCharge);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }

}
