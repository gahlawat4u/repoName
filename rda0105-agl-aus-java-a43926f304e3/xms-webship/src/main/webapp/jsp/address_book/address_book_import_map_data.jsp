<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/xms-tags" prefix="xms" %>
<s:if test="!fieldErrors.isEmpty()">
    <div class="alert alert-danger" role="alert">
        <div class="row">
            <div class="col-lg-1" style="width: 10px;">
                <i class="fa fa-exclamation-triangle fa-fw"> </i>
            </div>
            <div class="text-left">
                <s:fielderror/>
            </div>
        </div>
    </div>
</s:if>
<div class="col-lg-12 flr pd0">
    <div class="form-group fll mgb">
        <p>
            <b><xms:localization text="Note"/>:</b> <br> -
            <xms:localization
                    text="Map the imported values on the left to the expected values on the right."/>
        </p>
    </div>
</div>
<s:form id="map-data-form">
    <table class="table table-hover  table-bordered mg0" id="">
        <thead>
        <tr bgcolor="#F5F5F5">
            <th><xms:localization text="Imported Country"/></th>
            <th><xms:localization text="Country"/></th>
            <th><xms:localization text="Imported State"/></th>
            <th><xms:localization text="State"/></th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="addressBookModels" status="count">
            <tr>
                <td style="vertical-align: middle;"><s:property
                        value="countryName"/></td>
                <td><s:select
                        name="addressBookModels[%{#count.index}].countryId"
                        value="countryId" list="countryList" listKey="countryId"
                        listValue="countryName" cssClass="form-control"
                        onchange="changeImportCountry(%{#count.index})"/></td>
                <td style="vertical-align: middle;"><s:hidden
                        id="state_hidden_%{#count.index}"
                        name="addressBookModels[%{#count.index}].state"/> <s:property
                        value="state"/></td>
                <td id="td_state_<s:property value='%{#count.index}'/>"><s:if
                        test="stateList!=null && stateList.size>0">
                    <s:select name="addressBookModels[%{#count.index}].state"
                              value="state" list="stateList" listKey="stateName"
                              listValue="stateName" cssClass="form-control"/>
                </s:if> <s:else>
                    <s:textfield name="addressBookModels[%{#count.index}].state"
                                 cssClass="form-control sel-box" data-placement="top"/>
                </s:else></td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</s:form>
<script type="text/javascript">
    function changeImportCountry(i) {
        var countryId = $('select[name="addressBookModels[' + i + '].countryId"] option:selected').val();
        var state = $("#state_hidden_" + i).val();
        var data = {
            "countryId": countryId,
            "index": i,
            "state": state
        };
        $.post("address_book_change_country.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $('#td_state_' + i).html(res.content);
            } else if (res.errorCode == "FIELD_ERROR") {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            } else if (res.errorCode == "ACTION_ERROR") {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }


</script>