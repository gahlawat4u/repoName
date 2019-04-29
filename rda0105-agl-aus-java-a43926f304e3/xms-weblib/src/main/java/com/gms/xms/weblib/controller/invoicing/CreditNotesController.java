package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.filter.invoicing.CreditNoteShowListFilter;
import com.gms.xms.model.CreditNoteModel;
import com.gms.xms.model.CreditNotesGSTSummaryModel;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.invoicing.*;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.InvoiceNumberingService;
import com.gms.xms.txndb.vo.CreditNoteFilter;
import com.gms.xms.txndb.vo.CreditNoteInfoVo;
import com.gms.xms.txndb.vo.CreditNoteVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.client.CreditNoteClient;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import com.gms.xms.workflow.client.integration.request.CreditNoteRequest;
import com.gms.xms.workflow.client.integration.response.CreditNoteResponse;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Posted from CreditNotesController
 * <p>
 * Author TanDT Date May 20, 2015
 */
public class CreditNotesController extends JsonBaseController {
    private static final long serialVersionUID = 4923595193899566440L;

    private List<String> listCreditNoteDate;
    private List<String> listCreditCode;
    private List<String> franchiseCodeList;
    private CreditNoteInfoModel creditNoteInfoModel;
    private CreditNoteInfoSystemAdminModel systemAdminInfo;
    private List<CreditNoteShowListModel> listAdjustment;
    private CreditNotesPageModel creditNotesPageModel;
    private String creditCode;
    private String grandTotal;
    private List<CreditNotesGSTSummaryModel> gstSummaryList;
    private String fileName;
    private InputStream stream;

    /**
     * Show index page CreditNote
     *
     * @return success
     */
    public String index() {
        try {
            this.prepareListDateCreditNote();
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    /**
     * Show CreditNote Detail by CreditCode
     *
     * @return success
     */
    public String showCreditNoteDetail() {
        if (this.creditNotesPageModel != null) {
            try {
                ManageCreditNoteModel model = this.prepareCreditNoteDetail(this.creditNotesPageModel.getCreditNoteCode());
                CreditNoteInfoModel creditNoteInfo = model.getCreditNoteInfo();
                this.setListAdjustment(model.getListCreditNoteAdj());
                this.setSystemAdminInfo(model.getInfoSystemAdmin());
                this.setCreditNoteInfoModel(creditNoteInfo);
                Float grandTt = 0F;
                if (listAdjustment != null && listAdjustment.size() > 0) {
                    for (CreditNoteShowListModel adjustment : listAdjustment) {
                        grandTt += Float.parseFloat(adjustment.getShipmentAmount()) + Float.parseFloat(adjustment.getGstAmount());
                    }
                }
                this.setGrandTotal(String.valueOf(HelperUtils.roundFloatBy2(grandTt)));
                this.setGstSummaryList(model.getGstSummary());
            } catch (Exception e) {
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage(e.getMessage());
                log.error(e.getMessage(), e);
            }
        }

        return "success";
    }

    public String doExportHtml() {
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                if (creditNotesPageModel != null && creditNotesPageModel.getCreditNoteCode() != null) {
                    String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/credit_notes_export" + creditNotesPageModel.getCreditNoteCode() + ".html";
                    String realPath = WebUtils.getContextPathURL(request);
                    creditNoteClient.renderHtmlPrintPreviewFile(creditNotesPageModel.getCreditNoteCode(), htmlFilePath, realPath);
                    creditNotesPageModel.setHtmlExportContent(AppUtils.readUTF8File2String(htmlFilePath));
                } else {
                    return "redirect";
                }
            } catch (Exception e) {
                log.error(e);
                return "redirect";
            }
        } else {
            return "redirect";
        }

        return "export";
    }

    public String doExportPdf() {
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        try {
            if (creditNotesPageModel != null && creditNotesPageModel.getCreditNoteCode() != null) {
                String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/credit_notes_export_" + creditNotesPageModel.getCreditNoteCode() + ".html";
                String pdfFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/credit_notes_export_" + creditNotesPageModel.getCreditNoteCode() + ".pdf";
                String realPath = request.getSession().getServletContext().getRealPath("");

                this.setFileName(creditNotesPageModel.getCreditNoteCode() + ".pdf");
                creditNoteClient.renderHtmlFile(creditNotesPageModel.getCreditNoteCode(), htmlFilePath, realPath);
                AppUtils.createPDFFromHTMLWithFont(htmlFilePath, pdfFilePath, "arial", true);
                this.setStream(new FileInputStream(new File(pdfFilePath)));
            } else {
                return "redirect";
            }
        } catch (Exception e) {
            log.error(e);
            return "redirect";
        }

        return "export";
    }

