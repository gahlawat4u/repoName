package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.persistence.dao.CreditNoteInfoDaoService;
import com.gms.xms.txndb.vo.CreditNoteInfoVo;
import com.gms.xms.txndb.vo.CreditNotesGSTSummaryVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;

/**
 * Posted from GetCreditNoteInfoTask
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class GetCreditNoteInfoTask implements Task {
    private static final Log log = LogFactory.getLog(GetCreditNoteInfoTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        CreditNoteInfoDaoService creditNoteDao = new CreditNoteInfoDaoService();
        try {
            String defaultCountry = SystemSettingCache.getInstance().getValueByKey(AppConstants.DEFAULT_ORIGIN_COUNTRY);
            String creditNoteCode = context.get(Attributes.CREDIT_NOTES_CODE);
            CreditNoteInfoVo creditNoteInfo = creditNoteDao.selectCreditNoteInfo(creditNoteCode);
            if (defaultCountry.equalsIgnoreCase("70")) {
                List<CreditNotesGSTSummaryVo> creditNotesGSTSummaryVos = creditNoteDao.selectCreditNoteGSTSummary(creditNoteCode);
                context.put(Attributes.CREDIT_NOTES_GST_SUMMARY, GsonUtils.toGson(creditNotesGSTSummaryVos));
            }
            context.put(Attributes.CREDIT_NOTES_INFO, GsonUtils.toGson(creditNoteInfo));
            if (creditNoteInfo == null) {
                throw new Exception("Cannot get credit note's detail because of missing airbill adjustments in this credit note.");
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
