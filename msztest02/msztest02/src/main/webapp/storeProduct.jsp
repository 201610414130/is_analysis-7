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
            <tbody>
                <tr><td>编号:<input id="productID" name="store.productID" onblur="productIDCheck(this)"></input> <span id="productIDWarn"></span> </td></tr>
                <tr><td>厂名:<input id="factoryName" name="store.factoryName" onblur="factoryNameCheck(this)"></input> <span id="factoryNameWarn"></span> </td></tr>
                <tr><td>长:<input id="size1" name="store.size1" onblur="intNumCheck(this)"></input> <span id="size1Warn"></span> </td></tr>
                <tr><td>宽:<input id="size2" name="store.size2" onblur="intNumCheck(this)"></input> <span id="size2Warn"></span> </td></tr>
                <tr><td>单价/片:<input id="oriPrice" name="store.oriPrice" onblur="floatNumCheck(this)"></input> <span id="oriPriceWarn"></span> </td></tr>
                <tr><td>件数:<input id="boxNum" name="store.boxNum" onblur="intNumCheck(this)"></input> <span id="boxNumWarn"></span> </td></tr>
                <tr><td>每件几张:<input id="boxOwn" name="store.boxOwn" onblur="intNumCheck(this)"></input> <span id="boxOwnWarn"></span> </td></tr>
                <tr><td>单项:<input id="singleNum" name="store.singleNum" onblur="intNumWithZeroCheck(this)"></input> <span id="singleNumWarn"></span> </td></tr>
                <tr><td>备注:<input id="remark" name="store.remark" onblur="remarkCheck(this)"></input> <span id="remarkWarn"></span> </td></tr>
                <tr><td><input type="button" id="submitBottom" name="Submit" value="提交" onclick="isAllWrite()" /></td></tr>
            </tbody>
        </table>
    </form>
    <script type="text/javascript" src="js/storeProduct.js" ></script>
	</body>
</html>