<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator"
          uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div id="add-carriers" title="New Territory">
    <s:form id="territory_add_form">
        <div class="form-group">
            <p class="">
                Territory<span class="s30">*</span>
            </p>

            <input class="form-control alloptions" type="text" maxlength="25"
                   placeholder="" name="territoryModel.territoryName">
        </div>
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
