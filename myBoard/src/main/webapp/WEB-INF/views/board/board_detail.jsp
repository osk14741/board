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
    <title>Board Page</title>
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
		
		<h2><a id="workspaceName"><strong>${workspaceName }</strong></a></h2>
		
		<hr>
		<h2><strong>${boardVO.title }</strong>
		<small>${boardVO.regId }&nbsp;&nbsp;${boardVO.regDt }</small></h2>
		<hr>
		<div class="content">
			<span>${boardVO.content }</span>
			
		</div>
		<hr>
		<c:set var="name" value="${boardVO.regId }"/>
		<c:set var="sessionName" value="${sessionScope.sessionId.id }"/>
		<c:if test="${name eq sessionName }">
			<input id="updateBtn" type="button" style="float: right" value="수정하기" class="btn btn-default btn-lg"/>
			<input id="deleteBtn" type="button" style="float: right" value="삭제하기" class="btn btn-default btn-lg"/>
		</c:if>
		
		<form name="updateData" id="updateData" action="/board/board/moveToUpdatePage.do" method="post">
			<input type="text" name="board_header" id="board_header" value="temp_header">
			<input type="text" name="board_title" id="board_title" value="${boardVO.title }">
			<input type="text" name="board_content" id="board_content" value="${boardVO.content }">
			<input type="text" name="board_readCnt" id="board_readCnt" value="${boardVO.readCount }">
			<input type="text" name="board_recommend" id="board_recommend" value="${boardVO.recommend }">
			<input type="text" name="regII" id="regII" value="${boardVO.regId }"/>
			<input type="text" name="board_seq" id="board_seq" value="${boardVO.seq }">
			<input type="text" name="workspace_name" id="workspace_name" value="${workspaceName }">
		</form>
		
	</div>













	<%@ include file="/WEB-INF/views/main/footer.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">

	// 삭제하기 버튼 클릭
	$("#deleteBtn").on("click", function(){
		var result = confirm("정말 삭제하시겠습니까?");
		if(!result){
			return;
		}
		
		console.log("clicked");
			doDelete();
		});
	
	function doDelete(){
		$.ajax({
			type:'POST',
			url:'${hContext}/board/doDelete.do',
			dataType:"html",
            async: true,
            data:{
				"seq" : $("#board_seq").val(),
				"regId" : $("#regII").val()
	            },
	        success:function(data){
		       		alert("삭제 성공");
		       		var workspaceName = document.getElementById('workspaceName').text;
					console.log(workspaceName);
					var gourl = "/board/workspace/moveToBoardPage.do?whereToGo=" + workspaceName;
					window.location.href = gourl;
		        },
		    error:function(){
					alert("실패했습니다. 다시 시도해주세요.");
			    }  
			});
		}
	
	// 삭제하기 버튼 클릭
	
	// 수정하기 버튼 클릭
	$("#updateBtn").on("click", function(){
			doUpdate();
		})
	
	function doUpdate(){
			var frm = document.updateData;
			frm.submit();

		}
	// 수정하기 버튼 클릭
	
	// 게시판으로 돌아가기
	$("#workspaceName").on("click", function(){
			var workspaceName = document.getElementById('workspaceName').text;
			console.log(workspaceName);
			var gourl = "/board/workspace/moveToBoardPage.do?whereToGo=" + workspaceName;
			console.log(gourl);
			window.location.href = gourl;
		});
	// 게시판으로 돌아가기
	
	</script>
	
</body>
</html>