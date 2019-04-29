<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li>
            <i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="hidden">
            <a href="#"><xms:localization text="Industries"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;
        </li>
        <li class="active">
            <xms:localization text="Industries"/>
        </li>
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
                    <div id="industry-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Industries"/>
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
                                                            text="- This is the Industry List.<br /> - Double-click the entry to modify its value."/>
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
                                                <div id="industry-list">
                                                    <table class="table table-bordered mg0" id="industry-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="System ID"/></th>
                                                            <th><xms:localization text="Industry Name"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="industryList.records.isEmpty()">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="industryList.records">
                                                                <tr data-industryId="<s:property value="industryId" />">
                                                                    <td><s:property value="industryId"/></td>
                                                                    <td industryId="<s:property value='industryId' />"
                                                                        industryName="<s:property value='industryName'/>"
                                                                        ondblclick="javascript:editindustry($(this).attr('industryId'),$(this).attr('industryName'));">
                                                                        <s:property value="industryName"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="industryList.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="industryList.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="industryList.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!industryList.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{industryList.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>

																<span> <s:iterator value="industryList.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{industryList.pageRange[#count.index] == industryList.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="industryList.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!industryList.hasNext()">
                                                                    <a class="paginate_button next"
                                                                       href="#"><xms:localization text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{industryList.currentPage+1}"/>)"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                        <script type="text/javascript">
                                                            $(document).ready(function () {
                                                                $('table#industry-list-table tbody tr').click(function () {
                                                                    var industryId = $(this).attr('data-industryId');
                                                                    if (typeof (industryId) != "undefined" && industryId != "") {
                                                                        $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                        selectindustry(industryId);
                                                                        $('#btnView').attr('disabled', false);
                                                                    }
                                                                });
                                                            });
                                                        </script>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-lg-12 text-right">
                                                    <div class="form-actions pal pdt10">
                                                        <button id="add-carriers-link" class="btn s37" type="button"
                                                                onclick="javascript:addindustry();">
                                                            <xms:localization text="New Industry"/>
                                                        </button>
                                                        <button class="btn s37" type="button"
                                                                onclick="javascript:deleteindustry();">
                                                            <xms:localization text="Delete Industry"/>
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
</div>
<div id="add_industry_dialog"></div>
<div id="edit_industry_dialog"></div>
<div id="delete_industry_dialog"></div>
<!--END CONTENT-->
<script type="text/javascript">
    var page = 1;
    var pageSize = $("select[name='pageSize'] option:selected").val();
    var industryId = 0;

    $(document).ready(function () {
        var fieldList = ["industry_id", "industry_name"];
        $("#industry-list-table").tablesorter({
            sortFieldId: "order_field",
            sortTypeId: "order_type",
            fieldList: fieldList,
            callback: doSearch
        });
    });
    function selectindustry(id) {
        industryId = id;
    }
    function addindustry() {
        var data = {
            "page": page,
            "pageSize": pageSize
        };
        console.log(1);
        console.log(data);
        loadDialog("industry_add.ix?reqType=json", data, "industry_add_form", "Add", "Cancel", "add_industry_dialog", "Add industry", "industry-list");
    }

    function deleteindustry() {
        if (industryId == 0) {
            alertDialog.html("<xms:localization text="Please select a row to delete." />");
            alertDialog.dialog("open");
            return false;
        }
        var data = {
            "page": page,
            "pageSize": pageSize,
            'industryModel.industryId': industryId,
        };
        loadDeleteDialog("industry_delete.ix?reqType=json", data, "Are you sure you want to delete this industry", "delete_industry_dialog", "industry-list", "Delete", "Cancel", "Delete industry");
        industryId = 0;
    }

    function editindustry(industryId, industryName) {
        var data = {
            "page": page,
            "pageSize": pageSize,
            'industryModel.industryId': industryId,
            'industryModel.industryName': industryName
        };
        loadDialog("industry_edit.ix?reqType=json", data, "industry_edit_form", "Edit", "Cancel", "edit_industry_dialog", "Edit industry", "industry-list");
    }

    function doSearch(ps, page) {
        pageSize = typeof (ps) != "undefined" ? ps : pageSize;
        p = typeof (page) != "undefined" ? page : 1;
        var data = "page=" + p;
        data = data + "&pageSize=" + pageSize;
        data = data + "&orderField=" + $("#order_field").val();
        data = data + "&orderType=" + $("#order_type").val();
        loadingDialog.dialog("open");
        $.post("industry_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#industry-list").html(res.content);
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