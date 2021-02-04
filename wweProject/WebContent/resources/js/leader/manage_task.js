/*
	total_task.jsp파일에서 사용하는 js파일
*/

let isChk = $('#wholeChk').data('chk');
let pageIdx = 1;
let pageCycle = 3;
let jsonData = 0;
//전체선택 클릭 시
function wohleCheck(){
   if($('#wholeChk').data('chk')==false){
           $('input[name=_selected_').prop('checked',true);
            $('#wholeChk').data('chk',true);
       }else{
           $('input[name=_selected_').prop('checked',false);
            $('#wholeChk').data('chk',false);
       }
   }

//페이지 번호 클릭 시
$(document).on('click','#btn_page',function(event){
	let wrapBtnPages =document.querySelectorAll('#wrap_btn_page');
	wrapBtnPages.forEach((e,i)=>{
		if(e.childNodes[0].getAttribute('idx')==event.target.getAttribute('idx')){
			e.className = "paginate_button page-item previous active";
			pageIdx = event.target.getAttribute('idx');
			document.querySelector('tbody').innerHTML="";
			if(jsonData==0){
				selectTaskList("");
			}else{
				selectTaskList(jsonData);
			}
		}else{
			e.className = "paginate_button page-item previous";
		}
	});
	
});

//다음버튼 클릭 시
let nextBtnClick =(totalPageCount)=>{
	if(pageIdx!=totalPageCount){
		let lastPage;
		document.querySelectorAll('#btn_page').forEach((e,i)=>{
		lastPage = e.getAttribute('idx');
	});
	console.log("totalPageCount : "+totalPageCount);
	console.log(lastPage);
	if(pageIdx<lastPage){
		pageIdx++;
		console.log(pageIdx);
		document.querySelectorAll('#wrap_btn_page').forEach((e,i)=>{
		if(e.childNodes[0].getAttribute('idx')==pageIdx){
			e.className = "paginate_button page-item previous active";
		}else{
			e.className = "paginate_button page-item previous";
		}
		document.querySelector('tbody').innerHTML="";
		if(jsonData==0){
				selectTaskList("");
			}else{
				selectTaskList(jsonData);
			}
	});
	
	}else if(pageIdx==lastPage){
		pageIdx++;
		pageCycle = totalPageCount;
		document.querySelector('#paging_ui').innerHTML = "";
		if(jsonData==0){
					doPaging("");
				}else{
					doPaging(jsonData.length);
				}
		document.querySelectorAll('#wrap_btn_page').forEach((e,i)=>{
		if(e.childNodes[0].getAttribute('idx')==pageIdx){
			e.className = "paginate_button page-item previous active";
		}else{
			e.className = "paginate_button page-item previous";
		}
		document.querySelector('tbody').innerHTML="";
			if(jsonData==0){
				selectTaskList("");
			}else{
				selectTaskList(jsonData);
			}
	});
	}
	}
	
}
//이전버튼 클릭 시
let prevBtnClick = (totalPageCount)=>{
		if(pageIdx!=1){
			let firstPage;
			document.querySelectorAll('#btn_page').forEach((e,i)=>{
				if(i==0){
					firstPage = e.getAttribute('idx');
					return false;
				}
			
			});
			
			if(pageIdx!=firstPage){
				
				pageIdx--;
				document.querySelectorAll('#wrap_btn_page').forEach((e,i)=>{
				if(e.childNodes[0].getAttribute('idx')==pageIdx){
					e.className = "paginate_button page-item previous active";
				}else{
					e.className = "paginate_button page-item previous";
				}
				document.querySelector('tbody').innerHTML="";
				if(jsonData==0){
					selectTaskList("");
						}else{
							selectTaskList(jsonData);
						}
					});
			}else if(pageIdx==firstPage){
				pageCycle = 3;	
				pageIdx = pageIdx - pageCycle;
				document.querySelector('#paging_ui').innerHTML = "";
				if(jsonData==0){
					doPaging("");
				}else{
					doPaging(jsonData.length);
				}
				document.querySelectorAll('#wrap_btn_page').forEach((e,i)=>{
				if(e.childNodes[0].getAttribute('idx')==pageIdx+pageCycle-1){
					e.className = "paginate_button page-item previous active";
					pageIdx = pageIdx+pageCycle-1;
				}else{
					e.className = "paginate_button page-item previous";
				}
				document.querySelector('tbody').innerHTML="";
				if(jsonData==0){
					selectTaskList("");
					}else{
						selectTaskList(jsonData);
					}
				});
			}
		}
	}

//업무명으로 업무목록을 검색
let searchTask = ()=>{
	let word = document.querySelector('#inp_word').value;
	let url = "/leader/search";
	let paramObj = new Object();
	paramObj.projectId = "프로젝트 1";
	paramObj.word = word;
	let headerObj = new Headers();
	headerObj.append('content-type','application/x-www-form-urlencoded');
	if(word){
		fetch(url,{
			method : "POST",
			headers : headerObj,
			body : "data=" + JSON.stringify(paramObj)
		}).then(response=>{
			if(response.ok){
				return response.text();
			}
			throw new AsyncPageError(response.text());
		}).then(msg=>{
			if(msg != 'failed'){
				jsonData = JSON.parse(msg);
				document.querySelector('tbody').innerHTML="";
				document.querySelector('#paging_ui').innerHTML = "";
				selectTaskList(jsonData);
				doPaging(jsonData.length);
			}else{
				alert("조건에 일치하는 업무가 없습니다.");
			}
		}).catch(error=>{
			erorr.alertMessage();
		})
	}
}






//페이지를 새로고침하는 함수
let reloadPage = ()=>{
	location.reload();
}