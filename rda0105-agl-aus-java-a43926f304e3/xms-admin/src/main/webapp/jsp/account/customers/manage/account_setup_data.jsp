<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="frmSaveAccountSetup">
    <div class="row">
        <div class="portlet-body b12 b11">
            <div class="row">
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td colspan="3" class="b16 uppercase"><p>
                                <s:property value="customer.address.customerName"/>
                                <s:hidden name="saveManageCustomer.accountSetup.address.customerName"
                                          value="%{customer.address.customerName}"/>
                                <br/>
                                <s:property value="customer.address.address1"/>
                                <s:hidden name="saveManageCustomer.accountSetup.address.address1"
                                          value="%{customer.address.address1}"/>
                                <br/>
                                <s:property value="customer.address.city"/>
                                <s:hidden name="saveManageCustomer.accountSetup.address.city"
                                          value="%{customer.address.city}"/>
                                <br/>
                                <s:property value="customer.address.postalCode"/>
                                <s:hidden name="saveManageCustomer.accountSetup.address.postalCode"
                                          value="%{customer.address.postalCode}"/>
                                <br/>
                                <s:property value="customer.countryName"/>
                                <s:hidden name="saveManageCustomer.accountSetup.countryName"
                                          value="%{customer.countryName}"/>
                                <br/>
                                <s:property value="customer.address.phone"/>
                                <s:hidden name="saveManageCustomer.accountSetup.address.phone"
                                          value="%{customer.address.phone}"/>
                                <br/>
                                <s:property value="customer.address.email"/>
                                <s:hidden name="saveManageCustomer.accountSetup.address.email"
                                          value="%{customer.address.email}"/>
                            </p></td>
                        </tr>
                        <tr>
                            <td colspan="3">
                                <div class="caption b17">
                                    <xms:localization text="Account Setup"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Customer #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.customerCode"
                                                                     value="%{customer.customerCode}"
                                                                     cssClass="form-control" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Submit Date"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.createDate"
                                                                     value="%{customer.createDate}"
                                                                     cssClass="form-control" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Activation Date"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.activateDate"
                                                                     value="%{customer.activateDate}"
                                                                     cssClass="form-control" readonly="true"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Inactive?"/>:</td>
                            <td class="td2" colspan="2"><s:checkbox name="saveManageCustomer.accountSetup.inActive"
                                                                    value="%{customer.inActive}"/></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td colspan="3" style="border-top: 0px !important">
                                <div class="caption b17">
                                    <xms:localization text="Reporting Setup"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Previous Carrier"/>:</td>
                            <td class="td2" colspan="2"><s:select name="saveManageCustomer.accountSetup.previousCarrier"
                                                                  value="%{customer.previousCarrier}" list="services"
                                                                  listValue="serviceName" listKey="serviceId"
                                                                  cssClass="form-control" headerValue=""
                                                                  headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Customer Group"/>:</td>
                            <td class="td2" colspan="2"><s:select name="saveManageCustomer.accountSetup.groupId"
                                                                  value="%{customer.groupId}" list="customerGroups"
                                                                  listValue="customerGroupName"
                                                                  listKey="customerGroupId" cssClass="form-control"
                                                                  headerValue="" headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Web Freight Group"/>:</td>
                            <td class="td2"><s:select name="saveManageCustomer.accountSetup.webshipGroupId"
                                                      value="%{customer.webshipGroupId}" list="webshipGroups"
                                                      listValue="webshipGroupName" listKey="webshipGroupId"
                                                      cssClass="form-control" headerValue="" headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Industry"/>:</td>
                            <td class="td2" colspan="2"><s:select name="saveManageCustomer.accountSetup.industryId"
                                                                  value="%{customer.industryId}" list="industries"
                                                                  listValue="industryName" listKey="industryId"
                                                                  cssClass="form-control" headerValue=""
                                                                  headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Area"/>:</td>
                            <td class="td2" colspan="2"><s:select name="saveManageCustomer.accountSetup.areaId"
                                                                  value="%{customer.areaId}" list="areas"
                                                                  listValue="areaName" listKey="areaId"
                                                                  cssClass="form-control" headerValue=""
                                                                  headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Sales Rep"/>:</td>
                            <td class="td2" colspan="2"><s:select name="saveManageCustomer.accountSetup.salesRepId"
                                                                  value="%{customer.salesRepId}" list="salesReps"
                                                                  listValue="displayName" listKey="userId"
                                                                  cssClass="form-control" headerValue=""
                                                                  headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Collector"/>:</td>
                            <td class="td2" colspan="2"><s:select name="saveManageCustomer.accountSetup.collectorId"
                                                                  value="%{customer.collectorId}" list="collectors"
                                                                  listValue="displayName" listKey="userId"
                                                                  cssClass="form-control" headerValue=""
                                                                  headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Business Registration #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.accountSetup.registrationId"
                                    value="%{customer.registrationId}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="GST #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.gstId"
                                                                     value="%{customer.gstId}" cssClass="form-control"
                                                                     onkeypress="return formartNumber(event,this,false);"/></td>
                        </tr>
                    </table>
                </div>
                <div class="col-lg-4">
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td colspan="3" style="border-top: 0px !important">
                                <div class="caption b17">
                                    <xms:localization text="Carrier Setup"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="DHL Account #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.dhlAccount"
                                                                     value="%{customer.dhlAccount}"
                                                                     cssClass="form-control"/></td>
                        </tr>
                       <%--  <tr>
                            <td class="td1"><xms:localization text="Hub Account #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.hubAccount"
                                                                     value="%{customer.hubAccount}"
                                                                     cssClass="form-control"/></td>
                        </tr> --%>
                        <%-- <tr>
                            <td class="td1"><xms:localization text="TNT Account #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.tntAccount"
                                                                     value="%{customer.tntAccount}"
                                                                     cssClass=" form-control"/></td>
                        </tr> --%>
                        <tr>
                            <td class="td1"><xms:localization text="Toll Priority Account #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.accountSetup.tollPriorityAccount"
                                    value="%{customer.tollPriorityAccount}" cssClass=" form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Startrack Account #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.accountSetup.startrackAccount"
                                    value="%{customer.startrackAccount}" cssClass=" form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Startrack Dispatch ID #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.dispatchId"
                                                                     value="%{customer.dispatchId}"
                                                                     cssClass=" form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Other Account #"/>:</td>
                            <td class="td2" colspan="2"><s:textfield name="saveManageCustomer.accountSetup.otherAccount"
                                                                     value="%{customer.otherAccount}"
                                                                     cssClass=" form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Booking Email Notification"/>:</td>
                            <td class="td2" colspan="2"><s:checkbox
                                    name="saveManageCustomer.accountSetup.bookingEmailNotification"
                                    value="%{customer.bookingEmailNotification}"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Rejection Notes"/>:</td>
                            <td class="td2" colspan="2"><s:textarea name="saveManageCustomer.accountSetup.rejectionNote"
                                                                    value="%{customer.rejectionNote}"
                                                                    cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Enable AGL Warranty"/>:</td>
                            <td class="td2" colspan="2"><s:checkbox name="saveManageCustomer.accountSetup.enableSi"
                                                                    value="%{customer.enableSi}"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
    function saveCustomerSetup() {
        var data = $("#frmSaveAccountSetup").serialize();
        loadingDialog.dialog("open");
        $.post("manage_customers_account_setup_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            $("#account-setup-tab").html(res.content);
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>