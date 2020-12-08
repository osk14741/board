<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %> 
<c:set var="hContext" value="${pageContext.request.contextPath }" ></c:set> 
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 위 3개의 메타 태그는 *반드시* head 태그의 처음에 와야합니다; 어떤 다른 콘텐츠들은 반드시 이 태그들 *다음에* 와야 합니다 -->
    <title>channel register Page</title>
    <link rel="shortcut icon" type="image/x-icon" href="${hContext}/resources/img/favicon.ico" > 
    <!-- 부트스트랩 -->
    <link href="${hContext}/resources/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
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
		<h1>channel register page</h1>
		<hr>
		<div style="width: 60%" class="contents">
			<form method="post" action="doLogin.do" class="" name="loginForm" id="loginForm">
				<div class="form-group">
					<label for="inputMemberId">채널 이름</label>
					<input type="text" class="form-control" name="inputWorkspaceName" id="inputWorkspaceName" placeholder="채널 이름">
				</div>
				<label for="inputPassword">채널 주제</label>
				<div class="form-group" id="topicList">
					<label class="radio-inline">
						<input type="radio" name="inlineRadioOptions" id="inlineRadio1" value="1"> 남성
					</label>
				</div>
				
				<input class="btn btn-primary btn-lg btn-block" type="button" value="등록" id="doRegisterBtn">
				<input class="btn btn-default btn-lg btn-block" type="button" value="뒤로가기" id="doBackBtn">
			</form>
		</div>	<!-- end contents -->
	</div>	<!-- end container -->
	
	
	
	
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">
		window.onload = function(){
			onloadFunction();
		}
		
		function changeThis(){
				$("input[name='inlineRadioOptions']:checked").attr('checked', false);
			}
		
		// 뒤로가기
		$("#doBackBtn").on("click",function(){
			window.location.href = "${hContext}/workspace/moveToChannel.do";
		});
		// 뒤로가기
		
		$("#doRegisterBtn").on("click", function(){
			doRegister();
		});

		// 채널 주제 불러오기
		function onloadFunction(){
			$.ajax({
				type:'POST',
				url:'${hContext}/workspace/doListingTopic.do',
				dataType:"html",
	            async: true,
	            data:{
		            },
		        success:function(data){
			       		console.log("success!");
			        	var parseData = JSON.parse(data);
						$("#topicList").empty();
						var html = "";
						$.each(parseData, function(i, value) {
							html += "<label class='radio-inline'>";
							html += "<input type='radio' name='inlineRadioOptions' value='"+value.topic+"'>"+value.topic;
							html += "</label>"
						  });
						html += "<form class='form-inline'>";
						html += "<div class='form-group'>";
						html += "<label class='radio-inline'>주제 추가 :</label>";
						html += "<input id='another_topic' style='width:200px;' class='form-control' type='text' placeholder='주제' onchange='changeThis();'> ";
						html += "</div>";
						html += "</form>";
						$("#topicList").append(html);
			        },
			    error:function(data){
						alert("실패");
				    }  
				});

			}
		// 채널 주제 불러오기
		
		// 채널 등록
		function doRegister(){

			var topic = $("input[name='inlineRadioOptions']:checked").val();
			var anotherTopic = $("#another_topic").val();
			
			if(anotherTopic != ""){
					topic = anotherTopic;
					console.log("topic : " + topic);
				}
			
			$.ajax({
				type:'POST',
				url:'${hContext}/workspace/doRegister.do',
				dataType:"html",
	            async: true,
	            data:{
					"name":$("#inputWorkspaceName").val(),
					"topic": topic
		            },
		        success:function(data){
			        		alert("등록 성공!");
			        		window.location.href = "${hContext}/workspace/moveToChannel.do";
			        },
			    error:function(data){
							alert("존재하는 채널명입니다.");
				    }  
				});

		}
		// 채널 등록
	
	</script>
	
</body>
</html>