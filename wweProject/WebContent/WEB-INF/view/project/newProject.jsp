<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Blank</title>

    <!-- Custom fonts for this template-->
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="/resources/css/sb-admin-2.css" rel="stylesheet">
    
       <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    

<style>
	
		/* 프로젝트 목록 */
		
		/* 새 프로젝트 추가 */
		* {
    		font-family: 'Jua', sans-serif;
		}

		.mytitle {
			margin: auto;
			
		    background-color: green;
		
		    width: 300px;
		    height: 200px;
		
		    color: white;
		
		    text-align: center;
		
		    background-image: url("https://www.ancient-origins.net/sites/default/files/field/image/Agesilaus-II-cover.jpg");
		    background-size: cover;
		    background-position: center;
		
		    border-radius: 10px;
		
		    padding-top: 20px;
		}
		
		.wrap {
		    width: 300px;
		    margin: auto;
		}
		
		.mybtn {
		    width: 100px;
		    margin: auto;
		    display: block;
		}
		
		.red-font {
		    color: red;
		    font-size: 16px;
		}

		
        .wrap {
            width: 700px;
            margin: auto;
        }

        .posting-box {
            width: 300px;
            margin: 30px auto;
            border: 3px solid black;
            border-radius: 5px;
            padding: 30px;

            display: none;
        }
        
     
        
        
        /* 채팅 */
        html, body {
            background: #efefef;
            height: 100%;
        }
        
 		#chat-circle { 
            position: fixed;
            bottom: 50px;
            right: 50px;
           	background-color: #1abc9c;
  			background-image: linear-gradient(180deg, #1abc9c 10%, #224abe 100%);
  			background-size: cover;
            width: 80px;
            height: 80px;
            border-radius: 50%;
            color: white;
            padding: 28px;
            cursor: pointer;
            box-shadow: 0px 3px 16px 0px rgba(0, 0, 0, 0.6), 0 3px 1px -2px rgba(0, 0, 0, 0.2), 0 1px 5px 0 rgba(0, 0, 0, 0.12);
        }

        .btn#my-btn {
            background: white;
            padding-top: 13px;
            padding-bottom: 12px;
            border-radius: 45px;
            padding-right: 40px;
            padding-left: 40px;
            color: #5865C3;
        }

		.chat-box {
			width: 300px;
			height: 500px;
		}

 		 #chat-overlay {
            background: rgba(255, 255, 255, 0.1);
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            border-radius: 50%;
            display: none;
        }


        .chat-box {
            display: none;
            background: #efefef;
            position: fixed;
            right: 30px;
            bottom: 50px;
            width: 350px;
            max-width: 85vw;
            max-height: 100vh;
            border-radius: 5px;
            /*   box-shadow: 0px 5px 35px 9px #464a92; */
            box-shadow: 0px 5px 35px 9px #ccc;
        }

        .chat-box-toggle {
            float: right;
            margin-right: 15px;
            cursor: pointer;
        }

        .chat-box-header {
            background-color: #1abc9c;
  			background-image: linear-gradient(180deg, #1abc9c 10%, #224abe 100%);
            height: 70px;
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
            color: white;
            text-align: center;
            font-size: 20px;
            padding-top: 17px;
        }

        .chat-box-body {
            position: relative;
            height: 370px;
            height: auto;
            border: 1px solid #ccc;
            overflow: hidden;
        }

        .chat-box-body:after {
            content: "";
            background-image: url('data:image/svg+xml;base64,PHN2ZyB3aWR0aD0iMjAwIiBoZWlnaHQ9IjIwMCIgdmlld0JveD0iMCAwIDIwMCAyMDAiIHhtbG5zPSJodHRwOi8vd3d3LnczLm9yZy8yMDAwL3N2ZyI+PGcgdHJhbnNmb3JtPSJ0cmFuc2xhdGUoMTAgOCkiIGZpbGw9Im5vbmUiIGZpbGwtcnVsZT0iZXZlbm9kZCI+PGNpcmNsZSBzdHJva2U9IiMwMDAiIHN0cm9rZS13aWR0aD0iMS4yNSIgY3g9IjE3NiIgY3k9IjEyIiByPSI0Ii8+PHBhdGggZD0iTTIwLjUuNWwyMyAxMW0tMjkgODRsLTMuNzkgMTAuMzc3TTI3LjAzNyAxMzEuNGw1Ljg5OCAyLjIwMy0zLjQ2IDUuOTQ3IDYuMDcyIDIuMzkyLTMuOTMzIDUuNzU4bTEyOC43MzMgMzUuMzdsLjY5My05LjMxNiAxMC4yOTIuMDUyLjQxNi05LjIyMiA5LjI3NC4zMzJNLjUgNDguNXM2LjEzMSA2LjQxMyA2Ljg0NyAxNC44MDVjLjcxNSA4LjM5My0yLjUyIDE0LjgwNi0yLjUyIDE0LjgwNk0xMjQuNTU1IDkwcy03LjQ0NCAwLTEzLjY3IDYuMTkyYy02LjIyNyA2LjE5Mi00LjgzOCAxMi4wMTItNC44MzggMTIuMDEybTIuMjQgNjguNjI2cy00LjAyNi05LjAyNS0xOC4xNDUtOS4wMjUtMTguMTQ1IDUuNy0xOC4xNDUgNS43IiBzdHJva2U9IiMwMDAiIHN0cm9rZS13aWR0aD0iMS4yNSIgc3Ryb2tlLWxpbmVjYXA9InJvdW5kIi8+PHBhdGggZD0iTTg1LjcxNiAzNi4xNDZsNS4yNDMtOS41MjFoMTEuMDkzbDUuNDE2IDkuNTIxLTUuNDEgOS4xODVIOTAuOTUzbC01LjIzNy05LjE4NXptNjMuOTA5IDE1LjQ3OWgxMC43NXYxMC43NWgtMTAuNzV6IiBzdHJva2U9IiMwMDAiIHN0cm9rZS13aWR0aD0iMS4yNSIvPjxjaXJjbGUgZmlsbD0iIzAwMCIgY3g9IjcxLjUiIGN5PSI3LjUiIHI9IjEuNSIvPjxjaXJjbGUgZmlsbD0iIzAwMCIgY3g9IjE3MC41IiBjeT0iOTUuNSIgcj0iMS41Ii8+PGNpcmNsZSBmaWxsPSIjMDAwIiBjeD0iODEuNSIgY3k9IjEzNC41IiByPSIxLjUiLz48Y2lyY2xlIGZpbGw9IiMwMDAiIGN4PSIxMy41IiBjeT0iMjMuNSIgcj0iMS41Ii8+PHBhdGggZmlsbD0iIzAwMCIgZD0iTTkzIDcxaDN2M2gtM3ptMzMgODRoM3YzaC0zem0tODUgMThoM3YzaC0zeiIvPjxwYXRoIGQ9Ik0zOS4zODQgNTEuMTIybDUuNzU4LTQuNDU0IDYuNDUzIDQuMjA1LTIuMjk0IDcuMzYzaC03Ljc5bC0yLjEyNy03LjExNHpNMTMwLjE5NSA0LjAzbDEzLjgzIDUuMDYyLTEwLjA5IDcuMDQ4LTMuNzQtMTIuMTF6bS04MyA5NWwxNC44MyA1LjQyOS0xMC44MiA3LjU1Ny00LjAxLTEyLjk4N3pNNS4yMTMgMTYxLjQ5NWwxMS4zMjggMjAuODk3TDIuMjY1IDE4MGwyLjk0OC0xOC41MDV6IiBzdHJva2U9IiMwMDAiIHN0cm9rZS13aWR0aD0iMS4yNSIvPjxwYXRoIGQ9Ik0xNDkuMDUgMTI3LjQ2OHMtLjUxIDIuMTgzLjk5NSAzLjM2NmMxLjU2IDEuMjI2IDguNjQyLTEuODk1IDMuOTY3LTcuNzg1LTIuMzY3LTIuNDc3LTYuNS0zLjIyNi05LjMzIDAtNS4yMDggNS45MzYgMCAxNy41MSAxMS42MSAxMy43MyAxMi40NTgtNi4yNTcgNS42MzMtMjEuNjU2LTUuMDczLTIyLjY1NC02LjYwMi0uNjA2LTE0LjA0MyAxLjc1Ni0xNi4xNTcgMTAuMjY4LTEuNzE4IDYuOTIgMS41ODQgMTcuMzg3IDEyLjQ1IDIwLjQ3NiAxMC44NjYgMy4wOSAxOS4zMzEtNC4zMSAxOS4zMzEtNC4zMSIgc3Ryb2tlPSIjMDAwIiBzdHJva2Utd2lkdGg9IjEuMjUiIHN0cm9rZS1saW5lY2FwPSJyb3VuZCIvPjwvZz48L3N2Zz4=');
            opacity: 0.1;
            top: 0;
            left: 0;
            bottom: 0;
            right: 0;
            height: 100%;
            position: absolute;
            z-index: -1;
        }

        #chat-input {
            background: #f4f7f9;
            width: 100%;
            position: relative;
            height: 47px;
            padding-top: 10px;
            padding-right: 50px;
            padding-bottom: 10px;
            padding-left: 15px;
            border: none;
            resize: none;
            outline: none;
            border: 1px solid #ccc;
            color: #888;
            border-top: none;
            border-bottom-right-radius: 5px;
            border-bottom-left-radius: 5px;
            overflow: hidden;
        }

        .chat-input > form {
            margin-bottom: 0;
        }

        #chat-input::-webkit-input-placeholder { /* Chrome/Opera/Safari */
            color: #ccc;
        }

        #chat-input::-moz-placeholder { /* Firefox 19+ */
            color: #ccc;
        }

        #chat-input:-ms-input-placeholder { /* IE 10+ */
            color: #ccc;
        }

        #chat-input:-moz-placeholder { /* Firefox 18- */
            color: #ccc;
        }

        .chat-submit {
            position: absolute;
            bottom: 3px;
            right: 10px;
            background: transparent;
            box-shadow: none;
            border: none;
            border-radius: 50%;
            color: #5A5EB9;
            width: 35px;
            height: 35px;
        }

        .chat-logs {
            padding: 15px;
            height: 370px;
            overflow-y: scroll;
        }

        .chat-logs::-webkit-scrollbar-track {
            -webkit-box-shadow: inset 0 0 6px rgba(0, 0, 0, 0.3);
            background-color: #F5F5F5;
        }

        .chat-logs::-webkit-scrollbar {
            width: 5px;
            background-color: #F5F5F5;
        }

        .chat-logs::-webkit-scrollbar-thumb {
            background-color: #5A5EB9;
        }


        @media only screen and (max-width: 500px) {
            .chat-logs {
                height: 40vh;
            }
        }

        .chat-msg.user > .msg-avatar img {
            width: 45px;
            height: 45px;
            border-radius: 50%;
            float: left;
            width: 15%;
        }

        .chat-msg.self > .msg-avatar img {
            width: 45px;
            height: 45px;
            border-radius: 50%;
            float: right;
            width: 15%;
        }

        .cm-msg-text {
            background: white;
            padding: 10px 15px 10px 15px;
            color: #666;
            max-width: 75%;
            float: left;
            margin-left: 10px;
            position: relative;
            margin-bottom: 20px;
            border-radius: 30px;
        }

        .chat-msg {
            clear: both;
        }

        .chat-msg.self > .cm-msg-text {
            float: right;
            margin-right: 10px;
            background: #5A5EB9;
            color: white;
        }

        .cm-msg-button > ul > li {
            list-style: none;
            float: left;
            width: 50%;
        }

        .cm-msg-button {
            clear: both;
            margin-bottom: 70px;
        }

