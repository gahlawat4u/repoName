<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/xms-tags" prefix="xms" %>
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
                                <xms:localization text="Quote/Job"/>
                            </div>
                            <div class="tools">
                                <i class="fa fa-chevron-up"></i><i class="afa fa-times"></i>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <div class="panel-body pan">
                                <div class="form-body pal">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="col-lg-12 flr pd0">
                                                <div class="form-group flr mgb">
                                                    <table class="s36" style="margin-bottom: 10px;">
                                                        <tr>
                                                            <td><xms:localization text="Start date"/>:</td>
                                                            <td><input id="startDate"
                                                                       class="form-control date form_datetime"
                                                                       type="text" data-date-format="dd MM yyyy"
                                                                       readonly="readonly"></td>
                                                            <td><xms:localization text="End date"/>:</td>
                                                            <td><input id="endDate"
                                                                       class="form-control date form_datetime"
                                                                       type="text" data-date-format="dd MM yyyy"
                                                                       readonly="readonly"></td>
                                                            <td><xms:localization text="Quote Number"/>:</td>
                                                            <td><input id="quoteNumber" type="text" placeholder=""
                                                                       class="form-control"/></td>
                                                            <td>
                                                                <button class="btn s33" type="button"
                                                                        onclick="doSearch()">
                                                                    <xms:localization text="Search"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s33" type="button"
                                                                        disabled="disabled" id="btn-reship"
                                                                        onclick="javascript:reshipQuoteJob();">
                                                                    <xms:localization text="Book this shipment"/>
                                                                </button>
                                                            </td>
                                                            <td>
                                                                <button class="btn s33" type="button"
                                                                        disabled="disabled" id="btn-view-detail"
                                                                        onclick="viewDetail()">
                                                                    <xms:localization text="View Details"/>
                                                                </button>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                            <p>
                                                <br/>
                                            </p>

                                            <div class="row" id="div-quote-job-table">
                                                <div class="col-lg-12 pd0 mg0"
                                                     style="min-width: 10px !important; overflow: auto">
                                                    <table class="table table-hover table-bordered mg0"
                                                           id="quote-job-table">
                                                        <thead>
                                                        <tr>
                                                            <th><xms:localization text="Quote Date"/></th>
                                                            <th><xms:localization text="Customer"/></th>
                                                            <th><xms:localization text="Quote/Job Number"/></th>
                                                            <th><xms:localization text="Sender Suburb"/></th>
                                                            <th><xms:localization text="Sender Postcode"/></th>
                                                            <th><xms:localization text="Receiver Suburb"/></th>
                                                            <th><xms:localization text="Receiver Postcode"/></th>
                                                            <th><xms:localization text="Shipment Type"/></th>
                                                            <th><xms:localization text="Package Type"/></th>
                                                            <th><xms:localization text="Total Amount"/></th>
                                                        </tr>
                                                        </thead>
                                                        <tbody>
                                                        <tr>
                                                            <td colspan="10"><xms:localization
                                                                    text="No data available..."/></td>
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
<div id="view-detail-dialog"></div>
<script type="text/javascript">
    $(document).ready(function(){
        //WABU-8: do search on enter this page
        doSearch();
    });
    $(".form_datetime").datetimepicker({
        weekStart: 1,
        todayBtn: 1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0,
        format: 'dd-mm-yyyy'
        /* format: 'dd-mm-yyyy, HH:ii p' */
    });
    var today = new Date();
    $("#startDate").datetimepicker("update", new Date(today.getFullYear(), today.getMonth(), today.getDate() - 7));
    $("#endDate").datetimepicker("update", today);
    var pageSize = $("#selPageSize").val();
    var startDate = "";
    var endDate = "";
    function changePageSize() {
        pageSize = $("#selPageSize").val();
        getData(pageSize, "1");
    }
    function changePage(page) {
        getData(pageSize, page)
    }
    ;
    function doSearch() {
        startDate = $("#startDate").val();
        endDate = $("#endDate").val();
        quoteNumber = $("#quoteNumber").val();
        getData();
    }
    function getData(pageSize, page) {
        var p = typeof (page) != "undefined" ? page : 1;
        var ps = typeof (pageSize) != "undefined" ? pageSize : $("#selPageSize").val();
        var data = {
            "page": p,
            "pageSize": ps,
            "startDate": startDate,
            "endDate": endDate,
            "quoteNumber": quoteNumber
        };
        console.log(data);
        doPostDataByParameters("quote_job_get_data.ix?reqType=json", data, "", "div-quote-job-table");
    }

    function reshipQuoteJob() {
        var quoteId = $("#selectedId").val();
        window.location = "webship.ix?quoteId=" + quoteId;
    }

    function viewDetail() {
        var quoteId = $("#selectedId").val();
        loadDetailDialog("quote_job_view_detail.ix?reqType=json", {
            "quoteId": quoteId
        }, "<xms:localization text="Quote/Job detail" />", "<xms:localization text="Close" />", "view-detail-dialog");
    }
</script>
<!--END CONTENT-->