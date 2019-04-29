package com.gms.xms.weblib.controller.webship.invoices;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.InvoiceFilter;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.render.invoice.IInvoiceRender;
import com.gms.xms.workflow.render.invoice.InvoiceRenderImp;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Posted from InvoicesController
 * <p>
 * Author DatTV Date Jul 11, 2015 9:32:44 AM
 */
public class InvoicesController extends JsonBaseController {

    private static final long serialVersionUID = 6050622544100144538L;

    private Paging<InvoiceModel> outstandingList;
    private Paging<InvoiceModel> paidList;
    private InvoiceModel outstandingTotal;
    private InvoiceModel paidTotal;
    private String page;
    private String pageSize;
    private String invoiceCode;

    private List<String> pageSizeList;
    private InputStream stream;
    private String fileName;

    public String show() {
        this.setPageTitle("Invoices");
        this.setBreadCrumb("Invoices");
        return doOutstanding();
    }

    public String doOutstanding() {
        preparePageSizeList();
        IInvoiceService invoiceService = new InvoiceServiceImp();

        try {
            InvoiceFilter filter = this.buildOutstandingFilter();
            List<InvoiceVo> outstandingInvoices = invoiceService.getOutstandingInvoicesByCustCode(filter);
            List<InvoiceModel> invoiceModels = ModelUtils.createListModelFromVo(outstandingInvoices, InvoiceModel.class);
            outstandingList.setRecords(invoiceModels);
            if (outstandingList.getCurrentPage() == outstandingList.getLastPage()) {
                InvoiceVo invoiceVo = invoiceService.getOutstandingInvoiceTotalByCustCode(filter);
                outstandingTotal = ModelUtils.createModelFromVo(invoiceVo, InvoiceModel.class);
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }

        return "success";
    }

    public String doPaid() {
        preparePageSizeList();
        IInvoiceService invoiceService = new InvoiceServiceImp();

        try {
            InvoiceFilter filter = this.buildPaidFilter();
            List<InvoiceVo> paidInvoices = invoiceService.getPaidInvoicesByCustCode(filter);
            List<InvoiceModel> invoiceModels = ModelUtils.createListModelFromVo(paidInvoices, InvoiceModel.class);
            paidList.setRecords(invoiceModels);
            if (paidList.getCurrentPage() == paidList.getLastPage()) {
                InvoiceVo invoiceVo = invoiceService.getPaidInvoiceTotalByCustCode(filter);
                paidTotal = ModelUtils.createModelFromVo(invoiceVo, InvoiceModel.class);
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            log.error(e);
        }

        return "success";
    }

    public String exportPdf() {
        try {
            InvoiceFilter filter = new InvoiceFilter();
            filter.setInvoiceCode(this.invoiceCode);
            filter.setCustomerCode(this.getWebshipLoginInfo().getCustomerCode());

            this.fileName = "webship_invoices_" + this.invoiceCode;
            String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + ".html";
            String pdfFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + ".pdf";

            IInvoiceRender render = new InvoiceRenderImp(this.getAddInfoMap());
            render.renderWebshipInvoiceHtmlReport(filter, htmlFilePath);
            AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);
            this.setStream(new FileInputStream(new File(pdfFilePath)));
            return "export";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    protected InvoiceFilter buildOutstandingFilter() {
        IInvoiceService invoiceService = new InvoiceServiceImp();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pageSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int page = 0;
        try {
            page = Integer.parseInt(this.page);
        } catch (Exception ex) {
            page = 1;
        }

        Long customerCode = this.getWebshipLoginInfo().getCustomerCode();

        InvoiceFilter filter = new InvoiceFilter();
        filter.setCustomerCode(customerCode);
        long recordCount = 0;
        try {
            recordCount = invoiceService.getOutstandingInvoicesCountByCustCode(filter);
        } catch (Exception ex) {
            recordCount = 0;
        }

        Paging<InvoiceModel> paging = new Paging<InvoiceModel>(page, nLinks, pageSize, recordCount);
        this.setOutstandingList(paging);

        filter.setRecordSize(paging.getPageSize());
        filter.setStartRecord((paging.getCurrentPage() - 1) * paging.getPageSize());

        return filter;
    }

    protected InvoiceFilter buildPaidFilter() {
        IInvoiceService invoiceService = new InvoiceServiceImp();
        InvoiceFilter filter = new InvoiceFilter();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pageSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int page = 0;
        try {
            page = Integer.parseInt(this.page);
        } catch (Exception ex) {
            page = 1;
        }

        Long customerCode = this.getWebshipLoginInfo().getCustomerCode();
        filter.setCustomerCode(customerCode);
        long recordCount = 0;
        try {
            recordCount = invoiceService.getPaidInvoicesCountByCustCode(filter);
        } catch (Exception ex) {
            recordCount = 0;
        }

        Paging<InvoiceModel> paging = new Paging<InvoiceModel>(page, nLinks, pageSize, recordCount);
        this.setPaidList(paging);

        filter.setRecordSize(paging.getPageSize());
        filter.setStartRecord((paging.getCurrentPage() - 1) * paging.getPageSize());
        return filter;
    }

    protected void preparePageSizeList() {
        List<String> pageSizes = new ArrayList<String>();
        pageSizes.add("5");
        pageSizes.add("10");
        pageSizes.add("25");
        pageSizes.add("50");
        this.setPageSizeList(pageSizes);
    }

    public Paging<InvoiceModel> getOutstandingList() {
        return outstandingList;
    }

    public void setOutstandingList(Paging<InvoiceModel> outstandingList) {
        this.outstandingList = outstandingList;
    }

    public Paging<InvoiceModel> getPaidList() {
        return paidList;
    }

    public void setPaidList(Paging<InvoiceModel> paidList) {
        this.paidList = paidList;
    }

    public InvoiceModel getOutstandingTotal() {
        return outstandingTotal;
    }

    public void setOutstandingTotal(InvoiceModel outstandingTotal) {
        this.outstandingTotal = outstandingTotal;
    }

    public InvoiceModel getPaidTotal() {
        return paidTotal;
    }

    public void setPaidTotal(InvoiceModel paidTotal) {
        this.paidTotal = paidTotal;
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

    public List<String> getPageSizeList() {
        return pageSizeList;
    }

    public void setPageSizeList(List<String> pageSizeList) {
        this.pageSizeList = pageSizeList;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}