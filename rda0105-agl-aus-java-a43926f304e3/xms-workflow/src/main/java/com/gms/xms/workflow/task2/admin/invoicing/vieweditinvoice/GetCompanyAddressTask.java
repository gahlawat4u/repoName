package com.gms.xms.workflow.task2.admin.invoicing.vieweditinvoice;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.workflow.core2.Task2;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetCompanyAddressTask
 * <p>
 * Author dattrinh Mar 4, 2016 10:59:51 AM
 */
public class GetCompanyAddressTask implements Task2 {
    private static final Log log = LogFactory.getLog(GetCompanyAddressTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
            // Get company address from the system setting.
            String companyAddress = SystemSettingCache.getInstance().getValueByKey("System Address for Tax Invoice");
            companyAddress = StringUtils.replace(companyAddress, "\n", "<br/>");
            // Get Franchise Customer Billing Address.
            // Get InvoiceInfoVo.
            InvoiceInfoVo invoiceInfoVo = context.get(Attributes.INVOICE_VO);
            String customerCode = invoiceInfoVo.getCustomerCode().length() < 3 ? invoiceInfoVo.getCustomerCode() : invoiceInfoVo.getCustomerCode().substring(0, 3);
            customerCode += "00001";
            CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
            CustomerBillingAddressVo customerBillingAddressVo = billingAddressDao.getByCustomerCode(customerCode);
            // Replace [PHONE_NUMBER] by phone number of the franchise billing
            // address.
            companyAddress = StringUtils.replace(companyAddress, "[PHONE_NUMBER]", customerBillingAddressVo == null ? "" : customerBillingAddressVo.getBillingPhone());
            context.put(Attributes.COMPANY_ADDRESS, companyAddress);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}
