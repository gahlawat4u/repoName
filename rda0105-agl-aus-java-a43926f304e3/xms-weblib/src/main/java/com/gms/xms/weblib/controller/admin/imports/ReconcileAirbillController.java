package com.gms.xms.weblib.controller.admin.imports;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.admin.imports.reconcileairbill.ReconcileAirbillFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.imports.ReconcileAirbillModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.imports.ReconcileAirbillDao;
import com.gms.xms.txndb.vo.admin.imports.ReconcileAirbillVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.render.reconcileairbill.IReconcileAirbillRender;
import com.gms.xms.workflow.render.reconcileairbill.ReconcileAirbillRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Posted from ReconcileAirbillController
 * <p>
 * Author dattrinh Mar 15, 2016 9:01:54 AM
 */
public class ReconcileAirbillController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    // Filter properties.
    private String airillList;
    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;

    // Models.
    private Paging<ReconcileAirbillModel> report;
    private ReconcileAirbillModel summary;
    private String missingCount;
    private String foundCount;

    private InputStream stream;
    private String fileName;

    public String show() {
        try {
            this.preparePageSizes();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            // Get filter.
            ReconcileAirbillFilter filter = this.buildFilter();
            // Get the setting number links on the page.
            Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
            // Get record count.
            ReconcileAirbillDao dao = new ReconcileAirbillDao();
            long recordCount = dao.countReconcileAirbillByFilter(filter);
            // Build paging object.
            Paging<ReconcileAirbillModel> paging = new Paging<ReconcileAirbillModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
            filter.setPage(paging.getCurrentPage());
            // Get list of records of reconcile airbill.
            List<ReconcileAirbillVo> reconcileAirbillVos = dao.getReconcileAirbillByFilter(filter);
            List<ReconcileAirbillModel> reconcileAirbillModels = ModelUtils.createListModelFromVo(reconcileAirbillVos, ReconcileAirbillModel.class);
            paging.setRecords(reconcileAirbillModels);
            // Get summary record.
            // if (reconcileAirbillVos != null && reconcileAirbillVos.size() >
            // 0) {
            // ReconcileAirbillVo summaryVo =
            // dao.sumReconcileAirbillByFilter(filter);
            // ReconcileAirbillModel summaryModel =
            // ModelUtils.createModelFromVo(summaryVo,
            // ReconcileAirbillModel.class);
            // this.setSummary(summaryModel);
            // }
            // Set reconcile airbill report.
            this.setReport(paging);
            // Get searching airbill list.
            long total = filter.getAirbillList().length() - filter.getAirbillList().replace(",", "").length() + 1;
            long found = recordCount;
            long missing = total - found;
            this.setFoundCount(String.valueOf(found));
            this.setMissingCount(String.valueOf(missing));
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doExport() {
        try {
            ReconcileAirbillFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            ReconcileAirbillDao dao = new ReconcileAirbillDao();
            List<ReconcileAirbillVo> reconcileAirbillVos = dao.getReconcileAirbillByFilter(filter);
            List<ReconcileAirbillModel> reconcileAirbillModels = ModelUtils.createListModelFromVo(reconcileAirbillVos, ReconcileAirbillModel.class);

            String outPutFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/reconcile_airbill_" + AppUtils.createMessageReference() + ".xls";
            IReconcileAirbillRender render = new ReconcileAirbillRenderImp(this.getAddInfoMap());
            render.genXLSFile(reconcileAirbillModels, outPutFilePath);
            this.setFileName("reconcile_airbill.xls");
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected ReconcileAirbillFilter buildFilter() throws Exception {
        ReconcileAirbillFilter filter = new ReconcileAirbillFilter();
        // Set franchise list.
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        // Set user level.
        this.determineAdminLevel();
        filter.setUserLevel(this.getAdminLevel());
        // Set airbill list.
        if (StringUtils.isBlank(this.getAirillList())) {
            throw new CustomException("Please enter or copy paste airbill number.");
        }
        String airbillList = "";
        String[] airbillArr = this.getAirillList().split("\n");
        if (airbillArr == null || airbillArr.length == 0) {
            airbillList = this.getAirillList().trim();
        } else {
            for (String airbill : airbillArr) {
                airbillList += "'" + airbill.trim() + "',";
            }
        }
        if (StringUtils.isBlank(airbillList)) {
            throw new CustomException("Please enter or copy paste airbill number.");
        } else {
            airbillList = airbillList.substring(0, airbillList.length() - 1);
        }
        filter.setAirbillList(airbillList);
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "invoice_code" : this.getOrderField());
        return filter;
    }

    public String getAirillList() {
        return airillList;
    }

    public void setAirillList(String airillList) {
        this.airillList = airillList;
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

    public Paging<ReconcileAirbillModel> getReport() {
        return report;
    }

    public void setReport(Paging<ReconcileAirbillModel> report) {
        this.report = report;
    }

    public ReconcileAirbillModel getSummary() {
        return summary;
    }

    public void setSummary(ReconcileAirbillModel summary) {
        this.summary = summary;
    }

    public String getMissingCount() {
        return missingCount;
    }

    public void setMissingCount(String missingCount) {
        this.missingCount = missingCount;
    }

    public String getFoundCount() {
        return foundCount;
    }

    public void setFoundCount(String foundCount) {
        this.foundCount = foundCount;
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
}
