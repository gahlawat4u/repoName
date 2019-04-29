<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="form_new_invoice">
    <s:hidden name="airbillInfo.customerCode" id="customerCode"/>
    <s:hidden name="invoiceStatus" value="0"/>
    <s:hidden name="invoiceId" value="164508"/>
    <div id="md-4" title="Adding Invoice">
        <div class="form-group">
            <p align="center">
                <b><xms:localization text="Adding Invoice"/></b>
            </p>
            <table class="table" style="font-size: 11px; max-width: 100%">
                <tr>
                    <td class="td1"><xms:localization text="Customer#:"/><span class="s30">*</span></td>
                    <td class="td2"><input class="form-control alloptions" type="text" id="txt_customer_code_search"
                                           placeholder="" maxlength="25" onkeyup="onKeyUpLoadCustomerCode()"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Customer:"/><span class="s30">*</span></td>
                    <td class="td2" id="td_result_customer_list"></td>
                <tr>
                    <td colspan="2" id="td_result_customer_code" style="color: red;"></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Invoice Date:"/><span class="s30">*</span></td>
                    <td class="td2"><input readonly="readonly" name="invoiceDate"
                                           class="form-control alloptions form_datetime" type="text" placeholder=""
                                           maxlength="25"></td>
                </tr>
            </table>
        </div>
    </div>
</s:form>
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
    function loadCustomerCode() {
        var action_load_customer = "view_edit_invoice_get_customer_code.ix?reqType=json";
        var data_customer = {
            'customerCode': $("#txt_customer_code_search").val()
        };
        $.post(action_load_customer, data_customer, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#td_result_customer_list").html(res.content);
                $("#customerCode").val($("#select_invoice_code").val());
            } else {
                alertDialog.html('Error: ' + res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }
    function loadCustomerName() {
        var textCustomer = $("#select_invoice_code option:selected").text();
        $("#td_result_customer_code").html(textCustomer);
        $("#customerCode").val($("#select_invoice_code").val());
    }
    function onKeyUpLoadCustomerCode() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            loadCustomerCode();
        }, 500);
        $(this).data('timer', wait);
    }
</script>
