<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="stat_webship_log" style="font-size: 11px; padding: 10px">
    <s:if test="webshipLogs==null || webshipLogs.size()==0">
    </s:if>
    <s:else>
        <s:iterator value="webshipLogs">
            <span><s:property value="logInfo"/></span><br/>
        </s:iterator>
    </s:else>
</div>