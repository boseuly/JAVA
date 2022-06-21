/**
 * 
 */
 window.onload = function() {
	initEventHandle();
}

function initEventHandle() {
	requiredCheckHandle();
	passwordInfoHandle();
}

function requiredCheckHandle() { // 이벤트 핸들러 추가해주는 메소드
	var requiredElements = document.querySelectorAll("[required]"); //required가 들어가는 모든 요소 찾기 -> 범용성을 이용
	for(element of requiredElements){ // 요소들을 element에 하나씩 넣어준다.
		element.addEventListener("blur", requiredCheck); // requiredCheck메소드를 넣어준다.  이때 주의할 점 -> requiredCheck() 이런 식으로 넣어주면 안 됨 
																									//- > 포커스가 풀렸을 때 실행되어야 하는데 바로 실행해버려서
	}
}

function passwordInfoHandle() { // 포커스가 된 경우 -> 이벤트 핸들러
	var requiredElements = document.querySelectorAll("[required]");
	for(element of requiredElements){
		element.addEventListener("focus", passwordInfo); // info 보다는 focus 사용하기
	}
}

function requiredCheck(e) { // e 매개변수 ->  이벤트 객체(즉 발생한 요소가 누군진 모른다는 것)
	/*console.log(e.target); /* target : 이벤트가 발생한 요소가 누구인지 알 수 있다.*/
	element = e.target;
	messageControl = element.parentElement.nextElementSibling; // nextElementSibling : 같은 노드 레벨의 다음 요소를 가져온다.
	messageLabel = messageControl.getElementsByClassName("message-label")[0]; // messageControl 요소(여기서는id=message-control인 div)의 안에서  
	
	if(!element.value){ // element에 내용이 없다면
			messageControl.style.display = "inline-block";
			messageLabel.innerText = "필수 입력 항목 입니다.";
	}else {
		messageControl.style.display="none";
		messageLabel.innerText = "";
	}
}
function passwordInfo () {
	element = e.target;
	messageControl = element.parentElement.nextElementSibling;
	messageLabel = messageControl.getElementsByClassName("message-label")[0];
	
	messageControl.style.display = "inline-block";
	messageLabel.innerText = "영문자 대/소문자 특수문자, 숫자 포함 8 ~ 32자";
	messageLabel.style.color = "gray";
	
	element.addEventListener("blur" , function(e) {
		messageLabel.style.color = "";
	})
}
