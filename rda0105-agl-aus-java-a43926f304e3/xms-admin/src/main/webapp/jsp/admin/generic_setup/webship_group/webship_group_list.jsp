<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li><xms:localization text="Admin"/>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li><xms:localization text="Generic Setup"/>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Web Freight Group"/></li>
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
                                    <xms:localization text="Web Freight Group"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <xms:localization
                                                            text="<b>Note: </b> <br /> - This is the Web Freight group that are available in the qualification data.<br /> - Double-click the entry to modify its value."/>
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
                                                                    <td><s:select list="listPageSize"
                                                                                  cssClass="form-control"
                                                                                  onchange="changePageSize($(this).val())"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="webship-group-list-div">
                                                    <table class="table table-bordered mg0 table-hover table-pointer"
                                                           id="webship-group-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="No"/></th>
                                                            <th><xms:localization text="Web Freight Group Name"/></th>
                                                            <th><xms:localization text="Owner Customer Id"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="webshipGroupList.records.isEmpty()">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="webshipGroupList.records">
                                                                <tr data-webshipGroupId="<s:property value="webshipGroupId" />"
                                                                    ondblclick="editWebshipGroup($(this).attr('data-webshipGroupId'))">
                                                                    <td><s:property value="webshipGroupId"/></td>
                                                                    <td><s:property value="webshipGroupName"/></td>
                                                                    <td><s:property value="ownerCustomerId"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>
                                                        </tbody>
                                                    </table>
                                                    <script type="text/javascript">
                                                        $(document).ready(function () {
                                                            $('table#webship-group-list-table tbody tr').click(function () {
                                                                var webshipGroupId = $(this).attr('data-webshipGroupId');
                                                                if (typeof (webshipGroupId) != "undefined" && webshipGroupId != "") {
                                                                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                    selectWebshipGroup(webshipGroupId);
                                                                    $('#btnRemove').attr('disabled', false);
                                                                }
                                                            });
                                                        });
                                                    </script>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="!webshipGroupList.hasPrev()">
                                                            <a class="paginate_button previous disabled"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a href="javascript:changePage(<s:property value="%{webshipGroupList.currentPage - 1}"/>)"
                                                               class="paginate_button previous"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:else>
														<span> <s:iterator value="webshipGroupList.pageRange"
                                                                           status="count">
                                                            <s:if test="%{webshipGroupList.pageRange[#count.index] == webshipGroupList.currentPage}">
                                                                <a class="paginate_button current"><s:property
                                                                        value="webshipGroupList.currentPage"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button"
                                                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                            </s:else>
                                                        </s:iterator>
														</span>
                                                        <s:if test="!webshipGroupList.hasNext()">
                                                            <a class="paginate_button next" href="#"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button next"
                                                               href="javascript:changePage(<s:property value="%{webshipGroupList.currentPage+1}"/>)"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:else>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions pal pdt10">
                                        <div class="row">
                                            <div class="col-lg-12 text-right">
                                                <button id="add-carriers-link" class="btn s37"
                                                        onclick="javascript:showAddDialog()">
                                                    <xms:localization text="New Web Freight Group"/>
                                                </button>
                                                <button class="btn s37" id="btnRemove" disabled="disabled"
                                                        onclick="javascript:deleteWebshipGroup();">
                                                    <xms:localization text="Delete Web Freight Group"/>
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
<!--END CONTENT-->
<div id="webship-group-list-dialog"></div>

<script type="text/javascript">
    var page = '<s:property value="page" />';
    var pageSize = '<s:property value="pageSize" />';
    var webshipGroupId = 0;
    function changePage(p) {
        page = p;
        var data = $("#search-form").serialize() + "&page=" + page + "&pageSize=" + pageSize;
        doPostDataByParameters("webship_group_list_get_data.ix?reqType=json", data, "", "webship-group-list-div", false);
    }

    function changePageSize(ps) {
        pageSize = ps;
        var data = $("#search-form").serialize() + "&page=" + page + "&pageSize=" + pageSize;
        doPostDataByParameters("webship_group_list_get_data.ix?reqType=json", data, "", "webship-group-list-div", false);
    }

    function editWebshipGroup(id) {
        var data = {
            "webshipGroupId": id,
            "page": page,
            "pageSize": pageSize
        };
        loadDialog("webship_group_edit.ix?reqType=json", data, "webship-group-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "webship-group-list-dialog", "<xms:localization text="Edit Web Freight Group" />", "webship-group-list-div");
    }

    function deleteWebshipGroup() {
        if (webshipGroupId == 0) {
            alertDialog.html("<xms:localization text="Please select a row to delete." />");
            alertDialog.dialog("open");
            return false;
        }
        var data = {
            "page": page,
            "pageSize": pageSize,
            "webshipGroupId": webshipGroupId
        };
        loadDeleteDialog("webship_group_delete.ix?reqType=json", data, "<xms:localization text="Are you sure to delete this Web Freight Group  " />", "webship-group-list-dialog", "webship-group-list-div", "<xms:localization text="Delete" />", "<xms:localization text="Cancel" />",
                "<xms:localization text="Delete Web Freight Group " />");
        $("#btnRemove").attr("disabled", true);
    }

    function selectWebshipGroup(id) {
        webshipGroupId = id;
    }

    function showAddDialog() {
        var data = {
            "page": page,
            "pageSize": pageSize
        };
        loadDialog("webship_group_add.ix?reqType=json", data, "webship-group-form", "<xms:localization text="Save" />", "<xms:localization text="Cancel" />", "webship-group-list-dialog", "<xms:localization text="Add new Web Freight Group " />", "webship-group-list-div");
    }

    function doSearch() {
        var data = $("#search-form").serialize() + "&pageSize=" + pageSize + "&carrierid=" + carrierid;
        doPostDataByParameters("webship_group_list_get_data.ix?reqType=json", data, "", "webship-group-list-div", true);
    }

</script>