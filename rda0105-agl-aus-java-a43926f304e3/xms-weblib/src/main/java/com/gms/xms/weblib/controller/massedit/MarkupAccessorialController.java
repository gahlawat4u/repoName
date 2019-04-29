package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from Aug 2, 2016 4:54:48 PM
 * <p>
 * Author huynd
 */
public class MarkupAccessorialController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private String txtCustomerCost;
    private Long count;

    public String show() {
        try {
            this.buildListAirbill();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            if (validateCustomerCost()) {
                List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
                Double customerCost = Double.valueOf(this.getTxtCustomerCost());
                Long count = 0L;
                IMassEditService service = new MassEditServiceImp();
                for (ListAirbillForMassEditModel list : listAirbill) {
                    service.markupAccessorial(this.getAddInfoMap(), customerCost, Long.valueOf(list.getShipmentId()), list.getAirbillNumber());
                    count++;
                }
                this.setCount(count);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean validateCustomerCost() {
        if (StringUtils.isBlank(this.getTxtCustomerCost())) {
            addFieldError("txtCustomerCost", "Please enter customer cost");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public String getTxtCustomerCost() {
        return txtCustomerCost;
    }

    public void setTxtCustomerCost(String txtCustomerCost) {
        this.txtCustomerCost = txtCustomerCost;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
