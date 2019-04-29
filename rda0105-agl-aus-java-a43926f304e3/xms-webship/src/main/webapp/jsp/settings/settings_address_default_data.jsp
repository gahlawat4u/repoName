<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-6">
        <div class="portlet box">
            <div class="portlet-header">
                <div class="caption w4">
                    <xms:localization text="Default From Address"/>
                </div>
            </div>
            <div class="portlet-body">
                <div class="row">
                    <s:if test="from!=null">
                        <input id="defaultFromAddressId" type="hidden" value="<s:property value="from.addressId" />"/>
                    </s:if>
                    <s:else>
                        <input id="defaultFromAddressId" type="hidden" value="0"/>
                    </s:else>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="control-label" for="inputName"><xms:localization
                                    text="Find By Company"/></label> <input id="fromCompany" type="text" placeholder=""
                                                                            class="form-control alloptions"
                                                                            data-placement="top" maxlength="25"
                                                                            onkeyup="doSearchAddress(true, true)"/>

                            <div id="from-address-search-by-company"></div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="control-label" for="inputName"><xms:localization
                                    text="Find By Contact"/></label> <input id="fromContact" type="text" placeholder=""
                                                                            class="form-control alloptions"
                                                                            data-placement="top" maxlength="25"
                                                                            onkeyup="doSearchAddress(true, false)"/>

                            <div id="from-address-search-by-contact"></div>
                        </div>
                    </div>
                </div>
                <div class=" text-right pal pdt10">
                    <div class="row">
                        <div class="col-lg-9 text-left">
                            <div id="from-address">
                                <s:if test="from!=null">
                                    <p>
                                        <s:property value="from.contactName"/>
                                    </p>

                                    <p>
                                        <s:property value="from.companyName"/>
                                    </p>

                                    <p>
                                        <s:property value="from.address1"/>
                                    </p>

                                    <p>
                                        <s:property value="from.address2"/>
                                    </p>

                                    <p>
                                        <s:property value="from.city"/>
                                        &nbsp;
                                        <s:property value="from.postalCode"/>
                                    </p>

                                    <p>
                                        <s:property value="from.countryName"/>
                                    </p>
                                </s:if>
                                <s:else>
                                    <p>
                                        <xms:localization text="No Default."/>
                                    </p>
                                </s:else>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <button class="btn s33 s44" type="button" onclick="updateAddressId(true, true)">
                                <xms:localization text="Reset"/>
                            </button>
                            <button class="btn s33 s44" type="button" onclick="updateAddressId(false, true)">
                                <xms:localization text="Save"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-6">
        <div class="portlet box">
            <div class="portlet-header">
                <div class="caption w4">
                    <xms:localization text="Default To Address"/>
                </div>
            </div>
            <div class="portlet-body">
                <div class="row">
                    <s:if test="to!=null">
                        <input id="defaultToAddressId" type="hidden" value="<s:property value="to.addressId" />"/>
                    </s:if>
                    <s:else>
                        <input id="defaultToAddressId" type="hidden" value="0"/>
                    </s:else>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="control-label" for="inputName"><xms:localization
                                    text="Find By Company"/></label> <input id="toCompany" type="text" placeholder=""
                                                                            class="form-control alloptions"
                                                                            data-placement="top" maxlength="25"
                                                                            onkeyup="doSearchAddress(false, true)"/>

                            <div id="to-address-search-by-company"></div>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="form-group">
                            <label class="control-label" for="inputName"><xms:localization
                                    text="Find By Contact"/></label> <input id="toContact" type="text" placeholder=""
                                                                            class="form-control alloptions"
                                                                            data-placement="top" maxlength="25"
                                                                            onkeyup="doSearchAddress(false, false)"/>

                            <div id="to-address-search-by-contact"></div>
                        </div>
                    </div>
                </div>
                <div class=" text-right pal pdt10">
                    <div class="row">
                        <div class="col-lg-9 text-left">
                            <div id="to-address">
                                <s:if test="to!=null">
                                    <p>
                                        <s:property value="to.contactName"/>
                                    </p>

                                    <p>
                                        <s:property value="to.companyName"/>
                                    </p>

                                    <p>
                                        <s:property value="to.address1"/>
                                    </p>

                                    <p>
                                        <s:property value="to.address2"/>
                                    </p>

                                    <p>
                                        <s:property value="to.city"/>
                                        &nbsp;
                                        <s:property value="to.postalCode"/>
                                    </p>

                                    <p>
                                        <s:property value="to.countryName"/>
                                    </p>
                                </s:if>
                                <s:else>
                                    <p>
                                        <xms:localization text="No Default."/>
                                    </p>
                                </s:else>
                            </div>
                        </div>
                        <div class="col-lg-3">
                            <button class="btn s33 s44" type="submit" onclick="updateAddressId(true, false)">
                                <xms:localization text="Reset"/>
                            </button>
                            <button class="btn s33 s44" type="submit" onclick="updateAddressId(false, false)">
                                <xms:localization text="Save"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    function doSearchAddress(isFrom, byCompany) {
        var company;
        var contact;
        if (isFrom) {
            if (byCompany) {
                company = $("#fromCompany").val();
                $("#fromContact").val("");
                contact = "";
            } else {
                $("#fromCompany").val("");
                company = "";
                contact = $("#fromContact").val();
            }
        } else {
            if (byCompany) {
                company = $("#toCompany").val();
                $("#toContact").val("");
                contact = "";
            } else {
                $("#toCompany").val("");
                company = "";
                contact = $("#toContact").val();
            }
        }
        var data = {
            "company": company,
            "contact": contact,
            "isFrom": isFrom
        };
        $.post("settings_address_default_search.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                if (isFrom) {
                    if (byCompany) {
                        $("#from-address-search-by-company").html(res.content);
                    } else {
                        $("#from-address-search-by-contact").html(res.content);
                    }
                } else {
                    if (byCompany) {
                        $("#to-address-search-by-company").html(res.content);
                    } else {
                        $("#to-address-search-by-contact").html(res.content);
                    }
                }
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function onSelectedAddress(obj, addressId, isFrom) {
        if (isFrom) {
            $("#defaultFromAddressId").val(addressId);
            $("#from-address").html(obj.html());
        } else {
            $("#defaultToAddressId").val(addressId);
            $("#to-address").html(obj.html());
        }
    }

    function updateAddressId(isReset, isFrom) {
        var addressId;
        if (isReset) {
            addressId = 0;
        } else {
            if (isFrom) {
                addressId = $("#defaultFromAddressId").val();
            } else {
                addressId = $("#defaultToAddressId").val();
            }
        }
        var data = {
            "addressId": addressId,
            "isFrom": isFrom
        };
        $.post("settings_address_default_update.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                if (isFrom) {
                    $("#defaultFromAddressId").val(addressId);
                    $("#from-address").html(res.content);
                } else {
                    $("#defaultToAddressId").val(addressId);
                    $("#to-address").html(res.content);
                }
                alertDialog.html("Updated successfull");
                alertDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>