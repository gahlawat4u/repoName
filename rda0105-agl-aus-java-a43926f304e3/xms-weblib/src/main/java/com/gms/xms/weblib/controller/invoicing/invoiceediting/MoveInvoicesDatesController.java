package com.gms.xms.weblib.controller.invoicing.invoiceediting;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.filter.invoicing.MoveUnfrozenInvoicesFilter;
import com.gms.xms.persistence.service.invoicing.IMoveUnfrozenInvoicesService;
import com.gms.xms.persistence.service.invoicing.MoveUnfrozenInvoicesServiceImp;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Posted from MoveInvoicesDatesController
 * <p>
 * Author dattrinh Mar 10, 2016 3:43:39 PM
 */
public class MoveInvoicesDatesController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1L;

    // Filter property.
    private String fromDate;
    private String toDate;
    private String exclude;
    private String customerList;
    private Long count;

    // Model.
    private List<String> dateList;

    public String show() {
        try {
            this.prepareUnfrozenDateList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String getInvoiceDates() {
        try {
            this.prepareUnfrozenDateList();
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String move() {
        try {
            // Get filter.
            MoveUnfrozenInvoicesFilter filter = this.buildFilter();
            // Do move.
            IMoveUnfrozenInvoicesService service = new MoveUnfrozenInvoicesServiceImp();
            long count = service.moveInvoices(this.getAddInfoMap(), filter);
            this.setCount(count);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareUnfrozenDateList() throws Exception {
        IMoveUnfrozenInvoicesService service = new MoveUnfrozenInvoicesServiceImp();
        List<Date> unfrozenInvoiceDates = service.getUnfrozenInvoiceDates(this.buildFranchiseCodeList("All"));
        List<String> dateList = new ArrayList<String>();
        for (Date date : unfrozenInvoiceDates) {
            dateList.add(DateUtils.convertDateToString(date, AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
        }
        this.setDateList(dateList);
    }

    protected MoveUnfrozenInvoicesFilter buildFilter() throws Exception {
        MoveUnfrozenInvoicesFilter filter = new MoveUnfrozenInvoicesFilter();
        // Set franchise list.
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        // Set from date.
        Date fromDate = null;
        try {
            fromDate = DateUtils.convertStringToDate(this.getFromDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (fromDate == null) {
                throw new CustomException("Please choose From date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid From date.");
        }
        filter.setFromDate(fromDate);
        // Set to date.
        Date toDate = null;
        try {
            toDate = DateUtils.convertStringToDate(this.getToDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
            if (toDate == null) {
                throw new CustomException("Please choose To date.");
            }
        } catch (Exception e) {
            throw new CustomException("Invalid To date.");
        }
        filter.setToDate(toDate);
        if (fromDate.compareTo(toDate) == 0) {
            throw new CustomException("Please choose other new invoice date. Cannot move to the same date.");
        }
        // Set exclude.
        Boolean exclude = StringUtils.isBlank(this.getExclude()) ? false : true;
        filter.setExclude(exclude);
        // Set exclude customer list.
        if (exclude) {
            if (StringUtils.isBlank(this.getCustomerList())) {
                throw new CustomException("Please input exclude customers.");
            }
            filter.setCustomerList(this.getCustomerList());
        }
        return filter;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public String getExclude() {
        return exclude;
    }

    public void setExclude(String exclude) {
        this.exclude = exclude;
    }

    public String getCustomerList() {
        return customerList;
    }

    public void setCustomerList(String customerList) {
        this.customerList = customerList;
    }

    public List<String> getDateList() {
        return dateList;
    }

    public void setDateList(List<String> dateList) {
        this.dateList = dateList;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}