package com.gms.xms.workflow.utils;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.service.BaseService;

import java.util.Map;

/**
 * Posted from Jul 13, 2016 5:56:59 PM
 * <p>
 * Author dattrinh
 */
public class RecalculateChargeImp extends BaseService implements IRecalculateCharge {

    public RecalculateChargeImp(Map<String, String> context) {
        super(context);
    }

    @Override
    public ContextBase2 recalculateBaseCharge(Long customerCode, ShipmentBillingVo shipmentBilling) throws Exception {
        ContextBase2 context = new ContextBase2(this.getContext());
        context.put(Attributes.SHIPMENT_BILLING_VO, shipmentBilling);
        context.put(Attributes.CUSTOMER_CODE, customerCode);
        Integer carrierId = Integer.parseInt(shipmentBilling.getCarrier().toString());
        switch (carrierId) {
            case 1: // DHL International
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlIntlBaseCharge");
                break;
            case 15: // DHL Domestic
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlDomesticBaseCharge");
                break;
            case 3: // TNT Domestic
                context.put(Attributes.WFP_NAME, "Wfl-GetTntDomesticBaseCharge");
                break;
            case 54: // TNT International
                context.put(Attributes.WFP_NAME, "Wfl-GetTntIntlBaseCharge");
                break;
            case 52: // Toll Priority
                context.put(Attributes.WFP_NAME, "Wfl-GetTollPriorityBaseCharge");
                break;
            case 59: // Toll Ipec
                context.put(Attributes.WFP_NAME, "Wfl-GetTollIpecBaseCharge");
                break;
            case 72: // Startrack
                context.put(Attributes.WFP_NAME, "Wfl-GetStartrackBaseCharge");
                break;
            default: // Other carriers
                context.put(Attributes.WFP_NAME, "Wfl-GetOtherBaseCharge");
                break;
        }
        context = WorkFlowManager2.getInstance().process(context);
        return context;
    }

    public ContextBase2 recalculateBaseCharge(Long customerCode, SaveImportBillingVo saveImportBillingVo) throws Exception {
        ContextBase2 context = new ContextBase2(this.getContext());
        context.put(Attributes.IMPORT_BILLING_VO, saveImportBillingVo);
        context.put(Attributes.CUSTOMER_CODE, customerCode);
        String carrierId = String.valueOf(saveImportBillingVo.getBillingBaseCharge().getCarrier());
        switch (carrierId) {
            case "1": // DHL International
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlIntlDownloadBillingBaseCharge");
                break;
            case "15": // DHL Domestic
                context.put(Attributes.WFP_NAME, "Wfl-GetDhlDomesticDownloadBillingBaseCharge");
                break;
            case "3": // TNT Domestic
                context.put(Attributes.WFP_NAME, "Wfl-GetTntDomesticImportBillingBaseCharge");
                break;
            case "54": // TNT International
                context.put(Attributes.WFP_NAME, "Wfl-GetTntIntlImportBillingBaseCharge");
                break;
            case "52": // Toll Priority
                context.put(Attributes.WFP_NAME, "Wfl-GetTollPriorityImportBillingBaseCharge");
                break;
            case "59": // Toll Ipec
                context.put(Attributes.WFP_NAME, "Wfl-GetTollIpecImportBillingBaseCharge");
                break;
            case "72": // Startrack
                context.put(Attributes.WFP_NAME, "Wfl-GetStartrackImportBillingBaseCharge");
                break;
            default:
                return context;
        }
        context = WorkFlowManager2.getInstance().process(context);
        return context;
    }
}
