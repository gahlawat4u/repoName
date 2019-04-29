<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="form-group">
        <form id="form_change_customer">
            <table class="s36 d1">
                <s:hidden name="listAirbillStr" fieldValue="listAirbillStr"/>
                <tr>
                    <td><xms:localization text="Customer#:"/><span class="s30">*</span></td>
                    <td><input class="form-control alloptions" type="text" id="txt_customer_code_search" placeholder=""
                               maxlength="25" onchange="loadCustomerCode()"></td>
                </tr>
                <tr>
                    <td><xms:localization text="Customer:"/><span class="s30">*</span></td>
                    <td id="td_result_customer_list"><select name="customerCode"
                                                             class="form-control alloptions"></select></td>
                <tr>
                <tr>
                    <td><xms:localization text="Recalc Customer Cost"/></td>
                    <td><input type="checkbox" name="checkRecalcCustomerCost" id="checkRecalcCustomerCost"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<script type="text/javascript">
    function loadCustomerCode() {
        loadingDialog.dialog("open");
        var action_load_customer = "view_edit_invoice_get_customer_code.ix?reqType=json";
        var data_customer = {
            'customerCode': $("#txt_customer_code_search").val()
        };
        $.post(action_load_customer, data_customer, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#td_result_customer_list").html(res.content);
            } else {
                alertDialog.html('Error: ' + res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }
</script>