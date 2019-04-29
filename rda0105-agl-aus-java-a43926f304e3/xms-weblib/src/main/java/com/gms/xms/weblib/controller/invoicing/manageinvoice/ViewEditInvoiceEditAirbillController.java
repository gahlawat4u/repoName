package com.gms.xms.weblib.controller.invoicing.manageinvoice;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.filter.account.franchises.MarkupRateFilter;
import com.gms.xms.model.AccessorialModel;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.MarkupRateModel;
import com.gms.xms.model.ServiceModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.editairbill.AccessorialInfoModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.editairbill.AirbillInfoEditModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.editairbill.ViewEditInvoiceAccessorialModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.persistence.dao.ShipmentBillingDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AirbillInfoEditVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.ViewEditInvoiceAccessorialVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from ViewEditInvoiceEditAirbillController
 * <p>
 * Author TanDT
 */
public class ViewEditInvoiceEditAirbillController extends AdminJsonBaseController {

    /**
     *
     */
    private static final long serialVersionUID = -8752990719335920957L;

    // Result
    private List<ViewEditInvoiceAccessorialModel> accessorialModels;
    protected List<AccessorialInfoModel> accessorialInfoModels;
    protected AirbillInfoEditModel airbillInfo;
    private List<ServiceModel> serviceModels;
    private List<ShipmentTypeModel> shipmentTypeModels;
    private List<AccessorialModel> accessorials;
    private List<CountryModel> listCountryOrigin;
    private List<CountryModel> listCountryDestination;

    // Parameter
    private String invoiceStatus;
    private String shipmentId;
    private String serviceId;
    private String airbillNumber;
    private String invoiceId;

    private MarkupRateModel markup;

    public String loadEditAirbill() {
        if (prepareShowAirbillDetail()) {
            return "success";
        } else {
            return "error";
        }
    }

