package com.gms.xms.workflow.utils;

import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.exception.DaoException;
import com.gms.xms.common.utils.GenCodeUtils;
import com.gms.xms.persistence.dao.AccessorialDao;
import com.gms.xms.persistence.dao.CustomerDao;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.dao.franchisepayable.FranchiseDao;
import com.gms.xms.txndb.vo.*;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Posted from May 28, 2016 11:28:17 AM
 * <p>
 * Author huynd
 */
public class ImportBillingUtils {
    protected static Log log = LogFactory.getLog(ImportBillingUtils.class);

    public static String duplicateAibillNumber(String airbillNumber, String checkAirbillNumber) {
        String airbillNumberX = airbillNumber;
        String diffAirbillNumber = checkAirbillNumber.replace(airbillNumber, "");
        Integer countDiff = diffAirbillNumber.length();
        countDiff++;
        for (int i = 0; i < countDiff; i++) {
            airbillNumberX += "X";
        }
        return airbillNumberX;
    }

    public static String getPackageFlag(String content, Long carrier) {
        if (carrier == 2L || carrier == 3L) {
            return "-1";
        }
        if ("Doc".equalsIgnoreCase(content.trim())) {
            return "0";
        } else {
            return "1";
        }
    }

    public static String removeCodeStr(String description) {
        List<String> removeStr = new ArrayList<String>();
        removeStr.add("NONDOC");
        removeStr.add("nondoc");
        removeStr.add("NON DOC");
        removeStr.add("NON DO");
        removeStr.add("DOC");
        removeStr.add("doc");
        removeStr.add("do");
        removeStr.add("no");
        for (int i = 0; i < removeStr.size(); i++) {
            description = description.replace(removeStr.get(i), "");
        }
        return description.trim();
    }

    public static String getServiceDisplayDescription(String description, Boolean bound, Integer contentType, Long carrier) {
        String boundStr = "";
        String contentStr = "";
        if ((carrier != 0 && carrier != 1L && carrier != 40L && carrier != 51L && carrier != 54L) || "NRI-NON REVENUE ITEM".equalsIgnoreCase(description) || "NON REVENUE ITEM".equalsIgnoreCase(description)) {
            return description;
        } else {
            description = removeCodeStr(description);
            boundStr = (bound == true) ? " (Inbound)" : "";
            if (contentType == 0) {
                if (carrier == 40 || carrier == 51) {
                    contentStr = " Env";
                } else {
                    contentStr = " Doc";
                }
            } else if (contentType == 2) {
                contentStr = " Pak";
            }
            return description + contentStr + boundStr;
        }
    }

    public static String checkAndSaveAccessorial(ContextBase2 context, String accessorial, Long carrier) throws DaoException {
        if (StringUtils.isBlank(accessorial)) {
            return "";
        }
        Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
        AccessorialDao accessorialDao = new AccessorialDao();
        AccessorialVo accessorialVo = new AccessorialVo();
        accessorialVo.setCarrier(carrier);
        accessorialVo.setCode(accessorial);
        accessorialVo.setDescription(accessorial);
        AccessorialVo checkCode = accessorialDao.checkAccessorialByCode(accessorialVo);
        AccessorialVo checkDescription = accessorialDao.checkAccessorialByDescription(accessorialVo);
        if (checkCode == null && checkDescription == null) {
            accessorialVo.setIsQuotable(0);
            accessorialVo.setTypeId(1);
            accessorialVo.setModifiedDate(new Date());
            accessorialDao.insertAccessorial(addInfo, accessorialVo);
            return accessorial;
        } else {
            if (checkCode != null) {
                return checkCode.getDescription();
            } else {
                return accessorial;
            }
        }
    }

    public static String checkAndSaveAccessorialByCode(ContextBase2 context, String code, String description, Long carrier) throws DaoException {
        if (StringUtils.isBlank(description)) {
            return "";
        }
        Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
        AccessorialDao accessorialDao = new AccessorialDao();
        AccessorialVo accessorialVo = new AccessorialVo();
        accessorialVo.setCarrier(carrier);
        accessorialVo.setCode(code);
        AccessorialVo checkCode = accessorialDao.checkAccessorialByCode(accessorialVo);
        if (checkCode == null) {
            accessorialVo.setDescription(description);
            accessorialVo.setIsQuotable(0);
            accessorialVo.setTypeId(1);
            accessorialVo.setModifiedDate(new Date());
            accessorialDao.insertAccessorial(addInfo, accessorialVo);
            return description;
        } else {
            return checkCode.getDescription();
        }
    }

    public static ShipmentInvoiceVo buildShipmentInvoice(Long shipmentId, String airbillNumber, Date invoiceDate, Long customerCode) throws Exception {
        ShipmentInvoiceVo shipmentInvoice = new ShipmentInvoiceVo();
        Long invoiceId;
        // Check if the new invoice code exists or not?
        InvoiceDao invoiceDao = new InvoiceDao();
        String invoiceCode = GenCodeUtils.generateInvoiceCode(String.valueOf(customerCode), invoiceDate);
        InvoiceVo invoice = invoiceDao.getByCode(invoiceCode);
        if (invoice != null) {
            invoiceId = invoice.getInvoiceId();
            // Insert shipment invoice
            shipmentInvoice.setInvoiceId(invoiceId);
        } else {
            // Prepare new invoice
            shipmentInvoice.setInvoiceId(0L);
        }
        shipmentInvoice.setShipmentId(shipmentId);
        shipmentInvoice.setAirbillNumber(airbillNumber);
        shipmentInvoice.setPaidCarrierCost(BigDecimal.valueOf(0D));
        shipmentInvoice.setPaidCustomerCost(BigDecimal.valueOf(0D));
        return shipmentInvoice;
    }

    public static InvoiceVo buildInvoice(Date invoiceDate, Long customerCode) throws Exception {
        InvoiceVo invoice = new InvoiceVo();
        String invoiceCode = GenCodeUtils.generateInvoiceCode(String.valueOf(customerCode), invoiceDate);
        invoice.setInvoiceCode(invoiceCode);
        invoice.setCustomerCode(customerCode);
        invoice.setInvoiceDate(invoiceDate);
        invoice.setStatus(0);
        invoice.setLateFee(BigDecimal.valueOf(0D));
        invoice.setPaid(0);
        invoice.setInvCreateDate(new Date());
        return invoice;
    }

    public static Boolean isExistedCustomer(Long customerCode) throws DaoException {
        if (customerCode == null || customerCode == 0) {
            return false;
        }
        String code = String.valueOf(customerCode);
        if (code.length() > 3 && code.substring(3, code.length()).equalsIgnoreCase("00001")) {
            FranchiseDao franchiseDao = new FranchiseDao();
            FranchiseVo franchiseVo = franchiseDao.selectByFranchiseCode(code);
            if (franchiseVo == null) {
                return false;
            }
        } else {
            CustomerDao customerDao = new CustomerDao();
            CustomerVo customerVo = customerDao.selectByCode(code);
            if (customerVo == null) {
                return false;
            }
        }
        return true;
    }

}
