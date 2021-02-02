	// 팝업 띄우기
		$('#openModalBtn').click(function(e) {
			$('#popup_modal').show();
		});
		// 팝업 닫기
		$('#btn_close').click(function(e) {
            document.querySelector('#idChk').innerHTML ="";
            $('#userId').val(" ");
			$('#popup_modal').hide();
		});

        let idCheck = ()=>{
          let userId = $('#userId').val();
            let headerObj = new Headers();
            console.log(userId);
            headerObj.append('content-type','application/x-www-form-urlencoded');
            if(userId){
                fetch("/leader/chkuser?userId="+userId,{
                    method : "GET",
                    headers : headerObj
                }).then(response=>{
                    if(response.ok){
                        return response.text();
                    }
                    throw new AsyncPageError(response.text());
                }).then((msg)=>{
                    if(msg=='success'){
                       inviteUser();
                    }else{
                        document.querySelector('#idChk').innerHTML = '유효하지 않은 아이디입니다.';
                    }
                }).catch(error=>{
                    error.alertMessage();
                });
            }
        }

		let inviteUser = ()=>{
            let url = "/leader/inviteimpl";
            let paramObj = new Object();
            paramObj.userId = $('#userId').val();
			
            paramObj.authority = authorityChk();

            let headerObj = new Headers();
            headerObj.append('content-type', 'application/x-www-form-urlencoded');
            fetch(url,{
                method : "POST",
                headers : headerObj,
                body : "data=" + JSON.stringify(paramObj)
            }).then(response=>{
                //http상태코드가 200~299사이라면 true를 반환
                if(response.ok){
                    return response.text();
                }
                throw new AsyncPageError(response.text());
            }).then(msg=>{
                if(msg=='success'){
	    		$('#userId').val(" ");
				$('#popup_modal').hide();
                    alert('유저를 초대했습니다.');
                }else{
                    alert('유저를 초대하지 못했습니다.');
                }
            }).catch(error=>{
                error.alertMessage();
            });
        }
        
        let authorityChk = ()=>{
            let radioChk = document.getElementsByName("authority");
            //라디오 버튼이 체크되었나 확인하기 위한 변수
            let radio_btn_chk = 0;
            for(let i = 0; radioChk.length;i++){
                //만약 라디오 버튼이 체크되었으면 true
                if(radioChk[i].checked==true){
                    //라디오 버튼 값 반환
                    return radioChk[i].value;
                }
            }
        }