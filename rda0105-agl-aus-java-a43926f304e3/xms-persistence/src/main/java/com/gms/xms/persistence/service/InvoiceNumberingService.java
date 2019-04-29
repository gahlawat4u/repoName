package com.gms.xms.persistence.service;

import com.gms.xms.common.exception.DaoException;
import com.gms.xms.dto.InvoiceNumberingVo;
import com.gms.xms.persistence.config.SqlSessionClient;
import com.gms.xms.persistence.dao.InvoiceNumberingDao;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.txndb.vo.InvoiceVo;
import org.apache.commons.lang.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * Posted from Aug 8, 2016 3:57:57 PM
 * <p>
 * Author dattrinh
 */
public class InvoiceNumberingService {
    public void updateCounter(Map<String, String> context, InvoiceVo invoiceVo, SqlSessionClient sessionClient) throws Exception {
        // Get default country.
        SystemSettingDao settingDao = new SystemSettingDao();
        Integer defaultCountry = Integer.valueOf(settingDao.getSystemSettingByName("Default Origin Country").getSettingValue());
        if (defaultCountry == 70) { // FR
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(invoiceVo.getInvoiceDate());
            int year = calendar.get(Calendar.YEAR) % 100;
            int month = calendar.get(Calendar.MONTH) + 1;
            InvoiceNumberingDao numberingDao = new InvoiceNumberingDao(sessionClient);
            InvoiceNumberingVo filter = new InvoiceNumberingVo();
            filter.setMonth(month);
            filter.setYear(year);
            // Get current unique number.
            InvoiceNumberingVo invoiceNumberingVo = numberingDao.selectByFilter(filter);
            if (invoiceNumberingVo == null) {
                // If it's null then insert into the database.
                filter.setCurrentUniqueNumber(1);
                numberingDao.insert(context, filter);
            } else {
                // Update it.
                invoiceNumberingVo.setCurrentUniqueNumber(invoiceNumberingVo.getCurrentUniqueNumber() + 1);
                numberingDao.update(context, invoiceNumberingVo);
            }
        }
    }

    public void updateCounter(Map<String, String> context, Date inputDate, Long customerCode, String invoiceCode, SqlSessionClient sessionClient) throws DaoException {
        String franchiseCode = "0";
        String customerCodeStr = "";
        try {
            customerCodeStr = String.valueOf(customerCode);
        } catch (Exception e) {
        }
        if (!StringUtils.isBlank(customerCodeStr) && customerCodeStr.length() >= 3) {
            franchiseCode = customerCodeStr.substring(0, 3);
        }

        InvoiceNumberingDao invoiceNumberingDao = new InvoiceNumberingDao(sessionClient);
        InvoiceNumberingVo invoiceNumberingVo = new InvoiceNumberingVo();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        int year = calendar.get(Calendar.YEAR) % 100;
        int month = calendar.get(Calendar.MONTH) + 1;

        invoiceNumberingVo.setYear(year);
        invoiceNumberingVo.setMonth(month);
        invoiceNumberingVo.setFranchiseCode(franchiseCode);
        invoiceNumberingVo.setInvoiceCode(invoiceCode);

        InvoiceNumberingVo currentCounter = invoiceNumberingDao.selectCounter(invoiceNumberingVo);
        Integer counter = 1;
        if (currentCounter == null) {
            InvoiceNumberingVo filter = new InvoiceNumberingVo();
            filter.setMonth(month);
            filter.setYear(year);
            filter.setFranchiseCode(franchiseCode);
            Integer maxCounter = invoiceNumberingDao.selectMaxCounter(filter);
            if (maxCounter != null) {
                counter = maxCounter + 1;
            }
            invoiceNumberingVo.setCurrentUniqueNumber(counter);
            invoiceNumberingDao.insert(context, invoiceNumberingVo);
        }
    }

    public String getCodeWithCounter(Date inputDate, String invoiceCode, Long customerCode) throws DaoException {
        String franchiseCode = "0";
        String customerCodeStr = "";
        try {
            customerCodeStr = String.valueOf(customerCode);
        } catch (Exception e) {
        }
        if (!StringUtils.isBlank(customerCodeStr) && customerCodeStr.length() >= 3) {
            franchiseCode = customerCodeStr.substring(0, 3);
        }
        InvoiceNumberingDao invoiceNumberingDao = new InvoiceNumberingDao();
        InvoiceNumberingVo invoiceNumberingVo = new InvoiceNumberingVo();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(inputDate);
        int year = calendar.get(Calendar.YEAR) % 100;
        int month = calendar.get(Calendar.MONTH) + 1;

        invoiceNumberingVo.setYear(year);
        invoiceNumberingVo.setMonth(month);
        invoiceNumberingVo.setFranchiseCode(franchiseCode);
        invoiceNumberingVo.setInvoiceCode(invoiceCode);
        InvoiceNumberingVo currentCounter = invoiceNumberingDao.selectCounter(invoiceNumberingVo);
        if (currentCounter != null) {
            String newCode = String.format("%02d", year) + String.format("%02d", month) + String.format("%05d", currentCounter.getCurrentUniqueNumber()) + "-" + invoiceCode;
            return newCode;
        }
        return invoiceCode;
    }
}
