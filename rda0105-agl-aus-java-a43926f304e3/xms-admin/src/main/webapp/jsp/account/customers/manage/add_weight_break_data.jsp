<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:property value="htmlString" escape="false"/>

<script type="text/javascript">
    var inpName = '<s:property value="weightBreakInputName"/>';
    var inpValue = '<s:property value="weightBreakModel.requestWeight"/>';
    $("input[name='" + inpName + "']").val(inpValue);


</script>