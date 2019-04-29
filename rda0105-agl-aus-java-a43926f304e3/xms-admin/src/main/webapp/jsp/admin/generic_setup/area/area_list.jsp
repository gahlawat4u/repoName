<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left"
        style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>">Home</a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Areas"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Areas"/></li>
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
                    <div id="area-chart-spline"
                         style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Areas"/>
                                </div>

                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <b>Note :</b> <br/> - This is the Area List.<br/> -
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
                                                                    <td><s:select list="listPageSize" name='pageSize'
                                                                                  cssClass="form-control"
                                                                                  onchange="javascript:doSearch($(this).val(),1);"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="area-list">

                                                    <table class="table table-bordered mg0"
                                                           id="area-list-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="No"/></th>
                                                            <th><xms:localization text="Areas Name"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <s:if test="areaList.records.isEmpty()">
                                                            <tr>
                                                                <td colspan="6"><xms:localization
                                                                        text="No data available..."/></td>
                                                            </tr>
                                                        </s:if>
                                                        <s:else>
                                                            <s:iterator value="areaList.records">
                                                                <tr data-areaId="<s:property value="areaId" />">
                                                                    <td><s:property value="areaId"/></td>
                                                                    <td areaId="<s:property value='areaId' />"
                                                                        areaName="<s:property value='areaName'/>"
                                                                        ondblclick="javascript:editarea($(this).attr('areaId'),$(this).attr('areaName'));">
                                                                        <s:property value="areaName"/>
                                                                    </td>
                                                                </tr>
                                                            </s:iterator>
                                                        </s:else>

                                                        </tbody>

                                                    </table>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="!areaList.hasPrev()">
                                                            <a class="paginate_button previous disabled"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a
                                                                    href="javascript:changePage(<s:property value="%{areaList.currentPage - 1}"/>)"
                                                                    class="paginate_button previous"><xms:localization
                                                                    text="Previous"/></a>
                                                        </s:else>
														<span> <s:iterator value="areaList.pageRange"
                                                                           status="count">
                                                            <s:if
                                                                    test="%{areaList.pageRange[#count.index] == areaList.currentPage}">
                                                                <a class="paginate_button current"><s:property
                                                                        value="areaList.currentPage"/></a>
                                                            </s:if>
                                                            <s:else>
                                                                <a class="paginate_button"
                                                                   href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                            </s:else>
                                                        </s:iterator>
														</span>
                                                        <s:if test="!areaList.hasNext()">
                                                            <a class="paginate_button next" href="#"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:if>
                                                        <s:else>
                                                            <a class="paginate_button next"
                                                               href="javascript:changePage(<s:property value="%{areaList.currentPage+1}"/>)"><xms:localization
                                                                    text="Next"/></a>
                                                        </s:else>
                                                    </div>
                                                    <script type="text/javascript">
                                                        $(document).ready(function () {
                                                            $('table#area-list-table tbody tr').click(function () {
                                                                var areaId = $(this).attr('data-areaId');
                                                                if (typeof (areaId) != "undefined" && areaId != "") {
                                                                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                    selectarea(areaId);
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
                                                <button id="add-carriers-link" class="btn s37" type="button"
                                                        onclick="javascript:addarea();">
                                                    <xms:localization text="New Areas"/>
                                                </button>
                                                <button class="btn s37" type="button"
                                                        onclick="javascript:deletearea();">
                                                    <xms:localization text="Delete Areas"/>
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
<div id="add_area_dialog"></div>
<div id="edit_area_dialog"></div>
<div id="delete_area_dialog"></div>
<!--END CONTENT-->
<script type="text/javascript">
    var page = 1;
    var pageSize = $("select[name='pageSize'] option:selected").val();
    var areaId = 0;
    $(document).ready(function () {
        //doSearch(pageSize, page);
    });
    function selectarea(id) {
        areaId = id;
    }
    function addarea() {
        var data = {
            "page": page,
            "pageSize": pageSize,

        };
        loadDialog("area_add.ix?reqType=json", data, "area_add_form", "Add", "Cancel", "add_area_dialog", "Add area", "area-list");
    }

    function deletearea() {
        if (areaId == 0) {
            alertDialog.html("<xms:localization text="Please select a row to delete." />");
            alertDialog.dialog("open");
            return false;
        }
        var data = {
            "page": page,
            'pageSize': pageSize,
            'areaModel.areaId': areaId,
        };
        loadDeleteDialog("area_delete.ix?reqType=json", data, "Are you sure you want to delete this area", "delete_area_dialog", "area-list", "Delete", "Cancel", "Delete area");
        areaId = 0;
    }

    function editarea(areaId, areaName) {
        var data = {
            'areaModel.areaId': areaId,
            'areaModel.areaName': areaName,
            "page": page,
            "page": page,
            "pageSize": pageSize
        };
        loadDialog("area_edit.ix?reqType=json", data, "area_edit_form", "Edit", "Cancel", "edit_area_dialog", "Edit area", "area-list");
    }

    function doSearch(ps, page) {
        pageSize = ps;
        var data = "page=" + page;
        data = data + "&pageSize=" + pageSize;
        loadingDialog.dialog("open");
        $.post("area_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#area-list").html(res.content);
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