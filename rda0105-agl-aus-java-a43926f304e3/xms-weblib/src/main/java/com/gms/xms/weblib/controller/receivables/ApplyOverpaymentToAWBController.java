package com.gms.xms.weblib.controller.receivables;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;

/**
 * Posted from ApplyOverpaymentToAWBController
 * <p>
 * Author DatTV Date May 6, 2015 9:39:07 AM
 */
public class ApplyOverpaymentToAWBController extends JsonBaseController {
    private static final long serialVersionUID = 1L;

    private List<InvoiceModel> invoiceList;
    private InvoiceModel invoice;
    private String invoiceCode;
    private String invoiceIndex;
    private String submitType;

    public String doPayment() {
        if (StringUtils.isBlank(invoiceCode) || StringUtils.isBlank(submitType)) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("Invalid invoice code or submit type"));
            return "success";
        }

        try {
            switch (submitType) {
                case "0":
                    autoApply();
                    break;
                case "1":
                    resetPayments();
                    break;
            }
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        return "success";
    }

    private void autoApply() throws Exception {
        InvoiceVo invoiceVo = searchInvoice(invoiceCode, invoiceIndex);
        autoAppleToAWB(invoiceVo);
        this.setInvoice(ModelUtils.createModelFromVo(invoiceVo, InvoiceModel.class));
    }

    private void resetPayments() throws Exception {
        InvoiceVo invoiceVo = searchInvoice(invoiceCode, invoiceIndex);
        invoiceVo.setUnappliedAmount(BigDecimal.ZERO);
        invoiceVo.setAppliedAmount(BigDecimal.ZERO);
        for (ShipmentInvoiceVo shipmentInvoice : invoiceVo.getShipmentInvoices()) {
            shipmentInvoice.setPayment(null);
        }
        this.setInvoice(ModelUtils.createModelFromVo(invoiceVo, InvoiceModel.class));
    }

    private void autoAppleToAWB(InvoiceVo invoice) {
        if (invoice != null) {
            BigDecimal totalPayment = invoice.getPayment() == null ? BigDecimal.ZERO : invoice.getPayment();
            BigDecimal appliedAmount = BigDecimal.ZERO;
            for (ShipmentInvoiceVo shipmentInvoice : invoice.getShipmentInvoices()) {
                // Not allocate for the airbill that haven't been got owed
                BigDecimal owed = shipmentInvoice.getAmountDue();
                if (owed.compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                if (totalPayment.compareTo(owed) == 1) {
                    totalPayment = totalPayment.subtract(owed);
                    shipmentInvoice.setPayment(owed);
                    appliedAmount = appliedAmount.add(owed);
                } else if (totalPayment.compareTo(BigDecimal.ZERO) == 1) {
                    shipmentInvoice.setPayment(totalPayment);
                    appliedAmount = appliedAmount.add(totalPayment);
                    totalPayment = BigDecimal.ZERO;
                } else {
                    shipmentInvoice.setPayment(null);
                }
            }

            // Set unapplied/applied for invoice
            invoice.setAppliedAmount(appliedAmount);
            invoice.setUnappliedAmount(totalPayment);

            // This invoice is valid if the payment was full allocated
            if (totalPayment.compareTo(BigDecimal.ZERO) == 0) {
                invoice.setAwbLevel(2);
            }
        }
    }

    private InvoiceVo searchInvoice(String invoiceCode, String invoiceIndex) throws Exception {
        List<InvoiceVo> invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);
        int index = 0;
        for (InvoiceVo inv : invoiceVoList) {
            if (inv.getInvoiceCode().equalsIgnoreCase(invoiceCode)) {
                invoiceIndex = String.valueOf(index);
                return inv;
            }
            index++;
        }
        return null;
    }

    public List<InvoiceModel> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceModel> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public String getSubmitType() {
        return submitType;
    }

    public void setSubmitType(String submitType) {
        this.submitType = submitType;
    }

    public InvoiceModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceModel invoice) {
        this.invoice = invoice;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceIndex() {
        return invoiceIndex;
    }

    public void setInvoiceIndex(String invoiceIndex) {
        this.invoiceIndex = invoiceIndex;
    }
}
