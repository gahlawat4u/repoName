package com.gms.xms.weblib.controller.admin.carrier;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.admin.AdministrationFilter;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.StateModel;
import com.gms.xms.model.admin.administration.CountryListModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.DhlCountryModel;
import com.gms.xms.persistence.dao.StateDao;
import com.gms.xms.persistence.service.admin.AdministrationServiceImp;
import com.gms.xms.persistence.service.admin.IAdministrationService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.StateVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Posted from CountryListController
 * <p>
 * Author TANDT 17-10-2015
 */
public class CountryListController extends JsonBaseController {
    private static final long serialVersionUID = -213162220910274195L;
    private String pageSize;
    private String page;
    private String orderType;
    private String orderField;
    // Props for country list filter.
    private String countryCode;
    private String countryName;
    private String displayName;
    private String otherName;
    private String gst;
    private String modifiedDate;
    private String isShow;
    private String apCode;
    private String apZone;
    // Props for state list filter.
    private String id;
    private String stateName;
    private String stateCode;
    private String cityName;
    private String cityCode;
    private String postalCodeFrom;
    private String postalCodeTo;
    private String dhlZoneCode;
    private List<String> pageSizes;
    private Paging<CountryListModel> countryLists;
    private List<CountryListModel> countryListModels;
    private Paging<StateModel> stateLists;
    private List<StateModel> stateListModels;
    private CountryModel countryModel;
    private DhlCountryModel dhlCountryModel;
    private String countryId;
    private String stateId;
    private StateModel stateModel;
    private HashMap<String, String> listTimeZone;
    private String isEditCountry;
    private String isAddCountry;
    private String isAddState;
    private String isEditState;

