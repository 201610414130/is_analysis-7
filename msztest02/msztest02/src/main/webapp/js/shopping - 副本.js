var index = 1;
var trNum = 1;
//添加行
function addAnotherInput() {
	var newTd = document.createElement("tr");
	//newTd.className = "shoppingInput";
	var lastTd = document.getElementById("lastTr");
	var ooClass = document.getElementsByClassName("addInputBig");
	var addInputhtml = "<td><span>编号</span><input name='orderproductList["+index+"].productID' ></input><span>卖价</span><input name='orderproductList["+index+"].sellPrice' ></input> <span>件数</span><input name='orderproductList["+index+"].sellBoxNum'></input> <span>每件有几张</span><input name='orderproductList["+index+"].BoxOwn' ></input> <span>单项</span><input name='orderproductList["+index+"].sellSingleNum' ></input> <button type='button' onClick='removeRow(this)'>删除</button></td>";
	newTd.innerHTML = addInputhtml;
    lastTd.insertBefore(newTd, ooClass[0]);
    index += 1;
    trNum += 1;
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

//Check
document.getElementById("order.customerTel").onblur = function () {
    var telInput = document.getElementById("order.customerTel");
    var reg = /^\d+$/;
    if (!reg.test(telInput.value)) {
        alert("请正确填写手机号！");
        telInput.value = "";
    }
}
