package com.gms.xms.persistence.service.admin.invoicing;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.AirbillInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.EditAirbillResultVo;

import java.util.List;
import java.util.Map;

/**
 * Posted from IInvoiceInfoService
 * <p>
 * Author TANDT
 */
public interface IViewEditInvoiceService {
    public InvoiceInfoVo selectInvoiceInfoById(Long invoiceId) throws DaoException;

    public List<AirbillInfoVo> selectAirbillList(Long invoiceId) throws DaoException;

    public void editAirbillDo(Map<String, String> context, EditAirbillResultVo editAirbillResultVo, Long customerCode, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void createInvoiceAndAirbillDo(Map<String, String> context, InvoiceVo newInvoice, EditAirbillResultVo createAirbillResultVo, ShipmentVo shipmentVo, ShipmentInvoiceVo shipmentInvoice, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void createAirbillDo(Map<String, String> context, EditAirbillResultVo createAirbillResultVo, ShipmentVo shipmentVo, ShipmentInvoiceVo shipmentInvoice, IRecalculateCharge iRecalculateCharge) throws Exception;

    public String moveAirbill(Map<String, String> context, ShipmentInvoiceVo shipmentInvoice, Long customerCode, String invoiceDate, String invoiceCode, String moveInvoiceType, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception;

    public void deleteAirbill(Map<String, String> context, String shipmentId, String airbillNumber) throws Exception;

    public String moveAirbill(Map<String, String> context, Long invoiceId, ShipmentInvoiceVo shipmentInvoice, Long customerCode, String invoiceDate, String invoiceCode, String moveInvoiceType, Boolean checkRecalcCustomerCost, IRecalculateCharge iRecalculateCharge) throws Exception;
}
