package com.gms.xms.workflow.render.reconcileairbill;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.admin.imports.ReconcileAirbillModel;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;
import org.jxls.common.Context;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class ReconcileAirbillRenderImp extends BaseRender implements IReconcileAirbillRender {
    public ReconcileAirbillRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void genXLSFile(List<ReconcileAirbillModel> reconcileAirbillModels, String outPutFilePath) {
        Context data = new Context();
        data.putVar("lang", new ExportLocalizationHelper(this.getAddInfo()));
        data.putVar("currencySymbol", SystemSettingCache.getInstance().getValueByKey("CurrencySymbol"));
        data.putVar("airbills", reconcileAirbillModels);
        AppUtils.generateXLSFile(outPutFilePath, "templates/excel/reconcile_airbill/reconcile_airbill.xls", data);
    }
}