</style>


</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

     <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

                        <!-- Sidebar - Brand -->
                        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
                            <div class="sidebar-brand-icon rotate-n-15">
                                <i class="fas fa-laugh-wink"></i>
                            </div>
                            <div class="sidebar-brand-text mx-3">WeWorkEasy</div>
                        </a>

                        <!-- Divider -->
                        <hr class="sidebar-divider my-0">

                        <!-- Nav Item - Dashboard -->
                        <li class="nav-item">
                            <a class="nav-link" href="${context}/member/mypage">
                                <i class="fas fa-user-alt"></i>
                                <span>My Page</span></a>
                        </li>


                        <!-- Nav Item - Pages Collapse Menu -->
                        <li class="nav-item ">
                            <a class="nav-link" href="project/newpro" data-target="#collapseTwo" aria-expanded="true"
                                aria-controls="collapseTwo">
                                <i class="fas fa-home"></i>
                                <span>Main Page</span>
                            </a>
                        </li>


                        <!-- Nav Item - Utilities Collapse Menu -->
                        <li class="nav-item">
                            <a class="nav-link collapsed" href="#" data-toggle="collapse"
                                data-target="#collapseUtilities" aria-expanded="true"
                                aria-controls="collapseUtilities">
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
                            <a class="nav-link collapsed" href="#" data-toggle="collapse"
                                data-target="#collapsePages" aria-expanded="true" aria-controls="collapsePages">
                                <i class="fas fa-fw fa-folder"></i>
                                <span>views</span>
                            </a>
                            <div id="collapsePages" class="collapse" aria-labelledby="headingPages"
                                data-parent="#accordionSidebar">
                                <div class="bg-white py-2 collapse-inner rounded">
                                    <h6 class="collapse-header">view Screens:</h6>
                                    <a class="collapse-item" href="/views/calendar">Calendar</a>
                                    <a class="collapse-item" href="/views/graph">Graph</a>
                                </div>
                            </div>
                        </li>

                        <!-- Nav Item - Charts -->


                        <li class="nav-item">
                            <a class="nav-link collapsed" href="#" data-toggle="collapse"
                                data-target="#collapsePages1" aria-expanded="true" aria-controls="collapsePages">
                                <i class="fas fa-fw fa-folder"></i>
                                <span>Storage</span>
                            </a>
                            <div id="collapsePages1" class="collapse" aria-labelledby="headingPages"
                                data-parent="#accordionSidebar">
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
                <!-- End of Sidebar -->

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- Topbar -->
                <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                    <!-- Sidebar Toggle (Topbar) -->
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>

                    <!-- Topbar Search -->
                    <form
                        class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
                        <div class="input-group">
                            <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..."
                                aria-label="Search" aria-describedby="basic-addon2">
                            <div class="input-group-append">
                                <button class="btn btn-primary" type="button">
                                    <i class="fas fa-search fa-sm"></i>
                                </button>
                            </div>
                        </div>
                    </form>

                    <!-- Topbar Navbar -->
                    <ul class="navbar-nav ml-auto">

                        <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                        <li class="nav-item dropdown no-arrow d-sm-none">
                            <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-search fa-fw"></i>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
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
                            <a class="nav-link dropdown-toggle" href="#" id="alertsDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-bell fa-fw"></i>
                                <!-- Counter - Alerts -->
                                <span class="badge badge-danger badge-counter">3+</span>
                            </a>
                            <!-- Dropdown - Alerts -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown">
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
                            <a class="nav-link dropdown-toggle" href="#" id="messagesDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <i class="fas fa-envelope fa-fw"></i>
                                <!-- Counter - Messages -->
                                <span class="badge badge-danger badge-counter">7</span>
                            </a>
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown">
                                <h6 class="dropdown-header">
                                    Message Center
                                </h6>
                                <a class="dropdown-item d-flex align-items-center" href="#">
                                    <div class="dropdown-list-image mr-3">
                                        <img class="rounded-circle" src="img/undraw_profile_1.svg"
                                            alt="">
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
                                        <img class="rounded-circle" src="img/undraw_profile_2.svg"
                                            alt="">
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
                                        <img class="rounded-circle" src="img/undraw_profile_3.svg"
                                            alt="">
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
                                        <img class="rounded-circle" src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="">
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
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                                data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.user.userName} 님</span>
                                <img class="img-profile rounded-circle"
                                    src="../resources/assets/img/icon/whale.png">
                            </a>
                            <!-- Dropdown - User Information -->
                            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Profile
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-cogs fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Settings
                                </a>
                                <a class="dropdown-item" href="#">
                                    <i class="fas fa-list fa-sm fa-fw mr-2 text-gray-400"></i>
                                    Activity Log
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

        <!-- Begin Page Content -->
   	<div class="wrap">



