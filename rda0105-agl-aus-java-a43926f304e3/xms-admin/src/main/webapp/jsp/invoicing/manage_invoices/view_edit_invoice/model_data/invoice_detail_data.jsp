<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<s:hidden id="selected-inv" name="invoiceId"/>
<s:hidden name="invoiceInfoModel.status" id="hid_status_fozen"></s:hidden>
<script type="text/javascript">
    var hasRecord = false;
</script>
<div class="row">
    <div class="col-lg-12">
        <table class="s36 b24" align="right">
            <tr>
                <td><span class="s30"><xms:localization text="This will be an email invoice to:"/> <s:property
                        value="invoiceInfoModel.billingEmail"/></span></td>
            </tr>
        </table>
    </div>
    <div class="col-lg-3">
        <img src="images/LOGOiN.png" width="100%" style="margin-top: -30px;"/>
    </div>
    <div class="col-lg-9">
        <div class="col-lg-10">
            <p style="color: red">
                <s:if test="invoiceInfoModel.status!=0">
                    <xms:localization text="This invoice is frozen"/>
                </s:if>
            </p>

            <p>
                <s:property value="companyAddress" escape="false"/>
            </p>
        </div>
        <div class="col-lg-8 flr">
            <div class="form-group flr mgb">
                <div class="btn-group" id="btn-export">
                    <input type="button" id="export-option" class="btn s37" value="<xms:localization text="Option" />"
                           onclick="invoiceExport($('#selected-option').val())"/>
                    <button type="button" class="btn s37 dropdown-toggle" data-toggle="dropdown" aria-expanded="true">
                        <span class="caret"></span>
                    </button>
                    <s:hidden id="selected-option"/>
                    <ul class="dropdown-menu dropdown-menu-right" role="menu">
                        <li><a href="#" onclick="invoiceExport('option')"><xms:localization text="Option"/></a></li>
                        <li><a href="#" onclick="invoiceExport('html')"><xms:localization
                                text="View Printable Copy"/></a></li>
                        <li><a href="#" onclick="invoiceExport('pdf')"><xms:localization text="View Pdf Copy"/></a></li>
                        <li><a href="#" onclick="createNewAirbill()"><xms:localization text="Create New Airbill"/></a>
                        </li>
                        <li><a href="#"
                               onclick="viewAirbillChangeLog(<s:property value='invoiceId'/>,'')"><xms:localization
                                text="View Airbill Change Log"/></a></li>
                        <li><a href="#" onclick="findAirbill()"><xms:localization text="Find Airbill"/></a></li>
                        <li><a href="#" onclick="refreshInvoice()"><xms:localization text="Refresh Invoice"/></a></li>
                    </ul>
                </div>
            </div>
        </div>
        <div class="col-lg-12">
            <table class="s36 b24" align="right">
                <tr>
                    <td><input type="checkbox" id="chkShowPayments" onclick="showPayments()"></td>
                    <td><xms:localization text="Show Payments"/></td>
                </tr>
            </table>
        </div>
        <div class="col-lg-12">
            <table id="invoice-info-summary" class="table table-bordered table-striped" style="margin-top: 5px">
                <thead>
                <tr>
                    <th><xms:localization text="Invoice Number"/></th>
                    <th><xms:localization text="Invoice Date"/></th>
                    <th><xms:localization text="Customer #"/></th>
                    <th class="text-right"><xms:localization text="Airbills"/></th>
                    <th><xms:localization text="Due Date"/></th>
                    <th class="text-right"><xms:localization text="Amount Due"/></th>
                    <th class="text-right"><xms:localization text="If Not Paid by Due Date"/></th>
                    <th class="text-right" group="show-payments"><xms:localization text="Paid/Credited"/></th>
                    <th class="text-right" group="show-payments"><xms:localization text="Remaining Due"/></th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td><s:property value="invoiceInfoModel.invoiceCode"/></td>
                    <td><s:property value="invoiceInfoModel.invoiceDate"/></td>
                    <td><s:property value="invoiceInfoModel.customerCode"/> <s:hidden
                            name="invoiceInfoModel.customerCode" id="hid_invoice_customer"></s:hidden></td>
                    <td class="text-right"><s:property value="invoiceInfoModel.noOfAirbills"/></td>
                    <td><s:property value="invoiceInfoModel.dueDate"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="invoiceInfoModel.totalAmount"/></td>
                    <td class="text-right"><s:property value="currencySymbol"/> <s:property
                            value="invoiceInfoModel.totalIfNotPaidByDueDate"/></td>
                    <td class="text-right" group="show-payments"><s:property value="currencySymbol"/> <s:property
                            value="invoiceInfoModel.totalPaid"/></td>
                    <td class="text-right" group="show-payments"><s:property value="currencySymbol"/> <s:property
                            value="invoiceInfoModel.remainingDue"/></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div class="col-lg-12">
        <div class="col-lg-6">
            <p style="border: 1px solid #ddd; padding: 8px;">
                <strong><xms:localization text="BILL TO:"/></strong><br/>
                <s:property value="invoiceInfoModel.billingCustomerName"/>
                <br/>
                <s:property value="invoiceInfoModel.billingAddress1"/>
                <br/>
                <s:if test="%{invoiceInfoModel.billingAddress2!=null && invoiceInfoModel.billingAddress2!=''}">
                    <s:property value="invoiceInfoModel.billingAddress2"/>
                    <br/>
                </s:if>
                <s:if test="%{invoiceInfoModel.billingPostalCode!=null && invoiceInfoModel.billingPostalCode!=''}">
                    <s:property value="invoiceInfoModel.billingPostalCode"/>
                    ,
                </s:if>
                <s:if test="%{invoiceInfoModel.billingCity!=null && invoiceInfoModel.billingCity!=''}">
                    <s:property value="invoiceInfoModel.billingCity"/>
                    ,
                </s:if>
                <s:property value="invoiceInfoModel.billingCountryName"/>
            </p>
        </div>
        <div class="col-lg-6">
            <p style="border: 1px solid #ddd; padding: 8px;">
                <strong><xms:localization text="MAIL PAYMENT TO:"/> </strong> <br>
                <s:property value="mailToPayment" escape="false"/>
            </p>
        </div>
    </div>
    <div class="col-lg-9">
        <table class="table table-bordered">
            <thead>
            <tr bgcolor="#f9f9f9">
                <th><xms:localization text="GST Summary"/></th>
                <th class="text-right"><xms:localization text="GST Percent"/></th>
                <th class="text-right"><xms:localization text="Credit Amount"/></th>
                <th class="text-right"><xms:localization text="GST Amount"/></th>
                <th class="text-right"><xms:localization text="Total Amount"/></th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td bgcolor="#f9f9f9"><strong><xms:localization text="GST Shipments"/></strong></td>
                <td class="text-right"><s:property value="invoiceInfoModel.gstTaxPercent"/>%</td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceInfoModel.gstTotalCost"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceInfoModel.gstTotalTax"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceInfoModel.gstTotalAmount"/></td>
            </tr>
            <tr>
                <td bgcolor="#f9f9f9"><strong><xms:localization text="Non-GST Shipments"/></strong></td>
                <td class="text-right"><s:property value="invoiceInfoModel.nonGstTaxPercent"/>%</td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceInfoModel.nonGstTotalCost"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceInfoModel.nonGstTotalTax"/></td>
                <td class="text-right"><s:property value="currencySymbol"/> <s:property
                        value="invoiceInfoModel.nonGstTotalAmount"/></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="col-lg-12 ">
        <div class="s70">
            <table class="table table-bordered">
                <thead>
                <tr bgcolor="#f9f9f9">
                    <th style="vertical-align: text-top !important;"><xms:localization text="Carrier - Airbill #"/><br/>
                        <xms:localization text="Orig/Dest"/><br/> <xms:localization text="Ship Date"/><br/>
                        <xms:localization text="Customer #"/><br/> <xms:localization text="Reference"/><br/>
                        <xms:localization text="Reference 2"/></th>
                    <th style="vertical-align: text-top !important;"><xms:localization text="Sender Address"/></th>
                    <th style="vertical-align: text-top !important;"><xms:localization text="Receiver Address"/></th>
                    <th style="vertical-align: text-top !important;"><xms:localization text="Pieces"/><br/>
                        <xms:localization text="Weight"/><br/> <xms:localization text="Dimensions"/><br/>
                        <xms:localization text="Zone"/></th>
                    <th style="vertical-align: text-top !important;"><xms:localization text="Charges"/></th>
                    <th style="vertical-align: text-top !important;" class="text-right"><xms:localization
                            text="Total"/></th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="airbillInfoModels">
                    <tr>
                        <td><p>
                            <a href="javascript:void(0)" title="Edit Airbill"
                               onclick="loadAirbillDetail(<s:property value='shipmentId'/>, '<s:property
                                       value='airbillNumber'/>')"><i class="fa fa fa-pencil s10 b3"
                                                                     style="font-size: 14px; margin-left: 5px"
                                                                     id="md-20-link"></i></a>
                            <s:if test="invoiceInfoModel.status != 0">
                                <a href="javascript:void(0)" title="Adjustment"
                                   onclick="loadAjustment(<s:property value='shipmentId'/>, '<s:property
                                           value='airbillNumber'/>','<s:property
                                           value='%{invoiceInfoModel.invoiceCode}'/>')"><i class="fa fa-wrench s10 b3"
                                                                                           style="font-size: 14px; margin-left: 5px"
                                                                                           id="md-7-link"></i></a>
                            </s:if>
                            <a href="javascript:void(0)" title="View Rate Sheet"
                               onclick="viewAirbillRateSheet(<s:property value='shipmentId'/>,'<s:property
                                       value='airbillNumber'/>')"><i class="fa fa-file-text s10 b3"
                                                                     style="font-size: 14px; margin-left: 5px"></i></a>
                            <s:if test="invoiceInfoModel.status == 0">
                                <a href="javascript:void(0)" title="Move Airbill"
                                   onclick="loadMoveAirbill(<s:property value='shipmentId'/>, '<s:property
                                           value='airbillNumber'/>','<s:property
                                           value='%{invoiceInfoModel.invoiceDate}'/>', '<s:property
                                           value='%{invoiceInfoModel.invoiceId}'/>', '<s:property
                                           value='%{invoiceInfoModel.customerCode}'/>')"><i class="fa fa-retweet s10 b3"
                                                                                            style="font-size: 14px; margin-left: 5px"
                                                                                            id="md-7-link"></i></a>
                            </s:if>
                            <a href="javascript:void(0)" title="View Customer"
                               onclick="viewManageCustomer(<s:property value='customerCode'/>)"><i
                                    class="fa fa-user s10 b3" style="font-size: 16px; margin-left: 5px"></i></a> <a
                                href="javascript:void(0)" title="Edit Sender Address"
                                onclick="loadSenderEdit(<s:property value='senderAddressId'/>)"><i
                                class="fa fa fa-reply s10 b3" style="font-size: 14px; margin-left: 5px"
                                id="md-6-link"></i></a> <a href="javascript:void(0)" title="Edit Receiver Address"
                                                           onclick="loadReceiverEdit(<s:property
                                                                   value='receiverAddressId'/>)"><i
                                class="fa fa fa-share s10 b3" style="font-size: 14px; margin-left: 5px"
                                id="md-6a-link"></i></a>
                            <%--XTD-58 only show delete button for admin level 1,2--%>
                            <s:if test="invoiceInfoModel.status == 0 && #session.SESS_XMS_ADMIN_LEVEL_INT < 3">
                                <a href="javascript:void(0)" title="Delete Airbill"
                                   onclick="deleteAirbill('<s:property value='airbillNumber'/>',<s:property
                                           value='shipmentId'/>,<s:property value='%{invoiceInfoModel.invoiceId}'/>)"><i
                                        class="fa fa-times-circle-o s10 b3"
                                        style="font-size: 16px; margin-left: 5px"></i></a>
                            </s:if>
                            <a href="javascript:void(0)" title="Change Log"
                               onclick="viewAirbillChangeLog('',<s:property value='shipmentId'/>)"><i
                                    class="fa fa-history" aria-hidden="true"></i></a>
                        </p> <s:property value="serviceName"/> - <s:property value="airbillNumber"/><br/> <s:property
                                value="shipmentDate"/><br/> <s:property value="customerCode"/><br/> <s:property
                                value="reference"/><br/> <s:property value="reference2"/></td>
                        <td><s:property value="senderCompanyName"/> <br/> <s:property value="senderContactName"/><br/>
                            <s:property value="senderAddress1"/><br/> <s:property value="senderCity"/>-<s:property
                                    value="senderStates"/> - <s:property value="senderPostalCode"/><br/> <s:property
                                    value="senderCountryName"/></td>
                        <td><s:property value="receiverCompanyName"/> <br/> <s:property
                                value="receiverContactName"/><br/> <s:property value="receiverAddress1"/><br/>
                            <s:property value="receiverCity"/>-<s:property value="receiverStates"/> - <s:property
                                    value="receiverPostalCode"/><br/> <s:property value="receiverCountryName"/></td>
                        <td><s:property value="noOfPieces"/> <br/> <s:property value="weight"/> <br/> <br/> <s:property
                                value="zone"/> <br/></td>
                        <td><s:iterator value="charges">
                            <s:property value="awbDescription"/> - <s:property value="currencySymbol"/>
                            <s:property value="awbCustomerCost"/>
                            <br/>
                        </s:iterator></td>
                        <td class="text-right"><s:property value="currencySymbol"/> <s:property value="total"/></td>
                    </tr>
                </s:iterator>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="6"><span class="b4"> <b><xms:localization text="Total:"/></b> <s:property
                            value="currencySymbol"/> <s:property value="invoiceInfoModel.totalCost"/>
						</span> <span class="b4"> <b>| <xms:localization text="Tax Amount:"/></b> <s:property
                            value="currencySymbol"/> <s:property value="invoiceInfoModel.totalTax"/>
						</span> <span class="b4"> <b>| <xms:localization text="Grand Total:"/></b> <s:property
                            value="currencySymbol"/> <s:property value="invoiceInfoModel.totalAmount"/>
						</span></td>
                </tr>
                </tfoot>
            </table>
        </div>
    </div>
