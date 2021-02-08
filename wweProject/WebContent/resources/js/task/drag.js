// my list 드래그 드롭기능
function onDragStart(event){
	event.dataTransfer.setData('text/plain',event.target.id);
	event.currentTarget.style.backgroundColor = 'gray';
}
        	
function onDragOver(event){
	event.preventDefault();
}
        	
function onDrop(event){
	const id = event.dataTransfer.getData('text');
        		
	const draggableElement = document.getElementById(id);
	const dropzone = event.target;
        		
	dropzone.appendChild(draggableElement);
	
	//드래그 대상에서 이동할 데이터 삭제
    draggableElement.parentNode.removeChild(dropzone);
   
    //드롭 대상에 데이터 추가
    event.currentTarget.appendChild(dropzone);
   
    //드롭 완료 후 이벤트 버블링을 막기 위해 호출           
    event.stopPropagation();
 		
    event.dataTransfer.clearData();
}

//taskId랑 userId도 같이 보내야하잖아...? 어떻게해...

let updateState = ()=>{
	let state = 'ST01';
	
	const url = "/task/updateState"
	let paramObj = new Object();
	paramObj.state = state;
	let headerObj = new Headers();
	headerObj.append('content-type','application/x-www-form-urlencoded');
	
	fetch(url,{
		method : "POST",
		headers : headerObj,
		body : "data="+JSON.stringify(paramObj)
	}).then(response=>{
		if(response.ok){
			return response.text();
		}else{
			throw new AsyncPageError(response.text());
		}
	}).then(msg =>{
		if(msg=='success'){
			console.dir('수정완료');
		}else{
			console.dir('수정실패');
		}
	}).catch(error=>{
		error.alertMessage();
	});
}