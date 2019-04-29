<%@ page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Banks"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active"><xms:localization text="Banks"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
    <div id="tab-general">
        <div class="row mbl">
            <div class="col-lg-12">
                <div class="col-md-12">
                    <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Banks"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <xms:localization
                                                            text="<b>Note :</b> <br /> - This is the list of banks that are available in the Bank selector when posting payments.<br /> - Double-click the entry to get its Detail Page."/>
                                                </p>
                                            </div>
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <form id="bank_list_search_form">
                                                                <s:hidden id="bank_list_order_type" name="orderType"/>
                                                                <s:hidden id="bank_list_order_field" name="orderField"/>
                                                                <s:hidden id="bank_list_page" name="page"/>
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization text="Show"/></td>
                                                                        <td><s:select id="bank_list_page_size"
                                                                                      name="pageSize"
                                                                                      list="listPageSize"
                                                                                      cssClass="form-control"
                                                                                      onchange="doSearch()"/></td>
                                                                        <td><xms:localization text="Entries"/></td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </form>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="bank-list-div">
                                                    <table class="table table-bordered mg0" id="bank-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Id"/></th>
                                                            <th><xms:localization text="Bank Name"/></th>
                                                            <th><xms:localization text="Admin Level"/></th>
                                                            <th><xms:localization text="Last Modified"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="bankList==null || bankList.totalRecords==0">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="bankList.records">
                                                                <tr data-bankId="<s:property value="bankId" />"
                                                                    ondblclick="editBank($(this).attr('data-bankId'))">
                                                                    <td><s:property value="bankId"/></td>
                                                                    <td><s:property value="bankName"/></td>
                                                                    <td><s:property
                                                                            value="userLevel.userLevelName"/></td>
                                                                    <td><s:property value="modifiedDate"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="bankList.startRecord"/> <xms:localization
                                                                        text="to"/> <s:property
                                                                        value="bankList.endRecord"/> <xms:localization
                                                                        text="of"/> <s:property
                                                                        value="bankList.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!bankList.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{bankList.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="bankList.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{bankList.pageRange[#count.index] == bankList.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="bankList.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!bankList.hasNext()">
                                                                    <a class="paginate_button next disabled"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{bankList.currentPage+1}"/>)"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions pal pdt10">
                                            <div class="row">
                                                <div class="col-lg-12 text-right">
                                                    <button class="btn s37" id="add-carriers-link"
                                                            onclick="javascript:showAddDialog()">
                                                        <xms:localization text="New Bank"/>
                                                    </button>
                                                    <button class="btn s37" id="btnRemove" disabled="disabled"
                                                            onclick="javascript:deleteBank();">
                                                        <xms:localization text="Delete Bank"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="bank-list-dialog"></div>

<script type="text/javascript">
    var bankId = 0;

    $(document).ready(function () {
        $('table#bank-list-table tbody tr').click(function () {
            var bId = $(this).attr('data-bankId');
            if (typeof (bId) != "undefined" && bId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                bankId = bId;
                $('#btnRemove').attr('disabled', false);
            }
        });
        var fieldList = ["bankid", "bankname", "ul_user_level_name", "modified_date"];
        $("#bank-list-table").tablesorter({
            sortFieldId: "bank_list_order_field",
            sortTypeId: "bank_list_order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });

    function changePage(page) {
        $("bank_list_page").val(page);
        doSearch();
    }

    function editBank(id) {
        var data = {
            "bankId": id,
            "page": $("bank_list_page").val(),
            "pageSize": $("bank_list_page_size").val()
        };
        loadDialog("bank_edit.ix?reqType=json", data, "bank-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "bank-list-dialog", "<xms:localization text="Edit Bank" />", "bank-list-div");
    }

    function deleteBank() {
        if (bankId == 0) {
            alertDialog.html("<xms:localization text="Please select a row to delete." />");
            alertDialog.dialog("open");
            return false;
        }
        var data = {
            "page": $("bank_list_page").val(),
            "pageSize": $("bank_list_page_size").val(),
            "bankId": bankId,
        };
        loadDeleteDialogWithCallBack("bank_delete.ix?reqType=json", data, "<xms:localization text="Are you sure to delete this Bank  " />", "bank-list-dialog", "bank-list-div", "<xms:localization text="Delete" />", "<xms:localization text="Cancel" />", "<xms:localization text="Delete Bank " />",
                function () {
                    $("#btnRemove").attr("disabled", true);
                });
    }

    function showAddDialog() {
        var data = {
            "page": $("bank_list_page").val(),
            "pageSize": $("bank_list_page_size").val()
        };
        loadDialog("bank_add.ix?reqType=json", data, "bank-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "bank-list-dialog", "<xms:localization text="Add New Bank " />", "bank-list-div");
    }

    function doSearch() {
        var data = $("#bank_list_search_form").serialize();
        loadingDialog.dialog("open");
        $.post("bank_list_get_data.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#bank-list-div").html(res.content);
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