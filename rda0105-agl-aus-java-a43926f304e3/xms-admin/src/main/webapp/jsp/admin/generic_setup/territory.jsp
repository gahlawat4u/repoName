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
        <li class="hidden"><a href="#"><xms:localization text="Territory"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active">Territory</li>
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
                                <div class="caption">Territory</div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <b>Note :</b> <br/> - This is the Territory List.<br/> -
                                                    Double-click the entry to modify its value.
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
                                                                                  name='pageSize'/></td>
                                                                    <td><xms:localization text="Entries"/>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="territory-list">

                                                    <table class="table table-bordered mg0" id="territory-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th>No</th>
                                                            <th>Territory</th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="territories.records.isEmpty()">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="territories.records">
                                                                <tr data-territoryId="<s:property value="territoryId" />">
                                                                    <td><s:property value="territoryId"/></td>
                                                                    <td territoryId="<s:property value='territoryId' />"
                                                                        territoryName="<s:property value='territoryName'/>"
                                                                        ondblclick="javascript:editTerritory($(this).attr('territoryId'),$(this).attr('territoryName'));">
                                                                        <s:property value="territoryName"/></td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>

                                                        </tbody>
                                                    </table>

                                                    <div class="dataTables_paginate">
                                                        <s:if test="!territories.hasPrev()">
                                                            <a class="paginate_button previous disabled"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a href="javascript:changePage(<s:property value="%{territories.currentPage - 1}"/>)"
                                                               class="paginate_button previous"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:else>
														<span> <s:iterator value="territories.pageRange" status="count">
                                                            <s:if test="%{territories.pageRange[#count.index] == territories.currentPage}">
                                                                <a class="paginate_button current"><s:property
                                                                        value="territories.currentPage"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button"
                                                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                            </s:else>
                                                        </s:iterator>
														</span>
                                                        <s:if test="!territories.hasNext()">
                                                            <a class="paginate_button next" href="#"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button next"
                                                               href="javascript:changePage(<s:property value="%{territories.currentPage+1}"/>)"><xms:localization
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
                                                        onclick="javascript:addTerritory();" type="button">New Territory
                                                </button>
                                                <button class="btn s37" type="button"
                                                        onclick="javascript:deleteTerritory();">Delete Territory
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
<div id="add_territory_dialog"></div>
<div id="edit_territory_dialog"></div>
<div id="delete_territory_dialog"></div>
<!--END CONTENT-->
<script type="text/javascript">
    $(document).ready(function () {
        $('table#territory-list-table tbody tr').click(function () {
            var territoryId = $(this).attr('data-territoryId');
            if (typeof (territoryId) != "undefined" && territoryId != "") {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectTerritory(territoryId);
                $('#btnView').attr('disabled', false);
            }
        });
        var page = 1;
        var pageSize = $("select[name='pageSize'] option:selected").val();
        var territoryId = 0;
        //doSearch(pageSize, page);
    });
    function selectTerritory(id) {
        territoryId = id;
    }
    function addTerritory() {
        loadDialog("territory_add.ix?reqType=json", "", "territory_add_form", "Add", "Cancel", "add_territory_dialog", "Add Territory", "territory-list");
    }

    function deleteTerritory() {
        var data = {
            'territoryModel.territoryId': territoryId,
        };
        loadDeleteDialog("territory_delete.ix?reqType=json", data, "Are you sure you want to delete this territory", "delete_territory_dialog", "territory-list", "Delete", "Cancel", "Delete Territory");
    }

    function editTerritory(territoryId, territoryName) {
        var data = {
            'territoryModel.territoryId': territoryId,
            'territoryModel.territoryName': territoryName
        };
        loadDialog("territory_edit.ix?reqType=json", data, "territory_edit_form", "Edit", "Cancel", "edit_territory_dialog", "Edit Territory", "territory-list");
    }

    function doSearch(pageSize, page) {
        var data = "page=" + page;
        data = data + "&pageSize=" + pageSize;
        loadingDialog.dialog("open");
        $.post("territory_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#territory-list").html(res.content);
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