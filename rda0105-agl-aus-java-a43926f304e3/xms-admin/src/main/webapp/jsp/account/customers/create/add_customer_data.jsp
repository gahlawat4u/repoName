<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script type="text/javascript">
    var activeTab = "#account-setup-tab";
</script>

<ul id="editCustomerTab" class="nav nav-tabs responsive">
    <li class="active" style="margin-left: 10px;"><a href="#account-setup-tab" data-toggle="tab"><xms:localization
            text="Account Setup"/></a></li>
    <li><a href="#address-tab" data-toggle="tab"><xms:localization text="Address"/></a></li>
    <li><a href="#base-rates-tab" data-toggle="tab"><xms:localization text="Base Rates"/></a></li>
    <%-- <li><a href="#invoice-options-tab" data-toggle="tab"><xms:localization text="Invoice Options"/></a></li>
    <li><a href="#collections-tab" data-toggle="tab"><xms:localization text="Collections"/></a></li> --%>
    <li><a href="#notes-tab" data-toggle="tab"><xms:localization text="Notes"/></a></li>
</ul>
<form id="add-customer-form">
    <div id="newCustomerContent" class="tab-content responsive">
        <s:hidden name="customer.customerCode"/>
        <s:hidden name="customer.franchiseCode"/>
        <s:hidden name="customer.srno"/>
        <s:hidden name="customer.dhlDomesticAccount"/>
        <s:hidden name="customer.dhlInternationalAccount"/>
        <s:hidden name="customer.dhlInboundAccount"/>
        <s:hidden name="customer.aaeAccount"/>
        <s:hidden name="customer.fedexAccount"/>
        <s:hidden name="customer.dhlDomesticAccount"/>
        <s:hidden name="customer.ukMailAccount"/>
        <s:hidden name="customer.upsAccount"/>
        <s:hidden name="customer.tollIpecAccount"/>
        <div id="account-setup-tab" class="tab-pane fade in active">
            <div class="row">
                <div class="portlet-body b12 b11">
                    <div class="row">
                        <div class="col-lg-4">
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="3" style="border-top: 0px !important">
                                        <div class="caption b17">
                                            <xms:localization text="Account Setup"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Customer #"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="customer.customerCode"
                                                                             cssClass="form-control"
                                                                             readonly="true"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Inactive?"/>:</td>
                                    <td class="td2" colspan="2"><s:checkbox name="customer.inActive"/></td>
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
                                    <td class="td2" colspan="2"><s:select name="customer.previousCarrier"
                                                                          list="services" listValue="serviceName"
                                                                          listKey="serviceId" cssClass="form-control"
                                                                          headerValue="" headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Customer Group"/>:</td>
                                    <td class="td2" colspan="2"><s:select name="customer.groupId" list="customerGroups"
                                                                          listValue="customerGroupName"
                                                                          listKey="customerGroupId"
                                                                          cssClass="form-control" headerValue=""
                                                                          headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Web Freight Group"/>:</td>
                                    <td class="td2"><s:select name="customer.webshipGroupId" list="webshipGroups"
                                                              listValue="webshipGroupName" listKey="webshipGroupId"
                                                              cssClass="form-control" headerValue=""
                                                              headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Industry"/>:</td>
                                    <td class="td2" colspan="2"><s:select name="customer.industryId" list="industries"
                                                                          listValue="industryName" listKey="industryId"
                                                                          cssClass="form-control" headerValue=""
                                                                          headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Area"/>:</td>
                                    <td class="td2" colspan="2"><s:select name="customer.areaId" list="areas"
                                                                          listValue="areaName" listKey="areaId"
                                                                          cssClass="form-control" headerValue=""
                                                                          headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Sales Rep"/>:</td>
                                    <td class="td2" colspan="2"><s:select name="customer.salesRepId" list="salesReps"
                                                                          listValue="displayName" listKey="userId"
                                                                          cssClass="form-control" headerValue=""
                                                                          headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Collector"/>:</td>
                                    <td class="td2" colspan="2"><s:select name="customer.collectorId" list="collectors"
                                                                          listValue="displayName" listKey="userId"
                                                                          cssClass="form-control" headerValue=""
                                                                          headerKey="0"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Business Registration #"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="customer.registrationId"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="GST #"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="customer.gstId"
                                                                             cssClass="form-control"
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
                                    <td class="td2" colspan="2"><s:textfield name="customer.dhlAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Hub Account #"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="customer.hubAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="TNT Account #"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="customer.tntAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Toll Priority Account #"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="customer.tollPriorityAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other Account #"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="customer.otherAccount"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Booking Email Notification"/>:</td>
                                    <td class="td2" colspan="2"><s:checkbox
                                            name="customer.bookingEmailNotification"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Rejection Notes"/>:</td>
                                    <td class="td2" colspan="2"><s:textarea name="customer.rejectionNote"
                                                                            cssClass="form-control"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="address-tab" class="tab-pane fade">
            <div class="row">
                <div class="portlet-body b12 b11">
                    <div class="row">
                        <div class="col-lg-4">
                            <s:hidden name="address.customerCode"/>
                            <table class="table" style="font-size: 11px;">
                                <tr>
                                    <td colspan="3" style="border-top: 0px !important">
                                        <div class="caption b17">
                                            <xms:localization text="Address"/>
                                        </div>
                                    </td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Customer Name"/>:<span class="s30">*</span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield name="address.customerName"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Contact Name"/>:<span class="s30">*</span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield name="address.contactName"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Contact Title"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.contactTitle"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Address"/>:<span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.address1"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1">&nbsp;</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.address2"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="City"/>:<span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.city" cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Country"/>:<span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:select name="address.country" list="countries"
                                                                          listValue="countryName" listKey="countryId"
                                                                          cssClass="form-control" group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Postal Code"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.postalCode"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="State Code"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.stateCode"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Phone"/>:<span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="address.phone"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Fax"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.fax" cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td2" colspan="2"><span> <xms:localization
                                            text="Eg: first@email.com; second@email.com"/>
									</span></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.email"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Mobile"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.mobile"
                                                                             cssClass="form-control"
                                                                             group="address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Alt Contact Phone"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.alternatePhone"
                                                                             cssClass="form-control"
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
                                    <td class="td1"><xms:localization text="Customer Name"/>:<span class="s30">*</span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingCustomerName"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Contact Name"/>:<span class="s30">*</span>
                                    </td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingContactName"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Contact Title"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingContactTitle"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Address"/>:<span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingAddress1"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1">&nbsp;</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingAddress2"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="City"/>:<span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingCity"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Country"/>:<span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:select name="billingAddress.billingCountry"
                                                                          list="countries" listValue="countryName"
                                                                          listKey="countryId" cssClass="form-control"
                                                                          group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Postal Code"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingPostalCode"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="State Code"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingStateCode"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Phone"/>:<span class="s30">*</span></td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingPhone"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Fax"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingFax"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td2" colspan="2"><span> <xms:localization
                                            text="Eg: first@email.com; second@email.com"/>
									</span></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingEmail"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Mobile"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingMobile"
                                                                             cssClass="form-control"
                                                                             group="billing-address"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Alt Contact Phone"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="billingAddress.billingAlternatePhone"
                                                                             cssClass="form-control"
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
                                    <td class="td1"><xms:localization text="Owner"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.owner"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Phone"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.ownerPhone"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.ownerEmail"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="AP Contact"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.apContact"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Phone"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.apPhone"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.apEmail"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other Contact"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.otherContact"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other Phone"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.otherPhone"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.otherEmail"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other 2 Contact"/> :</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.other2Contact"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Other 2 Phone"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.other2Phone"
                                                                             cssClass="form-control"/></td>
                                </tr>
                                <tr>
                                    <td class="td1"><xms:localization text="Email"/>:</td>
                                    <td class="td2" colspan="2"><s:textfield name="address.other2Email"
                                                                             cssClass="form-control"/></td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div id="base-rates-tab" class="tab-pane fade"></div>
        <div id="invoice-options-tab" class="tab-pane fade">
            <div class="row">
                <div class="portlet-body b12 b11 col-md-4">
                    <table class="table">
                        <tbody>
                        <tr>
                            <td style="border-top: 0px !important" colspan="3">
                                <div class="caption b17">
                                    <xms:localization text="Invoicing Options"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoice Sorting"/></td>
                            <td colspan="2" class="td2"><s:select name="customer.invoiceSorting" cssClass="form-control"
                                                                  list="invoiceSortingOptions" listValue="name"
                                                                  listKey="id"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoice Terms"/></td>
                            <td colspan="2" class="td2"><s:select name="customer.invoiceTerms" cssClass="form-control"
                                                                  list="invoiceTerms" listKey="invoiceTermId"
                                                                  listValue="days + ' days'"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoice to Customer"/></td>
                            <td colspan="2" class="td2"><s:select name="customer.invoiceToCustomerId"
                                                                  cssClass="form-control" list="invoiceToCustomers"
                                                                  listValue="customerCode" listKey="customerCode"
                                                                  headerValue="" headerKey="0"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Pickup Fee"/></td>
                            <td colspan="2" class="td2"><s:select name="customer.pickupFee" cssClass="form-control"
                                                                  list="pickupFees" listValue="name" listKey="id"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Invoice Late Fee % (e.g. 10)"/></td>
                            <td width="60" class="td2"><s:textfield cssClass="form-control"
                                                                    name="customer.invoiceLateFee"
                                                                    onkeypress="return formartNumber(event,this,true);"/></td>
                            <td class="td2">%</td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Download .CSV Invoice"/></td>
                            <td colspan="2" class="td2"><s:checkbox name="customer.downloadCsvInvoice"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="*E-mail Invoice"/></td>
                            <td colspan="2" class="td2"><s:checkbox name="customer.emailInvoice"/></td>
                        </tr>
                        <tr>
                            <td colspan="3"><xms:localization
                                    text="* = Requires valid e-mail listed in Billing Address under Address tab."/></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="collections-tab" class="tab-pane fade">
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
                        <s:hidden name="customerCollection.userId"/>
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
                                            <td><xms:localization text="Freight Credit Limits"/> :</td>
                                            <td width="90"><s:textfield name="customerCollection.freightCreditLimit"
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
                                            <td><s:checkbox name="customerCollection.reminder"/></td>
                                            <td><xms:localization text="Send"/></td>
                                            <td><s:checkbox name="customerCollection.reminderEmail"/></td>
                                            <td><xms:localization text="Email"/></td>
                                            <td><s:checkbox name="customerCollection.reminderPrint"/></td>
                                            <td><xms:localization text="Print"/></td>
                                            <td><s:checkbox name="customerCollection.reminderUseEmailInvoice"/></td>
                                            <td><xms:localization text="Use Email Inv. AddressInv."/></td>
                                            <td><s:textfield name="customerCollection.reminderEmailAddress"
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
        <div id="notes-tab" class="tab-pane fade"></div>
    </div>
    <div class="portlet-body pal form-actions">
        <table class="s36" align="right">
            <tr>
                <td>
                    <button class="btn s37" type="reset">
                        <xms:localization text="Reset"/>
                    </button>
                </td>
                <td>
                    <button class="btn s37" type="button">
                        <xms:localization text="Save"/>
                    </button>
                </td>
            </tr>
        </table>
    </div>
</form>
<script type="text/javascript">
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        activeTab = $('ul#editCustomerTab li.active a').attr('href');
    });

    $(document).ready(function () {
        $("#billing-same-with-customer-checkbox").attr("checked", "checked");
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


</script>