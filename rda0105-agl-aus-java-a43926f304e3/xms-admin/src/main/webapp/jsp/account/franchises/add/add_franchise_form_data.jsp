<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<style type="text/css">
    ul.ul_charge_amount {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    ul.ul_charge_amount li {
        display: inline;
    }

    div.div_customer_form_control input.form-control {
        width: 80%;
    }

    ul.ul_charge_amount input {
        -webkit-box-shadow: none !important;
        box-shadow: none !important;
        border-color: #e5e5e5;
        -webkit-border-radius: 0 !important;
        -moz-border-radius: 0 !important;
        border-radius: 0 !important;
        height: 25px;
        font-size: 11px !important;
        padding-top: 3px;
        padding-bottom: 3px;
        padding-left: 5px;
        padding-right: 5px;
        padding: 6px 12px;
        font-size: 14px;
        line-height: 1.42857143;
        color: #555;
        background-color: #fff;
        background-image: none;
        border: 1px solid #ccc;
        -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
        -webkit-transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
        transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s;
    }
</style>
<ul id="manage_franchise_tab_nav" class="nav nav-tabs responsive">
    <li class="active" style="margin-left: 10px;"><a href="#Account-tab" data-toggle="tab"><xms:localization
            text="Account Setup"/></a></li>
    <li><a href="#Address-tab" data-toggle="tab" class="tb1"><xms:localization text="Address"/></a></li>
    <li><a href="#Base-tab" data-toggle="tab" class="tb1"><xms:localization text="Base Rates"/></a></li>
    <%-- <li><a href="#Invoice-tab" data-toggle="tab"><xms:localization text="Invoice Options"/></a></li> --%>
    <%-- <li><a href="#Collections-tab" data-toggle="tab" class="tb1"><xms:localization text="Collections"/></a></li> --%>
    <li><a href="#Notes-tab" data-toggle="tab" class="tb1"><xms:localization text="Notes"/></a></li>
</ul>
<form id="add_franchise_form">
    <div id="manage_franchise_tab_content" class="tab-content responsive">
        <!-- Account setup -->
        <div id="Account-tab" class="tab-pane fade in active">
            <div class="row">
                <div class="portlet-body b12 b11">
                    <div class="row">
                        <div class="col-lg-4">
                            <s:hidden name="franchise.dhlInternationalAccount"/>
                            <s:hidden name="franchise.dhlInboundAccount"/>
                            <s:hidden name="franchise.dhlDomesticAccount"/>
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="3">
                                        <div class="caption b17">
                                            <xms:localization text="Account Setup"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Franchise # (*):"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.franchiseCode"
                                                                             cssClass="form-control txt_ready_only"
                                                                             id="txt_franchise_code"
                                                                             maxLength="3"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Inactive?:"/></td>
                                    <td class="td2" colspan="2"><s:checkbox name="franchise.inactive"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Exclude From ALL:"/></td>
                                    <td class="td2" colspan="2"><s:checkbox name="franchise.excludeFromAll"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Franchise Start Date:"/></td>
                                    <td class="td2" colspan="2"><s:textfield class="form-control form_datetime"
                                                                             name="franchise.franchiseStartDate"
                                                                             data-date-format="dd MM yyyy"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Group With Franchise:"/></td>
                                    <td class="td2" colspan="2"><s:select name="franchise.groupId" list="franchises"
                                                                          class="form-control" listKey="id"
                                                                          headerKey="0" headerValue=""
                                                                          listValue="name"/></td>
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
                                    <td class="td1"><xms:localization text="Web Freight Group:"/></td>
                                    <td class="td2"><s:select name="franchise.webshipGroupid" list="webshipGroups"
                                                              listValue="webshipGroupName" listKey="webshipGroupId"
                                                              cssClass="form-control" headerValue=""
                                                              headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Area:"/></td>
                                    <td class="td2" colspan="2"><s:select name="franchise.areaId" list="areas"
                                                                          listValue="areaName" listKey="areaId"
                                                                          cssClass="form-control" headerValue=""
                                                                          headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Sales Rep:"/></td>
                                    <td class="td2" colspan="2"><s:select name="franchise.salesRepId" list="salesReps"
                                                                          listValue="displayName" listKey="userId"
                                                                          cssClass="form-control" headerValue=""
                                                                          headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Business Registration #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.registrationid"
                                                                             cssClass="form-control"
                                                                             onkeypress="return formartNumber(event,this,false);"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="GST #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.gstid"
                                                                             cssClass="form-control"
                                                                             onkeypress="return formartNumber(event,this,false);"/></td>
                                </tr>

                                <tr>
                                    <td class="td1"><xms:localization text="Franchise Territory (*):"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.franchiseTerritory"
                                                                             cssClass="form-control"/></td>
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
                                    <td class="td1"><xms:localization text="DHL Account #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.dhlAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <%-- <tr>
                                    <td class="td1"><xms:localization text="Hub Account #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.hubAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="TNT Account #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.tntAccount"
                                                                             cssClass="form-control"/></td>
                                </tr> --%>
                                <tr>
                                    <td class="td1"><xms:localization text="Toll Priority Account #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.tollPriorityAccount"
                                                                             value="%{franchise.tollPriorityAccount}"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Startrack Account #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.startrackAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Startrack Dispatch ID #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.dispatchId"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other Account #:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.otherAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Booking Email Notification:"/></td>
                                    <td class="td2" colspan="2"><s:checkbox
                                            name="franchise.bookingEmailNotification"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Profit Split:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="franchise.profitShare"
                                                                             cssClass="form-control"></s:textfield></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Rejection Notes:"/></td>
                                    <td class="td2" colspan="2"><s:textarea name="franchise.rejectionNote"
                                                                            cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Invoicing Charge:"/></td>
                                    <td class="td2" colspan="2"><s:checkbox name="franchise.invoicingCharge"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Enable AGL Warranty"/>:</td>
                                    <td class="td2" colspan="2"><s:checkbox name="franchise.enableSi"/></td>
                                </tr>
                                <s:if test="franchise.id != 1">
                                    <tr>
                                        <td class="td1"><xms:localization text="DHL Int. Markup Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield name="franchise.markupRate"
                                                                                 cssClass="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td class="td1"><xms:localization text="DHL Int. Domestic Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield name="franchise.dhlDomMarkupRate"
                                                                                 cssClass="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td class="td1"><xms:localization text="TNT Int. Markup Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield
                                                name="franchise.tntInternationalMarkupRate"
                                                cssClass="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td class="td1"><xms:localization text="TNT Int. Economy Markup Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield name="listServiceMarkup[0].markupRate"
                                                                                 value="0"
                                                                                 cssClass="form-control"/></td>
                                        <s:hidden name="listServiceMarkup[0].shipmentTypeId" value="213"/>
                                        <s:hidden name="listServiceMarkup[0].serviceId" value="54"/>
                                    </tr>
                                    <tr>
                                        <td class="td1"><xms:localization text="TNT Int. Economy Markup Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield name="listServiceMarkup[1].markupRate"
                                                                                 value="0"
                                                                                 cssClass="form-control"></s:textfield></td>
                                        <s:hidden name="listServiceMarkup[1].shipmentTypeId" value="214"/>
                                        <s:hidden name="listServiceMarkup[1].serviceId" value="54"/>
                                    </tr>
                                    <tr>
                                        <td class="td1"><xms:localization text="TNT Markup Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield name="franchise.tntMarkupRate"
                                                                                 cssClass="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td class="td1"><xms:localization text="Toll Priority Markup Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield name="franchise.tollMarkupRate"
                                                                                 cssClass="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td class="td1"><xms:localization text="Toll IPEC Markup Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield name="franchise.tollIpecMarkupRate"
                                                                                 cssClass="form-control"/></td>
                                    </tr>
                                    <tr>
                                        <td class="td1"><xms:localization text="Startrack Markup Rate:"/></td>
                                        <td class="td2" colspan="2"><s:textfield name="franchise.startrackMarkupRate"
                                                                                 cssClass="form-control"/></td>
                                    </tr>
                                </s:if>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Address -->
        <div id="Address-tab" class="tab-pane fade in">
            <div class="row">
                <div class="portlet-body b12 b11">
                    <div class="row">
                        <div class="col-lg-4">
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="3" style="border-top: 0px !important">
                                        <div class="caption b17">
                                            <xms:localization text="Address"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Customer Name:"/><span class="s30">*</span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield name="address.customerName"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Contact Name:"/><span class="s30">*</span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield name="address.contactName"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Contact Title:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.contactTitle"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Address:"/><span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.address1"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1">&nbsp;</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.address2"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="City:"/><span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.city"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Country:"/><span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:select name="address.country" list="countries"
                                                                          listValue="countryName" listKey="countryId"
                                                                          cssClass="form-control uppercase"
                                                                          group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Postal Code:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.postalCode"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="State Code:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.stateCode"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Phone:"/><span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.phone"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Fax:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.fax"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td2" colspan="2"><span> <xms:localization
                                            text="Eg: first@email.com; second@email.com"/>
									</span></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.email"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Mobile:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.mobile"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Alt Contact Phone:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.alternatePhone"
                                                                             cssClass="form-control uppercase"
                                                                             group="address"/></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-lg-4">
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="" style="border-top: 0px !important">
                                        <div class="caption b17">
                                            <xms:localization text="Billing Address"/>
                                        </div>
                                    </td>
                                    <td colspan="" style="border-top: 0px !important">
                                        <div class="b21">
                                            <span class="b20"><xms:localization
                                                    text="Same As Customer Address"/> </span>
                                            <s:checkbox id="billing-same-with-customer-checkbox"
                                                        name="billingAddress.billingSameWithCustomer"
                                                        onchange="sameWithCustomerCheckBoxChange()"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Customer Name:"/><span class="s30">*</span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingCustomerName"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Contact Name:"/><span class="s30">*</span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingContactName"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Contact Title:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingContactTitle"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Address:"/><span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingAddress1"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1">&nbsp;</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingAddress2"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="City:"/><span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingCity"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Country:"/><span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:select name="billingAddress.billingCountry"
                                                                          list="countries" listValue="countryName"
                                                                          listKey="countryId"
                                                                          cssClass="form-control uppercase"
                                                                          group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Postal Code:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingPostalCode"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="State Code:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingStateCode"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Phone:"/><span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingPhone"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Fax:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingFax"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td2" colspan="2"><span> <xms:localization
                                            text="Eg: first@email.com; second@email.com"/>
									</span></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingEmail"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Mobile:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingMobile"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Alt Contact Phone:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingAlternatePhone"
                                                                             cssClass="form-control uppercase"
                                                                             group="billing-address"/></td>
                                </tr>
                            </table>
                        </div>
                        <div class="col-lg-4">
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="3" style="border-top: 0px !important">
                                        <div class="caption b17">
                                            <xms:localization text="Other Contact"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Owner:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.owner"
                                                                             cssClass="form-control uppercase"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Phone:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.ownerPhone"
                                                                             cssClass="form-control uppercase"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.ownerEmail"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="AP Contact:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.apContact"
                                                                             cssClass="form-control uppercase"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Phone:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.apPhone"
                                                                             cssClass="form-control uppercase"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.apEmail"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other Contact:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.otherContact"
                                                                             cssClass="form-control uppercase"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other Phone:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.otherPhone"
                                                                             cssClass="form-control uppercase"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.otherEmail"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other 2 Contact"/> :</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.other2Contact"
                                                                             cssClass="form-control uppercase"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other 2 Phone:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.other2Phone"
                                                                             cssClass="form-control uppercase"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email:"/></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.other2Email"
                                                                             cssClass="form-control"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Base rates tab -->
        <div id="Base-tab" class="tab-pane fade in">
            <div class="row">
                <div class="portlet-body b12 b11">
                    <div class="portlet-body b22" style="padding: 0px;">
                        <ul id="cust_base_rate_tabs_nav" class="nav nav-tabs responsive">
                            <li class="active" style="margin-left: 10px;"><a href="#cust_base_rates_general_tab"
                                                                             data-toggle="tab"><xms:localization
                                    text="General"/></a></li>
                            <li><a href="#cust_base_rates_dhl_tab" data-toggle="tab" class="tb3"><xms:localization
                                    text="DHL"/></a></li>
                            <li><a href="#cust_base_rates_dhl_dom_tab" data-toggle="tab" class="tb3"><xms:localization
                                    text="DHL Domestic"/></a></li>
                            <%-- <li><a href="#cust_base_rates_tnt_dom_tab" data-toggle="tab" class="tb3"><xms:localization
                                    text="TNT Domestic"/></a></li>
                            <li><a href="#cust_base_rates_tnt_intl_tab" data-toggle="tab" class="tb3"><xms:localization
                                    text="TNT International"/></a></li> --%>
                            <li><a href="#cust_base_rates_toll_priority_tab" data-toggle="tab"
                                   class="tb3"><xms:localization text="Toll Priority"/></a></li>
                            <li><a href="#cust_base_rates_toll_ipec_tab" data-toggle="tab" class="tb3"><xms:localization
                                    text="Toll Ipec"/></a></li>
                            <li><a href="#cust_base_rates_star_track_tab" data-toggle="tab"
                                   class="tb3"><xms:localization text="Star Track"/></a></li>
                            <li><a href="#cust_base_rates_others_tab" data-toggle="tab" class="tb3"><xms:localization
                                    text="Others"/></a></li>
                        </ul>
                        <div id="cust_base_rates_tab_content" class="tab-content responsive">
                            <div id="cust_base_rates_general_tab" class="tab-pane fade in tb2 active">
                                <div class="row">
                                    <div class="portlet-body b12 b11">
                                        <table class="s36">
                                            <tbody>
                                            <tr>
                                                <td>* = <xms:localization text="May override other settings"/></td>
                                                <td class="caption b17" colspan="2"><xms:localization
                                                        text="Base Rates"/></td>
                                            </tr>
                                            <TR>
                                                <td colspan="3" height="5"></td>
                                            </TR>
                                            <tr>
                                                <td>* <xms:localization
                                                        text="Minimum Customer Base Charge Margin"/></td>
                                                <td width="60"><s:textfield id="minimum_base_charge"
                                                                            name="franchise.minimunBaseCharge"
                                                                            cssClass="form-control"/></td>
                                                <td>%</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <s:set var="global_index" value="-1"/>
                            <div id="cust_base_rates_dhl_tab" class="tab-pane fade in">
                                <div class="row">
                                    <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                        <!-- Print Rate Sheet -->
                                        <div class="form-group">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Print Rate Sheets"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckAll($(this))">
                                                            <xms:localization text="Check All"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckNone($(this))">
                                                            <xms:localization text="Check None"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheet($(this))">
                                                            <xms:localization text="Print Checked Rate Sheets"/>
                                                        </button>
                                                    </td>
                                                    <td><input type="checkbox" data-type="pdf-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Pdf"/></td>
                                                    <td><input type="checkbox" data-type="excel-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Excel"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <!-- Service Type List -->
                                        <s:iterator value="dhl" var="sv">
                                            <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                <s:iterator value="custBaseRates" status="stats">
                                                    <s:set var="global_index" value="%{#global_index + 1}"/>
                                                    <!-- Customer base rate -->
                                                    <div class="form-group base-rate-row"
                                                         data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                         data-weight="<s:property value="weight"/>">
                                                        <div class="base-rate">
                                                            <div class="well well-sm pull-left c33" style="width: 100%">
                                                                <div class="pull-left c32"
                                                                     style="padding: 5px; width: 250px;">
                                                                    <s:if test="#stats.index == 0">
                                                                        <span class="br-display-name"><s:property
                                                                                value="displayName"/></span>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:if test="#stats.index == 0">
                                                                        <input data-type="print-rate-sheet-checkbox"
                                                                               type="checkbox" data-customercode="0"
                                                                               data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                               data-content="<s:property value="%{#sv.content}" />"
                                                                               data-bound="<s:property value="%{#sv.bound}" />"
                                                                               data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                               style="margin-top: 6px;"/>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-remove"
                                                                     <s:if test="custBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span data-group="br-remove"><i
                                                                            onclick="removeWeightBreak($(this))"
                                                                            style="color: red; font-size: 18px; padding-top: 3px;"
                                                                            class="fa fa-times s10 b3"></i></span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-weight"
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span
                                                                            data-group="br-weight"><s:property
                                                                            value="weight"/>+</span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                       class="fa fa-chevron-circle-right s10 b3"
                                                                       style="font-size: 18px; padding-top: 3px;"
                                                                       data-index="<s:property value="#global_index"/>"></i>
                                                                </div>
                                                                <div class="pull-left c32" data-group="sel-rate-type">
                                                                    <s:select data-index="%{#global_index}"
                                                                              name="customerBaseRates[%{#global_index}].rateType"
                                                                              list="rateTypes" listKey="id"
                                                                              listValue="name" value="%{rateType}"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                                <div class="pull-left c32" data-group="base-rate">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     data-group="br-rate"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     readonly="true"
                                                                                     data-group="br-rate"/>
                                                                    </s:else>
                                                                </div>
                                                                <div class="pull-left c32a">%</div>
                                                                <div class="pull-left c32a">
                                                                    <a href="javascript:void(0)"
                                                                       onclick="viewRateSheet(0,<s:property
                                                                               value="%{#sv.shipmentTypeId}"/>,
                                                                           <s:property value="%{#sv.content}"/>,
                                                                           <s:property value="%{#sv.bound}"/>,
                                                                           <s:property value="%{#sv.serviceId}"/>)"
                                                                       class="b19"
                                                                       data-sheet-id="<s:property value="rateSheetId"/>"
                                                                       data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                       data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                       data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                            text="View"/></b></u></a>
                                                                </div>
                                                                <div class="pull-left c32a">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-plus-square s10 b3"
                                                                           data-show="close"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-minus-square s10 b3"
                                                                           data-show="open"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:else>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- Customer base rate detail -->
                                                        <div class="form-group pull-left base-rate"
                                                             style="width: 100%; overflow: auto; <s:if
                                                                     test="zoneCheck=='false'">display: none;</s:if>">
                                                            <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                                <li class="pull-left c35" style="margin-left: 40px;">
                                                                    <s:checkbox data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="customerBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                <li class="pull-left c35" style="padding: 5px;">
                                                                    <xms:localization text="By Zone"/> :
                                                                </li>
                                                                <s:iterator value="custBaseRateDetails" status="dStats">
                                                                    <li>
                                                                        <div class="pull-left c34" data-group="zone">
                                                                            <input data-index="<s:property value="#global_index"/>"
                                                                                   name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].zone"
                                                                                   class="form-control alloptions text-center"
                                                                                   maxlength="25"
                                                                                   value="<s:property value="zone"/>"
                                                                                   <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                   <s:else>readonly</s:else> type="text"
                                                                                   data-group="zone-name"/> <input
                                                                                data-index="<s:property value="#global_index"/>"
                                                                                name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].rate"
                                                                                <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                class="form-control alloptions text-center"
                                                                                type="text" maxlength="25"
                                                                                value="<s:property value="rate"/>"
                                                                                data-group="zone-rate">
                                                                        </div>
                                                                    </li>
                                                                </s:iterator>
                                                            </ul>
                                                        </div>
                                                        <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].baserateDescription"
                                                                    value="%{displayName}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].weight"
                                                                      value="%{weight}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                    value="%{shipmentTypeId}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].customerCode"
                                                                    value="%{customerCode}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].content"
                                                                      value="%{content}" data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].bound"
                                                                      value="%{bound}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].carrierId"
                                                                    value="%{serviceId}" data-index="%{#global_index}"/>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <!-- DHL Dom -->
                            <div id="cust_base_rates_dhl_dom_tab" class="tab-pane fade in">
                                <div class="row">
                                    <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                        <!-- Print Rate Sheet -->
                                        <div class="form-group">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Print Rate Sheets"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckAll($(this))">
                                                            <xms:localization text="Check All"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckNone($(this))">
                                                            <xms:localization text="Check None"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheet($(this))">
                                                            <xms:localization text="Print Checked Rate Sheets"/>
                                                        </button>
                                                    </td>
                                                    <td><input type="checkbox" data-type="pdf-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Pdf"/></td>
                                                    <td><input type="checkbox" data-type="excel-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Excel"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <!-- Service Type List -->
                                        <s:iterator value="dhlDom" var="sv">
                                            <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                <s:iterator value="custBaseRates" status="stats">
                                                    <s:set var="global_index" value="%{#global_index + 1}"/>
                                                    <!-- Customer base rate -->
                                                    <div class="form-group base-rate-row"
                                                         data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                         data-weight="<s:property value="weight"/>">
                                                        <div class="base-rate">
                                                            <div class="well well-sm pull-left c33" style="width: 100%">
                                                                <div class="pull-left c32"
                                                                     style="padding: 5px; width: 250px;">
                                                                    <s:if test="#stats.index == 0">
                                                                        <span class="br-display-name"><s:property
                                                                                value="displayName"/></span>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:if test="#stats.index == 0">
                                                                        <input data-type="print-rate-sheet-checkbox"
                                                                               type="checkbox" data-customercode="0"
                                                                               data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                               data-content="<s:property value="%{#sv.content}" />"
                                                                               data-bound="<s:property value="%{#sv.bound}" />"
                                                                               data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                               style="margin-top: 6px;"/>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-remove"
                                                                     <s:if test="custBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span data-group="br-remove"><i
                                                                            onclick="removeWeightBreak($(this))"
                                                                            style="color: red; font-size: 18px; padding-top: 3px;"
                                                                            class="fa fa-times s10 b3"></i></span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-weight"
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span
                                                                            data-group="br-weight"><s:property
                                                                            value="weight"/>+</span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                       class="fa fa-chevron-circle-right s10 b3"
                                                                       style="font-size: 18px; padding-top: 3px;"
                                                                       data-index="<s:property value="#global_index"/>"></i>
                                                                </div>
                                                                <div class="pull-left c32" data-group="sel-rate-type">
                                                                    <s:select data-index="%{#global_index}"
                                                                              name="customerBaseRates[%{#global_index}].rateType"
                                                                              list="rateTypes" listKey="id"
                                                                              listValue="name" value="%{rateType}"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                                <div class="pull-left c32" data-group="base-rate">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     data-group="br-rate"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     readonly="true"
                                                                                     data-group="br-rate"/>
                                                                    </s:else>
                                                                </div>
                                                                <div class="pull-left c32a">%</div>
                                                                <div class="pull-left c32a">
                                                                    <a href="javascript:void(0)"
                                                                       onclick="viewRateSheet(0,<s:property
                                                                               value="%{#sv.shipmentTypeId}"/>,
                                                                           <s:property value="%{#sv.content}"/>,
                                                                           <s:property value="%{#sv.bound}"/>,
                                                                           <s:property value="%{#sv.serviceId}"/>)"
                                                                       class="b19"
                                                                       data-sheet-id="<s:property value="rateSheetId"/>"
                                                                       data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                       data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                       data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                            text="View"/></b></u></a>
                                                                </div>
                                                                <div class="pull-left c32a">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-plus-square s10 b3"
                                                                           data-show="close"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-minus-square s10 b3"
                                                                           data-show="open"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:else>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- Customer base rate detail -->
                                                        <div class="form-group pull-left base-rate"
                                                             style="width: 100%; overflow: auto; <s:if
                                                                     test="zoneCheck=='false'">display: none;</s:if>">
                                                            <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                                <li class="pull-left c35" style="margin-left: 40px;">
                                                                    <s:checkbox data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="customerBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                <li class="pull-left c35" style="padding: 5px;">
                                                                    <xms:localization text="By Zone"/> :
                                                                </li>
                                                                <s:iterator value="custBaseRateDetails" status="dStats">
                                                                    <li>
                                                                        <div class="pull-left c34" data-group="zone">
                                                                            <input data-index="<s:property value="#global_index"/>"
                                                                                   name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].zone"
                                                                                   class="form-control alloptions text-center"
                                                                                   maxlength="25"
                                                                                   value="<s:property value="zone"/>"
                                                                                   <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                   <s:else>readonly</s:else> type="text"
                                                                                   data-group="zone-name"/> <input
                                                                                data-index="<s:property value="#global_index"/>"
                                                                                name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].rate"
                                                                                <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                class="form-control alloptions text-center"
                                                                                type="text" maxlength="25"
                                                                                value="<s:property value="rate"/>"
                                                                                data-group="zone-rate">
                                                                        </div>
                                                                    </li>
                                                                </s:iterator>
                                                            </ul>
                                                        </div>
                                                        <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].baserateDescription"
                                                                    value="%{displayName}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].weight"
                                                                      value="%{weight}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                    value="%{shipmentTypeId}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].customerCode"
                                                                    value="%{customerCode}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].content"
                                                                      value="%{content}" data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].bound"
                                                                      value="%{bound}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].carrierId"
                                                                    value="%{serviceId}" data-index="%{#global_index}"/>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <!-- TNT Dom -->
                            <div id="cust_base_rates_tnt_dom_tab" class="tab-pane fade in">
                                <div class="row">
                                    <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                        <!-- Orgin -->
                                        <div class="form-group">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Origin"/></td>
                                                    <td><s:select id="tntDomColumnName" cssClass="form-control"
                                                                  list="tntDomColumns"
                                                                  listValue="zoneName + ' - ' + zoneCode"
                                                                  listKey="zoneCode"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <!-- Print Rate Sheet -->
                                        <div class="form-group">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Print Rate Sheets"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckAll($(this))">
                                                            <xms:localization text="Check All"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckNone($(this))">
                                                            <xms:localization text="Check None"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheet($(this))">
                                                            <xms:localization text="Print Checked Rate Sheets"/>
                                                        </button>
                                                    </td>
                                                    <td><input type="checkbox" data-type="pdf-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Pdf"/></td>
                                                    <td><input type="checkbox" data-type="excel-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Excel"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <!-- Service Type List -->
                                        <s:iterator value="tntDom" var="sv">
                                            <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                <s:iterator value="custBaseRates" status="stats">
                                                    <s:set var="global_index" value="%{#global_index + 1}"/>
                                                    <!-- Customer base rate -->
                                                    <div class="form-group base-rate-row"
                                                         data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                         data-weight="<s:property value="weight"/>">
                                                        <div class="base-rate">
                                                            <div class="well well-sm pull-left c33" style="width: 100%">
                                                                <div class="pull-left c32"
                                                                     style="padding: 5px; width: 250px;">
                                                                    <s:if test="#stats.index == 0">
                                                                        <span class="br-display-name"><s:property
                                                                                value="displayName"/></span>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:if test="#stats.index == 0">
                                                                        <input data-type="print-rate-sheet-checkbox"
                                                                               type="checkbox" data-customercode="0"
                                                                               data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                               data-content="<s:property value="%{#sv.content}" />"
                                                                               data-bound="<s:property value="%{#sv.bound}" />"
                                                                               data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                               style="margin-top: 6px;"/>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-remove"
                                                                     <s:if test="custBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span data-group="br-remove"><i
                                                                            onclick="removeWeightBreak($(this))"
                                                                            style="color: red; font-size: 18px; padding-top: 3px;"
                                                                            class="fa fa-times s10 b3"></i></span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-weight"
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span
                                                                            data-group="br-weight"><s:property
                                                                            value="weight"/>+</span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                       class="fa fa-chevron-circle-right s10 b3"
                                                                       style="font-size: 18px; padding-top: 3px;"
                                                                       data-index="<s:property value="#global_index"/>"></i>
                                                                </div>
                                                                <div class="pull-left c32" data-group="sel-rate-type">
                                                                    <s:select data-index="%{#global_index}"
                                                                              name="customerBaseRates[%{#global_index}].rateType"
                                                                              list="rateTypes" listKey="id"
                                                                              listValue="name" value="%{rateType}"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                                <div class="pull-left c32" data-group="base-rate">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     data-group="br-rate"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     readonly="true"
                                                                                     data-group="br-rate"/>
                                                                    </s:else>
                                                                </div>
                                                                <div class="pull-left c32a">%</div>
                                                                <div class="pull-left c32a">
                                                                    <a href="javascript:void(0)"
                                                                       onclick="viewRateSheet(0,<s:property
                                                                               value="%{#sv.shipmentTypeId}"/>,
                                                                           <s:property value="%{#sv.content}"/>,
                                                                           <s:property value="%{#sv.bound}"/>,
                                                                           <s:property value="%{#sv.serviceId}"/>)"
                                                                       class="b19"
                                                                       data-sheet-id="<s:property value="rateSheetId"/>"
                                                                       data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                       data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                       data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                            text="View"/></b></u></a>
                                                                </div>
                                                                <div class="pull-left c32a">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-plus-square s10 b3"
                                                                           data-show="close"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-minus-square s10 b3"
                                                                           data-show="open"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:else>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- Customer base rate detail -->
                                                        <div class="form-group pull-left base-rate"
                                                             style="width: 100%; overflow: auto; <s:if
                                                                     test="zoneCheck=='false'">display: none;</s:if>">
                                                            <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                                <li class="pull-left c35" style="margin-left: 40px;">
                                                                    <s:checkbox data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="customerBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                <li class="pull-left c35" style="padding: 5px;">
                                                                    <xms:localization text="By Zone"/> :
                                                                </li>
                                                                <s:iterator value="custBaseRateDetails" status="dStats">
                                                                    <li>
                                                                        <div class="pull-left c34" data-group="zone">
                                                                            <input data-index="<s:property value="#global_index"/>"
                                                                                   name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].zone"
                                                                                   class="form-control alloptions text-center"
                                                                                   maxlength="25"
                                                                                   value="<s:property value="zone"/>"
                                                                                   <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                   <s:else>readonly</s:else> type="text"
                                                                                   data-group="zone-name"/> <input
                                                                                data-index="<s:property value="#global_index"/>"
                                                                                name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].rate"
                                                                                <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                class="form-control alloptions text-center"
                                                                                type="text" maxlength="25"
                                                                                value="<s:property value="rate"/>"
                                                                                data-group="zone-rate">
                                                                        </div>
                                                                    </li>
                                                                </s:iterator>
                                                            </ul>
                                                        </div>
                                                        <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].baserateDescription"
                                                                    value="%{displayName}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].weight"
                                                                      value="%{weight}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                    value="%{shipmentTypeId}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].customerCode"
                                                                    value="%{customerCode}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].content"
                                                                      value="%{content}" data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].bound"
                                                                      value="%{bound}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].carrierId"
                                                                    value="%{serviceId}" data-index="%{#global_index}"/>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <!-- TNT Intl -->
                            <div id="cust_base_rates_tnt_intl_tab" class="tab-pane fade in">
                                <div class="row">
                                    <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                        <!-- Print Rate Sheet -->
                                        <div class="form-group">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Print Rate Sheets"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckAll($(this))">
                                                            <xms:localization text="Check All"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckNone($(this))">
                                                            <xms:localization text="Check None"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheet($(this))">
                                                            <xms:localization text="Print Checked Rate Sheets"/>
                                                        </button>
                                                    </td>
                                                    <td><input type="checkbox" data-type="pdf-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Pdf"/></td>
                                                    <td><input type="checkbox" data-type="excel-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Excel"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <!-- Service Type List -->
                                        <s:iterator value="tntIntl" var="sv">
                                            <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                <s:iterator value="custBaseRates" status="stats">
                                                    <s:set var="global_index" value="%{#global_index + 1}"/>
                                                    <!-- Customer base rate -->
                                                    <div class="form-group base-rate-row"
                                                         data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                         data-weight="<s:property value="weight"/>">
                                                        <div class="base-rate">
                                                            <div class="well well-sm pull-left c33" style="width: 100%">
                                                                <div class="pull-left c32"
                                                                     style="padding: 5px; width: 250px;">
                                                                    <s:if test="#stats.index == 0">
                                                                        <span class="br-display-name"><s:property
                                                                                value="displayName"/></span>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:if test="#stats.index == 0">
                                                                        <input data-type="print-rate-sheet-checkbox"
                                                                               type="checkbox" data-customercode="0"
                                                                               data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                               data-content="<s:property value="%{#sv.content}" />"
                                                                               data-bound="<s:property value="%{#sv.bound}" />"
                                                                               data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                               style="margin-top: 6px;"/>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-remove"
                                                                     <s:if test="custBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span data-group="br-remove"><i
                                                                            onclick="removeWeightBreak($(this))"
                                                                            style="color: red; font-size: 18px; padding-top: 3px;"
                                                                            class="fa fa-times s10 b3"></i></span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-weight"
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span
                                                                            data-group="br-weight"><s:property
                                                                            value="weight"/>+</span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                       class="fa fa-chevron-circle-right s10 b3"
                                                                       style="font-size: 18px; padding-top: 3px;"
                                                                       data-index="<s:property value="#global_index"/>"></i>
                                                                </div>
                                                                <div class="pull-left c32" data-group="sel-rate-type">
                                                                    <s:select data-index="%{#global_index}"
                                                                              name="customerBaseRates[%{#global_index}].rateType"
                                                                              list="rateTypes" listKey="id"
                                                                              listValue="name" value="%{rateType}"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                                <div class="pull-left c32" data-group="base-rate">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     data-group="br-rate"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     readonly="true"
                                                                                     data-group="br-rate"/>
                                                                    </s:else>
                                                                </div>
                                                                <div class="pull-left c32a">%</div>
                                                                <div class="pull-left c32a">
                                                                    <a href="javascript:void(0)"
                                                                       onclick="viewRateSheet(0,<s:property
                                                                               value="%{#sv.shipmentTypeId}"/>,
                                                                           <s:property value="%{#sv.content}"/>,
                                                                           <s:property value="%{#sv.bound}"/>,
                                                                           <s:property value="%{#sv.serviceId}"/>)"
                                                                       class="b19"
                                                                       data-sheet-id="<s:property value="rateSheetId"/>"
                                                                       data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                       data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                       data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                            text="View"/></b></u></a>
                                                                </div>
                                                                <div class="pull-left c32a">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-plus-square s10 b3"
                                                                           data-show="close"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-minus-square s10 b3"
                                                                           data-show="open"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:else>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- Customer base rate detail -->
                                                        <div class="form-group pull-left base-rate"
                                                             style="width: 100%; overflow: auto; <s:if
                                                                     test="zoneCheck=='false'">display: none;</s:if>">
                                                            <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                                <li class="pull-left c35" style="margin-left: 40px;">
                                                                    <s:checkbox data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="customerBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                <li class="pull-left c35" style="padding: 5px;">
                                                                    <xms:localization text="By Zone"/> :
                                                                </li>
                                                                <s:iterator value="custBaseRateDetails" status="dStats">
                                                                    <li>
                                                                        <div class="pull-left c34" data-group="zone">
                                                                            <input data-index="<s:property value="#global_index"/>"
                                                                                   name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].zone"
                                                                                   class="form-control alloptions text-center"
                                                                                   maxlength="25"
                                                                                   value="<s:property value="zone"/>"
                                                                                   <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                   <s:else>readonly</s:else> type="text"
                                                                                   data-group="zone-name"/> <input
                                                                                data-index="<s:property value="#global_index"/>"
                                                                                name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].rate"
                                                                                <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                class="form-control alloptions text-center"
                                                                                type="text" maxlength="25"
                                                                                value="<s:property value="rate"/>"
                                                                                data-group="zone-rate">
                                                                        </div>
                                                                    </li>
                                                                </s:iterator>
                                                            </ul>
                                                        </div>
                                                        <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].baserateDescription"
                                                                    value="%{displayName}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].weight"
                                                                      value="%{weight}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                    value="%{shipmentTypeId}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].customerCode"
                                                                    value="%{customerCode}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].content"
                                                                      value="%{content}" data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].bound"
                                                                      value="%{bound}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].carrierId"
                                                                    value="%{serviceId}" data-index="%{#global_index}"/>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <!-- Toll priority -->
                            <div id="cust_base_rates_toll_priority_tab" class="tab-pane fade in">
                                <div class="row">
                                    <div class="portlet-body b12 b11">
                                        <!-- Service Type List -->
                                        <s:iterator value="tollPriority">
                                            <div>
                                                <s:iterator value="custBaseRates" status="stats">
                                                    <s:set var="global_index" value="%{#global_index + 1}"/>
                                                    <!-- Customer base rate -->
                                                    <div class="form-group base-rate-row"
                                                         data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                         data-weight="<s:property value="weight"/>">
                                                        <div class="base-rate">
                                                            <div class="well well-sm pull-left c33" style="width: 100%">
                                                                <div class="pull-left c32"
                                                                     style="padding: 5px; width: 250px;">
                                                                    <s:if test="#stats.index == 0">
                                                                        <span class="br-display-name"><s:property
                                                                                value="displayName"/></span>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32" data-group="sel-rate-type">
                                                                    <s:select data-index="%{#global_index}"
                                                                              name="customerBaseRates[%{#global_index}].rateType"
                                                                              list="rateTypes" listKey="id"
                                                                              listValue="name" value="%{rateType}"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="customerBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 data-group="br-rate"/>
                                                                </div>
                                                                <div class="pull-left c32a">%</div>
                                                            </div>
                                                        </div>
                                                        <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].baserateDescription"
                                                                    value="%{displayName}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].weight"
                                                                      value="%{weight}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                    value="%{shipmentTypeId}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].customerCode"
                                                                    value="%{customerCode}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].content"
                                                                      value="%{content}" data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].bound"
                                                                      value="%{bound}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].carrierId"
                                                                    value="%{serviceId}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].zoneCheck"
                                                                    value="%{zoneCheck}" data-index="%{#global_index}"/>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <!-- Toll Ipec -->
                            <div id="cust_base_rates_toll_ipec_tab" class="tab-pane fade in">
                                <div class="row">
                                    <div class="portlet-body b12 b11">
                                        <!-- Service Type List -->
                                        <s:iterator value="tollIpec">
                                            <div>
                                                <s:iterator value="custBaseRates" status="stats">
                                                    <s:set var="global_index" value="%{#global_index + 1}"/>
                                                    <!-- Customer base rate -->
                                                    <div class="form-group base-rate-row"
                                                         data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                         data-weight="<s:property value="weight"/>">
                                                        <div class="base-rate">
                                                            <div class="well well-sm pull-left c33" style="width: 100%">
                                                                <div class="pull-left c32"
                                                                     style="padding: 5px; width: 250px;">
                                                                    <s:if test="#stats.index == 0">
                                                                        <span class="br-display-name"><s:property
                                                                                value="displayName"/></span>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32" data-group="sel-rate-type">
                                                                    <s:select data-index="%{#global_index}"
                                                                              name="customerBaseRates[%{#global_index}].rateType"
                                                                              list="rateTypes" listKey="id"
                                                                              listValue="name" value="%{rateType}"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:textfield data-index="%{#global_index}"
                                                                                 name="customerBaseRates[%{#global_index}].rate"
                                                                                 value="%{rate}"
                                                                                 cssClass="form-control alloptions"
                                                                                 maxlength="25" cssStyle="width: 50px;"
                                                                                 data-group="br-rate"/>
                                                                </div>
                                                                <div class="pull-left c32a">%</div>
                                                            </div>
                                                        </div>
                                                        <!-- Customer base rate detail -->
                                                        <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].baserateDescription"
                                                                    value="%{displayName}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].weight"
                                                                      value="%{weight}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                    value="%{shipmentTypeId}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].customerCode"
                                                                    value="%{customerCode}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].content"
                                                                      value="%{content}" data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].bound"
                                                                      value="%{bound}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].carrierId"
                                                                    value="%{serviceId}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].zoneCheck"
                                                                    value="%{zoneCheck}" data-index="%{#global_index}"/>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <!-- Star Track -->
                            <div id="cust_base_rates_star_track_tab" class="tab-pane fade in">
                                <div class="row">
                                    <div data-type="print-rate-sheet" class="portlet-body b12 b11">
                                        <!-- Orgin -->
                                        <div class="form-group">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Origin"/></td>
                                                    <td><s:select id="starTrackColumnName" cssClass="form-control"
                                                                  list="starTrackColumns"
                                                                  listValue="zoneName + ' - ' + zoneCode"
                                                                  listKey="zoneCode"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <!-- Print Rate Sheet -->
                                        <div class="form-group">
                                            <table class="s36">
                                                <tr>
                                                    <td><xms:localization text="Print Rate Sheets"/></td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckAll($(this))">
                                                            <xms:localization text="Check All"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheetCheckNone($(this))">
                                                            <xms:localization text="Check None"/>
                                                        </button>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button"
                                                                onclick="printRateSheet($(this))">
                                                            <xms:localization text="Print Checked Rate Sheets"/>
                                                        </button>
                                                    </td>
                                                    <td><input type="checkbox" data-type="pdf-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Pdf"/></td>
                                                    <td><input type="checkbox" data-type="excel-format"
                                                               onchange="rateSheetFormatChange($(this))"/></td>
                                                    <td><xms:localization text="Excel"/></td>
                                                </tr>
                                            </table>
                                        </div>
                                        <!-- Service Type List -->
                                        <s:iterator value="starTrack" var="sv">
                                            <div id="shipment_type_<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>">
                                                <s:iterator value="custBaseRates" status="stats">
                                                    <s:set var="global_index" value="%{#global_index + 1}"/>
                                                    <!-- Customer base rate -->
                                                    <div class="form-group base-rate-row"
                                                         data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                         data-weight="<s:property value="weight"/>">
                                                        <div class="base-rate">
                                                            <div class="well well-sm pull-left c33" style="width: 100%">
                                                                <div class="pull-left c32"
                                                                     style="padding: 5px; width: 250px;">
                                                                    <s:if test="#stats.index == 0">
                                                                        <span class="br-display-name"><s:property
                                                                                value="displayName"/></span>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <s:if test="#stats.index == 0">
                                                                        <input data-type="print-rate-sheet-checkbox"
                                                                               type="checkbox" data-customercode="0"
                                                                               data-shipmenttypeid="<s:property value="%{#sv.shipmentTypeId}" />"
                                                                               data-content="<s:property value="%{#sv.content}" />"
                                                                               data-bound="<s:property value="%{#sv.bound}" />"
                                                                               data-serviceid="<s:property value="%{#sv.serviceId}" />"
                                                                               style="margin-top: 6px;"/>
                                                                    </s:if>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-remove"
                                                                     <s:if test="custBaseRates.size() != 1 && #stats.index == 0">style="display: none;"</s:if>
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span data-group="br-remove"><i
                                                                            onclick="removeWeightBreak($(this))"
                                                                            style="color: red; font-size: 18px; padding-top: 3px;"
                                                                            class="fa fa-times s10 b3"></i></span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32" data-group="br-weight"
                                                                     <s:if test="custBaseRates.size() == 1">style="display: none;"</s:if>>
                                                                    <a class="b18"> <b><span
                                                                            data-group="br-weight"><s:property
                                                                            value="weight"/>+</span></b>
                                                                    </a>
                                                                </div>
                                                                <div class="pull-left c32">
                                                                    <i onclick="addWeightBreak($(this))" id="dwed-link"
                                                                       class="fa fa-chevron-circle-right s10 b3"
                                                                       style="font-size: 18px; padding-top: 3px;"
                                                                       data-index="<s:property value="#global_index"/>"></i>
                                                                </div>
                                                                <div class="pull-left c32" data-group="sel-rate-type">
                                                                    <s:select data-index="%{#global_index}"
                                                                              name="customerBaseRates[%{#global_index}].rateType"
                                                                              list="rateTypes" listKey="id"
                                                                              listValue="name" value="%{rateType}"
                                                                              cssClass="form-control"/>
                                                                </div>
                                                                <div class="pull-left c32" data-group="base-rate">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     data-group="br-rate"/>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:textfield data-index="%{#global_index}"
                                                                                     name="customerBaseRates[%{#global_index}].rate"
                                                                                     value="%{rate}"
                                                                                     cssClass="form-control alloptions"
                                                                                     maxlength="25"
                                                                                     cssStyle="width: 50px;"
                                                                                     readonly="true"
                                                                                     data-group="br-rate"/>
                                                                    </s:else>
                                                                </div>
                                                                <div class="pull-left c32a">%</div>
                                                                <div class="pull-left c32a">
                                                                    <a href="javascript:void(0)"
                                                                       onclick="viewRateSheet(0,<s:property
                                                                               value="%{#sv.shipmentTypeId}"/>,
                                                                           <s:property value="%{#sv.content}"/>,
                                                                           <s:property value="%{#sv.bound}"/>,
                                                                           <s:property value="%{#sv.serviceId}"/>)"
                                                                       class="b19"
                                                                       data-sheet-id="<s:property value="rateSheetId"/>"
                                                                       data-perweight-sheet-id="<s:property value="perWeightRateSheetId"/>"
                                                                       data-nc-sheet-id="<s:property value="ncRateSheetId"/>"
                                                                       data-nc-perweight-sheet-id="<s:property value="ncPerWeightRateSheetId"/>"><u><b><xms:localization
                                                                            text="View"/></b></u></a>
                                                                </div>
                                                                <div class="pull-left c32a">
                                                                    <s:if test="zoneCheck == 'false'">
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-plus-square s10 b3"
                                                                           data-show="close"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <i id="show-zones" onclick="showZones($(this))"
                                                                           class="fa fa-minus-square s10 b3"
                                                                           data-show="open"
                                                                           style="font-size: 18px;"></i>
                                                                    </s:else>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <!-- Customer base rate detail -->
                                                        <div class="form-group pull-left base-rate"
                                                             style="width: 100%; overflow: auto; <s:if
                                                                     test="zoneCheck=='false'">display: none;</s:if>">
                                                            <ul class="c36" style="width: 14324px; overflow: hidden;">
                                                                <li class="pull-left c35" style="margin-left: 40px;">
                                                                    <s:checkbox data-group="zone-check"
                                                                                data-index="%{#global_index}"
                                                                                name="customerBaseRates[%{#global_index}].zoneCheck"
                                                                                value="%{zoneCheck}" fieldValue="true"
                                                                                onclick="onCheckZoneCheck($(this),$(this).parents('ul'))"
                                                                                cssStyle="margin-top: 7px;"/></li>
                                                                <li class="pull-left c35" style="padding: 5px;">
                                                                    <xms:localization text="By Zone"/> :
                                                                </li>
                                                                <s:iterator value="custBaseRateDetails" status="dStats">
                                                                    <li>
                                                                        <div class="pull-left c34" data-group="zone">
                                                                            <input data-index="<s:property value="#global_index"/>"
                                                                                   name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].zone"
                                                                                   class="form-control alloptions text-center"
                                                                                   maxlength="25"
                                                                                   value="<s:property value="zone"/>"
                                                                                   <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                   <s:else>readonly</s:else> type="text"
                                                                                   data-group="zone-name"/> <input
                                                                                data-index="<s:property value="#global_index"/>"
                                                                                name="customerBaseRates[<s:property value="#global_index"/>].customerBaseRateDetails[<s:property value="#dStats.index"/>].rate"
                                                                                <s:if test="zoneCheck == 'false'">disabled="disabled"</s:if>
                                                                                class="form-control alloptions text-center"
                                                                                type="text" maxlength="25"
                                                                                value="<s:property value="rate"/>"
                                                                                data-group="zone-rate">
                                                                        </div>
                                                                    </li>
                                                                </s:iterator>
                                                            </ul>
                                                        </div>
                                                        <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].baserateDescription"
                                                                    value="%{displayName}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].weight"
                                                                      value="%{weight}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                    value="%{shipmentTypeId}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].customerCode"
                                                                    value="%{customerCode}"
                                                                    data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].content"
                                                                      value="%{content}" data-index="%{#global_index}"/>
                                                            <s:hidden name="customerBaseRates[%{#global_index}].bound"
                                                                      value="%{bound}" data-index="%{#global_index}"/>
                                                            <s:hidden
                                                                    name="customerBaseRates[%{#global_index}].carrierId"
                                                                    value="%{serviceId}" data-index="%{#global_index}"/>
                                                        </div>
                                                    </div>
                                                </s:iterator>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                            <!-- Others -->
                            <div id="cust_base_rates_others_tab" class="tab-pane fade in">
                                <div class="row">
                                    <div class="portlet-body b12 b11">
                                        <!-- Service Type List -->
                                        <s:iterator value="others">
                                            <div>
                                                <s:set var="global_index" value="%{#global_index + 1}"/>
                                                <!-- Customer base rate -->
                                                <div class="form-group base-rate-row"
                                                     data-baseRate="<s:property value="shipmentTypeId"/>_<s:property value="content"/>_<s:property value="bound"/>"
                                                     data-weight="<s:property value="weight"/>">
                                                    <div class="base-rate">
                                                        <div class="well well-sm pull-left c33" style="width: 100%">
                                                            <div class="pull-left c32"
                                                                 style="padding: 5px; width: 250px;">
                                                                <s:property value="serviceName"/>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:if test="#stats.index == 0">
                                                                    <input type="checkbox" value=""
                                                                           style="margin-top: 6px;">
                                                                </s:if>
                                                            </div>
                                                            <div class="pull-left c32" data-group="sel-rate-type">
                                                                <s:select data-index="%{#global_index}"
                                                                          name="customerBaseRates[%{#global_index}].rateType"
                                                                          list="rateTypes" listKey="id" listValue="name"
                                                                          value="%{rateType}" cssClass="form-control"/>
                                                            </div>
                                                            <div class="pull-left c32">
                                                                <s:textfield data-index="%{#global_index}"
                                                                             name="customerBaseRates[%{#global_index}].rate"
                                                                             value="%{rate}"
                                                                             cssClass="form-control alloptions"
                                                                             maxlength="25" cssStyle="width: 50px;"
                                                                             data-group="br-rate"/>
                                                            </div>
                                                            <div class="pull-left c32a">%</div>
                                                        </div>
                                                    </div>
                                                    <!-- Customer base rate detail -->
                                                    <div id="hidden-input-<s:property value="shipmentTypeId"/>-<s:property value="content"/>-<s:property value="bound"/>">
                                                        <s:hidden
                                                                name="customerBaseRates[%{#global_index}].baserateDescription"
                                                                value="%{displayName}" data-index="%{#global_index}"/>
                                                        <s:hidden name="customerBaseRates[%{#global_index}].weight"
                                                                  value="%{weight}" data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="customerBaseRates[%{#global_index}].shipmentTypeId"
                                                                value="%{shipmentTypeId}"
                                                                data-index="%{#global_index}"/>
                                                        <s:hidden
                                                                name="customerBaseRates[%{#global_index}].customerCode"
                                                                value="%{customerCode}" data-index="%{#global_index}"/>
                                                        <s:hidden name="customerBaseRates[%{#global_index}].content"
                                                                  value="%{content}" data-index="%{#global_index}"/>
                                                        <s:hidden name="customerBaseRates[%{#global_index}].bound"
                                                                  value="%{bound}" data-index="%{#global_index}"/>
                                                        <s:hidden name="customerBaseRates[%{#global_index}].carrierId"
                                                                  value="%{serviceId}" data-index="%{#global_index}"/>
                                                        <s:hidden name="customerBaseRates[%{#global_index}].zoneCheck"
                                                                  value="%{zoneCheck}" data-index="%{#global_index}"/>
                                                    </div>
                                                </div>
                                            </div>
                                        </s:iterator>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Invoice option tab -->
        <div id="Invoice-tab" class="tab-pane fade in">
            <div class="row">
                <div class="portlet-body b12 b11 col-md-12">
                    <div class="col-lg-4">
                        <s:hidden name="franchise.invoicingFee"/>
                        <table class="table">
                            <tbody>
                            <tr>
                                <td style="border-top: 0px !important" colspan="3">
                                    <div class="caption b17">
                                        <xms:localization text="Invoicing Options"/>
                                    </div>
                                    <s:hidden name="collection.franchiseCode"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Invoice Sorting"/></td>
                                <td colspan="2" class="td2"><s:select name="franchise.invoiceSorting"
                                                                      cssClass="form-control"
                                                                      list="invoiceSortingOptions" listValue="name"
                                                                      listKey="id"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Invoice Terms"/></td>
                                <td colspan="2" class="td2"><s:select name="franchise.invoiceTerms"
                                                                      cssClass="form-control" list="invoiceTerms"
                                                                      listKey="invoiceTermId"
                                                                      listValue="days + ' days'"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Invoice to Customer"/></td>
                                <td colspan="2" class="td2"><s:select name="franchise.invoiceToCustomerid"
                                                                      cssClass="form-control" list="invoiceToCustomers"
                                                                      listValue="customerCode" listKey="customerCode"
                                                                      headerValue="" headerKey="0"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Pickup Fee"/></td>
                                <td colspan="2" class="td2"><s:select name="franchise.pickupFee" cssClass="form-control"
                                                                      list="pickupFees" listValue="name"
                                                                      listKey="id"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Invoice Late Fee % (e.g. 10)"/></td>
                                <td width="60" class="td2"><s:textfield cssClass="form-control"
                                                                        name="franchise.invoiceLateFee"
                                                                        onkeypress="return formartNumber(event,this,true);"/></td>
                                <td class="td2">%</td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Download .CSV Invoice"/></td>
                                <td colspan="2" class="td2"><s:checkbox name="franchise.downloadCsvInvoice"/></td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="*E-mail Invoice"/></td>
                                <td colspan="2" class="td2"><s:checkbox name="franchise.emailInvoice"/></td>
                            </tr>
                            <tr>
                                <td colspan="3"><xms:localization
                                        text="* = Requires valid e-mail listed in Billing Address under Address tab."/></td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4 div_customer_form_control">
                        <table class="table">
                            <tbody>
                            <tr>
                                <td style="border-top: 0px !important" colspan="3">
                                    <div class="caption b17">
                                        <xms:localization text="Percent of Carrier Cost"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Overnight"/></td>
                                <td colspan="2" class="td2"><s:textfield cssClass="form-control"
                                                                         cssStyle="display:inline"
                                                                         name="franchise.overnight"
                                                                         onkeypress="return formartNumber(event,this,false);"/>
                                    %
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Next Afternoon"/></td>
                                <td colspan="2" class="td2"><s:textfield cssClass="form-control"
                                                                         cssStyle="display:inline"
                                                                         name="franchise.nextAfternoon"
                                                                         onkeypress="return formartNumber(event,this,false);"/>
                                    %
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Second Day"/></td>
                                <td colspan="2" class="td2"><s:textfield cssClass="form-control"
                                                                         cssStyle="display:inline"
                                                                         name="franchise.secondDay"
                                                                         onkeypress="return formartNumber(event,this,false);"/>
                                    %
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Ground"/></td>
                                <td colspan="2" class="td2"><s:textfield cssClass="form-control"
                                                                         cssStyle="display:inline"
                                                                         name="franchise.ground"
                                                                         onkeypress="return formartNumber(event,this,false);"/>
                                    %
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Intl(Outbound)"/></td>
                                <td colspan="2" class="td2"><s:textfield cssClass="form-control"
                                                                         cssStyle="display:inline"
                                                                         name="franchise.intlOutbound"
                                                                         onkeypress="return formartNumber(event,this,false);"/>
                                    %
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Intl(Inbound)"/></td>
                                <td colspan="2" class="td2"><s:textfield cssClass="form-control"
                                                                         cssStyle="display:inline"
                                                                         name="franchise.intlInbound"
                                                                         onkeypress="return formartNumber(event,this,false);"/>
                                    %
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Other"/></td>
                                <td colspan="2" class="td2">$ <s:textfield cssStyle="display:inline"
                                                                           cssClass="form-control"
                                                                           name="franchise.other"
                                                                           onkeypress="return formartNumber(event,this,false);"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Per Airbill Fee - Express"/></td>
                                <td colspan="2" class="td2">$ <s:textfield cssClass="form-control"
                                                                           cssStyle="display:inline"
                                                                           name="franchise.expressPerAirbill"
                                                                           onkeypress="return formartNumber(event,this,false);"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Per Airbill Fee - Freight"/></td>
                                <td colspan="2" class="td2"><s:textfield cssClass="form-control"
                                                                         cssStyle="display:inline"
                                                                         name="franchise.freightPerAirbill"
                                                                         onkeypress="return formartNumber(event,this,false);"/>
                                    %
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Misc. Charge1"/></td>
                                <td colspan="2">
                                    <ul class="ul_charge_amount">
                                        <li><s:textfield name="franchise.charge1"
                                                         onkeypress="return formartNumber(event,this,false);"/></li>
                                        <li><xms:localization text="Amount "/></li>
                                        <li>$ <s:textfield name="franchise.charge1Amount"
                                                           onkeypress="return formartNumber(event,this,false);"/>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Misc. Charge2"/></td>
                                <td colspan="2">
                                    <ul class="ul_charge_amount">
                                        <li><s:textfield name="franchise.charge2"
                                                         onkeypress="return formartNumber(event,this,false);"/></li>
                                        <li><xms:localization text="Amount "/></li>
                                        <li>$ <s:textfield name="franchise.charge2Amount"
                                                           onkeypress="return formartNumber(event,this,false);"/>
                                        </li>
                                    </ul>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Misc. Charge3"/></td>
                                <td colspan="2">
                                    <ul class="ul_charge_amount">
                                        <li><s:textfield name="franchise.charge3"
                                                         onkeypress="return formartNumber(event,this,false);"/></li>
                                        <li><xms:localization text="Amount "/></li>
                                        <li>$ <s:textfield name="franchise.charge3Amount"
                                                           onkeypress="return formartNumber(event,this,false);"/>
                                        </li>
                                    </ul>
                                </td>
                            </tr>

                            </tbody>
                        </table>
                    </div>
                    <div class="col-lg-4 div_customer_form_control">
                        <table class="table">
                            <tbody>
                            <tr>
                                <td style="border-top: 0px !important" colspan="3">
                                    <div class="caption b17">
                                        <xms:localization text="Other Fees"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Software Maintenance"/></td>
                                <td colspan="2" class="td2">$ <s:textfield
                                        onkeypress="return formartNumber(event,this,false);" cssStyle="display:inline"
                                        cssClass="form-control" name="franchise.swMaintenance"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Software Development"/></td>
                                <td colspan="2" class="td2">$ <s:textfield
                                        onkeypress="return formartNumber(event,this,false);" cssStyle="display:inline"
                                        cssClass="form-control" name="franchise.swDevelopment"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Marketing"/></td>
                                <td colspan="2" class="td2">$ <s:textfield
                                        onkeypress="return formartNumber(event,this,false);" cssStyle="display:inline"
                                        cssClass="form-control" name="franchise.marketing"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="td1"><xms:localization text="Web Freight"/></td>
                                <td colspan="2" class="td2"><s:textfield
                                        onkeypress="return formartNumber(event,this,false);" cssClass="form-control"
                                        name="franchise.webship"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>

                </div>
            </div>
        </div>

        <!-- Collections tab -->
        <div id="Collections-tab" class="tab-pane fade in">
            <div class="portlet-body b12 b11">
                <div class="portlet-body b22" style="padding: 0px;">
                    <ul id="edit-customers-collections" class="nav nav-tabs responsive">
                        <li class="active" style="margin-left: 10px;"><a href="#collections-freight-tab"
                                                                         data-toggle="tab"><xms:localization
                                text="Freight"/></a></li>
                        <li><a href="#collections-reminder-letters-tab" data-toggle="tab" class="tb3"><xms:localization
                                text="Reminder Letters"/></a></li>
                    </ul>
                    <div id="edit-customers-collections-content" class="tab-content responsive">
                        <s:hidden name="collection.userId"/>
                        <s:hidden name="collection.userType"/>
                        <div id="collections-freight-tab" class="tab-pane fade in tb2 active">
                            <div class="row">
                                <div class="portlet-body b12 b11">
                                    <table class="s36">
                                        <tbody>
                                        <tr>
                                            <td>&nbsp;</td>
                                            <td class="caption b17" colspan="2"><xms:localization
                                                    text="Credit Limits"/></td>
                                        </tr>
                                        <tr>
                                            <td colspan="3" height="5"></td>
                                        </tr>
                                        <tr>
                                            <td><xms:localization text="Freight Credit Limits:"/></td>
                                            <td width="90"><s:textfield name="collection.freightCreditLimit"
                                                                        cssClass="form-control"
                                                                        onkeypress="return formartNumber(event,this,true);"/></td>
                                            <td>$</td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <div id="collections-reminder-letters-tab" class="tab-pane fade in">
                            <div class="row">
                                <div class="portlet-body b12 b11">
                                    <table class="s36">
                                        <tbody>
                                        <tr>
                                            <td class="caption b17" colspan="9"><xms:localization
                                                    text="Reminder Letters"/></td>
                                            <td>&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td colspan="10" height="5"></td>
                                        </tr>
                                        <tr>
                                            <td><s:checkbox name="collection.reminder"/></td>
                                            <td><xms:localization text="Send"/></td>
                                            <td><s:checkbox name="collection.reminderEmail"/></td>
                                            <td><xms:localization text="Email"/></td>
                                            <td><s:checkbox name="collection.reminderPrint"/></td>
                                            <td><xms:localization text="Print"/></td>
                                            <td><s:checkbox name="collection.reminderUseEmailInvoice"/></td>
                                            <td><xms:localization text="Use Email Inv. AddressInv."/></td>
                                            <td><s:textfield name="collection.reminderEmailAddress"
                                                             cssClass="form-control"/></td>
                                            <td><xms:localization text="Other Email Address(es)"/></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Note tab -->
        <div id="Notes-tab" class="tab-pane fade in">
            <b><xms:localization text="Notes"/></b>

            <p>
                <xms:localization text="Type text in the box below for the initial note on this account."/>
            </p>
            <s:textarea name="note.note" cols="100" rows="5"></s:textarea>
        </div>
        <div class="row" style="text-align: right;">
            <div class="col-lg-12">
                <button class="btn s37" type="reset">
                    <xms:localization text="Reset"/>
                </button>
                <button class="btn s37" type="button" onclick="saveNewFranchise();">
                    <xms:localization text="Save"/>
                </button>
            </div>
        </div>
    </div>
