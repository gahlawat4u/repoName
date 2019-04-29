package com.gms.xms.weblib.controller.admin.quicksearch;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.MathUtils;
import com.gms.xms.filter.admin.AdminQuickSearchFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.account.contact.ManageContactModel;
import com.gms.xms.model.admin.quicksearch.*;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.PieceModel;
import com.gms.xms.model.webship.history.*;
import com.gms.xms.persistence.dao.PieceDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.admin.quicksearch.*;
import com.gms.xms.persistence.dao.contact.ManageContactDao;
import com.gms.xms.txndb.vo.PieceVo;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.account.contact.ManageContactVo;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchCustomerVo;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchInvoiceVo;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchShipmentBillingVo;
import com.gms.xms.txndb.vo.admin.quicksearch.QuickSearchShipmentVo;
import com.gms.xms.txndb.vo.webship.history.HistoryDetailFilter;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.service.webship.history.HistoryDetailServiceImp;
import com.gms.xms.workflow.service.webship.history.IHistoryDetailService;
import com.gms.xms.workflow.utils.ShipmentUtils;
import com.gms.xms.workflow.utils.weight.BaseGrossWeight;
import com.gms.xms.workflow.utils.weight.GrossWeightFactory;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from Apr 28, 2016 11:42:34 AM
 * <p>
 * Author huynd
 */
