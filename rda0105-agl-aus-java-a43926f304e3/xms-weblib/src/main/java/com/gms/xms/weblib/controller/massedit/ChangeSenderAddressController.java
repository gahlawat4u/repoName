package com.gms.xms.weblib.controller.massedit;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.model.invoicing.ListAirbillForMassEditModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.massedit.MassEditDao;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CustomerAddressForMassEditVo;
import com.gms.xms.txndb.vo.ShipmentBillingFilter;
import com.gms.xms.txndb.vo.WebshipAddressForMassEditVo;
import com.gms.xms.txndb.vo.invoicing.ListAirbillForMassEditVo;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Posted from May 12, 2016 3:05:18 PM
 * <p>
 * Author huynd
 */
public class ChangeSenderAddressController extends MassEditController {

    private static final long serialVersionUID = 1L;

    private List<ListAirbillForMassEditModel> listAirbillMassEdit;
    private String listAirbillStr;
    private Long count;
    private Integer type;

    public String showToCustomer() {
        try {
            this.buildListAirbill();
            this.setType(1);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String saveToCustomer() {
        try {
            if (validateChangeSenderAddress()) {
                List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
                List<ListAirbillForMassEditVo> listAirbillVo = ModelUtils.createListVoFromModel(listAirbill, ListAirbillForMassEditVo.class);
                ShipmentBillingFilter shipmentBilling;
                AddressVo address;
                MassEditDao massEditDao = new MassEditDao();
                Long count = 0L;
                for (ListAirbillForMassEditVo list : listAirbillVo) {
                    shipmentBilling = new ShipmentBillingFilter();
                    shipmentBilling.setShipmentId(list.getShipmentId());
                    shipmentBilling.setAirbillNumber(list.getAirbillNumber());
                    CustomerAddressForMassEditVo addressForMassEdit = massEditDao.selectCustomerAddress(shipmentBilling);
                    if (addressForMassEdit != null) {
                        address = new AddressVo();
                        address.setCompanyName(addressForMassEdit.getCustomerName());
                        address.setPhone(addressForMassEdit.getPhone());
                        address.setContactName(addressForMassEdit.getContactName());
                        address.setEmail(addressForMassEdit.getEmail());
                        address.setCountry(addressForMassEdit.getCountry());
                        address.setAddress(addressForMassEdit.getAddress1());
                        address.setAddress2(addressForMassEdit.getAddress2());
                        address.setCity(addressForMassEdit.getCity());
                        address.setPostalCode(addressForMassEdit.getPostalCode());
                        address.setAddressId(addressForMassEdit.getSenderAddressId());
                        massEditDao.massEditChangeSenderAddress(this.getAddInfoMap(), address);
                        count++;
                    }
                }
                this.setCount(count);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String showToWebship() {
        try {
            this.buildListAirbill();
            this.setType(2);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String saveToWebship() {
        try {
            if (validateChangeSenderAddress()) {
                List<ListAirbillForMassEditModel> listAirbill = this.getListAirbill();
                List<ListAirbillForMassEditVo> listAirbillVo = ModelUtils.createListVoFromModel(listAirbill, ListAirbillForMassEditVo.class);
                ShipmentBillingFilter shipmentBilling;
                AddressVo address;
                MassEditDao massEditDao = new MassEditDao();
                Long count = 0L;
                for (ListAirbillForMassEditVo list : listAirbillVo) {
                    shipmentBilling = new ShipmentBillingFilter();
                    shipmentBilling.setShipmentId(list.getShipmentId());
                    shipmentBilling.setAirbillNumber(list.getAirbillNumber());
                    WebshipAddressForMassEditVo addressForMassEdit = massEditDao.selectWebshipAddress(shipmentBilling);
                    if (addressForMassEdit != null) {
                        address = new AddressVo();
                        address.setCompanyName(addressForMassEdit.getCompanyName());
                        address.setPhone(addressForMassEdit.getPhone());
                        address.setContactName(addressForMassEdit.getContactName());
                        address.setEmail(addressForMassEdit.getEmail());
                        address.setCountry(addressForMassEdit.getCountry());
                        address.setAddress(addressForMassEdit.getAddress());
                        address.setAddress2(addressForMassEdit.getAddress2());
                        address.setCity(addressForMassEdit.getCity());
                        address.setPostalCode(addressForMassEdit.getPostalCode());
                        address.setAddressId(addressForMassEdit.getSbSender());
                        massEditDao.massEditChangeSenderAddress(this.getAddInfoMap(), address);
                        count++;
                    }
                }
                this.setCount(count);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private boolean validateChangeSenderAddress() throws CustomException {
        if (StringUtils.isBlank(this.getListAirbillStr())) {
            throw new CustomException("Please choose airbill(s)!");
        }
        return !hasActionErrors() && !hasFieldErrors();
    }

    public List<ListAirbillForMassEditModel> getListAirbillMassEdit() {
        return listAirbillMassEdit;
    }

    public void setListAirbillMassEdit(List<ListAirbillForMassEditModel> listAirbillMassEdit) {
        this.listAirbillMassEdit = listAirbillMassEdit;
    }

    public String getListAirbillStr() {
        return listAirbillStr;
    }

    public void setListAirbillStr(String listAirbillStr) {
        this.listAirbillStr = listAirbillStr;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
