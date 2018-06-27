<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tbody>
	<tr>
		<td>时间</td><td>姓名</td><td>地址</td><td>电话</td><td>总价</td><td>备注</td><td>操作</td>
	</tr>
	<tr><td><s:date name="#orderForAfterService.orderDate" format="yyyy-MM-dd HH:mm" /></td><td><s:property value="#orderForAfterService.customerName"/></td><td><s:property value="#orderForAfterService.customerAdress"/></td><td><s:property value="#orderForAfterService.customerTel"/></td><td><s:property value="#orderForAfterService.shoppingTotalPrice"/></td><td><s:property value="#orderForAfterService.remark"/></td><td> </td></tr>
	<s:iterator value="orderproductListForAfterService" var="OrderproductService" >
		<tr><td>编号:<s:property value="#OrderproductService.productID"/></td><td>价格:<s:property value="#OrderproductService.sellPrice"/></td><td>件数:<s:property value="#OrderproductService.sellBoxNum"/></td><td>每件几张:<s:property value="#OrderproductService.BoxOwn"/></td><td>单项:<s:property value="#OrderproductService.sellSingleNum"/></td><td>总价:<s:property value="(#OrderproductService.sellBoxNum * #OrderproductService.BoxOwn +#OrderproductService.sellSingleNum) * #OrderproductService.sellPrice"/></td>
		<td><s:if test="#OrderproductService.isStock()">已出</s:if>
		<s:url action="stockOutInAfterService" var="orderproductToStockOutUrl" >
       			<s:param name="orderproductToStockOut.orderProductID" value="#OrderproductService.orderProductID"></s:param>
       			<s:param name="orderproductToStockOut.productID" value="#OrderproductService.productID"></s:param>
       			<s:param name="orderproductToStockOut.sellBoxNum" value="#OrderproductService.sellBoxNum"></s:param>
       			<s:param name="orderproductToStockOut.BoxOwn" value="#OrderproductService.BoxOwn"></s:param>
       			<s:param name="orderproductToStockOut.sellPrice" value="#OrderproductService.sellPrice"></s:param>
       			<s:param name="orderproductToStockOut.sellSingleNum" value="#OrderproductService.sellSingleNum"></s:param>
     		</s:url>
			<s:if test="!#OrderproductService.isStock()">
			<a href="${orderproductToStockOutUrl}">出货</a>
			</s:if>
		</td></tr>
		</s:iterator>
</tbody>
</table>

<table>
<tbody>
<tr><td>售后</td></tr>
<s:iterator value="serviceListForAfterService" var="OrderproductService" status="orderServiceStatus">
		<tr><td>时间:<s:date name="#OrderproductService.serviceTime" format="yyyy-MM-dd HH:mm" /></td><td>类型:<s:property value="#OrderproductService.type?'增':'退'"/></td><td>编号:<s:property value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).productID"/></td><td>价格:<s:property value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellPrice"/></td><td>件数:<s:property value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellBoxNum"/></td><td>每件几张:<s:property value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).BoxOwn"/></td><td>单项:<s:property value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellSingleNum"/></td><td>总价:<s:property value="(#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellBoxNum * #serviceOrderproductListForAfterService.get(#orderServiceStatus.index).BoxOwn + #serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellSingleNum) * #serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellPrice"/></td>
		<td><s:if test="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).isStock()">已出</s:if>
		<s:url action="ServiceStockOutInAfterService" var="serviceOrderproductToStockOutUrl" >
				<s:param name="serviceToStockOut.type" value="#OrderproductService.type"></s:param>
       			<s:param name="orderproductToStockOut.orderProductID" value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).orderProductID"></s:param>
       			<s:param name="orderproductToStockOut.productID" value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).productID"></s:param>
       			<s:param name="orderproductToStockOut.sellBoxNum" value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellBoxNum"></s:param>
       			<s:param name="orderproductToStockOut.BoxOwn" value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).BoxOwn"></s:param>
       			<s:param name="orderproductToStockOut.sellPrice" value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellPrice"></s:param>
       			<s:param name="orderproductToStockOut.sellSingleNum" value="#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).sellSingleNum"></s:param>
     		</s:url>
			<s:if test="!#serviceOrderproductListForAfterService.get(#orderServiceStatus.index).isStock()">
			<a href="${serviceOrderproductToStockOutUrl}">出货</a>
			</s:if>
		</td></tr>
		</s:iterator>
</tbody>
</table>

<form action="storeAfterService" method="post">
<table class="serviceInput">
<tbody id="lastTr">
<tr><td>新增售后</td></tr>
<tr><td>
			<span>类型</span><select name="serviceListAfterService[0].type"><option value="true">增</option><option value="false">退</option></select>
			<span>编号</span><input name="orderproductListAfterService[0].productID" ></input>
			<span>卖价</span><input name="orderproductListAfterService[0].sellPrice" ></input> 
			<span>件数</span><input name="orderproductListAfterService[0].sellBoxNum"></input> 
			<span>每件几张</span><input name="orderproductListAfterService[0].BoxOwn" ></input> 
			<span>单项</span><input name="orderproductListAfterService[0].sellSingleNum" ></input>
			<span>备注</span><input name="serviceListAfterService[0].remark" ></input>
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
<script type="text/javascript" src="js/afterService.js" ></script>
</body>
</html>