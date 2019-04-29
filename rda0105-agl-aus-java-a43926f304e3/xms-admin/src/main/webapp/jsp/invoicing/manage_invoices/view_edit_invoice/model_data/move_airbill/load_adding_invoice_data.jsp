<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:hidden name="customerCode" id="customerCode"/>
<div id="md-4" title="Adding Invoice">
    <div class="form-group">
        <p align="center">
            <b><xms:localization text="Adding Invoice"/></b>
        </p>
        <table class="table" style="font-size: 11px;">
            <tr>
                <td class="td1"><xms:localization text="Customer#:"/><span class="s30">*</span></td>
                <td class="td2"><input class="form-control alloptions" type="text" id="txt_customer_code_search"
                                       placeholder="" maxlength="25" onkeyup="addingInvoiceCustomersSearch()"
                                       style="width: 250px"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Customer:"/><span class="s30">*</span></td>
                <td class="td2" id="td_result_customer_list"></td>
            <tr>
                <td colspan="2" id="td_result_customer_code" style="color: red;"></td>
            </tr>
        </table>
    </div>
</div>
<script type="text/javascript">
    function addingInvoiceCustomersSearch() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            loadCustomerCode();
        }, 500);
        $(this).data('timer', wait);
    }

    function loadCustomerCode() {
        var action_load_customer = "view_edit_invoice_get_customer_code.ix?reqType=json";
        var data_customer = {
            'customerCode': $("#txt_customer_code_search").val()
        };
        $.post(action_load_customer, data_customer, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#div_adding_invoice #td_result_customer_list").html(res.content);
                loadCustomerName();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function loadCustomerName() {
        var textCustomer = $("#select_invoice_code option:selected").text();
        console.log(textCustomer);
        $("#div_adding_invoice #td_result_customer_code").html(textCustomer);
        $("#div_adding_invoice #customerCode").val($("#select_invoice_code").val());
    }


</script>