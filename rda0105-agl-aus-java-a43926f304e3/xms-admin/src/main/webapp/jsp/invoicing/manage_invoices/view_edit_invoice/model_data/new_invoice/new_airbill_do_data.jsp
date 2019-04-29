<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script type="text/javascript">
    $(document).ready(function () {
        window.location.href = "view_edit_invoice.ix?" + "<s:property value="urlParams" escapeHtml="false" />";
    });
</script>