package com.gms.xms.workflow.render.airbillimporterrorlogs;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.invoicing.DuplicateAirbillFilter;
import com.gms.xms.model.invoicing.DuplicateAirbillModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.invoicing.DuplicateAirbillVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirbillImportErrorsLogRenderImp extends BaseRender implements IAirbillImportErrorLogsRender {

    public AirbillImportErrorsLogRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateHtmlFile(DuplicateAirbillFilter filter, String outputFilePath) throws Exception {
        IInvoiceService service = new InvoiceServiceImp();
        List<DuplicateAirbillVo> duplicateAirbillVos = service.getDuplicateAirbillByFilter(filter);
        List<DuplicateAirbillModel> duplicateAirbillModels = ModelUtils.createListModelFromVo(duplicateAirbillVos, DuplicateAirbillModel.class);

        ExportLocalizationHelper lang = new ExportLocalizationHelper(getAddInfo());
        String note;
        for (DuplicateAirbillModel duplicateAirbillModel : duplicateAirbillModels) {
            note = lang.translate("Error: Duplicate airbill" + " " + duplicateAirbillModel.getAirbillNumber() + ". " + lang.translate("Will not import."));
            duplicateAirbillModel.setNote(note);
        }
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("lang", lang);
        data.put("listData", duplicateAirbillModels);
        data.put("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.template2File(outputFilePath, false, "templates/html/airbill_import_error_logs/airbill_import_error_logs.ftl", data);
    }

    @Override
    public void generateXlsFile(DuplicateAirbillFilter filter, String outputFilePath) throws Exception {
        IInvoiceService service = new InvoiceServiceImp();
        List<DuplicateAirbillVo> duplicateAirbillVos = service.getDuplicateAirbillByFilter(filter);
        List<DuplicateAirbillModel> duplicateAirbillModels = ModelUtils.createListModelFromVo(duplicateAirbillVos, DuplicateAirbillModel.class);

        ExportLocalizationHelper lang = new ExportLocalizationHelper(getAddInfo());
        String note;
        for (DuplicateAirbillModel duplicateAirbillModel : duplicateAirbillModels) {
            note = lang.translate("Error: Duplicate airbill" + " " + duplicateAirbillModel.getAirbillNumber() + ". " + lang.translate("Will not import."));
            duplicateAirbillModel.setNote(note);
        }
        Context data = new Context();
        data.putVar("lang", lang);
        data.putVar("listData", duplicateAirbillModels);
        data.putVar("currencySymbol", SystemSettingCache.getInstance().getValueByKey(Attributes.CURRENCY_SYMBOL));
        AppUtils.generateXLSFile(outputFilePath, "templates/excel/airbill_import_error_logs/airbill_import_error_logs.xls", data);
    }
}
