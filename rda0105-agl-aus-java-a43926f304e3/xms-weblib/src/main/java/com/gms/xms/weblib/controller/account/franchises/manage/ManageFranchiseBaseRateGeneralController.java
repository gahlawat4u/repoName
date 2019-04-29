package com.gms.xms.weblib.controller.account.franchises.manage;

/**
 * File Name: ManageFranchiseBaseRateGeneralController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 24-11-2015 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.account.franchises.manage <br/>
 * Class: ManageFranchiseBaseRateGeneralController
 */
public class ManageFranchiseBaseRateGeneralController extends ManageFranchiseAccountSetupController {

    private static final long serialVersionUID = 6535907576828917275L;

    public String indexBaseRateGeneral() {
        try {
            if (this.prepareFranchiseDetail()) {
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