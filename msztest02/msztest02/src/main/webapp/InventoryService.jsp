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
<form action="getInventory" method="post"> 
		<table>
		<tr><td>
		<s:select  theme="simple" name="searchchoose" list="#{'编号':'编号','厂名':'厂名','尺寸':'尺寸','件数':'件数'}" />
		<s:textfield  theme="simple" name="searchinfo"></s:textfield><s:submit  theme="simple" value="查询"></s:submit></td></tr>
		</table>
</form>

<s:if test="InventoryServiceList==null||InventoryServiceList.size==0">
<p>无记录</p>
</s:if>

<s:if test="InventoryServiceList!=null&&InventoryServiceList.size!=0">
<table>
<tbody>
	<tr>
		<td>编号</td><td>厂名</td><td>尺寸</td><td>单价/片</td><td>件数</td><td>每件几张</td><td>单项</td><td>总价值</td><td>上次出库时间</td>
	</tr>
	<s:iterator value="InventoryServiceList" var="InventoryService" >
	<tr><td><s:property value="#InventoryService.productID"/></td><td><s:property value="#InventoryService.factoryName"/></td><td><s:property value="#InventoryService.size1"/> - <s:property value="#InventoryService.size2"/></td><td><s:property value="#InventoryService.oriPrice"/></td><td><s:property value="#InventoryService.boxNum"/></td><td><s:property value="#InventoryService.boxOwn"/></td><td><s:property value="#InventoryService.singleNum"/></td><td><s:property value="#InventoryService.totalPrice"/></td><td><s:property value="#InventoryService.remark"/></td></tr>
	</s:iterator>
</tbody>
<tfoot>
<tr>
	<td colspan="9"><a href="getInventory.action?pageDirection=up&pageNum=${pageNum}&pageNow=${pageNow}&pageAll=${pageAll}&searchinfo=${searchinfo}&searchchoose=${searchchoose}">上一页</a> <s:property value="#pageNow"/>/<s:property value="#pageAll"/> <a href="getInventory?pageDirection=next&pageNum=${pageNum}&pageNow=${pageNow}&pageAll=${pageAll}&searchinfo=${searchinfo}&searchchoose=${searchchoose}">下一页</a>
	<form action="getInventory" method="post"> 
	每页显示<input name="pageNum" ></input>条<input type="hidden" name="searchinfo" value="${searchinfo}"></input><input type="hidden" name="searchchoose" value="${searchchoose}"></input><s:submit  theme="simple" value="确定"></s:submit>
	</form>
	</td>
</tr>
</tfoot>
</table>
</s:if>
<s:fielderror><s:param>pageNum</s:param></s:fielderror>
</body>
</html>