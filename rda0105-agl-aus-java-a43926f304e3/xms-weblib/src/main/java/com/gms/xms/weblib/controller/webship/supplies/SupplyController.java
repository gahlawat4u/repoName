package com.gms.xms.weblib.controller.webship.supplies;

import com.gms.xms.cache.SystemSettingCache;
import com.gms.xms.common.constants.AppConstants;
import com.gms.xms.common.exception.CustomException;
import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.GsonUtils;
import com.gms.xms.dto.email.EmailAddressInfoVo;
import com.gms.xms.model.SupplyModel;
import com.gms.xms.model.utils.ModelUtils;
import com.gms.xms.model.webship.supplies.SupplyServiceModel;
import com.gms.xms.persistence.dao.EmailTemplateDao;
import com.gms.xms.persistence.dao.ServiceDao;
import com.gms.xms.persistence.dao.SupplyDao;
import com.gms.xms.persistence.dao.SystemSettingDao;
import com.gms.xms.persistence.dao.email.EmailAddressInfoDao;
import com.gms.xms.persistence.service.service.IServiceService;
import com.gms.xms.persistence.service.service.ServiceServiceImp;
import com.gms.xms.persistence.service.supply.ISupplyService;
import com.gms.xms.persistence.service.supply.SupplyServiceImp;
import com.gms.xms.txndb.vo.EmailTemplateVo;
import com.gms.xms.txndb.vo.ServiceVo;
import com.gms.xms.txndb.vo.SupplyVo;
import com.gms.xms.txndb.vo.webship.supplies.SupplyServiceVo;
import com.gms.xms.weblib.controller.JsonBaseController;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.helper.StringUtil;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Posted from SupplyController
 * <p>
 * Author DatTV Date Jul 16, 2015 9:42:08 AM
 */
public class SupplyController extends JsonBaseController {
    private static final long serialVersionUID = -3491572415649247950L;

    private List<SupplyServiceModel> serviceList;
    private SupplyModel supply;
    private String id;
    private String supplyList;

