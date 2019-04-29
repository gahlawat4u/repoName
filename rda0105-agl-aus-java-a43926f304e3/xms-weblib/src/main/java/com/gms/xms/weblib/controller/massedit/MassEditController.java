package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.account.customers.BasicCustomerModel;
import com.gms.xms.model.admin.invoicing.searchairbill.SearchAirbillFilterModel;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.SearchAirbillDao;
import com.gms.xms.persistence.dao.admin.invoicing.ViewEditInvoiceDao;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerFilter;
import com.gms.xms.txndb.vo.account.customers.BasicCustomerVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillFilter;
import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Posted from May 13, 2016 10:01:10 AM
 * <p>
 * Author huynd
 */
public class MassEditController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private List<ListAirbillForMassEditModel> listAirbillMassEdit;
    private SearchAirbillFilterModel searchAirbillFilter;
    private String listAirbillStr;

    private String franchiseCode;
    private String searchText;

    private List<BasicCustomerModel> customers;

    protected void buildListAirbill() throws Exception {
        String listAirbillStr = "";
        if (this.getListAirbillMassEdit() != null) {
            listAirbillStr = GsonUtils.toGson(this.getListAirbillMassEdit());
        } else {
            List<SearchAirbillVo> airbillVos = getAirbillListByFilter();
            if (airbillVos.size() == 0) {
                throw new CustomException("No airbill chosen!");
            } else {
                List<ListAirbillForMassEditModel> data = new ArrayList<ListAirbillForMassEditModel>();
                ListAirbillForMassEditModel listAirbillMassEdit;
                SearchAirbillVo airbillVo;
                InvoiceInfoVo invoiceInfoVo;
                ViewEditInvoiceDao dao = new ViewEditInvoiceDao();
                for (Iterator<SearchAirbillVo> searchAirbillVo = airbillVos.listIterator(); searchAirbillVo.hasNext(); ) {
                    airbillVo = searchAirbillVo.next();
                    invoiceInfoVo = dao.selectInvoiceInfoById(Long.valueOf(airbillVo.getInvoiceId()));
                    if (invoiceInfoVo.getStatus() != 0) {
                        searchAirbillVo.remove();
                        continue;
                    }
                    listAirbillMassEdit = new ListAirbillForMassEditModel();
                    listAirbillMassEdit.setShipmentId(airbillVo.getShipmentId().toString());
                    listAirbillMassEdit.setAirbillNumber(airbillVo.getAirbillNumber());
                    listAirbillMassEdit.setInvoiceId(airbillVo.getInvoiceId().toString());
                    listAirbillMassEdit.setInvoiceDate(DateUtils.convertDateToString(airbillVo.getInvoiceDate(), "dd-MM-yyyy", null));
                    listAirbillMassEdit.setCustomerCode(airbillVo.getCustomerCode().toString());
                    listAirbillMassEdit.setInvoiceStatus(airbillVo.getInvoiceStatus().toString());
                    data.add(listAirbillMassEdit);
                }
                listAirbillStr = GsonUtils.toGson(data);
            }
        }
        this.setListAirbillStr(listAirbillStr);
    }

    protected List<SearchAirbillVo> getAirbillListByFilter() throws Exception {
        SearchAirbillDao dao = new SearchAirbillDao();
        this.prepareSearchAirbilFilter();
        SearchAirbillFilter filter = new SearchAirbillFilter();
        filter = ModelUtils.createVoFromModel(this.getSearchAirbillFilter(), SearchAirbillFilter.class);
        filter.setInvoiceStatus(5); // Unfrozen invoices
        filter.setPage(null);
        filter.setPageSize(null);
        List<SearchAirbillVo> airbillVos = dao.selectAirbillList(filter);
        return airbillVos;
    }

    protected void prepareSearchAirbilFilter() throws Exception {
        SearchAirbillFilterModel filterModel = new SearchAirbillFilterModel();
        if (this.getSearchAirbillFilter() == null) {
            // Load default index history
            filterModel.setPage("1");
            filterModel.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
        } else {
            filterModel = this.getSearchAirbillFilter();
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
                replaceMap.put("'", "");
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
            if (searchAirbillFilter.getPackageTypeId() != null && StringUtils.isNotEmpty(searchAirbillFilter.getPackageTypeId())) {
                if (searchAirbillFilter.getPackageTypeId().equals("0")) {
                    searchAirbillFilter.setPackageTypeId(null);
                }
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
        filterModel.setFranchiseList(this.buildFranchiseCodeList("All"));
        this.setSearchAirbillFilter(filterModel);
    }

    protected List<ListAirbillForMassEditModel> getListAirbill() {
        List<ListAirbillForMassEditModel> listAirbill = GsonUtils.fromGson(this.getListAirbillStr(), new TypeToken<List<ListAirbillForMassEditModel>>() {
        }.getType());
        return listAirbill;
    }

    protected void buildCustomersList(BasicCustomerFilter filter) throws Exception {
        ICustomerService customerService = new CustomerServiceImp();
        List<BasicCustomerVo> customerVos = customerService.selectByBasicCustomerFilter(filter);
        List<BasicCustomerModel> customerModels = ModelUtils.createListModelFromVo(customerVos, BasicCustomerModel.class);
        List<BasicCustomerModel> result = new ArrayList<BasicCustomerModel>();
        BasicCustomerModel model = new BasicCustomerModel();
        model.setCustomerCode("0");
        model.setCustomerName(this.getLocalizationText(""));
        result.add(model);
        result.addAll(customerModels);
        this.setCustomers(result);
    }

    protected BasicCustomerFilter buildCustomerFilter() throws Exception {
        BasicCustomerFilter filter = new BasicCustomerFilter();
        filter.setSearchText(searchText);
        filter.setFranchiseCode(franchiseCode);
        return filter;
    }

    public List<ListAirbillForMassEditModel> getListAirbillMassEdit() {
        return listAirbillMassEdit;
    }

    public void setListAirbillMassEdit(List<ListAirbillForMassEditModel> listAirbillMassEdit) {
        this.listAirbillMassEdit = listAirbillMassEdit;
    }

    public SearchAirbillFilterModel getSearchAirbillFilter() {
        return searchAirbillFilter;
    }

    public void setSearchAirbillFilter(SearchAirbillFilterModel searchAirbillFilter) {
        this.searchAirbillFilter = searchAirbillFilter;
    }

    public String getListAirbillStr() {
        return listAirbillStr;
    }

    public void setListAirbillStr(String listAirbillStr) {
        this.listAirbillStr = listAirbillStr;
    }

    public String getFranchiseCode() {
        return franchiseCode;
    }

    public void setFranchiseCode(String franchiseCode) {
        this.franchiseCode = franchiseCode;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<BasicCustomerModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<BasicCustomerModel> customers) {
        this.customers = customers;
    }

}
