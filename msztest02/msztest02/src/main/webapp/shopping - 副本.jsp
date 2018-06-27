<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script languge="javascript">
        function closeWindow(){
        	alert("123");
        }
    </script>

</head>
<body>
	<form action="shopping" method="post">
	<table class="shoppingInput" >
	<tbody id="lastTr">
		<tr><td><span>姓名</span><input name="order.customerName" ></input></td></tr>
		<tr><td><span>地址</span><input  name="order.customerAdress" ></input></td></tr>
		<tr><td><span>电话</span><input id="order.customerTel" name="order.customerTel" ></input></td></tr>
		<tr><td><span>备注</span><input name="order.remark" ></input></td></tr>
		
		<tr><td>
			<span>编号</span><input name="orderproductList[0].productID" ></input>
			<span>卖价</span><input name="orderproductList[0].sellPrice" ></input> 
			<span>件数</span><input name="orderproductList[0].sellBoxNum"></input> 
			<span>每件几张</span><input name="orderproductList[0].BoxOwn" ></input> 
			<span>单项</span><input name="orderproductList[0].sellSingleNum" ></input>
			<button type='button' onClick="removeRow(this)">删除</button>
			</td> 
		</tr>
		</tbody>
		<tfoot>
		<tr><td><button type='button' onClick="addAnotherInput()">添加</button></td></tr>
			<tr><td><s:submit theme="simple" value="提交"></s:submit></td></tr>
		</tfoot>
	</table>
	</form>
<script type="text/javascript" src="js/shopping.js" ></script>
</body>
</html>