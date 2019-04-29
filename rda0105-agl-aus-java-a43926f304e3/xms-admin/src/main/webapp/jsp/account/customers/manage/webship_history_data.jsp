<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form name="form_history" id="form_history">
    <s:hidden name="filterModel.page" value="1" id="hid_page"></s:hidden>
    <s:hidden value="" id="hid_schedule_collection_id"></s:hidden>
    <s:hidden name="customerCode"></s:hidden>
    <div class="portlet-body">
        <div class="panel-body pan">
            <div class="form-body pal">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="col-lg-6 pd0">
                            <div class="form-group fll mgb">
                                <table class="s36">
                                    <tr>
                                        <td><s:select list="listTotalDate" listKey="key" listValue="value"
                                                      id="sel_total_date" cssClass="form-control"
                                                      name="filterModel.totalDate" value="2"></s:select></td>
                                        <td>
                                            <button class="btn s33" type="button" id="btn_refresh"
                                                    onclick="javascript:refreshHistory();">
                                                <xms:localization text="Refresh"/>
                                            </button>
                                        </td>
                                        <td>
                                            <button class="btn s33" type="button" onclick="javascript:exportToExcel();">
                                                <xms:localization text="Export to Excel"/>
                                            </button>
                                        </td>
                                        <td>
                                            <button class="btn s33" type="button" onclick="javascript:exportToPdf();">
                                                <xms:localization text="Export to PDF"/>
                                            </button>
                                        </td>
                                    </tr>
                                </table>
                                <br/>
                            </div>
                            <div class="form-group fll mgb" style="margin-top: 5px;" id="div_range_date">
                                <table class="s36">
                                    <tr>
                                        <td><label> <xms:localization text="From"/> :
                                        </label></td>
                                        <td>
                                            <div class="form-group input-group mg0">
												<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
												</span> <input class="form-control form_datetime" id="txtFromDate"
                                                               type="text" name="filterModel.fromDate"
                                                               data-date-format="dd MM yyyy">
                                            </div>
                                        </td>
                                        <td><label> <xms:localization text="To"/> :
                                        </label></td>
                                        <td>
                                            <div class="form-group input-group mg0">
												<span class="input-group-addon s31"> <i class="fa fa-calendar"></i>
												</span> <input class="form-control form_datetime" id="txtToDate"
                                                               name="filterModel.toDate" type="text"
                                                               data-date-format="dd MM yyyy">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <br/>
                            </div>
                            <br/>
                        </div>

                        <table class="table mg0">
                            <tr>
                                <th class="s42"><span class="s10 lsc t1"><xms:localization text="Show "/> </span> <span
                                        class="lsc t1 s12"> <s:select list="pageSizeList" cssClass="form-control"
                                                                      name="filterModel.recordSize" value="25"
                                                                      id="sel_page_size"></s:select></span><span
                                        class="s10 lsc t1"><xms:localization text=" entries"/></span></th>
                            </tr>
                        </table>
                        <div id="div_history_data">
                            <s:hidden value="" id="hid_shipment_id"></s:hidden>
                            <input type="hidden" id="hid_history_records"
                                   value="<s:property value='historyModels.records.size' />"/>

                            <div class="col-lg-12 pd0 mg0 " style="min-width: 10px !important; overflow: auto"
                                 data-original-title='<xms:localization text="Double click Shipment to view details." />'
                                 data-toggle="tooltip" data-placement="top" placeholder="">
                                <s:if test="historyModels.records.size != 0">
                                    <table class="table table-hover table-bordered mg0 datatable1" id="datatable1">
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
                                            <th class="col_hidden th_package"><xms:localization text="Package"/></th>
                                            <th><xms:localization text="Weight"/></th>
                                            <th class="col_hidden th_dimensions"><xms:localization
                                                    text="Dimension"/></th>
                                            <th><xms:localization text="Quoted"/></th>
                                            <th><xms:localization text="Insured Amount"/></th>
                                            <th><xms:localization text="Scheduled"/></th>
                                            <th><xms:localization text="Scheduled collection timestamp"/></th>
                                            <th><xms:localization text="Collection Information"/></th>
                                            <th class="col_hidden th_shipment_reference"><xms:localization
                                                    text="Reference"/></th>
                                            <th class="col_hidden th_billing_party"><xms:localization
                                                    text="Billing Party"/></th>
                                            <th class="col_hidden th_sender_company"><xms:localization
                                                    text="Sender Company"/></th>
                                            <th class="col_hidden th_sender_contact"><xms:localization
                                                    text="Sender Contact"/></th>
                                            <th class="col_hidden th_sender_location"><xms:localization
                                                    text="Sender Location"/></th>
                                            <th class="col_hidden th_receiver_company"><xms:localization
                                                    text="Receiver Company"/></th>
                                            <th class="col_hidden th_receiver_contact"><xms:localization
                                                    text="Receiver Contact"/></th>
                                            <th><xms:localization text="Destination"/></th>
                                            <th><xms:localization text="Dest. Country"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <s:iterator value="historyModels.records">
                                            <tr class="tr_no_record">
                                                <td><s:if test="%{voidStatus=='NO'}">
                                                    <s:checkbox name="chk_multivoid[]" fieldValue="%{shipmentId}"
                                                                class="chk_multivoid chk_multivoid_%{voidStatus}"></s:checkbox>
                                                    <s:hidden value="0" id="is_void"/>
                                                </s:if> <s:else>
                                                    <s:hidden value="1" id="is_void"/>
                                                </s:else></td>

                                                <td><s:property value="serviceName"/> <s:hidden value="%{shipmentId}"
                                                                                                id="shipmentId"/>
                                                    <s:hidden value="%{commercialInvoiceId}" id="commercialInvoiceId"/>
                                                    <s:hidden value="%{schId}" id="schedule_collection_id"/> <s:hidden
                                                            value="%{serviceId}" id="service_id"/> <s:hidden
                                                            value="%{packingList}" id="packingList"/></td>
                                                <td class="td_void_status"><s:property value="voidStatus"/></td>
                                                <td id="td_airbillNumber"><s:property value="airbillNumber"/></td>
                                                <td><s:property value="createDate"/></td>
                                                <td><s:property value="timeStamp"/></td>
                                                <td><s:property value="shipDate"/></td>
                                                <td><s:property value="noOfPieces"/></td>
                                                <td><s:property value="shipmentType"/></td>
                                                <td class="col_hidden th_package"><s:property value="packageName"/></td>
                                                <td><s:property value="weight"/></td>
                                                <td class="col_hidden th_dimensions"><s:property
                                                        value="dimensions"/></td>
                                                <td><s:if test="total != 0">
                                                    <s:property value="total"/>
                                                </s:if> <s:else>
                                                    <xms:localization text="TBA"/>
                                                </s:else></td>
                                                <td><s:property value="withInsurance"/></td>
                                                <td class="td_schedule"><s:property value="schedule"/></td>
                                                <td><s:property value="schcollTimeStamp"/></td>
                                                <td><s:property value="confirmationNo"/></td>
                                                <td class="col_hidden th_shipment_reference"><s:property
                                                        value="reference"/></td>
                                                <td class="col_hidden th_billing_party"><s:property
                                                        value="billingParty"/></td>
                                                <td class="col_hidden th_sender_company"><s:property
                                                        value="senderCompany"/></td>
                                                <td class="col_hidden th_sender_contact"><s:property
                                                        value="senderName"/></td>
                                                <td class="col_hidden th_sender_location"><s:property
                                                        value="senderLocation"/></td>
                                                <td class="col_hidden th_receiver_company"><s:property
                                                        value="reciverCompany"/></td>
                                                <td class="col_hidden th_receiver_contact"><s:property
                                                        value="reciverContact"/></td>
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
                                            <th class="col_hidden th_package"><xms:localization text="Package"/></th>
                                            <th><xms:localization text="Weight"/></th>
                                            <th class="col_hidden th_dimensions"><xms:localization
                                                    text="Dimension"/></th>
                                            <th><xms:localization text="Quoted"/></th>
                                            <th><xms:localization text="Insured Amount"/></th>
                                            <th><xms:localization text="Scheduled"/></th>
                                            <th><xms:localization text="Scheduled collection timestamp"/></th>
                                            <th><xms:localization text="Collection Information"/></th>
                                            <th class="col_hidden th_shipment_reference"><xms:localization
                                                    text="Reference"/></th>
                                            <th class="col_hidden th_billing_party"><xms:localization
                                                    text="Billing Party"/></th>
                                            <th class="col_hidden th_sender_company"><xms:localization
                                                    text="Sender Company"/></th>
                                            <th class="col_hidden th_sender_contact"><xms:localization
                                                    text="Sender Contact"/></th>
                                            <th class="col_hidden th_sender_location"><xms:localization
                                                    text="Sender Location"/></th>
                                            <th class="col_hidden th_receiver_company"><xms:localization
                                                    text="Receiver Company"/></th>
                                            <th class="col_hidden th_receiver_contact"><xms:localization
                                                    text="Receiver Contact"/></th>
                                            <th><xms:localization text="Destination"/></th>
                                            <th><xms:localization text="Dest. Country"/></th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <td colspan="26"><xms:localization text="No record to view"/> ...</td>
                                        </tr>

                                        </tbody>
                                    </table>
                                </s:else>
                            </div>
                        </div>
                        <button class="btn s33 s44 multivoid" type="button" onclick="javascript:proceedToVoid();">
                            <xms:localization text="Proceed to Void"/>
                        </button>
                        <button class="btn s33 s44 multivoid" type="button" onclick="javascript:proceedToUpate();">
                            <xms:localization text="Proceed to Update"/>
                        </button>
                        <button class="btn s33 s44 multivoid" type="button" id="cancel_multivoid">
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
                                                   onclick="javascript:showOption();"> &nbsp; <xms:localization
                                            text="Show Report Options"/>
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-10" id="button_actions">

                        <button class="btn s33 s44" type="button" onclick="javascript:reshipHistory();">
                            <xms:localization text="Re-Ship Same Package"/>
                        </button>
                        <button class="btn s33 s44" type="button" onclick="javascript:viewDetail();">
                            <xms:localization text="View Details"/>
                        </button>
                        <button class="btn s33 s44" type="button" onclick="javascript:viewAirbill();">
                            <xms:localization text="View Airbill"/>
                        </button>
                        <button class="btn s33 s44" type="button" onclick="javascript:viewThermalLabel()">
                            <xms:localization text="Thermal Label"/>
                        </button>
                        <button class="btn s33 s44" type="button" id="btn_viewcommercial"
                                onclick="javascript:viewCommercialInvoice()">
                            <xms:localization text="View Commercial Invoice"/>
                        </button>
                        <button class="btn s33 s44" type="button" id="btn_packing_list">
                            <xms:localization text="View Packing List"/>
                        </button>
                        <button class="btn s33 s44" type="button" onclick="javascript:sendAirbill();">
                            <xms:localization text="Send Airbill"/>
                        </button>
                        <button class="btn s33 s44" type="button" id="btn_schedule_collection"
                                onclick="javascript:scheduleCollection();">
                            <xms:localization text="Schedule Collection"/>
                        </button>
                        <button class="btn s33 s44" type="button" id="btn_mo_schedule_collection"
                                onclick="javascript:moScheduleCollection();">
                            <xms:localization text="Modify Schedule Collection"/>
                        </button>
                        <button class="btn s33 s44" type="button" id="btn_ca_schedule_collection"
                                onclick="javascript:caScheduleCollection();">
                            <xms:localization text="Cancel Collection"/>
                        </button>
                        <button class="btn s33 s44" type="submit">
                            <xms:localization text="Shipping Label"/>
                        </button>
                        <button class="btn s33 s44" type="button" onclick="javascript:trackAirbill();">
                            <xms:localization text="Track"/>
                        </button>
                        <button class="btn s33 s44" type="button">
                            <xms:localization text="Tracking Label"/>
                        </button>
                        <button class="btn s33 s44" type="button" id="btn_void" onclick="javascript:voidAirbill();">
                            <xms:localization text="Void"/>
                        </button>
                        <button class="btn s33 s44" type="button" onclick="javascript:showNote();">
                            <xms:localization text="Note"/>
                        </button>
                        <button class="btn s33 s44" type="button" id="addnote-link" onclick="javascript:addNote();">
                            <xms:localization text="Add Note"/>
                        </button>
                        <button class="btn s33 s44" type="button" id="multi_void" onclick="javascript:multiVoid();">
                            <xms:localization text="Multi Void"/>
                        </button>
                        <button class="btn s33 s44" type="button" onclick="javascript:updateCollectionNo();"
                                id="update_collection">
                            <xms:localization text="Update Collection No"/>
                        </button>

                        <!-- Note -->

                        <!-- End AddNote -->
                    </div>

                    <div class="col-lg-12" id="div_list_chk_option">
                        <div class="form-group text-left s99">
                            <div class="form-group">
                                <label class="checkbox-inline"> <input type="checkbox" class="cb1" id="chk_option_all"
                                                                       onclick="javascript:optionShowAll();"> &nbsp;
                                    <xms:localization text="All"/>
                                </label> <label class="checkbox-inline"> <input type="checkbox" name="th_package"
                                                                                class="cb1"
                                                                                onclick="javascript:optionShowCol();">
                                &nbsp; <xms:localization text="Package Type"/>
                            </label> <label class="checkbox-inline"> <input type="checkbox" name="th_dimensions"
                                                                            class="cb1"
                                                                            onclick="javascript:optionShowCol();">
                                &nbsp; <xms:localization text="Dimensions"/>
                            </label> <label class="checkbox-inline"> <input type="checkbox" name="th_shipment_reference"
                                                                            class="cb1"
                                                                            onclick="javascript:optionShowCol();">
                                &nbsp; <xms:localization text="Shipment Reference"/>
                            </label> <label class="checkbox-inline"> <input type="checkbox" name="th_billing_party"
                                                                            class="cb1"
                                                                            onclick="javascript:optionShowCol();">
                                &nbsp; <xms:localization text="Billing Party"/>
                            </label> <label class="checkbox-inline"> <input type="checkbox" name="th_sender_company"
                                                                            class="cb1"
                                                                            onclick="javascript:optionShowCol();">
                                &nbsp; <xms:localization text="Sender Company"/>
                            </label> <label class="checkbox-inline"> <input type="checkbox" name="th_sender_contact"
                                                                            class="cb1"
                                                                            onclick="javascript:optionShowCol();">
                                &nbsp; <xms:localization text="Sender Contact"/>
                            </label> <label class="checkbox-inline"> <input type="checkbox" name="th_sender_location"
                                                                            class="cb1"
                                                                            onclick="javascript:optionShowCol();">
                                &nbsp; <xms:localization text="Sender Localtion"/>
                            </label> <label class="checkbox-inline"> <input type="checkbox" name="th_receiver_company"
                                                                            class="cb1"
                                                                            onclick="javascript:optionShowCol();">
                                &nbsp; <xms:localization text="Receiver Company"/>
                            </label> <label class="checkbox-inline"> <input type="checkbox" name="th_receiver_contact"
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
</s:form>
<div id="note-dialog"></div>
<div id="send-airbill-dialog"></div>
<div id="history_detaill"></div>
<div class="remember_me">
    <ul style="margin-left: 5px; margin-top: 10px; list-style: none;">
        <li style="color: #F00;"><s:fielderror/></li>
        <li style="color: #F00;"><s:actionerror/></li>
    </ul>
