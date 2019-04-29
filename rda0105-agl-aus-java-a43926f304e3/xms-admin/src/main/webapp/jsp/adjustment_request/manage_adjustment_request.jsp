<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Manage Adjustments Request"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Manage Adjustments Request"/></li>
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
                                    <xms:localization text="Manage Adjustments Request"/>
                                </div>
                                <div class="col-lg-12">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group">
                                            <input type="button" id="export-option" class="btn s37"
                                                   value="<xms:localization text="Option" />"
                                                   onclick="exportClick($('#selected-option').val())"/>
                                            <button type="button" class="btn s37 dropdown-toggle" data-toggle="dropdown"
                                                    aria-expanded="true">
                                                <span class="caret"></span>
                                            </button>
                                            <s:hidden id="selected-option"/>
                                            <ul class="dropdown-menu dropdown-menu-right" role="menu">
                                                <li><a href="#" onclick="exportClick('option')"><xms:localization
                                                        text="Option"/></a></li>
                                                <li><a href="#" onclick="exportClick('html')"><xms:localization
                                                        text="Print"/></a></li>
                                                <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                        text="Export"/></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <!-- BEGIN SEARCH FORM -->
                                        <form id="frmSearchAdjustment">
                                            <div class="row">
                                                <s:hidden name="filter.page" value="1"/>
                                                <s:hidden name="filter.orderBy" value="adjustment_type asc"/>
                                                <div class="col-lg-5">
                                                    <table class="table" style="font-size: 11px;">
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Req. Date Range:"/></td>
                                                            <td class="td2">
                                                                <div class="form-group input-group"
                                                                     style="margin-bottom: 0px;">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span>
                                                                    <s:textfield name="filter.requestDateFrom"
                                                                                 cssClass="form-control form_datetime"
                                                                                 data-date-format="dd MM yyyy"/>
                                                                </div>
                                                            </td>
                                                            <td class="td2">
                                                                <div class="form-group input-group"
                                                                     style="margin-bottom: 0px;">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span>
                                                                    <s:textfield name="filter.requestDateTo"
                                                                                 cssClass="form-control form_datetime"
                                                                                 data-date-format="dd MM yyyy"/>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Response Date:"/></td>
                                                            <td class="td2">
                                                                <div class="form-group input-group"
                                                                     style="margin-bottom: 0px;">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span>
                                                                    <s:textfield name="filter.responseDateFrom"
                                                                                 cssClass="form-control form_datetime"
                                                                                 data-date-format="dd MM yyyy"/>
                                                                </div>
                                                            </td>
                                                            <td class="td2">
                                                                <div class="form-group input-group"
                                                                     style="margin-bottom: 0px;">
																	<span class="input-group-addon s31"> <i
                                                                            class="fa fa-calendar"></i>
																	</span>
                                                                    <s:textfield name="filter.responseDateTo"
                                                                                 cssClass="form-control form_datetime"
                                                                                 data-date-format="dd MM yyyy"/>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Request Type:"/></td>
                                                            <td class="td2" colspan="2"><s:i18n_select
                                                                    name="filter.adjustmentType" list="requestTypes"
                                                                    headerKey="" headerValue="All"
                                                                    cssClass="form-control s52"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Status:"/></td>
                                                            <td class="td2" colspan="2"><s:i18n_select
                                                                    name="filter.status" list="statusList" headerKey=""
                                                                    headerValue="All" cssClass="form-control s52"
                                                                    listKey="id" listValue="name"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Carrier:"/></td>
                                                            <td class="td2" colspan="2"><s:i18n_select
                                                                    name="filter.serviceId" list="carrierList"
                                                                    headerKey="" headerValue="All"
                                                                    cssClass="form-control s52" listKey="serviceId"
                                                                    listValue="serviceName" i18nitem="no"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Airbill #:"/></td>
                                                            <td class="td2"><s:textfield name="filter.airbillNumber"
                                                                                         cssClass="form-control alloptions"
                                                                                         maxlength="25"/></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="col-lg-5">
                                                    <table class="table" style="font-size: 11px;">
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Franchise #:"/></td>
                                                            <td class="td2"><s:i18n_select name="filter.franchiseCode"
                                                                                           list="franchiseList"
                                                                                           headerKey="All"
                                                                                           headerValue="All"
                                                                                           cssClass="form-control s52"
                                                                                           listKey="code"
                                                                                           listValue="code"
                                                                                           i18nitem="no"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Customer #:"/></td>
                                                            <td class="td2"><s:textfield name="filter.customerCode"
                                                                                         cssClass="form-control alloptions"
                                                                                         maxlength="25"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Req. Notes:"/></td>
                                                            <td class="td2"><s:textfield name="filter.note"
                                                                                         cssClass="form-control alloptions"
                                                                                         maxlength="25"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="2" style="border: none; padding: 4px 0;">
                                                                <div class="form-actions pal pdt10">
                                                                    <div class="row">
                                                                        <div class="col-lg-12 ">
                                                                            <button id="btnSearchAdjusment"
                                                                                    class="btn s37" type="button">
                                                                                <xms:localization text="Search"/>
                                                                            </button>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </form>
                                        <!-- END SEARCH FORM -->
                                        <!-- BEGIN ADJUSTMENT LIST -->
                                        <div class="row" id="adjustment_list">
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tr>
                                                        <th class="s42" class="text-center" valign="middle"><span
                                                                style="margin-left: 10px;"><xms:localization
                                                                text="Show"/></span> <s:i18n_select
                                                                name="filter.pageSize" list="pageSizeList"
                                                                cssClass="s52" onchange="changePageSize();"
                                                                cssStyle="height: 22px; padding-top: 1px;"
                                                                i18nitem="no"/> <span><xms:localization
                                                                text="entries"/></span></th>
                                                    </tr>
                                                </table>
                                                <table class="table table-bordered mg0" id="datatable1">
                                                    <thead>
                                                    <tr>
                                                        <th width="4%"></th>
                                                        <th width="8%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Type"/></th>
                                                        <th width="6%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Airbill #"/></th>
                                                        <th width="6%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Customer #"/></th>
                                                        <th width="6%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Request Date"/></th>
                                                        <th width="6%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Response Date"/></th>
                                                        <s:if test="userLevel<3">
                                                            <th width="7%" class="text-center"
                                                                style="vertical-align: middle !important;">
                                                                <xms:localization
                                                                        text="Carrier Amt.Requested Credited"/></th>
                                                        </s:if>
                                                        <th width="7%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Franchise Amt.Requested Credited"/></th>
                                                        <th width="7%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Customer Amt.Requested Credited"/></th>
                                                        <th width="5%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Status"/></th>
                                                        <th width="8%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Reason for deleting Credit Note request"/></th>
                                                        <th width="8%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Intial request information"/></th>
                                                        <th width="8%" class="text-center"
                                                            style="vertical-align: middle !important;"><xms:localization
                                                                text="Franchise comments to FSC"/></th>
                                                        <s:if test="userLevel<3">
                                                            <th width="8%" class="text-center"
                                                                style="vertical-align: middle !important;">
                                                                <xms:localization text="FSC Credit notes"/></th>
                                                        </s:if>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <tr>
                                                        <td colspan="<s:if test="userLevel<3">14</s:if><s:else>12</s:else>">
                                                            <xms:localization text="No data available..."/></td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="<s:if test="userLevel<3">14</s:if><s:else>12</s:else>">
                                                            <span class="b4"><b><xms:localization
                                                                    text="Total Adjustments:"/></b> 0</span> <span
                                                                class="b4"><b>| <xms:localization
                                                                text="Franchise Request Total:"/>
                                                        </b> 0</span> <span class="b4"><b>| <xms:localization
                                                                text="Customer Request Total:"/>
                                                        </b> 0</span> <span class="b4"><b>| <xms:localization
                                                                text="Franchise Credit Total:"/>
                                                        </b> 0</span> <span class="b4"><b>| <xms:localization
                                                                text="Customer Credit Total:"/>
                                                        </b> 0</span></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                            <div class="col-lg-12">
                                                <div class="dataTables_paginate"></div>
                                            </div>
                                        </div>
                                        <!-- END OF ADJUSTMENT LIST -->
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
<div id="show-adjustment" title='<xms:localization text="Adjustments"/>'></div>
<!-- BEGIN DIALOG BOX TO NOTES -->
<div id="delete-adjustment" title='<xms:localization text="Delete Adjustment"/>'></div>
<div id="franchise-comment" title='<xms:localization text="Franchise comments to FSC"/>'></div>
<div id="make-fsc-credit-note" title='<xms:localization text="FSC Credit notes"/>'>
    <input type="hidden" name="adjustmentId" value=""/>

    <div class="form-group">
        <textarea name="note" class="form-control alloptions" maxlength="" style="height: 60px;"
                  placeholder=""></textarea>
    </div>
