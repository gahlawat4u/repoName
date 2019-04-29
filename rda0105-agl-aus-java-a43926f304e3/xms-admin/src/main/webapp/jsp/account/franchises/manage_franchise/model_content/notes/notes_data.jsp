<%@page import="com.gms.xms.weblib.utils.WebUtils" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@taglib prefix="xms" uri="/xms-tags" %>

<script src="<%=WebUtils.getContextPathURL(request)%>script/xms/customer/dato.tablesorter.js"></script>
<div class="row" id="row_note_create">
    <div class="portlet-body b12 b11">
        <table class="" style="font-size: 11px;">
            <tbody>
            <tr>
                <td style="border-top: 0px !important" colspan="">
                    <div class="caption b17">
                        <xms:localization text="Notes"/>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <table class="table mg0">
            <tbody>
            <tr>
                <td><xms:localization text="Type text in the box below for the initial note on this account."/></td>
            </tr>
            <tr>
                <td><s:textarea name="note.note" cols="60" rows="10" cssClass="form-control"></s:textarea></td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="row" id="row_note_update">
    <div class="portlet-body b12 b11">
        <div class="form-group flr ">
            <table class="s36 ">
                <tr>
                    <td>
                        <button class="btn s37" type="button" onclick="deleteNote()">
                            <xms:localization text="Delete"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn s37" type="button" onclick="showNoteDialog(false)">
                            <xms:localization text="Edit"/>
                        </button>
                    </td>
                    <td>
                        <button class="btn s37" type="button" onclick="showNoteDialog(true)">
                            <xms:localization text="Add"/>
                        </button>
                    </td>
                </tr>
            </table>
        </div>
        <table class="" style="font-size: 11px;">
            <tbody>
            <tr>
                <td style="border-top: 0px !important" colspan="">
                    <div class="caption b17">
                        <xms:localization text="Notes"/>
                    </div>
                </td>
            </tr>
            </tbody>
        </table>
        <table class="table mg0">
            <tbody>
            <tr>
                <th class="s42">
                    <form id="franchise_notes_form">
                        <s:hidden id="franchise_notes_order_field" name="orderField"/>
                        <s:hidden id="franchise_notes_order_type" name="orderType"/>
                        <s:hidden id="franchise_notes_page" name="page"/>
                        <table class="s36">
                            <tbody>
                            <tr>
                                <td><xms:localization text="Show"/></td>
                                <td><s:select id="franchise_notes_page_size" name="pageSize" list="pageSizes"
                                              cssClass="form-control" cssStyle="height: 22px; padding-top: 1px;"
                                              onchange="getNotes(1)"/></td>
                                <td><xms:localization text="Entries"/></td>
                            </tr>
                            </tbody>
                        </table>
                    </form>
                </th>
            </tr>
            </tbody>
        </table>
        <div id="notes-list-result">
            <table class="table table-bordered mg0" id="franchise_notes_list_table">
                <thead>
                <tr>
                    <th><xms:localization text="Last Modified"/></th>
                    <th><xms:localization text="Franchise"/></th>
                    <th><xms:localization text="User"/></th>
                    <th><xms:localization text="Account"/></th>
                    <th><xms:localization text="Note"/></th>
                </tr>
                </thead>
                <tbody>
                <s:if test="notes!=null && notes.totalRecords>0">
                    <s:iterator value="notes.records">
                        <tr data-group="note-row" noteid="<s:property value="noteId" />">
                            <td><s:property value="modifyDate"/></td>
                            <td><s:property value="user.userCode"/></td>
                            <td><s:property value="user.displayName"/></td>
                            <td><s:property value="accountNo"/></td>
                            <td><s:property value="note"/></td>
                        </tr>
                    </s:iterator>
                </s:if>
                <s:else>
                    <tr>
                        <td colspan="5"><xms:localization text="No data available..."/></td>
                    </tr>
                </s:else>
                </tbody>
            </table>
            <div class="dataTables_paginate records" style="margin-bottom:15px;">
                <div class="row">
                    <div class="col-xs-4 text-left">
                        <b><xms:localization text="Showing"/> <s:property value="notes.startRecord"/> <xms:localization
                                text="to"/> <s:property value="notes.endRecord"/> <xms:localization text="of"/>
                            <s:property value="notes.totalRecords"/> <xms:localization text="entries"/></b>
                    </div>
                    <div class="col-xs-8">
                        <s:if test="!notes.hasPrev()">
                            <a class="paginate_button previous disabled"><xms:localization text="Previous"/></a>
                        </s:if>
                        <s:else>
                            <a href="javascript:getNotes(<s:property value="%{notes.currentPage - 1}"/>)"
                               class="paginate_button previous"><xms:localization text="Previous"/></a>
                        </s:else>
						<span> <s:iterator value="notes.pageRange" status="count">
                            <s:if test="%{notes.pageRange[#count.index] == notes.currentPage}">
                                <a class="paginate_button current"><s:property value="notes.currentPage"/></a>
                            </s:if>
                            <s:else>
                                <a class="paginate_button" href="javascript:getNotes(<s:property/>);"><s:property/></a>
                            </s:else>
                        </s:iterator>
						</span>
                        <s:if test="!notes.hasNext()">
                            <a class="paginate_button next disabled"><xms:localization text="Next"/></a>
                        </s:if>
                        <s:else>
                            <a class="paginate_button next"
                               href="javascript:getNotes(<s:property value="%{notes.currentPage+1}"/>)"><xms:localization
                                    text="Next"/></a>
                        </s:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="note-dialog"></div>

