<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="add_payment_form">
    <s:hidden name="payment.invoicePaymentId"/>
    <table class="s36">
        <tr>
            <th colspan="2" class="text-center"><xms:localization text="Adding NSF"/></th>
        </tr>
        <tr>
            <td colspan="2">&nbsp;</td>
        </tr>
        <tr>
            <td colspan="2"><span style="color: red"><s:fielderror/></span></td>
        </tr>
        <tr>
            <td><xms:localization text="Invoice Number:"/></td>
            <td id="add_payment_invoice_code"><s:property value="payment.invoiceCode"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Payment Date:"/><span style="color: red">*</span></td>
            <td><s:textfield name="payment.paymentDate" readonly="true" cssClass="form-control"
                             cssStyle="width: 80px;"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Cheque #:"/><span style="color: red">*</span></td>
            <td><s:textfield name="payment.cheque" readonly="true" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Amount:"/><span style="color: red">*</span></td>
            <td><s:textfield name="payment.amount" readonly="true" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td valign="top"><xms:localization text="Note:"/></td>
            <td><s:textarea name="payment.note" cssClass="form-control"/></td>
        </tr>
        <tr>
            <td><xms:localization text="Bank:"/></td>
            <td><s:select name="payment.bankId" list="bankList" listKey="bankId" listValue="bankName"
                          cssClass="form-control"/></td>
        </tr>
    </table>
</form>