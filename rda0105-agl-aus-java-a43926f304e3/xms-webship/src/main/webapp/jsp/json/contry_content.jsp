<%@ taglib prefix="s" uri="/struts-tags" %>
<br>
<s:iterator value="countryList">
    <s:property value="countryCode"/> <br>
    <s:property value="countryName"/>
</s:iterator>

