<%@taglib uri="/xms-struts-tags" prefix="s" %>
<%@ taglib prefix="decorator"
           uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<xms:localization text="Beginning EDI Downloads"/> <s:property value="beginDownload"/><br/>
<s:if test="results!=null">
    <s:iterator value="results">
        <s:property value="fileName"/><br/>
        <s:if test="errorList!=null">
            <s:iterator value="errorList">
                <s:property/><br/>
            </s:iterator>
        </s:if>
    </s:iterator>
</s:if>
<xms:localization text="End EDI Downloads"/> <s:property value="endDownload"/><br/>