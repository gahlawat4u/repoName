package com.gms.xms.persistence.service;

import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.dto.FranInvoiceNumberingVo;
import com.gms.xms.persistence.dao.FranInvoiceNumberingDao;
import org.apache.commons.lang.StringUtils;

import java.util.Date;

/**
 * Posted from Sep 28, 2016 10:29:06 AM
 * <p>
 * Author dattrinh
 */
public class FranInvoiceNumberingService {
    public String getFranInvoiceNumbering(String franchiseCode, Date startDate, Date endDate) throws Exception {
        String newFranchiseCode = franchiseCode.length() > 3 ? franchiseCode.substring(0, 3) : franchiseCode;
        FranInvoiceNumberingDao numberingDao = new FranInvoiceNumberingDao();
        // Create franchise invoice numbering filter.
        FranInvoiceNumberingVo numberingFilter = new FranInvoiceNumberingVo();
        numberingFilter.setStartDate(startDate);
        numberingFilter.setEndDate(endDate);
        numberingFilter.setFranchiseCode(newFranchiseCode);
        // Get franchise invoice numbering info.
        FranInvoiceNumberingVo numberingVo = numberingDao.selectByFilter(numberingFilter);
        String franInvoiceCode = "";
        if (numberingVo == null) {
            franInvoiceCode = GenCodeUtils.generateInvoiceCode(newFranchiseCode, endDate);
        } else {
            // Generate franchise invoice numbering.
            franInvoiceCode = GenCodeUtils.generateFranchiseReceivableInvoiceCode(newFranchiseCode, numberingVo.getYear(), numberingVo.getMonth(), numberingVo.getCounter(), numberingVo.getEndDate());
            franInvoiceCode = StringUtils.left(franInvoiceCode, franInvoiceCode.length() - 4);
        }
        return franInvoiceCode;
    }
}
