// input태그를  checkbox 타입으로 여러개 만들기
// 이전에 배웠던 createElement와 setAttribute, appendChild를 사용한다.


// window.onload : body가 다 로드 되면 실행하도록 만든 것 -> 이렇게 하면 html에 script 위에 넣어도 됨
window.onload = function (){ 
    let month_selects = document.querySelectorAll("select.sel-month");

    for(e of month_selects) {
        createOptionMonth(e);
    }
};

function createOptionMonth (element) {
    for(let i = 1; i <= 12; i++){
        let option = document.createElement("option");
        option.setAttribute("value", i);
        option.innerText = `${ i }월`;
        element.append(option);
    }
}


function createOptionDate(e1, e2) {
    let month = e1.value;
    let date = new Date();
    date.setMonth(month, 0);    // 해당 월의 마지막 일로 세팅해두기
    

    let count = e2.chlidElementCount;
    if(count > 1) {
        let opts = e2.children;
        for(let idx = 1; idx < count; idx++){
            e2.removeChild(opts[1]); // 자바 배열은 0번째를 지우면
            // 0번째 인덱스에의 값은 null이 되고, 다음 인덱스에는 영향x 지만, 얘는 조금 다르다. 
            // 0번째 인덱스를 지우면 다 한칸씩 앞으로 온다. 크기도 줄어든다. 
        }
    }

    for(let d=1; d <= Date.getDate(); d++){
        let option = document.createElement("option");
        option.setAttribute("value", i);
        option.innerText = `${d}일`;
        e2.append(option);
    }
}

// 내가 복습겸 사용해본 createElement, setAttribute, appendChild 
// function newInput(){
//     var div1 = document.getElementById("div1");
    
//     for(let i=0; i < 4; i++) {
//         var input1 = document.createElement("input");
//         input1.setAttribute("type", "checkbox");
//         div1.appendChild(input1);// 자식 요소의 끝부분에 추가한다.
//     }
// }


// 다른 풀이
// function toggleAll (element) { 
// const chk1 = document.getElementsByName('chk1'); // 이렇게 하면 input 배열이 저장됨

//     chk1.forEach( (ch) => {
//         ch.checked = selectAll.checked;
//     });
// }
// 강사님 풀이 
function toggleAll(element) {   
    var name= element.getAttribute("name"); // name 속성값
    console.log(name);  
    var chk_items = document.getElementsByName(name);

    if (element.getAttribute ( "checked" )  === ""){ // 전체선택 체크박스가 선택이 되었는지 확인
        for(e of chk_items) {
            e.removeAttribute("checked");   // checked 속성을 없애라
        }
    }else {
        for(e of chk_items){
            e.setAttribute("checked", ""); // checked 속성을 만들어줘라
        }
    }
}

// 네이버 슬라이드 메뉴바 만들기
function prevMenu(){
    var sliderMenu = document.getElementsByClassName("slide-menu")[0];
    var sliderItems = sliderMenu.children;
    var activeIdx;

    for(let idx = 0; idx < sliderItems.length; idx++){
        let classname = sliderItems[idx].getAttribute("class");
        if(classname.indexOf("active") != -1) {   // active 클래스명을 찾으면 
            sliderItems[idx].setAttribute("class", "slide-item");
            activeIdx = idx + 1;    // 해당 위치에서 오른쪽으로 이동
            break;
        }
    }   
    sliderItems[activeIdx].setAttribute("class", "slider-item active");     // 다음 요소의 class명에 active추가
}



function nextMenu(){
    var sliderMenu = document.getElementsByClassName("slide-menu")[0];
    var sliderItems = sliderMenu.children;
    var activeIdx;

    for(let idx = 0; idx < sliderItems.length; idx++){
        let classname = sliderItems[idx].getAttribute("class");
        if(classname.indexOf("active") != -1) {   // active 클래스명을 찾으면 
            sliderItems[idx].setAttribute("class", "slide-item");
            activeIdx = idx + 1;    // 해당 위치에서 오른쪽으로 이동
            break;
        }
    }   
    sliderItems[activeIdx].setAttribute("class", "slider-item active");     // 클래스명을 바꿔주는 것
}

// 원래 hidden 이었던 걸 hidden을 없애주고, 반대편 요소의 slide-item에 다시 hidden 추가

// 클래스 속성을 변경 하거나, 스타일 속성을 변경하거나! 주로 클래스 속성을 변경한다.
// 스타일 속성을 건드리는 것보다는 클래스 속성을 변경하는 것이 협업에 효율적이다.
