package com.gms.xms.workflow.render.csvinvoices;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.invoicing.CsvInvoicesExportFilter;
import com.gms.xms.model.admin.csvinvoices.CsvInvoicesExportModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.invoicing.CsvInvoicesExportDao;
import com.gms.xms.txndb.vo.ShipmentBillingFilter;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.invoicing.csvinvoices.CsvInvoicesExportVo;
import com.gms.xms.workflow.helper.ExportLocalizationHelper;
import com.gms.xms.workflow.render.BaseRender;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CsvInvoicesRenderImp extends BaseRender implements ICsvInvoicesRender {

    public CsvInvoicesRenderImp(Map<String, String> addInfo) {
        super(addInfo);
    }

    @Override
    public void generateCsvFile(Long invoiceId, String franchiseCode, String outputFilePath) throws Exception {
        CsvInvoicesExportDao csvInvoicesExportDao = new CsvInvoicesExportDao();
        CsvInvoicesExportFilter filter = new CsvInvoicesExportFilter();
        filter.setInvoiceId(invoiceId);
        filter.setFranchiseCode(franchiseCode);
        List<CsvInvoicesExportVo> csvInvoicesExportVos = csvInvoicesExportDao.selectExportList(filter);
        for (CsvInvoicesExportVo csvInvoicesExportVo : csvInvoicesExportVos) {
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
            shipmentBillingFilter.setAirbillNumber(csvInvoicesExportVo.getAirbillNumber());
            shipmentBillingFilter.setShipmentId(csvInvoicesExportVo.getShipmentId());
            List<ShipmentBillingVo> surcharges = shipmentBillingDao.selectSurchargesByFilter(shipmentBillingFilter);
            List<ShipmentBillingVo> surchargesAdded = new LinkedList<>();
            surchargesAdded.addAll(surcharges);
            if (surcharges != null && surcharges.size() < 8) {
                for (int i = 0; i < 8 - surcharges.size(); i++) {
                    surchargesAdded.add(new ShipmentBillingVo());
                }
            }

            List<Map<String, String>> listSurcharges = new LinkedList<>();
            NumberFormat formatter = new DecimalFormat(AppConstants.APP_SETTINGS.getDefaultNumberFormat());
            for (int i = 0; i < 8; i++) {
                Map<String, String> surcharge = new HashMap<>();

                if (surchargesAdded.get(i) != null) {
                    surcharge.put("customerCost", surchargesAdded.get(i).getCustomerCost() != null ? formatter.format(surchargesAdded.get(i).getCustomerCost()) : "");
                    surcharge.put("description", surchargesAdded.get(i).getDescription() != null ? surchargesAdded.get(i).getDescription() : "");
                }
                listSurcharges.add(surcharge);
            }
            csvInvoicesExportVo.setListSurcharges(listSurcharges);
        }
        List<CsvInvoicesExportModel> csvInvoicesExportModels = ModelUtils.createListModelFromVo(csvInvoicesExportVos, CsvInvoicesExportModel.class);
        Map<String, Object> data = new HashMap<>();
        data.put("listData", csvInvoicesExportModels);
        data.put("lang", new ExportLocalizationHelper(this.getAddInfo()));
        AppUtils.template2File(outputFilePath, false, "templates/csv/csv_invoices/csv_invoices.ftl", data);
    }
}
