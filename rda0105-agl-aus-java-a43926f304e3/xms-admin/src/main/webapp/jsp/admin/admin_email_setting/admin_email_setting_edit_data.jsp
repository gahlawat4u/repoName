<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="edit-email" title="Edit Email">
    <div class="form-group">
        <p class="">
            Email :<span class="s30">*</span>
        </p>
        <s:form id="admin_email_setting_edit_form">

            <input type="hidden" name="adminEmailModel.id" value='<s:property value="adminEmailModel.id"/>'/>
            <input type="hidden" name="isEdit" value="1"/>
            <input class="form-control alloptions" type="text" placeholder=""
                   value='<s:property value="adminEmailModel.email"/>' name="adminEmailModel.email">
        </s:form>
    </div>
</div>
<script type="text/javascript">
    $('#admin_email_setting_edit_form').on('keyup keypress', function (e) {
        var code = e.keyCode || e.which;
        if (code == 13) {
            e.preventDefault();
        }
    });
</script>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>