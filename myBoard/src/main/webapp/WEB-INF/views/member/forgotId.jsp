<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="hContext" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
<title>forgot id</title>
<!-- 부트스트랩 -->
<link rel="stylesheet" type="text/css" href="${hContext}/resources/css/bootstrap.min.css"  >
<link rel="stylesheet" type="text/css" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- <link href="/EJDBC/css/layout.css" rel="stylesheet"> -->

<!-- IE8 에서 HTML5 요소와 미디어 쿼리를 위한 HTML5 shim 와 Respond.js -->
<!-- WARNING: Respond.js 는 당신이 file:// 을 통해 페이지를 볼 때는 동작하지 않습니다. -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div style="text-align: center;" class="container">
		<h1>아이디 찾기</h1>
		<hr>
		<div class="contents text-center">
			<br>
			<a href="${hContext}/member/loginView.do"><img style="margin-top: 10px;" alt="" src="${hContext }/resources/img/board1.jpg"></a>
			<br><br><br>
			<form method="post" action="${hContext }/member/forgotId.do" class="" name="loginForm" id="loginForm">
				<div class="form-group">
					<label for="inputName">이름</label><br>
					<input style="width: 400px; display: inline;" type="text" class="form-control" name="inputName" id="inputName" placeholder="아이디">
				</div>
				<div class="form-group">
					<label for="inputEmail">이메일</label><br>
					<input style="width: 400px; display: inline;" type="text" class="form-control" name="inputEmail" id="inputEmail" placeholder="이메일">
				</div>
				<input style="width: 400px; margin: 5px;" class="btn btn-danger btn-lg" type="button" value="아이디 찾기" id="forgotId"><br>
				<input style="width: 400px; margin: 5px;" class="btn btn-default btn-lg" type="button" value="뒤로가기" id="doBackBtn"><br><br>
				<a href="/board/member/moveToForgotPassword.do">혹시 비밀번호가 기억나지 않나요?</a>
			</form>
		</div>	<!-- end contents -->
		
	</div>	<!-- end container -->



<%@ include file="/WEB-INF/views/main/footer.jsp" %>





<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">
	
	// 뒤로가기 버튼
		$("#doBackBtn").on("click", function() {
			window.location.href = "${hContext}/member/loginView.do";
		});
	// 뒤로가기 버튼
	
	// 아이디 찾기 버튼
		$("#forgotId").on("click", function(){
			var email = $("#inputEmail").val().trim();
			var name = $("#inputName").val().trim();
			
			if(name == ""){
				document.getElementById('inputName').focus();
				alert("이름을 입력하세요.");
				return;
			}
			if(email == ""){
				document.getElementById('inputEmail').focus();
					alert("이메일을 입력하세요.");
					return;
				}
			
			
			$.ajax({
				type : 'POST',
				url : '${hContext}/member/forgotId.do',
				dataType : "html",
				async : true,
				data : {
					"email" : $("#inputEmail").val(),
					"name" : $("#inputName").val()
				},
				success : function(data) {
					console.log("success!");
					var parseData = JSON.parse(data);
					alert("아이디는 "+parseData.id+" 입니다.");
					window.location.href = "${hContext}/member/loginView.do";
				},
				error : function() {
					alert("이름 혹은 이메일이 틀렸습니다.");
				}
			});

			});
	// 아이디 찾기 버튼

	</script>
	
</body>
</html>