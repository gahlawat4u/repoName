<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Surcharge List"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Surcharge List"/></li>
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
                                    <xms:localization text="Surcharge List"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <p>
                                            <xms:localization text="<b>Note:</b>
											<br>
											- This is the Accessorial information used for invoices and airbill imports.
											<br>
											Double-click the entry to modify its value.
											<br>"/>
                                        </p>

                                        <div class="row">
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select id="surcharge_page_size"
                                                                                  name="pageSize" list="listPageSize"
                                                                                  cssClass="form-control"
                                                                                  onchange="doSearch()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                </table>
                                                <div id="accessorial-list-div">
                                                    <s:hidden id="surcharge_page" name="page"/>
                                                    <s:hidden id="orderField" name="orderField"/>
                                                    <s:hidden id="orderType" name="orderType"/>
                                                    <table class="table table-bordered mg0 table-hover table-pointer"
                                                           id="surcharge-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Accessorial Code"/></th>
                                                            <th><xms:localization text="Last Modified"/></th>
                                                            <th><xms:localization text="Accessorial Type"/></th>
                                                            <th><xms:localization text="Accessorial Description"/></th>
                                                            <th><xms:localization text="Carrier"/></th>
                                                            <th><xms:localization text="Quoteable"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <!-- Multiple filter -->
                                                        <tr id="surcharge-list-multiple-filter">
                                                            <td><s:textfield name="code" cssClass="form-control"
                                                                             onkeyup="searchSurchargeByKeyUp()"/></td>
                                                            <td><s:textfield name="modifiedDate"
                                                                             cssClass="form-control form_datetime"
                                                                             data-date-format="dd MM yyyy"
                                                                             onchange="doSearch()"/></td>
                                                            <td><s:select name="type" cssClass="form-control"
                                                                          list="accessorialTypeList" listKey="key"
                                                                          listValue="value" headerKey="" headerValue=""
                                                                          onchange="doSearch()"/></td>
                                                            <td><s:textfield name="description" cssClass="form-control"
                                                                             onkeyup="searchSurchargeByKeyUp()"/></td>
                                                            <td><s:textfield name="carrierName" cssClass="form-control"
                                                                             onkeyup="searchSurchargeByKeyUp()"/></td>
                                                            <td><s:select name="quotable" cssClass="form-control"
                                                                          list="quotableList" listKey="key"
                                                                          listValue="value" headerKey="" headerValue=""
                                                                          onchange="doSearch()"/></td>
                                                        </tr>
                                                        <s:if test="surchargeList.records.isEmpty()">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="surchargeList.records">
                                                                <tr data-accessorialId="<s:property value="accessorialId" />"
                                                                    ondblclick="showAccessorial($(this).attr('data-accessorialId'))">
                                                                    <td><s:property value="code"/></td>
                                                                    <td><s:property value="modifiedDate"/></td>
                                                                    <td><s:property value="typeId"/></td>
                                                                    <td><s:property value="description"/></td>
                                                                    <td><s:property value="serviceName"/></td>
                                                                    <td><s:property value="isQuotable"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <script type="text/javascript">
                                                        $(document).ready(function () {
                                                            var fieldList = ["code", "modified_date", "typeid", "description", "service_name", "isquotable"];
                                                            $('table#surcharge-list-table tbody tr').click(function () {
                                                                var accessorialId = $(this).attr('data-accessorialId');
                                                                if (typeof (accessorialId) != "undefined" && accessorialId != "") {
                                                                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                    selectAccessorial(accessorialId);
                                                                    $('#btnView').attr('disabled', false);
                                                                }
                                                            });
                                                            $("#surcharge-list-table").tablesorter({
                                                                sortFieldId: "orderField",
                                                                sortTypeId: "orderType",
                                                                fieldList: fieldList,
                                                                callback: doSearch
                                                            });
                                                        });
                                                    </script>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="surchargeList.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="surchargeList.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="surchargeList.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!surchargeList.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{surchargeList.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="surchargeList.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{surchargeList.pageRange[#count.index] == surchargeList.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="surchargeList.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!surchargeList.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{surchargeList.currentPage+1}"/>)"><xms:localization
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
                                                    <button class="btn s37" onclick="showAddDialog()">
                                                        <xms:localization text="New Accessorial Charge"/>
                                                    </button>
                                                    <button class="btn s37" id="btnView" onclick="showEditPage()"
                                                            disabled="disabled">
                                                        <xms:localization text="View Accesorial Detail"/>
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
<div id="surcharge-list-dialog"></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var accessorialId = 0;
    function changePage(p) {
        $("#surcharge_page").val(p);
        doSearch();
    }
    function showAccessorial(id) {
        var data = {
            "accessorialId": id,
            "page": $("#surcharge_page").val(),
            "pageSize": $("#surcharge_page_size option:selected").val(),
            "orderField": $("#orderField").val(),
            "orderType": $("#orderType").val()
        };
        loadDialog("surcharge_list_edit.ix?reqType=json", data, "accessorial-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "surcharge-list-dialog", "<xms:localization text="Edit accessorial" />", "accessorial-list-div");
    }
    function selectAccessorial(id) {
        accessorialId = id;
    }
    function showAddDialog() {
        var data = {
            "page": $("#surcharge_page").val(),
            "pageSize": $("#surcharge_page_size option:selected").val(),
            "orderField": $("#orderField").val(),
            "orderType": $("#orderType").val()
        };
        loadDialog("surcharge_list_add.ix?reqType=json", data, "accessorial-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "surcharge-list-dialog", "<xms:localization text="Add new accessorial" />", "accessorial-list-div");
    }
    function showEditPage() {
        if (accessorialId == 0) {
            alertDialog.html("<xms:localization text="Please select a row to view detail." />");
            alertDialog.dialog("open");
            return false;
        }
        var url = "surcharge_details.ix?accessorialId=" + accessorialId;
        window.location.href = url;
    }
    function doSearch() {
        var page = $("#surcharge_page").val();
        var pageSize = $("#surcharge_page_size option:selected").val();
        var data = $("#surcharge-list-multiple-filter input,#surcharge-list-multiple-filter select").serialize() + "&pageSize=" + pageSize + "&page=" + page;
        data += "&orderField=" + $("#orderField").val();
        data += "&orderType=" + $("#orderType").val();
        doPostDataByParameters("surcharge_list_get_data.ix?reqType=json", data, "", "accessorial-list-div", true);
    }
    function searchSurchargeByKeyUp() {
        clearTimeout($.data(this, 'timer'));
        wait = setTimeout(function () {
            doSearch();
        }, 500);
        $(this).data('timer', wait);
    }
</script>
<!--END CONTENT-->
