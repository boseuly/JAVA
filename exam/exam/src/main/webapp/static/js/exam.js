/**
 * 
 */
 /**
 * 
 */

var id = document.getElementById("user-id");
var pw1 = document.getElementById("user-pw1");
var pw2 = document.getElementById("user-pw2");

var message1 = document.getElementById("message1");
var message2  = document.getElementById("message2");
var message3 = document.getElementById("message3");

//해당 input을 선택했을 때 해당 input의 위에 형제가 만약 값이 비어있다면
function warning(kind) {
    kind.addEventListener("click", function (e) { // 클릭을 했을 때 다른 요소가 비어있나?
        console.log(kind);
        if(kind.id === 'user-id'){
            if(pw1.value === '' && !(pw2.value === '')) {
                message2.innerHTML = "필수 입력 항목 입니다";
                message3.innerHTML =""; // 만약 얘는 값이 있으면 빈값으로
            }else if(pw2.value === '' && !(pw1.value === '')){
                message3.innerHTML = "필수 입력 항목 입니다";
                message1.innerHTML =""; // 만약 얘는 값이 있으면 빈값으로
            }else if(pw1.value === '' && pw2.value === ''){
                message2.innerHTML = "필수 입력 항목 입니다";
                message3.innerHTML = "필수 입력 항목 입니다";
            }else {
                message2.innerHTML =""
                message3.innerHTML =""
            }
            
        }else if(kind.id === 'user-pw1') {
            message2.innerHTML = "영문자 대/소문자 특수문자, 숫자 포함 8 ~ 32자";
            if(id.value === '' && !(pw2.value === '')) {
                message1.innerHTML = "필수 입력 항목 입니다";
                message2.innerHTML ="";     // 만약 얘는 값이 있으면 빈값으로
            }else if(pw2.value === '' && !(id.value === '')){
                message3.innerHTML = "필수 입력 항목 입니다";
                message1.innerHTML =""; // 만약 얘는 값이 있으면 빈값으로
            }else if(pw2.value === '' && id.value === ''){
                message1.innerHTML = "필수 입력 항목 입니다";
                message3.innerHTML = "필수 입력 항목 입니다";
            }else {
                message1.innerHTML = "";
                message3.innerHTML= "";
            }
        }else if(kind.id === 'user-pw2') {
            if(id.value === "" && !(pw1.value === "")) {
                message1.innerHTML = "필수 입력 항목 입니다";
                message2.innerHTML =""; // 만약 얘는 값이 있으면 빈값으로
            }else if(pw1.value === '' && !(id.value === '')) {
                message2.innerHTML = "필수 입력 항목 입니다";
                message1.innerHTML =""; // 만약 얘는 값이 있으면 빈값으로
            }else if(pw1.value === '' && id.value === ''){
                message1.innerHTML = "필수 입력 항목 입니다";
                message2.innerHTML = "필수 입력 항목 입니다";
            }else {
                message1.innerHTML = "";
                message2.innerHTML = "";
            }
        }
    })
}


warning(id);
warning(pw1);
warning(pw2);

 