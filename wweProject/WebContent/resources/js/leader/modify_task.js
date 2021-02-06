let curTidx;
//업무 수정 모달을 띄워주는 함수
let modifyTask = (data)=>{
	$('#task_content_modal').show();
	curTidx = data.getAttribute('tIdx');
	let taskId = data.innerHTML;
	let content = data.getAttribute("taskContent");
	content = content.replace(/<br>|<br\/>|<br \/>/g,'\r\n');
	let curDeadLine = data.getAttribute('deadLine');
	document.querySelector('#task_id').value =taskId;
	document.querySelector('#modified_content').value = content;
	document.querySelector('#deadLine').value = curDeadLine;
	
}

// 업무 수정 모달을 닫는 함수
$('#btn_modify_close').click(function(e) {
	$('#modified_content').val(" ");
	$('#deadLine').val(" ");
	$('#task_content_modal').hide();
});

//업무를 수정하는 함수
let modify = ()=>{
	let modTask =  document.querySelector('#modified_content').value;
	modTask = modTask.replace(/(?:\r\n|\r|\n)/g,'<br>');
	
	let modTaskId = document.querySelector('#task_id').value;
	let modDeadLine	= document.querySelector('#deadLine').value;
	
	let today = new Date();
	let year = today.getFullYear();
	let month = ("0"+(1+today.getMonth())).slice(-2);
	let day = ("0" + today.getDate()).slice(-2);
	
	today = new Date(year, month-1, day);
	let tempArr = modDeadLine.split('-');
	let modDate = new Date(tempArr[0], tempArr[1]-1, tempArr[2]);
	let betweenDay = (modDate.getTime() - today.getTime())/1000/60/60/24;
	
	if(betweenDay<0){
		alert("변경할 수 없는 날짜입니다.");
	}else{
		let url = "/leader/modifytask"
		let paramObj = new Object();
		paramObj.taskId = modTaskId;
		paramObj.deadLine = modDeadLine;
		paramObj.modifiedContent = modTask;
		paramObj.tIdx = curTidx;
	
		let headerObj = new Headers();
		headerObj.append("content-type","application/x-www-form-urlencoded");
	
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
		}).then(msg =>{
			if(msg=='success'){
				alert('업무를 수정했습니다.');
				$('#modified_content').val(" ");
				$('#deadLine').val(" ");
				$('#task_content_modal').hide();
				reloadPage();
			
			}else{
				alert('업무를 수정하지 못했습니다.');			
			}
		}).catch(error=>{
			error.alertMessage();
		});	
	}
	
}