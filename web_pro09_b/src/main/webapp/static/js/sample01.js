/*배열
 * 
 */
 
var arr1 = [1,2,3];
var res1 = document.getElementById("res1");
res1.innerHTML += arr1 + "<br>";					// 문자열 더하는 것과 비슷 
res1.innerHTML += "arr1[0] -> " + arr1[0] + "<br>";	
res1.innerHTML += "arr1[1] -> " + arr1[1] + "<br>";
res1.innerHTML += "arr1[2] -> " + arr1[2] + "<br>";

var res2 = document.getElementById("res2");
arr1[0] = 10;
arr1[1] = 20;
arr1[2] = 30;
res1.innerHTML += arr1 + "<br>";

// 배열에서 제공하는 함수

res1.innerHTML += "arr1.indexOf(10) -> " + arr1.indexOf(10) + "<br>";
res1.innerHTML += "arr1.indexOf(20) -> " + arr1.indexOf(20) + "<br>";
res1.innerHTML += "arr1.indexOf(30) -> " + arr1.indexOf(30) + "<br>";

// push : 배열에 값 추가하기. 뒤에서 이어서 추가가 됨.
res1.innerHTML += "arr1.push(40)" + "<br>";
arr1.push(40);
res1.innerHTML += "arr1.push(50)" + "<br>";
arr1.push(50);
res1.innerHTML += "arr1.push(60)" + "<br>";
arr1.push(60);
res1.innerHTML += arr1 + "<br>";

// nushift() : 앞에서 이어서 추가
res1.innerHTML += "arr1.nushift(0)" + "<br>";
arr1.unshift(0);
res1.innerHTML += arr1 + "<br>";

// pop : 
res1.innerHTML += "arr1.pop()" + "<br>";

/*
 	다음의 input 요소에 있는 값을 배열로 만들어 exam1 에 출력
	출력형식은 ['a','b','c','d','e'] 와 같이 대괄호 안에 값이 출력되도록 한다.
 */

var arr2;		// 얘는 그냥 split(",")의 결과를 넣기 위한 변수
var inputArr = document.getElementById("id_input1").value;
var exam = document.getElementById("exam1");
arr2 = inputArr.split(", "); // 1 2 3 

for(var i =0; i < arr2.length; i++){
	arr2[i] = arr2[i].trim();			// 공백 제거
}
exam.innerHTML = "['" + arr2.join("', '") + "']";

/*
	다음의 input 요소에 있는 값을 문제1에서 만든 배열에 추가 후 출력<br>
	출력 형식은 문제1과 동일하다. <br>
	추가로 input 요소에 있는 값의 총 합을 구하여 배열에 추가도 한다.
 */

var inputArr2 = document.getElementById("id_input2").value;
var exam1 = document.getElementById("exam2");
var tmp = inputArr2.split(",");
var total=0;

for(let item of tmp){
	item = parseInt(item);
	total += item;
	arr2.push(item);
}
arr2.push(total);
exam2.innerHTML = "['" + arr2.join("','") + "']";




























