<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<label class="control-label" for="inputName">
    <xms:localization text="Package Type"/>
</label>
<s:select name="shipmentPage.packageId" list="packageList" listKey="packageId" listValue="packageName"
          cssClass="form-control" onchange="changePackage()"/>
<script type="text/javascript">
    $(document).ready(function () {
        changePackage("<s:property value="packageId"/>", "<s:property value="shipmentTypeId"/>");
        loadAdditionalConfig();
    });
</script>
