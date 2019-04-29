<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="xms" uri="/xms-tags" %>
<div class="row">
    <div class="col-lg-8">
        <div class="portlet box">
            <div class="portlet-header">
                <div class="caption w4">
                    <xms:localization text="Dimensions"/>
                </div>
            </div>
            <div class="portlet-body">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="text-left">
                            <ul class="pagination mtn mbn" data-hover="">
                                <li><span class="lsc"><xms:localization text="Show"/></span></li>
                                <li><span class="lsc t1"><s:select name="pageSize" list="pageSizeList"
                                                                   cssClass="form-control"
                                                                   onchange="changePageSize()"/> </span></li>
                                <li><span class="lsc"><xms:localization text="entries"/></span></li>
                            </ul>
                        </div>
                        <table class="table table-hover table-bordered mg0" id="datatable1">
                            <thead>
                            <tr>
                                <th><xms:localization text="Name"/></th>
                                <th><xms:localization text="L"/></th>
                                <th><xms:localization text="W"/></th>
                                <th><xms:localization text="H"/></th>
                                <th><xms:localization text="Comment"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <s:if test="dimensionList==null || dimensionList.size()==0">
                                <tr>
                                    <td colspan="5"><xms:localization text="No data available..."/></td>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:iterator value="dimensionList.records">
                                    <tr onclick='dimensionSelect(<s:property value="id"/>,$(this))'>
                                        <td><s:property value="name"/></td>
                                        <td><s:property value="l"/></td>
                                        <td><s:property value="w"/></td>
                                        <td><s:property value="h"/></td>
                                        <td><s:property value="comment"/></td>
                                    </tr>
                                </s:iterator>
                            </s:else>
                            </tbody>
                        </table>
                        <s:if test="dimensionList.totalPage != 1">
                            <div class="text-right">
                                <ul class="pagination mts mbs" data-hover="">
                                    <s:if test="!dimensionList.hasPrev()">
                                        <li class="disabled"><a><xms:localization text="Previous"/></a></li>
                                    </s:if>
                                    <s:else>
                                        <li>
                                            <a href="javascript:changePage(<s:property value="dimensionList - 1}"/>)"><xms:localization
                                                    text="Previous"/></a></li>
                                    </s:else>
                                    <s:iterator value="dimensionList.pageRange" status="count">
                                        <s:if test="%{dimensionList.pageRange[#count.index] == dimensionList.currentPage}">
                                            <li class="active"><a><s:property value="dimensionList.currentPage"/></a>
                                            </li>
                                        </s:if>
                                        <s:else>
                                            <li><a href="javascript:changePage(<s:property/>);"><s:property/></a></li>
                                        </s:else>
                                    </s:iterator>
                                    <s:if test="!dimensionList.hasNext()">
                                        <li class="disabled"><a href="#"><xms:localization text="Next"/></a></li>
                                    </s:if>
                                    <s:else>
                                        <li>
                                            <a href="javascript:changePage(<s:property value="%{dimensionList.currentPage + 1}"/>)"><xms:localization
                                                    text="Next"/></a></li>
                                    </s:else>
                                </ul>
                            </div>
                        </s:if>
                    </div>
                    <s:hidden name="page"/>
                </div>
                <div class=" text-right pal pdt10">
                    <div class="row">
                        <div class="col-lg-12">
                            <button class="btn s33 s44" type="button" onclick="onAddDimension()">
                                <xms:localization text="Add"/>
                            </button>
                            <button class="btn s33 s44" type="button" onclick="onEditDimension()">
                                <xms:localization text="Edit"/>
                            </button>
                            <button class="btn s33 s44" type="button" onclick="onDeleteDimension()">
                                <xms:localization text="Delete"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="add-dimension-dialog" title="Add Dimension"></div>
<div id="edit-dimension-dialog" title="Edit Dimension"></div>
<input type="hidden" id="edit-dimension-id"/>
<script type="text/javascript">
    function changePage(page) {
        $("#page").val(page);
        loadDimensionList();
    }

    function changePageSize() {
        loadDimensionList();
    }

    function loadDimensionList() {
        var page = $("#page").val();
        var pageSize = $("#pageSize option:selected").val();
        var data = {
            "page": page,
            "pageSize": pageSize
        };
        $.post("settings_load_dimension_list.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                $("#Dimensions-tab").html(res.content);
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function onAddDimension() {
        var addDialog = $("#add-dimension-dialog").dialog({
            modal: true,
            autoOpen: false,
            width: "auto",
            buttons: {
                "Save": function () {
                    var data = $("#dimension-form").serialize();
                    $.post("settings_add_dimension.ix?reqType=json", data, function (res) {
                        if (res.errorCode == "SUCCESS") {
                            loadDimensionList();
                        }
                        addDialog.html(res.content);
                    }).fail(function () {
                        alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                        alertDialog.dialog("open");
                    });
                },
                "Cancel": function () {
                    $(this).dialog("close");
                }
            },
            show: {
                effect: "fade",
                duration: 300
            },
            close: function (event, ui) {
                addDialog.html("");
            }
        });
        $.post("settings_add_dimension.ix?reqType=json", "", function (res) {
            if (res.errorCode == "SUCCESS") {
                addDialog.html(res.content);
                addDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function onEditDimension() {
        var id = $("#edit-dimension-id").val();
        if (id == "") {
            alertDialog.html("Please select a dimension for editing");
            alertDialog.dialog("open");
            return;
        }
        var editDialog = $("#edit-dimension-dialog").dialog({
            modal: true,
            autoOpen: false,
            width: "auto",
            buttons: {
                "Save": function () {
                    var data = $("#dimension-form").serialize();
                    console.log(data);
                    $.post("settings_edit_dimension.ix?reqType=json", data, function (res) {
                        if (res.errorCode == "SUCCESS") {
                            loadDimensionList();
                        }
                        editDialog.html(res.content);
                    }).fail(function () {
                        alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
                        alertDialog.dialog("open");
                    });
                },
                "Cancel": function () {
                    $(this).dialog("close");
                }
            },
            show: {
                effect: "fade",
                duration: 300
            },
            close: function (event, ui) {
                editDialog.html("");
            }
        });
        var data = {
            "id": id
        };
        $.post("settings_edit_dimension.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                editDialog.html(res.content);
                editDialog.dialog("open");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function onDeleteDimension() {
        var id = $("#edit-dimension-id").val();
        if (id == "") {
            alertDialog.html("Please select a dimension for deleting");
            alertDialog.dialog("open");
            return;
        }
        var data = {
            "id": id
        };
        $.post("settings_delete_dimension.ix?reqType=json", data, function (res) {
            if (res.errorCode == "SUCCESS") {
                alertDialog.html("Dimension deleted successfull");
                alertDialog.dialog("open");
                loadDimensionList();
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            alertDialog.html('<xms:localization text="System internal error, please contact administrator." />');
            alertDialog.dialog("open");
        });
    }

    function dimensionSelect(id, obj) {
        $("#edit-dimension-id").val(id);
        obj.addClass('selected-row').siblings().removeClass('selected-row');
    }
</script>