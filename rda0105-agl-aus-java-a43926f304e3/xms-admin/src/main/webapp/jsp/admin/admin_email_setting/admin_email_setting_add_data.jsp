<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:form id="admin_email_setting_add_form">
    <div class="form-group">
        <p class="">
            Email :<span class="s30">*</span>
        </p>

        <input class="form-control alloptions" type="text" placeholder=""
               maxlength="25" name="adminEmailModel.email">
    </div>
</s:form>
<script type="text/javascript">
    $('#admin_email_setting_add_form').on('keyup keypress', function (e) {
        var code = e.keyCode || e.which;
        if (code == 13) {
            e.preventDefault();

        }
    });
</script>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>