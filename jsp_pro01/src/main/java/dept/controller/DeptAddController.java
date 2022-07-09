package dept.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dept.model.DeptDTO;
import dept.service.DeptService;

@WebServlet("/depts/add")
public class DeptAddController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private DeptService service = new DeptService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String view = "/WEB-INF/jsp/dept/add.jsp"; // jsp 파일 경로
		request.getRequestDispatcher(view).forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 사용자로부터 전달 받은 데이터를 service에 전달하고
		 * service는 전달 받은 데이터를 dao에 전달하고
		 * service에서는 필요한 경우 전달 받은 데이터에 대한 비지니스 로직을 수행 후 dao에 전달 할 수 있다.
		 * dao에서는 전달받은 데이터를 처리하기에 적절한 SQL구문을 찾아서 처리할 수 있게 한다.  
		 */
		// 사용자로부터 전달받은 데이터는 getParameter로 받아오고 
		// 
		String deptId = request.getParameter("deptId");
		String deptName = request.getParameter("deptName");
		String mngId = request.getParameter("mngId");
		String locationId = request.getParameter("locationId");
		
		DeptDTO data = service.addDept(deptId, deptName, mngId, locationId); 

		String view = "/WEB-INF/jsp/dept/add.jsp";
		if(data != null ) {
			/*
			 * 성공 이후의 처리 로직
			 * 		1. 등록되었습니다. 알람 -> 확인 -> 조회 목록 페이지  -> 이거 하려면 AJAX 사용해야 함 
			 * 		2. 리다이렉트를 사용하여 페이지를 이동하도록 함. 다시 원래 화면으로 이동(조회 목록 페이지)
			 */
				if(data.getDeptId() == -1) { // deptId가 중복되어서 제약조건 위배인 경우
						request.setAttribute("error",data);
						request.setAttribute("errorMsg", "부서코드 중복!");
						request.getRequestDispatcher(view).forward(request, response); 
				}else if (data.getMngId() == -1) {	
						request.setAttribute("error",data);
						request.setAttribute("errorMsg", "해당 관리자 ID 없음");
						request.getRequestDispatcher(view).forward(request, response); 
				}else if (data.getLocationId() == -1) {
						request.setAttribute("error",data);
						request.setAttribute("errorMsg", "해당 지역 ID 없음");
						request.getRequestDispatcher(view).forward(request, response); 
				}else { 
					//제약에 위배되지 않는 경우 -> 성공한 경우 로직
					response.sendRedirect("jsp01/depts?search=" + data.getDeptId()); 
					// 클라이언트가 서버에게 다시 요청을 해서 -> 서버는 해당 url을 다시 보여주는 것 -> 이전의 내용을 보존할 수 없다.
				}
		}else {
			// 실패 이후의 처리 로직 -> 기존 페이지를 유지하면서 사용자가 입력했던 데이터도 최대한 보존
			// 데이터를 보존하기 위해서는 redirect를 사용하면 안 됨(다 사라짐) -> forward 해서 다시 jsp에 전달을 -> jsp에서는 해당 내용은 유지할 수 있도록 value 를 설정해놓는다.
			System.out.print("실패하였습니다.");
			request.getRequestDispatcher(view).forward(request, response); 
		}
	}
}
