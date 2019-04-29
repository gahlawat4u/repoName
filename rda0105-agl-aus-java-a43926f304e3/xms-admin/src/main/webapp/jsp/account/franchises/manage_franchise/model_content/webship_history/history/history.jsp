<%@page import="com.gms.xms.common.constants.AppConstants.ENCODE_TYPE" %>
<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<s:form name="form_history" id="form_history">
    <s:hidden name="filterModel.page" value="1" id="hid_page"></s:hidden>
    <s:hidden value="" id="hid_schedule_collection_id"></s:hidden>
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
                                    <xms:localization text="History"/>
                                </div>
                                <div class="tools">
                                    <i class="fa fa-chevron-up"></i><i class="fa fa-times"></i>
                                </div>
                            </div>
                            <div class="portlet-body">
                                <div class="panel-body pan">
                                    <div class="form-body pal">
                                        <div class="row">
                                            <div class="col-lg-12">
                                                <div class="col-lg-12 pd0">
                                                    <div class="form-group mgb">
                                                        <table class="s36">
                                                            <tr>
                                                                <td><s:select list="listTotalDate" listKey="key"
                                                                              listValue="value" id="sel_total_date"
                                                                              cssClass="form-control"
                                                                              name="filterModel.totalDate"
                                                                              value="2"></s:select></td>
                                                                <td>
                                                                    <button class="btn s33" type="button"
                                                                            id="btn_refresh"
                                                                            onclick="javascript:refreshHistory();">
                                                                        <xms:localization text="Refresh"/>
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <button class="btn s33" type="button"
                                                                            onclick="javascript:exportToExcel();">
                                                                        <xms:localization text="Export to Excel"/>
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <button class="btn s33" type="button"
                                                                            onclick="javascript:exportToPdf();">
                                                                        <xms:localization text="Export to PDF"/>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12 pd0" style="margin-top: 5px;" id="div_range_date">
                                                    <div class="form-group mgb">
                                                        <table class="s36">
                                                            <tr>
                                                                <td><label> <xms:localization text="From"/> :
                                                                </label></td>
                                                                <td>
                                                                    <div class="form-group input-group mg0">
																		<span class="input-group-addon s31"> <i
                                                                                class="fa fa-calendar"></i>
																		</span> <input
                                                                            class="form-control form_datetime"
                                                                            id="txtFromDate" type="text"
                                                                            name="filterModel.fromDate"
                                                                            data-date-format="dd MM yyyy">
                                                                    </div>
                                                                </td>
                                                                <td><label> <xms:localization text="To"/> :
                                                                </label></td>
                                                                <td>
                                                                    <div class="form-group input-group mg0">
																		<span class="input-group-addon s31"> <i
                                                                                class="fa fa-calendar"></i>
																		</span> <input
                                                                            class="form-control form_datetime"
                                                                            id="txtToDate" name="filterModel.toDate"
                                                                            type="text" data-date-format="dd MM yyyy">
                                                                    </div>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>

                                                <div class="col-lg-12 pd0" style="margin-top: 5px;">
                                                    <div class="form-group mgb">
                                                        <table class="s36">
                                                            <tr>
                                                                <td><label> <xms:localization text="Search"/> :
                                                                </label></td>
                                                                <td><s:select list="listSearch" listKey="key"
                                                                              listValue="value"
                                                                              id="sel_list_search"></s:select></td>
                                                                <td><input class="form-control" id="txt_value_search"
                                                                           name="filterModel.connoteNumber"/></td>
                                                                <td>
                                                                    <button class="btn s33" type="button"
                                                                            onclick="javascript:refreshHistory();">
                                                                        <xms:localization text="Search"/>
                                                                    </button>
                                                                </td>
                                                                <td>
                                                                    <button class="btn s33" type="reset">
                                                                        <xms:localization text="Clear"/>
                                                                    </button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </div>
                                                </div>
                                                <div class="col-lg-12 " id="button_actions" style="margin-top: 10px">

                                                    <button class="btn s33 s44" type="button"
                                                            onclick="javascript:reshipHistory();">
                                                        <xms:localization text="Re-Ship Same Package"/>
                                                    </button>
                                                        <%-- <button class="btn s33 s44" type="button" onclick="javascript:viewDetail();">
                                                            <xms:localization text="View Details" />
                                                        </button> --%>
                                                    <button class="btn s33 s44" type="button" id="btnViewAirbill"
                                                            onclick="javascript:viewAirbill();">
                                                        <xms:localization text="View Airbill"/>
                                                    </button>
                                                    <button class="btn s33 s44" type="button" id="btnViewThermalLabel"
                                                            onclick="javascript:viewThermalLabel()">
                                                        <xms:localization text="Thermal Label"/>
                                                    </button>
                                                    <button class="btn s33 s44" type="button" id="btnviewmanifest"
                                                            onclick="javascript:viewManifest()">
                                                        <xms:localization text="View Manifest"/>
                                                    </button>
                                                    <button class="btn s33 s44" type="button" id="btn_viewcommercial"
                                                            onclick="javascript:viewCommercialInvoice()">
                                                        <xms:localization text="View Commercial Invoice"/>
                                                    </button>
                                                    <button class="btn s33 s44" type="button"
                                                            onclick="javascript:viewPackingList()"
                                                            id="btn_packing_list">
                                                        <xms:localization text="View Packing List"/>
                                                    </button>
                                                    <button class="btn s33 s44" type="button" id="btnSendAirbill"
                                                            onclick="javascript:sendAirbill();">
                                                        <xms:localization text="Send Airbill"/>
                                                    </button>
                                                    <button class="btn s33 s44" type="button"
                                                            id="btn_schedule_collection"
                                                            onclick="javascript:scheduleCollection();">
                                                        <xms:localization text="Schedule Collection"/>
                                                    </button>
                                                    <button disabled="disabled" class="btn s33 s44" type="button"
                                                            id="btn_mo_schedule_collection"
                                                            onclick="javascript:moScheduleCollection();">
                                                        <xms:localization text="Modify Schedule Collection"/>
                                                    </button>
                                                    <button class="btn s33 s44" type="button"
                                                            id="btn_ca_schedule_collection"
                                                            onclick="javascript:caScheduleCollection();">
                                                        <xms:localization text="Cancel Collection"/>
                                                    </button>

                                                        <%-- <button class="btn s33 s44" type="submit">
                                                        <xms:localization text="Shipping Label" />
                                                    </button> --%>
                                                    <button class="btn s33 s44" type="button"
                                                            onclick="javascript:trackAirbill();">
                                                        <xms:localization text="Track"/>
                                                    </button>
                                                        <%-- <button class="btn s33 s44" type="button">
                                                        <xms:localization text="Tracking Label" />
                                                    </button> --%>
                                                    <button class="btn s33 s44" type="button" id="btn_void"
                                                            onclick="javascript:voidAirbill();">
                                                        <xms:localization text="Void"/>
                                                    </button>
                                                    <button class="btn s33 s44" type="button"
                                                            onclick="javascript:showNote();">
                                                        <xms:localization text="View Note"/>
                                                    </button>
                                                        <%-- <button class="btn s33 s44" type="button" id="addnote-link" onclick="javascript:addNote();">
                                                            <xms:localization text="Add Note" />
                                                        </button>
                                                        <button class="btn s33 s44" type="button" id="multi_void" onclick="javascript:multiVoid();">
                                                            <xms:localization text="Multi Void" />
                                                        </button>
                                                        <button class="btn s33 s44" type="button" onclick="javascript:updateCollectionNo();" id="update_collection">
                                                            <xms:localization text="Update Collection No" />
                                                        </button>--%>

                                                    <!-- Note -->

                                                    <!-- End AddNote -->
                                                </div>
                                                <div class="text-left">
                                                    <ul class="pagination mtn mbn" data-hover="">
                                                        <li>
                                                            <span class="lsc"><xms:localization text="Show"/></span>
                                                        </li>
                                                        <li>
															<span class="lsc t1"> <s:select list="pageSizeList"
                                                                                            cssClass="form-control"
                                                                                            name="filterModel.recordSize"
                                                                                            value="25"
                                                                                            id="sel_page_size"></s:select>
															</span>
                                                        </li>
                                                        <li>
                                                            <span class="lsc"><xms:localization text="entries"/></span>
                                                        </li>
                                                    </ul>
                                                </div>
                                                <div id="div_history_data">
                                                    <s:hidden value="" id="hid_shipment_id"></s:hidden>
                                                    <input type="hidden" id="hid_history_records"
                                                           value="<s:property value='historyModels.records.size' />"/>

                                                    <div class="col-lg-12 pd0 mg0 "
                                                         style="min-width: 10px !important; overflow: auto"
                                                         data-original-title='<%=WebUtils.getTooltip(request, "Double click Shipment to view details.", ENCODE_TYPE.JAVASCRIPT)%>'
                                                         data-toggle="tooltip" data-placement="top" placeholder="">
                                                        <s:if test="historyModels.records.size != 0">
                                                            <table class="table table-hover table-bordered mg0 datatable1"
                                                                   id="datatable1">
                                                                <thead>
                                                                <tr>
                                                                    <th></th>
                                                                    <th><xms:localization text="Carrier"/></th>
                                                                    <th><xms:localization text="Voided"/></th>
                                                                    <th><xms:localization text="Tracking"/>#</th>
                                                                    <th><xms:localization text="Date"/></th>
                                                                    <th><xms:localization text="Time Stamp"/></th>
                                                                    <th><xms:localization text="Ship Date"/></th>
                                                                    <th><xms:localization text="Pieces"/></th>
                                                                    <th><xms:localization text="Service"/></th>
                                                                    <th class="col_hidden th_package"><xms:localization
                                                                            text="Package"/></th>
                                                                    <th><xms:localization text="Weight"/></th>
                                                                    <th class="col_hidden th_dimensions">
                                                                        <xms:localization text="Dimension"/></th>
                                                                    <th><xms:localization text="Quoted"/></th>
                                                                    <th><xms:localization text="Insured Amount"/></th>
                                                                    <th><xms:localization text="Scheduled"/></th>
                                                                    <th><xms:localization
                                                                            text="Scheduled collection timestamp"/></th>
                                                                    <th><xms:localization
                                                                            text="Collection Information"/></th>
                                                                    <th class="col_hidden th_shipment_reference">
                                                                        <xms:localization text="Reference"/></th>
                                                                    <th class="col_hidden th_billing_party">
                                                                        <xms:localization text="Billing Party"/></th>
                                                                    <th class="col_hidden th_sender_company">
                                                                        <xms:localization text="Sender Company"/></th>
                                                                    <th class="col_hidden th_sender_contact">
                                                                        <xms:localization text="Sender Contact"/></th>
                                                                    <th class="col_hidden th_sender_location">
                                                                        <xms:localization text="Sender Location"/></th>
                                                                    <th class="col_hidden th_receiver_company">
                                                                        <xms:localization text="Receiver Company"/></th>
                                                                    <th class="col_hidden th_receiver_contact">
                                                                        <xms:localization text="Receiver Contact"/></th>
                                                                    <th><xms:localization text="Destination"/></th>
                                                                    <th><xms:localization text="Dest. Country"/></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <s:iterator value="historyModels.records">
                                                                    <tr class="tr_no_record">
                                                                        <td><s:if test="%{voidStatus=='NO'}">
                                                                            <s:checkbox name="chk_multivoid[]"
                                                                                        fieldValue="%{shipmentId}"
                                                                                        class="chk_multivoid chk_multivoid_%{voidStatus}"></s:checkbox>
                                                                            <s:hidden value="0" id="is_void"/>
                                                                        </s:if> <s:else>
                                                                            <s:hidden value="1" id="is_void"/>
                                                                        </s:else></td>

                                                                        <td><s:property value="serviceName"/> <s:hidden
                                                                                value="%{shipmentId}" id="shipmentId"/>
                                                                            <s:hidden value="%{commercialInvoiceId}"
                                                                                      id="commercialInvoiceId"/>
                                                                            <s:hidden value="%{schId}"
                                                                                      id="schedule_collection_id"/>
                                                                            <s:hidden value="%{serviceId}"
                                                                                      id="service_id"/> <s:hidden
                                                                                    value="%{packingList}"
                                                                                    id="packingList"/></td>
                                                                        <td class="td_void_status"><s:property
                                                                                value="voidStatus"/></td>
                                                                        <td id="td_airbillNumber"><s:property
                                                                                value="airbillNumber"/></td>
                                                                        <td><s:property value="createDate"/></td>
                                                                        <td><s:property value="timeStamp"/></td>
                                                                        <td><s:property value="shipDate"/></td>
                                                                        <td><s:property value="noOfPieces"/></td>
                                                                        <td><s:property value="shipmentType"/></td>
                                                                        <td class="col_hidden th_package"><s:property
                                                                                value="packageName"/></td>
                                                                        <td><s:property value="weight"/></td>
                                                                        <td class="col_hidden th_dimensions"><s:property
                                                                                value="dimensions"/></td>
                                                                        <td><s:if test="total != 0">
                                                                            <s:property value="total"/>
                                                                        </s:if> <s:else>
                                                                            <xms:localization text="TBA"/>
                                                                        </s:else></td>
                                                                        <td><s:property value="withInsurance"/></td>
                                                                        <td class="td_schedule"><s:property
                                                                                value="schedule"/></td>
                                                                        <td><s:property value="schcollTimeStamp"/></td>
                                                                        <td><s:property value="confirmationNo"/></td>
                                                                        <td class="col_hidden th_shipment_reference">
                                                                            <s:property value="reference"/></td>
                                                                        <td class="col_hidden th_billing_party">
                                                                            <s:property value="billingParty"/></td>
                                                                        <td class="col_hidden th_sender_company">
                                                                            <s:property value="senderCompany"/></td>
                                                                        <td class="col_hidden th_sender_contact">
                                                                            <s:property value="senderName"/></td>
                                                                        <td class="col_hidden th_sender_location">
                                                                            <s:property value="senderLocation"/></td>
                                                                        <td class="col_hidden th_receiver_company">
                                                                            <s:property value="reciverCompany"/></td>
                                                                        <td class="col_hidden th_receiver_contact">
                                                                            <s:property value="reciverContact"/></td>
                                                                        <td><s:property value="destinations"/></td>
                                                                        <td><s:property value="destCountry"/></td>
                                                                    </tr>
                                                                </s:iterator>
                                                                </tbody>
                                                            </table>
                                                        </s:if>
                                                        <s:else>
                                                            <table class="table table-hover table-bordered mg0 datatable1">
                                                                <thead>
                                                                <tr>
                                                                    <th></th>
                                                                    <th><xms:localization text="Carrier"/></th>
                                                                    <th><xms:localization text="Voided"/></th>
                                                                    <th><xms:localization text="Tracking"/>#</th>
                                                                    <th><xms:localization text="Date"/></th>
                                                                    <th><xms:localization text="Time Stamp"/></th>
                                                                    <th><xms:localization text="Ship Date"/></th>
                                                                    <th><xms:localization text="Pieces"/></th>
                                                                    <th><xms:localization text="Service"/></th>
                                                                    <th class="col_hidden th_package"><xms:localization
                                                                            text="Package"/></th>
                                                                    <th><xms:localization text="Weight"/></th>
                                                                    <th class="col_hidden th_dimensions">
                                                                        <xms:localization text="Dimension"/></th>
                                                                    <th><xms:localization text="Quoted"/></th>
                                                                    <th><xms:localization text="Insured Amount"/></th>
                                                                    <th><xms:localization text="Scheduled"/></th>
                                                                    <th><xms:localization
                                                                            text="Scheduled collection timestamp"/></th>
                                                                    <th><xms:localization
                                                                            text="Collection Information"/></th>
                                                                    <th class="col_hidden th_shipment_reference">
                                                                        <xms:localization text="Reference"/></th>
                                                                    <th class="col_hidden th_billing_party">
                                                                        <xms:localization text="Billing Party"/></th>
                                                                    <th class="col_hidden th_sender_company">
                                                                        <xms:localization text="Sender Company"/></th>
                                                                    <th class="col_hidden th_sender_contact">
                                                                        <xms:localization text="Sender Contact"/></th>
                                                                    <th class="col_hidden th_sender_location">
                                                                        <xms:localization text="Sender Location"/></th>
                                                                    <th class="col_hidden th_receiver_company">
                                                                        <xms:localization text="Receiver Company"/></th>
                                                                    <th class="col_hidden th_receiver_contact">
                                                                        <xms:localization text="Receiver Contact"/></th>
                                                                    <th><xms:localization text="Destination"/></th>
                                                                    <th><xms:localization text="Dest. Country"/></th>
                                                                </tr>
                                                                </thead>
                                                                <tbody>
                                                                <tr>
                                                                    <td colspan="26"><xms:localization
                                                                            text="No record to view"/> ...
                                                                    </td>
                                                                </tr>

                                                                </tbody>
                                                            </table>
                                                        </s:else>
                                                    </div>
                                                </div>
                                                <button class="btn s33 s44 multivoid" type="button"
                                                        onclick="javascript:proceedToVoid();">
                                                    <xms:localization text="Proceed to Void"/>
                                                </button>
                                                <button class="btn s33 s44 multivoid" type="button"
                                                        onclick="javascript:proceedToUpate();">
                                                    <xms:localization text="Proceed to Update"/>
                                                </button>
                                                <button class="btn s33 s44 multivoid" type="button"
                                                        id="cancel_multivoid">
                                                    <xms:localization text="Cancel"/>
                                                </button>

                                                <div id="div_proceed_tovoid"></div>
                                                <div id="div_proceed_tovoid_result"></div>
                                                <div id="div_proceed_toupdate"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="form-actions text-right pal pdt10">
                                        <div class="row">
                                            <div class="col-lg-2">
                                                <div class="form-group text-left s100">
                                                    <div class="form-group">
                                                        <div class="checkbox">
                                                            <label> <input type="checkbox" id="chk_show_option"
                                                                           onclick="javascript:showOption();"> &nbsp;
                                                                <xms:localization text="Show Report Options"/>
                                                            </label>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-lg-12" id="div_list_chk_option">
                                                <div class="form-group text-left s99">
                                                    <div class="form-group">
                                                        <label class="checkbox-inline"> <input type="checkbox"
                                                                                               class="cb1"
                                                                                               id="chk_option_all"
                                                                                               onclick="javascript:optionShowAll();">
                                                            &nbsp; <xms:localization text="All"/>
                                                        </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                        name="th_package"
                                                                                                        class="cb1"
                                                                                                        onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Package Type"/>
                                                    </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                    name="th_dimensions"
                                                                                                    class="cb1"
                                                                                                    onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Dimensions"/>
                                                    </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                    name="th_shipment_reference"
                                                                                                    class="cb1"
                                                                                                    onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Shipment Reference"/>
                                                    </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                    name="th_billing_party"
                                                                                                    class="cb1"
                                                                                                    onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Billing Party"/>
                                                    </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                    name="th_sender_company"
                                                                                                    class="cb1"
                                                                                                    onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Sender Company"/>
                                                    </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                    name="th_sender_contact"
                                                                                                    class="cb1"
                                                                                                    onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Sender Contact"/>
                                                    </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                    name="th_sender_location"
                                                                                                    class="cb1"
                                                                                                    onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Sender Localtion"/>
                                                    </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                    name="th_receiver_company"
                                                                                                    class="cb1"
                                                                                                    onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Receiver Company"/>
                                                    </label> <label class="checkbox-inline"> <input type="checkbox"
                                                                                                    name="th_receiver_contact"
                                                                                                    class="cb1"
                                                                                                    onclick="javascript:optionShowCol();">
                                                        &nbsp; <xms:localization text="Receiver Contact"/>
                                                    </label>
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
</s:form>
<div id="note-dialog"></div>
<div id="add-note-dialog"></div>
<div id="send-airbill-dialog"></div>
<div id="history_detaill"></div>
<div class="remember_me">
    <ul style="margin-left: 5px; margin-top: 10px;">
        <li style="color: #F00;">
            <s:fielderror/>
        </li>
        <li style="color: #F00;">
            <s:actionerror/>
        </li>
    </ul>
