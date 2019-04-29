package com.gms.xms.persistence.utils.invoicecode;

import com.gms.xms.persistence.dao.SystemSettingDao;

/**
 * Posted from Aug 8, 2016 10:45:33 AM
 * <p>
 * Author dattrinh
 */
public class InvoiceCodeGeneratorFactory {
    public static IInvoiceCodeGenerator getGenerator() throws Exception {
        SystemSettingDao settingDao = new SystemSettingDao();
        Integer defaultCountry;
        try {
            defaultCountry = Integer.valueOf(settingDao.getSystemSettingByName("Default Origin Country").getSettingValue());
        } catch (Exception e) {
            defaultCountry = 16;
        }
        if (defaultCountry == 70) { // France country.
            return new FranceInvoiceCodeGenerator();
        }
        return new DefaultInvoiceCodeGenerator();
    }
}
