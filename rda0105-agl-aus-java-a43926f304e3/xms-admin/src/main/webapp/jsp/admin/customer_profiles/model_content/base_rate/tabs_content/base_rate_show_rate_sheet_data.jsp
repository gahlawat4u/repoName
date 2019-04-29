<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>
<style type="text/css">
    .scroll_horizontal {
        overflow-x: scroll;
        overflow-y: hidden;
        min-height: 55px;
        width: 100%;
    }

    .div_baseRate input {
        min-width: 50px;
    }
</style>

<div class="row">
    <div class="portlet-body b12 b11">
        <div class="form-group">
            <table class="s36">
                <tr>
                    <td><xms:localization text="Print Rate Sheets"/></td>
                    <td>
                        <button class="btn s37" type="submit">
                            <xms:localization text="Check All"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn s37" type="submit">
                            <xms:localization text="Check None"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn s37" type="submit">
                            <xms:localization text="Print Checked Rate Sheets"/>
                        </button>
                    </td>
                    <td><input type="checkbox" value=""></td>
                    <td>Pdf</td>
                    <td><input type="checkbox" value=""></td>
                    <td>Excel</td>
                </tr>
            </table>
        </div>

        <s:iterator value="rateSheets">
            <div class="form-group b23">
                <table class="s36">
                    <tr>
                        <td width="290"><s:property value="key"/></td>
                        <td><input type="checkbox"/></td>
                        <td><i id="note-link3" class="fa fa-chevron-circle-right s10 b3" style="font-size: 18px;"></i>
                        </td>
                        <td><s:select list="listRateType" listKey="key" listValue="value"
                                      value='value.profileBaseRateDetail[0].rateType'
                                      class="form-control"></s:select></td>
                        <td width="50"><s:if test="value.profileBaseRateDetail[0].rateP != null">
                            <input type="text" class="form-control alloptions"
                                   value='<s:property value="value.profileBaseRateDetail[0].rateP" />'/>
                        </s:if> <s:else>
                            <input type="text" class="form-control alloptions" value='0.0'/>
                        </s:else></td>
                        <td>%</td>
                        <td><strong class="b19" sheetId='<s:property value="value.carrierRateSheetCols[0].sheetId"/>'
                                    sheetName='<s:property value="key"/>'
                                    onclick="javascript:showRateSheet($(this).attr('sheetId'), $(this).attr('sheetName'));"><u><b><xms:localization
                                text="View"/></b></u></strong></td>
                        <td><i id="note-link3" class="fa fa-minus-square s10 b3" style="font-size: 18px;"></i>&nbsp;<i
                                id="note-link3" class="fa fa-plus-square s10 b3" style="font-size: 18px;"></i></td>
                    </tr>
                </table>
            </div>
            <div class="form-group div_baseRate scroll_horizontal">
                <table class="s36">
                    <tr>
                        <td width="0">&nbsp;</td>
                        <td><s:if test="%{value.profileBaseRateDetail[0].zoneCheck == 1}">
                            <input type="checkbox" value="1" checked="checked" class="chk_rate_type_active"/>
                        </s:if> <s:else>
                            <input type="checkbox" value="0" class="chk_rate_type_active"/>
                        </s:else></td>
                        <td><xms:localization text="By Zone"/>:</td>

                        <s:iterator value="value.carrierRateSheetCols" var="carrierBaseRate" status="status">
                            <td width="70"><s:textfield name="columnName" readonly="true"
                                                        class="form-control alloptions text-center" maxlength="25"
                                                        placeholder="1"></s:textfield> <s:if
                                    test="%{value.profileBaseRateDetail.size() > 0 }">
                                <s:if test="%{value.profileBaseRateDetail[#status.index].rate != null}">
                                    <input type="text"
                                           value='<s:property value="value.profileBaseRateDetail[#status.index].rate"/>'
                                           class="form-control alloptions text-center" maxlength="25"/>
                                </s:if>
                                <s:elseif test="%{value.profileBaseRateDetail[#status.index].rateP != null}">
                                    <input type="text"
                                           value='<s:property value="value.profileBaseRateDetail[#status.index].rateP"/>'
                                           class="form-control alloptions text-center" maxlength="25"/>
                                </s:elseif>
                                <s:else>
                                    <input type="text" value='<s:property value="0.0"/>'
                                           class="form-control alloptions text-center" maxlength="25"/>
                                </s:else>
                            </s:if> <s:else>
                                <input type="text" value='<s:property value="0.0" />'
                                       class="form-control alloptions text-center" maxlength="25" placeholder="1"
                                       readonly="readonly"/>
                            </s:else></td>
                        </s:iterator>
                    </tr>
                </table>
            </div>
        </s:iterator>
    </div>
</div>
<div id="sheetDialog"></div>
<script type="text/javascript">
    $(document).ready(function () {
        $("div.div_baseRate").hide();
        $(".chk_rate_type_active").each(function () {
            if ($(this).is(':checked')) {
                $(this).parents("div.div_baseRate").slideDown("slow");
            }
        });

    });

    $("i.fa-plus-square").click(function () {
        $(this).parents("div.form-group").next().slideDown("slow");
    });
    $("i.fa-minus-square").click(function () {
        $(this).parents("div.form-group").next().slideUp("slow");
    });

    function showRateSheet(sheetId, sheetName) {
        var data = {
            'sheetId': sheetId,
            'sheetName': sheetName,
            'franchiseCode': $('#sel_franchise').val()
        };
        var action = 'manager_customer_profiles_base_rate_show_rate_sheet.ix?reqType=json';
        loadDialog(action, data, '', '', 'Close', 'sheetDialog', 'Rate Sheet', '');
    }

</script>