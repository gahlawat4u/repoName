package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.admin.csvinvoices.CsvInvoicesModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.service.invoice.IInvoiceService;
import com.gms.xms.persistence.service.invoice.InvoiceServiceImp;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.invoicing.csvinvoices.CsvInvoicesVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.render.csvinvoices.CsvInvoicesRenderImp;
import com.gms.xms.workflow.render.csvinvoices.ICsvInvoicesRender;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

public class CsvInvoicesController extends AdminJsonBaseController {
    private static final long serialVersionUID = -3767419791238308571L;
    private String franchiseCode;
    private String invoiceId;
    private List<CsvInvoicesModel> invoicesList;

    private String fileName;
    private InputStream stream;

    public String show() {
        try {
            this.prepareFranchises();
            String franchiseCode = this.getFranchises().get(0).getCode();
            buildInvoicesList(franchiseCode);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getListInvoices() {
        try {
            if (!StringUtils.isBlank(this.getFranchiseCode())) {
                String franchiseCode = this.getFranchiseCode();
                buildInvoicesList(franchiseCode);
            } else {
                throw new CustomException("Cannot get invoices because franchise code is invalid.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportCsv() {
        try {
            if (!StringUtils.isBlank(this.getInvoiceId()) && !StringUtils.isBlank(this.getFranchiseCode())) {
                ICsvInvoicesRender render = new CsvInvoicesRenderImp(this.getAddInfoMap());
                InvoiceDao invoiceDao = new InvoiceDao();
                InvoiceVo invoiceVo = invoiceDao.selectById(Long.parseLong(this.getInvoiceId()));
                if (invoiceVo != null) {
                    this.setFileName(invoiceVo.getInvoiceCode() + ".csv");
                    String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + this.getFileName();
                    String franchiseCode = this.getFranchiseCode() + "00001";
                    render.generateCsvFile(Long.parseLong(this.getInvoiceId()), franchiseCode, outputFilePath);
                    this.setStream(new FileInputStream(new File(outputFilePath)));
                    response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                    return "export";
                } else {
                    throw new CustomException("Cannot find this invoice.");
                }
            } else {
                throw new CustomException("InvoiceId/Franchise code cannot be empty.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }

        return "success";
    }

    private void buildInvoicesList(String franchiseCode) throws Exception {
        IInvoiceService invoiceService = new InvoiceServiceImp();
        List<CsvInvoicesVo> invoicesVos = invoiceService.getListCsvInvoicesByFranchiseCode(franchiseCode);
        List<CsvInvoicesModel> invoicesModels = ModelUtils.createListModelFromVo(invoicesVos, CsvInvoicesModel.class);
        if (invoicesModels.size() == 0) {
            CsvInvoicesModel csvInvoicesModel = new CsvInvoicesModel();
            csvInvoicesModel.setInvoiceStr(this.getLocalizationText("No invoices"));
            invoicesModels.add(csvInvoicesModel);
        }
        this.setInvoicesList(invoicesModels);
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public List<CsvInvoicesModel> getInvoicesList() {
        return invoicesList;
    }

    public void setInvoicesList(List<CsvInvoicesModel> invoicesList) {
        this.invoicesList = invoicesList;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
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
}
