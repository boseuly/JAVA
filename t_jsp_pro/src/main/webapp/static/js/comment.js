/**
 * 
 */
 

window.onload = function (){
	var forms = document.getelegetElementsByTagName("form");
	var inputs = forms[0].getElementsByTagName("input");	// input요소들을 가지고 옴

	inputs.deptId.addEventListener("blur", errorComment);
	inputs.deptName.addEventListener("blur", errorComment);
	inputs.mngId.addEventListener("blur", errorComment);
	inputs.locId.addEventListener("blur", errorComment);
}


function errorComment (e){
	var value = e.target.value;	 // 해당 value 값을 확인 해야 함
	
	// value가 부서아이디, 부서명, 매니저아이디, 지역코드 넷 중 랜덤으로 해당 -> 이걸 어떻게 해결 함..?
	$.ajax({
		type : "post",
		url : "/ajax/error",
		data : value,
		success : function (data, stasus){
			// 해당 값이 
		},
	})
	
	var errorLabel = inputs[0].nextElementSibling;
	errorLabel.innerText = '해당 부서 ID는 이미 존재합니다.';
	
	
};