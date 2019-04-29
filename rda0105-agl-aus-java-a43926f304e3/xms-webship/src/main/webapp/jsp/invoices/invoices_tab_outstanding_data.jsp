<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-12">
        <div class="text-left">
            <ul class="pagination mtn mbn" data-hover="">
                <li><span class="lsc"><xms:localization text="Show"/></span></li>
                <li><span class="lsc t1"> <s:select id="selRecordSizeOutstandingInv" name="pageSize" list="pageSizeList"
                                                    value="outstandingList.pageSize" onchange="changePageSize()"/>
				</span></li>
                <li><span class="lsc"><xms:localization text="entries"/></span></li>
            </ul>
        </div>
        <table class="table table-hover table-bordered mg0" id="out-standing-table">
            <thead>
            <tr>
                <th width="20%"><xms:localization text="Invoice No"/></th>
                <th width="10%"><xms:localization text="Invoice Date"/></th>
                <th width="20%" class="text-right"><xms:localization text="Total Amount"/></th>
                <th width="20%" class="text-right"><xms:localization text="Paid"/></th>
                <th width="20%" class="text-right"><xms:localization text="Remaining Balance"/></th>
                <th width="10%"><xms:localization text="Due Date"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="outstandingList==null || outstandingList.size()==0">
                <tr>
                    <td colspan="6"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="outstandingList.records">
                    <tr data-invoice-code="<s:property value="invoiceCode" />">
                        <td><s:property value="invoiceCode"/></td>
                        <td><s:property value="invoiceDate"/></td>
                        <td class="text-right"><s:property value="totalAmount"/></td>
                        <td class="text-right"><s:property value="totalPayment"/></td>
                        <td class="text-right"><s:property value="remainningBalance"/></td>
                        <td><s:property value="dueDate"/></td>
                    </tr>
                </s:iterator>
            </s:else>
            </tbody>
            <tfoot>
            <s:if test="outstandingTotal!=null">
                <tr>
                    <th class="text-right" colspan="2">Total</th>
                    <th class="text-right"><s:property value="outstandingTotal.totalAmount"/></th>
                    <th class="text-right"><s:property value="outstandingTotal.totalPayment"/></th>
                    <th class="text-right"><s:property value="outstandingTotal.remainningBalance"/></th>
                    <th>&nbsp;</th>
                </tr>
            </s:if>
            </tfoot>
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
                        <li class="disabled"><a><xms:localization text="Previous"/></a></li>
                    </s:if>
                    <s:else>
                        <li>
                            <a href="javascript:changePage(<s:property value="%{outstandingList.currentPage - 1}"/>)"><xms:localization
                                    text="Previous"/></a></li>
                    </s:else>
                    <s:iterator value="outstandingList.pageRange" status="count">
                        <s:if test="%{outstandingList.pageRange[#count.index] == outstandingList.currentPage}">
                            <li class="active"><a><s:property value="outstandingList.currentPage"/></a></li>
                        </s:if>
                        <s:else>
                            <li><a href="javascript:changePage(<s:property/>);"><s:property/></a></li>
                        </s:else>
                    </s:iterator>
                    <s:if test="!outstandingList.hasNext()">
                        <li class="disabled"><a href="#"><xms:localization text="Next"/></a></li>
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