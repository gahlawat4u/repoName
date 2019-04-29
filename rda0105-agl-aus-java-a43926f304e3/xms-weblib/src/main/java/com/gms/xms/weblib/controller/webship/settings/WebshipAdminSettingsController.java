package com.gms.xms.weblib.controller.webship.settings;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.CustomerDefaultSettingModel;
import com.gms.xms.persistence.dao.CustomerDefaultSettingDao;
import com.gms.xms.persistence.service.webship.settings.IUserSettingsService;
import com.gms.xms.persistence.service.webship.settings.UserSettingsServiceImp;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.weblib.controller.JsonBaseController;

/**
 * Posted from Jun 27, 2016 9:07:53 AM
 * <p>
 * Author dattrinh
 */
public class WebshipAdminSettingsController extends JsonBaseController {
    private static final long serialVersionUID = -1L;
    private CustomerDefaultSettingModel adminSettings;

    public String show() {
        try {
            loadAdminSettings();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void save() {
        try {
            // Valid admin settings.
            if (this.getAdminSettings() == null) {
                throw new CustomException("No admin settings for saving...");
            }
            // Convert model to vo for saving.
            CustomerDefaultSettingVo defaultSettingVo = ModelUtils.createVoFromModel(this.getAdminSettings(), CustomerDefaultSettingVo.class);
            saveAdminSettings(defaultSettingVo);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    public void clear() {
        try {
            // Check login
            WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
            if (webshipLoginVo == null) {
                throw new CustomException("You don't have permission to do this action.");
            }
            CustomerDefaultSettingVo customerDefaultSettingVo = new CustomerDefaultSettingVo();
            customerDefaultSettingVo.setDisableQuote(false);
            customerDefaultSettingVo.setGlobalAddressBook(false);
            customerDefaultSettingVo.setIndividualUserHistory(false);
            customerDefaultSettingVo.setCustomerCode(webshipLoginVo.getCustomerCode());
            saveAdminSettings(customerDefaultSettingVo);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    protected void saveAdminSettings(CustomerDefaultSettingVo defaultSettingVo) throws Exception {
        CustomerDefaultSettingDao dao = new CustomerDefaultSettingDao();
        dao.update(this.getAddInfoMap(), defaultSettingVo);
    }

    protected void loadAdminSettings() throws Exception {
        // Check login
        WebshipLoginVo webshipLoginVo = this.getWebshipLoginInfo();
        if (webshipLoginVo == null) {
            throw new CustomException("You don't have permission to do this action.");
        }

        // Load default settings
        IUserSettingsService settingsService = new UserSettingsServiceImp();
        CustomerDefaultSettingVo customerDefaultSettingVo = settingsService.selectByCustomerCode(webshipLoginVo.getCustomerCode());
        if (customerDefaultSettingVo == null) {
            customerDefaultSettingVo = new CustomerDefaultSettingVo();
            customerDefaultSettingVo.setDisableQuote(false);
            customerDefaultSettingVo.setGlobalAddressBook(false);
            customerDefaultSettingVo.setIndividualUserHistory(false);
            customerDefaultSettingVo.setCustomerCode(webshipLoginVo.getCustomerCode());
        }
        CustomerDefaultSettingModel defaultSettingModel = ModelUtils.createModelFromVo(customerDefaultSettingVo, CustomerDefaultSettingModel.class);
        this.setAdminSettings(defaultSettingModel);
    }

    public CustomerDefaultSettingModel getAdminSettings() {
        return adminSettings;
    }

    public void setAdminSettings(CustomerDefaultSettingModel adminSettings) {
        this.adminSettings = adminSettings;
    }
}