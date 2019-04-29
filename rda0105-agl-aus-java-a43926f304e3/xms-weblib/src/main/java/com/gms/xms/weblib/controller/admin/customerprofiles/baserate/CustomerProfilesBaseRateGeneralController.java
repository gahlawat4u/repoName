package com.gms.xms.weblib.controller.admin.customerprofiles.baserate;

import com.gms.xms.weblib.controller.admin.customerprofiles.CustomerProfilesAccountSetupController;

/**
 * Posted from CustomerProfilesBaseRateGeneralController
 * <p>
 * Author TANDT 28-10-2015
 */
public class CustomerProfilesBaseRateGeneralController extends CustomerProfilesAccountSetupController {
    private static final long serialVersionUID = -7110570240797000983L;

    public String indexBaseRateGeneral() {
        try {
            if (this.preapareCustomerDetail()) {
                return "success";
            } else {
                return "error";
            }
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

}