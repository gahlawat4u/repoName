<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-12">
        <div class="text-left">
            <ul class="pagination mtn mbn" data-hover="">
                <li><span class="lsc"><xms:localization text="Show"/></span></li>
                <li><span class="lsc t1"> <s:select id="selPaidPageSize" name="pageSize" list="pageSizeList"
                                                    value="paidList.pageSize" onchange="changePageSize()"/>
				</span></li>
                <li><span class="lsc"><xms:localization text="entries"/></span></li>
            </ul>
        </div>
        <table class="table table-hover table-bordered mg0" id="paid-table">
            <thead>
            <tr>
                <th><xms:localization text="Invoice No"/></th>
                <th><xms:localization text="Invoice Date"/></th>
                <th class="text-right"><xms:localization text="Total Amount"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="paidList==null || paidList.size()==0">
                <tr>
                    <td colspan="3"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="paidList.records">
                    <tr data-invoice-code="<s:property value="invoiceCode" />">
                        <td><s:property value="invoiceCode"/></td>
                        <td><s:property value="invoiceDate"/></td>
                        <td class="text-right"><s:property value="totalAmount"/></td>
                    </tr>
                </s:iterator>
            </s:else>
            </tbody>
            <s:if test="paidTotal!=null">
                <tfoot>
                <tr>
                    <th class="text-right" colspan="2">Total</th>
                    <th class="text-right"><s:property value="paidTotal.totalAmount"/></th>
                </tr>
                </tfoot>
            </s:if>
        </table>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#selectedId').val("");
                $('table#paid-table tbody tr').click(function () {
                    $(this).addClass('selected-row').siblings().removeClass('selected-row');
                    var selectedInvCode = $(this).attr("data-invoice-code");
                    $('#selectedInvCode').val(selectedInvCode);
                    $('#btn-view-pdf').attr('disabled', false);
                });
            });
        </script>
        <s:if test="paidList.totalPage != 1">
            <div class="text-right">
                <ul class="pagination mts mbs" data-hover="">
                    <s:if test="!paidList.hasPrev()">
                        <li class="disabled"><a><xms:localization text="Previous"/></a></li>
                    </s:if>
                    <s:else>
                        <li>
                            <a href="javascript:changePage(<s:property value="%{paidList.currentPage - 1}"/>)"><xms:localization
                                    text="Previous"/></a></li>
                    </s:else>
                    <s:iterator value="paidList.pageRange" status="count">
                        <s:if test="%{paidList.pageRange[#count.index] == paidList.currentPage}">
                            <li class="active"><a><s:property value="paidList.currentPage"/></a></li>
                        </s:if>
                        <s:else>
                            <li><a href="javascript:changePage(<s:property/>);"><s:property/></a></li>
                        </s:else>
                    </s:iterator>
                    <s:if test="!paidList.hasNext()">
                        <li class="disabled"><a href="#"><xms:localization text="Next"/></a></li>
                    </s:if>
                    <s:else>
                        <li>
                            <a href="javascript:changePage(<s:property value="%{paidList.currentPage + 1}"/>)"><xms:localization
                                    text="Next"/></a></li>
                    </s:else>
                </ul>
            </div>
        </s:if>
    </div>
</div>