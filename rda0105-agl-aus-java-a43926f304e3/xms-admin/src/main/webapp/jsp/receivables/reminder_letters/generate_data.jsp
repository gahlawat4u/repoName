<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<form id="email_invoices_form">
    <s:iterator value="emailInvoices">
        <input type="hidden" name="emailList" value='<s:property value="invoiceId" />'/>
    </s:iterator>
    <table class="s36 b24">
        <tbody>
        <tr>
            <td class="caption b17" colspan="3"><xms:localization text="E-mail Letters:"/></td>
        </tr>
        <tr>
            <td colspan="3"><s:i18n_select id="emailInvoice" name="emailInvoice" list="emailInvoices"
                                           listValue="invoiceCode" listKey="invoiceId" headerKey="" headerValue="Select"
                                           cssClass="form-control" i18nitem="no" cssStyle="width: 200px"/></td>
        </tr>
        <tr>
            <td colspan="3">
                <button id="btnSendSelected" class="btn s37" type="button"
                        <s:if test="emailInvoices==null || emailInvoices.size()==0">disabled="disabled"</s:if>
                        onclick="sendInv()">
                    <xms:localization text="Send E-mail Letter for Selected Customer"/>
                </button>
            </td>
        </tr>
        <tr>
            <td colspan="3">
                <p>
                    <i>Eg: first@email.com; second@email.com</i>
                </p>
            </td>
        </tr>
        <tr>
            <td><xms:localization text="Email Address:"/></td>
            <td><s:textfield id="testEmail" name="testEmail" cssClass="form-control"/></td>
            <td>
                <button id="btnSendTest" class="btn s37" type="button"
                        <s:if test="emailInvoices==null || emailInvoices.size()==0">disabled="disabled"</s:if>
                        onclick="sendPreview()">
                    <xms:localization text="Send Test Preview Email"/>
                </button>
            </td>
        </tr>
        <tr>
            <td colspan="3"><p>
                <i><xms:localization
                        text="Note: E-mail reminder letters will be marked as 'sent' once the button below is clicked"/></i>
            </p>
                <button id="btnSendAll" class="btn s37" type="button"
                        <s:if test="emailInvoices==null || emailInvoices.size()==0">disabled="disabled"</s:if>
                        onclick="sendAll()">
                    <xms:localization text="Send E-mail letters for All Customers in List"/>
                </button>
            </td>
        </tr>
        </tbody>
    </table>
</form>
<hr>
<form id="print_invoices_form">
    <s:iterator value="printInvoices">
        <input type="hidden" name="printList" value='<s:property value="invoiceId" />'/>
    </s:iterator>
    <table class="s36 b24">
        <tbody>
        <tr>
            <td class="caption b17" colspan="3"><xms:localization text="Print Letters:"/></td>
        </tr>
        <tr>
            <td><s:i18n_select id="printInvoice" name="printInvoice" list="printInvoices" listValue="invoiceCode"
                               listKey="invoiceId" headerKey="" headerValue="Select" cssClass="form-control"
                               i18nitem="no" cssStyle="width: 200px"/></td>
        </tr>
        <tr>
            <td>
                <button id="btnPrintSelected" class="btn s37" type="button"
                        <s:if test="printInvoices==null || printInvoices.size()==0">disabled="disabled"</s:if>
                        onclick="printInv()">
                    <xms:localization text="Print Letter for Selected Customer"/>
                </button>
            </td>
        </tr>
        <tr>
            <td><p>
                <i><xms:localization
                        text="Note: E-mail reminder letters will be marked as 'sent' once the button below is clicked"/> </i>
            </p>
                <button id="btnPrintAll" class="btn s37" type="button"
                        <s:if test="printInvoices==null || printInvoices.size()==0">disabled="disabled"</s:if>
                        onclick="printAll()">
                    <xms:localization text="Print Letters for All Customers in List"/>
                </button>
            </td>
        </tr>
        </tbody>
    </table>
</form>