package com.gms.xms.persistence.service.importbilling;

import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.admin.imports.CheckDuplicateAirbillVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;

import java.util.Map;

/**
 * Posted from May 26, 2016 11:21:15 AM
 * <p>
 * Author huynd
 */
public interface IImportBillingService {
    public void saveImportBilling(Map<String, String> context, SaveImportBillingVo saveImportVo, ShipmentVo shipmentVo, CheckDuplicateAirbillVo checkDuplicateAirbillVo, ShipmentInvoiceVo shipmentInvoice, InvoiceVo invoice, IRecalculateCharge iRecalculateCharge) throws Exception;
}
