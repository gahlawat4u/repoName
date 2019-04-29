/**
 * DATO Solutions custom javascript Author: HungNT Date: Apr 10, 2015
 */
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

function loadDialog(action, data, formId, btnSave, btnCancel, dialogId, title, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
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
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.70;
    width = width * 0.9;
    var dialog = $("#" + dialogId).dialog({
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
            $("#" + dialogId).html("");
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

function loadDialog2action(action, actionSave, data, formId, btnSave, btnCancel, dialogId, title, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    if (divId !== '') {
                        $('#' + divId).html(res.content);
                    }
                    dialog.dialog("close");
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
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.70;
    width = width * 0.9;
    var dialog = $("#" + dialogId).dialog({
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
            $("#" + dialogId).html("");
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
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
            }
            dialog.dialog("close");
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

function loadDeleteDialogWithCallBack(action, data, message, dialogId, divId, btnDelete, btnCancel, title, callBackFunction) {
    btnDelete = typeof btnDelete !== "undefined" ? btnDelete : "Delete";
    btnCancel = typeof btnCancel !== "undefined" ? btnCancel : "Cancel";

    var buttons = {};
    buttons[btnDelete] = function () {
        loadingDialog.dialog("open");
        $.post(action, data, function (res) {
            loadingDialog.dialog("close");
            if (res.errorCode == "SUCCESS") {
                $('#' + divId).html(res.content);
                callBackFunction();
                dialog.dialog("close");
            } else {
                alertDialog.html(res.errorMsg);
                alertDialog.dialog("open");
                dialog.dialog("close");
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

function loadDetailDialog(action, data, title, btnClose, dialogId) {
    loadingDialog.dialog("open");
    var dialog = $("#" + dialogId).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        minHeight: "300px",
        minWidth: "300px",
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
                messageDialog.html(message);
                messageDialog.dialog("open");
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
            alertDialog.html(res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function doPostDataByParameters(action, data, message, divId, loading, errorPopup) {
    loading = typeof loading !== "undefined" ? loading : true;
    errorPopup = typeof errorPopup !== "undefined" ? errorPopup : false;
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
                messageDialog.html(message);
                messageDialog.dialog("open");
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            if (errorPopup) {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                if (divId != '') {
                    $('#' + divId).html(res.content);
                } else {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
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

function doPostDataByParametersWithCallBack(action, data, message, divId, loading, errorPopup, callbackFunction) {
    loading = typeof loading !== "undefined" ? loading : true;
    errorPopup = typeof errorPopup !== "undefined" ? errorPopup : false;
    if (loading) {
        loadingDialog.dialog("open");
    }
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (divId != '') {
                $('#' + divId).html(res.content);
                if (typeof (callbackFunction) == "function") {
                    callbackFunction();
                }
            }
            if (message != '') {
                messageDialog.html(message);
                messageDialog.dialog("open");
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            if (errorPopup) {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                if (divId != '') {
                    $('#' + divId).html(res.content);
                } else {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
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

function doPostDataByParametersWithCallBack(action, data, message, divId, loading, errorPopup, callbackSuccess, callbackFail) {
    loading = typeof loading !== "undefined" ? loading : true;
    errorPopup = typeof errorPopup !== "undefined" ? errorPopup : false;
    if (loading) {
        loadingDialog.dialog("open");
    }
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (divId != '') {
                $('#' + divId).html(res.content);
                if (typeof (callbackSuccess) == "function") {
                    callbackSuccess();
                }
            }
            if (message != '') {
                messageDialog.html(message);
                messageDialog.dialog("open");
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            if (errorPopup) {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                if (divId != '') {
                    $('#' + divId).html(res.content);
                } else {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
            }
            if (typeof (callbackFail) == "function") {
                callbackFail();
            }
        } else {
            alertDialog.html("Error: " + res.errorMsg);
            alertDialog.dialog("open");
            if (typeof (callbackFail) == "function") {
                callbackFail();
            }
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
function doPostWithError(action, data, message, contentId, errorId) {
    loadingDialog.dialog("open");
    $.post(action, data, function (res) {
        $('#' + errorId).html("");
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (contentId != '') {
                $('#' + contentId).html(res.content);
            }
            if (message != '') {
                alertDialog.html(message);
                alertDialog.dialog("open");
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            $('#' + errorId).html(res.content);
        } else {
            alertDialog.html('Error: ' + res.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        BootstrapDialog.alertDialog('System Error: System internal error, please contact administrator.');
    });
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

function doPostDataDiagLogError(action, formId, message, divId) {
    loadingDialog.dialog("open");
    var data = $('#' + formId).serialize();
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (divId != '') {
                $('#' + divId).html(res.content);
            }
        } else if (res.errorCode == "FIELD_ERROR") {
            alertDialog.html(res.content);
            alertDialog.dialog("open");
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

function loadDialogFieldError(action, data, formId, btnSave, btnCancel, dialogId, title, divId, message) {
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
                if (typeof (message) != "undefined" && message != "") {
                    messageDialog.html(message);
                    messageDialog.dialog("open");
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

function loadDialogWithFunctionCallBack(action, data, functionCallBack, btnSave, btnCancel, dialogId, title, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    buttons[btnSave] = function () {
        functionCallBack();
    };
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

function loadDialogToSave(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    if (divSaveResult !== '') {
                        dialog.dialog("close");
                        dialogResult.html(res.content);
                        dialogResult.dialog("open");
                    }
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
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    var buttonResult = {};
    buttonResult["Close"] = function () {
        $(this).dialog("close");
    }
    var dialogResult = $("#" + divSaveResult).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttonResult,
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
            $("#" + divSaveResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialogResult.dialog("option", "title", title + " Result");
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function loadDialogToEditAirbill(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult, action, formId, message, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    if (divSaveResult !== '') {
                        dialog.dialog("close");
                        dialogResult.html(res.content);
                        dialogResult.dialog("open");
                    }
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
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
        resizable: false,
        show: {
            effect: "fade",
            duration: 300
        },
        close: function (e) {
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    var buttonResult = {};
    buttonResult["Close"] = function () {
        $(this).dialog("close");
    }
    var dialogResult = $("#" + divSaveResult).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttonResult,
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
        close: function (event, e) {
            $("#" + divSaveResult).html("");
            loadingDialog.dialog("open");
            var data = $('#' + formId).serialize();
            $.post(action, data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    if (divId != '') {
                        $('#' + divId).html(res.content);
                    }
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
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
    });

    if (typeof title != "undefined" && title != "") {
        dialogResult.dialog("option", "title", title + " Result");
    }

    $.post(actionLoad, dataLoad, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            dialog.html(res.content);
            var invStatus = dialog.find("input[name='invoiceStatus']").val();
            if (invStatus != "0") {
                delete buttons[btnSave];
            }
            dialog.dialog("option", "buttons", buttons);
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

function loadDialogToMoveAirbill(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult, action, formId, message, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    if (divSaveResult !== '') {
                        dialog.dialog("close");
                        dialogResult.html(res.content);
                        dialogResult.dialog("open");
                    }
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
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
        close: function (event, e) {
            $("#" + divLoadResult).html("");
            loadingDialog.dialog("open");
            var data = $('#' + formId).serialize();
            $.post(action, data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    if (divId != '') {
                        $('#' + divId).html(res.content);
                    }
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
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
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    var buttonResult = {};
    buttonResult["Close"] = function () {
        $(this).dialog("close");
    }
    var dialogResult = $("#" + divSaveResult).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttonResult,
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
            $("#" + divSaveResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialogResult.dialog("option", "title", title + " Result");
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function loadDialogToSaveCallBack(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult, functionCallBack) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    if (divSaveResult !== '') {
                        dialog.dialog("close");
                        dialogResult.html(res.content);
                        dialogResult.dialog("open");
                    }
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
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    } else {
        $(".ui-dialog-titlebar").hide();
    }

    var buttonResult = {};
    buttonResult["Close"] = function () {
        $(this).dialog("close");
        functionCallBack();
    }
    var dialogResult = $("#" + divSaveResult).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttonResult,
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
            $("#" + divSaveResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialogResult.dialog("option", "title", title + " Result");
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function loadResultToSave(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    dialog.dialog("close");
                    if (divSaveResult !== '') {
                        $("#" + divSaveResult).html(res.content);
                    }
                } else if (res.errorCode == "ACTION_ERROR") {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html("System internal error, please contact administrator.");
                alertDialog.dialog("open");
            });
        };
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function loadResultToDeleteAirbill(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult, action, formId, message, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    dialog.dialog("close");
                    if (divSaveResult !== '') {
                        $("#" + divSaveResult).html(res.content);
                        loadingDialog.dialog("open");
                        var data = $('#' + formId).serialize();
                        $.post(action, data, function (res) {
                            loadingDialog.dialog("close");
                            if (res.errorCode == "SUCCESS") {
                                if (divId != '') {
                                    $('#' + divId).html(res.content);
                                }
                            } else if (res.errorCode == "FIELD_ERROR") {
                                alertDialog.html(res.content);
                                alertDialog.dialog("open");
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
                } else if (res.errorCode == "ACTION_ERROR") {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html("System internal error, please contact administrator.");
                alertDialog.dialog("open");
            });
        };
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function loadResultToSaveNewAirbill(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    dialog.dialog("close");
                    messageDialog.html("Create new airbill success!");
                    messageDialog.dialog("open");
                    if (divSaveResult !== '') {
                        $("#" + divSaveResult).html(res.content);
                    }
                } else if (res.errorCode == "ACTION_ERROR") {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html("System internal error, please contact administrator.");
                alertDialog.dialog("open");
            });
        };
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function loadResultToEditAirbill(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    dialog.dialog("close");
                    dialog.html("");
                    messageDialog.html("Edit airbill success!");
                    messageDialog.dialog("open");
                    if (divSaveResult !== '') {
                        $("#" + divSaveResult).html(res.content);
                    }
                } else if (res.errorCode == "ACTION_ERROR") {
                    alertDialog.html(res.errorMsg);
                    alertDialog.dialog("open");
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html("System internal error, please contact administrator.");
                alertDialog.dialog("open");
            });
        };
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function doPostDataByParametersTwoAcction(action1, data1, divId1, action2, data2, divId2) {
    loadingDialog.dialog("open");
    $.post(action1, data1, function (res1) {
        loadingDialog.dialog("close");
        if (res1.errorCode == "SUCCESS") {
            if (divId1 != '') {
                $('#' + divId1).html(res1.content);
            }
            $.post(action2, data2, function (res2) {
                if (res2.errorCode == "SUCCESS") {
                    if (divId2 != '') {
                        $('#' + divId2).html(res2.content);
                    }
                } else if (res2.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res2.content);
                    alertDialog.dialog("open");
                } else {
                    alertDialog.html("Error: " + res2.errorMsg);
                    alertDialog.dialog("open");
                }
            }).fail(function () {
                loadingDialog.dialog("close");
                alertDialog.html("System internal error, please contact administrator.");
                alertDialog.dialog("open");
            });
        } else if (res1.errorCode == "FIELD_ERROR") {
            alertDialog.html(res1.content);
            alertDialog.dialog("open");
        } else {
            alertDialog.html("Error: " + res1.errorMsg);
            alertDialog.dialog("open");
        }
    }).fail(function () {
        loadingDialog.dialog("close");
        alertDialog.html("System internal error, please contact administrator.");
        alertDialog.dialog("open");
    });
}

function loadTwoDialogWithUpdate(action, data, message, dialogId, btnConfirm, btnCancel, title, data2) {
    btnConfirm = typeof btnConfirm !== "undefined" ? btnConfirm : "Ok";
    btnCancel = typeof btnCancel !== "undefined" ? btnCancel : "Cancel";
    var buttons = {};

    buttons[btnConfirm] = function () {
        loadDialog("history_shipment_note_add.ix?reqType=json", data2, "form-add-note", "Add", "Cancel", "add-note-dialog", "Add Note", "");
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

function load2DialogToSave(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult, title2, btnSave2, divSaveResult2, action, formId) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    dialogResult.html(res.content);
                    dialogResult.dialog("open");
                } else if (res.errorCode == "FIELD_ERROR") {
                    dialog.html(res.content);
                    dialog.dialog("open");
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
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    var buttonResult = {};
    buttonResult["Close"] = function () {
        $(this).dialog("close");
    }
    var buttons2 = {};
    if (btnSave2 != "") {
        buttons2[btnSave2] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formId).serialize();
            $.post(action, dataForm, function (res) {
                loadingDialog.dialog("close");
                dialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    dialogResult.dialog("close");
                    messageDialog.html("Add new invoice success!");
                    messageDialog.dialog("open");
                    $("#" + divSaveResult2).html(res.content);
                } else if (res.errorCode == "FIELD_ERROR") {
                    dialogResult2.html(res.content);
                    dialogResult2.dialog("open");
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
    }
    buttons2[btnCancel] = function () {
        $(this).dialog("close");
    }
    var dialogResult = $("#" + divSaveResult).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttons2,
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
            $("#" + divSaveResult).html("");
        }
    });

    if (typeof title2 != "undefined" && title2 != "") {
        dialogResult.dialog("option", "title", title2);
    }

    var dialogResult2 = $("#" + divSaveResult2).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttonResult,
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
            $("#" + divSaveResult).html("");
        }
    });

    if (typeof title2 != "undefined" && title2 != "") {
        dialogResult2.dialog("option", "title", title2 + " Result");
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function loadDialogToSaveMassEdit(actionLoad, actionSave, dataLoad, formSaveId, btnSave, btnCancel, divLoadResult, title, divSaveResult, action, formId, message, divId) {
    loadingDialog.dialog("open");
    var buttons = {};
    if (btnSave != "") {
        buttons[btnSave] = function () {
            loadingDialog.dialog("open");
            var dataForm = $('#' + formSaveId).serialize();
            $.post(actionSave, dataForm, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    dialog.dialog("close");
                    if (divSaveResult !== '') {
                        dialogResult.html(res.content);
                        dialogResult.dialog("open");
                    }
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
    }
    buttons[btnCancel] = function () {
        $(this).dialog("close");
        $(this).empty();
    }
    var height = $(window).height();
    var width = $(window).width();
    height = height * 0.9;
    width = width * 0.9;
    var dialog = $("#" + divLoadResult).dialog({
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
            $("#" + divLoadResult).html("");
        }
    });

    if (typeof title != "undefined" && title != "") {
        dialog.dialog("option", "title", title);
    }

    var buttonResult = {};
    buttonResult["Close"] = function () {
        $(this).dialog("close");
    }
    var dialogResult = $("#" + divSaveResult).dialog({
        modal: true,
        autoOpen: false,
        width: "auto",
        buttons: buttonResult,
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
        close: function (event, e) {
            $("#" + divSaveResult).html("");
            var data = $('#' + formId).serialize();
            loadingDialog.dialog("open");
            $.post(action, data, function (res) {
                loadingDialog.dialog("close");
                if (res.errorCode == "SUCCESS") {
                    if (divId != '') {
                        $('#' + divId).html(res.content);
                    }
                } else if (res.errorCode == "FIELD_ERROR") {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
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
    });

    if (typeof title != "undefined" && title != "") {
        dialogResult.dialog("option", "title", title + " Result");
    }

    $.post(actionLoad, dataLoad, function (res) {
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

function getServiceLevelAndAccessorials(action, data, message, divId, loading, errorPopup, action2, divId2) {
    loading = typeof loading !== "undefined" ? loading : true;
    errorPopup = typeof errorPopup !== "undefined" ? errorPopup : false;
    if (loading) {
        loadingDialog.dialog("open");
    }
    $.post(action, data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            if (divId != '') {
                $('#' + divId).html(res.content);
            }
            $.post(action2, data, function (res) {
                if (res.errorCode == "SUCCESS") {
                    for (i = 0; i < 8; i++) {
                        var selName = "accessorialInfoModels[" + i + "].description";
                        var selId = "accessorialInfoModels_" + i + "__description";
                        var selObject = $("select[name='" + selName + "']");
                        $('#' + divId2 + '_' + i).html(res.content.trim());
                        $('#' + divId2 + '_' + i).find("select").attr("name", selName);
                        $('#' + divId2 + '_' + i).find("select").attr("id", selId);
                    }
                } else if (res.errorCode == "FIELD_ERROR") {
                    if (errorPopup) {
                        alertDialog.html(res.content);
                        alertDialog.dialog("open");
                    } else {
                        if (divId != '') {
                            $('#' + divId).html(res.content);
                        } else {
                            alertDialog.html(res.content);
                            alertDialog.dialog("open");
                        }
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
        } else if (res.errorCode == "FIELD_ERROR") {
            if (errorPopup) {
                alertDialog.html(res.content);
                alertDialog.dialog("open");
            } else {
                if (divId != '') {
                    $('#' + divId).html(res.content);
                } else {
                    alertDialog.html(res.content);
                    alertDialog.dialog("open");
                }
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

function viewAirbillRateSheet(shipmentId, airbillNumber) {
    loadingDialog.dialog("open");
    var data = {
        'shipmentId': shipmentId,
        'airbillNumber': airbillNumber,
    };
    $.post("view_airbill_rate_sheet.ix?reqType=json", data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            var uniqueId = createUniqueId("view_awb_rate_sheet", "dialog");
            var dialogId = "<div id='" + uniqueId + "'></div>";
            var dialog = $(dialogId).dialog({
                title: "View Rate Sheet",
                modal: true,
                autoOpen: false,
                width: "90%",
                height: 'auto',
                position: {
                    my: "top",
                    at: "top+50"
                },
                resizeStop: function (event, ui) {
                    $(".ui-dialog-content").css("height", "100%")
                },
                show: {
                    effect: "fade",
                    duration: 300
                },
                close: function (event, e) {
                    $("#" + uniqueId).remove();
                }
            });
            dialog.html(res.content);
            dialog.dialog("open");
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

function createUniqueId(prefix, surfix) {
    var divId = Math.random().toString(36).substr(2, 16);
    if (prefix != null && prefix != "") {
        divId = prefix + "-" + divId;
    }
    if (surfix != null && surfix != "") {
        divId = divId + "-" + surfix;
    }
    return divId;
}

function createDiv(divId) {
    var obj = $("body").find("div[id='" + divId + "']");
    if (obj == null || obj.length == 0) {
        $("body").append("<div id='" + divId + "'></div>");
    }
}

function viewManageCustomer(customerCode) {
    // Detect this is franchise or customer.
    var isFranchise = false;
    if (customerCode.length >= 5) {
        var rightStr = customerCode.substring(customerCode.length - 5, customerCode.length);
        if (rightStr == "00001") {
            isFranchise = true;
        }
    }
    var data;
    var action;
    if (isFranchise) {
        data = {
            "franchiseCode": customerCode,
            "tabId": "#Account-tab"
        }
        action = "manage_franchise_view";
    } else {
        data = {
            "customerCode": customerCode,
            "tabId": "#account-setup-tab"
        }
        action = "manage_customers_view";
    }
    action += ".ix?reqType=json";
    var dialogId = createUniqueId("view_customer", "dialog");
    createDiv(dialogId);
    loadDialog(action, data, "", "", "Close", dialogId, "View Customer", "");
}

function viewAirbillChangeLog(invoiceId, shipmentId) {
    // Create div for dialog.
    var dialogId = createUniqueId("airbill_change_log", "dialog");
    var dialogDiv = "<div id='" + dialogId + "'></div>";
    // Define dialog.
    var changeLogDialog = $(dialogDiv).dialog({
        title: 'Airbill Change Log',
        modal: true,
        autoOpen: false,
        width: '1000px',
        height: 'auto',
        maxHeight: 600,
        resizeStop: function (event, ui) {
            $(".ui-dialog-content").css("height", "100%")
        },
        close: function (e) {
            $("#" + dialogId).remove();
        }
    });
    // Get content for dialog.
    loadingDialog.dialog("open");
    var data = {
        'invoiceId': invoiceId,
        'shipmentId': shipmentId
    };
    $.post("airbill_change_log.ix?reqType=json", data, function (res) {
        loadingDialog.dialog("close");
        if (res.errorCode == "SUCCESS") {
            changeLogDialog.html(res.content);
            changeLogDialog.dialog("open");
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