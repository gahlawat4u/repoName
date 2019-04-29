package com.gms.xms.filter.admin.receivables.reminderletter;

/**
 * Posted from Mar 31, 2016 2:38:35 PM
 * <p>
 * Author dattrinh
 */
public class ReminderLetterFilter {
    private String franchiseList;

    public String getFranchiseList() {
        return franchiseList;
    }

    public void setFranchiseList(String franchiseList) {
        this.franchiseList = franchiseList;
    }

    @Override
    public String toString() {
        return "ReminderLetterFilter [franchiseList=" + franchiseList + "]";
    }
}
