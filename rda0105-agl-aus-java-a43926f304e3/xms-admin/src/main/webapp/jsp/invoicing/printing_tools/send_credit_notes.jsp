<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<!--BEGIN TITLE & BREADCRUMB PAGE-->
<div id="title-breadcrumb-option-demo" class="page-title-breadcrumb">
    <ol class="breadcrumb page-breadcrumb pull-left" style="padding-top: 0px;">
        <li><i class="fa fa-home"></i>&nbsp;<a href="<%=WebUtils.getHomeUrl(request)%>"><xms:localization
                text="Home"/></a>&nbsp;&nbsp;<i class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="hidden"><a href="#"><xms:localization text="Send Credit Notes"/></a>&nbsp;&nbsp;<i
                class="fa fa-angle-right"></i>&nbsp;&nbsp;</li>
        <li class="active"><xms:localization text="Send Credit Notes"/></li>
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
                                    <xms:localization text="Send Credit Notes"/>
                                </div>
                            </div>
                            <div class="portlet-body" style="padding: 0px;">
                                <div class="tab-content responsive">
                                    <div id="Overview-tab" class="tab-pane fade in active">
                                        <div class="row">
                                            <div class="col-lg-12" style="height: 500px;">
                                                <p>
                                                    <b>
                                                        <xms:localization text="Note:</b> <br />
													- Before sending credit notes, credit notes must be frozen.<br /> If you do not see a date here that you would expect, because<br /> a) the credit notes have not been frozen or<br /> b) the credit notes have already been sent.<br /> <br /> To send all credit notes for a date, select a date and Franchise the below drop-down."/>
                                                        <br/> <br/>
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
                                                                       onclick="doSendCreditNoteByFranchiseCodeData()">
                                                                    <xms:localization
                                                                            text="Send E-mail Credit Notes for this Date"/>
                                                                </a></td>
                                                            </tr>
                                                        </table>
                                                        </s:form>
                                                        <br/>
                                                        <s:form id="send-credit-note-by-credit-code">
                                                        <table class="s36">
                                                            <tr>
                                                                <td><b><xms:localization text="Credit Note"/></b></td>
                                                                <td><s:textarea
                                                                        name="sendCreditNoteModel.creditNoteCode"
                                                                        cssClass="form-control"/></td>
                                                                <td><a class="btn s37"
                                                                       onclick="doSendCreditNoteByCreditCode()">
                                                                    <xms:localization
                                                                            text="Send E-mail this Credit Note"/>
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
<script type="text/javascript">
    function changeFranchiseCode(franchiseCode) {
        var data = {
            "sendCreditNoteModel.franchiseCode": franchiseCode
        };
        doPostDataByParameters("send_credit_notes_change_franchise_code.ix?reqType=json", data, "", "sel-date-list");
    }

    function doSendCreditNoteByFranchiseCodeData() {
        var data = $("#send-credit-by-franchise-code-date").serialize();
        doPostDataByParameters("do_send_credit_notes_by_franchise_code.ix?reqType=json", data, "Send successful!", "sel-date-list");
    }

    function doSendCreditNoteByCreditCode() {
        var data = $("#send-credit-note-by-credit-code").serialize();
        doPostDataByParameters("do_send_credit_notes_by_credit_code.ix?reqType=json", data, "Send successful!", "sel-date-list");
    }


</script>