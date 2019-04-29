<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<s:iterator value="periodList">
    <option><s:property value="startDate"/> - <s:property value="endDate"/></option>
</s:iterator>
<script type="text/javascript">
    $(document).ready(function () {
        changeDateRange();
    });
</script>