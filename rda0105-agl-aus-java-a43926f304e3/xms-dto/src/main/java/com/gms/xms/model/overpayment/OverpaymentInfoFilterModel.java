package com.gms.xms.model.overpayment;

import java.util.List;

/**
 * Posted from OverpaymentInfoFilterModel
 * <p>
 * Author DatTV Date Apr 23, 2015 5:19:17 PM
 */
public class OverpaymentInfoFilterModel extends OverpaymentInfoModel {

    private static final long serialVersionUID = 1L;
    private List<String> franchiseList;
    private String page;
    private String pageSize;
    private String orderBy;

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public List<String> getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(List<String> franchiseList) {
        this.franchiseList = franchiseList;
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
}