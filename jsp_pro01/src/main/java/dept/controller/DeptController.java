package dept.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts")
public class DeptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeptService service = new DeptService(); // 자바클래스 참조하는 변수를 생성하기 // 객체는 밖에 빼놓기 (doGet에 넣으면 사용자가 요청할 때마다 객체를 생성하니까)
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search = request.getParameter("search");
		
		
		List<DeptDTO> deptDatas = null;
		if(search == null) {
			deptDatas = service.getAll(); // 반환값이 DeptDTO 임 -> 근데 이게 여러행이 반환되는 거임
		}else {
			// 여러 방법으로 만들어주면 좋다.
			// DTO 타입 그대로 보내기
//			DeptDTO dto = new DeptDTO();
//			dto.setDeptId(Integer.parseInt(search));
			// int 형으로 형변환해서 보내기
//			DeptDTO iData = service.getId(Integer.parseInt(search));
			// 문자열로 보내기
			boolean isNumber = search.matches("\\d+"); // 정규표현식을 이용해서 숫자를 체크해준다.
																								//  -> 문자열 안에 작성할 때는 \(역슬레쉬)는 두 번 써야 된다. (이스케이프)
			if(isNumber) {
				DeptDTO data = service.getId(search); // 하나의 dto 객체를 위한 로직을 추가 하거나
				if(data != null) {
					deptDatas = new ArrayList<DeptDTO>(); 
					deptDatas.add(data); 							// 기존의 list만들었던 걸 활용하거나  -> 추천
				// ArrayList이지만 0번째 인덱스만 존재 -> data하나만 추가했으니까
				}
			}
		}
//		List<DeptDTO> deptDatas = service.getAll(); // 자바 클래스의 getAll 메소드 사용
		
		// controller에서 view(jsp)에게 db에서 받은 데이터를 전달해줘야 함
		// request 객체에게 속성을 설정하여 deptData를 저장한다. -> 그 뒤에 forward를 하면 request에 저장된 속성, 정보를 다 보내는 것 
		request.setAttribute("deptDatas", deptDatas);
		
		String view = "/WEB-INF/jsp/dept/index.jsp";
		request.getRequestDispatcher(view).forward(request, response); // 받은 데이터를 response 로 전달해야 함
	}
}
