package ajax.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import board.model.EmpBoardDTO;
import board.service.EmpBoardService;
import emp.model.EmpDTO;

@WebServlet("/board/delete")
public class AjaxBoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private EmpBoardService boardService = new EmpBoardService();

	// 삭제 기능은 post만 있어도 됨
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; chrset=utf-8");
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		// 불러온 id에 해당하는 게시물을 삭제 해야 한다.
		
		EmpDTO empData = (EmpDTO) session.getAttribute("loginData");
		EmpBoardDTO data = boardService.getData(Integer.parseInt(id));
		
		// delete요청을 한 사용자랑 게시글 작성자가 같아야 한다.
		StringBuilder sb = new StringBuilder();	// 문자열로 보내려고 만든 거임
		sb.append("{");
		if(data.getEmpId() == empData.getEmpId()) {
			boolean result = boardService.boardDelete(data);
			
			if(result) { // 만약 삭제에 성공했다면 
				sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 성공"));
				sb.append(String.format("\"%s\": \"%s\",", "message", "게시물을 삭제 하였습니다."));
			}else { // 삭제 실패
				sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 오류"));
				sb.append(String.format("\"%s\": \"%s\",", "message", "해당 게시물을 삭제하는 중 문제가 발생하였습니다."));
			}
		}else {
			sb.append(String.format("\"%s\": \"%s\",", "title", "삭제 오류"));
			sb.append(String.format("\"%s\": \"%s\",", "message", "요청한 게시글을 삭제할 권한이 없습니다."));
		}
		
		sb.append("}");
		response.getWriter().append(sb.toString());
		response.getWriter().flush();
		
	}

}
