let addTask = () => {
				const url = '/task/addimpl';
				let paramObj = new Object();
				paramObj.taskName = taskName.value;
				paramObj.deadLine = deadLine.value;
				paramObj.taskContent = taskContent.value;
				
				let headerObj = new Headers();
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
						window.alert("내용을 입력해주세요.");
					}else{
						window.alert("업무 추가 성공");
						location.href= "/task/my";
					}
				}).catch(error => {
					error.alertMessage();
				});
	}