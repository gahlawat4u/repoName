<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<div class="row">
    <div class="col-lg-12">
        <p>
            <b><xms:localization text="Note"/> :</b> <br/>
            <xms:localization text="- Double-click a setting to modify its value."/>
            <br/>
            <xms:localization text="- Any change to the values below will take effect immediately."/>
        </p>
    </div>
    <div class="col-lg-12">
        <table class="table mg0">
            <tbody>
            <tr>
                <th class="s42">
                    <form id="customer_markup_form">
                        <s:hidden id="customer_markup_order_field" name="orderField"/>
                        <s:hidden id="customer_markup_order_type" name="orderType"/>
                        <s:hidden id="customer_markup_page" name="page"/>
                        <table class="s36">
                            <tbody>
                            <tr>
                                <td><xms:localization text="Show"/></td>
                                <td><s:select id="customer_markup_page_size" name="pageSize" cssClass="form-control"
                                              cssStyle="height: 22px; padding-top: 1px;" list="pageSizes"
                                              onchange="getMarkups(1)"/></td>
                                <td><xms:localization text="Entries"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </th>
            </tr>
            </tbody>
        </table>
        <div id="markups-list-result">
            <table class="table table-bordered mg0" id="customer_markup_table">
                <thead>
                <tr>
                    <th><xms:localization text="Description"/></th>
                    <th><xms:localization text="Id"/></th>
                    <th><xms:localization text="Markup Type"/></th>
                    <th><xms:localization text="Amount"/></th>
                    <th><xms:localization text="Carrier"/></th>
                    <th><xms:localization text="Last Modified"/></th>
                </tr>
                </thead>
                <tbody>
                <tr id="markup-filter">
                    <td><s:textfield name="description" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
                    <td><s:textfield name="code" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
                    <td><s:textfield name="typeName" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
                    <td><s:textfield name="amount" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
                    <td><s:textfield name="serviceName" cssClass="form-control s50" onkeyup="searchOnKeypress()"/></td>
                    <td><input class="form-control s50"></td>
                </tr>
                <s:if test="markups!=null && markups.totalRecords>0">
                    <s:iterator value="markups.records">
                        <tr style="cursor: pointer;" ondblclick="editMarkup($(this))"
                            accessorial-id="<s:property value="accessorialId" />">
                            <td><s:property value="description"/></td>
                            <td><s:property value="code"/></td>
                            <td><s:property value="typeName"/></td>
                            <td><s:property value="amount"/></td>
                            <td><s:property value="serviceName"/></td>
                            <td><s:property value="modifiedDate"/></td>
                        </tr>
                    </s:iterator>
                </s:if>
                <s:else>
                    <tr>
                        <td colspan="6"><xms:localization text="No data available"/>...</td>
                    </tr>
                </s:else>
                </tbody>
            </table>
            <div class="dataTables_paginate records">
                <div class="row">
                    <div class="col-xs-4 text-left">
                        <b><xms:localization text="Showing"/> <s:property value="markups.startRecord"/>
                            <xms:localization text="to"/> <s:property value="markups.endRecord"/> <xms:localization
                                    text="of"/> <s:property value="markups.totalRecords"/> <xms:localization
                                    text="entries"/></b>
                    </div>
                    <div class="col-xs-8">
                        <s:if test="!markups.hasPrev()">
                            <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
                        </s:if>
                        <s:else>
                            <a href="javascript:getMarkups(<s:property value="%{markups.currentPage - 1}"/>)"
                               class="paginate_button previous"><xms:localization text="Previous"/></a>
                        </s:else>
						<span> <s:iterator value="markups.pageRange" status="count">
                            <s:if test="%{markups.pageRange[#count.index] == markups.currentPage}">
                                <a class="paginate_button current"><s:property value="markups.currentPage"/></a>
                            </s:if>
                            <s:else>
                                <a class="paginate_button"
                                   href="javascript:getMarkups(<s:property/>);"><s:property/></a>
                            </s:else>
                        </s:iterator>
						</span>
                        <s:if test="!markups.hasNext()">
                            <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
                        </s:if>
                        <s:else>
                            <a class="paginate_button next"
                               href="javascript:getMarkups(<s:property value="%{markups.currentPage+1}"/>)"><xms:localization
                                    text="Next"/></a>
                        </s:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Edit Markup Dialog -->
