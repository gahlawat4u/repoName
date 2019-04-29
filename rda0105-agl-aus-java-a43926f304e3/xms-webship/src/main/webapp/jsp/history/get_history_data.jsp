<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<table class="table mg0">
    <tr>
        <th class="s42">
            <div class="col-xs-4 pull-left">
                <s:select list="{5,10,25,50,100}" value="shipmentList.pageSize" id="selRecordSizeHistory"
                          onchange="changePageSize()">
                </s:select>

            </div>
        </th>
    </tr>
</table>
<div class="col-lg-12 pd0 mg0" style="min-width: 10px !important; overflow: auto">
    <table class="table table-hover table-striped table-bordered mg0" id="listHistoryTable">
        <thead>
        <tr>
            <th><xms:localization text="Carrier"/></th>
            <th><xms:localization text="Voided"/></th>
            <th><xms:localization text="Tracking"/>#</th>
            <th><xms:localization text="Date"/></th>
            <th><xms:localization text="Ship Date"/></th>
            <th><xms:localization text="Pieces"/></th>
            <th><xms:localization text="Service"/></th>
            <th class="th_package"><xms:localization text="Package"/></th>
            <th><xms:localization text="Weight"/></th>
            <th class="th_dimensions"><xms:localization text="Dimension"/></th>
            <th><xms:localization text="Quoted"/></th>
            <th><xms:localization text="Insured Amount"/></th>
            <th><xms:localization text="Scheduled"/></th>
            <th><xms:localization text="Collection Information"/></th>
            <th class="th_shipment_reference"><xms:localization text="Carrier"/></th>
            <th class="th_billing_party"><xms:localization text="Billing Party"/></th>
            <th class="th_sender_company"><xms:localization text="Sender Company"/></th>
            <th class="th_sender_contact"><xms:localization text="Sender Contact"/></th>
            <th class="th_sender_location"><xms:localization text="Sender Location"/></th>
            <th class="th_receiver_company"><xms:localization text="Receiver Company"/></th>
            <th class="th_receiver_contact"><xms:localization text="Receiver Contact"/></th>
            <th><xms:localization text="Destination"/></th>
            <th><xms:localization text="Dest. Country"/></th>
        </tr>
        </thead>

        <tbody>

        <s:iterator value="shipmentList.records">
            <tr>
                <td><s:if test="%{status==0}">
                    <s:checkbox name="chk_multivoid[]" fieldValue="%{shipmentId}"
                                class="chk_multivoid chk_multivoid_%{status}"></s:checkbox>
                </s:if> <s:hidden value="%{shipmentId}" id="shipmentId"/> <s:hidden value="%{service.serviceId}"
                                                                                    id="serviceId"/> <s:property
                        value="service.serviceName"/> <s:hidden value="%{packinglist}" id="packinglist"/></td>
                <td><s:if test="%{status==0}">
                    <s:hidden value="0" id="void"/>
                    NO
                </s:if> <s:elseif test="%{status==1}">
                    <s:hidden value="1" id="void"/>
                    YES
                </s:elseif></td>
                <td><s:property value="airbillNumber"/></td>
                <td><s:property value="createDate"/></td>
                <td><s:property value="shipmentDate"/></td>
                <td><s:property value="noOfPieces"/></td>
                <td><s:property value="shipmentType.shipmentTypeName"/></td>
                <td class="th_package"><s:property value="_package.packageName"/></td>
                <td><s:property value="totalWeight"/> (<s:property value="weightUnit"/>)</td>
                <s:iterator value="pieces">
                    <td class="th_dimensions"><s:property value="dimensionL"/> x <s:property value="dimensionH"/> x
                        <s:property value="dimensionW"/> <s:property value="shipment.dimensionUnit"/></td>
                </s:iterator>
                <td><s:property value="totalAmount"/></td>
                <td><s:property value="withInsurance"/></td>
                <td><s:property value="scheduled"/></td>
                <td><s:property value="schedule.collectionInfo"/></td>
                <td class="th_shipment_reference"><s:property value="customerCode"/></td>
                <td class="th_billing_party"><s:if test="%{billingType==1}">
                    S
                </s:if> <s:elseif test="%{billingType==2}">
                    R
                </s:elseif> <s:elseif test="%{billingType==3}">
                    T
                </s:elseif></td>
                <td class="th_sender_company"><s:property value="sender.companyName"/></td>
                <td class="th_sender_contact"><s:property value="sender.contactName"/></td>
                <td class="th_sender_location"><s:property value="sender.city"/>, <s:property
                        value="receiver.postalCode"/></td>
                <td class="th_receiver_company"><s:property value="receiver.companyName"/></td>
                <td class="th_receiver_contact"><s:property value="receiver.contactName"/></td>
                <td><s:property value="receiver.city"/>, <s:property value="receiver.postalCode"/></td>
                <td><s:property value="country.countryName"/></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>