</form>
<div id="add-weight-break-comp" style="display: none;"></div>
<div id="add-weight-dialog" title="<xms:localization text="Add Weight Break" />" style="display: none;"></div>
<div id="view_rate_sheet_dialog" title="<xms:localization text="View Rate Sheet" />" style="display: none;"></div>
<div id="msg-dialog" title="<xms:localization text="Message" />"></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'dd-mm-yyyy'
    });
    $(document).ready(function () {
        enableDisableBillingAddress();
        // Bind on keyup event handler to the inputs that it's group is address
        $("input[group='address']").keyup(function () {
            copyValue($(this));
        });
        $("select[group='address']").change(function () {
            copyValue($(this));
        });
    });

    function enableDisableBillingAddress() {
        if ($("#billing-same-with-customer-checkbox").is(':checked')) {
            $("input[group='billing-address'], select[group='billing-address']").each(function () {
                $(this).attr("readonly", true);
            });
        } else {
            $("input[group='billing-address'], select[group='billing-address']").each(function () {
                $(this).attr("readonly", false);
            });
        }
    }

    function sameWithCustomerCheckBoxChange() {
        enableDisableBillingAddress();
        $("input[group='address'], select[group='address']").each(function () {
            copyValue($(this));
        });
    }

    function copyValue(obj) {
        if ($("#billing-same-with-customer-checkbox").is(':checked')) {
            var addressName = $(obj).attr("name");
            var lastName = addressName.substring(addressName.lastIndexOf(".") + 1);
            lastName = lastName.charAt(0).toUpperCase() + lastName.slice(1);
            var billingAddressName = "billingAddress.billing" + lastName;
            $("input[name='" + billingAddressName + "'], select[name='" + billingAddressName + "']").val($(obj).val());
        }
    }

    var globalIndex = "<s:property value="#global_index"/>";
    function showZones($this) {
        if ($this.data("show") == "close") {
            $this.removeClass("fa-plus-square");
            $this.addClass("fa-minus-square");
            $this.parents("div.base-rate").next().show("slow");
            $this.data("show", "open");
        } else {
            $this.removeClass("fa-minus-square");
            $this.addClass("fa-plus-square");
            $this.parents("div.base-rate").next().hide("slow");
            $this.data("show", "close");
        }
    }

    function onCheckZoneCheck($this, $parent) {
        if ($this.is(":checked") == true) {
            $parent.find("[data-group='zone-name']").each(function (i) {
                $(this).attr("disabled", false);
                $(this).attr("readonly", true);
            });
            $parent.find("[data-group='zone-rate']").each(function (i) {
                $(this).attr("disabled", false);
            });
            $parent.parents("div.base-rate").prev().find("[data-group='br-rate']").attr("readonly", true);
        } else {
            $parent.find("[data-group='zone-name']").each(function (i) {
                $(this).attr("disabled", true);
                $(this).attr("readonly", false);
            });
            $parent.find("[data-group='zone-rate']").each(function (i) {
                $(this).attr("disabled", "disabled");
            });
            $parent.parents("div.base-rate").prev().find("[data-group='br-rate']").attr("readonly", false);
        }
    }

    function addWeightBreak($this) {
        loadingDialog.dialog("open");
        var $parent = $this.parents("div.base-rate-row");
        var baseRateData = $parent.data("baserate");
        var baseRateDataArr = baseRateData.split("_");
        var shipmentId = baseRateDataArr[0];
        var content = baseRateDataArr[1];
        var bound = baseRateDataArr[2];
        var currentWeight = $parent.data("weight");
        var nextWeight = $parent.next().data("weight");
        var index = $this.data("index");
        var displayName = $parent.find("span.br-display-name").html();
        var html = $parent.html();
        var data = {
            "weightBreakModel.htmlString": html,
            "weightBreakModel.globalIndex": globalIndex,
            "weightBreakModel.currentIndex": index,
            "weightBreakModel.displayName": displayName,
            "weightBreakModel.baseRateData": baseRateData,
            "weightBreakModel.currentWeight": currentWeight,
            "weightBreakModel.nextWeight": nextWeight
        };
        var buttons = {};
        buttons["<xms:localization text="Save" />"] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#form-add-weight-break').serialize();
            $.post("add_weight_break_do_add.ix?reqType=json", dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    $parent.after(res.content);
                    $parent.parent("div").find("div[data-group='br-weight']").show();
                    $parent.parent("div").find("div[data-group='br-remove-break']").show();
                    dialog.dialog("close");
                    globalIndex++;
                } else if (res.errorCode == "FIELD_ERROR") {
                    dialog.html(res.content);
                } else if (res.errorCode == "ACTION_ERROR") {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                alertDialog.dialog("open");
            });
        };
        buttons["<xms:localization text="Cancel" />"] = function () {
            $(this).dialog("close");
        }
        var height = $(window).height();
        var width = $(window).width();
        height = height * 0.70;
        width = width * 0.9;
        var dialog = $("#add-weight-dialog").dialog({
            modal: true,
            autoOpen: false,
            width: "auto",
            buttons: buttons,
            width: 'auto',
            height: 'auto',
            maxHeight: height,
            create: function (event, ui) {
                $(this).css("maxWidth", width);
            },
            show: {
                effect: "fade",
                duration: 300
            },
            close: function (e) {
                $("#add-weight-dialog").html("");
            }
        });
        $.post("add_weight_break.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                dialog.html(res.content);
                dialog.dialog("open");
            } else {
                alertDialog.html('Error: ' + res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
        console.log("saveManageCustomer.saveCustBaseRate.customerBaseRates[" + index + "]");
        console.log("saveManageCustomer.saveCustBaseRate.customerBaseRates[" + (index + 1) + "]");
        console.log("shipmentId: " + shipmentId + "| content: " + content + "| bound: " + bound + "| current weight: " + currentWeight + "| next weight: " + nextWeight);
    }

    function removeWeightBreak($this) {
        $parent = $this.parents("div.base-rate-row");
        var count = $parent.parent("div").find("div.base-rate-row").length;
        count = count - 1;
        if (count == 1) {
            $parent.parent("div").find("div[data-group='br-weight']").hide();
        }
        $parent.remove();

    }

    function viewRateSheet(customerCode, shipmentTypeId, content, bound, serviceId) {
        var request = getRateSheetRequest(customerCode, shipmentTypeId, content, bound, serviceId);
        $.post("view_rate_sheet.ix?reqType=json", "viewRequest=" + request, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                var dialog = $("#view_rate_sheet_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: "90%",
                    height: 'auto',
                    position: {my: "top", at: "top+50"},
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                dialog.html(res.content);
                dialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function getRateSheetRequest(customerCode, shipmentTypeId, content, bound, serviceId) {
        var columnName = "";
        if (serviceId == 3) {
            columnName = $("#tntDomColumnName option:selected").val();
        } else if (serviceId == 72) {
            columnName = $("#starTrackColumnName option:selected").val();
        }
        var stId = "#shipment_type_" + shipmentTypeId + "_" + content + "_" + bound;
        var request = "{";
        // Type=1 for view franchise base rate.
        request += "\"type\":1,";
        request += "\"customerCode\":\"" + customerCode + "\",";
        request += "\"shipmentTypeId\":" + shipmentTypeId + ",";
        request += "\"content\":" + content + ",";
        request += "\"bound\":" + bound + ",";
        request += "\"minimumBaseCharge\":" + $("#minimum_base_charge").val() + ",";
        if (columnName != "") {
            request += "\"columnName\":\"" + columnName + "\",";
        }
        request += "\"baseRates\":[";
        $(stId).find("div[data-baserate]").each(function () {
            request += "{";
            // Get weight.
            var weightText = $(this).find("span[data-group='br-weight']").html();
            var weight = 0;
            if (weightText == "") {
                weight = 0.0;
            } else {
                weight = parseFloat(weightText.substring(0, weightText.length - 1));
            }
            // Get rate type.
            var rateType = $(this).find("div[data-group='sel-rate-type']>select option:selected").val();
            // Get rate.
            var rate = $(this).find("div[data-group='base-rate']>input").val();
            request += "\"weight\":" + weight + ",";
            request += "\"rateType\":" + rateType + ",";
            request += "\"rate\":" + rate + ",";
            // Get zone check.
            var zoneCheck = $(this).find("input[data-group='zone-check']").is(":checked");
            if (zoneCheck) {
                var zones = "{";
                $(this).find("div[data-group='zone']").each(function () {
                    var zoneName = $(this).find("input[data-group='zone-name']").val();
                    var zoneRate = $(this).find("input[data-group='zone-rate']").val();
                    zones += "\"" + zoneName + "\":" + zoneRate + ",";
                });
                if (zones.charAt(zones.length - 1) == ',') {
                    zones = zones.substring(0, zones.length - 1);
                }
                zones += "}";
                request += "\"zoneRates\":" + zones;
            } else {
                request = request.substring(0, request.length - 1);
            }
            request += "},";
        });
        if (request.charAt(request.length - 1) == ',') {
            request = request.substring(0, request.length - 1);
        }
        request += "]}";
        return request;
    }

    function saveNewFranchise() {
        loadingDialog.dialog("open");
        var data = $("#add_franchise_form").serialize();
        $.post("add_franchise_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define message dialog.
                var buttons = {};
                buttons["<xms:localization text="OK" />"] = function () {
                    window.location.href = "manage_franchise.ix?franchiseCode=" + $("#txt_franchise_code").val() + "00001";
                };
                var msgDialog = $("#msg-dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    buttons: buttons,
                    width: '320',
                    height: '180'
                });
                msgDialog.html("Saved successfully.");
                msgDialog.dialog("open");
            } else if (res.errorCode == "FIELD_ERROR") {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function printRateSheetCheckAll(obj) {
        var topDiv = $(obj).closest("div[data-type='print-rate-sheet']");
        $(topDiv).find("input[data-type='print-rate-sheet-checkbox']").each(function () {
            $(this).attr("checked", true);
        });
    }

    function printRateSheetCheckNone(obj) {
        var topDiv = $(obj).closest("div[data-type='print-rate-sheet']");
        $(topDiv).find("input[data-type='print-rate-sheet-checkbox']").each(function () {
            $(this).attr("checked", false);
        });
    }

    function printRateSheet(obj) {
        var topDiv = $(obj).closest("div[data-type='print-rate-sheet']");
        var pdfCheck = $(topDiv).find("input[data-type='pdf-format']").is(":checked");
        var excelCheck = $(topDiv).find("input[data-type='excel-format']").is(":checked");
        var selected = false;
        $(topDiv).find("input[data-type='print-rate-sheet-checkbox']").each(function () {
            if ($(this).is(":checked")) {
                selected = true;
                return false;
            }
        });
        if (!selected) {
            alertDialog.html("<xms:localization text="Please select shipment type to print." />");
            alertDialog.dialog("open");
        } else {
            // Prepare data for print rate sheet request.
            var index = 0;
            var result = "";
            $(topDiv).find("input[data-type='print-rate-sheet-checkbox']").each(function () {
                if ($(this).is(":checked")) {
                    var customerCode = $(this).attr("data-customercode");
                    var shipmentTypeId = $(this).attr("data-shipmenttypeid");
                    var content = $(this).attr("data-content");
                    var bound = $(this).attr("data-bound");
                    var serviceId = $(this).attr("data-serviceid");
                    var request = getRateSheetRequest(customerCode, shipmentTypeId, content, bound, serviceId);
                    result += "listViewRequest[" + index + "]=" + request + "&";
                    index++;
                }
            });
            result = result.substring(0, result.length - 1);
            // Get print option.
            var excelCheck = $(topDiv).find("input[data-type='excel-format']");
            var pdfCheck = $(topDiv).find("input[data-type='pdf-format']");
            var printType = 0;
            if (pdfCheck.is(":checked")) {
                printType = 1;
            }
            if (excelCheck.is(":checked")) {
                printType = 2;
            }
            switch (printType) {
                case 0:
                    exportRateSheet2Html(result);
                    break;
                case 1:
                    exportRateSheet2Pdf(result);
                    break;
                case 2:
                    exportRateSheet2Excel(result);
                    break;
            }
        }
    }

    function exportRateSheet2Pdf(listRequest) {
        loadingDialog.dialog("open");
        $.fileDownload("rate_sheet_export_to_pdf.ix", {
            failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
            httpMethod: "POST",
            data: listRequest,
            successCallback: function (url) {
                loadingDialog.dialog("close");
            },
            failCallback: function (url) {
                loadingDialog.dialog("close");
            }
        });
    }

    function exportRateSheet2Excel(listRequest) {
        loadingDialog.dialog("open");
        $.fileDownload("rate_sheet_export_to_excel.ix", {
            failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
            httpMethod: "POST",
            data: listRequest,
            successCallback: function (url) {
                loadingDialog.dialog("close");
            },
            failCallback: function (url) {
                loadingDialog.dialog("close");
            }
        });
    }

    function exportRateSheet2Html(listRequest) {
        loadingDialog.dialog("open");
        $.post("rate_sheet_export_to_html.ix?reqType=json", listRequest, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                win.document.write(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function rateSheetFormatChange(obj) {
        if ($(obj).is(":checked")) {
            var topDiv = $(obj).closest("div[data-type='print-rate-sheet']");
            if ($(obj).attr("data-type") == "pdf-format") {
                var excelCheck = $(topDiv).find("input[data-type='excel-format']");
                excelCheck.attr("checked", false);
            } else {
                var pdfCheck = $(topDiv).find("input[data-type='pdf-format']");
                pdfCheck.attr("checked", false);
            }
        }
    }
</script>