<!-- Icon Divider-->
<h5 class="page-section-heading text-center text-uppercase text-secondary mb-0">새 프로젝트</h5>
<div class="divider-custom divider-light">
    <div class="divider-custom-line"></div>
    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
    <div class="divider-custom-line"></div>
</div>
<!-- 새 프로젝트 -->
    <div class="wrap">
<!doctype html>
<html lang="en">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>

    <title>NEW PROJECT</title>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Jua&display=swap" rel="stylesheet">
    <style>
        * {
            font-family: 'Jua', sans-serif;
            box-sizing: border-box;
        }

        .due {
            border-color: lightgrey;
            border-radius: 5px;
            width: 50px;
        }

        .new-project {
            all: unset;
            background-color: steelblue;
            color: white;
            padding: 5px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        .modal {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .modal__overlay {
            background-color: rgba(0, 0, 0, 0.6);
            width: 100%;
            height: 100%;
            position: absolute;
        }

        .modal__content {
            background-color: white;
            padding: 50px 100px;
            text-align: center;
            position: relative;
            top: 0px;
            border-radius: 5px;
            width: 50%;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.19), 0 6px 6px rgba(0, 0, 0, 0.23);
        }

        h4 {
            margin: 0;
        }

        .hidden {
            display: none;
        }

        }

        body {
            font: 16px Arial;
        }

        /*the container must be positioned relative:*/
        .autocomplete {
            position: relative;
            display: inline-block;
        }

        input {
            border: 1px solid transparent;
            background-color: #f1f1f1;
            padding: 10px;
            font-size: 16px;
        }

        input[type=text] {
            background-color: #f1f1f1;
            width: 100%;
        }

        input[type=submit] {
            background-color: DodgerBlue;
            color: #fff;
            cursor: pointer;
        }

        .autocomplete-items {
            position: absolute;
            border: 1px solid #d4d4d4;
            border-bottom: none;
            border-top: none;
            z-index: 99;
            /*position the autocomplete items to be the same width as the container:*/
            top: 100%;
            left: 0;
            right: 0;
        }

        .autocomplete-items div {
            padding: 10px;
            cursor: pointer;
            background-color: #fff;
            border-bottom: 1px solid #d4d4d4;
        }

        /*when hovering an item:*/
        .autocomplete-items div:hover {
            background-color: #e9e9e9;
        }

        /*when navigating through the items using the arrow keys:*/
        .autocomplete-active {
            background-color: DodgerBlue !important;
            color: #ffffff;
        }

    </style>

