<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">
.location01 {
	padding-top: 10px;
	padding-left: 650px;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="Keywords" content="회원가입" />
<meta name="Description" content="회원가입" />
<script type="text/javascript">
// <![CDATA[
    function check() {
        var form = document.getElementById("join");
        //TODO 유효성 검사
        return true;
    }
// ]]>
</script>
<%@ include file="../template/header.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	 $("#join").click(function() {
	 	var memId = $("#memId").val();
		var memName = $("#memName").val();
		var memPassword = $("#memPassword").val();
		var email = $("#email").val();
		var address = $("#address").val();
		var hp = $("#hp").val();
		
		if(memId == "") {
			alert("아이디를 입력하세요");
			$("#memId").focus();
			return false;
		}
		
		else if(memName == "") {
			alert("이름을 입력하세요");
			$("#memName").focus();
			return false;
		}
		
		else if(memPassword == "") {
			alert("비밀번호를 입력하세요");
			$("#memPassword").focus();
			return false;
		}
		
		else if(email == "") {
			alert("이메일을 입력하세요");
			$("#email").focus();
			return false;
		}
		
		else if(address == "") {
			alert("주소를 입력하세요");
			$("#address").focus();
			return false;
		}
		
		else if(hp == "") {
			alert("전화번호를 입력하세요");
			$("#hp").focus();
			return false;
		}
		document.join1.action = "/shop01/join";
		document.join1.submit();
		
	});
});
</script>
</head>
<body>
	<%@ include file="../template/header2.jsp"%>
	<div class="location01">
		<form id="join1" class="form-horizontal" action="/shop01/join/"
			method="POST" onsubmit="return check()">
			<fieldset>
				<div id="legend">
					<legend>JOIN</legend>
				</div>
				<div class="control-group">
					<!-- ID -->
					<label class="control-label" for="ID">ID</label>
					<div class="controls">
						<input type="text" id="memId" name="memId" placeholder="아이디를 입력하세요"
							class="input-xlarge" style="text-align: Left; width: 288px;">
						<br />
					</div>
				</div>

				<div class="control-group">
					<!-- Username -->
					<label class="control-label" for="name">NAME</label>
					<div class="controls">
						<input type="text" id="memName" name="memName" placeholder="이름을 입력하세요"
							class="input-xlarge" style="text-align: Left; width: 288px;">
						<br />
					</div>
				</div>

				<div class="control-group">
					<!-- Password-->
					<label class="control-label" for="password">PASSWORD</label>
					<div class="controls">
						<input type="password" id="memPassword" name="memPassword"
							placeholder="" class="input-xlarge"
							style="text-align: Left; width: 288px;"> <br />
					</div>
				</div>

				<!--     <div class="control-group"> -->
				<!--       <label class="control-label" for="passwordCk">PASSWORD CHECK</label> -->
				<!--       <div class="controls"> -->
				<!--         <input type="password" id="memPassword" name="memPassword" placeholder="" class="input-xlarge" -->
				<!--          style="padding-left: 130px"> -->
				<!--       <br/> -->
				<!--       </div> -->
				<!--     </div> -->


				<div class="control-group">
					<!-- E-mail -->
					<label class="control-label" for="email">E-MAIL</label>
					<div class="controls">
						<input type="text" id="email" name="email" placeholder="이메일을 입력하세요"
							class="input-xlarge" style="text-align: Left; width: 288px;">
						<br />
					</div>
				</div>


				<div class="control-group">
					<!-- ADDRESS-->
					<label class="control-label" for="address">ADDRESS</label>
					<div class="controls">
						<input type="text" id="address" name="address" placeholder="주소를 입력하세요"
							class="input-xlarge" style="text-align: Left; width: 288px;">
						<br />
					</div>
				</div>
				<div class="control-group">
					<!-- HP-->
					<label class="control-label" for="hp">HP</label>
					<div class="controls">
						<input type="text" id="hp" name="hp" placeholder="전화번호를 입력하세요"
							class="input-xlarge" style="text-align: Left; width: 288px;">
						<br /> <br />
					</div>
				</div>
				<div class="control-group">
					<!-- Button -->
					<div class="controls">
						<a href="#"><button id="join" class="btn btn-info btn-sm active"
								style="width: 288px" type="submit">JOIN</button></a> <br /> <br />
						<button class="btn btn-info btn-sm active" style="width: 288px"
							type="reset">CANCEL</button>
					</div>
				</div>
			</fieldset>
		</form>

	</div>
	<%@ include file="../template/footer.jsp"%>
</body>
</html>