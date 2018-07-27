<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic');

body {
	font-family: 'Nanum Gothic', sans-serif;
} 
</style>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.location01 {
	padding-top: 0px;
	padding-left: 250px;
}

.pagination {
	display: inline-block;
	padding-left: 500px;
	margin: 30px 0;
	border-radius: 4px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
function goList(page) {
	var form = document.getElementById("listForm3");
	form.curPage.value = page;
	form.submit();
}
function goWrite() {
	var form = document.getElementById("writeForm3");
	form.submit();
}
function goView(bbseditno) {
	var form = document.getElementById("viewForm3");
	form.productNum = productNum;
	form.submit();
}
</script>
<%@ include file="../../template3/header.jsp" %>
</head>
<body>
<%@ include file="../../template3/header2.jsp" %>
<div class="location01">
	<div id="product">
		<div class="row">
			<div class="col-md-3">
				<a href="/bottomV3?productNum=${proVo.productNum }">
				<input type="hidden" value="번호${proVo.productNum }" /></a>
				<a><img src="../../resources/imgs3/${file1.filename }" alt="..."
					class="img-responsive img-rounded"></a><br/>
					<a href="/bottomV3?productNum=${proVo.productNum }">상품명${proVo.productName }</a>
				<h6>설명${proVo.explan }</h6>
				<h5>&#8361;${proVo.price }</h5>
			</div>
		</div>
		
		<div id="list-menu" style="text-align: right;">
			<input type="button" value="상품추가" onclick="goWrite()"
				class="btn btn-info btn-sm active" />
		</div>
	</div>
</div>
<br/><br/><br/><br/>
<nav aria-label="Page navigation" style="padding-left: 800px;">
	<ul class="pagination">
		<li><a href="/shop01/bottom3?productType=bot&curPage=1"
			aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
		</a></li>
		<li><a href="/shop01/bottom3/?productType=bot&curPage=1">1</a></li>
		<li><a href="/shop01/bottom3/?productType=bot&curPage=2">2</a></li>
		<li><a href="/shop01/bottom3/?productType=bot&curPage=3">3</a></li>
		<li><a href="/shop01/bottom3/?productType=bot&curPage=4">4</a></li>
		<li><a href="/shop01/bottom3/?productType=bot&curPage=5">5</a></li>
		<li><a href="/shop01/bottom3/?productType=bot&curPage=6"
			aria-label="next"> <span aria-hidden="true">&raquo;</span>
		</a></li>
	</ul>
</nav>

<%@ include file="../../template3/footer.jsp" %>

<div id="form-group" style="display: none;">
	<form id="listForm3" action="/shop01/bottom3/" method="get">
		<p>
			<input type="hidden" name="procd" value="${procd }" /> <input
				type="hidden" name="curPage" />
		</p>
	</form>
	<form id="writeForm3" action="/shop01/bottomW3/" method="get">
		<p>
			<input type="hidden" name="procd" value="${procd }" /> <input
				type="hidden" name="curPage" value="${curPage }" />
		</p>
	</form>

	<form id="viewForm3" action="/shop01/bottomV3/?productName=" method="get">
		<p>
			<input type="hidden" name="productName" /> <input type="hidden"
				name="procd" value="${procd }" /> <input type="hidden"
				name="curPage" value="${curPage }" />
		</p>
	</form>
</div>

</body>
</html>