<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_mass_edit_move_airbill">
            <table class="s36 b24" style="width: auto; margin-bottom: auto">
                <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
                <s:if test="isMultiAirbill==false">
                    <tr>
                        <td><xms:localization text="Airbill"/></td>
                        <td colspan="4"><s:textfield cssClass="form-control alloptions" name="airbillNumber"
                                                     disabled="disabled"></s:textfield>
                    </tr>
                </s:if>
                <tr>
                    <td><xms:localization text="Invoice"/></td>
                    <td><label class="radio-inline cl666"> <input id="optionInvoiceExisting" type="radio"
                                                                  class="moveInvoiceType" checked="checked" value="0"
                                                                  name="moveInvoiceType">
                    </label></td>
                    <td><xms:localization text="Existing"/> :<span class="s30">*</span></td>
                    <td colspan="2"><s:select list="invoiceExistings" listValue="customerName" listKey="invoiceCode"
                                              name="invoiceCode" value="invoiceCode"/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><label class="radio-inline cl666"> <input id="optionInvoiceNew" class="moveInvoiceType"
                                                                  type="radio" value="1" name="moveInvoiceType">
                    </label></td>
                    <td><xms:localization text="New"/></td>
                    <td>
                        <button class="btn s37" disabled="disabled" id="btn_select_customer_code" type="button"
                                onclick="loadCustomer()">
                            <xms:localization text="Select Customer"/>
                        </button>
                        <s:hidden id="mass_edit_customerCode" name="customerCode"/></td>
                    <s:if test="adminLevel <= 2">
                        <td>
                            <div class="form-group input-group mg0">
								<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
								</span> <input readonly="readonly" name="invoiceDate" class="form-control form_datetime"
                                               type="text" data-date-format="dd MM yyyy" placeholder="End"/>
                            </div>
                        </td>
                    </s:if>
                </tr>
                <tr>
                    <td colspan="5"><xms:localization
                            text="Any customer adjustment credit will also be moved to the target invoice"/></td>
                </tr>
                <tr>
                    <td colspan="3"><xms:localization text="Recalc Customer Cost"/></td>
                    <td><input type="checkbox" name="checkRecalcCustomerCost" value="true"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div id="div_adding_invoice"></div>
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

    $(".moveInvoiceType").click(function () {
        if ($(this).val() != 1) {
            $("#btn_select_customer_code").prop("disabled", true);
        } else {
            $("#btn_select_customer_code").removeAttr("disabled")
        }
    });
    function loadCustomer() {
        var buttons = {};
        loadingDialog.dialog("open");
        var action = "view_edit_invoice_get_adding_invoice.ix?reqType=json";

        buttons["Save"] = function () {
            var custCode = $("#select_invoice_code").val();
            if (custCode == null || custCode.trim() == "") {
                messageDialog.html('<xms:localization text="Please select a customer." />');
                messageDialog.dialog("open");
            } else {
                $("#btn_select_customer_code").html($("#select_invoice_code").val());
                $("#mass_edit_customerCode").val($("#select_invoice_code").val());
                console.log("mass edit: " + $("#mass_edit_customerCode").val());
                dialog.dialog("close");
                dialog.empty();
            }
        };

        buttons["Close"] = function () {
            $(this).dialog("close");
            $(this).empty();
        }
        var dialog = $("#div_adding_invoice").dialog({
            modal: true,
            buttons: buttons,
            width: "auto",
            show: {
                effect: "fade",
                duration: 300
            },
            close: function () {
                dialog.html("");
            }
        });
        dialog.dialog("option", "title", '<xms:localization text="Adding Invoice" />');

        $.post(action, "", function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
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


</script>