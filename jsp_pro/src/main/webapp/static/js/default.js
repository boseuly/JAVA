/**
 * 
 */
 function duplicateCheck(element, url){ // this를 전달 받음 -> 해당 element 요소
	sendElementDataValid(element, url);
}
function existsCheck(element, url){	
	sendElementDataValid(element, url); // 서로 url이 달라서 따로 해줌 
}
 
function sendElementDataValid(element, url){
	$.ajax({
		type: "get", 
		url : url,
		data : {
			name : element.name,
			value : element.value
		},
		success : function (data, status){
			setLabelState(element.nextElementSibling, data.message);
		},
		complete : function (){ // 성공하든 실패하든 무조건 실행
			// 만약 전달 받은 element의 값이 빈값 또는 알 수 없는 글이라면 -> 빈문자열을 보낼 수도 있으니까 
			if(element.value === "" || element.value === undefined){ 
				element.nextElementSibling.innerText = ""; // 그럴 때는 에러 메시지를 보내지 않는다. 
			}
		}
	});
}
// 서버로부터 데이터를 전달 받고 해야할 것 -> success인 경우
function setLabelState(element, message){
	element.innerText = message;
}