<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<script src="script/webship/customer/dato.tablesorter.js"></script>
<div class="row">
    <div class="form-group">
        <table class="s36 d1">
            <tr>
                <td><s:textfield cssClass="form-control" name="name" id="txtQuickSearchLoginAsName"/></td>
                <td>
                    <button class="btn s33" type="button" onclick="doQuickSearchLoginAs()">
                        <xms:localization text="Search"/>
                    </button>
                </td>
            </tr>
        </table>
        <br/>

        <table class="s32 table table-striped table-bordered" border="0" id="login_as_table">
            <thead>
            <tr>
                <th align="left"><xms:localization text="Customer ID"/></th>
                <th align="left"><xms:localization text="Customer Name"/></th>
            </tr>
            </thead>
            <s:if test="userList==null || userList.size()==0">
                <td align="left" colspan="2"><xms:localization text="No data available..."/></td>
            </s:if>
            <s:else>
                <s:iterator value="userList">
                    <tr style="cursor:pointer" onclick="doLoginAsChild('<s:property value="webshipId"/>')">
                        <td align="left"><s:property value="customerCode"/></td>
                        <td align="left"><s:property value="customerName"/></td>
                    </tr>
                </s:iterator>
            </s:else>
        </table>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
//        console.log("table order");
        var fieldList = ["customer_code", "customer_name"];
        $("#login_as_table").tablesorter({
            sortFieldId: "login_as_table_field",
            sortTypeId: "login_as_table_type",
            fieldList: fieldList,
            callback: doQuickSearchLoginAs
        });
    });

    function doQuickSearchLoginAs() {

        var dataOrder = $("#login_as_order_form").serialize();
        console.log("Data order: " + dataOrder);
        var name = $("#txtQuickSearchLoginAsName").val();
        var orderField = $("#login_as_table_field").val();
        var orderType = $("#login_as_table_type").val();
        var data = {
            "name": name,
            "orderField": orderField,
            "orderType": orderType
        };
        $.post("quick_search_login_as.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                loginAsDialog.html(res.content);
                loginAsDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function doLoginAsChild(webshipId) {
        var data = {
            "user.webshipId": webshipId
        };
        $.post("login_as.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                window.location = "webship.ix";
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>