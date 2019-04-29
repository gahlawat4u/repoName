var isPosted = false;
var isPostedOverview = false;
var isPostedPaymentMarginDetail = false;
var isPosted61dayPaymentCreditDetail = false;
var isPosterCarrierCostDeduction = false;
var isPostedCarrierCreditDetails = false;
var isPostedNonCentralizedDetails = false;
var isPostedOverpayment = false;
var isGoEvent = false;
var rptTxnId = "";
var submitStartDate = "";
var submitEndDate = "";
var submitFranchiseCode = "";
$('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
    isGoEvent = false;
    if (isPosted == true) {
        var activeTab = $('ul#generalTab li.active a').attr('href');
        switch (activeTab) {
            case '#carrierCreditDetails-tab':
                if (isPostedCarrierCreditDetails == false) {
                    getCarrierCreditDetails();
                    isPostedCarrierCreditDetails = true;
                }
            case '#overpayment-tab':
                if (isPostedOverpayment == false) {
                    getOverpayment();
                    isPostedOverpayment = true;
                }
                break;
        }
    }
});
var dateRange = $('#payableSelDaterange').val();
var dateArr = dateRange.split(' - ');
var startDate = dateArr[0];
var endDate = dateArr[1];
$('#startDate').val(startDate);
$('#endDate').val(endDate);

function changeDateRange() {
    var result = $('#payableSelDaterange').val();
    if (result == 'Custom date range') {
        $('.customDateRange').show().val('');
    } else {
        $('.customDateRange').hide();
        var dateArr = result.split(' - ');
        var startDate = dateArr[0];
        var endDate = dateArr[1];
        $('#startDate').val(startDate);
        $('#endDate').val(endDate);
    }
}

function getCarrierCreditDetails(page, recordSize) {
    var franchiseCode = $('#selFranchiseCode').val();
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();
    var rptTxnId = $('#rptTxnId').val();
    if (typeof page == 'undefined' || page == '' || page == null) {
        page = 1;
    }
    if (typeof recordSize == 'undefined' || recordSize == '' || recordSize == null) {
        recordSize = 25;
    }
    if (isGoEvent == true) {
        rptTxnId = '';
    }
    postJsonByPramameters('fpb_sc_credit.ix?reqType=json', {
        'startDate': submitStartDate,
        'endDate': submitEndDate,
        'franchiseCode': submitFranchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    }, "loading", 'carrierCreditDetails-tab', 'btn_export');
}

function getOverpayment(page, recordSize) {
    var franchiseCode = $('#selFranchiseCode').val();
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();
    var rptTxnId = $('#rptTxnId').val();
    if (typeof page == 'undefined' || page == '' || page == null) {
        page = 1;
    }
    if (typeof recordSize == 'undefined' || recordSize == '' || recordSize == null) {
        recordSize = 25;
    }

    if (isGoEvent == true) {
        rptTxnId = '';
    }
    postJsonByPramameters('fpb_sc_overpayment.ix?reqType=json', {
        'startDate': submitStartDate,
        'endDate': submitEndDate,
        'franchiseCode': submitFranchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    }, "loading", 'overpayment-tab', 'btn_export');
}

function franchisePayableSubmit() {
    var activeTab = $('ul#generalTab li.active a').attr('href');
    isPostedOverview = false;
    isPostedPaymentMarginDetail = false;
    isPosted61dayPaymentCreditDetail = false;
    isPosterCarrierCostDeduction = false;
    isPostedCarrierCreditDetails = false;
    isPostedNonCentralizedDetails = false;
    isPostedOverpayment = false;
    isGoEvent = true;
    $('#rptTxnId').val("");
    submitFranchiseCode = $('#selFranchiseCode').val();
    submitStartDate = $('#startDate').val();
    submitEndDate = $('#endDate').val();
    switch (activeTab) {
        case '#carrierCreditDetails-tab':
            getCarrierCreditDetails();
            isPostedCarrierCreditDetails = true;
            isGoEvent = false;
            break;
        case '#overpayment-tab':
            getOverpayment();
            isPostedOverpayment = true;
            isGoEvent = false;
            break;
        default:
            isGoEvent = false;
    }
    isPosted = true;
}
function changeRecordSize() {
    var activeTab = $('ul#generalTab li.active a').attr('href');
    switch (activeTab) {
        case '#carrierCreditDetails-tab':
            recordSize = $('#selRecordSizeCarrierCreditDetails').val();
            getCarrierCreditDetails(1, recordSize);
            break;
        case '#overpayment-tab':
            recordSize = $('#selRecordSizeOverpayment').val();
            getOverpayment(1, recordSize);
            break;
    }
}
function export_report_html() {
    rptTxnId = $('#rptTxnId').val();
    var url = "fpb_sc_print.ix?startDate=" + submitStartDate + "&endDate=" + submitEndDate + "&franchiseCode=" + submitFranchiseCode + "&rptTxnId=" + rptTxnId;
    window.open(url, '_blank');
}