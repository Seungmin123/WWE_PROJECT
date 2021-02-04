<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/include/header.jsp" %>
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
        }

        .due {
            border-color: lightgrey;
            border-radius: 5px;
            width: 100px;
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
            background-color:rgba(0, 0, 0, 0.6);
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
            <input type="text" class="form-control" aria-label="Sizing example input"
                   aria-describedby="inputGroup-sizing-default">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <label class="input-group-text" for="inputGroupSelect01">참여자</label>
            </div>
            <select class="custom-select" id="inputGroupSelect01">
                
                <option value="1">영우</option>
                <option value="2">선민</option>
                <option value="3">승민</option>
                <option value="4">희원</option>
            </select>
        </div>

        <div class="input-group mb-3">
            <span class="input-group-text">진행기간</span>
            <input type="text" class="due start"><span>  ~  </span><input type="text" class="due end">
        </div>

        <div class="modal-footer">
            <button type="button" class="btn create-btn">생성</button>
            <button type="button" class="btn cancel-btn">취소</button>
        </div>

        </div>

        <script>
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
        </script>

</body>
</html>