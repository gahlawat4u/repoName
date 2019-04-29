package com.gms.xms.weblib.controller.webship.settings;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.AddressBookDetailModel;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.dao.CustomerAddressDao;
import com.gms.xms.persistence.dao.CustomerDefaultSettingDao;
import com.gms.xms.persistence.service.address.AddressServiceImp;
import com.gms.xms.persistence.service.address.IAddressService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.webship.settings.IUserSettingsService;
import com.gms.xms.persistence.service.webship.settings.UserSettingsServiceImp;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.CustomerAddressVo;
import com.gms.xms.txndb.vo.webship.AddressBookDetailFilter;
import com.gms.xms.txndb.vo.webship.AddressBookDetailVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.txndb.vo.webship.settings.CustomerDefaultSettingDetailVo;
import com.gms.xms.weblib.controller.JsonBaseController;

import java.util.ArrayList;
import java.util.List;

/**
 * Posted from AddressDefaultController
 * <p>
 * Author DatTV Date Jul 15, 2015 9:04:33 AM
 */
public class AddressDefaultController extends JsonBaseController {

    private static final long serialVersionUID = -135363707070005939L;

    private AddressBookDetailModel from;
    private AddressBookDetailModel to;
    private AddressBookDetailModel address;
    private String company;
    private String contact;
    private String isFrom;
    private String addressId;
    private List<AddressBookDetailModel> addressList;
    private String fromWebship;
    private List<CountryModel> countryList;
    private String isSender;

