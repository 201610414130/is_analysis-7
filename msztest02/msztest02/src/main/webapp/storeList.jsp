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
<form action="getStore" method="post"> 
		<table>
		<tr><td>
		<s:select  theme="simple" name="searchchoose" list="#{'编号':'编号','厂名':'厂名','尺寸':'尺寸','件数':'件数'}" />
		<s:textfield  theme="simple" name="searchinfo"></s:textfield><s:submit  theme="simple" value="查询"></s:submit></td></tr>
		</table>
</form>

<s:if test="StoreServiceList==null||StoreServiceList.size==0">
<p>无记录</p>
</s:if>

<s:if test="StoreServiceList!=null&&StoreServiceList.size!=0">
<table>
<tbody>
	<tr>
		<td>时间</td><td>编号</td><td>厂名</td><td>尺寸</td><td>单价/片</td><td>件数</td><td>每件几张</td><td>单项</td><td>总价值</td><td>备注</td><td>操作</td>
	</tr>
	<s:iterator value="StoreServiceList" var="storeService" >
	<s:url action="revokeStore" var="storeToRevokeStore" >
       <s:param name="storeToRevoke.storeID" value="#storeService.storeID"></s:param>
       <s:param name="storeToRevoke.productID" value="#storeService.productID"></s:param>
       <s:param name="storeToRevoke.boxNum" value="#storeService.boxNum"></s:param>
       <s:param name="storeToRevoke.boxOwn" value="#storeService.boxOwn"></s:param>
       <s:param name="storeToRevoke.singleNum" value="#storeService.singleNum"></s:param>
       <s:param name="storeToRevoke.oriPrice" value="#storeService.oriPrice"></s:param>
     </s:url>
	<tr><td><s:date name="#storeService.storeDate" format="yyyy-MM-dd HH:mm" /></td><td><s:property value="#storeService.productID"/></td><td><s:property value="#storeService.factoryName"/></td><td><s:property value="#storeService.size1"/> - <s:property value="#storeService.size2"/></td><td><s:property value="#storeService.oriPrice"/></td><td><s:property value="#storeService.boxNum"/></td><td><s:property value="#storeService.boxOwn"/></td><td><s:property value="#storeService.singleNum"/></td><td><s:property value="#storeService.storeTotalPrice"/></td><td><s:property value="#storeService.remark"/></td><td><a href="${storeToRevokeStore}">撤销</a></td></tr>
	</s:iterator>
</tbody>
<tfoot>
<tr>
	<td colspan="11"><a href="getStore.action?pageDirection=up&pageNum=${pageNum}&pageNow=${pageNow}&pageAll=${pageAll}&searchinfo=${searchinfo}&searchchoose=${searchchoose}">上一页</a> <s:property value="#pageNow"/>/<s:property value="#pageAll"/> <a href="getStore?pageDirection=next&pageNum=${pageNum}&pageNow=${pageNow}&pageAll=${pageAll}&searchinfo=${searchinfo}&searchchoose=${searchchoose}">下一页</a>
	<form action="getStore" method="post"> 
	每页显示<input name="pageNum" ></input>条<input type="hidden" name="searchinfo" value="${searchinfo}"></input><input type="hidden" name="searchchoose" value="${searchchoose}"></input><s:submit  theme="simple" value="确定"></s:submit>
	</form>
	</td>
</tr>
</tfoot>
</table>
</s:if>
</body>
</html>