package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.model.InvoiceModel;
import com.gms.xms.model.admin.invoicing.manageinvoice.moveairbill.InvoiceExistingModel;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.dao.admin.invoicing.ViewEditInvoiceDao;
import com.gms.xms.persistence.service.massedit.IMassEditService;
import com.gms.xms.persistence.service.massedit.MassEditServiceImp;
import com.gms.xms.persistence.utils.IRecalculateCharge;
import com.gms.xms.txndb.vo.InvoiceVo;
import com.gms.xms.txndb.vo.ShipmentInvoiceVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill.InvoiceExistingFilter;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.moveairbill.InvoiceExistingVo;
import com.gms.xms.txndb.vo.invoicing.searchairbill.SearchAirbillVo;
import com.gms.xms.workflow.utils.RecalculateChargeImp;
import org.apache.commons.lang.StringUtils;

import java.util.List;

/**
 * Posted from Jun 30, 2016 9:36:00 AM
 * <p>
 * Author huynd
 */
public class MoveAirbillController extends MassEditController {

    private static final long serialVersionUID = 1L;
    // Parameter
    private String airbillNumber;
    private String invoiceDate;
    private String moveInvoiceType;
    private String customerCode;
    private String invoiceCode;
    private Boolean checkRecalcCustomerCost;
    private String invoiceCodeResult;
    private InvoiceModel invoice;
    private Boolean isMultiAirbill;

    // Result
    private List<InvoiceExistingModel> invoiceExistings;

    private Long count;

