<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			<li><a href="/board/member/moveToProfile.do">${sessionScope.sessionId.id }님</a></li>
			<li><a>로그인 하신지 ${sessionScope.sessionId.loginCount }번째 되는 날입니다.</a></li>
			<li><a href="/board/member/logout.do">Logout</a></li>
			
		</ul>
	</div>
	<!-- /.container-fluid -->
</nav>