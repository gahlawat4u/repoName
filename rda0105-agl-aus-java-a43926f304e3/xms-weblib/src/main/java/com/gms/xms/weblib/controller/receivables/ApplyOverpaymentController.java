package com.gms.xms.weblib.controller.receivables;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.OverpaymentModel;
import com.gms.xms.model.overpayment.ApplyOverpaymentModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.OverpaymentDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.overpayment.ApplyOverpaymentVo;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.InvoiceOverpaymentClient;
import com.gms.xms.workflow.client.ReceivePaymentsClient;
import com.gms.xms.workflow.client.integration.request.InvoiceRequest;
import com.gms.xms.workflow.client.integration.response.InvoiceResponse;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Posted from ApplyOverpaymentController
 * <p>
 * Author DatTV Date May 4, 2015 4:53:25 PM
 */
public class ApplyOverpaymentController extends JsonBaseController {
    private static final long serialVersionUID = 1L;

    private ApplyOverpaymentModel overpayment;
    private List<InvoiceModel> invoiceList;
    private List<String> overpaymentSource = Arrays.asList(new String[]{"Customer Payment", "Credit Note", "Carrier Credit Note"});
    private List<OverpaymentModel> overpaymentList;
    private String cusPaymentId;

    public String show() {
        if (overpayment == null) {
            return "success";
        }
        try {
            loadOverpaymentList();
            prepareInvoices();
        } catch (Exception e) {
            addActionError(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        return "success";
    }

    private void loadOverpaymentList() throws Exception {
        InvoiceOverpaymentClient client = new InvoiceOverpaymentClient(this.getAddInfoMap());
        OverpaymentInfoFilter filter = new OverpaymentInfoFilter();
        filter.setCustomerCode(overpayment.getCustomerCode());
        filter.setSource(overpayment.getSource());
        List<OverpaymentModel> overpaymentList = ModelUtils.createListModelFromVo(client.getOverpaymentVoByCustCodeAndSource(filter), OverpaymentModel.class);
        this.setOverpaymentList(overpaymentList);
    }

    /**
     * This action use to get over payment list by customer code and source
     */
    public String prepareOverpaymentList() {
        if (overpayment == null) {
            return "success";
        }
        InvoiceOverpaymentClient client = new InvoiceOverpaymentClient(this.getAddInfoMap());
        OverpaymentInfoFilter filter = new OverpaymentInfoFilter();
        filter.setCustomerCode(overpayment.getCustomerCode());
        filter.setSource(overpayment.getSource());
        try {
            List<OverpaymentModel> overpaymentList = ModelUtils.createListModelFromVo(client.getOverpaymentVoByCustCodeAndSource(filter), OverpaymentModel.class);
            this.setOverpaymentList(overpaymentList);
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        return "success";
    }

    /**
     * This action use to get over payment list by customer code and source
     *
     * @throws DaoException
     * @throws NumberFormatException
     */
    public String getPaymentTypeByCusPaymentId() throws Exception {
        if (StringUtils.isBlank(cusPaymentId)) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("not found paymentId in request"));
        } else {
            OverpaymentDao dao = new OverpaymentDao();
            Long result = dao.selectCreditTypeByCustomerPaymentId(Long.valueOf(cusPaymentId));
            if (result == null) {
                request.setAttribute("creditType", "Customer Payment");
            } else if (result == 2) {
                request.setAttribute("creditType", "Agl Warranty Credit");
            } else if (result == 1) {
                request.setAttribute("creditType", "Credit Note");
            } else if (result == 0) {
                request.setAttribute("creditType", "Carrier Credit Note");
            } else {
                request.setAttribute("creditType", "Unknown Payment Type");
            }
        }

        return "json";
    }

    /**
     * This action use to delete over payment by customer payment id
     */
    public void deleteOverpayment() {
        if (overpayment == null) {
            return;
        }

        try {
            InvoiceOverpaymentClient client = new InvoiceOverpaymentClient(this.getAddInfoMap());
            client.deleteOverpayment(this.getAddInfoMap(), overpayment.getCusPaymentId());
            log.info("Delete overpayment: #" + overpayment.getCusPaymentId());
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
    }

    public String doPayment() {
        if (overpayment == null || invoiceList == null || invoiceList.size() == 0) {
            overpaymentList = new ArrayList<OverpaymentModel>();
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText("No amount or invoices to payment"));
            return "success";
        }

        switch (overpayment.getSubmitType()) {
            case "0": // Auto Apply Payments
                try {
                    loadOverpaymentList();
                    autoApplyToInvoices();
                } catch (Exception e) {
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    setErrorMessage(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
            case "1": // Reset Payments
                try {
                    loadOverpaymentList();
                    resetInvoicePayments();
                } catch (Exception e) {
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    setErrorMessage(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
            case "2": // Save
                try {
                    saveInvoicePayments();
                } catch (Exception e) {
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    setErrorMessage(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                try {
                    loadOverpaymentList();
                } catch (Exception e) {
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    setErrorMessage(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
        }

        return "success";

    }

    private void saveInvoicePayments() throws Exception {
        ApplyOverpaymentVo applyOverpaymentVo = null;
        List<InvoiceVo> invoiceVoList = null;
        applyOverpaymentVo = ModelUtils.createVoFromModel(overpayment, ApplyOverpaymentVo.class);
        invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);
        if (isValidPayments(applyOverpaymentVo, invoiceVoList)) {
            saveInvoicePaymentsToDatabase(applyOverpaymentVo, invoiceVoList);
        } else {
            throw new Exception(this.getLocalizationText("The payment is invalid"));
        }
    }

    private void saveInvoicePaymentsToDatabase(ApplyOverpaymentVo applyOverpaymentVo, List<InvoiceVo> invoiceVoList) throws Exception {
        // Get current date
        Calendar cal = Calendar.getInstance();

        List<InvoicePaymentVo> invoicePaymentList = new ArrayList<InvoicePaymentVo>();
        BigDecimal invTotalPayment = BigDecimal.ZERO;
        for (InvoiceVo invoice : invoiceVoList) {
            // Not save the invoice that cannot be pay
            if (invoice.getPayment() == null || invoice.getPayment().compareTo(BigDecimal.ZERO) == 0) {
                continue;
            }
            InvoicePaymentVo invoicePaymentVo = new InvoicePaymentVo();
            invoicePaymentVo.setInvoiceId(invoice.getInvoiceId());
            invoicePaymentVo.setAmount(invoice.getPayment());
            invoicePaymentVo.setLateFee(invoice.getLateFee());
            invoicePaymentVo.setReverseFlag((byte) 0);
            invoicePaymentVo.setRevInvoicePaymentId(0L);
            invoicePaymentVo.setApplyDate(cal.getTime());
            invoicePaymentVo.setCusPaymentId(applyOverpaymentVo.getCusPaymentId());
            invTotalPayment = invTotalPayment.add(invoice.getPayment());
            List<InvoicePaymentDetailVo> invoicePaymentDetailList = new ArrayList<InvoicePaymentDetailVo>();
            for (ShipmentInvoiceVo shipmentInvoice : invoice.getShipmentInvoices()) {
                // Ignore the shipment invoice that isn't pay (payment=null)
                if (shipmentInvoice.getPayment() == null || shipmentInvoice.getPayment().compareTo(BigDecimal.ZERO) == 0) {
                    continue;
                }
                InvoicePaymentDetailVo invoicePaymentDetail = new InvoicePaymentDetailVo();
                invoicePaymentDetail.setAirbillNumber(shipmentInvoice.getAirbillNumber());
                invoicePaymentDetail.setAmount(shipmentInvoice.getPayment());
                invoicePaymentDetail.setShipmentId(shipmentInvoice.getShipmentId());
                invoicePaymentDetailList.add(invoicePaymentDetail);
            }
            invoicePaymentVo.setInvoicePaymentDetails(invoicePaymentDetailList);
            invoicePaymentList.add(invoicePaymentVo);
        }

        // Get over payment info
        OverpaymentVo overpaymentVo = new OverpaymentVo();
        overpaymentVo.setCusPaymentId(applyOverpaymentVo.getCusPaymentId());
        overpaymentVo.setOverAmount(applyOverpaymentVo.getAmountToApply().subtract(invTotalPayment));

        // Save it
        InvoiceOverpaymentClient client = new InvoiceOverpaymentClient(this.getAddInfoMap());
        client.saveInvoicePayments(invoicePaymentList, overpaymentVo);
    }

    private boolean isValidPayments(ApplyOverpaymentVo applyOverpaymentVo, List<InvoiceVo> invoiceList) {
        BigDecimal invPaymentTotal = BigDecimal.ZERO;
        BigDecimal invRemainningBalanceTotal = BigDecimal.ZERO;

        int count = 0;
        for (InvoiceVo invoice : invoiceList) {
            // Ignore the invoice that cannot be pay
            if (invoice.getAwbLevel() == 1) {
                count++;
                continue;
            }
            // Round invoice payment
            if (invoice.getPayment() != null) {
                BigDecimal pay = invoice.getPayment();
                pay = pay.setScale(2, BigDecimal.ROUND_HALF_UP);
                invoice.setPayment(pay);
            }
            // Get invoice payment and it's owed
            BigDecimal invoicePayment = invoice.getPayment() == null ? BigDecimal.ZERO : invoice.getPayment();
            if (invoicePayment.compareTo(BigDecimal.ZERO) == 0) {
                count++;
            }
            BigDecimal remainningBalance = invoice.getRemainningBalance() == null ? BigDecimal.ZERO : invoice.getRemainningBalance();
            // Check if invoice payment is greater than it's owed
            if (invoicePayment.compareTo(remainningBalance) == 1) {
                log.info("Error : invoicePayment greater than RemainningBalance");
                return false;
            }
            invPaymentTotal = invPaymentTotal.add(invoicePayment);
            invRemainningBalanceTotal = invRemainningBalanceTotal.add(remainningBalance);
            // Check awb level
            BigDecimal totalAwbPayment = BigDecimal.ZERO;
            if (invoice.getShipmentInvoices() != null) {
                for (ShipmentInvoiceVo shipmentInvoiceVo : invoice.getShipmentInvoices()) {
                    // Round airbill payment
                    if (shipmentInvoiceVo.getPayment() != null) {
                        BigDecimal pay = shipmentInvoiceVo.getPayment();
                        pay = pay.setScale(2, BigDecimal.ROUND_HALF_UP);
                        shipmentInvoiceVo.setPayment(pay);
                    }
                    // Get payment for airbill
                    BigDecimal awbPayment = shipmentInvoiceVo.getPayment() == null ? BigDecimal.ZERO : shipmentInvoiceVo.getPayment();
                    totalAwbPayment = totalAwbPayment.add(awbPayment);
                    // Check if payment for airbill is greater than it's owed
                    if (awbPayment.compareTo(shipmentInvoiceVo.getAmountDue()) == 1) {
                        log.info("Error : awbPayment greater than AmountDue");
                        return false;
                    }
                }
            }
            // Check if total payment for all airbill is equals with invoice
            // payment
            if (totalAwbPayment.compareTo(invoicePayment) != 0) {
                log.info("Error : totalAwbPayment " + totalAwbPayment + " not equal invoicePayment " + invoicePayment);
                return false;
            }
            // Check if total payment for all invoices is less than amount to
            // apply
            if (applyOverpaymentVo.getAmountToApply().compareTo(invPaymentTotal) == -1) {
                log.info("Error :  totalInvoicePayment greater than OverPayment");
                return false;
            }
        }
        // Check that at least one invoice is paid
        if (count == invoiceList.size()) {
            return false;
        }
        return true;
    }

    private void autoApplyToInvoices() throws Exception {
        // Gets over payment info and invoice list
        ApplyOverpaymentVo overpaymentVo = ModelUtils.createVoFromModel(overpayment, ApplyOverpaymentVo.class);
        List<InvoiceVo> invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);

        // Gets total amount to apply
        BigDecimal totalPaymentAmount = overpaymentVo.getAmountToApply();
        // Not allocate if the amount is not invalid
        if (totalPaymentAmount.compareTo(BigDecimal.ZERO) <= 0)
            return;

        BigDecimal appliedAmount = BigDecimal.ZERO;
        for (InvoiceVo invoice : invoiceVoList) {
            BigDecimal owed = invoice.getRemainningBalance();

            // Not allocate for the invoice that haven't been reconcile
            if (invoice.getAwbLevel() == 1)
                continue;
            if (totalPaymentAmount.compareTo(owed) == 1) {
                invoice.setPayment(owed);
                invoice.setUnappliedAmount(owed);
                invoice.setAppliedAmount(BigDecimal.ZERO);
                totalPaymentAmount = totalPaymentAmount.subtract(owed);
                appliedAmount = appliedAmount.add(owed);
                this.autoAppleToAWB(invoice);
            } else if (totalPaymentAmount.compareTo(BigDecimal.ZERO) == 1) {
                invoice.setPayment(totalPaymentAmount);
                invoice.setUnappliedAmount(totalPaymentAmount);
                invoice.setAppliedAmount(BigDecimal.ZERO);
                appliedAmount = appliedAmount.add(totalPaymentAmount);
                totalPaymentAmount = BigDecimal.ZERO;
                this.autoAppleToAWB(invoice);
            } else {
                invoice.setPayment(null);
                invoice.setAwbLevel(0);
            }
        }

        // Set applied/un applied amount for payment
        overpaymentVo.setAppliedAmount(appliedAmount);
        overpaymentVo.setUnappliedAmount(totalPaymentAmount);

        this.setOverpayment(ModelUtils.createModelFromVo(overpaymentVo, ApplyOverpaymentModel.class));
        this.setInvoiceList(ModelUtils.createListModelFromVo(invoiceVoList, InvoiceModel.class));
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

    private void resetInvoicePayments() throws Exception {
        // Gets over payment info and invoice list
        ApplyOverpaymentVo overpaymentVo = ModelUtils.createVoFromModel(overpayment, ApplyOverpaymentVo.class);
        List<InvoiceVo> invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);

        for (InvoiceVo invoice : invoiceVoList) {
            // Not allocate for the invoice that haven't been reconcile
            if (invoice.getAwbLevel() == 1)
                continue;
            invoice.setPayment(null);
            invoice.setUnappliedAmount(BigDecimal.ZERO);
            invoice.setAppliedAmount(BigDecimal.ZERO);
            for (ShipmentInvoiceVo shipmentInvoice : invoice.getShipmentInvoices()) {
                shipmentInvoice.setPayment(null);
            }
            if (invoice.getTotalPayment().compareTo(invoice.getTotalAwbPayment()) == 0) {
                // Set status 0 for invoice that means it is valid invoice
                // and it can be pay but now it have got invalid payment amount
                invoice.setAwbLevel(0);
            } else {

                // Set status 1 for invoice that means it cannot to be pay
                // It need reconcile awb level to do this
                invoice.setAwbLevel(1);
            }
        }

        // Set applied/un applied amount for payment
        overpaymentVo.setAppliedAmount(BigDecimal.ZERO);
        overpaymentVo.setUnappliedAmount(overpaymentVo.getAmountToApply());
        this.setOverpayment(ModelUtils.createModelFromVo(overpaymentVo, ApplyOverpaymentModel.class));
        this.setInvoiceList(ModelUtils.createListModelFromVo(invoiceVoList, InvoiceModel.class));
    }

    private void prepareInvoices() throws Exception {
        ReceivePaymentsClient rpClient = new ReceivePaymentsClient(this.getAddInfoMap());
        // Search invoice by customer or invoice code
        InvoiceFilter filter = new InvoiceFilter();
        filter.setCustomerCode(Long.parseLong(overpayment.getCustomerCode()));
        InvoiceRequest request = new InvoiceRequest();
        request.setFilter(filter);

        // Gets list of outstanding invoices
        InvoiceResponse response = rpClient.getOutstandingInvoices(request);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(response.getErrorCode())) {
            throw new Exception(response.getErrorMessage());
        }
        List<InvoiceVo> invoiceListVo = response.getInvoiceList();

        // Determines status of invoices
        for (InvoiceVo invoice : invoiceListVo) {
            if (invoice.getTotalPayment().compareTo(invoice.getTotalAwbPayment()) == 0) {
                // Set status 0 for invoice that means it is valid invoice
                // and it can be pay but now it have got invalid payment amount
                invoice.setAwbLevel(0);
            } else {

                // Set status 1 for invoice that means it cannot to be pay
                // It need reconcile awb level to do this
                invoice.setAwbLevel(1);
            }
        }

        // Set it to model
        List<InvoiceModel> invoiceListModel = ModelUtils.createListModelFromVo(invoiceListVo, InvoiceModel.class);
        this.setInvoiceList(invoiceListModel);
    }

    public List<String> getOverpaymentSource() {
        return overpaymentSource;
    }

    public void setOverpaymentSource(List<String> overpaymentSource) {
        this.overpaymentSource = overpaymentSource;
    }

    public ApplyOverpaymentModel getOverpayment() {
        return overpayment;
    }

    public void setOverpayment(ApplyOverpaymentModel overpayment) {
        this.overpayment = overpayment;
    }

    public List<InvoiceModel> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceModel> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List<OverpaymentModel> getOverpaymentList() {
        return overpaymentList;
    }

    public void setOverpaymentList(List<OverpaymentModel> overpaymentList) {
        this.overpaymentList = overpaymentList;
    }

    public String getCusPaymentId() {
        return cusPaymentId;
    }

    public void setCusPaymentId(String cusPaymentId) {
        this.cusPaymentId = cusPaymentId;
    }
}