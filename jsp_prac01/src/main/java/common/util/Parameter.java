package common.util;

import javax.servlet.http.HttpServletRequest;

public class Parameter {
// 몇 페이지로 이동할지, 한 페이지에 몇 행으로 할지 정하는 클래스임
// 만약 해당 메소드를 사용할 클래스가 여러 개 있다면 아예 클래스를 만들어서 임포트해서 사용할 수 있다.
	public int defaultIntValue(HttpServletRequest request, String paramName, String defValue) {
		// 만약 request를 통해 전달된 "page"라는 속성명을 가진 parameter가 null이라면 defValue(1), 있다면 result = 해당 파라미터 값을 저장
		String result = request.getParameter(paramName) == null ? defValue : request.getParameter(paramName);
		result = result.isEmpty() ? defValue: result;	// 얘는 굳이 왜 설정해뒀는지 이해 안 감
		result = result.matches("\\d+") ? result : defValue;	// 숫자 형식이 맞는지 확인 -> 숫자 형식이 아니라면 defValue로 설정
		return Integer.parseInt(result);	// 만약 맞다면 result 를 형변환해서 전달
		// 페이지 설정 과정 -> 몇 페이지로 설정할지
	}
}
	
