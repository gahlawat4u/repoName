<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<table class="table table-bordered mg0" id="datatable1">
    <thead>
    <tr style="background: #F4F4F4">
        <th><xms:localization text="Total Due"/></th>
        <th><xms:localization text="Total Overdue"/></th>
        <th><xms:localization text="<=0 days" escapeHtml="true"/></th>
        <th><xms:localization text="1 to 15"/></th>
        <th><xms:localization text="16 to 30"/></th>
        <th><xms:localization text="31 to 45"/></th>
        <th><xms:localization text="46 to 60"/></th>
        <th><xms:localization text="61 to 90"/></th>
        <th><xms:localization text="91 to 120"/></th>
        <th><xms:localization text="121+"/></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="agingTotal==null">
        <tr>
            <td colspan="10"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <tr>
            <td class="text-right"><s:property value="agingTotal.totalDue"/></td>
            <td class="text-right"><s:property value="agingTotal.totalOverdue"/></td>
            <td class="text-right"><s:property value="agingTotal.range0"/></td>
            <td class="text-right"><s:property value="agingTotal.range1To15"/></td>
            <td class="text-right"><s:property value="agingTotal.range16To30"/></td>
            <td class="text-right"><s:property value="agingTotal.range31To45"/></td>
            <td class="text-right"><s:property value="agingTotal.range46To60"/></td>
            <td class="text-right"><s:property value="agingTotal.range61To90"/></td>
            <td class="text-right"><s:property value="agingTotal.range91To120"/></td>
            <td class="text-right"><s:property value="agingTotal.range120"/></td>
        </tr>
    </s:else>
    </tbody>
</table>
<br/>

<div class="caption b5">
    <xms:localization text="Aging"/>
</div>
<table class="table mg0">
    <tr>
        <th class="s42"><xms:localization text="Show"/> <s:select name="pageSize" list="pageSizes"
                                                                  onchange="search(false,1)"/> <xms:localization
                text="entries"/></th>
    </tr>
</table>
<table class="table table-hover table-bordered mg0" id="customer-aging-table">
    <thead>
    <tr>
        <th sort-field="customer_name"><xms:localization text="Customer"/> <i class="fa fa-sort"></i></th>
        <th sort-field="customer_code"><xms:localization text="Customer #"/> <i class="fa fa-sort"></i></th>
        <th sort-field="sales_rep_name" data-group="salesRep" style="display: none;"><xms:localization
                text="Sales Rep"/> <i class="fa fa-sort"></i></th>
        <th sort-field="total_due"><xms:localization text="Total Due"/> <i class="fa fa-sort"></i></th>
        <th sort-field="total_overdue"><xms:localization text="Total Overdue"/> <i class="fa fa-sort"></i></th>
        <th sort-field="range_0" data-group="agingBuckets" style="display: none;"><xms:localization text="<=0 days"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="range_1_15" data-group="agingBuckets" style="display: none;"><xms:localization text="1 to 15"/>
            <i class="fa fa-sort"></i></th>
        <th sort-field="range_16_30" data-group="agingBuckets" style="display: none;"><xms:localization
                text="16 to 30"/> <i class="fa fa-sort"></i></th>
        <th sort-field="range_31_45" data-group="agingBuckets" style="display: none;"><xms:localization
                text="31 to 45"/> <i class="fa fa-sort"></i></th>
        <th sort-field="range_46_60" data-group="agingBuckets" style="display: none;"><xms:localization
                text="46 to 60"/> <i class="fa fa-sort"></i></th>
        <th sort-field="range_61_90" data-group="agingBuckets" style="display: none;"><xms:localization
                text="61 to 90"/> <i class="fa fa-sort"></i></th>
        <th sort-field="range_91_120" data-group="agingBuckets" style="display: none;"><xms:localization
                text="91 to 120"/> <i class="fa fa-sort"></i></th>
        <th sort-field="range_120" data-group="agingBuckets" style="display: none;"><xms:localization text="121+"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="max_invoice_age" data-group="invoiceAge" style="display: none;"><xms:localization
                text="Max Age"/> <i class="fa fa-sort"></i></th>
        <th sort-field="max_days_overdue" data-group="daysOverdue" style="display: none;"><xms:localization
                text="Max Days Overdue"/> <i class="fa fa-sort"></i></th>
        <th data-group="invoice" style="display: none;" width="20%"><xms:localization
                text="Outstanding Invoice(s)"/></th>
        <th sort-field="total_overpaid" data-group="totalOverpayments" style="display: none;"><xms:localization
                text="Total Overpaid"/> <i class="fa fa-sort"></i></th>
        <th sort-field="terms" data-group="terms" style="display: none;"><xms:localization text="Terms"/> <i
                class="fa fa-sort"></i></th>
        <th sort-field="avg_days_to_pay" data-group="avgDaysToPay" style="display: none;"><xms:localization
                text="Avg Days to pay"/> <i class="fa fa-sort"></i></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="agingList==null || agingList.totalRecords==0">
        <tr>
            <td colspan="19"><xms:localization text="No records to view"/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="agingList.records" status="row">
            <tr>
                <td><a href="javascript:void(0)"
                       onclick="showManageCustomers(<s:property value="customerCode"/>)"><b><s:property
                        value="customerName"/></b></a></td>
                <td><s:property value="customerCode"/></td>
                <td data-group="salesRep" style="display: none;"><s:property value="salesRepName"/></td>
                <td class="text-right"><s:property value="totalDue"/></td>
                <td class="text-right"><s:property value="totalOverdue"/></td>
                <td class="text-right" data-group="agingBuckets" style="display: none;"><s:property
                        value="range0"/></td>
                <td class="text-right" data-group="agingBuckets" style="display: none;"><s:property
                        value="range1To15"/></td>
                <td class="text-right" data-group="agingBuckets" style="display: none;"><s:property
                        value="range16To30"/></td>
                <td class="text-right" data-group="agingBuckets" style="display: none;"><s:property
                        value="range31To45"/></td>
                <td class="text-right" data-group="agingBuckets" style="display: none;"><s:property
                        value="range46To60"/></td>
                <td class="text-right" data-group="agingBuckets" style="display: none;"><s:property
                        value="range61To90"/></td>
                <td class="text-right" data-group="agingBuckets" style="display: none;"><s:property
                        value="range91To120"/></td>
                <td class="text-right" data-group="agingBuckets" style="display: none;"><s:property
                        value="range120"/></td>
                <td class="text-right" data-group="invoiceAge" style="display: none;"><s:property
                        value="maxInvoiceAge"/></td>
                <td class="text-right" data-group="daysOverdue" style="display: none;"><s:property
                        value="maxDaysOverdue"/></td>
                <td data-group="invoice" style="display: none;"><s:property value="unpaidInvoices"/></td>
                <td class="text-right" data-group="totalOverpayments" style="display: none;"><s:property
                        value="totalOverpaid"/></td>
                <td class="text-right" data-group="terms" style="display: none;"><s:property value="terms"/></td>
                <td class="text-right" data-group="avgDaysToPay" style="display: none;"><s:property
                        value="avgDaysToPay"/></td>
            </tr>
        </s:iterator>
    </s:else>
    </tbody>
