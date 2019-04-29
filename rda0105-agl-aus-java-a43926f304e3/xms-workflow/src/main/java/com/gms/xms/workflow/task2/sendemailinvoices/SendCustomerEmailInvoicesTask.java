package com.gms.xms.workflow.task2.sendemailinvoices;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.constants.Attributes;
import com.gms.xms.common.constants.ErrorCode;
import com.gms.xms.common.context.ContextBase2;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.persistence.dao.InvoiceDao;
import com.gms.xms.persistence.service.customer.CustomerServiceImp;
import com.gms.xms.persistence.service.customer.ICustomerService;
import com.gms.xms.persistence.service.customeraddress.CustomerAddressServiceImp;
import com.gms.xms.persistence.service.customeraddress.ICustomerAddressService;
import com.gms.xms.persistence.service.customerbillingaddress.CustomerBillingAddressServiceImp;
import com.gms.xms.persistence.service.customerbillingaddress.ICustomerBillingAddressService;
import com.gms.xms.persistence.service.franchise.FranchiseServiceImp;
import com.gms.xms.persistence.service.franchise.IFranchiseService;
import com.gms.xms.txndb.vo.*;
import com.gms.xms.txndb.vo.invoicing.manageinvoice.InvoiceInfoVo;
import com.gms.xms.workflow.core2.Task2;
import com.gms.xms.workflow.render.invoice.vieweditinvoice.IViewEditInvoiceRender;
import com.gms.xms.workflow.render.invoice.vieweditinvoice.ViewEditInvoiceRenderImp;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Posted from SendCustomerEmailInvoicesTask
 * <p>
 * Author @author HungNT Mar 29, 2016
 */
public class SendCustomerEmailInvoicesTask implements Task2 {

    private static final Log log = LogFactory.getLog(SendCustomerEmailInvoicesTask.class);