    public String show() {
        try {
            loadAddressDefault();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        // Search
        IAddressService addressService = new AddressServiceImp();
        try {
            List<AddressBookDetailVo> addressDetailVos = addressService.selectByFilter(this.buildFilter());
            setAddressList(ModelUtils.createListModelFromVo(addressDetailVos, AddressBookDetailModel.class));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }

        return isWebshipSearch() ? "webship_success" : "success";
    }

    public String update() {
        try {
            updateSettings(Long.valueOf(addressId), Boolean.valueOf(isFrom));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String loadWebshipAddress() {
        address = new AddressBookDetailModel();
        Long addId = 0L;
        try {
            addId = Long.valueOf(addressId);
        } catch (Exception e) {
        }

        IAddressService addressService = new AddressServiceImp();
        try {
            prepareCountryList();
            AddressBookDetailVo addressBookDetailVo = addressService.selectById(addId);
            AddressBookDetailModel addressBookDetailModel = ModelUtils.createModelFromVo(addressBookDetailVo, AddressBookDetailModel.class);
            address = addressBookDetailModel;
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }

        boolean sender = true;
        try {
            sender = Boolean.valueOf(isSender);
        } catch (Exception e) {
        }
        return sender ? "sender" : "receiver";
    }

    private void prepareCountryList() throws Exception {
        countryList = new ArrayList<CountryModel>();
        ICountryService service = new CountryServiceImp();
        List<CountryVo> countryVos = service.getWebshipCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        CountryModel headerValue = new CountryModel();
        headerValue.setCountryId("0");
        headerValue.setCountryName("Please select a country.");
        countryModels.remove(0);
        countryModels.add(0, headerValue);
        this.setCountryList(countryModels);
    }

    private boolean isWebshipSearch() {
        boolean result = false;
        try {
            result = Boolean.valueOf(fromWebship);
        } catch (Exception e) {
        }
        return result;
    }

    private void updateSettings(Long addressId, boolean isFrom) throws Exception {
        // Check login
        WebshipLoginVo loginVo = this.getWebshipLoginInfo();
        if (loginVo == null) {
            throw new Exception("You don't have permission to do this action");
        }
        // Load customer default settings
        IUserSettingsService settingsService = new UserSettingsServiceImp();
        CustomerDefaultSettingDetailVo settingVo = settingsService.selectDetailByCustomerCode(loginVo.getCustomerCode());

        if (settingVo != null) {
            // Update default address id
            if (isFrom) {
                settingVo.setDefaultFromAddressId(addressId);
            } else {
                settingVo.setDefaultToAddressId(addressId);
            }
            settingsService.update(this.getAddInfoMap(), settingVo);
        } else {
            // Create new customer default setting.
            settingVo = new CustomerDefaultSettingDetailVo();
            settingVo.setCustomerCode(loginVo.getCustomerCode());
            settingVo.setBillingParty(0);
            settingVo.setDutiesBillTo(0);
            settingVo.setDefaultServiceType("0");
            settingVo.setDefaultPackageType(0);
            settingVo.setDefaultFromAddressId(0L);
            settingVo.setDefaultToAddressId(0L);
            settingVo.setDefaultCollection(0);
            settingVo.setDisableQuote(false);
            settingVo.setGlobalAddressBook(false);
            settingVo.setIndividualUserHistory(false);
            settingVo.setBatchProcessing(false);
            settingVo.setDefaultShipperReference("");
            // Insert into the database.
            CustomerDefaultSettingDao defaultSettingDao = new CustomerDefaultSettingDao();
            defaultSettingDao.insert(this.getAddInfoMap(), settingVo);
        }

        // Create from & to default address
        settingVo = settingsService.selectDetailByCustomerCode(loginVo.getCustomerCode());
        buildAddressFromCustomerDefaultSetting(settingVo);
        if (isFrom) {
            address = from;
        } else {
            address = to;
        }
        from = null;
        to = null;
    }

    private AddressBookDetailFilter buildFilter() {
        AddressBookDetailFilter filter = new AddressBookDetailFilter();
        WebshipLoginVo loginVo = this.getWebshipLoginInfo();
        filter.setCustomerCode(loginVo.getCustomerCode());
        filter.setWebshipId(loginVo.getWebshipId());
        filter.setCompanyName(company);
        filter.setContactName(contact);
        return filter;
    }

    private void loadAddressDefault() throws Exception {
        // Check login
        WebshipLoginVo loginVo = this.getWebshipLoginInfo();
        if (loginVo == null) {
            throw new Exception(this.getLocalizationText("You don't have permission to do this action"));
        }
        // Load customer default settings
        IUserSettingsService settingsService = new UserSettingsServiceImp();
        CustomerDefaultSettingDetailVo settingVo = settingsService.selectDetailByCustomerCode(loginVo.getCustomerCode());
        // Create from & to default address
        buildAddressFromCustomerDefaultSetting(settingVo);
    }

    private void buildAddressFromCustomerDefaultSetting(CustomerDefaultSettingDetailVo settingVo) throws Exception {
        AddressBookDetailVo fromAddress = new AddressBookDetailVo();
        AddressBookDetailVo toAddress = new AddressBookDetailVo();
        if (settingVo != null) {
            fromAddress.setAddressId(settingVo.getDefaultFromAddressId());
            fromAddress.setCompanyName(settingVo.getFromCompanyName());
            fromAddress.setPhone(settingVo.getFromPhone());
            fromAddress.setContactName(settingVo.getFromContactName());
            fromAddress.setEmail(settingVo.getFromEmail());
            fromAddress.setCountry(settingVo.getFromCountry());
            fromAddress.setAddress1(settingVo.getFromAddress1());
            fromAddress.setAddress2(settingVo.getFromAddress2());
            fromAddress.setCity(settingVo.getFromCity());
            fromAddress.setPostalCode(settingVo.getFromPostalCode());
            fromAddress.setState(settingVo.getFromState());
            fromAddress.setIsResidential(settingVo.getFromIsresidential());
            fromAddress.setCountryName(settingVo.getFromCountryName());
            toAddress.setAddressId(settingVo.getDefaultToAddressId());
            toAddress.setCompanyName(settingVo.getToCompanyName());
            toAddress.setPhone(settingVo.getToPhone());
            toAddress.setContactName(settingVo.getToContactName());
            toAddress.setEmail(settingVo.getToEmail());
            toAddress.setCountry(settingVo.getToCountry());
            toAddress.setAddress1(settingVo.getToAddress1());
            toAddress.setAddress2(settingVo.getToAddress2());
            toAddress.setCity(settingVo.getToCity());
            toAddress.setPostalCode(settingVo.getToPostalCode());
            toAddress.setState(settingVo.getToState());
            toAddress.setIsResidential(settingVo.getToIsresidential());
            toAddress.setCountryName(settingVo.getToCountryName());
        } else {
            // Get customer code.
            WebshipLoginVo loginVo = this.getWebshipLoginInfo();
            String customerCode = String.valueOf(loginVo.getCustomerCode());
            // Get customer address.
            CustomerAddressDao addressDao = new CustomerAddressDao();
            CustomerAddressVo customerAddressVo = addressDao.selectByCode(customerCode);
            // Set default from address.
            fromAddress = new AddressBookDetailVo();
            fromAddress.setAddressId(0L);
            fromAddress.setCompanyName(customerAddressVo.getCustomerName());
            fromAddress.setPhone(customerAddressVo.getPhone());
            fromAddress.setContactName(customerAddressVo.getContactName());
            fromAddress.setEmail(customerAddressVo.getEmail());
            fromAddress.setCountry(customerAddressVo.getCountry());
            fromAddress.setAddress1(customerAddressVo.getAddress1());
            fromAddress.setAddress2(customerAddressVo.getAddress2());
            fromAddress.setCity(customerAddressVo.getCity());
            fromAddress.setPostalCode(customerAddressVo.getPostalCode());
            fromAddress.setState(customerAddressVo.getStateCode());
            fromAddress.setIsResidential(0);
            // Get country name.
            CountryDao countryDao = new CountryDao();
            CountryVo countryVo = countryDao.getCountryById(customerAddressVo.getCountry());
            fromAddress.setCountryName(countryVo.getCountryName());
            toAddress = new AddressBookDetailVo();
            toAddress.setAddressId(0L);
        }
        this.setFrom(ModelUtils.createModelFromVo(fromAddress, AddressBookDetailModel.class));
        this.setTo(ModelUtils.createModelFromVo(toAddress, AddressBookDetailModel.class));
    }

    public AddressBookDetailModel getFrom() {
        return from;
    }

    public void setFrom(AddressBookDetailModel from) {
        this.from = from;
    }

    public AddressBookDetailModel getTo() {
        return to;
    }

    public void setTo(AddressBookDetailModel to) {
        this.to = to;
    }

    public List<AddressBookDetailModel> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<AddressBookDetailModel> addressList) {
        this.addressList = addressList;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getIsFrom() {
        return isFrom;
    }

    public void setIsFrom(String isFrom) {
        this.isFrom = isFrom;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public AddressBookDetailModel getAddress() {
        return address;
    }

    public void setAddress(AddressBookDetailModel address) {
        this.address = address;
    }

    public String getFromWebship() {
        return fromWebship;
    }

    public void setFromWebship(String fromWebship) {
        this.fromWebship = fromWebship;
    }

    public List<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public String getIsSender() {
        return isSender;
    }

    public void setIsSender(String isSender) {
        this.isSender = isSender;
    }
}