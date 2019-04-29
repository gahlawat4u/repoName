package com.gms.xms.weblib.controller.account.customers.manage;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.model.account.customers.manage.AddWeightBreakModel;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class AddWeightBreakController extends ManageCustBaseRateController {
    private static final long serialVersionUID = 5755856536266700146L;
    private AddWeightBreakModel weightBreakModel;
    private String htmlString;
    private String weightBreakInputName;

    public String addWeightBreak() {
        return "success";
    }

    public String doAddWeightBreak() {
        try {
            this.validateWeightBreak();
            String htmlString = "<div class=\"form-group base-rate-row\" data-baseRate=\"" + this.getWeightBreakModel().getBaseRateData() + "\" data-weight=\"" + this.getWeightBreakModel().getRequestWeight() + "\">" + this.getWeightBreakModel().getHtmlString() + "</div>";
            Map<String, String> map = new HashMap<>();
            String globalIndex = String.valueOf(Integer.valueOf(this.getWeightBreakModel().getGlobalIndex()) + 1);
            String currentIndex = this.getWeightBreakModel().getCurrentIndex();
            // customer base rate.
            String key = "customerBaseRates[" + currentIndex + "]";
            String value = "customerBaseRates[" + globalIndex + "]";
            map.put(key, value);
            // data-index.
            key = "data-index=\"" + currentIndex + "\"";
            value = "data-index=\"" + globalIndex + "\"";
            map.put(key, value);
            // weight.
            key = "name=\"saveManageCustomer.saveCustBaseRate.customerBaseRates[" + currentIndex + "].weight\" value=\"" + this.getWeightBreakModel().getCurrentWeight() + "\"";
            value = "name=\"saveManageCustomer.saveCustBaseRate.customerBaseRates[" + globalIndex + "].weight\" value=\"" + this.getWeightBreakModel().getRequestWeight() + "\"";
            map.put(key, value);
            // remove display name.
            map.put("<span class=\"br-display-name\">" + this.getWeightBreakModel().getDisplayName() + "</span>", "");
            // weight break.
            key = "<span data-group=\"br-weight\">" + this.getWeightBreakModel().getCurrentWeight() + "+</span>";
            value = "<span data-group=\"br-weight\">" + this.getWeightBreakModel().getRequestWeight() + "+</span>";
            map.put(key, value);
            // remove button.
            key = "data-group=\"br-remove\"";
            value = "data-group=\"br-remove-break\"";
            map.put(key, value);
            htmlString = AppUtils.replaceStringByMap(map, htmlString);
            this.setHtmlString(htmlString);
            String weightBreakInputName = "saveManageCustomer.saveCustBaseRate.customerBaseRates[" + globalIndex + "].weight";
            this.setWeightBreakInputName(weightBreakInputName);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public String doAddWeightBreakFranchise() {
        try {
            this.validateWeightBreak();
            String htmlString = "<div class=\"form-group base-rate-row\" data-baseRate=\"" + this.getWeightBreakModel().getBaseRateData() + "\" data-weight=\"" + this.getWeightBreakModel().getRequestWeight() + "\">" + this.getWeightBreakModel().getHtmlString() + "</div>";
            Map<String, String> map = new HashMap<>();
            String globalIndex = String.valueOf(Integer.valueOf(this.getWeightBreakModel().getGlobalIndex()) + 1);
            String currentIndex = this.getWeightBreakModel().getCurrentIndex();
            // customer base rate.
            String key = "customerBaseRates[" + currentIndex + "]";
            String value = "customerBaseRates[" + globalIndex + "]";
            map.put(key, value);
            // data-index.
            key = "data-index=\"" + currentIndex + "\"";
            value = "data-index=\"" + globalIndex + "\"";
            map.put(key, value);
            // weight.
            key = "name=\"saveManageFranchiseModel.saveFranBaseRate.customerBaseRates[" + currentIndex + "].weight\" value=\"" + this.getWeightBreakModel().getCurrentWeight() + "\"";
            value = "name=\"saveManageFranchiseModel.saveFranBaseRate.customerBaseRates[" + globalIndex + "].weight\" value=\"" + this.getWeightBreakModel().getRequestWeight() + "\"";
            map.put(key, value);
            // remove display name.
            map.put("<span class=\"br-display-name\">" + this.getWeightBreakModel().getDisplayName() + "</span>", "");
            // weight break.
            key = "<span data-group=\"br-weight\">" + this.getWeightBreakModel().getCurrentWeight() + "+</span>";
            value = "<span data-group=\"br-weight\">" + this.getWeightBreakModel().getRequestWeight() + "+</span>";
            map.put(key, value);
            // remove button.
            key = "data-group=\"br-remove\"";
            value = "data-group=\"br-remove-break\"";
            map.put(key, value);
            htmlString = AppUtils.replaceStringByMap(map, htmlString);
            this.setHtmlString(htmlString);
            String weightBreakInputName = "saveManageFranchiseModel.saveFranBaseRate.customerBaseRates[" + globalIndex + "].weight";
            this.setWeightBreakInputName(weightBreakInputName);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public String doAddWeightBreakProfile() {
        try {
            this.validateWeightBreak();
            String htmlString = "<div class=\"form-group base-rate-row\" data-baseRate=\"" + this.getWeightBreakModel().getBaseRateData() + "\" data-weight=\"" + this.getWeightBreakModel().getRequestWeight() + "\">" + this.getWeightBreakModel().getHtmlString() + "</div>";
            Map<String, String> map = new HashMap<>();
            String globalIndex = String.valueOf(Integer.valueOf(this.getWeightBreakModel().getGlobalIndex()) + 1);
            String currentIndex = this.getWeightBreakModel().getCurrentIndex();
            // customer base rate.
            String key = "customerProfileBaseRates[" + currentIndex + "]";
            String value = "customerProfileBaseRates[" + globalIndex + "]";
            map.put(key, value);
            // data-index.
            key = "data-index=\"" + currentIndex + "\"";
            value = "data-index=\"" + globalIndex + "\"";
            map.put(key, value);
            // weight.
            key = "name=\"saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[" + currentIndex + "].weight\" value=\"" + this.getWeightBreakModel().getCurrentWeight() + "\"";
            value = "name=\"saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[" + globalIndex + "].weight\" value=\"" + this.getWeightBreakModel().getRequestWeight() + "\"";
            map.put(key, value);
            // remove display name.
            map.put("<span class=\"br-display-name\">" + this.getWeightBreakModel().getDisplayName() + "</span>", "");
            // weight break.
            key = "<span data-group=\"br-weight\">" + this.getWeightBreakModel().getCurrentWeight() + "+</span>";
            value = "<span data-group=\"br-weight\">" + this.getWeightBreakModel().getRequestWeight() + "+</span>";
            map.put(key, value);
            // remove button.
            key = "data-group=\"br-remove\"";
            value = "data-group=\"br-remove-break\"";
            map.put(key, value);
            htmlString = AppUtils.replaceStringByMap(map, htmlString);
            this.setHtmlString(htmlString);
            String weightBreakInputName = "saveCustomerProfile.saveCustProfileBaseRate.customerProfileBaseRates[" + globalIndex + "].weight";
            this.setWeightBreakInputName(weightBreakInputName);
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    protected void validateWeightBreak() throws CustomException {
        if (this.getWeightBreakModel() != null) {
            if (StringUtils.isBlank(this.getWeightBreakModel().getRequestWeight())) {
                throw new CustomException("Weight cannot be empty.");
            } else {
                Double currentWeight = 0D;
                Double nextWeight = 0D;
                Double requestWeight = 0D;
                if (!StringUtils.isBlank(this.getWeightBreakModel().getCurrentWeight()) && !StringUtils.isBlank(this.getWeightBreakModel().getNextWeight())) {
                    currentWeight = Double.parseDouble(this.getWeightBreakModel().getCurrentWeight());
                    nextWeight = Double.parseDouble(this.getWeightBreakModel().getNextWeight());
                    requestWeight = Double.parseDouble(this.getWeightBreakModel().getRequestWeight());
                    if (requestWeight <= currentWeight || requestWeight >= nextWeight) {
                        throw new CustomException("Request weight must be larger than " + this.getWeightBreakModel().getCurrentWeight() + " and less than " + this.getWeightBreakModel().getNextWeight());
                    }
                } else if (!StringUtils.isBlank(this.getWeightBreakModel().getCurrentWeight()) && StringUtils.isBlank(this.getWeightBreakModel().getNextWeight())) {
                    currentWeight = Double.parseDouble(this.getWeightBreakModel().getCurrentWeight());
                    requestWeight = Double.parseDouble(this.getWeightBreakModel().getRequestWeight());
                    if (requestWeight <= currentWeight) {
                        throw new CustomException("Request weight must be larger than " + this.getWeightBreakModel().getCurrentWeight());
                    }
                }
            }
        }
    }

    public AddWeightBreakModel getWeightBreakModel() {
        return weightBreakModel;
    }

    public void setWeightBreakModel(AddWeightBreakModel weightBreakModel) {
        this.weightBreakModel = weightBreakModel;
    }

    public String getHtmlString() {
        return htmlString;
    }

    public void setHtmlString(String htmlString) {
        this.htmlString = htmlString;
    }

    public String getWeightBreakInputName() {
        return weightBreakInputName;
    }

    public void setWeightBreakInputName(String weightBreakInputName) {
        this.weightBreakInputName = weightBreakInputName;
    }
}
