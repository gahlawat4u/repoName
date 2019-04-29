<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<script type="text/javascript">
    var globalPackingListIndex = 0;
    $(document).ready(function () {
        onCommerialOrPackingListChange();
    });

    function onCommerialOrPackingListChange() {
        var commercialInvoiceType = $("#commercialInvoiceSelect option:selected").val();
        var packingListType = $("#packingListSelect option:selected").val();
        if (commercialInvoiceType == 3 && packingListType == 2) {
            $("#ws-commercial-invoice-div").show();
            $("#ws-packing-list-div").show();
            $("#ws-packing-list-div #packing_list_table td[class='ws-packing-list-div-extra']").show();
            $("#ws-packing-list-div #packing_list_table th[class='ws-packing-list-div-extra']").show();
        } else if (packingListType == 2) {
            $("#ws-commercial-invoice-div").hide();
            $("#ws-packing-list-div").show();
            $("#ws-packing-list-div #packing_list_table td[class='ws-packing-list-div-extra']").show();
            $("#ws-packing-list-div #packing_list_table th[class='ws-packing-list-div-extra']").show();
            calculateSubTotal();
        } else if (commercialInvoiceType == 3) {
            $("#ws-commercial-invoice-div").show();
            $("#ws-packing-list-div").show();
            $("#ws-packing-list-div #packing_list_table td[class='ws-packing-list-div-extra']").hide();
            $("#ws-packing-list-div #packing_list_table th[class='ws-packing-list-div-extra']").hide();
        } else {
            $("#ws-commercial-invoice-div").hide();
            $("#ws-packing-list-div").hide();
            $("#total_shipment_value").val(0);
        }
    }

    function calculateProductAmount(obj) {
        var row = $(obj).parent().parent();
        var qty = $(row).find("#productDetailQty").val();
        var price = $(row).find("#productDetailAmount").val();
        if (!isNumeric(qty)) {
            qty = 0;
        }
        if (!isNumeric(price)) {
            price = 0;
        }
        var amount = qty * price;
        $(row).find("#span-amount").html(Math.round(amount*100)/100);
        calculateSubTotal();
    }

    function calculateSubTotal() {
        var subTotal = 0;
        $("table#packing_list_table span[id='span-amount']").each(function () {
            subTotal += parseFloat($(this).html());
        });
        $("#item_sub_total").html(Math.round(subTotal*100)/100);
        $("#total_shipment_value").val(Math.round(subTotal*100)/100);
    }

    function isNumeric(n) {
        return !isNaN(parseFloat(n)) && isFinite(n);
    }

    function htsLookup(obj) {
        var htsGoodId = $(obj).prev().val();
        var index = $(obj).parent().parent().index();
        var data = {
            'index': index
        };
        loadHtsGoodSearchDialog(data);
    }

    function loadHtsGoodSearchDialog(data) {
        var dialog = $("#hts_good_lookup_load").dialog({
            modal: true,
            autoOpen: false,
            width: "auto",
            height: "auto",
            minWidth: 640,
            maxWidth: 800,
            minHeight: 480,
            maxHeight: 600,
            show: {
                effect: "fade",
                duration: 300
            },
            buttons: [{
                text: '<xms:localization text="Close" />',
                click: function () {
                    $(this).dialog("close");
                }
            }],
            title: '<xms:localization text="HTS Code Lookup" />'
        });
        loadingDialog.dialog("open");
        $.post("hts_good_lookup.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                dialog.html(res.content);
                dialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
            alertDialog.dialog("open");
        });
    }

    function addAnotherItem() {
        copyPackingListRow();
        reIndexPackingListRows(true);
    }

    function copyPackingListRow() {
        var copyRow = $("table#packing_list_table tr:last").prev().prev();
        var clone = copyRow.clone();
        copyRow.after(clone);
    }

    function reIndexPackingListRows(isAdd) {
        var len = $("table#packing_list_table tr").size();
        $("table#packing_list_table tr").each(function (index) {
            // Rename of all rows.
            if (index > 0 && index < len - 2) {
                $(this).find("input, select, textarea").each(function () {
                    var name = $(this).attr("name");
                    var oldName = name.substring(0, name.indexOf("]") + 1);
                    var newName = name.substring(0, name.indexOf("[")) + "[" + (index - 1) + "]";
                    name = name.replace(oldName, newName);
                    $(this).attr("name", name);
                    // Set value is empty with the last row.
                    if (isAdd && index == len - 3 && $(this).is("input")) {
                        $(this).val("");
                    }
                });
            }
            // Hide remove button of the first row.
            if (index == 1) {
                $(this).find("button.btn_remove_item").hide();
            } else if (index < len - 2) {
                $(this).find("button.btn_remove_item").show();
            }
            // Set total is empty with the last row.
            if (index == len - 3) {
                $(this).find("#span-amount").html("");
            }
        });
    }

    function removePackingListRow(obj) {
        $(obj).parent().parent().remove();
        calculateSubTotal();
    }


</script>