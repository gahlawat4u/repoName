<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-12">
        <p class="s38">
            <xms:localization text="Technology Fee Details"/>
        </p>

        <p>
            <s:set var="paramMap" value="#{'{startDate}':'startDate', '{endDate}':'endDate'}"/>
            <xms:localization
                    text="Please Note:</br> - The details below list only invoices > 61 days from invoice due date that had payment/credit activity from {startDate} to {endDate}.</br> - Franchise is paid back 100% of carrier credits or payments that contribute toward the carrier cost</br> - Franchise is paid % of carrier credits or payments that result in a discount profit (amount above the carrier cost)</br> - Franchise is paid 50% of carrier credits or payments above the discount invoice price(list profit)"
                    paramMap="paramMap"/>
        </p>
        <table class="table mg0">
            <tr>
                <th class="s42">
                    <table class="s36">
                        <tbody>
                        <tr>
                            <td><xms:localization text="Show"/></td>
                            <td><s:select list="listPageSize" value="recordSize" cssClass="form-control"
                                          cssStyle="height: 22px; padding-top: 1px; width: 55px;"
                                          id="selPageSizeTechFees" onchange="changePageSelTechFees()"/></td>
                            <td><xms:localization text="Entries"/></td>
                        </tr>
                        </tbody>
                    </table>
                </th>
            </tr>
        </table>
        <table class="table table-hover table-striped table-bordered mg0">
            <thead>
            <tr>
                <th width="100px;"><xms:localization text="Import Date"/></th>
                <th><xms:localization text="Customer No"/></th>
                <th><xms:localization text="Customer Name"/></th>
                <th><xms:localization text="Invoice #"/></th>
                <th><xms:localization text="AWB/Connote No"/></th>
                <th><xms:localization text="International Domestic"/></th>
                <th><xms:localization text="Tech Fee on International Shipments"/></th>
                <th><xms:localization text="Tech Fee on Domestic Shipments"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="techFees.isEmpty()">
                <tr>
                    <td colspan="8"><xms:localization text="No data available..."/></td>
                </tr>
            </s:if>
            <s:else>
                <s:iterator value="techFees">
                    <tr>
                        <td><s:property value="importDate"/></td>
                        <td><s:property value="customerCode"/></td>
                        <td><s:property value="customerName"/></td>
                        <td><s:property value="invoiceCode"/></td>
                        <td><s:property value="airbillNumber"/></td>
                        <td><s:if test="isDomestic=='true'">
                            <xms:localization text="Domestic"/>
                        </s:if> <s:else>
                            <xms:localization text="International"/>
                        </s:else></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="intlShipmentFee"/></td>
                        <td class="text-right"><s:property value="currencySymbol"/><s:property
                                value="domShipmentFee"/></td>
                    </tr>
                </s:iterator>
            </s:else>
            <s:if test="page == pageCount">
                <tr>
                    <th colspan="6"><xms:localization text="Total"/></th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="techFeeTotal.intlShipmentFee"/> (<s:property value="techFeeTotal.intlShipmentCount"/>)
                    </th>
                    <th class="text-right"><s:property value="currencySymbol"/><s:property
                            value="techFeeTotal.domShipmentFee"/> (<s:property value="techFeeTotal.domShipmentCount"/>)
                    </th>
                </tr>
            </s:if>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12">
        <div class="dataTables_paginate">
            <s:if test="%{page > 1}">
                <a href="javascript:changePageTechFees('<s:property value="%{page-1}"/>');"
                   class="paginate_button previous"><xms:localization text="Previous"/></a>
            </s:if>
            <s:iterator var="count" begin="1" end="pageCount">
                <s:if test="%{(page-4) < top && top < (page+4)}">
					<span> <a href="javascript:changePageTechFees('<s:property value="top"/>');"
                              class="paginate_button <s:if test="%{page==top}">current</s:if>"
                              data-targetpage="<s:property value="top"/>"> <s:property value="top"/>
                    </a>
					</span>
                </s:if>
                <s:elseif test="%{top>(page+4) && top<(page+6) && (pageCount - page) >=6}">
                    <span>...</span>
                </s:elseif>
                <s:elseif test="%{top>(pageCount-7)}">
                    <span><a href="javascript:changePageTechFees('<s:property value="top"/>');" class="paginate_button"
                             data-targetpage="<s:property value="top"/>"><s:property value="top"/></a></span>
                </s:elseif>
            </s:iterator>
            <s:if test="%{page < pageCount}">
                <a href="javascript:changePageTechFees('<s:property value="%{page+1}"/>');"
                   class="paginate_button next"><xms:localization text="Next"/></a>
            </s:if>
        </div>
    </div>
</div>
<script type="text/javascript">
    function changePageTechFees(page) {
        var recordSize = $('#selPageSizeTechFees').val();
        getTechFees(page, recordSize);
    }
    function changePageSelTechFees() {
        var recordSize = $('#selPageSizeTechFees').val();
        getTechFees(1, recordSize);
    }
    $(document).ready(function () {
        $('#rptTxnId').val('<s:property value="rptTxnId" />');
        $('#frozen-message').html('<s:property value="frozenMessage"/>');
    });


</script>