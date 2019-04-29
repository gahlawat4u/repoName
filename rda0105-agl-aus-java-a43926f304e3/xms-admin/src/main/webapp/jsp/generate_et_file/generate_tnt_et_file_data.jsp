<%@taglib uri="/xms-struts-tags" prefix="s" %>
<s:if test="hasActionErrors()">
    <s:actionerror/>
</s:if>
<s:else>
    <s:actionmessage/>
</s:else>