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

<form action="getOrder" method="post"> 
		<table>
		<tr><td>
		<s:textfield  theme="simple" name="searchinfo"></s:textfield><s:submit  theme="simple" value="查询"></s:submit>
		</td></tr>
		</table>
</form>

<s:if test="orderServiceList==null||orderServiceList.size==0">
<p>无记录</p>
</s:if>

<s:if test="orderServiceList!=null&&orderServiceList.size!=0">
<table>
<tbody>
	<tr>
		<td>时间</td><td>姓名</td><td>地址</td><td>电话</td><td>总价</td><td>备注</td><td>操作</td>
	</tr>
	<s:iterator value="orderServiceList" var="orderService" status="orderServiceStatus">
		<tr><td><s:date name="#orderService.orderDate" format="yyyy-MM-dd HH:mm" /></td><td><s:property value="#orderService.customerName"/></td><td><s:property value="#orderService.customerAdress"/></td><td><s:property value="#orderService.customerTel"/></td><td><s:property value="#orderService.shoppingTotalPrice"/></td><td><s:property value="#orderService.remark"/></td><td> </td></tr>
		<s:iterator value="OrderproductServiceList.get(#orderServiceStatus.index)" var="OrderproductService" >
		<tr><td>编号:<s:property value="#OrderproductService.productID"/></td><td>价格:<s:property value="#OrderproductService.sellPrice"/></td><td>件数:<s:property value="#OrderproductService.sellBoxNum"/></td><td>每件几张:<s:property value="#OrderproductService.BoxOwn"/></td><td>单项:<s:property value="#OrderproductService.sellSingleNum"/></td><td>总价:<s:property value="@msz.tools.DoubleMathTools@mul((#OrderproductService.sellBoxNum * #OrderproductService.BoxOwn+#OrderproductService.sellSingleNum) , #OrderproductService.sellPrice)"/></td>
		<td><s:if test="#OrderproductService.isStock()">已出</s:if>
		<s:url action="stockOut" var="orderproductToStockOutUrl" >
       			<s:param name="orderproductToStockOut.orderProductID" value="#OrderproductService.orderProductID"></s:param>
       			<s:param name="orderproductToStockOut.productID" value="#OrderproductService.productID"></s:param>
       			<s:param name="orderproductToStockOut.sellBoxNum" value="#OrderproductService.sellBoxNum"></s:param>
       			<s:param name="orderproductToStockOut.BoxOwn" value="#OrderproductService.BoxOwn"></s:param>
       			<s:param name="orderproductToStockOut.sellSingleNum" value="#OrderproductService.sellSingleNum"></s:param>
       			<s:param name="orderproductToStockOut.sellPrice" value="#OrderproductService.sellPrice"></s:param>
     		</s:url>
			<s:if test="!#OrderproductService.isStock()">
			<a href="${orderproductToStockOutUrl}">出货</a>
			</s:if>
		</td></tr>
		</s:iterator>
	<tr><td><a href="getAfterServiceOrder?orderIDForAfterAction=<s:property value="#orderService.orderID"/>">售后</a></td></tr>
	</s:iterator>
</tbody>
<tr>
	<td colspan="9"><a href="getOrder.action?pageDirection=up&pageNum=${pageNum}&pageNow=${pageNow}&pageAll=${pageAll}&searchinfo=${searchinfo}&searchchoose=${searchchoose}">上一页</a> <s:property value="#pageNow"/>/<s:property value="#pageAll"/> <a href="getOrder?pageDirection=next&pageNum=${pageNum}&pageNow=${pageNow}&pageAll=${pageAll}&searchinfo=${searchinfo}&searchchoose=${searchchoose}">下一页</a>
	<form action="getOrder" method="post"> 
	每页显示<input name="pageNum" ></input>条<input type="hidden" name="searchinfo" value="${searchinfo}"></input><input type="hidden" name="searchchoose" value="${searchchoose}"></input><s:submit  theme="simple" value="确定"></s:submit>
	</form>
	</td>
</tr>
</table>
</s:if>
</body>
</html>