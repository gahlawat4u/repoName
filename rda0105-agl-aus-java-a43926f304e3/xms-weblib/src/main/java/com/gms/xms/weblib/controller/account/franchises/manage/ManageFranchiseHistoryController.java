package com.gms.xms.weblib.controller.account.franchises.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.eventlog.CustomerEventLogFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.EventLogModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.EventLogServiceImp;
import com.gms.xms.persistence.service.admin.IEventLogService;
import com.gms.xms.txndb.vo.admin.EventLogVo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * File Name: ManageFranchiseHistoryController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 23-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseHistoryController
 */
public class ManageFranchiseHistoryController extends ManageFranchiseAccountSetupController {

    private static final long serialVersionUID = 2818924947617736034L;

    // Filter properties.
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    // Models.
    private Paging<EventLogModel> eventLogs;
    private List<String> pageSizes;

    public String indexHistory() {
        try {
            // Set default sorting properties.
            this.setPage("1");
            this.setOrderType("1");
            this.setOrderField("action_date");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            pageSizes = this.buildPageSizeList();
            prepareHistoryLog();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String indexHistorySearch() {
        try {
            prepareHistoryLog();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void prepareHistoryLog() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        CustomerEventLogFilter filter = this.buidHistoryFilter();
        IEventLogService service = new EventLogServiceImp();
        long countEventlog = service.countByCustCode(filter);
        Paging<EventLogModel> paging = new Paging<EventLogModel>(filter.getPage(), nLinks, filter.getPageSize(), countEventlog);
        List<EventLogVo> eventLogVos = service.selectByCustCode(filter);
        List<EventLogModel> eventLogModels = ModelUtils.createListModelFromVo(eventLogVos, EventLogModel.class);
        for (EventLogModel eventLogModel : eventLogModels) {
            eventLogModel.setChangesMode(eventLogModel.getChangesMode().replace("@,@", "<br/>"));
        }
        paging.setRecords(eventLogModels);
        this.setEventLogs(paging);

    }

    private CustomerEventLogFilter buidHistoryFilter() throws Exception {
        CustomerEventLogFilter filter = new CustomerEventLogFilter();
        filter.setCustomerCode(this.getFranchiseCode());
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
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "action_date" : this.getOrderField());
        return filter;
    }

    public Paging<EventLogModel> getEventLogs() {
        return eventLogs;
    }

    public void setEventLogs(Paging<EventLogModel> eventLogs) {
        this.eventLogs = eventLogs;
    }

    public List<String> getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(List<String> pageSizes) {
        this.pageSizes = pageSizes;
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
}