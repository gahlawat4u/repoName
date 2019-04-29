<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="saveCustomerProfileInvoiceOption">
    <s:hidden name="saveCustomerProfile.invoiceOptions.profileId" value="%{cusProfile.profileId}"/>
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
                    <td colspan="2" class="td2"><s:select class="form-control" headerKey="" headerValue=""
                                                          list="invoiceSortingOptions"
                                                          name="saveCustomerProfile.invoiceOptions.invoiceSorting"
                                                          value="%{cusProfile.invoiceSorting}" listValue="name"
                                                          listKey="id"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Invoice Terms"/></td>
                    <td colspan="2" class="td2"><s:select class="form-control" headerKey="" headerValue=""
                                                          list="invoiceTerms"
                                                          name="saveCustomerProfile.invoiceOptions.invoiceTerms"
                                                          value="%{cusProfile.invoiceTerms}" listValue="days"
                                                          listKey="invoiceTermId"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Invoice to Customer"/></td>
                    <td colspan="2" class="td2"><s:select class="form-control" headerKey="" headerValue=""
                                                          list="invoiceToCustomers"
                                                          name="saveCustomerProfile.invoiceOptions.invoiceToCustomerId"
                                                          value="%{cusProfile.invoiceToCustomerId}"
                                                          listValue="customerCode" listKey="id"
                                                          onkeypress="return formartNumber(event,this,false);"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Pickup Fee"/></td>
                    <td colspan="2" class="td2"><s:select class="form-control" headerKey="" headerValue=""
                                                          list="pickupFees"
                                                          name="saveCustomerProfile.invoiceOptions.pickupFee"
                                                          value="%{cusProfile.pickupFee}" listValue="name" listKey="id">
                    </s:select></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Invoice Late Fee"/> % (e.g. 10)</td>
                    <td width="60" class="td2"><s:textfield class="form-control"
                                                            name="saveCustomerProfile.invoiceOptions.invoiceLateFee"
                                                            value="%{cusProfile.invoiceLateFee}"
                                                            onkeypress="return formartNumber(event,this,false);"/></td>
                    <td class="td2">%</td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Download .CSV Invoice"/></td>
                    <td colspan="2" class="td2"><s:checkbox name="saveCustomerProfile.invoiceOptions.downloadCsvInvoice"
                                                            value="%{cusProfile.downloadCsvInvoice}"/></td>
                </tr>
                <tr>
                    <td class="td1">*<xms:localization text="E-mail Invoice"/></td>
                    <td colspan="2" class="td2"><s:checkbox name="saveCustomerProfile.invoiceOptions.emailInvoice"
                                                            value="%{cusProfile.emailInvoice}"/></td>
                </tr>
                <tr>
                    <td colspan="3">* = <xms:localization
                            text="Requires valid e-mail listed in Billing Address under Address tab."/>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</s:form>
<div id="div_save_invoice_option_result"></div>

<script type="text/javascript">
    function saveCustomerProfileInvoiceOptions() {
        var data = $("#saveCustomerProfileInvoiceOption").serialize();
        loadingDialog.dialog("open");
        $.post("manage_customer_profile_invoice_options_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            dialog.html(res.content);
            dialog.dialog("open");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });

        var buttons = {};
        var height = $(window).height();
        var width = $(window).width();
        height = height * 0.70;
        width = width * 0.9;
        var dialog = $("#div_save_invoice_option_result").dialog({
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
                $("#div_save_invoice_option_result").html("");
            }
        });
        dialog.dialog("option", "title", "Save Invoice Options.");
    }


</script>