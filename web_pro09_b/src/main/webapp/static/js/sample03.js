/**
 * 
 */
var student = {
	name: "홍길동", 
	age: 23, 
	gender: "M"
};
 

function f1(){
	var res1 = document.getElementById("res1");
	res1.innerHTML += "var student = {" + "<br>"
	res1.innerHTML += "		name: '홍길동'," + "<br>"
	res1.innerHTML += "		age : 23," + "<br>"	
	res1.innerHTML += "		gender: 'M'" + "<br>"
	res1.innerHTML += "};"
}


function f2() {
	var res2 = document.getElementById("res2");
	res2.innerHTML += "student -> " + student + "<br>";
	res2.innerHTML += "student['name'] -> " + student['name'] + "<br>";
	res2.innerHTML += "student.name -> " + student.name + "<br>";
	
}

// for(..in..) 반복문을 사용해야 객체의 값이 제대로 나온다.
function f2_1() {
	var arr;
	var res2_1 = document.getElementById("res2_1"); 
	for(let stu in student){		// student의 속성명(name, age, gender)을 stu에 차례대로 하나씩 다 넣어준다.
		let value = student[stu];
		// 문자열이면 '' 들어가게 하기
		if(typeof(value) === 'string'){
			value = "'" + value + "'";	// +=인지 = 인지 헷갈림
		}
		arr.push(stu + ":" + value);
	};
	res3.innerHTML += "student -> "
	res3.innerHTML += "{" + arr.join(", ") + "}";
	res3.innerHTML += "student['name'] -> " + student['name'] + "<br>";
	res3.innerHTML += "student.name -> " + student.name + "<br>";  
}

function f3(){
	//input 태그에 입력된 정보를 추출하여 stock 객체 생성하기
	// 만약 입력된 값이 없으면, res3에 값을 입력하라는 메시지 출력
	
	var res3 = document.getElementById("res3");	// 내가 글을 작성할 위치 지정
	var inName = document.getElementById("id_input1_name");
	var inCount = document.getElementById("id_input1_count");
	var inPrice = document.getElementById("id_input1_price");
	// input 내용들을 name, count, price 에 각각 넣어줘야 한다.
	
	if(!inName.value){
		res3.innerHTML = "주식명을 입력해주세요."
		inName.focus();		// focus() 를 해주면 입력창이 진해진다.
		return;
	}
	if(!inCount.value){
		res3.innerHTML = "주식수량을 입력해주세요."
		inCount.focus();
		return;
	}
	if(!inPrice.value){
		res3.innerHTML = "주식금액을 입력해주세요."
		inPrice.focus();
		return;
	}
	
	var data = genStock(inName.value,inCount.value,inPrice.value);	// genStock의 return 값이 stock임
	res3.innerHTML = print(data);
}

// 객체 속성의 값을 변경해준다.
function f4(){
	var name = document.getElementById("id_input2_name");
	var value = document.getElementById("id_input2_value");
	var res4 = document.getElementById("res4");
	
	if(!name.value){
		res4.innerHTML = "속성명을 입력해 주세요."
		name.focus();
		return;
	}
	if(!value.value){
		res4.innerHTML = "속성값을 입력해 주세요."
		value.focus();
		return ;
	}
	student[name.value] = value.value;	// 객체 속성 값 변경
	res4.innerHTML = print(student);	
}


function genStock(name, count, price){
	var stock = {
		name: name, 
		count: count,
		price: price
	}
	return stock;
}




// 문제 1 : 속성값을 입력받은 뒤 사각형의 속성값을 res4영역에 출력해라
function f5() {
	var res5 = document.getElementById("res5");	// res4의 영역을 가져온다.
	var width = document.getElementById("input_width");	// 너비 가져오기
	var height = document.getElementById("input_height");
	var color = document.getElementById("input_color");
	
	
	// 변수에 입력받은 값을 넣어주기(매개변수를 통해서)
	var square = makeSquare(width.value, height.value, color.value);	// .value라고 안해주면 정확한 값이 안 나온다.
	res5.innerHTML = print(square);
	
	var element = document.createElement("div");	// div 요소를 추가하겠다.
	element.style.width = square.width + "px";
	element.style.height = square.height + "px";
	element.style.backgroundColor = square.color;
	element.style.width = square.width + "px";
	element.innerHTML = "이 사각형의 면적은 " + square.getArea() + "입니다.";
	res5.appendChild(element);
	// 항상 추가를 해주는데 , 마지막에(끝에 이어서) 추가를 한다.
	
/*	<복잡한 방식>
	var element = "";
	element += "<div"
	element += " style='display:inline-block;";
	element += "width:" + square.width + "px;";
	element += "height:" + square.height + "px;";
	element += "background-color:" + square.color + ";'>";
	element += "면적이 " + square.getArea() + "인 사각형 입니다.";
	element += "</div>"
	res5.innerHTML = element;		// innerHTML 은 브라우저에 HTML로 값을 가져오는 거고 innerTEXT는 내가 작성한 식을 TEXT로 브라우저에 띄어준다.
*/
}


function makeSquare(width=1, height=1, color='black') {	// 사각형을 만들어주는 함수
	var square = {
		width: width,
		height: height,
		color: color,
		getArea: ()=> {				// 해당 매소드의 return 값을 getArea 에 넣는다.
			return width *  height;		// this. 붙이면 구체적인값이 안 나온다. (화살표 메서드 말고 그냥 메서드도 사용 가능)
		}
	}
	return square;
}



function print(data){
	var arr = new Array();
	console.log(data);		
	for(let d in data){		// data의 속성명을 차례대로 하나씩 d에 넣어준다. (for문이 한번 돌면 다음 속성명)
		let value = data[d];
		
		if(typeof(value) === 'string'){	// 만약 d가 문자열이라면
			value = "'" + value + "'";	
		}else if(typeof(value) === 'function'){
			value = "'" + value() + "'";
		}
		arr.push(d + ": " + value);
	};
	return "{" + arr.join(", ") + "}";
}

