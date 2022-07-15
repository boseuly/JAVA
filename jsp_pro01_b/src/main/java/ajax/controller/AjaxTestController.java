package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import emps.model.EmpDTO;

@WebServlet("/ajax/test")
public class AjaxTestController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		
		System.out.println("x : " + x);
		System.out.println("y : " + y);
		
		
		// json 라이브러리
		/* 문자열과 숫자 등의 타입을 전달하고 싶다면
		PrintWriter out = response.getWriter(); // 출력을 하기 위한 객체
		JsonFactory factory = new JsonFactory();
		StringWriter sw = new StringWriter();
		JsonGenerator jg = factory.createGenerator(sw);
		jg.useDefaultPrettyPrinter();	// 출력할 때 보기 좋은 형식으로 출력해줌 
		jg.writeStartObject();			// 객체를 쓰기 위한 준비를 하겠다. 
		
		jg.writeFieldName("msg");	// 필드명
		jg.writeString("Hello");	// 해당 필드의 데이터값 
		
		jg.writeFieldName("kor");
		jg.writeString("안녕하세요");
		
		jg.writeFieldName("x");
		jg.writeNumber(100);		// 원하는 자바 객체 타입과 맞는 메소드를 찾아서 넣어주면 된다.
		
		jg.writeEndObject();
		jg.close();
		
		System.out.println(sw.toString());	// 필드가 문자열이기 때문에 tostring
		out.print(sw.toString());
		out.flush();
		*/
		
		// 객체를 전달하고 싶다면 (이게 더 수월함)		
		PrintWriter out = response.getWriter(); // 출력을 하기 위한 객체
		ObjectMapper om = new ObjectMapper();
/*
 		EmpDTO data = new EmpDTO();
 
		
		data.setEmpId(100);
		data.setEmpName("json");
		data.setEmail("JSON@emp.com");
		data.setDeptId(100);
		data.setDeptName("제이선부");
		data.setJobId("code");
		data.setJobName("json code");
*/		
		// List를 만들어서 전달하는 경우
		List<EmpDTO> datas =  new ArrayList<EmpDTO>();
 
		for(int i =0; i < 5; i++) {
			EmpDTO data = new EmpDTO();
			data.setEmpId(100+i);
			data.setEmpName("json");
			data.setEmail("JSON@emp.com");
			data.setDeptId(100);
			data.setDeptName("제이선부");
			data.setJobId("code");
			data.setJobName("json code");
			datas.add(data);
		}
		om.writeValue(out, datas);
		
		// 어떤 타입으로 보낼지 반드시 작성해줘야 한다. -> 지금은 json 타입
		response.setContentType("application/json; charset=utf-8");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}
