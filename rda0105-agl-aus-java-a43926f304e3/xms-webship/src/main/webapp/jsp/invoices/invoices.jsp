<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
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
                                <xms:localization text="Invoices"/>
                            </div>
                        </div>
                        <div class="portlet-body" style="padding: 0px;">
                            <ul id="invoicesTab" class="nav nav-tabs responsive">
                                <li class="active" style="margin-left: 10px;"><a href="#Outstanding-tab"
                                                                                 data-toggle="tab"><xms:localization
                                        text="Outstanding Invoices"/></a></li>
                                <li><a href="#Paid-tab" data-toggle="tab"><xms:localization text="Paid Invoices"/></a>
                                </li>
                            </ul>
                            <s:hidden id="selectedInvCode"/>
                            <div id="generalTabContent" class="tab-content responsive">
                                <div id="Outstanding-tab" class="tab-pane fade in active">
                                    <div class="row">
                                        <div class="col-lg-12">
                                            <div class="text-left">
                                                <ul class="pagination mtn mbn" data-hover="">
                                                    <li><span class="lsc"><xms:localization text="Show"/></span></li>
                                                    <li><span class="lsc t1"> <s:select id="selRecordSizeOutstandingInv"
                                                                                        name="pageSize"
                                                                                        list="pageSizeList"
                                                                                        value="outstandingList.pageSize"
                                                                                        onchange="changePageSize()"/>
													</span></li>
                                                    <li><span class="lsc"><xms:localization text="entries"/></span></li>
                                                </ul>
                                            </div>
                                            <table class="table table-hover table-bordered mg0" id="out-standing-table">
                                                <thead>
                                                <tr>
                                                    <th width="20%"><xms:localization text="Invoice No"/></th>
                                                    <th width="10%"><xms:localization text="Invoice Date"/></th>
                                                    <th width="20%" class="text-right"><xms:localization
                                                            text="Total Amount"/></th>
                                                    <th width="20%" class="text-right"><xms:localization
                                                            text="Paid"/></th>
                                                    <th width="20%" class="text-right"><xms:localization
                                                            text="Remaining Balance"/></th>
                                                    <th width="10%"><xms:localization text="Due Date"/></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <s:if test="outstandingList==null || outstandingList.size()==0">
                                                    <tr>
                                                        <td colspan="6" class="text-center"><xms:localization
                                                                text="No data available..."/></td>
                                                    </tr>
                                                </s:if>
                                                <s:else>
                                                    <s:iterator value="outstandingList.records">
                                                        <tr data-invoice-code="<s:property value="invoiceCode" />">
                                                            <td><s:property value="invoiceCode"/></td>
                                                            <td><s:property value="invoiceDate"/></td>
                                                            <td class="text-right"><s:property
                                                                    value="totalAmount"/></td>
                                                            <td class="text-right"><s:property
                                                                    value="totalPayment"/></td>
                                                            <td class="text-right"><s:property
                                                                    value="remainningBalance"/></td>
                                                            <td><s:property value="dueDate"/></td>
                                                        </tr>
                                                    </s:iterator>
                                                </s:else>
                                                </tbody>
                                            </table>
                                            <script type="text/javascript">
                                                $(document).ready(function () {
                                                    $('#selectedId').val("");
                                                    $('table#out-standing-table tbody tr').click(function () {
                                                        $(this).addClass('selected-row').siblings().removeClass('selected-row');
                                                        var selectedInvCode = $(this).attr("data-invoice-code");
                                                        $('#selectedInvCode').val(selectedInvCode);
                                                        $('#btn-view-pdf').attr('disabled', false);
                                                    });
                                                });
                                            </script>
                                            <s:if test="outstandingList.totalPage != 1">
                                                <div class="text-right">
                                                    <ul class="pagination mts mbs" data-hover="">
                                                        <s:if test="!outstandingList.hasPrev()">
                                                            <li class="disabled"><a><xms:localization
                                                                    text="Previous"/></a></li>
                                                        </s:if>
                                                        <s:else>
                                                            <li>
                                                                <a href="javascript:changePage(<s:property value="%{outstandingList.currentPage - 1}"/>)"><xms:localization
                                                                        text="Previous"/></a></li>
                                                        </s:else>
                                                        <s:iterator value="outstandingList.pageRange" status="count">
                                                            <s:if test="%{outstandingList.pageRange[#count.index] == outstandingList.currentPage}">
                                                                <li class="active"><a><s:property
                                                                        value="outstandingList.currentPage"/></a></li>
                                                            </s:if>
                                                            <s:else>
                                                                <li>
                                                                    <a href="javascript:changePage(<s:property/>);"><s:property/></a>
                                                                </li>
                                                            </s:else>
                                                        </s:iterator>
                                                        <s:if test="!outstandingList.hasNext()">
                                                            <li class="disabled"><a href="#"><xms:localization
                                                                    text="Next"/></a></li>
                                                        </s:if>
                                                        <s:else>
                                                            <li>
                                                                <a href="javascript:changePage(<s:property value="%{outstandingList.currentPage + 1}"/>)"><xms:localization
                                                                        text="Next"/></a></li>
                                                        </s:else>
                                                    </ul>
                                                </div>
                                            </s:if>
                                        </div>
                                    </div>
                                </div>
                                <div id="Paid-tab" class="tab-pane fade"></div>
                                <div class=" text-right pal pdt10">
                                    <button class="btn s33 " type="button" disabled="disabled" onclick="viewPdf()"
                                            id="btn-view-pdf">
                                        <xms:localization text="View PDF"/>
                                    </button>
                                    <button class="btn s33 " type="button" onclick="doRefresh()">
                                        <xms:localization text="Refresh"/>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var isPostedOutstanding = true;
    var isPostedPaid = false;
    var activeTab = "";
    $('a[data-toggle="tab"]').on('shown.bs.tab', function (e) {
        activeTab = $('ul#invoicesTab li.active a').attr('href');
        $("#btn-view-pdf").attr("disabled", "disabled");
        switch (activeTab) {
            case "#Outstanding-tab":
                if (isPostedOutstanding == false) {
                    getOutstanding();
                    isPostedOutstanding = true;
                }
                break;
            case "#Paid-tab":
                if (isPostedPaid == false) {
                    getPaid();
                    isPostedPaid = true;
                }
                break;
        }
    })
    function doRefresh() {
        isPostedOutstanding = false;
        isPostedPaid = false;
        activeTab = $('ul#invoicesTab li.active a').attr('href');
        $("#btn-view-pdf").attr("disabled", "disabled");
        switch (activeTab) {

            case "#Outstanding-tab":
                getOutstanding();
                isPostedOutstanding = true;
                break;
            case "#Paid-tab":
                getPaid();
                isPostedPaid = true;
                break;
        }
    }
    function viewPdf() {
        var invCode = $("#selectedInvCode").val();
        window.open("invoices_view_pdf.ix?invoiceCode=" + invCode);
    }
    function changePage(page) {
        var pageSize = "";
        activeTab = $('ul#invoicesTab li.active a').attr('href');
        switch (activeTab) {
            case "#Outstanding-tab":
                pageSize = $('#selRecordSizeOutstandingInv').val();
                getOutstanding(pageSize, page);
                isPostedOutstanding = true;
                break;
            case "#Paid-tab":
                pageSize = $('#selPaidPageSize').val();
                getPaid(pageSize, page);
                isPostedPaid = true;
                break;
        }
    }
    function changePageSize() {
        var pageSize = "";
        activeTab = $('ul#invoicesTab li.active a').attr('href');
        switch (activeTab) {
            case "#Outstanding-tab":
                pageSize = $('#selRecordSizeOutstandingInv').val();
                getOutstanding(pageSize);
                break;
            case "#Paid-tab":
                pageSize = $('#selPaidPageSize').val();
                getPaid(pageSize);
                break;
        }
    }
    function getOutstanding(pageSize, page) {
        var p = "";
        var ps = "";
        if (page != '' && pageSize != '') {
            var p = page;
            var ps = pageSize;
        } else {
            p = 1;
            ps = 5;
        }
        doPostDataByParameters("invoices_get_outstanding.ix?reqType=json", {
            'page': p,
            'pageSize': ps
        }, "", "Outstanding-tab");
    }
    function getPaid(pageSize, page) {
        var p = "";
        var ps = "";
        if (page != '' && pageSize != '') {
            var p = page;
            var ps = pageSize;
        } else {
            p = 1;
            ps = 5;
        }
        doPostDataByParameters("invoices_get_paid.ix?reqType=json", {
            'page': p,
            'pageSize': ps
        }, "", "Paid-tab");
    }
</script>
<!--END CONTENT-->