</div>
<!--END CONTENT-->
<script type="text/javascript">
    $('.form_datetime').datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'dd-mm-yyyy'
    });
    $(document).ready(function () {
        $("#div_range_date").hide();
        $("#div_list_chk_option").hide();
        var hid_history_records = $("#hid_history_records").val();

        $("#button_actions").find("button").attr("disabled", true);
        if (hid_history_records > 0) {
            $("#multi_void, #update_collection").attr("disabled", false);
        }
        $(".chk_multivoid").hide();
        $(".col_hidden").hide();
        optionShowAll();
        optionShowCol();

        $('.multivoid').hide();

        $("#txt_value_search").attr("name", "filterModel.reciverName");

    });
    function multiVoid() {
        $('.chk_multivoid_NO').show();
        $('.multivoid').show();

        $("#button_actions").find("button").attr("disabled", true);
        $("#multi_void, #update_collection").attr("disabled", false);
    }

    $('#cancel_multivoid').click(function () {
        $('.chk_multivoid_NO').hide();
        $('.multivoid').hide();
    });
    function refreshHistory() {
        $("#hid_page").val(1);
        doPostDataNonError('history_search.ix?reqType=json', 'form_history', '', 'div_history_data');
    }

    function changePage(page) {
        $("#hid_page").val(page);
        doPostDataNonError('history_search.ix?reqType=json', 'form_history', '', 'div_history_data');

    }

    function sendAirbill() {
        var shipment_id = $("#hid_shipment_id").val();
        var emails = $('#list_email').val();
        loadDialogFieldError("send_airbill.ix?reqType=json", {
            'shipmentId': shipment_id,
            'sendAirlbillHistoryFillterModel.templateEmail': 'Send Airbill Label'
        }, "form-send-airbill", "Send", "Close", "send-airbill-dialog", "Send Airbill", "error_send_airbill");
    }
    function showNote() {
        var shipment_id = $("#hid_shipment_id").val();
        var data = {
            'shipmentNoteFilterModel.shipmentId': shipment_id,
            'shipmentNoteFilterModel.sizeRecord': 10
        };
        var data2 = {
            'shipmentNoteFilterModel.shipmentId': shipment_id
        };
        loadTwoDialogWithUpdate("history_shipment_note.ix?reqType=json", data, null, "note-dialog", "Add Note", "Cancel", "View Note", data2);
    }
    function addNote() {
        var shipment_id = $("#hid_shipment_id").val();
        var data = {
            'shipmentNoteFilterModel.shipmentId': shipment_id
        };
        loadDialog("history_shipment_note_add.ix?reqType=json", data, "form-add-note", "Add", "Cancel", "add-note-dialog", "Add Note", "note-dialog");
    }

    function showOption() {
        if ($("#chk_show_option").is(":checked")) {
            $("#div_list_chk_option").slideDown();
        } else {
            $("#div_list_chk_option").slideUp();
        }
    }

    function optionShowAll() {
        if ($("#chk_option_all").is(":checked")) {
            $("#div_list_chk_option").find("input").attr('checked', true);
            $(".col_hidden").show("slow");
        } else {
            $("#div_list_chk_option").find("input").attr('checked', false);
            $(".col_hidden").hide("slow");
        }
        columnOption = '';
        $("#div_list_chk_option").find("input").each(function () {
            var nameColumn = $(this).attr("name");
            if (!$(this).is(":checked")) {
                columnOption += nameColumn+',';
            }
        });
    }

    function viewThermalLabel() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("view_thermal_label.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function updateCollectionNo() {
        doPostDataNonError('history_search.ix?reqType=json', 'form_history', '', 'div_history_data');
    }

    function trackAirbill() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("history_tracking.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function optionShowCol() {
        columnOption = '';
        $("#div_list_chk_option").find("input").each(function () {
            var index = $("#div_list_chk_option").find("input").index(this) - 1;
            var nameColumn = $(this).attr("name");
            var colToHide = $(".datatable1").find("." + nameColumn);
            if ($(this).is(":checked")) {
                $(this).attr('checked', true);
                colToHide.show("slow");
            } else {
                $(this).attr('checked', false);
                colToHide.hide("slow");
                columnOption += nameColumn+',';
            }
        });
    }

    function proceedToVoid() {
        var a = document.getElementsByName('chk_multivoid[]');
        var len = a.length;
        var list_sm = "0";
        var totalSm = 0;
        for (i = 0; i < len; i++) {
            if (a[i].checked == true) {
                list_sm = list_sm + "|" + a[i].value;
                totalSm = totalSm + 1
            }
        }
        var data = {
            'listShipmentId': list_sm
        };
        if (totalSm > 0) {
            var messageVoid = "Are You Sure Want To Void Total " + totalSm;
            loadConfirmDialog("history_proceed_to_void.ix?reqType=json", data, messageVoid, "div_proceed_tovoid", updateCollectionNo(), "OK", "Cancel", "Proceed To Void");
        } else {
            alert("Please select shipment(s) to void.");
        }

    }

    function proceedToUpate() {
        var a = document.getElementsByName('chk_multivoid[]');
        var len = a.length;
        var list_sm = "";
        var totalSm = 0;
        for (i = 0; i < len; i++) {
            if (a[i].checked == true) {
                list_sm = list_sm + "|" + a[i].value;
                totalSm = totalSm + 1
            }
        }
        if (totalSm > 0) {
            var contentDialog = '<p>New collection no:<form id="form_updateCollection"><input name="collectionNoNew" id="collection_no_new" class="form-control" /></form></p>';
            var messageUpdate = "Update Collection of  Total " + totalSm + contentDialog;
            var dataUpdate = {
                'listShipmentId': list_sm
            };
            loadConfirmDialog("history_proceed_update_collection.ix?reqType=json", dataUpdate, messageUpdate, "div_proceed_toupdate", updateCollectionNo(), "OK", "Cancel", "Proceed To Update Collection", "form_updateCollection");
        } else {
            alert("Please select shipment(s) to void.");
        }

    }

    function scheduleCollection(shipmentId) {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("schedule_collection_create.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();

    }

    function moScheduleCollection(shipmentId) {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("schedule_collection_modify.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function reshipHistory() {
        var serviceId = $(this).find("#service_id").val();
        var shipment_id = $("#hid_shipment_id").val();
        if (serviceId == "16" || serviceId == "17" || serviceId == "18" || serviceId == "19" || serviceId == "20" || serviceId == "26" || serviceId == "27" || serviceId == "36") {
            window.location = "view_shipment_receipt.ix?shipmentId=" + shipment_id;
        } else {
            window.location = "webship.ix?shipmentId=" + shipment_id;
        }
    }

    function viewAirbill() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("view_airbill.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function viewCommercialInvoice() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("view_commercial_invoice.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function caScheduleCollection() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("history_cancel_collection.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function viewDetail() {
        var shipment_id = $("#hid_shipment_id").val();
        var data = {
            'shipmentId': shipment_id
        }
        loadDialogWithFunctionCallBack("history_detail.ix?reqType=json", data, exportDetail, "Export to PDF", "Close", "history_detaill", "Shipment Detail", "");

    }

    function exportDetail() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("history_export_detail.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function voidAirbill() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("history_void_airbill.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function exportToPdf() {
        var data = $("#form_history").serialize();
        columnOption = columnOption.replace("undefined,","");
        columnHide = "&columnHide="+ columnOption.slice(0, - 1);
        window.open("history_export_pdf.ix?" + data + columnHide, '_self');
    }

    function exportDetail() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("history_export_detail.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }


    function exportToExcel() {
        var data = $("#form_history").serialize();
        columnOption = columnOption.replace("undefined,","");
        columnHide = "&columnHide="+ columnOption.slice(0, - 1);
        window.open("history_export_excel.ix?" + data + columnHide, '_self');
    }

    $("select#sel_list_search").change(function () {
        var filter_value = $(this).val();
        $("#txt_value_search").attr("name", filter_value);
    });

    $("select#sel_total_date").change(function () {
        var total_date = $(this).val();
        if (total_date == 5) {
            $("#div_range_date").slideDown();
        } else {
            $("#div_range_date").find("input").val("");
            $("#div_range_date").slideUp();
        }
    });

    $("select#sel_page_size").change(function () {
        doPostDataNonError('history_search.ix?reqType=json', 'form_history', '', 'div_history_data');
    });

    function viewPackingList() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("view_packing_list.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    $('table#datatable1 tbody tr').click(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var shipmentId = $(this).find('#shipmentId').val();
        var commercialInvoiceId = $(this).find('#commercialInvoiceId').val();
        var scheduleCollectionId = $(this).find('#schedule_collection_id').val();
        var serviceId = $(this).find("#service_id").val();
        var packingList = $(this).find("#packingList").val();
        $("#hid_shipment_id").val(shipmentId);
        $("#hidenShipmentId").val(shipmentId);
        $("#hid_schedule_collection_id").val(scheduleCollectionId);
        var strvoid = $(this).find("td.td_void_status").text();

        if ($(".multivoid").css('display') == 'none') {
            $("#button_actions").find("button").attr("disabled", false);
            if (packingList == "2") {
                if ($(this).find("td.td_airbillNumber").text() == "" && serviceId == "50") {
                    $("#btn_packing_list").attr("disabled", true);
                } else {
                    $("#btn_packing_list").attr("disabled", false);
                }
            } else {
                $("#btn_packing_list").attr("disabled", true);
            }
            checkManifestButton(serviceId, strvoid);
            if (commercialInvoiceId == "3") {
                if ($(this).find("td.td_airbillNumber").text() == "" && serviceId == "50") {
                    $("#btn_viewcommercial").attr("disabled", true);
                } else {
                    $("#btn_viewcommercial").attr("disabled", false);
                }
            } else {
                $("#btn_viewcommercial").attr("disabled", true);
            }

            if ($(this).find("td.td_void_status").text() == "YES") {
                $("#btn_void").attr("disabled", true);
                $("#btn_schedule_collection, #btn_mo_schedule_collection, #btn_ca_schedule_collection").attr("disabled", true);
            } else {
                $("#btn_void").attr("disabled", false);
                if ($(this).find("td.td_schedule").text() == "YES") {
                    $("#btn_schedule_collection").attr("disabled", true);
                    if (serviceId == 54) {
                        $("#btn_mo_schedule_collection, #btn_ca_schedule_collection").attr("disabled", true);
                    } else {
                        $("#btn_mo_schedule_collection, #btn_ca_schedule_collection").attr("disabled", false);
                    }
                    if(serviceId==72)
                    {
                        $("#btn_ca_schedule_collection").attr("disabled", true);
                    }
                } else {
                    $("#btn_schedule_collection").attr("disabled", false);
                    $("#btn_mo_schedule_collection, #btn_ca_schedule_collection").attr("disabled", true);
                }
            }
//            $("#btn_ca_schedule_collection").attr("disabled", true);//disiable modifie collection
            if (serviceId != 1 && serviceId != 3 && serviceId != 15 && serviceId != 52 && serviceId != 54 && serviceId != 59 && serviceId != 72) {
                $("#btnViewAirbill").attr("disabled", true);
                $("#btnViewThermalLabel").attr("disabled", true);
                $("#btnSendAirbill").attr("disabled", true);
            }
        } else {
            $("#button_actions").find("button").attr("disabled", true);
            $("#multi_void, #update_collection").attr("disabled", false);
            $("#btn_ca_schedule_collection").attr("disabled", true);//disiable modifie collection
        }

    });

    $('table#datatable1 tbody tr').dblclick(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var shipmentId = $(this).find('#shipmentId').val();
        $("#hid_shipment_id").val(shipmentId);
        viewDetail();
    });

    function checkManifestButton(service_id, strvoid) {
        if (service_id == 50 || service_id == 2 || (service_id == 3 && strvoid != "YES")) {
            $("#btnviewmanifest").attr("disabled", false);
            /* if(new_aae_manifest==true){
             $("#btngenerate_manifest").attr("disabled", false);
             } */
        } else if (service_id == 52) {
            $("#btnviewmanifest").attr("disabled", false);
            /* if(new_toll_manifest==true){
             $("#toll_btngenerate_manifest").attr("disabled", false);
             }	 */
        } else if (service_id == 59) {
            $("#btnviewmanifest").attr("disabled", false);
            /* if(new_toll_ipec_manifest==true){
             $("#toll_ipec_btngenerate_manifest").attr("disabled", false);
             } */
        }
        else if (service_id == 54) {
            $("#btnviewmanifest").attr("disabled", false);
            /* if(new_tnt_int_manifest==true){
             $("#btngenerate_manifest").attr("disabled", true);
             }	 */
        }
        else if (service_id == 72) {
            $("#btnviewmanifest").attr("disabled", false);
        }
        else {
            $("#btnviewmanifest").attr("disabled", true);
        }
    }
    function viewManifest() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("view_manifest.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }
</script>
