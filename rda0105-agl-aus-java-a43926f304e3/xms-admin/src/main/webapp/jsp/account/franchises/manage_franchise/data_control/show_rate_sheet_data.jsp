<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-12 ">
        <p class="s38 text-center">
            <xms:localization text='Rate Sheet for'/>
            <s:property value="sheetName"/>
        </p>

        <div class="form-group flr">
            <table class="">
                <tr>
                    <td><select class="form-control">
                        <option><xms:localization text="Option"/></option>
                        <option value="0"><xms:localization text="Print"/></option>
                        <option value="1"><xms:localization text="Import"/></option>
                    </select></td>
                </tr>
            </table>
        </div>
        <table class="s36 b24">
            <tr>
                <td colspan="2"><b><xms:localization text="Value In AUD"/> </b></td>
            </tr>
            <tr>
                <td><input type="checkbox" value=""></td>
                <td><xms:localization text="Show Margin Analysis"/></td>
            </tr>
        </table>

        <s:if test="rateSheet.getShipmentType().getServiceId() == 3">
            <table class="table mg0">
                <tr>
                    <th class="s42">
                        <table class="s36">
                            <tbody>
                            <tr>
                                <td><xms:localization text="Option"/><xms:localization text="Show"/>Show</td>
                                <td><s:select list="listPageSize" value="25" cssClass="form-control"
                                              cssStyle="height: 22px; padding-top: 1px; width: 55px;"></s:select></td>
                                <td><xms:localization text="Option"/><xms:localization text="Entries"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </th>
                </tr>
            </table>
            <table class="table table-hover table-striped table-bordered mg0" id="datatable">
                <thead>
                <tr>
                    <th><xms:localization text="Destination"/></th>
                    <th><xms:localization text="Min"/></th>
                    <th><xms:localization text="Base"/></th>
                    <th><xms:localization text="Per"/></th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="rateSheet.rateSheetDetails" status="details">
                    <tr>
                        <td><s:property value="rateSheet.shipmentType.carrierZones[#details.index].zoneName"/> -
                            <s:property value="rateSheet.shipmentType.carrierZones[#details.index].zoneCode"/></td>
                        <td><s:property value="minCharge"/></td>
                        <td><s:property value="baseCharge"/></td>
                        <td><s:property value="perKg"/></td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:else>
            <s:if test="rateSheet != null">
                <table class="table mg0">
                    <tr>
                        <th class="s42">
                            <table class="s36">
                                <tbody>
                                <tr>
                                    <td><xms:localization text="Option"/><xms:localization text="Show"/></td>
                                    <td><s:select list="listPageSize" value="25" cssClass="form-control"
                                                  cssStyle="height: 22px; padding-top: 1px; width: 55px;"></s:select></td>
                                    <td><xms:localization text="Entries"/></td>
                                </tr>
                                </tbody>
                            </table>
                        </th>
                    </tr>
                </table>
                <table class="table table-hover table-striped table-bordered mg0" id="datatable">
                    <thead>
                    <tr>
                        <th><xms:localization text="Weight"/></th>
                        <s:iterator value="rateSheet.rateSheetCols">
                            <th><s:property value="columnName"/></th>
                        </s:iterator>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="rateSheet.rateSheetRows" status="rows">
                        <tr>
                            <td><s:property value="rowName"/></td>
                            <s:iterator value="rateSheet.rateSheetCols" status="cols">
                                <s:iterator value="rateSheet.rateSheetDetails" status="details">
                                    <s:if test="rateSheet.rateSheetRows[#rows.index].rowId == rateSheet.rateSheetDetails[#details.index].rowId and rateSheet.rateSheetCols[#cols.index].columnId == rateSheet.rateSheetDetails[#details.index].columnId">
                                        <td><s:property value="rateSheet.rateSheetDetails[#details.index].value"/></td>
                                    </s:if>
                                </s:iterator>
                            </s:iterator>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </s:if>

            <s:if test="rateSheetPer != null">
                <div class="col-lg-12 "></div>
                <p>
                    <s:set name="lastRow" value="rateSheet.rateSheetRows[rateSheet.rateSheetRows.size()-1]"/>
                    <b><xms:localization text="Non-Document above"/> <s:property value="#lastRow.rowName"/>
                        <xms:localization text="kg (Multiply shipment weight by zone rate)"/> </b>
                </p>
                <table class="table table-hover table-striped table-bordered mg0" id="datatable">
                    <thead>
                    <tr>
                        <th><xms:localization text="Weight"/></th>
                        <s:iterator value="rateSheetPer.rateSheetCols">
                            <th><s:property value="columnName"/></th>
                        </s:iterator>
                    </tr>
                    </thead>
                    <tbody>
                    <s:iterator value="rateSheetPer.rateSheetRows" status="rows">
                        <tr>
                            <td><xms:localization text="Per Kg"/> <s:property value="rowName"/> <xms:localization
                                    text="Kgs"/>
                            </td>
                            <s:iterator value="rateSheetPer.rateSheetCols" status="cols">
                                <s:iterator value="rateSheetPer.rateSheetDetails" status="details">
                                    <s:if test="rateSheetPer.rateSheetRows[#rows.index].rowId == rateSheetPer.rateSheetDetails[#details.index].rowId and rateSheetPer.rateSheetCols[#cols.index].columnId == rateSheetPer.rateSheetDetails[#details.index].columnId">
                                        <td><s:property
                                                value="rateSheetPer.rateSheetDetails[#details.index].value"/></td>
                                    </s:if>
                                </s:iterator>
                            </s:iterator>
                        </tr>
                    </s:iterator>
                    </tbody>
                </table>
            </s:if>
        </s:else>


    </div>
    <div class="col-lg-12">
        <p>
            <b><xms:localization text="Note"/>: </b><br/> -
            <xms:localization text="All rates are stated in AUD exclusive of GST and all surcharges"/>
            .<br/> -
            <xms:localization text="Rates for specific weights not shown on the table above are available on request"/>
            .
        </p>
    </div>
</div>
<!--Modal End-->
