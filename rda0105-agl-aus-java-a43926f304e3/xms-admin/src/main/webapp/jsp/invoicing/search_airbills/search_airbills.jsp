<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script type="text/javascript">
    var hasRecords = false;
</script>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Search Airbills"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Search Airbills"/></li>
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
                                    <xms:localization text="Search Airbills"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="portlet-body b12 b11" id="div_result_search_airbill">
                                                <s:form id="form_search_airbill">
                                                    <div class="form-group  ">
                                                        <s:hidden name="searchAirbillFilter.page" value="1"
                                                                  id="hid_page"/>
                                                        <s:hidden name="searchAirbillFilter.pageSize" value="25"
                                                                  id="hid_record_size"/>
                                                        <s:hidden id="search_airbill_order_field"
                                                                  name="searchAirbillFilter.orderField"/>
                                                        <s:hidden id="search_airbill_order_type"
                                                                  name="searchAirbillFilter.orderType"/>
                                                        <table class="s36 b24">
                                                            <tbody>
                                                            <tr>
                                                                <td width="75"><xms:localization text="Franchise #"/>
                                                                    <s:hidden
                                                                            name="searchAirbillFilter.franchiseList"/></td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckFranchiseCode"/>
																	</span></td>
                                                                <td><s:textfield
                                                                        name="searchAirbillFilter.franchiseCode"
                                                                        cssClass="form-control alloptions"/></td>
                                                                <td><xms:localization text="Invoice Date"/></td>
                                                                <td colspan="2"><s:select list="listInvoiceDates"
                                                                                          cssClass="form-control"
                                                                                          headerKey="" headerValue=""
                                                                                          name="searchAirbillFilter.invoiceDate"/></td>
                                                                <td><xms:localization text="Packing Type"/></td>
                                                                <td colspan="4"><s:select list="listPackageTypes"
                                                                                          listKey="packageId"
                                                                                          listValue="packageName"
                                                                                          cssClass="form-control"
                                                                                          name="searchAirbillFilter.packageTypeId"/></td>
                                                                <td><xms:localization text="Sender Country Code"/></td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckSenderCode"/>
																	</span></td>
                                                                <td><s:select list="listCountries" listKey="countryCode"
                                                                              listValue="countryCode"
                                                                              cssClass="form-control"
                                                                              name="searchAirbillFilter.senderCode"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td width="75"><xms:localization text="Sales Rep"/></td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckSaleRepId"/>
																	</span></td>
                                                                <td><s:select list="listSalesRep" listKey="userId"
                                                                              listValue="displayName"
                                                                              cssClass="form-control"
                                                                              name="searchAirbillFilter.saleRepId"/></td>
                                                                <td><xms:localization text="Import Date"/></td>
                                                                <td colspan="2"><s:i18n_select list="listImportDate"
                                                                                               listValue="importDate"
                                                                                               listKey="importDate"
                                                                                               headerKey=""
                                                                                               headerValue=""
                                                                                               cssClass="form-control"
                                                                                               name="searchAirbillFilter.importDate"/></td>
                                                                <td><xms:localization text="Zone"/></td>
                                                                <td colspan="2"><s:textfield
                                                                        cssClass="form-control alloptions "
                                                                        cssStyle="width: 73px;"
                                                                        name="searchAirbillFilter.zone"/></td>
                                                                <td><xms:localization text="No Zone"/></td>
                                                                <td><s:checkbox name="searchAirbillFilter.isNoZone"/>
                                                                <td><xms:localization
                                                                        text="Receiver Country Code"/></td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckReceiverCode"/>
																	</span></td>
                                                                <td><s:select list="listCountries" listKey="countryCode"
                                                                              listValue="countryCode"
                                                                              cssClass="form-control"
                                                                              name="searchAirbillFilter.receiverCode"/></td>
                                                            </tr>

                                                            <tr>
                                                                <td width="75"><xms:localization
                                                                        text="Customer #"/></td>
                                                                <td colspan="2"><s:textfield
                                                                        cssClass="form-control alloptions "
                                                                        name="searchAirbillFilter.customerCode"/></td>
                                                                <td><xms:localization text="Carrier"/></td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckServiceId"/>
																	</span></td>
                                                                <td><s:i18n_select list="listCarriers"
                                                                                   listKey="serviceId"
                                                                                   onchange="changeServiceFilter($(this).val())"
                                                                                   listValue="serviceName" headerKey=""
                                                                                   headerValue="All"
                                                                                   cssClass="form-control"
                                                                                   name="searchAirbillFilter.serviceId"/></td>
                                                                <td><xms:localization text="Pieces"/></td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckPieces"/>
																	</span></td>
                                                                <td><s:textfield cssClass="form-control alloptions"
                                                                                 cssStyle="width: 40px;"
                                                                                 name="searchAirbillFilter.minPieces"
                                                                                 placeholder="min"/></td>
                                                                <td colspan="2"><s:textfield
                                                                        cssClass="form-control alloptions"
                                                                        cssStyle="width: 40px;"
                                                                        name="searchAirbillFilter.maxPieces"
                                                                        placeholder="max"/></td>
                                                                <td><xms:localization text="Sender Name"/></td>
                                                                <td colspan="2"><s:textfield
                                                                        cssClass="form-control alloptions"
                                                                        name="searchAirbillFilter.senderName"/></td>
                                                            </tr>
                                                            <tr>
                                                                <td width="75"><xms:localization text="Invoice #"/></td>
                                                                <td colspan="2"><s:textfield
                                                                        cssClass="form-control alloptions"
                                                                        name="searchAirbillFilter.invoiceCode"/></td>
                                                                <td><xms:localization text="Service Level"/></td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckServiceLevel"/>
																	</span></td>
                                                                <td id="td_shipment_type_filter"><s:i18n_select
                                                                        list="listShipmentType" cssClass="form-control"
                                                                        listKey="shipmentTypeKey"
                                                                        listValue="shipmentTypeName"
                                                                        name="searchAirbillFilter.serviceLevel"
                                                                        headerKey="" headerValue="All"/></td>
                                                                <td><xms:localization text="Weight"/></td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckWeight"/>
																	</span></td>
                                                                <td><s:textfield cssClass="form-control alloptions"
                                                                                 cssStyle="width: 40px;"
                                                                                 name="searchAirbillFilter.minWeight"
                                                                                 placeholder="min"/></td>
                                                                <td colspan="2"><s:textfield
                                                                        cssClass="form-control alloptions"
                                                                        cssStyle="width: 40px;"
                                                                        name="searchAirbillFilter.maxWeight"
                                                                        placeholder="max"/></td>
                                                                <td><xms:localization text="Invoice Status"/></td>
                                                                <td colspan="2"><s:select list="listInvoiceStatus"
                                                                                          cssClass="form-control"
                                                                                          name="searchAirbillFilter.invoiceStatus"
                                                                                          headerKey=""
                                                                                          headerValue=""/></td>
                                                            </tr>
                                                            <tr>
                                                                <td width="75"><xms:localization text="Airbill #"/></td>
                                                                <td colspan="2"><s:textfield
                                                                        cssClass="form-control alloptions"
                                                                        name="searchAirbillFilter.airbillNumber"/></td>
                                                                <td><xms:localization text="Accessorial"/>&nbsp;&nbsp;&nbsp;</td>
                                                                <td><span class="b34"> <s:checkbox
                                                                        name="searchAirbillFilter.isCheckAccessorialName"/>
																	</span></td>
                                                                <td id="td_accessorial_filter"><s:select
                                                                        list="listAccessorial" listKey="description"
                                                                        headerKey=""
                                                                        name="searchAirbillFilter.accessorialName"
                                                                        headerValue="All" listValue="description"
                                                                        cssClass="form-control"/></td>
                                                                <td><xms:localization text="Shipment Date"/></td>
                                                                <td colspan="3">
                                                                    <div class="form-group input-group mg0">
																			<span class="input-group-addon s31"> <i
                                                                                    class="fa fa-calendar"></i>
																			</span>
                                                                        <s:textfield
                                                                                cssClass="form-control form_datetime"
                                                                                data-date-format="dd MM yyyy"
                                                                                placeholder="Start"
                                                                                cssStyle="width: 98px;"
                                                                                name="searchAirbillFilter.startShipmentDate"/>
                                                                        <span class="text-danger"><s:fielderror
                                                                                fieldName="searchAirbillFilter.startShipmentDate"/></span>
                                                                    </div>
                                                                <td colspan="2">
                                                                    <div class="form-group input-group mg0">
																			<span class="input-group-addon s31"> <i
                                                                                    class="fa fa-calendar"></i>
																			</span>
                                                                        <s:textfield
                                                                                cssClass="form-control form_datetime"
                                                                                data-date-format="dd MM yyyy"
                                                                                placeholder="End"
                                                                                cssStyle="width: 98px;"
                                                                                name="searchAirbillFilter.endShipmentDate"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="6"></td>
                                                                <td><xms:localization text="Invoice Date"/></td>
                                                                <td colspan="3">
                                                                    <div class="form-group input-group mg0">
																			<span class="input-group-addon s31"> <i
                                                                                    class="fa fa-calendar"></i>
																			</span>
                                                                        <s:textfield
                                                                                cssClass="form-control form_datetime"
                                                                                data-date-format="dd MM yyyy"
                                                                                placeholder="Start"
                                                                                cssStyle="width: 98px;"
                                                                                name="searchAirbillFilter.startInvoiceDate"/>
                                                                    </div>
                                                                </td>
                                                                <td colspan="2">
                                                                    <div class="form-group input-group mg0">
																			<span class="input-group-addon s31"> <i
                                                                                    class="fa fa-calendar"></i>
																			</span>
                                                                        <s:textfield
                                                                                cssClass="form-control form_datetime"
                                                                                data-date-format="dd MM yyyy"
                                                                                placeholder="End"
                                                                                cssStyle="width: 98px;"
                                                                                name="searchAirbillFilter.endInvoiceDate"/>
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                            </tbody>
                                                        </table>
                                                        <table class="s36 b24">
                                                            <tr>
                                                                <td width="75"><xms:localization
                                                                        text="Airbill List"/></td>
                                                                <td colspan="2"><textarea rows="3" cols="50"
                                                                                          class="form-control"
                                                                                          name="searchAirbillFilter.airbillNumberList"></textarea>
                                                                </td>
                                                                <td>
                                                                    <button class="btn s37" type="button"
                                                                            onclick="searchAirbill()">
                                                                        <xms:localization text="Search"/>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <div class="form-group flr mgb">
                                                            <div class="btn-group" id="btn-export">
                                                                <input type="button" id="export-option" class="btn s37"
                                                                       value="<xms:localization text="Option" />"
                                                                       onclick="searchAirbillExport($('#selected-option').val())"/>
                                                                <button type="button" class="btn s37 dropdown-toggle"
                                                                        data-toggle="dropdown" aria-expanded="true">
                                                                    <span class="caret"></span>
                                                                </button>
                                                                <s:hidden id="selected-option"/>
                                                                <ul class="dropdown-menu dropdown-menu-right"
                                                                    role="menu">
                                                                    <li><a href="#"
                                                                           onclick="searchAirbillExport('option')"><xms:localization
                                                                            text="Option"/></a></li>
                                                                    <li><a href="#"
                                                                           onclick="searchAirbillExport('pdf')"><xms:localization
                                                                            text="View PDF Copy"/></a></li>
                                                                    <li><a href="#"
                                                                           onclick="searchAirbillExport('csv')"><xms:localization
                                                                            text="Export CSV"/></a></li>
                                                                </ul>
                                                            </div>
                                                        </div>
                                                        <table class="s36 b24">
                                                            <tr>
                                                                <td><span class="s30">* </span> <xms:localization
                                                                        text="Check the red checkboxes"/> (
                                                                </td>
                                                                <td><span class="b34"> <input type="checkbox"
                                                                                              disabled="disabled">
																</span></td>
                                                                <td>) <xms:localization
                                                                        text="to negate search parameters."/></td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </s:form>
                                                <table class="table mg0">
                                                    <tbody>
                                                    <tr>
                                                        <th class="s42">
                                                            <table class="s36">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Show"/></td>
                                                                    <td><s:select list="pageSizes"
                                                                                  cssClass="form-control"
                                                                                  cssStyle="height: 22px; padding-top: 1px; width: 65px;"
                                                                                  id="sel_page_size"/></td>
                                                                    <td><xms:localization text="Entries"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                        </th>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                                <div id="result_search_airbill">
                                                    <table class="table table-hover table-bordered mg0 datatable1"
                                                           id="datatable1">
                                                        <thead>
                                                        <tr>
                                                            <th></th>
                                                            <th><xms:localization text="Carrier - Airbill #"/><br>
                                                                <xms:localization text="Orig/Dest"/><br>
                                                                <xms:localization text="Ship Date"/><br>
                                                                <xms:localization text="Customer #"/><br>
                                                                <xms:localization text="Reference"/><br>
                                                                <xms:localization text="Reference 2"/></th>
                                                            <th><xms:localization text="Sender Address"/></th>
                                                            <th><xms:localization text="Receiver Address"/></th>
                                                            <th><xms:localization text="Pieces"/><br> <xms:localization
                                                                    text="Weight"/><br> <xms:localization
                                                                    text="Dimensions Zone"/></th>
                                                            <th><xms:localization text="Charges"/></th>
                                                            <th><xms:localization text="Total"/></th>
                                                            <th class="td_quoted"><xms:localization text="Quoted"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td colspan="8"><xms:localization
                                                                    text="No data available..."/></td>
                                                        </tr>
                                                        </tbody>
                                                    </table>
                                                    <div class="dataTables_paginate records">
                                                        <div class="row">
                                                            <div class="col-xs-4 text-left">
                                                                <b><xms:localization text="Showing"/> <s:property
                                                                        value="listAirbill.startRecord"/>
                                                                    <xms:localization text="to"/> <s:property
                                                                            value="listAirbill.endRecord"/>
                                                                    <xms:localization text="of"/> <s:property
                                                                            value="listAirbill.totalRecords"/>
                                                                    <xms:localization text="entries"/></b>
                                                            </div>
                                                            <div class="col-xs-8">
                                                                <s:if test="!listAirbill.hasPrev()">
                                                                    <a class="paginate_button previous disabled"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a href="javascript:changePage(<s:property value="%{listAirbill.currentPage - 1}"/>)"
                                                                       class="paginate_button previous"><xms:localization
                                                                            text="Previous"/></a>
                                                                </s:else>
																<span> <s:iterator value="listAirbill.pageRange"
                                                                                   status="count">
                                                                    <s:if test="%{listAirbill.pageRange[#count.index] == listAirbill.currentPage}">
                                                                        <a class="paginate_button current"><s:property
                                                                                value="listAirbill.currentPage"/></a>
                                                                    </s:if>
                                                                    <s:else>
                                                                        <a class="paginate_button"
                                                                           href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                    </s:else>
                                                                </s:iterator>
																</span>
                                                                <s:if test="!listAirbill.hasNext()">
                                                                    <a class="paginate_button next disabled"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:if>
                                                                <s:else>
                                                                    <a class="paginate_button next"
                                                                       href="javascript:changePage(<s:property value="%{listAirbill.currentPage+1}"/>)"><xms:localization
                                                                            text="Next"/></a>
                                                                </s:else>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-actions pal pdt10 div_mass_edit">
                                            <div class="row">
                                                <div class="col-lg-12">
                                                    <div class="form-group text-left s99">
                                                        <div class="form-group">
                                                            <table class="s36 " id="div_mass_edit"
                                                                   style="display: none;">
                                                                <tbody>
                                                                <tr>
                                                                    <td><xms:localization text="Mass Edit"/></td>
                                                                    <td><xms:localization text="Check All Pages"/></td>
                                                                    <td><input type="checkbox" id="is_check_all_pages"
                                                                               onclick="check_all_pages()"/></td>
                                                                    <td>
                                                                        <button class="btn s37" id="check_all_airbills"
                                                                                onclick="check_all_airbills()">
                                                                            <xms:localization text="Check All"/>
                                                                        </button>
                                                                    </td>
                                                                    <td>
                                                                        <button class="btn s37"
                                                                                id="uncheck_all_airbills"
                                                                                onclick="uncheck_all_airbills()">
                                                                            <xms:localization text="Check None"/>
                                                                        </button>
                                                                    </td>
                                                                    <td>
                                                                        <button class="btn s37"
                                                                                id="check_selected_airbill"
                                                                                onclick="check_selected_airbill()">
                                                                            <xms:localization text="Check Selected"/>
                                                                        </button>
                                                                    </td>
                                                                    <td>
                                                                        <button class="btn s37"
                                                                                id="uncheck_selected_airbill"
                                                                                onclick="uncheck_selected_airbill()">
                                                                            <xms:localization text="Uncheck Selected"/>
                                                                        </button>
                                                                    </td>
                                                                    <td><s:select list="massEditOptions" listKey="id"
                                                                                  listValue="name"
                                                                                  cssClass="form-control"
                                                                                  name="massEditValue"
                                                                                  id="massEditValueId"
                                                                                  onchange="massEditToolSelect($(this).val())"/></td>
                                                                </tr>
                                                                </tbody>
                                                            </table>
                                                            <table class="s36 b24">
                                                                <tbody>
                                                                <tr>
                                                                    <td><input type="checkbox" id="is_show_mass_edit"
                                                                               onclick="show_mass_edit()"/></td>
                                                                    <td><xms:localization
                                                                            text="Show Mass Edit Options"/></td>
                                                                    <td><input type="checkbox" id="show-quoted"
                                                                               onclick="showQuoted()"></td>
                                                                    <td><xms:localization
                                                                            text="Show Web Freight Quote"/></td>
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
<s:hidden id="export-data"/>
<div id="div_delete_airbill"></div>
<div id="div_edit_airbill"></div>
<div id="div_edit_airbill_do"></div>
<div id="div_move_airbill"></div>
<div id="div_move_airbill_do"></div>
<div id="div_sender_address"></div>
<div id="div_receiver_address"></div>
<div id="div_sender_address_do"></div>
<div id="div_receiver_address_do"></div>
<div id="div_frm_mass_edit"></div>
<div id="div_frm_mass_edit_do"></div>
<div id="airbill_change_log_dialog" title='<xms:localization text="Airbill Change Log"/>'></div>
<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $(".td_quoted").hide();
    });

    function showQuoted() {
        if ($("#show-quoted").is(":checked")) {
            $(".td_quoted").show();
            $("#result_search_airbill").find(".td_quoted").show();
        } else {
            $(".td_quoted").hide();
            $("#result_search_airbill").find(".td_quoted").hide();
        }
    }

    function changeServiceFilter(val) {
        var dataLoadServiceType = {
            'serviceId': val
        };
        var acctionLoadServiceType = "search_airbill_load_service_type_by_service.ix?reqType=json";
        var divLoadServiceTypeResult = "td_shipment_type_filter";

        var dataLoadAccessorial = {
            'serviceId': val
        };
        var acctionLoadAccessorial = "search_airbill_load_accessorial_by_service.ix?reqType=json";
        var divLoadAccessorialResult = "td_accessorial_filter";
        doPostDataByParametersTwoAcction(acctionLoadServiceType, dataLoadServiceType, divLoadServiceTypeResult, acctionLoadAccessorial, dataLoadAccessorial, divLoadAccessorialResult);
    }

    function changeService(val) {
        var dataLoadServiceType = {
            'serviceId': val
        };
        var acctionLoadServiceType = "search_airbill_load_service_type_by_service.ix?reqType=json";
        var divLoadServiceTypeResult = "td_shipment_type";

        var dataLoadAccessorial = {
            'serviceId': val
        };
        var acctionLoadAccessorial = "search_airbill_load_accessorial_by_service.ix?reqType=json";
        var divLoadAccessorialResult = "td_accessorial";
        doPostDataByParametersTwoAcction(acctionLoadServiceType, dataLoadServiceType, divLoadServiceTypeResult, acctionLoadAccessorial, dataLoadAccessorial, divLoadAccessorialResult);
    }

    function searchAirbill() {
        //XTD-107: keep page index on edit
//        $("#hid_page").val(1);
        var action = "search_airbill_by_filter.ix?reqType=json";
        $("#export-data").val($("#form_search_airbill").serialize())
        doPostDataDiagLogError(action, 'form_search_airbill', '', 'result_search_airbill');
    }
    function changePage(page) {
        $("#hid_page").val(page);
        doPostDataNonError('search_airbill_by_filter.ix?reqType=json', 'form_search_airbill', '', 'result_search_airbill');
    }

    $("select#sel_page_size").change(function () {
        $("#hid_record_size").val($(this).val());
        doPostDataNonError('search_airbill_by_filter.ix?reqType=json', 'form_search_airbill', '', 'result_search_airbill');
    });

    function loadAirbillDetail(shipmentId, airbillNumber, statusFozen, customerCode) {
        var btnSave = "";
        btnSave = "Save";
        var dataLoad = {
            'shipmentId': shipmentId,
            'invoiceStatus': statusFozen,
            'airbillNumber': airbillNumber,
            'airbillInfo.customerCode': customerCode
        };
        var actionLoad = "view_edit_invoice_show_edit_airbill.ix?reqType=json";
        var actionSave = "view_edit_invoice_show_edit_airbill_do.ix?reqType=json";
        // Refresh search airbill after editting airbill
        var action = "search_airbill_by_filter.ix?reqType=json";
        $("#export-data").val($("#form_search_airbill").serialize());
        loadDialogToEditAirbill(actionLoad, actionSave, dataLoad, "form_edit_airbill", btnSave, "Cancel", "div_edit_airbill", "Edit Airbill", "div_edit_airbill_do", action, 'form_search_airbill', '', 'result_search_airbill');
    }
    function loadMoveAirbill(shipmentId, airbillNumber, invoiceDate, invoiceId, customerCode, invoiceStatus) {
        var statusFozen = invoiceStatus;
        var btnSave = "";
        if (statusFozen == "0") {
            btnSave = "Save";
        }
        var dataLoad = {
            'shipmentId': shipmentId,
            'invoiceStatus': statusFozen,
            'airbillNumber': airbillNumber,
            'invoiceDate': invoiceDate,
            'invoiceId': invoiceId,
            'customerCode': customerCode
        };
        var actionLoad = "view_edit_invoice_show_move_airbill.ix?reqType=json";
        var actionSave = "view_edit_invoice_show_move_airbill_do.ix?reqType=json";
        // Refresh search airbill after moving
//        $("#hid_page").val(1);
        var action = "search_airbill_by_filter.ix?reqType=json";
        $("#export-data").val($("#form_search_airbill").serialize());
        loadDialogToMoveAirbill(actionLoad, actionSave, dataLoad, "form_move_airbill", btnSave, "Cancel", "div_move_airbill", "Move Airbill", "div_move_airbill_do", action, 'form_search_airbill', '', 'result_search_airbill');
    }

    function loadAjustment(shipmentId, airbilNumber, invoiceCode) {
        var win = window.open("adjustment.ix?adjustmentRequest.airbillNumber=" + airbilNumber + "&adjustmentRequest.shipmentId=" + shipmentId + "&adjustmentRequest.invoiceCode=" + invoiceCode, '_blank');
        win.focus();
    }

    function loadChangeLog() {
        alert("Function building ...");
    }

    function loadSenderEdit(senderAddressId, invoiceStatus) {
        var statusFozen = invoiceStatus;
        var btnSave = "";
        if (statusFozen == "0") {
            btnSave = "Save";
        }
        var dataLoad = {
            'senderAddressId': senderAddressId,
            'invoiceStatus': statusFozen
        };
        var actionLoad = "view_edit_invoice_edit_sender_address.ix?reqType=json";
        var actionSave = "view_edit_invoice_edit_sender_address_do.ix?reqType=json";
        loadDialogToSaveCallBack(actionLoad, actionSave, dataLoad, "form_edit_sender_address", btnSave, "Cancel", "div_sender_address", "Edit Sender Airbill", "div_sender_address_do", searchAirbill);
    }

    function loadReceiverEdit(receiverAddressId, invoiceStatus) {
        var statusFozen = invoiceStatus;
        var btnSave = "";
        if (statusFozen == "0") {
            btnSave = "Save";
        }
        var dataLoad = {
            'receiverAddressId': receiverAddressId,
            'invoiceStatus': statusFozen
        };
        var actionLoad = "view_edit_invoice_edit_receiver_address.ix?reqType=json";
        var actionSave = "view_edit_invoice_edit_receiver_address_do.ix?reqType=json";
        loadDialogToSaveCallBack(actionLoad, actionSave, dataLoad, "form_edit_receiver_address", btnSave, "Cancel", "div_receiver_address", "Edit Receiver Airbill", "div_receiver_address_do", searchAirbill);
    }

    function deleteAirbill(airbillNumber, shipmentId, invoiceId) {
        var data = {
            'shipmentId': shipmentId,
            'airbillNumber': airbillNumber,
            'invoiceId': invoiceId
        };
        var actionLoad = "view_edit_invoice_delete_airbill.ix?reqType=json";
        var actionSave = "view_edit_invoice_delete_airbill_do.ix?reqType=json";
        // Refresh search airbill after deleting
        $("#hid_page").val(1);
        var action = "search_airbill_by_filter.ix?reqType=json";
        $("#export-data").val($("#form_search_airbill").serialize());
        loadResultToDeleteAirbill(actionLoad, actionSave, data, "form_delete_airbill", "OK", "Cancel", "div_delete_airbill", "Delete Airbill", "div_invocie_detail", action, 'form_search_airbill', '', 'result_search_airbill');
    }

    function check_all_pages() {
        var is_check_all_pages = $('#is_check_all_pages').is(':checked');
        if (is_check_all_pages)//make disabled
        {
            $("#check_all_airbills").attr("disabled", true);
            $("#uncheck_all_airbills").attr("disabled", true);
            $("#check_selected_airbill").attr("disabled", true);
            $("#uncheck_selected_airbill").attr("disabled", true);

            var a = document.getElementsByName('massEditShipments[]');
            for (i = 0; i < a.length; i++) {
                if (a[i].style.display == '') {
                    a[i].checked = true;
                }
            }
        } else//remove disabled
        {
            $("#check_all_airbills").attr("disabled", false);
            $("#uncheck_all_airbills").attr("disabled", false);
            $("#check_selected_airbill").attr("disabled", false);
            $("#uncheck_selected_airbill").attr("disabled", false);

            var a = document.getElementsByName('massEditShipments[]');
            for (i = 0; i < a.length; i++) {
                a[i].checked = false;
            }
        }
    }

    function check_all_airbills() {
        $('.massEditShipments').prop('checked', true);
    }

    function uncheck_all_airbills() {
        $('.massEditShipments').prop('checked', false);
    }

    function check_selected_airbill() {
        $('.selected-row').find('.massEditShipments').prop('checked', true);
    }

    function uncheck_selected_airbill() {
        $('.selected-row').find('.massEditShipments').prop('checked', false);
    }

    function show_mass_edit() {
        if ($('#is_show_mass_edit').is(":checked")) {
            $('#div_mass_edit').show();
        } else {
            $('#div_mass_edit').hide();
        }
    }

    function reset_mass_edit() {
        // Reset mass edit
        $('#massEditValueId').val(0);
    }

    function massEditToolSelect(val) {
        var postData = "";
        if (val != '0' && !(hasRecords == true && $('#is_check_all_pages').is(':checked'))) {
            var i = 0;
            $('.massEditShipments').each(function () {
                if ($(this).is(':checked')) {
                    var shipmentId = $(this).attr("data-shipmentId");
                    var airbillNumber = $(this).attr("data-airbillNumber");
                    var invoiceId = $(this).attr("data-invoiceId");
                    var invoiceDate = $(this).attr("data-invoiceDate");
                    var customerCode = $(this).attr("data-customerCode");
                    var invoiceStatus = $(this).attr("data-invoiceStatus");
                    postData += "listAirbillMassEdit[" + i + "].shipmentId=" + shipmentId;
                    postData += "&listAirbillMassEdit[" + i + "].airbillNumber=" + airbillNumber;
                    postData += "&listAirbillMassEdit[" + i + "].invoiceId=" + invoiceId;
                    postData += "&listAirbillMassEdit[" + i + "].invoiceDate=" + invoiceDate;
                    postData += "&listAirbillMassEdit[" + i + "].customerCode=" + customerCode;
                    postData += "&listAirbillMassEdit[" + i + "].invoiceStatus=" + invoiceStatus + "&";
                    i++;
                }
            });
            if (postData.length == 0) {
                alertDialog.html("<xms:localization text="Please select Airbill" />");
                alertDialog.dialog("open");
                reset_mass_edit();
                return false;
            }
        } else if (val != '0' && hasRecords == true && $('#is_check_all_pages').is(':checked')) {
            var postData = $('#form_search_airbill').serialize();
        }
        var dataLoad = postData;

        // Refresh search airbill after mass edit
        var action = "search_airbill_by_filter.ix?reqType=json";
        $("#export-data").val($("#form_search_airbill").serialize());

        switch (+val) {
            case 1:
                // Change Weight
                var btnSave = "Save";
                var actionLoad = "show_change_weight.ix?reqType=json";
                var actionSave = "save_change_weight.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_change_weight", btnSave, "Cancel", "div_frm_mass_edit", "Change Weight", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 2:
                // Force Base Charge Recalculate
                var btnSave = "OK";
                var actionLoad = "show_recalc_base_charge.ix?reqType=json";
                var actionSave = "save_recalc_base_charge.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_recalc_base_charge", btnSave, "Cancel", "div_frm_mass_edit", "Recalculate Base Charge", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 3:
                // Force Customer Base Charge
                var btnSave = "OK";
                var actionLoad = "show_recalc_customer_base_charge.ix?reqType=json";
                var actionSave = "save_recalc_customer_base_charge.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_mass_edit_recalc_customer_base_charge", btnSave, "Cancel", "div_frm_mass_edit", "Recalculate Customer Base Charge", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 4:
                // Change Sender address to Customer Physical Address
                var btnSave = "Save";
                var actionLoad = "show_change_sender_address_to_customer.ix?reqType=json";
                var actionSave = "save_change_sender_address_to_customer.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_change_sender_address", btnSave, "Cancel", "div_frm_mass_edit", "Change Sender Address", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 5:
                // Change Sender Address to Sender Address from Webship
                var btnSave = "Save";
                var actionLoad = "show_change_sender_address_to_webship.ix?reqType=json";
                var actionSave = "save_change_sender_address_to_webship.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_change_sender_address", btnSave, "Cancel", "div_frm_mass_edit", "Change Sender Address", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 6:
                // Change Service Type and Force Base Charge Recalc
                // Not neeeded
                break;
            case 7:
                // Change Customer #
                // Not needed as should use ‘move airbill’
                //var btnSave = "Save";
                //var actionLoad = "show_change_customer.ix?reqType=json";
                //var actionSave = "save_change_customer.ix?reqType=json";
                //loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_change_customer", btnSave, "Cancel", "div_frm_mass_edit", "Change Customer #", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 8:
                // Move Airbills to Another Invoice
                // Not needed
                break;
            case 9:
                // Add Accessorial Charge
                var btnSave = "Save";
                var actionLoad = "show_add_accessorial_charge.ix?reqType=json";
                var actionSave = "save_add_accessorial_charge.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_add_accessorial_charge", btnSave, "Cancel", "div_frm_mass_edit", "Add Accessorial Charge", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 10:
                // Adjustment : 'Accessorial Dispute' (Carrier Adjustment Only)
                // Not needed due 2.0 credits process
                break;
            case 11:
                // Recalc Franchise Base Cost
                var btnSave = "OK";
                var actionLoad = "show_recalc_franchise_base.ix?reqType=json";
                var actionSave = "save_recalc_franchise_base.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_recalc_franchise_base", btnSave, "Cancel", "div_frm_mass_edit", "Recalculate Franchise Base Cost", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 12:
                // Move airbill
                var btnSave = "Move";
                var actionLoad = "show_move_airbill.ix?reqType=json";
                var actionSave = "move_airbill.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_mass_edit_move_airbill", btnSave, "Cancel", "div_frm_mass_edit", "Move Airbill", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 13:
                // Change Zone
                var btnSave = "Save";
                var actionLoad = "show_change_zone.ix?reqType=json";
                var actionSave = "save_change_zone.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_change_zone", btnSave, "Cancel", "div_frm_mass_edit", "Change Zone", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 14:
                // Change Service Type
                var btnSave = "Save";
                var actionLoad = "show_change_service_type.ix?reqType=json";
                var actionSave = "save_change_service_type.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_change_service_type", btnSave, "Cancel", "div_frm_mass_edit", "Change Service Type", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 15:
                // Recalc Accessorial Mark-up
                var btnSave = "Save";
                var actionLoad = "show_recalc_accessorial_markup.ix?reqType=json";
                var actionSave = "save_recalc_accessorial_markup.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_recalc_accessorial_markup", btnSave, "Cancel", "div_frm_mass_edit", "Recalculate Accessorial Markup", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 16:
                // Markup Accessorial
                var btnSave = "Save";
                var actionLoad = "show_markup_accessorial.ix?reqType=json";
                var actionSave = "save_markup_accessorial.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_markup_accessorial", btnSave, "Cancel", "div_frm_mass_edit", "Add Customer Cost", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 17:
                // Mass Change Accessorial Type
                var btnSave = "Save";
                var actionLoad = "show_change_accessorial_type.ix?reqType=json";
                var actionSave = "save_change_accessorial_type.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_change_accessorial_type", btnSave, "Cancel", "div_frm_mass_edit", "Mass Change Accessorial Type", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 18:
                // Delete airbill
                var btnSave = "Delete";
                var actionLoad = "show_delete_airbill.ix?reqType=json";
                var actionSave = "delete_airbill.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_delete_airbill", btnSave, "Cancel", "div_frm_mass_edit", "Delete Airbill", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 19:
                // Recalculate Customer Cost
                var btnSave = "OK";
                var actionLoad = "show_recalc_customer_cost.ix?reqType=json";
                var actionSave = "save_recalc_customer_cost.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_recalc_customer_cost", btnSave, "Cancel", "div_frm_mass_edit", "Recalculate Customer Cost", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 20:
                // Force Quoted Charge
                var btnSave = "OK";
                var actionLoad = "show_force_quoted_charge.ix?reqType=json";
                var actionSave = "save_force_quoted_charge.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_force_quoted_charge", btnSave, "Cancel", "div_frm_mass_edit", "Force Quoted Charge", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 21:
                // Force Quoted Accessorial Charge
                var btnSave = "OK";
                var actionLoad = "show_force_quoted_accessorial_charge.ix?reqType=json";
                var actionSave = "save_force_quoted_accessorial_charge.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_force_quoted_accessorial_charge", btnSave, "Cancel", "div_frm_mass_edit", "Force Quoted Accessorial Charge", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 22:
                // Force Markup Customer Cost
                var btnSave = "Save";
                var actionLoad = "show_force_markup_customer_cost.ix?reqType=json";
                var actionSave = "save_force_markup_customer_cost.ix?reqType=json";
                loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, "form_force_markup_customer_cost", btnSave, "Cancel", "div_frm_mass_edit", "Force Markup Customer Cost", "div_frm_mass_edit_do", action, 'form_search_airbill', '', 'result_search_airbill');
                break;
            case 23:
                // Full Carrier Credit Note
                // Not needed
                break;
        }
        // Reset mass edit option
        reset_mass_edit();
    }
    function searchAirbillExport(option) {
        if (!hasRecords && option != "option") {
            alertDialog.html("<xms:localization text="There's no records to export" />");
            alertDialog.dialog("open");
            return false;
        }
        var data = $("#export-data").val();
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'pdf':
                $("#export-option").val("<xms:localization text="View PDF Copy" />");
                loadingDialog.dialog("open");
                $.fileDownload("search_airbill_export_pdf.ix", {
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
            case 'csv':
                $("#export-option").val("<xms:localization text="Export CSV" />");
                loadingDialog.dialog("open");
                $.fileDownload("search_airbill_export_csv.ix", {
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