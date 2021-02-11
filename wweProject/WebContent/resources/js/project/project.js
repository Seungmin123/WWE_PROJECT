
/* 프로젝트 생성 modal창 */
            const openButton = document.getElementById('open');
            const modal = document.querySelector(".modal2");
            const overlay = modal.querySelector(".modal__overlay");
            const closedBtn = modal.querySelector(".cancel-btn");
            const openModal = () => {
                modal.classList.remove("hidden");
            }
            const closeModal = () => {
                modal.classList.add("hidden");
            }
            overlay.addEventListener("click", closeModal);
            closedBtn.addEventListener("click", closeModal);
            openButton.addEventListener('click', openModal);
            
            /* 생성 버튼 클릭 시 실행 */
            let addProject = () => {
                const url = '/project/newproimpl';
                let paramObj = new Object();
                
                paramObj.title = $('#title').val();
                paramObj.deadline = $('#deadline').val();
                
                console.log($('#title').val());
                console.log($('#deadline').val());
                
                let headerObj = new Headers();
                headerObj.append("content-type", "application/x-www-form-urlencoded");
               
                fetch(url, {
                    method: "post",
                    headers: headerObj,
                    body: "data=" + JSON.stringify(paramObj)
                    
                }).then(response => {
                    if (response.ok) {
                        return response.text();
                    }
                    
                }).then((text) => {
                        if (text == 'success') {
                        	location.href = '/task/main';
                        }
                    }
                )
            }

            
            
            
            
            /* 최근 프로젝트를 클릭했을 시 task메인 페이지로 이동 */
            let recentProject = (projectId,userId,workTime) => {
            	
            	let url = '/project/selectpro'; //정보를 요청할 경로
            	let paramObj = new Object();
            	paramObj.projectId = projectId;
            	paramObj.userId = userId; 
            	paramObj.workTime = workTime;
            	
            	console.log(paramObj);
            	
            	let headerObj = new Headers();
            	headerObj.append('content-type', 'application/x-www-form-urlencoded');
            	
            	fetch(url, {
            		method : "post",
            		headers : headerObj,
            		body : "data="+ JSON.stringify(paramObj) //data: 키값
            		
            	}).then(response => {
            		if(response.ok) {
            			return response.text();
            		}else {
            			throw new AsyncPageError(response.text());
            		}
            		
            	}).then((text) => {
            		if(text == 'success') {
            			location.href = '/task/main';
            		}else {
            			alert("프로젝트를 불러오지 못했습니다.")
            		}
            		
            	}).catch((error) => {
            		error.alertMessage();
            	})

            }
            
