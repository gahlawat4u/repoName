package com.gms.xms.weblib.controller.admin;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.admin.AirbillLabelFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.admin.AirbillLabelModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.ScheduleCollectionDao;
import com.gms.xms.persistence.dao.ShipmentDao;
import com.gms.xms.persistence.dao.admin.AirbillLabelDao;
import com.gms.xms.txndb.vo.ScheduleCollectionVo;
import com.gms.xms.txndb.vo.ShipmentVo;
import com.gms.xms.txndb.vo.admin.AirbillLabelVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Posted from Aug 27, 2016 9:08:19 PM
 * <p>
 * Author dattrinh
 */
public class AirbillLabelController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    private String periodType;
    private String period;
    private String page;
    private String pageSize;
    private String orderField;
    private String orderType;
    private Paging<AirbillLabelModel> airbillLabels;
    private String shipmentId;
    private String scheduleCollectionId;
    private String confirmationNo;

    public String show() {
        try {
            // Prepare page size list.
            preparePageSizes();
            // Set default filter and order props.
            this.setPage("1");
            this.setPageSize(AppConstants.APP_SETTINGS.getDefaultPageSize());
            this.setOrderType("0");
            this.setOrderField("customer_code");
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

    public void updateConfirmationNo() {
        try {
            // Valid shipment id.
            if (StringUtils.isBlank(this.getShipmentId())) {
                throw new CustomException("No shipment id.");
            }
            Long shipmentId = null;
            try {
                shipmentId = Long.valueOf(this.getShipmentId());
            } catch (Exception e) {
                throw new CustomException("Invalid shipment id. It must be integer number.");
            }
            // Valid schedule collection id.
            if (StringUtils.isBlank(this.getScheduleCollectionId())) {
                throw new CustomException("No schedule collection id.");
            }
            Long scheduleCollectionId = null;
            try {
                scheduleCollectionId = Long.valueOf(this.getScheduleCollectionId());
            } catch (Exception e) {
                throw new CustomException("Invalid schedule collection id. It must be integer number.");
            }
            // Update confirmation no.
            if (scheduleCollectionId == 0) {
                // Update dhl note of shipment if schedule collection id is 0.
                ShipmentDao shipmentDao = new ShipmentDao();
                ShipmentVo shipmentVo = new ShipmentVo();
                shipmentVo.setShipmentId(shipmentId);
                shipmentVo.setDhlNote(this.getConfirmationNo());
                shipmentDao.update(this.getAddInfoMap(), shipmentVo);
            } else {
                ScheduleCollectionDao scheduleCollectionDao = new ScheduleCollectionDao();
                ScheduleCollectionVo scheduleCollectionVo = new ScheduleCollectionVo();
                scheduleCollectionVo.setScheduleCollectionId(scheduleCollectionId);
                scheduleCollectionVo.setConfirmationNo(this.getConfirmationNo());
                scheduleCollectionDao.updateScheduleCollectionById(this.getAddInfoMap(), scheduleCollectionVo);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    protected void getRecords() throws Exception {
        AirbillLabelFilter filter = this.buildFilter();
        AirbillLabelDao airbillLabelDao = new AirbillLabelDao();
        // Get record count.
        long recordCount = airbillLabelDao.countAirbillLabels(filter);
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        Paging<AirbillLabelModel> paging = new Paging<AirbillLabelModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get records of this page.
        List<AirbillLabelVo> airbillLabelVos = airbillLabelDao.getAirbillLabels(filter);
        List<AirbillLabelModel> airbillLabelModels = ModelUtils.createListModelFromVo(airbillLabelVos, AirbillLabelModel.class);
        paging.setRecords(airbillLabelModels);
        this.setAirbillLabels(paging);
    }

    protected AirbillLabelFilter buildFilter() throws Exception {
        AirbillLabelFilter filter = new AirbillLabelFilter();
        // Set period type.
        if (StringUtils.isBlank(this.getPeriodType())) {
            throw new CustomException("Please choose a period type.");
        }
        Integer periodType = null;
        try {
            periodType = Integer.valueOf(this.getPeriodType());
            if (periodType < 1 || periodType > 3) {
                throw new CustomException("Invalid period type.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid period type.");
        }
        filter.setPeriodType(periodType);
        // Detect start date and end date.
        if (StringUtils.isBlank(this.getPeriod())) {
            throw new CustomException("Please choose a period.");
        }
        String searchDate = null;
        Calendar cal = Calendar.getInstance();
        Date today = cal.getTime();
        int curYear = cal.get(Calendar.YEAR);
        int curMonth = cal.get(Calendar.MONTH) + 1;
        int month = 0;
        String[] arrDate;
        switch (filter.getPeriodType()) {
            case 1: // Today.
                searchDate = DateUtils.convertDateToString(today, "dd/MM/yyyy", null);
                searchDate = searchDate + " " + this.getPeriod() + ":00:00";
                break;
            case 2: // Week.
                arrDate = this.getPeriod().split("/");
                month = Integer.valueOf(arrDate[1]);
                if (month > curMonth) {
                    searchDate = String.valueOf(curYear - 1);
                } else {
                    searchDate = String.valueOf(curYear);
                }
                searchDate = this.getPeriod() + "/" + searchDate;
                break;
            case 3: // Month.
                switch (this.getPeriod()) {
                    case "Jan":
                        month = 1;
                        break;
                    case "Feb":
                        month = 2;
                        break;
                    case "Mar":
                        month = 3;
                        break;
                    case "Apr":
                        month = 4;
                        break;
                    case "May":
                        month = 5;
                        break;
                    case "Jun":
                        month = 6;
                        break;
                    case "Jul":
                        month = 7;
                        break;
                    case "Aug":
                        month = 8;
                        break;
                    case "Sep":
                        month = 9;
                        break;
                    case "Oct":
                        month = 10;
                        break;
                    case "Nov":
                        month = 11;
                        break;
                    case "Dec":
                        month = 12;
                        break;
                }
                if (month > curMonth) {
                    searchDate = String.valueOf(curYear - 1);
                } else {
                    searchDate = String.valueOf(curYear);
                }
                searchDate = "01/" + String.valueOf(month) + "/" + searchDate;
                break;
        }
        filter.setSearchDate(searchDate);
        // Set franchise list.
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
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

    public String getPeriodType() {
        return periodType;
    }

    public void setPeriodType(String periodType) {
        this.periodType = periodType;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public Paging<AirbillLabelModel> getAirbillLabels() {
        return airbillLabels;
    }

    public void setAirbillLabels(Paging<AirbillLabelModel> airbillLabels) {
        this.airbillLabels = airbillLabels;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getScheduleCollectionId() {
        return scheduleCollectionId;
    }

    public void setScheduleCollectionId(String scheduleCollectionId) {
        this.scheduleCollectionId = scheduleCollectionId;
    }

    public String getConfirmationNo() {
        return confirmationNo;
    }

    public void setConfirmationNo(String confirmationNo) {
        this.confirmationNo = confirmationNo;
    }
}
