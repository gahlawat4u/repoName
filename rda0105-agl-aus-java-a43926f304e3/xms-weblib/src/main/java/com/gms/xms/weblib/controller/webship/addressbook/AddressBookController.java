package com.gms.xms.weblib.controller.webship.addressbook;

import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.Paging;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.BillingTypeModel;
import com.gms.xms.model.webship.CustomerAddressBookModel;
import com.gms.xms.model.webship.PackageModel;
import com.gms.xms.model.webship.ShipmentTypeModel;
import com.gms.xms.model.webship.addressbook.AddressBookModel;
import com.gms.xms.persistence.dao.PackageDao;
import com.gms.xms.persistence.service.billingtype.BillingTypeServiceImp;
import com.gms.xms.persistence.service.billingtype.IBillingTypeService;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.persistence.service.shipmenttype.IShipmentTypeService;
import com.gms.xms.persistence.service.shipmenttype.ShipmentTypeServiceImp;
import com.gms.xms.persistence.service.webship.addressbook.AddressBookServiceImp;
import com.gms.xms.persistence.service.webship.addressbook.IAddressBookService;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.webship.BillingTypeVo;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;
import com.gms.xms.txndb.vo.webship.PackageVo;
import com.gms.xms.txndb.vo.webship.ShipmentTypeVo;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookFilter;
import com.gms.xms.txndb.vo.webship.addressbook.AddressBookVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

/**
 * Posted from AddressBookController
 * <p>
 * Author HungNT Date Jul 7, 2015
 */
public class AddressBookController extends JsonBaseController {
    private static final long serialVersionUID = -808331333517752616L;
    private List<CountryModel> countryList;
    private List<ShipmentTypeModel> shipmentTypeList;
    private List<PackageModel> packageList;
    private List<BillingTypeModel> billingTypeList;

    private Paging<AddressBookModel> addressBookList = new Paging<AddressBookModel>();
    private CustomerAddressBookModel addressBookModel;
    private AddressBookFilter searchFilter;
    private InputStream stream;
    private String fileName;

    private String addressId;
    private String pageSize = "20";
    private String page = "1";

    public String show() {
        this.setPageTitle("Customer Address Book");
        this.setBreadCrumb("Address Book");
        try {
            this.buildCountryList();
            IAddressBookService service = new AddressBookServiceImp();
            List<AddressBookVo> addressBookVos = service.getCustomerAddressBookByFilter(this.buildFilter());
            List<AddressBookModel> addressBookModels = ModelUtils.createListModelFromVo(addressBookVos, AddressBookModel.class);
            this.addressBookList.setRecords(addressBookModels);
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
        }

        return "success";
    }

    public String getData() {
        try {
            IAddressBookService service = new AddressBookServiceImp();
            List<AddressBookVo> addressBookVos = service.getCustomerAddressBookByFilter(this.buildFilter());
            List<AddressBookModel> addressBookModels = ModelUtils.createListModelFromVo(addressBookVos, AddressBookModel.class);
            this.addressBookList.setRecords(addressBookModels);
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            return "error";
        }
        return "success";
    }

    public String edit() {
        this.setPageTitle("Edit customer address book");
        IAddressBookService addressBookService = new AddressBookServiceImp();
        try {
            this.prepareData();
            if (this.addressBookModel != null) {
                if (!this.validateInputs()) {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return "success";
                }
                CustomerAddressBookVo addressBookVo = ModelUtils.createVoFromModel(addressBookModel, CustomerAddressBookVo.class);
                addressBookService.updateCustomerAddressBook(this.getAddInfoMap(), addressBookVo);
                this.addActionMessage("Updated successful.");
                return "saved";
            } else {
                if (!StringUtils.isBlank(this.addressId)) {
                    Long addressId = Long.parseLong(this.addressId);
                    CustomerAddressBookVo addressBookVo = addressBookService.getCustomerAddressBookById(addressId);
                    CustomerAddressBookModel addressBookModel = ModelUtils.createModelFromVo(addressBookVo, CustomerAddressBookModel.class);
                    this.addressBookModel = addressBookModel;
                }
            }
        } catch (Exception e) {
            log.error(e);
            this.addActionError(e.getMessage());
        }
        return "success";
    }