    public String editAirbill() {
        try {
            if (validAirbillInfo()) {
                determineAdminLevel();
                ContextBase2 contextBase2 = new ContextBase2(this.getAddInfoMap());
                AirbillInfoEditVo airbillInfoEditVo = ModelUtils.createVoFromModel(airbillInfo, AirbillInfoEditVo.class);
                List<AccessorialInfoVo> accessorialInfoVo = ModelUtils.createListVoFromModel(accessorialInfoModels, AccessorialInfoVo.class);
                AccessorialDao accessorialDao = new AccessorialDao();
                for (AccessorialInfoVo accessorialInfoVo2 : accessorialInfoVo) {
                    AccessorialVo accessorialVo = accessorialDao.selectById(accessorialInfoVo2.getAccessorialId());
                    if (accessorialVo != null) {
                        accessorialInfoVo2.setDescription(accessorialVo.getDescription());
                    }
                }
                contextBase2.put(Attributes.ADMIN_LEVEL, this.getAdminLevel());
                contextBase2.put(Attributes.AIRBILL_INFO_VO, airbillInfoEditVo);
                contextBase2.put(Attributes.ACCESSORIAL_INFO_VO, accessorialInfoVo);
                contextBase2.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoice-EditAirbillDo");
                contextBase2 = WorkFlowManager2.getInstance().process(contextBase2);
                if (ErrorCode.ERROR.equalsIgnoreCase(contextBase2.getString(Attributes.ERROR_CODE))) {
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    setErrorMessage(contextBase2.getString(Attributes.ERROR_MESSAGE));
                    log.error(contextBase2.getString(Attributes.ERROR_MESSAGE));
                    return "error";
                }
            } else {
                setErrorCode(ErrorCode.ACTION_ERROR);
                return "error";
            }
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String loadCreateAirbill() {
        if (prepareShowCreateAirbill()) {
            return "success";
        } else {
            return "error";
        }
    }

    public String createAirbillDo() {
        this.setPageTitle("View Edit Invoice - Create Airbill");
        try {
            if (validAirbillInfo() && validCreateAirbillData()) {
                determineAdminLevel();
                ContextBase2 contextBase2 = new ContextBase2(this.getAddInfoMap());
                AirbillInfoEditVo airbillInfoEditVo = ModelUtils.createVoFromModel(airbillInfo, AirbillInfoEditVo.class);
                List<AccessorialInfoVo> accessorialInfoVo = ModelUtils.createListVoFromModel(accessorialInfoModels, AccessorialInfoVo.class);
                contextBase2.put(Attributes.AIRBILL_INFO_VO, airbillInfoEditVo);
                contextBase2.put(Attributes.ADMIN_LEVEL, this.getAdminLevel());
                contextBase2.put(Attributes.INVOICE_ID, Long.parseLong(invoiceId));
                contextBase2.put(Attributes.ACCESSORIAL_INFO_VO, accessorialInfoVo);
                contextBase2.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoice-CreateAirbillDo");
                contextBase2 = WorkFlowManager2.getInstance().process(contextBase2);
                if (ErrorCode.ERROR.equalsIgnoreCase(contextBase2.getString(Attributes.ERROR_CODE))) {
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    setErrorMessage(contextBase2.getString(Attributes.ERROR_MESSAGE));
                    log.error(contextBase2.getString(Attributes.ERROR_MESSAGE));
                    return "error";
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected boolean validAirbillInfo() throws CustomException {
        // Valid airbillInfo collection model.
        if (StringUtils.isBlank(airbillInfo.getAirbillNumber())) {
            throw new CustomException("Airbill cannot blank.");
        }
        if (StringUtils.isBlank(airbillInfo.getServiceCode())) {
            throw new CustomException("Service level cannot blank.");
        }
        if (StringUtils.isBlank(airbillInfo.getShipmentDate())) {
            throw new CustomException("Shipment date cannot leave blank.");
        }
        if (StringUtils.isBlank(airbillInfo.getDestinationId())) {
            throw new CustomException("Destination cannot leave blank.");
        }

        if (airbillInfo.getDestinationId().equals("0")) {
            throw new CustomException("Please chooice destination.");
        }
        return !hasActionErrors();
    }

    protected boolean validCreateAirbillData() throws CustomException, DaoException {
        // Check airbill in system.
        ShipmentBillingDao shipmentBillingDao = new ShipmentBillingDao();
        ShipmentBillingFilter shipmentBillingFilter = new ShipmentBillingFilter();
        shipmentBillingFilter.setAirbillNumber(airbillInfo.getAirbillNumber());
        shipmentBillingFilter.setCarrier(Long.valueOf(airbillInfo.getServiceId()));
        List<ShipmentBillingVo> listShipmentBillingVo = shipmentBillingDao.selectByAirbillNumber(shipmentBillingFilter);
        if (listShipmentBillingVo != null && listShipmentBillingVo.size() > 0) {
            throw new CustomException("This airbill is already exists.");
        }
        return !hasActionErrors();
    }

    protected boolean prepareShowCreateAirbill() {
        try {
            Integer invStatus = Integer.valueOf(this.getInvoiceStatus());
            if (invStatus != 0) {
                addActionError("This invoice is frozen");
                return false;
            }
            ContextBase2 contextBase2 = new ContextBase2(this.getAddInfoMap());
            contextBase2.put(Attributes.WFP_NAME, "Wfl-ShowCreateAirbill");
            contextBase2 = WorkFlowManager2.getInstance().process(contextBase2);
            if (ErrorCode.ERROR.equalsIgnoreCase(contextBase2.getString(Attributes.ERROR_CODE))) {
                log.error(contextBase2.getString(Attributes.ERROR_MESSAGE));
                setErrorCode(ErrorCode.ACTION_ERROR);
                setErrorMessage(contextBase2.getString(Attributes.ERROR_MESSAGE));
                log.error(contextBase2.getString(Attributes.ERROR_MESSAGE));
                return false;
            }
            List<ViewEditInvoiceAccessorialVo> accessorialVos = contextBase2.get(Attributes.ACCESSORIAL_LIST);
            List<ShipmentTypeVo> shipmentTypeVos = contextBase2.get(Attributes.SHIPMENT_TYPE_LIST_RESULT);
            List<CountryModel> countryOrigins = contextBase2.get(Attributes.COUNTRY_LIST_RESULT);
            List<CountryModel> countryDestinations = contextBase2.get(Attributes.COUNTRY_DESTINATION_LIST_RESULT);
            List<ServiceModel> serviceModelsN = contextBase2.get(Attributes.SERVICE_LIST_RESULT);
            List<AccessorialInfoVo> accessorialInfoVos = new ArrayList<AccessorialInfoVo>();
            for (int i = 0; i < 8; i++) {
                AccessorialInfoVo accessorialInfoN = new AccessorialInfoVo();
                accessorialInfoN.setIsGst(false);
                accessorialInfoN.setRequireCalculate(false);
                accessorialInfoVos.add(i, accessorialInfoN);
            }
            this.setAccessorialInfoModels(ModelUtils.createListModelFromVo(accessorialInfoVos, AccessorialInfoModel.class));
            this.setAccessorialModels(ModelUtils.createListModelFromVo(accessorialVos, ViewEditInvoiceAccessorialModel.class));
            this.setShipmentTypeModels(ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class));
            this.setListCountryOrigin(countryOrigins);
            this.setListCountryDestination(countryDestinations);
            this.setServiceModels(serviceModelsN);
            this.setInvoiceId(invoiceId);
            this.setAirbillInfo(airbillInfo);
            AirbillInfoEditVo airbillInfoVo = ModelUtils.createVoFromModel(airbillInfo, AirbillInfoEditVo.class);
            prepareMarkupRate(airbillInfoVo.getCustomerCode(), null, null);
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    private boolean prepareShowAirbillDetail() {
        try {
            determineAdminLevel();
            ContextBase2 contextBase2 = new ContextBase2(this.getAddInfoMap());
            contextBase2.put(Attributes.SHIPMENT_ID, Long.parseLong(shipmentId));
            contextBase2.put(Attributes.AIRBILL_NUMBER, airbillNumber);
            contextBase2.put(Attributes.WFP_NAME, "Wfl-ShowAirbillDetail");
            contextBase2 = WorkFlowManager2.getInstance().process(contextBase2);
            if (ErrorCode.ERROR.equalsIgnoreCase(contextBase2.getString(Attributes.ERROR_CODE))) {
                log.error(contextBase2.getString(Attributes.ERROR_MESSAGE));
                setErrorCode(ErrorCode.ACTION_ERROR);
                setErrorMessage(contextBase2.getString(Attributes.ERROR_MESSAGE));
                log.error(contextBase2.getString(Attributes.ERROR_MESSAGE));
                return false;
            }
            List<ViewEditInvoiceAccessorialVo> accessorialVos = contextBase2.get(Attributes.ACCESSORIAL_LIST);
            List<AccessorialInfoVo> accessorialInfoVos = contextBase2.get(Attributes.ACCESSORIAL_INFO_VO);
            AirbillInfoEditVo airbillInfoEditVo = contextBase2.get(Attributes.AIRBILL_INFO_VO);
            List<ShipmentTypeVo> shipmentTypeVos = contextBase2.get(Attributes.SHIPMENT_TYPE_LIST_RESULT);
            List<CountryModel> countryOrigins = contextBase2.get(Attributes.COUNTRY_LIST_RESULT);
            List<CountryModel> countryDestinations = contextBase2.get(Attributes.COUNTRY_DESTINATION_LIST_RESULT);
            List<ServiceModel> serviceModelsN = contextBase2.get(Attributes.SERVICE_LIST_RESULT);

            int count = (accessorialInfoVos == null || accessorialInfoVos.size() == 0) ? 0 : accessorialInfoVos.size();
            for (int i = 0; i < 8 - count; i++) {
                AccessorialInfoVo accessorialInfoN = new AccessorialInfoVo();
                accessorialInfoN.setIsGst(false);
                accessorialInfoN.setRequireCalculate(false);
                accessorialInfoN.setIsNew(1);
                accessorialInfoVos.add(accessorialInfoN);
            }

            Integer invStatus = contextBase2.getInt(Attributes.INVOICE_STATUS);
            this.setInvoiceStatus(String.valueOf(invStatus));
            this.setAccessorialModels(ModelUtils.createListModelFromVo(accessorialVos, ViewEditInvoiceAccessorialModel.class));
            this.setAccessorialInfoModels(ModelUtils.createListModelFromVo(accessorialInfoVos, AccessorialInfoModel.class));
            this.setAirbillInfo(ModelUtils.createModelFromVo(airbillInfoEditVo, AirbillInfoEditModel.class));
            this.setShipmentTypeModels(ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class));
            this.setListCountryOrigin(countryOrigins);
            this.setListCountryDestination(countryDestinations);
            this.setServiceModels(serviceModelsN);
            this.setServiceId(String.valueOf(airbillInfoEditVo.getServiceId()));
            prepareMarkupRate(airbillInfoEditVo.getCustomerCode(), airbillInfoEditVo.getServiceId(), airbillInfoEditVo.getShipmentTypeId());
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    public String changeService() {
        try {
            ContextBase2 contextBase2 = new ContextBase2(this.getAddInfoMap());
            contextBase2.put(Attributes.SERVICE_ID, Integer.parseInt(serviceId));
            contextBase2.put(Attributes.WFP_NAME, "Wfl-ShowAirbillDetail-ChangeService");
            contextBase2 = WorkFlowManager2.getInstance().process(contextBase2);
            if (ErrorCode.ERROR.equalsIgnoreCase(contextBase2.getString(Attributes.ERROR_CODE))) {
                throw new Exception(Attributes.ERROR_MESSAGE);
            }
            List<ShipmentTypeVo> shipmentTypeVos = contextBase2.get(Attributes.SHIPMENT_TYPE_LIST_RESULT);
            this.setShipmentTypeModels(ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    public String changeAccessorial() throws DaoException {
        try {
            AccessorialDao accessorialDao = new AccessorialDao();
            AccessorialFilter accessorialFilter = new AccessorialFilter();
            accessorialFilter.setCarrier(Long.parseLong(serviceId));
            List<AccessorialVo> accessorialVos = accessorialDao.selectSurChargeList(accessorialFilter);
            List<AccessorialModel> accessorials = ModelUtils.createListModelFromVo(accessorialVos, AccessorialModel.class);
            this.setAccessorials(accessorials);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void prepareMarkupRate(Long customerCode, Integer serviceId, Integer shipmentTypeId) throws Exception {
        FranchiseDao franchiseDao = new FranchiseDao();
        MarkupRateFilter markupRateFilter = new MarkupRateFilter();
        markupRateFilter.setCustomerCode(customerCode);
        if (serviceId != null && serviceId == 54) {
            markupRateFilter.setShipmentTypeId(shipmentTypeId);
        }
        MarkupRateVo markupRateVo = franchiseDao.selectMarkupRateByCustomerCode(markupRateFilter);
        MarkupRateModel markup = ModelUtils.createModelFromVo(markupRateVo, MarkupRateModel.class);
        this.setMarkup(markup);
    }

    public List<ViewEditInvoiceAccessorialModel> getAccessorialModels() {
        return accessorialModels;
    }

    public void setAccessorialModels(List<ViewEditInvoiceAccessorialModel> accessorialModels) {
        this.accessorialModels = accessorialModels;
    }

    public List<AccessorialInfoModel> getAccessorialInfoModels() {
        return accessorialInfoModels;
    }

    public void setAccessorialInfoModels(List<AccessorialInfoModel> accessorialInfoModels) {
        this.accessorialInfoModels = accessorialInfoModels;
    }

    public AirbillInfoEditModel getAirbillInfo() {
        return airbillInfo;
    }

    public void setAirbillInfo(AirbillInfoEditModel airbillInfo) {
        this.airbillInfo = airbillInfo;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public List<ServiceModel> getServiceModels() {
        return serviceModels;
    }

    public void setServiceModels(List<ServiceModel> serviceModels) {
        this.serviceModels = serviceModels;
    }

    public List<ShipmentTypeModel> getShipmentTypeModels() {
        return shipmentTypeModels;
    }

    public void setShipmentTypeModels(List<ShipmentTypeModel> shipmentTypeModels) {
        this.shipmentTypeModels = shipmentTypeModels;
    }

    public List<CountryModel> getListCountryOrigin() {
        return listCountryOrigin;
    }

    public void setListCountryOrigin(List<CountryModel> listCountryOrigin) {
        this.listCountryOrigin = listCountryOrigin;
    }

    public List<CountryModel> getListCountryDestination() {
        return listCountryDestination;
    }

    public void setListCountryDestination(List<CountryModel> listCountryDestination) {
        this.listCountryDestination = listCountryDestination;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public MarkupRateModel getMarkup() {
        return markup;
    }

    public void setMarkup(MarkupRateModel markup) {
        this.markup = markup;
    }

    public List<AccessorialModel> getAccessorials() {
        return accessorials;
    }

    public void setAccessorials(List<AccessorialModel> accessorials) {
        this.accessorials = accessorials;
    }

}
