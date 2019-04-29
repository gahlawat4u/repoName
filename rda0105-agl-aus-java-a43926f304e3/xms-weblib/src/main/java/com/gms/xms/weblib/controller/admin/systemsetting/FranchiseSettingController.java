package com.gms.xms.weblib.controller.admin.systemsetting;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.filter.admin.FranchiseSettingListExtFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.FranchiseSettingListExtModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.FranchiseSettingServiceImp;
import com.gms.xms.persistence.service.admin.IFranchiseSettingService;
import com.gms.xms.txndb.vo.admin.FranchiseSettingListExtVo;
import com.gms.xms.weblib.controller.JsonBaseController;

import java.util.List;

/**
 * Posted from AdminEmailSettingController
 * <p>
 * Author TANDT
 */
public class FranchiseSettingController extends JsonBaseController {
    private static final long serialVersionUID = -7110570240797000983L;

    private String page;
    private String pageSize;
    private List<String> pageSizes;
    private Paging<FranchiseSettingListExtModel> franchiseSettingListExtModels;

    public String show() {
        try {
            pageSizes = this.buildPageSizeList();
            doSearch();
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    private void doSearch() {
        IFranchiseSettingService service = new FranchiseSettingServiceImp();
        FranchiseSettingListExtFilter filter = null;
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        int pSize = 0;
        try {
            pSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int iPage = 0;
        try {
            iPage = Integer.parseInt(this.page);
        } catch (Exception ex) {
            iPage = 1;
        }

        try {
            filter = this.buildFilter();
            long recordCount = service.selectListFranchiseByCodeCount(filter);

            // Set paging info
            Paging<FranchiseSettingListExtModel> paging = new Paging<FranchiseSettingListExtModel>(iPage, nLinks, pSize, recordCount);
            filter.setPage(paging.getCurrentPage());
            filter.setPageSize(paging.getPageSize());

            // Get data of page
            List<FranchiseSettingListExtVo> franchiseSettingListExtVos = service.selectListFranchiseByCode(filter);
            List<FranchiseSettingListExtModel> franchiseSettingListExtModels = ModelUtils.createListModelFromVo(franchiseSettingListExtVos, FranchiseSettingListExtModel.class);
            paging.setRecords(franchiseSettingListExtModels);
            this.setFranchiseSettingListExtModels(paging);
        } catch (Exception e) {
            log.error(e);
            setErrorMessage(e.getMessage());
            setErrorCode(ErrorCode.ACTION_ERROR);
        }
    }


    private FranchiseSettingListExtFilter buildFilter() throws Exception {
        FranchiseSettingListExtFilter filter = new FranchiseSettingListExtFilter();
        filter.setFranchiseCode(100);
        filter.setUserLevelCode(100);
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

    public Paging<FranchiseSettingListExtModel> getFranchiseSettingListExtModels() {
        return franchiseSettingListExtModels;
    }

    public void setFranchiseSettingListExtModels(Paging<FranchiseSettingListExtModel> franchiseSettingListExtModels) {
        this.franchiseSettingListExtModels = franchiseSettingListExtModels;
    }

}