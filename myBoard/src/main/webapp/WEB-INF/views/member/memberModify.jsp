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
		
		<div style="width: 40%; float: left; margin-right: 100px;" class="contents">
			<form method="post" action="doLogin.do" class="" name="loginForm" id="loginForm">
				<div class="form-group">
					<label for="inputMemberId">아이디</label>
					<input type="text" class="form-control" name="inputMemberId" id="inputMemberId" placeholder="아이디" value="${sessionScope.sessionId.id }" readonly="readonly">
				</div>
				<div class="form-group">
					<label for="inputPassword">비밀번호</label>
					<input type="text" class="form-control" name="inputPassword" id="inputPassword" placeholder="비밀번호" value="${sessionScope.sessionId.password }">
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
				
				
			</form>
		</div>	<!-- end contents -->
		<div>
			<div style="text-align: center;">
				<label>프로필 사진</label><br>
				
				<c:choose>
					<c:when test="${sessionScope.sessionProfile eq 'omg'}"> 
						<img height="300px" width="300px" src="${hContext }/resources/img/default.jpg" id="profileImgModify"/>
					</c:when>
					<c:otherwise> 
						<img height="300px" width="300px" src="${sessionScope.sessionProfile }" id="profileImgModify"/>
					</c:otherwise>
				</c:choose>
				<br><br>
				<input class="btn btn-primary" type="button" value="프로필 사진 변경(.jpg)" id="profileImgChange"/>
			</div>
		</div> <!-- end profileImg -->
		<br><br><br>
		<input class="btn btn-primary btn-lg btn-block" type="button" value="프로필 변경" id="doUpdateBtn">
		<input class="btn btn-default btn-lg btn-block" type="button" value="뒤로가기" id="doBackBtn">
		<input class="btn btn-danger btn-lg btn-block" type="button" value="회원 탈퇴" id="doDeleteBtn">
	</div>
	<form name="fileUpload" method="post" action="" id="form_data_img" enctype="multipart/form-data">
		<input style="display: none;" type="file" id="profileImgChangeHidden" name="profileImgChangeHidden" accept=".jpg" type="file">
	</form>
	





	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">
		// 프로필 사진 변경 버튼
		$("#profileImgChange").on("click", function(){
			$("#profileImgChangeHidden").click();		
		})
		// 프로필 사진 변경 버튼
		
		// 프로필 사진 미리보기
		$(document).ready(function(){
			$("#profileImgChangeHidden").on("change", handleImgFileSelect);
		});

		function handleImgFileSelect(e) {
			var files = e.target.files;
			var filesArr = Array.prototype.slice.call(files);

			filesArr.forEach(function(f){
				sel_file = f;

				var reader = new FileReader();
				reader.onload = function(e){
						$("#profileImgModify").attr("src", e.target.result);
					}
				reader.readAsDataURL(f);
				});
		}
		// 프로필 사진 미리보기
	
		// 뒤로가기 버튼
		$("#doBackBtn").on("click", function() {
			window.location.href = "${hContext}/workspace/moveToChannel.do";
		});
		// 뒤로가기 버튼

		// 회원수정 버튼
		$("#doUpdateBtn").on("click", function() {
			doUpdateProfileImg();
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
					window.location.href = "${hContext}/workspace/moveToChannel.do";
				},
				error : function(data) {
					alert("수정에 실패");
				}
			});

		}

		function doUpdateProfileImg(){
			if($("#profileImgChangeHidden").val() == ""){
					return;
				}
			
			console.log("doUpdateProfileImg");
			var formData = new FormData($("#form_data_img")[0]);
			formData.append("file", $("input[name=profileImgChangeHidden]")[0].files[0]);
			
			var fileType = document.getElementById("profileImgChangeHidden").value.split(".");
			var last_element = fileType[fileType.length - 1];

			console.log("2단계");
			
			if(last_element!="jpg"){
				alert(".jpg만 가능합니다.");
				return;
				}
			
			console.log("formData : " + formData);
			console.log("doUpdateProfileImg")
			$.ajax({
				url : '${hContext}/member/doUpdateProfileImg.do',
				data : formData,
				enctype : 'multipart/form-data',
				processData : false,
				contentType : false,
				type : 'POST',
				success : function(data) {
					console.log("success");
				},
				error : function(err) {
					console.log("error");
				}
			});
		}
		// 회원 수정
		
		
	</script>

</body>
</html>