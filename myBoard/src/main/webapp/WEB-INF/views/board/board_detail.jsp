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
    <style>
    	.iwantbg{
    		background-color: #F5F5F5;
    		border-radius: 5px;
    		padding: 5px;
    		padding-top:10px;
    		margin-top : 10px;
    		padding-left:8px;
    	}
    	
    	.iwantcontent{
    		padding: 3px;
    		padding-top: 8px;
    		padding-left:10px;
    	}
    	
    	.childcomment{
    		margin-left:40px;
    	}
    </style>
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
			
			<br><br>
			<div class="text-center">
				<button id="board_recommend_btn" style="padding-left:6px;" class="btn btn-default btn-sm">
				<img width="25px" height="25px" alt="" src="${hContext }/resources/img/up.png">
				추천<strong><span id="recommend_count"> ${boardVO.recommend }</span></strong></button>
			</div>
			
		</div>
		<hr>
		<c:set var="name" value="${boardVO.regId }"/>
		<c:set var="sessionName" value="${sessionScope.sessionId.id }"/>
			<input type="button" style="float: right; margin-right: 10px;" value="뒤로가기" class="btn btn-default btn-lg" onclick="goBack();">
		<c:if test="${name eq sessionName }">
			<input id="updateBtn" type="button" style="float: right; margin-right: 10px;" value="수정하기" class="btn btn-default btn-lg"/>
			<input id="deleteBtn" type="button" style="float: right; margin-right: 10px;" value="삭제하기" class="btn btn-default btn-lg"/>
		</c:if>
		<br><br><br><br>
		<hr>
		<!-- 한 셋트 -->
		<div id="countBox"></div>
		<br>
		<div id="comment_box"></div>
		<br>
		<div id="comment_insert_box">
			<textarea id="comment_content" class="form-control" rows="4"></textarea>
			<input id="comment_insert_btn" type="button" value="댓글 등록" class="btn btn-default btn-lg" style="float: right; margin-top: 10px;"/>
			<input type="hidden" id="comment_parent_seq" value="0">
		</div>
		
		
		<form name="updateData" id="updateData" action="/board/board/moveToUpdatePage.do" method="post">
			<input type="hidden" name="board_header" id="board_header" value="temp_header">
			<input type="hidden" name="board_title" id="board_title" value="${boardVO.title }">
			<input type="hidden" name="board_content" id="board_content" value="${boardVO.content }">
			<input type="hidden" name="board_readCnt" id="board_readCnt" value="${boardVO.readCount }">
			<input type="hidden" name="board_recommend" id="board_recommend" value="${boardVO.recommend }">
			<input type="hidden" name="regII" id="regII" value="${boardVO.regId }"/>
			<input type="hidden" name="board_seq" id="board_seq" value="${boardVO.seq }">
			<input type="hidden" name="workspace_name" id="workspace_name" value="${workspaceName }">
			<input type="hidden" name="page_num" id="page_num" value="${pageNum }"/>
			<input type="hidden" name="search_div" id="search_div" value="${searchDiv }">
			<input type="hidden" name="search_word" id="search_word" value="${searchWord }">
			<input type="hidden" name="session_id" id="session_id" value="${sessionScope.sessionId.id }">
		</form>
		
	</div>
	<span id=""></span>

	<%@ include file="/WEB-INF/views/main/footer.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">

	// 코멘트 댓글&삭제&추천 버튼 클릭
	$("#comment_box").on("click", "span", function(){

			var tmp2 = $(this).attr('name');
			console.log(tmp2);
			var tmp = $(this).parent().parent();
			var tmp3 = tmp.attr("id");
			var content = $(this).parent().parent().find('span').eq(0).text();
			var recommend = $(this).parent().parent().find('strong').eq(1).text();

			
			if(tmp2 == 'recomment'){
					// parentSeq
					
					$("#comment_insert_box").detach();
	
					var html = "";
					html += "<div id='comment_insert_box'>";
					html += "<textarea id='comment_content' class='form-control' rows='4'></textarea>";
					html += "<input id='comment_insert_btn' type='button' value='댓글 등록' class='btn btn-default btn-lg' style='float: right; margin-top: 10px;'/>";
					html += "<input type='hidden' id='comment_parent_seq' value='"+tmp3+"'>";
					html += "<br><br><br></div>";
	
					$("#"+tmp3).after(html);
	
					$("#comment_content").focus();

				} else if(tmp2 == 'comment_delete'){
					var result = confirm("댓글에 대한 정보(추천 수, 대댓글)가 모두 지워집니다. 정말 삭제하시겠습니까?");
					if(!result){
						return;
					}
					doDeleteComment(tmp3);
				} else if(tmp2 == 'recommend'){
					console.log("commentSeq : "+tmp3);
					console.log("content : "+content);
					console.log("recommend : " + recommend);
					doRecommendComment(tmp3, recommend, content);
				}
			
			
		});

	function doRecommendComment(seq, recommend, content){
		
		$.ajax({
			type:'POST',
			url:'${hContext}/comment/doRecommend.do',
			dataType:"html",
            async: true,
            data:{
				"seq" : seq,
				"recommend" : recommend,
				"content" : content
	            },
	        success:function(data){
		        	location.reload();
	        	},
		    error:function(){
					console.log("실패..");
			    },
			complete:function(data){
				
				}  
			});


		}
	
	function doDeleteComment(seq){
		$.ajax({
			type:'POST',
			url:'${hContext}/comment/doDeleteComment.do',
			dataType:"html",
            async: true,
            data:{
				"seq" : seq,
				"regId" : $("#session_id").val()
	            },
	        success:function(data){
		        	location.reload();
	        	},
		    error:function(){
					console.log("실패..");
			    },
			complete:function(data){
				
				}  
			});

		}
		
		
	// 코멘트 댓글&삭제&추천 버튼 클릭
	
	// 추천하기
	$("#board_recommend_btn").on("click", function(){
			doRecommend();
		})
		
	function doRecommend(){
		$.ajax({
			type:'POST',
			url:'${hContext}/board/doRecommend.do',
			dataType:"json",
            async: true,
            data:{
				"seq" : $("#board_seq").val(),
				"header" : $("#board_header").val(),
				"title" : $("#board_title").val(),
				"content" : $("#board_content").val(),
				"readCount" : $("#board_readCnt").val(),
				"recommend" : $("#board_recommend").val()
	            },
	        success:function(data){
		        	console.log("암튼 성공!");
		        	location.reload();
	        	},
		    error:function(){
					alert("실패했습니다. 다시 시도해주세요.");
			    },
			complete:function(data){
				
				}  
			});

		}
	// 추천하기
	
	// 댓글 등록하기
	$(document).on("click",'#comment_insert_btn' ,function(){
			doInsertComment();
		})
		
	function doInsertComment(){
		var tmp = $("#comment_parent_seq").val();
		$.ajax({
			type:'POST',
			url:'${hContext}/comment/doInsertComment.do',
			dataType:"json",
            async: true,
            data:{
				"boardSeq" : $("#board_seq").val(),
				"parentSeq" : $("#comment_parent_seq").val(),
				"content" : $("#comment_content").val()
	            },
	        success:function(data){
					doSelectListComment();
					document.getElementById('comment_content').value = "";
					if(tmp != 0){
							var html = '';
							html += "<br><div id='comment_insert_box'>";
							html += "<textarea id='comment_content' class='form-control' rows='4'></textarea>";
							html += "<input id='comment_insert_btn' type='button' value='댓글 등록' class='btn btn-default btn-lg' style='float: right; margin-top: 10px;'/>";
							html += "<input type='hidden' id='comment_parent_seq' value='0'>";
							html += "</div>";

							$("#comment_box").after(html);
						}
	        	},
		    error:function(){
					alert("실패했습니다. 다시 시도해주세요.");
			    },
			complete:function(data){
				
				}  
			});

		}
	// 댓글 등록하기
	
	// 댓글 불러오기
	window.onload = function(){
			doSelectListComment();
		}

	function doSelectListComment(){
		$.ajax({
			type:'POST',
			url:'${hContext}/comment/doSelectListComment.do',
			dataType:"json",
            async: true,
            data:{
				"boardSeq" : $("#board_seq").val()
	            },
	        success:function(data){
		        	console.log(data);
		        	var html = "";
					var html2 = "";
					var sessionId = document.getElementById('session_id').value;
					var countComment = data.length;
					$("#countBox").empty();
					$("#comment_box").empty();
					html += "<h3>"+countComment+"개의 댓글</h3>";
					$("#countBox").append(html);
					
					$.each(data, function(i, value){
							if(value.parentSeq == 0){
									html = "";
									html += "<div class='parentcomment' id='"+value.seq+"'>";
									html += "<div class='iwantbg'>";
									html += "<strong>"+value.regId+"</strong> "+value.regDt;
									html += "</div>";
									html += "<div class='iwantcontent'>";
									html += "<span>"+ value.content +"<span>";
									html += "</div>";
									html += "<div class='text-right'>";
									html += "<span name='recommend'>추천</span>&nbsp;<strong>"+value.recommend+"</strong>&nbsp;&nbsp;<span name='recomment'>댓글</span>";
									if(value.regId == sessionId){
										html += "<span name='comment_delete'>&nbsp;&nbsp;삭제</span>";
										}
									html += "</div>";
									html += "</div>";
									$("#comment_box").append(html);
								} else if(value.parentSeq != 0) {
									html2 = "";
									html2 += "<div class='childcomment' id='"+value.seq+"' name='parent_"+value.parentSeq+"'>";
									html2 += "<div class='iwantbg'>";
									html2 += "<strong>"+value.regId+"</strong> "+value.regDt;
									html2 += "</div>";
									html2 += "<div class='iwantcontent'>";
									html2 += "<span>"+ value.content +"<span>";
									html2 += "</div>";
									html2 += "<div class='text-right'>";
									html2 += "<span name='recommend'>추천</span>&nbsp;<strong>"+value.recommend+"</strong>&nbsp;&nbsp;";
									if(value.regId == sessionId){
										html2 += "<span name='comment_delete'>삭제</span>";
										}
									html2 += "</div>";
									html2 += "</div>";
									$("#"+value.parentSeq).after(html2);
									
									}

							
						});
		        	
		        },
		    error:function(){
					alert("실패했습니다. 다시 시도해주세요.");
			    }  
			});

		}
	// 댓글 불러오기
	
	// 뒤로가기 버튼
	function goBack(){
	   		var workspaceName = document.getElementById('workspaceName').text;
	   		var pageNum = document.getElementById('page_num').value;
	   		var searchWord = document.getElementById('search_word').value;
	   		var searchDiv = document.getElementById('search_div').value;
			var gourl = "/board/workspace/moveToBoardPage.do?search_div="+ searchDiv  +"&search_word="+ searchWord +"&page_num="+pageNum+"&page_size=10&whereToGo=" + workspaceName;
			window.location.href = gourl;
		}
	// 뒤로가기 버튼
	
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
		       		var workspaceName = document.getElementById('workspaceName').text;
					console.log(workspaceName);
					var gourl = "/board/workspace/moveToBoardPage.do?whereToGo=" + workspaceName+"&search_div=&search_word=";
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
			var gourl = "/board/workspace/moveToBoardPage.do?whereToGo=" + workspaceName + "&search_div=&search_word=";
			console.log(gourl);
			window.location.href = gourl;
		});
	// 게시판으로 돌아가기
	
	</script>
	
</body>
</html>