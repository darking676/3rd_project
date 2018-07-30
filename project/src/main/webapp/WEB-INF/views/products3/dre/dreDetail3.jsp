<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../template3/header.jsp" %>
</head>
<body>
<%@ include file="../../template3/header2.jsp" %>
<h2>Dress Detail</h2>
<table border="1">
	<thead>
		<tr>
			<td>
				<img src="${path }/resources/imgs3/${proVo.product_url}" width="340px" height="340px" />
			</td>
			<td>
				<table border="1" style="height: 300px; width: 400px;">
					<tr align="center">
						<td>상품명</td>
						<td>${proVO.productName }</td>
					</tr>
					<tr align="center">
						<td>가격</td>
						<td><fmt:formatNumber value="${proVO.price }" pattern="###,###,###" /></td>
					</tr>
					<tr align="center">
						<td>설명</td>
						<td>${proVO.explan }</td>
					</tr>
					<tr align="center">
						<td colspan="2">
							<form action="${path }/com/bit/shop01/info/cart">
								<input type="hidden" name="productNum" value="${proVo.productNum }" />
								<select name="inventory">
									<c:forEach begin="1" end="10" var="i">
										<option value="${i }">${i }</option>
									</c:forEach>
								</select>&nbsp;개
								<input type="submit" value="장바구니에 담기" />
							</form>
							<a href="${path }/com/bit/shop01/dre/dreList3">Dress List</a>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</thead>
</table>
<%@ include file="../../template3/footer.jsp" %>
</body>
</html>