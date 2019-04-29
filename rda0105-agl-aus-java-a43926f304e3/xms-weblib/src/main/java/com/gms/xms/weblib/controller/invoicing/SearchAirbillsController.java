package com.gms.xms.weblib.controller.invoicing;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.*;
import com.gms.xms.model.admin.SearchAirbillShipmentTypeModel;
import com.gms.xms.model.admin.invoicing.searchairbill.SearchAirbillFilterModel;
import com.gms.xms.model.admin.invoicing.searchairbill.SearchAirbillModel;
import com.gms.xms.model.admin.invoicing.searchairbill.TotalSearchAirbillModel;
import com.gms.xms.model.massedit.MassEditOptionsModel;
import com.gms.xms.model.surchargelist.SurchargeListModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.PackageModel;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.persistence.dao.admin.SearchAirbillDao;
import com.gms.xms.persistence.service.accessorial.AccessorialServiceImp;
import com.gms.xms.persistence.service.accessorial.IAccessorialSerivce;
import com.gms.xms.persistence.service.admin.searchairbill.ISearchAirbillService;
import com.gms.xms.persistence.service.admin.searchairbill.SearchAirbillServiceImp;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.packagetype.IPackageService;
import com.gms.xms.persistence.service.packagetype.PackageServiceImp;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.user.IUserService;
import com.gms.xms.persistence.service.user.UserServiceImp;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.admin.SearchAirbillShipmentTypeVo;
import com.gms.xms.txndb.vo.invoicing.searchairbill.*;
import com.gms.xms.txndb.vo.surchargelist.SurchargeListVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.render.searchairbill.ISearchAirbillRender;
import com.gms.xms.workflow.render.searchairbill.SearchAirbillRenderImp;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * File Name: SearchAirbillsController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 17-03-2016 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.invoicing <br/>
 */
