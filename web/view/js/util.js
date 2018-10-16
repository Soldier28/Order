//验证控件值是否为空
function checkNull( obj , name) {
    if (name == undefined) return false;

    if (obj.value.length == 0) {
        alert( name + "不能为空！");
        obj.focus();
        obj.select();
        return false;
    }
    return true;
}
//验证控件值是否为空，没有提示，只是做个判断
function isNull(obj) {
    if (name == "undefined") return true;
    if (obj.value.length == 0) {
        return false;
    }
    return true;
}
////验证控件值长度
function checkLength(obj,name,minLens,maxLens) {
    if (minLens==null || minLens=="undefined") minLens=0;
    if (maxLens==null || maxLens=="undefined") minLens=100000;
    //return true;
    var tlen = obj.value.length;
    if (tlen==0 || (tlen>=minLens && tlen)) {
        return true;
    }
    if (minLens<maxLens) {
        alert(name+"的长度必须在"+minLens+"位和"+maxLens+"位之间！");
    } else {
        alert(name+"的长度必须是"+maxLens+"位");
    }
    obj.focus();
    obj.select();
    return false;
}
//----------------------------检查不超过（或等于）n位的数字--------------------------------//
function chkDigit(obj, n, flag) {    //flag=true 等于；flag=false 不超过
    var num = obj.value;
    if (num == "")  return true;
    for (var i=0; i<num.length; i++) {
        if (isDigit(num.charAt(i)) == false) {
            alert("请输入数字！");
            return false;
        }
    }
    if (flag) {
        if (num.length != n) {
            alert("请输入"+n+"位数字！");
            return false;
        }
    } else {
        if (num.length > n) {
            alert("请输入不超过"+n+"位数字！");
            return false;
        }
    }
    return true;
}

//---------------------------------------------------------------------------------------//
function isDigit(theNum) {
    var theMask = "0123456789";
    if (theNum == "") {
        return true;
    } else if (theMask.indexOf(theNum) == -1) {
        return false;
    }
    return true;
}