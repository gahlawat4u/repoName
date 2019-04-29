<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>

<div class="row">
    <div class="col-lg-12">
        <p>
            <b><xms:localization text="Note"/> :</b> <br/> -
            <xms:localization text="Double-click a setting to modify its value"/>
            .<br/> -
            <xms:localization text="Any change to the values below will take effect immediately"/>
            .
        </p>
    </div>
    <div class="col-lg-12">
        <table class="table mg0">
            <tbody>
            <tr>
                <th class="s42">
                    <form id="franchise_markup_form">
                        <s:hidden id="franchise_markup_order_field" name="orderField"/>
                        <s:hidden id="franchise_markup_order_type" name="orderType"/>
                        <s:hidden id="franchise_markup_page" name="page"/>
                        <table class="s36">
                            <tbody>
                            <tr>
                                <td><xms:localization text="Show"/></td>
                                <td><s:select id="franchise_markup_page_size" name="pageSize" list="pageSizes"
                                              onchange="onChangePageSize()"/></td>
                                <td><xms:localization text="Entries"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </th>
            </tr>
            </tbody>
        </table>
        <div id="listDataMarkup">
            <s:hidden name="franchiseCode"></s:hidden>
            <s:hidden id="franchise_code"></s:hidden>
            <s:hidden id="accessorial_id" name="accessorialId"></s:hidden>
            <table class="table table-bordered mg0" id="franchise_markup_table">
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
                <tr id="tr_filter_markup">
                    <td><s:textfield name="description" class="filter form-control s50"
                                     onkeyup="searchOnKeypress()"></s:textfield></td>
                    <td><s:textfield name="code" class="filter form-control s50"
                                     onkeyup="searchOnKeypress()"></s:textfield></td>
                    <td><s:textfield name="typeName" class="filter form-control s50"
                                     onkeyup="searchOnKeypress()"></s:textfield></td>
                    <td><s:textfield name="amount" class="filter form-control s50"
                                     onkeyup="searchOnKeypress()"></s:textfield></td>
                    <td><s:textfield name="serviceName" class="filter form-control s50"
                                     onkeyup="searchOnKeypress()"></s:textfield></td>
                    <td><s:textfield name="modifiedDate" class="filter form-control s50"
                                     onkeyup="searchOnKeypress()"></s:textfield></td>
                </tr>
                <s:if test="markups.records.size != 0">
                    <s:iterator value="markups.records">
                        <tr id="edit-markup-1-link" class="edit-markup">
                            <td><s:property value="description"/> <s:hidden id="accessorialId"
                                                                            value="%{accessorialId}"></s:hidden>
                                <s:hidden id="franchiseCode" value="%{franchiseCode}"></s:hidden></td>
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
                        <td colspan="6"><xms:localization text="No record to view"/> ...</td>
                    </tr>
                </s:else>
                </tbody>
            </table>
            <div class="dataTables_paginate records" style="margin-bottom:15px;">
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
                            <a href="javascript:changePage(<s:property value="%{markups.currentPage - 1}"/>)"
                               class="paginate_button previous"><xms:localization text="Previous"/></a>
                        </s:else>
						<span> <s:iterator value="markups.pageRange" status="count">
                            <s:if test="%{markups.pageRange[#count.index] == markups.currentPage}">
                                <a class="paginate_button current"><s:property value="markups.currentPage"/></a>
                            </s:if>
                            <s:else>
                                <a class="paginate_button"
                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                            </s:else>
                        </s:iterator>
						</span>
                        <s:if test="!markups.hasNext()">
                            <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
                        </s:if>
                        <s:else>
                            <a class="paginate_button next"
                               href="javascript:changePage(<s:property value="%{markups.currentPage+1}"/>)"><xms:localization
                                    text="Next"/></a>
                        </s:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="div_edit_markup"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $('table#franchise_markup_table tbody tr.edit-markup').click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            var accessorialId = $(this).find('#accessorialId').val();
            var franchiseCode = $(this).find('#franchiseCode').val();
            $("#accessorial_id").val(accessorialId);
            $("#franchise_code").val(franchiseCode);
        });

        $('table#franchise_markup_table tbody tr.edit-markup').dblclick(function () {
            viewMarkupDetail();
        });

        var fieldList = ["description", "code", "typename", "amount", "service_name", "modified_date"];
        $("#franchise_markup_table").tablesorter({
            sortFieldId: "franchise_markup_order_field",
            sortTypeId: "franchise_markup_order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });

    function doSearch() {
        var data = $("#franchise_markup_form").serialize();
        data += "&" + $("#tr_filter_markup input").serialize();
        data += "&franchiseCode=" + $("#sel_franchise").val();
        loadingDialog.dialog("open");
        $.post("manage_franchise_markups_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#listDataMarkup").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function searchOnKeypress() {
        $("#franchise_markup_page").val("1");
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            doSearch();
        }, 500);
        $(this).data('timer', wait);
    }

    function changePage(page) {
        $("#franchise_markup_page").val(page);
        doSearch();
    }

    function onChangePageSize() {
        $("#franchise_markup_page").val(1);
        doSearch();
    }

    function viewMarkupDetail() {
        var accessorial_id = $('#accessorial_id').val();
        var franchise_code = $("input[name=franchiseCode]").val();
        var pageSize = $("#franchise_markup_page_size").val();
        var page = $("#franchise_markup_page").val();
        var data = 'filterMarkup.accessorialId=' + accessorial_id + '&filterMarkup.page=' + page + '&filterMarkup.pageSize=' + pageSize + '&filterMarkup.franchiseCode=' + franchise_code + '&franchiseCode=' + $("input[name=franchiseCode]").val();
        data += "&" + $("#tr_filter_markup input").serialize();
        loadDialog("manage_franchise_markups_edit.ix?reqType=json", data, "form_markup_edit", "Edit", "Cancel", "div_edit_markup", "Edit Markup", "listDataMarkup");
    }


</script>