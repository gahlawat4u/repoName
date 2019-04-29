package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.CustomerVo;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class UnfreezeInvoicesController extends AdminJsonBaseController {
    private static final long serialVersionUID = 4794009754519315084L;
    private List<String> listInvoiceDates;
    private String franchiseCode;
    private String invoiceCode;
    private String invoiceDate;

    public String show() {
        try {
            this.prepareDatas();
        } catch (Exception e) {
            log.error(this.getLocalizationText(e.getMessage()), e);
        }
        return "success";
    }

    public String getInvDates() {
        try {
            this.prepareFranchises();
            this.prepareInvoiceDates();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public String doUnfreezeInvoicesByDate() {
        try {
            if (StringUtils.isNotBlank(this.getInvoiceDate())) {
                List<String> franchiseCodes = this.buildFranchisesList();
                IInvoiceService invoiceService = new InvoiceServiceImp();
                InvoiceFilter filter = new InvoiceFilter();
                filter.setInvoiceDate(DateUtils.convertStringToDate(this.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                filter.setStatus(1);
                filter.setFranchiseCodes(franchiseCodes);
                List<InvoiceVo> invoiceVos = invoiceService.getInvoicesByInvDatesAndStatus(filter);
                for (InvoiceVo invoiceVo : invoiceVos) {
                    invoiceVo.setStatus(0);
                    try {
                        invoiceService.updateInvoiceStatus(this.getAddInfoMap(), invoiceVo);
                    } catch (Exception e) {
                        log.error("Cannot unfreeze invoice:" + invoiceVo.getInvoiceCode());
                    }
                }
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

    public String doUnfreezeInvoiceByInvoiceCode() {
        try {
            if (StringUtils.isNotBlank(this.getInvoiceCode())) {
                List<String> franchiseCodes = this.buildFranchisesList();
                this.prepareInvoiceDates();
                IInvoiceService invoiceService = new InvoiceServiceImp();
                InvoiceVo invoiceVo = invoiceService.selectByCode(this.getInvoiceCode());
                if (invoiceVo == null) {
                    throw new CustomException("Can't find the invoice with this invoice code.");
                }
                if (invoiceVo.getStatus() == 0) {
                    throw new CustomException("This invoice has already been unfrozen.");
                }

                ICustomerService customerService = new CustomerServiceImp();
                String customerCode = String.valueOf(invoiceVo.getCustomerCode());
                String customerCodeSub = customerCode.substring(3, customerCode.length());
                String franchiseCode = customerCode.substring(0, 3);
                if (customerCodeSub.equalsIgnoreCase("00001")) {
                    franchiseCode = customerCode.substring(0, 3);
                } else {
                    CustomerVo customerVo = customerService.selectByCode(customerCode);
                    franchiseCode = String.valueOf(customerVo.getFranchiseCode());
                }

                Boolean hasPermission = false;
                for (String franCode : franchiseCodes) {
                    if (franchiseCode.equalsIgnoreCase(franCode)) {
                        hasPermission = true;
                    }
                }
                if (hasPermission) {
                    invoiceVo.setStatus(0);
                    invoiceService.updateInvoiceStatus(this.getAddInfoMap(), invoiceVo);
                    this.prepareInvoiceDates();
                } else {
                    throw new CustomException("You don't have permission to unfreeze this invoice");
                }
            } else {
                throw new CustomException("Invoice Code cannot be empty.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public void prepareDatas() throws Exception {
        this.prepareFranchises();
        this.prepareInvoiceDates();
    }

    private void prepareInvoiceDates() throws Exception {
        // Get list frozen invoices' dates to do unfreeze
        List<String> franchiseCodes = new ArrayList<>();
        List<String> invoiceDates = new LinkedList<>();
        InvoiceFilter filter = new InvoiceFilter();
        if (StringUtils.isBlank(this.getFranchiseCode())) {
            for (FranchiseInfoModel franchise : this.getFranchises()) {
                franchiseCodes.add(franchise.getCode());
            }
        } else {
            filter.setFranchiseCode(this.getFranchiseCode());
            franchiseCodes.add(this.getFranchiseCode());
        }
        filter.setFranchiseCodes(franchiseCodes);
        IInvoiceService invoiceService = new InvoiceServiceImp();
        List<InvoiceVo> invoiceDatesVo = invoiceService.getInvoiceDatesToUnfreeze(filter);
        List<InvoiceModel> invoiceDatesModel = ModelUtils.createListModelFromVo(invoiceDatesVo, InvoiceModel.class);
        for (InvoiceModel invoiceModel : invoiceDatesModel) {
            if (invoiceModel != null && StringUtils.isNotBlank(invoiceModel.getInvoiceDate())) {
                invoiceDates.add(invoiceModel.getInvoiceDate());
            }
        }
        this.setListInvoiceDates(invoiceDates);
    }

    private List<String> buildFranchisesList() throws Exception {
        List<String> franchiseCodes = new ArrayList<>();
        if (StringUtils.isBlank(this.getFranchiseCode())) {
            this.prepareFranchises();
            for (FranchiseInfoModel franchise : this.getFranchises()) {
                franchiseCodes.add(franchise.getCode());
            }
        } else {
            franchiseCodes.add(this.getFranchiseCode());
        }
        return franchiseCodes;
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
