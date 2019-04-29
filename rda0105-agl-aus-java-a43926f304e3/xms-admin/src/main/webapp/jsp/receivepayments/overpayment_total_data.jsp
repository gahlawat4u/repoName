<%@ taglib prefix="s" uri="/struts-tags" %>
<s:if test="%{fieldErrors.isEmpty()}">
    <span id="lbl_unapplied_amount"><s:property value="receivePayment.unappliedAmount"/></span> <s:hidden
        name="receivePayment.unappliedAmount" id="hid_unapplied_amount"/>
</s:if>
<s:else>
    <s:fielderror/>
</s:else>