    public String show() {
        this.setPageTitle(this.getLocalizationText("Supplies"));
        this.setBreadCrumb("Supplies");
        try {
            loadSupplyServiceList();
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public String showDetail() {
        ISupplyService supplyService = new SupplyServiceImp();
        try {
            Integer supplyId = Integer.valueOf(id);
            SupplyVo supplyVo = supplyService.selectById(supplyId);
            supply = ModelUtils.createModelFromVo(supplyVo, SupplyModel.class);
            supply.setImage(supply.getImage().replace("..", AppConstants.APP_SETTINGS.getXms1Url()));
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
        return "success";
    }

    public void order() {
        try {
            // Convert input string to the hash map.
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            Map<String, String> supplyMap = GsonUtils.fromGson(this.getSupplyList(), type);
            // Valid input.
            if (!isValidInput(supplyMap)) {
                throw new CustomException("Please fill out Order for supply list.");
            }
            Map<String, Object> infoMap = buildSupplyInfo(supplyMap);
            // Get email address info.
            EmailAddressInfoDao addressInfoDao = new EmailAddressInfoDao();
            String customerCode = String.valueOf(this.getWebshipLoginInfo().getCustomerCode());
            EmailAddressInfoVo emailAddressInfoVo = addressInfoDao.getEmailAddressInfoByCustCode(customerCode);
            // Get customer name.
            String customerName = emailAddressInfoVo.getCustomerName();
            String franchiseName = emailAddressInfoVo.getFranchiseName();
            String franchiseEmail = emailAddressInfoVo.getFranchiseEmail();
            // Get admin email list by name.
            List<String> adminEmailList = addressInfoDao.getAdminEmailListByName("Supply Request");
            String emails = "";
            for (String email : adminEmailList) {
                emails += email + ";";
            }
            emails = emails.trim();
            if (emails.length() > 0) {
                emails = emails.substring(0, emails.length() - 1);
            }
            franchiseEmail += ";" + emails;
            // Get email template.
            EmailTemplateDao emailTemplateDao = new EmailTemplateDao();
            boolean isTollOrder = (Boolean) infoMap.get("Is Toll Order");
            String tollSupplyInfo = (String) infoMap.get("Toll Supply Info");
            String supplyInfo = (String) infoMap.get("Supply Info");
            SystemSettingDao settingDao = new SystemSettingDao();
            EmailTemplateVo emailTemplateVo;
            String subject, body;
            // Send toll supply info.
            if (isTollOrder) {
                String tollEmail = settingDao.getSystemSettingByName("Email for Toll Priority Order Supply").getSettingValue();
                String tollEmailFranchise = StringUtils.isBlank(tollEmail) ? franchiseEmail : franchiseEmail + ";" + tollEmail;
                emailTemplateVo = emailTemplateDao.getEmailTemplateByName("Order Supply for Toll Priority");
                if (emailTemplateVo != null) {
                    subject = emailTemplateVo.getSubject();
                    body = emailTemplateVo.getTemplateContent();
                    // Create replace map.
                    Map<String, String> replaceMap = new HashMap<String, String>();
                    replaceMap.put("\n", "<br/>");
                    replaceMap.put("[Franchise Name]", franchiseName);
                    replaceMap.put("[Supply Info]", tollSupplyInfo);
                    replaceMap.put("[Customer Name]", customerName);
                    replaceMap.put("[Customer Code]", customerCode);
                    // Replace subject and body content.
                    subject = AppUtils.replaceStringByMap(replaceMap, subject);
                    body = AppUtils.replaceStringByMap(replaceMap, body);
                    sendEmail("", tollEmailFranchise, subject, body);
                }
            }
            // Send supply info.
            emailTemplateVo = emailTemplateDao.getEmailTemplateByName("Order Supply");
            subject = emailTemplateVo.getSubject();
            body = emailTemplateVo.getTemplateContent();
            // Create replace map.
            Map<String, String> replaceMap = new HashMap<String, String>();
            replaceMap.put("\n", "<br/>");
            replaceMap.put("[Franchise Name]", franchiseName);
            replaceMap.put("[Supply Info]", supplyInfo);
            replaceMap.put("[Customer Name]", customerName);
            replaceMap.put("[Customer Code]", customerCode);
            // Replace subject and body content.
            subject = AppUtils.replaceStringByMap(replaceMap, subject);
            body = AppUtils.replaceStringByMap(replaceMap, body);
            sendEmail("", franchiseEmail, subject, body);
        } catch (Exception e) {
            handleError(e, AppConstants.SYSTEM_ERROR_MESSAGE);
        }
    }

    protected boolean isValidInput(Map<String, String> map) throws CustomException {
        if (map == null || map.size() == 0) {
            return false;
        }
        int elementCount = 0;
        int invalidElementCount = 0;
        for (String key : map.keySet()) {
            elementCount++;
            if (!StringUtils.isBlank(map.get(key))) {
                try {
                    String order = (String) map.get(key);
                    if (!StringUtils.isBlank(order)) {
                        Integer.valueOf(map.get(key));
                    }
                } catch (Exception e) {
                    throw new CustomException("Invalid order number.");
                }
            } else {
                invalidElementCount++;
            }
        }
        return elementCount != invalidElementCount;
    }

    protected void sendEmail(String fromEmail, String toEmail, String subject, String content) throws Exception {
        // Get email settings.
        if (StringUtil.isBlank(fromEmail)) {
            fromEmail = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_EMAIL);
        }
        String emailUserName = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_USER_NAME);
        String emailPassword = SystemSettingCache.getInstance().getValueByKey(AppConstants.EMAIL_PASSWORD);
        String smtpServerName = SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_SERVER_NAME);
        int smtpPortNumber = Integer.parseInt(SystemSettingCache.getInstance().getValueByKey(AppConstants.SMTP_PORT_NUMBER));
        String fromName = SystemSettingCache.getInstance().getValueByKey(AppConstants.FROM_NAME);
        // Send email.
        AppUtils.sendEmail(smtpServerName, smtpPortNumber, fromName, fromEmail, emailUserName, emailPassword, toEmail, null, null, subject, content, null);
    }

