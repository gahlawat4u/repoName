package com.gms.xms.weblib.controller.invoicing.manageinvoice;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.AddressModel;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.txndb.vo.AddressVo;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;

import java.util.List;

/**
 * File Name: ViewEditInvoiceEditAddressController.java <br/>
 * Author: TANDT <br/>
 * Create Date: 16-03-2016 <br/>
 * Project Name: xms-weblib <br/>
 * package Name: com.gms.xms.weblib.controller.invoicing.manageinvoice <br/>
 */
public class ViewEditInvoiceEditAddressController extends AdminJsonBaseController {
    private static final long serialVersionUID = -6615398575552178433L;

    private String senderAddressId;
    private String receiverAddressId;
    private String invoiceStatus;
    private AddressModel senderAddress;
    private AddressModel receiverAddress;
    private List<CountryModel> countryList;

    public String editSenderAddress() {
        try {
            this.setInvoiceStatus(invoiceStatus);
            this.buildCountryList();
            IAddressService service = new AddressServiceImp();
            this.setSenderAddress(ModelUtils.createModelFromVo(service.getAddressById(Integer.parseInt(senderAddressId)), AddressModel.class));
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String editSenderAddressDo() {
        try {
            IAddressService service = new AddressServiceImp();
            service.update(this.getAddInfoMap(), ModelUtils.createVoFromModel(this.getSenderAddress(), AddressVo.class));
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String editReceiverAddress() {
        try {
            this.setInvoiceStatus(invoiceStatus);
            this.buildCountryList();
            IAddressService service = new AddressServiceImp();
            this.setReceiverAddress(ModelUtils.createModelFromVo(service.getAddressById(Integer.parseInt(receiverAddressId)), AddressModel.class));
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    public String editReceiverAddressDo() {
        try {
            IAddressService service = new AddressServiceImp();
            service.update(this.getAddInfoMap(), ModelUtils.createVoFromModel(this.getReceiverAddress(), AddressVo.class));
        } catch (Exception e) {
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            log.error(e);
        }
        return "success";
    }

    protected void buildCountryList() throws Exception {
        ICountryService service = new CountryServiceImp();
        List<CountryVo> countryVos = service.getCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        for (CountryModel country : countryModels) {
            country.setDisplayName(country.getCountryCode().concat(" - ").concat(country.getCountryName()));
        }
        this.setCountryList(countryModels);
    }

    public String getSenderAddressId() {
        return senderAddressId;
    }

    public void setSenderAddressId(String senderAddressId) {
        this.senderAddressId = senderAddressId;
    }

    public String getReceiverAddressId() {
        return receiverAddressId;
    }

    public void setReceiverAddressId(String receiverAddressId) {
        this.receiverAddressId = receiverAddressId;
    }

    public AddressModel getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(AddressModel senderAddress) {
        this.senderAddress = senderAddress;
    }

    public AddressModel getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(AddressModel receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public List<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

}
