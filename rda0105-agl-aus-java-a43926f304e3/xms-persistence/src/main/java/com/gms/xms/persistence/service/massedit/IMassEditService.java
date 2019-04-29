package com.gms.xms.persistence.service.massedit;

import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from Jun 29, 2016 12:00:42 PM
 * <p>
 * Author huynd
 */
public interface IMassEditService {

    public void recalcBaseCharge(Map<String, String> context, Long shipmentId, String airbillNumber, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void recalcCustomerBaseCharge(Map<String, String> context, Long shipmentId, String airbillNumber, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void deleteAirbill(Map<String, String> context, Long shipmentId, String airbillNumber, String invoiceId) throws Exception;

    public String moveAirbill(Map<String, String> context, ShipmentInvoiceVo shipmentInvoice, Long customerCode, String invoiceDate, String invoiceCode, String moveInvoiceType, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception;

    public String moveAirbill(Map<String, String> context, Long invoiceId, ShipmentInvoiceVo shipmentInvoice, Long customerCode, String invoiceDate, String invoiceCode, String moveInvoiceType, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void changeZone(Map<String, String> context, Long shipmentId, String airbillNumber, String zone, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void addAccessorialCharge(Map<String, String> context, Long shipmentId, String airbillNumber, AccessorialInfoVo accessorialInfoVo, Integer adminLevel, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void recalcFranchiseBaseCost(Map<String, String> context, Long shipmentId, String airbillNumber, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void recalcAccessorialMarkup(Map<String, String> context, Long shipmentId, String airbillNumber) throws Exception;

    public void markupAccessorial(Map<String, String> context, Double customerCost, Long shipmentId, String airbillNumber) throws Exception;

    public void changeServiceType(Map<String, String> context, Long shipmentId, String airbillNumber, String serviceLevel, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void changeAccessorialType(Map<String, String> context, Long shipmentId, String airbillNumber, List<AccessorialInfoVo> accessorialInfoVos, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void recalcCustomerCost(Map<String, String> context, Long shipmentId, String airbillNumber, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void forceQuotedCharge(Map<String, String> context, Long shipmentId, String airbillNumber, Integer adminLevel) throws Exception;

    public void forceQuotedAccessorialCharge(Map<String, String> context, Long shipmentId, String airbillNumber) throws Exception;

    public void forceMarkupCustomerCost(Map<String, String> context, Long shipmentId, String airbillNumber, Double markup) throws Exception;

}
