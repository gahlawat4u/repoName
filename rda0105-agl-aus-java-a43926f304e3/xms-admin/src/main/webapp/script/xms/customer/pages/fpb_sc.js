var isPostedOverview = false;
var isPostedShipmentDetail = false;
var isPostedCarrierCredit = false;
var isPostedTechFee = false;
var isPostedOverpayment = false;
var isPosted = false;

var franchiseCode = "";
var dateRange = $('#payableSelDaterange').val();
var dateArr = dateRange.split(' - ');
var startDate = dateArr[0];
var endDate = dateArr[1];
$('#startDate').val(startDate);
$('#endDate').val(endDate);
var activeTab = $('ul#generalTab li.active a').attr('href');
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    isGoEvent = false;
    activeTab = $('ul#generalTab li.active a').attr('href');
    if (isPosted == true) {
        switch (activeTab) {
            case '#overview-tab':
                if (isPostedOverview == false) {
                    getOverView();
                    isPostedOverview = true;
                }
                break;
            case '#shipment-details-tab':
                if (isPostedShipmentDetail == false) {
                    getShipmentDetail();
                    isPostedShipmentDetail = true;
                }
                break;
            case '#carrier-credit-tab':
                if (isPostedCarrierCredit == false) {
                    getCarrierCredit();
                    isPostedCarrierCredit = true;
                }
                break;
            case '#tech-fee-tab':
                if (isPostedTechFee == false) {
                    getTechFees();
                    isPostedTechFee = true;
                }
                break;
            case '#overpayment-tab':
                if (isPostedOverpayment == false) {
                    getOverpayment();
                    isPostedOverpayment = true;
                }
                break;
        }
    }
});

function changeDateRange() {
    var result = $('#payableSelDaterange').val();
    if (result == 'Custom date range') {
        $('.customDateRange').show().val('');
    } else {
        $('.customDateRange').hide();
        var dateArr = result.split(' - ');
        var startDateStr = dateArr[0];
        var endDateStr = dateArr[1];
        $('#startDate').val(startDateStr);
        $('#endDate').val(endDateStr);
    }
}

function getOverView(page, recordSize) {
    var rptTxnId = $("#rptTxnId").val();
    if (typeof page == 'undefined' || page == '' || page == null) {
        page = 1;
    }
    if (typeof recordSize == 'undefined' || recordSize == '' || recordSize == null) {
        recordSize = 25;
    }
    var data = {
        'startDate': startDate,
        'endDate': endDate,
        'franchiseCode': franchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    };
    doPostDataByParametersWithCallBack("fpb_sc_overview.ix?reqType=json", data, "", "overview-tab", true, false, removeDisableButton, disableButton);
}

function getCarrierCredit(page, recordSize) {
    var rptTxnId = $("#rptTxnId").val();
    if (typeof page == 'undefined' || page == '' || page == null) {
        page = 1;
    }
    if (typeof recordSize == 'undefined' || recordSize == '' || recordSize == null) {
        recordSize = 25;
    }
    var data = {
        'startDate': startDate,
        'endDate': endDate,
        'franchiseCode': franchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    };
    doPostDataByParametersWithCallBack("fpb_sc_credit.ix?reqType=json", data, "", "carrier-credit-tab", true, false, removeDisableButton, disableButton);
}

function getOverpayment(page, recordSize) {
    var rptTxnId = $("#rptTxnId").val();
    if (typeof page == 'undefined' || page == '' || page == null) {
        page = 1;
    }
    if (typeof recordSize == 'undefined' || recordSize == '' || recordSize == null) {
        recordSize = 25;
    }
    var data = {
        'startDate': startDate,
        'endDate': endDate,
        'franchiseCode': franchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    };
    doPostDataByParametersWithCallBack("fpb_sc_overpayment.ix?reqType=json", data, "", "overpayment-tab", true, false, removeDisableButton, disableButton);
}

function getShipmentDetail(page, recordSize) {
    var rptTxnId = $("#rptTxnId").val();
    if (typeof page == 'undefined' || page == '' || page == null) {
        page = 1;
    }
    if (typeof recordSize == 'undefined' || recordSize == '' || recordSize == null) {
        recordSize = 25;
    }
    var data = {
        'startDate': startDate,
        'endDate': endDate,
        'franchiseCode': franchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    };
    doPostDataByParametersWithCallBack("fpb_sc_shipment.ix?reqType=json", data, "", "shipment-details-tab", true, false, removeDisableButton, disableButton);
}

function getTechFees(page, recordSize) {
    var rptTxnId = $("#rptTxnId").val();
    if (typeof page == 'undefined' || page == '' || page == null) {
        page = 1;
    }
    if (typeof recordSize == 'undefined' || recordSize == '' || recordSize == null) {
        recordSize = 25;
    }
    var data = {
        'startDate': startDate,
        'endDate': endDate,
        'franchiseCode': franchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    };
    doPostDataByParametersWithCallBack("fpb_sc_tech_fee.ix?reqType=json", data, "", "tech-fee-tab", true, false, removeDisableButton, disableButton);
}

function fpbSubmit() {
    isPostedOverview = false;
    isPostedShipmentDetail = false;
    isPostedCarrierCredit = false;
    isPostedTechFee = false;
    isPostedOverpayment = false;
    isGoEvent = true;
    $("#rptTxnId").val("");
    franchiseCode = $("#selFranchiseCode").val();
    startDate = $("#startDate").val();
    endDate = $("#endDate").val();
    switch (activeTab) {
        case '#overview-tab':
            getOverView();
            isPostedOverview = true;
            break;
        case '#shipment-details-tab':
            getShipmentDetail();
            isPostedShipmentDetail = true;
            break;
        case '#carrier-credit-tab':
            getCarrierCredit();
            isPostedCarrierCredit = true;
            break;
        case '#tech-fee-tab':
            getTechFees();
            isPostedTechFee = true;
            break;
        case '#overpayment-tab':
            getOverpayment();
            isPostedOverpayment = true;
            break;
    }
    isPosted = true;
}
function export_report_html() {
    var rptTxnId = $("#rptTxnId").val();
    loadingDialog.dialog("open");
    var data = {
        "startDate": startDate,
        "endDate": endDate,
        "franchiseCode": franchiseCode,
        "rptTxnId": rptTxnId
    };
    $.post("fpb_sc_print.ix", data, function (res) {
        loadingDialog.dialog("close");
        var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
        win.document.write(res);
    });
}
function removeDisableButton() {
    $("#btn-print").attr("disabled", false);
    $("#btn-export").attr("disabled", false);
}
function disableButton() {
    $("#btn-print").attr("disabled", true);
    $("#btn-export").attr("disabled", true);
}
function export_report_pdf() {
    rptTxnId = $('#rptTxnId').val();
    loadingDialog.dialog("open");
    var data = {
        "startDate": startDate,
        "endDate": endDate,
        "franchiseCode": franchiseCode,
        "rptTxnId": rptTxnId
    };
    $.fileDownload("fpb_sc_export_pdf.ix", {
        failMessageHtml: "There was a problem generating your report, please try again.",
        httpMethod: "POST",
        data: data,
        successCallback: function (url) {
            loadingDialog.dialog("close");
        },
        failCallback: function (url) {
            loadingDialog.dialog("close");
        }
    });
}
