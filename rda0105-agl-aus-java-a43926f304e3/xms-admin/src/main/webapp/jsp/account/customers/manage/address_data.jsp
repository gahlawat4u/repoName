<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="frmSaveCustomerAddress">
    <div class="row">
        <div class="portlet-body b12 b11">
            <div class="row">
                <div class="col-lg-4">
                    <s:hidden name="saveManageCustomer.customerAddress.customerCode" value="%{customer.customerCode}"/>
                    <s:hidden name="saveManageCustomer.customerAddress.address.customerCode"
                              value="%{customer.customerCode}"/>
                    <s:hidden name="saveManageCustomer.customerAddress.billingAddress.customerCode"
                              value="%{customer.customerCode}"/>
                    <table class="table" style="font-size: 11px;">
                        <tr>
                            <td colspan="3" style="border-top: 0px !important">
                                <div class="caption b17">
                                    <xms:localization text="Address"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Customer Name"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.customerName"
                                    value="%{customer.address.customerName}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Contact Name"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.contactName"
                                    value="%{customer.address.contactName}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Contact Title"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.contactTitle"
                                    value="%{customer.address.contactTitle}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Address"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.address1"
                                    value="%{customer.address.address1}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1">&nbsp;</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.address2"
                                    value="%{customer.address.address2}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="City"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.city"
                                    value="%{customer.address.city}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Country"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:select
                                    name="saveManageCustomer.customerAddress.address.country"
                                    value="%{customer.address.country}" list="countries" listValue="countryName"
                                    listKey="countryId" cssClass="form-control uppercase" group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Postal Code"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.postalCode"
                                    value="%{customer.address.postalCode}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="State Code"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.stateCode"
                                    value="%{customer.address.stateCode}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Phone"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.phone"
                                    value="%{customer.address.phone}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Fax"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.fax"
                                    value="%{customer.address.fax}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td2" colspan="2"><span> <xms:localization
                                    text="Eg: first@email.com; second@email.com"/>
							</span></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Email"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.email"
                                    value="%{customer.address.email}" cssClass="form-control" group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Mobile"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.mobile"
                                    value="%{customer.address.mobile}" cssClass="form-control uppercase"
                                    group="address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Alt Contact Phone"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.alternatePhone"
                                    value="%{customer.address.alternatePhone}" cssClass="form-control uppercase"
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
                                    <span class="b20"><xms:localization text="Same As Customer Address"/> </span>
                                    <s:checkbox id="billing-same-with-customer-checkbox"
                                                name="saveManageCustomer.customerAddress.billingAddress.billingSameWithCustomer"
                                                value="%{customer.billingAddress.billingSameWithCustomer}"
                                                onchange="sameWithCustomerCheckBoxChange()"/>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Customer Name"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingCustomerName"
                                    value="%{customer.billingAddress.billingCustomerName}"
                                    cssClass="form-control uppercase" group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Contact Name"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingContactName"
                                    value="%{customer.billingAddress.billingContactName}"
                                    cssClass="form-control uppercase" group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Contact Title"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingContactTitle"
                                    value="%{customer.billingAddress.billingContactTitle}"
                                    cssClass="form-control uppercase" group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Address"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingAddress1"
                                    value="%{customer.billingAddress.billingAddress1}" cssClass="form-control uppercase"
                                    group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1">&nbsp;</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingAddress2"
                                    value="%{customer.billingAddress.billingAddress2}" cssClass="form-control uppercase"
                                    group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="City"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingCity"
                                    value="%{customer.billingAddress.billingCity}" cssClass="form-control uppercase"
                                    group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Country"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:select
                                    name="saveManageCustomer.customerAddress.billingAddress.billingCountry"
                                    value="%{customer.billingAddress.billingCountry}" list="countries"
                                    listValue="countryName" listKey="countryId" cssClass="form-control uppercase"
                                    group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Postal Code"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingPostalCode"
                                    value="%{customer.billingAddress.billingPostalCode}"
                                    cssClass="form-control uppercase" group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="State Code"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingStateCode"
                                    value="%{customer.billingAddress.billingStateCode}"
                                    cssClass="form-control uppercase" group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Phone"/>:<span class="s30">*</span></td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingPhone"
                                    value="%{customer.billingAddress.billingPhone}" cssClass="form-control uppercase"
                                    group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Fax"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingFax"
                                    value="%{customer.billingAddress.billingFax}" cssClass="form-control uppercase"
                                    group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td2" colspan="2"><span> <xms:localization
                                    text="Eg: first@email.com; second@email.com"/>
							</span></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Email"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingEmail"
                                    value="%{customer.billingAddress.billingEmail}" cssClass="form-control"
                                    group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Mobile"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingMobile"
                                    value="%{customer.billingAddress.billingMobile}" cssClass="form-control uppercase"
                                    group="billing-address"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Alt Contact Phone"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.billingAddress.billingAlternatePhone"
                                    value="%{customer.billingAddress.billingAlternatePhone}"
                                    cssClass="form-control uppercase" group="billing-address"/></td>
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
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.owner"
                                    value="%{customer.address.owner}" cssClass="form-control uppercase"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Phone"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.ownerPhone"
                                    value="%{customer.address.ownerPhone}" cssClass="form-control uppercase"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Email"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.ownerEmail"
                                    value="%{customer.address.ownerEmail}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="AP Contact"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.apContact"
                                    value="%{customer.address.apContact}" cssClass="form-control uppercase"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Phone"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.apPhone"
                                    value="%{customer.address.apPhone}" cssClass="form-control uppercase"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Email"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.apEmail"
                                    value="%{customer.address.apEmail}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Other Contact"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.otherContact"
                                    value="%{customer.address.otherContact}" cssClass="form-control uppercase"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Other Phone"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.otherPhone"
                                    value="%{customer.address.otherPhone}" cssClass="form-control uppercase"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Email"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.otherEmail"
                                    value="%{customer.address.otherEmail}" cssClass="form-control"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Other 2 Contact"/> :</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.other2Contact"
                                    value="%{customer.address.other2Contact}" cssClass="form-control uppercase"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Other 2 Phone"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.other2Phone"
                                    value="%{customer.address.other2Phone}" cssClass="form-control uppercase"/></td>
                        </tr>
                        <tr>
                            <td class="td1"><xms:localization text="Email"/>:</td>
                            <td class="td2" colspan="2"><s:textfield
                                    name="saveManageCustomer.customerAddress.address.other2Email"
                                    value="%{customer.address.other2Email}" cssClass="form-control"/></td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
</form>
<script type="text/javascript">
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

    function saveCustomerAddress() {
        var data = $("#frmSaveCustomerAddress").serialize();
        loadingDialog.dialog("open");
        $.post("manage_customers_address_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            $("#address-tab").html(res.content);
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

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
            var billingAddressName = "saveManageCustomer.customerAddress.billingAddress.billing" + lastName;
            $("input[name='" + billingAddressName + "'], select[name='" + billingAddressName + "']").val($(obj).val());
        }
    }


</script>