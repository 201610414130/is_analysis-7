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
	<form action="storeProduct" method="post">
	<table>
	<s:fielderror><s:param>store.productID</s:param></s:fielderror>
		<tr><td><s:textfield name="store.productID" label="编号"></s:textfield></td></tr>
		<tr><td><s:textfield name="store.factoryName" label="厂名"></s:textfield></td></tr>
		<tr><td><s:textfield name="store.size1" label="长"></s:textfield></td></tr>
		<tr><td><s:textfield name="store.size2" label="宽"></s:textfield></td></tr>
		<tr><td><s:textfield name="store.oriPrice" label="单价/片"></s:textfield></td></tr>
		<tr><td><s:textfield name="store.boxNum" label="件数"></s:textfield></td></tr>
		<tr><td><s:textfield name="store.boxOwn" label="每件几张"></s:textfield></td></tr>
		<tr><td><s:textfield name="store.singleNum" label="单项"></s:textfield></td></tr>
		<tr><td><s:textfield name="store.remark" label="备注"></s:textfield></td></tr>
		<tr><td><s:submit value="提交"></s:submit>
	</table>
	</form>
	
</html>