    @Override
    public boolean execute(ContextBase2 context) throws Exception {
        try {
            // Prepare datas
            List<Long> invoiceIds = context.get(Attributes.LIST_INVOICE_IDS);
            Map<String, String> addInfo = context.getMap(Attributes.STR_ADD_INFO);
            String fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
            String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
            String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
            String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
            int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
            String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
            List<Map<String, String>> sentList = new LinkedList<>();
            for (Long invoiceId : invoiceIds) {
                ContextBase2 invoiceContext = new ContextBase2(addInfo);
                invoiceContext.put(Attributes.INVOICE_ID, invoiceId);
                invoiceContext.put(Attributes.WFP_NAME, "Wfl-ViewEditInvoiceSearchInvoiceDetail");
                IViewEditInvoiceRender render = new ViewEditInvoiceRenderImp(addInfo);
                render.generateHtmlViewPdfFile(invoiceContext, false);
                InvoiceInfoVo invoiceInfoVo = invoiceContext.get(Attributes.INVOICE_VO);
                String customerCode = invoiceInfoVo.getCustomerCode();
                String franchiseCode = customerCode.substring(0, 3) + "00001";
                String invoiceCode = invoiceInfoVo.getInvoiceCode();
                String outputFilePath = invoiceContext.getString(Attributes.OUT_PUT_FILE_PATH);
                String fileName = "invoice_" + invoiceCode + ".pdf";
                String pdfFilePath = AppConstants.APP_SETTINGS.getAppTmpPath() + "/" + fileName;
                try {
                    AppUtils.createPDFFromHTMLWithFont(outputFilePath, pdfFilePath, "arial", true);
                } catch (Exception e) {
                    log.error("Error create PDF file", e);
                    //continue process other invoice if this invoice has error
                    continue;
                }
                Boolean emailInvoice = false;
                Long invoiceToCustomerId = 0L;
                if (customerCode.substring(3, customerCode.length()).equalsIgnoreCase("00001")) {
                    IFranchiseService franchiseService = new FranchiseServiceImp();
                    FranchiseVo franchiseVo = franchiseService.selectFranchiseByFranchiseCode(customerCode);
                    emailInvoice = franchiseVo.getEmailInvoice();
                    invoiceToCustomerId = franchiseVo.getInvoiceToCustomerid();
                } else {
                    ICustomerService customerService = new CustomerServiceImp();
                    CustomerVo customerVo = customerService.selectByCode(customerCode);
                    emailInvoice = customerVo.getEmailInvoice();
                    invoiceToCustomerId = customerVo.getInvoiceToCustomerId();
                }
                ICustomerBillingAddressService billingAddressService = new CustomerBillingAddressServiceImp();
                CustomerBillingAddressVo customerBillingAddressVo = new CustomerBillingAddressVo();
                if (invoiceToCustomerId != 0) {
                    customerBillingAddressVo = billingAddressService.getCustomerBillingAddressByCustomerCode(invoiceToCustomerId);
                } else {
                    customerBillingAddressVo = billingAddressService.getCustomerBillingAddressByCustomerCode(Long.parseLong(customerCode));
                }

                ICustomerAddressService addressService = new CustomerAddressServiceImp();
                CustomerAddressVo franchiseAddressVo = addressService.getCustomerAddressByCustomerCode(Long.parseLong(franchiseCode));

                String customerName = customerBillingAddressVo.getBillingCustomerName();
                String customerEmail = customerBillingAddressVo.getBillingEmail();
                String franchisePhone = franchiseAddressVo.getPhone();
                String franchiseEmail = franchiseAddressVo.getEmail();
                String invoiceDate = DateUtils.convertDateToString(invoiceInfoVo.getInvoiceDate(), AppConstants.APP_SETTINGS.getDefaultDateFormat(), null);
                if (emailInvoice) {
                    EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
                    EmailTemplateVo emailTemplateVo = new EmailTemplateVo();
                    if (invoiceIds.size() == 1) {
                        emailTemplateVo = emailTemplateDao.getEmailTemplateByName(AppConstants.INDIVIDUAL_SEND_EMAIL_INVOICE);
                    } else {
                        emailTemplateVo = emailTemplateDao.getEmailTemplateByName(AppConstants.SEND_EMAIL_INVOICE);
                    }
                    String content = emailTemplateVo.getTemplateContent();
                    String subject = emailTemplateVo.getSubject();
                    Map<String, String> replaceMap = new HashMap<>();
                    replaceMap.put("[Customer Name]", customerName);
                    replaceMap.put("[Customer ID]", customerCode);
                    replaceMap.put("[Invoice Date]", invoiceDate);
                    replaceMap.put("[Franchise Phone]", franchisePhone);
                    replaceMap.put("[Franchise Email]", franchiseEmail);
                    replaceMap.put("[Invoice Number]", invoiceCode);
                    replaceMap.put("\r\n", "<br>");
                    content = AppUtils.replaceStringByMap(replaceMap, content);
                    subject = AppUtils.replaceStringByMap(replaceMap, subject);

                    StringBuilder logInfo = new StringBuilder();
                    logInfo.append("SEND CUSTOMER INVOICES EMAIL:\n");
                    logInfo.append("SUBJECT: " + subject + "\n");
                    logInfo.append("FROM: " + fromEmail + "\n");
                    logInfo.append("TO: " + customerEmail + "\n");
                    logInfo.append("CONTENT: \n" + content + "\n");
                    logInfo.append("ATTACHED FILE: " + pdfFilePath + "\n\n");
                    log.info(logInfo.toString());

                    // 4. Send email
                    InvoiceDao invoiceDao = new InvoiceDao();
                    InvoiceVo invoiceVo = new InvoiceVo();
                    try {
                        Map<String, String> sent = new HashMap<>();
                        AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, customerEmail, null, null, subject, content, new String[]{pdfFilePath});
                        invoiceVo.setInvoiceCode(invoiceInfoVo.getInvoiceCode());
                        invoiceVo.setStatus(2);
                        invoiceDao.updateInvStatusByInvCode(addInfo, invoiceVo);
                        sent.put("file", fileName);
                        sent.put("email", customerEmail);
                        sentList.add(sent);
                    } catch (Exception e) {
                        log.error(e);
                        invoiceVo.setInvoiceCode(invoiceInfoVo.getInvoiceCode());
                        invoiceVo.setStatus(4);
                        invoiceDao.updateInvStatusByInvCode(addInfo, invoiceVo);
                    }
                    context.put(Attributes.SENT_LIST, sentList);
                }
            }
        } catch (Exception e) {
            context.put(Attributes.ERROR_CODE, ErrorCode.ERROR);
            context.put(Attributes.ERROR_MESSAGE, e.getMessage());
            log.error(e);
            return false;
        }
        return true;
    }

}
