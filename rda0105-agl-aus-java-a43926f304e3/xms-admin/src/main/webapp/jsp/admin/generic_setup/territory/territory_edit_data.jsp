<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="edit-carriers" title="Edit Territory">

    <s:form id="territory_edit_form">
        <input type="hidden" name="territoryModel.territoryId"
               value='<s:property value="territoryModel.territoryId"/>'/>
        <input type="hidden" name="isEdit" value="1"/>

        <p class="">
            <xms:localization text="Territory"/>
            <span class="s30">*</span>
        </p>
        <input class="form-control alloptions" type="text" placeholder=""
               value='<s:property value="territoryModel.territoryName"/>'
               name="territoryModel.territoryName">
        <s:hidden name="page"></s:hidden>
        <s:hidden name="pageSize"></s:hidden>
    </s:form>
</div>

<script type="text/javascript">
    $('#territory_add_form').on('keyup keypress', function (e) {
        var code = e.keyCode || e.which;
        if (code == 13) {
            e.preventDefault();

        }
    });
</script>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>
