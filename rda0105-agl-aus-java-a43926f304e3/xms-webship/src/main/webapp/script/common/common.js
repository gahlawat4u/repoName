function Numericvalue(evevtobj, obj, isdecimal) {
    var KeyCode;

    if (document.all) {
        KeyCode = evevtobj.keyCode;
    }
    else {
        KeyCode = evevtobj.which;
    }

    var str = obj.value;
    oldstring = str;

    if (KeyCode == 45) {
        if (str.indexOf("-") > -1) {
            return false;
        }
        else {
            return true;
        }
    }
    if (KeyCode == 46) {
        if (isdecimal == true) {
            if (str.indexOf(".") > -1) {
                return false;
            }
        }
        else {
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

function Numericvaluewithoutminus(event, value, isdecimal) {
    var KeyCode;

    if (document.all) {
        KeyCode = event.keyCode;
    } else {
        KeyCode = event.which;
    }

    var str = value;
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

function toDecimal(value) {
    value = Number(value);
    if (!isNaN(value)) {
        return Number(value).toFixed(2);
    } else {
        return Number(0).toFixed(2);
    }
}
