<style>
@import url('https://fonts.googleapis.com/css?family=Nanum+Gothic');

body {
	font-family: 'Nanum Gothic', sans-serif;
}
</style>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page session="false"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.location01 {
	padding-top: 0px;
	padding-left: 650px;
}

hr.line {
	width: 653px;
	color: black;
	border: thin solid gray;
	border-bottom: 2px;
	text-align: left;
	margin-left: 0px;
}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="../../resources/css/screen.css"
	type="text/css" media="screen" />
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
		form.productTye.value = productTye;
		form.submit();
	}
	function goModify() {
		var form = document.getElementById("modifyForm3");
		form.submit();
	}
	function goDelete() {
		var form = document.getElementById("deleteForm3");
		form.submit();
	}
</script>
<%@ include file="../../template3/header.jsp" %>
</head>
<body>
<%@ include file="../../template3/header2.jsp" %>
<h1>상품 정보 : ${file.filename }</h1>
<%@ include file="../../template3/footer.jsp" %>
</body>
</html>