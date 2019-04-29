package com.gms.xms.weblib.controller.account.franchises;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.account.franchises.FranchiseDetailFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.account.franchises.FranchiseDetailModel;
import com.gms.xms.model.account.franchises.FranchiseListColumnFlagsModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.FranchiseInfoVo;
import com.gms.xms.txndb.vo.account.franchises.FranchiseDetailVo;
import com.gms.xms.txndb.vo.account.franchises.FranchiseListColumnFlagsVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.workflow.render.franchise.FranchiseRenderImp;
import com.gms.xms.workflow.render.franchise.IFranchiseRender;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Posted from FranchiseListController
 * <p>
 * Author DatTV Oct 22, 2015
 */
public class FranchiseListController extends JsonBaseController {

    private static final long serialVersionUID = 1587880101974723174L;

    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private Paging<FranchiseDetailModel> franchises;
    private List<String> pageSizes;
    private FranchiseListColumnFlagsModel columnFlags;

    private InputStream stream;
    private String fileName;

    public String show() {
        try {
            // Set default order field and order type
            this.setOrderField("franchise_code");
            this.setOrderType("0");
            // Prepare page size list
            this.setPageSizes(this.buildPageSizeList());
            loadFranchises();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String search() {
        try {
            loadFranchises();
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
        return "success";
    }

    public String export() {
        try {
            FranchiseDetailFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            this.fileName = "franchise_list";
            String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName + "_" + this.request.getRequestedSessionId() + ".xls";
            FranchiseListColumnFlagsVo columnFlagsVo = ModelUtils.createVoFromModel(this.columnFlags, FranchiseListColumnFlagsVo.class);
            IFranchiseRender render = new FranchiseRenderImp(this.getAddInfoMap());
            render.renderFranchiseListXlsFile(filter, outPutFilePath, columnFlagsVo);
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void loadFranchises() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());

        FranchiseDetailFilter filter = this.buildFilter();
        IFranchiseService franchiseService = new FranchiseServiceImp();
        long recordCount = franchiseService.countFranchises(filter);

        // Set paging info
        Paging<FranchiseDetailModel> paging = new Paging<FranchiseDetailModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());

        // Get data of page
        List<FranchiseDetailVo> franchiseDetailVos = franchiseService.getFranchises(filter);
        List<FranchiseDetailModel> franchiseDetailModels = ModelUtils.createListModelFromVo(franchiseDetailVos, FranchiseDetailModel.class);
        paging.setRecords(franchiseDetailModels);
        this.setFranchises(paging);
    }

    private String buildFranchiseCodeList() throws Exception {
        IFranchiseService franchiseService = new FranchiseServiceImp();
        String userId = (String) this.getSession("SESS_XMS_ADMIN_ID");
        List<FranchiseInfoVo> franchiseInfoVos = franchiseService.getFranchiseListManagedByUser(userId);
        String codeList = "";
        for (FranchiseInfoVo franchiseInfoVo : franchiseInfoVos) {
            codeList += franchiseInfoVo.getCode() + ",";
        }
        return codeList.substring(0, codeList.length() - 1);
    }

    private FranchiseDetailFilter buildFilter() throws Exception {
        FranchiseDetailFilter filter = new FranchiseDetailFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList());
        filter.setPage(StringUtils.isBlank(page) ? 1 : Integer.valueOf(page));
        // StringUtils.isBlank(pageSize) ?
        // Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) :
        // Integer.valueOf(pageSize)
        filter.setPageSize(StringUtils.isBlank(pageSize) ? 25 : Integer.valueOf(pageSize));
        filter.setOrderField(StringUtils.isBlank(orderField) ? null : orderField);
        filter.setOrderType(StringUtils.isBlank(orderType) ? null : Integer.valueOf(orderType));
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

    public Paging<FranchiseDetailModel> getFranchises() {
        return franchises;
    }

    public void setFranchises(Paging<FranchiseDetailModel> franchises) {
        this.franchises = franchises;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FranchiseListColumnFlagsModel getColumnFlags() {
        return columnFlags;
    }

    public void setColumnFlags(FranchiseListColumnFlagsModel columnFlags) {
        this.columnFlags = columnFlags;
    }
}