<button class="btn s33 s44 multivoid" type="submit" id="proceed-tovoid">
    <xms:localization text="Proceed to Void"/>
</button>
<button class="btn s33 s44 multivoid" type="submit" id="proceed-toupdate">
    <xms:localization text="Proceed to Update"/>
</button>
<button class="btn s33 s44 multivoid" type="button" id="cancel_multivoid">
    <xms:localization text="Cancel"/>
</button>
<s:if test="shipmentList.totalPage != 1">
    <div class="col-xs-4 pull-right">
        <ul class="pagination pagination-sm">
            <li><a href='javascript:changePage(<s:property value="shipmentList.firstPage" />);'><<</a></li>
            <s:iterator value="shipmentList.pageRange" status="count">
                <s:if test="%{shipmentList.pageRange[#count.index] == shipmentList.currentPage}">
                    <li class="active"><a><s:property value="shipmentList.currentPage"/></a></li>
                </s:if>
                <s:else>
                    <li><a href="javascript:changePage(<s:property/>);"><s:property/></a></li>
                </s:else>
            </s:iterator>
            <li><a href='javascript:changePage(<s:property value="shipmentList.lastPage"/>);'>>></a></li>
        </ul>
    </div>
</s:if>

<div id="div_proceed_tovoid"></div>
<div id="div_proceed_tovoid_result"></div>
<div id="div_proceed_toupdate">
</div>
<script type="text/javascript">
    $(document).ready(function () {
        $('.multivoid').hide();
        $('.chk_multivoid').hide();
        $('.th_package').css("display", "none");
        $('.th_dimensions').css("display", "none");
        $('.th_shipment_reference').css("display", "none");
        $('.th_billing_party').css("display", "none");
        $('.th_sender_company').css("display", "none");
        $('.th_sender_contact').css("display", "none");
        $('.th_sender_location').css("display", "none");
        $('.th_receiver_company').css("display", "none");
        $('.th_receiver_contact').css("display", "none");


        if ($('#show_package_type input').is(":checked")) {
            $('.th_package').css("display", "table-cell");
        } else {
            $('.th_package').css("display", "none");
        }
        if ($('#show_dimensions input').is(":checked")) {
            $('.th_dimensions').css("display", "table-cell");
        } else {
            $('.th_dimensions').css("display", "none");
        }

        if ($('#show_shipment_reference input').is(":checked")) {
            $('.th_shipment_reference').css("display", "table-cell");
        } else {
            $('.th_shipment_reference').css("display", "none");
        }

        if ($('#show_billing_party input').is(":checked")) {
            $('.th_billing_party').css("display", "table-cell");
        } else {
            $('.th_billing_party').css("display", "none");
        }

        if ($('#show_sender_company input').is(":checked")) {
            $('.th_sender_company').css("display", "table-cell");
        } else {
            $('.th_sender_company').css("display", "none");
        }

        if ($('#show_sender_contact input').is(":checked")) {
            $('.th_sender_contact').css("display", "table-cell");
        } else {
            $('.th_sender_contact').css("display", "none");
        }

        if ($('#show_sender_localtion input').is(":checked")) {
            $('.th_sender_location').css("display", "table-cell");
        } else {
            $('.th_sender_location').css("display", "none");
        }

        if ($('#show_receiver_company input').is(":checked")) {
            $('.th_receiver_company').css("display", "table-cell");
        } else {
            $('.th_receiver_company').css("display", "none");
        }

        if ($('#show_receiver_contact input').is(":checked")) {
            $('.th_receiver_contact').css("display", "table-cell");
        } else {
            $('.th_receiver_contact').css("display", "none");
        }


        $('table#listHistoryTable tbody tr').click(function () {
            $("#div_rp_op button").prop('disabled', false);
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            shipmentId = $(this).find('#shipmentId').val();
            serviceId = $(this).find('#serviceId').val();
            showPackinglist = $(this).find('#packinglist').val();
            void_status = $(this).find('#void').val();
            if (showPackinglist == 2) {
                if (serviceId == 50)  //&& airbill_no==''
                    document.getElementById('btnviewpackinglist').disabled = true;
                else
                    document.getElementById('btnviewpackinglist').disabled = false;
            } else {
                document.getElementById('btnviewpackinglist').disabled = true;
            }
            if (void_status == 1) {
                document.getElementById('btn_void').disabled = true;
            } else {
                document.getElementById('btn_void').disabled = false;
            }
            $("#hidenShipmentId").val(shipmentId);
            show_hide_button(serviceId);
        });
        $('table#history_detaill_btn').click(function () {
            show_history_detail(shipmentId);
        });
        $('#cancel_multivoid').click(function () {
            $('.chk_multivoid_0').hide();
            $('.multivoid').hide();
        });

        $('#proceed-tovoid').click(function () {
            proceedtovoid();
        });

        $('#proceed-toupdate').click(function () {
            proceedtoupdate();
        });
    });

    function proceedtovoid() {
        var pageSize = document.getElementById('selRecordSizeHistory').value;
        var a = document.getElementsByName('chk_multivoid[]');
        var len = a.length;
        var list_sm = "0";
        var totalSm = 0;
        for (i = 0; i < len; i++) {
            if (a[i].checked == true) {
                list_sm = list_sm + "|" + a[i].value;
                totalSm = totalSm + 1
            }
        }
        var data = {
            'listShipmentId': list_sm
        };
        var messageVoid = "Are You Sure Want To Void Total " + totalSm;
        loadConfirmDialog("multivoid_proceedtovoid.ix?reqType=json", data, messageVoid, "div_proceed_tovoid", getHistory(pageSize), "OK", "Cancel", "Proceed To Void");
    }

    function proceedtoupdate() {
        var a = document.getElementsByName('chk_multivoid[]');
        var len = a.length;
        var list_sm = "";
        var totalSm = 0;
        for (i = 0; i < len; i++) {
            if (a[i].checked == true) {
                list_sm = list_sm + "|" + a[i].value;
                totalSm = totalSm + 1
            }
        }
        var contentDialog = '<p>New collection no:<form id="form_updateCollection"><input name="collectionNoNew" id="collection_no_new" class="form-control" /></form></p>';
        var messageUpdate = "Update Collection of  Total " + totalSm + contentDialog;
        var pageSize = document.getElementById('selRecordSizeHistory').value;
        var dataUpdate = {
            'listShipmentId': list_sm
        };
        loadConfirmDialog("multivoid_proceedtoupdatecollection.ix?reqType=json", dataUpdate, messageUpdate, "div_proceed_toupdate", getHistory(pageSize), "OK", "Cancel", "Proceed To Update Collection", "form_updateCollection");
    }

    function show_hide_button(serviceId) {
        if (serviceId == 54) {
            document.getElementById('btntntconnote').disabled = false;
        } else {
            document.getElementById('btntntconnote').disabled = true;
        }
    }
</script>