</div>
<!--END CONTENT-->
<script type="text/javascript">
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
        doPostDataNonError('manage_customers_webship_history_search.ix?reqType=json', 'form_history', '', 'div_history_data');
    }

    function changePage(page) {
        $("#hid_page").val(1);
        doPostDataNonError('manage_customers_webship_history_search.ix?reqType=json', 'form_history', '', 'div_history_data');

    }
    function sendAirbill() {
        var shipment_id = $("#hid_shipment_id").val();
        var emails = $('#list_email').val();
        loadDialogFieldError("manage_customers_webship_send_airbill.ix?reqType=json", {
            'shipmentId': shipment_id,
            'sendAirlbillHistoryFillterModel.templateEmail': 'Send Airbill Label'
        }, "form-send-airbill", "Save", "Close", "send-airbill-dialog", "Send Airbill", "error_send_airbill");
    }
    function showNote() {
        var shipment_id = $("#hid_shipment_id").val();
        loadDetailDialog("manage_customers_webship_history_shipment_note.ix?reqType=json", {
            'shipmentNoteFilterModel.shipmentId': shipment_id,
            'shipmentNoteFilterModel.sizeRecord': 10
        }, "Note", "Close", "note-dialog");
    }

    function addNote() {
        var shipment_id = $("#hid_shipment_id").val();
        var data = {
            'shipmentNoteFilterModel.shipmentId': shipment_id
        };
        loadDialog("manage_customers_webship_history_shipment_note_add.ix?reqType=json", data, "form-add-note", "Add", "Cancel", "note-dialog", "Add Note", "note-dialog");
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
    }

    function viewThermalLabel() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_view_airbill.ix?shipmentId=" + shipment_id + "&small=1", '_blank');
        win.focus();
    }

    function updateCollectionNo() {
        doPostDataNonError('manage_customers_webship_history_search.ix?reqType=json', 'form_history', '', 'div_history_data');
    }

    function trackAirbill() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_history_tracking.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function optionShowCol() {
        $("#div_list_chk_option").find("input").each(function () {
            var index = $("#div_list_chk_option").find("input").index(this) - 1;
            var colToHide = $(".datatable1").find("." + $(this).attr("name"));
            if ($(this).is(":checked")) {
                $(this).attr('checked', true);
                colToHide.show("slow");
            } else {
                $(this).attr('checked', false);
                colToHide.hide("slow");
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
            var messageVoid = '<xms:localization text="Are You Sure Want To Void Total "/>' + totalSm;
            loadConfirmDialog("manage_customers_webship_history_proceed_to_void.ix?reqType=json", data, messageVoid, "div_proceed_tovoid", updateCollectionNo(), "OK", "Cancel", "Proceed To Void");
        } else {
            alert('<xms:localization text="Please select shipment(s) to void."/>');
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
            var contentDialog = '<p><xms:localization text="New collection no:"/><form id="form_updateCollection"><input name="collectionNoNew" id="collection_no_new" class="form-control" /></form></p>';
            var messageUpdate = "Update Collection of  Total " + totalSm + contentDialog;
            var dataUpdate = {
                'listShipmentId': list_sm
            };
            loadConfirmDialog("manage_customers_webship_history_proceed_update_collection.ix?reqType=json", dataUpdate, messageUpdate, "div_proceed_toupdate", updateCollectionNo(), "OK", "Cancel", "Proceed To Update Collection", "form_updateCollection");
        } else {
            alert('<xms:localization text="Please select shipment(s) to void." />');
        }

    }

    function scheduleCollection(shipmentId) {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_history_schedule_collection.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();

    }

    function moScheduleCollection(shipmentId) {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_history_schedule_collection.ix?shipmentId=" + shipment_id + "&action=update", '_blank');
        win.focus();

    }

    function reshipHistory() {
        var shipment_id = $("#hid_shipment_id").val();
        window.location = "webship.ix?shipmentId=" + shipment_id;
    }

    function viewAirbill() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_view_airbill.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function viewCommercialInvoice() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_view_commercial_invoice.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function caScheduleCollection() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_history_cancel_collection.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function viewDetail() {
        var shipment_id = $("#hid_shipment_id").val();
        var data = {
            'shipmentId': shipment_id
        }
        loadDialogWithFunctionCallBack("manage_customers_webship_history_detail.ix?reqType=json", data, exportDetail, "Export to PDF", "Close", "history_detaill", "Shipment Detail", "");

    }

    function exportDetail() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_history_export_detail.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function voidAirbill() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_history_void_airbill.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function exportToPdf() {
        var data = $("#form_history").serialize();
        window.open("manage_customers_webship_history_export_pdf.ix?" + data, '_self');
    }

    function exportDetail() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("manage_customers_webship_history_export_detail.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function exportToExcel() {
        var data = $("#form_history").serialize();
        window.open("manage_customers_webship_history_export_excel.ix?" + data, '_self');
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
        doPostDataNonError('manage_customers_webship_history_search.ix?reqType=json', 'form_history', '', 'div_history_data');
    });

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
        } else {
            $("#button_actions").find("button").attr("disabled", true);
            $("#multi_void, #update_collection").attr("disabled", false);
        }

    });

    $('table#datatable1 tbody tr').dblclick(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var shipmentId = $(this).find('#shipmentId').val();
        $("#hid_shipment_id").val(shipmentId);
        viewDetail();
    });
</script>