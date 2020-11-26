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
<title>member register Page</title>
<link rel="shortcut icon" type="image/x-icon"
	href="${hContext}/resources/img/favicon.ico">
<!-- 부트스트랩 -->
<link href="${hContext}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
	<%@ include file="/WEB-INF/views/main/nav.jsp" %>
	<div class="container">
		<h1>member modify</h1>
		<hr>
		
		<div style="width: 40%" class="contents">
			<form method="post" action="doLogin.do" class="" name="loginForm" id="loginForm">
				<div class="form-group">
					<label for="inputMemberId">아이디</label>
					<input type="text" class="form-control" name="inputMemberId" id="inputMemberId" placeholder="아이디" value="${sessionScope.sessionId.id }" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="inputPassword">비밀번호</label>
					<input type="text" class="form-control" name="inputPassword" id="inputPassword" placeholder="비밀번호">
				</div>
				<div class="form-group">
					<label for="inputPassword">비밀번호 확인</label>
					<input type="text" class="form-control" name="inputPassword2" id="inputPassword2" placeholder="비밀번호">
				</div>
				<div class="form-group">
					<label for="inputPassword">이름</label>
					<input type="text" class="form-control" name="inputName" id="inputName" placeholder="이름" value="${sessionScope.sessionId.name }" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="inputPassword">이메일</label>
					<input type="text" class="form-control" name="inputEmail" id="inputEmail" value="${sessionScope.sessionId.email }" placeholder="이메일">
				</div>
				
				<input class="btn btn-primary btn-lg btn-block" type="button" value="프로필 변경" id="doUpdateBtn">
				<input class="btn btn-default btn-lg btn-block" type="button" value="뒤로가기" id="doBackBtn">
				<input class="btn btn-danger btn-lg btn-block" type="button" value="회원 탈퇴" id="doDeleteBtn">
			</form>
		</div>	<!-- end contents -->
	</div>
	






	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">
		// 뒤로가기 버튼
		$("#doBackBtn").on("click", function() {
			window.location.href = "${hContext}/workspace/moveToChannel.do";
		});
		// 뒤로가기 버튼

		// 회원수정 버튼
		$("#doUpdateBtn").on("click", function() {
			doUpdate();
		});
		// 회원수정 버튼
		
		// 회원 탈퇴 버튼
		$("#doDeleteBtn").on("click", function(){
			if($("#inputPassword").val() != $("#inputPassword2").val()){
				alert("비밀번호가 다릅니다!");
				return;
				}
				doDeleteUser();

			})
		// 회원 탈퇴 버튼

		// 회원 탈퇴
		function doDeleteUser(){
			$.ajax({
				type : 'POST',
				url : '${hContext}/member/doDeleteUser.do',
				dataType : "html",
				async : true,
				data : {
					"password" : $("#inputPassword").val(),
					"id" : $("#inputMemberId").val()
				},
				success : function() {
					alert("삭제 성공");
					window.location.href = "${hContext}/member/loginView.do";
				},
				error : function() {
					alert("비밀번호가 틀렸습니다!");
				}
			});

			}
		// 회원 탈퇴
		
		// 회원 수정
		function doUpdate() {
			$.ajax({
				type : 'POST',
				url : '${hContext}/member/doUpdateProfile.do',
				dataType : "html",
				async : true,
				data : {
					"password" : $("#inputPassword").val(),
					"email" : $("#inputEmail").val()
				},
				success : function(data) {
					alert("수정 성공!");
					window.location.href = "${hContext}/workspace/moveToChannel.do";
				},
				error : function(data) {
					alert("수정에 실패");
				}
			});

		}
		// 회원 수정
	</script>

</body>
</html>