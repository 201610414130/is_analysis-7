function loadXMLDoc(content) {
    var xmlhttp;
    var readyState = -1;
    var status = -1;
    if (window.XMLHttpRequest) // 非IE
    {
        xmlhttp = new XMLHttpRequest();
    }
    else // IE
    {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    if (!xmlhttp) {
        alert("创建xmlhttprequest失败");
    }
    xmlhttp.onreadystatechange = function () {
        readyState = xmlhttp.readyState;
        status = xmlhttp.status;
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var result = xmlhttp.responseText;
            //alert(result);
            resolveJson(result);

        }
        else {
            // alert("请求的页面有误");
            //	document.getElementById("temp").innerHTML =readyState + " "+status;
        }
    }

    var url = "storeToAjax";
    xmlhttp.open("post", url, true);
    //xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
    xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");   //注意大小写
    xmlhttp.send(content);

}

function resolveJson(result) {
    if (result != "0" && result != "1") {
        var inventory = JSON.parse(result);
        //alert(inventory.productID);
        //alert(inventory.factoryName);
        //alert(inventory.size1);
        //(inventory.size2);
        //(inventory.oriPrice);
        //alert(inventory.boxOwn);
        //alert(inventory.remark);
        document.getElementById("factoryName").value = inventory.factoryName;
        document.getElementById("size1").value = inventory.size1;
        document.getElementById("size2").value = inventory.size2;
        document.getElementById("oriPrice").value = inventory.oriPrice;
        document.getElementById("boxOwn").value = inventory.boxOwn;
    }
}

function isAllWrite() {
    var productIDId = document.getElementById("productID");
    var size1Id = document.getElementById("size1");
    var size2Id = document.getElementById("size2");
    var oriPriceId = document.getElementById("oriPrice");
    var boxNumId = document.getElementById("boxNum");
    var boxOwnId = document.getElementById("boxOwn");
    var singleNumId = document.getElementById("singleNum");

    var state1 = true, state2 = true, state3 = true, state4 = true, state5 = true, state6 = true, state7 = true;
    var isaddInput = 0;
    
    var stringLengthProductID = Trim(String(productID.value)).length;
    if (isaddInput == 0) {
        if (stringLengthProductID == 0) {
            state1 = false;
            document.getElementById(productID.id + "Warn").innerHTML = "此项必填";
        } else {
            document.getElementById(productID.id + "Warn").innerHTML = "";
        }
    }
    var stringLengthSize1 = Trim(String(size1Id.value)).length;
    if (isaddInput == 0) {
        if (stringLengthSize1 == 0) {
            state2 = false;
            document.getElementById(size1Id.id + "Warn").innerHTML = "此项必填";
        } else {
            document.getElementById(size1Id.id + "Warn").innerHTML = "";
        }
    }

    var stringLengthSize2 = Trim(String(size2Id.value)).length;
    if (isaddInput == 0) {
        if (stringLengthSize2 == 0) {
            state3 = false;
            document.getElementById(size2Id.id + "Warn").innerHTML = "此项必填";
        } else {
            document.getElementById(size2Id.id + "Warn").innerHTML = "";
        }
    }

    var stringLengthOriPrice = Trim(String(oriPriceId.value)).length;
    if (isaddInput == 0) {
        if (stringLengthOriPrice == 0) {
            state4 = false;
            document.getElementById(oriPriceId.id + "Warn").innerHTML = "此项必填";
        } else {
            document.getElementById(oriPriceId.id + "Warn").innerHTML = "";
        }
    }

    var stringLengthBoxNum = Trim(String(boxNumId.value)).length;
    if (isaddInput == 0) {
        if (stringLengthBoxNum == 0) {
            state5 = false;
            document.getElementById(boxNumId.id + "Warn").innerHTML = "此项必填";
        } else {
            document.getElementById(boxNumId.id + "Warn").innerHTML = "";
        }
    }

    var stringLengthBoxOwn = Trim(String(boxOwnId.value)).length;
    if (isaddInput == 0) {
        if (stringLengthBoxOwn == 0) {
            state6 = false;
            document.getElementById(boxOwnId.id + "Warn").innerHTML = "此项必填";
        } else {
            document.getElementById(boxOwnId.id + "Warn").innerHTML = "";
        }
    }
    
    var stringLengthsingleNum = Trim(String(singleNumId.value)).length;
    if (isaddInput == 0) {
        if (stringLengthsingleNum == 0) {
        	document.getElementById("singleNum").value = "0";
        }
    }
    
    if (state1 && state2 && state3 && state4 && state5 && state6 && state7) {
        document.getElementById("submitBottom").type = "submit";
        //alert("可提交");
    } else {
        document.getElementById("submitBottom").type = "button";
    }
    //alert(inputClass[0].value);
}

