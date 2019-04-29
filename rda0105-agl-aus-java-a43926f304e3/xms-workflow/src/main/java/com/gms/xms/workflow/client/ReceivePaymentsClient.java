package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.txndb.vo.BankVo;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.OverpaymentVo;
import com.gms.xms.workflow.client.integration.request.*;
import com.gms.xms.workflow.client.integration.response.*;
import com.gms.xms.workflow.core.WorkFlowManager;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;

/**
 * Posted from ReceivePaymentsClient
 * <p>
 * Author DatTV Date Apr 8, 2015 11:27:50 AM
 */
public class ReceivePaymentsClient extends WorkflowBaseClient {

    public ReceivePaymentsClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    /**
     * Gets list of outstanding invoices by customer or invoice number
     *
     * @param request {@link InvoiceRequest}
     * @return {@link InvoiceResponse}
     * @throws Exception
     */
    public InvoiceResponse getOutstandingInvoices(InvoiceRequest request) throws Exception {
        InvoiceResponse response = new InvoiceResponse();

        // Validates customer/invoice number
        if (StringUtils.isBlank(request.getFilter().getInvoiceCode()) && request.getFilter().getCustomerCode() == -1) {
            response.setErrorCode(ErrorCode.FIELD_ERROR);
            response.addFieldError("receivePayment.customerOrInvoiceCode", "Please enter valid customer or invoice code");
            return response;
        }

        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_FILTER, GsonUtils.toGson(request.getFilter()));
        context.put(Attributes.WFP_NAME, "Wfl-GetOutstandingInvoicesByCustOrInvoiceCode");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<InvoiceVo> invoiceList = GsonUtils.fromGson(context.get(Attributes.INVOICE_LIST_RESULT), new TypeToken<List<InvoiceVo>>() {
            }.getType());
            response.setInvoiceList(invoiceList);
        } else {
            response.setErrorCode(context.get(Attributes.ERROR_CODE));
            response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        }

        return response;
    }

    /**
     * Gets list of customer addresses by name
     *
     * @param request {@link CustomerAddressRequest}
     * @return {@link CustomerAddressResponse}
     * @throws Exception
     */
    public CustomerAddressResponse getCustomersByName(CustomerAddressRequest request) throws Exception {
        CustomerAddressResponse response = new CustomerAddressResponse();

        // Validates customer name
        if (StringUtils.isBlank(request.getFilter().getCustomerName())) {
            response.setErrorCode(ErrorCode.FIELD_ERROR);
            response.addFieldError("receivePayment.customerName", "Please enter invalid customer name");
            return response;
        }

        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CUSTOMER_ADDRESS_FILTER, GsonUtils.toGson(request.getFilter()));
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerAddressByNameTask");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<CustomerAddressVo> customerList = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_ADDRESS_LIST_RESULT), new TypeToken<List<CustomerAddressVo>>() {
            }.getType());

            response.setCustomerList(customerList);
        } else {
            response.setErrorCode(context.get(Attributes.ERROR_CODE));
            response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        }

        return response;
    }

    /**
     * Gets list of banks
     *
     * @param request {@link BankRequest}
     * @return {@link BankResponse}
     * @throws Exception
     */
    public BankResponse getAllBank(BankRequest request) throws Exception {
        BankResponse response = new BankResponse();

        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetAllBankTask");
        context = WorkFlowManager.getInstance().process(context);

        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<BankVo> bankList = GsonUtils.fromGson(context.get(Attributes.BANK_LIST_RESULT), new TypeToken<List<BankVo>>() {
            }.getType());

            response.setBankList(bankList);
        } else {
            response.setErrorCode(context.get(Attributes.ERROR_CODE));
            response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        }

        return response;
    }

    /**
     * Gets list of over payment
     *
     * @param request {@link OverpaymentRequest}
     * @return {@link OverpaymentResponse}
     * @throws Exception
     */
    public OverpaymentResponse getAllOverpayment(OverpaymentRequest request) throws Exception {
        OverpaymentResponse response = new OverpaymentResponse();
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CUSTOMER_CODE, String.valueOf(request.getCustomerCode()));
        context.put(Attributes.WFP_NAME, "Wfl-GetOverpaymentByCustomerCode");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            List<OverpaymentVo> overpaymentList = GsonUtils.fromGson(context.get(Attributes.OVERPAYMENT_LIST_RESULT), new TypeToken<List<OverpaymentVo>>() {
            }.getType());

            response.setOverpaymentList(overpaymentList);
        } else {
            response.setErrorCode(context.get(Attributes.ERROR_CODE));
            response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        }

        return response;
    }

    public CustomerPaymentResponse saveCustomerPayment(CustomerPaymentRequest request) throws Exception {
        CustomerPaymentResponse response = new CustomerPaymentResponse();

        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CUSTOMER_PAYMENT_OBJECT, GsonUtils.toGson(request.getCustomerPaymentVo()));
        context.put(Attributes.OVERPAYMENT_OBJECT, GsonUtils.toGson(request.getOverpaymentVo()));
        context.put(Attributes.NOTE_OBJECT, GsonUtils.toGson(request.getNoteVo()));
        context.put(Attributes.WFP_NAME, "Wfl-SaveCustomerPayment");
        context = WorkFlowManager.getInstance().process(context);

        response.setErrorCode(context.get(Attributes.ERROR_CODE));
        response.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));

        return response;
    }

    public CustomerAddressVo getCustomerAddressByCode(String customerCode) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CUSTOMER_CODE, customerCode);
        context.put(Attributes.WFP_NAME, "Wfl-GetCustomerAddressByCode");
        context = WorkFlowManager.getInstance().process(context);
        CustomerAddressVo customerAddressVo = null;
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            customerAddressVo = GsonUtils.fromGson(context.get(Attributes.CUSTOMER_ADDRESS_RESULT), CustomerAddressVo.class);
        }

        return customerAddressVo;
    }

    public InvoiceVo getInvoiceByCode(String invoiceCode) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.INVOICE_CODE, invoiceCode);
        context.put(Attributes.WFP_NAME, "Wfl-GetInvoiceByCode");
        context = WorkFlowManager.getInstance().process(context);
        if (ErrorCode.SUCCESS.equalsIgnoreCase(context.get(Attributes.ERROR_CODE))) {
            InvoiceVo invoice = GsonUtils.fromGson(context.get(Attributes.INVOICE_RESULT), InvoiceVo.class);
            return invoice;
        }

        return null;
    }
}
