package com.gms.xms.weblib.controller.invoicing.manageinvoice;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.moveairbill.InvoiceCustomerModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.moveairbill.InvoiceExistingModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.dao.admin.invoicing.ViewEditInvoiceDao;
import com.gms.xms.persistence.service.admin.invoicing.IViewEditInvoiceService;
import com.gms.xms.persistence.service.admin.invoicing.ViewEditInvoiceServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AccessorialInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.editairbill.AirbillInfoEditVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill.InvoiceCustomerVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill.InvoiceExistingFilter;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill.InvoiceExistingVo;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.utils.RecalculateChargeImp;
import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * File Name: ViewEditInvoiceMoveAirbillController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 15-03-2016 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.invoicing.manageinvoice <br/>
 */
public class ViewEditInvoiceMoveAirbillController extends ViewEditInvoiceEditAirbillController {

    private static final long serialVersionUID = -5730698920351749898L;
    // Parameter
    private String shipmentId;
    private String airbillNumber;
    private String invoiceId;
    private String invoiceDate;
    private String moveInvoiceType;
    private String customerCode;
    private String invoiceCode;
    private List<String> massEditShipments;
    private InvoiceModel invoice;
    private Boolean checkRecalcCustomerCost;

    // Result
    private List<InvoiceExistingModel> invoiceExistings;

    private List<InvoiceCustomerModel> invoiceCustomerCode;

    private String urlParams;

    public String loadMoveAirbill() {
        if (prepareShowAirbillDetail()) {
            return "success";
        } else {
            return "error";
        }
    }

    public String loadAddingInvoice() {
        return "success";
    }

    public String loadNewInvoice() {
        return "success";
    }

    public String loadFindAirbill() {
        this.setInvoiceId(invoiceId);
        return "success";
    }

