<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="assign_airbill_form">
    <table style="width: 250px;">
        <tr>
            <td style="min-width: 90px;"><xms:localization text="Customer#: "/></td>
            <td><input id="search_code" type="text" class="form-control" onkeyup="onCustomerCodeKeyPress()"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Customer: "/><span style="color: red">*</span></td>
            <td id="customer_list"><s:select id="customer_select_list" list="customerList" listKey="customerCode"
                                             listValue="customerCode + ' - ' + customerName" cssClass="form-control"
                                             onchange="onSelectCustomer()"/></td>
        </tr>
        <tr>
            <td colspan="2"><span id="search_customer_code" style="color:red"><s:property value="customerCode"/></span>
            </td>
        </tr>
    </table>
</form>
<script type="text/javascript">
    function onCustomerCodeKeyPress() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            searchCustomers();
        }, 500);
        $(this).data('timer', wait);
    }

    function searchCustomers() {
        var data = "customerCode=" + $("#search_code").val();
        $.post("customer_quick_search_do.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#customer_list").html(res.content);
                onSelectCustomer();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function onSelectCustomer() {
        var selectedCode = $("#customer_select_list").val();
        if (selectedCode != "" && selectedCode != null) {
            $("#search_customer_code").html(selectedCode);
        } else {
            $("#search_customer_code").html("<xms:localization text="No customer found." />");
        }
    }


</script>