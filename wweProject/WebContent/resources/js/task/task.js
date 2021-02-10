let feedBack = ()=>{
	
	const url = "/task/insertfeedback"
	let paramObj = new Object();
	paramObj.userId = document.querySelector('#userId').innerHTML;
	paramObj.feedbackComment = feedbackComment.value;
	paramObj.taskId = document.querySelector('#taskId').innerHTML;

	alert(document.querySelector('#taskId').innerHTML);
	alert(document.querySelector('#userId').innerHTML);
	alert(feedbackComment.value);
	
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
			console.dir('완료');
			document.querySelector('#feedbackComment') = '';
		}else{
			console.dir('실패');
		}
	}).catch(error=>{
		error.alertMessage();
	});
}
	
let calendar = () => {
	
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
   }
}
	
	
	
	