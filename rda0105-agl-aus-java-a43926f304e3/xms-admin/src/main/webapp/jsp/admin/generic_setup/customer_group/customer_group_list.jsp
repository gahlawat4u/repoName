<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Customer Group"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Customer Group"/></li>
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
                                    <xms:localization text="Customer Group"/>
                                </div>

                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <b><xms:localization text="Note:"/></b> <br/>
                                                    <xms:localization
                                                            text="- This is the list of customer group that are available in the Generic Setup drop-down selector.<br /> - Double-click the entry to modify its value."/>
                                                </p>
                                            </div>
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select list="listPageSize" name='pageSize'
                                                                                  cssClass="form-control"
                                                                                  onchange="javascript:doSearch($(this).val())"/></td>
                                                                    <td><xms:localization text="Entries"/>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <s:hidden id="order_type" name="orderType"/>
                                                <s:hidden id="order_field" name="orderField"/>
                                                <div id="customer-group-list">
                                                    <table class="table table-bordered mg0"
                                                           id="customer-group-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="No"/></th>
                                                            <th><xms:localization text="Customer Group Name"/></th>
                                                        </tr>
                                                        </thead>

                                                        <tbody>
                                                        <s:if test="customerGroupList.records.isEmpty()">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="customerGroupList.records">
                                                                <tr data-customerGroupId="<s:property value="customerGroupId" />">
                                                                    <td><s:property value="customerGroupId"/></td>
                                                                    <td customerGroupId="<s:property value='customerGroupId' />"
                                                                        customerGroupName="<s:property value='customerGroupName'/>"
                                                                        ondblclick="javascript:editcustomergroup($(this).attr('customerGroupId'),$(this).attr('customerGroupName'));">
                                                                        <s:property value="customerGroupName"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>

                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="customerGroupList.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="customerGroupList.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="customerGroupList.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!customerGroupList.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{customerGroupList.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="customerGroupList.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{customerGroupList.pageRange[#count.index] == customerGroupList.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="customerGroupList.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!customerGroupList.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{customerGroupList.currentPage+1}"/>)"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                        <script type="text/javascript">
                                                            $(document).ready(function () {
                                                                $('table#customer-group-list-table tbody tr').click(function () {
                                                                    var customerGroupId = $(this).attr('data-customerGroupId');
                                                                    if (typeof (customerGroupId) != "undefined" && customerGroupId != "") {
                                                                        $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                        selectcustomergroup(customerGroupId);
                                                                        $('#btnView').attr('disabled', false);
                                                                    }
                                                                });
                                                            });
                                                        </script>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions pal pdt10">
                                            <div class="row">
                                                <div class="col-lg-12 text-right">
                                                    <button onclick="javascript:addcustomergroup();"
                                                            id="add-carriers-link" class="btn s37" type="button">
                                                        <xms:localization text="New Customer Group"/>
                                                    </button>
                                                    <button onclick="javascript:deletecustomergroup();" class="btn s37"
                                                            type="button">
                                                        <xms:localization text="Delete Customer
													Group"/>
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
    <div id="add_customer_group_dialog"></div>
    <div id="edit_customer_group_dialog"></div>
    <div id="delete_customer_group_dialog"></div>
    <!--END CONTENT-->
    <script type="text/javascript">
        var page = 1;
        var pageSize = $("#pageSize").val();
        var customerGroupId = 0;
        $(document).ready(function () {

            var fieldList = ["customer_group_id", "customer_group_name"];
            $("#customer-group-list-table").tablesorter({
                sortFieldId: "order_field",
                sortTypeId: "order_type",
                fieldList: fieldList,
                callback: doSearch
            });
        });
        function selectcustomergroup(id) {
            customerGroupId = id;
        }
        function addcustomergroup() {
            var data = {
                "page": page,
                "pageSize": pageSize,
            };
            loadDialog("customer_group_add.ix?reqType=json", data, "customer_group_add_form", "Add", "Cancel", "add_customer_group_dialog", "Add Customer Group", "customer-group-list");
        }

        function deletecustomergroup() {
            if (customerGroupId == 0) {
                alertDialog.html("<xms:localization text="Please select a row to delete." />");
                alertDialog.dialog("open");
                return false;
            }
            var data = {
                "page": page,
                "pageSize": pageSize,
                'customerGroupModel.customerGroupId': customerGroupId,
            };
            loadDeleteDialog("customer_group_delete.ix?reqType=json", data, "Are you sure you want to delete this customer group", "delete_customer_group_dialog", "customer-group-list", "Delete", "Cancel", "Delete customer group");
            customerGroupId = 0;
        }

        function editcustomergroup(customerGroupId, customerGroupName) {
            var data = {
                "page": page,
                "pageSize": pageSize,
                'customerGroupModel.customerGroupId': customerGroupId,
                'customerGroupModel.customerGroupName': customerGroupName
            };
            loadDialog("customer_group_edit.ix?reqType=json", data, "customer_group_edit_form", "Edit", "Cancel", "edit_customer_group_dialog", "Edit Customer Group", "customer-group-list");
        }

        function doSearch(ps, page) {
            pageSize = typeof (ps) != "undefined" ? ps : pageSize;
            var p = typeof (page) != "undefined" ? page : 1;
            var data = "page=" + p;
            data = data + "&pageSize=" + pageSize;
            data = data + "&orderField=" + $("#order_field").val();
            data = data + "&orderType=" + $("#order_type").val();
            loadingDialog.dialog("open");
            $.post("customer_group_search.ix?reqType=json", data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    $("#customer-group-list").html(res.content);
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

        function changePage(page) {
            var pageSize = $("select[name='pageSize'] option:selected").val();
            doSearch(pageSize, page);
        }

        function onChangePageSize() {
            var pageSize = $("select[name='pageSize'] option:selected").val();
            doSearch(pageSize, 1);
        }


    </script>