</table>
<div class="dataTables_paginate">
    <s:if test="!agingList.hasPrev()">
        <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
    </s:if>
    <s:else>
        <a href="javascript:changePage(<s:property value="%{agingList.currentPage - 1}"/>)"
           class="paginate_button previous"><xms:localization text="Previous"/></a>
    </s:else>
	<span> <s:iterator value="agingList.pageRange" status="count">
        <s:if test="%{agingList.pageRange[#count.index] == agingList.currentPage}">
            <a class="paginate_button current"><s:property value="agingList.currentPage"/></a>
        </s:if>
        <s:else>
            <a class="paginate_button" href="javascript:changePage(<s:property/>);"><s:property/></a>
        </s:else>
    </s:iterator>
	</span>
    <s:if test="!agingList.hasNext()">
        <a class="paginate_button next" href="#"><xms:localization text="Next"/></a>
    </s:if>
    <s:else>
        <a class="paginate_button next"
           href="javascript:changePage(<s:property value="%{agingList.currentPage+1}"/>)"><xms:localization
                text="Next"/></a>
    </s:else>
</div>
<br/>
<br/>
<script type="text/javascript">
    $(document).ready(function () {
        // Get rptTxnId
        var rptTxnId = "<s:property value="rptTxnId" />";
        $("#rptTxnId").val(rptTxnId);
        // Show sorting status
        $("#customer-aging-table th[sort-field]").each(function () {
            $(this).css("cursor", "pointer");
            var curField = $("input[name='orderField']").val();
            var curType = $("input[name='orderType']").val();
            if (curField == $(this).attr("sort-field")) {
                if (curType == 0) {
                    $(this).find("i").removeClass().addClass("fa fa-sort-up");
                } else {
                    $(this).find("i").removeClass().addClass("fa fa-sort-down");
                }
            }
            $(this).click(function () {
                var field = $(this).attr("sort-field");
                var type = 0;
                if (field == curField) {
                    if (curType == 0) {
                        curType = 1;
                        type = 1;
                    } else {
                        curType = 0;
                        type = 0;
                    }
                }
                $("input[name='orderField']").val(field);
                $("input[name='orderType']").val(type);
                search(false, 1);
            });
        });
        // Hide unchecked columns
        $("#customer-aging-table").find("th[data-group]").each(function () {
            showColumn($(this).data("group"));
        });
    });


</script>