public class AdminQuickSearchController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    // Filter properties.
    private Byte searchType;
    private String searchValue;
    private String franchiseList;
    private String prospectList;
    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;

    // Model.
    private List<SearchTypeModel> searchTypeList;
    private Paging<QuickSearchCustomerModel> qsCustomer;
    private Paging<ManageContactModel> qsContact;
    private Paging<QuickSearchShipmentBillingModel> qsShipmentBilling;
    private Paging<QuickSearchInvoiceModel> qsInvoice;
    private Paging<QuickSearchShipmentModel> qsShipmentByAirbill;
    private Paging<QuickSearchShipmentModel> qsShipmentByReference;
    private Paging<QuickSearchShipmentModel> qsShipmentByConfirmationNo;

    private String shipmentId;
    private List<HistoryDetailPieceModel> detailPieceModels;
    private List<PieceModel> pieceModels;
    private List<HistoryViewAirbillModel> listPieceViewAirbill;
    private List<HistoryDetailAccessorialModel> detailAccessorialModels;
    private HistoryDetailInfoModel detailInfoModel;
    private HistoryDetailFilterModel detailFilterModel;
    private List<HistoryProductAirbillModel> productAirbillModels;

    // Return view search result
    private String searchResults;

    public String search() {
        try {
            loadSearchTypeList();
            getProspectListString();
            AdminQuickSearchFilter filter = this.buildFilter();
            if (filter != null && !StringUtils.isBlank(filter.getSearchValue()) && filter.getSearchValue().length() >= 3) {
                loadSearchResults();
                preparePageSizes();
            } else {
                throw new CustomException("Search text must be at least 3 characters long.");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return this.getSearchResults();
    }

    public String searchForm() {
        try {
            loadSearchTypeList();
            getProspectListString();
            AdminQuickSearchFilter filter = this.buildFilter();
            if (filter != null && !StringUtils.isBlank(filter.getSearchValue()) && filter.getSearchValue().length() >= 3) {
                loadSearchResults();
                preparePageSizes();
            } else {
                throw new CustomException("Search text must be at least 3 characters long.");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return this.getSearchResults();
    }

    private void loadSearchResults() throws Exception {
        // Get filter.
        AdminQuickSearchFilter filter = this.buildFilter();
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        switch (filter.getSearchType()) {
            case 0:
                // Quick search customers
                // Get record count.
                QuickSearchCustomerDao daoCustomer = new QuickSearchCustomerDao();
                long countCustomers = daoCustomer.countCustomersByCustomerCode(filter);
                // Build paging object.
                Paging<QuickSearchCustomerModel> pagingCustomers = new Paging<QuickSearchCustomerModel>(filter.getPage(), nLinks, filter.getPageSize(), countCustomers);
                filter.setPage(pagingCustomers.getCurrentPage());
                // Get list of records of customers.
                List<QuickSearchCustomerVo> quickSearchCustomerVos = daoCustomer.getCustomersByCustomerCode(filter);
                List<QuickSearchCustomerModel> quickSearchCustomerModel = ModelUtils.createListModelFromVo(quickSearchCustomerVos, QuickSearchCustomerModel.class);
                pagingCustomers.setRecords(quickSearchCustomerModel);
                this.setQsCustomer(pagingCustomers);
                this.setSearchResults("success_customer");
                break;
            case 1:
                // Quick search contacts
                // Get record count.
                QuickSearchContactDao daoContact = new QuickSearchContactDao();
                long countContacts = daoContact.countContactsByContact(filter);
                // Build paging object.
                Paging<ManageContactModel> pagingContacts = new Paging<ManageContactModel>(filter.getPage(), nLinks, filter.getPageSize(), countContacts);
                filter.setPage(pagingContacts.getCurrentPage());
                // Get list of records of contacts.
                List<ManageContactVo> manageContactVos = daoContact.getContactsByContact(filter);
                List<ManageContactModel> manageContactModel = ModelUtils.createListModelFromVo(manageContactVos, ManageContactModel.class);
                pagingContacts.setRecords(manageContactModel);
                this.setQsContact(pagingContacts);
                this.setSearchResults("success_contact");
                break;
            case 2:
                // Quick search airbills
                // Get record count.
                QuickSearchShipmentBillingDao daoAirbill = new QuickSearchShipmentBillingDao();
                long countAirbills = daoAirbill.countShipmentBillingsByAirbill(filter);
                // Build paging object.
                Paging<QuickSearchShipmentBillingModel> pagingAirbills = new Paging<QuickSearchShipmentBillingModel>(filter.getPage(), nLinks, filter.getPageSize(), countAirbills);
                filter.setPage(pagingAirbills.getCurrentPage());
                // Get list of records of airbills.
                List<QuickSearchShipmentBillingVo> searchShipmentBillingVos = daoAirbill.getShipmentBillingsByAirbill(filter);
                List<QuickSearchShipmentBillingModel> searchShipmentBillingModel = ModelUtils.createListModelFromVo(searchShipmentBillingVos, QuickSearchShipmentBillingModel.class);
                pagingAirbills.setRecords(searchShipmentBillingModel);
                this.setQsShipmentBilling(pagingAirbills);
                this.setSearchResults("success_airbill");
                break;
            case 3:
                // Quick search invoices
                // Get record count.
                QuickSearchInvoiceDao daoInvoice = new QuickSearchInvoiceDao();
                long countInvoices = daoInvoice.countInvoicesByInvoiceCode(filter);
                // Build paging object.
                Paging<QuickSearchInvoiceModel> pagingInvoices = new Paging<QuickSearchInvoiceModel>(filter.getPage(), nLinks, filter.getPageSize(), countInvoices);
                filter.setPage(pagingInvoices.getCurrentPage());
                // Get list of records of invoices.
                List<QuickSearchInvoiceVo> searchInvoiceVos = daoInvoice.getInvoicesByInvoiceCode(filter);
                List<QuickSearchInvoiceModel> searchInvoiceModel = ModelUtils.createListModelFromVo(searchInvoiceVos, QuickSearchInvoiceModel.class);
                pagingInvoices.setRecords(searchInvoiceModel);
                this.setQsInvoice(pagingInvoices);
                this.setSearchResults("success_invoice");
                break;
            case 4:
                // Quick search airbill labels
                // Get record count.
                QuickSearchShipmentDao daoAirbillLabel = new QuickSearchShipmentDao();
                long countAirbillLabels = daoAirbillLabel.countShipmentsByAirbill(filter);
                // Build paging object.
                Paging<QuickSearchShipmentModel> pagingAirbillLabels = new Paging<QuickSearchShipmentModel>(filter.getPage(), nLinks, filter.getPageSize(), countAirbillLabels);
                filter.setPage(pagingAirbillLabels.getCurrentPage());
                // Get list of records of aribill labels.
                List<QuickSearchShipmentVo> searchShipmentVos = daoAirbillLabel.getShipmentsByAirbill(filter);
                for (QuickSearchShipmentVo quickSearchShipmentVo : searchShipmentVos) {
                    // Get pieces.
                    PieceDao pieceDao = new PieceDao();
                    List<PieceVo> pieceVos = pieceDao.selectByShipmentId(quickSearchShipmentVo.getShipmentId());
                    // Recalculate dim weight and actual weight.
                    BaseGrossWeight grossWeight = GrossWeightFactory.getGrossWeight(quickSearchShipmentVo.getServiceId(), quickSearchShipmentVo.getShipmentTypeId());
                    // Update quoted weight.
                    double weight = grossWeight.getQuoteWeight(pieceVos, quickSearchShipmentVo.getDimensionUnit(), quickSearchShipmentVo.getWeightUnit());
                    if (quickSearchShipmentVo.getServiceId() == 1) {
                        weight = MathUtils.shipmentWeightRound(weight, false);
                        quickSearchShipmentVo.setWeightStr(String.valueOf(weight) + quickSearchShipmentVo.getWeightUnit() + "(s)");
                    }
                }
                List<QuickSearchShipmentModel> searchShipmentModel = ModelUtils.createListModelFromVo(searchShipmentVos, QuickSearchShipmentModel.class);
                pagingAirbillLabels.setRecords(searchShipmentModel);
                this.setQsShipmentByAirbill(pagingAirbillLabels);
                this.setSearchResults("success_airbill_label");
                break;
            case 5:
                // Quick search webship details
                prepareHistoryDetailFilter(filter);
                if (this.getDetailFilterModel() != null) {
                    prepareHistoryDetail();
                }
                this.setSearchResults("success_webship_detail");
                break;
            case 6:
                // Quick search reference #
                // Get record count.
                QuickSearchReferenceDao daoReference = new QuickSearchReferenceDao();
                long countReferences = daoReference.countShipmentsByReference(filter);
                // Build paging object.
                Paging<QuickSearchShipmentModel> pagingReferences = new Paging<QuickSearchShipmentModel>(filter.getPage(), nLinks, filter.getPageSize(), countReferences);
                filter.setPage(pagingReferences.getCurrentPage());
                // Get list of records of references.
                List<QuickSearchShipmentVo> searchReferenceVos = daoReference.getShipmentsByReference(filter);
                List<QuickSearchShipmentModel> searchReferenceModel = ModelUtils.createListModelFromVo(searchReferenceVos, QuickSearchShipmentModel.class);
                pagingReferences.setRecords(searchReferenceModel);
                this.setQsShipmentByReference(pagingReferences);
                this.setSearchResults("success_reference");
                break;
            case 7:
                // Quick search booking No.
                // Get record count.
                QuickSearchConfirmationNoDao daoConfirmationNo = new QuickSearchConfirmationNoDao();
                long countConfirmationNos = daoConfirmationNo.countShipmentsByConfirmationNo(filter);
                // Build paging object.
                Paging<QuickSearchShipmentModel> pagingConfirmationNos = new Paging<QuickSearchShipmentModel>(filter.getPage(), nLinks, filter.getPageSize(), countConfirmationNos);
                filter.setPage(pagingConfirmationNos.getCurrentPage());
                // Get list of records of references.
                List<QuickSearchShipmentVo> searchConfirmationNoVos = daoConfirmationNo.getShipmentsByConfirmationNo(filter);
                List<QuickSearchShipmentModel> searchConfirmationNoModel = ModelUtils.createListModelFromVo(searchConfirmationNoVos, QuickSearchShipmentModel.class);
                pagingConfirmationNos.setRecords(searchConfirmationNoModel);
                this.setQsShipmentByConfirmationNo(pagingConfirmationNos);
                this.setSearchResults("success_booking_no");
                break;
        }
    }

    private void prepareHistoryDetail() throws Exception {
        IHistoryDetailService detailService = new HistoryDetailServiceImp();
        HistoryDetailFilter filterDetails = new HistoryDetailFilter();
        filterDetails = ModelUtils.createVoFromModel(this.getDetailFilterModel(), HistoryDetailFilter.class);
        this.setDetailInfoModel(ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filterDetails), HistoryDetailInfoModel.class));
        detailInfoModel.setBaseCharge(detailInfoModel.getBaseCharge());
        detailInfoModel.setShipmentId(this.getShipmentId());
        this.setPieceModels(ModelUtils.createListModelFromVo(detailService.selectPieceByIdNonGroup(Long.parseLong(shipmentId)), PieceModel.class));
        this.setDetailPieceModels(ModelUtils.createListModelFromVo(detailService.selectPieceInfo(filterDetails, true), HistoryDetailPieceModel.class));
        this.setDetailAccessorialModels(ModelUtils.createListModelFromVo(detailService.selectHistoryDetailAccessorial(filterDetails), HistoryDetailAccessorialModel.class));
    }

    private void prepareHistoryDetailFilter(AdminQuickSearchFilter filter) throws Exception {
        // Get user id and user level.
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        String userLevel = (String) this.getSession("SESS_XMS_ADMIN_LEVEL");
        long id = Long.valueOf(userId);
        int level = Double.valueOf(userLevel).intValue();
        // Check user level permissions.
        if (level == 6) {
            QuickSearchShipmentDao dao = new QuickSearchShipmentDao();
            // Get sales rep id of search airbill.
            Long saleRepId = dao.getSaleRepIdOfAirbill(filter.getSearchValue());
            saleRepId = (saleRepId == null) ? -1 : saleRepId;
            if (saleRepId != id) {
                this.setDetailFilterModel(null);
            }
        } else {
            ShipmentDao shipmentDao = new ShipmentDao();
            this.setShipmentId(shipmentDao.selectShipmentIdByAirbillNumber(filter.getSearchValue()));
            HistoryDetailFilterModel detailFilterModelN = new HistoryDetailFilterModel();
            detailFilterModelN.setShipmentId(this.getShipmentId());
            detailFilterModelN.setLbToKg("0.45359237");
            detailFilterModelN.setInToCm("2.54");
            detailFilterModelN.setWeightValue("5000");
            IHistoryDetailService detailService = new HistoryDetailServiceImp();
            HistoryDetailInfoModel historyDetailInfoModelN = new HistoryDetailInfoModel();
            HistoryDetailFilter filterWebshipDetail = new HistoryDetailFilter();
            filterWebshipDetail = ModelUtils.createVoFromModel(detailFilterModelN, HistoryDetailFilter.class);
            historyDetailInfoModelN = ModelUtils.createModelFromVo(detailService.selectHistoryDetailInfo(filterWebshipDetail), HistoryDetailInfoModel.class);
            detailFilterModelN.setWeightValue(ShipmentUtils.getForceVolWeight(Integer.parseInt(historyDetailInfoModelN.getServiceId())).toString());
            this.setDetailFilterModel(detailFilterModelN);
        }
    }

    private AdminQuickSearchFilter buildFilter() throws Exception {
        AdminQuickSearchFilter filter = new AdminQuickSearchFilter();
        // Get user id and user level.
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        String userLevel = (String) this.getSession("SESS_XMS_ADMIN_LEVEL");
        filter.setUserId(Long.valueOf(userId));
        filter.setUserLevel(Double.valueOf(userLevel).intValue());
        // Set search type.
        Byte searchType = Byte.valueOf(this.getSearchType());
        filter.setSearchValue(this.getSearchValue().trim());
        switch (searchType) {
            case 0:
                // Quick search customers
                filter.setFranchiseList(this.buildFranchiseCodeList("All"));
                // Set order field.
                filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "customer_code" : this.getOrderField());
                break;
            case 1:
                // Quick search contacts
                filter.setProspectList(this.getProspectList());
                // Set order field.
                filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "prospectid" : this.getOrderField());
                break;
            case 2:
                // Quick search airbills
                filter.setFranchiseList(this.buildFranchiseCodeList("All"));
                // Set order field.
                filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "shipmentid" : this.getOrderField());
                break;
            case 3:
                // Quick search invoices
                filter.setFranchiseList(this.buildFranchiseCodeList("All"));
                // Set order field.
                filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "invoice_code" : this.getOrderField());
                break;
            case 4:
                // Quick search airbill labels
                filter.setFranchiseList(this.buildFranchiseCodeList("All"));
                // Set order field.
                filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "shipmentid" : this.getOrderField());
                break;
            case 5:
                // Quick search webship details
                // Set order field.
                filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "shipmentid" : this.getOrderField());
                break;
            case 6:
                // Quick search reference #
                filter.setFranchiseList(this.buildFranchiseCodeList("All"));
                // Set order field.
                filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "shipmentid" : this.getOrderField());
                break;
            case 7:
                // Quick search booking No.
                filter.setFranchiseList(this.buildFranchiseCodeList("All"));
                // Set order field.
                filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "shipmentid" : this.getOrderField());
                break;
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
        filter.setSearchType(searchType);
        return filter;
    }

    private void getProspectListString() throws Exception {
        ManageContactDao dao = new ManageContactDao();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        UserDao userDao = new UserDao();
        UserVo userVo = userDao.getUserById(userId);
        List<Long> prospectIdList = dao.getProspectByLogin(userVo);
        String result = "";
        for (Long prospect : prospectIdList) {
            result += String.valueOf(prospect) + ",";
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length() - 1);
        }
        this.setProspectList(result);
    }

    private void loadSearchTypeList() {
        List<SearchTypeModel> list = new ArrayList<SearchTypeModel>();
        list.add(new SearchTypeModel("0", "Customers"));
        list.add(new SearchTypeModel("1", "Contacts"));
        list.add(new SearchTypeModel("2", "Airbills"));
        list.add(new SearchTypeModel("3", "Invoices"));
        list.add(new SearchTypeModel("4", "Airbill labels"));
        list.add(new SearchTypeModel("5", "Webship details"));
        list.add(new SearchTypeModel("6", "Reference #"));
        list.add(new SearchTypeModel("7", "Booking No."));
        this.setSearchTypeList(list);
    }

    public Byte getSearchType() {
        return searchType;
    }

    public void setSearchType(Byte searchType) {
        this.searchType = searchType;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    public String getProspectList() {
        return prospectList;
    }

    public void setProspectList(String prospectList) {
        this.prospectList = prospectList;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public List<SearchTypeModel> getSearchTypeList() {
        return searchTypeList;
    }

    public void setSearchTypeList(List<SearchTypeModel> searchTypeList) {
        this.searchTypeList = searchTypeList;
    }

    public Paging<QuickSearchCustomerModel> getQsCustomer() {
        return qsCustomer;
    }

    public void setQsCustomer(Paging<QuickSearchCustomerModel> qsCustomer) {
        this.qsCustomer = qsCustomer;
    }

    public Paging<ManageContactModel> getQsContact() {
        return qsContact;
    }

    public void setQsContact(Paging<ManageContactModel> qsContact) {
        this.qsContact = qsContact;
    }

    public Paging<QuickSearchShipmentBillingModel> getQsShipmentBilling() {
        return qsShipmentBilling;
    }

    public void setQsShipmentBilling(Paging<QuickSearchShipmentBillingModel> qsShipmentBilling) {
        this.qsShipmentBilling = qsShipmentBilling;
    }

    public Paging<QuickSearchInvoiceModel> getQsInvoice() {
        return qsInvoice;
    }

    public void setQsInvoice(Paging<QuickSearchInvoiceModel> qsInvoice) {
        this.qsInvoice = qsInvoice;
    }

    public Paging<QuickSearchShipmentModel> getQsShipmentByAirbill() {
        return qsShipmentByAirbill;
    }

    public void setQsShipmentByAirbill(Paging<QuickSearchShipmentModel> qsShipmentByAirbill) {
        this.qsShipmentByAirbill = qsShipmentByAirbill;
    }

    public Paging<QuickSearchShipmentModel> getQsShipmentByReference() {
        return qsShipmentByReference;
    }

    public void setQsShipmentByReference(Paging<QuickSearchShipmentModel> qsShipmentByReference) {
        this.qsShipmentByReference = qsShipmentByReference;
    }

    public Paging<QuickSearchShipmentModel> getQsShipmentByConfirmationNo() {
        return qsShipmentByConfirmationNo;
    }

    public void setQsShipmentByConfirmationNo(Paging<QuickSearchShipmentModel> qsShipmentByConfirmationNo) {
        this.qsShipmentByConfirmationNo = qsShipmentByConfirmationNo;
    }

    public String getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(String searchResults) {
        this.searchResults = searchResults;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public List<HistoryDetailPieceModel> getDetailPieceModels() {
        return detailPieceModels;
    }

    public void setDetailPieceModels(List<HistoryDetailPieceModel> detailPieceModels) {
        this.detailPieceModels = detailPieceModels;
    }

    public List<PieceModel> getPieceModels() {
        return pieceModels;
    }

    public void setPieceModels(List<PieceModel> pieceModels) {
        this.pieceModels = pieceModels;
    }

    public List<HistoryViewAirbillModel> getListPieceViewAirbill() {
        return listPieceViewAirbill;
    }

    public void setListPieceViewAirbill(List<HistoryViewAirbillModel> listPieceViewAirbill) {
        this.listPieceViewAirbill = listPieceViewAirbill;
    }

    public List<HistoryDetailAccessorialModel> getDetailAccessorialModels() {
        return detailAccessorialModels;
    }

    public void setDetailAccessorialModels(List<HistoryDetailAccessorialModel> detailAccessorialModels) {
        this.detailAccessorialModels = detailAccessorialModels;
    }

    public HistoryDetailInfoModel getDetailInfoModel() {
        return detailInfoModel;
    }

    public void setDetailInfoModel(HistoryDetailInfoModel detailInfoModel) {
        this.detailInfoModel = detailInfoModel;
    }

    public HistoryDetailFilterModel getDetailFilterModel() {
        return detailFilterModel;
    }

    public void setDetailFilterModel(HistoryDetailFilterModel detailFilterModel) {
        this.detailFilterModel = detailFilterModel;
    }

    public List<HistoryProductAirbillModel> getProductAirbillModels() {
        return productAirbillModels;
    }

    public void setProductAirbillModels(List<HistoryProductAirbillModel> productAirbillModels) {
        this.productAirbillModels = productAirbillModels;
    }
}
