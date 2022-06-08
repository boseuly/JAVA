/*
 *	 
 */
 
function f1() {
	return "함수 실행이 완료 되었습니다.";
}
function f2() {
	return 100;
}
function f3(){
	
}
 
var f4 = function() { return "익명함수 입니다."; };	// 변수에 담은 거니까 ;를 찍어줘야 한다.

(function() {
	console.log("즉시 실행되는 익명함수 입니다.");
})();

function f5(x){
	console.log("매개변수 x값 -> " + x);
}
function f6(x, y=0){	// y는 기본값이다. 그래서 ?로 나온다. 생략 가능 매개변수
	console.log("매개변수 x, y 값 -> " + x + ", " + y);
}
function f7(x, ...args){		// args는 배열이다.
	console.log("매개변수 x값 -> " + x );
	for(let arg of args){
		console.log("매개변수 args값 -> " + arg);
	}
}

// 기본값도 사용하고 매개변수도 사용하려면
function f8(x, y=0, ...args) {	// y=0이랑 ...args랑 순서 바뀌면 안 됨
	console.log("매개변수 x,y 값 -> " + x + ", " + y);
	for(let arg of args){
		console.log("매개변수 args값 -> " + arg);
	}
}
// 모든 함수를 호출하면 함수에 내장된 arguments라는 객체가 존재한다.
function f9(x){
	console.log(arguments);
	console.log("매개변수 x값 -> " + x);
}






















