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
        <table class="shoppingInput">
            <tbody id="lastTr">
                <tr><td><span>姓名</span><input id="customerName" name="order.customerName" onblur="customerNameCheck(this)"></input> <span id="customerNameWarn"></span></td></tr>
                <tr><td><span>地址</span><input id="customerAdress" name="order.customerAdress" onblur="customerAdressCheck(this)"></input> <span id="customerAdressWarn"></span></td></tr>
                <tr><td><span>电话</span><input id="customerTel" name="order.customerTel" onblur="customerTelCheck(this)"></input> <span id="customerTelWarn"></span></td></tr>
                <tr><td><span>备注</span><input id="remark" name="order.remark" onblur="remarkCheck(this)"></input> <span id="remarkWarn"></span></td></tr>

                <tr>
                    <td>
                        <span>编号</span><input class="productID" name="orderproductList[0].productID" onblur="productIDCheck(this)"></input> <span id="productIDWarn0"></span>
                        <span>价格</span><input class="sellPrice" name="orderproductList[0].sellPrice" onblur="floatNumCheck(this)"></input> <span id="sellPriceWarn0"></span>
                        <span>件数</span><input class="sellBoxNum" name="orderproductList[0].sellBoxNum" onblur="intNumCheck(this)"></input> <span id="sellBoxNumWarn0"></span>
                        <span>每件几张</span><input class="BoxOwn"  name="orderproductList[0].BoxOwn" onblur="intNumCheck(this)"></input> <span id="BoxOwnWarn0"></span>
                        <span>单项</span><input class="sellSingleNum" name="orderproductList[0].sellSingleNum" onblur="intNumWithZeroCheck(this)"></input> <span id="sellSingleNumWarn0"></span>
                        <button type='button' onClick="removeRow(this)">删除</button>
                    </td>
                </tr>
            </tbody>
            <tfoot>
                <tr><td><button type='button' onClick="addAnotherInput()">添加</button></td></tr>
                <tr><td><input type="button" id="submitBottom" name="Submit" value="提交" onclick="isAllWrite(0)"/></td></tr>
            </tfoot>
        </table>
    </form>
<script type="text/javascript" src="js/shopping.js" ></script>
</body>
</html>