package com.gms.xms.weblib.controller.admin.accessorial;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.surchargelist.SurchargeListModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.accessorial.AccessorialServiceImp;
import com.gms.xms.persistence.service.accessorial.IAccessorialSerivce;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.txndb.vo.AccessorialFilter;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.surchargelist.SurchargeListVo;
import com.gms.xms.txndb.vo.webship.ServiceFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Posted from SurchargeListController
 * <p>
 * Author HungNT Date Sep 14, 2015
 */
public class SurchargeListController extends JsonBaseController {
    private static final long serialVersionUID = -7945740360436049333L;

    private Paging<SurchargeListModel> surchargeList;
    private List<ServiceModel> serviceList;
    private AccessorialModel accessorial;
    private String accessorialId;
    private List<String> listPageSize = this.buildPageSizeList();
    private Map<String, String> accessorialTypeList;
    private String pageSize;
    private String page;
    private String orderField;
    private String orderType;
    private String code;
    private String modifiedDate;
    private String type;
    private String description;
    private String carrierName;
    private String quotable;
    private Map<Integer, String> quotableList;

    public String show() {
        try {
            buildAccessorialTypeList();
            prepareQuotableList();
            setPageSize(this.listPageSize.get(0));
            // Set default sorting props.
            setPage("1");
            setOrderField("code");
            setOrderType("0");
            setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            // Get surcharge list.
            searchSurchargeList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareQuotableList() {
        Map<Integer, String> quotableList = new HashMap<Integer, String>();
        quotableList.put(0, this.getLocalizationText("No"));
        quotableList.put(1, this.getLocalizationText("Yes"));
        this.setQuotableList(quotableList);
    }

    protected void searchSurchargeList() throws Exception {
        IAccessorialSerivce service = new AccessorialServiceImp();
        AccessorialFilter filter = this.buildFilter();
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        long recordCount = service.getSurchargeListRecordCount(filter);
        Paging<SurchargeListModel> paging = new Paging<SurchargeListModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        this.setPage(String.valueOf(filter.getPage()));
        List<SurchargeListVo> surchargeListVos = service.getSurchargeList(this.buildFilter());
        List<SurchargeListModel> surchargeListModels = ModelUtils.createListModelFromVo(surchargeListVos, SurchargeListModel.class);
        surchargeListModels = this.setTypeId(surchargeListModels);
        paging.setRecords(surchargeListModels);
        this.setSurchargeList(paging);
    }

    public String getData() {
        try {
            searchSurchargeList();
            buildAccessorialTypeList();
            prepareQuotableList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String edit() {
        try {
            this.prepareQuotableList();
            this.buildServiceList();
            this.buildAccessorialTypeList();
            IAccessorialSerivce service = new AccessorialServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (this.accessorial != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        AccessorialModel accessorialModel = this.accessorial;
                        this.accessorial = accessorialModel;
                        return "success";
                    }
                    AccessorialVo accessorialVo = ModelUtils.createVoFromModel(this.accessorial, AccessorialVo.class);
                    accessorialVo.setModifiedDate(new Date());
                    if (accessorialVo.getIsQuotable() == null) {
                        accessorialVo.setIsQuotable(0);
                    }
                    service.updateAccessorial(this.getAddInfoMap(), accessorialVo);
                    searchSurchargeList();
                    return "saved";
                } else {
                    if (!StringUtils.isBlank(this.accessorialId)) {
                        AccessorialVo accessorialVo = service.selectById(Long.parseLong(this.accessorialId));
                        AccessorialModel accessorialModel = ModelUtils.createModelFromVo(accessorialVo, AccessorialModel.class);
                        this.accessorial = accessorialModel;
                    } else {
                        throw new CustomException("Please select an accessorial to edit.");
                    }
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String add() {
        try {
            this.prepareQuotableList();
            this.buildServiceList();
            this.buildAccessorialTypeList();
            IAccessorialSerivce service = new AccessorialServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (accessorial != null) {
                    if (!this.validateFields()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                    AccessorialVo accessorialVo = ModelUtils.createVoFromModel(this.accessorial, AccessorialVo.class);
                    accessorialVo.setModifiedDate(new Date());
                    if (accessorialVo.getIsQuotable() == null) {
                        accessorialVo.setIsQuotable(0);
                    }
                    service.addAccessorial(this.getAddInfoMap(), accessorialVo);
                    searchSurchargeList();
                    return "saved";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected List<SurchargeListModel> setTypeId(List<SurchargeListModel> surchargeListModels) {
        for (SurchargeListModel surchargeListModel : surchargeListModels) {
            switch (surchargeListModel.getTypeId()) {
                case "1":
                    surchargeListModel.setTypeId(this.getLocalizationText("Fixed"));
                    break;
                case "2":
                    surchargeListModel.setTypeId(this.getLocalizationText("Percent of Base Charge"));
                    break;
                case "3":
                    surchargeListModel.setTypeId(this.getLocalizationText("Percent of Surcharge"));
                    break;
                default:
                    break;
            }

            if (surchargeListModel.getIsQuotable().equals("0")) {
                surchargeListModel.setIsQuotable(this.getLocalizationText("No"));
            } else {
                surchargeListModel.setIsQuotable(this.getLocalizationText("Yes"));
            }
        }
        return surchargeListModels;
    }

    protected void buildAccessorialTypeList() {
        Map<String, String> typeList = new TreeMap<String, String>();
        typeList.put("1", this.getLocalizationText("Fixed"));
        typeList.put("2", this.getLocalizationText("Percent of Base Charge"));
        typeList.put("3", this.getLocalizationText("Percent of Surcharge"));
        this.accessorialTypeList = typeList;
    }

    protected void buildServiceList() throws Exception {
        IServiceService service = new ServiceServiceImp();
        ServiceFilter filter = new ServiceFilter();
        List<ServiceVo> serviceVos = service.getAllService(filter);
        List<ServiceModel> serviceModels = ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class);
        this.serviceList = serviceModels;
    }

    protected AccessorialFilter buildFilter() throws Exception {
        AccessorialFilter filter = new AccessorialFilter();
        // Set code.
        filter.setCode(this.getCode());
        // Set modified date.
        Date modifiedDate = null;
        try {
            modifiedDate = DateUtils.convertStringToDate(this.getModifiedDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
            throw new CustomException("Invalid invoice date.");
        }
        filter.setModifiedDate(modifiedDate);
        // Set type id.
        Integer typeId = StringUtils.isBlank(this.getType()) ? null : Integer.valueOf(this.getType());
        filter.setTypeId(typeId);
        // Set description.
        filter.setDescription(this.getDescription());
        // Set carrier name.
        filter.setCarrierName(this.getCarrierName());
        // Set is quotable.
        Integer isQuotable = StringUtils.isBlank(this.getQuotable()) ? null : Integer.valueOf(this.getQuotable());
        filter.setIsQuotable(isQuotable);
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "code" : this.getOrderField());
        return filter;
    }

    protected Boolean validateFields() {

        if (this.accessorial != null) {
            if (StringUtils.isBlank(this.accessorial.getCode())) {
                this.addFieldError("accessorial.code", "Accessorial Code cannot be empty.");
            }
            if (StringUtils.isBlank(this.accessorial.getDescription())) {
                this.addFieldError("accessorial.description", "Description cannot be empty");
            }
            if (StringUtils.isBlank(this.accessorial.getCarrier())) {
                this.addFieldError("accessorial.carrier", "Please select a carrier.");
            }
        }
        if (this.hasFieldErrors()) {
            return false;
        }
        return true;
    }

    public Paging<SurchargeListModel> getSurchargeList() {
        return surchargeList;
    }

    public void setSurchargeList(Paging<SurchargeListModel> surchargeList) {
        this.surchargeList = surchargeList;
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

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public List<ServiceModel> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<ServiceModel> serviceList) {
        this.serviceList = serviceList;
    }

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public AccessorialModel getAccessorial() {
        return accessorial;
    }

    public void setAccessorial(AccessorialModel accessorial) {
        this.accessorial = accessorial;
    }

    public Map<String, String> getAccessorialTypeList() {
        return accessorialTypeList;
    }

    public void setAccessorialTypeList(Map<String, String> accessorialTypeList) {
        this.accessorialTypeList = accessorialTypeList;
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

    public Map<Integer, String> getQuotableList() {
        return quotableList;
    }

    public void setQuotableList(Map<Integer, String> quotableList) {
        this.quotableList = quotableList;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQuotable() {
        return quotable;
    }

    public void setQuotable(String quotable) {
        this.quotable = quotable;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
