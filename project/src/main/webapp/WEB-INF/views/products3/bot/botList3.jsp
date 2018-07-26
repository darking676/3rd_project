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
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
			<div class="col-md-3">
				<a><img src="../../resources/imgs3/${file.filename }" alt="..."
					class="img-responsive img-rounded"></a> <br /> <a href="#">하늘하늘원피스(5
					color)</a>
				<h6>누구에게나 잘 어울리는 하늘색 원피스</h6>
				<h5>35,000원</h5>
			</div>
		</div>
		<table class="table table-hover">
			<tbody>
				<c:forEach items="${list }" var="bbsVo" varStatus="status">
					<tr>
						<%-- 														<td style="text-align: center;">${no - status.index}</td>  --%>
						<!-- 게시물 내림차순 정렬 -->
						<td align="right" style="text-align: right; width: 100px;">${bbsVo.bbseditno }</td>
						<td align="right" style="text-align: right; width: 600px;"><a
							href="javascript:goView('${bbsVo.bbseditno }')">${bbsVo.title }
							<c:if test="${bbsVo.attachFileNum > 0 }">
							<img src="../resources/imgs/file.png" alt="첨부파일" width="30px" />
						</c:if>
						<c:if test="${bbsVo.commentno > 0 }">
							<span class="bbs-strong">[${bbsVo.commentno }]</span>
						</c:if>	
							</a></td>
						<td align="right" style="text-align: right; width: 400px;">${bbsVo.bbs_date }</td>
						<td align="right" style="text-align: right; width: 400px;">${bbsVo.hit }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
		<div id="list-menu" style="text-align: right;">
			<input type="button" value="상품추가" onclick="goWrite()"
				class="btn btn-info btn-sm active" />
		</div>
	</div>
</div>
<br/><br/><br/><br/>
<nav aria-label="Page navigation" style="padding-left: 800px;">
	<ul class="pagination">
		<li><a href="/shop01/bottom3/?productType=bot&curPage=1"
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
</body>
</html>