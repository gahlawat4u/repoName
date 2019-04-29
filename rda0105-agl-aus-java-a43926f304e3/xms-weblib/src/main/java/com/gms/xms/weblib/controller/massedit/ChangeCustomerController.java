package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;

import java.util.List;

/**
 * Posted from May 13, 2016 9:59:36 AM
 * <p>
 * Author huynd
 */
public class ChangeCustomerController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private String customerCode;
    private Long count;

    public String show() {
        try {
            this.buildListAirbill();
            this.buildCustomersList(this.buildCustomerFilter());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String save() {
        try {
            List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
            Long count = 0L;
            for (ListAirbillForMassEditModel list : listAirbill) {
                Long shipmentId = Long.valueOf(list.getShipmentId());
                String airbillNumber = list.getAirbillNumber();
                if (isHasInvoice(shipmentId, airbillNumber)) {

                }
                count++;
            }
            this.setCount(count);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean isHasInvoice(Long shipmentId, String airbillNumber) {
        // TODO Auto-generated method stub
        return false;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }
}
