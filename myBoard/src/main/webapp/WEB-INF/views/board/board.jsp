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
		<h1>Board Page</h1>
		<hr>
		<form name="saveData" id="saveData" action="/board/board/moveToWritePage.do" method="get">
			<label>workspaceSeq = </label>
			<input type="text" value="${workspaceSeq }" id="workspaceSeq" name="workspaceSeq"/>
		</form>
		<br>
		<label>page</label>
		<input type="text" value="0" id="page">
		<hr>
		
		<div class="table-responsive">
			<table id="board_table" class="table table-striped table-hover">
				<thead>
					<tr>
						<th class="text-center col-md-6">title</th>
						<th class="text-center col-md-2">regId</th>
						<th class="text-center col-md-2">regDt</th>
					</tr>
				</thead>
				<tbody></tbody>
			</table>
		</div>
		
		<input id="writeBtn" type="button" style="float: right" value="글쓰기" class="btn btn-default"/>
		
	</div>
	





	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">

	// 글쓰기 페이지 이동
	$("#writeBtn").on("click", function(){
			var frm = document.saveData;
			frm.submit();
		})
	// 글쓰기 페이지 이동
	
	// 리스팅
	window.onload = function(){
			doSelectList();
		}

	function doSelectList(){
		$.ajax({
			type:"GET",
               url:"${hContext}/board/doSelectList.do",
               dataType:"html",
               async: true,
               data:{
               		"workspaceSeq":$("#workspaceSeq").val()
               },
               success: function(data){
					var parseData = JSON.parse(data);
					$("#board_table>tbody").empty();
					var html = "";
					
					$.each(parseData, function(i, value) {
						  html += "<tr>";
						  html += "<td style='display: none;' class='text-center'>"+value.seq+"</td>";
						  html += "<td class='text-left col-md-6'>"+value.title+"</td>";
						  html += "<td class='text-center col-md-2'>"+value.regId+"</td>";
						  html += "<td class='text-center col-md-2'>"+value.regDt+"</td>";
						  html += "</tr>";
					  });
					console.log(parseData.length);
					if(parseData.length == "0"){
							console.log(parseData.length);
							html += "<tr>";
							html += "<td colspan='3' class='text-center'>등록된 게시글이 없습니다.</td>";
							html += "</tr>";
						}
					
					$("#board_table>tbody").append(html);
	               }
			});

		}
	// 리스팅

	</script>
	
</body>
</html>