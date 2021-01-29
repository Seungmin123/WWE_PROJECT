	/**
	 * 
	 */

	let login = () => {
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
						location.href= "/member/signup";
					}
				}).catch(error => {
					error.alertMessage();
				});
	}
	
	