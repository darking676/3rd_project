<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic');

body {
	font-family: 'Nanum Gothic', sans-serif;
}
</style>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cart List</title>
<%@ include file="../template2/header.jsp"%>
<style type="text/css">
	h2{
		padding-left: 800px;
	}
	
	#form1 {
		padding-top: 10px;
		padding-left: 300px;
		right: 0; left: 0; margin-bottom: auto; margin-left: auto; margin-right: auto; margin-top: auto; 
	}
	
	table{
		width: 90%;
	}
	
	body>p {
		padding-top: 10px;
		padding-left: 300px;
		right: 0; left: 0; margin-bottom: auto; margin-left: auto; margin-right: auto; margin-top: auto; 
	}
	
</style>
<script type="text/javascript">
	$(document).ready(function() {
		
		//Go Home
		$('#btnHome').click(function() {
			location.href="/shop01/home2/";
		});
		
		//Update
// 		$('#btnUpdate').click(function() {
// 			location.href = "/shop01/cart2/update/";
// 		});
	});
</script>
</head>
<body>
<%@ include file="../template2/header2.jsp"%>
	<h2>장바구니</h2>
	<c:choose>
		<c:when test="${map.count < 0 }">
			<p>장바구니가 비었습니다.</p>
		</c:when>
		<c:otherwise>
			<form name="form1" id="form1" method="post" action="/shop01/cart2/update">
				<table border="1">
					<tr>
						<th>상품명</th>
						<th>가 격</th>
						<th>수 량</th>
						<th>금 액</th>
						<th>취 소</th>
					</tr>
					<c:forEach var="cartVo" items="${map.list }" varStatus="i">
					<tr>
						<td>
							${cartVo.productName }
						</td>
						<td style="width: 80px" align="right">
							<fmt:formatNumber pattern="###,###,###" value="${cartVo.price }"/>
						</td>
						<td>
							<input type="number" style="width: 40px" name="quantity" value="${cartVo.quantity }" min="1" max="10" />
							<input type="hidden" name="productNum" value="${cartVo.productNum }" />
						</td>
						<td style="width: 100px" align="right">
							<fmt:formatNumber pattern="###,###,###" value="${cartVo.sumPrice }" />
						</td>
						<td>
							<button type="button" id="btnDel"><a href="/shop01/cart2/delete/?basketNum=${cartVo.basketNum }">삭제</a></button>
						</td>
					</tr>
					</c:forEach>
					<tr>
						<td colspan="5" align="right">
							장바구니 금액 합계 : <fmt:formatNumber pattern="###,###,###" value="${map.sumPrice }" /><br/>
							배송료 : ${map.fee }<br/>
							전체 주문 금액 : <fmt:formatNumber pattern="###,###,###" value="${map.AllSum }" />
						</td>
					</tr>
				</table>
				<input type="hidden" name="count" value="${map.count }"><br/>
				<button type="submit" id="btnUpdate">수정</button>&nbsp;&nbsp;
				<button type="button" id="btnSel">주문하기</button>&nbsp;&nbsp;<!-- 결제로 이동 -->
				<button type="button" id="btnHome">쇼핑 계속하기</button>
			</form>
		</c:otherwise>
	</c:choose>
<%@ include file="../template/footer.jsp"%>
</body>
</html>