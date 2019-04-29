package com.gms.xms.workflow.service.webship.addressbook;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.model.webship.addressbook.AddressRowDataModel;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.webship.addressbook.CustomerAddressBookDao;
import com.gms.xms.txndb.vo.webship.CustomerAddressBookVo;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Map;

/**
 * Posted from Aug 27, 2016 12:42:41 PM
 * <p>
 * Author huynd
 */
public class WebshipAddressBookImportServiceImp implements IWebshipAddressBookImportService {
    private static final Log logger = LogFactory.getLog(WebshipAddressBookImportServiceImp.class);

    @Override
    public Integer importAddressBook(Map<String, String> context, AddressRowDataModel rowData, Long customerCode, Long webshipId) throws Exception {
        SqlSessionClient sessionClient = new SqlSessionClient();
        sessionClient.startTransaction();
        try {
            Map<String, String> cellData = rowData.getCellData();
            CustomerAddressBookVo customerAddressBookVo = new CustomerAddressBookVo();
            customerAddressBookVo.setWebshipId(webshipId);
            customerAddressBookVo.setCustomerCode(customerCode);
            customerAddressBookVo.setContactName(StringUtils.isBlank(cellData.get("contact_name")) ? "" : cellData.get("contact_name"));
            customerAddressBookVo.setCompanyName(StringUtils.isBlank(cellData.get("company_name")) ? "" : cellData.get("company_name"));
            customerAddressBookVo.setAddress1(StringUtils.isBlank(cellData.get("address1")) ? "" : cellData.get("address1"));
            customerAddressBookVo.setAddress2(StringUtils.isBlank(cellData.get("address2")) ? "" : cellData.get("address2"));
            customerAddressBookVo.setCity(StringUtils.isBlank(cellData.get("city")) ? "" : cellData.get("city"));
            customerAddressBookVo.setState(StringUtils.isBlank(cellData.get("state")) ? "" : cellData.get("state"));
            customerAddressBookVo.setPostalCode(StringUtils.isBlank(cellData.get("postal_code")) ? "" : cellData.get("postal_code"));
            customerAddressBookVo.setCountry(StringUtils.isBlank(cellData.get("country")) ? 0L : Long.valueOf(cellData.get("country")));
            customerAddressBookVo.setPhone(StringUtils.isBlank(cellData.get("phone")) ? "" : cellData.get("phone"));
            customerAddressBookVo.setEmail(StringUtils.isBlank(cellData.get("email")) ? "" : cellData.get("email"));
            customerAddressBookVo.setDepartment(StringUtils.isBlank(cellData.get("department")) ? "" : cellData.get("department"));
            customerAddressBookVo.setDefaultPackageType(0);
            customerAddressBookVo.setDefaultServiceType(0);
            customerAddressBookVo.setDefaultBillingType(0);
            customerAddressBookVo.setIsResidential(0);
            CustomerAddressBookDao customerAddressBookDao = new CustomerAddressBookDao(sessionClient);
            Long checkDuplicate = customerAddressBookDao.checkDuplicateCustomerAddress(customerAddressBookVo);
            if (checkDuplicate > 0) {
                return -1;
            }
            // Put signal to the context to log insert customer address book as import.
            context.put(Attributes.ADD_INFO_EXT_LOG_ACTION_TYPE, "Import");
            customerAddressBookDao.insertCustomerAddressBook(context, customerAddressBookVo);
            // Commit transaction
            sessionClient.endTransaction();
        } catch (Exception e) {
            // Log database action error
            logger.error(e);
            // Roll back transaction
            sessionClient.rollback();
            throw e;
        }
        return 1;
    }

}
