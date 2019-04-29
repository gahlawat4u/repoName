<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<script type="text/javascript">
    var hasRecords = false;
</script>
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li><xms:localization text="Reports"/> &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li><xms:localization text="Warranty"/> &nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="AGL Warranty Invoice History Report"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
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
                                    <xms:localization text="Invoice History Report"/>
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
                                                        text="View Printable Copy"/></a></li>
                                                <li><a href="#" onclick="exportClick('pdf')"><xms:localization
                                                        text="View PDF Copy"/></a></li>
                                                <li><a href="#" onclick="exportClick('xls')"><xms:localization
                                                        text="Export"/></a></li>
                                            </ul>
                                        </div>
                                    </div>
                                    <div class="form-group flr mgb">
                                        <form id="invoiced_airbill_form">
                                            <s:hidden id="rpt_txn_id" name="rptTxnId"/>
                                            <table class="s36">
                                                <tbody>
                                                <tr>
                                                    <td><xms:localization text="Filter by"/></td>
                                                    <td><select name="searchType" class="form-control">
                                                        <option value="1"><xms:localization text="Ship date"/></option>
                                                        <option value="2"><xms:localization
                                                                text="Import date"/></option>
                                                        <option value="3"><xms:localization
                                                                text="Invoice date"/></option>
                                                    </select></td>
                                                    <td>
                                                        <div class="form-group input-group mg0">
                                                            <span class="input-group-addon s31"><i
                                                                    class="fa fa-calendar"></i></span> <input
                                                                name="startDate" class="form-control form_datetime"
                                                                type="text" data-date-format="dd MM yyyy"
                                                                placeholder='<xms:localization text="Start"/>'>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="form-group input-group mg0">
                                                            <span class="input-group-addon s31"><i
                                                                    class="fa fa-calendar"></i></span> <input
                                                                name="endDate" class="form-control form_datetime"
                                                                type="text" data-date-format="dd MM yyyy"
                                                                placeholder='<xms:localization text="End"/>'>
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
                                            <input type="hidden" id="orderField" name="orderField"
                                                   value="invoice_code"/> <input type="hidden" id="orderType"
                                                                                 name="orderType" value="0"/> <input
                                                type="hidden" id="page" name="page" value="1"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select id="pageSize" name="pageSize"
                                                                                  cssClass="form-control"
                                                                                  cssStyle="height: 22px; padding-top: 1px;"
                                                                                  list="pageSizes"
                                                                                  onchange="doSearch()"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="invoiced_airbill_report">
                                                    <div class="s70">
                                                        <table class="table table-bordered mg0"
                                                               id="invoiced_airbill_report_table">
                                                            <thead>
                                                            <tr>
                                                                <th><xms:localization text="Invoice #"/></th>
                                                                <th><xms:localization text="Customer #"/></th>
                                                                <th><xms:localization text="Airbill #"/></th>
                                                                <th><xms:localization text="Carrier"/></th>
                                                                <th><xms:localization text="Service Type"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Warranty Revenue (Exc. GST)"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="GST Revenue on Warranty"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="Total Warranty Revenue (inc GST)"/></th>
                                                                <th><xms:localization text="Ship Date"/></th>
                                                                <th><xms:localization text="Import Date"/></th>
                                                                <th><xms:localization text="Invoice Date"/></th>
                                                                <th class="text-right" data-group="pieces">
                                                                    <xms:localization text="Pieces"/></th>
                                                                <th class="text-right" data-group="dead-weight">
                                                                    <xms:localization text="Dead Weight"/></th>
                                                                <th class="text-right" data-group="dimension">
                                                                    <xms:localization text="Dimension"/></th>
                                                                <th class="text-right" data-group="quoted">
                                                                    <xms:localization text="Quoted"/></th>
                                                                <th><xms:localization text="Charges"/></th>
                                                                <th class="text-right"><xms:localization
                                                                        text="total"/></th>
                                                                <th data-group="sender"><xms:localization
                                                                        text="Sender Address"/></th>
                                                                <th data-group="receiver"><xms:localization
                                                                        text="Receiver Address"/></th>
                                                            </tr>
                                                            </thead>
                                                            <tbody>
                                                            <s:if test="report==null || report.totalRecords==0">
                                                                <tr>
                                                                    <td colspan="19"><xms:localization
                                                                            text="No data available..."/></td>
                                                                </tr>
                                                            </s:if>
                                                            <s:else>
                                                                <s:iterator value="report.records">
                                                                    <tr>
                                                                        <td><s:property value="invoiceCode"/></td>
                                                                        <td><s:property value="customerCode"/></td>
                                                                        <td><s:property value="airbillNumber"/></td>
                                                                        <td><s:property value="carrier"/></td>
                                                                        <td><s:property value="service"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="insuredAmount"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="insuredGst"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="insuredTotal"/></td>
                                                                        <td><s:property value="shipDate"/></td>
                                                                        <td><s:property value="importDate"/></td>
                                                                        <td><s:property value="invoiceDate"/></td>
                                                                        <td class="text-right" data-group="pieces">
                                                                            <s:property value="noOfPieces"/></td>
                                                                        <td class="text-right" data-group="dead-weight">
                                                                            <s:property value="deadWeight"/><s:property
                                                                                value="weightUnit"/></td>
                                                                        <td class="text-right" data-group="dimension">
                                                                            <s:property value="dimension"/><s:property
                                                                                value="dimensionUnit"/></td>
                                                                        <td class="text-right" data-group="quoted">
                                                                            <s:property
                                                                                    value="currencySymbol"/><s:property
                                                                                value="quoted"/></td>
                                                                        <td><s:property value="charges"
                                                                                        escape="false"/></td>
                                                                        <td class="text-right"><s:property
                                                                                value="currencySymbol"/><s:property
                                                                                value="Total"/></td>
                                                                        <td data-group="sender"><s:property
                                                                                value="senderAddress1"/></td>
                                                                        <td data-group="receiver"><s:property
                                                                                value="receiverAddress1"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                <tr>
                                                                    <th colspan="19"><xms:localization text="Showing"/>
                                                                        <s:property value="report.startRecord"/>
                                                                        <xms:localization text="to"/> <s:property
                                                                                value="report.endRecord"/>
                                                                        <xms:localization text="of"/> <s:property
                                                                                value="report.totalRecords"/></th>
                                                                </tr>
                                                            </s:else>
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                    <div style="float: right; clear: both; margin-top:5px;">
                                                        <s:if test="summary!=null">
                                                            <table class="table table-bordered mg0"
                                                                   id="summary_invoiced_airbill_report_table">
                                                                <thead>
                                                                <tr>
                                                                    <th class="text-right"><xms:localization
                                                                            text="International Shipment Count"/></th>
                                                                    <th class="text-right"><xms:localization
                                                                            text="Domestic Shipment Count"/></th>
                                                                    <th class="text-right"><xms:localization
                                                                            text="International Total Revenue"/></th>
                                                                    <th class="text-right"><xms:localization
                                                                            text="Domestic Total Revenue"/></th>
                                                                    <th class="text-right"><xms:localization
                                                                            text="GST on Domestic"/></th>
                                                                    <th class="text-right"><xms:localization
                                                                            text="Total Revenue inc GST"/></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <tr>
                                                                    <td class="text-right"><s:property
                                                                            value="summary.intlShipmentCount"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="summary.domShipmentCount"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="currencySymbol"/><s:property
                                                                            value="summary.intlTotalRevenue"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="currencySymbol"/><s:property
                                                                            value="summary.domTotalRevenue"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="currencySymbol"/><s:property
                                                                            value="summary.domGst"/></td>
                                                                    <td class="text-right"><s:property
                                                                            value="currencySymbol"/><s:property
                                                                            value="summary.totalRevenueIncGst"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </s:if>
                                                    </div>
                                                    <div class="dataTables_paginate">
                                                        <s:if test="report!=null">
                                                            <s:if test="report.hasPrev()">
                                                                <a href="javascript:changePage(<s:property value="%{report.currentPage - 1}"/>)"
                                                                   class="paginate_button previous"><xms:localization
                                                                        text="Previous"/></a>
                                                            </s:if>
															<span> <s:iterator value="report.pageRange" status="count">
                                                                <s:if test="%{report.pageRange[#count.index] == report.currentPage}">
                                                                    <a class="paginate_button current"><s:property
                                                                            value="report.currentPage"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button"
                                                                       href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                </s:else>
                                                            </s:iterator>
															</span>
                                                            <s:if test="report.hasNext()">
                                                                <a class="paginate_button next"
                                                                   href="javascript:changePage(<s:property value="%{report.currentPage+1}"/>)"><xms:localization
                                                                        text="Next"/></a>
                                                            </s:if>
                                                        </s:if>
                                                    </div>
                                                </div>
                                                <form id="column-flags" style="clear:both;">
                                                    <table class="s36">
                                                        <tbody>
                                                        <tr>
                                                            <td><b><xms:localization text="Columns"/></b></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="pieces"
                                                                       checked="checked"/> <s:hidden data-group="pieces"
                                                                                                     name="columnFlags.hasPieces"
                                                                                                     value="true"/></td>
                                                            <td><xms:localization text="Pieces"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="dead-weight"
                                                                       checked="checked"/> <s:hidden
                                                                    data-group="dead-weight"
                                                                    name="columnFlags.hasDeadWeight" value="true"/></td>
                                                            <td><xms:localization text="Dead Weight"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="dimension"
                                                                       checked="checked"/> <s:hidden
                                                                    data-group="dimension"
                                                                    name="columnFlags.hasDimension" value="true"/></td>
                                                            <td><xms:localization text="Dimension"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="quoted"
                                                                       checked="checked"/> <s:hidden data-group="quoted"
                                                                                                     name="columnFlags.hasQuoted"
                                                                                                     value="true"/></td>
                                                            <td><xms:localization text="Quoted"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="sender"
                                                                       checked="checked"/> <s:hidden data-group="sender"
                                                                                                     name="columnFlags.hasSender"
                                                                                                     value="true"/></td>
                                                            <td><xms:localization text="Sender Address"/></td>
                                                            <td>&nbsp;</td>
                                                            <td><input type="checkbox" data-group="receiver"
                                                                       checked="checked"/> <s:hidden
                                                                    data-group="receiver" name="columnFlags.hasReceiver"
                                                                    value="true"/></td>
                                                            <td><xms:localization text="Receiver Address"/></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                </form>
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
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        showHideColumns();
        assignCheckBoxHandler();
    });
    var dataExport = "";
    function doSearch() {
        var data = $("#invoiced_airbill_form").serialize();
        dataExport = data;
        data += "&pageSize=" + $("#pageSize option:selected").val();
        loadingDialog.dialog("open");
        $.post("invoiced_airbill_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#invoiced_airbill_report").html(res.content);
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
        $("#rpt_txn_id").val("");
        $("#page").val(1);
        $("#orderField").val("invoice_code");
        $("#orderType").val(0);
        doSearch();
    }

    function changePage(page) {
        $("#page").val(page);
        doSearch();
    }

    function showHideColumns() {
        $("input[type='checkbox'][data-group]").each(function () {
            var group = $(this).attr("data-group");
            if ($(this).is(":checked")) {
                $("#invoiced_airbill_report_table th[data-group='" + group + "']").show();
                $("#invoiced_airbill_report_table td[data-group='" + group + "']").show();
                $("#column-flags input[type='hidden'][data-group='" + group + "'").val("true");
            } else {
                $("#invoiced_airbill_report_table th[data-group='" + group + "']").hide();
                $("#invoiced_airbill_report_table td[data-group='" + group + "']").hide();
                $("#column-flags input[type='hidden'][data-group='" + group + "'").val("false");
            }
        });
    }

    function assignCheckBoxHandler() {
        $("input[type='checkbox'][data-group]").each(function () {
            $(this).click(function () {
                showHideColumns();
            });
        });
    }
    function exportClick(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = dataExport + "&" + $("#column-flags").serialize() + "&rptTxnId=" + $("#rpt_txn_id").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="View Printable Copy" />");
                $.post("invoiced_airbill_export_html.ix?reqType=json", data, function (res) {
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
            case 'pdf':
                $("#export-option").val("<xms:localization text="View PDF Copy" />");
                loadingDialog.dialog("open");
                $.fileDownload("invoiced_airbill_export_pdf.ix", {
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
            case 'xls':
                $("#export-option").val("<xms:localization text="Export" />");
                loadingDialog.dialog("open");
                $.fileDownload("invoiced_airbill_export_xls.ix", {
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


</script>