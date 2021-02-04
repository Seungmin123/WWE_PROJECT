let feedBack = () => {
				const url = '/task/insertfeedback';
				let paramObj = new Object();
				paramObj.nick = nick.value;
				paramObj.feedbackComment = feedbackComment.value;
				
				alert(nick);
				alert(feedbackComment);
				
				let headerObj = new Headers();
				headerObj.append('content-type', 'application/x-www-form-urlencoded');
				
				fetch(url, {
					
					method : "post",
					headers : headerObj,		
					body:"feedback="+ JSON.stringify(paramObj)

				}).then(response => {
					//response.ok : 상태코드 200~209 사이라면 ok = true
					if(response.ok){
						return response.text();
					}	
					//200번대 코드가 아니라면 에러를 발생시켜서 catch 블록으로 이동
				})	
				.then((text) => {
					if(text == "fail"){
						window.alert("내용을 입력해주세요.");
					}else{
						window.alert("피드백 등록완료");
						document.querySelector('.test').innerHTML = `${nick} : ${feedbackComment}`;
						document.querySelector('#nick').innerHTML = "";
						document.querySelector('#feedbackComment').innerHTML = "";
						
					}
				}).catch(error => {
					error.alertMessage();
				});
	}
	
	
	
	