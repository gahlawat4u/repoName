<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div class="col-lg-12 pd0 mg0 " style="min-width: 10px !important; overflow: auto">
    <s:hidden value="" id="hid_shipment_id"></s:hidden>
    <input type="hidden" id="hid_history_records" value="<s:property value='historyModels.records.size' />"/>
    <!-- Begin Paging Index History -->
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
                <th class="col_hidden th_dimensions"><xms:localization text="Dimension"/></th>
                <s:if test="quotable">
                    <th><xms:localization text="Quoted"/></th>
                </s:if>
                    <%-- <th><xms:localization text="Insured Amount" /></th> --%>
                <th><xms:localization text="Scheduled"/></th>
                <th><xms:localization text="Scheduled collection timestamp"/></th>
                <th><xms:localization text="Collection Information"/></th>
                <th class="col_hidden th_shipment_reference"><xms:localization text="Reference"/></th>
                <th class="col_hidden th_billing_party"><xms:localization text="Billing Party"/></th>
                <th class="col_hidden th_sender_company"><xms:localization text="Sender Company"/></th>
                <th class="col_hidden th_sender_contact"><xms:localization text="Sender Contact"/></th>
                <th class="col_hidden th_sender_location"><xms:localization text="Sender Location"/></th>
                <th class="col_hidden th_receiver_company"><xms:localization text="Receiver Company"/></th>
                <th class="col_hidden th_receiver_contact"><xms:localization text="Receiver Contact"/></th>
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
                    <td><s:property value="serviceName"/> <s:hidden value="%{shipmentId}" id="shipmentId"/> <s:hidden
                            value="%{commercialInvoiceId}" id="commercialInvoiceId"/> <s:hidden value="%{schId}"
                                                                                                id="schedule_collection_id"/>
                        <s:hidden value="%{serviceId}" id="service_id"/> <s:hidden value="%{packingList}"
                                                                                   id="packingList"/></td>
                    <td class="td_void_status"><s:property value="voidStatus"/></td>
                    <td id="td_airbillNumber"><s:property value="airbillNumber"/></td>
                    <td><s:property value="createDate"/></td>
                    <td><s:property value="timeStamp"/></td>
                    <td><s:property value="shipDate"/></td>
                    <td><s:property value="noOfPieces"/></td>
                    <td><s:property value="shipmentType"/></td>
                    <td class="col_hidden th_package"><s:property value="packageName"/></td>
                    <td><s:property value="weight"/></td>
                    <td class="col_hidden th_dimensions"><s:property value="dimensions"/></td>
                    <s:if test="quotable">
                        <td><s:if test="total != 0">
                            <s:property value="total"/>
                        </s:if> <s:else>
                            <xms:localization text="TBA"/>
                        </s:else></td>
                    </s:if>
                        <%-- <td><s:property value="withInsurance" /></td> --%>
                    <td class="td_schedule"><s:property value="schedule"/></td>
                    <td><s:property value="schcollTimeStamp"/></td>
                    <td><s:property value="confirmationNo"/></td>
                    <td class="col_hidden th_shipment_reference"><s:property value="reference"/></td>
                    <td class="col_hidden th_billing_party"><s:property value="billingParty"/></td>
                    <td class="col_hidden th_sender_company"><s:property value="senderCompany"/></td>
                    <td class="col_hidden th_sender_contact"><s:property value="senderName"/></td>
                    <td class="col_hidden th_sender_location"><s:property value="senderLocation"/></td>
                    <td class="col_hidden th_receiver_company"><s:property value="reciverCompany"/></td>
                    <td class="col_hidden th_receiver_contact"><s:property value="reciverContact"/></td>
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
                <th class="col_hidden th_dimensions"><xms:localization text="Dimension"/></th>
                <s:if test="quotable">
                    <th><xms:localization text="Quoted"/></th>
                </s:if>
                <th><xms:localization text="Insured Amount"/></th>
                <th><xms:localization text="Scheduled"/></th>
                <th><xms:localization text="Scheduled collection timestamp"/></th>
                <th><xms:localization text="Collection Information"/></th>
                <th class="col_hidden th_shipment_reference"><xms:localization text="Reference"/></th>
                <th class="col_hidden th_billing_party"><xms:localization text="Billing Party"/></th>
                <th class="col_hidden th_sender_company"><xms:localization text="Sender Company"/></th>
                <th class="col_hidden th_sender_contact"><xms:localization text="Sender Contact"/></th>
                <th class="col_hidden th_sender_location"><xms:localization text="Sender Location"/></th>
                <th class="col_hidden th_receiver_company"><xms:localization text="Receiver Company"/></th>
                <th class="col_hidden th_receiver_contact"><xms:localization text="Receiver Contact"/></th>
                <th><xms:localization text="Destination"/></th>
                <th><xms:localization text="Dest. Country"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="quotable">
                <tr>
                    <td colspan="26"><xms:localization text="No record to view"/> ...</td>
                </tr>
            </s:if>
            <s:else>
                <tr>
                    <td colspan="25"><xms:localization text="No record to view"/> ...</td>
                </tr>
            </s:else>
            </tbody>
        </table>
    </s:else>
