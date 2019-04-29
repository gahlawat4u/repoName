<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<label class="control-label" for="inputName">
    <xms:localization text="Service Type"/>
    <span class="s30"> *</span>
</label>
<s:select name="shipmentPage.shipmentTypeId" list="shipmentTypeList" listKey="shipmentTypeId"
          listValue="shipmentTypeName" cssClass="form-control" onchange="changeShipmentType($(this).val())"/>
<script type="text/javascript">
    $(document).ready(function () {
        changeShipmentType("<s:property value="shipmentTypeId"/>", "<s:property value="prevPackageName"/>");
        loadAdditionalConfig();
    });
</script>