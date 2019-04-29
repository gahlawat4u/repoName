package com.gms.xms.weblib.controller.receivables;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.FranchiseInfoModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.overpayment.OverpaymentInfoFilterModel;
import com.gms.xms.model.overpayment.OverpaymentInfoModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.txndb.vo.overpayment.OverpaymentInfoFilter;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.client.FranchisePayableClient;
import com.gms.xms.workflow.client.InvoiceOverpaymentClient;
import com.gms.xms.workflow.client.adjustment.ManageAdjustmentClient;
import com.gms.xms.workflow.client.integration.request.InvoiceOverpaymentRequest;
import com.gms.xms.workflow.client.integration.response.InvoiceOverpaymentResponse;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Posted from InvoiceOverpaymentController
 * <p>
 * Author DatTV Date Apr 23, 2015 4:38:20 PM
 */
public class InvoiceOverpaymentController extends JsonBaseController {
    private static final long serialVersionUID = 1L;

    private List<FranchiseInfoModel> franchiseList;
    private OverpaymentInfoFilterModel filter;
    private Paging<OverpaymentInfoModel> overpaymentList;
    private String totalOverpayment;
    private String recordCount;
    private List<String> pageSizeList;
    private Integer userLevel;

    public String showPage() {
        try {
            prepareFranchiseList();
            preparePageSizeList();
            determineUserLevel();
        } catch (Exception e) {
            addActionError(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        if (filter == null) {
            return "success";
        } else {
            try {
                prepareOverpaymentsInfo();
            } catch (Exception e) {
                addActionError(this.getLocalizationText(e.getMessage()));
                log.error(e);
            }
        }
        return "success";
    }

    public String getOverpayList() {
        try {
            prepareFranchiseList();
            preparePageSizeList();
            determineUserLevel();
            prepareOverpaymentsInfo();
        } catch (Exception e) {
            setErrorCode(ErrorCode.ACTION_ERROR);
            setErrorMessage(this.getLocalizationText(e.getMessage()));
            log.error(e);
        }
        return "success";
    }

    @SuppressWarnings("unchecked")
    private void prepareFranchiseList() throws Exception {
        // Get franchise list managed by logged user
        FranchisePayableClient payableClient = new FranchisePayableClient(this.getAddInfoMap());
        Map<String, Object> resultMap = payableClient.getFranchiseListManagedByUser((String) this.getSession("SESS_XMS_ADMIN_ID"));
        List<FranchiseInfoModel> franchiseList = (List<FranchiseInfoModel>) resultMap.get(Attributes.RESULT);
        this.setFranchiseList(franchiseList);
    }

    private void prepareOverpaymentsInfo() throws Exception {
        InvoiceOverpaymentClient client = new InvoiceOverpaymentClient(this.getAddInfoMap());
        InvoiceOverpaymentRequest request = new InvoiceOverpaymentRequest();
        request.setFilter(this.buildFilter());
        InvoiceOverpaymentResponse response = client.getOverpaymentInfoByFilter(request);
        List<OverpaymentInfoModel> overpaymentInfoModels = ModelUtils.createListModelFromVo(response.getOverpaymentList(), OverpaymentInfoModel.class);
        overpaymentList.setRecords(overpaymentInfoModels);
        this.setTotalOverpayment(String.valueOf(response.getTotalOverpayment()));
    }

    private OverpaymentInfoFilter buildFilter() throws Exception {
        InvoiceOverpaymentClient client = new InvoiceOverpaymentClient(this.getAddInfoMap());
        OverpaymentInfoFilter opfilter = new OverpaymentInfoFilter();
        opfilter.setCustomerCode(filter.getCustomerCode());
        opfilter.setFranchiseList(this.buildFranchiseCodeList(filter.getFranchiseCode()));
        long recordCount = client.countOverpaymentInfoByFilter(opfilter);
        this.setRecordCount(String.valueOf(recordCount));
        int page = StringUtils.isBlank(filter.getPage()) ? 1 : Integer.valueOf(filter.getPage());
        int pageSize = StringUtils.isBlank(filter.getPageSize()) ? 10 : Integer.valueOf(filter.getPageSize());
        Paging<OverpaymentInfoModel> paging = new Paging<OverpaymentInfoModel>(page, 5, pageSize, recordCount);
        this.setOverpaymentList(paging);
        opfilter.setStartRecord((paging.getCurrentPage() - 1) * paging.getPageSize());
        opfilter.setRecordSize(paging.getPageSize());
        return opfilter;
    }

    private List<String> buildFranchiseCodeList(String franchiseCode) {
        List<String> result = new ArrayList<String>();
        if (StringUtils.isBlank(franchiseCode) || "All".equalsIgnoreCase(franchiseCode)) {
            for (FranchiseInfoModel franModel : franchiseList) {
                result.add(franModel.getCode());
            }
        } else {
            result.add(franchiseCode);
        }
        return result;
    }

    private void preparePageSizeList() {
        this.setPageSizeList(this.buildPageSizeList());
    }

    private void determineUserLevel() throws Exception {
        ManageAdjustmentClient client = new ManageAdjustmentClient(this.getAddInfoMap());
        Integer userLevelId = Integer.valueOf((String) this.getSession("SESS_XMS_ADMIN_LEVEL_ID"));
        Double userLevel = client.getUserLevel(userLevelId);
        this.userLevel = userLevel.intValue();
    }

    public List<FranchiseInfoModel> getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(List<FranchiseInfoModel> franchiseList) {
        this.franchiseList = franchiseList;
    }

    public OverpaymentInfoFilterModel getFilter() {
        return filter;
    }

    public void setFilter(OverpaymentInfoFilterModel filter) {
        this.filter = filter;
    }

    public Paging<OverpaymentInfoModel> getOverpaymentList() {
        return overpaymentList;
    }

    public void setOverpaymentList(Paging<OverpaymentInfoModel> overpaymentList) {
        this.overpaymentList = overpaymentList;
    }

    public String getTotalOverpayment() {
        return totalOverpayment;
    }

    public void setTotalOverpayment(String totalOverpayment) {
        this.totalOverpayment = totalOverpayment;
    }

    public String getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(String recordCount) {
        this.recordCount = recordCount;
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

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }
}