    public String newInvoiceDo() {
        try {
            InvoiceDao invoiceDao = new InvoiceDao();
            String newInvoiceCode;
            InvoiceVo newInvoice;
            if (validateInvoice(customerCode, invoiceDate)) {
                newInvoiceCode = GenCodeUtils.generateInvoiceCode(customerCode, DateUtils.convertStringToDate(invoiceDate, "dd-MM-yyyy", null));
                // Check if the new invoice code exists or not?
                newInvoice = invoiceDao.getByCode(newInvoiceCode);
                if (validateNewInvoice(newInvoice)) {
                    // New invoice code not existed.
                    InvoiceModel invoice = new InvoiceModel();
                    invoice.setInvoiceCode(newInvoiceCode);
                    invoice.setCustomerCode(customerCode);
                    invoice.setInvoiceDate(invoiceDate);
                    this.setInvoice(invoice);
                    prepareMarkupRate(Long.valueOf(customerCode), null, null);
                    prepareShowCreateAirbill();
                }
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    private boolean validateInvoice(String customerCode, String invoiceDate) throws CustomException {
        if (StringUtils.isBlank(customerCode)) {
            throw new CustomException("Invalid customer code.");
        }
        if (StringUtils.isBlank(invoiceDate)) {
            throw new CustomException("Invoice date can not blank.");
        }
        return !hasActionErrors();
    }

    private boolean validateNewInvoice(InvoiceVo newInvoice) throws CustomException {
        if (newInvoice != null) {
            throw new CustomException("Invoice Existed In System.");
        }
        return !hasActionErrors();
    }

    public String newAirbillDo() {
        try {
            if (validAirbillInfo() && validCreateAirbillData()) {
                determineAdminLevel();
                InvoiceModel invoice = this.getInvoice();
                InvoiceVo newInvoice = ModelUtils.createVoFromModel(invoice, InvoiceVo.class);
                newInvoice.setStatus(0);
                newInvoice.setLateFee(BigDecimal.valueOf(0D));
                newInvoice.setPaid(0);
                newInvoice.setInvCreateDate(new Date());

                ContextBase2 contextBase2 = new ContextBase2(this.getAddInfoMap());
                AirbillInfoEditVo airbillInfoEditVo = ModelUtils.createVoFromModel(airbillInfo, AirbillInfoEditVo.class);
                List<AccessorialInfoVo> accessorialInfoVo = ModelUtils.createListVoFromModel(accessorialInfoModels, AccessorialInfoVo.class);
                contextBase2.put(Attributes.ADMIN_LEVEL, this.getAdminLevel());
                contextBase2.put(Attributes.INVOICE_VO, newInvoice);
                contextBase2.put(Attributes.AIRBILL_INFO_VO, airbillInfoEditVo);
                contextBase2.put(Attributes.ACCESSORIAL_INFO_VO, accessorialInfoVo);
                contextBase2.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoice-CreateInvoiceAndAirbill");
                contextBase2 = WorkFlowManager2.getInstance().process(contextBase2);
                InvoiceVo invoiceVo = contextBase2.get(Attributes.INVOICE_VO);
                Map<String, String> replaceMap = new HashMap<String, String>();
                replaceMap.put("{0}", DateUtils.convertDateToString(invoiceVo.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                replaceMap.put("{1}", invoiceVo.getInvoiceCode());
                replaceMap.put("{2}", String.valueOf(invoiceVo.getInvoiceId()));
                String urlParams = "invoiceDate={0}&invoiceCode={1}&invoiceId={2}";
                urlParams = AppUtils.replaceStringByMap(replaceMap, urlParams);
                this.setUrlParams(urlParams);
                if (ErrorCode.ERROR.equalsIgnoreCase(contextBase2.getString(Attributes.ERROR_CODE))) {
                    setErrorCode(ErrorCode.ACTION_ERROR);
                    setErrorMessage(contextBase2.getString(Attributes.ERROR_MESSAGE));
                    log.error(contextBase2.getString(Attributes.ERROR_MESSAGE));
                    return "error";
                }
            } else {
                return "error";
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String loadCustomerCode() {
        try {
            ViewEditInvoiceDao dao = new ViewEditInvoiceDao();
            InvoiceExistingFilter filter = new InvoiceExistingFilter();
            filter.setFranchiseList(this.buildFranchiseCodeList("All"));
            filter.setCustomerCode(customerCode);
            List<InvoiceCustomerVo> invoiceCustomerVo = dao.selectCustomerCodeForAddingInvoice(filter);
            this.setInvoiceCustomerCode(ModelUtils.createListModelFromVo(invoiceCustomerVo, InvoiceCustomerModel.class));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String moveAirbill() throws Exception {
        try {
            this.determineAdminLevel();
            IViewEditInvoiceService viewEditInvoiceService = new ViewEditInvoiceServiceImp();
            Boolean checkRecalcCustomerCost = (this.getCheckRecalcCustomerCost() != null) ? true : false;
            if (massEditShipments != null) {
                for (String massEditShipment : massEditShipments) {
                    String massEditShipmentSplit[] = StringUtils.split(massEditShipment, ",");
                    shipmentId = massEditShipmentSplit[0];
                    airbillNumber = massEditShipmentSplit[1];
                    invoiceId = massEditShipmentSplit[2];
                    invoiceDate = massEditShipmentSplit[3];
                    customerCode = massEditShipmentSplit[4];
                    ShipmentInvoiceVo shipmentInvoice = new ShipmentInvoiceVo();
                    shipmentInvoice.setInvoiceId(Long.parseLong(invoiceId));
                    shipmentInvoice.setAirbillNumber(airbillNumber);
                    shipmentInvoice.setShipmentId(Long.parseLong(shipmentId));
                    IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(this.getAddInfoMap());
                    viewEditInvoiceService.moveAirbill(this.getAddInfoMap(), shipmentInvoice.getInvoiceId(), shipmentInvoice, Long.parseLong(customerCode), invoiceDate, invoiceCode, moveInvoiceType, checkRecalcCustomerCost, iRecalculateCharge);
                }
                this.setInvoiceCode("");
            } else {
                InvoiceDao invoiceDao = new InvoiceDao();
                InvoiceVo invoiceVo = invoiceDao.selectById(Long.parseLong(this.getInvoiceId()));

                ShipmentInvoiceVo shipmentInvoice = new ShipmentInvoiceVo();
                shipmentInvoice.setInvoiceId(Long.parseLong(invoiceId));
                shipmentInvoice.setAirbillNumber(airbillNumber);
                shipmentInvoice.setShipmentId(Long.parseLong(shipmentId));
                if (getAdminLevel() > 2) {
                    this.setInvoiceDate(DateUtils.convertDateToString(invoiceVo.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
                }
                IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(this.getAddInfoMap());
                if (invoiceVo.getStatus() == 0) {
                    String invoiceCodeResult = viewEditInvoiceService.moveAirbill(this.getAddInfoMap(), shipmentInvoice.getInvoiceId(), shipmentInvoice, Long.parseLong(this.getCustomerCode()), invoiceDate, invoiceCode, moveInvoiceType, checkRecalcCustomerCost, iRecalculateCharge);
                    this.setInvoiceCode(invoiceCodeResult);
                } else {
                    throw new CustomException("This airbill has been frozen. Cannot be moved!");
                }
            }

        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    private boolean prepareShowAirbillDetail() {
        try {
            if (massEditShipments != null) {
                invoiceDate = "";
                airbillNumber = "";
                invoiceDate = "";
            }
            ViewEditInvoiceDao dao = new ViewEditInvoiceDao();
            InvoiceExistingFilter filter = new InvoiceExistingFilter();
            filter.setFranchiseList(this.buildFranchiseCodeList("All"));
            this.determineAdminLevel();
            Integer userLevel = this.getAdminLevel();
            if (userLevel > 2) {
                filter.setInvoiceDate(DateUtils.convertStringToDate(this.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null));
            }
            List<InvoiceExistingVo> listInvoiceExits = dao.selectExistingInvoice(filter);
            this.setInvoiceExistings(ModelUtils.createListModelFromVo(listInvoiceExits, InvoiceExistingModel.class));
            InvoiceInfoVo invoiceInfoVo = dao.selectInvoiceInfoById(Long.valueOf(this.getInvoiceId()));
            this.setInvoiceCode(invoiceInfoVo.getInvoiceCode());
            this.setAirbillNumber(this.getAirbillNumber());
            this.setInvoiceDate(this.getInvoiceDate());
            this.setMassEditShipments(this.getMassEditShipments());
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

    @Override
    public String getShipmentId() {
        return shipmentId;
    }

    @Override
    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    @Override
    public String getAirbillNumber() {
        return airbillNumber;
    }

    @Override
    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
    }

    public List<InvoiceExistingModel> getInvoiceExistings() {
        return invoiceExistings;
    }

    public void setInvoiceExistings(List<InvoiceExistingModel> invoiceExistings) {
        this.invoiceExistings = invoiceExistings;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getMoveInvoiceType() {
        return moveInvoiceType;
    }

    public void setMoveInvoiceType(String moveInvoiceType) {
        this.moveInvoiceType = moveInvoiceType;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public List<InvoiceCustomerModel> getInvoiceCustomerCode() {
        return invoiceCustomerCode;
    }

    public void setInvoiceCustomerCode(List<InvoiceCustomerModel> invoiceCustomerCode) {
        this.invoiceCustomerCode = invoiceCustomerCode;
    }

    @Override
    public String getInvoiceId() {
        return invoiceId;
    }

    @Override
    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public List<String> getMassEditShipments() {
        return massEditShipments;
    }

    public void setMassEditShipments(List<String> massEditShipments) {
        this.massEditShipments = massEditShipments;
    }

    public InvoiceModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceModel invoice) {
        this.invoice = invoice;
    }

    public String getUrlParams() {
        return urlParams;
    }

    public void setUrlParams(String urlParams) {
        this.urlParams = urlParams;
    }

    public Boolean getCheckRecalcCustomerCost() {
        return checkRecalcCustomerCost;
    }

    public void setCheckRecalcCustomerCost(Boolean checkRecalcCustomerCost) {
        this.checkRecalcCustomerCost = checkRecalcCustomerCost;
    }
}
