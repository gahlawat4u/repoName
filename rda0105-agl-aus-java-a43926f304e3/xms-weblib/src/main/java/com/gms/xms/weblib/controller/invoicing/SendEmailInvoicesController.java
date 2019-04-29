package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SendEmailInvoicesController extends AdminJsonBaseController {
    private static final long serialVersionUID = -8336988049322842141L;
    private List<String> listInvoiceDates;
    private String franchiseCode;
    private String invoiceCode;
    private String invoiceDate;

    public String show() {
        try {
            this.prepareDatas();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getInvDates() {
        try {
            this.prepareInvoiceDates();
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String sendEmailByInvDate() {
        try {
            if (!StringUtils.isBlank(this.getInvoiceDate())) {
                IInvoiceService invoiceService = new InvoiceServiceImp();
                this.prepareFranchises();
                List<String> franchiseCodes = new LinkedList<>();
                if (StringUtils.isBlank(this.getFranchiseCode())) {
                    for (FranchiseInfoModel franchiseInfo : this.getFranchises()) {
                        franchiseCodes.add(franchiseInfo.getCode());
                    }
                } else {
                    franchiseCodes.add(this.getFranchiseCode());
                }

                InvoiceFilter filter = new InvoiceFilter();
                filter.setInvoiceDate(DateUtils.convertStringToDate(this.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                filter.setStatus(1);
                filter.setFranchiseCodes(franchiseCodes);
                List<InvoiceVo> invoiceVos = invoiceService.getInvoicesByInvDatesAndStatus(filter);
                List<Long> invoiceIds = new LinkedList<>();
                for (InvoiceVo invoiceVo : invoiceVos) {
                    invoiceIds.add(invoiceVo.getInvoiceId());
                }
                // Load invoice information and send email in background.
                final ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                context.put(Attributes.LIST_INVOICE_IDS, invoiceIds);
                context.put(Attributes.FRANCHISE_CODE_LIST, franchiseCodes);
                context.put(Attributes.WFP_NAME, "Wfl-SendCustomerInvoicesEmail");
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ContextBase2 runableContext = context;
                        try {
                            runableContext = WorkFlowManager2.getInstance().process(runableContext);
                            if (runableContext.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
                            } else {
                                throw new CustomException(context.getString(Attributes.ERROR_MESSAGE));
                            }
                        } catch (Exception e) {
                            log.error(e);
                        }
                    }
                });
                thread.start();
                this.prepareInvoiceDates();
            } else {
                throw new CustomException("Invoice date cannot be empty.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public String sendEmailByInvCode() {
        try {
            if (!StringUtils.isBlank(this.getInvoiceCode())) {
                IInvoiceService invoiceService = new InvoiceServiceImp();
                InvoiceVo invoiceVo = invoiceService.selectByCode(this.getInvoiceCode());
                if (invoiceVo == null) {
                    throw new CustomException("Cannot find the invoice with this invoice code.");
                }
                if (invoiceVo.getStatus() < 1) {
                    throw new CustomException("This invoice has not been frozen or already sent.");
                }
                this.prepareFranchises();
                String customerCode = String.valueOf(invoiceVo.getCustomerCode());
                String franchiseCode = customerCode.substring(0, 3);
                Boolean hasPermission = false;
                List<String> franchiseCodes = new LinkedList<>();
                if (StringUtils.isBlank(this.getFranchiseCode())) {
                    for (FranchiseInfoModel franchiseInfo : this.getFranchises()) {
                        if (franchiseCode.equalsIgnoreCase(franchiseInfo.getCode())) {
                            hasPermission = true;
                            franchiseCodes.add(franchiseInfo.getCode());
                        }
                    }
                } else {
                    franchiseCodes.add(this.getFranchiseCode());
                }
                if (hasPermission) {
                    List<Long> invoiceIds = new LinkedList<>();
                    if (invoiceVo.getInvoiceId() != null) {
                        invoiceIds.add(invoiceVo.getInvoiceId());
                    }
                    final ContextBase2 context = new ContextBase2(this.getAddInfoMap());
                    context.put(Attributes.LIST_INVOICE_IDS, invoiceIds);
                    context.put(Attributes.FRANCHISE_CODE_LIST, franchiseCodes);
                    context.put(Attributes.WFP_NAME, "Wfl-SendCustomerInvoicesEmail");
                    Thread thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            ContextBase2 runableContext = context;
                            try {
                                runableContext = WorkFlowManager2.getInstance().process(runableContext);
                                if (runableContext.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
                                } else {
                                    throw new CustomException(context.getString(Attributes.ERROR_MESSAGE));
                                }
                            } catch (Exception e) {
                                log.error(e);
                            }
                        }
                    });
                    thread.start();
                    this.prepareInvoiceDates();
                } else {
                    throw new CustomException("You do not have permission to send this invoice.");
                }
            } else {
                throw new CustomException("Invoice code cannot be empty.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    private void prepareDatas() throws Exception {
        this.prepareFranchises();
        this.prepareInvoiceDates();
    }

    private void prepareInvoiceDates() throws Exception {
        List<String> franchiseCodes = new ArrayList<>();
        List<String> invoiceDates = new LinkedList<>();
        if (StringUtils.isBlank(this.getFranchiseCode())) {
            for (FranchiseInfoModel franchise : this.getFranchises()) {
                franchiseCodes.add(franchise.getCode());
            }
        } else {
            franchiseCodes.add(this.getFranchiseCode());
        }
        IInvoiceService invoiceService = new InvoiceServiceImp();
        InvoiceFilter filter = new InvoiceFilter();
        filter.setFranchiseCodes(franchiseCodes);
        filter.setStatus(1);
        filter.setEmailInvoice(1);
        List<InvoiceVo> invoiceDatesVo = invoiceService.getInvoiceDates(filter);
        List<InvoiceModel> invoiceDatesModel = ModelUtils.createListModelFromVo(invoiceDatesVo, InvoiceModel.class);
        for (InvoiceModel invoiceModel : invoiceDatesModel) {
            if (invoiceModel != null && StringUtils.isNotBlank(invoiceModel.getInvoiceDate())) {
                invoiceDates.add(invoiceModel.getInvoiceDate());
            }
        }
        this.setListInvoiceDates(invoiceDates);
    }

    public List<String> getListInvoiceDates() {
        return listInvoiceDates;
    }

    public void setListInvoiceDates(List<String> listInvoiceDates) {
        this.listInvoiceDates = listInvoiceDates;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
