<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<table class="s36 d1">
    <tr>
        <td><xms:localization text="Search"/></td>
        <td><s:textfield cssClass="form-control ss36" name="searchValue" id="txtQuickSearchForm"/></td>
        <td><s:select list="searchTypeList" listValue="typeName" id="searchTypeForm" name="searchType" listKey="id"
                      cssClass="form-control"/></td>
        <td>
            <button class="btn s37" type="button" onclick="doQuickSearchForm()">
                <xms:localization text="Search"/>
            </button>
        </td>
    </tr>
</table>
<br/>

<div id="quickSearchResult">
    <s:if test="detailFilterModel==null">
        <xms:localization text="No shipment found."/>
    </s:if>
    <s:else>
        <div class="col-lg-6">
            <s:hidden id="input_hidden_shipmentId" value="%{detailInfoModel.shipmentId}"/>
            <table class="table" style="font-size: 11px; margin-bottom: 0px">
                <tr>
                    <td class="td1"><xms:localization text="Service Type"/></td>
                    <td class="td2"><s:property value="detailInfoModel.serviceType"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Package Type"/></td>
                    <td class="td2"><s:property value="detailInfoModel.packageType"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Reference"/>#</td>
                    <td class="td2"><s:property value="detailInfoModel.referenceNo"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Zone"/></td>
                    <td class="td2"><s:property value="detailInfoModel.zone"/></td>
                </tr>
            </table>
        </div>
        <div class="col-lg-6">
            <table class="table" style="font-size: 11px; margin-bottom: 0px">
                <tr>
                    <td class="td1"><xms:localization text="Shipment Date"/></td>
                    <td class="td2"><s:property value="detailInfoModel.shipmentDate"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Tracking"/>#</td>
                    <td class="td2"><s:property value="detailInfoModel.tracking"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Confirmation"/>#</td>
                    <td class="td2"><s:property value="detailInfoModel.confirmationNo"/></td>
                </tr>
                <tr>
                    <td class="td1"><xms:localization text="Dim. Weight"/></td>
                    <td class="td2"><s:property value="detailInfoModel.dimWeight"/></td>
                </tr>
            </table>
        </div>
        <div class="col-lg-12">
            <br/> <label> <b><xms:localization text="Package Information"/>:</b>
        </label>
            <hr class="w11">
            <table class="table table-hover table-bordeed mg0" id="datatable1">
                <thead>
                <tr>
                    <th><xms:localization text="Piece"/></th>
                    <th><xms:localization text="Dead weight"/></th>
                    <th><xms:localization text="Cubic weight"/></th>
                    <th><xms:localization text="Dimension"/></th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="detailPieceModels">
                    <tr>
                        <td><s:property value="pieces"/></td>
                        <td><s:property value="deadWeight"/><br/></td>
                        <td><s:property value="cubicWeight"/></td>
                        <td><s:property value="dimension"/></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
            <hr class="w11">
        </div>
        <div class="col-lg-12">
            <hr class="w11">
            <div class="col-lg-6">
                <table class="table table-hover  mg0" id="">
                    <thead>
                    <tr>
                        <th><xms:localization text="Shipper Address"/></th>
                        <th><xms:localization text="Receiver Address"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>${detailInfoModel.sCompanyName}</td>
                        <td>${detailInfoModel.rCompanyName}</td>
                    </tr>
                    <tr>
                        <td>${detailInfoModel.sAddress}</td>
                        <td>${detailInfoModel.rAddress}</td>
                    </tr>
                    <tr>
                        <td>${detailInfoModel.sCity}${detailInfoModel.sPostalCode}${detailInfoModel.sState}</td>
                        <td>${detailInfoModel.rCity}${detailInfoModel.rPostalCode}${detailInfoModel.rState}</td>
                    </tr>
                    <tr>
                        <td>${detailInfoModel.sCountryName}</td>
                        <td>${detailInfoModel.rCountryName}</td>
                    </tr>
                    <tr>
                        <td>Ph: ${detailInfoModel.sPhone}</td>
                        <td>Ph: ${detailInfoModel.rPhone}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-lg-6">
                <table class="table table-hover  mg0" id="">
                    <thead>
                    <tr>
                        <th colspan="2"><xms:localization text="Quote Detail"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>- <xms:localization text="Base Charge"/>:
                        </td>
                        <td><s:property value="detailInfoModel.baseCharge"/></td>
                    </tr>
                    <s:iterator value="detailAccessorialModels">
                        <tr>
                            <td>- <s:property value="description"/>:
                            </td>
                            <td><s:property value="amount"/></td>
                        </tr>
                    </s:iterator>
                    <tr>
                        <td colspan="2"><i><xms:localization
                                text="Quote is an estimate. Additional fees may apply"/>.</i></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <hr class="w11">
        </div>
    </s:else>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('#txtQuickSearchForm').val($('#txtQuickSearch').val());
    });

    function exportDetail() {
        var shipmentId = $("#input_hidden_shipmentId").val();
        //alert(shipmentId);
        var win = window.open("history_export_detail.ix?shipmentId=" + shipmentId, '_blank');
        win.focus();
    }


</script>