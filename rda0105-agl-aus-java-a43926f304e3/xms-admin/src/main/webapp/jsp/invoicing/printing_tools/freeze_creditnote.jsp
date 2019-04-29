<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div id="wrapper">
    <!--BEGIN SIDEBAR MENU-->
    <div id="page-wrapper">
        <!--BEGIN TITLE & BREADCRUMB PAGE-->
        <div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
            <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
                <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                        text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                <li class="hidden"><a href="#"><xms:localization text="Freeze Credit Notes"/></a>&nbsp;&nbsp;<i
                        class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
                <li class="active"><xms:localization text="Freeze Credit Notes"/></li>
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
                                        <div class="caption"><xms:localization text="Freeze Credit Notes"/></div>
                                    </div>
                                    <div class="portlet-body" style="padding: 0px;">
                                        <div class="tab-content responsive">
                                            <div id="Overview-tab" class="tab-pane fade in active">
                                                <div class="row">
                                                    <div class="col-lg-12" style="height: 500px;">
                                                        <p>
                                                            <xms:localization
                                                                    text="<b>Note:</b> </br> - Before printing or sending email-credit notes should be frozen.</br> Freezing credit notes prevents airbills from being edited, making the</br> customer invoice total permanent.</br> </br> To freeze all credit notes for a date, select a date and Franchise the below drop-down.</br> </br>"/>
                                                            <s:form id="send-credit-by-franchise-code-date">
                                                        <table class="s36">
                                                            <tr>
                                                                <td><b><xms:localization text="Franchise Code"/></b>
                                                                </td>
                                                                <td><s:select name="sendCreditNoteModel.franchiseCode"
                                                                              list="franchiseList" headerKey="All"
                                                                              headerValue="All" cssClass="form-control"
                                                                              listKey="code" listValue="code"
                                                                              onchange="changeFranchiseCode($(this).val())"/></td>
                                                                <td><b><xms:localization text="Date"/></b></td>
                                                                <td id="sel-date-list"><s:select
                                                                        name="sendCreditNoteModel.createDate"
                                                                        cssClass="form-control"
                                                                        list="dateList"></s:select></td>
                                                                <td><a class="btn s37"
                                                                       onclick="doFreezeCreditNoteByFranchiseCodeData()">
                                                                    <xms:localization
                                                                            text="Freeze Credit Notes for this Date"/>
                                                                </a></td>
                                                            </tr>
                                                        </table>
                                                        </s:form>
                                                        </br>
                                                        <s:form id="freeze-by-credit-note-code">
                                                            <table class="s36">
                                                                <tr>
                                                                    <td><b><xms:localization text="Credit Note"/></b>
                                                                    </td>
                                                                    <td><textarea name="creditNoteCode" type="text"
                                                                                  placeholder=""
                                                                                  maxlength="25"></textarea></td>
                                                                    <td><a class="btn s37"
                                                                           onclick="doFreezeByCreditNoteCode()">
                                                                        <xms:localization
                                                                                text="Freeze this Credit Note"/>
                                                                    </a></td>
                                                                </tr>
                                                            </table>
                                                        </s:form>
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
    </div>
</div>
<script type="text/javascript">


    function changeFranchiseCode(franchiseCode) {
        var data = {
            "sendCreditNoteModel.franchiseCode": franchiseCode
        };
        doPostDataByParameters("freeze_credit_notes_change_franchise_code.ix?reqType=json", data, "", "sel-date-list");
    }

    function doFreezeCreditNoteByFranchiseCodeData() {
        var data = $("#send-credit-by-franchise-code-date").serialize();
        doPostDataByParameters("do_freeze_credit_notes.ix?reqType=json", data, "Freeze successful!", "sel-date-list");
    }

    function doFreezeByCreditNoteCode() {
        var data = $("#freeze-by-credit-note-code").serialize();
        doPostDataByParameters("do_freeze_by_credit_note_code.ix?reqType=json", data, "Freeze successful!", "sel-date-list");
    }

</script>
