<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var hasRecords = false;
</script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Airbill Errors"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Airbill Errors"/></li>
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
                                    <xms:localization text="Airbill Errors"/>
                                </div>
                                <div class="col-lg-8 flr">
                                    <div class="form-group flr mgb">
                                        <div class="btn-group" id="btn-export">
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
                                    <div class="form-group flr mgb">
                                        <form id="search_airbill_errors_form">
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td><xms:localization text="Carrier"/></td>
                                                    <td><s:i18n_select id="carrier" name="carrier"
                                                                       cssClass="form-control" list="services"
                                                                       listKey="serviceId" listValue="serviceName"
                                                                       headerKey="" headerValue="All"
                                                                       i18nitem="no"/></td>
                                                    <td>
                                                        <div class="form-group input-group mg0">
                                                            <span class="input-group-addon s31"><i
                                                                    class="fa fa-calendar"></i></span> <input
                                                                name="fromDate" class="form-control form_datetime"
                                                                type="text" data-date-format="dd MM yyyy"
                                                                placeholder="Start" readonly="readonly">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="form-group input-group mg0">
                                                            <span class="input-group-addon s31"><i
                                                                    class="fa fa-calendar"></i></span> <input
                                                                name="toDate" class="form-control form_datetime"
                                                                type="text" data-date-format="dd MM yyyy"
                                                                placeholder="End" readonly="readonly">
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <button class="btn s37" type="button" onclick="onSearchClick()">
                                                            <xms:localization text="Search"/>
                                                        </button>
                                                    </td>
                                                </tr>
                                                </tbody>
                                            </table>
                                            <s:hidden id="orderField" name="orderField"/>
                                            <s:hidden id="orderType" name="orderType"/>
                                            <s:hidden id="page" name="page"/>
                                        </form>
                                        <s:hidden id="export-data"/>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="portlet-body b12 b11">
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select list="pageSizes" name="pageSize"
                                                                                  cssClass="form-control"
                                                                                  cssStyle="height: 22px; padding-top: 1px;"
                                                                                  id="pageSize"
                                                                                  onchange="doSearch()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="airbill_list_result">
                                                    <div class="s70">
                                                        <table class="table table-bordered mg0" id="airbill_list_table">
                                                            <thead>
                                                            <tr>
                                                                <th>&nbsp;</th>
                                                                <th><xms:localization text="Airbill #"/></th>
                                                                <th><xms:localization text="Invoice Date"/></th>
                                                                <th><xms:localization text="Customer #"/></th>
                                                                <th group="sender"><xms:localization
                                                                        text="Sender"/></th>
                                                                <th group="sender"><xms:localization
                                                                        text="Sender Attn"/></th>
                                                                <th group="sender"><xms:localization
                                                                        text="Sender Address"/></th>
                                                                <th group="sender"><xms:localization
                                                                        text="Sender City"/></th>
                                                                <th group="sender"><xms:localization
                                                                        text="Sender State"/></th>
                                                                <th group="sender"><xms:localization
                                                                        text="Sender Zip"/></th>
                                                                <th><xms:localization text="Reference"/></th>
                                                                <th><xms:localization text="Service"/></th>
                                                                <th group="receiver"><xms:localization
                                                                        text="Receiver"/></th>
                                                                <th group="receiver"><xms:localization
                                                                        text="Receiver Attn"/></th>
                                                                <th group="receiver"><xms:localization
                                                                        text="Receiver Address"/></th>
                                                                <th group="receiver"><xms:localization
                                                                        text="Receiver City"/></th>
                                                                <th group="receiver"><xms:localization
                                                                        text="Receiver State"/></th>
                                                                <th group="receiver"><xms:localization
                                                                        text="Receiver Zip"/></th>
                                                                <th group="account"><xms:localization
                                                                        text="Carrier Cost"/></th>
                                                                <th group="account"><xms:localization
                                                                        text="Sender Account"/></th>
                                                                <th group="account"><xms:localization
                                                                        text="Receiver Account"/></th>
                                                                <th group="account"><xms:localization
                                                                        text="BILL TO Account"/></th>
                                                                <th group="account"><xms:localization
                                                                        text="3P Account"/></th>
                                                                <th><xms:localization text="Ship Date"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="airbills==null || airbills.totalRecords==0">
                                                                <tr>
                                                                    <td colspan="26"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:else>
                                                                <s:iterator value="airbills.records" status="awb">
                                                                    <tr>
                                                                        <td><input
                                                                                name='chkAirbill_<s:property value="%{#awb.index+1}" />'
                                                                                group="airbill-list" type="checkbox"
                                                                                airbill-number='<s:property value="airbillNumber" />'/>
                                                                        </td>
                                                                        <td><s:property value="airbillNumber"/></td>
                                                                        <td><s:property value="invoiceDate"/></td>
                                                                        <td><s:property value="customerCode"/></td>
                                                                        <td group="sender"><s:property
                                                                                value="senderCompanyName"/></td>
                                                                        <td group="sender"><s:property
                                                                                value="senderContactName"/></td>
                                                                        <td group="sender"><s:property
                                                                                value="senderAddress1"/></td>
                                                                        <td group="sender"><s:property
                                                                                value="senderCity"/></td>
                                                                        <td group="sender"><s:property
                                                                                value="senderState"/></td>
                                                                        <td group="sender"><s:property
                                                                                value="senderPostalCode"/></td>
                                                                        <td><s:property value="reference"/></td>
                                                                        <td><s:property value="service"/></td>
                                                                        <td group="receiver"><s:property
                                                                                value="receiverCompanyName"/></td>
                                                                        <td group="receiver"><s:property
                                                                                value="receiverContactName"/></td>
                                                                        <td group="receiver"><s:property
                                                                                value="receiverAddress1"/></td>
                                                                        <td group="receiver"><s:property
                                                                                value="receiverCity"/></td>
                                                                        <td group="receiver"><s:property
                                                                                value="receiverState"/></td>
                                                                        <td group="receiver"><s:property
                                                                                value="receiverPostalCode"/></td>
                                                                        <td group="account" class="text-right">
                                                                            <s:property value="carrierCost"/></td>
                                                                        <td group="account"><s:property
                                                                                value="senderAccount"/></td>
                                                                        <td group="account"><s:property
                                                                                value="receiverAccount"/></td>
                                                                        <td group="account"><s:property
                                                                                value="billingToAccount"/></td>
                                                                        <td group="account"><s:property
                                                                                value="p3Account"/></td>
                                                                        <td><s:property value="shipDate"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                <tr>
                                                                    <th colspan="26"><xms:localization text="Showing"/>
                                                                        <s:property value="airbills.startRecord"/>
                                                                        <xms:localization text="to"/> <s:property
                                                                                value="airbills.endRecord"/>
                                                                        <xms:localization text="of"/> <s:property
                                                                                value="airbills.totalRecords"/></th>
                                                                </tr>
                                                            </s:else>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div class="dataTables_paginate records"
                                                         style="margin-bottom: 15px;">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="airbills.startRecord"/> <xms:localization
                                                                        text="to"/> <s:property
                                                                        value="airbills.endRecord"/> <xms:localization
                                                                        text="of"/> <s:property
                                                                        value="airbills.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="airbills!=null">
                                                                    <s:if test="!airbills.hasPrev()">
                                                                        <a class="paginate_button previous disabled"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a href="javascript:changePage(<s:property value="%{airbills.currentPage - 1}"/>)"
                                                                           class="paginate_button previous"><xms:localization
                                                                                text="Previous"/></a>
                                                                    </s:else>
																	<span> <s:iterator value="airbills.pageRange"
                                                                                       status="count">
                                                                        <s:if test="%{airbills.pageRange[#count.index] == airbills.currentPage}">
                                                                            <a class="paginate_button current"><s:property
                                                                                    value="airbills.currentPage"/></a>
                                                                        </s:if>
                                                                        <s:else>
                                                                            <a class="paginate_button"
                                                                               href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                        </s:else>
                                                                    </s:iterator>
																	</span>
                                                                    <s:if test="!airbills.hasNext()">
                                                                        <a class="paginate_button next disabled"><xms:localization
                                                                                text="Next"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button next"
                                                                           href="javascript:changePage(<s:property value="%{airbills.currentPage+1}"/>)"><xms:localization
                                                                                text="Next"/></a>
                                                                    </s:else>
                                                                </s:if>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions pal pdt10">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="form-group text-left s99">
                                                        <div class="form-group">
                                                            <table class="s36 ">
                                                                <tbody>
                                                                <tr>
                                                                    <td><input type="checkbox" group="show-hide-option"
                                                                               col-group="sender"
                                                                               id="check-show-sender"/></td>
                                                                    <td><xms:localization text="Sender"/></td>
                                                                    <td><input type="checkbox" group="show-hide-option"
                                                                               col-group="receiver"
                                                                               id="check-show-receiver"/></td>
                                                                    <td><xms:localization text="Receiver"/></td>
                                                                    <td><input type="checkbox" group="show-hide-option"
                                                                               col-group="account" id="check-show-acc"/>
                                                                    </td>
                                                                    <td><xms:localization text="Account"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                            <table class="s36 b20">
                                                                <tbody>
                                                                <tr>
                                                                    <td>
                                                                        <button class="btn s37" type="button"
                                                                                onclick="checkAll()">
                                                                            <xms:localization text="Check All"/>
                                                                        </button>
                                                                    </td>
                                                                    <td>
                                                                        <button class="btn s37" type="button"
                                                                                onclick="checkNone()">
                                                                            <xms:localization text="Check None"/>
                                                                        </button>
                                                                    </td>
                                                                    <td>
                                                                        <button id="btnAssignAirbills" class="btn s37"
                                                                                type="button" disabled="disabled"
                                                                                onclick="showAssignAirbillDialog()">
                                                                            <xms:localization text="Assign"/>
                                                                            <span id="total-checked">0</span>
                                                                            <xms:localization text="Checked Airbills"/>
                                                                        </button>
                                                                    </td>
                                                                    <td>
                                                                        <button id="btnMoveInvoice" class="btn s37"
                                                                                type="button" disabled="disabled"
                                                                                onclick="showMoveInvoiceDialog()">
                                                                            <xms:localization text="Move Invoice Date"/>
                                                                        </button>
                                                                    </td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
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
</div>
<div id="assign_airbill_dialog" title='<xms:localization text="Select Customer"/>' style="display: none"></div>
<div id="move-invoice-dialog" title='<xms:localization text="Move Invoice Date"/>' style="display: none">
    <form id="frmMoveInvoice">
        <input name="checkAirbills" type="hidden" id="checkAirbills" autofocus="autofocus"/>
        <table style="width: 250px;">
            <tr>
                <td style="min-width: 90px;"><xms:localization text="Invoice Date:"/></td>
                <td>
                    <div class="form-group input-group mg0">
                        <span class="input-group-addon s31"><i class="fa fa-calendar"></i></span> <input
                            name="invoiceDate" class="form-control form_datetime" type="text"
                            data-date-format="dd MM yyyy"/>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="div_delete_airbill_error"></div>
