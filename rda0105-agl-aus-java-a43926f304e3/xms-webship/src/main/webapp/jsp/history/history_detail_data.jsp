<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>

<div class="col-lg-6">
    <table class="table" style="font-size: 11px; margin-bottom: 0px">
        <tr>
            <td class="td1"><xms:localization text="Service Type"/></td>
            <td class="td2"><s:property value="detailInfoModel.serviceType"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Shipment Date"/></td>
            <td class="td2"><s:property value="detailInfoModel.shipmentDate"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Package Type"/></td>
            <td class="td2"><s:property value="detailInfoModel.packageType"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Tracking"/>#</td>
            <td class="td2"><s:property value="detailInfoModel.tracking"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Contents Description"/>#</td>
            <td class="td2"><s:property value="detailInfoModel.contentDescription"/></td>
        </tr>

    </table>
</div>
<div class="col-lg-6">
    <table class="table" style="font-size: 11px; margin-bottom: 0px">
        <tr>
            <td class="td1"><xms:localization text="Reference"/>#</td>
            <td class="td2"><s:property value="detailInfoModel.referenceNo"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Confirmation"/>#</td>
            <td class="td2"><s:property value="detailInfoModel.confirmationNo"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Zone"/></td>
            <td class="td2"><s:property value="detailInfoModel.zone"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Dim. Weight"/></td>
            <td class="td2"><s:property value="detailInfoModel.dimWeight"/></td>
        </tr>
        <tr>
            <td class="td1"><xms:localization text="Actual. Weight"/></td>
            <td class="td2"><s:property value="detailInfoModel.actualWeight"/></td>
        </tr>
    </table>
</div>
<div class="col-lg-12">
    </br> <label> <b><u><xms:localization text="Package Information"/>:</u></b>
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
    <div class="row">
        <div class="text-right pal pdt10 w6">
            <button type="button" class="btn s33" onclick="javascript:viewAirbill();">View</button>
            <button type="button" class="btn s33" onclick="javascript:trackAirbill();">Track</button>
            <!-- Modal
				<div class="modal fade" id="View-Airbill" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content portlet box">
							<div class="modal-header portlet-header">
								<button type="button" class="close tools" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
								<div class="caption text-left">
									<xms:localization text="View Airbill" />
								</div>
							</div>
							<div class="modal-body text-left">
								<p>
									<b> <xms:localization text="Print Label Instructions" />:
									</b></br> 1.
									<xms:localization text=" 2 copies of this airwaybill using the Print function of your web browser" />
									.</br> 2. Fold the printed waybills.</br> 3. Attach a label to each piece of your shipment, using a DHL plastic pouch. Extra copies should be given to the courier. OR Extra copies should be attached to the first piece of your shipment.
								</p>
							</div> 
							<div class="form-actions text-right pal pdt10 w6">
								<button type="button" class="btn s33">Ok</button>
							</div>
						</div>
					</div>
				</div>
				<!-- End Modal -->
        </div>
    </div>
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
                <td>${detailInfoModel.sContactName}</td>
                <td>${detailInfoModel.rContactName}</td>
            </tr>
            <tr>
                <td>${detailInfoModel.sAddress}</td>
                <td>${detailInfoModel.rAddress}</td>
            </tr>
            <tr>
                <td>${detailInfoModel.sAddress2}</td>
                <td>${detailInfoModel.rAddress2}</td>
            </tr>
            <tr>
                <td>${detailInfoModel.sCity} ${detailInfoModel.sPostalCode} ${detailInfoModel.sState}</td>
                <td>${detailInfoModel.rCity} ${detailInfoModel.rPostalCode} ${detailInfoModel.rState}</td>
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
            <s:if test='!detailInfoModel.baseCharge.equals("0.00")'>
                <tr>
                    <td>- <xms:localization text="Base Charge:"/>
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
            </s:if>
            <s:else>
                <tr>
                    <td><xms:localization text="TBA"/></td>
                </tr>
            </s:else>
            <tr>
                <td colspan="2"><i><xms:localization text="Quote is an estimate. Additional fees may apply"/>.</i></td>
            </tr>
            </tbody>
        </table>
    </div>
    <hr class="w11">
</div>