/*            // 최근프로젝트 개수 제한
            $(function() {
            	var maxAppend = 1; //버튼 누른 횟수 저장
            	
	            	$('#add').click(function() {
	            		if(maxAppend >= 3) 
	            			return; //3번째부터는 append 안되고 return 시키기
	            	maxAppend++;
            	});
            })*/ 
            
            /* 초대된 프로젝트를 클릭했을 시 task메인 페이지로 이동*/
            let invitedProject = (projectId,leaderId) => {
            	
            	let url = '/project/invitedpro'; //정보를 요청할 경로
            	let paramObj = new Object();
            	paramObj.projectId = projectId;
            	paramObj.leaderId = leaderId;
            	
            	let headerObj = new Headers();
            	headerObj.append('content-type', 'application/x-www-form-urlencoded');
            	
            	fetch(url, {
            		method : "post",
            		headers : headerObj,
            		body : "data="+ JSON.stringify(paramObj) //data: 키값
            		
            	}).then(response => {
            		if(response.ok) {
            			return response.text();
            		}else {
            			throw new AsyncPageError(response.text());
            		}
            	}).then((text) => {
            		if(text == 'success') {
            			location.href = '/task/main';
            		}else {
            			alert("프로젝트를 불러오지 못했습니다.")
            		}
            	}).catch((error) => {
            		error.alertMessage();
            	})
            }
            
            
            
            /* 팀원 추가 (Autocomplete) */
            function autocomplete(inp, arr) {
                /*the autocomplete function takes two arguments,
                the text field element and an array of possible autocompleted values:*/
                var currentFocus;
                /*execute a function when someone writes in the text field:*/
                inp.addEventListener("input", function (e) {
                    var a, b, i, val = this.value;
                    /*close any already open lists of autocompleted values*/
                    closeAllLists();
                    if (!val) {
                        return false;
                    }
                    currentFocus = -1;
                    /*create a DIV element that will contain the items (values):*/
                    a = document.createElement("DIV");
                    a.setAttribute("id", this.id + "autocomplete-list");
                    a.setAttribute("class", "autocomplete-items");
                    /*append the DIV element as a child of the autocomplete container:*/
                    this.parentNode.appendChild(a);
                    /*for each item in the array...*/
                    for (i = 0; i < arr.length; i++) {
                        /*check if the item starts with the same letters as the text field value:*/
                        if (arr[i].substr(0, val.length).toUpperCase() == val.toUpperCase()) {
                            /*create a DIV element for each matching element:*/
                            b = document.createElement("DIV");
                            /*make the matching letters bold:*/
                            b.innerHTML = "<strong>" + arr[i].substr(0, val.length) + "</strong>";
                            b.innerHTML += arr[i].substr(val.length);
                            /*insert a input field that will hold the current array item's value:*/
                            b.innerHTML += "<input type='hidden' value='" + arr[i] + "'>";
                            /*execute a function when someone clicks on the item value (DIV element):*/
                            b.addEventListener("click", function (e) {
                                /*insert the value for the autocomplete text field:*/
                                inp.value = this.getElementsByTagName("input")[0].value;
                                /*close the list of autocompleted values,
                                (or any other open lists of autocompleted values:*/
                                closeAllLists();
                            });
                            a.appendChild(b);
                        }
                    }
                });
                /*execute a function presses a key on the keyboard:*/
                inp.addEventListener("keydown", function (e) {
                    var x = document.getElementById(this.id + "autocomplete-list");
                    if (x) x = x.getElementsByTagName("div");
                    if (e.keyCode == 40) {
                        /*If the arrow DOWN key is pressed,
                        increase the currentFocus variable:*/
                        currentFocus++;
                        /*and and make the current item more visible:*/
                        addActive(x);
                    } else if (e.keyCode == 38) { //up
                        /*If the arrow UP key is pressed,
                        decrease the currentFocus variable:*/
                        currentFocus--;
                        /*and and make the current item more visible:*/
                        addActive(x);
                    } else if (e.keyCode == 13) {
                        /*If the ENTER key is pressed, prevent the form from being submitted,*/
                        e.preventDefault();
                        if (currentFocus > -1) {
                            /*and simulate a click on the "active" item:*/
                            if (x) x[currentFocus].click();
                        }
                    }
                });
                function addActive(x) {
                    /*a function to classify an item as "active":*/
                    if (!x) return false;
                    /*start by removing the "active" class on all items:*/
                    removeActive(x);
                    if (currentFocus >= x.length) currentFocus = 0;
                    if (currentFocus < 0) currentFocus = (x.length - 1);
                    /*add class "autocomplete-active":*/
                    x[currentFocus].classList.add("autocomplete-active");
                }
                function removeActive(x) {
                    /*a function to remove the "active" class from all autocomplete items:*/
                    for (var i = 0; i < x.length; i++) {
                        x[i].classList.remove("autocomplete-active");
                    }
                }
                function closeAllLists(elmnt) {
                    /*close all autocomplete lists in the document,
                    except the one passed as an argument:*/
                    var x = document.getElementsByClassName("autocomplete-items");
                    for (var i = 0; i < x.length; i++) {
                        if (elmnt != x[i] && elmnt != inp) {
                            x[i].parentNode.removeChild(x[i]);
                        }
                    }
                }
                /*execute a function when someone clicks in the document:*/
                document.addEventListener("click", function (e) {
                    closeAllLists(e.target);
                });
            }
            /*An array containing all the country names in the world:*/
            var countries = ["Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Anguilla", "Antigua & Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia & Herzegovina", "Botswana", "Brazil", "British Virgin Islands", "Brunei", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central Arfrican Republic", "Chad", "Chile", "China", "Colombia", "Congo", "Cook Islands", "Costa Rica", "Cote D Ivoire", "Croatia", "Cuba", "Curacao", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Polynesia", "French West Indies", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea Bissau", "Guyana", "Haiti", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauro", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "North Korea", "Norway", "Oman", "Pakistan", "Palau", "Palestine", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russia", "Rwanda", "Saint Pierre & Miquelon", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Korea", "South Sudan", "Spain", "Sri Lanka", "St Kitts & Nevis", "St Lucia", "St Vincent", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Timor L'Este", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks & Caicos", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Virgin Islands (US)", "Yemen", "Zambia", "Zimbabwe"];
            /*initiate the autocomplete function on the "myInput" element, and pass along the countries array as possible autocomplete values:*/
            autocomplete(document.getElementById("myInput"), countries);
            
            
//            //이전날짜 실행 막기 
//            let today = new Date();
//            let year = today.getFullYear();
//            let month = ("0" + (1 + today.getMonth())).slice(-2);
//            let day = ("0" + today.getDate()).slice(-2);
//            today = new Date(year, month - 1, day);
//            let tempArr = modDeadLine.split('-');
//            let modDate = new Date(tempArr[0], tempArr[1] - 1, tempArr[2]);
//            let betweenDay = (modDate.getTime() - today.getTime()) / 1000 / 60 / 60 / 24;
//            if (betweenDay < 0) {
//                alert("변경할 수 없는 날짜입니다.");
//            } 