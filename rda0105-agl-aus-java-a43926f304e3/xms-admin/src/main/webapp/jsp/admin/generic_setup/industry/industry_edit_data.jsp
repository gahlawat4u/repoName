<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="edit-carriers" title="Edit Industry">

    <s:form id="industry_edit_form">
        <input type="hidden" name="industryModel.industryId"
               value='<s:property value="industryModel.industryId"/>'/>
        <input type="hidden" name="isEdit" value="1"/>

        <p class="">
            Industry Name:<span class="s30">*</span>
        </p>
        <input class="form-control alloptions" type="text" placeholder=""
               value='<s:property value="industryModel.industryName"/>'
               name="industryModel.industryName">
        <s:hidden name="page"></s:hidden>
        <s:hidden name="pageSize"></s:hidden>
    </s:form>
</div>

<script type="text/javascript">
    $('#industry_add_form').on('keyup keypress', function (e) {
        var code = e.keyCode || e.which;
        if (code == 13) {
            e.preventDefault();

        }
    });
</script>
<s:actionerror/>
<s:actionmessage/>
<s:fielderror/>
