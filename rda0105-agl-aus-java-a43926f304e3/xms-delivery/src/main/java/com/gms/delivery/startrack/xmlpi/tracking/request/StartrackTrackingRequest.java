package com.gms.delivery.startrack.xmlpi.tracking.request;

import com.gms.xms.common.utils.AppUtils;
import com.gms.xms.common.utils.DateUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Posted from Jun 24, 2016 10:45:36 AM
 * <p>
 * Author dattrinh
 */
public class StartrackTrackingRequest {
    private String userName;
    private String password;
    private String nonce;
    private Date createDate;
    private String source;
    private String accountNo;
    private String userAccessKey;
    private String consignmentId;

    public String getXml() throws Exception {
        GetConsignmentDetailRequest request = new GetConsignmentDetailRequest();
        Header header = new Header();
        header.setSource(this.getSource());
        header.setAccountNo(this.getAccountNo());
        header.setUserAccessKey(this.getUserAccessKey());
        request.setHeader(header);
        request.setConsignmentId(this.getConsignmentId());
        String detailRequestXml = AppUtils.Object2XmlString(request, GetConsignmentDetailRequest.class);
        Map<String, String> replaceMap = new HashMap<String, String>();
        replaceMap.put("getConsignmentDetailRequest>", "ns2:getConsignmentDetailRequest>");
        replaceMap.put("header>", "ns2:header>");
        replaceMap.put("source>", "ns1:source>");
        replaceMap.put("accountNo>", "ns1:accountNo>");
        replaceMap.put("userAccessKey>", "ns1:userAccessKey>");
        replaceMap.put("consignmentId>", "ns2:consignmentId>");
        detailRequestXml = AppUtils.replaceStringByMap(replaceMap, detailRequestXml);
        String soapHeader = "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ns1=\"http://startrackexpress/Common/Primitives/v1\" xmlns:ns2=\"http://startrackexpress/Common/actions/externals/Consignment/v1\" xmlns:ns3=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><SOAP-ENV:Header><wsse:Security SOAP-ENV:mustUnderstand=\"1\" xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd\"><wsse:UsernameToken wsu:Id=\"UsernameToken-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\"><wsse:Username>#{userName}</wsse:Username><wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-username-token-profile-1.0#PasswordText\">#{password}</wsse:Password><wsse:Nonce>#{nonce}</wsse:Nonce><wsu:Created xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">#{createDate}</wsu:Created></wsse:UsernameToken></wsse:Security></SOAP-ENV:Header><SOAP-ENV:Body>";
        String soapFooter = "</SOAP-ENV:Body></SOAP-ENV:Envelope>";
        replaceMap.clear();
        replaceMap.put("<ns2:getConsignmentDetailRequest>", soapHeader + "<ns2:getConsignmentDetailRequest>");
        replaceMap.put("</ns2:getConsignmentDetailRequest>", "</ns2:getConsignmentDetailRequest>" + soapFooter);
        replaceMap.put("#{userName}", this.getUserName());
        replaceMap.put("#{password}", this.getPassword());
        replaceMap.put("#{nonce}", this.getNonce());
        //replaceMap.put("#{nonce}", this.getUserAccessKey());
        String sDate = DateUtils.convertDateToString(this.getCreateDate(), "yyyy-MM-dd", null);
        String sTime = DateUtils.convertDateToString(this.getCreateDate(), "hh:mm:ss", null);
        replaceMap.put("#{createDate}", sDate + "T" + sTime + "Z");
        detailRequestXml = AppUtils.replaceStringByMap(replaceMap, detailRequestXml);
        detailRequestXml =   detailRequestXml.replace("#{password}", this.getPassword());
        return detailRequestXml;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getUserAccessKey() {
        return userAccessKey;
    }

    public void setUserAccessKey(String userAccessKey) {
        this.userAccessKey = userAccessKey;
    }

    public String getConsignmentId() {
        return consignmentId;
    }

    public void setConsignmentId(String consignmentId) {
        this.consignmentId = consignmentId;
    }
}
