package com.gms.xms.weblib.controller.receivables;

import com.gms.xms.common.config.dto.PaymentType;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.BankModel;
import com.gms.xms.model.CustomerAddressModel;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.franchisepayable.ReceivePaymentModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.ReceivePaymentsClient;
import com.gms.xms.workflow.client.integration.request.*;
import com.gms.xms.workflow.client.integration.response.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Posted from ReceivePaymentsController
 * <p>
 * Author DatTV Date Apr 9, 2015 2:08:40 PM
 */
public class ReceivePaymentsController extends JsonBaseController {
    private static final long serialVersionUID = 1L;

    private List<BankModel> bankList;
    private List<InvoiceModel> invoiceList;
    private List<CustomerAddressModel> customerList;
    private List<PaymentType> paymentTypeList;
    private ReceivePaymentModel receivePayment;
    private String invoiceCode;
    private String invoiceIndex;
    private InvoiceModel invoiceModel;

    public String doReceivePayment() throws Exception {
        // Prepares bank list and payment type list
        try {
            this.preparePaymentTypes();
            this.prepareBankList();
        } catch (Exception e) {
            addActionError(this.getLocalizationText(e.getMessage()));
            log.error(e);
            return "success";
        }

        // Checks if this is not post back
        if (receivePayment == null) {
            return "success";
        }

        // Check valid customer.
        Boolean isValid = true;
        try {
            isValid = this.isValidCustomer(receivePayment.getCustomerOrInvoiceCode());
        } catch (Exception e) {
            log.error(e);
            addActionError(this.getLocalizationText(e.getMessage()));
            return "success";
        }
        if (!isValid) {
            String errorMsg = "You don't have permission to create receive payment for this customer.";
            this.setInvoiceList(null);
            addActionError(this.getLocalizationText(errorMsg));
            log.error(errorMsg);
            return "success";
        }

        // Validates all data
        this.validateData();
        if (getFieldErrors().size() > 0 || getActionErrors().size() > 0) {
            return "success";
        }

        // Gets list of customer by customer name
        if (StringUtils.equalsIgnoreCase(receivePayment.getSearchOption(), "1")) {
            try {
                this.prepareCustomerList();
            } catch (Exception e) {
                addActionError(this.getLocalizationText(e.getMessage()));
                log.error(e);
                return "success";
            }
        }

        // Do save/auto apply/reset payments
        switch (receivePayment.getSubmitType()) {
            case "Go":
                try {
                    this.prepareInvoices();
                    validatePaymentInvoice();
                } catch (Exception e) {
                    addActionError(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
            case "Save":
                try {
                    saveReceivePayment();
                    validatePaymentInvoice();
                } catch (Exception e) {
                    addActionError(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
            case "Convert":
                try {
                    convertToOverpayment();
                    this.receivePayment = null;
                    this.invoiceList = null;
                    addActionMessage(this.getLocalizationText("The payment was successful"));
                } catch (Exception e) {
                    addActionError(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
            case "Auto":
                try {
                    this.autoApplyPayments();
                    validatePaymentInvoice();
                } catch (Exception e) {
                    addActionError(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
            case "Reset":
                try {
                    validatePaymentInvoice();
                    this.resetPayments();
                } catch (Exception e) {
                    addActionError(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
            case "Manual":
                try {
                    this.manualApplyPayment();
                    validatePaymentInvoice();
                } catch (Exception e) {
                    addActionError(this.getLocalizationText(e.getMessage()));
                    log.error(e);
                }
                break;
            case "Update":
                validatePaymentInvoice();
                break;
        }

        return "success";
    }

    public String autoApplyAwbPayments() {
        if (StringUtils.isBlank(invoiceCode)) {
            this.setErrorCode(ErrorCode.ERROR);
            this.setErrorMessage(this.getLocalizationText("Invalid invoice code"));
            return "success";
        }
        try {
            InvoiceVo invoice = this.searchInvoiceByCode(invoiceCode);
            this.autoApplePaymentAWBLevel(invoice);
            this.setInvoiceModel(ModelUtils.createModelFromVo(invoice, InvoiceModel.class));
        } catch (Exception ex) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(ex.getMessage()));
            log.error(ex);
        }

        return "success";
    }

    public String resetAwbPayment() {
        if (StringUtils.isBlank(invoiceCode)) {
            this.setErrorCode(ErrorCode.ERROR);
            this.setErrorMessage(this.getLocalizationText("Invalid invoice code"));
            return "success";
        }
        try {
            InvoiceVo invoice = this.searchInvoiceByCode(invoiceCode);
            this.resetPaymentsAWBLevel(invoice);
            this.setInvoiceModel(ModelUtils.createModelFromVo(invoice, InvoiceModel.class));
        } catch (Exception ex) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(ex.getMessage()));
            log.error(ex);
        }

        return "success";
    }

    public String searchCustomers() {
        try {
            this.prepareCustomerList();
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        return SUCCESS;
    }

    private void validateCustomerInfo() {
        ReceivePaymentsClient client = new ReceivePaymentsClient(this.getAddInfoMap());
        // Validates customer/invoice number
        if ("0".equalsIgnoreCase(receivePayment.getSearchOption())) {
            if (StringUtils.isBlank(receivePayment.getCustomerOrInvoiceCode())) {
                addFieldError("receivePayment.customerOrInvoiceCode", this.getLocalizationText("Please enter valid customer/invoice number"));
            }
        } else {
            if (StringUtils.isBlank(receivePayment.getCustomerName())) {
                addFieldError("receivePayment.customerName", this.getLocalizationText("Please enter valid customer name"));
            }
        }

        // No need check customer code from database if it's not valid from view
        if (getFieldErrors().size() > 0) {
            this.setInvoiceList(null);
            return;
        }

        // Check existing of customer/invoice code
        InvoiceVo invoiceVo = null;
        try {
            invoiceVo = client.getInvoiceByCode(receivePayment.getCustomerOrInvoiceCode());
        } catch (Exception e) {
        }

        CustomerAddressVo customerAddressVo = null;
        try {
            customerAddressVo = client.getCustomerAddressByCode(receivePayment.getCustomerOrInvoiceCode());
        } catch (Exception ex) {
        }

        if (invoiceVo == null && customerAddressVo == null) {
            addFieldError("receivePayment.customerOrInvoiceCode", this.getLocalizationText("Invalid customer/invoice number"));
        } else {
            try {
                ReceivePaymentVo receivePaymentVo = ModelUtils.createVoFromModel(receivePayment, ReceivePaymentVo.class);
                if (customerAddressVo == null) {
                    customerAddressVo = client.getCustomerAddressByCode(String.valueOf(invoiceVo.getCustomerCode()));
                }
                receivePaymentVo.setCustomerName(customerAddressVo.getCustomerName());
                receivePaymentVo.setCustomerCode(customerAddressVo.getCustomerCode());
                getTotalOverpayment(receivePaymentVo);
                this.setReceivePayment(ModelUtils.createModelFromVo(receivePaymentVo, ReceivePaymentModel.class));
            } catch (Exception ex) {
                addActionError(this.getLocalizationText(ex.getMessage()));
                log.error(ex);
            }
        }

        // If the customer code is invalid so clear invoice list
        if (getFieldErrors().size() > 0) {
            this.setInvoiceList(null);
        }
    }

    private void getTotalOverpayment(ReceivePaymentVo receivePaymentVo) throws Exception {
        ReceivePaymentsClient client = new ReceivePaymentsClient(this.getAddInfoMap());
        OverpaymentRequest request = new OverpaymentRequest();
        request.setCustomerCode(receivePaymentVo.getCustomerCode());
        OverpaymentResponse response = client.getAllOverpayment(request);
        receivePaymentVo.setTotalOverpayment(response.getTotal());
    }

    private void validatePaymentInfo() {
        // Validates cheque number
        if ("0".equalsIgnoreCase(receivePayment.getPaymentType()) && StringUtils.isBlank(receivePayment.getCheque())) {
            addFieldError("receivePayment.cheque", this.getLocalizationText("Please enter Cheque #"));
        }
        // Validates amount
        if (StringUtils.isBlank(receivePayment.getAmount())) {
            addFieldError("receivePayment.amount", this.getLocalizationText("Please enter Amount"));
        } else {
            if (!NumberUtils.isNumber(receivePayment.getAmount())) {
                addFieldError("receivePayment.amount", this.getLocalizationText("Amount must be a number"));
            }
        }
        // Validates payment date
        if (StringUtils.isBlank(receivePayment.getPaymentDate())) {
            addFieldError("receivePayment.paymentDate", this.getLocalizationText("Please choose a payment date"));
        } else {
            Calendar cal = Calendar.getInstance();
            Date paymentDate = null;
            try {
                paymentDate = DateUtils.convertStringToDate(receivePayment.getPaymentDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            } catch (Exception ex) {
                addFieldError("receivePayment.paymentDate", this.getLocalizationText("Invalid date format"));
                log.error(ex);
            }

            cal.add(Calendar.DATE, -14);
            Date startDate = cal.getTime();
            if (paymentDate.before(startDate)) {
                addFieldError("receivePayment.paymentDate", this.getLocalizationText("Cannot choose a date before 2 weeks ago."));
            }
        }
    }

    private void manualApplyPayment() throws Exception {
        if (invoiceList == null || invoiceList.size() == 0)
            return;

        for (InvoiceModel invoice : invoiceList) {
            // Not validate blank payment
            if (StringUtils.isBlank(invoice.getPayment())) {
                continue;
            }

            if (!StringUtils.isNumeric(invoice.getPayment())) {
                invoice.setPageSize("0");
            }
        }

        ReceivePaymentVo receivePaymentVo = ModelUtils.createVoFromModel(receivePayment, ReceivePaymentVo.class);
        List<InvoiceVo> invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);

        BigDecimal totalPayment = receivePaymentVo.getAmount();
        BigDecimal appliedAmount = BigDecimal.ZERO;
        for (InvoiceVo invoice : invoiceVoList) {
            BigDecimal invoicePayment = invoice.getPayment() == null ? BigDecimal.ZERO : invoice.getPayment();
            BigDecimal invoiceOwed = invoice.getRemainningBalance();
            if (invoicePayment.compareTo(invoiceOwed) == 1) {
                totalPayment = totalPayment.subtract(invoiceOwed);
                invoice.setPayment(invoiceOwed);
                appliedAmount = appliedAmount.add(invoiceOwed);
            } else {
                totalPayment = totalPayment.subtract(invoicePayment);
                appliedAmount = appliedAmount.add(invoicePayment);
            }
        }

        receivePaymentVo.setAppliedAmount(appliedAmount);
        receivePaymentVo.setUnappliedAmount(totalPayment);

        this.setReceivePayment(ModelUtils.createModelFromVo(receivePaymentVo, ReceivePaymentModel.class));
        this.setInvoiceList(ModelUtils.createListModelFromVo(invoiceVoList, InvoiceModel.class));
    }

    private void validateData() {
        // Validates submit type
        if (StringUtils.isBlank(receivePayment.getSubmitType())) {
            addFieldError("receivePayment.submitType", this.getLocalizationText("Your submit type must be in ['Go','Save','Auto','Reset']"));
        }
        // If the submit type is "Save" so we need validates some other info
        validateCustomerInfo();
        switch (receivePayment.getSubmitType()) {
            case "Go":
                break;
            case "Save":
            case "Convert":
                validatePaymentInfo();
                break;
            case "Auto":
                // Validates amount
                if (StringUtils.isBlank(receivePayment.getAmount())) {
                    addFieldError("receivePayment.amount", this.getLocalizationText("Please enter Amount"));
                } else {
                    if (!NumberUtils.isNumber(receivePayment.getAmount())) {
                        addFieldError("receivePayment.amount", this.getLocalizationText("Amount must be a number"));
                    }
                }
                break;
            case "Reset":
                break;
            case "Update":
                break;
            case "Manual":
                // Validates amount
                if (StringUtils.isBlank(receivePayment.getAmount())) {
                    addFieldError("receivePayment.amount", this.getLocalizationText("Please enter Amount"));
                } else {
                    if (!NumberUtils.isNumber(receivePayment.getAmount())) {
                        addFieldError("receivePayment.amount", this.getLocalizationText("Amount must be a number"));
                    }
                }
                break;
        }
    }

    private boolean isValidReceivePayment(ReceivePaymentVo receivePayment, List<InvoiceVo> invoiceList, List<InvoiceModel> invoiceModalList) {
        BigDecimal invPaymentTotal = BigDecimal.ZERO;
        BigDecimal invRemainningBalanceTotal = BigDecimal.ZERO;
        // If the customer has no invoices so the payment will be convert to
        // over payment automatically
        if (invoiceList == null) {
            return true;
        }
        double totalPay = 0.0;
        for (InvoiceVo invoice : invoiceList) {
            if (invoice.getPayment() != null) {
                BigDecimal pay = invoice.getPayment();
                pay = pay.setScale(2, BigDecimal.ROUND_HALF_UP);
                invoice.setPayment(pay);
                totalPay += pay.doubleValue();
            }
            if (invoice.getAwbLevel() == 1) {
                continue;
            }
            BigDecimal invoicePayment = invoice.getPayment() == null ? BigDecimal.ZERO : invoice.getPayment();
            BigDecimal remainningBalance = invoice.getRemainningBalance() == null ? BigDecimal.ZERO : invoice.getRemainningBalance();
            if (invoicePayment.compareTo(remainningBalance) == 1) {
                log.info("Error : invoicePayment greater than RemainningBalance");
                return false;
            }
            invPaymentTotal = invPaymentTotal.add(invoicePayment);
            invRemainningBalanceTotal = invRemainningBalanceTotal.add(remainningBalance);
            BigDecimal totalAwbPayment = BigDecimal.ZERO;
            if (invoice.getShipmentInvoices() != null) {
                for (ShipmentInvoiceVo shipmentInvoiceVo : invoice.getShipmentInvoices()) {
                    if (shipmentInvoiceVo.getPayment() != null) {
                        BigDecimal pay = shipmentInvoiceVo.getPayment();
                        pay = pay.setScale(2, BigDecimal.ROUND_HALF_UP);
                        shipmentInvoiceVo.setPayment(pay);
                    }
                    BigDecimal awbPayment = shipmentInvoiceVo.getPayment() == null ? BigDecimal.ZERO : shipmentInvoiceVo.getPayment();
                    totalAwbPayment = totalAwbPayment.add(awbPayment);
                    if (awbPayment.compareTo(shipmentInvoiceVo.getAmountDue()) == 1) {
                        log.info("Error : awbPayment greater than AmountDue");
                        return false;
                    }
                }
            }
            if (totalAwbPayment.compareTo(invoicePayment) != 0) {
                log.info("Error : totalAwbPayment " + totalAwbPayment + " not equal invoicePayment " + invoicePayment);
                return false;
            }
            if (receivePayment.getAmount().compareTo(invPaymentTotal) == -1) {
                log.info("Error :  totalInvoicePayment greater than ReceivePayment");
                return false;
            }
        }
        if (totalPay == 0) {
            log.info("Error: please apply payment to the invoices.");
            return false;
        }

        return true;
    }

    private Map<String, String> validatePaymentContent(ReceivePaymentVo receivePayment, List<InvoiceVo> invoiceList) {
        Map<String, String> resultMap = new HashMap<String, String>();
        BigDecimal invPaymentTotal = BigDecimal.ZERO;
        for (InvoiceVo invoice : invoiceList) {
            if (invoice.getAwbLevel() == 1) {
                continue;
            }
            BigDecimal invoicePayment = invoice.getPayment() == null ? BigDecimal.ZERO : invoice.getPayment();
            if (invoicePayment.compareTo(invoice.getRemainningBalance()) == 1) {
                // log.info("Error : invoicePayment greater than
                // RemainningBalance");
                resultMap.put(invoice.getInvoiceCode(), ErrorCode.ERROR);
            }
            invPaymentTotal = invPaymentTotal.add(invoicePayment);
            BigDecimal totalAwbPayment = BigDecimal.ZERO;
            if (invoice.getShipmentInvoices() != null) {
                for (ShipmentInvoiceVo shipmentInvoiceVo : invoice.getShipmentInvoices()) {
                    BigDecimal awbPayment = shipmentInvoiceVo.getPayment() == null ? BigDecimal.ZERO : shipmentInvoiceVo.getPayment();
                    totalAwbPayment = totalAwbPayment.add(awbPayment);
                    if (awbPayment.compareTo(shipmentInvoiceVo.getAmountDue()) == 1) {
                        // log.info("Error : awbPayment greater than
                        // AmountDue");
                        resultMap.put(invoice.getInvoiceCode(), ErrorCode.ERROR);
                    }
                }
            }

            if (totalAwbPayment.compareTo(invoicePayment) != 0) {
                resultMap.put(invoice.getInvoiceCode(), ErrorCode.ERROR);
            } else if (totalAwbPayment.compareTo(BigDecimal.ZERO) == 1) {
                if (!ErrorCode.ERROR.equalsIgnoreCase(resultMap.get(invoice.getInvoiceCode()))) {
                    resultMap.put(invoice.getInvoiceCode(), ErrorCode.SUCCESS);
                } else {
                }
            }
        }
        return resultMap;
    }

    private void saveReceivePayment() throws Exception {
        ReceivePaymentVo receivePaymentVo = null;
        List<InvoiceVo> invoiceVoList = null;
        receivePaymentVo = ModelUtils.createVoFromModel(receivePayment, ReceivePaymentVo.class);
        invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);

        if (isValidReceivePayment(receivePaymentVo, invoiceVoList, invoiceList)) {
            savePaymentToDatabase(receivePaymentVo, invoiceVoList);
            this.receivePayment = null;
            this.invoiceList = null;
            addActionMessage(this.getLocalizationText("The payment was successful"));
        } else {
            throw new Exception(this.getLocalizationText("The receive payment is invalid"));
        }
    }

    private void convertToOverpayment() throws Exception {
        ReceivePaymentVo receivePaymentVo = null;
        receivePaymentVo = ModelUtils.createVoFromModel(receivePayment, ReceivePaymentVo.class);
        savePaymentToDatabase(receivePaymentVo, null);
    }

    private void validatePaymentInvoice() throws Exception {
        if (invoiceList != null && receivePayment != null) {
            ReceivePaymentVo receivePaymentVo = null;
            List<InvoiceVo> invoiceVoList = null;
            receivePaymentVo = ModelUtils.createVoFromModel(receivePayment, ReceivePaymentVo.class);
            invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);
            Map<String, String> result = validatePaymentContent(receivePaymentVo, invoiceVoList);
            for (InvoiceModel invoiceModel : invoiceList) {
                if (ErrorCode.ERROR.equalsIgnoreCase(result.get(invoiceModel.getInvoiceCode()))) {
                    invoiceModel.setAwbLevel("0");
                } else if (ErrorCode.SUCCESS.equalsIgnoreCase(result.get(invoiceModel.getInvoiceCode()))) {
                    invoiceModel.setAwbLevel("2");
                } else if (!"1".equalsIgnoreCase(invoiceModel.getAwbLevel())) {
                    invoiceModel.setAwbLevel("4");
                }
            }
        }
    }

    private void savePaymentToDatabase(ReceivePaymentVo receivePayment, List<InvoiceVo> invoiceList) throws Exception {
        // Create customer payment object
        CustomerPaymentVo customerPaymentVo = new CustomerPaymentVo();
        ReceivePaymentsClient client = new ReceivePaymentsClient(this.getAddInfoMap());
        // Try to gets customer code first
        Long customerCode = null;
        try {
            customerCode = Long.parseLong(receivePayment.getCustomerOrInvoiceCode());
        } catch (Exception ex) {
            customerCode = null;
        }

        // If it's a customer code so try gets customer code from invoice
        if (customerCode == null) {
            try {
                InvoiceVo invoice = client.getInvoiceByCode(receivePayment.getCustomerOrInvoiceCode());
                customerCode = invoice.getCustomerCode();
            } catch (Exception ex) {
                throw ex;
            }
        }

        customerPaymentVo.setCustomerCode(customerCode);
        customerPaymentVo.setAmount(receivePayment.getAmount());
        customerPaymentVo.setCheque(receivePayment.getCheque());
        customerPaymentVo.setBankId(receivePayment.getBankId());
        customerPaymentVo.setPaymentDate(receivePayment.getPaymentDate());
        customerPaymentVo.setPaymentType(receivePayment.getPaymentType());

        BigDecimal unAppliedAmount = receivePayment.getAmount();
        List<InvoicePaymentVo> invoicePaymentList = new ArrayList<InvoicePaymentVo>();
        if (invoiceList != null && invoiceList.size() > 0) {
            for (InvoiceVo invoice : invoiceList) {
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
                invoicePaymentVo.setApplyDate(receivePayment.getPaymentDate());
                invoicePaymentVo.setRemainningBalance(invoice.getRemainningBalance());
                invoicePaymentVo.setInvoiceCode(invoice.getInvoiceCode());
                unAppliedAmount = unAppliedAmount.subtract(invoice.getPayment());
                List<InvoicePaymentDetailVo> invoicePaymentDetailList = new ArrayList<InvoicePaymentDetailVo>();
                for (ShipmentInvoiceVo shipmentInvoice : invoice.getShipmentInvoices()) {
                    // Ignore the shipment invoice that isn't pay (payment=null)
                    if (shipmentInvoice.getPayment() == null || shipmentInvoice.getPayment().compareTo(BigDecimal.ZERO) == 0)
                        continue;
                    InvoicePaymentDetailVo invoicePaymentDetail = new InvoicePaymentDetailVo();
                    invoicePaymentDetail.setAirbillNumber(shipmentInvoice.getAirbillNumber());
                    invoicePaymentDetail.setAmount(shipmentInvoice.getPayment());
                    invoicePaymentDetail.setShipmentId(shipmentInvoice.getShipmentId());
                    invoicePaymentDetailList.add(invoicePaymentDetail);
                }
                invoicePaymentVo.setInvoicePaymentDetails(invoicePaymentDetailList);
                invoicePaymentList.add(invoicePaymentVo);
            }
        }
        customerPaymentVo.setInvoicePayments(invoicePaymentList);

        // Create over payment object
        OverpaymentVo overpaymentVo = null;
        if (unAppliedAmount.compareTo(BigDecimal.ZERO) == 1) {
            overpaymentVo = new OverpaymentVo();
            overpaymentVo.setOverAmount(unAppliedAmount);
        }

        // Create Note if it's not blank
        NoteVo noteVo = null;
        if (!StringUtils.isBlank(receivePayment.getNote())) {
            noteVo = new NoteVo();
            noteVo.setAccountNo(receivePayment.getCustomerCode());
            String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
            noteVo.setUserId(Long.valueOf(userId));
            noteVo.setNoteType((byte) 1);
            // Get current date
            Calendar cal = Calendar.getInstance();
            noteVo.setModifyDate(cal.getTime());
            noteVo.setFollowUpDate(null);
            noteVo.setCheck(false);
            noteVo.setCurrent((byte) 0);
            noteVo.setNote(receivePayment.getNote());
        }

        // Create request to save customer payment
        CustomerPaymentRequest request = new CustomerPaymentRequest();
        request.setCustomerPaymentVo(customerPaymentVo);
        request.setOverpaymentVo(overpaymentVo);
        request.setNoteVo(noteVo);

        // Save it
        CustomerPaymentResponse response = client.saveCustomerPayment(request);
        if (ErrorCode.ERROR.equalsIgnoreCase(response.getErrorCode())) {
            throw new Exception(response.getErrorMessage());
        }
    }

    private void autoApplyPayments() throws Exception {
        ReceivePaymentVo receivePaymentVo = ModelUtils.createVoFromModel(receivePayment, ReceivePaymentVo.class);
        List<InvoiceVo> invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);

        BigDecimal totalPaymentAmount = receivePaymentVo.getAmount();
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
                this.autoApplePaymentAWBLevel(invoice);
            } else if (totalPaymentAmount.compareTo(BigDecimal.ZERO) == 1) {
                invoice.setPayment(totalPaymentAmount);
                invoice.setUnappliedAmount(totalPaymentAmount);
                invoice.setAppliedAmount(BigDecimal.ZERO);
                appliedAmount = appliedAmount.add(totalPaymentAmount);
                totalPaymentAmount = BigDecimal.ZERO;
                this.autoApplePaymentAWBLevel(invoice);
            } else {
                invoice.setPayment(null);
                invoice.setAwbLevel(0);
            }
        }

        // Set applied/un applied amount for payment
        receivePaymentVo.setAppliedAmount(appliedAmount);
        receivePaymentVo.setUnappliedAmount(totalPaymentAmount);

        this.setReceivePayment(ModelUtils.createModelFromVo(receivePaymentVo, ReceivePaymentModel.class));
        this.setInvoiceList(ModelUtils.createListModelFromVo(invoiceVoList, InvoiceModel.class));
    }

    private void resetPayments() throws Exception {
        ReceivePaymentVo receivePaymentVo = ModelUtils.createVoFromModel(receivePayment, ReceivePaymentVo.class);
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
        receivePaymentVo.setUnappliedAmount(receivePaymentVo.getAmount());
        receivePaymentVo.setAppliedAmount(BigDecimal.ZERO);

        this.setInvoiceList(ModelUtils.createListModelFromVo(invoiceVoList, InvoiceModel.class));
        this.setReceivePayment(ModelUtils.createModelFromVo(receivePaymentVo, ReceivePaymentModel.class));
    }

    private void resetPaymentsAWBLevel(InvoiceVo invoice) {
        for (ShipmentInvoiceVo shipmentInvoice : invoice.getShipmentInvoices()) {
            shipmentInvoice.setPayment(null);
        }
        invoice.setUnappliedAmount(invoice.getPayment());
        invoice.setAppliedAmount(BigDecimal.ZERO);
    }

    private InvoiceVo searchInvoiceByCode(String invoiceCode) throws Exception {
        InvoiceVo result = null;
        List<InvoiceVo> invoiceVoList = ModelUtils.createListVoFromModel(invoiceList, InvoiceVo.class);
        for (InvoiceVo invoice : invoiceVoList) {
            if (invoiceCode.equalsIgnoreCase(invoice.getInvoiceCode())) {
                result = invoice;
                break;
            }
        }
        return result;
    }

    private void autoApplePaymentAWBLevel(InvoiceVo invoice) throws Exception {
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
            if (totalPayment.compareTo(BigDecimal.ZERO) == 0) {
                invoice.setAwbLevel(2);
            }
        }
    }

    private void prepareInvoices() throws Exception {
        ReceivePaymentsClient client = new ReceivePaymentsClient(this.getAddInfoMap());
        // Search invoice by customer or invoice code
        InvoiceRequest request = new InvoiceRequest();
        request.setFilter(this.buildInvoiceFilter());

        // Gets list of outstanding invoices
        InvoiceResponse response = client.getOutstandingInvoices(request);
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
        this.setErrorCode(response.getErrorCode());
        this.setErrorMessage(response.getErrorMessage());
    }

    private void preparePaymentTypes() {
        List<PaymentType> paymentTypeList = AppConstants.APP_SETTINGS.getPayments();
        this.setPaymentTypeList(paymentTypeList);
    }

    private void prepareBankList() throws Exception {
        ReceivePaymentsClient client = new ReceivePaymentsClient(this.getAddInfoMap());
        // Gets list of banks
        BankRequest bankRequest = new BankRequest();
        BankResponse bankResponse = client.getAllBank(bankRequest);

        List<BankModel> bankListModel = ModelUtils.createListModelFromVo(bankResponse.getBankList(), BankModel.class);
        this.setBankList(bankListModel);
    }

    private void prepareCustomerList() throws Exception {
        ReceivePaymentsClient client = new ReceivePaymentsClient(this.getAddInfoMap());
        if (StringUtils.isBlank(receivePayment.getCustomerName())) {
            addFieldError("receivePayment.customerName", this.getLocalizationText("Please enter valid customer name"));
            this.setErrorCode(ErrorCode.FIELD_ERROR);
        }

        CustomerAddressRequest request = new CustomerAddressRequest();
        request.setFilter(this.buildCustomerAddressFilter());

        CustomerAddressResponse response = client.getCustomersByName(request);
        List<CustomerAddressVo> customerListVo = response.getCustomerList();
        List<CustomerAddressModel> customerListModel = ModelUtils.createListModelFromVo(customerListVo, CustomerAddressModel.class);
        this.setCustomerList(customerListModel);
    }

    private InvoiceFilter buildInvoiceFilter() {
        InvoiceFilter filter = new InvoiceFilter();

        long customerCode = -1;
        try {
            customerCode = Long.parseLong(receivePayment.getCustomerOrInvoiceCode());
        } catch (Exception ex) {
            customerCode = -1;
        }

        filter.setCustomerCode(customerCode == -1 ? null : customerCode);
        filter.setInvoiceCode(receivePayment.getCustomerOrInvoiceCode());

        return filter;
    }

    private CustomerAddressFilter buildCustomerAddressFilter() throws Exception {
        CustomerAddressFilter filter = new CustomerAddressFilter();
        filter.setCustomerName(receivePayment.getCustomerName());
        filter.setFranchiseList(this.buildFranchiseCodeList());
        return filter;
    }

    private List<FranchiseInfoVo> getManagedFranchises() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        return franchiseInfoVos;
    }