</div>
<div id="div_edit_airbill"></div>
<div id="div_edit_airbill_do"></div>
<div id="div_move_airbill"></div>
<div id="div_move_airbill_do"></div>
<div id="div_sender_address"></div>
<div id="div_reciver_address"></div>
<div id="div_sender_address_do"></div>
<div id="div_reciver_address_do"></div>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/vendor/jquery.fileDownload.js"></script>
<script type="text/javascript">
    function loadAirbillDetail(shipmentId, airbillNumber) {
        var statusFozen = $("#hid_status_fozen").val();
        var btnSave = "";
        if (statusFozen == "0") {
            btnSave = "Save";
        }
        var dataLoad = {
            'shipmentId': shipmentId,
            'invoiceStatus': statusFozen,
            'airbillNumber': airbillNumber,
            'airbillInfo.customerCode': $("#hid_invoice_customer").val()
        };
        var actionLoad = "view_edit_invoice_show_edit_airbill.ix?reqType=json";
        var actionSave = "view_edit_invoice_show_edit_airbill_do.ix?reqType=json";
        loadResultToEditAirbill(actionLoad, actionSave, dataLoad, "form_edit_airbill", btnSave, "Cancel", "div_edit_airbill", "Edit Airbill", "div_edit_airbill_do");
    }
    function loadMoveAirbill(shipmentId, airbillNumber, invoiceDate, invoiceId, customerCode) {
        var statusFozen = $("#hid_status_fozen").val();
        var btnSave = "";
        if (statusFozen == "0") {
            btnSave = "Save";
        }
        var dataLoad = {
            'shipmentId': shipmentId,
            'invoiceStatus': statusFozen,
            'airbillNumber': airbillNumber,
            'invoiceDate': invoiceDate,
            'invoiceId': invoiceId,
            'customerCode': customerCode
        };
        var actionLoad = "view_edit_invoice_show_move_airbill.ix?reqType=json";
        var actionSave = "view_edit_invoice_show_move_airbill_do.ix?reqType=json";
        loadDialogToSave(actionLoad, actionSave, dataLoad, "form_move_airbill", btnSave, "Cancel", "div_move_airbill", "Move Airbill", "div_move_airbill_do");
    }

    function showPayments() {
        var show = $("#chkShowPayments").is(":checked");
        if (show) {
            $("#invoice-info-summary td[group='show-payments']").show();
            $("#invoice-info-summary th[group='show-payments']").show();
        } else {
            $("#invoice-info-summary td[group='show-payments']").hide();
            $("#invoice-info-summary th[group='show-payments']").hide();
        }
    }

    function loadAjustment(shipmentId, airbilNumber, invoiceCode) {
        var win = window.open("adjustment.ix?adjustmentRequest.airbillNumber=" + airbilNumber + "&adjustmentRequest.shipmentId=" + shipmentId + "&adjustmentRequest.invoiceCode=" + invoiceCode, '_blank');
        win.focus();
    }

    function loadChangeLog() {
        alert("Function building ...");
    }

    function loadSenderEdit(senderAddressId) {
        var statusFozen = $("#hid_status_fozen").val();
        var btnSave = "";
        if (statusFozen == "0") {
            btnSave = "Save";
        }
        var dataLoad = {
            'senderAddressId': senderAddressId,
            'invoiceStatus': statusFozen
        };
        var actionLoad = "view_edit_invoice_edit_sender_address.ix?reqType=json";
        var actionSave = "view_edit_invoice_edit_sender_address_do.ix?reqType=json";
        loadDialogToSave(actionLoad, actionSave, dataLoad, "form_edit_sender_address", btnSave, "Cancel", "div_sender_address", "Edit Sender Airbill", "div_sender_address_do");
    }

    function loadReceiverEdit(receiverAddressId) {
        var statusFozen = $("#hid_status_fozen").val();
        var btnSave = "";
        if (statusFozen == "0") {
            btnSave = "Save";
        }
        var dataLoad = {
            'receiverAddressId': receiverAddressId,
            'invoiceStatus': statusFozen
        };
        var actionLoad = "view_edit_invoice_edit_receiver_address.ix?reqType=json";
        var actionSave = "view_edit_invoice_edit_receiver_address_do.ix?reqType=json";
        loadDialogToSave(actionLoad, actionSave, dataLoad, "form_edit_receiver_address", btnSave, "Cancel", "div_reciver_address", "Edit Receiver Airbill", "div_reciver_address_do");
    }
    function invoiceExport(option) {
        var invId = $("#selected-inv").val();
        var showPayment = false;
        if ($("#chkShowPayments").is(":checked")) {
            showPayment = true;
        }
        var data = {"invoiceId": invId, "showPayments": showPayment}
        $('#selected-option').val(option);
        switch (option) {
            case 'option':
                $("#export-option").val("<xms:localization text="Option" />");
                break;
            case 'html':
                loadingDialog.dialog("open");
                $("#export-option").val("<xms:localization text="View Printable Copy" />");
                $.post("view_edit_invoice_view_printable.ix", data, function (res) {
                    loadingDialog.dialog("close");
                    var win = window.open('', 'win2', 'toolbar=no,location=no,status=no,menubar=no,scrollbars=yes,resizable=yes,width=1000,height=500');
                    win.document.write(res);
                });
                break;
            case 'pdf':
                $("#export-option").val("<xms:localization text="View Pdf Copy" />");
                loadingDialog.dialog("open");
                $.fileDownload("view_edit_invoice_view_pdf.ix", {
                    failMessageHtml: "<xms:localization text="There was a problem generating your report, please try again." />",
                    httpMethod: "POST",
                    data: data,
                    successCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                    failCallback: function (url) {
                        loadingDialog.dialog("close");
                    },
                });

                break;
        }
    }
    function findAirbill() {
        var action = "view_edit_invoice_find_airbill.ix?reqType=json";
        var actionSave = "view_edit_invoice_find_airbill_do.ix?reqType=json";
        var data = {
            'invoiceId': $("#selected-inv").val()
        };
        var dialogId = createUniqueId("find_airbill", "dialog");
        createDiv(dialogId);
        loadResultToSave(action, actionSave, data, "form_find_airbill", "Find", "Close", dialogId, "Find Airbill", "div_invocie_detail");
    }

    function createNewAirbill() {
        var statusFozen = parseInt($("#hid_status_fozen").val().trim());
        if (statusFozen == 0) {
            var btnSave = "";
            if (statusFozen == "0") {
                btnSave = "Save";
            }
            var action = "view_edit_invoice_show_create_airbill.ix?reqType=json";
            var actionSave = "view_edit_invoice_show_create_airbill_do.ix?reqType=json";
            var data = {
                'invoiceId': $("#selected-inv").val(),
                'invoiceStatus': statusFozen,
                'airbillInfo.customerCode': $("#hid_invoice_customer").val()
            };
            var dialogId = createUniqueId("create_airbill", "dialog");
            createDiv(dialogId);
            loadResultToSaveNewAirbill(action, actionSave, data, "form_create_airbill", btnSave, "Close", dialogId, "Create Airbill", "div_invocie_detail");
        } else {
            alertDialog.html("<xms:localization text="This invoice is frozen." />");
            alertDialog.dialog("open");
        }
    }

    function refreshInvoice() {
        var action = "view_edit_invoice_get_invoice_detail.ix?reqType=json";
        var data = {
            'invoiceId': $("#selected-inv").val()
        };
        doPostDataByParameters(action, data, "", "div_invocie_detail", true, false);
    }
</script>
