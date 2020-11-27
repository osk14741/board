<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/board/workspace/moveToChannel.do">BOARD</a>
		</div>
		<form class="navbar-form navbar-left" role="search">
			<div class="form-group">
				<input type="text" class="form-control" placeholder="Search">
			</div>
			<button type="submit" class="btn btn-default">Submit</button>
		</form>

		<ul class="nav navbar-nav navbar-right">
				<c:choose>
					<c:when test="${sessionScope.sessionId.authority eq '0'}"> 
						<li><a> 일반 </a></li>
					</c:when>
					<c:when test="${sessionScope.sessionId.authority eq '1'}">
						<li><a> 중간 관리자 </a></li>
					</c:when>
					<c:when test="${sessionScope.sessionId.authority eq '2'}">
						<li><a> 관리자 </a></li>
					</c:when>
					<c:otherwise></c:otherwise>
				</c:choose>
			<li><a href="/board/member/moveToProfile.do">${sessionScope.sessionId.id }님</a></li>
			<li><a>접속 ${sessionScope.sessionId.loginCount }번째 되는 날입니다.</a></li>
			<li><a href="${hContext }/member/loginView.do">Logout</a></li>
			
		</ul>
	</div>
	<!-- /.container-fluid -->
</nav>