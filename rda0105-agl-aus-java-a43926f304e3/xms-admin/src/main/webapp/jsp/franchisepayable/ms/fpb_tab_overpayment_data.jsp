<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="row">
    <div class="col-lg-12">
        <p class="s38"><xms:localization text="Overpayment Page on Payables"/></p>

        <div class="form-group flr mgb">
            <table class="">
                <tr>
                    <td><s:select list="{10,25,50,100}" value="recordSize"
                                  id="selRecordSizeOverpayment"
                                  onchange="changeRecordSize();" class="form-control"></s:select></td>
                    <input type="hidden" id="paymentMarginCurrPage" value="page">
                </tr>
            </table>
        </div>
        <p><xms:localization text=""/>
        </p>
        <table class="table mg0">
            <tr>
                <th class="s42"></th>
            </tr>
        </table>
        <table class="table table-hover table-striped table-bordered mg0">
            <thead>
            <tr>
                <th><xms:localization text="Original Payment Data"/></th>
                <th><xms:localization text="Customer No"/></th>
                <th><xms:localization text="Customer Name"/></th>
                <th><xms:localization text="Overpayment Type"/></th>
                <th><xms:localization text="Amount"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="%{overpayment.isEmpty()}">
                <tr>
                    <td colspan="5"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="overpayment">
                    <tr>
                        <td><s:property value="originPaymentDate"/></td>
                        <td><s:property value="customerNumber"/></td>
                        <td><s:property value="customerName"/></td>
                        <td><s:property value="overpaymentType"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property value="amount"/></td>
                    </tr>
                </s:iterator>
                <s:if test="%{page == pageCount}">
                    <tr>
                        <th colspan="4"><xms:localization text="TOTAL"/></th>
                        <th class="text-right"><s:property value="currencySymbol"/><s:property
                                value="overpaymentTotal.amount"/></th>
                    </tr>
                </s:if>
            </s:else>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <div class="col-xs-2 pull-right">
            <select class="form-control" id="selPageOverpayment"
                    onchange="changePageSelOverpayment()">
                <option value=""><xms:localization text="Select Page"/></option>
                <s:iterator var="count" begin="1" end="pageCount">
                    <option
                            <s:if test="%{page==top}">selected</s:if>
                            value="<s:property value="top"/>"><s:property value="top"/></option>
                </s:iterator>
            </select>
        </div>
        <div class="col-xs-10">
            <div class="dataTables_paginate">
                <s:if test="%{page > 1}">
                    <a
                            href="javascript:changePageOverpayment('<s:property value="%{page-1}"/>');"
                            class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
                <s:iterator var="count" begin="1" end="pageCount">
                    <s:if test="%{(page-4) < top && top < (page+4)}">
						<span> <a
                                href="javascript:changePageOverpayment('<s:property value="top"/>');"
                                class="paginate_button <s:if test="%{page==top}">current</s:if>"
                                data-targetpage="<s:property value="top"/>"> <s:property
                                value="top"/>
                        </a>
						</span>
                    </s:if>
                    <s:elseif
                            test="%{top>(page+4) && top<(page+6) && (pageCount - page) >=6}">
                        <span>...</span>
                    </s:elseif>
                    <s:elseif test="%{top>(pageCount-7)}">
						<span><a
                                href="javascript:changePageOverpayment('<s:property value="top"/>');"
                                class="paginate_button"
                                data-targetpage="<s:property value="top"/>"><s:property
                                value="top"/></a></span>
                    </s:elseif>
                </s:iterator>
                <s:if test="%{page < pageCount}">
                    <a
                            href="javascript:changePageOverpayment('<s:property value="%{page+1}"/>');"
                            class="paginate_button next"><xms:localization text="Next"/></a>
                </s:if>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        function changePageOverpayment(page) {
            var recordSize = $('#selRecordSizeOverpayment').val();
            getOverpayment(page, recordSize);
        }
        function changePageSelOverpayment() {
            var recordSize = $('#selRecordSizeOverpayment').val();
            var page = $('#selPageOverpayment').val();
            if (page == null || page == '') {
                page = 1;
            }
            getOverpayment(page, recordSize);
        }
        $(document).ready(function () {
            $('#rptTxnId').val('<s:property value="rptTxnId" />');
            $('#frozen-message').html('<s:property value="frozenMessage"/>');
        });
    </script>
</div>