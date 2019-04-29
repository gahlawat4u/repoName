package com.gms.xms.weblib.controller.admin.ratesheets;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.RateSheetsPageModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.ratesheetspage.IRateSheetsPageService;
import com.gms.xms.persistence.service.admin.ratesheetspage.RateSheetsPageServiceImp;
import com.gms.xms.txndb.vo.admin.RateSheetsPageFilter;
import com.gms.xms.txndb.vo.admin.RateSheetsPageVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class RateSheetsController extends JsonBaseController {
    private static final long serialVersionUID = 6605683166927476562L;

    private Paging<RateSheetsPageModel> rateSheetsList;
    private List<String> listPageSize;

    private String sheetId;
    private String page;
    private String pageSize;

    private RateSheetsPageFilter rateSheetFilter;
    private Map<String, String> listRateSheetsType;

    public String show() {
        try {
            this.listPageSize = this.buildPageSizeList();
            this.buildRateSheetsList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getData() {
        try {
            this.buildRateSheetsList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String delete() {
        try {
            if (!StringUtils.isBlank(this.sheetId)) {
                IRateSheetsPageService service = new RateSheetsPageServiceImp();
                Long sheetId = 0l;
                try {
                    sheetId = Long.parseLong(this.sheetId);
                } catch (Exception e) {
                }
                // Check before delete
                Integer totalUsed = service.getTotalUsed(sheetId);
                if (totalUsed > 0) {
                    this.setErrorCode(ErrorCode.ERROR);
                    this.setErrorMessage("This rate sheet could not be deleted because it has been used.");
                    return "success";
                }
                // Delete
                service.deleteRateSheet(this.getAddInfoMap(), sheetId);
                this.buildRateSheetsList();
                return "deleted";
            } else {
                this.setErrorCode(ErrorCode.ERROR);
                this.setErrorMessage("Please select a rate sheet to delete.");
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void buildRateSheetsList() throws DaoException, Exception {
        IRateSheetsPageService service = new RateSheetsPageServiceImp();
        List<RateSheetsPageVo> rateSheetsPageVos = service.getRateSheetsList(this.buildFilter());
        List<RateSheetsPageModel> rateSheetsPageModels = ModelUtils.createListModelFromVo(rateSheetsPageVos, RateSheetsPageModel.class);
        this.rateSheetsList.setRecords(rateSheetsPageModels);
        this.buildRateSheetsTypeList();
    }

    protected RateSheetsPageFilter buildFilter() throws Exception {
        IRateSheetsPageService service = new RateSheetsPageServiceImp();
        RateSheetsPageFilter filter = new RateSheetsPageFilter();
        if (this.getRateSheetFilter() != null) {
            filter = this.getRateSheetFilter();
        }
        int pageSize = 0;
        try {
            pageSize = Integer.parseInt(this.pageSize);
        } catch (Exception ex) {
            pageSize = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize());
        }

        int page = 0;
        try {
            page = Integer.parseInt(this.page);
        } catch (Exception ex) {
            page = 1;
        }
        long recordCount = 0;
        try {
            recordCount = service.getCountRateSheets(filter);
        } catch (Exception ex) {
            recordCount = 0;
        }

        Paging<RateSheetsPageModel> paging = new Paging<RateSheetsPageModel>(page, 10, pageSize, recordCount);
        this.setRateSheetsList(paging);
        filter.setRecordSize(paging.getPageSize());
        filter.setStartRecord((paging.getCurrentPage() - 1) * paging.getPageSize());
        return filter;
    }

    protected void buildRateSheetsTypeList() {
        Map<String, String> rateSheetsType = new TreeMap<String, String>();
        rateSheetsType.put("0", this.getLocalizationText("Document"));
        rateSheetsType.put("1", this.getLocalizationText("Package"));
        this.setListRateSheetsType(rateSheetsType);
    }

    public Paging<RateSheetsPageModel> getRateSheetsList() {
        return rateSheetsList;
    }

    public void setRateSheetsList(Paging<RateSheetsPageModel> rateSheetsList) {
        this.rateSheetsList = rateSheetsList;
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

    public String getSheetId() {
        return sheetId;
    }

    public void setSheetId(String sheetId) {
        this.sheetId = sheetId;
    }

    public RateSheetsPageFilter getRateSheetFilter() {
        return rateSheetFilter;
    }

    public void setRateSheetFilter(RateSheetsPageFilter rateSheetFilter) {
        this.rateSheetFilter = rateSheetFilter;
    }

    public Map<String, String> getListRateSheetsType() {
        return listRateSheetsType;
    }

    public void setListRateSheetsType(Map<String, String> listRateSheetsType) {
        this.listRateSheetsType = listRateSheetsType;
    }
}
