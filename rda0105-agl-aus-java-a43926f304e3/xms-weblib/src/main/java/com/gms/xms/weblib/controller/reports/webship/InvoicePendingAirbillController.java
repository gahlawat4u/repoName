package com.gms.xms.weblib.controller.reports.webship;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.reports.webship.InvoicePendingAirbillFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.reports.webship.InvoicePendingAirbillModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.report.webship.IInvoicePendingAirbillService;
import com.gms.xms.persistence.service.report.webship.InvoicePendingAirbillServiceImp;
import com.gms.xms.persistence.service.webship.shipment.IShipmentService;
import com.gms.xms.persistence.service.webship.shipment.ShipmentServiceImp;
import com.gms.xms.txndb.vo.reports.webship.InvoicePendingAirbillVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.render.report.webship.IInvoicePendingAirbillRender;
import com.gms.xms.workflow.render.report.webship.InvoicePendingAirbillRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Posted from InvoicePendingAirbillController
 * <p>
 * Author DatTV Dec 4, 2015
 */
public class InvoicePendingAirbillController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String franchiseCode;
    private String startDate;
    private String endDate;
    private String orderField;
    private String orderType;
    private String page;
    private String pageSize;
    private Paging<InvoicePendingAirbillModel> report;
    private List<String> deletedAirbills;

    private InputStream stream;
    private String fileName;
    private String htmlExportString;

    private String customerCode;
    private String customerName;
    private String airbillNumber;
    private String carrierName;
    private String service;
    private String weight;
    private String pieces;
    private String createDate;
    private String shipDate;
    private String destination;
    private String destinationCountry;

    public String show() {
        try {
            prepareFranchises();
            preparePageSizes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doReport() {
        try {
            prepareReportData();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void deleteShipments() {
        if (deletedAirbills == null || deletedAirbills.size() == 0) {
            return;
        }
        IShipmentService shipmentService = new ShipmentServiceImp();
        try {
            shipmentService.deleteShipments(this.getAddInfoMap(), deletedAirbills);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    public String print() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/invoice_pending_airbill_" + this.franchiseCode + ".html";
                String realPath = WebUtils.getContextPathURL(request);
                InvoicePendingAirbillFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                IInvoicePendingAirbillRender render = new InvoicePendingAirbillRenderImp(this.getAddInfoMap());
                render.renderInvoicePendingAirbillHtmlFile(filter, htmlFilePath, realPath);
                this.setHtmlExportString(AppUtils.readUTF8File2String(htmlFilePath));
                return "export";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String export() {
        try {
            if (this.request.getMethod().equalsIgnoreCase("post")) {
                this.fileName = "invoice_pending_airbill";
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
                InvoicePendingAirbillFilter filter = this.buildFilter();
                filter.setPage(null);
                filter.setPageSize(null);
                IInvoicePendingAirbillRender render = new InvoicePendingAirbillRenderImp(this.getAddInfoMap());
                render.renderInvoicePendingAirbillXlsFile(filter, outPutFilePath);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void prepareReportData() throws Exception {
        IInvoicePendingAirbillService invoiceService = new InvoicePendingAirbillServiceImp();
        InvoicePendingAirbillFilter filter = this.buildFilter();
        // Prepare for paging
        Long recordCount = invoiceService.countInvoicePendingAirbillReport(filter);
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<InvoicePendingAirbillModel> paging = new Paging<InvoicePendingAirbillModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get the invoice pending airbill report data
        List<InvoicePendingAirbillVo> historyVos = invoiceService.getInvoicePendingAirbillReport(filter);
        List<InvoicePendingAirbillModel> historyModels = ModelUtils.createListModelFromVo(historyVos, InvoicePendingAirbillModel.class);
        paging.setRecords(historyModels);
        // Set the invoice pending airbill report data
        this.setReport(paging);
    }

    private InvoicePendingAirbillFilter buildFilter() throws Exception {
        InvoicePendingAirbillFilter filter = new InvoicePendingAirbillFilter();
        // Set franchise code
        franchiseCode = StringUtils.isBlank(franchiseCode) ? "All" : franchiseCode;
        filter.setFranchiseList(this.buildFranchiseCodeList(franchiseCode));
        // Set start date
        if (StringUtils.isBlank(startDate)) {
            throw new Exception("Please choose the start date");
        }
        Date start = null;
        Date end = null;
        try {
            start = DateUtils.convertStringToDate(startDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (start == null) {
            throw new Exception("Invalid start date format");
        }
        // Set end date
        if (StringUtils.isBlank(endDate)) {
            throw new Exception("Please choose the end date");
        }
        try {
            end = DateUtils.convertStringToDate(endDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        if (end == null) {
            throw new Exception("Invalid end date format");
        }
        // The end date must be after start date
        if (end.before(start)) {
            throw new Exception("The start date must be before the end date");
        }
        filter.setStartDate(start);
        filter.setEndDate(end);
        // Default sort field is customer_code
        orderField = StringUtils.isBlank(orderField) ? "customer_code" : orderField;
        filter.setOrderField(orderField);
        // Default sort type is 0 (ascending)
        orderType = StringUtils.isBlank(orderType) ? "0" : orderType;
        filter.setOrderType(Integer.valueOf(orderType));
        // Set default page
        page = StringUtils.isBlank(page) ? "0" : page;
        filter.setPage(Integer.valueOf(page));
        // Set default page size
        pageSize = StringUtils.isBlank(pageSize) ? AppConstants.APP_SETTINGS.getDefaultPageSize() : pageSize;
        filter.setPageSize(Integer.valueOf(pageSize));

        // Set search filters
        filter.setCustomerCode(this.getCustomerCode());
        filter.setCustomerName(this.getCustomerName());
        filter.setAirbillNumber(this.getAirbillNumber());
        filter.setCarrierName(this.getCarrierName());
        filter.setService(this.getService());
        filter.setDestination(this.getDestination());
        filter.setDestinationCountry(this.getDestinationCountry());
        if (!StringUtils.isBlank(this.getWeight())) {
            try {
                filter.setWeight(Float.valueOf(this.getWeight()));
            } catch (Exception e) {
                throw new CustomException("Invalid weight value.");
            }
        }
        if (!StringUtils.isBlank(this.getPieces())) {
            try {
                filter.setPieces(Integer.valueOf(this.getPieces()));
            } catch (Exception e) {
                throw new CustomException("Invalid pieces value.");
            }
        }
        if (!StringUtils.isBlank(this.getCreateDate())) {
            Date createDate = DateUtils.convertStringToDate(this.getCreateDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (createDate == null) {
                throw new CustomException("Invalid create date.");
            } else {
                filter.setCreateDate(createDate);
            }
        }
        if (!StringUtils.isBlank(this.getShipDate())) {
            Date shipDate = DateUtils.convertStringToDate(this.getShipDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (shipDate == null) {
                throw new CustomException("Invalid ship date.");
            } else {
                filter.setShipDate(shipDate);
            }
        }
        return filter;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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

    public Paging<InvoicePendingAirbillModel> getReport() {
        return report;
    }

    public void setReport(Paging<InvoicePendingAirbillModel> report) {
        this.report = report;
    }

    public List<String> getDeletedAirbills() {
        return deletedAirbills;
    }

    public void setDeletedAirbills(List<String> deletedAirbills) {
        this.deletedAirbills = deletedAirbills;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getPieces() {
        return pieces;
    }

    public void setPieces(String pieces) {
        this.pieces = pieces;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getShipDate() {
        return shipDate;
    }

    public void setShipDate(String shipDate) {
        this.shipDate = shipDate;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }
}
