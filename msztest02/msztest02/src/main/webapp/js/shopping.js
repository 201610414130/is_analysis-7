var index = 1;
var trNum = 1;

function loadXMLDoc(content,obj) {
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
            resolveJson(result,obj);

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

function resolveJson(result,obj) {
    var indexInput = getInputIndex(obj.name);
    if (result == "1") {
        document.getElementById(obj.className + "Warn" + indexInput).innerHTML = "此编号未录入";
    } else if (result == "0") {
        document.getElementById(obj.className + "Warn" + indexInput).innerHTML = "";
    } else{
        var inventory = JSON.parse(result);
        document.getElementsByClassName("BoxOwn")[indexInput].value = inventory.boxOwn;
        document.getElementById(obj.className + "Warn" + indexInput).innerHTML = "";
    }
}
//添加行
function addAnotherInput() {
	if(isAllWrite(1)){
		var newTd = document.createElement("tr");
		//newTd.className = "shoppingInput";
		var lastTd = document.getElementById("lastTr");
		var ooClass = document.getElementsByClassName("addInputBig");
		var addInputhtml = '<td><span>编号</span><input class="productID" name="orderproductList[' + index + '].productID" onblur="productIDCheck(this)"></input> <span id="productIDWarn' + index + '"></span> <span>价格</span><input class="sellPrice" name="orderproductList[' + index + '].sellPrice" onblur="floatNumCheck(this)"></input> <span id="sellPriceWarn' + index + '"></span> <span>件数</span><input class="sellBoxNum" name="orderproductList[' + index + '].sellBoxNum" onblur="intNumCheck(this)"></input> <span id="sellBoxNumWarn' + index + '"></span> <span>每件几张</span><input class="BoxOwn"  name="orderproductList[' + index + '].BoxOwn" onblur="intNumCheck(this)"></input> <span id="BoxOwnWarn' + index + '"></span> <span>单项</span><input class="sellSingleNum" name="orderproductList[' + index + '].sellSingleNum" onblur="intNumWithZeroCheck(this)"></input> <span id="sellSingleNumWarn' + index + '"></span><button type="button" onClick="removeRow(this)">删除</button></td>';
		newTd.innerHTML = addInputhtml;
		lastTd.insertBefore(newTd, ooClass[0]);
		index += 1;
		trNum += 1;
	}
}
//删除行  
function removeRow(obj){  
	if(trNum == 1){
		alert("至少保留一行");
		return;
	}
	var tr = this.getRowObj(obj);  
	if(tr != null){  
		tr.parentNode.removeChild(tr);  
		trNum -= 1;
	}  
}  
//得到行对象  
function getRowObj(obj){  
	var i = 0;  
	while(obj.tagName.toLowerCase() != "tr"){  
		obj = obj.parentNode;  
		if(obj.tagName.toLowerCase() == "table")return null;  
	}  
	return obj;  
}

function isAllWrite(isaddInput) {
    var customerNameId = document.getElementById("customerName");

    var productIDClass = document.getElementsByClassName("productID");
    var sellPriceClass = document.getElementsByClassName("sellPrice");
    var sellBoxNumClass = document.getElementsByClassName("sellBoxNum");
    var BoxOwnClass = document.getElementsByClassName("BoxOwn");
    var singleNumClass = document.getElementsByClassName("sellSingleNum");
    var state1 = true, state2 = true, state3 = true, state4 = true, state5 = true, state6 = true;

    var stringTemp = Trim(String(customerNameId.value))
    var stringLength = stringTemp.length;
    if (isaddInput == 0){
    	if (stringLength == 0) {
    		state1 = false;
    		document.getElementById(customerNameId.id + "Warn").innerHTML = "此项必填";
    	} else {
    		document.getElementById(customerNameId.id + "Warn").innerHTML = "";
    	}
    }
    for (var i = 0; i < productIDClass.length; i += 1) {
        var stringTemp = Trim(String(productIDClass[i].value))
        var stringLength = stringTemp.length;
        if (stringLength == 0) {
            state2 = false;
            document.getElementById(productIDClass[i].className + "Warn" + i).innerHTML = "此项必填";
            break;
        } else {
            document.getElementById(productIDClass[i].className + "Warn" + i).innerHTML = "";
        }
    }
    for (var i = 0; i < sellPriceClass.length; i += 1) {
        var stringTemp = Trim(String(sellPriceClass[i].value))
        var stringLength = stringTemp.length;
        if (stringLength == 0) {
            state3 = false;
            document.getElementById(sellPriceClass[i].className + "Warn" + i).innerHTML = "此项必填";
            break;
        } else {
            document.getElementById(sellPriceClass[i].className + "Warn" + i).innerHTML = "";
        }
    }
    for (var i = 0; i < sellBoxNumClass.length; i += 1) {
        var stringTemp = Trim(String(sellBoxNumClass[i].value))
        var stringLength = stringTemp.length;
        if (stringLength == 0) {
            state4 = false;
            document.getElementById(sellBoxNumClass[i].className + "Warn" + i).innerHTML = "此项必填";
            break;
        } else {
            document.getElementById(sellBoxNumClass[i].className + "Warn" + i).innerHTML = "";
        }
    }
    for (var i = 0; i < BoxOwnClass.length; i += 1) {
        var stringTemp = Trim(String(BoxOwnClass[i].value))
        var stringLength = stringTemp.length;
        if (stringLength == 0) {
            state5 = false;
            document.getElementById(BoxOwnClass[i].className + "Warn" + i).innerHTML = "此项必填";
            break;
        } else {
            document.getElementById(BoxOwnClass[i].className + "Warn" + i).innerHTML = "";
        }
    }

    for (var i = 0; i < singleNumClass.length; i += 1) {
        var stringTemp = Trim(String(singleNumClass[i].value))
        var stringLength = stringTemp.length;
        if (stringLength == 0) {
            document.getElementsByClassName("sellSingleNum")[i].value = "0";
            break;
        }
    }

    if (isaddInput == 0 && state1 && state2 && state3 && state4 && state5 && state6) {
        document.getElementById("submitBottom").type = "submit";
        //alert("可提交");
    } else {
        document.getElementById("submitBottom").type = "button";
    }
    return state2 && state3 && state4 && state5 && state6;
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
//document.getElementById("customerTel").onblur = function () {
function customerTelCheck(obj){
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
    }else {
        document.getElementById("customerTelWarn").innerHTML = "";
    }
}
//必填项：
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

function customerAdressCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;
    if (stringLength > 50) {
        document.getElementById(obj.id + "Warn").innerHTML = "最多50个字";
        obj.value = "";
    } else {
        document.getElementById(obj.id + "Warn").innerHTML = "";
    }
}

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

function productIDCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;
    var indexInput = getInputIndex(obj.name);

    if (stringLength > 32) {
        document.getElementById(obj.className + "Warn" + indexInput).innerHTML = "最多32个字";
        obj.value = "";
    } else {
        var content = "productIDForAjax=" + stringTemp;
        loadXMLDoc(content,obj);
    }
}

//浮点数检测
function floatNumCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;
    var indexInput = getInputIndex(obj.name);

    var regFloat = /^\d+\.\d+$/;
    var regInt = /^\d+$/;
    if (stringLength != 0 && !regFloat.test(obj.value) && !regInt.test(obj.value)) {
        document.getElementById(obj.className + "Warn" + indexInput).innerHTML = "请填写数字";
        obj.value = "";
    }else {
        document.getElementById(obj.className + "Warn" + indexInput).innerHTML = "";
    }
}

//整数检测
function intNumCheck(obj) {
    var stringTemp = Trim(String(obj.value))
    var stringLength = stringTemp.length;
    var indexInput = getInputIndex(obj.name);
    //alert("orderproductList[0].productID");
    //getInputIndex(obj.name);
    
    //var reg = /^[1-9]+$/;
    var reg = /^[1-9]\d*$/;
    if (stringLength != 0 && !reg.test(obj.value)) {
        document.getElementById(obj.className + "Warn" + indexInput).innerHTML = "请填写大于零的数字";
        obj.value = "";
    } else {
        document.getElementById(obj.className + "Warn" + indexInput).innerHTML = "";
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