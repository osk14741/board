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
    <title>Board Write Page</title>
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
    
    <!-- SmartEditor2 라이브러리 -->
    <script type="text/javascript" src="${hContext }/resources/se2/js/HuskyEZCreator.js" charset="utf-8"></script> 
    <script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script>

    
</head>
<body>

	<%@ include file="/WEB-INF/views/main/nav.jsp" %>
	<div class="container">
		<form name="noticeWriteForm" id="noticeWriteForm" action="/board/board/doUpdate.do" method="get">
		<input class="form-control" type="text" value="${boardVO.title }" id="title" name="title" placeholder="제목을 입력해주세요">
		<!-- SmartEditor2 -->
		<br>
		<div class="jsx-2303464893 editor">
			<div class="fr-box fr-basic fr-top" role="application">
				<div class="fr-wrapper show-placeholder" dir="auto"
					style="overflow: scroll;">
					<textarea name="notice_content" id="smartEditor"
						style="width: 100%; height: 412px;">${boardVO.content }</textarea>
				</div>
			</div>
		</div>
			<input type="text" name="board_seq" id="board_seq" value="${boardVO.seq }"/>
			<input type="text" name="board_header" id="board_header" value="temp_header">
			<input type="text" name="board_content" id="board_content" value="${boardVO.content }">
			<input type="text" name="board_readCnt" id="board_readCnt" value="${boardVO.readCount }">
			<input type="text" name="board_recommend" id="board_recommend" value="${boardVO.recommend }">
			<input type="text" name="workspace_name" id="workspace_name" value="${workspaceName }">
		</form>
		<br>
		<input id="savebutton" type="button" style="float: right" value="수정하기" class="btn btn-default"/>
	</div>
	
	
	
	
	
	<%@ include file="/WEB-INF/views/main/footer.jsp" %>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript">

	

	</script>
	<!-- SmartEditor2 -->
	<script type="text/javascript" src="${hContext }/resources/js/notice-write.js"></script>
</body>
</html>