<div id="edit-markup-dialog" title="Edit Markup">
    <input type="hidden" id="markup_accessorialId"/>

    <div class="form-group">
        <label><xms:localization text="ID"/> :</label> <input id="markup_code" class="form-control alloptions"
                                                              type="text" maxlength="25" disabled="disabled"/>
    </div>
    <div class="form-group">
        <label><xms:localization text="Description"/> :</label> <input id="markup_desc" class="form-control alloptions"
                                                                       type="text" maxlength="25" disabled="disabled"/>
    </div>
    <div class="form-group">
        <label><xms:localization text="Markup Type"/> :</label> <input id="markup_type" class="form-control alloptions"
                                                                       type="text" maxlength="25" disabled="disabled"/>
    </div>
    <div class="form-group">
        <label><xms:localization text="Amount"/> :</label> <input id="markup_amount"
                                                                  onkeypress="return formartNumber(event,this,true);"
                                                                  class="form-control alloptions" type="text"
                                                                  maxlength="25"/>
    </div>
</div>

<script type="text/javascript">
    var editMarkupDialog = $("#edit-markup-dialog").dialog({
        autoOpen: false,
        width: 400,
        modal: true,
        buttons: [{
            text: "Change Markup",
            click: function () {
                var data = {
                    "markup.customerCode": $("#customerCode option:selected").val(),
                    "markup.accessorialid": $("#edit-markup-dialog #markup_accessorialId").val(),
                    "markup.amount": $("#markup_amount").val()
                };
                $.post("manage_customers_markups_edit.ix?reqType=json", data, function (res) {
                    if (res.errorCode == "SUCCESS") {
                        getMarkups(1);
                        editMarkupDialog.dialog("close");
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
        }, {
            text: "Cancel",
            click: function () {
                $(this).dialog("close")
            }
        }]
    });

    $(document).ready(function () {
        var fieldList = ["description", "code", "typename", "amount", "service_name", "modified_date"];
        $("#customer_markup_table").tablesorter({
            sortFieldId: "customer_markup_order_field",
            sortTypeId: "customer_markup_order_type",
            fieldList: fieldList,
            callback: searchCustomerMarkup
        });
    });

    function searchCustomerMarkup() {
        // Load Markups List
        var data = $("#customer_markup_form").serialize();
        data += "&" + $("#markup-filter input").serialize();
        data += "&customerCode=" + $("#customerCode option:selected").val();
        loadingDialog.dialog("open");
        $.post("manage_customers_markups_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#markups-list-result").html(res.content);
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

    function getMarkups(page) {
        $("#customer_markup_page").val(page);
        searchCustomerMarkup();
    }

    function searchOnKeypress() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            getMarkups(1);
        }, 500);
        $(this).data('timer', wait);
    }

    function editMarkup(obj) {
        var tdList = $(obj).find("td");
        var accessorialId = $(obj).attr("accessorial-id");
        var desc = tdList.eq(0).html();
        var code = tdList.eq(1).html();
        var type = tdList.eq(2).html();
        var amount = tdList.eq(3).html();
        $("#edit-markup-dialog #markup_accessorialId").val(accessorialId);
        $("#edit-markup-dialog #markup_code").val(code);
        $("#edit-markup-dialog #markup_desc").val(desc);
        $("#edit-markup-dialog #markup_type").val(type);
        $("#edit-markup-dialog #markup_amount").val(amount);
        editMarkupDialog.dialog("open");
    }


</script>