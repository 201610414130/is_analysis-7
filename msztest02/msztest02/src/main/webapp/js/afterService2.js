var index = 1;
var trNum = 1;
//添加行
function addAnotherInput() {
	var newTd = document.createElement("tr");
	//newTd.className = "shoppingInput";
	var lastTd = document.getElementById("lastTr");
	var ooClass = document.getElementsByClassName("addInputBig");
	var addInputhtml = "<td><span>类型</span><select name='serviceListAfterService[0].type'><option value='true'>增</option><option value='false'>退</option></select><span>编号</span><input name='orderproductListAfterService[0].productID' ></input><span>卖价</span><input name='orderproductListAfterService[0].sellPrice' ></input> <span>件数</span><input name='orderproductListAfterService[0].sellBoxNum'></input> <span>每件几张</span><input name='orderproductListAfterService[0].BoxOwn' ></input> <span>单项</span><input name='orderproductListAfterService[0].sellSingleNum' ></input><span>备注</span><input name='serviceListAfterService[0].remark' ></input><button type='button' onClick='removeRow(this)'>删除</button></td>";
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
