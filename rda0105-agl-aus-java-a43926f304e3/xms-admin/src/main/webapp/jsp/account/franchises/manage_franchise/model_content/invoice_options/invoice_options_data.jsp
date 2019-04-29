<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
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
<form id="frmSaveFranchiseInvoiceOptions">
    <div class="row">
        <div class="portlet-body b12 b11 col-md-12">
            <div class="col-lg-4">
                <table class="table">
                    <tbody>
                    <tr>
                        <td style="border-top: 0px !important" colspan="3">
                            <div class="caption b17">
                                <xms:localization text="Invoicing Options"/>
                            </div>
                            <s:hidden name="saveManageFranchiseModel.invoiceOption.franchiseCode"
                                      value="%{franchise.franchiseCode}"/></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Invoice Sorting"/></td>
                        <td colspan="2" class="td2"><s:select
                                name="saveManageFranchiseModel.invoiceOption.invoiceSorting"
                                value="%{franchise.invoiceSorting}" cssClass="form-control" list="invoiceSortingOptions"
                                listValue="name" listKey="id"/></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Invoice Terms"/></td>
                        <td colspan="2" class="td2"><s:select name="saveManageFranchiseModel.invoiceOption.invoiceTerms"
                                                              value="%{franchise.invoiceTerms}" cssClass="form-control"
                                                              list="invoiceTerms" listKey="invoiceTermId"
                                                              listValue="days + ' days'"/></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Invoice to Customer"/></td>
                        <td colspan="2" class="td2"><s:select
                                name="saveManageFranchiseModel.invoiceOption.invoiceToCustomerId"
                                value="%{franchise.invoiceToCustomerId}" cssClass="form-control"
                                list="invoiceToCustomers" listValue="customerCode" listKey="customerCode" headerValue=""
                                headerKey="0"/></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Pickup Fee"/></td>
                        <td colspan="2" class="td2"><s:select name="saveManageFranchiseModel.invoiceOption.pickupFee"
                                                              value="%{franchise.pickupFee}" cssClass="form-control"
                                                              list="pickupFees" listValue="name" listKey="id"/></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Invoice Late Fee % (e.g. 10)"/></td>
                        <td width="60" class="td2"><s:textfield cssClass="form-control"
                                                                name="saveManageFranchiseModel.invoiceOption.invoiceLateFee"
                                                                value="%{franchise.invoiceLateFee}"
                                                                onkeypress="return formartNumber(event,this,true);"/></td>
                        <td class="td2">%</td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Download .CSV Invoice"/></td>
                        <td colspan="2" class="td2"><s:checkbox
                                name="saveManageFranchiseModel.invoiceOption.downloadCsvInvoice"
                                value="%{franchise.downloadCsvInvoice}"/></td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="*E-mail Invoice"/></td>
                        <td colspan="2" class="td2"><s:checkbox
                                name="saveManageFranchiseModel.invoiceOption.emailInvoice"
                                value="%{franchise.emailInvoice}"/></td>
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
                        <td colspan="2" class="td2"><s:textfield cssClass="form-control" cssStyle="display:inline"
                                                                 name="saveManageFranchiseModel.invoiceOption.overnight"
                                                                 value="%{franchise.overnight}"
                                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield>
                            %
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Next Afternoon"/></td>
                        <td colspan="2" class="td2"><s:textfield cssClass="form-control" cssStyle="display:inline"
                                                                 name="saveManageFranchiseModel.invoiceOption.nextAfternoon"
                                                                 value="%{franchise.nextAfternoon}"
                                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield>
                            %
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Second Day"/></td>
                        <td colspan="2" class="td2"><s:textfield cssClass="form-control" cssStyle="display:inline"
                                                                 name="saveManageFranchiseModel.invoiceOption.secondDay"
                                                                 value="%{franchise.secondDay}"
                                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield>
                            %
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Ground"/></td>
                        <td colspan="2" class="td2"><s:textfield cssClass="form-control" cssStyle="display:inline"
                                                                 name="saveManageFranchiseModel.invoiceOption.ground"
                                                                 value="%{franchise.ground}"
                                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield>
                            %
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Intl(Outbound)"/></td>
                        <td colspan="2" class="td2"><s:textfield cssClass="form-control" cssStyle="display:inline"
                                                                 name="saveManageFranchiseModel.invoiceOption.intlOutbound"
                                                                 value="%{franchise.intlOutbound}"
                                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield>
                            %
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Intl(Inbound)"/></td>
                        <td colspan="2" class="td2"><s:textfield cssClass="form-control" cssStyle="display:inline"
                                                                 name="saveManageFranchiseModel.invoiceOption.intlInbound"
                                                                 value="%{franchise.intlInbound}"
                                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield>
                            %
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Other"/></td>
                        <td colspan="2" class="td2">$ <s:textfield cssStyle="display:inline" cssClass="form-control"
                                                                   name="saveManageFranchiseModel.invoiceOption.other"
                                                                   value="%{franchise.other}"
                                                                   onkeypress="return formartNumber(event,this,false);"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Per Airbill Fee - Express"/></td>
                        <td colspan="2" class="td2">$ <s:textfield cssClass="form-control" cssStyle="display:inline"
                                                                   name="saveManageFranchiseModel.invoiceOption.expressPerAirbill"
                                                                   value="%{franchise.expressPerAirbill}"
                                                                   onkeypress="return formartNumber(event,this,false);"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Per Airbill Fee - Freight"/></td>
                        <td colspan="2" class="td2"><s:textfield cssClass="form-control" cssStyle="display:inline"
                                                                 name="saveManageFranchiseModel.invoiceOption.freightPerAirbill"
                                                                 value="%{franchise.freightPerAirbill}"
                                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield>
                            %
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Misc. Charge1"/></td>
                        <td colspan="2">
                            <ul class="ul_charge_amount">
                                <li><s:textfield name="saveManageFranchiseModel.invoiceOption.charge1"
                                                 value="%{franchise.charge1}"
                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield></li>
                                <li><xms:localization text="Amount "/></li>
                                <li>$ <s:textfield name="saveManageFranchiseModel.invoiceOption.charge1Amount"
                                                   value="%{franchise.charge1Amount}"
                                                   onkeypress="return formartNumber(event,this,false);"></s:textfield></li>
                            </ul>
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Misc. Charge2"/></td>
                        <td colspan="2">
                            <ul class="ul_charge_amount">
                                <li><s:textfield name="saveManageFranchiseModel.invoiceOption.charge2"
                                                 value="%{franchise.charge2}"
                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield></li>
                                <li><xms:localization text="Amount "/></li>
                                <li>$ <s:textfield name="saveManageFranchiseModel.invoiceOption.charge2Amount"
                                                   value="%{franchise.charge2Amount}"
                                                   onkeypress="return formartNumber(event,this,false);"></s:textfield></li>
                            </ul>
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Misc. Charge3"/></td>
                        <td colspan="2">
                            <ul class="ul_charge_amount">
                                <li><s:textfield name="saveManageFranchiseModel.invoiceOption.charge3"
                                                 value="%{franchise.charge3}"
                                                 onkeypress="return formartNumber(event,this,false);"></s:textfield></li>
                                <li><xms:localization text="Amount "/></li>
                                <li>$ <s:textfield name="saveManageFranchiseModel.invoiceOption.charge3Amount"
                                                   value="%{franchise.charge3Amount}"
                                                   onkeypress="return formartNumber(event,this,false);"></s:textfield></li>
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
                        <td colspan="2" class="td2">$ <s:textfield onkeypress="return formartNumber(event,this,false);"
                                                                   cssStyle="display:inline" cssClass="form-control"
                                                                   name="saveManageFranchiseModel.invoiceOption.swMaintenance"
                                                                   value="%{franchise.swMaintenance}"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Software Development"/></td>
                        <td colspan="2" class="td2">$ <s:textfield onkeypress="return formartNumber(event,this,false);"
                                                                   cssStyle="display:inline" cssClass="form-control"
                                                                   name="saveManageFranchiseModel.invoiceOption.swDevelopment"
                                                                   value="%{franchise.swDevelopment}"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Marketing"/></td>
                        <td colspan="2" class="td2">$ <s:textfield onkeypress="return formartNumber(event,this,false);"
                                                                   cssStyle="display:inline" cssClass="form-control"
                                                                   name="saveManageFranchiseModel.invoiceOption.marketing"
                                                                   value="%{franchise.marketing}"></s:textfield>
                        </td>
                    </tr>
                    <tr>
                        <td class="td1"><xms:localization text="Web Freight"/></td>
                        <td colspan="2" class="td2"><s:textfield onkeypress="return formartNumber(event,this,false);"
                                                                 cssClass="form-control"
                                                                 name="saveManageFranchiseModel.invoiceOption.webship"
                                                                 value="%{franchise.webship}"></s:textfield></td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
    <div id="div_save_invoice_option_result"></div>
</form>
<script type="text/javascript">
    function saveCustomerInvoiceOptions() {
        var data = $("#frmSaveFranchiseInvoiceOptions").serialize();
        loadingDialog.dialog("open");
        $.post("manage_franchise_invoice_options_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            dialog.html(res.content);
            dialog.dialog("open");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
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
        dialog.dialog("option", "title", "Save Invoice Option Result.");
    }


</script>