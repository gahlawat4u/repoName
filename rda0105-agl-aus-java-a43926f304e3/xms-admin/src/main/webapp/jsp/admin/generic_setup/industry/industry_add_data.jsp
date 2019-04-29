<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="add-carriers" title="New Industry">
    <s:form id="industry_add_form">
        <div class="form-group">
            <p class="">
                <xms:localization text="Industry Name"/>
                <span class="s30">*</span>
            </p>

            <input class="form-control alloptions" type="text" maxlength="25"
                   placeholder="" name="industryModel.industryName">
            <s:hidden name="page"></s:hidden>
            <s:hidden name="pageSize"></s:hidden>
        </div>
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
