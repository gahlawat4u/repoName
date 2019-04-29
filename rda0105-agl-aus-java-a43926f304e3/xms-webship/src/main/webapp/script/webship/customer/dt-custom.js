/**
 * DATO Solutions custom javascript Author: HungNT Date: Apr 10, 2015
 */
function loadDialogAddNote(action, data, formId, btnSave, btnCancel, dialogId, title, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    buttons[btnSave] = function () {
        loadingDialog.dialog("open");
        var dataForm = $('#' + formId).serialize();
        $.post(action, dataForm, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                if (divId !== '') {
                    $('#' + divId).html(res.content);
                }
                dialog.dialog("close");

                showNote();
            } else if (res.errorCode == "FIELD_ERROR") {
                dialog.html(res.content);
            } else if (res.errorCode == "ACTION_ERROR") {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("System internal error, please contact administrator.");
            alertDialog.dialog("open");
        });
    };
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    };

    var dialog = $("#" + dialogId).dialog({
        modal: true,
        autoOpen: false,
        width: "800",
        buttons: buttons,
        show: {
            effect: "fade",
            duration: 300
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            dialog.html(res.content);
            dialog.dialog("open");
        } else {
            alertDialog.html('Error: ' + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function loadDialogFieldError(action, data, formId, btnSave, btnCancel, dialogId, title, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    buttons[btnSave] = function () {
        loadingDialog.dialog("open");
        var dataForm = $('#' + formId).serialize();
        $.post(action, dataForm, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                if (divId !== '') {
                    $('#' + divId).html(res.content);
                }
                dialog.dialog("close");
            } else if (res.errorCode == "FIELD_ERROR") {
                $('#' + divId).html(res.content);
            } else if (res.errorCode == "ACTION_ERROR") {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("System internal error, please contact administrator.");
            alertDialog.dialog("open");
        });
    };
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }

    var dialog = $("#" + dialogId).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttons,
        show: {
            effect: "fade",
            duration: 300
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            dialog.html(res.content);
            dialog.dialog("open");
        } else {
            alertDialog.html('Error: ' + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function loadDeleteDialog(action, data, message, dialogId, divId, btnDelete, btnCancel, title) {
    btnDelete = typeof btnDelete !== "undefined" ? btnDelete : "Delete";
    btnCancel = typeof btnCancel !== "undefined" ? btnCancel : "Cancel";

    var buttons = {};
    buttons[btnDelete] = function () {
        loadingDialog.dialog("open");
        $.post(action, data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $('#' + divId).html(res.content);
                dialog.dialog("close");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("System internal error, please contact administrator.");
            alertDialog.dialog("open");
        });
    };
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    };

    var dialog = $("#" + dialogId).dialog({
        modal: true,
        buttons: buttons,
        width: "auto",
        show: {
            effect: "fade",
            duration: 300
        }
    });
    dialog.html(message);
    if (typeof title !== "undefined" || title !== "") {
        dialog.dialog("option", "title", title);
    } else {
        dialog.dialog("option", "title", "Delete");
    }
    dialog.dialog("open");

}

function loadConfirmDialog(action, data, message, dialogId, function_calback, btnConfirm, btnCancel, title, formId) {
    btnConfirm = typeof btnConfirm !== "undefined" ? btnConfirm : "OK";
    btnCancel = typeof btnCancel !== "undefined" ? btnCancel : "Cancel";
    var buttons = {};
    buttons[btnConfirm] = function () {
        dialog.dialog("close");
        if (typeof formId !== "undefined" || formId !== "") {
            var data2 = $('#' + formId).serializeArray();
            $.each(data2, function (i, field) {
                if (typeof data !== "undefined" || data !== "") {
                    data[field.name] = field.value;
                }
            });
        }
        loadingDialog.dialog("open");
        $.post(action, data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                dialog.dialog("close");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
        }).fail(function () {
            loadingDialog.dialog("close");
            alertDialog.html("System internal error, please contact administrator.");
            alertDialog.dialog("open");
        });
    };
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    };

    var dialog = $("#" + dialogId).dialog({
        modal: true,
        buttons: buttons,
        width: "auto",
        show: {
            effect: "fade",
            duration: 300
        }
    });
    dialog.html(message);
    if (typeof title !== "undefined" || title !== "") {
        dialog.dialog("option", "title", title);
    } else {
        dialog.dialog("option", "title", "Delete");
    }
    dialog.dialog("open");

    function_calback;
}

function loadTwoDialogWithUpdate(action, data, message, dialogId, btnConfirm, btnCancel, title, data2) {
    btnConfirm = typeof btnConfirm !== "undefined" ? btnConfirm : "Ok";
    btnCancel = typeof btnCancel !== "undefined" ? btnCancel : "Cancel";
    var buttons = {};

    buttons[btnConfirm] = function () {
        loadDialogAddNote("history_shipment_note_add.ix?reqType=json", data2, "form-add-note", "Add", "Cancel", "add-note-dialog", "Add Note", "");
    };
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    };

    var dialog = $("#" + dialogId).dialog({
        modal: true,
        buttons: buttons,
        width: "auto",
        show: {
            effect: "fade",
            duration: 300
        }
    });
    dialog.html(message);
    if (typeof title !== "undefined" || title !== "") {
        dialog.dialog("option", "title", title);
    } else {
        dialog.dialog("option", "title", "Delete");
    }

    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            dialog.html(res.content);
            dialog.dialog("open");
        } else {
            alertDialog.html('Error: ' + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });

}

