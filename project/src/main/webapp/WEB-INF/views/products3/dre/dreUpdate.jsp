<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../template3/header.jsp" %>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#editBtn').click(function() {
			var productName = $("#productName").val();
			var price = $('#price').val();
			var explan = $('#explan').val();
			
			if(producName == "") {
				alert("상품명을 입력해 주세요");
				productName.focus();
			}esle if(price == "") {
				alert("상품 가격을 입력해 주세요");
				price.focus();
			}esle if(explan == "") {
				alert("상품 설명을 입력해 주세요");
				explan.focus(); == "") {
				alert()
			}
		document.form1.action = "${path}/dre/dreUpdate3";
		dicument.form1.submit();
		});
	
		$('#deleteBtn').click(function() {
			if(confirm("삭제 하시겠습니까?")) {
				document.form1.action = "${path}/dre/dreDelete3";
				document.form1.submit();
			}
		});
		
		$('#listBtn').click(function() {
			location.href = "${path}/dre/dreList3";
		});
	});
</script>
</head>
<body>
<%@ include file="../../template3/header2.jsp" %>
<h2>수정or삭제</h2>
<form id="form1" name="form1" enctype="multipart/form-data" method="post">
	<table border="1">
		<tr>
			<td>이미지</td>
			<td>
				<img alt="..." src="${path }/imgs3/">
				<input type="file" id="product_url" name="product_url">
			</td>
		</tr>
	</table>
</form>
<%@ include file="../../template3/footer.jsp" %>
</body>
</html>