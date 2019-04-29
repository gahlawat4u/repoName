<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Manage Cover Sheets"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Manage Cover Sheets"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<script type="text/javascript">
    var cPage = 1;
    var cPageSize = 5;
    var scPage = 1;
    var scPageSize = 5;
    var cSheetId = 0;
</script>
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
                                    <xms:localization text="Manage Cover Sheets"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <p>
                                                    <xms:localization
                                                            text="<b>Note :</b><br /> - This tool allows you to import JPEG,JPG,GIF,PNG,BMP file and set them as cover sheets for carrier rate sheets."/>
                                                </p>

                                                <div class="col-lg-12 ">
                                                    <div class="form-group text-center">
														<span class="b13 "> <xms:localization
                                                                text="Selected file:"/>(<span
                                                                id="selected-file"><xms:localization
                                                                text="nothing selected"/></span>) <xms:localization
                                                                text="Check to"/> <span id="rate-sheets-count">0</span> <xms:localization
                                                                text="carrier rate sheets."/>
														</span>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-lg-5 ">
                                                        <div class="form-group text-right mgb">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><s:form method="post"
                                                                                enctype="multipart/form-data">
                                                                        <s:file name="fileUpload" cssClass="w10"
                                                                                id="cover-upload" accept="image/*"/>
                                                                    </s:form>
                                                                        <div class="progress progress-striped active"
                                                                             style="display: none">
                                                                            <div class="progress-bar progress-bar-success"
                                                                                 role="progressbar" aria-valuenow="0"
                                                                                 aria-valuemin="0" aria-valuemax="100"
                                                                                 style="width: 0%;"></div>
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <button class="btn s37" type="button"
                                                                                id="btn-delete-cover"
                                                                                disabled="disabled"
                                                                                onclick="deleteCoverSheet(afterDeleteCoverSheet)">
                                                                            <xms:localization text="Delete Attachment"/>
                                                                        </button>
                                                                    </td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </div>
                                                        <table class="table mg0">
                                                            <tr>
                                                                <th class="s42">
                                                                    <table class="s36">
                                                                        <tbody>
                                                                        <tr>
                                                                            <td><xms:localization text="Show"/></td>
                                                                            <td><s:select list="listPageSize"
                                                                                          cssClass="form-control"
                                                                                          cssStyle="height: 22px; padding-top: 1px; width: 55px;"
                                                                                          onchange="changeCPageSize($(this).val())"/></td>
                                                                            <td><xms:localization text="Entries"/></td>
                                                                        </tr>
                                                                        </tbody>
                                                                    </table>
                                                                </th>
                                                            </tr>
                                                        </table>
                                                        <div id="cover-sheets-table-div">
                                                            <table class="table table-bordered mg0 table-hover table-pointer"
                                                                   id="cover-sheets-table">
                                                                <thead>
                                                                <tr>
                                                                    <th><xms:localization text="File Name"/></th>
                                                                    <th><xms:localization text="Upload Date"/></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <s:if test="cvSheets.records.isEmpty()">
                                                                    <tr>
                                                                        <td colspan="2"><xms:localization
                                                                                text="No data available..."/></td>
                                                                    </tr>
                                                                </s:if>
                                                                <s:else>
                                                                    <s:iterator value="cvSheets.records">
                                                                        <tr data-cover-sheet-id="<s:property value="coverSheetId"/>"
                                                                            data-file-name="<s:property value="fileName" />">
                                                                            <td><s:property value="fileName"/></td>
                                                                            <td><s:property value="createDate"/></td>
                                                                        </tr>
                                                                    </s:iterator>
                                                                </s:else>
                                                                </tbody>
                                                            </table>
                                                            <script type="text/javascript">
                                                                $(document).ready(function () {
                                                                    $('table#cover-sheets-table tbody tr').click(function () {
                                                                        var coverSheetId = $(this).attr('data-cover-sheet-id');
                                                                        var fileName = $(this).attr('data-file-name');
                                                                        if (typeof (coverSheetId) != "undefined" && coverSheetId != "") {
                                                                            $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                                            selectCSheet(coverSheetId, fileName);
                                                                        }
                                                                    });
                                                                });
                                                            </script>
                                                            <div class="row">
                                                                <div class="col-lg-12">
                                                                    <div class="dataTables_paginate">
                                                                        <s:if test="!cvSheets.hasPrev()">
                                                                            <button class="paginate_button previous disabled"
                                                                                    disabled="disabled">
                                                                                <xms:localization text="Previous"/>
                                                                            </button>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a href="javascript:changeCPage(<s:property value="%{cvSheets.currentPage - 1}"/>)"
                                                                               class="paginate_button previous"><xms:localization
                                                                                    text="Previous"/></a>
                                                                        </s:else>
																		<span> <s:iterator value="cvSheets.pageRange"
                                                                                           status="count">
                                                                            <s:if test="%{cvSheets.pageRange[#count.index] == cvSheets.currentPage}">
                                                                                <a class="paginate_button current"><s:property
                                                                                        value="cvSheets.currentPage"/></a>
                                                                            </s:if>
                                                                            <s:else>
                                                                                <a class="paginate_button"
                                                                                   href="javascript:changeCPage(<s:property/>);"><s:property/></a>
                                                                            </s:else>
                                                                        </s:iterator>
																		</span>
                                                                        <s:if test="!cvSheets.hasNext()">
                                                                            <button class="paginate_button next"
                                                                                    disabled="disabled">
                                                                                <xms:localization text="Next"/>
                                                                            </button>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button next"
                                                                               href="javascript:changeCPage(<s:property value="%{cvSheets.currentPage+1}"/>)"><xms:localization
                                                                                    text="Next"/></a>
                                                                        </s:else>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col-lg-7 ">
                                                        <s:form id="service-cover-sheets-form">
                                                            <div class="form-group text-right w11">
                                                                <table class="s36">
                                                                    <tbody>
                                                                    <tr>
                                                                        <td><xms:localization
                                                                                text="Check the carrier rate sheets to attach to:"/></td>
                                                                        <td><label class="radio-inline cl666"> <input
                                                                                type="radio" name="coverType"
                                                                                value="cover" checked="checked"/> &nbsp;
                                                                            <xms:localization text="Cover Sheet"/>
                                                                        </label> <label class="radio-inline cl666">
                                                                            <input type="radio" name="coverType"
                                                                                   value="inbound_cover"/> &nbsp;
                                                                            <xms:localization
                                                                                    text="Inbound Cover Sheet"/>
                                                                        </label></td>
                                                                        <td>
                                                                            <button class="btn s37" type="button"
                                                                                    id="btn-attach" disabled="disabled"
                                                                                    onclick="attachCoverSheet()">
                                                                                <xms:localization text="Attach"/>
                                                                            </button>
                                                                        </td>
                                                                        <td>
                                                                            <button class="btn s37" type="button"
                                                                                    id="btn-remove" disabled="disabled"
                                                                                    onclick="removeAttach(disabledButtons)">
                                                                                <xms:localization
                                                                                        text="Remove Attachment"/>
                                                                            </button>
                                                                        </td>
                                                                    </tr>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <table class="table mg0">
                                                                <tr>
                                                                    <th class="s42"></th>
                                                                </tr>
                                                            </table>
                                                            <div id="service-cover-sheet-div">
                                                                <table class="table table-bordered mg0 table-hover"
                                                                       id="service-cover-sheets-table">
                                                                    <thead>
                                                                    <tr>
                                                                        <th width="22"><input type="checkbox"
                                                                                              onclick="selectAll($(this))">
                                                                        </th>
                                                                        <th><xms:localization text="Carrier Name"/></th>
                                                                        <th><xms:localization text="Cover Sheet"/></th>
                                                                        <th><xms:localization
                                                                                text="Inbound Cover Sheet"/></th>
                                                                    </tr>
                                                                    </thead>
                                                                    <tbody>
                                                                    <s:if test="scvSheets.isEmpty()">
                                                                        <tr>
                                                                            <td colspan="4"><xms:localization
                                                                                    text="No data available..."/></td>
                                                                        </tr>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <s:iterator value="scvSheets">
                                                                            <tr>
                                                                                <td><input name="serviceId"
                                                                                           type="checkbox"
                                                                                           value="<s:property value="serviceId"/>"
                                                                                           onclick="countService()">
                                                                                </td>
                                                                                <td><s:property
                                                                                        value="serviceName"/></td>
                                                                                <td><s:property value="fileName"/></td>
                                                                                <td><s:property
                                                                                        value="inboundFileName"/></td>
                                                                            </tr>
                                                                        </s:iterator>
                                                                    </s:else>
                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </s:form>
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
<div id="cover-delete-dialog"></div>
<script type="text/javascript">
    var serviceCount = 0;
    $(document).ready(function () {
        $('#cover-upload').fileupload({
            url: "manage_cover_sheets_upload.ix?reqType=json",
            done: function (e, data) {
                $('.progress-bar').css('width', '0%');
                $('.progress').hide();
                var result = data.result;
                if (result.errorCode == "SUCCESS") {
                    $('#cover-sheets-table-div').html(result.content);
                    messageDialog.html("<xms:localization text="Upload successfully." />");
                    messageDialog.dialog("open");
                } else {
                    alertDialog.html(result.errorMsg);
                    alertDialog.dialog("open");
                }
            },
            submit: function (e, data) {
                $('.progress').show();
            },
            progressall: function (e, data) {
                var progress = parseInt(data.loaded / data.total * 100, 10);
                $('.progress-bar').css(
                        'width',
                        progress + '%'
                );
            }
        });
    });
    function changeCPage(p) {
        cPage = p;
        var data = {
            "cvPage": cPage,
            "cvPageSize": cPageSize,
        }
        $('#cover-upload').fileupload({
            formData: {cvPage: cPage, cvPageSize: cPageSize}
        })
        doPostDataByParameters("manage_cover_sheets_get_c_sheet_data.ix?reqType=json", data, "", "cover-sheets-table-div", false);
    }
    function changeCPageSize(ps) {
        cPageSize = ps;
        var data = {
            "cvPage": cPage,
            "cvPageSize": cPageSize,
        }
        $('#cover-upload').fileupload({
            formData: {cvPage: cPage, cvPageSize: cPageSize}
        })
        doPostDataByParameters("manage_cover_sheets_get_c_sheet_data.ix?reqType=json", data, "", "cover-sheets-table-div", false);
    }
    function selectCSheet(id, name) {
        cSheetId = id;
        $("#selected-file").html(name);
        if (typeof (cSheetId) != "undefined" && cSheetId != 0) {
            $("#btn-delete-cover").attr("disabled", false);
        } else {
            $("#btn-delete-cover").attr("disabled", true);
        }
        disabledButtons();
    }
    function countService() {
        serviceCount = $("#service-cover-sheets-table input[type='checkbox']:checked").length;
        $("#rate-sheets-count").html(serviceCount);
        disabledButtons();
    }
    function selectAll($this) {
        if ($this.attr("checked") == "checked") {
            $("#service-cover-sheets-table input[type='checkbox']").attr("checked", true);
        } else {
            $("#service-cover-sheets-table input[type='checkbox']").attr("checked", false);
        }
        disabledButtons();
    }
    function attachCoverSheet() {
        var data = $("#service-cover-sheets-form").serialize() + "&coverSheetId=" + cSheetId;
        doPostDataByParametersWithCallBack("manage_cover_sheets_attach.ix?reqType=json", data, "<xms:localization text="Attach Successfully" />", "service-cover-sheet-div", true, true, countService());
    }
    function removeAttach(callBack) {
        var data = $("#service-cover-sheets-form").serialize();
        loadDeleteDialogWithCallBack("manage_cover_sheets_remove_attach.ix?reqType=json", data, "<xms:localization text="Are you sure to remove service's cover sheets?" />", "cover-delete-dialog", "service-cover-sheet-div", "<xms:localization text="Remove" />", "<xms:localization text="Cancel" />", "<xms:localization text="Remove Cover Sheet" />", callBack);
    }

    function afterDeleteCoverSheet() {
        cSheetId = 0;
        selectCSheet("0", "<xms:localization text="nothing selected" />");
        disabledButtons();
    }

    function deleteCoverSheet(callBack) {
        var data = {
            "coverSheetId": cSheetId,
            "cvPage": cPage,
            "cvPageSize": cPageSize
        }
        loadDeleteDialogWithCallBack("manage_cover_sheets_delete_cover.ix?reqType=json", data, "<xms:localization text="Are you sure to delete this cover sheet?" />", "cover-delete-dialog", "cover-sheets-table-div", "<xms:localization text="Delete" />", "<xms:localization text="Cancel" />", "<xms:localization text="Delete Cover Sheet" />", afterDeleteCoverSheet);
    }

    function disabledButtons() {
        if (serviceCount == 0 && cSheetId == 0) {
            $("#btn-attach").attr("disabled", true);
            $("#btn-remove").attr("disabled", true);
        }
        if (serviceCount != 0 && cSheetId == 0) {
            $("#btn-remove").attr("disabled", false);
            $("#btn-attach").attr("disabled", true);
        }

        if (serviceCount == 0 && cSheetId != 0) {
            $("#btn-remove").attr("disabled", true);
            $("#btn-attach").attr("disabled", true);
        }

        if (serviceCount != 0 && cSheetId != 0) {
            $("#btn-attach").attr("disabled", false);
            $("#btn-remove").attr("disabled", false);
        }
    }
</script>
<!--END CONTENT-->
