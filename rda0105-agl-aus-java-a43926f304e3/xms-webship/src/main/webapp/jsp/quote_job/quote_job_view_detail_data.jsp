<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/xms-tags" prefix="xms" %>
<div class="row" style="width: 800px;">
    <div class="col-lg-6">
        <table class="table" style="font-size: 11px; margin-bottom: 0px">
            <tr>
                <td class="td1"><xms:localization text="Service Type"/></td>
                <td class="td2"><s:property value="quoteJob.shipmentType.shipmentTypeName"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Package Type"/></td>
                <td class="td2"><s:property value="quoteJob.packageType.packageName"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Reference#"/></td>
                <td class="td2"><s:property value="quoteJob.customerCode"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Zone"/></td>
                <td class="td2"><s:property value="quoteJob.zone"/></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-6">
        <table class="table" style="font-size: 11px; margin-bottom: 0px">
            <tr>
                <td class="td1"><xms:localization text="Quote Date"/></td>
                <td class="td2"><s:property value="quoteJob.quoteDate"/></td>
            </tr>

            <tr>
                <td class="td1"><xms:localization text="Job Number"/></td>
                <td class="td2"><s:property value="quoteJob.quoteNumber"/></td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Acutal weight"/></td>
                <td class="td2"><s:property value="quoteJob.actualWeight"/> <s:property
                        value="quoteJob.weightUnit"/>(s)
                </td>
            </tr>
            <tr>
                <td class="td1"><xms:localization text="Dim. Weight"/></td>
                <td class="td2"><s:property value="quoteJob.dimWeight"/> <s:property value="quoteJob.weightUnit"/>(s)
                </td>
            </tr>
        </table>
    </div>
    <div class="col-lg-12">
        </br>
        <label>
            <b><u><xms:localization text="Package Information:"/></u></b>
        </label>
        <hr class="w11">
        <table class="table table-hover table-bordeed mg0" id="datatable1">
            <thead>
            <tr>
                <th><xms:localization text="Piece"/></th>
                <th><xms:localization text="Dead weight"/></th>
                <th><xms:localization text="Cubic weight"/></th>
                <th><xms:localization text="Quantity"/></th>
                <th><xms:localization text="Dimension"/></th>
            </tr>
            </thead>
            <tbody>
            <s:iterator value="quoteJob.quotePieces" status="count">
                <tr>
                    <td><s:property value="#count.count"/></td>
                    <td><s:property value="weight"/> <s:property value="quoteJob.weightUnit"/>(s)</td>
                    <td><s:property value="cubicWeight"/> <s:property value="quoteJob.weightUnit"/>(s)</td>
                    <td><s:property value="quantity"/></td>
                    <td><s:property value="dimensionL"/> x <s:property value="dimensionW"/> x <s:property
                            value="dimensionH"/> <s:property value="quoteJob.dimensionUnit"/>(s)
                    </td>
                </tr>
            </s:iterator>
            </tbody>
        </table>
        <hr class="w11">
        <div class="row">
            <div class="text-right pal pdt10 w6"></div>
        </div>
    </div>
    <div class="col-lg-12">
        <hr class="w11">
        <div class="col-lg-6">
            <table class="table table-hover  mg0">
                <thead>
                <tr>
                    <th><xms:localization text="Shipper Address"/></th>
                    <th><xms:localization text="Receiver Address"/></th>
                </tr>
                </thead>
                <tbody>
                <td>
                    <table>
                        <tr>
                            <td><s:property value="quoteJob.senderAddress.companyName"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.senderAddress.contactName"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.senderAddress.address"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.senderAddress.address2"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.senderAddress.city"/>&nbsp;<s:property
                                    value="quoteJob.senderAddress.postalCode"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.senderAddress.countryDetail.countryName"/></td>
                        </tr>
                        <tr>
                            <td>Ph: <s:property value="quoteJob.senderAddress.phone"/></td>
                        </tr>
                    </table>
                </td>
                <td>
                    <table>
                        <tr>
                            <td><s:property value="quoteJob.receiverAddress.companyName"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.receiverAddress.contactName"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.receiverAddress.address"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.receiverAddress.address2"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.receiverAddress.city"/>&nbsp;<s:property
                                    value="quoteJob.receiverAddress.postalCode"/></td>
                        </tr>
                        <tr>
                            <td><s:property value="quoteJob.receiverAddress.countryDetail.countryName"/></td>
                        </tr>
                        <tr>
                            <td>Ph: <s:property value="quoteJob.receiverAddress.phone"/></td>
                        </tr>
                    </table>
                </td>
                </tbody>
            </table>
        </div>
        <div class="col-lg-6">
            <s:form id="formUpdatequoteJob">
                <table class="table table-hover  mg0" id="">
                    <thead>
                    <tr>
                        <th><xms:localization text="Quote Detail"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td colspan="2" width="60%">- <xms:localization text="Base Charge"/>:
                        </td>
                        <td colspan="2"><s:hidden value="%{quoteJob.quoteId}"
                                                  name="webshipQuoteLogModel.quoteId"></s:hidden> <s:property
                                value="%{quoteJob.baseCharge}"/></td>
                    </tr>
                    <s:iterator value="quoteJob.quoteLogDetails" status="rowStatus">
                        <tr>
                            <td colspan="2" width="60%">- <s:property value="accessorial.description"/>:
                            </td>
                            <s:hidden value="%{quoteJob.quoteId}"
                                      name="listWebshipQuoteLogDetailModels[%{#rowStatus.index}].quoteId"></s:hidden>
                            <td colspan="2"><s:property value="%{amount}"/>
                        </tr>
                    </s:iterator>
                    <tr>
                        <td colspan="2" width="60%">- <xms:localization text="Total Charges"/>:
                        </td>
                        <td colspan="2"><s:property value="quoteJob.totalCharge"/></td>
                    </tr>
                    <tr>
                        <td colspan="4"><i><xms:localization
                                text="Quote is an estimate. Additional fees may apply."/></i></td>
                    </tr>
                    </tbody>
                </table>
            </s:form>
        </div>
        <hr class="w11">
    </div>
</div>