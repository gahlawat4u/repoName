<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="s70">
    <table class="table table-bordered mg0" id="sales_rep_settings_report_table">
        <thead>
        <tr>
            <th colspan="2"><xms:localization text="General Info"/></th>
            <th><xms:localization text="Salary and Allowances"/></th>
            <th><xms:localization text="Payout (%Margin)"/></th>
            <th><xms:localization text="Deductions"/></th>
            <th><xms:localization text="Setups/Activations Goals"/></th>
            <th><xms:localization text="Shipment Goals"/></th>
            <th><xms:localization text="Other"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="salesRepReport==null || salesRepReport.totalRecords==0">
            <tr>
                <td colspan="8"><xms:localization text="No data available..."/></td>
            </tr>
        </s:if>
        <s:else>
            <s:iterator value="salesRepReport.records">
                <tr>
                    <td><xms:localization text="Franchise: "/> <s:property value="user.userCode"/><br/>
                        <xms:localization text="Sales Rep: "/> <s:property value="user.displayName"/></td>
                    <td width="41">
                        <button class="btn s37" onclick="editSalesRepSetting(<s:property value="salesRepId"/>)">
                            <xms:localization text="Edit"/>
                        </button>
                    </td>
                    <td><xms:localization text="Salary: "/> <s:property value="currencySymbol"/> <s:property
                            value="salary"/><br/> <xms:localization text="Vehicle Allow: "/> <s:property
                            value="currencySymbol"/> <s:property value="vehicleAllowance"/><br/> <xms:localization
                            text="Phone Allow: "/> <s:property value="phoneAllowance"/><br/> <xms:localization
                            text="Health Allow: "/> <s:property value="healthAllowance"/><br/> <s:property
                            value="allowance1Description"/> <s:property value="currencySymbol"/> <s:property
                            value="allowance1Amount"/><br/> <s:property value="allowance2Description"/> <s:property
                            value="currencySymbol"/> <s:property value="allowance2Amount"/><br/> <s:property
                            value="allowance3Description"/> <s:property value="currencySymbol"/> <s:property
                            value="allowance3Amount"/></td>
                    <td><s:iterator value="salesRepServices">
                        <s:if test="payout!=0">
                            <s:property value="service.serviceName"/>: <s:property value="payout"/>
                        </s:if>
                        <br/>
                    </s:iterator></td>
                    <td><s:property value="deduction1Description"/> <s:property value="currencySymbol"/> <s:property
                            value="deduction1Amount"/><br/> <s:property value="deduction2Description"/> <s:property
                            value="currencySymbol"/> <s:property value="deduction2Amount"/><br/> <s:property
                            value="deduction3Description"/> <s:property value="currencySymbol"/> <s:property
                            value="deduction3Amount"/></td>
                    <td><xms:localization text="Setups: "/> <s:property value="setups"/><br/> <xms:localization
                            text="Activation: "/> <s:property value="activation"/><br/> <xms:localization
                            text="DayOfActivate: "/> <s:property value="dayOfActivate"/></td>
                    <td><s:iterator value="salesRepServices">
                        <s:if test="goal!=0">
                            <s:property value="service.serviceName"/>: <s:property value="goal"/>
                        </s:if>
                        <br/>
                    </s:iterator></td>
                    <td><xms:localization text="Sales Manager: "/> <s:property value="salesManager"/><br/>
                        <xms:localization text="Sales Manager % Payouts: "/> <s:property value="percentPayout"/></td>
                </tr>
            </s:iterator>
        </s:else>
        </tbody>
    </table>
</div>
<div class="dataTables_paginate records">
    <div class="row">
        <div class="col-xs-4 text-left">
            <b><xms:localization text="Showing"/> <s:property value="salesRepReport.startRecord"/> <xms:localization
                    text="to"/> <s:property value="salesRepReport.endRecord"/> <xms:localization text="of"/> <s:property
                    value="salesRepReport.totalRecords"/> <xms:localization text="entries"/></b>
        </div>
        <div class="col-xs-8">
            <s:if test="salesRepReport!=null">
                <s:if test="salesRepReport.hasPrev()">
                    <a href="javascript:changeSalesRepPage(<s:property value="%{salesRepReport.currentPage - 1}"/>)"
                       class="paginate_button previous"><xms:localization text="Previous"/></a>
                </s:if>
				<span> <s:iterator value="salesRepReport.pageRange" status="count">
                    <s:if test="%{salesRepReport.pageRange[#count.index] == salesRepReport.currentPage}">
                        <a class="paginate_button current"><s:property value="salesRepReport.currentPage"/></a>
                    </s:if>
                    <s:else>
                        <a class="paginate_button"
                           href="javascript:changeSalesRepPage(<s:property/>);"><s:property/></a>
                    </s:else>
                </s:iterator>
				</span>
                <s:if test="salesRepReport.hasNext()">
                    <a class="paginate_button next"
                       href="javascript:changeSalesRepPage(<s:property value="%{salesRepReport.currentPage+1}"/>)"><xms:localization
                            text="Next"/></a>
                </s:if>
            </s:if>
        </div>
    </div>
</div>