<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<link href="../resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link href="../resources/css/sb-admin-2.css" rel="stylesheet">
</head>
<body id="page-top">

	<div>
		<c:forEach var="alarmData" items="${alarmList}">
			<a class="dropdown-item d-flex align-items-center" href="#">
				<div class="mr-3">
					<div class="icon-circle bg-primary">
						<i class="fas fa-file-alt text-white"></i>
					</div>
				</div>
				<div>
					<div class="small text-gray-500">${alarmData.addDate}</div>
					<span class="font-weight-bold">${alarmData.writer} 님이
						${alarmData.typeOfAlarm}를 추가 하셨습니다.</span>
				</div>
			</a>
		</c:forEach>
	</div>



	<!-- Bootstrap core JavaScript-->
    <script src="../resources/vendor/jquery/jquery.min.js"></script>
    <script src="../resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="../resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="../resources/js/sb-admin-2.min.js"></script>
    <script src="../resources/js/model/signController.js"></script>

</body>
</html>