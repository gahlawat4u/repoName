<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<form id="frmCreditNotes">
    <s:hidden name="noteType"/>
    <s:hidden name="adjustmentRequestId"/>
    <div class="form-group">
        <textarea name="note" class="form-control alloptions ckeditor s7" id="credit-note-editor" cols="80"
                  style="height: 100px;"><s:property value="note"/></textarea>
    </div>
</form>
<script type="text/javascript">
    CKEDITOR.replace('credit-note-editor', {
        toolbar: []
    });
    CKEDITOR.on("instanceReady", function (event) {
        $(".cke_top").addClass("b9");
        $(".cke_bottom").addClass("b9");
    });
</script>