//去掉字符串前后的空格
function Trim(str) {
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
//获取name中的索引
function getInputIndex(str) {
    var indexArray = new Array();
    var isFound = false;
    var indexInput = 0;
    for (var i = 0, j = 0; i < str.length; i += 1) {

        if (str[i] == '[' || isFound) {
            isFound = true;
            if (str[i + 1] == ']') {
                break;
            }
            indexArray[j] = str[i + 1];
            j += 1;
        }
    }
    for (var i = 0; i < indexArray.length; i += 1) {
        indexInput += indexArray[i] * Math.pow(10, indexArray.length - i - 1);
    }
    //alert(index);
    return indexInput;
}

//Check
/*
//document.getElementById("customerTel").onblur = function () {
function customerTelCheck(obj) {
    //var telInputWarn = document.getElementById("customerTelWarn");
    //var obj = this;
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;
    var reg = /^\d+$/;
    if (!reg.test(obj.value)) {
        //alert("请填写数字！");
        document.getElementById(obj.id + "Warn").innerHTML = "请填写数字";
        obj.value = "";
    } else if (stringLength > 15) {
        document.getElementById(obj.id + "Warn").innerHTML = "最多15个数字";
    } else {
        document.getElementById("customerTelWarn").innerHTML = "";
    }
}


function customerNameCheck(obj) {

    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;

    if (stringLength > 25) {
        document.getElementById(obj.id + "Warn").innerHTML = "最多25个字";
        //document.getElementById("submitBottom").type = "button";
        obj.value = "";
    } else if (stringLength == 0) {
        document.getElementById(obj.id + "Warn").innerHTML = "请输入姓名";
        //document.getElementById("submitBottom").type = "button";
        obj.value = "";
    } else {
        //document.getElementById("submitBottom").type = "submit";
        document.getElementById(obj.id + "Warn").innerHTML = "";
    }
}
*/

function remarkCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;
    if (stringLength > 100) {
        document.getElementById(obj.id + "Warn").innerHTML = "最多100个字";
        obj.value = "";
    } else {
        document.getElementById(obj.id + "Warn").innerHTML = "";
    }
}

//productID
function productIDCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;

    if (stringLength > 32) {
        document.getElementById(obj.id + "Warn").innerHTML = "最多32个字";
        obj.value = "";
    } else {
        document.getElementById(obj.id + "Warn").innerHTML = "";
        var content = "productIDForAjax=" + stringTemp;
        loadXMLDoc(content);
    }
}
//factoryName
function factoryNameCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;
    if (stringLength > 50) {
        document.getElementById(obj.id + "Warn").innerHTML = "最多50个字";
        obj.value = "";
    } else {
        document.getElementById(obj.id + "Warn").innerHTML = "";
    }
}

//浮点数检测
function floatNumCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;

    var regFloat = /^\d+\.\d+$/;
    var regInt = /^\d+$/;
    if (stringLength != 0 && !regFloat.test(obj.value) && !regInt.test(obj.value)) {
        document.getElementById(obj.id + "Warn").innerHTML = "请填写数字";
        obj.value = "";
    } else {
        document.getElementById(obj.id + "Warn").innerHTML = "";
    }
}

//整数检测
function intNumCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;

    //alert("orderproductList[0].productID");
    //getInputIndex(obj.name);
    
    //var reg = /^[1-9]+$/;
    var reg = /^[1-9]\d*$/;
    if (stringLength != 0 && !reg.test(obj.value)) {
        document.getElementById(obj.id + "Warn").innerHTML = "请填写大于零的数字";
        obj.value = "";
    } else {
        document.getElementById(obj.id + "Warn").innerHTML = "";
    }
}

function intNumWithZeroCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;

    //alert("orderproductList[0].productID");
    //getInputIndex(obj.name);

    //var reg = /^[1-9]+$/;
    var reg = /^\d*$/;
    if (stringLength != 0 && !reg.test(obj.value)) {
        document.getElementById(obj.id + "Warn").innerHTML = "请填写数字";
        obj.value = "";
    } else {
        document.getElementById(obj.id + "Warn").innerHTML = "";
    }
}

