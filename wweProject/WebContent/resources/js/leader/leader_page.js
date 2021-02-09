let selectUser;
let openAllocModal = (data)=>{
	$('#alloc_task_modal').show();
	selectUser = data.innerText;
}

$('#btn_allow_modal_close').click(function(e) {
			$('#alloc_task_modal').hide();
});
		

let allocTask = ()=>{
	let taskId = document.querySelector('#task_id').value;
	let deadLine = document.querySelector('#deadLine').value;
	let content = document.querySelector('#task_content').value;
	let projectId = '프로젝트 1';
		
	if(taskId!="" && deadLine!="" && content !=""){
		let today = new Date();
		let year = today.getFullYear();
		let month = ("0" + (1 + today.getMonth())).slice(-2);
		let day = ("0" + today.getDate()).slice(-2);


		today = new Date(year, month - 1, day);
		let tempArr = deadLine.split('-');
		let selectDate = new Date(tempArr[0], tempArr[1] - 1, tempArr[2]);
		let betweenDay = (selectDate.getTime() - today.getTime()) / 1000 / 60 / 60 / 24;

		if (betweenDay < 0) {
			alert("변경할 수 없는 날짜입니다.");
		}else{
					url = "/leader/alloctask";
		let paramObj = new Object();
		let headerObj = new Headers();
		paramObj.taskId = taskId;
		paramObj.deadLine = deadLine;
		paramObj.content = content;
		paramObj.projectId = projectId;
		paramObj.userId = selectUser;
		
		headerObj.append('content-type','application/x-www-form-urlencoded');
		
		fetch(url,{
			method : "POST",
			headers : headerObj,
			body : "data="+JSON.stringify(paramObj)
		}).then(response =>{
			if(response.ok){
				return response.text();
			}else{
				throw new AsyncPageError(response.text());
			}
		}).then(msg=>{
			if(msg=='success'){
				alert('팀원에게 업무를 할당했습니다.');
				document.querySelector('#task_id').value="";
				document.querySelector('#deadLine').value="";
				document.querySelector('#task_content').value="";
			}else{
				alet('팀원에게 업무를 할당하지 못했습니다.');
			}
			$('#alloc_task_modal').hide();
		}).catch(error=>{
			error.alertMessage();
		});
		}

	}else{
		alert('정보를 정확히 입력해주세요.');
	}
}	
		