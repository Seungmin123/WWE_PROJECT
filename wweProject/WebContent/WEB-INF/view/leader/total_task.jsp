<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="/resources/css/sb-admin-2.min.css" rel="stylesheet">
<link href="/resources/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet">
<link href="/resources/vendor/fontawesome-free/css/fontawesome.min.css"
	rel="stylesheet">
<link href="/resources/vendor/fontawesome-free/css/solid.min.css"
	rel="stylesheet">
<link href="/resources/vendor/fontawesome-free/css/brands.min.css"
	rel="stylesheet">
<link href="/resources/vendor/fontawesome-free/css/svg-with-js.min.css"
	rel="stylesheet">
<link href="/resources/vendor/fontawesome-free/css/regular.min.css"
	rel="stylesheet">
<link href="/resources/vendor/fontawesome-free/css/v4-shims.min.css"
	rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha256-4+XzXVhsDmqanXGHaHvgh1gMQKX40OUvDEBTu8JcmNs="
	crossorigin="anonymous"></script>
<link href="/resources/scss/_variables.scss" rel="stylesheet">
</head>
<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">
        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
            <!-- 사이드바 제목-->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                <div class="sidebar-brand-icon rotate-n-15">
                    <i class="fas fa-laugh-wink"></i>
                </div>
                <div class="sidebar-brand-text mx-3">WeWorkEasy</div>
            </a>
            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
               <!-- Nav Item - Dashboard -->
    <li class="nav-item">
        <a class="nav-link" href="index.html">
            <i class="fas fa-user-alt"></i>
            <span>My Page</span></a>
    </li>


    <!-- Nav Item - Pages Collapse Menu -->
    <li class="nav-item ">
        <a class="nav-link" href="#" data-target="#collapseTwo"
            aria-expanded="true" aria-controls="collapseTwo">
            <i class="fas fa-home"></i>
            <span>Main Page</span>
        </a>
    </li>
            

              <!-- Nav Item - Utilities Collapse Menu -->
    <li class="nav-item">
        <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseUtilities"
            aria-expanded="true" aria-controls="collapseUtilities" >
            <i class="fas fa-crown"></i>
            <span>Admin Page</span>
        </a>
        <div id="collapseUtilities" class="collapse" aria-labelledby="headingUtilities"
            data-parent="#accordionSidebar">
            <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Manage</h6>
                <a class="collapse-item" href="${context}/leader/manage">팀관리</a>
                <a class="collapse-item" href="${context}/leader/totaltask">업무관리</a>
            </div>
        </div>
    </li>

            <hr class="sidebar-divider my-0">

            <!-- Heading -->
            <div class="sidebar-heading">
                views
            </div>

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>views</span>
                </a>
                <div id="collapsePages" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">view Screens:</h6>
                        <a class="collapse-item" href="/views/calendar">Calendar</a>
                        <a class="collapse-item" href="/views/graph">Graph</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Charts -->
            
            
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePages1" aria-expanded="true" aria-controls="collapsePages">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Storage</span>
                </a>
                <div id="collapsePages1" class="collapse" aria-labelledby="headingPages" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <h6 class="collapse-header">Storage</h6>
                        <a class="collapse-item" href="/storage/personal">Personal</a>
                        <a class="collapse-item" href="/storage/share">Share</a>
                    </div>
                </div>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>

        </ul>
		<div id="content-wrapper" class="d-flex flex-column">
			<div id="content">
				<!-- Topbar -->
				<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                    <h3 class="text-primary">업무관리</h3>
                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">
                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - Alerts -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="alertsDropdown">
                                <h6 class="dropdown-header">
                                    Alerts Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-primary">
                                            <i class="fas fa-file-alt text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 12, 2019</div>
                                        <span class="font-weight-bold">A new monthly report is ready to download!</span>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-success">
                                            <i class="fas fa-donate text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 7, 2019</div>
                                        $290.29 has been deposited into your account!
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="mr-3">
                                        <div class="icon-circle bg-warning">
                                            <i class="fas fa-exclamation-triangle text-white"></i>
                                        </div>
                                    </div>
                                    <div>
                                        <div class="small text-gray-500">December 2, 2019</div>
                                        Spending Alert: We've noticed unusually high spending for your account.
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Show All Alerts</a>
                            </div>
                        </li>

                        <!-- Nav Item - Messages -->
                        <li class="nav-item dropdown no-arrow mx-1">
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_1.svg" alt="">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div class="font-weight-bold">
                                        <div class="text-truncate">Hi there! I am wondering if you can help me with a
                                            problem I've been having.</div>
                                        <div class="small text-gray-500">Emily Fowler · 58m</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_2.svg" alt="">
                                        <div class="status-indicator"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">I have the photos that you ordered last month, how
                                            would you like them sent to you?</div>
                                        <div class="small text-gray-500">Jae Chun · 1d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_3.svg" alt="">
                                        <div class="status-indicator bg-warning"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Last month's report looks great, I am very happy with
                                            the progress so far, keep up the good work!</div>
                                        <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                    </div>
                                </a>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60" alt="">
                                        <div class="status-indicator bg-success"></div>
                                    </div>
                                    <div>
                                        <div class="text-truncate">Am I a good boy? The reason I ask is because someone
                                            told me that people say this to all dogs, even if they aren't good...</div>
                                        <div class="small text-gray-500">Chicken the Dog · 2w</div>
                                    </div>
                                </a>
                                <a class="dropdown-item text-center small text-gray-500" href="#">Read More Messages</a>
                            </div>
                        </li>

                        <div class="topbar-divider d-none d-sm-block"></div>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">Douglas McGee</span>
                                <img class="img-profile rounded-circle" src="img/undraw_profile.svg">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Logout
                                </a>
                            </div>
                        </li>

                    </ul>

                </nav>
				<!-- End of Topbar -->

				<!-- 페이지 컨텐트 시작-->
				<div class="container-md border" >
                    <!-- 검색inputBox시작 -->
                        <form class=" form-inline float-right mb-2 mt-2">
                            <div class="input-group input-group-sm">
                                <input type="text" class="form-control bg-light border small" placeholder="검색" aria-label="Search" aria-describedby="inputGroup-sizing-sm">
                                <div class="input-group-append">
                                    <!-- 검색 버튼 -->
                                    <button class="btn btn-secondary" type="button">
                                        <i class="fas fa-search fa-sm"></i>
                                    </button>
                                    <!-- 검색버튼 끝 -->
                                </div>
                            </div>
                        </form>
                    <!-- 검색inputBox 끝 -->
                          <!-- 게시판 테이블 시작 -->
                          <!-- DB에서 받아온 데이터를 반복문 처리하여 테이블 그려야 할듯 -->
                        <table class="table">
                            <thead>
                                <tr class="text-dark table-primary">
                                	<th scope="col">No.</th>
                                    <th scope="col">담당자</th>
                                    <th scope="col">업무</th>
                                    <th scope="col" class="text-right" onclick="wohleCheck()"style="cursor:pointer" data-chk=false  id="wholeChk"> 전체선택</th>
                                </tr>
                            </thead>
                            <tbody>
                            	<!-- DB불러와서 동적으로 tr요소 생성할 곳 -->
                            </tbody>
                        </table>
                        <!-- 게시판 테이블 끝 -->
                        
                        <!--게시판 페이징 처리 부분 -->
                       <div class="row justify-content-center">
                            <div class="col-sm-12 col-md-7">
                                <div class="dataTables_paginate paging_simple_numbers" id="dataTable_paginate">
                                    <ul class="pagination justify-content-center" id="paging_ui">
                                    	<!--DB를 읽어와서 동적으로 업무 목록을 생성하는 부분  -->
                                    </ul>
                                </div>
                            </div>
                        </div>
                         <!--게시판 페이징 처리 부분 끝-->
				</div>
				<!-- 페이지 컨텐트 시작끝 -->
			</div>
			<!-- End of Main Content -->

			<!-- Footer시작 -->
			<footer class="sticky-footer bg-white">
				<div class="container my-auto">
					<div class="copyright text-center my-auto">
						<span>Copyright &copy; Your Website 2020</span>
					</div>
				</div>
			</footer>
			<!--Footer 끝 -->

		</div>
		<!-- End of Content Wrapper -->

	</div>
	<!-- End of Page Wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>
    
	<!-- Bootstrap core JavaScript-->

	<script src="/resources/vendor/jquery/jquery.min.js"></script>
	<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="/resources/js/sb-admin-2.min.js"></script>
	<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>
    <script src="/resources/js/leader/manage_task.js"></script>
    <script>
    $(function(){
    	selectTaskList();
    	doPaging();
    });
    //동적으로 업무리스트 게시판 생성하는 함수
    let selectTaskList = ()=>{
        
        let task;
		let sliceTaskList;
		
		let taskList = new Array();
        
   		<c:forEach var="i" begin="0" end="${taskList.size()-1}" step="1">
   			task = new Object();
   			task.tIdx = "${taskList.get(i).tIdx}";
   			task.userId = "${taskList.get(i).userId}";
   			task.taskId = "${taskList.get(i).taskId}";
   			taskList.push(task);
   		</c:forEach>
   		
   		let pageCount = taskList.length / 10 == 0 ? taskList.length/10 : parseInt(taskList.length/10) + 1;
   		sliceTaskList = new Array(pageCount);
   		let iSize = 0;
   		for(i = 0; i < sliceTaskList.length; i++){
   			sliceTaskList[i] = new Array();
   			while(true){
   				sliceTaskList[i].push(taskList[iSize]);
   				if(++iSize % 10 == 0 || iSize == taskList.length){
   					break;
   				}
   			}
   		}
   		for(i = pageIdx-1; i< pageIdx; i++){
   			for(j = 0; j< sliceTaskList[i].length; j++){
   				let trElement = document.createElement('tr');
           		document.querySelector('tbody').appendChild(trElement);
           		for(k =0; k <= 3; k++){
           			let tdElement = document.createElement('td');
           			if(k==0){
               			tdElement.innerHTML = sliceTaskList[i][j].tIdx;
               		}else if(k==1){
               			tdElement.innerHTML =sliceTaskList[i][j].userId;
               		}else if(k==2){
               			let aElement = document.createElement('a');
               			aElement.href="#";
               			aElement.innerHTML = sliceTaskList[i][j].taskId;
               			tdElement.appendChild(aElement);
               		}else{
               			tdElement.align="right";
    			  		let inputElement = document.createElement('input');
    			  		inputElement.className="form-check-input";
    			  		inputElement.type="checkbox";
    			  		inputElement.name="_selected_";
    			  		inputElement.value="ROW_1";
    			  		tdElement.appendChild(inputElement);
               		}
           	 	  trElement.appendChild(tdElement);
           		}
           		
   			}
   		}
    }
    
    //페이징 처리를 위한 번호 버튼을 생성하는 함수
    let doPaging =()=>{
    	let taskCount = "${taskCount}";
    	let pageCount = taskCount%10==0 ? taskCount/10 : parseInt(taskCount/10)+1;
    	
    	let wrapPrevBtn = document.createElement('li');
    	wrapPrevBtn.className ="paginate_button page-item previous";
    	let prevBtn = document.createElement('a');
    	prevBtn.className="page-link";
    	prevBtn.innerHTML ="이전";
    	prevBtn.href="#";
    	prevBtn.setAttribute("aria-controls","dataTable");
    	prevBtn.setAttribute('id','btn_prev');
    	wrapPrevBtn.appendChild(prevBtn);
    	document.querySelector('#paging_ui').appendChild(wrapPrevBtn);
    	for(i=0;i<pageCount;i++){
    		let liElement = document.createElement('li');
    		liElement.setAttribute('id','wrap_btn_page');
    		if(i==0){
    			liElement.className ="paginate_button page-item previous active";
    		}else{
    			liElement.className ="paginate_button page-item previous";
    		}
    		document.querySelector('#paging_ui').appendChild(liElement);
    		let aElement = document.createElement('a');
    		aElement.className = "page-link";
    		aElement.setAttribute("idx",i+1);
    		aElement.href="#";
    		aElement.setAttribute('id',"btn_page");
    		aElement.setAttribute('aria-controls',"dataTable");
    		aElement.innerHTML = i+1;
    		liElement.appendChild(aElement);
    	}
     	let wrapNextBtn = document.createElement('li');
     	wrapNextBtn.className ="paginate_button page-item next"
     	let nextBtn = document.createElement('a');
     	nextBtn.className ="page-link";
     	nextBtn.href="#";
     	nextBtn.setAttribute("aria-controls","dataTable");
     	nextBtn.setAttribute('id','btn_next');
     	nextBtn.innerHTML="다음";
     	wrapNextBtn.appendChild(nextBtn);
     	
     	document.querySelector('#paging_ui').appendChild(wrapNextBtn);
    }
    </script>
</body>
</html>