<script type="text/javascript">
    var noteId = "";

    $(document).ready(function () {
        $("tr[data-group='note-row']").click(function () {
            $(this).addClass('selected-row').siblings().removeClass('selected-row');
            noteId = $(this).attr("noteid");
        });
        if ($("#sel_franchise").val() == '0') {
            $("#row_note_update").html("");
        } else {
            $("#row_note_create").html("");
        }

        var fieldList = ["modify_date", "user_code", "display_name", "note"];
        $("#franchise_notes_list_table").tablesorter({
            sortFieldId: "franchise_notes_order_field",
            sortTypeId: "franchise_notes_order_type",
            fieldList: fieldList,
            callback: searchFranchiseNotes
        });
    });

    function searchFranchiseNotes() {
        // Reset note id
        noteId = "";
        // Load Note List
        var data = $("#franchise_notes_form").serialize();
        data += "&customerCode=" + $("#sel_franchise option:selected").val();
        loadingDialog.dialog("open");
        $.post("manage_franchise_notes_search.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $("#notes-list-result").html(res.content);
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

    function getNotes(page) {
        $("#franchise_notes_page").val(page);
        searchFranchiseNotes();
    }

    function showNoteDialog(isAdd) {
        if (!isAdd) {
            if (noteId == "") {
                alertDialog.html("Please choose a note for editing");
                alertDialog.dialog("open");
            } else {
                loadNote(noteId);
            }
        } else {
            loadNote("");
        }
    }

    function deleteNote() {
        if (noteId == "") {
            alertDialog.html("Please choose a note for deleting");
            alertDialog.dialog("open");
        } else {
            var answer = window.confirm("Are you sure?");
            if (answer) {
                loadingDialog.dialog("open");
                $.post("manage_customers_notes_delete.ix?reqType=json", "noteId=" + noteId, function (res) {
                    loadingDialog.dialog("close");
                    if (res.errorCode == "SUCCESS") {
                        getNotes(1);
                        messageDialog.html("Delete successfully");
                        messageDialog.dialog("open");
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
        }
    }

    function loadNote(noteId) {
        var data = {
            "noteId": noteId,
            "customerCode": $("#sel_franchise option:selected").val()
        };
        loadingDialog.dialog("open");
        $.post("manage_customers_notes_load.ix?reqType=json", data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                // Define Save and Cancel buttons
                var buttons = {};
                buttons["Save"] = function () {
                    var formData = $("#note-form").serialize();
                    loadingDialog.dialog("open");
                    $.post("manage_customers_notes_save.ix?reqType=json", formData, function (res) {
                        loadingDialog.dialog("close");
                        if (res.errorCode == "SUCCESS") {
                            $("#note-dialog").html("");
                            $("#note-dialog").dialog("close");
                            getNotes(1);
                            messageDialog.html("Save successfully");
                            messageDialog.dialog("open");
                        } else if (res.errorCode == "FIELD_ERROR") {
                            $("#note-dialog").html(res.content);
                        } else {
                            alertDialog.html(res.errorMsg);
                            alertDialog.dialog("open");
                        }
                    }).fail(function () {
                        loadingDialog.dialog("close");
                        alertDialog.html("<xms:localization text="System internal error, please contact administrator." />");
                        alertDialog.dialog("open");
                    });
                };
                buttons["Cancel"] = function () {
                    $(this).dialog("close");
                }
                // Define dialog
                var dialog = $("#note-dialog").dialog({
                    modal: true,
                    autoOpen: false,
                    width: "auto",
                    buttons: buttons,
                    show: {
                        effect: "fade",
                        duration: 300
                    }
                });
                // Dialog title
                if (noteId == "") {
                    dialog.dialog("option", "title", "Add a Note");
                } else {
                    dialog.dialog("option", "title", "Edit Note");
                }
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


</script>