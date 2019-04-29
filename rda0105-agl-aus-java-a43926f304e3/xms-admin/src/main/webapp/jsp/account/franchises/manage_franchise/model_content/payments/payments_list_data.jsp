<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<div class="col-lg-12 form-group">
    <table style="font-size: 11px; margin-top: 15px;">
        <tr>
            <td colspan="" style="border-top: 0px !important">
                <div class="caption b17">
                    <xms:localization text="Payments"/>
                </div>
            </td>
        </tr>
    </table>
    <table class="table table-bordered mg0">
        <thead>
        <tr>
            <th>&nbsp;</th>
            <th><xms:localization text="Chq #"/></th>
            <th class="text-right"><xms:localization text="Amount"/></th>
            <th class="text-center"><xms:localization text="Date"/></th>
            <th><xms:localization text="Follow Up"/></th>
            <th><xms:localization text="Note"/></th>
        </tr>
        </thead>
        <tbody>
        <s:if test="payments!=null && payments.size()>0">
            <s:iterator value="payments">
                <tr>
                    <td><s:if test="canReverse==1">
                        <a href="javascript:void(0)"
                           onclick='showAddPaymentDialog(<s:property value="invoicePaymentId"/>)'
                           title='<xms:localization text="Reverse Payments (Non-Sufficient Funds)"/>'><i
                                class="fa fa-undo" aria-hidden="true"></i></a>
                    </s:if></td>
                    <td><s:property value="customerPayment.cheque"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property value="amount"/></td>
                    <td class="text-center"><s:property value="customerPayment.paymentDate"/></td>
                    <td><s:if test="customerPayment.notes!=null && customerPayment.notes.size()>0">
                        <s:property value="customerPayment.notes[0].followUpDate"/>
                    </s:if></td>
                    <td><s:if test="customerPayment.notes!=null && customerPayment.notes.size()>0">
                        <s:property value="customerPayment.notes[0].note"/>
                    </s:if></td>
                </tr>
            </s:iterator>
        </s:if>
        <s:else>
            <tr>
                <td colspan="6"><xms:localization text="No data available..."/></td>
            </tr>
        </s:else>
        </tbody>
    </table>
</div>