    public String show() {
        try {
            this.determineAdminLevel();
            this.buildListAirbill();
            this.validateListAirbill();
            if (!prepareShowAirbillDetail()) {
                throw new CustomException("No airbill to move!");
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
            return "error";
        }
        return "success";
    }

    private Boolean prepareShowAirbillDetail() throws Exception {
        ViewEditInvoiceDao dao = new ViewEditInvoiceDao();
        InvoiceExistingFilter filter = new InvoiceExistingFilter();
        filter.setFranchiseList(this.buildFranchiseCodeList("All"));
        this.determineAdminLevel();
        Integer userLevel = this.getAdminLevel();
        String invoiceDateList = "";
        InvoiceInfoVo invoiceInfoVo;
        this.setIsMultiAirbill(false);
        Integer countNotUnFrozen = 0;
        if (this.getListAirbillMassEdit() != null) {
            List<ListAirbillForMassEditModel> listAirbillMassEdit = this.getListAirbillMassEdit();
            if (listAirbillMassEdit.size() == 1) {
                invoiceInfoVo = dao.selectInvoiceInfoById(Long.valueOf(listAirbillMassEdit.get(0).getInvoiceId()));
                if (invoiceInfoVo.getStatus() != 0) {
                    return false;
                }
                this.setInvoiceCode(invoiceInfoVo.getInvoiceCode());
                this.setAirbillNumber(listAirbillMassEdit.get(0).getAirbillNumber());
                this.setInvoiceDate(DateUtils.convertDateToString(invoiceInfoVo.getInvoiceDate(), "dd-MM-yyyy", null));
                if (userLevel < 2) {
                    filter.setInvoiceDate(invoiceInfoVo.getInvoiceDate());
                }
            } else {
                for (ListAirbillForMassEditModel listAirbill : listAirbillMassEdit) {
                    invoiceInfoVo = dao.selectInvoiceInfoById(Long.valueOf(listAirbill.getInvoiceId()));
                    invoiceDateList += "'" + DateUtils.convertDateToString(invoiceInfoVo.getInvoiceDate(), "yyyy-MM-dd", null) + "',";
                    if (invoiceInfoVo.getStatus() != 0) {
                        countNotUnFrozen++;
                    }
                }
                if (countNotUnFrozen > 0 && countNotUnFrozen == listAirbillMassEdit.size()) {
                    return false;
                }
                invoiceDateList = invoiceDateList.substring(0, invoiceDateList.length() - 1);
                if (userLevel < 2) {
                    filter.setInvoiceDateList(invoiceDateList);
                }
                this.setIsMultiAirbill(true);
            }
        } else {
            List<SearchAirbillVo> airbillVos = getAirbillListByFilter();
            if (airbillVos.size() == 0) {
                return false;
            } else if (airbillVos.size() == 1) {
                invoiceInfoVo = dao.selectInvoiceInfoById(Long.valueOf(airbillVos.get(0).getInvoiceId()));
                if (invoiceInfoVo.getStatus() != 0) {
                    return false;
                }
                this.setInvoiceCode(invoiceInfoVo.getInvoiceCode());
                this.setAirbillNumber(airbillVos.get(0).getAirbillNumber());
                this.setInvoiceDate(DateUtils.convertDateToString(airbillVos.get(0).getInvoiceDate(), "dd-MM-yyyy", null));
                if (userLevel < 2) {
                    filter.setInvoiceDate(airbillVos.get(0).getInvoiceDate());
                }
            } else {
                for (SearchAirbillVo searchAirbill : airbillVos) {
                    invoiceInfoVo = dao.selectInvoiceInfoById(searchAirbill.getInvoiceId());
                    invoiceDateList += "'" + DateUtils.convertDateToString(invoiceInfoVo.getInvoiceDate(), "yyyy-MM-dd", null) + "',";
                    if (invoiceInfoVo.getStatus() != 0) {
                        countNotUnFrozen++;
                    }
                }
                if (countNotUnFrozen == 1 || (countNotUnFrozen > 0 && countNotUnFrozen == airbillVos.size())) {
                    return false;
                }
                invoiceDateList = invoiceDateList.substring(0, invoiceDateList.length() - 1);
                if (userLevel < 2) {
                    filter.setInvoiceDateList(invoiceDateList);
                }
                this.setIsMultiAirbill(true);
            }
        }
        List<InvoiceExistingVo> listInvoiceExits = dao.selectExistingInvoice(filter);
        List<InvoiceExistingModel> invoiceExistingModels = ModelUtils.createListModelFromVo(listInvoiceExits, InvoiceExistingModel.class);
        if (this.getIsMultiAirbill()) {
            InvoiceExistingModel firstChoice = new InvoiceExistingModel();
            firstChoice.setInvoiceCode("");
            firstChoice.setCustomerName("");
            invoiceExistingModels.add(0, firstChoice);
        }
        this.setInvoiceExistings(invoiceExistingModels);
        return true;
    }

    public String move() {
        try {
            this.determineAdminLevel();
            List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
            if (listAirbill.size() > 0) {
                if (this.getAdminLevel() > 2) {
                    this.setInvoiceDate(listAirbill.get(0).getInvoiceDate());
                }
            }
            // Valid input.
            validInput();
            // Do moving.
            Long count = 0L;
            IMassEditService service = new MassEditServiceImp();
            ShipmentInvoiceVo shipmentInvoice;
            String invoiceCodeResult = "";
            Boolean checkRecalcCustomerCost = (this.getCheckRecalcCustomerCost() != null) ? true : false;
            customerCode = (StringUtils.isEmpty(this.getCustomerCode())) ? "0" : this.getCustomerCode();
            IRecalculateCharge iRecalculateCharge = new RecalculateChargeImp(this.getAddInfoMap());
            for (ListAirbillForMassEditModel list : listAirbill) {
                Long invoiceId = Long.valueOf(list.getInvoiceId());
                shipmentInvoice = new ShipmentInvoiceVo();
                shipmentInvoice.setInvoiceId(invoiceId);
                shipmentInvoice.setAirbillNumber(list.getAirbillNumber());
                shipmentInvoice.setShipmentId(Long.parseLong(list.getShipmentId()));
                // Move airbill.
                InvoiceDao invoiceDao = new InvoiceDao();
                InvoiceVo invoiceVo = invoiceDao.selectById(invoiceId);
                if (invoiceVo.getStatus() == 0) {
                    invoiceCodeResult = service.moveAirbill(this.getAddInfoMap(), invoiceId, shipmentInvoice, Long.parseLong(this.getCustomerCode()), invoiceDate, invoiceCode, moveInvoiceType, checkRecalcCustomerCost, iRecalculateCharge);
                    count++;
                }
            }
            this.setInvoiceCodeResult(invoiceCodeResult);
            this.setCount(count);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void validateListAirbill() throws Exception {
        this.determineAdminLevel();
        List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
        String oldInvoiceDate = "";
        for (ListAirbillForMassEditModel listAirbillForMassEditModel : listAirbill) {
            if (this.getAdminLevel() > 2) {
                if (!StringUtils.isBlank(oldInvoiceDate) && !listAirbillForMassEditModel.getInvoiceDate().equalsIgnoreCase(oldInvoiceDate)) {
                    throw new CustomException("Please choose airbills that have same invoice date to move");
                } else {
                    oldInvoiceDate = listAirbillForMassEditModel.getInvoiceDate();
                }
            }

        }
    }

    protected void validInput() throws Exception {
        Integer moveType;
        try {
            moveType = Integer.valueOf(this.getMoveInvoiceType());
        } catch (Exception e) {
            moveType = null;
        }
        if (moveType == null) {
            throw new CustomException("Invalid move invoice type.");
        }
        // If it's moving to new customer.
        if (moveType == 1) {
            if (StringUtils.isBlank(this.getCustomerCode())) {
                throw new CustomException("Please enter Customer Code.");
            }
            if (StringUtils.isBlank(this.getInvoiceDate())) {
                throw new CustomException("Please enter Invoice Date.");
            }
        } else {
            // Moving to existing invoice.
            if (StringUtils.isBlank(this.getInvoiceCode())) {
                throw new CustomException("Please enter Invoice Code.");
            }
        }
    }

    public String getAirbillNumber() {
        return airbillNumber;
    }

    public void setAirbillNumber(String airbillNumber) {
        this.airbillNumber = airbillNumber;
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

    public String getInvoiceCode() {
        return invoiceCode;
    }

    public void setInvoiceCode(String invoiceCode) {
        this.invoiceCode = invoiceCode;
    }

    public Boolean getCheckRecalcCustomerCost() {
        return checkRecalcCustomerCost;
    }

    public void setCheckRecalcCustomerCost(Boolean checkRecalcCustomerCost) {
        this.checkRecalcCustomerCost = checkRecalcCustomerCost;
    }

    public String getInvoiceCodeResult() {
        return invoiceCodeResult;
    }

    public void setInvoiceCodeResult(String invoiceCodeResult) {
        this.invoiceCodeResult = invoiceCodeResult;
    }

    public InvoiceModel getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceModel invoice) {
        this.invoice = invoice;
    }

    public Boolean getIsMultiAirbill() {
        return isMultiAirbill;
    }

    public void setIsMultiAirbill(Boolean isMultiAirbill) {
        this.isMultiAirbill = isMultiAirbill;
    }

    public List<InvoiceExistingModel> getInvoiceExistings() {
        return invoiceExistings;
    }

    public void setInvoiceExistings(List<InvoiceExistingModel> invoiceExistings) {
        this.invoiceExistings = invoiceExistings;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