    public String show() {
        try {
            // Set default sort
            setPage("1");
            setOrderType("0");
            setOrderField("countryCode");
            setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            pageSizes = this.buildPageSizeList();
            prepareDataCountryList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            pageSizes = this.buildPageSizeList();
            prepareDataCountryList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String edit() {
        try {
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (isEditCountry != null) {
                    if (validCountry()) {
                        updateCountry();
                        pageSizes = this.buildPageSizeList();
                        prepareDataCountryList();
                        return "home";
                    } else {
                        prepareListTimeZone();
                        return "success";
                    }
                } else {
                    prepareCountryDetail();
                }
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String editState() {
        try {
            prepareStateDetail();
            if (isEditState != null) {
                if (validState()) {
                    updateState();
                    pageSizes = this.buildPageSizeList();
                    this.setCountryId(stateModel.getCountryId());
                    prepareListState();
                    return "editSuccess";
                }
            } else {
                return "success";
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String deleteState() {
        try {
            StateDao stateDao = new StateDao();
            StateVo stateVo = stateDao.selectStateById(Long.parseLong(stateId));
            this.setCountryId(stateVo.getCountryId().toString());
            stateDao.delete(this.getAddInfoMap(), Long.parseLong(stateId));
            prepareListState();
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String showListState() {
        try {
            pageSizes = this.buildPageSizeList();
            prepareListState();
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String updateIsShow() {
        try {
            if (countryModel != null) {
                IAdministrationService service = new AdministrationServiceImp();
                service.updateIsShow(this.getAddInfoMap(), ModelUtils.createVoFromModel(countryModel, CountryVo.class));
                pageSizes = this.buildPageSizeList();
                prepareDataCountryList();
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String add() {
        try {
            if (isAddCountry != null) {
                if (validCountry()) {
                    addCountry();
                    pageSizes = this.buildPageSizeList();
                    prepareDataCountryList();
                    return "home";
                } else {
                    return "home";
                }
            } else {
                prepareCountryDetail();
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    public String addState() {
        try {
            if (isAddState != null) {
                if (validState()) {
                    doAddState();
                    pageSizes = this.buildPageSizeList();
                    this.setCountryId(stateModel.getCountryId());
                    prepareListState();
                    return "insertSuccess";
                } else {
                    return "home";
                }
            } else {
                prepareStateDetail();
            }
        } catch (Exception e) {
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
            addActionError(e.getMessage());
            log.error(e.getMessage(), e);
        }
        return "success";
    }

    private void updateState() throws Exception {
        IAdministrationService service = new AdministrationServiceImp();
        service.updateState(this.getAddInfoMap(), ModelUtils.createVoFromModel(stateModel, StateVo.class));
    }

    private void prepareCountryDetail() throws Exception {
        IAdministrationService service = new AdministrationServiceImp();
        if (countryId != null) {
            this.setCountryModel(ModelUtils.createModelFromVo(service.selectCountryDetail(Long.parseLong(countryId)), CountryModel.class));
            this.prepareListTimeZone();
        }
    }

    private void prepareStateDetail() throws Exception {
        StateDao stateDao = new StateDao();
        if (stateId != null) {
            this.setStateModel(ModelUtils.createModelFromVo(stateDao.selectStateById(Long.parseLong(stateId)), StateModel.class));
            this.prepareListTimeZone();
        }
    }

    private boolean prepareListState() throws Exception {
        if (StringUtils.isNotEmpty(countryId)) {
            IAdministrationService service = new AdministrationServiceImp();
            AdministrationFilter filter = buildFilter();
            filter.setCountryId(Long.parseLong(countryId));
            long recordCount = service.selectListStateByCountryIdCount(filter);
            int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            Paging<StateModel> paging = new Paging<StateModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            filter.setPageSize(paging.getPageSize());
            stateListModels = ModelUtils.createListModelFromVo(service.selectListStateByCountryId(filter), StateModel.class);
            paging.setRecords(stateListModels);
            this.setStateLists(paging);
        } else {
            setErrorMessage("Please select a country!");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        return true;
    }

    private void addCountry() throws Exception {
        IAdministrationService service = new AdministrationServiceImp();
        service.addCountry(this.getAddInfoMap(), ModelUtils.createVoFromModel(countryModel, CountryVo.class));
    }

    private void doAddState() throws Exception {
        StateDao stateService = new StateDao();
        stateService.insert(this.getAddInfoMap(), ModelUtils.createVoFromModel(stateModel, StateVo.class));
    }

    private boolean validCountry() throws Exception {
        ICountryService service = new CountryServiceImp();
        if (!StringUtils.isBlank(this.getCountryModel().getCountryName())) {
            CountryVo countryVo = service.selectCountryByCountryName(this.getCountryModel().getCountryName());
            Long currentCountryId = Long.valueOf(this.getCountryModel().getCountryId());
            if (countryVo != null && countryVo.getCountryId() != currentCountryId) {
                addFieldError("countryModel.countryName", "Country name exists.");
            }
        }
        if (StringUtils.isBlank(this.getCountryModel().getCountryCode().trim())) {
            addFieldError("countryModel.countryCode", "Please enter Country Code!");
        }
        if (StringUtils.isBlank(this.getCountryModel().getCountryName().trim())) {
            addFieldError("countryModel.countryName", "Please enter Country Name!");
        }
        if (StringUtils.isBlank(this.getCountryModel().getDisplayName())) {
            addFieldError("countryModel.displayName", "Please enter Other Name!");
        }
        if (hasFieldErrors()) {
            setErrorCode(ErrorCode.FIELD_ERROR);
            return false;
        }
        return true;
    }

    private boolean validState() throws Exception {
        IAdministrationService service = new AdministrationServiceImp();
        if (stateModel != null) {
            if (service.checkInsertState(ModelUtils.createVoFromModel(stateModel, StateVo.class)) > 1) {
                setErrorMessage("This State had exit");
                setErrorCode(ErrorCode.ACTION_ERROR);
                return false;
            }
        }
        if (StringUtils.isEmpty(stateModel.getStateName().trim())) {
            setErrorMessage("State Name is not empty!");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        if (StringUtils.isEmpty(stateModel.getStateCode().trim())) {
            setErrorMessage("State Code is not empty!");
            setErrorCode(ErrorCode.ACTION_ERROR);
            return false;
        }
        return true;
    }

    private void updateCountry() throws Exception {
        IAdministrationService service = new AdministrationServiceImp();
        service.updateCountry(this.getAddInfoMap(), ModelUtils.createVoFromModel(countryModel, CountryVo.class));
    }

    private void prepareDataCountryList() throws Exception {
        IAdministrationService service = new AdministrationServiceImp();
        AdministrationFilter filter = this.buildFilter();
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        long recordCount = service.selectCountryListCount(filter);
        Paging<CountryListModel> paging = new Paging<CountryListModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        countryListModels = ModelUtils.createListModelFromVo(service.selectCountryList(filter), CountryListModel.class);
        paging.setRecords(countryListModels);
        this.setCountryLists(paging);
    }

    private void prepareListTimeZone() throws Exception {
        this.setListTimeZone(AppUtils.getTimeZoneList());
    }

    private AdministrationFilter buildFilter() throws Exception {
        AdministrationFilter filter = new AdministrationFilter();
        // Set country code.
        filter.setCountryCode(this.getCountryCode());
        // Set country name.
        filter.setCountryName(this.getCountryName());
        // Set display name.
        filter.setDisplayName(this.getDisplayName());
        // Set other name.
        filter.setOtherName(this.getOtherName());
        // Set gst.
        Double gst = null;
        try {
            gst = StringUtils.isBlank(this.getGst()) ? null : Double.valueOf(this.getGst());
        } catch (Exception e) {
            throw new CustomException("Invalid GST: it must be a number.");
        }
        filter.setGst(gst);
        // Set modified date.
        Date modifiedDate = null;
        try {
            modifiedDate = DateUtils.convertStringToDate(this.getModifiedDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
            throw new CustomException("Invalid modified date.");
        }
        filter.setModifiedDate(modifiedDate);
        // Set is show.
        // Integer isShow = StringUtils.isBlank(this.getIsShow()) ? 0 : 1;
        // filter.setIsShow(isShow);
        // Set DHL Ap Code.
        filter.setApCode(this.getApCode());
        // Set DHL Ap Zone.
        filter.setApZone(this.getApZone());
        // Set id.
        Long id = null;
        try {
            id = StringUtils.isBlank(this.getId()) ? null : Long.valueOf(this.getId());
        } catch (Exception e) {
            throw new CustomException("Invalid id");
        }
        filter.setId(id);
        // Set state name.
        filter.setStateName(this.getStateName());
        // Set state code.
        filter.setStateCode(this.getStateCode());
        // Set city name.
        filter.setCityName(this.getCityName());
        // Set city code.
        filter.setCityCode(this.getCityCode());
        // Set postal code from.
        Long postalCodeFrom = null;
        try {
            postalCodeFrom = StringUtils.isBlank(this.getPostalCodeFrom()) ? null : Long.valueOf(this.getPostalCodeFrom());
        } catch (Exception e) {
            throw new CustomException("Invalid postal code from");
        }
        filter.setPostalCodeFrom(postalCodeFrom);
        // Set postal code to.
        Long postalCodeTo = null;
        try {
            postalCodeTo = StringUtils.isBlank(this.getPostalCodeTo()) ? null : Long.valueOf(this.getPostalCodeTo());
        } catch (Exception e) {
            throw new CustomException("Invalid postal code to");
        }
        filter.setPostalCodeTo(postalCodeTo);
        // Set DHL Zone code.
        filter.setDhlZoneCode(this.getDhlZoneCode());
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "state_name" : this.getOrderField());
        return filter;
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

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public Paging<CountryListModel> getCountryLists() {
        return countryLists;
    }

    public void setCountryLists(Paging<CountryListModel> countryLists) {
        this.countryLists = countryLists;
    }

    public List<CountryListModel> getCountryListModels() {
        return countryListModels;
    }

    public void setCountryListModels(List<CountryListModel> countryListModels) {
        this.countryListModels = countryListModels;
    }

    public CountryModel getCountryModel() {
        return countryModel;
    }

    public void setCountryModel(CountryModel countryModel) {
        this.countryModel = countryModel;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public HashMap<String, String> getListTimeZone() {
        return listTimeZone;
    }

    public void setListTimeZone(HashMap<String, String> listTimeZone) {
        this.listTimeZone = listTimeZone;
    }

    public String getIsEditCountry() {
        return isEditCountry;
    }

    public void setIsEditCountry(String isEditCountry) {
        this.isEditCountry = isEditCountry;
    }

    public DhlCountryModel getDhlCountryModel() {
        return dhlCountryModel;
    }

    public void setDhlCountryModel(DhlCountryModel dhlCountryModel) {
        this.dhlCountryModel = dhlCountryModel;
    }

    public String getIsAddCountry() {
        return isAddCountry;
    }

    public void setIsAddCountry(String isAddCountry) {
        this.isAddCountry = isAddCountry;
    }

    public Paging<StateModel> getStateLists() {
        return stateLists;
    }

    public void setStateLists(Paging<StateModel> stateLists) {
        this.stateLists = stateLists;
    }

    public List<StateModel> getStateListModels() {
        return stateListModels;
    }

    public void setStateListModels(List<StateModel> stateListModels) {
        this.stateListModels = stateListModels;
    }

    public StateModel getStateModel() {
        return stateModel;
    }

    public void setStateModel(StateModel stateModel) {
        this.stateModel = stateModel;
    }

    public String getIsAddState() {
        return isAddState;
    }

    public void setIsAddState(String isAddState) {
        this.isAddState = isAddState;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getIsEditState() {
        return isEditState;
    }

    public void setIsEditState(String isEditState) {
        this.isEditState = isEditState;
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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getOtherName() {
        return otherName;
    }

    public void setOtherName(String otherName) {
        this.otherName = otherName;
    }

    public String getGst() {
        return gst;
    }

    public void setGst(String gst) {
        this.gst = gst;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getApCode() {
        return apCode;
    }

    public void setApCode(String apCode) {
        this.apCode = apCode;
    }

    public String getApZone() {
        return apZone;
    }

    public void setApZone(String apZone) {
        this.apZone = apZone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getPostalCodeFrom() {
        return postalCodeFrom;
    }

    public void setPostalCodeFrom(String postalCodeFrom) {
        this.postalCodeFrom = postalCodeFrom;
    }

    public String getPostalCodeTo() {
        return postalCodeTo;
    }

    public void setPostalCodeTo(String postalCodeTo) {
        this.postalCodeTo = postalCodeTo;
    }

    public String getDhlZoneCode() {
        return dhlZoneCode;
    }

    public void setDhlZoneCode(String dhlZoneCode) {
        this.dhlZoneCode = dhlZoneCode;
    }
}