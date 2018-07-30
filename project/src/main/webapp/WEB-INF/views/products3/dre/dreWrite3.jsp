<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../../template3/header.jsp" %>
<script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		// 상품 등록 유효성 검사
		$('#addBtn').click(function() {
			var productName = $('#productName').val();
			var price = $('#price').val();
			var explan = $('#explan').val();
			var product_url = $('#product_url').val();
			
			if(producName == "") {
				alert("상품명을 입력해 주세요");
				productName.focus();
			}esle if(price == "") {
				alert("상품 가격을 입력해 주세요");
				price.focus();
			}esle if(explan == "") {
				alert("상품 설명을 입력해 주세요");
				explan.focus();
			}esle if(product_url == "") {
				alert("상품 이미지 파일을 넣어 주세요");
			}
			
			//상품 정보 전송
			document.form1.action = "${path}/dre/dreWrite3";
			document.form1.submit();
		});
		
		//상품 목록 이동
		$('#listBtn').click(function() {
			location.href = '${path}/dre/dreList3';
		});
	});
</script>
</head>
<body>
<%@ include file="../../template3/header2.jsp" %>
<h2>Insert Product</h2>
	<form id="form1" name="form1" enctype="multipart/form-data" method="post">
		<table border="1">
			<tr>
				<td>상품명</td>
				<td><input type="text" name="productName" id="productName"></td>
			</tr>
			<tr>
				<td>가격</td>
				<td><input type="text" name="price" id="price"></td>
			</tr>
			<tr>
				<td>상품설명</td>
				<td><input type="text" name="explan" id="explan"></td>
			</tr>
			<tr>
				<td>상품이미지</td>
				<td><input type="file" name="product_url" id="product_url"></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="button" id="addBtn">Insert</button>
					<button type="button" id="listBtn">List</button>
				</td>
			</tr>
		</table>
	</form>
<%@ include file="../../template3/footer.jsp" %>
</body>
</html>