</div>
<s:if test="historyModels.records.size != 0">
    <!-- Begin Paging Index History -->
    <s:if test="historyModels.totalPage != 1">
        <div class="text-right">
            <ul class="pagination mts mbs" data-hover="">
                <s:if test="!historyModels.hasPrev()">
                    <li class="disabled"><a><xms:localization text="Previous"/></a></li>
                </s:if>
                <s:else>
                    <li>
                        <a href="javascript:changePage(<s:property value="%{historyModels.currentPage - 1}"/>)"><xms:localization
                                text="Previous"/></a></li>
                </s:else>
                <s:iterator value="historyModels.pageRange" status="count">
                    <s:if test="%{historyModels.pageRange[#count.index] == historyModels.currentPage}">
                        <li class="active"><a><s:property value="historyModels.currentPage"/></a></li>
                    </s:if>
                    <s:else>
                        <li><a href="javascript:changePage(<s:property/>);"><s:property/></a></li>
                    </s:else>
                </s:iterator>
                <s:if test="!historyModels.hasNext()">
                    <li class="disabled"><a href="#"><xms:localization text="Next"/></a></li>
                </s:if>
                <s:else>
                    <li>
                        <a href="javascript:changePage(<s:property value="%{historyModels.currentPage + 1}"/>)"><xms:localization
                                text="Next"/></a></li>
                </s:else>
            </ul>
        </div>
    </s:if>
</s:if>
<!-- // End Paging Index History -->
<script type="text/javascript">
    $(document).ready(function () {
        tnt_manifest_status = $("#tnt_manifest_status").val();

        toll_priority_manifest_status = $("#toll_priority_manifest_status").val();
        toll_ipec_manifest_status = $("#toll_ipec_manifest_status").val();
        if(tnt_manifest_status!=null && tnt_manifest_status!=undefined)
        {
            tnt_manifest_status = parseInt(tnt_manifest_status);
        }

        if(toll_priority_manifest_status!=null && toll_priority_manifest_status!=undefined)
        {
            toll_priority_manifest_status = parseInt(toll_priority_manifest_status);
        }

        if(toll_ipec_manifest_status!=null && toll_ipec_manifest_status!=undefined)
        {
            toll_ipec_manifest_status = parseInt(toll_ipec_manifest_status);
        }
        if(tnt_manifest_status>0)
        {
            new_aae_manifest = true;
        }else
        {
            new_aae_manifest = false;
        }
        if(toll_priority_manifest_status>0)
        {
            toll_view_gen_manifest = true;
        }

        if(toll_ipec_manifest_status>0)
        {
            toll_ipec_view_gen_manifest = true;
        }

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

        if ($("#sel_total_date").val() == 5) {
            $("#div_range_date").show();
        }
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
        loadDialogAddNote("history_shipment_note_add.ix?reqType=json", data, "form-add-note", "Add", "Cancel", "add-note-dialog", "Add Note", "note-dialog");
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
        loadDialogWithFunctionCallBack("history_detail.ix?reqType=json", data, exportDetail, "", "Close", "history_detaill", "Shipment Detail", "");

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
        window.open("history_export_pdf.ix?" + data, '_self');
    }

    function exportDetail() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("history_export_detail.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function exportToExcel() {
        var data = $("#form_history").serialize();
        window.open("history_export_excel.ix?" + data, '_self');
    }

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
                if ($(this).find("td.td_schedule").html() == "YES") {
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
        if (serviceId == 54) {
            $("#btn_tnt_connote").show();
            $("#btn_tnt_connote").attr("disabled", false);
        } else {
            $("#btn_tnt_connote").hide();
            $("#btn_tnt_connote").attr("disabled", true);
        }
    });

    $('table#datatable1 tbody tr').dblclick(function () {
        $(this).addClass('selected-row').siblings().removeClass('selected-row');
        var shipmentId = $(this).find('#shipmentId').val();
        $("#hid_shipment_id").val(shipmentId);
        viewDetail();
    });

    function checkManifestButton(service_id, strvoid) {
        document.getElementById("btngenerate_manifest").style.display = "none";
        document.getElementById("btntollprioritygenerate_manifest").style.display = "none";
        document.getElementById("btntollipecgenerate_manifest").style.display = "none";
        if (service_id == 50 || service_id == 2 || (service_id == 3 && strvoid != "YES")) {
            $("#btnviewmanifest").attr("disabled", false);
            if(new_aae_manifest==true){
                document.getElementById("btngenerate_manifest").style.display = "block";
            }
        } else if (service_id == 52) {
            $("#btnviewmanifest").attr("disabled", false);
//             if(new_toll_manifest==true){
            $("#toll_btngenerate_manifest").attr("disabled", false);
            if(toll_view_gen_manifest)
            {
                document.getElementById("btntollprioritygenerate_manifest").style.display = "block";
                $("#btntollprioritygenerate_manifest").attr("disabled", false);
            }else
            {
                document.getElementById("btntollprioritygenerate_manifest").style.display = "none";
            }
//             }
        } else if (service_id == 59) {
            $("#btnviewmanifest").attr("disabled", false);
//             if(new_toll_manifest==true){
            $("#toll_ipec_btngenerate_manifest").attr("disabled", false);
            if(toll_ipec_view_gen_manifest)
            {
                document.getElementById("btntollipecgenerate_manifest").style.display = "block";
                $("#btntollipecgenerate_manifest").attr("disabled", false);
            }else
            {
                document.getElementById("btntollipecgenerate_manifest").style.display = "none";
            }
        } else if (service_id == 54) {
            $("#btnviewmanifest").attr("disabled", false);
//             if(new_tnt_int_manifest==true){
            $("#btngenerate_manifest").attr("disabled", true);
//             }
        } else if (service_id == 72) {
            $("#btnviewmanifest").attr("disabled", false);
        } else {
            $("#btnviewmanifest").attr("disabled", true);
        }
    }
    function viewManifest() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("view_manifest.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }

    function viewTntConnote() {
        var shipment_id = $("#hid_shipment_id").val();
        var win = window.open("view_tnt_connote.ix?shipmentId=" + shipment_id, '_blank');
        win.focus();
    }
</script>