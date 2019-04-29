var div_show = "#Airbill-tab";
var div_show_id = "Airbill-tab";
var status = "1";
var pageSize = "25";
var page = 0;
// Load default page
var fieldList = ["", "airbillNumber", "adjustmentId", "serviceName", "shipmentDate", "deliveryDate", "createDate", "importDate", "adjustmentType", "customerAmount", "note"];
$(document).ready(function () {
    changePageSize();
});

function doPostDataCarrierAdjustments(action, data, message, divId, loading, errorPopup, callbackFunction) {
    loading = typeof loading !== "undefined" ? loading : true;
    errorPopup = typeof errorPopup !== "undefined" ? errorPopup : false;
    if (loading) {
        loadingDialog.dialog("open");
    }
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (divId != '') {
                if (typeof (callbackFunction) == "function") {
                    callbackFunction();
                }
            }
            if (message != '') {
                messageDialog.html(message);
                messageDialog.dialog("open");
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            if (errorPopup) {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                if (divId != '') {
                    $('#' + divId).html(res.content);
                } else {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
            }
        } else {
            alertDialog.html("Error: " + res.errorMsg);
            alertDialog.dialog("open");
        }

    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function reload_page() {
    // window.location.reload();
    pageSize = $("#recordSize_" + div_show_id).val();
    page = $("#current_page_" + div_show_id).val();
    var data = {
        'manageAdjustmentPageModel.status': status,
        'manageAdjustmentPageModel.recordSize': pageSize,
        'manageAdjustmentPageModel.page': page,
        'orderField': $("#orderField").val(),
        'orderType': $("#orderType").val(),
        'tableId': div_show_id
    };
    doPostDataByParameters("carrier_adjustments_get_data.ix?reqType=json", data, "", div_show_id + "-table");
}
$("#tbl_static").find("input").attr("disabled", "disabled");
// accept sumitted request
function acceptSubmittedRequest() {
    var a = document.getElementsByName('chk_airbill[]');
    var list_sm = "0";
    var len = a.length;
    for (i = 0; i < len; i++) {
        if (a[i].checked == true) {
            list_sm = list_sm + "|" + a[i].value;
        }
    }
    var data = {
        'listAdjId': list_sm
    };
    doPostDataCarrierAdjustments("carrier_adjustments_submitted.ix?reqType=json", data, "", div_show_id + "-table", true, true, reload_page);

}
// reject sumitted request
function rejectSubmittedRequest() {
    var a = document.getElementsByName('chk_airbill[]');
    var list_sm = "0";
    var len = a.length;
    for (i = 0; i < len; i++) {
        if (a[i].checked == true) {
            list_sm = list_sm + "|" + a[i].value;
        }
    }
    var data = {
        'listAdjId': list_sm
    };
    doPostDataCarrierAdjustments("carrier_adjustments_submitted_re.ix?reqType=json", data, "", div_show_id + "-table", true, true, reload_page);
}

// accept Pending request
function acceptPendingRequest() {
    var a = document.getElementsByName('chk_airbill[]');
    var list_sm = "0";
    var len = a.length;
    for (i = 0; i < len; i++) {
        if (a[i].checked == true) {
            list_sm = list_sm + "|" + a[i].value;
        }
    }
    var data = {
        'listAdjId': list_sm
    };
    doPostDataCarrierAdjustments("carrier_adjustments_pending.ix?reqType=json", data, "", div_show_id + "-table", true, true, reload_page);
}
// reject Pending request
function rejectPendingRequest() {
    var a = document.getElementsByName('chk_airbill[]');
    var list_sm = "0";
    var len = a.length;
    for (i = 0; i < len; i++) {
        if (a[i].checked == true) {
            list_sm = list_sm + "|" + a[i].value;
        }
    }
    var data = {
        'listAdjId': list_sm
    };
    doPostDataCarrierAdjustments("carrier_adjustments_pending_re.ix?reqType=json", data, "", div_show_id + "-table", true, true, reload_page);

}

$("#generalTab li").click(function () {
    // Get id DIV show content
    div_show = $(this).find("a").attr("href");
    div_show_id = div_show.replace("#", "");
    // Get Status by order element LI
    status = $(this).index() + 1;
    // Set current tab
    $("#current_tab").val(div_show_id);
    // Start Post data
    var htmlContent = $(div_show + "-table").html();
    if (htmlContent == '') {
        doPostDataByParameters("carrier_adjustments_get_data.ix?reqType=json", {
            'manageAdjustmentPageModel.status': status,
            'manageAdjustmentPageModel.recordSize': pageSize,
            'tableId': div_show_id
        }, "", div_show_id + "-table");
    }
});

// change page size
function changePageSize() {
    pageSize = $("#recordSize_" + div_show_id).val();
    doPostDataByParameters("carrier_adjustments_get_data.ix?reqType=json", {
        'manageAdjustmentPageModel.status': status,
        'manageAdjustmentPageModel.recordSize': pageSize,
        'manageAdjustmentPageModel.page': page,
        'orderField': $("#orderField").val(),
        'orderType': $("#orderType").val(),
        'tableId': div_show_id
    }, "", div_show_id + "-table");
}

// change page
function changePage(page) {
    page = page;
    doPostDataByParameters("carrier_adjustments_get_data.ix?reqType=json", {
        'manageAdjustmentPageModel.status': status,
        'manageAdjustmentPageModel.recordSize': pageSize,
        'manageAdjustmentPageModel.page': page,
        'orderField': $("#orderField").val(),
        'orderType': $("#orderType").val(),
        'tableId': div_show_id
    }, "", div_show_id + "-table");
}

function doExport(status) {
    var url = "carrier_adjustments_do_export.ix?manageAdjustmentPageModel.status=" + status;
    window.open(url);
}