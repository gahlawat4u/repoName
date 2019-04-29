package com.gms.xms.workflow.render.reconcileairbill;

import com.gms.xms.model.admin.imports.ReconcileAirbillModel;

import java.util.List;

public interface IReconcileAirbillRender {
    public void genXLSFile(List<ReconcileAirbillModel> reconcileAirbillModels, String outPutFilePath);
}