    public String add() {
        this.setPageTitle("Add a customer address book");
        IAddressBookService addressBookService = new AddressBookServiceImp();
        try {
            this.prepareData();
            if (this.addressBookModel != null) {
                if (!this.validateInputs()) {
                    this.setErrorCode(ErrorCode.FIELD_ERROR);
                    return "success";
                }
                CustomerAddressBookVo addressBookVo = ModelUtils.createVoFromModel(this.addressBookModel, CustomerAddressBookVo.class);
                addressBookVo.setWebshipId(this.getWebshipLoginInfo().getWebshipId());
                addressBookVo.setCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
                addressBookService.insertCustomerAddressBook(this.getAddInfoMap(), addressBookVo);
                this.addActionMessage("Saved successful.");
                return "saved";
            }
        } catch (Exception e) {
            log.error(e);
            this.addActionError(e.getMessage());
        }
        return "success";
    }

    public String delete() {
        IAddressBookService service = new AddressBookServiceImp();
        try {
            if (!StringUtils.isBlank(this.addressId)) {
                service.deleteCustomerAddressBookByIdTask(this.getAddInfoMap(), Long.parseLong(this.addressId));
                List<AddressBookVo> addressBookVos = service.getCustomerAddressBook(this.buildFilter());
                List<AddressBookModel> addressBookList = ModelUtils.createListModelFromVo(addressBookVos, AddressBookModel.class);
                this.addressBookList.setRecords(addressBookList);
            }
        } catch (Exception e) {
            log.error(e);
            this.setErrorCode(ErrorCode.ACTION_ERROR);
            this.setErrorMessage(e.getMessage());
            return "error";
        }
        return "success";
    }

    public String export() {
        IAddressBookService service = new AddressBookServiceImp();
        try {
            AddressBookFilter addressBookFilter = new AddressBookFilter();
            addressBookFilter.setWebshipId(this.getWebshipLoginInfo().getWebshipId());
            addressBookFilter.setCustomerCode(this.getWebshipLoginInfo().getCustomerCode());
            this.fileName = "address_book_sheet_" + String.valueOf(this.getWebshipLoginInfo().getCustomerCode()) + ".xls";
            String outPutFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp/" + this.fileName;
            String createTemp = request.getSession().getServletContext().getRealPath("") + "tmp/";
            File file = new File(createTemp);
            if(!file.exists()){
            	file.mkdirs();
            }
            service.renderXLSFile(addressBookFilter, outPutFilePath);
            this.setStream(new FileInputStream(new File(outPutFilePath)));
            return "export";
        } catch (Exception e) {
            log.error(e);
        }
        return "success";
    }

    protected void prepareData() throws Exception {
        this.buildCountryList();
        this.buildShipmentTypeList();
        this.buildPackageList();
        this.buildBillingTypeList();
    }

    protected void buildCountryList() throws Exception {
        ICountryService service = new CountryServiceImp();
        List<CountryVo> countryVos = service.getCountryList();
        List<CountryModel> countryList = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        this.countryList = countryList;
    }

    protected void buildShipmentTypeList() throws Exception {
        IShipmentTypeService service = new ShipmentTypeServiceImp();
        List<ShipmentTypeVo> shipmentTypeVos = service.getShipmentTypeList();
        List<ShipmentTypeModel> shipmentTypeList = ModelUtils.createListModelFromVo(shipmentTypeVos, ShipmentTypeModel.class);
        this.shipmentTypeList = shipmentTypeList;
    }

    protected void buildPackageList() throws Exception {
        PackageDao packageDao = new PackageDao();
        List<PackageVo> packageVos = packageDao.getPackageListByCarrier(1);
        List<PackageModel> packageList = ModelUtils.createListModelFromVo(packageVos, PackageModel.class);
        this.packageList = packageList;
    }