<div id="div_delete_airbill_error_do"></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        showHideGroup();
        $("input[group='airbill-list'][type='checkbox']").each(function () {
            $(this).click(function () {
                airbillListChange();
            });
        });
    });

    function doSearch() {
        var data = $("#search_airbill_errors_form").serialize();
        $("#export-data").val(data);
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("repair_airbill_errors_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#airbill_list_result").html(res.content);
                airbillListChange();
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

    function onSearchClick() {
        $("#page").val(1);
        $("#orderField").val("airbill_number");
        $("#orderType").val(0);
        doSearch();
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }

    function showHideGroup() {
        $("input[type='checkbox'][group='show-hide-option']").each(function () {
            var colGroup = $(this).attr("col-group");
            if ($(this).is(":checked")) {
                $("#airbill_list_table th[group='" + colGroup + "']").show();
                $("#airbill_list_table td[group='" + colGroup + "']").show();
            } else {
                $("#airbill_list_table th[group='" + colGroup + "']").hide();
                $("#airbill_list_table td[group='" + colGroup + "']").hide();
            }
            $(this).click(function () {
                var col = $(this).attr("col-group");
                if ($(this).is(":checked")) {
                    $("#airbill_list_table th[group='" + col + "']").show();
                    $("#airbill_list_table td[group='" + col + "']").show();
                } else {
                    $("#airbill_list_table th[group='" + col + "']").hide();
                    $("#airbill_list_table td[group='" + col + "']").hide();
                }
            });
        });
    }

    function checkAll() {
        $("input[group='airbill-list'][type='checkbox']").each(function () {
            $(this).prop('checked', true);
        });
        airbillListChange();
    }

    function checkNone() {
        $("input[group='airbill-list'][type='checkbox']").each(function () {
            $(this).prop('checked', false);
        });
        airbillListChange();
    }

    function getAirbillList() {
        var airbillList = "";
        $("input[group='airbill-list'][type='checkbox']").each(function () {
            if ($(this).is(":checked")) {
                airbillList += $(this).attr("airbill-number") + ",";
            }
        });
        airbillList = airbillList.substring(0, airbillList.length - 1);
        return airbillList;
    }

    function airbillListChange() {
        var airbillList = getAirbillList();
        var count = airbillList.split(",").length;
        if (airbillList == "") {
            count = 0;
        }
        $("#total-checked").html(count);
        if (count == 0) {
            $("#btnAssignAirbills").prop('disabled', true);
            $("#btnMoveInvoice").prop('disabled', true);
        } else {
            $("#btnAssignAirbills").prop('disabled', false);
            $("#btnMoveInvoice").prop('disabled', false);
        }
    }

    function moveInvoiceDate() {
        var data = $("#frmMoveInvoice").serialize();
        loadingDialog.dialog("open");
        $.post("repair_airbill_errors_move_inv_date.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#move-invoice-dialog").dialog("close");
                messageDialog.html('<xms:localization text="Move Successfully" />');
                messageDialog.dialog("open");
                doSearch();
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

    function assignAirbills() {
        var data = "customerCode=" + $("#assign_airbill_form #search_customer_code").html().trim();
        data += "&checkAirbills=" + getAirbillList();
        loadingDialog.dialog("open");
        $.post("repair_airbill_errors_assign_airbills.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#assign_airbill_dialog").dialog("close");
                messageDialog.html('<xms:localization text="Assigned Successfully" />');
                messageDialog.dialog("open");
                doSearch();
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

    function showMoveInvoiceDialog() {
        var airbillList = getAirbillList();
        $("#checkAirbills").val(airbillList);
        var buttons = {};
        buttons["<xms:localization text="Move" />"] = function () {
            moveInvoiceDate();
        }
        buttons["<xms:localization text="Cancel" />"] = function () {
            $(this).dialog("close");
        }
        var dialog = $("#move-invoice-dialog").dialog({
            modal: true,
            autoOpen: false,
            width: 'auto',
            height: 'auto',
            maxHeight: '200',
            buttons: buttons,
            show: {
                effect: "fade",
                duration: 300
            }
        });
        dialog.dialog("open");
    }

    function showAssignAirbillDialog() {
        loadingDialog.dialog("open");
        $.post("customer_quick_search.ix?reqType=json", "", function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                var buttons = {};
                buttons["<xms:localization text="Save" />"] = function () {
                    assignAirbills();
                }
                buttons["<xms:localization text="Cancel" />"] = function () {
                    $(this).dialog("close");
                }
                var dialog = $("#assign_airbill_dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: 'auto',
                    height: 'auto',
                    maxHeight: '200',
                    buttons: buttons,
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                dialog.html(res.content);
                dialog.dialog("open");
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

    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val();
        var showSender = "false";
        var showReceiver = "false";
        var showAcc = "false";
        if ($("#check-show-sender").is(":checked")) {
            showSender = "true";
        }
        if ($("#check-show-receiver").is(":checked")) {
            showReceiver = "true";
        }
        if ($("#check-show-acc").is(":checked")) {
            showAcc = "true";
        }
        data += "&showSenderAddress=" + showSender + "&showReceiverAddress=" + showReceiver + "&showAccount=" + showAcc;
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="Print" />");
                $.post("repair_airbill_errors_print.ix?reqType=json", data, function (res) {
                    loadingDialog.dialog("close");
                    if (res.errorCode == "SUCCESS") {
                        var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                        win.document.write(res.content);
                    } else {
                        alertDialog.html(res.errorMsg);
                        alertDialog.dialog("open");
                    }
                });
                break;
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("repair_airbill_errors_export.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                    failCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                });
                break;
        }
    }
    function deleteAirbillError(airbillNumber) {
        var dataLoad = {'airbillNumber': airbillNumber};
        var actionLoad = "delete_airbill_error_show.ix?reqType=json";
        var actionSave = "delete_airbill_error_do.ix?reqType=json";
        loadDialogToSaveCallBack(actionLoad, actionSave, dataLoad, "form_delete_airbill_error", "OK", "Cancel", "div_delete_airbill_error", "", "div_delete_airbill_error_do", onSearchClick);
    }


</script>