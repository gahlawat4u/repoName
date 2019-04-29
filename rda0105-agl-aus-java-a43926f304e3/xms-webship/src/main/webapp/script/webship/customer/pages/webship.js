/**
 * Dato solutions custom javascript for webship page
 */
$(document).ready(function () {
    isClear = false;
    var dateNow = new Date();
    $("#shipment-date-input").datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'dd-mm-yyyy',
        startDate: "2015-08-03 10:00"
    });
    $("#shipment-date-input").datetimepicker("update", new Date());
});
function loadDialogQuote(loadAction, postAction, dialogId, formId, formDialogId, btnSave, btnCancel) {
    loadingDialog.dialog("open");
    var formData = $("#" + formId).serialize();
    $.post(loadAction, formData, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            dialog.html(res.content);
            dialog.dialog("open");
        } else if (res.errorCode == "FIELD_ERROR") {
            dialog.html(res.content);
            dialog.dialog("open");
        } else if (res.errorCode == "ACTION_ERROR") {
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        } else {
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
    var buttons = {};

    buttons[btnSave] = function () {
        var postData = $("#" + formDialogId).serialize();
        $.post(postAction, postData, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                alertDialog.html("Saved successfully.");
                alertDialog.dialog("open");
            } else if (res.errorCode == "FIELD_ERROR") {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                alertDialog.html("System Error: " + res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("System internal error, please contact administrator.");
            alertDialog.dialog("open");
        });
    };
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    };
    var dialog = $("#" + dialogId).dialog({
        autoOpen: false,
        modal: true,
        show: {
            effect: 'fade',
            duration: 300
        },
        buttons: buttons,
        width: "500px",
        position: {
            my: "center",
            at: "body",
            of: window,
            collision: "fit"
        }
    });
}

