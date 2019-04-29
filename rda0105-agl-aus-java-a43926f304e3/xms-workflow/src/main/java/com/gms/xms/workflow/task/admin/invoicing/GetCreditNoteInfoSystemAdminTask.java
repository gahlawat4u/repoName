package com.gms.xms.workflow.task.admin.invoicing;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.model.invoicing.CustomerBillingAddressModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.persistence.dao.CountryDao;
import com.gms.xms.persistence.dao.CustomerBillingAddressDao;
import com.gms.xms.txndb.vo.CountryVo;
import com.gms.xms.txndb.vo.CreditNoteInfoSystemAdminVo;
import com.gms.xms.txndb.vo.CreditNoteInfoVo;
import com.gms.xms.txndb.vo.CustomerBillingAddressVo;
import com.gms.xms.workflow.core.Task;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Posted from GetCreditNoteInfoSystemAdminTask
 * <p>
 * Author TanDT Date May 22, 2015
 */
public class GetCreditNoteInfoSystemAdminTask implements Task {
    private static final Log log = LogFactory.getLog(GetCreditNoteInfoSystemAdminTask.class);

    @Override
    public boolean execute(ContextBase context) throws Exception {
        context.put(Attributes.ERROR_CODE, ErrorCode.SUCCESS);
        try {
            String systemAddress = SystemSettingCache.getInstance().getValueByKey("System Address");
            String siteAddress = SystemSettingCache.getInstance().getValueByKey("Site Address");
            String defaultCountry = SystemSettingCache.getInstance().getValueByKey("Default Origin Country");
            String mailPaymentToAddress = SystemSettingCache.getInstance().getValueByKey("Mail Payment To Address");
            if (StringUtils.isNotBlank(defaultCountry) && defaultCountry.equalsIgnoreCase("70")) {
                CreditNoteInfoVo creditNoteInfoVo = GsonUtils.fromGson(context.get(Attributes.CREDIT_NOTES_INFO), CreditNoteInfoVo.class);
                String customerCode = String.valueOf(creditNoteInfoVo.getCustomerCode());
                String franchiseCode = customerCode.substring(0, 3) + "00001";
                CustomerBillingAddressDao billingAddressDao = new CustomerBillingAddressDao();
                CustomerBillingAddressVo billingAddressVo = billingAddressDao.selectBillingCustomerByCode(Long.parseLong(franchiseCode));
                CountryDao countryDao = new CountryDao();
                CountryVo countryVo = countryDao.getCountryById(billingAddressVo.getBillingCountry());
                CustomerBillingAddressModel billingAddressModel = ModelUtils.createModelFromVo(billingAddressVo, CustomerBillingAddressModel.class);
                mailPaymentToAddress = billingAddressModel.getBillingCustomerName().toUpperCase() + "<br/>" + billingAddressModel.getBillingContactName().toUpperCase() + "<br/>" + billingAddressModel.getBillingAddress1().toUpperCase() + "," + billingAddressModel.getBillingAddress2().toUpperCase() + "<br/>" + billingAddressModel.getBillingCity().toUpperCase() + "," + countryVo.getCountryName() + "," + billingAddressModel.getBillingPostalCode().toUpperCase();
                systemAddress = mailPaymentToAddress;
            }
            String detaulTaxPercent = SystemSettingCache.getInstance().getValueByKey("Tax % Amount");
            CreditNoteInfoSystemAdminVo infoSystemAdminVo = new CreditNoteInfoSystemAdminVo();
            infoSystemAdminVo.setDetaulTaxPercent(Float.parseFloat(detaulTaxPercent));
            infoSystemAdminVo.setMailPaymentToAddress(mailPaymentToAddress);
            infoSystemAdminVo.setSiteAddress(siteAddress);
            infoSystemAdminVo.setSystemAddress(systemAddress);
            context.put(Attributes.CREDIT_NOTE_SYSTEM_ADMIN, GsonUtils.toGson(infoSystemAdminVo));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            return false;
        }
        return true;
    }
}