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
    <title>Channel View</title>
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
    <style type="text/css">
    	dd:hover {
    		text-decoration: underline;
    	}
    	
    	dl{
    		width:20%;
    	}
    </style>
</head>
<body>

	<%@ include file="/WEB-INF/views/main/nav.jsp" %>
	
	<div class="container">
		<h1>Channel View</h1>
		<hr>
		<div class="form-group" id="channelList">
		</div>
		<form method="get" name="moveToBoardPage" id="moveToBoardPage" action="/board/workspace/moveToBoardPage.do">
			<input type="hidden" id="whereToGo" name="whereToGo">
			<input type="hidden" name="search_div" value="">
			<input type="hidden" name="search_word" value="">
		</form>
		<form action="/board/workspace/moveToRegisterPage.do">
		
			<input class="btn btn-primary" type="submit" value="채널 등록">
		</form>
	</div>	<!-- end container -->
	


	<%@ include file="/WEB-INF/views/main/footer.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">
	// 채널 리스트 불러오기
	window.onload = function(){
		onloadFunction();
	}
	
	function onloadFunction(){
		$.ajax({
			type:'POST',
			url:'${hContext}/workspace/doListing.do',
			dataType:"html",
            async: true,
            data:{
				
	            },
	        success:function(data){
		       		console.log("success!");
		        	var parseData = JSON.parse(data);
					$("#channelList").empty();
					var html = "";
					$.each(parseData, function(i, value) {
						try{
								if(parseData[i-1].topic == value.topic){
									html += "<dd>"+value.name+"</dd>";
								} else {
									html += "</dl>";
									html += "<dl>";
									html += "<dt>"+value.topic+"</dt>";
									html += "<dd>"+value.name+"</dd>";
								}
							} catch (e) {
								html += "<dl>";
								html += "<dt>"+value.topic+"</dt>";
								html += "<dd>"+value.name+"</dd>";
							}
					  });
					html += "</dl>";
					$("#channelList").append(html);
		        },
		    error:function(data){
					alert("실패");
			    }  
			});
	} 
	// 채널 리스트 불러오기
	
	// 채널 클릭 시 게시판으로 이동
	$("#channelList").on("click","dd",function(event){
	    console.log($(event.target).text());
	    document.getElementById('whereToGo').value = $(event.target).text();
	    document.moveToBoardPage.submit();
	})
	// 채널 클릭 시 게시판으로 이동
	
	// 채널 등록 페이지로 이동
	function moveToRegister(){
		window.location.href = "${hContext}/member/registerView.do";
	}
	// 채널 등록 페이지로 이동	

	</script>
	
</body>
</html>