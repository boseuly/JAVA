package common.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Parameter {
	
	public int defaultIntValue(HttpServletRequest request, String paramName, String defValue) {
		String result = request.getParameter(paramName) == null ? defValue : request.getParameter(paramName);
		result = result.isEmpty() ? defValue : result;
		result = result.matches("\\d+") ? result : defValue;
		
		return Integer.parseInt(defValue);
	}

	public int defaultSessionIntValue(HttpServletRequest request, String paramName, String defValue) {
		HttpSession session = request.getSession();
		String defSession = session.getAttribute(paramName) == null ? defValue : session.getAttribute(paramName).toString();	 // 해당 파라미터 정보를 기본값으로 저장
		String result = request.getParameter(paramName) == null ? defValue : request.getParameter(paramName); // 파라미터가 null 일 수도 있으니까 null처리해준다. 있으면 파라미터 값을 저장
		result = result.isEmpty() ? defSession : result;		// 만약 result가 비워져 있으면 defSession을 넣어라	
		result = result.matches("\\d+") ? result : defSession;	// 만약 숫자가 아니라면 defSession 넣기 (원래의 값 그대로)
		
		session.setAttribute(paramName, result);	// 세션에 저장한다. 
		
		return Integer.parseInt(result);
	}
}
