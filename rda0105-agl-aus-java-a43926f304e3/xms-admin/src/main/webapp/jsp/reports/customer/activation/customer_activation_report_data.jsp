<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:hidden name="rptTxnId"/>
<table class="table table-bordered mg0 table-hover">
    <thead>
    <tr>
        <th sort-field="customer_code"><xms:localization text="Customer #"/> <i class="fa fa-sort"></i></th>
        <th sort-field="customer_name"><xms:localization text="Customer Name"/> <i class="fa fa-sort"></i></th>
        <th sort-field="sale_rep_name"><xms:localization text="Sales Rep"/> <i class="fa fa-sort"></i></th>
        <th sort-field="submission_date"><xms:localization text="Submission Date"/> <i class="fa fa-sort"></i></th>
        <th sort-field="activation_date"><xms:localization text="Activation Date"/> <i class="fa fa-sort"></i></th>
        <th sort-field="days_to_activate"><xms:localization text="Days to Activate"/> <i class="fa fa-sort"></i></th>
        <th sort-field="first_invoice"><xms:localization text="First Invoice"/> <i class="fa fa-sort"></i></th>
        <th sort-field="margin_on_invoice"><xms:localization text="Margin On Invoice"/> <i class="fa fa-sort"></i></th>
    </tr>
    </thead>
    <tbody>
    <s:if test="activationReport==null || activationReport.totalRecords==0">
        <tr>
            <td colspan="8"><xms:localization text="No data available..."/></td>
        </tr>
    </s:if>
    <s:else>
        <s:iterator value="activationReport.records">
            <tr>
                <td><s:property value="customerCode"/></td>
                <td><s:property value="customerName"/></td>
                <td><s:property value="saleRepName"/></td>
                <td><s:property value="submissionDate"/></td>
                <td><s:property value="activationDate"/></td>
                <td class="text-right"><s:property value="daysToActivate"/></td>
                <td><s:property value="firstInvoice"/></td>
                <td class="text-right">$<s:property value="marginOnInvoice"/></td>
            </tr>
        </s:iterator>
    </s:else>
    <tr>
        <th colspan="8"><xms:localization text="Total"/>: <s:if test="activationReport==null">
            0
        </s:if> <s:else>
            <s:property value="activationReport.totalRecords"/>
        </s:else> <xms:localization text="Activation(s)"/></th>
    </tr>
    </tbody>
</table>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="activationReport.startRecord"/> <xms:localization
                    text="to"/> <s:property value="activationReport.endRecord"/> <xms:localization text="of"/>
                <s:property value="activationReport.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="activationReport!=nul">
                <s:if test="activationReport.hasPrev()">
                    <a href="javascript:doReport(<s:property value="%{activationReport.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="activationReport.pageRange" status="count">
                    <s:if test="%{activationReport.pageRange[#count.index] == activationReport.currentPage}">
                        <a class="paginate_button current"><s:property value="activationReport.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button" href="javascript:doReport(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="activationReport.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:doReport(<s:property value="%{activationReport.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $("#activation-report-result th[sort-field]").each(function () {
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
                var curField = $("input[name='orderField']").val();
                var curType = $("input[name='orderType']").val();
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
                doReport(1);
            });
        });
    });
    <s:if test="activationReport==null || activationReport.totalRecords==0">
    hasRecords = false;
    </s:if>
    <s:else>
    hasRecords = true;
    </s:else>


</script>