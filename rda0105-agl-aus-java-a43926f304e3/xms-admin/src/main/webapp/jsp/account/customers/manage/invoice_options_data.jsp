<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="frmSaveCustomerInvoiceOptions">
    <div class="row">
        <div class="portlet-body b12 b11 col-md-4">
            <s:hidden name="saveManageCustomer.invoiceOption.customerCode" value="%{customer.customerCode}"/>
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
                    <td colspan="2" class="td2"><s:select name="saveManageCustomer.invoiceOption.invoiceSorting"
                                                          value="%{customer.invoiceSorting}" cssClass="form-control"
                                                          list="invoiceSortingOptions" listValue="name"
                                                          listKey="id"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Invoice Terms"/></td>
                    <td colspan="2" class="td2"><s:select name="saveManageCustomer.invoiceOption.invoiceTerms"
                                                          value="%{customer.invoiceTerms}" cssClass="form-control"
                                                          list="invoiceTerms" listKey="invoiceTermId"
                                                          listValue="days + ' days'"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Invoice to Customer"/></td>
                    <td colspan="2" class="td2"><s:select name="saveManageCustomer.invoiceOption.invoiceToCustomerId"
                                                          value="%{customer.invoiceToCustomerId}"
                                                          cssClass="form-control" list="invoiceToCustomers"
                                                          listValue="customerCode" listKey="customerCode" headerValue=""
                                                          headerKey="0"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Pickup Fee"/></td>
                    <td colspan="2" class="td2"><s:select name="saveManageCustomer.invoiceOption.pickupFee"
                                                          value="%{customer.pickupFee}" cssClass="form-control"
                                                          list="pickupFees" listValue="name" listKey="id"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Invoice Late Fee % (e.g. 10)"/></td>
                    <td width="60" class="td2"><s:textfield cssClass="form-control"
                                                            name="saveManageCustomer.invoiceOption.invoiceLateFee"
                                                            value="%{customer.invoiceLateFee}"
                                                            onkeypress="return formartNumber(event,this,true);"/></td>
                    <td class="td2">%</td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Download .CSV Invoice"/></td>
                    <td colspan="2" class="td2"><s:checkbox name="saveManageCustomer.invoiceOption.downloadCsvInvoice"
                                                            value="%{customer.downloadCsvInvoice}"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="*E-mail Invoice"/></td>
                    <td colspan="2" class="td2"><s:checkbox name="saveManageCustomer.invoiceOption.emailInvoice"
                                                            value="%{customer.emailInvoice}"/></td>
                </tr>
                <tr>
                    <td colspan="3"><xms:localization
                            text="* = Requires valid e-mail listed in Billing Address under Address tab."/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</form>

<script type="text/javascript">
    function saveCustomerInvoiceOptions() {
        var data = $("#frmSaveCustomerInvoiceOptions").serialize();
        loadingDialog.dialog("open");
        $.post("manage_customers_invoice_options_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            $("#invoice-options-tab").html(res.content);
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>