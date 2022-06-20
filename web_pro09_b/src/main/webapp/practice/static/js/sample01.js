/**
 * 
 */
 // 연습 목적 : jquery , dom 연습
// input을 입력하지 않고 다음 칸으로 넘어가면 메시지를 띄워라
 window.onload = function (){ // 페이지가 실행되면
	initEventHandle();
}
// 함수를 불러서 초기화해주는 메소드
function initEventHandle () {
	blurEventHandle();
	focusEventHandle();
}

// blur
function blurEventHandle(){
	var requiredElements = document.querySelectorAll("[required]");	// 배열
	// 배열에 사용할 수 있는 for of , forEach 문  // 객체에 사용할 수 있는 건 for in문  
	// 요소 하나하나에 이벤트핸들러를 추가해줘야 한다. 
	for(element of requiredElements){ 
		element.addEventListener("blur", InfocheckEventHandle);
	}
	
	// 주어진 input에 작성한 게 있는지 확인
	function InfocheckEventHandle(e) {  // e 매개변수 ->  이벤트 객체(즉 발생한 요소가 누군진 모른다는 것)
		element = e.target; 							// 이벤트가 알고 싶다면 target을 사용한다.
			var message = element.nextElementSibling; // 다음 요소인 메시지 요소를 가지고 와라
		if(!element.value) {	// 만약 element의 값이 없다면
			message.style.display="block";
			message.style.color = "red";
			message.innerText = "필수 정보입니다.";
		}else {
			message.style.display="none";
		}
	}
	
}
// 비밀번호 입력란이 focus 됐을 때
function focusEventHandle() {
	// 비밀번호 입력란
	var password = document.getElementsByClassName("user-input")[1];
	password.addEventListener("focus", passwordInfo);
}

function passwordInfo(e){
	var element = e.target;
	var info = document.getElementsByClassName("info")[0];
	info.style.display = "block";
	info.style.color = "gray";
	
	info.innerText = "패스워드는 10 ~ 32자";
}