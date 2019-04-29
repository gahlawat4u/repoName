<%@page import="com.gms.xms.common.constants.AppConstants" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="col-lg-12">
    <p>
        <b><xms:localization text="Note:</b><br>
		- Double-click a customer to apply overpayments."/>
    </p>
    <table class="table mg0">
        <tr>
            <td class="s42"><span style="margin-left: 10px;"><xms:localization text="Show"/></span> <s:i18n_select
                    name="filter.pageSize" list="pageSizeList" cssClass="s52" onchange="changePageSize();"
                    i18nitem="no"/> <span><xms:localization text="entries"/></span></td>
        </tr>
    </table>
    <table class="table table-bordered  table-hover mg0 " id="datatable1">
        <thead>
        <tr>
            <th><xms:localization text="Franchise #"/></th>
            <th><xms:localization text="Customer #"/></th>
            <th><xms:localization text="Customer Name"/></th>
            <th><xms:localization text="Total Overpayments"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="%{overpaymentList==null || overpaymentList.isEmpty()}">
            <tr>
                <td colspan="6"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="overpaymentList.records">
                <tr <%
                    if (AppConstants.APP_SETTINGS.getFanchiseReceivePayment()) {
                %>
                        <s:if test="userLevel<=3"> onclick="showDialog($(this))"</s:if>
                        <%} else { %>
                        <s:if test="userLevel<3"> onclick="showDialog($(this))"</s:if>
                        <% }%>
                        >
                    <s:hidden id="customer-code" value="%{customerCode}"></s:hidden>
                    <s:hidden id="source" value="%{source}"></s:hidden>
                    <s:hidden id="customer-name" value="%{customerName}"></s:hidden>
                    <td><s:property value="franchiseCode"/></td>
                    <td><s:property value="customerCode"/></td>
                    <td><s:property value="customerName"/></td>
                    <td align="right"><s:property value="totalOverpayment"/></td>
                </tr>
            </s:iterator>
        </s:else>
        <tr>
            <td colspan="6"><b><xms:localization text="Total:"/></b> <s:property
                    value="%{totalOverpayment==null?0:totalOverpayment}"/> <xms:localization text="overpaid"/></td>
        </tr>
        <tr>
            <td colspan="6"><b><xms:localization text="Total:"/></b> <s:property
                    value="%{recordCount==null?0:recordCount}"/> <xms:localization text="overpayment"/></td>
        </tr>
        </tbody>
    </table>
</div>
<div class="col-lg-12">
    <div class="dataTables_paginate">
        <s:if test="%{overpaymentList!=null && overpaymentList.totalRecords>0}">
            <a href="javascript:changePage(1);" class="paginate_button previous"><xms:localization text="First"/></a>
            <s:iterator value="overpaymentList.pageRange" status="count">
                <s:if test="%{overpaymentList.pageRange[#count.index] == overpaymentList.currentPage}">
                    <span><a href="#" class="paginate_button current active"><s:property
                            value="overpaymentList.currentPage"/></a> <input type="hidden"
                                                                             value='<s:property value="overpaymentList.currentPage" />'
                                                                             id='current_page'/> </span>
                </s:if>
                <s:else>
                    <span><a href='javascript:changePage(<s:property />);'
                             class="paginate_button"><s:property/></a></span>
                </s:else>
            </s:iterator>
            <a href="javascript:changePage(<s:property value='%{overpaymentList.totalPage}'/>);"
               class="paginate_button next"><xms:localization text="Last"/></a>
        </s:if>
    </div>
</div>