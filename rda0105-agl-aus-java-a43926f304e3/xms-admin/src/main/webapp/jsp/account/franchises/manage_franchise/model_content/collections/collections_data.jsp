<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<form id="frmSaveFranchiseCollection">
    <div class="row">
        <s:hidden name="saveManageFranchiseModel.collection.userId" value="%{collectionModel.userId}"/>
        <s:hidden name="saveManageFranchiseModel.collection.userType" value="%{collectionModel.userType}"/>
        <s:hidden name="saveManageFranchiseModel.collection.customerCode" value="%{collectionModel.customerCode}"/>
        <div class="portlet-body b12 b11">
            <div class="portlet-body b22" style="padding: 0px;">
                <ul id="generalTab1" class="nav nav-tabs responsive">
                    <li class="active" style="margin-left: 10px;"><a href="#9-tab" data-toggle="tab"><xms:localization
                            text="Freight"/></a></li>
                    <li><a href="#10-tab" data-toggle="tab" class="tb3"><xms:localization text="Reminder Letters"/></a>
                    </li>
                </ul>
                <div class="tab-content responsive">
                    <div id="9-tab" class="tab-pane fade in tb2 active">
                        <div class="row">
                            <div class="portlet-body b12 b11">
                                <table class="s36">
                                    <tbody>
                                    <tr>
                                        <td>&nbsp;</td>
                                        <td class="caption b17" colspan="2"><xms:localization
                                                text="Credit Limits"/></td>
                                    </tr>
                                    <TR>
                                        <td colspan="3" height="5"></td>
                                    </TR>
                                    <tr>
                                        <td><xms:localization text="Freight Credit Limits"/> :</td>
                                        <s:if test="collectionModel.freightCreditLimit == null ">
                                            <td width="90"><s:textfield class="form-control"
                                                                        name="saveManageFranchiseModel.collection.freightCreditLimit"
                                                                        value="0"
                                                                        onkeypress="return formartNumber(event,this,false);"></s:textfield></td>
                                        </s:if>
                                        <s:else>
                                            <td width="90"><s:textfield class="form-control"
                                                                        name="saveManageFranchiseModel.collection.freightCreditLimit"
                                                                        value="%{collectionModel.freightCreditLimit}"
                                                                        onkeypress="return formartNumber(event,this,false);"></s:textfield></td>
                                        </s:else>

                                        <td>$</td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div id="10-tab" class="tab-pane fade in">
                        <div class="row">
                            <div class="portlet-body b12 b11">
                                <table class="s36">
                                    <tbody>
                                    <tr>
                                        <td class="caption b17" colspan="9"><xms:localization
                                                text="Credit Limits"/></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td colspan="10" height="5"></td>
                                    </tr>
                                    <tr>
                                        <td><s:checkbox name="saveManageFranchiseModel.collection.reminder"
                                                        value="%{collectionModel.reminder}"/></td>
                                        <td><xms:localization text="Send"/></td>
                                        <td><s:checkbox name="saveManageFranchiseModel.collection.reminderEmail"
                                                        value="%{collectionModel.reminderEmail}"
                                                        onclick="changeEmailCheck()"/></td>
                                        <td><xms:localization text="Email"/></td>
                                        <td><s:checkbox name="saveManageFranchiseModel.collection.reminderPrint"
                                                        value="%{collectionModel.reminderPrint}"/></td>
                                        <td><xms:localization text="Print"/></td>
                                        <td><s:checkbox
                                                name="saveManageFranchiseModel.collection.reminderUseEmailInvoice"
                                                value="%{collectionModel.reminderUseEmailInvoice}"/></td>
                                        <td><xms:localization text="Use Email Inv. AddressInv"/>.</td>
                                        <td><s:textfield class="form-control"
                                                         name="saveManageFranchiseModel.collection.reminderEmailAddress"
                                                         value="%{collectionModel.reminderEmailAddress}"/></td>
                                        <td><xms:localization text="Other Email Address(es)"/></td>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="div_save_franchise_collection_result"></div>
</form>
<script type="text/javascript">
    function saveCustomerCollection() {
        var data = $("#frmSaveFranchiseCollection").serialize();
        loadingDialog.dialog("open");
        $.post("manage_franchise_collections_save.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            loadingDialog.dialog("close");
            dialog.html(res.content);
            dialog.dialog("open");
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });

        var buttons = {};
        var height = $(window).height();
        var width = $(window).width();
        height = height * 0.70;
        width = width * 0.9;
        var dialog = $("#div_save_franchise_collection_result").dialog({
            modal: true,
            autoOpen: false,
            width: "auto",
            buttons: buttons,
            width: 'auto',
            height: 'auto',
            maxHeight: height,
            create: function (event, ui) {
                $(this).css("maxWidth", width);
            },
            show: {
                effect: "fade",
                duration: 300
            },
            close: function (e) {
                $("#div_save_franchise_collection_result").html("");
            }
        });
        dialog.dialog("option", "title", "Save Collection.");
    }

    function changeEmailCheck() {
        var isChecked = $("input[name='saveManageFranchiseModel.collection.reminderEmail']").is(":checked");
        if (isChecked) {
            $("input[name='saveManageFranchiseModel.collection.reminderUseEmailInvoice']").removeAttr("disabled");
            $("input[name='saveManageFranchiseModel.collection.reminderEmailAddress']").removeAttr("readonly");
        } else {
            $("input[name='saveManageFranchiseModel.collection.reminderUseEmailInvoice']").attr("disabled", "disabled");
            $("input[name='saveManageFranchiseModel.collection.reminderEmailAddress']").attr("readonly", "readonly");
            $("input[name='saveManageFranchiseModel.collection.reminderEmailAddress']").val("");
        }
    }


</script>