    public String doExportHtmlAll() {
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        if (request.getMethod().equalsIgnoreCase("post")) {
            try {
                if (creditNotesPageModel != null && creditNotesPageModel.getCreditNoteCodes() != null) {
                    String htmlFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/credit_notes_export_all.html";
                    String realPath = WebUtils.getContextPathURL(request);
                    List<String> creditNoteCodes = GsonUtils.fromGson(creditNotesPageModel.getCreditNoteCodes(), new TypeToken<List<String>>() {
                    }.getType());
                    creditNoteCodes.remove(creditNoteCodes.size() - 1);
                    creditNoteClient.renderHtmlPrintPreviewFileAll(creditNoteCodes, htmlFilePath, realPath);
                    creditNotesPageModel.setHtmlExportContent(AppUtils.readUTF8File2String(htmlFilePath));
                } else {
                    return "redirect";
                }
            } catch (Exception e) {
                log.error(e);
                return "redirect";
            }
        } else {
            return "redirect";
        }
        return "export";
    }

    /**
     * Display list CreditNote Code By Date
     *
     * @return
     */
    public String showListCreditNotes() {
        if (this.creditNotesPageModel != null) {
            try {
                List<String> creditCodeList = new ArrayList<String>();
                if (!StringUtils.isBlank(this.creditNotesPageModel.getDateSelect())) {
                    List<CreditNoteVo> creditNoteVos = this.prepareListCreditNoteByDate(this.getCreditNotesPageModel().getDateSelect());
                    List<CreditNoteModel> listCreditNoteModel = ModelUtils.createListModelFromVo(creditNoteVos, CreditNoteModel.class);
                    for (CreditNoteModel model : listCreditNoteModel) {
                        creditCodeList.add(model.getCreditCode());
                    }
                } else if (!StringUtils.isBlank(this.creditNotesPageModel.getFranchiseCode())) {
                    List<CreditNoteVo> creditNoteVos = this.prepareListCreditNoteByFranchise(this.getCreditNotesPageModel().getFranchiseCode());
                    List<CreditNoteModel> listCreditNoteModel = ModelUtils.createListModelFromVo(creditNoteVos, CreditNoteModel.class);
                    for (CreditNoteModel model : listCreditNoteModel) {
                        creditCodeList.add(model.getCreditCode());
                    }
                } else if (!StringUtils.isBlank(this.creditNotesPageModel.getCustomerCode())) {
                    List<String> franchiseCodeList = prepareFranchiseCodeList();
                    String cusFranchiseCode = this.creditNotesPageModel.getCustomerCode().substring(0, 3);

                    if (!franchiseCodeList.contains(cusFranchiseCode)) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        this.addFieldError("creditNotesPageModel.customerCode", this.getLocalizationText("You do not have permission to get the credit notes of this customer."));
                        return "error";
                    }

                    List<CreditNoteVo> creditNoteVos = this.prepareListCreditNoteByCustomerCode(Long.parseLong(this.getCreditNotesPageModel().getCustomerCode()));
                    List<CreditNoteModel> listCreditNoteModel = ModelUtils.createListModelFromVo(creditNoteVos, CreditNoteModel.class);
                    for (CreditNoteModel model : listCreditNoteModel) {
                        creditCodeList.add(model.getCreditCode());
                    }
                }
                creditCodeList.add("Print all");
                this.setListCreditCode(creditCodeList);
                this.creditNotesPageModel.setCreditNoteCodes(GsonUtils.toGson(creditCodeList));
            } catch (Exception e) {
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage(e.getMessage());
                log.error(e);
            }
        }
        return "success";
    }

    public String chaneSearchFilterBy() {
        if (this.creditNotesPageModel != null) {
            try {
                String filterBy = this.getCreditNotesPageModel().getFilterBy();
                switch (filterBy) {
                    case "date":
                        prepareListDateCreditNote();
                        break;
                    case "franchise":
                        List<String> franchiseCodeList = prepareFranchiseCodeList();
                        this.setFranchiseCodeList(franchiseCodeList);
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage(e.getMessage());
                log.error(e);
            }

        }
        return "success";
    }

    /**
     * Put date for manage CreditNote Detail
     *
     * @param creditCode
     * @return ManageCreditNoteModel
     * @throws Exception
     */
    protected ManageCreditNoteModel prepareCreditNoteDetail(String creditCode) throws Exception {
        ManageCreditNoteModel manageCreditNoteModel = new ManageCreditNoteModel();
        CreditNoteClient client = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest request = new CreditNoteRequest();
        CreditNoteResponse response = new CreditNoteResponse();
        CreditNoteShowListFilter filter = new CreditNoteShowListFilter();
        filter.setCreditCode(creditCode);
        request.setCreditNoteCode(creditCode);
        response = client.getCreditNotesDetail(request);

        if (!ErrorCode.SUCCESS.equalsIgnoreCase(response.getErrorCode())) {
            throw new Exception(response.getErrorMessage());
        }

        CreditNoteInfoVo creditNoteInfoVo = response.getCreditNoteInfo();
        String defaultCountryId = SystemSettingCache.getInstance().getValueByKey(AppConstants.DEFAULT_ORIGIN_COUNTRY);
        if (defaultCountryId.equalsIgnoreCase("70")) { // FR invoice numbering
            InvoiceNumberingService invoiceNumberingService = new InvoiceNumberingService();
            if (creditNoteInfoVo.getStatus() > 0) {
                String newCreditNoteCode = invoiceNumberingService.getCodeWithCounter(creditNoteInfoVo.getCreateDate(), creditNoteInfoVo.getCreditCode(), creditNoteInfoVo.getCustomerCode());
                creditNoteInfoVo.setCreditCode(newCreditNoteCode);
            }
        }

        manageCreditNoteModel.setCreditNoteInfo(ModelUtils.createModelFromVo(creditNoteInfoVo, CreditNoteInfoModel.class));
        manageCreditNoteModel.setInfoSystemAdmin(ModelUtils.createModelFromVo(response.getInfoSystemAdminVo(), CreditNoteInfoSystemAdminModel.class));
        manageCreditNoteModel.setListCreditNoteAdj(ModelUtils.createListModelFromVo(response.getListAdjustment(), CreditNoteShowListModel.class));
        manageCreditNoteModel.setGstSummary(ModelUtils.createListModelFromVo(response.getCreditNotesGSTSummaryVos(), CreditNotesGSTSummaryModel.class));
        return manageCreditNoteModel;
    }

    /**
     * prepareListCreditNoteByDate
     *
     * @param date
     * @return
     * @throws Exception
     */
    protected List<CreditNoteVo> prepareListCreditNoteByDate(String date) throws Exception {
        CreditNoteClient client = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest request = new CreditNoteRequest();
        CreditNoteResponse response = new CreditNoteResponse();
        List<String> franchiseCodeList = this.prepareFranchiseCodeList();
        Date dateFomated = DateUtils.convertStringToDate(date, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        CreditNoteFilter filter = new CreditNoteFilter();
        filter.setFranchiseCodeList(franchiseCodeList);
        filter.setCreateDate(dateFomated);
        request.setCreditNoteFilter(filter);

        response = client.getCreditNotesList(request);

        if (!ErrorCode.SUCCESS.equalsIgnoreCase(response.getErrorCode())) {
            throw new Exception(response.getErrorMessage());
        }
        List<CreditNoteVo> creditNoteVos = response.getCreditNoteVos();
        return creditNoteVos;
    }

    protected List<CreditNoteVo> prepareListCreditNoteByFranchise(String franchiseCode) throws Exception {
        CreditNoteClient client = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest request = new CreditNoteRequest();
        CreditNoteResponse response = new CreditNoteResponse();
        List<String> franchiseCodeList = this.prepareFranchiseCodeList();

        CreditNoteFilter filter = new CreditNoteFilter();
        filter.setFranchiseCodeList(franchiseCodeList);
        filter.setFranchiseCode(franchiseCode);
        request.setCreditNoteFilter(filter);

        response = client.getCreditNotesList(request);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(response.getErrorCode())) {
            throw new Exception(response.getErrorMessage());
        }
        List<CreditNoteVo> creditNoteVos = response.getCreditNoteVos();
        return creditNoteVos;
    }

    protected List<CreditNoteVo> prepareListCreditNoteByCustomerCode(Long customerCode) throws Exception {
        CreditNoteClient client = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest request = new CreditNoteRequest();
        CreditNoteResponse response = new CreditNoteResponse();
        List<String> franchiseCodeList = this.prepareFranchiseCodeList();
        CreditNoteFilter filter = new CreditNoteFilter();
        filter.setFranchiseCodeList(franchiseCodeList);
        filter.setCustomerCode(customerCode);
        request.setCreditNoteFilter(filter);

        response = client.getCreditNotesList(request);
        if (!ErrorCode.SUCCESS.equalsIgnoreCase(response.getErrorCode())) {
            throw new Exception(response.getErrorMessage());
        }
        List<CreditNoteVo> creditNoteVos = response.getCreditNoteVos();
        return creditNoteVos;
    }

    /**
     * Prepare ListDate
     *
     * @throws Exception
     */
    protected void prepareListDateCreditNote() throws Exception {
        List<String> franchiseCodeList = this.prepareFranchiseCodeList();
        CreditNoteFilter creditNoteFilter = new CreditNoteFilter();
        creditNoteFilter.setFranchiseCodeList(franchiseCodeList);
        CreditNoteClient creditNoteClient = new CreditNoteClient(this.getAddInfoMap());
        CreditNoteRequest creditNoteRequest = new CreditNoteRequest();
        creditNoteRequest.setCreditNoteFilter(creditNoteFilter);

        CreditNoteResponse creditNoteResponse = creditNoteClient.getDateList(creditNoteRequest);
        this.setListCreditNoteDate(creditNoteResponse.getDateList());
    }

    /**
     * prepareFranchiseCodeList
     *
     * @return
     * @throws Exception
     */
    protected List<String> prepareFranchiseCodeList() throws Exception {
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        ManageAdjustmentClient manageAdjustmentClient = new ManageAdjustmentClient(this.getAddInfoMap());
        List<String> franchiseCodeList = new LinkedList<>();
        List<FranchiseInfoVo> franchiseInfoVos = manageAdjustmentClient.getFranchiseListManagedByUser(userId);
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        for (FranchiseInfoModel franchiseInfoModel : franchiseInfoModels) {
            franchiseCodeList.add(franchiseInfoModel.getCode());
        }
        return franchiseCodeList;
    }

    public List<String> getListCreditNoteDate() {
        return listCreditNoteDate;
    }

    public void setListCreditNoteDate(List<String> listCreditNoteDate) {
        this.listCreditNoteDate = listCreditNoteDate;
    }

    public List<String> getListCreditCode() {
        return listCreditCode;
    }

    public void setListCreditCode(List<String> listCreditCode) {
        this.listCreditCode = listCreditCode;
    }

    public CreditNoteInfoModel getCreditNoteInfoModel() {
        return creditNoteInfoModel;
    }

    public void setCreditNoteInfoModel(CreditNoteInfoModel creditNoteInfoModel) {
        this.creditNoteInfoModel = creditNoteInfoModel;
    }

    public CreditNoteInfoSystemAdminModel getSystemAdminInfo() {
        return systemAdminInfo;
    }

    public void setSystemAdminInfo(CreditNoteInfoSystemAdminModel systemAdminInfo) {
        this.systemAdminInfo = systemAdminInfo;
    }

    public List<CreditNoteShowListModel> getListAdjustment() {
        return listAdjustment;
    }

    public void setListAdjustment(List<CreditNoteShowListModel> listAdjustment) {
        this.listAdjustment = listAdjustment;
    }

    public String getCreditCode() {
        return creditCode;
    }

    public void setCreditCode(String creditCode) {
        this.creditCode = creditCode;
    }

    public String getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(String grandTotal) {
        this.grandTotal = grandTotal;
    }

    public CreditNotesPageModel getCreditNotesPageModel() {
        return creditNotesPageModel;
    }

    public void setCreditNotesPageModel(CreditNotesPageModel creditNotesPageModel) {
        this.creditNotesPageModel = creditNotesPageModel;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public List<String> getFranchiseCodeList() {
        return franchiseCodeList;
    }

    public void setFranchiseCodeList(List<String> franchiseCodeList) {
        this.franchiseCodeList = franchiseCodeList;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<CreditNotesGSTSummaryModel> getGstSummaryList() {
        return gstSummaryList;
    }

    public void setGstSummaryList(List<CreditNotesGSTSummaryModel> gstSummaryList) {
        this.gstSummaryList = gstSummaryList;
    }
}
