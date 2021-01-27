<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/bootstrap_green.css" rel="stylesheet">
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
		<a class="navbar-brand" href="#" style="color: white">WWE</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarColor03" aria-controls="navbarColor03"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	</nav>

	<div class="jumbotron container-sm">
		<div class="row justify-content-md-center">
		<table class="table table-hover">
			<thead>
				<tr class="table-success">
					<th scope="col">이름</th>
					<th scope="col">상태</th>
					<th scope="col">권한</th>
					<th scope="col">관리</th>
				</tr>
			</thead>
			<tbody>

			</tbody>
		</table>
		</div>
		
		<div>
			<button type="button" class="btn btn-primary" id="btn_invite">초대</button>
		</div>
	</div>
</body>
<script>
    document.querySelector('#btn_invite').addEventListener('click',(e)=>{
    	alert("이거 누르면 팝업띄우자")
    });
</script>
</html>