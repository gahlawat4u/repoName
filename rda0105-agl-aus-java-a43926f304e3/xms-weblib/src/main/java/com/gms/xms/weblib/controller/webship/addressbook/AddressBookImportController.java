package com.gms.xms.weblib.controller.webship.addressbook;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.CountryModel;
import com.gms.xms.model.StateModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.addressbook.AddressBookModel;
import com.gms.xms.model.webship.addressbook.AddressDataModel;
import com.gms.xms.model.webship.addressbook.AddressRowDataModel;
import com.gms.xms.persistence.dao.StateDao;
import com.gms.xms.persistence.service.country.CountryServiceImp;
import com.gms.xms.persistence.service.country.ICountryService;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.StateVo;
import com.gms.xms.txndb.vo.webship.login.WebshipLoginVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.gms.xms.weblib.utils.HelperUtils;
import com.gms.xms.workflow.core2.WorkFlowManager2;
import com.gms.xms.workflow.service.webship.addressbook.IWebshipAddressBookImportService;
import com.gms.xms.workflow.service.webship.addressbook.WebshipAddressBookImportServiceImp;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.*;

/**
 * Posted from AddressBookImportController
 * <p>
 * Author HungNT Date Jul 14, 2015
 */
public class AddressBookImportController extends JsonBaseController {
    private static final long serialVersionUID = 4969513253548394588L;

    // Import address book
    private List<AddressBookModel> addressBookModels;
    private File fileUpload;
    private String fileUploadFileName;
    private String addressDataStr;
    private String addressFilePath;
    private String mapStateCountryDataStr;

    private Integer totalColumns;
    private Map<String, String> importFields;
    private List<AddressRowDataModel> rowData;

    private Boolean isMapped = false;
    private Boolean hasHeader = false;
    private List<String> selectedImportFields;
    private List<CountryModel> countryList;

    private String countryId;
    private String index;
    private List<StateModel> stateList;
    private String state;

    private Integer countSuccess;
    private Integer countUnSuccess;

