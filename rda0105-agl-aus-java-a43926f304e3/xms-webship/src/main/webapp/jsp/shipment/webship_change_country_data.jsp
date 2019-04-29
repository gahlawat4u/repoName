<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<label class="control-label" for="inputName">
    <xms:localization text="Carrier"/>
    <span class="s30"> *</span>
</label>
<s:select name="shipmentPage.serviceId" list="serviceList" listKey="serviceId" listValue="serviceName"
          cssClass="form-control" onchange="changeService($(this).val())"/>
<script type="text/javascript">
    $(document).ready(function () {
        changeService("<s:property value="serviceId"/>");
    });
</script>