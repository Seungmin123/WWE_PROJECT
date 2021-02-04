/*
	total_task.jsp파일에서 사용하는 js파일
*/

let isChk = $('#wholeChk').data('chk');
let pageIdx = 1;
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
			selectTaskList();
		}else{
			e.className = "paginate_button page-item previous";
		}
	});
	
});

//다음버튼 클릭 시
$(document).on('click','#btn_next',function(e){
	let lastPage;
	document.querySelectorAll('#btn_page').forEach((e,i)=>{
		lastPage = e.getAttribute('idx');
	});
	if(pageIdx!=lastPage){
			pageIdx++;
	document.querySelectorAll('#wrap_btn_page').forEach((e,i)=>{
		if(e.childNodes[0].getAttribute('idx')==pageIdx){
			e.className = "paginate_button page-item previous active";
			document.querySelector('tbody').innerHTML="";
			selectTaskList();
		}else{
			e.className = "paginate_button page-item previous";
		}
	});
	document.querySelector('tbody').innerHTML="";
	selectTaskList();
	}

});

//이전버튼 클릭 시
$(document).on('click','#btn_prev',function(e){
	if(pageIdx!=1){
		pageIdx--;
	document.querySelectorAll('#wrap_btn_page').forEach((e,i)=>{
		if(e.childNodes[0].getAttribute('idx')==pageIdx){
			e.className = "paginate_button page-item previous active";
			document.querySelector('tbody').innerHTML="";
			selectTaskList();
		}else{
			e.className = "paginate_button page-item previous";
		}
	});
	document.querySelector('tbody').innerHTML="";
	selectTaskList();
	}
	
});

//페이지를 새로고침하는 함수
let reloadPage = ()=>{
	location.reload();
}