    public String show() {
        this.setPageTitle("Import Customer Address Book");
        try {
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String uploadAddress() {
        try {
            // ImportAddressDataModel addressData = this.getImportAddressData();
            String addressFilePath = request.getSession().getServletContext().getRealPath("") + "/tmp";
            // Get extension
            String addressFileName = this.getFileUploadFileName();
            String[] addressFileNameArr = addressFileName.split("\\.");
            String ext = addressFileNameArr[addressFileNameArr.length - 1];
            String outputFilePath = "/" + "address_book_" + AppUtils.createMessageReference() + "." + ext;
            File addressFile = new File(addressFilePath, outputFilePath);
            // Upload file
            FileUtils.copyFile(this.getFileUpload(), addressFile);
            this.setAddressFilePath(addressFilePath + outputFilePath);
            // Read file
            String filePath = addressFilePath + outputFilePath;
            ContextBase2 context = new ContextBase2(this.getAddInfoMap());
            context.put(Attributes.EXTENSION, filePath.substring(filePath.length() - 3, filePath.length()));
            context.put(Attributes.ADDRESS_BOOK_FILE, addressFilePath + outputFilePath);
            context.put(Attributes.WFP_NAME, "Wfl-ReadImportAddressBook");
            context = WorkFlowManager2.getInstance().process(context);
            if (context.getString(Attributes.ERROR_CODE).equalsIgnoreCase(ErrorCode.SUCCESS)) {
                AddressDataModel dataSheet = context.get(Attributes.ADDRESS_BOOK_DATA_FILE);
                this.setTotalColumns(dataSheet.getRowData().get(0).getCellData().size());
                this.setRowData(dataSheet.getRowData());
                this.setAddressDataStr(GsonUtils.toGson(dataSheet));
            }
            this.buildListImportFields();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private AddressDataModel getImportAddressData() {
        AddressDataModel addressData = GsonUtils.fromGson(this.getAddressDataStr(), new TypeToken<AddressDataModel>() {
        }.getType());
        return addressData;
    }

    protected Boolean validateImportFields() {
        this.buildListImportFields();
        // Check selected import fields for each column
        List<String> fields = this.getSelectedImportFields();
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            if (field.equalsIgnoreCase("0")) {
                this.setErrorMessage(this.getLocalizationText("Please select a field mapping for each column."));
                return false;
            }
        }
        // Check if has a duplicate field in the selected import fields
        Set<String> duplicateFields = HelperUtils.getDuplicateField(fields);
        if (duplicateFields.size() > 0) {
            for (String field : duplicateFields) {
                this.setErrorMessage("Having duplications In '" + this.getImportFields().get(field) + "'");
                return false;
            }
        }
        List<String> requiredFields = getRequiredFields();
        for (String field : requiredFields) {
            if (!fields.contains(field)) {
                this.setErrorMessage("Please select '" + this.getImportFields().get(field) + "'");
                return false;
            }
        }
        return true;
    }

    protected void buildListImportFields() {
        Map<String, String> listImportFields = new LinkedHashMap<String, String>();
        listImportFields.put("1", this.getLocalizationText("Ignore"));
        listImportFields.put("contact_name", this.getLocalizationText("Contact"));
        listImportFields.put("company_name", this.getLocalizationText("Company"));
        listImportFields.put("address1", this.getLocalizationText("Address 1"));
        listImportFields.put("address2", this.getLocalizationText("Address 2"));
        listImportFields.put("city", this.getLocalizationText("City"));
        listImportFields.put("state", this.getLocalizationText("State"));
        listImportFields.put("postal_code", this.getLocalizationText("Postal Code"));
        listImportFields.put("country", this.getLocalizationText("Country"));
        listImportFields.put("phone", this.getLocalizationText("Phone"));
        listImportFields.put("email", this.getLocalizationText("Email"));
        listImportFields.put("department", this.getLocalizationText("Department"));
        this.setImportFields(listImportFields);
    }

    public String showMapping() {
        try {
            this.buildCountryList();
            this.buildListImportFields();
            List<CountryModel> countryList = this.countryList;
            if (!this.validateMapStateCountry()) {
                this.setErrorCode(ErrorCode.FIELD_ERROR);
                return "field_error";
            }
            AddressDataModel addressData = this.getImportAddressData();
            if (addressData != null) {
                List<AddressBookModel> addressBookModels = new LinkedList<AddressBookModel>();
                AddressBookModel addressBookModel;
                Map<String, String> cellData;
                List<AddressRowDataModel> rowData = buildListAddressData(addressData.getRowData(), this.selectedImportFields, this.hasHeader);
                StateDao stateDao = new StateDao();
                List<StateVo> stateVos;
                List<StateModel> stateList = null;
                for (AddressRowDataModel row : rowData) {
                    // Do mapping
                    addressBookModel = new AddressBookModel();
                    cellData = new LinkedHashMap<String, String>();
                    cellData = row.getCellData();
                    for (CountryModel country : countryList) {
                        if (country.getCountryName().equalsIgnoreCase(cellData.get("country"))) {
                            addressBookModel.setCountryId(country.getCountryId());
                            addressBookModel.setCountryName(country.getCountryName());
                            addressBookModel.setState(cellData.get("state"));
                            if (!"16".equalsIgnoreCase(country.getCountryId()) && !"37".equalsIgnoreCase(country.getCountryId())) {
                                // Except Australia & Canada
                                stateVos = stateDao.selectStateByCountryId(Long.valueOf(country.getCountryId()));
                                stateList = ModelUtils.createListModelFromVo(stateVos, StateModel.class);
                            }
                            addressBookModel.setStateList(stateList);
                        }
                    }
                    addressBookModels.add(addressBookModel);
                }
                this.setAddressBookModels(addressBookModels);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doMapping() {
        try {
            this.setMapStateCountryDataStr(GsonUtils.toGson(this.getAddressBookModels()).trim());
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    private List<AddressRowDataModel> buildListAddressData(List<AddressRowDataModel> addressRowData, List<String> selectedImportFields, Boolean hasHeader) {
        int count = 0;
        String key = "", value = "";
        Boolean emptyRow;
        AddressRowDataModel addressRow;
        List<AddressRowDataModel> listAddressData = new LinkedList<AddressRowDataModel>();
        for (AddressRowDataModel rowData : addressRowData) {
            if (hasHeader != null && hasHeader && count == 0) {
                count++;
                continue;
            }
            emptyRow = true;
            Map<String, String> cellData = rowData.getCellData();
            addressRow = new AddressRowDataModel();
            addressRow.setCellData(new LinkedHashMap<String, String>());
            for (int i = 0; i < cellData.size(); i++) {
                key = selectedImportFields.get(i);
                if (key.equalsIgnoreCase("1")) {
                    continue;
                }
                value = (cellData.get(String.valueOf(i)) == null) ? "" : cellData.get(String.valueOf(i)).trim();
                if (!StringUtils.isBlank(value)) {
                    emptyRow = false;
                }
                addressRow.getCellData().put(key, value);
            }
            if (!emptyRow) {
                listAddressData.add(addressRow);
            }
            count++;
        }
        return listAddressData;
    }

    private Boolean validateMapStateCountry() {
        Map<String, String> selectList = this.getImportFields();
        // Check selected import fields for each column
        List<String> fields = this.selectedImportFields;
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            if (field.equalsIgnoreCase("0")) {
                this.addFieldError("importFields", "Please select a field mapping for each column.");
                return false;
            }
        }
        // Check if has a duplicate field in the selected import fields
        Set<String> duplicateFields = HelperUtils.getDuplicateField(fields);
        if (duplicateFields.size() > 0) {
            for (String field : duplicateFields) {
                this.addFieldError("importFields", "Having duplications In '" + selectList.get(field) + "'");
                return false;
            }
        }
        List<String> requiredFields = getRequiredFields();
        for (String field : requiredFields) {
            if (!fields.contains(field)) {
                this.addFieldError("importFields", "Please select '" + selectList.get(field) + "'");
                return false;
            }
        }
        return true;
    }

    private List<String> getRequiredFields() {
        List<String> requiredFields = new LinkedList<>();
        requiredFields.add("contact_name");
        requiredFields.add("address1");
        requiredFields.add("city");
        requiredFields.add("country");
        requiredFields.add("phone");
        requiredFields.add("state");
        return requiredFields;
    }

    private void buildCountryList() throws Exception {
        ICountryService service = new CountryServiceImp();
        List<CountryVo> countryVos = service.getCountryList();
        List<CountryModel> countryModels = ModelUtils.createListModelFromVo(countryVos, CountryModel.class);
        this.countryList = countryModels;
    }

    public String changeCountry() {
        try {
            if (!"16".equalsIgnoreCase(this.getCountryId()) && !"37".equalsIgnoreCase(this.getCountryId())) {
                StateDao stateDao = new StateDao();
                // Except Australia & Canada
                List<StateVo> stateVos = stateDao.selectStateByCountryId(Long.valueOf(this.getCountryId()));
                List<StateModel> stateList = ModelUtils.createListModelFromVo(stateVos, StateModel.class);
                this.setStateList(stateList);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String doImport() {
        try {
            AddressDataModel addressData = this.getImportAddressData();
            if (addressData != null) {
                WebshipLoginVo loggedWebshipVo = (WebshipLoginVo) this.getSession(Attributes.SESS_XMS_WEBSHIP_LOGIN_INFO);
                Long customerCode = loggedWebshipVo.getCustomerCode();
                Long webshipId = loggedWebshipVo.getWebshipId();
                int countSuccess = 0, countUnSuccess = 0;
                Integer result = 0;
                List<AddressRowDataModel> addressRowData = buildListAddressData(addressData.getRowData(), this.selectedImportFields, this.hasHeader);
                List<AddressBookModel> mapStateCountryData = GsonUtils.fromGson(this.getMapStateCountryDataStr(), new TypeToken<List<AddressBookModel>>() {
                }.getType());
                IWebshipAddressBookImportService service = new WebshipAddressBookImportServiceImp();
                AddressRowDataModel rowData;
                AddressBookModel map;
                Map<String, String> cellData;
                for (int i = 0; i < addressRowData.size(); i++) {
                    map = mapStateCountryData.get(i);
                    rowData = addressRowData.get(i);
                    cellData = rowData.getCellData();
                    cellData.put("state", map.getState().split(",")[Integer.valueOf(Attributes.NEW_IMPORTED_STATE)].trim());
                    cellData.put("country", map.getCountryId());
                    rowData.setCellData(cellData);
                    result = service.importAddressBook(this.getAddInfoMap(), rowData, customerCode, webshipId);
                    if (result == 1) {
                        countSuccess++;
                    } else {
                        countUnSuccess++;
                    }
                }
                this.setCountSuccess(countSuccess);
                this.setCountUnSuccess(countUnSuccess);
            }
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public Boolean getIsMapped() {
        return isMapped;
    }

    public void setIsMapped(Boolean isMapped) {
        this.isMapped = isMapped;
    }

    public List<String> getSelectedImportFields() {
        return selectedImportFields;
    }

    public void setSelectedImportFields(List<String> selectedImportFields) {
        this.selectedImportFields = selectedImportFields;
    }

    public List<AddressBookModel> getAddressBookModels() {
        return addressBookModels;
    }

    public void setAddressBookModels(List<AddressBookModel> addressBookModels) {
        this.addressBookModels = addressBookModels;
    }

    public Boolean getHasHeader() {
        return hasHeader;
    }

    public void setHasHeader(Boolean hasHeader) {
        this.hasHeader = hasHeader;
    }

    public List<CountryModel> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<CountryModel> countryList) {
        this.countryList = countryList;
    }

    public String getAddressDataStr() {
        return addressDataStr;
    }

    public void setAddressDataStr(String addressDataStr) {
        this.addressDataStr = addressDataStr;
    }

    public String getAddressFilePath() {
        return addressFilePath;
    }

    public void setAddressFilePath(String addressFilePath) {
        this.addressFilePath = addressFilePath;
    }

    public Integer getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(Integer totalColumns) {
        this.totalColumns = totalColumns;
    }

    public Map<String, String> getImportFields() {
        return importFields;
    }

    public void setImportFields(Map<String, String> importFields) {
        this.importFields = importFields;
    }

    public List<AddressRowDataModel> getRowData() {
        return rowData;
    }

    public void setRowData(List<AddressRowDataModel> rowData) {
        this.rowData = rowData;
    }

    public String getCountryId() {
        return countryId;
    }

    public void setCountryId(String countryId) {
        this.countryId = countryId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<StateModel> getStateList() {
        return stateList;
    }

    public void setStateList(List<StateModel> stateList) {
        this.stateList = stateList;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMapStateCountryDataStr() {
        return mapStateCountryDataStr;
    }

    public void setMapStateCountryDataStr(String mapStateCountryDataStr) {
        this.mapStateCountryDataStr = mapStateCountryDataStr;
    }

    public Integer getCountSuccess() {
        return countSuccess;
    }

    public void setCountSuccess(Integer countSuccess) {
        this.countSuccess = countSuccess;
    }

    public Integer getCountUnSuccess() {
        return countUnSuccess;
    }

    public void setCountUnSuccess(Integer countUnSuccess) {
        this.countUnSuccess = countUnSuccess;
    }

}
