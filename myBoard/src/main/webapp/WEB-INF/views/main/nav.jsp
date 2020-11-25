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
			<li><a href="/board/member/moveToProfile.do">Profile</a></li>
			
			
			
			
			
			<li class="dropdown"><a href="/board/workspace/moveToChannel.do" class="dropdown-toggle">Dropdown
					<span class="caret"></span>
			</a>
				<ul class="dropdown-menu" role="menu">
					<li><a href="#">Action</a></li>
					<li><a href="#">Another action</a></li>
					<li><a href="#">Something else here</a></li>
					<li class="divider"></li>
					<li><a href="#">Separated link</a></li>
				</ul></li>
		</ul>
	</div>
	<!-- /.container-fluid -->
</nav>