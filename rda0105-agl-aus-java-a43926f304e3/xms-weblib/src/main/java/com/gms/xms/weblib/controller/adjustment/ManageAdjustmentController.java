package com.gms.xms.weblib.controller.adjustment;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.adjustment.AirbillAdjustmentDetailFilterModel;
import com.gms.xms.model.adjustment.AirbillAdjustmentDetailModel;
import com.gms.xms.model.adjustment.AirbillAdjustmentTotalModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.AirbillAdjustmentVo;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.adjustment.AdjustmentStatus;
import com.gms.xms.txndb.vo.adjustment.AirbillAdjustmentDetailFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.WebUtils;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import com.gms.xms.workflow.client.integration.response.ManageAdjustmentResponse;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from ManageAdjustmentController
 * <p>
 * Author DatTV Date May 13, 2015 10:46:39 AM
 */
public class ManageAdjustmentController extends JsonBaseController {
    private static final long serialVersionUID = 1L;
    private AirbillAdjustmentDetailFilterModel filter;
    private List<String> requestTypes;
    private List<AdjustmentStatus> statusList;
    private List<ServiceModel> carrierList;
    private Paging<AirbillAdjustmentDetailModel> adjustmentList;
    private AirbillAdjustmentTotalModel total;
    private List<FranchiseInfoModel> franchiseList;
    private List<String> pageSizeList;
    private Integer userLevel;
    private String htmlContent;
    private InputStream stream;