    protected void buildBillingTypeList() throws Exception {
        IBillingTypeService service = new BillingTypeServiceImp();
        List<BillingTypeVo> billingTypeVos = service.getBillingTypeList();
        List<BillingTypeModel> billingTypeList = ModelUtils.createListModelFromVo(billingTypeVos, BillingTypeModel.class);
        this.billingTypeList = billingTypeList;
    }

    protected AddressBookFilter buildFilter() {
        IAddressBookService service = new AddressBookServiceImp();
        AddressBookFilter filter = new AddressBookFilter();
        if (this.searchFilter != null) {
            filter = this.searchFilter;
        }

        //init default page size instead of set on exception
        int pageSize = 20;
        int page = 1;
        long customerCode = 0;
        long recordCount = 0;
        try {
            pageSize = Integer.parseInt(this.pageSize);
            page = Integer.parseInt(this.page);
            customerCode = this.getWebshipLoginInfo().getCustomerCode();

            filter.setCustomerCode(customerCode);
            filter.setWebshipId(this.getWebshipLoginInfo().getWebshipId());
            recordCount = service.getCustomerAddressBookCountByFilter(filter);
        } catch (Exception ignored) {
            pageSize = 20;
            page = 1;
            recordCount = 0;
        }

        Paging<AddressBookModel> paging = new Paging<AddressBookModel>(page, 10, pageSize, recordCount);

        this.setAddressBookList(paging);

        filter.setRecordSize(paging.getPageSize());
        filter.setStartRecord((paging.getCurrentPage() - 1) * paging.getPageSize());
        return filter;
    }

    protected Boolean validateInputs() {
        if (StringUtils.isBlank(this.addressBookModel.getContactName())) {
            this.addFieldError("addressBookModel.contactName", "Contact Name cannot be empty.");
        }
        if (StringUtils.isBlank(this.addressBookModel.getAddress1())) {
            this.addFieldError("addressBookModel.address1", "Address cannot be empty.");
        }
        if (StringUtils.isBlank(this.addressBookModel.getCity())) {
            this.addFieldError("addressBookModel.city", "City cannot be empty.");
        }
        if (StringUtils.isBlank(this.addressBookModel.getPhone())) {
            this.addFieldError("addressBookModel.phone", "Phone cannot be empty.");
        }
        if (StringUtils.equalsIgnoreCase(this.addressBookModel.getCountry(), "0")) {
            this.addFieldError("addressBookModel.country", "Please select a country.");
        }

        if (!StringUtils.isBlank(this.addressBookModel.getEmail())) {
            if (!HelperUtils.isEmailAddress(this.addressBookModel.getEmail())) {
                this.addFieldError("addressBookModel.email", "Email is invalid.");
            }
        }

        if (StringUtils.isBlank(this.addressBookModel.getIsResidential())) {
            this.addressBookModel.setIsResidential("0");
        }

        if (this.hasFieldErrors()) {
            return false;
        }

        return true;
    }

    public List<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public Paging<AddressBookModel> getAddressBookList() {
        return addressBookList;
    }

    public void setAddressBookList(Paging<AddressBookModel> addressBookList) {
        this.addressBookList = addressBookList;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public CustomerAddressBookModel getAddressBookModel() {
        return addressBookModel;
    }

    public void setAddressBookModel(CustomerAddressBookModel addressBookModel) {
        this.addressBookModel = addressBookModel;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public List<ShipmentTypeModel> getShipmentTypeList() {
        return shipmentTypeList;
    }

    public void setShipmentTypeList(List<ShipmentTypeModel> shipmentTypeList) {
        this.shipmentTypeList = shipmentTypeList;
    }

    public List<PackageModel> getPackageList() {
        return packageList;
    }

    public void setPackageList(List<PackageModel> packageList) {
        this.packageList = packageList;
    }

    public List<BillingTypeModel> getBillingTypeList() {
        return billingTypeList;
    }

    public void setBillingTypeList(List<BillingTypeModel> billingTypeList) {
        this.billingTypeList = billingTypeList;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public AddressBookFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(AddressBookFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}