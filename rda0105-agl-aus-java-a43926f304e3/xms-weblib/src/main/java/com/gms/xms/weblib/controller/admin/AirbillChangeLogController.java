package com.gms.xms.weblib.controller.admin;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.filter.AirbillChangeLogFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.EventLogModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.admin.EventLogDao;
import com.gms.xms.txndb.vo.admin.EventLogVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from Aug 26, 2016 5:24:47 PM
 * <p>
 * Author dattrinh
 */
public class AirbillChangeLogController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String invoiceId;
    private String shipmentId;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private Paging<EventLogModel> events;

    public String show() {
        try {
            // Prepare page size list.
            preparePageSizes();
            // Set default filter and order props.
            this.setPage("1");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            this.setOrderType("0");
            this.setOrderField("action_date");
            // Get records.
            getRecords();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            getRecords();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void getRecords() throws Exception {
        AirbillChangeLogFilter filter = this.buildFilter();
        EventLogDao eventLogDao = new EventLogDao();
        // Get record count.
        long recordCount = eventLogDao.countAirbillChangeLog(filter);
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<EventLogModel> paging = new Paging<EventLogModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get records of this page.
        List<EventLogVo> eventLogVos = eventLogDao.getAirbillChangeLog(filter);
        // Replace @,@ by new line character.
        for (EventLogVo eventLogVo : eventLogVos) {
            eventLogVo.setChangesMode(eventLogVo.getChangesMode().replace("@,@", "<br/>"));
        }
        List<EventLogModel> eventLogModels = ModelUtils.createListModelFromVo(eventLogVos, EventLogModel.class);
        paging.setRecords(eventLogModels);
        this.setEvents(paging);
    }

    protected AirbillChangeLogFilter buildFilter() throws Exception {
        AirbillChangeLogFilter filter = new AirbillChangeLogFilter();
        // Validate filter.
        if (StringUtils.isBlank(this.getInvoiceId()) && StringUtils.isBlank(this.getShipmentId())) {
            throw new CustomException("Please select invoice or shipment for view airbill change log.");
        }
        // Set invoice id.
        Long invoiceId = null;
        try {
            invoiceId = StringUtils.isBlank(this.getInvoiceId()) ? null : Long.valueOf(this.getInvoiceId());
        } catch (Exception e) {
            throw new CustomException("Invalid invoice id. It must be a integer number.");
        }
        filter.setInvoiceId(invoiceId);
        // Set shipment id.
        Long shipmentId = null;
        try {
            shipmentId = StringUtils.isBlank(this.getShipmentId()) ? null : Long.valueOf(this.getShipmentId());
        } catch (Exception e) {
            throw new CustomException("Invalid shipment id. It must be a integer number.");
        }
        filter.setShipmentId(shipmentId);
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

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
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

    public Paging<EventLogModel> getEvents() {
        return events;
    }

    public void setEvents(Paging<EventLogModel> events) {
        this.events = events;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
}
