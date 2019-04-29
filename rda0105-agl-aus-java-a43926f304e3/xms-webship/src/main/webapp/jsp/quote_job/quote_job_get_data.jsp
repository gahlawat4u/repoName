<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/xms-tags" prefix="xms" %>
<div class="text-left">
    <ul class="pagination mtn mbn" data-hover="">
        <li><span class="lsc"><xms:localization text="Show"/></span></li>
        <li><span class="lsc t1"> <s:select list="pageSizeList" value="quoteJobList.pageSize" class="form-control"
                                            id="selPageSize" onchange="changePageSize()"></s:select>
		</span></li>
        <li><span class="lsc"><xms:localization text="entries"/></span></li>
    </ul>
</div>
<div class="col-lg-12 pd0 mg0" style="min-width: 10px !important; overflow: auto">
    <table class="table table-hover table-bordered mg0"
           <s:if test="!quoteJobList.records.isEmpty()">id="quote-job-table"</s:if>>
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
        <s:if test="quoteJobList.records.isEmpty()">
            <tr>
                <td colspan="10"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="quoteJobList.records">
                <tr data-quote-id="<s:property value="quoteId" />">
                    <td><s:property value="quoteDate"/></td>
                    <td><s:property value="customerAddress.customerName"/></td>
                    <td><s:property value="quoteNumber"/></td>
                    <td><s:property value="senderAddress.city"/></td>
                    <td><s:property value="senderAddress.postalCode"/></td>
                    <td><s:property value="receiverAddress.city"/></td>
                    <td><s:property value="receiverAddress.postalCode"/></td>
                    <td><s:property value="shipmentType.shipmentTypeName"/></td>
                    <td><s:property value="packageType.packageName"/></td>
                    <td><s:property value="totalCharge"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
    <s:hidden value="%{quoteJobList.currentPage}" id="currPage"></s:hidden>
    <input type="hidden" id="selectedId" value=""/>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#selectedId').val("");
            $('table#quote-job-table tbody tr').click(function () {
                $(this).addClass('selected-row').siblings().removeClass('selected-row');
                selectedAddressId = $(this).attr("data-quote-id");
                $('#selectedId').val(selectedAddressId);
                $('#btn-view-detail').attr('disabled', false);
                $('#btn-reship').attr('disabled', false);
            });
            $('table#quote-job-table tbody tr').dblclick(function () {
                viewDetail();
            });
        });
    </script>
</div>
<div class="text-right">
    <ul class="pagination mts mbs" data-hover="">
        <s:if test="!quoteJobList.hasPrev()">
            <li class="disabled"><a><xms:localization text="Previous"/></a></li>
        </s:if>
        <s:else>
            <li><a href="javascript:changePage(<s:property value="%{quoteJobList.currentPage - 1}"/>)"><xms:localization
                    text="Previous"/></a></li>
        </s:else>
        <s:iterator value="quoteJobList.pageRange" status="count">
            <s:if test="%{quoteJobList.pageRange[#count.index] == quoteJobList.currentPage}">
                <li class="active"><a><s:property value="quoteJobList.currentPage"/></a></li>
            </s:if>
            <s:else>
                <li><a href="javascript:changePage(<s:property/>);"><s:property/></a></li>
            </s:else>
        </s:iterator>
        <s:if test="!quoteJobList.hasNext()">
            <li class="disabled"><a href="#"><xms:localization text="Next"/></a></li>
        </s:if>
        <s:else>
            <li><a href="javascript:changePage(<s:property value="%{quoteJobList.currentPage + 1}"/>)"><xms:localization
                    text="Next"/></a></li>
        </s:else>
    </ul>
</div>