function loadDetailDialog(action, data, title, btnClose, dialogId) {
    loadingDialog.dialog("open");
    var dialog = $("#" + dialogId).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        minHeight: "480px",
        minWidth: "640px",
        maxWidth: "800px",
        maxHeight: "600px",
        show: {
            effect: "fade",
            duration: 300
        },
        buttons: [{
            text: btnClose,
            click: function () {
                $(this).dialog("close");
            }
        }],
        title: title
    });

    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            dialog.html(res.content);
            dialog.dialog("open");
        } else {
            alertDialog.html('Error: ' + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}
function doPostData(action, formId, message, divId, loading) {
    loading = typeof loading !== "undefined" ? loading : true;
    if (loading) {
        loadingDialog.dialog("open");
    }
    var data = $('#' + formId).serialize();
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (divId != '') {
                $('#' + divId).html(res.content);
            }
            if (message != '') {
                alertDialog.html(message);
                alertDialog.dialog("option", "show", {
                    effect: "fade",
                    duration: 300
                });
                alertDialog.dialog("open");
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            if (divId != '') {
                $('#' + divId).html(res.content);
            } else {
                alertDialog.html(res.content);
                alertDialog.dialog("option", "show", {
                    effect: "fade",
                    duration: 300
                });
                alertDialog.dialog("open");
            }
        } else {
            alertDialog.html('Error: ' + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}
function doPostDataByParameters(action, data, message, divId, loading) {
    loading = typeof loading !== "undefined" ? loading : true;
    if (loading) {
        loadingDialog.dialog("open");
    }
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (divId != '') {
                $('#' + divId).html(res.content);
            }
            if (message != '') {
                alertDialog.html(message);
                alertDialog.dialog("open");
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            if (divId != '') {
                $('#' + divId).html(res.content);
            } else {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            }
        } else {
            alertDialog.html("Error: " + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function merge_data(data1, data2) {
    for (var key in data2) {
        if (data2.hasOwnProperty(key))
            data1[key] = data2[key]
    }
    return data1
}

function doPostDataNonError(action, formId, message, divId) {
    loadingDialog.dialog("open");
    var data = $('#' + formId).serialize();
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (divId != '') {
                $('#' + divId).html(res.content);
            }
            if (message != '') {
                alertDialog.html(message);
                alertDialog.dialog("open");
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            if (divId != '') {
                $('#' + divId).html(res.content);
            } else {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            }
        } else {
            alertDialog.html("Error: " + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function doPostDataUpdate(action, formId, message, divId, errorDiv, loading) {
    loading = typeof loading !== "undefined" ? loading : true;
    if (loading) {
        loadingDialog.dialog("open");
    }
    var data = $('#' + formId).serialize();
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            loadingDialog.dialog("open");
            $('#' + divId).html(res.content);
            loadingDialog.dialog("close");
        } else if (res.errorCode == "FIELD_ERROR") {
            if (errorDiv != '') {
                $('#' + errorDiv).html(res.content);
            } else {
                alertDialog.html(res.content);
                alertDialog.dialog("option", "show", {
                    effect: "fade",
                    duration: 300
                });
                alertDialog.dialog("open");
            }
        } else {
            alertDialog.html('Error: ' + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}
function formartNumber(evevtobj, obj, isdecimal) {
    var KeyCode;
    if (document.all) {
        KeyCode = evevtobj.keyCode;
    } else {
        KeyCode = evevtobj.which;
    }
    var str = obj.value;
    if ((KeyCode == 45) && str == "") {
        return false;
    }
    if (KeyCode == 46) {
        if (isdecimal == true) {
            if (str.indexOf(".") > -1) {
                return false;
            }
        } else {
            return false;
        }
    }
    if ((KeyCode == 0) || (KeyCode == 8)) {
        return true;
    }
    if ((KeyCode < 48 || KeyCode >= 58) && (KeyCode != 46)) {
        return false;
    }
    return true;
}

function loadDialogWithFunctionCallBack(action, data, functionCallBack, btnSave, btnCancel, dialogId, title, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            functionCallBack();
        };
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }

    var dialog = $("#" + dialogId).dialog({
        modal: true,
        autoOpen: false,
        width: "80%",
        buttons: buttons,
        show: {
            effect: "fade",
            duration: 300
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    $.post(action, data, function (res) {
        console.log(res);
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {

            dialog.html(res.content);
            dialog.dialog("open");
        } else {
            alertDialog.html('Error: ' + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}