package com.gms.xms.workflow.client;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.DimensionModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.DimensionDao;
import com.gms.xms.txndb.vo.WebshipChangePassVo;
import com.gms.xms.txndb.vo.webship.CustomerDefaultSettingVo;
import com.gms.xms.txndb.vo.webship.WebshipVo;
import com.gms.xms.workflow.client.integration.request.ChangePasswordRequest;
import com.gms.xms.workflow.client.integration.request.CustomerDefaultSettingRequest;
import com.gms.xms.workflow.client.integration.response.ChangePasswordResponse;
import com.gms.xms.workflow.client.integration.response.CustomerDefaultSettingResponse;
import com.gms.xms.workflow.core.WorkFlowManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;

/**
 * Posted from WebshipSettingClient
 * <p>
 * Author DatTV Date Mar 27, 2015
 */
public class WebshipSettingClient extends WorkflowBaseClient {
    public WebshipSettingClient(Map<String, String> addInfo) {
        super(addInfo);
    }

    protected static final Log log = LogFactory.getLog(WebshipSettingClient.class);

    /**
     * Gets list of collection types
     *
     * @return The context
     * @throws Exception
     */
    public ContextBase getCollectionTypeList() throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetCollectionTypeList");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    /**
     * Gets list of trade types
     *
     * @return The context
     * @throws Exception
     */
    public ContextBase getTradeTypeList() throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetTradeTypeList");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    /**
     * Gets list of package types by service id
     *
     * @param serviceId
     * @return The context
     * @throws Exception
     */
    public ContextBase getPackageListByServiceId(int serviceId) throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.SERVICE_ID, String.valueOf(serviceId));
        context.put(Attributes.WFP_NAME, "Wfl-GetPackageListByServiceId");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    /**
     * Gets list of billing types
     *
     * @return The context
     * @throws Exception
     */
    public ContextBase getBillingTypeList() throws Exception {
        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WFP_NAME, "Wfl-GetBillingTypeList");
        context = WorkFlowManager.getInstance().process(context);
        return context;
    }

    /**
     * Inserts new customer default setting
     *
     * @param defaultSetting
     * @throws Exception
     */
    public CustomerDefaultSettingResponse insertCustomerDefaultSetting(CustomerDefaultSettingRequest customerDefaultSettingRequest) throws Exception {
        CustomerDefaultSettingVo customerDefaultSettingVo = customerDefaultSettingRequest.getCustomerDefaultSettingVo();
        CustomerDefaultSettingResponse customerDefaultSettingResponse = new CustomerDefaultSettingResponse();

        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CUSTOMER_DEFAULT_SETTING_OBJECT, GsonUtils.toGson(customerDefaultSettingVo));
        context.put(Attributes.WFP_NAME, "Wfl-InsertCustomerDefaultSetting");
        context = WorkFlowManager.getInstance().process(context);

        customerDefaultSettingResponse.setErrorCode(ErrorCode.SUCCESS);
        if (context.get(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.ERROR)) {
            customerDefaultSettingResponse.setErrorCode(context.get(Attributes.ERROR_CODE));
            customerDefaultSettingResponse.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        }

        return customerDefaultSettingResponse;
    }

    /**
     * Updates customer default setting
     *
     * @param defaultSetting
     * @throws Exception
     */
    public CustomerDefaultSettingResponse updateCustomerDefaultSetting(CustomerDefaultSettingRequest customerDefaultSettingRequest) throws Exception {
        CustomerDefaultSettingVo customerDefaultSettingVo = customerDefaultSettingRequest.getCustomerDefaultSettingVo();
        CustomerDefaultSettingResponse customerDefaultSettingResponse = new CustomerDefaultSettingResponse();

        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.CUSTOMER_DEFAULT_SETTING_OBJECT, GsonUtils.toGson(customerDefaultSettingVo));
        context.put(Attributes.WFP_NAME, "Wfl-UpdateCustomerDefaultSetting");
        context = WorkFlowManager.getInstance().process(context);

        customerDefaultSettingResponse.setErrorCode(ErrorCode.SUCCESS);
        if (context.get(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.ERROR)) {
            customerDefaultSettingResponse.setErrorCode(context.get(Attributes.ERROR_CODE));
            customerDefaultSettingResponse.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));
        }

        return customerDefaultSettingResponse;
    }

    /**
     * Gets customer address book by company or contact name
     *
     * @param customerCode
     * @param companyName
     * @param contactName
     * @return The context
     * @throws Exception
     */

    public ChangePasswordResponse getWebshipById(ChangePasswordRequest changePasswordRequest) throws Exception {
        long webshipId = changePasswordRequest.getWebshipId();
        ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();

        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WEBSHIP_ID, String.valueOf(webshipId));
        context.put(Attributes.WFP_NAME, "Wfl-GetWebshipById");
        context = WorkFlowManager.getInstance().process(context);

        WebshipVo webshipVo = GsonUtils.fromGson(context.get(Attributes.WEBSHIP_RESULT), WebshipVo.class);
        changePasswordResponse.setWebshipVo(webshipVo);

        return changePasswordResponse;
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest changePasswordRequest) throws Exception {
        ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
        WebshipChangePassVo webshipChangePassVo = new WebshipChangePassVo();
        webshipChangePassVo.setWebshipId(changePasswordRequest.getWebshipId());
        webshipChangePassVo.setNewPassword(changePasswordRequest.getEncryptPass());

        ContextBase context = new ContextBase(this.getAddInfo());
        context.put(Attributes.WEBSHIP_CHANGE_PASSWORD_OBJECT, GsonUtils.toGson(webshipChangePassVo));
        context.put(Attributes.WFP_NAME, "Wfl-ChangeWebshipPass");
        context = WorkFlowManager.getInstance().process(context);

        changePasswordResponse.setErrorCode(context.get(Attributes.ERROR_CODE));
        changePasswordResponse.setErrorMessage(context.get(Attributes.ERROR_MESSAGE));

        return changePasswordResponse;
    }

    public List<DimensionModel> selectDimensionByWebshipId(int webshipId) throws DaoException, Exception {
        DimensionDao dimensionDao = new DimensionDao();
        List<DimensionModel> dimensionModels = ModelUtils.createListModelFromVo(dimensionDao.getByWebshipId(webshipId), DimensionModel.class);
        return dimensionModels;
    }
}
