<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../template3/header.jsp" %>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#btnAdd').click(function() {
			location.href="${path}/dre/dreWrite3";
		});
	});
</script>
</head>
<body>
<%@ include file="../../template3/header2.jsp" %>
<h2>Dress List</h2>
<!-- 관리자가 아니면 등록 or 편집 버튼 안나옴 -->
<c:if test="${sessionScope.adminId != null }">
	<button type="button" id="btnAdd">Insert</button><br/>
</c:if>
<c:if test="${sessionScope.adminId != null }">
	<button type="button" id="btnUpdate">Edit</button><br/>
</c:if>
<table border="1">
	<thead>
		<tr>
			<th>상품 번호</th>
			<th>상품 이미지</th>
			<th>상품 이름</th>
			<th>상품 가격</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list }" var="row">
			<tr>
				<td>${row.productNum }</td>
				<td>
					<a href="${path }/com/bit/shop01/detail/${row.productNum">
						<img src="${path }/imgs3/${row.product_url}" width="120ox" height="110px" />
					</a>
				</td>
				<td>
					<a href="${path }/com/bit/shop01/detail/${row.productNum">${row.productName }</a><br/>
					<c:if test="${sessionScope.adminId != null}">
						<a href="${path }/dre/dreUpdate3/${row.productNum}">Edit</a>
					</c:if>
				</td>
				<td>
					<fmt:formatNumber value="${row.price }" pattern="###,###,###" />
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<%@ include file="../../template3/footer.jsp" %>
</body>
</html>