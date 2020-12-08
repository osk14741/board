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
</head>
<body>
	<%@ include file="/WEB-INF/views/main/nav.jsp" %>
	<div class="container">
		<h1>채널 관리</h1>
		<hr>
		
		<div class="table-responsive">
				<table id="channel_table" class="table table-striped table-hover ">
					<thead>
						<tr>
							<th class="text-center col-md-3">주제</th>
							<th class="text-center col-md-3">채널이름</th>
							<th class="text-center col-md-2">등록일</th>
							<th class="text-center col-md-1">게시글 수</th>
							<th class="text-center col-md-1">오늘 게시글</th>
							<th class="text-center col-md-2">삭제</th>
						</tr>
					</thead>
					<tbody></tbody>
				</table>
			</div>
			<div class="text-right">
				<input class="btn btn-default btn-lg" type="button" value="채널 등록" onclick="channelReg();">
			</div>
			
	</div>
	
	
	<%@ include file="/WEB-INF/views/main/footer.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">

	// 삭제 버튼 클릭
	$("#channel_table tbody").on("click", "td", function(){
		var tmp = $(this).parent().find("td:eq(0)").text();
		var tmp2 = $(this).find("td");

		console.log(tmp);
		
		var result = confirm("정말 삭제하시겠습니까?");
		if(!result){
			return;
		}
		
		$.ajax({
			type:"GET",
               url:"${hContext}/workspace/doDelete.do",
               dataType:"html",
               async: false,
               data:{
                   "seq":tmp
               },
               success: function(data){
                   		alert("삭제 완료");
                   		doSelectListForMng();
	               }
			});
		
		
		
	});
	// 삭제 버튼 클릭
	
	// 채널 등록 페이지로 이동
	function channelReg(){
		window.location.href = "/board/workspace/moveToRegisterPage.do";
	}
	// 채널 등록 페이지로 이동	
	
	// 채널 리스팅
	window.onload = function(){
		doSelectListForMng();
		}

	function doSelectListForMng(){
		$.ajax({
			type:"GET",
               url:"${hContext}/workspace/doSelectListForMng.do",
               dataType:"json",
               async: true,
               data:{
               },
               success: function(data){
					$("#channel_table>tbody").empty();
					var html = "";
					$.each(data, function(i, value) {
						  html += "<tr>";
						  html += "<td name='workspace_seq' style='display: none;' class='text-center'>"+value.seq+"</td>";
						  html += "<td class='text-center col-md-3'>"+value.topic+"</td>";
						  html += "<td class='text-center col-md-3'>"+value.name+"</td>";
						  html += "<td class='text-center col-md-2'>"+value.regDt+"</td>";
						  html += "<td class='text-center col-md-1'>"+value.boardCount+"</td>";
						  html += "<td class='text-center col-md-1'>"+value.regBoardCount+"</td>";
						  html += "<td class='text-center col-md-2'><input type='button' value='삭제' class='btn btn-default'></td>";
						  html += "</tr>";
					  });
					console.log(data.length);
					if(data.length == "0"){
							console.log(data.length);
							html += "<tr>";
							html += "<td style='display: none;'>a</td>";
							html += "<td colspan='3' class='text-center'>등록된 채널이 없습니다.</td>";
							html += "</tr>";
						}
					
					$("#channel_table>tbody").append(html);
	               }
			});
		}
	// 채널 리스팅

	</script>
	
</body>
</html>