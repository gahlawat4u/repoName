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
            case '#Overview-tab':
                if (isPostedOverview == false) {
                    getOverView();
                    isPostedOverview = true;
                }
                break;
            case '#paymentMarginDetail-tab':
                if (isPostedPaymentMarginDetail == false) {
                    getPaymentMarginDetail();
                    isPostedPaymentMarginDetail = true;
                }
                break;
            case '#carrierCreditDetails-tab':
                if (isPostedCarrierCreditDetails == false) {
                    getCarrierCreditDetails();
                    isPostedCarrierCreditDetails = true;
                }
                break;
            case '#after61PaymentCreditDetail-tab':
                if (isPosted61dayPaymentCreditDetail == false) {
                    get61dayPaymentCreditDetail();
                    isPosted61dayPaymentCreditDetail = true;
                }
                break;
            case '#carrierCostDeduction-tab':
                if (isPosterCarrierCostDeduction == false) {
                    getCarrierCostDeduction();
                    isPosterCarrierCostDeduction = true;
                }
                break;
            case '#nonCentralizedDetails-tab':
                if (isPostedNonCentralizedDetails == false) {
                    getNonCentralizedDetails();
                    isPostedNonCentralizedDetails = true;
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

function getOverView() {
    var franchiseCode = $('#selFranchiseCode').val();
    var startDate = $('#startDate').val();
    var endDate = $('#endDate').val();
    var rptTxnId = $('#rptTxnId').val();
    postJsonByPramameters('fpb_get_overview_data_ms.ix?reqType=json', {
        'startDate': submitStartDate,
        'endDate': submitEndDate,
        'franchiseCode': submitFranchiseCode,
        'rptTxnId': rptTxnId
    }, "loading", 'Overview-tab', 'btn_export');
}

function getPaymentMarginDetail(page, recordSize) {
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
    postJsonByPramameters('fpb_get_margin_data_ms.ix?reqType=json', {
        'startDate': submitStartDate,
        'endDate': submitEndDate,
        'franchiseCode': submitFranchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    }, "loading", 'paymentMarginDetail-tab', 'btn_export');
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
    postJsonByPramameters('fpb_get_credit_data_ms.ix?reqType=json', {
        'startDate': submitStartDate,
        'endDate': submitEndDate,
        'franchiseCode': submitFranchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    }, "loading", 'carrierCreditDetails-tab', 'btn_export');
}

function getCarrierCostDeduction(page, recordSize) {
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
    postJsonByPramameters('fpb_get_deduct_data_ms.ix?reqType=json', {
        'startDate': submitStartDate,
        'endDate': submitEndDate,
        'franchiseCode': submitFranchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    }, "loading", 'carrierCostDeduction-tab', 'btn_export');
}
function get61dayPaymentCreditDetail(page, recordSize) {
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
    postJsonByPramameters('fpb_get_61days_data_ms.ix?reqType=json', {
        'startDate': submitStartDate,
        'endDate': submitEndDate,
        'franchiseCode': submitFranchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    }, "loading", 'after61PaymentCreditDetail-tab', 'btn_export');
}
function getNonCentralizedDetails(page, recordSize) {

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
    postJsonByPramameters('fpb_get_non_central_data_ms.ix?reqType=json', {
        'startDate': submitStartDate,
        'endDate': submitEndDate,
        'franchiseCode': submitFranchiseCode,
        'page': page,
        'recordSize': recordSize,
        'rptTxnId': rptTxnId
    }, "loading", 'nonCentralizedDetails-tab', 'btn_export');

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
    postJsonByPramameters('fpb_get_overpayment_data_ms.ix?reqType=json', {
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
        case '#Overview-tab':
            getOverView();
            isPostedOverview = true;
            isGoEvent = false;
            break;
        case '#paymentMarginDetail-tab':
            getPaymentMarginDetail();
            isPostedPaymentMarginDetail = true;
            isGoEvent = false;
            break;
        case '#carrierCreditDetails-tab':
            getCarrierCreditDetails();
            isPostedCarrierCreditDetails = true;
            isGoEvent = false;
            break;
        case '#after61PaymentCreditDetail-tab':
            get61dayPaymentCreditDetail();
            isPosted61dayPaymentCreditDetail = true;
            isGoEvent = false;
            break;
        case '#carrierCostDeduction-tab':
            getCarrierCostDeduction();
            isPosterCarrierCostDeduction = true;
            isGoEvent = false;
            break;
        case '#nonCentralizedDetails-tab':
            getNonCentralizedDetails();
            isPostedNonCentralizedDetails = true;
            isGoEvent = false;
            break;
        case '#overpayment-tab':
            getOverpayment();
            isPostedOverpayment = true;
            isGoEvent = false;
            break;
        default:
            getOverView();
            isGoEvent = false;
    }
    isPosted = true;
}
function changeRecordSize() {
    var activeTab = $('ul#generalTab li.active a').attr('href');
    switch (activeTab) {
        case '#paymentMarginDetail-tab':
            recordSize = $('#selRecordSize').val();
            getPaymentMarginDetail(1, recordSize);
            break;
        case '#carrierCreditDetails-tab':
            recordSize = $('#selRecordSizeCarrierCreditDetails').val();
            getCarrierCreditDetails(1, recordSize);
            break;
        case '#after61PaymentCreditDetail-tab':
            recordSize = $('#selRecordSize61').val();
            get61dayPaymentCreditDetail(1, recordSize);
            break;
        case '#carrierCostDeduction-tab':
            recordSize = $('#selRecordSizeCarrierCostDeduction').val();
            getCarrierCostDeduction(1, recordSize);
            break;
        case '#nonCentralizedDetails-tab':
            recordSize = $('#selRecordSizeNonCentralizedDetails').val();
            getNonCentralizedDetails(1, recordSize);
            break;
        case '#overpayment-tab':
            recordSize = $('#selRecordSizeOverpayment').val();
            getOverpayment(1, recordSize);
            break;
    }
}
function export_report_pdf() {
    rptTxnId = $('#rptTxnId').val();
    var url = "fpb_export_report_pdf_ms.ix?startDate=" + submitStartDate + "&endDate=" + submitEndDate + "&franchiseCode=" + submitFranchiseCode + "&rptTxnId=" + rptTxnId;
    window.open(url, '_blank');
}
function export_report_html() {
    rptTxnId = $('#rptTxnId').val();
    var url = "fpb_export_report_html_ms.ix?startDate=" + submitStartDate + "&endDate=" + submitEndDate + "&franchiseCode=" + submitFranchiseCode + "&rptTxnId=" + rptTxnId;
    window.open(url, '_blank');
}