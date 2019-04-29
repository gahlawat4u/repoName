package com.gms.xms.weblib.controller.admin.accessorial;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.AccessorialDetailModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.accessorial.AccessorialServiceImp;
import com.gms.xms.persistence.service.accessorial.IAccessorialSerivce;
import com.gms.xms.persistence.service.accessorialdetail.AccessorialDetailServiceImp;
import com.gms.xms.persistence.service.accessorialdetail.IAccessorialDetailService;
import com.gms.xms.txndb.vo.AccessorialDetailFilter;
import com.gms.xms.txndb.vo.AccessorialDetailUpdateVo;
import com.gms.xms.txndb.vo.AccessorialDetailVo;
import com.gms.xms.txndb.vo.AccessorialVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * Posted from AccessorialDetailController
 * <p>
 * Author HungNT Date Sep 16, 2015
 */
public class SurchargeDetailsController extends JsonBaseController {
    private static final long serialVersionUID = 8114768747241670263L;

    private Paging<AccessorialDetailModel> accessorialDetailList;
    private String accessorialName;
    private AccessorialDetailModel accessorialDetail;

    private List<String> listPageSize;
    private String oldApplyStartDate;
    private String accessorialId;
    private String applyStartDate;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    public String show() {
        try {
            // Set default sorting props.
            setPage("1");
            setOrderType("0");
            setOrderField("default_charge");
            // Detect name of accessorial.
            detectSurchargeName();
            this.listPageSize = this.buildPageSizeList();
            buildAccessorialDetailList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getData() {
        try {
            buildAccessorialDetailList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String edit() {
        try {
            IAccessorialDetailService service = new AccessorialDetailServiceImp();
            if (this.accessorialDetail == null) {
                AccessorialDetailFilter filter = new AccessorialDetailFilter();
                filter.setApplyStartDate(DateUtils.convertStringToDate(this.applyStartDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                filter.setAccessorialId(Long.parseLong(this.accessorialId));
                AccessorialDetailVo accessorialDetailVo = service.getAccessorialDetail(filter);
                AccessorialDetailModel accessorialDetailModel = ModelUtils.createModelFromVo(accessorialDetailVo, AccessorialDetailModel.class);
                this.accessorialDetail = accessorialDetailModel;
                this.oldApplyStartDate = accessorialDetailModel.getApplyStartDate();
            } else {
                if (!this.validateField()) {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return "success";
                }
                AccessorialDetailUpdateVo accessorialDetailUpdateVo = new AccessorialDetailUpdateVo();
                this.accessorialDetail.setAccessorialId(this.accessorialId);
                accessorialDetailUpdateVo = ModelUtils.createVoFromModel(this.accessorialDetail, AccessorialDetailUpdateVo.class);
                accessorialDetailUpdateVo.setOldApplyStartDate(DateUtils.convertStringToDate(this.oldApplyStartDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                // Check if the accessorial detail has created on that date.
                AccessorialDetailFilter filter = new AccessorialDetailFilter();
                filter.setAccessorialId(accessorialDetailUpdateVo.getAccessorialId());
                filter.setApplyStartDate(accessorialDetailUpdateVo.getApplyStartDate());
                AccessorialDetailVo accessorialDetailCheck = service.getAccessorialDetail(filter);
                if (!accessorialDetailUpdateVo.getApplyStartDate().equals(accessorialDetailUpdateVo.getOldApplyStartDate())) {
                    if (accessorialDetailCheck != null) {
                        throw new CustomException("The accessorial detail has already been created on this date.");
                    }
                }
                service.updateAccessorialDetail(this.getAddInfoMap(), accessorialDetailUpdateVo);
                buildAccessorialDetailList();
                return "saved";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String add() {
        try {
            IAccessorialDetailService service = new AccessorialDetailServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (this.accessorialDetail != null) {
                    if (!this.validateField()) {
                        this.setErrorCode(ErrorCode.FIELD_ERROR);
                        return "success";
                    }
                    this.accessorialDetail.setAccessorialId(this.accessorialId);
                    AccessorialDetailVo accessorialDetailVo = ModelUtils.createVoFromModel(this.accessorialDetail, AccessorialDetailVo.class);

                    // Check if the accessorial detail has created on that date.
                    AccessorialDetailFilter filter = new AccessorialDetailFilter();
                    filter.setAccessorialId(accessorialDetailVo.getAccessorialId());
                    filter.setApplyStartDate(accessorialDetailVo.getApplyStartDate());
                    AccessorialDetailVo accessorialDetailCheck = service.getAccessorialDetail(filter);
                    if (accessorialDetailCheck != null) {
                        throw new CustomException("The accessorial detail has already been created on this date.");
                    }
                    service.addAccessorialDetail(this.getAddInfoMap(), accessorialDetailVo);
                    buildAccessorialDetailList();
                    return "saved";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String delete() {
        try {
            IAccessorialDetailService service = new AccessorialDetailServiceImp();
            if (request.getMethod().equalsIgnoreCase("post")) {
                if (!StringUtils.isBlank(this.applyStartDate) && !StringUtils.isBlank(this.accessorialId)) {
                    AccessorialDetailFilter filter = new AccessorialDetailFilter();
                    filter.setAccessorialId(Long.parseLong(this.accessorialId));
                    filter.setApplyStartDate(DateUtils.convertStringToDate(this.applyStartDate, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                    service.deleteAccessorialDetail(this.getAddInfoMap(), filter);
                    buildAccessorialDetailList();
                    return "deleted";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void detectSurchargeName() throws Exception {
        IAccessorialSerivce service = new AccessorialServiceImp();
        AccessorialVo accessorialVo = service.selectById(Long.parseLong(this.getAccessorialId()));
        if (accessorialVo != null) {
            this.setAccessorialName(accessorialVo.getDescription());
        }
    }

    protected void buildAccessorialDetailList() throws Exception {
        if (!StringUtils.isBlank(this.accessorialId)) {
            IAccessorialDetailService service = new AccessorialDetailServiceImp();
            AccessorialDetailFilter filter = this.buildFilter();
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            long recordCount = service.getRecordCountByAccessorialId(Long.valueOf(this.getAccessorialId()));
            Paging<AccessorialDetailModel> paging = new Paging<AccessorialDetailModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            List<AccessorialDetailVo> accessorialDetailVos = service.getListbyAccessorialId(filter);
            List<AccessorialDetailModel> accessorialDetailModels = ModelUtils.createListModelFromVo(accessorialDetailVos, AccessorialDetailModel.class);
            paging.setRecords(accessorialDetailModels);
            this.setAccessorialDetailList(paging);
        } else {
            throw new CustomException("Please enter accessorial id.");
        }
    }

    protected AccessorialDetailFilter buildFilter() throws Exception {
        AccessorialDetailFilter filter = new AccessorialDetailFilter();
        filter.setAccessorialId(Long.parseLong(this.getAccessorialId()));
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "default_charge" : this.getOrderField());
        return filter;
    }

    protected Boolean validateField() {
        if (this.accessorialDetail == null) {
            return false;
        }
        if (StringUtils.isBlank(this.accessorialDetail.getDefaultCharge())) {
            this.addFieldError("accessorialDetail.defaultCharge", "Please enter default charge.");
        }

        if (!NumberUtils.isNumber(this.accessorialDetail.getDefaultCharge()) && !StringUtils.isBlank(this.accessorialDetail.getDefaultCharge())) {
            this.addFieldError("accessorialDetail.defaultCharge", "Default charge must be a number.");
        } else {
            Double defaultCharge = 0D;
            defaultCharge = Double.parseDouble(this.accessorialDetail.getDefaultCharge());
            if (defaultCharge < 0) {
                this.addFieldError("accessorialDetail.defaultCharge", "Default charge must be larger than 0.");
            }
        }
        if (!NumberUtils.isNumber(this.accessorialDetail.getDefaultChargeCarrier()) && !StringUtils.isBlank(this.accessorialDetail.getDefaultChargeCarrier())) {
            this.addFieldError("accessorialDetail.defaultChargeCarrier", "Default carrier charge must be a number.");
        } else {
            Double defaultCarrierCharge = 0D;
            defaultCarrierCharge = Double.parseDouble(this.accessorialDetail.getDefaultChargeCarrier());
            if (defaultCarrierCharge < 0) {
                this.addFieldError("accessorialDetail.defaultChargeCarrier", "Default carrier charge must be larger than 0.");
            }
        }
        if (StringUtils.isBlank(this.accessorialDetail.getApplyStartDate())) {
            this.addFieldError("accessorialDetail.applyStartDate", "Please enter apply start date.");
        }
        if (this.hasFieldErrors()) {
            return false;
        }
        return true;
    }

    public String getAccessorialId() {
        return accessorialId;
    }

    public void setAccessorialId(String accessorialId) {
        this.accessorialId = accessorialId;
    }

    public Paging<AccessorialDetailModel> getAccessorialDetailList() {
        return accessorialDetailList;
    }

    public void setAccessorialDetailList(Paging<AccessorialDetailModel> accessorialDetailList) {
        this.accessorialDetailList = accessorialDetailList;
    }

    public String getAccessorialName() {
        return accessorialName;
    }

    public void setAccessorialName(String accessorialName) {
        this.accessorialName = accessorialName;
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

    public List<String> getListPageSize() {
        return listPageSize;
    }

    public void setListPageSize(List<String> listPageSize) {
        this.listPageSize = listPageSize;
    }

    public String getApplyStartDate() {
        return applyStartDate;
    }

    public void setApplyStartDate(String applyStartDate) {
        this.applyStartDate = applyStartDate;
    }

    public AccessorialDetailModel getAccessorialDetail() {
        return accessorialDetail;
    }

    public void setAccessorialDetail(AccessorialDetailModel accessorialDetail) {
        this.accessorialDetail = accessorialDetail;
    }

    public String getOldApplyStartDate() {
        return oldApplyStartDate;
    }

    public void setOldApplyStartDate(String oldApplyStartDate) {
        this.oldApplyStartDate = oldApplyStartDate;
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
}