    public String show() {
        try {
            prepareRequestTypes();
            prepareStatusList();
            prepareCarrierList();
            prepareFranchiseList();
            preparePageSizeList();
            determineUserLevel();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        return "success";
    }

    public String search() {
        if (filter == null) {
            return "success";
        }
        try {
            preparePageSizeList();
            prepareFranchiseList();
            prepareAdjustmentList();
            determineUserLevel();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        return "success";
    }

    public String doPrint() {
        if (filter == null) {
            return "redirect";
        }
        try {
            prepareFranchiseList();
            determineUserLevel();
            String outputFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/manage_adjustment_" + request.getSession().getId() + ".html";
            String realPath = WebUtils.getContextPathURL(request);

            AirbillAdjustmentDetailFilter filterVo = new AirbillAdjustmentDetailFilter();
            this.buildFilter(filterVo);
            ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
            client.renderManageAdjustmentHtmlFile(filterVo, this.getUserLevel(), realPath, outputFilePath);
            this.setHtmlContent(AppUtils.readUTF8File2String(outputFilePath));
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }

        return "export";
    }

    public String doExportXls() {
        if (filter == null) {
            return "redirect";
        } else {
            try {
                prepareFranchiseList();
                determineUserLevel();
                String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/manage_adjustment_" + request.getSession().getId() + ".xls";
                String tmpName = "";
                if (this.getUserLevel() < 3) {
                    tmpName = "manage_adjustment.xls";
                } else {
                    tmpName = "manage_adjustment_lv_from_3.xls";
                }
                AirbillAdjustmentDetailFilter filterVo = new AirbillAdjustmentDetailFilter();
                this.buildFilter(filterVo);
                ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
                client.renderManageAdjustmentXLSFile(filterVo, outPutFilePath, tmpName);
                this.setStream(new FileInputStream(new File(outPutFilePath)));
                response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            } catch (Exception e) {
                this.setErrorCode(ErrorCode.ACTION_ERROR);
                this.setErrorMessage(this.getLocalizationText(e.getMessage()));
                log.error(e);
            }
        }
        return "export";
    }

    private void determineUserLevel() throws Exception {
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
        Double userLevel = client.getUserLevel(userLevelId);
        this.userLevel = userLevel.intValue();
    }

    private void prepareRequestTypes() throws DaoException {
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        List<AirbillAdjustmentVo> adjustmentVos = client.getRequestTypes();
        List<String> typeList = new ArrayList<String>();
        for (AirbillAdjustmentVo adjustment : adjustmentVos) {
            typeList.add(adjustment.getAdjustmentType());
        }
        this.setRequestTypes(typeList);
    }

    private void prepareStatusList() {
        List<AdjustmentStatus> list = new ArrayList<AdjustmentStatus>();
        list.add(new AdjustmentStatus(1, "Submitted"));
        list.add(new AdjustmentStatus(2, "Pending"));
        list.add(new AdjustmentStatus(3, "Disputed"));
        list.add(new AdjustmentStatus(4, "Approved"));
        list.add(new AdjustmentStatus(5, "Disputed Denied"));
        list.add(new AdjustmentStatus(6, "Closed")); // Deleted
        list.add(new AdjustmentStatus(11, AppConstants.ADJUSTMENT_SUBSTATUS_REQUEST_FRANCHISE_COMMENT));
        list.add(new AdjustmentStatus(12, AppConstants.ADJUSTMENT_SUBSTATUS_UPDATE_TO_FSC));
        this.setStatusList(list);
    }

    private void prepareCarrierList() throws Exception {
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        List<ServiceVo> serviceVos = client.getServices();
        this.setCarrierList(ModelUtils.createListModelFromVo(serviceVos, ServiceModel.class));
    }

    private void preparePageSizeList() {
        this.setPageSizeList(this.buildPageSizeList());
    }

    private void prepareAdjustmentList() throws Exception {
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        AirbillAdjustmentDetailFilter filterVo = new AirbillAdjustmentDetailFilter();
        Paging<AirbillAdjustmentDetailModel> paging = new Paging<AirbillAdjustmentDetailModel>();
        this.buildFilterAndPaging(filterVo, paging);
        ManageAdjustmentResponse response = client.getAWBAdjustmentByFilter(filterVo);
        paging.setRecords(ModelUtils.createListModelFromVo(response.getAdjustmentList(), AirbillAdjustmentDetailModel.class));
        this.setAdjustmentList(paging);
        this.setTotal(ModelUtils.createModelFromVo(response.getTotalRecord(), AirbillAdjustmentTotalModel.class));
    }

    private void buildFilterAndPaging(AirbillAdjustmentDetailFilter filterVo, Paging<AirbillAdjustmentDetailModel> paging) {
        // Get page size
        Integer pageSize = 0;
        try {
            pageSize = "All".equalsIgnoreCase(filter.getPageSize()) ? null : Integer.valueOf(filter.getPageSize());
        } catch (Exception e) {
            pageSize = 10;
        }
        filterVo.setPageSize(pageSize);
        // Get current page
        int page = 0;
        try {
            page = Integer.valueOf(filter.getPage());
        } catch (Exception e) {
            page = 1;
        }
        filterVo.setPage(page);
        // Get request date from
        Date requestDateFrom = null;
        try {
            requestDateFrom = DateUtils.convertStringToDate(filter.getRequestDateFrom(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        filterVo.setRequestDateFrom(requestDateFrom);
        // Get request date to
        Date requestDateTo = null;
        try {
            requestDateTo = DateUtils.convertStringToDate(filter.getRequestDateTo(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        filterVo.setRequestDateTo(requestDateTo);
        // Get response date from
        Date responseDateFrom = null;
        try {
            responseDateFrom = DateUtils.convertStringToDate(filter.getResponseDateFrom(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        filterVo.setResponseDateFrom(responseDateFrom);
        // Get request date to
        Date responseDateTo = null;
        try {
            responseDateTo = DateUtils.convertStringToDate(filter.getResponseDateTo(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        filterVo.setResponseDateTo(responseDateTo);
        // Get request type
        filterVo.setAdjustmentType(filter.getAdjustmentType());
        // Get status
        Byte status = null;
        Byte subStatus = null;
        try {
            status = StringUtils.isBlank(filter.getStatus()) ? null : Byte.valueOf(filter.getStatus());
            if (status != null) {
                if (status == 11) {
                    status = 1;
                    subStatus = 1;
                } else if (status == 12) {
                    status = 1;
                    subStatus = 2;
                } else if (status == 1) {
                    status = 1;
                    subStatus = 0;
                }
            }
        } catch (Exception e) {
        }
        filterVo.setStatus(status);
        if (subStatus != null) {
            filterVo.setSubStatus(Integer.valueOf(subStatus));
        }
        // Get carrier
        Integer carrier = null;
        try {
            carrier = StringUtils.isBlank(filter.getServiceId()) ? null : Integer.valueOf(filter.getServiceId());
        } catch (Exception e) {
        }
        filterVo.setServiceId(carrier);
        // Get airbill number
        filterVo.setAirbillNumber(filter.getAirbillNumber());
        // Get customer code
        filterVo.setCustomerCode(filter.getCustomerCode());
        // Get note
        filterVo.setNote(filter.getNote());
        // Get franchise code list
        filterVo.setFranchiseCodeList(this.buildFranchiseCodeList());
        // Get credit code
        filterVo.setCreditCode(filter.getCreditCode());
        // Get order clause
        filterVo.setOrderBy(filter.getOrderBy());
        // Get records count
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        Long totalRecord = null;
        try {
            totalRecord = client.countAWBAdjustmentByFilter(filterVo);
        } catch (Exception e) {
            totalRecord = 0L;
        }
        // Adjust current page
        paging.setCurrentPage(page);
        paging.setNlinks(5);
        paging.setPageSize(pageSize == null ? totalRecord.intValue() : pageSize);
        paging.setTotalRecords(totalRecord);
        paging.doPaging();
        filterVo.setPage(paging.getCurrentPage());
    }

    private void buildFilter(AirbillAdjustmentDetailFilter filterVo) {
        // Get page size
        Integer pageSize = null;
        filterVo.setPageSize(pageSize);
        // Get current page
        Integer page = null;
        filterVo.setPage(page);
        // Get request date from
        Date requestDateFrom = null;
        try {
            requestDateFrom = DateUtils.convertStringToDate(filter.getRequestDateFrom(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        filterVo.setRequestDateFrom(requestDateFrom);
        // Get request date to
        Date requestDateTo = null;
        try {
            requestDateTo = DateUtils.convertStringToDate(filter.getRequestDateTo(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        filterVo.setRequestDateTo(requestDateTo);
        // Get response date from
        Date responseDateFrom = null;
        try {
            responseDateFrom = DateUtils.convertStringToDate(filter.getResponseDateFrom(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        filterVo.setResponseDateFrom(responseDateFrom);
        // Get request date to
        Date responseDateTo = null;
        try {
            responseDateTo = DateUtils.convertStringToDate(filter.getResponseDateTo(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
        } catch (Exception e) {
        }
        filterVo.setResponseDateTo(responseDateTo);
        // Get request type
        filterVo.setAdjustmentType(filter.getAdjustmentType());
        // Get status
        Byte status = null;
        Byte subStatus = null;
        try {
            status = StringUtils.isBlank(filter.getStatus()) ? null : Byte.valueOf(filter.getStatus());
            if (status != null) {
                if (status == 11) {
                    status = 1;
                    subStatus = 1;
                } else if (status == 12) {
                    status = 1;
                    subStatus = 2;
                } else if (status == 1) {
                    status = 1;
                    subStatus = null;
                }
            }
        } catch (Exception e) {
        }
        filterVo.setStatus(status);
        if (subStatus != null) {
            filterVo.setSubStatus(Integer.valueOf(subStatus));
        }
        // Get carrier
        Integer carrier = null;
        try {
            carrier = StringUtils.isBlank(filter.getServiceId()) ? null : Integer.valueOf(filter.getServiceId());
        } catch (Exception e) {
        }
        filterVo.setServiceId(carrier);
        // Get airbill number
        filterVo.setAirbillNumber(filter.getAirbillNumber());
        // Get customer code
        filterVo.setCustomerCode(filter.getCustomerCode());
        // Get note
        filterVo.setNote(filter.getNote());
        // Get franchise code list
        filterVo.setFranchiseCodeList(this.buildFranchiseCodeList());
        // Get credit code
        filterVo.setCreditCode(filter.getCreditCode());
        // Get order clause
        filterVo.setOrderBy(filter.getOrderBy());
        // Get records count
    }

    private void prepareFranchiseList() throws Exception {
        // Get franchise list managed by logged user
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        List<FranchiseInfoVo> franchiseInfoVos = client.getFranchiseListManagedByUser((String) this.getSession("SESS_XMS_ADMIN_ID"));
        List<FranchiseInfoModel> franchiseInfoModels = ModelUtils.createListModelFromVo(franchiseInfoVos, FranchiseInfoModel.class);
        this.setFranchiseList(franchiseInfoModels);
    }

    private List<String> buildFranchiseCodeList() {
        List<String> result = new ArrayList<String>();
        if (StringUtils.isBlank(filter.getFranchiseCode()) || "All".equalsIgnoreCase(filter.getFranchiseCode())) {
            for (FranchiseInfoModel franchise : franchiseList) {
                result.add(franchise.getCode());
            }
        } else {
            result.add(filter.getFranchiseCode());
        }
        return result;
    }

    public AirbillAdjustmentDetailFilterModel getFilter() {
        return filter;
    }

    public void setFilter(AirbillAdjustmentDetailFilterModel filter) {
        this.filter = filter;
    }

    public List<String> getRequestTypes() {
        return requestTypes;
    }

    public void setRequestTypes(List<String> requestTypes) {
        this.requestTypes = requestTypes;
    }

    public List<AdjustmentStatus> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<AdjustmentStatus> statusList) {
        this.statusList = statusList;
    }

    public List<ServiceModel> getCarrierList() {
        return carrierList;
    }

    public void setCarrierList(List<ServiceModel> carrierList) {
        this.carrierList = carrierList;
    }

    public Paging<AirbillAdjustmentDetailModel> getAdjustmentList() {
        return adjustmentList;
    }

    public void setAdjustmentList(Paging<AirbillAdjustmentDetailModel> adjustmentList) {
        this.adjustmentList = adjustmentList;
    }

    public AirbillAdjustmentTotalModel getTotal() {
        return total;
    }

    public void setTotal(AirbillAdjustmentTotalModel total) {
        this.total = total;
    }

    public List<FranchiseInfoModel> getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(List<FranchiseInfoModel> franchiseList) {
        this.franchiseList = franchiseList;
    }

    public List<String> getPageSizeList() {
        return pageSizeList;
    }

    public void setPageSizeList(List<String> pageSizeList) {
        this.pageSizeList = pageSizeList;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }
}