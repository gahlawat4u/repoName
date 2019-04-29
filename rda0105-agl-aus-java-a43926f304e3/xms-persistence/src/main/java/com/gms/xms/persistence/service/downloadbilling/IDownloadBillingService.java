package com.gms.xms.persistence.service.downloadbilling;

import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.admin.imports.SaveImportBillingVo;

import java.util.Map;

/**
 * Posted from Jun 8, 2016 9:52:11 AM
 * <p>
 * Author huynd
 */
public interface IDownloadBillingService {
    public void saveDownloadBilling(Map<String, String> context, SaveImportBillingVo saveImportVo, ShipmentVo newShipmentVo, ShipmentInvoiceVo shipmentInvoice, InvoiceVo invoice, ShipmentBillingVo billingOverPaymentVo, IRecalculateCharge iRecalculateCharge) throws Exception;

}
