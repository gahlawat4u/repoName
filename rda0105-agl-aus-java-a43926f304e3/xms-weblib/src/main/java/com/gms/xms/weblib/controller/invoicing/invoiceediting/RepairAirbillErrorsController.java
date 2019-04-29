package com.gms.xms.weblib.controller.invoicing.invoiceediting;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.invoicing.MoveInvoiceDateFilter;
import com.gms.xms.filter.invoicing.RepairAirbillErrorFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.invoicing.AirbillErrorModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.service.invoicing.IRepairAirbillErrorService;
import com.gms.xms.persistence.service.invoicing.RepairAirbillErrorServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.ShipmentBillingVo;
import com.gms.xms.txndb.vo.invoicing.AirbillErrorVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.render.airbillerror.AirbillErrorRenderImp;
import com.gms.xms.workflow.render.airbillerror.IAirbillErrorRender;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Posted from RepairAirbillErrorsController
 * <p>
 * Author dattrinh Mar 7, 2016 1:41:15 PM
 */
public class RepairAirbillErrorsController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    // Search filter property.
    private String carrier;
    private String fromDate;
    private String toDate;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    private List<ServiceModel> services;
    private Paging<AirbillErrorModel> airbills;

    private String showSenderAddress;
    private String showReceiverAddress;
    private String showAccount;

    private String airbillNumber;

    // Move invoice date filter property.
    private String checkAirbills;
    private String invoiceDate;

    // Assign check airbills property.
    private String customerCode;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String show() {
        try {
            this.setPageSizes(this.buildPageSizeList());
            this.prepareServices();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            this.prepareAirbillErrorList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String showDeleteAirbillError() {
        try {

        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String deleteAirbillError() {
        try {
            ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
            ShipmentBillingVo shipmentBillingVo = new ShipmentBillingVo();
            shipmentBillingVo.setAirbillNumber(this.getAirbillNumber());
            shipmentBillingDao.deleteAirbillError(this.getAddInfoMap(), shipmentBillingVo);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String moveInvoiceDate() {
        try {
            // Prepare filter to move.
            MoveInvoiceDateFilter filter = new MoveInvoiceDateFilter();
            if (StringUtils.isBlank(this.getCheckAirbills())) {
                throw new CustomException("No airbill to move");
            }
            String airbillList = this.getCheckAirbills();
            airbillList = airbillList.replace(",", "','");
            airbillList = "'" + airbillList + "'";
            filter.setAirbillList(airbillList);
            Date invoiceDate = DateUtils.convertStringToDate(this.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (invoiceDate == null) {
                throw new CustomException("Please select invoice date to move.");
            }
            filter.setInvoiceDate(invoiceDate);
            // Move invoice date.
            IRepairAirbillErrorService airbillErrorService = new RepairAirbillErrorServiceImp();
            airbillErrorService.moveInvoiceDate(this.getAddInfoMap(), filter);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void assignAirbills() {
        try {
            // Valid customer code and airbill list.
            if (StringUtils.isBlank(this.getCustomerCode())) {
                throw new CustomException("Please input customer code to assign checked airbills.");
            }
            if (StringUtils.isBlank(this.getCheckAirbills())) {
                throw new CustomException("Please choose airbills for assigning.");
            }
            // Call workflow to assign airbills to the given customer.
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.CUSTOMER_CODE, this.getCustomerCode());
            String airbillList = this.getCheckAirbills();
            airbillList = airbillList.replace(",", "','");
            airbillList = "'" + airbillList + "'";
            context.put(Attributes.AIRBILL_NUMBER_LIST, airbillList);
            context.put(Attributes.WFP_NAME, "Wfl-AssignErrorAirbills");
            context = WorkFlowManager2.getInstance().process(context);
            // Check if have error.
            if (ErrorCode.ERROR.equalsIgnoreCase(context.getString(Attributes.ERROR_CODE))) {
                throw new Exception(context.getString(Attributes.ERROR_MESSAGE));
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    public String doExportHtml() {
        try {
            RepairAirbillErrorFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            IAirbillErrorRender render = new AirbillErrorRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/airbill_error_" + AppUtils.createMessageReference() + ".html";

            Boolean showSenderAddress = false;
            Boolean showReceiverAddress = false;
            Boolean showAccount = false;
            showSenderAddress = !StringUtils.isBlank(this.getShowSenderAddress()) ? Boolean.parseBoolean(this.getShowSenderAddress()) : showSenderAddress;
            showReceiverAddress = !StringUtils.isBlank(this.getShowReceiverAddress()) ? Boolean.parseBoolean(this.getShowReceiverAddress()) : showReceiverAddress;
            showAccount = !StringUtils.isBlank(this.getShowAccount()) ? Boolean.parseBoolean(this.getShowAccount()) : showAccount;

            render.generateHtmlFile(filter, outputFilePath, showSenderAddress, showReceiverAddress, showAccount);
            this.setHtmlExportString(AppUtils.readUTF8File2String(outputFilePath));
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportXls() {
        try {
            RepairAirbillErrorFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            IAirbillErrorRender render = new AirbillErrorRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/airbill_error" + AppUtils.createMessageReference() + ".xls";

            Boolean showSenderAddress = false;
            Boolean showReceiverAddress = false;
            Boolean showAccount = false;
            showSenderAddress = !StringUtils.isBlank(this.getShowSenderAddress()) ? Boolean.parseBoolean(this.getShowSenderAddress()) : showSenderAddress;
            showReceiverAddress = !StringUtils.isBlank(this.getShowReceiverAddress()) ? Boolean.parseBoolean(this.getShowReceiverAddress()) : showReceiverAddress;
            showAccount = !StringUtils.isBlank(this.getShowAccount()) ? Boolean.parseBoolean(this.getShowAccount()) : showAccount;
            render.generateXlsFile(filter, outputFilePath, showSenderAddress, showReceiverAddress, showAccount);
            this.setFileName("airbill_error.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareAirbillErrorList() throws Exception {
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Prepare filter.
        RepairAirbillErrorFilter filter = this.buildFilter();
        IRepairAirbillErrorService airbillErrorService = new RepairAirbillErrorServiceImp();
        // Get record count.
        long recordCount = airbillErrorService.countAirbillErrorByFilter(filter);
        // Build paging object.
        Paging<AirbillErrorModel> paging = new Paging<AirbillErrorModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get airbill error list.
        List<AirbillErrorVo> airbillErrorVos = airbillErrorService.getAirbillErrorByFilter(filter);
        List<AirbillErrorModel> airbillErrorModels = ModelUtils.createListModelFromVo(airbillErrorVos, AirbillErrorModel.class);
        paging.setRecords(airbillErrorModels);
        // Set airbill error list.
        this.setAirbills(paging);
    }

    protected RepairAirbillErrorFilter buildFilter() throws Exception {
        RepairAirbillErrorFilter filter = new RepairAirbillErrorFilter();
        // Set carrier.
        Long carrier = null;
        try {
            carrier = StringUtils.isBlank(this.getCarrier()) ? null : Long.valueOf(this.getCarrier());
        } catch (Exception e) {
            throw new CustomException("Carrier must be an integer number.");
        }
        filter.setCarrier(carrier);
        // Set from date.
        Date from = null;
        try {
            from = StringUtils.isBlank(this.getFromDate()) ? null : DateUtils.convertStringToDate(this.getFromDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (from == null) {
                throw new CustomException("Invalid from date.");
            }
            filter.setFromDate(from);
        } catch (Exception e) {
            throw new CustomException("Invalid from date.");
        }
        // Set to date.
        Date to = null;
        try {
            to = StringUtils.isBlank(this.getToDate()) ? null : DateUtils.convertStringToDate(this.getToDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (to == null) {
                throw new CustomException("Invalid to date.");
            }
            filter.setToDate(to);
        } catch (Exception e) {
            throw new CustomException("Invalid to date.");
        }
        // Check date range.
        if (to.before(from)) {
            throw new CustomException("Start date must before End date.");
        }
        // Set page.
        Integer page = null;
        try {
            page = StringUtils.isBlank(this.getPage()) ? 1 : Integer.valueOf(this.getPage());
            if (page <= 0) {
                throw new CustomException("The page number cannot be less than 0.");
            }
            filter.setPage(page);
        } catch (Exception e) {
            throw new CustomException("Invalid page number.");
        }
        // Set page size.
        Integer pageSize = null;
        try {
            pageSize = StringUtils.isBlank(this.getPageSize()) ? Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) : Integer.valueOf(this.getPageSize());
            if (pageSize <= 0) {
                throw new CustomException("The page size cannot be less than 0.");
            }
            filter.setPageSize(pageSize);
        } catch (Exception e) {
            throw new CustomException("Invalid page size number.");
        }
        // Set order type.
        Integer order = null;
        try {
            order = StringUtils.isBlank(this.getOrderType()) ? 0 : Integer.valueOf(this.getOrderType());
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            filter.setOrderType(order);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "airbill_number" : this.getOrderField());
        return filter;
    }

    protected void prepareServices() throws Exception {
        IServiceService iServiceService = new ServiceServiceImp();
        List<ServiceVo> serviceVos = iServiceService.selectAll();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setServices(serviceModels);
    }

    public List<ServiceModel> getServices() {
        return services;
    }

    public void setServices(List<ServiceModel> services) {
        this.services = services;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Paging<AirbillErrorModel> getAirbills() {
        return airbills;
    }

    public void setAirbills(Paging<AirbillErrorModel> airbills) {
        this.airbills = airbills;
    }

    public String getCheckAirbills() {
        return checkAirbills;
    }

    public void setCheckAirbills(String checkAirbills) {
        this.checkAirbills = checkAirbills;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }

    public String getShowSenderAddress() {
        return showSenderAddress;
    }

    public void setShowSenderAddress(String showSenderAddress) {
        this.showSenderAddress = showSenderAddress;
    }

    public String getShowReceiverAddress() {
        return showReceiverAddress;
    }

    public void setShowReceiverAddress(String showReceiverAddress) {
        this.showReceiverAddress = showReceiverAddress;
    }

    public String getShowAccount() {
        return showAccount;
    }

    public void setShowAccount(String showAccount) {
        this.showAccount = showAccount;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }
}