</div>
<!-- END DIALOG BOX TO NOTES -->
<!--END CONTENT-->
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script>
    // Initial dialog boxes
    $("#show-adjustment").dialog({
        autoOpen: false,
        width: 900,
        modal: true,
        buttons: [{
            text: "<xms:localization text="Save" />",
            click: function () {
                if (validInput()) {
                    updateAdjustment($(this));
                }
            }
        }, {
            text: "<xms:localization text="Cancel" />",
            click: function () {
                $(this).dialog("close");
            }
        }]
    });

    $("#delete-adjustment").dialog({
        autoOpen: false,
        width: 500,
        modal: true,
        buttons: [{
            text: "<xms:localization text="Ok" />",
            click: function () {
                makeAdjNote(1, $(this));
            }
        }, {
            text: "<xms:localization text="Cancel" />",
            click: function () {
                $(this).dialog("close");
            }
        }]
    });

    $("#franchise-comment").dialog({
        autoOpen: false,
        width: 500,
        modal: true,
        buttons: [{
            text: "<xms:localization text="Ok" />",
            click: function () {
                makeAdjNote(2, $(this));
            }
        }, {
            text: "<xms:localization text="Cancel" />",
            click: function () {
                $(this).dialog("close");
            }
        }]
    });

    $("#make-fsc-credit-note").dialog({
        autoOpen: false,
        width: 500,
        modal: true,
        buttons: [{
            text: "<xms:localization text="Ok" />",
            click: function () {
                makeAdjNote(3, $(this));
            }
        }, {
            text: "<xms:localization text="Cancel" />",
            click: function () {
                $(this).dialog("close");
            }
        }]
    });

    // End of initial dialog boxes

    $("#btnSearchAdjusment").click(function () {
        $("#filter_page").val(1);
        doSearch();
    });

    function updateAdjustment(obj) {
        var data = $("#frmEditAdjustment").serialize();
        $.post("manage_adjustment_request_update_adjustment.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#filter_page").val($("#current_page").val());
                doSearch();
                messageDialog.html("<xms:localization text="Save successfull" />");
                messageDialog.dialog("open");
                $(obj).dialog("close");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function changePageSize() {
        $("#filter_page").val(1);
        doSearch();
    }

    function changePage(page) {
        $("#filter_page").val(page);
        doSearch();
    }

    function doSearch() {
        var data = $("#frmSearchAdjustment").serialize();
        var pageSize = $("select#filter_pageSize option:selected").val();
        data = data + "&filter.pageSize=" + pageSize;
        loadingDialog.dialog("open");
        $.post("manage_adjustment_request_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#adjustment_list").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function showNoteAdjDlg(id, type) {
        var dlgId;
        switch (type) {
            case 1:
                dlgId = "#delete-adjustment";
                break;
            case 2:
                dlgId = "#franchise-comment";
                break;
            case 3:
                dlgId = "#make-fsc-credit-note";
                break;
        }
        var data = "adjustmentRequestId=" + id;
        data = data + "&noteType=" + type;

        loadingDialog.dialog("open");
        $.post("manage_adjustment_request_load_adjustment_note.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $(dlgId).html(res.content);
                $(dlgId).dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function makeAdjNote(type, obj) {
        var formId;
        CKupdate();
        switch (type) {
            case 1:
                formId = "#frmDeleteAdjustment";
                break;
            case 2:
                formId = "#frmFranchiseComment";
                break;
            case 3:
                formId = "#frmCreditNotes";
                break;
        }
        var data = $(formId).serialize();
        $.post("manage_adjustment_request_make_adjustment_note.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $(obj).dialog("close");
                $("#filter_page").val($("#current_page").val());
                doSearch();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function editAdj(id) {
        var data = "adjustmentId=" + id;
        loadingDialog.dialog("open");
        $.post("manage_adjustment_request_show_adjustment.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#show-adjustment").html(res.content);
                $("#show-adjustment").dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function franchiseCommentAdj(id) {
        $("#franchise-comment input[name='adjustmentId']").val(id);
        $("#franchise-comment").dialog("open");
    }

    function makeFscCreditNoteAdj(id) {
        $("#make-fsc-credit-note input[name='adjustmentId']").val(id);
        $("#make-fsc-credit-note").dialog("open");
    }

    function updateSubStatus(id) {
        var data = "adjustmentRequestId=" + id;
        loadingDialog.dialog("open");
        $.post("update_adjustment_request_sub_status.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#filter_page").val($("#current_page").val());
                doSearch();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function resubmit(id) {
        var data = "adjustmentId=" + id;
        loadingDialog.dialog("open");
        $.post("resubmit_adjustment.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#filter_page").val($("#current_page").val());
                doSearch();
                messageDialog.html("Updated successfull.");
                messageDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error.<br/>Please contact administrator for supporting." />");
            alertDialog.dialog("open");
        });
    }

    function exportClick(option) {
        loadingDialog.dialog("open");
        var creditNoteCode = $("#selected-credit-note").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");

                break;
            case 'html':
                $("#export-option").val("<xms:localization text="Print" />");
                var data = $("#frmSearchAdjustment").serialize();
                $.post("manage_adjustment_request_do_print.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', '', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1200,height=500');
                    win.document.write("");
                    win.document.write(res);
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                var data = $("#frmSearchAdjustment").serialize();
                var pageSize = $("select#filter_pageSize option:selected").val();
                data = data + "&filter.pageSize=" + pageSize;
                $.fileDownload("manage_adjustment_request_do_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    }
                });
                break;
        }

    }

    function CKupdate() {
        for (instance in CKEDITOR.instances)
            CKEDITOR.instances[instance].updateElement();
    }


</script>