function loadDialogQuoteWithSessionTimeout(loadAction, postAction, dialogId, formId, formDialogId, btnSave, btnCancel, timeoutId, btnOk) {
    loadingDialog.dialog("open");
    var formData = $("#" + formId).serialize();
    $.post(loadAction, formData, function (res) {
        loadingDialog.dialog("close");
        // Check session timeout.
        if (res.errorType == "SESSION_TIMEOUT") {
            var tButtons = {};
            tButtons[btnOk] = function () {
                window.location = "login.ix";
            };
            var tDialog = $("#" + timeoutId).dialog({
                autoOpen: false,
                modal: true,
                show: {
                    effect: 'fade',
                    duration: 300
                },
                close: function (e) {
                    window.location = "login.ix";
                },
                buttons: tButtons,
                width: "320px",
                position: {
                    my: "center",
                    at: "body",
                    of: window,
                    collision: "fit"
                }
            });
            tDialog.html(res.errorMsg);
            tDialog.dialog("open");
        } else {
            if (res.errorCode == "SUCCESS") {
                dialog.html(res.content);
                dialog.dialog("open");
            } else if (res.errorCode == "FIELD_ERROR") {
                dialog.html(res.content);
                dialog.dialog("open");
            } else if (res.errorCode == "ACTION_ERROR") {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
    var buttons = {};

    buttons[btnSave] = function () {
        var postData = $("#" + formDialogId).serialize();
        $.post(postAction, postData, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                alertDialog.html("Saved successfully.");
                alertDialog.dialog("open");
            } else if (res.errorCode == "FIELD_ERROR") {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                alertDialog.html("System Error: " + res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("System internal error, please contact administrator.");
            alertDialog.dialog("open");
        });
    };
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    };
    var dialog = $("#" + dialogId).dialog({
        autoOpen: false,
        modal: true,
        show: {
            effect: 'fade',
            duration: 300
        },
        buttons: buttons,
        width: "500px",
        position: {
            my: "center",
            at: "body",
            of: window,
            collision: "fit"
        }
    });
}

function loadDialogResultBooking(actionPost, captionList) {
    var data = $("#form_booking").serialize();
    loadingDialog.dialog("open");
    $.post(actionPost, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            // Define buttons's dialog result.
            var buttons = {};
            for (i = 0; i < captionList.length; i++) {
                if (captionList[i] != "") {
                    switch (i) {
                        case 0:
                            buttons[captionList[i]] = function () {
                                webshipViewAirbill();
                            };
                            break;
                        case 1:
                            buttons[captionList[i]] = function () {
                                webshipThermalLabel();
                            };
                            break;
                        case 2:
                            buttons[captionList[i]] = function () {
                                webshipCommercialInvoice();
                            };
                            break;
                        case 3:
                            buttons[captionList[i]] = function () {
                                webshipPackingList();
                            };
                            break;
                        case 4:
                            buttons[captionList[i]] = function () {
                                webshipScheduleCollection();
                            };
                            break;
                        case 5:
                            buttons[captionList[i]] = function () {
                                webshipSendAirbill();
                            };
                            break;
                        case 6:
                            buttons[captionList[i]] = function () {
                                webshipNewShipment();
                            };
                            break;
                        case 7:
                            buttons[captionList[i]] = function () {
                                webshipReShipSamePackage();
                            };
                            break;
                        case 8:
                            buttons[captionList[i]] = function () {
                                webshipTNTConnote();
                            };
                            break;
                        case 9:
                            buttons[captionList[i]] = function () {
                                webshipReceiptShipment();
                            };
                            break;
                        case 10:
                            buttons[captionList[i]] = function () {
                                webshipViewManifest();
                            };
                            break;
                    }
                }
            }
            // Define dialog result.
            var dialog = $("#result_booking").dialog({
                autoOpen: false,
                modal: true,
                show: {
                    effect: 'fade',
                    duration: 300
                },
                buttons: buttons,
                width: "auto",
                position: {
                    my: "center",
                    at: "body",
                    of: window,
                    collision: "fit"
                }
            }).on('dialogclose', function(event) {
                window.location = "webship.ix";
            });
            // Show dialog.
            dialog.html(res.content);
            dialog.dialog("open");
        } else if (res.errorCode == "FIELD_ERROR") {
            alertDialog.html(res.content);
            alertDialog.dialog("open");
        } else {
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function webshipViewAirbill() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    var url = "view_airbill.ix?shipmentId=" + shipmentId;
    var win = window.open(url, '_blank');
    win.focus();
}

function webshipThermalLabel() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    var url = "view_thermal_label.ix?shipmentId=" + shipmentId;
    var win = window.open(url, '_blank');
    win.focus();
}

function webshipCommercialInvoice() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    var url = "view_commercial_invoice.ix?shipmentId=" + shipmentId;
    var win = window.open(url, '_blank');
    win.focus();
}

function webshipPackingList() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    var url = "view_packing_list.ix?shipmentId=" + shipmentId;
    var win = window.open(url, '_blank');
    win.focus();
}

function webshipScheduleCollection() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    var url = "schedule_collection_create.ix?shipmentId=" + shipmentId;
    var win = window.open(url, '_blank');
    win.focus();
}

function webshipTNTConnote() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    var url = "view_tnt_connote.ix?shipmentId=" + shipmentId;
    var win = window.open(url, '_blank');
    win.focus();
}

function webshipSendAirbill() {
    var data = {
        'shipmentId': $("body").find("input#booking_result_shipmentid").val(),
        'sendAirlbillHistoryFillterModel.templateEmail': 'Send Airbill Label'
    };
    loadingDialog.dialog("open");
    $.post("send_airbill.ix?reqType=json", data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            // Define dialog buttons.
            var buttons = {};
            buttons["Send"] = function () {
                loadingDialog.dialog("open");
                var dataForm = $("#form-send-airbill").serialize();
                $.post("send_airbill.ix?reqType=json", dataForm, function (res) {
                    loadingDialog.dialog("close");
                    if (res.errorCode == "SUCCESS") {
                        $("#webship_booking_send_airbill_dialog").dialog("close");
                        var buttonSuccess = {};
                        buttonSuccess["Ok"] = function(){
                            webshipNewShipment();
                        };
                        notifyDialog("#webship_booking_send_airbill_dialog", "Send successfull.", buttonSuccess);
                    } else {
                        alertDialog.html(res.errorMsg);
                        alertDialog.dialog("open");
                    }
                }).fail(function () {
                    loadingDialog.dialog("close");
                    alertDialog.html("System internal error, please contact administrator.");
                    alertDialog.dialog("open");
                });
            };
            buttons["Close"] = function () {
                $(this).dialog("close");
            }
            // Define dialog.
            var dialog = $("#webship_booking_send_airbill_dialog").dialog({
                modal: true,
                autoOpen: false,
                width: "auto",
                buttons: buttons,
                show: {
                    effect: "fade",
                    duration: 300
                }
            });
            // Open dialog.
            dialog.html(res.content);
            dialog.dialog("open");
        } else {
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function notifyDialog(id, content, actions){
    var notifyDialog = $(id).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: actions,
        show: {
            effect: "fade",
            duration: 300
        }
    });
    notifyDialog.html(content);
    notifyDialog.dialog("open")
}

