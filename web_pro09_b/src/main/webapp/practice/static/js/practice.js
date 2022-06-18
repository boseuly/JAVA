const p1 = document.getElementById("p1");
const object = {name: '이보슬', age: 24, gender: '여성'}




// 유효성 검사
function myFunction (){
    const p2 = document.getElementById("p2");
    const input1 = document.getElementById("input1").value;
    let text;
    if(isNaN(input1) || input1 < 1 || input1> 100) {
        text = '1~100 사이의 수를 입력하세요.';
    }else {
        text = '유효성 검사에 통과 하였습니다.';
    }
    p2.innerHTML = text;
}

// 스타일 변경
function styleChange() {
    const p3 = document.getElementById("p3");
    const text = document.getElementById("input2").value;
    
    if(text==='' || !(isNaN)){
        p3.innerHTML = '변경하실 글자색을 입력해주세요.'
    }else {
        p3.style.color = text;
    }
    
}


function sizeChange() {
    const button3 = document.getElementById("button3");
    const div2 = document.getElementById("div2"); 
    if(div2.style.width === '5rem'){
        div2.style.width ="2rem";
        div2.style.height="2rem";

    }else {
        div2.style.width ="5rem";
        div2.style.height="5rem";

    }
}

// child로 노드(요소) 추가하기
const para = document.createElement("p");
const node = document.createTextNode("This is new.");
para.appendChild(node);
const element = document.getElementById("div4");
element.appendChild(para);