    private boolean isValidCustomer(String customerCode) throws Exception {
        if (StringUtils.isBlank(customerCode)) {
            throw new Exception("Please enter valid customer/invoice number");
        }
        String franchiseCode = "";
        if (customerCode.length() >= 3) {
            franchiseCode = customerCode.substring(0, 3);
        } else {
            franchiseCode = customerCode;
        }
        List<FranchiseInfoVo> franchiseInfoVos = this.getManagedFranchises();
        for (FranchiseInfoVo franchiseInfoVo : franchiseInfoVos) {
            if (franchiseCode.equalsIgnoreCase(franchiseInfoVo.getCode())) {
                return true;
            }
        }
        return false;
    }

    private String buildFranchiseCodeList() throws Exception {
        String result = "";
        List<FranchiseInfoVo> franchiseInfoVos = this.getManagedFranchises();
        for (FranchiseInfoVo franchiseInfoVo : franchiseInfoVos) {
            result += franchiseInfoVo.getCode() + ",";
        }
        result = result.substring(0, result.length() - 1);
        return result;
    }

    public List<BankModel> getBankList() {
        return bankList;
    }

    public void setBankList(List<BankModel> bankList) {
        this.bankList = bankList;
    }

    public List<InvoiceModel> getInvoiceList() {
        return invoiceList;
    }

    public void setInvoiceList(List<InvoiceModel> invoiceList) {
        this.invoiceList = invoiceList;
    }

    public List<CustomerAddressModel> getCustomerList() {
        return customerList;
    }

    public void setCustomerList(List<CustomerAddressModel> customerList) {
        this.customerList = customerList;
    }

    public ReceivePaymentModel getReceivePayment() {
        return receivePayment;
    }

    public void setReceivePayment(ReceivePaymentModel receivePayment) {
        this.receivePayment = receivePayment;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public InvoiceModel getInvoiceModel() {
        return invoiceModel;
    }

    public void setInvoiceModel(InvoiceModel invoiceModel) {
        this.invoiceModel = invoiceModel;
    }

    public String getInvoiceIndex() {
        return invoiceIndex;
    }

    public void setInvoiceIndex(String invoiceIndex) {
        this.invoiceIndex = invoiceIndex;
    }

    public List<PaymentType> getPaymentTypeList() {
        return paymentTypeList;
    }

    public void setPaymentTypeList(List<PaymentType> paymentTypeList) {
        this.paymentTypeList = paymentTypeList;
    }
}