function webshipNewShipment() {
    window.location.replace("webship.ix");
}

function webshipReShipSamePackage() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    var url = "webship.ix?shipmentId=" + shipmentId;
    window.location.replace(url);
}

function webshipReceiptShipment() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    // window.location = "view_shipment_receipt.ix?shipmentId=" + shipmentId;
    var win = window.open("view_shipment_receipt.ix?shipmentId=" + shipmentId, '_blank');
    win.focus();
}

function webshipViewManifest() {
    var shipmentId = $("body").find("input#booking_result_shipmentid").val();
    var win = window.open("view_manifest.ix?shipmentId=" + shipmentId, '_blank');
    win.focus();
}

function doContinueBooking(action, continueDivId, webshipDivId, timeoutId, btnOk) {
    loadingDialog.dialog("open");
    var data = $(document).find('form#shipment-info-form').serialize();
    data += "&" + $(document).find('form#form_booking').serialize();
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        // Check session timeout.
        if (res.errorType == "SESSION_TIMEOUT") {
            var tButtons = {};
            tButtons[btnOk] = function () {
                window.location = "login.ix";
            };
            var tDialog = $("#" + timeoutId).dialog({
                autoOpen: false,
                modal: true,
                show: {
                    effect: 'fade',
                    duration: 300
                },
                close: function (e) {
                    window.location = "login.ix";
                },
                buttons: tButtons,
                width: "320px",
                position: {
                    my: "center",
                    at: "body",
                    of: window,
                    collision: "fit"
                }
            });
            tDialog.html(res.errorMsg);
            tDialog.dialog("open");
        } else {
            if (res.errorCode == "SUCCESS") {
                $("#" + webshipDivId).slideUp('slow');
                $('#' + continueDivId).html(res.content);
                $('#' + continueDivId).slideDown();
            } else if (res.errorCode == "FIELD_ERROR") {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}
function backToShipment() {
    $("#shipment-div").show("slow");
    $("#continue-booking-div").hide("slow");
    $("#hts_good_lookup_load").empty().remove();
    $("#hts_good_lookup_load").html("");
}
function loadAdditionalConfig() {
    var shipmentTypeId = $('#shipmentPage_shipmentTypeId').val();
    var serviceId = $("#shipmentPage_serviceId").val();
    doPostDataByParameters("webship_get_additional_config.ix?reqType=json", {
        "serviceId": serviceId,
        "shipmentTypeId": shipmentTypeId
    }, "", "additional-config-div", false);
}
function resetAddPiece(contentType) {
    var senderCountryId = $("#shipmentPage_senderAddress_country").val();
    var receiverCountryId = $("#shipmentPage_receiverAddress_country").val();
    if (typeof (contentType) == "undefined" || contentType == '') {
        contentType = $('#shipmentPage_packageId').val();
    }
    var packageId = $('#shipmentPage_packageId').val();
    var shipmentTypeId = $('#shipmentPage_shipmentTypeId').val();
    var data = {
        'packageId': packageId,
        'shipmentTypeId': shipmentTypeId,
        'contentType': contentType,
        "shipmentPage.senderAddress.country": senderCountryId,
        "shipmentPage.receiverAddress.country": receiverCountryId
    };
    $("#piece-table").find("tbody").find("tr").each(function () {
        $(this).find("td").find("input").each(function () {
            var name_piece = $(this).attr("name");
            var value_piece = $(this).val();
            data[name_piece] = value_piece;
        });
    });
    doPostDataByParameters("webship_reset_addpiece.ix?reqType=json", data, "", "reset-add-piece-div", false);
}
function changeCountry(type) {
    var senderCountryId = $("#shipmentPage_senderAddress_country").val();
    var receiverCountryId = $("#shipmentPage_receiverAddress_country").val();
    
    var data = {
        "shipmentPage.senderAddress.country": senderCountryId,
        "shipmentPage.receiverAddress.country": receiverCountryId
    };
    
   
    
    if(!isClear)
    {
        clearAddresses(type);
    }
    else
    {
        isClear =false;
    }
    loadState(type);
    doPostDataByParameters("webship_change_country.ix?reqType=json", data, "", "change-country-div", false);
}

function clearAddresses(type) {
    if (type == 'sender') {
        if (document.getElementById("shipment-info-form_shipmentPage_senderAddress_address") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_senderAddress_address").value = "";
        }
        if (document.getElementById("shipmentPage_senderAddress_address") != undefined) {
            document.getElementById("shipmentPage_senderAddress_address").value = "";
        }
        if (document.getElementById("shipment-info-form_shipmentPage_senderAddress_address2") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_senderAddress_address2").value = "";
        }
        if (document.getElementById("shipmentPage_senderAddress_address2") != undefined) {
            document.getElementById("shipmentPage_senderAddress_address2").value = "";
        }

        if (document.getElementById("shipment-info-form_shipmentPage_senderAddress_city") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_senderAddress_city").value = "";
        }

        if (document.getElementById("shipmentPage_senderAddress_city") != undefined) {
            document.getElementById("shipmentPage_senderAddress_city").value = "";
        }

        if (document.getElementById("shipmentPage_senderAddress_address2") != undefined) {
            document.getElementById("shipmentPage_senderAddress_address2").value = "";
        }

        if (document.getElementById("shipment-info-form_shipmentPage_senderAddress_postalCode") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_senderAddress_postalCode").value = "";
        }

        if (document.getElementById("shipmentPage_senderAddress_postalCode") != undefined) {
            document.getElementById("shipmentPage_senderAddress_postalCode").value = "";
        }

        if (document.getElementById("shipment-info-form_shipmentPage_senderAddress_state") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_senderAddress_state").value = "";
        }
        if (document.getElementById("shipmentPage_senderAddress_state") != undefined) {
            document.getElementById("shipmentPage_senderAddress_state").value = "";
        }
    } else if (type == 'receiver') {
        if (document.getElementById("shipment-info-form_shipmentPage_receiverAddress_address") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_receiverAddress_address").value = "";
        }
        if (document.getElementById("shipmentPage_receiverAddress_address") != undefined) {
            document.getElementById("shipmentPage_receiverAddress_address").value = "";
        }
        if (document.getElementById("shipment-info-form_shipmentPage_receiverAddress_address2") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_receiverAddress_address2").value = "";
        }
        if (document.getElementById("shipmentPage_receiverAddress_address2") != undefined) {
            document.getElementById("shipmentPage_receiverAddress_address2").value = "";
        }

        if (document.getElementById("shipment-info-form_shipmentPage_receiverAddress_city") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_receiverAddress_city").value = "";
        }

        if (document.getElementById("shipmentPage_receiverAddress_city") != undefined) {
            document.getElementById("shipmentPage_receiverAddress_city").value = "";
        }

        if (document.getElementById("shipment-info-form_shipmentPage_receiverAddress_postalCode") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_receiverAddress_postalCode").value = "";
        }

        if (document.getElementById("shipmentPage_receiverAddress_postalCode") != undefined) {
            document.getElementById("shipmentPage_receiverAddress_postalCode").value = "";
        }

        if (document.getElementById("shipment-info-form_shipmentPage_receiverAddress_state") != undefined) {
            document.getElementById("shipment-info-form_shipmentPage_receiverAddress_state").value = "";
        }
        if (document.getElementById("shipmentPage_receiverAddress_state") != undefined) {
            document.getElementById("shipmentPage_receiverAddress_state").value = "";
        }
    }
}

function loadState(type) {
    var divId = "div-sender-state";
    if (type == 'sender') {
        var data = {
            "changeCountryId": $("#shipmentPage_senderAddress_country").val(),
            "isSender": true,
            "senderState": $("#senderStateId").val()
        };
    } else {
        var data = {
            "changeCountryId": $("#shipmentPage_receiverAddress_country").val(),
            "isSender": false,
            "receiverState": $("#receiverStateId").val()
        };
        divId = "div-receiver-state";
    }
    doPostDataByParameters("webship_load_states.ix?reqType=json", data, "", divId, false);
}
function changeService(serviceId) {
    if (typeof (serviceId) == "undefined" || serviceId == '') {
        serviceId = $('#shipmentPage_serviceId').val();
    }
    var data = {
        'serviceId': serviceId,
        'prevPackageName': $('#shipmentPage_packageId option:selected').text()
    };
    $("#piece-table").find("tbody").find("tr").each(function () {
        $(this).find("td").find("input").each(function () {
            var name_piece = $(this).attr("name");
            var value_piece = $(this).val();
            data[name_piece] = value_piece;
        });
    });
    doPostDataByParameters("webship_change_service.ix?reqType=json", data, "", "change-service-div", false);
    loadAdditionalConfig(serviceId);
}
function changeShipmentType(shipmentTypeId, prevPackageName) {
    if (typeof (shipmentTypeId) == "undefined" || shipmentTypeId == '') {
        shipmentTypeId = $('#shipmentPage_shipmentTypeId').val();
    }
    var data = {
        'shipmentTypeId': shipmentTypeId,
        'prevPackageName': prevPackageName
    };
    $("#piece-table").find("tbody").find("tr").each(function () {
        $(this).find("td").find("input").each(function () {
            var name_piece = $(this).attr("name");
            var value_piece = $(this).val();
            data[name_piece] = value_piece;
        });
    });
    doPostDataByParameters("webship_change_shipment_type.ix?reqType=json", data, "", "change-shipmenttype-div", false);
}
function changePackage(packageId, shipmentTypeId) {
    var senderCountryId = $("#shipmentPage_senderAddress_country").val();
    var receiverCountryId = $("#shipmentPage_receiverAddress_country").val();
    if (typeof (packageId) == "undefined" || packageId == '') {
        packageId = $('#shipmentPage_packageId').val();
    }
    if (typeof (shipmentTypeId) == "undefined" || shipmentTypeId == '') {
        shipmentTypeId = $('#shipmentPage_shipmentTypeId').val();
    }
    var data = {
        'packageId': packageId,
        'shipmentTypeId': shipmentTypeId,
        "shipmentPage.senderAddress.country": senderCountryId,
        "shipmentPage.receiverAddress.country": receiverCountryId
    };
    $("#piece-table").find("tbody").find("tr").each(function () {
        $(this).find("td").find("input").each(function () {
            var name_piece = $(this).attr("name");
            var value_piece = $(this).val();
            data[name_piece] = value_piece;
        });
    });
    doPostDataByParameters("webship_change_package_type.ix?reqType=json", data, "", "change-package-div", false);
}
function continueBooking() {
    doContinueBooking("webship_continue_booking.ix?reqType=json", "continue-booking-div", "shipment-div", "session_time_out_dialog", "Ok");
}
function changeWeightUnit(unit) {
    if (unit == "KG") {
        $("#sel-dim-unit").val("CM");
        $("#weight-unit").text("Weight(kgs)*");
        $("#dimensions-unit").text("Dimensions(CM)");
    } else {
        $("#sel-dim-unit").val("IN");
        $("#weight-unit").text("Weight(lbs)*");
        $("#dimensions-unit").text("Dimensions(IN)");
    }
}
function changeDimUnit(unit) {
    if (unit == "CM") {
        $("#weight-unit").text("Weight(kgs)*");
        $("#sel-weight-unit").val("KG");
        $("#dimensions-unit").text("Dimensions(CM)");
    } else {
        $("#weight-unit").text("Weight(lbs)*");
        $("#sel-weight-unit").val("LB");
        $("#dimensions-unit").text("Dimensions(IN)");
    }
}
function searchSenderAddress(byCompany) {
    var company = $("input[name='shipmentPage.senderAddress.companyName']").val();
    var contact = $("input[name='shipmentPage.senderAddress.contactName']").val();
    var data;
    if (byCompany) {
        data = {
            "company": company,
            "fromWebship": true,
            "isSender": true
        };
    } else {
        data = {
            "contact": contact,
            "fromWebship": true,
            "isSender": true
        };
    }
    $.post("settings_address_default_search.ix?reqType=json", data, function (res) {
        if (res.errorCode == "SUCCESS") {
            if (byCompany) {
                $("#sender-address-by-company-search-result").html(res.content);
            } else {
                $("#sender-address-by-contact-search-result").html(res.content);
            }
        } else {
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}
function searchReceiverAddress(byCompany) {
    var company = $("input[name='shipmentPage.receiverAddress.companyName']").val();
    var contact = $("input[name='shipmentPage.receiverAddress.contactName']").val();
    var data;
    if (byCompany) {
        data = {
            "company": company,
            "fromWebship": true,
            "isSender": false
        };
    } else {
        data = {
            "contact": contact,
            "fromWebship": true,
            "isSender": false
        };
    }
    $.post("settings_address_default_search.ix?reqType=json", data, function (res) {
        if (res.errorCode == "SUCCESS") {
            if (byCompany) {
                $("#receiver-address-by-company-search-result").html(res.content);
            } else {
                $("#receiver-address-by-contact-search-result").html(res.content);
            }
        } else {
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}
function onAddressClick(addressId, isSender) {
    isClear = true;
    var data = {
        "addressId": addressId,
        "isSender": isSender
    };
    $.post("webship_load_address.ix?reqType=json", data, function (res) {
        if (res.errorCode == "SUCCESS") {
            if (isSender) {
                $("#sender-address-box").html(res.content);
            } else {
                $("#receiver-address-box").html(res.content);
            }
        } else {
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}
function searchCity(isSender, byName) {
    var searchData = "";
    var resultDiv = "";
    if (isSender) {
        if (byName) {
            searchData = {
                cityName: $("input[name='shipmentPage.senderAddress.city']").val(),
                countryId: $("#shipmentPage_senderAddress_country option:selected").val(),
                isSender: true
            };
        } else {
            searchData = {
                postalCode: $("input[name='shipmentPage.senderAddress.postalCode']").val(),
                countryId: $("#shipmentPage_senderAddress_country option:selected").val(),
                isSender: true
            };
        }
        resultDiv = "#sender-city-search";
    } else {
        if (byName) {
            searchData = {
                cityName: $("input[name='shipmentPage.receiverAddress.city']").val(),
                countryId: $("#shipmentPage_receiverAddress_country option:selected").val(),
                isSender: false
            };
        } else {
            searchData = {
                postalCode: $("input[name='shipmentPage.receiverAddress.postalCode']").val(),
                countryId: $("#shipmentPage_receiverAddress_country option:selected").val(),
                
                isSender: false
            };
        }
        resultDiv = "#receiver-city-search";
    }
    $.post("webship_search_city.ix?reqType=json", searchData, function (res) {
        if (res.errorCode == "SUCCESS") {
            $(resultDiv).html(res.content);
        } else {
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}
function onCityClick(obj, isSender) {
    var cityName = $(obj).find("div[data-cityName]").html();
    cityName = cityName.trim();
    var postalCode = $(obj).find("div[data-postalCode]").html();
    postalCode = postalCode.trim();
    var stateCode = $(obj).find("div[data-stateCode]").html();
    stateCode = stateCode.trim();
    if (isSender) {
        $("input[name='shipmentPage.senderAddress.city']").val(cityName);
        $("input[name='shipmentPage.senderAddress.postalCode']").val(postalCode);
        $("input[name='shipmentPage.senderAddress.state']").val(stateCode);
    } else {
        $("input[name='shipmentPage.receiverAddress.city']").val(cityName);
        $("input[name='shipmentPage.receiverAddress.postalCode']").val(postalCode);
        $("input[name='shipmentPage.receiverAddress.state']").val(stateCode);
    }
}