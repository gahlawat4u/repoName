package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.eventlog.CustomerEventLogFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.EventLogModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.service.admin.EventLogServiceImp;
import com.gms.xms.persistence.service.admin.IEventLogService;
import com.gms.xms.txndb.vo.admin.EventLogVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from ManageCustomerHistoryController
 * <p>
 * Author DatTV Oct 5, 2015
 */
public class ManageCustomerHistoryController extends JsonBaseController {

    private static final long serialVersionUID = -7242057538335952856L;

    // Filter properties.
    private String customerCode;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;

    // Models.
    private Paging<EventLogModel> eventLogs;
    private List<String> pageSizes;

    public String show() {
        try {
            // Set default sorting properties.
            this.setPage("1");
            this.setOrderType("1");
            this.setOrderField("action_date");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            this.setPageSizes(this.buildPageSizeList());
            loadEventLogs();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            loadEventLogs();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private void loadEventLogs() throws Exception {
        int nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        IEventLogService eventLogService = new EventLogServiceImp();
        CustomerEventLogFilter filter = this.buildFilter();
        long recordCount = eventLogService.countByCustCode(filter);
        // Set paging info
        Paging<EventLogModel> paging = new Paging<EventLogModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        filter.setPageSize(paging.getPageSize());
        // Get data of page
        List<EventLogVo> eventLogVos = eventLogService.selectByCustCode(filter);
        List<EventLogModel> eventLogModels = ModelUtils.createListModelFromVo(eventLogVos, EventLogModel.class);
        // Convert @,@ from change mode column to <br/>
        for (EventLogModel eventLogModel : eventLogModels) {
            eventLogModel.setChangesMode(eventLogModel.getChangesMode().replace("@,@", "<br/>"));
        }
        paging.setRecords(eventLogModels);
        this.setEventLogs(paging);
    }

    private CustomerEventLogFilter buildFilter() throws Exception {
        CustomerEventLogFilter filter = new CustomerEventLogFilter();
        filter.setCustomerCode(customerCode);
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

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Paging<EventLogModel> getEventLogs() {
        return eventLogs;
    }

    public void setEventLogs(Paging<EventLogModel> eventLogs) {
        this.eventLogs = eventLogs;
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