public class SearchAirbillsController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;
    private List<UserModel> listSalesRep;
    private List<ServiceModel> listCarriers;
    private List<CountryModel> listCountries;
    private List<SearchAirbillShipmentTypeModel> listShipmentType;
    private Map<String, String> listInvoiceStatus;
    private List<SurchargeListModel> listAccessorial;
    private List<PackageModel> listPackageTypes;
    private List<String> listInvoiceDates;
    private SearchAirbillFilterModel searchAirbillFilter;
    private List<ShipmentBillingModel> listImportDate;
    private Paging<SearchAirbillModel> listAirbill;
    private String franchiseList;
    private TotalSearchAirbillModel totalSearchAirbill;
    private List<MassEditOptionsModel> massEditOptions;

    private List<String> pageSizeList;

    // Parameter
    private String serviceId;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String show() {
        try {
            // Set default sorting properties.
            SearchAirbillFilter filter = new SearchAirbillFilter();
            filter.setPage(1);
            filter.setPageSize(Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()));
            filter.setOrderField("customer_code");
            filter.setOrderType(0);
            this.setSearchAirbillFilter(ModelUtils.createModelFromVo(filter, SearchAirbillFilterModel.class));
            this.prepareDatas();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String searchAirbill() {
        try {
            this.getCurrencySymbol();
            if (prepareSearchAirbilFilter() && validDateSearch()) {
                SearchAirbillDao dao = new SearchAirbillDao();
                SearchAirbillFilter filter = new SearchAirbillFilter();
                filter = ModelUtils.createVoFromModel(this.getSearchAirbillFilter(), SearchAirbillFilter.class);
                if (filter.getInvoiceStatus() != null && filter.getInvoiceStatus() == 0) {
                    filter.setInvoiceStatus(5);
                }
                TotalSearchAirbillVo totalSearchAirbillVo = dao.selectCountAirbillList(filter);
                Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
                Paging<SearchAirbillModel> paging = new Paging<>(filter.getPage(), nLinks, filter.getPageSize(), totalSearchAirbillVo.getTotalAirbills());
                this.setListAirbill(paging);
                List<SearchAirbillVo> airbillVos = dao.selectAirbillList(filter);
                refineAirbillList(airbillVos);
                this.setTotalSearchAirbill(ModelUtils.createModelFromVo(totalSearchAirbillVo, TotalSearchAirbillModel.class));
                listAirbill.setRecords(ModelUtils.createListModelFromVo(airbillVos, SearchAirbillModel.class));
            } else {
                setErrorCode(ErrorCode.FIELD_ERROR);
                return "error";
            }

        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    protected void refineAirbillList(List<SearchAirbillVo> airbillVos) throws Exception {
        if (airbillVos != null) {
            for (SearchAirbillVo searchAirbillVo : airbillVos) {
                if (searchAirbillVo != null) {
                    if (searchAirbillVo.getGstTaxAmount() == 0) {
                        searchAirbillVo.setGstTaxAmount(null);
                    }
                    searchAirbillVo.setTotalCharges(determineQuoteDetails(searchAirbillVo));
                }
            }
        }
    }

    protected double determineQuoteDetails(SearchAirbillVo airbillVo) throws Exception {
        // Calculate total charges.
        double baseCharge = airbillVo.getBaseCharge();
        if (baseCharge == 0) {
            airbillVo.setBaseCharge(null); // TBA
        }
        double insurance = airbillVo.getWithInSurance();
        double nonStandardCharge = airbillVo.getNonStandardCharge();
        double manualHandlingSurcharge = airbillVo.getManualHandlingSurcharge();
        double totalCharges = baseCharge;
        for (QuoteAirbillVo quoteAirbillVo : airbillVo.getListQuote()) {
            totalCharges += quoteAirbillVo.getAmount();
        }
        // New charge.
        QuoteAirbillVo charge;
        // Add additional protection charge if it have.
        if (airbillVo.getWithInSurance() != null && airbillVo.getWithInSurance() != 0) {
            charge = new QuoteAirbillVo();
            charge.setQuoteDescription("Additional Protection");
            charge.setAmount(airbillVo.getWithInSurance());
            airbillVo.getListQuote().add(charge);
        }
        // Add non-standard charge if it have.
        if (airbillVo.getNonStandardCharge() != null && airbillVo.getNonStandardCharge() != 0) {
            charge = new QuoteAirbillVo();
            charge.setQuoteDescription("Non-standard Shipping Charge");
            charge.setAmount(airbillVo.getNonStandardCharge());
            airbillVo.getListQuote().add(charge);
        }
        // Add GST charge.
        SystemSettingDao systemSettingDao = new SystemSettingDao();
        String vatRate;
        if (airbillVo.getSenderCountryCode().equalsIgnoreCase(airbillVo.getReceiverCountryCode())) {
            vatRate = systemSettingDao.getSystemSettingByName("Domestic Tax Percentage").getSettingValue();
        } else {
            vatRate = systemSettingDao.getSystemSettingByName("VAT percent based on Base Charge").getSettingValue();
        }
        double vat = 0.0;
        if (!StringUtils.isBlank(vatRate)) {
            String taxName = systemSettingDao.getSystemSettingByName("Tax Name").getSettingValue();
            vat = (totalCharges + insurance + nonStandardCharge + manualHandlingSurcharge) * Double.parseDouble(vatRate) / 100;
            charge = new QuoteAirbillVo();
            charge.setQuoteDescription(taxName);
            charge.setAmount(vat);
            airbillVo.getListQuote().add(charge);
        }

        // Add manual handling surcharge
        if ( airbillVo.getManualHandlingSurcharge() != null && airbillVo.getManualHandlingSurcharge() !=0){
            charge = new QuoteAirbillVo();
            charge.setQuoteDescription("Manual Handling Surcharge");
            charge.setAmount(airbillVo.getManualHandlingSurcharge());
            airbillVo.getListQuote().add(charge);
        }
        return totalCharges + insurance + nonStandardCharge + manualHandlingSurcharge + vat;
    }

    public String loadShipmentTypeByService() {
        try {
            ISearchAirbillService searchAirbillService = new SearchAirbillServiceImp();
            Integer serviceId = StringUtils.isBlank(this.getServiceId()) ? null : Integer.valueOf(this.getServiceId());
            List<SearchAirbillShipmentTypeVo> airbillShipmentTypeVos = searchAirbillService.getListShipmentTypeByServiceId(serviceId);
            List<SearchAirbillShipmentTypeModel> airbillShipmentTypeModels = ModelUtils.createListModelFromVo(airbillShipmentTypeVos, SearchAirbillShipmentTypeModel.class);
            this.setListShipmentType(airbillShipmentTypeModels);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public String loadAccessorialByService() {
        try {
            IAccessorialSerivce accessorialSerivce = new AccessorialServiceImp();
            AccessorialVo accessorialVo = new AccessorialVo();
            Long serviceId = StringUtils.isBlank(this.getServiceId()) ? null : Long.valueOf(this.getServiceId());
            accessorialVo.setCarrier(serviceId);
            List<SurchargeListVo> surchargeListVos = accessorialSerivce.selectAccessorialListByCarrier(accessorialVo);
            List<SurchargeListModel> surchargeListModels = ModelUtils.createListModelFromVo(surchargeListVos, SurchargeListModel.class);
            this.setListAccessorial(surchargeListModels);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public String doExportPdf() {
        try {
            if (prepareSearchAirbilFilter() && validDateSearch()) {
                this.setFileName("search_airbills.pdf");
                String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/search_airbills_" + AppUtils.createMessageReference() + ".html";
                String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/search_airbills_" + AppUtils.createMessageReference() + ".pdf";
                SearchAirbillFilter filter = new SearchAirbillFilter();
                filter = ModelUtils.createVoFromModel(this.getSearchAirbillFilter(), SearchAirbillFilter.class);
                if (filter.getInvoiceStatus() != null && filter.getInvoiceStatus() == 0) {
                    filter.setInvoiceStatus(5);
                }
                filter.setPage(null);
                filter.setPageSize(null);

                ISearchAirbillRender render = new SearchAirbillRenderImp(this.getAddInfoMap());
                render.generateHTMLForPDF(filter, outputFilePath);
                AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
                this.setStream(new FileInputStream(new File(pdfFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            } else {
                throw new CustomException("Please select a invoice to export.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExportCSV() {
        try {
            if (prepareSearchAirbilFilter() && validDateSearch()) {
                this.setFileName("search_airbills.csv");
                String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/search_airbills_" + AppUtils.createMessageReference() + ".csv";
                ISearchAirbillRender render = new SearchAirbillRenderImp(this.getAddInfoMap());
                SearchAirbillFilter filter = new SearchAirbillFilter();
                filter = ModelUtils.createVoFromModel(this.getSearchAirbillFilter(), SearchAirbillFilter.class);
                if (filter.getInvoiceStatus() != null && filter.getInvoiceStatus() == 0) {
                    filter.setInvoiceStatus(5);
                }
                filter.setPage(null);
                filter.setPageSize(null);
                this.determineAdminLevel();
                Integer userLevel = this.getAdminLevel();
                render.generateCSVFile(filter, userLevel, outputFilePath);
                this.setStream(new FileInputStream(new File(outputFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
                return "export";
            } else {
                throw new CustomException("Please select a invoice to export.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected List<SearchAirbillCsvVo> buildCSVVo(List<SearchAirbillVo> airbillVos) throws IllegalAccessException, InvocationTargetException {
        List<SearchAirbillCsvVo> airbillCsvVos = new LinkedList<>();
        for (SearchAirbillVo searchAirbillVo : airbillVos) {
            SearchAirbillCsvVo searchAirbillCsvVo = new SearchAirbillCsvVo();
            BeanUtilsBean.getInstance().getConvertUtils().register(false, false, 0);
            BeanUtils.copyProperties(searchAirbillCsvVo, searchAirbillVo);
            airbillCsvVos.add(searchAirbillCsvVo);
        }
        return airbillCsvVos;
    }

    protected boolean prepareSearchAirbilFilter() throws Exception {
        SearchAirbillFilterModel filterModelN = new SearchAirbillFilterModel();
        if (this.getSearchAirbillFilter() == null) {
            // Load default index history
            filterModelN.setPage("1");
            filterModelN.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
        } else {
            filterModelN = this.getSearchAirbillFilter();
            if (searchAirbillFilter.getFranchiseCode() != null && StringUtils.isNotEmpty(searchAirbillFilter.getFranchiseCode())) {
                searchAirbillFilter.setFranchiseCode(searchAirbillFilter.getFranchiseCode().trim());
                searchAirbillFilter.setFranchiseList(searchAirbillFilter.getFranchiseCode());
                try {
                    Long.parseLong(searchAirbillFilter.getFranchiseCode());
                } catch (Exception e) {
                    this.addFieldError("searchAirbillFilter.franchiseCode", "Franchise Code is not valid.");
                }
            } else {
                searchAirbillFilter.setFranchiseCode(null);
            }
            if (searchAirbillFilter.getCustomerCode() != null && StringUtils.isNotEmpty(searchAirbillFilter.getCustomerCode())) {
                searchAirbillFilter.setCustomerCode(searchAirbillFilter.getCustomerCode().trim());
                searchAirbillFilter.setFranchiseList(StringUtils.left(searchAirbillFilter.getCustomerCode(), 3));
                try {
                    Long.parseLong(searchAirbillFilter.getCustomerCode());
                } catch (Exception e) {
                    this.addFieldError("searchAirbillFilter.customerCode", "Customer Code is not valid.");
                }
            } else {
                searchAirbillFilter.setCustomerCode(null);
            }
            if (searchAirbillFilter.getInvoiceCode() != null && StringUtils.isNotEmpty(searchAirbillFilter.getInvoiceCode())) {
                searchAirbillFilter.setInvoiceCode(searchAirbillFilter.getInvoiceCode().trim());
                searchAirbillFilter.setFranchiseList(StringUtils.left(searchAirbillFilter.getInvoiceCode(), 3));
            } else {
                searchAirbillFilter.setInvoiceCode(null);
            }
            if (searchAirbillFilter.getAirbillNumber() != null && StringUtils.isNotEmpty(searchAirbillFilter.getAirbillNumber())) {
                searchAirbillFilter.setAirbillNumber(searchAirbillFilter.getAirbillNumber().trim());
            } else {
                searchAirbillFilter.setAirbillNumber(null);
            }
            if (searchAirbillFilter.getAirbillNumberList() != null && StringUtils.isNotEmpty(searchAirbillFilter.getAirbillNumberList())) {
                searchAirbillFilter.setAirbillNumberList(StringUtils.replace(searchAirbillFilter.getAirbillNumberList(), " ", ""));
                Map<String, String> replaceMap = new HashMap<String, String>();
                replaceMap.put("\t", "");
                String airbillList = AppUtils.replaceStringByMap(replaceMap, searchAirbillFilter.getAirbillNumberList());
                replaceMap.clear();
                replaceMap.put("\r\n", ",");
                airbillList = AppUtils.replaceStringByMap(replaceMap, airbillList);
                String listAirbillNumber[] = StringUtils.split(airbillList, ",");
                String listAirbillNumbers = "'";
                for (int i = 0; i < listAirbillNumber.length; i++) {
                    if (i < listAirbillNumber.length - 1) {
                        listAirbillNumbers = listAirbillNumbers + listAirbillNumber[i] + "','";
                    } else {
                        listAirbillNumbers = listAirbillNumbers + listAirbillNumber[i] + "'";
                    }
                }
                searchAirbillFilter.setAirbillNumberList(listAirbillNumbers);
            } else {
                searchAirbillFilter.setAirbillNumberList(null);
            }
            if (searchAirbillFilter.getZone() != null && StringUtils.isNotEmpty(searchAirbillFilter.getZone())) {
                searchAirbillFilter.setZone(searchAirbillFilter.getZone().trim());
            } else {
                searchAirbillFilter.setZone(null);
            }
            if (searchAirbillFilter.getMinPieces() != null && StringUtils.isNotEmpty(searchAirbillFilter.getMinPieces())) {
                searchAirbillFilter.setMinPieces(searchAirbillFilter.getMinPieces().trim());
                if (!StringUtils.isNumeric(searchAirbillFilter.getMinPieces())) {
                    this.addFieldError("searchAirbillFilter.minPieces", "Min Piece is not Numeric");
                }
            } else {
                searchAirbillFilter.setMinPieces(null);
            }
            if (searchAirbillFilter.getMaxPieces() != null && StringUtils.isNotEmpty(searchAirbillFilter.getMaxPieces())) {
                searchAirbillFilter.setMaxPieces(searchAirbillFilter.getMaxPieces().trim());
                if (!StringUtils.isNumeric(searchAirbillFilter.getMaxPieces())) {
                    this.addFieldError("searchAirbillFilter.maxPieces", "Max Piece is not Numeric");
                }
            } else {
                searchAirbillFilter.setMaxPieces(null);
            }
            if (searchAirbillFilter.getMinWeight() != null && StringUtils.isNotEmpty(searchAirbillFilter.getMinWeight())) {
                searchAirbillFilter.setMinWeight(searchAirbillFilter.getMinWeight().trim());
                if (!StringUtils.isNumeric(searchAirbillFilter.getMinWeight())) {
                    this.addFieldError("searchAirbillFilter.minWeight", "Min Weight is not Numeric");
                }
            }
            if (searchAirbillFilter.getSaleRepId() != null && StringUtils.isNotEmpty(searchAirbillFilter.getSaleRepId()) && !searchAirbillFilter.getSaleRepId().equals("0")) {
                searchAirbillFilter.setSaleRepId(searchAirbillFilter.getSaleRepId().trim());
            } else {
                searchAirbillFilter.setSaleRepId(null);
            }
            if (searchAirbillFilter.getMaxWeight() != null && StringUtils.isNotEmpty(searchAirbillFilter.getMaxWeight())) {
                searchAirbillFilter.setMaxWeight(searchAirbillFilter.getMaxWeight().trim());
                if (!StringUtils.isNumeric(searchAirbillFilter.getMaxWeight())) {
                    this.addFieldError("searchAirbillFilter.maxWeight", "Max Weight is not Numeric");
                }
            } else {
                searchAirbillFilter.setMaxWeight(null);
            }
            if (searchAirbillFilter.getMinPieces() != null && StringUtils.isNotEmpty(searchAirbillFilter.getMinPieces()) && searchAirbillFilter.getMaxPieces() != null && StringUtils.isNotEmpty(searchAirbillFilter.getMaxPieces()) && StringUtils.isNumeric(searchAirbillFilter.getMinPieces()) && StringUtils.isNumeric(searchAirbillFilter.getMaxPieces())) {
                if (Integer.parseInt(String.valueOf(searchAirbillFilter.getMinPieces())) > Integer.parseInt(String.valueOf(searchAirbillFilter.getMaxPieces()))) {
                    this.addFieldError("searchAirbillFilter.minPieces", "Min piece > Max pieces");
                }
            }
            if (searchAirbillFilter.getMinWeight() != null && StringUtils.isNotEmpty(searchAirbillFilter.getMinWeight()) && searchAirbillFilter.getMaxWeight() != null && StringUtils.isNotEmpty(searchAirbillFilter.getMaxWeight()) && StringUtils.isNumeric(searchAirbillFilter.getMinWeight()) && StringUtils.isNumeric(searchAirbillFilter.getMaxWeight())) {
                if (Double.parseDouble(String.valueOf(searchAirbillFilter.getMinWeight())) > Double.parseDouble(String.valueOf(searchAirbillFilter.getMaxWeight()))) {
                    this.addFieldError("searchAirbillFilter.minWeight", "Min Weight > Max Weight");
                }
            }
            if (searchAirbillFilter.getPackageTypeId().equals("0")) {
                searchAirbillFilter.setPackageTypeId(null);
            }
            if (searchAirbillFilter.getServiceId() != null && StringUtils.isNotEmpty(searchAirbillFilter.getServiceId())) {
                searchAirbillFilter.setServiceId(searchAirbillFilter.getServiceId());
            } else {
                searchAirbillFilter.setServiceId(null);
            }
            if (searchAirbillFilter.getServiceLevel() != null && StringUtils.contains(searchAirbillFilter.getServiceLevel(), ",")) {
                String serviceLevels[] = StringUtils.split(searchAirbillFilter.getServiceLevel(), ",");
                String billingShipmentTypeId = serviceLevels[0];
                String billingContents = serviceLevels[1];
                String billingBound = serviceLevels[2];
                String carrier = serviceLevels[3];
                searchAirbillFilter.setBillingBound(billingBound);
                searchAirbillFilter.setBillingContents(billingContents);
                searchAirbillFilter.setCarrierId(carrier);
                searchAirbillFilter.setShipmentTypeId(billingShipmentTypeId);
                searchAirbillFilter.setServiceId(carrier);
            }
            if (searchAirbillFilter.getReceiverCode() != null && StringUtils.isNotEmpty(searchAirbillFilter.getReceiverCode())) {
                searchAirbillFilter.setReceiverCode(searchAirbillFilter.getReceiverCode().trim());
            } else {
                searchAirbillFilter.setReceiverCode(null);
            }
            if (searchAirbillFilter.getSenderCode() != null && StringUtils.isNotEmpty(searchAirbillFilter.getSenderCode())) {
                searchAirbillFilter.setSenderCode(searchAirbillFilter.getSenderCode().trim());
            } else {
                searchAirbillFilter.setSenderCode(null);
            }
            if (searchAirbillFilter.getSenderName() != null && StringUtils.isNotEmpty(searchAirbillFilter.getSenderName())) {
                searchAirbillFilter.setSenderName(searchAirbillFilter.getSenderName().trim());
            } else {
                searchAirbillFilter.setSenderName(null);
            }
            if (searchAirbillFilter.getAccessorialName() != null && StringUtils.isNotEmpty(searchAirbillFilter.getAccessorialName())) {
                searchAirbillFilter.setAccessorialName(searchAirbillFilter.getAccessorialName().trim());
            } else {
                searchAirbillFilter.setAccessorialName(null);
            }
        }
        filterModelN.setFranchiseList(this.buildFranchiseCodeList("All"));
        this.setSearchAirbillFilter(filterModelN);
        return !

                hasFieldErrors();

    }

    protected boolean validDateSearch() throws Exception {
        if (StringUtils.isNotEmpty(this.getSearchAirbillFilter().getInvoiceDate()) && !HelperUtils.isValidDate(this.getSearchAirbillFilter().getInvoiceDate(), "dd-MM-yyyy")) {
            this.addFieldError("searchAirbillFilter.endInvoiceDate", "Invoice Date is not valid");
        }
        if (StringUtils.isNotEmpty(this.getSearchAirbillFilter().getImportDate()) && !HelperUtils.isValidDate(this.getSearchAirbillFilter().getImportDate(), "dd-MM-yyyy")) {
            this.addFieldError("searchAirbillFilter.endInvoiceDate", "Import Date is not valid");
        }
        if (StringUtils.isNotEmpty(this.getSearchAirbillFilter().getEndInvoiceDate()) && !HelperUtils.isValidDate(this.getSearchAirbillFilter().getEndInvoiceDate(), "dd-MM-yyyy")) {
            this.addFieldError("searchAirbillFilter.endInvoiceDate", "End invoice Date is not valid");
        }
        if (StringUtils.isNotEmpty(this.getSearchAirbillFilter().getStartInvoiceDate()) && !HelperUtils.isValidDate(this.getSearchAirbillFilter().getStartInvoiceDate(), "dd-MM-yyyy")) {
            this.addFieldError("searchAirbillFilter.startInvoiceDate", "Start invoice Date is not valid");
        }
        if (StringUtils.isNotEmpty(this.getSearchAirbillFilter().getEndShipmentDate()) && !HelperUtils.isValidDate(this.getSearchAirbillFilter().getEndShipmentDate(), "dd-MM-yyyy")) {
            this.addFieldError("searchAirbillFilter.endShipmentDate", "End Shipment Date is not valid");
        }
        if (StringUtils.isNotEmpty(this.getSearchAirbillFilter().getStartShipmentDate()) && !HelperUtils.isValidDate(this.getSearchAirbillFilter().getStartShipmentDate(), "dd-MM-yyyy")) {
            this.addFieldError("searchAirbillFilter.startShipmentDate", "Start Shipment Date is not valid");
        }
        SearchAirbillFilter filterVo = ModelUtils.createVoFromModel(searchAirbillFilter, SearchAirbillFilter.class);
        if (filterVo.getStartInvoiceDate() != null && filterVo.getEndInvoiceDate() != null) {
            if (filterVo.getStartInvoiceDate().compareTo(filterVo.getEndInvoiceDate()) > 0) {
                setErrorMessage("Start Invoice date must be before the end date.");
            }
        }
        if (filterVo.getStartShipmentDate() != null && filterVo.getEndShipmentDate() != null) {
            if (filterVo.getStartShipmentDate().compareTo(filterVo.getEndShipmentDate()) > 0) {
                setErrorMessage("Start Shipment date must be before the end date.");
            }
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    private void prepareDatas() throws Exception {
        this.prepareFranchises();
        this.preparePageSizes();
        this.prepareSalesRepList();
        this.prepareListCarriers();
        this.prepareListShipmentType();
        this.prepareCountryList();
        this.prepareInvoiceStatusList();
        this.prepareAccessorialList();
        this.preparePackageTypeList();
        this.prepareInvoiceDateList();
        this.prepareListImportDate();
        this.prepareMassEditOptions();
        this.setFranchiseList(this.buildFranchiseCodeList("All"));
        pageSizeList = this.buildPageSizeList();
    }

    private void prepareMassEditOptions() {
        Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
        List<MassEditOptionsModel> list = new ArrayList<MassEditOptionsModel>();
        list.add(new MassEditOptionsModel("0", "Select a Mass Edit Action"));
        list.add(new MassEditOptionsModel("1", "Change Weight"));
        if (userLevelId == 1 || userLevelId == 2) {
            list.add(new MassEditOptionsModel("2", "Force Base Charge Recalc"));
        }
        list.add(new MassEditOptionsModel("3", "Force Customer Base Charge"));
        list.add(new MassEditOptionsModel("4", "Change Sender address to Customer Physical Address"));
        list.add(new MassEditOptionsModel("5", "Change Sender Address to Sender Address from Webship"));
        // list.add(new MassEditOptionsModel("6", "Change Service Type and Force
        // Base Charge Recalc"));
        // list.add(new MassEditOptionsModel("7", "Change Customer #"));
        // list.add(new MassEditOptionsModel("8", "Move Airbills to Another
        // Invoice"));
        list.add(new MassEditOptionsModel("12", "Move Airbill"));
        list.add(new MassEditOptionsModel("9", "Add Accessorial Charge"));
        // list.add(new MassEditOptionsModel("10", "Adjustment : 'Accessorial
        // Dispute' (Carrier Adjustment Only)"));
        if (userLevelId == 1 || userLevelId == 2) {
            list.add(new MassEditOptionsModel("11", "Recalc Franchise Base Cost"));
        }
        list.add(new MassEditOptionsModel("13", "Change Zone"));
        list.add(new MassEditOptionsModel("14", "Change Service Type"));
        list.add(new MassEditOptionsModel("15", "Recalc Accessorial Mark-up"));
        list.add(new MassEditOptionsModel("16", "Markup Accessorial"));
        list.add(new MassEditOptionsModel("17", "Mass Change Accessorial Type"));
        if (userLevelId != 3) {
            list.add(new MassEditOptionsModel("18", "Delete Airbill"));
        }
        list.add(new MassEditOptionsModel("19", "Recalculate Customer Cost"));
        list.add(new MassEditOptionsModel("20", "Force Quoted Charge"));
        list.add(new MassEditOptionsModel("21", "Force Quoted Accessorial Charge"));
        list.add(new MassEditOptionsModel("22", "Force Markup Customer Cost"));
        // list.add(new MassEditOptionsModel("23", "Full Carrier Credit Note"));
        this.setMassEditOptions(list);
    }

    private void prepareListImportDate() throws Exception {
        ISearchAirbillService service = new SearchAirbillServiceImp();
        this.setListImportDate(ModelUtils.createListModelFromVo(service.selectImportDateForSearchAirbill(), ShipmentBillingModel.class));
    }

    private void prepareSalesRepList() throws Exception {
        IUserService userService = new UserServiceImp();
        List<String> franchiseCode = new ArrayList<>();
        franchiseCode.add("0");
        for (FranchiseInfoModel franchiseInfo : this.getFranchises()) {
            franchiseCode.add(franchiseInfo.getCode());
        }
        List<UserVo> listSalesRep = userService.getSaleReps(franchiseCode);
        List<UserModel> listSalesRepModel = ModelUtils.createListModelFromVo(listSalesRep, UserModel.class);
        this.setListSalesRep(listSalesRepModel);
    }

    private void prepareCountryList() throws Exception {
        ICountryService countryService = new CountryServiceImp();
        List<CountryVo> countryVos = countryService.getSearchAirbilCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        this.setListCountries(countryModels);
    }

    private void prepareListCarriers() throws Exception {
        IServiceService serviceService = new ServiceServiceImp();
        List<ServiceVo> serviceVos = serviceService.selectAll();
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.setListCarriers(serviceModels);
    }

    private void prepareListShipmentType() throws Exception {
        Integer serviceId = null;
        if (this.getSearchAirbillFilter() != null && !StringUtils.isBlank(this.getSearchAirbillFilter().getServiceId())) {
            serviceId = Integer.parseInt(this.getSearchAirbillFilter().getServiceId());
        }
        ISearchAirbillService searchAirbillService = new SearchAirbillServiceImp();
        List<SearchAirbillShipmentTypeVo> airbillShipmentTypeVos = searchAirbillService.getListShipmentTypeByServiceId(serviceId);
        List<SearchAirbillShipmentTypeModel> airbillShipmentTypeModels = ModelUtils.createListModelFromVo(airbillShipmentTypeVos, SearchAirbillShipmentTypeModel.class);
        this.setListShipmentType(airbillShipmentTypeModels);
    }

    private void prepareInvoiceStatusList() {
        Map<String, String> invoiceStatus = new TreeMap<>();
        invoiceStatus.put("0", this.getLocalizationText("Unfrozen"));
        invoiceStatus.put("1", this.getLocalizationText("Frozen"));
        invoiceStatus.put("2", this.getLocalizationText("Sent Email"));
        invoiceStatus.put("4", this.getLocalizationText("Printed"));
        this.setListInvoiceStatus(invoiceStatus);
    }

    private void prepareAccessorialList() throws Exception {
        IAccessorialSerivce accessorialSerivce = new AccessorialServiceImp();
        List<SurchargeListVo> surchargeListVos = accessorialSerivce.getSurchargeList(new AccessorialFilter());
        List<SurchargeListModel> surchargeListModels = ModelUtils.createListModelFromVo(surchargeListVos, SurchargeListModel.class);
        this.setListAccessorial(surchargeListModels);
    }

    private void preparePackageTypeList() throws Exception {
        IPackageService packageService = new PackageServiceImp();
        List<PackageVo> packageVos = packageService.getPackageList();
        List<PackageModel> packageModels = ModelUtils.createListModelFromVo(packageVos, PackageModel.class);
        this.setListPackageTypes(packageModels);
    }

    private void prepareInvoiceDateList() throws Exception {
        List<String> listFranchiseCode = new ArrayList<>();
        for (FranchiseInfoModel franchiseInfoModel : this.getFranchises()) {
            listFranchiseCode.add(franchiseInfoModel.getCode());
        }
        ISearchAirbillService searchAirbillService = new SearchAirbillServiceImp();
        List<InvoiceVo> invoiceDatesVo = searchAirbillService.getInvoiceDatesByFranchiseCodeList(listFranchiseCode);
        List<InvoiceModel> invoiceDatesModel = ModelUtils.createListModelFromVo(invoiceDatesVo, InvoiceModel.class);
        List<String> listInvoiceDates = new LinkedList<>();
        for (InvoiceModel invoice : invoiceDatesModel) {
            if (invoice != null) {
                listInvoiceDates.add(invoice.getInvoiceDate());
            }
        }
        this.setListInvoiceDates(listInvoiceDates);
    }

    public List<UserModel> getListSalesRep() {
        return listSalesRep;
    }

    public void setListSalesRep(List<UserModel> listSalesRep) {
        this.listSalesRep = listSalesRep;
    }

    public List<ServiceModel> getListCarriers() {
        return listCarriers;
    }

    public void setListCarriers(List<ServiceModel> listCarriers) {
        this.listCarriers = listCarriers;
    }

    public List<CountryModel> getListCountries() {
        return listCountries;
    }

    public void setListCountries(List<CountryModel> listCountries) {
        this.listCountries = listCountries;
    }

    public SearchAirbillFilterModel getSearchAirbillFilter() {
        return searchAirbillFilter;
    }

    public void setSearchAirbillFilter(SearchAirbillFilterModel searchAirbillFilter) {
        this.searchAirbillFilter = searchAirbillFilter;
    }

    public List<SearchAirbillShipmentTypeModel> getListShipmentType() {
        return listShipmentType;
    }

    public void setListShipmentType(List<SearchAirbillShipmentTypeModel> listShipmentType) {
        this.listShipmentType = listShipmentType;
    }

    public Map<String, String> getListInvoiceStatus() {
        return listInvoiceStatus;
    }

    public void setListInvoiceStatus(Map<String, String> listInvoiceStatus) {
        this.listInvoiceStatus = listInvoiceStatus;
    }

    public List<SurchargeListModel> getListAccessorial() {
        return listAccessorial;
    }

    public void setListAccessorial(List<SurchargeListModel> listAccessorial) {
        this.listAccessorial = listAccessorial;
    }

    public List<PackageModel> getListPackageTypes() {
        return listPackageTypes;
    }

    public void setListPackageTypes(List<PackageModel> listPackageTypes) {
        this.listPackageTypes = listPackageTypes;
    }

    public List<String> getListInvoiceDates() {
        return listInvoiceDates;
    }

    public void setListInvoiceDates(List<String> listInvoiceDates) {
        this.listInvoiceDates = listInvoiceDates;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public List<ShipmentBillingModel> getListImportDate() {
        return listImportDate;
    }

    public void setListImportDate(List<ShipmentBillingModel> listImportDate) {
        this.listImportDate = listImportDate;
    }

    public Paging<SearchAirbillModel> getListAirbill() {
        return listAirbill;
    }

    public void setListAirbill(Paging<SearchAirbillModel> listAirbill) {
        this.listAirbill = listAirbill;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public List<String> getPageSizeList() {
        return pageSizeList;
    }

    public void setPageSizeList(List<String> pageSizeList) {
        this.pageSizeList = pageSizeList;
    }

    public TotalSearchAirbillModel getTotalSearchAirbill() {
        return totalSearchAirbill;
    }

    public void setTotalSearchAirbill(TotalSearchAirbillModel totalSearchAirbill) {
        this.totalSearchAirbill = totalSearchAirbill;
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

    public List<MassEditOptionsModel> getMassEditOptions() {
        return massEditOptions;
    }

    public void setMassEditOptions(List<MassEditOptionsModel> massEditOptions) {
        this.massEditOptions = massEditOptions;
    }
}