</head>

<body>


<button class="new-project" id="open">프로젝트 추가 +</button>

<div class="modal hidden">
    <div class="modal__overlay"></div>
    <div class="modal__content">
        <div class="header">
            <h2 class="modal-title" id="main-title">New Project</h2>
        </div>

        <hr>

        <div class="body">
            <div class="input-group mb-3">
                <span class="input-group-text">제목</span>
                <input type="text" id="title" class="form-control" aria-label="Sizing example input"
                       aria-describedby="inputGroup-sizing-default">
            </div>


            <!--Make sure the form has the autocomplete function switched off:-->
            <!-- <div class="autocomplete" style="width:300px;">
                <input type="text" name="addMember" id="myInput" placeholder="팀원을 추가하세요!">
            </div>
            <button type="button" onclick="">추가</button> -->


            <div class="input-group mb-3">
                <span class="input-group-text">진행기간</span>
                <div class="col-sm-9">
                   <input type="date" id="deadLine"class="form-control form-control-user h6 rounded"></input>
                </div>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn create-btn" onclick="addProject()">생성</button>
                <button type="button" class="btn cancel-btn">취소</button>
            </div>

        </div>

        <script>
            /* 프로젝트 생성 modal창 */
            const openButton = document.getElementById('open');
            const modal = document.querySelector(".modal");
            const overlay = modal.querySelector(".modal__overlay");
            const closedBtn = modal.querySelector(".cancel-btn");
            const openModal = () => {
                modal.classList.remove("hidden");
            }
            const closeModal = () => {
                modal.classList.add("hidden");
            }

            overlay.addEventListener("click", closeModal);
            closedBtn.addEventListener("click", closeModal);
            openButton.addEventListener('click', openModal);


            /* 생성 버튼 클릭 시 실행 */
            let addProject = () => {

                const url = '/project/newproimpl';

                let paramObj = new Object();

                paramObj.title = $('#title').val();
                paramObj.due = $('#due').val();

                let headerObj = new Headers();
                headerObj.append("content-type", "application/x-www-form-urlencoded");

                fetch(url, {
                    method: "post",
                    headers: headerObj,
                    body: "data=" + JSON.stringify(paramObj)

                }).then(response => {
                    //response.ok : 상태코드 200~290번 사이라면 ok(= true)
                    if (response.ok) {
                        return response.text();
                    }
                    //200번대 코드가 아니라면 에러를 발생시켜서 catch 블록으로 이동
                    throw new AsyncPageErr(response.text());

                }).then((text) => {
                        if (text == 'fail') {
                            document.querySelector('.valid_info')
                                .innerHTML = '프로젝트 생성에 필요한 정보를 모두 입력해 주세요';

                        }else {
                        	location.href = /task/main.jsp;
                        	
                        }
                    }
                ).catch(error => {
                    error.alertMessage();
                })
            }

            /* 팀원 추가 (Autocomplete) */
            function autocomplete(inp, arr) {
                /*the autocomplete function takes two arguments,
                the text field element and an array of possible autocompleted values:*/
                var currentFocus;
                /*execute a function when someone writes in the text field:*/
                inp.addEventListener("input", function (e) {
                    var a, b, i, val = this.value;
                    /*close any already open lists of autocompleted values*/
                    closeAllLists();
                    if (!val) {
                        return false;
                    }
                    currentFocus = -1;
                    /*create a DIV element that will contain the items (values):*/
                    a = document.createElement("DIV");
                    a.setAttribute("id", this.id + "autocomplete-list");
                    a.setAttribute("class", "autocomplete-items");
                    /*append the DIV element as a child of the autocomplete container:*/
                    this.parentNode.appendChild(a);
                    /*for each item in the array...*/
                    for (i = 0; i < arr.length; i++) {
                        /*check if the item starts with the same letters as the text field value:*/
                        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                            /*create a DIV element for each matching element:*/
                            b = document.createElement("DIV");
                            /*make the matching letters bold:*/
                            b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                            b.innerHTML += arr[i].substr(val.length);
                            /*insert a input field that will hold the current array item's value:*/
                            b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                            /*execute a function when someone clicks on the item value (DIV element):*/
                            b.addEventListener("click", function (e) {
                                /*insert the value for the autocomplete text field:*/
                                inp.value = this.getElementsByTagName("input")[0].value;
                                /*close the list of autocompleted values,
                                (or any other open lists of autocompleted values:*/
                                closeAllLists();
                            });
                            a.appendChild(b);
                        }
                    }
                });
                /*execute a function presses a key on the keyboard:*/
                inp.addEventListener("keydown", function (e) {
                    var x = document.getElementById(this.id + "autocomplete-list");
                    if (x) x = x.getElementsByTagName("div");
                    if (e.keyCode == 40) {
                        /*If the arrow DOWN key is pressed,
                        increase the currentFocus variable:*/
                        currentFocus++;
                        /*and and make the current item more visible:*/
                        addActive(x);
                    } else if (e.keyCode == 38) { //up
                        /*If the arrow UP key is pressed,
                        decrease the currentFocus variable:*/
                        currentFocus--;
                        /*and and make the current item more visible:*/
                        addActive(x);
                    } else if (e.keyCode == 13) {
                        /*If the ENTER key is pressed, prevent the form from being submitted,*/
                        e.preventDefault();
                        if (currentFocus > -1) {
                            /*and simulate a click on the "active" item:*/
                            if (x) x[currentFocus].click();
                        }
                    }
                });

                function addActive(x) {
                    /*a function to classify an item as "active":*/
                    if (!x) return false;
                    /*start by removing the "active" class on all items:*/
                    removeActive(x);
                    if (currentFocus >= x.length) currentFocus = 0;
                    if (currentFocus < 0) currentFocus = (x.length - 1);
                    /*add class "autocomplete-active":*/
                    x[currentFocus].classList.add("autocomplete-active");
                }

                function removeActive(x) {
                    /*a function to remove the "active" class from all autocomplete items:*/
                    for (var i = 0; i < x.length; i++) {
                        x[i].classList.remove("autocomplete-active");
                    }
                }

                function closeAllLists(elmnt) {
                    /*close all autocomplete lists in the document,
                    except the one passed as an argument:*/
                    var x = document.getElementsByClassName("autocomplete-items");
                    for (var i = 0; i < x.length; i++) {
                        if (elmnt != x[i] && elmnt != inp) {
                            x[i].parentNode.removeChild(x[i]);
                        }
                    }
                }

                /*execute a function when someone clicks in the document:*/
                document.addEventListener("click", function (e) {
                    closeAllLists(e.target);
                });
            }

            /*An array containing all the country names in the world:*/
            var countries = ["Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central Arfrican Republic", "Chad", "Chile", "China", "Colombia", "Congo", "Cook Islands", "Costa Rica", "Cote D Ivoire", "Croatia", "Cuba", "Curacao", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "French West Indies", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauro", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Pierre & Miquelon", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "St Kitts & Nevis", "St Lucia", "St Vincent", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor L'Este", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks & Caicos", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands (US)", "Yemen", "Zambia", "Zimbabwe"];

            /*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
            autocomplete(document.getElementById("myInput"), countries);

            /* 이전날짜 실행 막기 */
            let today = new Date();
            let year = today.getFullYear();
            let month = ("0" + (1 + today.getMonth())).slice(-2);
            let day = ("0" + today.getDate()).slice(-2);


            today = new Date(year, month - 1, day);
            let tempArr = modDeadLine.split('-');
            let modDate = new Date(tempArr[0], tempArr[1] - 1, tempArr[2]);
            let betweenDay = (modDate.getTime() - today.getTime()) / 1000 / 60 / 60 / 24;

            if (betweenDay < 0) {
                alert("변경할 수 없는 날짜입니다.");
            }
        </script>

</body>
</html>

    </div>
   


<!-- Icon Divider-->
<h5 class="page-section-heading text-center text-uppercase text-secondary mb-0">최근 프로젝트</h5>
<div class="divider-custom divider-light">
    <div class="divider-custom-line"></div>
    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
    <div class="divider-custom-line"></div>
</div>
<!-- 최근 프로젝트 목록 -->
    <div class="card newpro">
        <img class="card-img-top"
             src="data:image/jpeg;base64,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"
             alt="Card image cap">
        <div class="card-body">
            <a href="http://naver.com/" class="card-title"></a>
            <p class="card-text"></p>
            <p class="card-text comment"></p>
        </div>
    </div>
    <div class="card">
        <img class="card-img-top"
             src="data:image/jpeg;base64,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"
             alt="Card image cap">
        <div class="card-body">
            <a href="http://naver.com/" class="card-title"></a>
            <p class="card-text"></p>
            <p class="card-text comment"></p>
        </div>
    </div>
    
    <div class="card">
        <img class="card-img-top"
             src="data:image/jpeg;base64,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"
             alt="Card image cap">
        <div class="card-body">
            <a href="http://naver.com/" class="card-title"></a>
            <p class="card-text"></p>
            <p class="card-text comment"></p>
        </div>
    </div>
    
    
<!-- Icon Divider-->
<h5 class="page-section-heading text-center text-uppercase text-secondary mb-0">초대 프로젝트</h5>
<div class="divider-custom divider-light">
    <div class="divider-custom-line"></div>
    <div class="divider-custom-icon"><i class="fas fa-star"></i></div>
    <div class="divider-custom-line"></div>
</div>    

<!-- 초대 프로젝트 목록 -->
    <div class="card">
        <img class="card-img-top"
             src="data:image/jpeg;base64,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"
             alt="Card image cap">
        <div class="card-body">
            <a href="http://naver.com/" class="card-title"></a>
            <p class="card-text"></p>
            <p class="card-text comment"></p>
        </div>
    </div>
    <div class="card">
        <img class="card-img-top"
             src="data:image/jpeg;base64,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"
             alt="Card image cap">
        <div class="card-body">
            <a href="http://naver.com/" class="card-title"></a>
            <p class="card-text"></p>
            <p class="card-text comment"></p>
        </div>

                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; WWE 2020</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
<div id="chat-circle" class="btn btn-raised">
        <div id="chat-overlay"></div>
        <i class="material-icons">WWE</i>
   		 </div>
   		 
<iframe src="http://localhost:3100" class="chat-box"></iframe>

    
</div>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript-->
    <script src="/resources/vendor/jquery/jquery.min.js"></script>
    <script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/resources/js/sb-admin-2.min.js"></script>

<script>
    $(function () {
        var INDEX = 0;
        $("#chat-submit").click(function (e) {
            e.preventDefault();
            var msg = $("#chat-input").val();
            if (msg.trim() == '') {
                return false;
            }
            generate_message(msg, 'self');
            var buttons = [
                {
                    name: 'Existing User',
                    value: 'existing'
                },
                {
                    name: 'New User',
                    value: 'new'
                }
            ];
        })

        function generate_message(msg, type) {
            INDEX++;
          var str = "";
       /*   str += "<div id='cm-msg-" + INDEX + "' class=\"chat-msg " + type + "\">";
            str += "          <span class=\"msg-avatar\">";
            str += "            <i class="fas fa-search" style="font-size: 1vw; color:black">";
            str += "          <\/span>";
            str += "          <div class=\"cm-msg-text\">";
            str += msg;
            str += "          <\/div>";
            str += "        <\/div>"; */
            
            str = "<iframe src='http://localhost:3100'></iframe>"
            
            $(".chat-logs").append(str);
            
            $("#cm-msg-" + INDEX).hide().fadeIn(300);
            if (type == 'self') {
                $("#chat-input").val('');
            }
            $(".chat-logs").stop().animate({scrollTop: $(".chat-logs")[0].scrollHeight}, 1000);
        }

        function generate_button_message(msg, buttons) {
            /* Buttons should be object array
              [
                {
                  name: 'Existing User',
                  value: 'existing'
                },
                {
                  name: 'New User',
                  value: 'new'
                }
              ]
            */
            INDEX++;
            var btn_obj = buttons.map(function (button) {
                return "              <li class=\"button\"><a href=\"javascript:;\" class=\"btn btn-primary chat-btn\" chat-value=\"" + button.value + "\">" + button.name + "<\/a><\/li>";
            }).join('');
            var str = "";
            str += "<div id='cm-msg-" + INDEX + "' class=\"chat-msg user\">";
            str += "          <span class=\"msg-avatar\">";
            str += "            <img src=\"https:\/\/image.crisp.im\/avatar\/operator\/196af8cc-f6ad-4ef7-afd1-c45d5231387c\/240\/?1483361727745\">";
            str += "          <\/span>";
            str += "          <div class=\"cm-msg-text\">";
            str += msg;
            str += "          <\/div>";
            str += "          <div class=\"cm-msg-button\">";
            str += "            <ul>";
            str += btn_obj;
            str += "            <\/ul>";
            str += "          <\/div>";
            str += "        <\/div>";
            $(".chat-logs").append(str);
            $("#cm-msg-" + INDEX).hide().fadeIn(300);
            $(".chat-logs").stop().animate({scrollTop: $(".chat-logs")[0].scrollHeight}, 1000);
            $("#chat-input").attr("disabled", true);
        }

        $(document).delegate(".chat-btn", "click", function () {
            var value = $(this).attr("chat-value");
            var name = $(this).html();
            $("#chat-input").attr("disabled", false);
            generate_message(name, 'self');
        })

        $("#chat-circle").click(function () {
            $("#chat-circle").toggle('scale');
            $(".chat-box").toggle('scale');
        })

        $(".chat-box-toggle").click(function () {
            $("#chat-circle").toggle('scale');
            $(".chat-box").toggle('scale');
        })

    })
    
    
    /* 프로젝트 생성 */
    		let createProject = ()=>{
            let url = "/project/newproimpl";
            let paramObj = new Object();
            paramObj.title = $('#title').val();
            paramObj.deadline = $('#deadline').val();
			
            paramObj.authority = authorityChk("authority");

            let headerObj = new Headers();
            headerObj.append('content-type', 'application/x-www-form-urlencoded');
            fetch(url,{
                method : "POST",
                headers : headerObj,
                body : "data=" + JSON.stringify(paramObj)
            }).then(response=>{
                //http상태코드가 200~299사이라면 true를 반환
                if(response.ok){
                    return response.text();
                }
                throw new AsyncPageError(response.text());
            }).then(msg=>{
                if(msg=='success'){
		    		location.href(/task/main.jsp);
            }).catch(error=>{
                error.alertMessage();
            });
        }
    

</script>
</body>

</html>s