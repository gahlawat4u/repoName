package com.gms.xms.weblib.controller.account.contact;

import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.filter.account.contact.ManageContactFilter;
import com.gms.xms.model.Paging;
import com.gms.xms.model.account.contact.ManageContactModel;
import com.gms.xms.model.account.contact.ProspectStatusModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.UserDao;
import com.gms.xms.persistence.dao.contact.ManageContactDao;
import com.gms.xms.txndb.vo.UserVo;
import com.gms.xms.txndb.vo.account.contact.ManageContactVo;
import com.gms.xms.weblib.controller.AdminJsonBaseController;
import com.gms.xms.workflow.render.managecontact.IManageContactRender;
import com.gms.xms.workflow.render.managecontact.ManageContactRenderImp;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ManageContactController extends AdminJsonBaseController {

    private static final long serialVersionUID = 1537042182659846561L;

    private String companyName;
    private String contactName;
    private String address1;
    private String address2;
    private String phone;
    private String email;
    private String postalCode;
    private String saleStage;

    private String page;
    private String pageSize;
    private String orderType;
    private String orderField;

    private Paging<ManageContactModel> contacts;
    private List<ProspectStatusModel> statusList;

    private String fileName;
    private InputStream stream;
    private String htmlExportString;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public InputStream getStream() {
        return stream;
    }

    public void setStream(InputStream stream) {
        this.stream = stream;
    }

    public String getHtmlExportString() {
        return htmlExportString;
    }

    public void setHtmlExportString(String htmlExportString) {
        this.htmlExportString = htmlExportString;
    }

    public String show() {
        try {
            loadStatusList();
            loadContacts();
            preparePageSizes();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String search() {
        try {
            loadContacts();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected void loadStatusList() {
        List<ProspectStatusModel> list = new ArrayList<ProspectStatusModel>();
        list.add(new ProspectStatusModel("", ""));
        list.add(new ProspectStatusModel("prospect", "Prospect"));
        list.add(new ProspectStatusModel("qualified", "Qualified"));
        list.add(new ProspectStatusModel("opportunity", "Opportunity"));
        list.add(new ProspectStatusModel("rejected_qualified", "Rejected Qualified"));
        list.add(new ProspectStatusModel("set_appointment", "Set Scheduled Visit"));
        list.add(new ProspectStatusModel("visit", "Visit"));
        list.add(new ProspectStatusModel("proposal", "Proposal"));
        list.add(new ProspectStatusModel("success", "Success"));
        list.add(new ProspectStatusModel("shipping", "Shipping"));
        list.add(new ProspectStatusModel("rejected_opportunity", "Rejected Opportunity"));
        list.add(new ProspectStatusModel("renewal", "Renewal"));
        this.setStatusList(list);
    }

    protected void loadContacts() throws Exception {
        // Get filter.
        ManageContactFilter filter = this.buildFilter();// new
        // ManageContactFilter();
        // Get the setting number links on the page.
        Integer nLinks = Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultLinksOnPage());
        // Get record count.
        ManageContactDao dao = new ManageContactDao();
        long recordCount = dao.countManageContactByFilter(filter);
        // Build paging object.
        Paging<ManageContactModel> paging = new Paging<ManageContactModel>(filter.getPage(), nLinks, filter.getPageSize(), recordCount);
        filter.setPage(paging.getCurrentPage());
        // Get list of records of cost deviation report.
        List<ManageContactVo> manageContactVos = dao.getManageContactByFilter(filter);
        List<ManageContactModel> manageContactModel = ModelUtils.createListModelFromVo(manageContactVos, ManageContactModel.class);
        paging.setRecords(manageContactModel);
        this.setContacts(paging);
    }

    public String doExportXls() {
        try {
            ManageContactFilter filter = this.buildFilter();
            filter.setPage(null);
            filter.setPageSize(null);
            IManageContactRender render = new ManageContactRenderImp(this.getAddInfoMap());
            String outputFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "contact_list_" + AppUtils.createMessageReference() + ".xls";
            render.generateXlsFile(filter, outputFilePath);
            this.setFileName("contact_list.xls");
            this.setStream(new FileInputStream(new File(outputFilePath)));
            response.setHeader("Set-Cookie", "fileDownload=true; path=/");
            return "export";
        } catch (Exception e) {
            this.handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    protected ManageContactFilter buildFilter() throws Exception {
        ManageContactDao dao = new ManageContactDao();
        String userId = "1";
        UserDao userDao = new UserDao();
        UserVo userVo = userDao.getUserById(userId);
        List<Long> prospectIdList = dao.getProspectByLogin(userVo);
        String result = "";
        for (Long prospect : prospectIdList) {
            result += String.valueOf(prospect) + ",";
        }
        result = result.substring(0, result.length() - 1);
        ManageContactFilter filter = new ManageContactFilter();
        filter.setProspectList(result);
        // Set Company Name
        filter.setCompanyName(this.getCompanyName());
        // Set Contact Name
        filter.setContactName(this.getContactName());
        // Set Address 1
        filter.setAddress1(this.getAddress1());
        // Set Address 2
        filter.setAddress2(this.getAddress2());
        // Set Phone
        filter.setPhone(this.getPhone());
        // Set Email
        filter.setEmail(this.getEmail());
        // Set Postal Code
        filter.setPostalCode(this.getPostalCode());
        // Set Sales Stage
        filter.setSaleStage(this.getSaleStage());
        // Set page.
        Integer page = null;
        try {
            page = StringUtils.isBlank(this.getPage()) ? 1 : Integer.valueOf(this.getPage());
            if (page <= 0) {
                throw new CustomException("The page number cannot be less than 0.");
            }
            filter.setPage(page);
        } catch (Exception e) {
            throw new CustomException("Invalid page number.");
        }
        // Set page size.
        Integer pageSize = null;
        try {
            pageSize = StringUtils.isBlank(this.getPageSize()) ? Integer.valueOf(AppConstants.APP_SETTINGS.getDefaultPageSize()) : Integer.valueOf(this.getPageSize());
            if (pageSize <= 0) {
                throw new CustomException("The page size cannot be less than 0.");
            }
            filter.setPageSize(pageSize);
        } catch (Exception e) {
            throw new CustomException("Invalid page size number.");
        }
        // Set order type.
        Integer order = null;
        try {
            order = StringUtils.isBlank(this.getOrderType()) ? 0 : Integer.valueOf(this.getOrderType());
            if (order != 0 && order != 1) {
                throw new CustomException("The order type cannot be other value exception (0: ascending, 1: descending)");
            }
            filter.setOrderType(order);
        } catch (Exception e) {
            throw new CustomException("Invalid order type value.");
        }
        // Set order field.
        filter.setOrderField(StringUtils.isBlank(this.getOrderField()) ? "prospectid" : this.getOrderField());
        return filter;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getSaleStage() {
        return saleStage;
    }

    public void setSaleStage(String saleStage) {
        this.saleStage = saleStage;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public Paging<ManageContactModel> getContacts() {
        return contacts;
    }

    public void setContacts(Paging<ManageContactModel> contacts) {
        this.contacts = contacts;
    }

    public List<ProspectStatusModel> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<ProspectStatusModel> statusList) {
        this.statusList = statusList;
    }
}
