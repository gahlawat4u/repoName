<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li><xms:localization text="Admin"/>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li><xms:localization text="Generic Setup"/>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Email Template"/></li>
    </ol>
    <div class="clearfix"></div>
</div>
<!--END TITLE & BREADCRUMB PAGE-->
<!--BEGIN CONTENT-->
<div class="page-content">
    <div id="tab-general">
        <div class="row mbl">
            <div class="col-lg-12">
                <div class="col-md-12">
                    <div id="area-chart-spline" style="width: 100%; height: 300px; display: none;"></div>
                </div>
            </div>
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="portlet box">
                            <div class="portlet-header">
                                <div class="caption">
                                    <xms:localization text="Email Template"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div class="tab-pane fade in active">
                                        <div class="row">
                                            <form id="email_template_form">
                                                <div class="col-lg-5">
                                                    <table class="table" style="font-size: 11px;">
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Template Name"/>:
                                                                <span class="s30">*</span></td>
                                                            <td class="td2" colspan="2"><s:select id="emailTemplateId"
                                                                                                  name="emailTemplate.templateId"
                                                                                                  list="emailTemplateList"
                                                                                                  listKey="templateId"
                                                                                                  listValue="templateName"
                                                                                                  cssClass="form-control"
                                                                                                  onchange="loadEmailTemplate()"/><span
                                                                    class="text-danger"><s:fielderror
                                                                    fieldName="emailTemplate.templateId"/></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization text="Subject"/>: <span
                                                                    class="s30">*</span></td>
                                                            <td class="td2" colspan="2"><s:textfield
                                                                    name="emailTemplate.subject"
                                                                    class="form-control alloptions"/><span
                                                                    class="text-danger"><s:fielderror
                                                                    fieldName="emailTemplate.subject"/></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="td1"><xms:localization
                                                                    text="Testing for credit card email"/> :
                                                            </td>
                                                            <td class="td2" colspan="2"><s:textarea
                                                                    name="emailTemplate.templateContent"
                                                                    class="form-control" rows="20"></s:textarea><span
                                                                    class="text-danger"><s:fielderror
                                                                    fieldName="emailTemplate.templateContent"/></span>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </div>
                                                <div class="col-lg-4">
                                                    <xms:localization
                                                            text="For email template, you can use the following variables:"/>
                                                    <br/>
                                                    <s:property value="emailTemplate.variable" escapeHtml="false"/>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="form-actions pal pdt10">
                                        <div class="row">
                                            <div class="col-lg-12 text-right">
                                                <button class="btn s37" type="button" onclick="saveEmailTempate()">
                                                    <xms:localization text="Save"/>
                                                </button>
                                                <button class="btn s37" type="reset">
                                                    <xms:localization text="Cancel"/>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!--END CONTENT-->

<script type="text/javascript">
    function loadEmailTemplate() {
        var data = {
            "templateId": $("#emailTemplateId option:selected").val()
        };
        loadingDialog.dialog("open");
        $.post("email_template_load.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#email_template_form").replaceWith(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function saveEmailTempate() {
        var data = $("#email_template_form").serialize();
        loadingDialog.dialog("open");
        $.post("email_template_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "FIELD_ERROR") {
                $("#email_template_form").replaceWith(res.content);
            } else if (res.errorCode == "SUCCESS") {
                messageDialog.html('<xms:localization text="Saved successfully." />');
                messageDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>