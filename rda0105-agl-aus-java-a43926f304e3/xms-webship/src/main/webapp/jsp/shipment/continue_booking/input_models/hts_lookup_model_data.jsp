<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div id="lookup" title="Look up">
    <div class="col-lg-12 flr pd0">
        <div class="form-group fll mgb">
            <table class="s36" style="margin-bottom: 10px;">
                <tr>
                    <td><s:textfield cssClass="form-control" name="description" id="txt_description"></s:textfield></td>
                    <td>
                        <button class="btn s33" type="button" onclick="javascript:htsGoodSearch();">
                            <xms:localization text="Search"/>
                        </button>
                    </td>
                </tr>
            </table>
        </div>
    </div>
    <div id="hts_good_lookup_search">
        <table class="table table-hover  table-bordered mg0" id="datatable_lookup">
            <thead>
            <tr bgcolor="#F5F5F5">
                <th><xms:localization text="Code"/></th>
                <th><xms:localization text="Description"/></th>
            </tr>
            </thead>
            <tbody>
            <s:if test="!listHtsGood.isEmpty()">
                <s:iterator value="listHtsGood">
                    <tr data-code="<s:property value="code"/>" data-description="<s:property value="description"/>">
                        <td><s:property value="code"/> <s:hidden name="code" id="txt_hts_code"></s:hidden></td>
                        <td><s:property value="description"/></td>
                    </tr>
                </s:iterator>
            </s:if>
            </tbody>
        </table>
    </div>
    <br>
</div>
<script type="text/javascript">
    var txt_hts_code = "";
    var htsCodeIndex = <s:property value="index"/>;
    $(document).ready(function () {
        $('table#datatable_lookup tbody tr').click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
        });
        $('table#datatable_lookup tbody tr').dblclick(function () {
            txt_hts_code = $(this).find('#txt_hts_code').val();
            $("table#packing_list_table tr:eq(" + htsCodeIndex + ")").find("#hts_lookup").val(txt_hts_code);
            $("#hts_good_lookup_load").dialog("close");
        });
    });

    function htsGoodSearch() {
        var data = {
            'description': $("#txt_description").val()
        };
        loadingDialog.dialog("open");
        $.post("hts_good_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#hts_good_lookup_search").html(res.content);
            } else {
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