    protected Map<String, Object> buildSupplyInfo(Map<String, String> supplyMap) throws Exception {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        String supplyInfo = buildSupplyInfoHeader();
        String tollSupplyInfo = supplyInfo;
        // Get supply info and service info.
        SupplyDao supplyDao = new SupplyDao();
        ServiceDao serviceDao = new ServiceDao();
        boolean isTollOrder = false;
        for (String key : supplyMap.keySet()) {
            if (!StringUtils.isBlank((String) supplyMap.get(key))) {
                Integer supplyId = Integer.valueOf(key);
                Integer order = Integer.valueOf(supplyMap.get(key));
                // Get supply info.
                SupplyVo supplyVo = supplyDao.selectById(supplyId);
                // Get service info.
                ServiceVo serviceVo = serviceDao.selectById(supplyVo.getCarrierId().intValue());
                supplyInfo += buildSupplyInfoRow(serviceVo.getServiceName(), supplyVo.getSupplyName(), order);
                if (supplyVo.getCarrierId() == 52) {
                    tollSupplyInfo += buildSupplyInfoRow(serviceVo.getServiceName(), supplyVo.getSupplyName(), order);
                    isTollOrder = true;
                }
            }
        }
        supplyInfo += buildSupplyInfoFooter();
        tollSupplyInfo += buildSupplyInfoFooter();
        resultMap.put("Supply Info", supplyInfo);
        resultMap.put("Toll Supply Info", tollSupplyInfo);
        resultMap.put("Is Toll Order", isTollOrder);
        return resultMap;
    }

    protected String buildSupplyInfoHeader() {
        StringBuilder builder = new StringBuilder();
        builder.append("<table border=\"0\"><tr bgcolor=\"#fde4d0\" style=\"vertical-align:top; height: 20px;\">");
        builder.append("<td style=\"width:150px; border: 1px solid #f9b074; border-width: 1px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 12pt Arial, sans-serif; color: #142b71;\">Carrier Name</td>");
        builder.append("<td style=\"width:150px; border: 1px solid #f9b074; border-width: 1px 0px 1px 1px; padding: 0 10px; font: normal 12pt Arial, sans-serif; color: #142b71;\">Supply Name</td>");
        builder.append("<td style=\"border: 1px solid #f9b074; padding:0 10px; font: normal 12pt Arial, sans-serif; color: #142b71;\">Quantity</td></tr>");
        return builder.toString();
    }

    protected String buildSupplyInfoRow(String serviceName, String supplyName, Integer order) {
        StringBuilder builder = new StringBuilder();
        builder.append("<tr style=\"vertical-align:top;\"><td style=\"border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; height: 20px; padding: 0 10px; font: normal 12pt Arial, sans-serif; color: #142b71;\">" + serviceName + "</td>");
        builder.append("<td style=\"border: 1px solid #f9b074; border-width: 0px 0px 1px 1px; padding: 0 10px; font: normal 12pt Arial, sans-serif; color: #142b71;\">" + supplyName + "</td>");
        builder.append("<td style=\"border: 1px solid #f9b074; border-width: 0px 1px 1px 1px; padding: 0 10px; font: normal 12pt Arial, sans-serif; color: #142b71;\">" + String.valueOf(order) + "</td></tr>");
        return builder.toString();
    }

    protected String buildSupplyInfoFooter() {
        return "</table>";
    }

    private void loadSupplyServiceList() throws Exception {
        List<SupplyServiceVo> result = new ArrayList<SupplyServiceVo>();
        IServiceService service = new ServiceServiceImp();
        ISupplyService supplyService = new SupplyServiceImp();
        List<ServiceVo> serviceModels = service.selectBySupply();
        for (ServiceVo serviceVo : serviceModels) {
            SupplyServiceVo supplyServiceVo = new SupplyServiceVo();
            supplyServiceVo.setService(serviceVo);
            supplyServiceVo.setSupplies(supplyService.selectByServiceId(serviceVo.getServiceId()));
            result.add(supplyServiceVo);
        }
        serviceList = ModelUtils.createListModelFromVo(result, SupplyServiceModel.class);
    }

    public List<SupplyServiceModel> getServiceList() {
        return serviceList;
    }

    public void setServiceList(List<SupplyServiceModel> serviceList) {
        this.serviceList = serviceList;
    }

    public SupplyModel getSupply() {
        return supply;
    }

    public void setSupply(SupplyModel supply) {
        this.supply = supply;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplyList() {
        return supplyList;
    }

    public void setSupplyList(String supplyList) {
        this.supplyList = supplyList;
    }
}