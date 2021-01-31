	/**
	 * 
	 */

	let submitCheck = false; 

	let check_pw = () => {
        let pw = document.getElementById('userPW').value;
        let pw2 = document.getElementById('userPW2').value;
                                        	
                                        	
        if(pw == pw2){
        	document.getElementById('check').innerHTML = '비밀번호가 일치합니다.';
			document.getElementById('check').style.color= 'blue';
			submitCheck = true;
        }else{
            document.getElementById('check').innerHTML = '비밀번호가 일치하지 않습니다.';
			document.getElementById('check').style.color= 'red';
			submitCheck = false;
        }
                                        	
	}

	let hideDiv = function(divID){
		document.getElementById(divID).style.display = 'none';
		document.getElementById(divID).style.visibility = 'hidden';
		
	}

	let showDiv = function(divID){
		document.getElementById(divID).style.display = 'block';
		document.getElementById(divID).style.visibility = 'visible';
	}

	let setDivFirstSet = () => {
		hideDiv('whale-LeftPic');
		hideDiv('whale-RightID');
		hideDiv('whale-RightPW');
		
		showDiv('whale-LeftFirstSet');
		showDiv('whale-RightFirstSet');
	}

	let hideLeftRight = () => {

		hideDiv('whale-LeftFirstSet');
		showDiv('whale-LeftPic');
		hideDiv('whale-RightFirstSet');

		}


	let showFindID = () => {

		hideLeftRight();
		showDiv('whale-RightID');
		hideDiv('whale-RightPW');

		}

	let showFindPW = () => {
		
		hideLeftRight();
		showDiv('whale-RightPW');
		hideDiv('whale-RightID');

		}
	

	let signin = () => {
				const url = '/member/signinimpl';
				let paramObj = new Object();
				paramObj.userID = userID.value;
				paramObj.userPW = userPW.value;


				let headerObj = new Headers();
				//headerObj.append('content-type', 'application/json');
				headerObj.append('content-type', 'application/x-www-form-urlencoded');
				
				fetch(url, {
					
					method : "post",
					headers : headerObj,		
					body:"data="+ JSON.stringify(paramObj)

				}).then(response => {
					//response.ok : 상태코드 200~209 사이라면 ok = true
					if(response.ok){
						return response.text();
					}	
					//200번대 코드가 아니라면 에러를 발생시켜서 catch 블록으로 이동
					
							
				})	
				.then((text) => {
					if(text == "fail"){
						window.alert("ID / PW를 확인하세요");
					}else{
						
						//location.href = "/index";
						location.href= "/member/mypage";
					}
				}).catch(error => {
					error.alertMessage();
				});
	}

	let signup = () => {
				const url = '/member/signupimpl';
				let paramObj = new Object();
				paramObj.userID = userID.value;
				paramObj.userPW = userPW.value;
				paramObj.userEmail = userEmail.value;
				paramObj.userName = userName.value;
				paramObj.userAdd = userAdd.value;
				paramObj.userTell = userTell.value;
				paramObj.userBirth = userBirth.value;

				let headerObj = new Headers();
				//headerObj.append('content-type', 'application/json');
				headerObj.append('content-type', 'application/x-www-form-urlencoded');
				
				if(submitCheck = false){
					return;
				}

				fetch(url, {
					
					method : "post",
					headers : headerObj,		
					body:"data="+ JSON.stringify(paramObj)

				}).then(response => {
					//response.ok : 상태코드 200~209 사이라면 ok = true
					if(response.ok){
						return response.text();
					}	
					//200번대 코드가 아니라면 에러를 발생시켜서 catch 블록으로 이동
					
							
				})	
				.then((text) => {
					if(text == "fail"){
						window.alert("ID / PW를 확인하세요");
					}else{
						
						//location.href = "/index";
						location.href= "/member/signin";
					}
				}).catch(error => {
					error.alertMessage();
				});
	}

	let findID = () => {
				const url = '/member/findidimpl';
				let paramObj = new Object();
				paramObj.userEmail = userEmailForID.value;

				let headerObj = new Headers();
				//headerObj.append('content-type', 'application/json');
				headerObj.append('content-type', 'application/x-www-form-urlencoded');

				fetch(url, {
					
					method : "post",
					headers : headerObj,		
					body:"data="+ JSON.stringify(paramObj)
		
				}).then(response => {
					//response.ok : 상태코드 200~209 사이라면 ok = true
					if(response.ok){
						return response.text();
					}	
					//200번대 코드가 아니라면 에러를 발생시켜서 catch 블록으로 이동
					
							
				})	
				.then((text) => {
					if(text == "fail"){
						window.alert("ID / PW를 확인하세요");
					}else{
						
						//location.href = "/index";
						location.href= "/member/findresult";
					}
				}).catch(error => {
					error.alertMessage();
				});
	}

	let findPW = () => {

				const url = '/member/findpwimpl';
				let paramObj = new Object();
				paramObj.userID = userID.value;
				paramObj.userEmail = userEmailForPW.value;
				

				let headerObj = new Headers();
				//headerObj.append('content-type', 'application/json');
				headerObj.append('content-type', 'application/x-www-form-urlencoded');
				
				fetch(url, {
					
					method : "post",
					headers : headerObj,		
					body:"data="+ JSON.stringify(paramObj)

				}).then(response => {
					//response.ok : 상태코드 200~209 사이라면 ok = true
					if(response.ok){
						return response.text();
					}	
					//200번대 코드가 아니라면 에러를 발생시켜서 catch 블록으로 이동
					
							
				})	
				.then((text) => {
					if(text == "fail"){
						window.alert("ID / PW를 확인하세요");
					}else{
						
						//location.href = "/index";
						location.href= "/member/findresult";
					}
				}).catch(error => {
					error.alertMessage();
				});
	}

	

	
	
	